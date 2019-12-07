package com.nvk.TrieuPhuMVP.Presenter;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis;
import com.nvk.TrieuPhuMVP.View.DangNhapView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static com.nvk.TrieuPhuMVP.Model.NguoiChoi.COLUMN_MAT_KHAU;
import static com.nvk.TrieuPhuMVP.Model.NguoiChoi.COLUMN_TEN_DANG_NHAP;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_DANGNHAP;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.BASE;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.URI_DANG_NHAP;

public class DangNhapPresenter {
    private DangNhapView dangNhapView;
    public DangNhapPresenter(DangNhapView dangNhapView) {
        this.dangNhapView = dangNhapView;
    }

    public void checkInternetAPP(){
        if(!dangNhapView.checkInternet()){
            dangNhapView.closeApp();
        }
    }

    public void login(final String tenDangNhap, final String matKhau){
        if (tenDangNhap.isEmpty()){
            dangNhapView.setErrorUsername();
        }else if(matKhau.isEmpty()){
            dangNhapView.setErrorPassword();
        }else{
            final ProgressDialog dialog = dangNhapView.showDialog();
            dialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, BASE + URI_DANG_NHAP, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        dangNhapView.closeDialog(dialog);
                        if(!dangNhapView.checkInternet()){
                            dangNhapView.setErrorInternet();
                        }else {
                            JSONObject objLogin = new JSONObject(response);
                            boolean result = objLogin.getBoolean("success");
                            if (result) {
                                JSONObject objNguoiChoi = objLogin.getJSONObject("nguoi_choi");
                                NguoiChoi nguoiChoi = new NguoiChoi(
                                        objNguoiChoi.getInt("id"),
                                        objNguoiChoi.getString("ten_dang_nhap"),
                                        objNguoiChoi.getString("mat_khau"),
                                        objNguoiChoi.getString("email"),
                                        objNguoiChoi.getString("hinh_dai_dien"),
                                        objNguoiChoi.getInt("diem_cao_nhat"),
                                        objNguoiChoi.getInt("credit"),
                                        false);
                                dangNhapView.navigate(nguoiChoi);
                                dangNhapView.loginSuccess();
                            } else {
                                dangNhapView.loginFail();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    dangNhapView.setErrorServer();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> map = new HashMap<>();
                    map.put(COLUMN_TEN_DANG_NHAP,tenDangNhap);
                    map.put(COLUMN_MAT_KHAU,matKhau);
                    return map;
                }
            };
            dangNhapView.loadBackGround(request);
        }

    }

}
