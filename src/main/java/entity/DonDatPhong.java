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

public class DonDatPhong {
    private String maDonDatPhong;
    private LocalDate ngayTao;
    private String phuongThucThanhToan;
    private String trangThaiDonDatPhong;
    private double tienDatCoc;
    private LocalDate ngayThanhToan;
    private String maNhanVien;
    private String maKhachHang;

    public DonDatPhong(String maDonDatPhong) {
        this.maDonDatPhong = maDonDatPhong;
    }

    public DonDatPhong(String maDonDatPhong, LocalDate ngayTao, String phuongThucThanhToan, String trangThaiDonDatPhong, double tienDatCoc, LocalDate ngayThanhToan, String maNhanVien, String maKhachHang) {
        this.maDonDatPhong = maDonDatPhong;
        this.ngayTao = ngayTao;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.trangThaiDonDatPhong = trangThaiDonDatPhong;
        this.tienDatCoc = tienDatCoc;
        this.ngayThanhToan = ngayThanhToan;
        this.maNhanVien = maNhanVien;
        this.maKhachHang = maKhachHang;
    }

    public String getMaDonDatPhong() {
        return maDonDatPhong;
    }

    public void setMaDonDatPhong(String maDonDatPhong) {
        this.maDonDatPhong = maDonDatPhong;
    }

    public LocalDate getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public String getTrangThaiDonDatPhong() {
        return trangThaiDonDatPhong;
    }

    public void setTrangThaiDonDatPhong(String trangThaiDonDatPhong) {
        this.trangThaiDonDatPhong = trangThaiDonDatPhong;
    }

    public double getTienDatCoc() {
        return tienDatCoc;
    }

    public void setTienDatCoc(double tienDatCoc) {
        this.tienDatCoc = tienDatCoc;
    }

    public LocalDate getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(LocalDate ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DonDatPhong that)) return false;
        return Objects.equals(maDonDatPhong, that.maDonDatPhong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maDonDatPhong);
    }

    @Override
    public String toString() {
        return "DonDatPhong{" +
                "maDonDatPhong='" + maDonDatPhong + '\'' +
                ", ngayTao=" + ngayTao +
                ", phuongThucThanhToan='" + phuongThucThanhToan + '\'' +
                ", trangThaiDonDatPhong='" + trangThaiDonDatPhong + '\'' +
                ", tienDatCoc=" + tienDatCoc +
                ", ngayThanhToan=" + ngayThanhToan +
                ", maNhanVien='" + maNhanVien + '\'' +
                ", maKhachHang='" + maKhachHang + '\'' +
                '}';
    }

}
