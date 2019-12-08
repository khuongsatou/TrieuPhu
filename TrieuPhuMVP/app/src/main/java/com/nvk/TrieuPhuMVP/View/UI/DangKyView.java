package com.nvk.TrieuPhuMVP.View;

import android.content.Intent;

import com.nvk.TrieuPhuMVP.Utilities.EmptyForm;
import com.nvk.TrieuPhuMVP.Utilities.InternetBackground;

public interface DangKyView extends EmptyForm, InternetBackground {
    boolean existUserName(String username);
    boolean existEmail(String email);
    boolean checkRepass(String pass,String repass);
    void setErrorRepass();
    void registerSuccess();
    void registerFail();

}
