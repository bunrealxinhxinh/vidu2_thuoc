package com.ltdd.vidu2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.Serializable;

public class thongTinThuoc implements Serializable {
    int soluong;
    String hsd, nsx;
    String id, ten;

    public thongTinThuoc(String id, String ten, int soluong, String hsd, String nsx) {
        this.id = id;
        this.ten = ten;
        this.soluong = soluong;
        this.hsd = hsd;
        this.nsx = nsx;
    }

    public thongTinThuoc() {
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getsoluong() {
        return soluong;
    }

    public void setsoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String gethsd() {
        return hsd;
    }

    public void sethsd(String hsd) {
        this.hsd = hsd;
    }

    public String getnsx() {
        return nsx;
    }

    public void setnsx(String nsx) {
        this.nsx = nsx;
    }
}