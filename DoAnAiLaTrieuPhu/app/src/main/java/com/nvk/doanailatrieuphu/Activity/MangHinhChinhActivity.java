package com.nvk.doanailatrieuphu.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nvk.doanailatrieuphu.Controller.NguoiChoiController;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;

import static com.nvk.doanailatrieuphu.Activity.DangNhapActivity.KEY_DANGNHAP_TENDANGNHAP;

public class MangHinhChinhActivity extends AppCompatActivity {
    private TextView tvTenDangNhap,tvCredit;

    private NguoiChoiController nguoiChoiController;
    private String tenDangNhap = null;
    private int credit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mang_hinh_chinh);

        Radiation();
        SetNameUser();


    }

    private void SetNameUser() {
        Intent intent = getIntent();
        this.tenDangNhap = intent.getStringExtra(KEY_DANGNHAP_TENDANGNHAP);
        nguoiChoiController = new NguoiChoiController(this);
        NguoiChoi nguoiChoi = nguoiChoiController.getTK(this.tenDangNhap);
        tvCredit.setText(String.valueOf(nguoiChoi.getCredit()));
        tvTenDangNhap.setText(nguoiChoi.getTenDangNhap());
    }

    private void Radiation() {
        tvTenDangNhap = findViewById(R.id.tvTenDangNhap);
        tvCredit = findViewById(R.id.tvCredit);

    }

    public void XuLiQuanLiTaiKhoan(View view) {
        Intent intent = new Intent(this,QuanLiTaiKhoanActivity.class);
        intent.putExtra(KEY_DANGNHAP_TENDANGNHAP,this.tenDangNhap);
        startActivity(intent);
    }

    public void XuLiTroChoiMoi(View view) {
        startActivity(new Intent(this,MangHinhTroChoiActivity.class));
    }

    public void XuLiLichSuChoi(View view) {
        startActivity(new Intent(this,LichSuCauHoiActivity.class));
    }

    public void XuLiBangXepHang(View view) {
        startActivity(new Intent(this,BangXepHangActivity.class));
    }

    public void XuLiMuaCreadit(View view) {
        startActivity(new Intent(this,MuaCreaditActivity.class));
    }
}
