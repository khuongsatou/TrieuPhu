package com.nvk.TrieuPhuMVP.Presenter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nvk.TrieuPhuMVP.Model.GoiCredit;
import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.View.UI.MuaCreditView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_CREDIT;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_DANGNHAP;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_ID;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.BASE;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.URI_CREDIT_DANH_SACH;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.URI_NGUOI_CHOI_UPDATE_CREDIT;

public class MuaCreditPressenter {
    private NguoiChoi nguoiChoi;
    private MuaCreditView muaCreditView;

    public MuaCreditPressenter( MuaCreditView muaCreditView) {
        this.muaCreditView = muaCreditView;
    }

    public NguoiChoi getNguoiChoi() {
        return nguoiChoi;
    }

    public void setNguoiChoi(NguoiChoi nguoiChoi) {
        this.nguoiChoi = nguoiChoi;
    }

    public void handleLoadGoiCredit(){
        final ProgressDialog dialog = muaCreditView.showDialog();
        dialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, BASE + URI_CREDIT_DANH_SACH, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    dialog.dismiss();
                    JSONObject objGoiCredit = new JSONObject(response);
                    JSONArray arrGoiCredit = objGoiCredit.getJSONArray("goi_credit");
                    for (int i = 0; i < arrGoiCredit.length(); i++) {
                        JSONObject objItem = arrGoiCredit.getJSONObject(i);
                        GoiCredit goiCredit = new GoiCredit(objItem.getInt("id"),
                                objItem.getString("ten_goi"),
                                objItem.getInt("credit"),
                                objItem.getInt("so_tien"),
                                false
                                );
                        muaCreditView.setGoiCredit(goiCredit);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                muaCreditView.setErrorInternet();
            }
        });
        muaCreditView.loadBackGround(request);
    }

    public void handleBuyCredit(int sotien){
        if (!muaCreditView.checkInternet()){
            muaCreditView.setErrorInternet();
            return;
        }
        this.nguoiChoi.setCredit(getNguoiChoi().getCredit()+sotien);
        final ProgressDialog progressDialog = muaCreditView.showDialog();
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, BASE + URI_NGUOI_CHOI_UPDATE_CREDIT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();
                    JSONObject objCredit = new JSONObject(response);
                    if (objCredit.getBoolean("success")) {
                        muaCreditView.updateCredit(nguoiChoi.getCredit());
                        muaCreditView.buySuccess();
                    } else {
                        muaCreditView.buyFail();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                muaCreditView.checkInternet();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put(KEY_ID, String.valueOf(getNguoiChoi().getId()));
                map.put(KEY_CREDIT, String.valueOf(getNguoiChoi().getCredit()));
                return map;
            }
        };
        muaCreditView.loadBackGround(request);
    }

    public void backPress(Intent intent){
        intent.putExtra(KEY_DANGNHAP,this.nguoiChoi);
    }
}
