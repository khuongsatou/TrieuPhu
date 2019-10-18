package com.nvk.doanailatrieuphu.Model;

public class CauHoi {
    private String noiDung;
    private int linhVucId;
    private String phuongAnA;
    private String phuongAnB;
    private String phuongAnC;
    private String phuongAnD;
    private String dapAn;
    private Boolean Checked;


    public CauHoi() {
    }

    public CauHoi(String noiDung, int linhVucId, String phuongAnA, String phuongAnB, String phuongAnC, String phuongAnD, String dapAn, Boolean checked) {
        this.noiDung = noiDung;
        this.linhVucId = linhVucId;
        this.phuongAnA = phuongAnA;
        this.phuongAnB = phuongAnB;
        this.phuongAnC = phuongAnC;
        this.phuongAnD = phuongAnD;
        this.dapAn = dapAn;
        Checked = checked;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public int getLinhVucId() {
        return linhVucId;
    }

    public void setLinhVucId(int linhVucId) {
        this.linhVucId = linhVucId;
    }

    public String getPhuongAnA() {
        return phuongAnA;
    }

    public void setPhuongAnA(String phuongAnA) {
        this.phuongAnA = phuongAnA;
    }

    public String getPhuongAnB() {
        return phuongAnB;
    }

    public void setPhuongAnB(String phuongAnB) {
        this.phuongAnB = phuongAnB;
    }

    public String getPhuongAnC() {
        return phuongAnC;
    }

    public void setPhuongAnC(String phuongAnC) {
        this.phuongAnC = phuongAnC;
    }

    public String getPhuongAnD() {
        return phuongAnD;
    }

    public void setPhuongAnD(String phuongAnD) {
        this.phuongAnD = phuongAnD;
    }

    public String getDapAn() {
        return dapAn;
    }

    public void setDapAn(String dapAn) {
        this.dapAn = dapAn;
    }

    public Boolean getChecked() {
        return Checked;
    }

    public void setChecked(Boolean checked) {
        Checked = checked;
    }
}
