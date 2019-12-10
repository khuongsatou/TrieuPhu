package com.nvk.TrieuPhuMVP.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.nvk.TrieuPhuMVP.Model.CauHoi;
import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.R;
import com.nvk.TrieuPhuMVP.View.Fragment.HienThiCauHoiFragment;

import java.util.List;

import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_CH_POSITION;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.TYPE_ITEM;
import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.TYPE_LOADING;

public class CauHoiAdapter extends FragmentStatePagerAdapter {
    private List<CauHoi> cauHois;
    private Context context;
    private NguoiChoi nguoiChoi;


    public CauHoiAdapter(FragmentManager fm, List<CauHoi> cauHois, Context context, NguoiChoi nguoiChoi) {
        super(fm);
        this.cauHois = cauHois;
        this.context = context;
        this.nguoiChoi = nguoiChoi;
    }



    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        HienThiCauHoiFragment hienThiCauHoiFragment = new HienThiCauHoiFragment(cauHois,context,this,nguoiChoi);
        Bundle saveStatus = new Bundle();
        saveStatus.putInt(KEY_CH_POSITION,position);
        hienThiCauHoiFragment.setArguments(saveStatus);
        return hienThiCauHoiFragment;
    }




    @Override
    public int getCount() {
        return cauHois.size();
    }
}
