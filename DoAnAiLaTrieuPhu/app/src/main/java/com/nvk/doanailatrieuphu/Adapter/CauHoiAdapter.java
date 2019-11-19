package com.nvk.doanailatrieuphu.Adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nvk.doanailatrieuphu.Fragment.HienThiCauHoiFragment;
import com.nvk.doanailatrieuphu.Model.CauHoi;
import com.nvk.doanailatrieuphu.Model.NguoiChoi;

import java.util.List;

import static com.nvk.doanailatrieuphu.Utilities.GlobalVariable.KEY_CH_POSITION;

public class CauHoiAdapter extends FragmentStatePagerAdapter{

    private List<CauHoi> cauHoiList;
    private Context context;
    private NguoiChoi nguoiChoi;


    public CauHoiAdapter(FragmentManager fm, List<CauHoi> cauHoiList,Context context,NguoiChoi nguoiChoi) {
        super(fm);
        this.cauHoiList = cauHoiList;
        this.context = context;
        this.nguoiChoi = nguoiChoi;
    }



    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        HienThiCauHoiFragment hienThiCauHoiFragment = new HienThiCauHoiFragment(cauHoiList,context,this,nguoiChoi);
        Bundle saveStatus = new Bundle();
        saveStatus.putInt(KEY_CH_POSITION,position);
        hienThiCauHoiFragment.setArguments(saveStatus);
        return hienThiCauHoiFragment;
    }




    @Override
    public int getCount() {
        return cauHoiList.size();
    }


}
