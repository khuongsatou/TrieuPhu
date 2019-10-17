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

public class DangNhapActivity extends AppCompatActivity {
    public static final String KEY_DANGNHAP_TENDANGNHAP = "dn1" ;
    private EditText edtTenDangNhap,edtMatKhau;

    private DBHelper db;
    private NguoiChoiController nguoiChoiController;
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
        nguoiChoiController = new NguoiChoiController(this);
        String tenDangNhap =edtTenDangNhap.getText().toString();
        String matKhau = edtMatKhau.getText().toString();
        Boolean result = nguoiChoiController.CheckUser(tenDangNhap,matKhau);
        if (result){
            Intent intent = new Intent(this,MangHinhChinhActivity.class);
            intent.putExtra(KEY_DANGNHAP_TENDANGNHAP,tenDangNhap);
            //intent.putExtra(KEY_DANGNHAP_DIEM,)
            startActivity(intent);


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
