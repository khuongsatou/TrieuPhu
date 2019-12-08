package com.nvk.TrieuPhuMVP.View;

import android.app.ProgressDialog;

import com.android.volley.toolbox.StringRequest;

public interface InternetBackground {
    void setErrorInternet();
    void setErrorServer();
    boolean checkInternet();
    void closeApp();
    ProgressDialog showDialog();
    void closeDialog(ProgressDialog dialog);
    void loadBackGround(StringRequest request);
}
