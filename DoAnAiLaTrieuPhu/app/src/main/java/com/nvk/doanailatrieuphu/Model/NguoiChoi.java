package com.nvk.doanailatrieuphu.Model;

import java.io.Serializable;

public class NguoiChoi implements Serializable {
    private int id;
    private String tenDangNhap;
    private String matKhau;
    private String email;
    private String hinhDaiDien;
    private int diemCaoNhat;
    private int credit;
    private boolean xoa;

    public NguoiChoi() {
    }

    public NguoiChoi(int id, String tenDangNhap, String matKhau, String email, String hinhDaiDien, int diemCaoNhat, int credit, boolean xoa) {
        this.id = id;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.email = email;
        this.hinhDaiDien = hinhDaiDien;
        this.diemCaoNhat = diemCaoNhat;
        this.credit = credit;
        this.xoa = xoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHinhDaiDien() {
        return hinhDaiDien;
    }

    public void setHinhDaiDien(String hinhDaiDien) {
        this.hinhDaiDien = hinhDaiDien;
    }

    public int getDiemCaoNhat() {
        return diemCaoNhat;
    }

    public void setDiemCaoNhat(int diemCaoNhat) {
        this.diemCaoNhat = diemCaoNhat;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean isXoa() {
        return xoa;
    }

    public void setXoa(boolean xoa) {
        this.xoa = xoa;
    }
}
