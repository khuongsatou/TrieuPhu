package com.nvk.TrieuPhuMVP.Presenter;

import android.app.ProgressDialog;
import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.View.UI.DangNhapView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.nvk.TrieuPhuMVP.Model.NguoiChoi.COLUMN_MAT_KHAU;
import static com.nvk.TrieuPhuMVP.Model.NguoiChoi.COLUMN_TEN_DANG_NHAP;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.TOKEN;
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


    public void handleLogin(final String tenDangNhap, final String matKhau){
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
                            boolean status = objLogin.getBoolean("status");
                            if (status){
                                 dangNhapView.saveReference(objLogin.getString("token"));
                                 dangNhapView.navigate();
                                 dangNhapView.loginSuccess();
                            }else{
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
