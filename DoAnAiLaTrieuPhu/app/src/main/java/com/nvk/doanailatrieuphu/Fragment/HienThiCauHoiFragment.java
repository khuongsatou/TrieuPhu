package com.nvk.doanailatrieuphu.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.nvk.doanailatrieuphu.Activity.HienThiCauHoiActivity;
import com.nvk.doanailatrieuphu.Adapter.CauHoiAdapter;
import com.nvk.doanailatrieuphu.Model.CauHoi;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyPermission;
import java.util.concurrent.TimeUnit;


import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.COUNT_TIME;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.GIA_DAP_AN;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.GIA_DIEM;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.KEY_CH_POSITION;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.KEY_CREDIT;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.KEY_ID;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.KEY_LIMIT;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.LIMIT_KHOI_TAO;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.PAGE_SIZE;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.TIME_CHUYEN_CAU_HOI;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.TOTAL_TIME_TIMER;
import static com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis.BASE;
import static com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis.URI_NGUOI_CHOI_UPDATE_CREDIT;

/**
 * A simple {@link Fragment} subclass.
 */

public class HienThiCauHoiFragment extends Fragment {
    //nó sẽ bị reset khi cập nhật adapter
    private List<CauHoi> cauHois;
    private CauHoi cauHoi;
    private CauHoiAdapter adapter;
    private TextView tvCauHoiSo, tvCauHoi;
    private Button[] btnPhuongAn;
    private HienThiCauHoiActivity hienThiCauHoiActivity;
    private boolean isChecked = false;
    private ImageButton[] ivbtnSP;
    public TextView tvTimer;

    //refresh lại sao mỗi lần chuyển
    private String saveTime = null;

    private NguoiChoi nguoiChoi;
    private int giaCredit = 0;

    public HienThiCauHoiFragment() {
    }

    public HienThiCauHoiFragment(List<CauHoi> cauHois, Context context, CauHoiAdapter adapter, NguoiChoi nguoiChoi) {
        this.cauHois = cauHois;
        this.adapter = adapter;
        this.hienThiCauHoiActivity = (HienThiCauHoiActivity) context;
        this.nguoiChoi = nguoiChoi;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(hienThiCauHoiActivity).inflate(R.layout.fragment_hien_thi_cau_hoi, container, false);
        radiation(view);
        return view;
    }

    private CauHoi getItem(int position) {
        return cauHois.get(position);
    }

    private void radiation(View v) {
        tvCauHoiSo = v.findViewById(R.id.tvCauHoiSo);
        tvCauHoi = v.findViewById(R.id.tvCauHoi);

        tvTimer = v.findViewById(R.id.tvtimer);


        btnPhuongAn = new Button[4];
        btnPhuongAn[0] = v.findViewById(R.id.btnPhuongAnA);
        btnPhuongAn[1] = v.findViewById(R.id.btnPhuongAnB);
        btnPhuongAn[2] = v.findViewById(R.id.btnPhuongAnC);
        btnPhuongAn[3] = v.findViewById(R.id.btnPhuongAnD);

        //tham chiếu file include
        View vFooter = v.findViewById(R.id.vFooter);
        //ánh xạ các ID trong đó
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
        createCountDownTime();
        startFirstCountDownTime();
        setDataText();
        chonCauHoi();
        loaiBatSuKienSupport();


    }

    private void startFirstCountDownTime() {
        //Kiểm tra lần đầu start count lên
        //nếu là vị trí đầu tiên thì start thằng 0 lên , Lần 0 OK
        if (getArguments().getInt(KEY_CH_POSITION) == 0){
            hienThiCauHoiActivity.countDownTimer.get(0).start();
            //xử lí lần đầu
            hienThiCauHoiActivity.checkCountTimerLoading = false;
        }

        //set lại đã xong lần đầu

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //start 2-> n ,
        // isVisibleToUser : false , false , true (Khi xong)
        // isResumed: true (Đã Hoạt động)
        // isVisible: đã hiển thị
        // checkCountTimerLoading : chưa được notification update
        // Thì chạy
        if (isVisibleToUser && isResumed() && isVisible()){
            //lần 2 ->n kiểm tra đã loading hay chưa nếu chưa thì start counttimer lên
            //Limit Khởi tạo 4 fragemnt , bắt đầu từ 0
            //1 OK , 2 OK , 3 OK , 4 OK , Loadmore lần 5 ko vào đây
            if (!hienThiCauHoiActivity.checkCountTimerLoading){
                //trước khi load sẽ ko vô
                if (getArguments().getInt(KEY_CH_POSITION) != cauHois.size() -1){
                    hienThiCauHoiActivity.countDownTimer.get(getArguments().getInt(KEY_CH_POSITION)).start();
                }else{
                    //không được chạy khi loading
                }
            }

        }
    }

    private void createCountDownTime() {
        //tạo được arr 0 , 1 nếu loading 2 page
        hienThiCauHoiActivity.countDownTimer.set(getArguments().getInt(KEY_CH_POSITION),new CountDownTimer(TOTAL_TIME_TIMER, COUNT_TIME) {
            @Override
            public void onTick(long millisUntilFinished) {
                String countTime = String.format("%02d:%02d",
                        //số này chuyển sang giây
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                setTextTimer(countTime);
            }
            @Override
            public void onFinish() {
                //tvTimer.setText(saveTime);
                hienThiCauHoiActivity.vpgShowCauHoi.setCurrentItem(hienThiCauHoiActivity.vpgShowCauHoi.getCurrentItem()+1);
            }
        });
    }

    private void setTextTimer(String countTime) {
        this.tvTimer.setText(countTime + "s");
    }

    private void chuyenCauSauHaiGiay() {
        new CountDownTimer(TIME_CHUYEN_CAU_HOI, COUNT_TIME) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                chuyenPage();
            }
        }.start();
    }

    public void chuyenPage() {
        if (hienThiCauHoiActivity.vpgShowCauHoi.getCurrentItem() == cauHois.size() - 1) {
            //showDialogKetThucLuotChoi();
            Toast.makeText(hienThiCauHoiActivity, "HẾT CÂU", Toast.LENGTH_SHORT).show();
        } else {
            //tắt timer
            hienThiCauHoiActivity.countDownTimer.get(getArguments().getInt(KEY_CH_POSITION)).cancel();
            //bật chuyển 2s
            hienThiCauHoiActivity.vpgShowCauHoi.setCurrentItem(hienThiCauHoiActivity.vpgShowCauHoi.getCurrentItem()+1);
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
        ivbtnSP[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                muaCredit();
            }
        });

    }

    private void muaCredit() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(hienThiCauHoiActivity);
        alert.setTitle("Bạn có muốn mua đáp án.");
        alert.setMessage("Giá 100 diamon");
        alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, int which) {
                giaCredit = nguoiChoi.getCredit() - GIA_DAP_AN;
                nguoiChoi.setCredit(giaCredit);

                final Map<String, String> map = new HashMap<>();
                map.put(KEY_ID, String.valueOf(nguoiChoi.getId()));
                map.put(KEY_CREDIT, String.valueOf(giaCredit));

                StringRequest request = new StringRequest(Request.Method.POST, BASE + URI_NGUOI_CHOI_UPDATE_CREDIT, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("AAAA", response);
                        try {
                            JSONObject objResult = new JSONObject(response);
                            boolean result = objResult.getBoolean("success");
                            if (result) {
                                showDapAn();
                                Toast.makeText(hienThiCauHoiActivity, "Xong", Toast.LENGTH_SHORT).show();
                                hienThiCauHoiActivity.tvTinDung.setText(nguoiChoi.getCredit() + "");
                            }else{
                                Toast.makeText(hienThiCauHoiActivity, "Có lỗi Xãy ra", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(hienThiCauHoiActivity, "Server Offline", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        return map;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(hienThiCauHoiActivity);
                queue.add(request);
            }
        });
        alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    private void showDapAn() {
        for (int i = 0; i < btnPhuongAn.length; i++) {
            if (cauHoi.getDapAn().equals(btnPhuongAn[i].getTag())) {
                btnPhuongAn[i].setBackgroundColor(Color.RED);
                tangDiemLen();
                chuyenCauSauHaiGiay();
                break;
            }
        }

    }

    private void goiDienNguoiThan() {
        if (!hienThiCauHoiActivity.ischeckedSP[3]) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(hienThiCauHoiActivity);
            View view = LayoutInflater.from(hienThiCauHoiActivity).inflate(R.layout.custom_dialog_goi_dien, null, false);
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
            hienThiCauHoiActivity.ischeckedSP[3] = true;
        }

    }

    private void troGiupKhangGia() {
        if (!hienThiCauHoiActivity.ischeckedSP[2]) {
            final Dialog alert = new Dialog(hienThiCauHoiActivity);
            View view = LayoutInflater.from(hienThiCauHoiActivity).inflate(R.layout.custom_dialog_tro_giup_khang_gia, null, false);
            alert.setContentView(view);
            Button btnXinCamOn = view.findViewById(R.id.btnXinCamOn);
            BarChart bcBieuDoCot = view.findViewById(R.id.bcBieuDoCot);

            bcBieuDoCot.getDescription().setEnabled(false);
            bcBieuDoCot.setFitBars(true);

            ArrayList<BarEntry> yBarChart = new ArrayList<>();
            //Nà ní
            int random_1 = (int) (Math.random() * 100);
            int r2 = 100 - random_1;
            int random_2 = (int) (Math.random() * 100 % (r2 + 1));
            int r3 = 100 - (random_1 + random_2);
            int random_3 = (int) (Math.random() * 100 % (r3 + 1));
            int r4 = 100 - (random_1 + random_2 + random_3);
            //viết dư số 2 đã delete
            int random_4 = r4;

            yBarChart.add(new BarEntry(1, random_1));
            yBarChart.add(new BarEntry(2, random_2));
            yBarChart.add(new BarEntry(3, random_3));
            yBarChart.add(new BarEntry(4, random_4));


            BarDataSet set = new BarDataSet(yBarChart, "Trợ Giúp Kháng Giả");
            set.setColors(ColorTemplate.MATERIAL_COLORS);
            set.setDrawValues(true);

            BarData data = new BarData(set);
            bcBieuDoCot.setData(data);
            bcBieuDoCot.invalidate();
            bcBieuDoCot.animateY(500);

            btnXinCamOn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alert.dismiss();
                }
            });
            alert.show();
            hienThiCauHoiActivity.ischeckedSP[2] = true;
        }
    }


    private void anHaiDapAnSai() {
        if (!hienThiCauHoiActivity.ischeckedSP[1]) {
            int dem = 0;
            for (int i = 0; i < btnPhuongAn.length; i++) {
                if (!btnPhuongAn[i].getTag().equals(cauHoi.getDapAn())) {
                    if (dem == 2) {
                        break;
                    } else {
                        btnPhuongAn[i].setVisibility(View.INVISIBLE);
                        dem++;
                    }

                }
            }
            hienThiCauHoiActivity.ischeckedSP[1] = true;
        }

    }

    private void restartAdapterSuport() {
        adapter.notifyDataSetChanged();
    }

    private void doiCauHoi() {
        if (!hienThiCauHoiActivity.ischeckedSP[0]) {
            //Trộn mảng
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
                hienThiCauHoiActivity.giamMangNguoiChoi(getArguments().getInt(KEY_CH_POSITION));
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
        //50 điểm cho 1 câu hỏi
        hienThiCauHoiActivity.tongDiem += GIA_DIEM;
        hienThiCauHoiActivity.tvDiem.setText("Điểm: " + hienThiCauHoiActivity.tongDiem);
    }

    private void setDataText() {
        //hiện khi start view
        this.cauHoi = getItem(getArguments().getInt(KEY_CH_POSITION));
        tvCauHoiSo.setText((getArguments().getInt(KEY_CH_POSITION)+1) + "");


        //nếu nó bị notification update thì true
        if (hienThiCauHoiActivity.checkCountTimerLoading){
            hienThiCauHoiActivity.countDownTimer.get(getArguments().getInt(KEY_CH_POSITION,-1)).start();
            hienThiCauHoiActivity.checkCountTimerLoading = false;
        }

        //khởi tạo
        //tvTimer.setText(saveTime);
        tvCauHoi.setText(cauHoi.getNoiDung());
        btnPhuongAn[0].setText(cauHoi.getPhuongAnA());
        btnPhuongAn[1].setText(cauHoi.getPhuongAnB());
        btnPhuongAn[2].setText(cauHoi.getPhuongAnC());
        btnPhuongAn[3].setText(cauHoi.getPhuongAnD());
    }

}
