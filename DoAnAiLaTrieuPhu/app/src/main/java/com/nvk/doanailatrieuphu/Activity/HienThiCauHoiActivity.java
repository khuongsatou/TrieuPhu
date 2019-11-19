package com.nvk.doanailatrieuphu.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nvk.doanailatrieuphu.Adapter.CauHoiAdapter;
import com.nvk.doanailatrieuphu.Model.CauHoi;
import com.nvk.doanailatrieuphu.Model.LinhVuc;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;
import com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import static com.nvk.doanailatrieuphu.Controller.CauHoiController.COLUMN_LINH_VUC_ID;
import static com.nvk.doanailatrieuphu.Controller.LuotChoiController.COLUMN_LUOT_CHOI_DIEM;
import static com.nvk.doanailatrieuphu.Controller.LuotChoiController.COLUMN_LUOT_CHOI_NGAY_GIO;
import static com.nvk.doanailatrieuphu.Controller.LuotChoiController.COLUMN_LUOT_CHOI_SO_CAU;
import static com.nvk.doanailatrieuphu.Controller.LuotChoiController.COLUMN_NGUOI_CHOI_ID;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.COUNT_TIME;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.GIA_LUOT_CHOI;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.KEY_CH_POSITION;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.KEY_DANGNHAP;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.KEY_LIMIT;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.KEY_LINHVUC;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.KEY_PAGE;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.LIMIT_KHOI_TAO;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.PAGE_KHOI_TAO;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.PAGE_SIZE;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.TOTAL_TIME_TIMER;
import static com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis.BASE;
import static com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis.URI_CAU_HOI;
import static com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis.URI_LUOT_CHOI_THEM;

public class HienThiCauHoiActivity extends AppCompatActivity {
    public ViewPager vpgShowCauHoi;
    public TextView tvTen, tvTinDung,tvDiem;
    private CauHoiAdapter cauHoiAdapter;
    private List<CauHoi> cauHois  = new ArrayList<>();;
    public ImageView[] ivMang = new ImageView[5];
    private NguoiChoi nguoiChoi;
    private LinhVuc linhVuc;
    private Intent intent;
    public int diemSoMang = 0;
    public int tongDiem = 0;
    public boolean[] ischeckedSP = {false, false,false, false};
    public boolean checkCountTimerLoading =false;
    private boolean checkLastPage = false;
    private int currentPage = 1;
    public ArrayList<CountDownTimer> countDownTimer = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_cau_hoi);

        radiation();
        showUserAndCredit();
        createAdapter();
        loadData(null);
        checkSurf();
    }


    private void checkSurf() {
        vpgShowCauHoi.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == (cauHois.size()-1)){
                    if(!checkLastPage){
                        currentPage++;
                        Bundle data = new Bundle();
                        data.putInt(KEY_PAGE, currentPage);
                        data.putInt(KEY_LIMIT,PAGE_SIZE);
                        loadData(data);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void loadData(Bundle data) {
        if (NetWorkUtilitis.checkConnect(this)){
            startVolley(data);
        }else{
            NetWorkUtilitis.showDialogNetWork(getString(R.string.tb_connect_internet),this);
        }
    }

    private void startVolley(Bundle data) {

        final ProgressDialog pgwait = NetWorkUtilitis.showProress(this);
        pgwait.show();
        final Map<String,String> map = new HashMap<>();
        map.put(KEY_PAGE,String.valueOf(data == null ? PAGE_KHOI_TAO : data.getInt(KEY_PAGE)));
        map.put(KEY_LIMIT,String.valueOf(data == null ? LIMIT_KHOI_TAO : data.getInt(KEY_LIMIT)));
        map.put(COLUMN_LINH_VUC_ID,String.valueOf(this.linhVuc.getId()));
        StringRequest request = new StringRequest(Request.Method.POST, BASE + URI_CAU_HOI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    checkLoadingCount();
                    pgwait.dismiss();
                    JSONObject objCauHoi = new JSONObject(response);
                    int total = objCauHoi.getInt("total");
                    int totalPage = total / PAGE_SIZE;
                    if (totalPage % PAGE_SIZE !=0){
                        totalPage++;
                    }
                    JSONArray arrCauHoi = objCauHoi.getJSONArray("cau_hoi");
                    //GET JSON
                    for (int i = 0; i < arrCauHoi.length(); i++) {
                        JSONObject objItem = arrCauHoi.getJSONObject(i);
                        int id = objItem.getInt("id");
                        String noiDung = objItem.getString("noi_dung");
                        int linhVucID = objItem.getInt("linh_vuc_id");
                        String phuongAnA = objItem.getString("phuong_an_a");
                        String phuongAnB = objItem.getString("phuong_an_b");
                        String phuongAnC = objItem.getString("phuong_an_c");
                        String phuongAnD = objItem.getString("phuong_an_d");
                        String dapAn = objItem.getString("dap_an");

                        CauHoi cauHoi= new CauHoi();
                        cauHoi.setId(id);
                        cauHoi.setLinhVucId(linhVucID);
                        cauHoi.setNoiDung(noiDung);
                        cauHoi.setPhuongAnA(phuongAnA);
                        cauHoi.setPhuongAnB(phuongAnB);
                        cauHoi.setPhuongAnC(phuongAnC);
                        cauHoi.setPhuongAnD(phuongAnD);
                        cauHoi.setDapAn(dapAn);
                        cauHois.add(cauHoi);
                        countDownTimer.add(null);
                    }
                    checkFinishLastPage(totalPage);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                cauHoiAdapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Server Offline",Toast.LENGTH_SHORT).show();
                pgwait.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void checkLoadingCount() {
        checkCountTimerLoading = true;
    }

    private void checkFinishLastPage(int totalPage) {
        checkLastPage = (currentPage == totalPage);

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

    public void giamMangNguoiChoi(int position) {
        if (this.diemSoMang == 5){
            showDialogKetThucLuotChoi(position);
            vpgShowCauHoi.setEnabled(false);
        }else{
            ivMang[this.diemSoMang].setImageResource(R.drawable.ic_action_heart_low);
            this.diemSoMang++;
        }
    }

    private void showDialogKetThucLuotChoi(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.custom_dialog_ket_thuc,null,false);
        final TextView tvDiem = view.findViewById(R.id.tvDiem);
        Button btnThemLuot = view.findViewById(R.id.btnThemLuot);
        Button btnKetThuc = view.findViewById(R.id.btnKetThuc);
        builder.setView(view);

        tvDiem.setText("Điểm: "+this.tongDiem);

        final AlertDialog dialog = builder.create();
        btnThemLuot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int giaLuotChoi = nguoiChoi.getCredit() - GIA_LUOT_CHOI;
                nguoiChoi.setCredit(giaLuotChoi);
                dialog.dismiss();
            }
        });



        btnKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Map<String,String> map = new HashMap<>();
                map.put(COLUMN_NGUOI_CHOI_ID,String.valueOf(nguoiChoi.getId()));
                map.put(COLUMN_LUOT_CHOI_SO_CAU,String.valueOf(position + 1));
                map.put(COLUMN_LUOT_CHOI_DIEM,String.valueOf(tongDiem));


                //format theo dạng cho trước
                SimpleDateFormat sdf = new SimpleDateFormat("ss:mm:hh dd/MM/yyyy");
                //lấy ngày giờ hiện tại
                String dateFormat = sdf.format(Calendar.getInstance().getTime());
                map.put(COLUMN_LUOT_CHOI_NGAY_GIO,dateFormat);

                //Viết hàm insert Lượt Chơi
                StringRequest request = new StringRequest(Request.Method.POST, BASE + URI_LUOT_CHOI_THEM, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject objResult = new JSONObject(response);
                            if (objResult.getBoolean("success")){
                                Toast.makeText(HienThiCauHoiActivity.this,"Bạn Đã Kết Thúc Lượt Chơi",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(HienThiCauHoiActivity.this,"Có lỗi Xảy ra",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HienThiCauHoiActivity.this,getString(R.string.tb_connect_internet),Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        return map;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(request);
                dialog.dismiss();
                finish();

            }
        });
        //nếu như chưa destroy activity thì hiện lên , nếu ko destroy rồi mà hiên lên nó sẽ lỗi
        if (!isFinishing()){
            dialog.show();
        }else if(dialog.isShowing()){
            dialog.dismiss();
        }

    }

    @Override
    protected void onPause() {

        super.onPause();
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
