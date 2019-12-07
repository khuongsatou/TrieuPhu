package com.nvk.TrieuPhuMVP.Model;

public class LuotChoi {
    private int id;
    private String noi_dung;
    private int linh_vuc_id;
    private String phuong_an_a;
    private String  phuong_an_b;
    private String  phuong_an_c;
    private String  phuong_an_d;
    private String dap_an;
    private boolean xoa;

    public LuotChoi() {
        this.id = 0;
        this.noi_dung = "";
        this.linh_vuc_id = 0;
        this.phuong_an_a = "";
        this.phuong_an_b = "";
        this.phuong_an_c = "";
        this.phuong_an_d = "";
        this.dap_an = "";
        this.xoa = false;
    }

    public LuotChoi(int id, String noi_dung, int linh_vuc_id, String phuong_an_a, String phuong_an_b, String phuong_an_c, String phuong_an_d, String dap_an, boolean xoa) {
        this.id = id;
        this.noi_dung = noi_dung;
        this.linh_vuc_id = linh_vuc_id;
        this.phuong_an_a = phuong_an_a;
        this.phuong_an_b = phuong_an_b;
        this.phuong_an_c = phuong_an_c;
        this.phuong_an_d = phuong_an_d;
        this.dap_an = dap_an;
        this.xoa = xoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoi_dung() {
        return noi_dung;
    }

    public void setNoi_dung(String noi_dung) {
        this.noi_dung = noi_dung;
    }

    public int getLinh_vuc_id() {
        return linh_vuc_id;
    }

    public void setLinh_vuc_id(int linh_vuc_id) {
        this.linh_vuc_id = linh_vuc_id;
    }

    public String getPhuong_an_a() {
        return phuong_an_a;
    }

    public void setPhuong_an_a(String phuong_an_a) {
        this.phuong_an_a = phuong_an_a;
    }

    public String getPhuong_an_b() {
        return phuong_an_b;
    }

    public void setPhuong_an_b(String phuong_an_b) {
        this.phuong_an_b = phuong_an_b;
    }

    public String getPhuong_an_c() {
        return phuong_an_c;
    }

    public void setPhuong_an_c(String phuong_an_c) {
        this.phuong_an_c = phuong_an_c;
    }

    public String getPhuong_an_d() {
        return phuong_an_d;
    }

    public void setPhuong_an_d(String phuong_an_d) {
        this.phuong_an_d = phuong_an_d;
    }

    public String getDap_an() {
        return dap_an;
    }

    public void setDap_an(String dap_an) {
        this.dap_an = dap_an;
    }

    public boolean isXoa() {
        return xoa;
    }

    public void setXoa(boolean xoa) {
        this.xoa = xoa;
    }
}
