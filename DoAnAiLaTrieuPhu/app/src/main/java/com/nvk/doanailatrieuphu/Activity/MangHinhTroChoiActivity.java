package com.nvk.doanailatrieuphu.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nvk.doanailatrieuphu.R;

public class MangHinhTroChoiActivity extends AppCompatActivity {

    public static final String KEY_LINHVUC = "linhvuc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mang_hinh_tro_choi);
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
