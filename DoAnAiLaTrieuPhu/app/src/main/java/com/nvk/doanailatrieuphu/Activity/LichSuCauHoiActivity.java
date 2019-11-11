package com.nvk.doanailatrieuphu.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nvk.doanailatrieuphu.Adapter.BangXepHangAdapter;
import com.nvk.doanailatrieuphu.Adapter.LichSuChoiAdapter;
import com.nvk.doanailatrieuphu.Controller.LichSuChoiController;
import com.nvk.doanailatrieuphu.Model.LuotChoi;
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

import static com.nvk.doanailatrieuphu.Activity.DangNhapActivity.KEY_DANGNHAP;
import static com.nvk.doanailatrieuphu.Controller.LichSuChoiController.COLUMN_NGUOI_CHOI_ID;
import static com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis.BASE;

public class LichSuCauHoiActivity extends AppCompatActivity {
    private static final String KEY_PAGE = "page";
    private static final String KEY_LIMIT = "limit";
    private static final String URI_LUOT_CHOI = "luot_choi/tim";
    private LichSuChoiAdapter lichSuChoiAdapter;
    private List<LuotChoi> luotChois = new ArrayList<>();;
    private RecyclerView rcvLichSuChoi;
    private int id_nguoiChoi = 0;
    private TextView tvTen,tvTinDung;

    private boolean checkLoading = false;
    private boolean checkLastPage = false;
    private int currentPage = 1;
    private static final int PAGE_SIZE = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_choi);

        Radiation();
        showUserVaCredit();
        CreateAdapter();
        loadData(null);
        checkScroll();
    }

    private void checkScroll() {
        rcvLichSuChoi.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager manager = (LinearLayoutManager) rcvLichSuChoi.getLayoutManager();
                int countItem = manager.getItemCount();
                int countChild = manager.getChildCount();
                int findNextChild = manager.findFirstVisibleItemPosition();
                if ((findNextChild+countChild)>=countItem && findNextChild >=0 && countItem >=PAGE_SIZE) {
                    if (!checkLoading && !checkLastPage){
                        checkLoading = true;
                        currentPage++;
                        luotChois.add(null);
                        lichSuChoiAdapter.notifyItemInserted(luotChois.size()-1);
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
            NetWorkUtilitis.showDialogNetWork("Không có kết nối internet",this);
        }

    }

    private void startVolley(Bundle data) {
        final Map<String,Integer> startMap = new HashMap<>();
        startMap.put(COLUMN_NGUOI_CHOI_ID,this.id_nguoiChoi);
        startMap.put(KEY_PAGE,(data == null ? 1 : data.getInt(KEY_PAGE)));
        startMap.put(KEY_LIMIT,(data == null ? 3 : data.getInt(KEY_LIMIT)));
        StringRequest request = new StringRequest(Request.Method.POST, BASE + URI_LUOT_CHOI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject objLuotChoi = new JSONObject(response);
                    int total = objLuotChoi.getInt("total");
                    int totalPage = total/PAGE_SIZE;
                    if (totalPage % PAGE_SIZE != 0){
                        totalPage++;
                    }

                    if (luotChois.size() > 0){
                        luotChois.remove(luotChois.size()-1);
                        lichSuChoiAdapter.notifyItemRemoved(luotChois.size());
                    }

                    JSONArray arrLuotChoi = objLuotChoi.getJSONArray("luot_choi");
                    for (int i = 0; i < arrLuotChoi.length() ; i++) {
                        JSONObject objItem = arrLuotChoi.getJSONObject(i);
                        int id = objItem.getInt("id");
                        int nguoiChoiID = objItem.getInt("nguoi_choi_id");
                        int soCau = objItem.getInt("so_cau");
                        int diem = objItem.getInt("diem");
                        String ngayGio = objItem.getString("ngay_gio");
                        LuotChoi luotChoi = new LuotChoi();
                        luotChoi.setId(id);
                        luotChoi.setNguoiChoiId(nguoiChoiID);
                        luotChoi.setDiem(diem);
                        luotChoi.setSoCau(soCau);
                        luotChoi.setNgayGio(ngayGio);

                        luotChois.add(luotChoi);
                    }

                    checkFinishLoad(totalPage);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                lichSuChoiAdapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put(COLUMN_NGUOI_CHOI_ID,String.valueOf(startMap.get(COLUMN_NGUOI_CHOI_ID)));
                map.put(KEY_PAGE,String.valueOf(startMap.get(KEY_PAGE)));
                map.put(KEY_LIMIT,String.valueOf(startMap.get(KEY_LIMIT)));
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void checkFinishLoad(int totalPage) {
        checkLastPage = (currentPage == totalPage);
        checkLoading = false;
    }

    private void showUserVaCredit() {
        NguoiChoi nguoiChoi = (NguoiChoi) getIntent().getSerializableExtra(KEY_DANGNHAP);
        this.id_nguoiChoi = nguoiChoi.getId();
        tvTen.setText(nguoiChoi.getTenDangNhap());
        tvTinDung.setText(nguoiChoi.getCredit()+"");
    }

    private void CreateAdapter() {
        rcvLichSuChoi.setLayoutManager(new LinearLayoutManager(this));
        lichSuChoiAdapter = new LichSuChoiAdapter(this,luotChois);
        rcvLichSuChoi.setAdapter(lichSuChoiAdapter);
    }

    private void Radiation() {
        View vHeaderNormal = findViewById(R.id.vHeaderNormal);
        tvTen = vHeaderNormal.findViewById(R.id.tvTen);
        tvTinDung = vHeaderNormal.findViewById(R.id.tvTinDung);
        rcvLichSuChoi = findViewById(R.id.rcvLichSuChoi);
    }
}
