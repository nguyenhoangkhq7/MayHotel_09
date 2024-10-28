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
    private String maDon;
    private LocalDate ngayTao;
    private String phuongThucThanhToan;
    private String trangThaiDonDatPhong;
    private boolean trangThaiDatCoc;
    private double tienDatCoc;
    private LocalDate ngayThanhToan;
    private NhanVien nhanVien;
    private KhachHang khachHang;
    private String moTa;

    public DonDatPhong(String maDonDatPhong) {
        this.maDon = maDonDatPhong;
    }
    public DonDatPhong() {
    }

    public DonDatPhong(String maDon, LocalDate ngayTao, String phuongThucThanhToan, String trangThaiDonDatPhong, boolean trangThaiDatCoc, double tienDatCoc, LocalDate ngayThanhToan, NhanVien nhanVien, KhachHang khachHang, String moTa) {
        this.maDon = maDon;
        this.ngayTao = ngayTao;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.trangThaiDonDatPhong = trangThaiDonDatPhong;
        this.trangThaiDatCoc = trangThaiDatCoc;
        this.tienDatCoc = tienDatCoc;
        this.ngayThanhToan = ngayThanhToan;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.moTa = moTa;
    }

    public String getMaDon() {
        return maDon;
    }

    public void setMaDon(String maDon) {
        this.maDon = maDon;
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

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public boolean isTrangThaiDatCoc() {
        return trangThaiDatCoc;
    }

    public void setTrangThaiDatCoc(boolean trangThaiDatCoc) {
        this.trangThaiDatCoc = trangThaiDatCoc;
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
        if (!(o instanceof DonDatPhong that)) return false;
        return Objects.equals(maDon, that.maDon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maDon);
    }

    @Override
    public String toString() {
        return "DonDatPhong{" +
                "maDon='" + maDon + '\'' +
                ", ngayTao=" + ngayTao +
                ", phuongThucThanhToan='" + phuongThucThanhToan + '\'' +
                ", trangThaiDonDatPhong='" + trangThaiDonDatPhong + '\'' +
                ", trangThaiDatCoc=" + trangThaiDatCoc +
                ", tienDatCoc=" + tienDatCoc +
                ", ngayThanhToan=" + ngayThanhToan +
                ", nhanVien=" + nhanVien +
                ", khachHang=" + khachHang +
                ", moTa='" + moTa + '\'' +
                '}';
    }
}
