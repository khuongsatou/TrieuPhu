package com.nvk.doanailatrieuphu.Model;

public class LinhSuMuaCredit {
    private int id;
    private int nguoiChoiID;
    private int goiCreditID;
    private int credit;
    private int soTien;
    private boolean xoa;

    public LinhSuMuaCredit() {
    }

    public LinhSuMuaCredit(int id, int nguoiChoiID, int goiCreditID, int credit, int soTien, boolean xoa) {
        this.id = id;
        this.nguoiChoiID = nguoiChoiID;
        this.goiCreditID = goiCreditID;
        this.credit = credit;
        this.soTien = soTien;
        this.xoa = xoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNguoiChoiID() {
        return nguoiChoiID;
    }

    public void setNguoiChoiID(int nguoiChoiID) {
        this.nguoiChoiID = nguoiChoiID;
    }

    public int getGoiCreditID() {
        return goiCreditID;
    }

    public void setGoiCreditID(int goiCreditID) {
        this.goiCreditID = goiCreditID;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public boolean isXoa() {
        return xoa;
    }

    public void setXoa(boolean xoa) {
        this.xoa = xoa;
    }
}
