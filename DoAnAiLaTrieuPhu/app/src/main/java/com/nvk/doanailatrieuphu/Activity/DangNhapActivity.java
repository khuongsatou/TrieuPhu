package com.nvk.doanailatrieuphu.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;
import com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nvk.doanailatrieuphu.Controller.NguoiChoiController.COLUMN_MAT_KHAU;
import static com.nvk.doanailatrieuphu.Controller.NguoiChoiController.COLUMN_TEN_DANG_NHAP;
import static com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis.BASE;

public class DangNhapActivity extends AppCompatActivity {
    public static final String KEY_DANGNHAP = "dn" ;
    public static final String KEY_SUCCESS  = "success";
    public static final String KEY_URI_DANG_NHAP  = "nguoi_choi/dang_nhap";
    private EditText edtTenDangNhap,edtMatKhau;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);


        Radiation();
        CheckConnectAPI();
    }

    private void CheckConnectAPI() {
        if (!NetWorkUtilitis.checkConnect(this)){
            NetWorkUtilitis.showDialogNetWork("Không có kết nối internet",this).show();
        }
    }


    private void Radiation() {
        edtTenDangNhap = findViewById(R.id.edtTenDangNhap);
        edtMatKhau = findViewById(R.id.edtMatKhau);
    }


    public void XuLiDangNhap(View v){
        //Lấy text
        final String tenDangNhap = edtTenDangNhap.getText().toString();
        final String matKhau = edtMatKhau.getText().toString();
        //Kiểm tra tài khoản có chưa
        if (tenDangNhap.isEmpty() || matKhau.isEmpty()){
            Toast.makeText(this,"Không Được Để Trống",Toast.LENGTH_LONG).show();
            return;
        }else{

            StringRequest request = new StringRequest(Request.Method.POST, BASE + KEY_URI_DANG_NHAP, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        if(!NetWorkUtilitis.checkConnect(getApplicationContext())){
                            NetWorkUtilitis.showDialogNetWork("Đừng Tắt InterNet Chứ",DangNhapActivity.this).show();

                        }else {


                            JSONObject objLogin = new JSONObject(response);
                            boolean result = objLogin.getBoolean("success");
                            if (result) {
                                JSONObject objNguoiChoi = objLogin.getJSONObject("nguoi_choi");
                                int id = objNguoiChoi.getInt("id");
                                String tenDangNhap = objNguoiChoi.getString("ten_dang_nhap");
                                String matKhau = objNguoiChoi.getString("mat_khau");
                                String email = objNguoiChoi.getString("email");
                                String hinhDaiDien = objNguoiChoi.getString("hinh_dai_dien");
                                int diemCaoNhat = objNguoiChoi.getInt("diem_cao_nhat");
                                int credit = objNguoiChoi.getInt("credit");
                                NguoiChoi nguoiChoi = new NguoiChoi();
                                nguoiChoi.setId(id);
                                nguoiChoi.setTenDangNhap(tenDangNhap);
                                nguoiChoi.setMatKhau(matKhau);
                                nguoiChoi.setEmail(email);
                                nguoiChoi.setHinhDaiDien(hinhDaiDien);
                                nguoiChoi.setCredit(credit);
                                nguoiChoi.setDiemCaoNhat(diemCaoNhat);

                                Intent intent = new Intent(getApplicationContext(), MangHinhChinhActivity.class);
                                intent.putExtra(KEY_DANGNHAP, (Serializable) nguoiChoi);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), getString(R.string.tb_dang_nhap), Toast.LENGTH_LONG).show();
                            }
                        }
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
                    map.put(COLUMN_TEN_DANG_NHAP,tenDangNhap);
                    map.put(COLUMN_MAT_KHAU,matKhau);
                    return map;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(request);
        }
    }

    public void XuLiDangKi(View view) {
        startActivity(new Intent(this,DangKiTaiKhoanActivity.class));
    }

    public void XuLiQuenMatKhau(View view) {
        startActivity(new Intent(this,QuenMatKhauActivity.class));
    }
}
