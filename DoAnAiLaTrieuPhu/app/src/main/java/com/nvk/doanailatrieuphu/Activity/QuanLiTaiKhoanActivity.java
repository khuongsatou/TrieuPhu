package com.nvk.doanailatrieuphu.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.nvk.doanailatrieuphu.Controller.NguoiChoiController;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.nvk.doanailatrieuphu.Activity.DangNhapActivity.KEY_DANGNHAP;

public class QuanLiTaiKhoanActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_GALLERY = 123;
    private ImageView ivLogo;
    private EditText edtTenTaiKhoan,edtEmail,edtMatKhau,edtXacNhanMatKhau;
    private NguoiChoiController nguoiChoiController= new NguoiChoiController(this);;
    private int id_nguoiChoi = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_li_tai_khoan);

        radiation();
        selectTK();

    }

    public void xuLiAnhDaiDien(View view) {

    }

    public static void addImageToGallery(final String filePath, final Context context) {

        ContentValues values = new ContentValues();

        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.MediaColumns.DATA, filePath);

        context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }


    private void selectTK() {
        NguoiChoi nguoiChoi = (NguoiChoi) getIntent().getSerializableExtra(KEY_DANGNHAP);
        this.id_nguoiChoi = nguoiChoi.getId();
        edtTenTaiKhoan.setText(nguoiChoi.getTenDangNhap());
        edtEmail.setText(nguoiChoi.getEmail());
    }

    private void radiation() {
        ivLogo = findViewById(R.id.ivLogo);
        edtTenTaiKhoan = findViewById(R.id.edtTenDangNhap);
        edtEmail = findViewById(R.id.edtEmail);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        edtXacNhanMatKhau = findViewById(R.id.edtXacNhanMatKhau);
    }

    public void xuLiDoiHinhDaiHien(View view) {
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
                //ivLogo.setImageBitmap(bitmap);
                Log.d("AAAAA",bitmap+"");
                Drawable drawable = new BitmapDrawable(getResources(),bitmap);
                Log.d("AAAAA",drawable+"");
                ivLogo.setImageDrawable(drawable);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public void xuLiCapNhap(View view) {
        String tenTaiKhoan = edtTenTaiKhoan.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String matKhau = edtMatKhau.getText().toString().trim();
        String xacNhanMatKhau = edtXacNhanMatKhau.getText().toString().trim();
        if (email.equals("") || matKhau.equals("")){
            Toast.makeText(getApplicationContext(),getString(R.string.tb_chua_nhap_du),Toast.LENGTH_SHORT).show();
        }else{
            if (!matKhau.equals(xacNhanMatKhau)){
                Toast.makeText(getApplicationContext(),getString(R.string.tb_mat_khau_khong_giong_nhau),Toast.LENGTH_SHORT).show();
            }else{
                NguoiChoi nguoiChoi = new NguoiChoi();
                nguoiChoi.setId(this.id_nguoiChoi);
                nguoiChoi.setTenDangNhap(tenTaiKhoan);
                nguoiChoi.setEmail(email);
                nguoiChoi.setMatKhau(matKhau);
                //xử lí hinh sau
                //chắc nên lưu đường dãn thôi , bằng cách copy vào drawer
                //nguoiChoi.setHinhDaiDien();
                Boolean result = nguoiChoiController.updateNguoiChoi(nguoiChoi);
                if (result){
                    Toast.makeText(this,getString(R.string.tb_update_user_tc),Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(this,getString(R.string.tb_update_user_tb),Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
