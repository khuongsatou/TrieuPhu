package com.nvk.doanailatrieuphu.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nvk.doanailatrieuphu.Fragment.HienThiCauHoiFragment;
import com.nvk.doanailatrieuphu.Model.CauHoi;

import java.util.List;

public class CauHoiAdapter extends FragmentStatePagerAdapter {
    private List<CauHoi> cauHoiList;
    private Context context;


    public CauHoiAdapter(FragmentManager fm, List<CauHoi> cauHoiList,Context context) {
        super(fm);
        this.cauHoiList = cauHoiList;
        this.context = context;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return new HienThiCauHoiFragment(cauHoiList,position,context);
    }

    @Override
    public int getCount() {
        return cauHoiList.size();
    }
}
