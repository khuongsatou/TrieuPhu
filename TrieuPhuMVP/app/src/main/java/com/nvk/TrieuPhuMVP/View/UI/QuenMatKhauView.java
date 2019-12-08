package com.nvk.TrieuPhuMVP.View;

import com.nvk.TrieuPhuMVP.Utilities.EmptyForm;
import com.nvk.TrieuPhuMVP.Utilities.InternetBackground;

public interface QuenMatKhauView extends InternetBackground, EmptyForm {
    void forgetSuccess(String password);
    void forgetFail();
}
