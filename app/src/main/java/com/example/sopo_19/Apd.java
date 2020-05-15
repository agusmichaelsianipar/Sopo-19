package com.example.sopo_19;

public class Apd {
    String namacmp;
    String kota;
    long coverall;
    long mask;
    String kontak;

    public Apd() {

    }

    public Apd(String namacmp, String kota, long coverall, long mask, String kontak) {
        this.namacmp = namacmp;
        this.kota = kota;
        this.coverall = coverall;
        this.mask = mask;
        this.kontak = kontak;
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

    public long getCoverall() {
        return coverall;
    }

    public void setCoverall(long coverall) {
        this.coverall = coverall;
    }

    public long getMask() {
        return mask;
    }

    public void setMask(long mask) {
        this.mask = mask;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }
}
