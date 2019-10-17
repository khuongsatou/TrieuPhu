package com.nvk.doanailatrieuphu.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.nvk.doanailatrieuphu.Adapter.MuaCreditAdapter;
import com.nvk.doanailatrieuphu.Controller.MuaCreditController;
import com.nvk.doanailatrieuphu.Model.GoiCredit;
import com.nvk.doanailatrieuphu.R;

import java.util.ArrayList;
import java.util.List;

public class MuaCreaditActivity extends AppCompatActivity {
    private RecyclerView rcvShowCredit;

    private MuaCreditAdapter muaCreditAdapter;
    private List<GoiCredit> goiCreditList;
    private RecyclerView.LayoutManager layoutManager;
    private MuaCreditController muaCreditController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mua_credit);

        Radiation();
        CreaterAdapter();
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
