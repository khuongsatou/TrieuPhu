package com.nvk.doanailatrieuphu.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nvk.doanailatrieuphu.Adapter.LinhVucAdapter;
import com.nvk.doanailatrieuphu.Model.LinhVuc;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;
import com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nvk.doanailatrieuphu.Activity.MangHinhChinhActivity.KEY_LIMIT;
import static com.nvk.doanailatrieuphu.Activity.MangHinhChinhActivity.KEY_PAGE;
import static com.nvk.doanailatrieuphu.Activity.MangHinhChinhActivity.KEY_REQUESTCODE;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.KEY_DANGNHAP;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.LIMIT_KHOI_TAO;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.PAGE_KHOI_TAO;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.PAGE_SIZE;
import static com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis.BASE;
import static com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis.URI_LINH_VUC;

public class MangHinhTroChoiActivity extends AppCompatActivity {
    private TextView tvTen,tvTinDung;
    private RecyclerView rcvLinhVuc;
    private LinhVucAdapter adapter;
    private List<LinhVuc> linhVucs = new ArrayList<>();
    private NguoiChoi nguoiChoi;

    private boolean checkLoading = false;
    private boolean checkLastPage = false;
    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mang_hinh_tro_choi);

        radiation();
        showNameAndCredit();
        createAdapter();
        loadData(null);
        checkScroll();
    }

    private void checkScroll() {
        rcvLinhVuc.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager manager = (LinearLayoutManager) rcvLinhVuc.getLayoutManager();
                int countItem = manager.getItemCount();
                int countChild = manager.getChildCount();
                int findNextChild = manager.findFirstVisibleItemPosition();
                if ((findNextChild+countChild) >= countItem && findNextChild >=0 && countItem >= PAGE_SIZE){
                    if(!checkLoading && !checkLastPage){
                        checkLoading = true;
                        currentPage++;

                        linhVucs.add(null);
                        adapter.notifyItemInserted(linhVucs.size()-1);

                        Bundle data = new Bundle();
                        data.putInt(KEY_PAGE,currentPage);
                        data.putInt(KEY_LIMIT,PAGE_SIZE);
                        loadData(data);
                    }
                }
            }
        });
    }

    private void loadData(Bundle data) {
        if (NetWorkUtilitis.checkConnect(this)){
            startVolley(data);
        }else{
            NetWorkUtilitis.showDialogNetWork(getString(R.string.tb_connect_internet),this);
        }
    }

    private void startVolley(Bundle data) {
        final Map<String,String> map =new HashMap<>();
        map.put(KEY_PAGE,String.valueOf(data == null ? PAGE_KHOI_TAO : data.getInt(KEY_PAGE)));
        map.put(KEY_LIMIT,String.valueOf(data == null ? LIMIT_KHOI_TAO : data.getInt(KEY_LIMIT)));
        StringRequest request = new StringRequest(Request.Method.POST,BASE + URI_LINH_VUC, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("AAAAA",response);
                try {
                    JSONObject objLinhVuc = new JSONObject(response);
                    int total = objLinhVuc.getInt("total");
                    int totalPage = total/PAGE_SIZE;
                    if (totalPage % PAGE_SIZE != 0){
                        totalPage++;
                    }
                    if (linhVucs.size() > 0){
                        linhVucs.remove(linhVucs.size()-1);
                        adapter.notifyItemRemoved(linhVucs.size());
                    }

                    JSONArray arrNguoiChoi = objLinhVuc.getJSONArray("linh_vuc");
                    for (int i = 0; i <arrNguoiChoi.length() ; i++) {
                        JSONObject objItem = arrNguoiChoi.getJSONObject(i);
                        int id = objItem.getInt("id");
                        String tenLinhVuc = objItem.getString("ten_linh_vuc");
                        LinhVuc linhVuc = new LinhVuc();
                        linhVuc.setId(id);
                        linhVuc.setTenLinhVuc(tenLinhVuc);
                        linhVucs.add(linhVuc);
                    }
                    checkFinishLoading(totalPage);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Server Offline",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };

        RequestQueue  queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void checkFinishLoading(int totalPage) {
        checkLoading = false;
        checkLastPage=(currentPage == totalPage);
    }

    private void createAdapter() {
        adapter = new LinhVucAdapter(this,linhVucs,this.nguoiChoi);
        rcvLinhVuc.setLayoutManager(new LinearLayoutManager(this));
        rcvLinhVuc.setHasFixedSize(true);
        rcvLinhVuc.setAdapter(adapter);
    }

    private void showNameAndCredit() {
        //Muốn chạy thì bật chỗ này nhé
        this.nguoiChoi = (NguoiChoi) getIntent().getSerializableExtra(KEY_DANGNHAP);
        tvTen.setText(this.nguoiChoi.getTenDangNhap());
        tvTinDung.setText(this.nguoiChoi.getCredit()+"");
    }

    private void radiation() {
        View vHeader = findViewById(R.id.vHeader);
        tvTen =  vHeader.findViewById(R.id.tvTen);
        tvTinDung = vHeader.findViewById(R.id.tvTinDung);
        rcvLinhVuc = findViewById(R.id.rcvLinhVuc);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == KEY_REQUESTCODE && resultCode == RESULT_OK && data!=null){
            this.nguoiChoi = (NguoiChoi) data.getSerializableExtra(KEY_DANGNHAP);
            tvTinDung.setText(this.nguoiChoi.getCredit()+"");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(KEY_DANGNHAP,nguoiChoi);
        setResult(RESULT_OK,intent);
        finish();
    }
}
