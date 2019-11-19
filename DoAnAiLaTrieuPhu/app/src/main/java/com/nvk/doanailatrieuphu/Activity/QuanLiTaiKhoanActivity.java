package com.nvk.doanailatrieuphu.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;
import com.nvk.doanailatrieuphu.R;
import com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static com.nvk.doanailatrieuphu.Controller.NguoiChoiController.COLUMN_EMAIL;
import static com.nvk.doanailatrieuphu.Controller.NguoiChoiController.COLUMN_ID;
import static com.nvk.doanailatrieuphu.Controller.NguoiChoiController.COLUMN_MAT_KHAU;
import static com.nvk.doanailatrieuphu.Controller.NguoiChoiController.COLUMN_TEN_DANG_NHAP;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.KEY_DANGNHAP;
import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.REQUEST_CODE_GALLERY;
import static com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis.BASE;
import static com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis.URI_NGUOI_CHOI_CAP_NHAT;

public class QuanLiTaiKhoanActivity extends AppCompatActivity {
    private ImageView ivLogo;
    private EditText edtTenDangNhap,edtEmail,edtMatKhau,edtXacNhanMatKhau;
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
        edtTenDangNhap.setText(nguoiChoi.getTenDangNhap());
        edtEmail.setText(nguoiChoi.getEmail());
    }

    private void radiation() {
        ivLogo = findViewById(R.id.ivLogo);
        edtTenDangNhap = findViewById(R.id.edtTenDangNhap);
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
        final int id = this.id_nguoiChoi;
        final String tenDangNhap = edtTenDangNhap.getText().toString().trim();
        final String email = edtEmail.getText().toString().trim();
        final String matKhau = edtMatKhau.getText().toString().trim();
        String xacNhanMatKhau = edtXacNhanMatKhau.getText().toString().trim();

        if (email.equals("") || matKhau.equals("")) {
            Toast.makeText(getApplicationContext(), getString(R.string.tb_chua_nhap_du),Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (!matKhau.equals(xacNhanMatKhau)){
                Toast.makeText(getApplicationContext(),getString(R.string.tb_mat_khau_khong_giong_nhau),Toast.LENGTH_SHORT).show();
            }else{
                final ProgressDialog pgwait = NetWorkUtilitis.showProress(this);
                pgwait.show();
                StringRequest request = new StringRequest(Request.Method.POST, BASE + URI_NGUOI_CHOI_CAP_NHAT, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            pgwait.dismiss();
                            JSONObject objLogin = new JSONObject(response);
                            boolean result = objLogin.getBoolean("success");
                            if (result) {
                                Toast.makeText(getApplicationContext(),tenDangNhap+" Cập Nhật Thành Công", Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(),getString(R.string.tb_update_user_tb) + " Hoặc User Tồn Tại", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAAAA", error.getMessage());
                        pgwait.dismiss();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put(COLUMN_ID,String.valueOf(id));
                        map.put(COLUMN_TEN_DANG_NHAP, tenDangNhap);
                        map.put(COLUMN_EMAIL, email);
                        map.put(COLUMN_MAT_KHAU, matKhau);
                        return map;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(this);
                queue.add(request);
            }
        }
    }
}
