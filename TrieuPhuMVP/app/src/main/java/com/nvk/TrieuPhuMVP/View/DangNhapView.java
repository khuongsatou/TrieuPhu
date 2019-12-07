package com.nvk.TrieuPhuMVP.View;

import android.app.ProgressDialog;

import com.android.volley.toolbox.StringRequest;
import com.nvk.TrieuPhuMVP.Model.NguoiChoi;

public interface DangNhapView {
    void loginFail();
    void loginSuccess();
    void navigate(NguoiChoi nguoiChoi);
    void setErrorUsername();
    void setErrorPassword();
    void setErrorInternet();
    void setErrorServer();
    boolean checkInternet();
    void closeApp();
    ProgressDialog showDialog();
    void closeDialog(ProgressDialog dialog);
    void loadBackGround(StringRequest request);
}
