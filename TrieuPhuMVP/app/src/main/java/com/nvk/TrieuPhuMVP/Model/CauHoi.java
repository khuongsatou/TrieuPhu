package com.nvk.TrieuPhuMVP.Model;

public class CauHoi {
    private int id;
    private String ten_dang_nhap;
    private String mat_khau;
    private String email;
    private String hinh_dai_dien;
    private int diem_cau_nhat;
    private int credit;
    private boolean xoa;

    public CauHoi() {
        this.id = 0;
        this.ten_dang_nhap = "";
        this.mat_khau = "";
        this.email = "";
        this.hinh_dai_dien = "";
        this.diem_cau_nhat = 0;
        this.credit = 0;
        this.xoa = false;
    }
    public CauHoi(int id, String ten_dang_nhap, String mat_khau, String email, String hinh_dai_dien, int diem_cau_nhat, int credit, boolean xoa) {
        super();
        this.id = id;
        this.ten_dang_nhap = ten_dang_nhap;
        this.mat_khau = mat_khau;
        this.email = email;
        this.hinh_dai_dien = hinh_dai_dien;
        this.diem_cau_nhat = diem_cau_nhat;
        this.credit = credit;
        this.xoa = xoa;
    }

    public int getId() {
        return id;
    }

    public String getTen_dang_nhap() {
        return ten_dang_nhap;
    }

    public String getMat_khau() {
        return mat_khau;
    }

    public String getEmail() {
        return email;
    }

    public String getHinh_dai_dien() {
        return hinh_dai_dien;
    }

    public int getDiem_cau_nhat() {
        return diem_cau_nhat;
    }

    public int getCredit() {
        return credit;
    }

    public boolean isXoa() {
        return xoa;
    }
}
