package com.nvk.doanailatrieuphu.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nvk.doanailatrieuphu.Adapter.CauHoiAdapter;
import com.nvk.doanailatrieuphu.Controller.CauHoiController;
import com.nvk.doanailatrieuphu.Model.CauHoi;
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
    public int demSoMang = 0;
    private int linhVucID;
    private ImageView[] ivMang;
    private TextView tvDiem;
    private int diem = 0;

    private int cauHoiThu = 0;


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
    }



    private void CreateQuestion() {
        cauHoiController = new CauHoiController(this);
        cauHoiList = new ArrayList<>();
        this.linhVucID = getIntent().getIntExtra(KEY_LINHVUC,1);
        cauHoiList.addAll(cauHoiController.getAllCauHoiByLinhVucID(this.linhVucID));
    }

    public void ChuyenCauTiepTheo(){
        if (this.cauHoiThu == cauHoiList.size() -1){
            Toast.makeText(this,"Đã Hết Câu Hỏi Bạn Win Hết !",Toast.LENGTH_SHORT).show();
        }else{
            this.cauHoiThu++;
            vpgShowCauHoi.setCurrentItem(this.cauHoiThu);
        }
    }




    @Override
    public void onBackPressed() {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Bạn Có Muốn dừng lại").setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            }).setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog.create().show();
    }



    private void CreateAdapter() {
        cauHoiAdapter = new CauHoiAdapter(getSupportFragmentManager(),cauHoiList,this);
        vpgShowCauHoi.setAdapter(cauHoiAdapter);
        vpgShowCauHoi.setOffscreenPageLimit(1);
    }

    private void Radiation() {
        vpgShowCauHoi = this.findViewById(R.id.vpgShowCauHoi);

        ivMang = new ImageView[5];
        ivMang[0] = findViewById(R.id.ivMang1);
        ivMang[1] = findViewById(R.id.ivMang2);
        ivMang[2] = findViewById(R.id.ivMang3);
        ivMang[3] = findViewById(R.id.ivMang4);
        ivMang[4] = findViewById(R.id.ivMang5);

        tvDiem = findViewById(R.id.tvDiem);
    }

    public void SetDapAnDung(View v) {
        this.diem+=50;
        tvDiem.setText("Điểm: "+this.diem);
        v.setBackgroundColor(Color.RED);
    }

    public void CheckHeart() {
        ImageView[] ivMangItem = ivMang;
        if (this.demSoMang == 5){
            Toast.makeText(this,"Bạn Đã hết Mạng",Toast.LENGTH_SHORT).show();

            if (!isFinishing()){
                final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                View view = LayoutInflater.from(this).inflate(R.layout.custom_dialog_ket_thuc,null,false);
                Button btnThemLuot = view.findViewById(R.id.btnThemLuot);
                Button btnKetThuc = view.findViewById(R.id.btnKetThuc);
                TextView tvDiemDialog = view.findViewById(R.id.tvDiem);
                dialog.setView(view);
                tvDiemDialog.setText(tvDiem.getText().toString());
                final AlertDialog dialog1 = dialog.create();
                btnThemLuot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),"Thêm lượt chưa được xây dựng",Toast.LENGTH_SHORT).show();
                        dialog1.cancel();
                    }
                });

                btnKetThuc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.cancel();
                        finish();
                    }
                });

                dialog1.show();
            }

        }else{
            ivMangItem[this.demSoMang].setImageResource(R.drawable.ic_action_heart_low);
            this.demSoMang++;
        }
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
        return dapAn.equals(ansert);
    }
}
