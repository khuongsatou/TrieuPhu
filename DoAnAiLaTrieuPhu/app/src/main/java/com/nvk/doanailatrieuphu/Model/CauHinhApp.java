package com.nvk.doanailatrieuphu.Model;

public class CauHinhApp {
    private int id;
    private int coHoiSai;
    private int thoiGianTraLoi;
    private boolean xoa;

    public CauHinhApp() {
    }

    public CauHinhApp(int id, int coHoiSai, int thoiGianTraLoi, boolean xoa) {
        this.id = id;
        this.coHoiSai = coHoiSai;
        this.thoiGianTraLoi = thoiGianTraLoi;
        this.xoa = xoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoHoiSai() {
        return coHoiSai;
    }

    public void setCoHoiSai(int coHoiSai) {
        this.coHoiSai = coHoiSai;
    }

    public int getThoiGianTraLoi() {
        return thoiGianTraLoi;
    }

    public void setThoiGianTraLoi(int thoiGianTraLoi) {
        this.thoiGianTraLoi = thoiGianTraLoi;
    }

    public boolean isXoa() {
        return xoa;
    }

    public void setXoa(boolean xoa) {
        this.xoa = xoa;
    }
}
