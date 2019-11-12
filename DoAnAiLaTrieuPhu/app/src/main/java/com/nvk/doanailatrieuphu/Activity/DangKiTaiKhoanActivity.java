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
import com.nvk.doanailatrieuphu.Controller.NguoiChoiController;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.nvk.doanailatrieuphu.Controller.NguoiChoiController.COLUMN_EMAIL;
import static com.nvk.doanailatrieuphu.Controller.NguoiChoiController.COLUMN_MAT_KHAU;
import static com.nvk.doanailatrieuphu.Controller.NguoiChoiController.COLUMN_TEN_DANG_NHAP;
import static com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis.BASE;

public class DangKiTaiKhoanActivity extends AppCompatActivity {
    private static final String URI_NGUOI_CHOI_THEM ="nguoi_choi/them" ;
    private EditText edtTenDangNhap,edtEmail,edtMatKhau,edtXacNhanMatKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki_tai_khoan);

        Radiation();
    }

    private void Radiation() {
        edtTenDangNhap = findViewById(R.id.edtTenDangNhap);
        edtEmail = findViewById(R.id.edtEmail);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        edtXacNhanMatKhau = findViewById(R.id.edtXacNhanMatKhau);
    }

    public void DangKy(View view) {
        //Lấy text
        final String tenDangNhap = edtTenDangNhap.getText().toString().trim();
        final String email = edtEmail.getText().toString().trim();
        final String matKhau = edtMatKhau.getText().toString().trim();
        String xacNhanMatKhau = edtXacNhanMatKhau.getText().toString().trim();

        if (tenDangNhap.equals("") || email.equals("") || matKhau.equals("") || xacNhanMatKhau.equals("")) {
            Toast.makeText(getApplicationContext(), getString(R.string.tb_chua_nhap_du),Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (!matKhau.equals(xacNhanMatKhau)){
                Toast.makeText(getApplicationContext(), getString(R.string.tb_mat_khau_khong_giong_nhau),Toast.LENGTH_SHORT).show();
            }else{
                StringRequest request = new StringRequest(Request.Method.POST, BASE + URI_NGUOI_CHOI_THEM, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject objLogin = new JSONObject(response);
                            boolean result = objLogin.getBoolean("success");
                            if (result) {
                                Toast.makeText(getApplicationContext(),tenDangNhap+" Tạo Thành Công", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(),getString(R.string.tb_dang_ky_tb) + " Hoặc User Tồn Tại", Toast.LENGTH_LONG).show();
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
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put(COLUMN_TEN_DANG_NHAP, tenDangNhap);
                        map.put(COLUMN_EMAIL, email);
                        map.put(COLUMN_MAT_KHAU, matKhau);
                        return map;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(this);
                queue.add(request);
                }
            }

    }
}
