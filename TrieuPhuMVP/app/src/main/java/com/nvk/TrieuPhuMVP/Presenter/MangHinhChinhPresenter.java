package com.nvk.TrieuPhuMVP.Presenter;

import android.content.Context;
import android.content.Intent;

import com.nvk.TrieuPhuMVP.Model.NguoiChoi;
import com.nvk.TrieuPhuMVP.View.UI.MangHinhChinhView;

import static com.nvk.TrieuPhuMVP.Utilities.GlobalVariable.KEY_DANGNHAP;

public class MangHinhChinhPresenter {
    private MangHinhChinhView mangHinhChinhView;
    private NguoiChoi nguoiChoi = new NguoiChoi();

    public MangHinhChinhView getMangHinhChinhView() {
        return mangHinhChinhView;
    }

    public void setMangHinhChinhView(MangHinhChinhView mangHinhChinhView) {
        this.mangHinhChinhView = mangHinhChinhView;
    }

    public NguoiChoi getNguoiChoi() {
        return nguoiChoi;
    }

    public void setNguoiChoi(NguoiChoi nguoiChoi) {
        this.nguoiChoi = nguoiChoi;
    }

    public MangHinhChinhPresenter(MangHinhChinhView mangHinhChinhView) {
        this.mangHinhChinhView = mangHinhChinhView;
    }

    public void navigate(Intent intent){
        intent.putExtra(KEY_DANGNHAP,this.nguoiChoi);
    }

}
