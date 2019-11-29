package com.nvk.doanailatrieuphu.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
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
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
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
import static com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis.BASE_IMAGE;
import static com.nvk.doanailatrieuphu.Utilities.NetWorkUtilitis.URI_NGUOI_CHOI_CAP_NHAT;

public class QuanLiTaiKhoanActivity extends AppCompatActivity {
    private static final int CODE_GALLERY_REQUEST = 123;
    private ImageView ivLogo;
    private EditText edtTenDangNhap,edtEmail,edtMatKhau,edtXacNhanMatKhau;
    private int id_nguoiChoi = 0;
    private Bitmap bitmap;
    private ProgressDialog progressDialog;
    private String urlUpLoad =BASE+"nguoi_choi/upload";
    private NguoiChoi nguoiChoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_li_tai_khoan);

        radiation();
        selectTK();

    }



    private void selectTK() {
        this.nguoiChoi = (NguoiChoi) getIntent().getSerializableExtra(KEY_DANGNHAP);
        this.id_nguoiChoi = nguoiChoi.getId();
        edtTenDangNhap.setText(nguoiChoi.getTenDangNhap());
        edtEmail.setText(nguoiChoi.getEmail());
        Picasso.get()
                .load(BASE_IMAGE+this.nguoiChoi.getHinhDaiDien())
                .error(R.drawable.logo_android)
                .into(ivLogo);

    }

    private void radiation() {
        ivLogo = findViewById(R.id.ivLogo);
        edtTenDangNhap = findViewById(R.id.edtTenDangNhap);
        edtEmail = findViewById(R.id.edtEmail);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        edtXacNhanMatKhau = findViewById(R.id.edtXacNhanMatKhau);
    }

    public void xuLiDoiHinhDaiHien(View view) {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                CODE_GALLERY_REQUEST
        );
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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

    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        byte[] imageBytes = outputStream.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(KEY_DANGNHAP,this.nguoiChoi);
        setResult(RESULT_OK,intent);
        finish();
        super.onBackPressed();
    }

    public void xuLiCapNhap(View view) {
        final int id = this.id_nguoiChoi;
        final String tenDangNhap = edtTenDangNhap.getText().toString().trim();
        final String email = edtEmail.getText().toString().trim();
        final String matKhau = edtMatKhau.getText().toString().trim();
        String xacNhanMatKhau = edtXacNhanMatKhau.getText().toString().trim();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading");
        progressDialog.setMessage("Please wait ....");
        progressDialog.show();

        final StringRequest request = new StringRequest(Request.Method.POST, urlUpLoad, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("success")){
                        nguoiChoi.setHinhDaiDien(obj.getString("hinh_dai_dien"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("AAAAAA",response);


                Toast.makeText(getApplicationContext(),response+"",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error+"",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                String imageData = imageToString(bitmap);
                params.put("id",String.valueOf(id_nguoiChoi));
                params.put("image",imageData);


                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }



//        if (email.equals("") || matKhau.equals("")) {
//            Toast.makeText(getApplicationContext(), getString(R.string.tb_chua_nhap_du),Toast.LENGTH_SHORT).show();
//            return;
//        } else {
//            if (!matKhau.equals(xacNhanMatKhau)){
//                Toast.makeText(getApplicationContext(),getString(R.string.tb_mat_khau_khong_giong_nhau),Toast.LENGTH_SHORT).show();
//            }else{
//                final ProgressDialog pgwait = NetWorkUtilitis.showProress(this);
//                pgwait.show();
//                StringRequest request = new StringRequest(Request.Method.POST, BASE + URI_NGUOI_CHOI_CAP_NHAT, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            pgwait.dismiss();
//                            JSONObject objLogin = new JSONObject(response);
//                            boolean result = objLogin.getBoolean("success");
//                            if (result) {
//                                Toast.makeText(getApplicationContext(),tenDangNhap+" Cập Nhật Thành Công", Toast.LENGTH_LONG).show();
//                                finish();
//                            } else {
//                                Toast.makeText(getApplicationContext(),getString(R.string.tb_update_user_tb) + " Hoặc User Tồn Tại", Toast.LENGTH_LONG).show();
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d("AAAAA", error.getMessage());
//                        pgwait.dismiss();
//                    }
//                }) {
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        Map<String, String> map = new HashMap<>();
//                        map.put(COLUMN_ID,String.valueOf(id));
//                        map.put(COLUMN_TEN_DANG_NHAP, tenDangNhap);
//                        map.put(COLUMN_EMAIL, email);
//                        map.put(COLUMN_MAT_KHAU, matKhau);
//                        return map;
//                    }
//                };
//                RequestQueue queue = Volley.newRequestQueue(this);
//                queue.add(request);
//            }
//        }
 //   }
}
