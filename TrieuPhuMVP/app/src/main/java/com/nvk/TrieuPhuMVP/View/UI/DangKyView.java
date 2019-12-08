package com.nvk.TrieuPhuMVP.View.UI;

import android.content.Intent;

import com.nvk.TrieuPhuMVP.Utilities.CheckExistForm;
import com.nvk.TrieuPhuMVP.Utilities.EmptyForm;
import com.nvk.TrieuPhuMVP.Utilities.InternetBackground;

public interface DangKyView extends EmptyForm, InternetBackground , CheckExistForm {
    void registerSuccess();
    void registerFail();
}
