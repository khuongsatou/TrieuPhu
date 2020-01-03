package com.nvk.TrieuPhuMVP.View.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.Presenter.MangHinhChinhPresenter;
import com.nvk.TrieuPhuMVP.R;
import com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis;
import com.nvk.TrieuPhuMVP.View.UI.MangHinhChinhView;
import com.squareup.picasso.Picasso;

import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_DANGNHAP;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_SHARE_PRE;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.TOKEN;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.BASE_IMAGE;

public class MangHinhChinhActivity extends AppCompatActivity implements MangHinhChinhView, View.OnClickListener {
    private MangHinhChinhPresenter mangHinhChinhPresenter = new MangHinhChinhPresenter(this);
    private Button btnQuanLyTaiKhoan,btnTroChoiMoi,btnLichSuChoi,btnBangXepHang,btnMuaCredit;
    private SharedPreferences sharedPreferences;


    public static final int KEY_REQUESTCODE = 123;
    private TextView tvTenDangNhap,tvCredit;
    private Intent intent;
    private ImageView ivHinhDaiDien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mang_hinh_chinh);

        initView();
        initAction();
        initLoad();

    }

    private void initLoad() {
          sharedPreferences = getSharedPreferences(KEY_SHARE_PRE, MODE_PRIVATE);
          mangHinhChinhPresenter.handleInfo();
//        mangHinhChinhPresenter.setNguoiChoi((NguoiChoi)getIntent().getSerializableExtra(KEY_DANGNHAP));

    }



    private void initAction() {
        btnQuanLyTaiKhoan.setOnClickListener(this);
        btnTroChoiMoi.setOnClickListener(this);
        btnLichSuChoi.setOnClickListener(this);
        btnBangXepHang.setOnClickListener(this);
        btnMuaCredit.setOnClickListener(this);
    }

    private void initView() {
        btnQuanLyTaiKhoan = findViewById(R.id.btnQuanLyTaiKhoan);
        btnTroChoiMoi = findViewById(R.id.btnTroChoiMoi);
        btnLichSuChoi = findViewById(R.id.btnLichSuChoi);
        btnBangXepHang = findViewById(R.id.btnBangXepHang);
        btnMuaCredit = findViewById(R.id.btnMuaCredit);
        View v_avatar = findViewById(R.id.i_Avatar);
        ivHinhDaiDien = v_avatar.findViewById(R.id.iv_hinhDaiDien);
        View v_profile = findViewById(R.id.i_profile);
        tvTenDangNhap = v_profile.findViewById(R.id.tvTenDangNhap);
        tvCredit = v_profile.findViewById(R.id.tvSoTien);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==KEY_REQUESTCODE && resultCode == RESULT_OK && data != null){
            mangHinhChinhPresenter.setNguoiChoi((NguoiChoi) data.getSerializableExtra(KEY_DANGNHAP));
            Picasso.get()
                    .load(BASE_IMAGE+mangHinhChinhPresenter.getNguoiChoi().getHinh_dai_dien())
                    .error(R.drawable.logo_android)
                    .into(ivHinhDaiDien);
            tvCredit.setText(mangHinhChinhPresenter.getNguoiChoi().getCredit()+"");
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnQuanLyTaiKhoan:
                intent = new Intent(this,QuanLyTaiKhoanActivity.class);
                mangHinhChinhPresenter.navigate(intent);
                startActivityForResult(intent,KEY_REQUESTCODE);
                break;
            case R.id.btnTroChoiMoi:
                intent = new Intent(this,MangHinhTroChoiActivity.class);
                mangHinhChinhPresenter.navigate(intent);
                startActivityForResult(intent,KEY_REQUESTCODE);
                break;
            case R.id.btnLichSuChoi:
                intent = new Intent(this,LichSuChoiActivity.class);
                mangHinhChinhPresenter.navigate(intent);
                startActivityForResult(intent,KEY_REQUESTCODE);
                break;
            case R.id.btnBangXepHang:
                intent = new Intent(this,BangXepHangActivity.class);
                mangHinhChinhPresenter.navigate(intent);
                startActivityForResult(intent,KEY_REQUESTCODE);
                break;
            case R.id.btnMuaCredit:
                intent = new Intent(this,MuaCreditActivity.class);
                mangHinhChinhPresenter.navigate(intent);
                startActivityForResult(intent,KEY_REQUESTCODE);
                break;
        }
    }

    @Override
    public void setErrorInternet() {
        Toast.makeText(this,getString(R.string.string_server_internet),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setErrorServer() {
        Toast.makeText(this,getString(R.string.string_server_disconnect),Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean checkInternet() {
        return NetWorkUtilitis.checkConnect(this);
    }

    @Override
    public void closeApp() {
        NetWorkUtilitis.showDialogNetWork(getString(R.string.string_server_internet), this).show();
    }

    @Override
    public void closeDialog(ProgressDialog dialog) {
        dialog.dismiss();
    }

    @Override
    public void loadBackGround(StringRequest request) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    @Override
    public ProgressDialog showDialog() {
        return NetWorkUtilitis.showProress(this);
    }

    @Override
    public String getReference() {
        return sharedPreferences.getString(TOKEN,"");
    }

    @Override
    public void restartData() {
        tvTenDangNhap.setText(mangHinhChinhPresenter.getNguoiChoi().getTen_dang_nhap());
        tvCredit.setText(mangHinhChinhPresenter.getNguoiChoi().getCredit()+"");
        Picasso.get()
                .load(BASE_IMAGE+mangHinhChinhPresenter.getNguoiChoi().getHinh_dai_dien())
                .error(R.drawable.logo_android)
                .into(ivHinhDaiDien);
    }
}
