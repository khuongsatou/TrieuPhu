package com.nvk.doanailatrieuphu.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nvk.doanailatrieuphu.R;

public class LoadingCauHoiFragment extends Fragment {

    private Context context;

    public LoadingCauHoiFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_loading_cau_hoi, container, false);
        return view;
    }

}
