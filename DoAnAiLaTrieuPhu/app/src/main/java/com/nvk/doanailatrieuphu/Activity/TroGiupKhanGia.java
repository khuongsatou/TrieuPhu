package com.nvk.doanailatrieuphu.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.nvk.doanailatrieuphu.R;

import java.util.ArrayList;

public class TroGiupKhanGia extends AppCompatActivity {

    private BarChart mbarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tro_giup_khan_gia);
        mbarChart = findViewById(R.id.char1);
        mbarChart.getDescription().setEnabled(false);

        setdata(4);
        mbarChart.setFitBars(true);
    }
    private void setdata(int count)
    {
        ArrayList<BarEntry> yBarChart = new ArrayList<>();
        for (int i= 0; i<count;i++){
            float random = (float)(Math.random()*100);
            yBarChart.add(new BarEntry(i,(int)random));
        }

        BarDataSet set = new BarDataSet(yBarChart,"DataSet");
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        set.setDrawValues(true);

        BarData data = new BarData(set);
        mbarChart.setData(data);
        mbarChart.invalidate();
        mbarChart.animateY(500);
    }

    public void Back_cau_hoi(View view) {
        finish();
    }
}
