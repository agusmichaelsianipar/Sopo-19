package com.example.sopo_19.fragment;

public class DataRs {
    String namars,kota;
    long mask,coverall;

    public DataRs(String namars, String kota, long mask, long coverall) {
        this.namars = namars;
        this.kota = kota;
        this.mask = mask;
        this.coverall = coverall;
    }

    public DataRs() {
        //Constructor for Firebase
    }

    public String getNamars() {
        return namars;
    }

    public void setNamars(String namars) {
        this.namars = namars;
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
