package com.nvk.TrieuPhuMVP.Model;

import java.io.Serializable;

public class NguoiChoi implements Serializable {
    public static final String TABLE_NGUOICHOI = "NguoiChoi";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEN_DANG_NHAP = "ten_dang_nhap";
    public static final String COLUMN_MAT_KHAU = "mat_khau";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_CREDIT = "credit";
    public static final String COLUMN_HINH_DAI_DIEN = "hinh_dai_dien";
    public static final String COLUMN_DIEM_CAO_NHAT = "diem_cao_nhat";
    public static final String COLUMN_XOA = "xoa";


    private int id;
    private String ten_dang_nhap;
    private String mat_khau;
    private String email;
    private String hinh_dai_dien;
    private int diem_cao_nhat;
    private int credit;
    private boolean xoa;

    public NguoiChoi() {
        this.id = 0;
        this.ten_dang_nhap = "";
        this.mat_khau = "";
        this.email = "";
        this.hinh_dai_dien = "";
        this.diem_cao_nhat = 0;
        this.credit = 0;
        this.xoa = false;
    }

    public NguoiChoi(int id, String ten_dang_nhap, String mat_khau, String email, String hinh_dai_dien, int diem_cao_nhat, int credit, boolean xoa) {
        this.id = id;
        this.ten_dang_nhap = ten_dang_nhap;
        this.mat_khau = mat_khau;
        this.email = email;
        this.hinh_dai_dien = hinh_dai_dien;
        this.diem_cao_nhat = diem_cao_nhat;
        this.credit = credit;
        this.xoa = xoa;
    }
}
