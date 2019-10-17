package com.nvk.doanailatrieuphu.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.nvk.doanailatrieuphu.Controller.NguoiChoiController;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.nvk.doanailatrieuphu.Activity.DangNhapActivity.KEY_DANGNHAP_TENDANGNHAP;

public class QuanLiTaiKhoanActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_GALLERY = 123;
    private ImageView ivLogo;
    private EditText edtTenTaiKhoan,edtEmail,edtMatKhau,edtXacNhanMatKhau;

    private NguoiChoiController nguoiChoiController= new NguoiChoiController(this);;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_li_tai_khoan);

        Radiation();
        SelectTK();
    }



    private void SelectTK() {
        Intent intent = getIntent();
        //nguoiChoiController = new NguoiChoiController(this);
        NguoiChoi nguoiChoi = nguoiChoiController.getTK(intent.getStringExtra(KEY_DANGNHAP_TENDANGNHAP));
        edtTenTaiKhoan.setText(nguoiChoi.getTenDangNhap());
        edtEmail.setText(nguoiChoi.getEmail());
        edtMatKhau.setText(nguoiChoi.getMatKhau());

    }

    private void Radiation() {
        ivLogo = findViewById(R.id.ivLogo);

        edtTenTaiKhoan = findViewById(R.id.edtTenDangNhap);
        edtEmail = findViewById(R.id.edtEmail);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        edtXacNhanMatKhau = findViewById(R.id.edtXacNhanMatKhau);
    }

    public void XuLiDoiHinhDaiHien(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        setResult(RESULT_OK,intent);
        startActivityForResult(intent,REQUEST_CODE_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            InputStream inputStream = null;
            try {
                inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ivLogo.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public void XuLiCapNhap(View view) {
        //nguoiChoiController = new NguoiChoiController(this);

        String tenTaiKhoan = edtTenTaiKhoan.getText().toString();
        String email = edtEmail.getText().toString();
        String matKhau = edtMatKhau.getText().toString();
        NguoiChoi nguoiChoi = new NguoiChoi();
        nguoiChoi.setTenDangNhap(tenTaiKhoan);
        nguoiChoi.setEmail(email);
        nguoiChoi.setMatKhau(matKhau);

        Boolean result = nguoiChoiController.UpdateUser(nguoiChoi);
        if (result){
            Toast.makeText(this,getString(R.string.tb_update_user_tc),Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,getString(R.string.tb_update_user_tb),Toast.LENGTH_LONG).show();
        }

    }
}
