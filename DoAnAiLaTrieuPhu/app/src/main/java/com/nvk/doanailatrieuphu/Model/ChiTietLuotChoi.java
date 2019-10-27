package com.nvk.doanailatrieuphu.Model;

public class ChiTietLuotChoi {
    private int id;
    private int luoiChoiID;
    private int cauHoiID;
    private String phuongAn;
    private int diem;
    private Boolean xoa;

    public ChiTietLuotChoi() {
    }

    public ChiTietLuotChoi(int id, int luoiChoiID, int cauHoiID, String phuongAn, int diem, Boolean xoa) {
        this.id = id;
        this.luoiChoiID = luoiChoiID;
        this.cauHoiID = cauHoiID;
        this.phuongAn = phuongAn;
        this.diem = diem;
        this.xoa = xoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLuoiChoiID() {
        return luoiChoiID;
    }

    public void setLuoiChoiID(int luoiChoiID) {
        this.luoiChoiID = luoiChoiID;
    }

    public int getCauHoiID() {
        return cauHoiID;
    }

    public void setCauHoiID(int cauHoiID) {
        this.cauHoiID = cauHoiID;
    }

    public String getPhuongAn() {
        return phuongAn;
    }

    public void setPhuongAn(String phuongAn) {
        this.phuongAn = phuongAn;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public Boolean getXoa() {
        return xoa;
    }

    public void setXoa(Boolean xoa) {
        this.xoa = xoa;
    }
}
