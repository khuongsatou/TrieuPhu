package com.nvk.TrieuPhuMVP.View.UI;

import android.app.ProgressDialog;

import com.android.volley.toolbox.StringRequest;
import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.Utilities.EmptyForm;
import com.nvk.TrieuPhuMVP.Utilities.InternetBackground;

public interface DangNhapView extends InternetBackground , EmptyForm {
    void loginFail();
    void loginSuccess();
    void saveReference(String token);
    void navigate();
}
