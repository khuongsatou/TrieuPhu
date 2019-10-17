package com.nvk.doanailatrieuphu.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.nvk.doanailatrieuphu.Adapter.BangXepHangAdapter;
import com.nvk.doanailatrieuphu.Adapter.LichSuChoiAdapter;
import com.nvk.doanailatrieuphu.Controller.LichSuChoiController;
import com.nvk.doanailatrieuphu.Model.LuotChoi;
import com.nvk.doanailatrieuphu.R;

import java.util.ArrayList;
import java.util.List;

public class LichSuCauHoiActivity extends AppCompatActivity {
    private LichSuChoiAdapter lichSuChoiAdapter;
    private List<LuotChoi> luotChois;
    private RecyclerView rcvLichSuChoi;
    private LichSuChoiController lichSuChoiController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_choi);

        Radiation();
        CreateAdapter();
    }

    private void CreateAdapter() {
        lichSuChoiController = new LichSuChoiController(this);
        luotChois = new ArrayList<>();
        luotChois.addAll(lichSuChoiController.getAllLuotChoiID(1));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvLichSuChoi.setLayoutManager(layoutManager);

        lichSuChoiAdapter = new LichSuChoiAdapter(this,luotChois);

        rcvLichSuChoi.setAdapter(lichSuChoiAdapter);
    }

    private void Radiation() {
        rcvLichSuChoi = findViewById(R.id.rcvLichSuChoi);
    }
}
