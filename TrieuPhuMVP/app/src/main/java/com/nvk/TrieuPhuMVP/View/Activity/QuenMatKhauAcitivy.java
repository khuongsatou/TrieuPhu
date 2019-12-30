package com.nvk.TrieuPhuMVP.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nvk.TrieuPhuMVP.Presenter.QuenMatKhauPresenter;
import com.nvk.TrieuPhuMVP.R;
import com.nvk.TrieuPhuMVP.Utilities.EmptyForm;
import com.nvk.TrieuPhuMVP.Utilities.InternetBackground;
import com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis;
import com.nvk.TrieuPhuMVP.View.UI.QuenMatKhauView;

import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.checkConnect;

public class QuenMatKhauAcitivy extends AppCompatActivity implements View.OnClickListener, EmptyForm, InternetBackground, QuenMatKhauView {
    private EditText edtTenDangNhapQMK,edtEmailQMK;
    private Button btnLayLaiMatKhau;
    private QuenMatKhauPresenter quenMatKhauView = new QuenMatKhauPresenter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau_acitivy);


        initView();
        initAction();

    }


    private void initAction() {
        btnLayLaiMatKhau.setOnClickListener(this);
    }

    private void initView() {
        edtTenDangNhapQMK = findViewById(R.id.edtTenDangNhapQMK);
        edtEmailQMK = findViewById(R.id.edtEmailQMK);
        btnLayLaiMatKhau = findViewById(R.id.btnLayLaiMatKhau);
    }

    @Override
    public void onClick(View v) {
        quenMatKhauView.handleForgetPass(edtTenDangNhapQMK.getText().toString().trim(),edtEmailQMK.getText().toString().trim());
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
        return NetWorkUtilitis.showProress(this);
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



    @Override
    public void setErrorUsername() {
        edtTenDangNhapQMK.setError("Tên đăng nhập Không Hợp Lệ");
    }

    @Override
    public void setErrorPassword() {

    }

    @Override
    public void setErrorEmail() {
        edtEmailQMK.setError("Email Không Hợp Lệ");
    }

    @Override
    public void clearForm() {

    }

    @Override
    public void forgetSuccess(String password) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.custom_dialog_quen_mat_khau, null, false);
        Button btnClose = view.findViewById(R.id.btnDialogDong);
        TextView tvDialogMatKhau = view.findViewById(R.id.tvDialogMatKhau);
        builder.setView(view);
        tvDialogMatKhau.setText(password);
        Toast.makeText(this,"Mật khẩu đã được reset", Toast.LENGTH_LONG).show();
        final AlertDialog dialog = builder.create();
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void forgetFail() {
        Toast.makeText(this, "Tên tài khoản hoặc Email sai", Toast.LENGTH_SHORT).show();
    }
}
