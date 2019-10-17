package com.nvk.doanailatrieuphu.Fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nvk.doanailatrieuphu.Activity.HienThiCauHoiActivity;
import com.nvk.doanailatrieuphu.Model.CauHoi;
import com.nvk.doanailatrieuphu.R;
import com.nvk.doanailatrieuphu.Utilities.TimeCounter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HienThiCauHoiFragment extends Fragment{
    private List<CauHoi> cauHoiList;
    private int position;
    private Context context;
    private HienThiCauHoiActivity hienThiCauHoiActivity;
    private ViewHolder viewHolder;
    public  Boolean isChecked = false;
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
        Radiation(view);
        return view;
    }

    private void Radiation(View view) {
        viewHolder = new ViewHolder();
        viewHolder.tvCauHoiSo = view.findViewById(R.id.tvCauHoiSo);
        viewHolder.tvCauHoi = view.findViewById(R.id.tvCauHoi);

        viewHolder.btnPhuongAnA = view.findViewById(R.id.btnPhuongAnA);
        viewHolder.btnPhuongAnB = view.findViewById(R.id.btnPhuongAnB);
        viewHolder.btnPhuongAnC = view.findViewById(R.id.btnPhuongAnC);
        viewHolder.btnPhuongAnD = view.findViewById(R.id.btnPhuongAnD);

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
        CounterLimited();
    }

    private void Event() {
        final CauHoi cauHoi = cauHoiList.get(position);
        viewHolder.tvCauHoiSo.setText((position+1)+"");
        viewHolder.tvCauHoi.setText(cauHoi.getNoiDung());
        viewHolder.btnPhuongAnA.setText("" + cauHoi.getPhuongAnA());
        viewHolder.btnPhuongAnB.setText("" + cauHoi.getPhuongAnB());
        viewHolder.btnPhuongAnC.setText("" + cauHoi.getPhuongAnC());
        viewHolder.btnPhuongAnD.setText("" + cauHoi.getPhuongAnD());


        CheckPhuongAn(cauHoi);
        CheckSupport(cauHoi);

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
    }

    //phân tích việc loại đi 2 Phương Án
    // phải tìm ra câu trả lời , còn 3 câu sai
    // dùng 3 câu sai chon 2 câu bất kì và ẩn đi


    private void InvisiablePhuongAn(CauHoi cauHoi) {
        Button[] btnCheckPhuongAn = {viewHolder.btnPhuongAnA,viewHolder.btnPhuongAnB,viewHolder.btnPhuongAnC,viewHolder.btnPhuongAnD};
        for (int i = 0; i < 4 ; i++) {
            if (cauHoi.getDapAn().equals(btnCheckPhuongAn[i])){

            }
        }
        if (cauHoi.getDapAn() == "A"){

        }
        viewHolder.btnPhuongAnA.setVisibility(View.INVISIBLE);
        viewHolder.btnPhuongAnB.setVisibility(View.INVISIBLE);
    }

    private void CheckCauTraLoi(View v,String ansert,String noiDung){
        ChonCauTraLoi(v);
        if (hienThiCauHoiActivity.checkAnsert(ansert,noiDung)){
            SetDapAnDung(v);
        }
    }


    private void CheckPhuongAn(final CauHoi cauHoi) {
        viewHolder.btnPhuongAnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!HienThiCauHoiFragment.this.isChecked){
                    HienThiCauHoiFragment.this.isChecked = true;
                    CheckCauTraLoi(v,"A",cauHoi.getNoiDung());
                }

            }
        });
        viewHolder.btnPhuongAnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!HienThiCauHoiFragment.this.isChecked){
                    ChonCauTraLoi(v);
                    CheckCauTraLoi(v,"B",cauHoi.getNoiDung());
                    HienThiCauHoiFragment.this.isChecked = true;
                }

            }
        });
        viewHolder.btnPhuongAnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!HienThiCauHoiFragment.this.isChecked){
                    CheckCauTraLoi(v,"C",cauHoi.getNoiDung());
                    HienThiCauHoiFragment.this.isChecked = true;
                }

            }
        });
        viewHolder.btnPhuongAnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!HienThiCauHoiFragment.this.isChecked){
                    CheckCauTraLoi(v,"D",cauHoi.getNoiDung());
                    HienThiCauHoiFragment.this.isChecked = true;
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
        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_dialog_tro_giup_khang_gia,null,false);
        dialog.setView(view);
        final AlertDialog dialog2 = dialog.create();
        Button btnXinCamOn = view.findViewById(R.id.btnXinCamOn);
        btnXinCamOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });
        dialog2.show();
    }


    private void CounterLimited() {
        int totaltime= 1;
        int second = 30;
        TimeCounter counter = new TimeCounter(totaltime*second*1000,1000,viewHolder.tvTimer);
        counter.start();
    }



    private void SetDapAnDung(View v) {
        v.setBackgroundColor(Color.RED);
    }

    private void ChonCauTraLoi(View v) {
       v.setBackgroundColor(Color.GREEN);
    }

    private class ViewHolder{
        private TextView tvCauHoiSo,tvCauHoi;
        private Button btnPhuongAnA,btnPhuongAnB,btnPhuongAnC,btnPhuongAnD;
        private TextView tvTimer;
        private ImageButton ivbtnDoiCauHoi,ivbtnNamMoi,ivbtnTroGiupKhangGia,ivbtnGioNguoiThan,ivbtnMuaCredit;
    }
}
