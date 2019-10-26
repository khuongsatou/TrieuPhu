package com.nvk.doanailatrieuphu.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;

import static com.nvk.doanailatrieuphu.Activity.DangNhapActivity.KEY_DANGNHAP;

public class MangHinhTroChoiActivity extends AppCompatActivity {

    public static final String KEY_LINHVUC = "linhvuc";

    private TextView tvTen,tvTinDung;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mang_hinh_tro_choi);

        radiation();
        showNameAndCredit();
    }

    private void showNameAndCredit() {
        NguoiChoi nguoiChoi = (NguoiChoi) getIntent().getSerializableExtra(KEY_DANGNHAP);
        tvTen.setText(nguoiChoi.getTenDangNhap());
        tvTinDung.setText(nguoiChoi.getCredit()+"");
    }

    private void radiation() {
        View vHeader = findViewById(R.id.vHeader);
        tvTen =  vHeader.findViewById(R.id.tvTen);
        tvTinDung = vHeader.findViewById(R.id.tvTinDung);
    }

    public void ChonLinhVuc(int linhVuc){
        Intent intent =  new Intent(this,HienThiCauHoiActivity.class);
        intent.putExtra(KEY_LINHVUC,linhVuc);
        startActivity(intent);
    }





    public void VHXHNT(View view) {
        ChonLinhVuc(1);
    }

    public void LSDL(View view) {
        ChonLinhVuc(2);
    }

    public void KHKT(View view) {
        ChonLinhVuc(3);
    }

    public void TT(View view) {
        ChonLinhVuc(4);
    }
}
