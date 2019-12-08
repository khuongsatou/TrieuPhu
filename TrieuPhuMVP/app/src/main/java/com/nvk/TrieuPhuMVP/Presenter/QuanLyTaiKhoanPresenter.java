package com.nvk.TrieuPhuMVP.Presenter;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.View.UI.QuanLyTaiKhoanView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import static com.nvk.TrieuPhuMVP.Model.NguoiChoi.COLUMN_EMAIL;
import static com.nvk.TrieuPhuMVP.Model.NguoiChoi.COLUMN_MAT_KHAU;
import static com.nvk.TrieuPhuMVP.Model.NguoiChoi.COLUMN_TEN_DANG_NHAP;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.BASE;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.URI_NGUOI_CHOI_THEM;
import static com.nvk.TrieuPhuMVP.View.Activity.QuanLyTaiKhoanActivity.urlUpLoad;

public class QuanLyTaiKhoanPresenter {
    private QuanLyTaiKhoanView quanLyTaiKhoanView;
    private NguoiChoi nguoiChoi = new NguoiChoi();

    public QuanLyTaiKhoanPresenter(QuanLyTaiKhoanView quanLyTaiKhoanView) {
        this.quanLyTaiKhoanView = quanLyTaiKhoanView;
    }

    public NguoiChoi getNguoiChoi() {
        return nguoiChoi;
    }

    public void setNguoiChoi(NguoiChoi nguoiChoi) {
        this.nguoiChoi = nguoiChoi;
    }

    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        byte[] imageBytes = outputStream.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }



    public void handleUpdate(final String tenDangNhap, final String email, final String matKhau, String xacNhanMatKhau, final Bitmap bitmap){
        if(email.isEmpty()){
            quanLyTaiKhoanView.setErrorEmail();
        }else if(matKhau.isEmpty()){
            quanLyTaiKhoanView.setErrorPassword();
        }else if(!quanLyTaiKhoanView.checkRepass(matKhau,xacNhanMatKhau)){
            quanLyTaiKhoanView.setErrorRepass();
        }else{
            final ProgressDialog dialog = quanLyTaiKhoanView.showDialog();
            dialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, urlUpLoad, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    quanLyTaiKhoanView.closeDialog(dialog);
                    try{
                        JSONObject obj = new JSONObject(response);
                        boolean result = obj.getBoolean("success");
                        if (result) {
                            nguoiChoi.setHinh_dai_dien(obj.getString("hinh_dai_dien"));
                            quanLyTaiKhoanView.updateSuccess();
                        } else {
                            quanLyTaiKhoanView.updateFail();
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    quanLyTaiKhoanView.setErrorServer();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("id",String.valueOf(nguoiChoi.getId()));
                    params.put("image",imageToString(bitmap));


                    return params;
                }
            };

            quanLyTaiKhoanView.loadBackGround(request);
        }

    }



}
