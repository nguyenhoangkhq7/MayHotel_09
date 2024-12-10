/*
    *MayHotel  day creative: 10/1/2024
    version: 2023.2  IntelliJ IDEA
    author: Đặng Thái Bảo  */
    /*
       *class description:
            write description right here   
     */


package entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class ChiTiet_DonDatPhong_Phong_DichVu {
    private int soLuongDat;
    private LocalDateTime tgSuDungDV;
    private DichVu dichVu;
    private DonDatPhong donDatPhong;
    private Phong phong;
    private String moTa;

    // Constructor

    public ChiTiet_DonDatPhong_Phong_DichVu(int soLuongDat, LocalDateTime tgSuDungDV, DichVu dichVu, DonDatPhong donDatPhong, Phong phong, String moTa) {
        this.soLuongDat = soLuongDat;
        this.tgSuDungDV = tgSuDungDV;
        this.dichVu = dichVu;
        this.donDatPhong = donDatPhong;
        this.phong = phong;
        this.moTa = moTa;
    }

    public ChiTiet_DonDatPhong_Phong_DichVu() {
    }

    public int getSoLuongDat() {
        return soLuongDat;
    }

    public void setSoLuongDat(int soLuongDat) {
        this.soLuongDat = soLuongDat;
    }

    public LocalDateTime getTgSuDungDV() {
        return tgSuDungDV;
    }

    public void setTgSuDungDV(LocalDateTime tgSuDungDV) {
        this.tgSuDungDV = tgSuDungDV;
    }

    public DichVu getDichVu() {
        return dichVu;
    }

    public void setDichVu(DichVu dichVu) {
        this.dichVu = dichVu;
    }

    public DonDatPhong getDonDatPhong() {
        return donDatPhong;
    }

    public void setDonDatPhong(DonDatPhong donDatPhong) {
        this.donDatPhong = donDatPhong;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChiTiet_DonDatPhong_Phong_DichVu that)) return false;
        return Objects.equals(dichVu, that.dichVu) && Objects.equals(donDatPhong, that.donDatPhong) && Objects.equals(phong, that.phong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dichVu, donDatPhong, phong);
    }

    @Override
    public String toString() {
        return "ChiTiet_DonDatPhong_Phong_DichVu{" +
                "soLuongDat=" + soLuongDat +
                ", ngayTao=" + tgSuDungDV +
                ", dichVu=" + dichVu +
                ", donDatPhong=" + donDatPhong +
                ", phong=" + phong +
                ", moTa='" + moTa + '\'' +
                '}';
    }
}
