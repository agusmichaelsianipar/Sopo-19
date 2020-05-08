package com.example.sopo_19.fragment;

public class DataApd {
    String namacmp,kota;
    long mask,coverall;

    public DataApd(String namacmp, String kota, long mask, long coverall) {
        this.namacmp = namacmp;
        this.kota = kota;
        this.mask = mask;
        this.coverall = coverall;
    }

    public DataApd(){
        //Constructor
    }

    public String getNamacmp() {
        return namacmp;
    }

    public void setNamacmp(String namacmp) {
        this.namacmp = namacmp;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public long getMask() {
        return mask;
    }

    public void setMask(long mask) {
        this.mask = mask;
    }

    public long getCoverall() {
        return coverall;
    }

    public void setCoverall(long coverall) {
        this.coverall = coverall;
    }
}
