package com.nvk.doanailatrieuphu.Model;

public class GoiCredit {
    private String ten_goi;
    private int credit;
    private int so_tien;

    public GoiCredit() {
    }

    public GoiCredit(String ten_goi, int credit, int so_tien) {
        this.ten_goi = ten_goi;
        this.credit = credit;
        this.so_tien = so_tien;
    }

    public String getTen_goi() {
        return ten_goi;
    }

    public void setTen_goi(String ten_goi) {
        this.ten_goi = ten_goi;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getSo_tien() {
        return so_tien;
    }

    public void setSo_tien(int so_tien) {
        this.so_tien = so_tien;
    }
}
