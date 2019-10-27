package com.nvk.doanailatrieuphu.Model;

public class LuotChoi {
    private int id;
    private int nguoiChoiId;
    private int soCau;
    private String diem;
    private String ngayGio;
    private Boolean xoa;

    public LuotChoi() {
    }

    public LuotChoi(int id, int nguoiChoiId, int soCau, String diem, String ngayGio, Boolean xoa) {
        this.id = id;
        this.nguoiChoiId = nguoiChoiId;
        this.soCau = soCau;
        this.diem = diem;
        this.ngayGio = ngayGio;
        this.xoa = xoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Boolean getXoa() {
        return xoa;
    }

    public void setXoa(Boolean xoa) {
        this.xoa = xoa;
    }
}
