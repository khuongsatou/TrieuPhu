package com.nvk.doanailatrieuphu.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.nvk.doanailatrieuphu.Controller.NguoiChoiController;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;

public class DangKiTaiKhoanActivity extends AppCompatActivity {
    private EditText edtTenDangNhap,edtEmail,edtMatKhau,edtXacNhanMatKhau;
    private NguoiChoiController nguoiChoiController;
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
        NguoiChoi nguoiChoi = new NguoiChoi();
        nguoiChoi.setTenDangNhap(edtTenDangNhap.getText().toString());
        nguoiChoi.setEmail(edtEmail.getText().toString());
        nguoiChoi.setMatKhau(edtMatKhau.getText().toString());

        if (!nguoiChoi.getMatKhau().equals(edtXacNhanMatKhau.getText().toString())){
            Toast.makeText(this,getString(R.string.tb_mat_khau_khong_giong_nhau),Toast.LENGTH_SHORT).show();
        }else{
            nguoiChoiController = new NguoiChoiController(this);
            long result = nguoiChoiController.DangKiUser(nguoiChoi);
            if (result > 0){
                Toast.makeText(this,getString(R.string.tb_dang_ky_tc),Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,DangNhapActivity.class));
            }else{
                Toast.makeText(this,getString(R.string.tb_dang_ky_tb),Toast.LENGTH_LONG).show();
            }
        }


    }


}
