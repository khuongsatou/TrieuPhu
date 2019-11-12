package com.nvk.doanailatrieuphu.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nvk.doanailatrieuphu.Adapter.MuaCreditAdapter;
import com.nvk.doanailatrieuphu.Controller.MuaCreditController;
import com.nvk.doanailatrieuphu.Controller.NguoiChoiController;
import com.nvk.doanailatrieuphu.Model.GoiCredit;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;
import com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.KEY_DANGNHAP;
import static com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis.BASE;
import static com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis.URI_CREDIT_DANH_SACH;

public class MuaCreaditActivity extends AppCompatActivity {
    private RecyclerView rcvShowCredit;
    private MuaCreditAdapter muaCreditAdapter;
    private List<GoiCredit> goiCredits = new ArrayList<>();
    private NguoiChoi nguoiChoi;
    public TextView tvTen,tvTinDung;
    public int id_nguoiChoi = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mua_credit);

        radiation();
        showUserAndCredit();
        createrAdapter();
        loadData();
    }

    private void loadData() {
        if (NetWorkUtilitis.checkConnect(this)){
            startVolley();
        }else{
            NetWorkUtilitis.showDialogNetWork(getString(R.string.tb_connect_internet),this);
        }

    }

    private void startVolley() {
        StringRequest request = new StringRequest(Request.Method.POST, BASE + URI_CREDIT_DANH_SACH, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject objGoiCredit = new JSONObject(response);
                    JSONArray arrGoiCredit = objGoiCredit.getJSONArray("goi_credit");
                    for (int i = 0; i < arrGoiCredit.length(); i++) {
                        JSONObject objItem = arrGoiCredit.getJSONObject(i);
                        int id = objItem.getInt("id");
                        String tenGoi = objItem.getString("ten_goi");
                        int credit = objItem.getInt("credit");
                        int soTien = objItem.getInt("so_tien");

                        GoiCredit goiCredit = new GoiCredit();
                        goiCredit.setId(id);
                        goiCredit.setTen_goi(tenGoi);
                        goiCredit.setCredit(credit);
                        goiCredit.setSo_tien(soTien);

                        goiCredits.add(goiCredit);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                muaCreditAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Server Offline",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void showUserAndCredit() {
        this.nguoiChoi = (NguoiChoi) getIntent().getSerializableExtra(KEY_DANGNHAP);
        this.id_nguoiChoi = nguoiChoi.getId();
        tvTen.setText(nguoiChoi.getTenDangNhap());
        tvTinDung.setText(nguoiChoi.getCredit()+"");
    }




    private void createrAdapter() {
        muaCreditAdapter = new MuaCreditAdapter(goiCredits,this,this.nguoiChoi);
        rcvShowCredit.setLayoutManager(new GridLayoutManager(this,2));
        rcvShowCredit.setAdapter(muaCreditAdapter);
    }

    private void radiation() {
        rcvShowCredit = findViewById(R.id.rcvShowCredit);
        View vHeaderNormal = findViewById(R.id.vHeaderNormal);
        tvTen = vHeaderNormal.findViewById(R.id.tvTen);
        tvTinDung = vHeaderNormal.findViewById(R.id.tvTinDung);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        this.nguoiChoi.setCredit(Integer.parseInt(tvTinDung.getText().toString()));
        intent.putExtra(KEY_DANGNHAP,this.nguoiChoi);
        setResult(RESULT_OK,intent);
        finish();
    }
}
