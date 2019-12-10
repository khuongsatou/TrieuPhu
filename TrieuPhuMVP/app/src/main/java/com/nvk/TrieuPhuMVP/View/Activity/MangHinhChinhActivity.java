package com.nvk.TrieuPhuMVP.View.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.Presenter.MangHinhChinhPresenter;
import com.nvk.TrieuPhuMVP.R;
import com.nvk.TrieuPhuMVP.View.UI.MangHinhChinhView;
import com.squareup.picasso.Picasso;

import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_DANGNHAP;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.BASE_IMAGE;

public class MangHinhChinhActivity extends AppCompatActivity implements MangHinhChinhView, View.OnClickListener {
    private MangHinhChinhPresenter mangHinhChinhPresenter = new MangHinhChinhPresenter(this);
    private Button btnQuanLyTaiKhoan,btnTroChoiMoi,btnLichSuChoi,btnBangXepHang,btnMuaCredit;

    public static final String KEY_PAGE = "page";
    public static final String KEY_LIMIT = "limit";


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
        mangHinhChinhPresenter.setNguoiChoi((NguoiChoi)getIntent().getSerializableExtra(KEY_DANGNHAP));
        tvTenDangNhap.setText(mangHinhChinhPresenter.getNguoiChoi().getTen_dang_nhap());
        tvCredit.setText(mangHinhChinhPresenter.getNguoiChoi().getCredit()+"");
        Picasso.get()
                .load(BASE_IMAGE+mangHinhChinhPresenter.getNguoiChoi().getHinh_dai_dien())
                .error(R.drawable.logo_android)
                .into(ivHinhDaiDien);
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
}
