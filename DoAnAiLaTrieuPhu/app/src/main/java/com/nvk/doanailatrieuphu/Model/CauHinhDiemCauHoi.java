package com.nvk.doanailatrieuphu.Model;

public class CauHinhDiemCauHoi {
    private int id;
    private int thuTu;
    private int diem;
    private boolean xoa;

    public CauHinhDiemCauHoi() {
    }

    public CauHinhDiemCauHoi(int id, int thuTu, int diem, boolean xoa) {
        this.id = id;
        this.thuTu = thuTu;
        this.diem = diem;
        this.xoa = xoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getThuTu() {
        return thuTu;
    }

    public void setThuTu(int thuTu) {
        this.thuTu = thuTu;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public boolean isXoa() {
        return xoa;
    }

    public void setXoa(boolean xoa) {
        this.xoa = xoa;
    }
}
