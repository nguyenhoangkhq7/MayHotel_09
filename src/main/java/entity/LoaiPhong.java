/*
    *MayHotel  day creative: 10/1/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here   
     */


package entity;

import java.util.Objects;

public class LoaiPhong {
    private String maLoaiPhong;
    private String tenLoaiPhong;
    private int sucChua;
    private double donGia;
    private String mota;
    private double chietKhau;

//    constructor
    public LoaiPhong(String maLoaiPhong, String tenLoaiPhong, int sucChua, double donGia, String mota, double chietKhau) {
        setMaLoaiPhong(maLoaiPhong);
        this.tenLoaiPhong = tenLoaiPhong;
        setSucChua(sucChua);
        setDonGia(donGia);
        this.mota = mota;
        this.chietKhau = chietKhau;
    }

    public LoaiPhong(String maLoaiPhong) {
        this.maLoaiPhong = maLoaiPhong;
    }

//    getter setter

    public String getMaLoaiPhong() {
        return maLoaiPhong;
    }

    public void setMaLoaiPhong(String maLoaiPhong) {
        if(maLoaiPhong.equals(""))
            throw new IllegalArgumentException("Mã loại phòng không được rỗng");
        this.maLoaiPhong = maLoaiPhong;
    }

    public String getTenLoaiPhong() {
        return tenLoaiPhong;
    }

    public void setTenLoaiPhong(String tenLoaiPhong) {
        this.tenLoaiPhong = tenLoaiPhong;
    }

    public int getSucChua() {
        return sucChua;
    }

    public void setSucChua(int sucChua) {
        if(sucChua <= 0)
            throw new IllegalArgumentException("Sức chứa > 0");
        this.sucChua = sucChua;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        if(donGia <= 0)
            throw new IllegalArgumentException("Đơn giá > 0");
        this.donGia = donGia;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public double getChietKhau() {
        return chietKhau;
    }

    public void setChietKhau(double chietKhau) {
        if(chietKhau <= 0)
            throw new IllegalArgumentException("Chiết khấu > 0");
        this.chietKhau = chietKhau;
    }

//    hashcode equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoaiPhong loaiPhong)) return false;
        return Objects.equals(maLoaiPhong, loaiPhong.maLoaiPhong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maLoaiPhong);
    }

//    toString

    @Override
    public String toString() {
        return "LoaiPhong{" +
                "maLoaiPhong='" + maLoaiPhong + '\'' +
                ", tenLoaiPhong='" + tenLoaiPhong + '\'' +
                ", sucChua=" + sucChua +
                ", donGia=" + donGia +
                ", mota='" + mota + '\'' +
                ", chietKhau=" + chietKhau +
                '}';
    }
}
