package com.nvk.TrieuPhuMVP.Model;

public class LichSuMuaCredit {
    private int id;
    private int nguoi_choi_id;
    private int credit;
    private int so_tien;

    public LichSuMuaCredit() {
        this.id = 0;
        this.nguoi_choi_id = 0;
        this.credit = 0;
        this.so_tien = 0;
    }

    public LichSuMuaCredit(int id, int nguoi_choi_id, int credit, int so_tien) {
        this.id = id;
        this.nguoi_choi_id = nguoi_choi_id;
        this.credit = credit;
        this.so_tien = so_tien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNguoi_choi_id() {
        return nguoi_choi_id;
    }

    public void setNguoi_choi_id(int nguoi_choi_id) {
        this.nguoi_choi_id = nguoi_choi_id;
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
