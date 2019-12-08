package com.nvk.TrieuPhuMVP.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nvk.TrieuPhuMVP.Presenter.DangKyPresenter;
import com.nvk.TrieuPhuMVP.R;
import com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis;
import com.nvk.TrieuPhuMVP.View.UI.DangKyView;

public class DangKyActivity extends AppCompatActivity implements DangKyView, View.OnClickListener{
    private EditText edtTenDangNhapDK,edtMatKhauDK,edtEmailDK,edtXacNhanMatKhauDK;
    private Button btnDangKyDK;
    private DangKyPresenter dangKyPresenter = new DangKyPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        initView();
        initAction();
    }

    private void initAction() {
        btnDangKyDK.setOnClickListener(this);
    }

    private void initView() {
        edtTenDangNhapDK = findViewById(R.id.edtTenDangNhapDK);
        edtMatKhauDK = findViewById(R.id.edtMatKhauDK);
        edtEmailDK = findViewById(R.id.edtEmailDK);
        edtXacNhanMatKhauDK = findViewById(R.id.edtXacNhanMatKhauDK);
        btnDangKyDK = findViewById(R.id.btnDangKyDK);
    }

    @Override
    public void onClick(View v) {
        dangKyPresenter.handleRegister(
                edtTenDangNhapDK.getText().toString().trim(),
                edtEmailDK.getText().toString().trim(),
                edtMatKhauDK.getText().toString().trim(),
                edtXacNhanMatKhauDK.getText().toString().trim()
        );
    }

    @Override
    public boolean existUserName(String username) {

        return false;
    }

    @Override
    public boolean existEmail(String email) {

        return false;
    }

    @Override
    public boolean checkRepass(String pass,String repass) {
        return (pass.equals(repass));
    }

    @Override
    public void setErrorRepass() {
        edtXacNhanMatKhauDK.setError("Không khớp với mật khẩu");
        edtXacNhanMatKhauDK.requestFocus();
    }

    @Override
    public void registerSuccess() {
        Toast.makeText(this,"Đăng kí thành công",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerFail() {
        Toast.makeText(this,"Đăng kí thất bại",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setErrorUsername() {
        edtTenDangNhapDK.setError("Bạn chưa nhập");
        edtTenDangNhapDK.requestFocus();
    }

    @Override
    public void setErrorPassword() {
        edtMatKhauDK.setError("Bạn chưa nhập");
        edtMatKhauDK.requestFocus();
    }

    @Override
    public void setErrorEmail() {
        edtEmailDK.setError("Bạn chưa nhập");
        edtEmailDK.requestFocus();
    }

    @Override
    public void clearForm() {
        edtEmailDK.setText("");
        edtTenDangNhapDK.setText("");
        edtMatKhauDK.setText("");
        edtXacNhanMatKhauDK.setText("");
    }

    @Override
    public void setErrorInternet() {
        Toast.makeText(this, "Bạn chưa kết nối Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setErrorServer() {
        Toast.makeText(this, "Server offline", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean checkInternet() {
        return false;
    }

    @Override
    public void closeApp() {

    }

    @Override
    public void closeDialog(ProgressDialog dialog) {
        dialog.dismiss();
    }

    @Override
    public void loadBackGround(StringRequest request) {
        RequestQueue  requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    @Override
    public ProgressDialog showDialog() {
        return NetWorkUtilitis.showProress(this);
    }


}
