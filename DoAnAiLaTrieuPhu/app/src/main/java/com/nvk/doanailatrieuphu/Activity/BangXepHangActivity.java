package com.nvk.doanailatrieuphu.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nvk.doanailatrieuphu.Adapter.BangXepHangAdapter;
import com.nvk.doanailatrieuphu.Controller.NguoiChoiController;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;

import java.util.ArrayList;
import java.util.List;

import static com.nvk.doanailatrieuphu.Activity.DangNhapActivity.KEY_DANGNHAP;

public class BangXepHangActivity extends AppCompatActivity {
    private BangXepHangAdapter bangXepHangAdapter;
    private RecyclerView rcvBangXepHang;
    private List<NguoiChoi> nguoiChois;
    private NguoiChoiController nguoiChoiController;
    private TextView tvTen,tvTinDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bang_xep_hang);

        Radiation();
        showUserAndCredit();
        CreateAdapter();
    }

    private void showUserAndCredit() {
        NguoiChoi nguoiChoi = (NguoiChoi) getIntent().getSerializableExtra(KEY_DANGNHAP);
        tvTen.setText(nguoiChoi.getTenDangNhap());
        tvTinDung.setText(nguoiChoi.getCredit()+"");
    }

    private void CreateAdapter() {
        nguoiChoiController = new NguoiChoiController(this);
        nguoiChois = new ArrayList<NguoiChoi>();

        nguoiChois.addAll(nguoiChoiController.getBangXepHang());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvBangXepHang.setLayoutManager(layoutManager);
        bangXepHangAdapter = new BangXepHangAdapter(this,nguoiChois);
        rcvBangXepHang.setAdapter(bangXepHangAdapter);
    }

    private void Radiation() {
        View vHeaderNormal = findViewById(R.id.vHeaderNormal);
        tvTen = vHeaderNormal.findViewById(R.id.tvTen);
        tvTinDung = vHeaderNormal.findViewById(R.id.tvTinDung);
        rcvBangXepHang = findViewById(R.id.rcvBangXepHang);

    }


}
