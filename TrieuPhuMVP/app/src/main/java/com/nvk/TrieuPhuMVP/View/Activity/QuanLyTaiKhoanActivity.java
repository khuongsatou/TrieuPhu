package com.nvk.TrieuPhuMVP.View.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.Presenter.DangKyPresenter;
import com.nvk.TrieuPhuMVP.Presenter.QuanLyTaiKhoanPresenter;
import com.nvk.TrieuPhuMVP.R;
import com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis;
import com.nvk.TrieuPhuMVP.View.UI.QuanLyTaiKhoanView;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_DANGNHAP;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.BASE;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.BASE_IMAGE;

public class QuanLyTaiKhoanActivity extends AppCompatActivity implements View.OnClickListener, QuanLyTaiKhoanView {
    private static final int CODE_GALLERY_REQUEST = 123;
    private EditText edtTenDangNhapQLTK,edtEmailQLTK,edtMatKhauQLTK,edtXacNhanMatKhauQLTK;
    private Button btnCapNhatQLTK,btnDoiHinhDaiDien;
    private ImageView ivLogo;
    private Bitmap bitmap;

    public static final String urlUpLoad =BASE+"nguoi_choi/upload";
    private QuanLyTaiKhoanPresenter quanLyTaiKhoanPresenter = new QuanLyTaiKhoanPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_tai_khoan);

        initView();
        initAction();
        initLoad();
    }

    private void initLoad() {
        quanLyTaiKhoanPresenter.setNguoiChoi(((NguoiChoi) getIntent().getSerializableExtra(KEY_DANGNHAP)));
        NguoiChoi nguoiChoi = quanLyTaiKhoanPresenter.getNguoiChoi();
        edtTenDangNhapQLTK.setText(nguoiChoi.getTen_dang_nhap());
        edtEmailQLTK.setText(nguoiChoi.getEmail());
        Picasso.get()
                .load(BASE_IMAGE+nguoiChoi.getHinh_dai_dien())
                .error(R.drawable.logo_android)
                .into(ivLogo);
    }

    private void initAction() {
        btnCapNhatQLTK.setOnClickListener(this);
        btnDoiHinhDaiDien.setOnClickListener(this);
    }

    private void initView() {
        edtTenDangNhapQLTK = findViewById(R.id.edtTenDangNhapQLTK);
        edtEmailQLTK = findViewById(R.id.edtEmailQLTK);
        edtMatKhauQLTK = findViewById(R.id.edtMatKhauQLTK);
        edtXacNhanMatKhauQLTK = findViewById(R.id.edtXacNhanMatKhauQLTK);
        btnCapNhatQLTK = findViewById(R.id.btnCapNhatQLTK);
        btnDoiHinhDaiDien = findViewById(R.id.btnDoiHinhDaiDien);

        View v = findViewById(R.id.i_AvatarQLTK);
        ivLogo = v.findViewById(R.id.iv_hinhDaiDien);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCapNhatQLTK:
                quanLyTaiKhoanPresenter.handleUpdate(
                        edtTenDangNhapQLTK.getText().toString().trim(),
                        edtEmailQLTK.getText().toString().trim(),
                        edtMatKhauQLTK.getText().toString().trim(),
                        edtXacNhanMatKhauQLTK.getText().toString().trim(),
                        bitmap
                        );
                break;
            case R.id.btnDoiHinhDaiDien:
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        CODE_GALLERY_REQUEST
                );
                break;

        }
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(KEY_DANGNHAP,quanLyTaiKhoanPresenter.getNguoiChoi());
        setResult(RESULT_OK,intent);
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CODE_GALLERY_REQUEST && resultCode==RESULT_OK && data !=null){
            Uri filePath = data.getData();
            try{
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                this.bitmap = BitmapFactory.decodeStream(inputStream);
                ivLogo.setImageBitmap(bitmap);
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CODE_GALLERY_REQUEST){
            if (grantResults.length >=0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Select Image"),CODE_GALLERY_REQUEST);
            }else{
                Toast.makeText(getApplicationContext(),"You don't have permisstion",Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    public void updateSuccess() {
        Toast.makeText(this,getString(R.string.string_success),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateFail() {
        Toast.makeText(this,getString(R.string.string_fail),Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean existUserName(String username) {
        return false;
    }

    @Override
    public boolean existEmail(String email) {
        return false;
    }

    @Override
    public boolean checkRepass(String pass, String repass) {
        return (pass.equals(repass));
    }

    @Override
    public void setErrorRepass() {
        edtXacNhanMatKhauQLTK.setError(getString(R.string.string_matched));
    }

    @Override
    public void setErrorUsername() {

    }

    @Override
    public void setErrorPassword() {
        edtMatKhauQLTK.setError(getString(R.string.string_empty));
    }

    @Override
    public void setErrorEmail() {
        edtEmailQLTK.setError(getString(R.string.string_empty));
    }

    @Override
    public void clearForm() {

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
        return false;
    }

    @Override
    public void closeApp() {

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
}
