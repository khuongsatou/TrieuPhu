package com.nvk.doanailatrieuphu.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.nvk.doanailatrieuphu.Adapter.BangXepHangAdapter;
import com.nvk.doanailatrieuphu.Controller.NguoiChoiController;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;

import java.util.ArrayList;
import java.util.List;

public class BangXepHangActivity extends AppCompatActivity {
    private BangXepHangAdapter bangXepHangAdapter;
    private RecyclerView rcvBangXepHang;
    private List<NguoiChoi> nguoiChois;
    private NguoiChoiController nguoiChoiController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bang_xep_hang);

        Radiation();
        CreateAdapter();
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
        rcvBangXepHang = findViewById(R.id.rcvBangXepHang);

    }
}
