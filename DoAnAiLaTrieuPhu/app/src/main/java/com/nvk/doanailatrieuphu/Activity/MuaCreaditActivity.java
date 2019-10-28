package com.nvk.doanailatrieuphu.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import static com.nvk.doanailatrieuphu.Activity.DangNhapActivity.KEY_DANGNHAP;

public class MuaCreaditActivity extends AppCompatActivity {
    private RecyclerView rcvShowCredit;

    private MuaCreditAdapter muaCreditAdapter;
    private List<GoiCredit> goiCreditList;
    private RecyclerView.LayoutManager layoutManager;
    private NguoiChoi nguoiChoi;

    private MuaCreditController muaCreditController = new MuaCreditController(this);
    private NguoiChoiController nguoiChoiController = new NguoiChoiController(this);

    public TextView tvTen,tvTinDung;
    public int id_nguoiChoi = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mua_credit);

        radiation();
        showUserAndCredit();
        createrAdapter();
    }

    private void showUserAndCredit() {
        this.nguoiChoi = (NguoiChoi) getIntent().getSerializableExtra(KEY_DANGNHAP);
        this.id_nguoiChoi = nguoiChoi.getId();
        tvTen.setText(nguoiChoi.getTenDangNhap());
        tvTinDung.setText(nguoiChoi.getCredit()+"");
    }




    private void createrAdapter() {
        muaCreditController = new MuaCreditController(this);
        goiCreditList = new ArrayList<>();

        goiCreditList.addAll(muaCreditController.getAllGoiCredit());
        muaCreditAdapter = new MuaCreditAdapter(goiCreditList,this,nguoiChoiController,this.nguoiChoi);

        layoutManager = new GridLayoutManager(this,2);
        rcvShowCredit.setLayoutManager(layoutManager);
        rcvShowCredit.setAdapter(muaCreditAdapter);
    }

    private void radiation() {
        rcvShowCredit = findViewById(R.id.rcvShowCredit);
        View vHeaderNormal = findViewById(R.id.vHeaderNormal);
        tvTen = vHeaderNormal.findViewById(R.id.tvTen);
        tvTinDung = vHeaderNormal.findViewById(R.id.tvTinDung);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        this.nguoiChoi.setCredit(Integer.parseInt(tvTinDung.getText().toString()));
        intent.putExtra(KEY_DANGNHAP,this.nguoiChoi);
        setResult(RESULT_OK,intent);
        finish();
    }
}
