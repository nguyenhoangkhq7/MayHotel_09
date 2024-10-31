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

public class Phong {
    private String maPhong;
    private String tenPhong;
    private LoaiPhong loaiPhong;
    private boolean trangThaiPhong;
    private String moTa;
    private String tang;

//    constructor
    public Phong(String maPhong, String tenPhong, LoaiPhong loaiPhong, boolean trangThaiPhong, String moTa,String tang) {
        setMaPhong(maPhong);
        this.tenPhong = tenPhong;
        this.tang = tang;
        setLoaiPhong(loaiPhong);
        this.trangThaiPhong = trangThaiPhong;
        this.moTa = moTa;
    }

    public Phong(String maPhong) {
        this.maPhong = maPhong;
    }

//    getter setter
    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        if(maPhong.equals(""))
            throw new IllegalArgumentException("Mã phòng không được rỗng");
        this.maPhong = maPhong;
    }

    public String getTang() {
        return tang;
    }

    public void setTang(String tang) {
        this.tang = tang;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public LoaiPhong getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(LoaiPhong loaiPhong) {
        if(loaiPhong == null)
            throw new IllegalArgumentException("Loại phòng không được null");
        this.loaiPhong = loaiPhong;
    }

    public boolean isTrangThaiPhong() {
        return trangThaiPhong;
    }

    public void setTrangThaiPhong(boolean trangThaiPhong) {
        this.trangThaiPhong = trangThaiPhong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

//    hashcode equal

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phong phong)) return false;
        return Objects.equals(maPhong, phong.maPhong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maPhong);
    }

//    toString
    @Override
    public String toString() {
        return "Phong{" +
                "maPhong='" + maPhong + '\'' +
                ", tenPhong='" + tenPhong + '\'' +
                ", tang='" + tang + '\'' +
                ", loaiPhong=" + loaiPhong +
                ", trangThaiPhong=" + trangThaiPhong +
                ", moTa='" + moTa + '\'' +
                '}';
    }
}
