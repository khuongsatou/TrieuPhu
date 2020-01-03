package com.nvk.TrieuPhuMVP.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.Presenter.DangNhapPresenter;
import com.nvk.TrieuPhuMVP.R;
import com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis;
import com.nvk.TrieuPhuMVP.View.UI.DangNhapView;

import java.io.Serializable;

import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_DANGNHAP;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_SHARE_PRE;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.TOKEN;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.checkConnect;

public class DangNhapActivity extends AppCompatActivity  implements DangNhapView,View.OnClickListener{
    private EditText edtTenDangNhap, edtMatKhau;
    private Button btnDangNhap, btnDangKy, btnQuenMatKhau;
    private DangNhapPresenter dangNhapPresenter = new DangNhapPresenter(this);
    public SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);


        initView();
        initAction();

    }

    private void initAction() {
        btnDangNhap.setOnClickListener(this);
        btnDangKy.setOnClickListener(this);
        btnQuenMatKhau.setOnClickListener(this);

    }

    private void initView() {
        sharedPreferences = getSharedPreferences(KEY_SHARE_PRE, MODE_PRIVATE);
        edtTenDangNhap = findViewById(R.id.edtTenDangNhap);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangKy = findViewById(R.id.btnDangKy);
        btnQuenMatKhau = findViewById(R.id.btnQuenMatKhau);
        dangNhapPresenter.checkInternetAPP();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDangNhap:
                String tenDangNhap = edtTenDangNhap.getText().toString().trim();
                String matKhau = edtMatKhau.getText().toString().trim();
                dangNhapPresenter.handleLogin(tenDangNhap, matKhau);
                break;
            case R.id.btnDangKy:
                startActivity(new Intent(this, DangKyActivity.class));
                break;
            case R.id.btnQuenMatKhau:
                startActivity(new Intent(this, QuenMatKhauAcitivy.class));
        }
    }

    @Override
    public void loginFail() {
        Toast.makeText(this, "Tên tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveReference(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN,token);
        editor.apply();
    }

    @Override
    public void navigate() {
        Intent intent = new Intent(this,MangHinhChinhActivity.class);
        startActivity(intent);
    }

    @Override
    public void setErrorUsername() {
        edtTenDangNhap.setError("Vui lòng nhập tên đăng nhập");
    }

    @Override
    public void setErrorPassword() {
        edtMatKhau.setError("Vui lòng nhập mật khẩu");
    }

    @Override
    public void setErrorEmail() {

    }

    @Override
    public void clearForm() {

    }

    @Override
    public void setErrorInternet() {
        Toast.makeText(this,getString(R.string.string_server_internet),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setErrorServer() {
        Toast.makeText(this,getString(R.string.string_server_disconnect),Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean checkInternet() {
        return NetWorkUtilitis.checkConnect(this);
    }

    @Override
    public void closeApp() {
        NetWorkUtilitis.showDialogNetWork(getString(R.string.string_server_internet), this).show();
    }

    @Override
    public void closeDialog(ProgressDialog dialog) {
        dialog.dismiss();
    }

    @Override
    public void loadBackGround(StringRequest request) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    @Override
    public ProgressDialog showDialog() {
        return NetWorkUtilitis.showProress(this);
    }
}


