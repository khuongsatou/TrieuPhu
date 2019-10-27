package com.nvk.doanailatrieuphu.Model;

public class CauHinhTroGiup {
    private int id;
    private int loaiTroGiup;
    private int thuTu;
    private int credit;
    private boolean xoa;

    public CauHinhTroGiup() {
    }

    public CauHinhTroGiup(int id, int loaiTroGiup, int thuTu, int credit, boolean xoa) {
        this.id = id;
        this.loaiTroGiup = loaiTroGiup;
        this.thuTu = thuTu;
        this.credit = credit;
        this.xoa = xoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoaiTroGiup() {
        return loaiTroGiup;
    }

    public void setLoaiTroGiup(int loaiTroGiup) {
        this.loaiTroGiup = loaiTroGiup;
    }

    public int getThuTu() {
        return thuTu;
    }

    public void setThuTu(int thuTu) {
        this.thuTu = thuTu;
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
