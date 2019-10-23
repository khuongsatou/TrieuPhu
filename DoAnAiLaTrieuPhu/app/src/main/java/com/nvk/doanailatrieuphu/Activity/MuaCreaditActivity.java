package com.nvk.doanailatrieuphu.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nvk.doanailatrieuphu.Adapter.MuaCreditAdapter;
import com.nvk.doanailatrieuphu.Controller.MuaCreditController;
import com.nvk.doanailatrieuphu.Controller.NguoiChoiController;
import com.nvk.doanailatrieuphu.Model.GoiCredit;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;

import java.util.ArrayList;
import java.util.List;

import static com.nvk.doanailatrieuphu.Activity.DangNhapActivity.KEY_DANGNHAP_TENDANGNHAP;

public class MuaCreaditActivity extends AppCompatActivity {
    private RecyclerView rcvShowCredit;
    private TextView tvTinhdung;
    private MuaCreditAdapter muaCreditAdapter;
    private List<GoiCredit> goiCreditList;
    private RecyclerView.LayoutManager layoutManager;
    private MuaCreditController muaCreditController;
    String tien;
    private NguoiChoiController nguoiChoiController;
    private String tenDangNhap = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mua_credit);
        tvTinhdung = findViewById(R.id.tvTinDung);

        SetNameUser();
        Radiation();
        CreaterAdapter();

    }


    private void SetNameUser() {
        Intent intent = getIntent();
        this.tenDangNhap = intent.getStringExtra("Ten_dang_nhap");
        nguoiChoiController = new NguoiChoiController(this);
        NguoiChoi nguoiChoi = nguoiChoiController.getTK(this.tenDangNhap);
        tvTinhdung.setText(String.valueOf(nguoiChoi.getCredit()));
    }

    public void CapNhatCredit(int credit) {
        Intent intent = getIntent();
        this.tenDangNhap = intent.getStringExtra("Ten_dang_nhap");
        NguoiChoi nguoiChoi = nguoiChoiController.getTK(this.tenDangNhap);

        nguoiChoi.setCredit(nguoiChoi.getCredit()+credit);

        boolean result=nguoiChoiController.UpdateGoiCredit(nguoiChoi);
        Log.d("AAAAAA",result+"");
    }



    private void CreaterAdapter() {
        muaCreditController = new MuaCreditController(this);
        goiCreditList = new ArrayList<>();
        goiCreditList.addAll(muaCreditController.getAllGoiCredit());
        muaCreditAdapter = new MuaCreditAdapter(goiCreditList,this);
        layoutManager = new GridLayoutManager(this,2);
        rcvShowCredit.setLayoutManager(layoutManager);
        rcvShowCredit.setAdapter(muaCreditAdapter);

    }



    private void Radiation() {
        rcvShowCredit = findViewById(R.id.rcvShowCredit);
    }
}
