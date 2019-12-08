package com.nvk.TrieuPhuMVP.Presenter;

import android.app.ProgressDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nvk.TrieuPhuMVP.View.UI.QuenMatKhauView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.nvk.TrieuPhuMVP.Model.NguoiChoi.COLUMN_EMAIL;
import static com.nvk.TrieuPhuMVP.Model.NguoiChoi.COLUMN_TEN_DANG_NHAP;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.BASE;
import static com.nvk.TrieuPhuMVP.Utilities.NetWorkUtilitis.URI_MAT_KHAU;

public class QuenMatKhauPresenter {
    private QuenMatKhauView quenMatKhauView;

    public QuenMatKhauPresenter(QuenMatKhauView quenMatKhauView) {
        this.quenMatKhauView = quenMatKhauView;
    }

    public void handleForgetPass(final String tenDangNhap, final String email) {
        if (tenDangNhap.isEmpty()) {
            quenMatKhauView.setErrorUsername();
        } else if (email.isEmpty()) {
            quenMatKhauView.setErrorEmail();
        } else {
            final ProgressDialog dialog = quenMatKhauView.showDialog();
            dialog.show();
            final StringRequest request = new StringRequest(Request.Method.POST, BASE + URI_MAT_KHAU, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    quenMatKhauView.closeDialog(dialog);
                    if (!quenMatKhauView.checkInternet()) {
                        quenMatKhauView.setErrorInternet();
                    } else {
                        try {
                            JSONObject objLogin = new JSONObject(response);
                            boolean result = objLogin.getBoolean("success");
                            if (result) {
                                JSONObject objNguoiChoi = objLogin.getJSONObject("nguoi_choi");
                                quenMatKhauView.forgetSuccess(objNguoiChoi.getString("mat_khau"));
                            } else {
                                quenMatKhauView.forgetFail();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    quenMatKhauView.setErrorServer();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put(COLUMN_TEN_DANG_NHAP, tenDangNhap);
                    map.put(COLUMN_EMAIL, email);
                    return map;
                }
            };
            quenMatKhauView.loadBackGround(request);

        }
    }
}


