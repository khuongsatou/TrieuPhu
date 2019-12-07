package com.nvk.TrieuPhuMVP.Model;

public class LinhVuc {
    private int id;
    private String ten_linh_vuc;
    private boolean xoa;

    public LinhVuc() {
        this.id = 0;
        this.ten_linh_vuc = "";
        this.xoa = false;
    }

    public LinhVuc(int id, String ten_linh_vuc, boolean xoa) {
        this.id = id;
        this.ten_linh_vuc = ten_linh_vuc;
        this.xoa = xoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen_linh_vuc() {
        return ten_linh_vuc;
    }

    public void setTen_linh_vuc(String ten_linh_vuc) {
        this.ten_linh_vuc = ten_linh_vuc;
    }

    public boolean isXoa() {
        return xoa;
    }

    public void setXoa(boolean xoa) {
        this.xoa = xoa;
    }
}
