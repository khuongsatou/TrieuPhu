package com.nvk.doanailatrieuphu.Adapter;

import android.content.Context;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.nvk.doanailatrieuphu.Fragment.HienThiCauHoiFragment;
import com.nvk.doanailatrieuphu.Model.CauHoi;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;

import java.util.List;

public class CauHoiAdapter extends FragmentStatePagerAdapter {
    private List<CauHoi> cauHoiList;
    private Context context;
    private ViewPager vpgShowCauHoi;
    private NguoiChoi nguoiChoi;



    public CauHoiAdapter(FragmentManager fm, List<CauHoi> cauHoiList,Context context,ViewPager vpgShowCauHoi,NguoiChoi nguoiChoi) {
        super(fm);
        this.cauHoiList = cauHoiList;
        this.context = context;
        this.vpgShowCauHoi = vpgShowCauHoi;
        this.nguoiChoi = nguoiChoi;

    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return new HienThiCauHoiFragment(cauHoiList,position,context,this,nguoiChoi);
    }

    @Override
    public int getCount() {
        return cauHoiList.size();
    }
}
