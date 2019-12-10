package com.nvk.TrieuPhuMVP.Model;

public class LuotChoi {
    public static final String COLUMN_NGUOI_CHOI_ID = "nguoi_choi_id";
    public static final String COLUMN_LUOT_CHOI_SO_CAU = "so_cau";
    public static final String COLUMN_LUOT_CHOI_DIEM = "diem";
    public static final String COLUMN_LUOT_CHOI_NGAY_GIO = "ngay_gio";

    private int id;
    private int nguoi_choi_id;
    private int so_cau;
    private int diem;
    private String ngay_gio;

    public LuotChoi() {
        id=0;
        nguoi_choi_id=0;
        so_cau=0;
        diem=0;
        ngay_gio="";
    }

    public LuotChoi(int id, int nguoi_choi_id, int so_cau, int diem, String ngay_gio) {
        this.id = id;
        this.nguoi_choi_id = nguoi_choi_id;
        this.so_cau = so_cau;
        this.diem = diem;
        this.ngay_gio = ngay_gio;
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

    public int getSo_cau() {
        return so_cau;
    }

    public void setSo_cau(int so_cau) {
        this.so_cau = so_cau;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public String getNgay_gio() {
        return ngay_gio;
    }

    public void setNgay_gio(String ngay_gio) {
        this.ngay_gio = ngay_gio;
    }
}
