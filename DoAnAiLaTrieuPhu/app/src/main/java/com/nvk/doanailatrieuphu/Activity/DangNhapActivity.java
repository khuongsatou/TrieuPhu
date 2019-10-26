package com.nvk.doanailatrieuphu.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.nvk.doanailatrieuphu.Controller.NguoiChoiController;
import com.nvk.doanailatrieuphu.Database.DBHelper;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;

import java.io.IOException;
import java.io.Serializable;

public class DangNhapActivity extends AppCompatActivity {
    public static final String KEY_DANGNHAP = "dn" ;
    private EditText edtTenDangNhap,edtMatKhau;
    private DBHelper db;
    private NguoiChoiController nguoiChoiController = new NguoiChoiController(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        ConnectDB();
        Radiation();
    }

    private void ConnectDB() {
        db = new DBHelper(this);
        //db.deleteDataBase();

        try {
            db.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Radiation() {
        edtTenDangNhap = findViewById(R.id.edtTenDangNhap);
        edtMatKhau = findViewById(R.id.edtMatKhau);
    }


    public void XuLiDangNhap(View v){
        //Lấy text
        String tenDangNhap = edtTenDangNhap.getText().toString();
        String matKhau = edtMatKhau.getText().toString();
        //Kiểm tra tài khoản có chưa
        Boolean result = nguoiChoiController.checkUser(tenDangNhap,matKhau);
        if (result){
            //select 1 người chơi ra
            NguoiChoi nguoiChoi = nguoiChoiController.getTK(tenDangNhap);
            //có người chơi
            if (nguoiChoi.getId() > 0){
                Intent intent = new Intent(this,MangHinhChinhActivity.class);
                intent.putExtra(KEY_DANGNHAP, (Serializable) nguoiChoi);
                startActivity(intent);
            }else{
                Toast.makeText(this,getString(R.string.tb_dang_nhap_xoa),Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this,getString(R.string.tb_dang_nhap),Toast.LENGTH_LONG).show();
        }

    }

    public void XuLiDangKi(View view) {
        startActivity(new Intent(this,DangKiTaiKhoanActivity.class));
    }

    public void XuLiQuenMatKhau(View view) {
        startActivity(new Intent(this,QuenMatKhauActivity.class));
    }
}
