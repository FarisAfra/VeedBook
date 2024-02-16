package com.farisafra.projectuasmobile;

import java.io.Serializable;

public class Produk implements Serializable {
    private int id;
    private String judul, penulis, deskripsi, harga, spek, kategori;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {

        this.judul = judul;
    }

    public String getPenulis() {

        return penulis;
    }

    public void setPenulis(String penulis) {

        this.penulis = penulis;
    }

    public String getDeskripsi() {

        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {

        this.deskripsi = deskripsi;
    }

    public String getHarga() {

        return harga;
    }

    public void setHarga(String harga) {

        this.harga = harga;
    }

    public String getSpek() {

        return spek;
    }

    public void setSpek(String spek) {

        this.spek = spek;
    }

    public String getKategori() {

        return kategori;
    }

    public void setKategori(String kategori) {

        this.kategori = kategori;
    }

}
