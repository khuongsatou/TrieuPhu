package com.nvk.doanailatrieuphu.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nvk.doanailatrieuphu.Adapter.CauHoiAdapter;
import com.nvk.doanailatrieuphu.Controller.CauHoiController;
import com.nvk.doanailatrieuphu.Model.CauHoi;
import com.nvk.doanailatrieuphu.Utilities.TimeCounter;
import com.nvk.doanailatrieuphu.Utilities.ViewPagerCurrentItem;
import com.nvk.doanailatrieuphu.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.nvk.doanailatrieuphu.Activity.MangHinhTroChoiActivity.KEY_LINHVUC;

public class HienThiCauHoiActivity extends AppCompatActivity implements ViewPagerCurrentItem {
    private ViewPager vpgShowCauHoi;


    private CauHoiAdapter cauHoiAdapter;
    private List<CauHoi> cauHoiList;
    private CauHoiController cauHoiController;

    private int linhVucID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_cau_hoi);

        Radiation();
        CreateQuestion();
        CreateAdapter();
    }



    public void DoiCauHoi(){
        cauHoiList.clear();
        cauHoiList.addAll(cauHoiController.getAllCauHoiByLinhVucID(this.linhVucID));
        Collections.shuffle(cauHoiList);
        vpgShowCauHoi.getAdapter().notifyDataSetChanged();
        Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_SHORT).show();
    }



    private void CreateQuestion() {
        cauHoiController = new CauHoiController(this);
        cauHoiList = new ArrayList<>();
        this.linhVucID = getIntent().getIntExtra(KEY_LINHVUC,1);
        cauHoiList.addAll(cauHoiController.getAllCauHoiByLinhVucID(this.linhVucID));
    }



    @Override
    public void onBackPressed() {
        if (vpgShowCauHoi.getCurrentItem() == 0){
            super.onBackPressed();
        }else{
            vpgShowCauHoi.setCurrentItem(vpgShowCauHoi.getCurrentItem()-1);
        }
    }



    private void CreateAdapter() {
        cauHoiAdapter = new CauHoiAdapter(getSupportFragmentManager(),cauHoiList,this);
        vpgShowCauHoi.setAdapter(cauHoiAdapter);
    }

    private void Radiation() {
        vpgShowCauHoi = this.findViewById(R.id.vpgShowCauHoi);


    }



    @Override
    public void current(View view) {
        if (vpgShowCauHoi.getCurrentItem() >= 0){// page đầu tiên là page 0
            vpgShowCauHoi.setCurrentItem(vpgShowCauHoi.getCurrentItem()+1);
        }else if(vpgShowCauHoi.getCurrentItem() == cauHoiList.size()-1){
            vpgShowCauHoi.setCurrentItem(vpgShowCauHoi.getCurrentItem());
        }
    }

    @Override
    public Boolean checkAnsert(String ansert, String cauhoi) {
        String dapAn = cauHoiController.getDapAn(cauhoi,this.linhVucID);
        if (dapAn.equals(ansert)){
            return true;
        }
        return false;
    }
}
