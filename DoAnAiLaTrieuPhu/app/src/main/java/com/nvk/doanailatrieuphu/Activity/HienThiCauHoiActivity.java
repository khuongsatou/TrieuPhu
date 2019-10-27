package com.nvk.doanailatrieuphu.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nvk.doanailatrieuphu.Adapter.CauHoiAdapter;
import com.nvk.doanailatrieuphu.Controller.CauHoiController;
import com.nvk.doanailatrieuphu.Model.CauHoi;
import com.nvk.doanailatrieuphu.Model.LinhVuc;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.Utilities.ViewPagerCurrentItem;
import com.nvk.doanailatrieuphu.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.nvk.doanailatrieuphu.Activity.DangNhapActivity.KEY_DANGNHAP;
import static com.nvk.doanailatrieuphu.Activity.MangHinhTroChoiActivity.KEY_LINHVUC;

public class HienThiCauHoiActivity extends AppCompatActivity {
    public ViewPager vpgShowCauHoi;
    public TextView tvTen, tvTinDung,tvDiem;
    private CauHoiAdapter cauHoiAdapter;
    private List<CauHoi> cauHois;
    private CauHoiController cauHoiController = new CauHoiController(this);
    private ImageView[] ivMang;
    private NguoiChoi nguoiChoi;
    private LinhVuc linhVuc;
    private Intent intent;

    public int tongDiem = 0;

    public boolean[] ischeckedSP = {false, false, false, false};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_cau_hoi);

        radiation();
        showUserAndCredit();
        createAdapter();
    }

    private void createAdapter() {
        cauHois = new ArrayList<>();
        cauHois.addAll(cauHoiController.getAllCauHoiByLinhVucID(this.linhVuc.getId()));
        cauHoiAdapter = new CauHoiAdapter(getSupportFragmentManager(), cauHois, this,vpgShowCauHoi);
        vpgShowCauHoi.setAdapter(cauHoiAdapter);
    }

    private void showUserAndCredit() {
        this.intent = getIntent();
        this.nguoiChoi = (NguoiChoi) this.intent.getSerializableExtra(KEY_DANGNHAP);
        this.linhVuc = (LinhVuc) this.intent.getSerializableExtra(KEY_LINHVUC);

        tvTen.setText(this.nguoiChoi.getTenDangNhap());
        tvTinDung.setText(this.nguoiChoi.getCredit()+"");
    }

    private void radiation() {
        vpgShowCauHoi = findViewById(R.id.vpgShowCauHoi);
        View vHeader = findViewById(R.id.vHeader);

        tvTen = vHeader.findViewById(R.id.tvTen);
        tvTinDung = vHeader.findViewById(R.id.tvTinDung);
        tvDiem = vHeader.findViewById(R.id.tvDiem);

        ivMang = new ImageView[5];
        ivMang[0] = findViewById(R.id.ivMang1);
        ivMang[1] = findViewById(R.id.ivMang2);
        ivMang[2] = findViewById(R.id.ivMang3);
        ivMang[3] = findViewById(R.id.ivMang4);
        ivMang[4] = findViewById(R.id.ivMang5);


    }

    public void anHaiDapAnSai(Button[] btnPhuongAn,CauHoi cauHoi) {
        int dem =0;
        for (int i = 0; i < btnPhuongAn.length ; i++) {
            if (btnPhuongAn[i].getTag().equals(cauHoi.getDapAn())){
                if (dem == 2){
                    break;
                }else{
                    btnPhuongAn[i].setBackgroundColor(Color.RED);
                    btnPhuongAn[i].setVisibility(View.INVISIBLE);
                    dem++;
                }
            }
        }
        cauHoiAdapter.notifyDataSetChanged();
    }

}
