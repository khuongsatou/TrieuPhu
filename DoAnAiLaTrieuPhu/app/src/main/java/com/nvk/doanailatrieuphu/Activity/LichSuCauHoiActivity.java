package com.nvk.doanailatrieuphu.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nvk.doanailatrieuphu.Adapter.BangXepHangAdapter;
import com.nvk.doanailatrieuphu.Adapter.LichSuChoiAdapter;
import com.nvk.doanailatrieuphu.Controller.LichSuChoiController;
import com.nvk.doanailatrieuphu.Model.LuotChoi;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;

import java.util.ArrayList;
import java.util.List;

import static com.nvk.doanailatrieuphu.Activity.DangNhapActivity.KEY_DANGNHAP;

public class LichSuCauHoiActivity extends AppCompatActivity {
    private LichSuChoiAdapter lichSuChoiAdapter;
    private List<LuotChoi> luotChois;
    private RecyclerView rcvLichSuChoi;
    private LichSuChoiController lichSuChoiController;
    private int id_nguoiChoi = 0;

    private TextView tvTen,tvTinDung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_choi);

        Radiation();
        showUserVaCredit();
        CreateAdapter();
    }

    private void showUserVaCredit() {
        NguoiChoi nguoiChoi = (NguoiChoi) getIntent().getSerializableExtra(KEY_DANGNHAP);
        this.id_nguoiChoi = nguoiChoi.getId();
        tvTen.setText(nguoiChoi.getTenDangNhap());
        tvTinDung.setText(nguoiChoi.getCredit()+"");
    }

    private void CreateAdapter() {
        lichSuChoiController = new LichSuChoiController(this);
        luotChois = new ArrayList<>();
        luotChois.addAll(lichSuChoiController.getAllLuotChoiID(this.id_nguoiChoi));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvLichSuChoi.setLayoutManager(layoutManager);
        lichSuChoiAdapter = new LichSuChoiAdapter(this,luotChois);
        rcvLichSuChoi.setAdapter(lichSuChoiAdapter);
    }

    private void Radiation() {
        View vHeaderNormal = findViewById(R.id.vHeaderNormal);
        tvTen = vHeaderNormal.findViewById(R.id.tvTen);
        tvTinDung = vHeaderNormal.findViewById(R.id.tvTinDung);
        rcvLichSuChoi = findViewById(R.id.rcvLichSuChoi);
    }
}
