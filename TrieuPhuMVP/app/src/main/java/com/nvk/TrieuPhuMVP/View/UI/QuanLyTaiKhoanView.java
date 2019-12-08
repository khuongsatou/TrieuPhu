package com.nvk.TrieuPhuMVP.View.UI;

import com.nvk.TrieuPhuMVP.Utilities.CheckExistForm;
import com.nvk.TrieuPhuMVP.Utilities.EmptyForm;
import com.nvk.TrieuPhuMVP.Utilities.InternetBackground;

public interface QuanLyTaiKhoanView extends EmptyForm, InternetBackground , CheckExistForm {
    void updateSuccess();
    void updateFail();
}
