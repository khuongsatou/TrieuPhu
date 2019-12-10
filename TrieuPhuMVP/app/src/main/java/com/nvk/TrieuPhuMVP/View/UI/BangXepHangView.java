package com.nvk.TrieuPhuMVP.View.UI;

import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.Utilities.InternetBackground;
import com.nvk.TrieuPhuMVP.Utilities.LoadMore;

public interface BangXepHangView extends InternetBackground, LoadMore {
    void updateList(NguoiChoi nguoiChoi);
}
