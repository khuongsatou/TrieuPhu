package com.nvk.TrieuPhuMVP.View.UI;

import com.nvk.TrieuPhuMVP.Model.LuotChoi;
import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.Utilities.InternetBackground;
import com.nvk.TrieuPhuMVP.Utilities.LoadMore;

public interface LichSuChoiView extends InternetBackground, LoadMore {
    void updateList(LuotChoi luotChoi);
}
