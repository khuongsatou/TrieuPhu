package com.nvk.doanailatrieuphu.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nvk.doanailatrieuphu.R;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static com.nvk.doanailatrieuphu.Controller.NguoiChoiController.COLUMN_EMAIL;
import static com.nvk.doanailatrieuphu.Controller.NguoiChoiController.COLUMN_TEN_DANG_NHAP;
import static com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis.BASE;
import static com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis.URI_MAT_KHAU;

public class QuenMatKhauActivity extends AppCompatActivity {
    private EditText edtTenDangNhap, edtEmail;
    private Button btnClose;
    private TextView tvHienThiPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);
        Radiation();
    }

    private void Radiation() {
        edtTenDangNhap = findViewById(R.id.edtTenDangNhap);
        edtEmail = findViewById(R.id.edtEmail);
    }


    public void QuenMatKhau(View view) {
        // lấy text
        final String tenDangNhap = edtTenDangNhap.getText().toString();
        final String email = edtEmail.getText().toString();
        //kiểm tra tồn tại
        //Kiểm tra tài khoản có chưa
        if (tenDangNhap.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Không Được Để Trống", Toast.LENGTH_LONG).show();
            return;
        } else {
            StringRequest request = new StringRequest(Request.Method.POST, BASE + URI_MAT_KHAU, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject objLogin = new JSONObject(response);
                        boolean result = objLogin.getBoolean("success");
                        if (result) {
                            JSONObject objNguoiChoi = objLogin.getJSONObject("nguoi_choi");
                            String matKhau = objNguoiChoi.getString("mat_khau");
                            showDialogResetPass(matKhau).show();
                        } else {
                            Toast.makeText(getApplicationContext(),getString(R.string.tb_quen_mat_khau_tb), Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("AAAAA", error.getMessage());
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put(COLUMN_TEN_DANG_NHAP, tenDangNhap);
                    map.put(COLUMN_EMAIL, email);
                    return map;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(request);
        }
    }

    private AlertDialog showDialogResetPass(String matKhau) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.custom_dialog_quen_mat_khau, null, false);
        btnClose = view.findViewById(R.id.btnClose);
        tvHienThiPass = view.findViewById(R.id.tvHienThiPass);
        builder.setView(view);
        tvHienThiPass.setText(matKhau);
        Toast.makeText(this, getString(R.string.tb_quen_mat_khau_tc), Toast.LENGTH_LONG).show();
        final AlertDialog dialog = builder.create();
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        return dialog;
    }
}
