package com.example.crud.entity;

import com.orm.SugarRecord;

public class Barang extends SugarRecord {
    Long id;
    String nama;
    Double harga;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public Barang(Long id, String nama, Double harga) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
    }

    public Barang() {
    }
}
