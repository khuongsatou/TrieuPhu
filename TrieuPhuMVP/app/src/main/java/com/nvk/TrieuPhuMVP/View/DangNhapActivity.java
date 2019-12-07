package com.nvk.TrieuPhuMVP.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.Presenter.DangNhapPresenter;
import com.nvk.TrieuPhuMVP.R;
import com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis;

import java.io.Serializable;
import java.util.Map;

import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_DANGNHAP;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.checkConnect;

public class DangNhapActivity extends AppCompatActivity  implements DangNhapView,View.OnClickListener{
    private EditText edtTenDangNhap, edtMatKhau;
    private Button btnDangNhap, btnDangKy, btnQuenMatKhau;
    private DangNhapPresenter dangNhapPresenter = new DangNhapPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);


        innitView();
        innitAction();

    }

    private void innitAction() {
        btnDangNhap.setOnClickListener(this);
        btnDangKy.setOnClickListener(this);
        btnQuenMatKhau.setOnClickListener(this);
    }

    private void innitView() {
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
                dangNhapPresenter.login(tenDangNhap, matKhau);
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
    public void navigate(NguoiChoi nguoiChoi) {
        Intent intent = new Intent(this,MangHinhChinhActivity.class);
        intent.putExtra(KEY_DANGNHAP, (Serializable) nguoiChoi);
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
    public void setErrorInternet() {
        Toast.makeText(this, "Internet đã tắt", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setErrorServer() {
        Toast.makeText(this, "Server không phản hồi", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean checkInternet() {
        return checkConnect(this);
    }

    @Override
    public void closeApp() {
        NetWorkUtilitis.showDialogNetWork("Internet không được bật", this).show();
    }

    @Override
    public ProgressDialog showDialog() {
        return  NetWorkUtilitis.showProress(this);
}

    @Override
    public void closeDialog(ProgressDialog dialog) {
        dialog.dismiss();
    }

    @Override
    public void loadBackGround(StringRequest request) {
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}


