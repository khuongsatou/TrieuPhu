package com.nvk.TrieuPhuMVP.Presenter;

import android.app.ProgressDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nvk.TrieuPhuMVP.View.UI.DangKyView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.nvk.TrieuPhuMVP.Model.NguoiChoi.COLUMN_EMAIL;
import static com.nvk.TrieuPhuMVP.Model.NguoiChoi.COLUMN_MAT_KHAU;
import static com.nvk.TrieuPhuMVP.Model.NguoiChoi.COLUMN_TEN_DANG_NHAP;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.BASE;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.URI_NGUOI_CHOI_THEM;

public class DangKyPresenter {
    private DangKyView dangKyView;

    public DangKyPresenter(DangKyView DangKyView) {
        this.dangKyView = DangKyView;
    }

    public void handleRegister(final String tenDangNhap, final String email, final String matKhau,String xacNhanMatKhau){
        if (tenDangNhap.isEmpty()){
            dangKyView.setErrorUsername();
        }else if(email.isEmpty()){
            dangKyView.setErrorEmail();
        }else if(matKhau.isEmpty()){
            dangKyView.setErrorPassword();
        }else if(!dangKyView.checkRepass(matKhau,xacNhanMatKhau)){
            dangKyView.setErrorRepass();
        }else{
            final ProgressDialog dialog = dangKyView.showDialog();
            dialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, BASE + URI_NGUOI_CHOI_THEM, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    dangKyView.closeDialog(dialog);
                    try{
                        JSONObject objLogin = new JSONObject(response);
                        boolean result = objLogin.getBoolean("success");
                        if (result) {
                            dangKyView.registerSuccess();
                        } else {
                            dangKyView.registerFail();
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    dangKyView.setErrorServer();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put(COLUMN_TEN_DANG_NHAP, tenDangNhap);
                    map.put(COLUMN_EMAIL, email);
                    map.put(COLUMN_MAT_KHAU, matKhau);
                    return map;
                }
            };

            dangKyView.loadBackGround(request);
        }

    }
}
