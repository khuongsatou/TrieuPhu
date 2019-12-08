package com.nvk.TrieuPhuMVP.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nvk.TrieuPhuMVP.Presenter.MangHinhChinhPresenter;
import com.nvk.TrieuPhuMVP.R;

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
        tvCredit = v_profile.findViewById(R.id.tvCredit);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnQuanLyTaiKhoan:
                
                break;
            case R.id.btnTroChoiMoi:

                break;
            case R.id.btnLichSuChoi:

                break;
            case R.id.btnBangXepHang:

                break;
            case R.id.btnMuaCredit:

                break;
        }
    }
}
