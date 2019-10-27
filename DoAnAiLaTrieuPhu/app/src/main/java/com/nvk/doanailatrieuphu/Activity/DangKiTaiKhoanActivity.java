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
    private NguoiChoiController nguoiChoiController = new NguoiChoiController(this);;
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
        String tenDangNhap = edtTenDangNhap.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String matKhau = edtMatKhau.getText().toString().trim();
        String xacNhanMatKhau = edtXacNhanMatKhau.getText().toString().trim();
        //Kiểm tra rỗng
        if (tenDangNhap.equals("") || email.equals("") || matKhau.equals("") || xacNhanMatKhau.equals("")){
            Toast.makeText(getApplicationContext(), getString(R.string.tb_chua_nhap_du),Toast.LENGTH_SHORT).show();
        }else{
            //kiểm tra xác nhận mật khẩu
            if (!matKhau.equals(xacNhanMatKhau)){
                Toast.makeText(getApplicationContext(), getString(R.string.tb_mat_khau_khong_giong_nhau),Toast.LENGTH_SHORT).show();
            }else{
                //kiểm tra có chưa
                Boolean checkTaiKhoanTonTai = nguoiChoiController.getTKTonTai(tenDangNhap);
                if (checkTaiKhoanTonTai){
                    Toast.makeText(getApplicationContext(), getString(R.string.tb_ton_tai),Toast.LENGTH_SHORT).show();
                }else{
                    //thêm người chơi
                    NguoiChoi nguoiChoi = new NguoiChoi();
                    nguoiChoi.setTenDangNhap(tenDangNhap);
                    nguoiChoi.setEmail(email);
                    nguoiChoi.setMatKhau(matKhau);
                    long result = nguoiChoiController.insertNguoiChoi(nguoiChoi);
                    if (result > 0){
                        Toast.makeText(this,getString(R.string.tb_dang_ky_tc),Toast.LENGTH_LONG).show();
                        startActivity(new Intent(this,DangNhapActivity.class));
                    }else{
                        Toast.makeText(this,getString(R.string.tb_dang_ky_tb),Toast.LENGTH_LONG).show();
                    }
                }
            }


        }
    }
}
