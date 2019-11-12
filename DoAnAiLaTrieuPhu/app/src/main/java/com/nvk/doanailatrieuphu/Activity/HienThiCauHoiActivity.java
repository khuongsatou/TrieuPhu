package com.nvk.doanailatrieuphu.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.nvk.doanailatrieuphu.Adapter.CauHoiAdapter;
import com.nvk.doanailatrieuphu.Model.CauHoi;
import com.nvk.doanailatrieuphu.Model.LinhVuc;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;
import com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis;

import java.util.ArrayList;
import java.util.List;


import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.KEY_DANGNHAP;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.KEY_LINHVUC;

public class HienThiCauHoiActivity extends AppCompatActivity {
    public ViewPager vpgShowCauHoi;
    public TextView tvTen, tvTinDung,tvDiem;
    private CauHoiAdapter cauHoiAdapter;
    private List<CauHoi> cauHois  = new ArrayList<>();;
    public ImageView[] ivMang = new ImageView[5];
    private NguoiChoi nguoiChoi;
    private LinhVuc linhVuc;
    private Intent intent;
    private int diemSoMang = 0;

    public int tongDiem = 0;
    public boolean[] ischeckedSP = {false, false,false, false};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_cau_hoi);

        radiation();
        showUserAndCredit();
        createAdapter();
        loadData();
    }

    private void loadData() {
        if (NetWorkUtilitis.checkConnect(this)){
            startVolley();
        }else{
            NetWorkUtilitis.showDialogNetWork(getString(R.string.tb_connect_internet),this);
        }
    }

    private void startVolley() {


    }

    private void createAdapter() {
        cauHoiAdapter = new CauHoiAdapter(getSupportFragmentManager(), cauHois, this,nguoiChoi);
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

        this.ivMang[0] = findViewById(R.id.ivMang1);
        this.ivMang[1] = findViewById(R.id.ivMang2);
        this.ivMang[2] = findViewById(R.id.ivMang3);
        this.ivMang[3] = findViewById(R.id.ivMang4);
        this.ivMang[4] = findViewById(R.id.ivMang5);
    }

    public void giamMangNguoiChoi() {
        if (this.diemSoMang == 5){
            Toast.makeText(this,"Hết",Toast.LENGTH_SHORT).show();
        }else{
            ivMang[this.diemSoMang].setImageResource(R.drawable.ic_action_heart_low);
            this.diemSoMang++;
        }
    }

    @Override
    public void onBackPressed() {
        if (vpgShowCauHoi.getCurrentItem() > 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Thông báo");
            alert.setMessage("Bạn có muốn dừng.");
            alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent();
                    intent.putExtra(KEY_DANGNHAP,nguoiChoi);
                    setResult(RESULT_OK,intent);
                    finish();
                    dialog.dismiss();
                }
            });
            alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alert.show();
        }else{
            super.onBackPressed();
        }
    }


}
