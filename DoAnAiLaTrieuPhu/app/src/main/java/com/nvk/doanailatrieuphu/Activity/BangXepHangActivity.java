package com.nvk.doanailatrieuphu.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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
import com.nvk.doanailatrieuphu.Adapter.BangXepHangAdapter;
import com.nvk.doanailatrieuphu.Controller.NguoiChoiController;
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
import static com.nvk.doanailatrieuphu.Activity.MangHinhChinhActivity.KEY_LIMIT;
import static com.nvk.doanailatrieuphu.Activity.MangHinhChinhActivity.KEY_PAGE;
import static com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis.BASE;
import static com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis.PAGE_SIZE;
import static com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis.URI_BANG_XEP_HANG;

public class BangXepHangActivity extends AppCompatActivity {
    private BangXepHangAdapter bangXepHangAdapter;
    private RecyclerView rcvBangXepHang;
    private List<NguoiChoi> nguoiChois = new ArrayList<>();;
    private TextView tvTen,tvTinDung;
    private boolean checkLoading = false;
    private boolean checkLastPage = false;
    private int currentPage = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bang_xep_hang);

        Radiation();
        showUserAndCredit();
        CreateAdapter();
        loadData(null);
        checkScroll();
    }

    private void checkScroll() {
        rcvBangXepHang.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager manager = (LinearLayoutManager) rcvBangXepHang.getLayoutManager();
                int countItem = manager.getItemCount();
                int countChild = manager.getChildCount();
                int findNextChild = manager.findFirstVisibleItemPosition();
                if ((countChild+ findNextChild) >= countItem && findNextChild >=0 && countItem >= PAGE_SIZE){
                    if (!checkLoading && !checkLastPage){
                        checkLoading=true;
                        currentPage++;

                        nguoiChois.add(null);
                        bangXepHangAdapter.notifyItemInserted(nguoiChois.size()-1);

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
        final Map<String,Integer> startMap = new HashMap<>();
        startMap.put(KEY_PAGE,(data == null ? 1 : data.getInt(KEY_PAGE)));
        startMap.put(KEY_LIMIT,(data == null ? 3 : data.getInt(KEY_LIMIT)));

        StringRequest request = new StringRequest(Request.Method.POST, BASE + URI_BANG_XEP_HANG, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject objNguoiChoi = new JSONObject(response);
                    int total = objNguoiChoi.getInt("total");
                    int totalPage = total / PAGE_SIZE;
                    if (totalPage % PAGE_SIZE != 0){
                        totalPage++;
                    }

                    if (nguoiChois.size() > 0){
                        nguoiChois.remove(nguoiChois.size()-1);
                        bangXepHangAdapter.notifyItemRemoved(nguoiChois.size());
                    }

                    JSONArray arrNguoiChoi = objNguoiChoi.getJSONArray("nguoi_choi");
                    for (int i = 0; i < arrNguoiChoi.length(); i++) {
                        JSONObject objItem = arrNguoiChoi.getJSONObject(i);
                        int id = objItem.getInt("id");
                        String tenDangNhap = objItem.getString("ten_dang_nhap");
                        String hinhDaiDien = objItem.getString("hinh_dai_dien");
                        int diemCaoNhat = objItem.getInt("diem_cao_nhat");

                        NguoiChoi nguoiChoi = new NguoiChoi();
                        nguoiChoi.setId(id);
                        nguoiChoi.setTenDangNhap(tenDangNhap);
                        nguoiChoi.setDiemCaoNhat(diemCaoNhat);
                        nguoiChoi.setHinhDaiDien(hinhDaiDien);
                        nguoiChois.add(nguoiChoi);
                    }

                    bangXepHangAdapter.notifyDataSetChanged();
                    checkFinishLoading(totalPage);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Server Offline",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put(KEY_PAGE,String.valueOf(startMap.get(KEY_PAGE)));
                map.put(KEY_LIMIT,String.valueOf(startMap.get(KEY_LIMIT)));

                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void checkFinishLoading(int totalPage) {
        checkLoading = false;
        checkLastPage = (currentPage == totalPage);
    }

    private void showUserAndCredit() {
        NguoiChoi nguoiChoi = (NguoiChoi) getIntent().getSerializableExtra(KEY_DANGNHAP);
        tvTen.setText(nguoiChoi.getTenDangNhap());
        tvTinDung.setText(nguoiChoi.getCredit()+"");
    }

    private void CreateAdapter() {
        rcvBangXepHang.setLayoutManager(new LinearLayoutManager(this));
        bangXepHangAdapter = new BangXepHangAdapter(this,nguoiChois);
        rcvBangXepHang.setAdapter(bangXepHangAdapter);
    }

    private void Radiation() {
        View vHeaderNormal = findViewById(R.id.vHeaderNormal);
        tvTen = vHeaderNormal.findViewById(R.id.tvTen);
        tvTinDung = vHeaderNormal.findViewById(R.id.tvTinDung);
        rcvBangXepHang = findViewById(R.id.rcvBangXepHang);

    }


}
