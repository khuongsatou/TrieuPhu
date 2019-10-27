package com.nvk.doanailatrieuphu.Fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.LongDef;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nvk.doanailatrieuphu.Activity.HienThiCauHoiActivity;
import com.nvk.doanailatrieuphu.Activity.TroGiupKhanGia;
import com.nvk.doanailatrieuphu.Model.CauHoi;
import com.nvk.doanailatrieuphu.R;
import com.nvk.doanailatrieuphu.Utilities.TimeCounter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class HienThiCauHoiFragment extends Fragment {
    private List<CauHoi> cauHoiList;
    private int position;
    private Context context;
    private HienThiCauHoiActivity hienThiCauHoiActivity;
    private ViewHolder viewHolder;
    public  Boolean isChecked = false;
    private String[] phuongAn = {"A","B","C","D"};

    private int totalTime= 1;
    private int secondTime = 30;
    private int countTime = 1000;

    private int timeChuyenCauHoi = 2000;



    public HienThiCauHoiFragment() {
        // Required empty public constructor
    }



    public HienThiCauHoiFragment(List<CauHoi> cauHoiList, int position,Context context) {
        this.cauHoiList = cauHoiList;
        this.position = position;
        this.context = context;
        this.hienThiCauHoiActivity = (HienThiCauHoiActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hien_thi_cau_hoi, container, false);

        Log.d("PAGER","Đã load");
        Radiation(view);
        return view;
    }

    private void Radiation(View view) {
        viewHolder = new ViewHolder();
        viewHolder.tvCauHoiSo = view.findViewById(R.id.tvCauHoiSo);
        viewHolder.tvCauHoi = view.findViewById(R.id.tvCauHoi);


        viewHolder.btnPhuongAn = new Button[4];

        viewHolder.btnPhuongAn[0] = view.findViewById(R.id.btnPhuongAnA);
        viewHolder.btnPhuongAn[1] = view.findViewById(R.id.btnPhuongAnB);
        viewHolder.btnPhuongAn[2] = view.findViewById(R.id.btnPhuongAnC);
        viewHolder.btnPhuongAn[3] = view.findViewById(R.id.btnPhuongAnD);

        viewHolder.tvTimer =  view.findViewById(R.id.tvtimer);
        viewHolder.ivbtnDoiCauHoi=  view.findViewById(R.id.ivbtnDoiCauHoi);
        viewHolder.ivbtnGioNguoiThan=  view.findViewById(R.id.ivbtnGioNguoiThan);
        viewHolder.ivbtnMuaCredit=  view.findViewById(R.id.ivbtnMuaCredit);
        viewHolder.ivbtnTroGiupKhangGia=  view.findViewById(R.id.ivbtnTroGiupKhangGia);
        viewHolder.ivbtnNamMoi=  view.findViewById(R.id.ivbtnNamMoi);



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Event();
        if (position ==0){// vi trí đầu tiên
            CounterLimited();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {// load 3 page trước.
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("PAGER","setUser Đã vào hàm hiện "+isVisibleToUser+" "+isResumed());
        if (isVisibleToUser && isResumed()){//cho page 2
            CounterLimited();
            Log.d("PAGER","setUser Hiện");
        }
    }

    private void Event() {
        final CauHoi cauHoi = cauHoiList.get(position);
        CreateData(cauHoi);
        CheckPhuongAn(cauHoi);
        CheckSupport(cauHoi);
    }



    private void CreateData(CauHoi cauHoi) {
        viewHolder.tvCauHoiSo.setText((position+1)+"");
        viewHolder.tvCauHoi.setText(cauHoi.getNoiDung());
        viewHolder.btnPhuongAn[0].setText("" + cauHoi.getPhuongAnA());
        viewHolder.btnPhuongAn[1].setText("" + cauHoi.getPhuongAnB());
        viewHolder.btnPhuongAn[2].setText("" + cauHoi.getPhuongAnC());
        viewHolder.btnPhuongAn[3].setText("" + cauHoi.getPhuongAnD());
    }

    private void CheckSupport(final CauHoi cauHoi) {
        viewHolder.ivbtnDoiCauHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hienThiCauHoiActivity.DoiCauHoi();
            }
        });

        viewHolder.ivbtnNamMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InvisiablePhuongAn(cauHoi);
            }
        });

        viewHolder.ivbtnTroGiupKhangGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDiaLogTroGiupKhangGia(cauHoi);
            }
        });

        viewHolder.ivbtnGioNguoiThan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogGoiDien(cauHoi);
            }
        });

        viewHolder.ivbtnMuaCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    //phân tích việc loại đi 2 Phương Án
    // phải tìm ra câu trả lời , còn 3 câu sai
    // dùng 3 câu sai chon 2 câu bất kì và ẩn đi


    private void InvisiablePhuongAn(CauHoi cauHoi) {
        int soLuong =0;
        for (int i = 0; i < phuongAn.length ; i++) {
            if (!cauHoi.getDapAn().equals(phuongAn[i])){
                if (soLuong == 2){
                    break;
                }else {
                    viewHolder.btnPhuongAn[i].setVisibility(View.INVISIBLE);
                    soLuong++;
                }
            }
        }
    }

    private void CheckCauTraLoi(View v,String ansert,String noiDung){

        ChonCauTraLoi(v);
        if (hienThiCauHoiActivity.checkAnsert(ansert,noiDung)){
            hienThiCauHoiActivity.SetDapAnDung(v);
            SetBaGiaySauDoiCauHoi();
        }else{
            if (HienThiCauHoiFragment.this.isChecked){
                String DapAn = cauHoiList.get(position).getDapAn();
                for (int i = 0; i < viewHolder.btnPhuongAn.length ; i++) {
                    if (DapAn.equals(viewHolder.btnPhuongAn[i].getTag())){
                        viewHolder.btnPhuongAn[i].setBackgroundColor(Color.RED);
                        hienThiCauHoiActivity.CheckHeart();
                        SetBaGiaySauDoiCauHoi();
                    }
                }
            }
        }


    }

    private void SetBaGiaySauDoiCauHoi() {
        CountDownTimer countDownTimer = new CountDownTimer(timeChuyenCauHoi,countTime) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                hienThiCauHoiActivity.ChuyenCauTiepTheo();
            }
        };
        countDownTimer.start();
    }


    private void CheckPhuongAn(final CauHoi cauHoi) {
        for (int i = 0; i < viewHolder.btnPhuongAn.length ; i++) {
            EventClickPhuongAn(i,cauHoi);
        }
    }

    private void EventClickPhuongAn(final int position ,final CauHoi cauHoi) {
        viewHolder.btnPhuongAn[position].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!HienThiCauHoiFragment.this.isChecked){
                    HienThiCauHoiFragment.this.isChecked = true;
                    CheckCauTraLoi(v,phuongAn[position],cauHoi.getNoiDung());
                }

            }
        });
    }

    private void ShowDialogGoiDien(CauHoi cauHoi) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_dialog_goi_dien,null,false);
        dialog.setView(view);
        final AlertDialog dialog2 = dialog.create();
        Button btnXinCamOn = view.findViewById(R.id.btnXinCamOn);
        TextView tvTroGiup = view.findViewById(R.id.tvCauTraLoi);
        tvTroGiup.setText(cauHoi.getDapAn());
        btnXinCamOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });
        dialog2.show();
    }

    private void ShowDiaLogTroGiupKhangGia(CauHoi cauHoi) {
//        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
//        View view = LayoutInflater.from(context).inflate(R.layout.activity_tro_giup_khan_gia,null,false);
//        dialog.setView(view);
//        final AlertDialog dialog2 = dialog.create();
//        Button btnXinCamOn = view.findViewById(R.id.btnXinCamOn);
//        btnXinCamOn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog2.dismiss();
//            }
//        });
//        dialog2.show();

        Intent i =new Intent(context, TroGiupKhanGia.class);
        context.startActivity(i);
    }

    //millisInFuture  1* 30 * 1000 = 30000ms / 1000 = 30s // Hết 30s chạy onFinish
    //countDownInterval : 1000 ms = 1s thì mỗi giây sẽ chạy hàm lại onTick
    private void CounterLimited() {
        TimeCounter counter = new TimeCounter(totalTime*secondTime*countTime,countTime,viewHolder.tvTimer,hienThiCauHoiActivity);

        counter.start();
    }





    private void ChonCauTraLoi(View v) {
       v.setBackgroundColor(Color.GREEN);
    }

    private class ViewHolder{
        private TextView tvCauHoiSo,tvCauHoi;
        private Button[] btnPhuongAn;
        private TextView tvTimer;
        private ImageButton ivbtnDoiCauHoi,ivbtnNamMoi,ivbtnTroGiupKhangGia,ivbtnGioNguoiThan,ivbtnMuaCredit;


    }
}
