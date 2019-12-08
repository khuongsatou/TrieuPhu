package com.nvk.TrieuPhuMVP.Utilities;

import android.app.ProgressDialog;

import com.android.volley.toolbox.StringRequest;

public interface InternetBackground{
    void setErrorInternet();
    void setErrorServer();
    boolean checkInternet();
    void closeApp();
    void closeDialog(ProgressDialog dialog);
    void loadBackGround(StringRequest request);
    ProgressDialog showDialog();
}
