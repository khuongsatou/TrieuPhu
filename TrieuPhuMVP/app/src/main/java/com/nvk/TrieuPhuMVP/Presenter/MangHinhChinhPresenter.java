package com.nvk.TrieuPhuMVP.Presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.View.UI.MangHinhChinhView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.nvk.TrieuPhuMVP.Model.NguoiChoi.COLUMN_MAT_KHAU;
import static com.nvk.TrieuPhuMVP.Model.NguoiChoi.COLUMN_TEN_DANG_NHAP;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_DANGNHAP;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.TOKEN;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.BASE;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.URI_DANG_NHAP;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.URI_LAY_THONG_TIN;

public class MangHinhChinhPresenter {
    private MangHinhChinhView mangHinhChinhView;
    private NguoiChoi nguoiChoi = new NguoiChoi();


    public NguoiChoi getNguoiChoi() {
        return nguoiChoi;
    }

    public void setNguoiChoi(NguoiChoi nguoiChoi) {
        this.nguoiChoi = nguoiChoi;
    }

    public MangHinhChinhPresenter(MangHinhChinhView mangHinhChinhView) {
        this.mangHinhChinhView = mangHinhChinhView;
    }

    public void handleInfo(){
        final ProgressDialog dialog = mangHinhChinhView.showDialog();
        dialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, BASE + URI_LAY_THONG_TIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    mangHinhChinhView.closeDialog(dialog);
                    if(!mangHinhChinhView.checkInternet()){
                        mangHinhChinhView.setErrorInternet();
                    }else {
                        JSONObject objLogin = new JSONObject(response);
                        nguoiChoi = new NguoiChoi(
                                objLogin.getInt("id"),
                                objLogin.getString("ten_dang_nhap"),
                                "",
                                objLogin.getString("email"),
                                objLogin.getString("hinh_dai_dien"),
                                objLogin.getInt("diem_cao_nhat"),
                                objLogin.getInt("credit"),
                                false);
                        mangHinhChinhView.restartData();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mangHinhChinhView.setErrorServer();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer " + mangHinhChinhView.getReference());

                return headers;
            }
        };
        mangHinhChinhView.loadBackGround(request);
    }

    public void navigate(Intent intent){
        intent.putExtra(KEY_DANGNHAP,this.nguoiChoi);
    }



}
