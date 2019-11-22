package com.example.pemand.model;

public class Data {
    String nama, gambar;
    double nilai;

    public Data(double nilai, String nama, String gambar){
        this.nilai = nilai;
        this.nama = nama;
        this.gambar = gambar;
    }

    public double getNilai() {
        return nilai;
    }

    public String getNama() {
        return nama;
    }

    public String getGambar() {
        return gambar;
    }
}
