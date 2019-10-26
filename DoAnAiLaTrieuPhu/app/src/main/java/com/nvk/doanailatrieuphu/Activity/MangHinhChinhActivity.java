package com.nvk.doanailatrieuphu.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nvk.doanailatrieuphu.Controller.NguoiChoiController;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;

import static com.nvk.doanailatrieuphu.Activity.DangNhapActivity.KEY_DANGNHAP;

public class MangHinhChinhActivity extends AppCompatActivity {
    private TextView tvTenDangNhap,tvCredit;
    private NguoiChoiController nguoiChoiController = new NguoiChoiController(this);;
    private String tenDangNhap = null;
    private int credit = 0;
    private NguoiChoi nguoiChoi;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mang_hinh_chinh);

        Radiation();
        SetNameUser();
    }

    private void SetNameUser() {
        this.nguoiChoi = (NguoiChoi) getIntent().getSerializableExtra(KEY_DANGNHAP);
        tvTenDangNhap.setText(nguoiChoi.getTenDangNhap());
        tvCredit.setText(nguoiChoi.getCredit()+"");
    }



    private void Radiation() {
        tvTenDangNhap = findViewById(R.id.tvTenDangNhap);
        tvCredit = findViewById(R.id.tvCredit);
    }

    public void XuLiQuanLiTaiKhoan(View view) {
        intent = new Intent(this,QuanLiTaiKhoanActivity.class);
        intent.putExtra(KEY_DANGNHAP,this.nguoiChoi);
        startActivity(intent);
    }

    public void XuLiTroChoiMoi(View view) {
        intent = new Intent(this,MangHinhTroChoiActivity.class);
        intent.putExtra(KEY_DANGNHAP,this.nguoiChoi);
        startActivity(intent);
    }

    public void XuLiLichSuChoi(View view) {
        intent =new Intent(this,LichSuCauHoiActivity.class);
        intent.putExtra(KEY_DANGNHAP,this.nguoiChoi);
        startActivity(intent);
    }

    public void XuLiBangXepHang(View view) {
        Intent intent = new Intent(this,BangXepHangActivity.class);
        intent.putExtra(KEY_DANGNHAP,this.nguoiChoi);
        startActivity(intent);
    }

    public void XuLiMuaCreadit(View view) {
        intent = new Intent(this,MuaCreaditActivity.class);
        intent.putExtra(KEY_DANGNHAP,this.nguoiChoi);
        startActivity(intent);
    }
}
