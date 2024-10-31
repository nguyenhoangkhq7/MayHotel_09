/*
    *MayHotel  day creative: 10/1/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here   
     */


package entity;

import java.time.LocalDate;
import java.util.Objects;

public class ChiTiet_DonDatPhong_Phong {
    private String maCT_DDP_P;
    private DonDatPhong donDatPhong;
    private Phong phong;
    private LocalDate ngayNhanPhong;
    private LocalDate ngayTra;
    private boolean laPhongChuyen;
    private double chietKhau;

//    constructor


    public ChiTiet_DonDatPhong_Phong(String maCT_DDP_P, DonDatPhong donDatPhong, Phong phong, LocalDate ngayNhanPhong, LocalDate ngayTra, boolean laPhongChuyen, double chietKhau) {
        this.maCT_DDP_P = maCT_DDP_P;
        this.donDatPhong = donDatPhong;
        this.phong = phong;
        this.ngayTra = ngayTra;
        this.laPhongChuyen = laPhongChuyen;
        this.ngayNhanPhong = ngayNhanPhong;
        this.chietKhau = chietKhau;
    }

    public boolean isLaPhongChuyen() {
        return laPhongChuyen;
    }

    public void setLaPhongChuyen(boolean laPhongChuyen) {
        this.laPhongChuyen = laPhongChuyen;
    }

    public double getChietKhau() {
        return chietKhau;
    }

    public void setChietKhau(double chietKhau) {
        this.chietKhau = chietKhau;
    }

    public ChiTiet_DonDatPhong_Phong(String maCT_DDP_P) {
        this.maCT_DDP_P = maCT_DDP_P;
    }

    public ChiTiet_DonDatPhong_Phong() {
    }

    //    setter getter
    public String getMaCT_DDP_P() {
        return maCT_DDP_P;
    }

    public void setMaCT_DDP_P(String maCT_DDP_P) {
        if(maCT_DDP_P.equals(""))
            throw new IllegalArgumentException("Mã đặt phòng không được rỗng");
        this.maCT_DDP_P = maCT_DDP_P;
    }

    public LocalDate getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(LocalDate ngayTra) {
        this.ngayTra = ngayTra;
    }

    public DonDatPhong getDonDatPhong() {
        return donDatPhong;
    }

    public void setDonDatPhong(DonDatPhong donDatPhong) {
        if(donDatPhong == null)
            throw new IllegalArgumentException("Đơn đặt phòng không được null");
        this.donDatPhong = donDatPhong;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        if(phong == null)
            throw new IllegalArgumentException("Phòng không được null");
        this.phong = phong;
    }

    public LocalDate getNgayNhanPhong() {
        return ngayNhanPhong;
    }

    public void setNgayNhanPhong(LocalDate ngayNhanPhong) {
        this.ngayNhanPhong = ngayNhanPhong;
    }

//    hashcode equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChiTiet_DonDatPhong_Phong that)) return false;
        return Objects.equals(maCT_DDP_P, that.maCT_DDP_P);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maCT_DDP_P);
    }

    //    toString

    @Override
    public String toString() {
        return "ChiTiet_DonDatPhong_Phong{" +
                "maCT_DDP_P='" + maCT_DDP_P + '\'' +
                ", donDatPhong=" + donDatPhong +
                ", phong=" + phong +
                ", ngayTra=" + ngayTra +
                ", ngayNhanPhong=" + ngayNhanPhong +
                '}';
    }
}
