package com.nvk.doanailatrieuphu.Model;

public class LuotChoi {
    private int nguoiChoiId;
    private int soCau;
    private String diem;
    private String ngayGio;

    public LuotChoi() {
    }

    public LuotChoi(int nguoiChoiId, int soCau, String diem, String ngayGio) {
        this.nguoiChoiId = nguoiChoiId;
        this.soCau = soCau;
        this.diem = diem;
        this.ngayGio = ngayGio;
    }

    public int getNguoiChoiId() {
        return nguoiChoiId;
    }

    public void setNguoiChoiId(int nguoiChoiId) {
        this.nguoiChoiId = nguoiChoiId;
    }

    public int getSoCau() {
        return soCau;
    }

    public void setSoCau(int soCau) {
        this.soCau = soCau;
    }

    public String getDiem() {
        return diem;
    }

    public void setDiem(String diem) {
        this.diem = diem;
    }

    public String getNgayGio() {
        return ngayGio;
    }

    public void setNgayGio(String ngayGio) {
        this.ngayGio = ngayGio;
    }
}
