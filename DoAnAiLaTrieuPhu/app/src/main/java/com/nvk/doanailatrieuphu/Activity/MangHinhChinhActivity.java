package com.nvk.doanailatrieuphu.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;
import com.squareup.picasso.Picasso;

import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.KEY_DANGNHAP;
import static com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis.BASE;
import static com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis.BASE_IMAGE;


public class MangHinhChinhActivity extends AppCompatActivity {
    public static final String KEY_PAGE = "page";
    public static final String KEY_LIMIT = "limit";

    public static final int KEY_REQUESTCODE = 123;
    private TextView tvTenDangNhap,tvCredit;
    private NguoiChoi nguoiChoi;
    private Intent intent;
    private ImageView ivHinhDaiDien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mang_hinh_chinh);

        Radiation();
        SetNameUser();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==KEY_REQUESTCODE && resultCode == RESULT_OK && data != null){
            this.nguoiChoi = (NguoiChoi) data.getSerializableExtra(KEY_DANGNHAP);
            Picasso.get()
                    .load(BASE_IMAGE+nguoiChoi.getHinhDaiDien())
                    .error(R.drawable.logo_android)
                    .into(ivHinhDaiDien);
            tvCredit.setText(this.nguoiChoi.getCredit()+"");
        }
    }

    private void SetNameUser() {
        this.nguoiChoi = (NguoiChoi) getIntent().getSerializableExtra(KEY_DANGNHAP);
        tvTenDangNhap.setText(nguoiChoi.getTenDangNhap());
        tvCredit.setText(nguoiChoi.getCredit()+"");
        Picasso.get()
                .load(BASE_IMAGE+nguoiChoi.getHinhDaiDien())
                .error(R.drawable.logo_android)
                .into(ivHinhDaiDien);

    }



    private void Radiation() {
        tvTenDangNhap = findViewById(R.id.tvTenDangNhap);
        tvCredit = findViewById(R.id.tvCredit);
        ivHinhDaiDien = findViewById(R.id.ivHinhDaiDien);
    }

    public void XuLiQuanLiTaiKhoan(View view) {
        intent = new Intent(this,QuanLiTaiKhoanActivity.class);
        intent.putExtra(KEY_DANGNHAP,this.nguoiChoi);
        startActivityForResult(intent,KEY_REQUESTCODE);
    }

    public void XuLiTroChoiMoi(View view) {
        intent = new Intent(this,MangHinhTroChoiActivity.class);
        intent.putExtra(KEY_DANGNHAP,this.nguoiChoi);
        startActivityForResult(intent,KEY_REQUESTCODE);
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
        startActivityForResult(intent,KEY_REQUESTCODE);
    }
}
