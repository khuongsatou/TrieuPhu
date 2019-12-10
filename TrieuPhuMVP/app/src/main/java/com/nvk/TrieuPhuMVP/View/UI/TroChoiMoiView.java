package com.nvk.TrieuPhuMVP.View.UI;

import com.nvk.TrieuPhuMVP.Model.LinhVuc;
import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.Utilities.InternetBackground;
import com.nvk.TrieuPhuMVP.Utilities.LoadMore;

public interface TroChoiMoiView extends InternetBackground, LoadMore {
    void updateList(LinhVuc linhVuc);
}
