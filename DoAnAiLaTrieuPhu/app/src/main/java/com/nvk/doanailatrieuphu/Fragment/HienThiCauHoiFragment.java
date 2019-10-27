package com.nvk.doanailatrieuphu.Fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
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
import com.nvk.doanailatrieuphu.Adapter.CauHoiAdapter;
import com.nvk.doanailatrieuphu.Controller.CauHoiController;
import com.nvk.doanailatrieuphu.Model.CauHoi;
import com.nvk.doanailatrieuphu.R;
import com.nvk.doanailatrieuphu.Utilities.TimeCounter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */

public class HienThiCauHoiFragment extends Fragment {
    //nó sẽ bị reset khi cập nhật adapter
    private List<CauHoi> cauHois;
    private CauHoi cauHoi;
    private int position;
    private Context context;
    private CauHoiAdapter adapter;
    private TextView tvCauHoiSo, tvCauHoi;
    private Button[] btnPhuongAn;
    private CauHoiController cauHoiController;
    private HienThiCauHoiActivity hienThiCauHoiActivity;
    private boolean isChecked = false;
    private ImageButton[] ivbtnSP;

    private final int timeChuyenCauHoi = 2000;
    private final int countTime = 1000;



    public HienThiCauHoiFragment() {
    }

    public HienThiCauHoiFragment(List<CauHoi> cauHois, int position, Context context, CauHoiAdapter adapter) {
        this.cauHois = cauHois;
        this.position = position;
        this.context = context;
        this.adapter = adapter;
        this.hienThiCauHoiActivity = (HienThiCauHoiActivity) context;
        this.cauHoiController = new CauHoiController(context);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_hien_thi_cau_hoi, container, false);
        radiation(view);
        return view;
    }

    private CauHoi getItem(int position) {
        return cauHois.get(position);
    }


    private void radiation(View v) {

        tvCauHoiSo = v.findViewById(R.id.tvCauHoiSo);
        tvCauHoi = v.findViewById(R.id.tvCauHoi);
        btnPhuongAn = new Button[4];
        btnPhuongAn[0] = v.findViewById(R.id.btnPhuongAnA);
        btnPhuongAn[1] = v.findViewById(R.id.btnPhuongAnB);
        btnPhuongAn[2] = v.findViewById(R.id.btnPhuongAnC);
        btnPhuongAn[3] = v.findViewById(R.id.btnPhuongAnD);

        View vFooter = v.findViewById(R.id.vFooter);
        ivbtnSP = new ImageButton[5];
        ivbtnSP[0] = vFooter.findViewById(R.id.ivbtnDoiCauHoi);
        ivbtnSP[1] = vFooter.findViewById(R.id.ivbtnNamMoi);
        ivbtnSP[2] = vFooter.findViewById(R.id.ivbtnTroGiupKhangGia);
        ivbtnSP[3] = vFooter.findViewById(R.id.ivbtnGioNguoiThan);
        ivbtnSP[4] = vFooter.findViewById(R.id.ivbtnMuaCredit);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setDataText();
        chonCauHoi();
        loaiBatSuKienSupport();

    }

    private void chuyenCauSauHaiGiay() {
        CountDownTimer timer = new CountDownTimer(timeChuyenCauHoi,countTime) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                chuyenPage();
            }
        };
        timer.start();
    }

    private void chuyenPage() {
        if (hienThiCauHoiActivity.vpgShowCauHoi.getCurrentItem() == cauHois.size() -1){
            Toast.makeText(context,"HẾT CÂU",Toast.LENGTH_SHORT).show();
        }else{
            hienThiCauHoiActivity.vpgShowCauHoi.setCurrentItem(position+1);
        }

    }

    private void loaiBatSuKienSupport() {
        ivbtnSP[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doiCauHoi();
            }
        });
        ivbtnSP[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anHaiDapAnSai();
            }
        });
        ivbtnSP[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                troGiupKhangGia();
            }
        });

        ivbtnSP[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goiDienNguoiThan();
            }
        });

    }

    private void goiDienNguoiThan() {
        if (!hienThiCauHoiActivity.ischeckedSP[2]){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            View view = LayoutInflater.from(context).inflate(R.layout.custom_dialog_goi_dien,null,false);
            alertDialog.setView(view);
            final AlertDialog dialog = alertDialog.create();
            TextView tvCauTraLoi = view.findViewById(R.id.tvCauTraLoi);
            Button btnXinCamOn = view.findViewById(R.id.btnXinCamOn);
            tvCauTraLoi.setText(cauHoi.getDapAn());
            btnXinCamOn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
            hienThiCauHoiActivity.ischeckedSP[2] = true;
        }

    }

    private void troGiupKhangGia() {

    }

    private void anHaiDapAnSai() {
        if (!hienThiCauHoiActivity.ischeckedSP[1]){
            int dem =0;
            for (int i = 0; i < btnPhuongAn.length ; i++) {
                if (!btnPhuongAn[i].getTag().equals(cauHoi.getDapAn())){
                    if (dem == 2){
                        break;
                    }else{
                        btnPhuongAn[i].setVisibility(View.INVISIBLE);
                        dem++;
                    }

                }
            }
            hienThiCauHoiActivity.ischeckedSP[1] = true;
        }

    }


    private void restartAdapterSuport(){
        adapter.notifyDataSetChanged();
    }

    private void doiCauHoi() {
        if (!hienThiCauHoiActivity.ischeckedSP[0]){
            Collections.shuffle(cauHois);
            restartAdapterSuport();
            hienThiCauHoiActivity.ischeckedSP[0] = true;
        }

    }

    private void chonCauHoi() {
        for (int i = 0; i < btnPhuongAn.length; i++) {
            setSuKienPhuongAn(i);
        }
    }

    private void setSuKienPhuongAn(final int i) {
        //1 hàm bắt 4 sự kiện ấn vào nút
        btnPhuongAn[i].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDapAn(v);
                chuyenCauSauHaiGiay();
            }
        });
    }

    private void checkDapAn(View v) {
        //nếu chưa chọn thì chọn , nếu đã chọn thì không cho nhấn nữa
        if (!isChecked) {
            //kiểm tra nhấn vào lần đầu hiện câu đúng lên
            if (v.getTag().equals(cauHoi.getDapAn())) {
                v.setBackgroundColor(Color.RED);
                tangDiemLen();
            } else {
                v.setBackgroundColor(Color.GREEN);
            }

            //Kiểm tra xem khi đã ấn vào câu sai thì hiện câu đúng lên
            for (int i = 0; i < btnPhuongAn.length; i++) {
                if (btnPhuongAn[i].getTag().equals(cauHoi.getDapAn())) {
                    btnPhuongAn[i].setBackgroundColor(Color.RED);
                    break;
                }
            }
            //đã chọn xong
            isChecked = true;
        }

    }

    private void tangDiemLen() {
        hienThiCauHoiActivity.tongDiem+=50;
        hienThiCauHoiActivity.tvDiem.setText("Điểm: "+hienThiCauHoiActivity.tongDiem);
    }

    private void setDataText() {
        //hiện khi start view
        this.cauHoi = getItem(position);
        tvCauHoiSo.setText((position + 1) + "");
        tvCauHoi.setText(cauHoi.getNoiDung());
        btnPhuongAn[0].setText(cauHoi.getPhuongAnA());
        btnPhuongAn[1].setText(cauHoi.getPhuongAnB());
        btnPhuongAn[2].setText(cauHoi.getPhuongAnC());
        btnPhuongAn[3].setText(cauHoi.getPhuongAnD());
    }

}
