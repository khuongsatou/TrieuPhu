package com.nvk.TrieuPhuMVP.View.UI;

import com.nvk.TrieuPhuMVP.Model.GoiCredit;
import com.nvk.TrieuPhuMVP.Utilities.InternetBackground;

public interface MuaCreditView extends InternetBackground {
    void buySuccess();
    void updateCredit(int credit);
    void setGoiCredit(GoiCredit goiCredit);
    void buyFail();
}
