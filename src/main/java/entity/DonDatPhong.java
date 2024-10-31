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
    private NhanVien nhanVien;
    private KhachHang khachHang;
    private double tongTien;
    private String moTa;
    private LocalDate ngayNhanPhong;
    private LocalDate ngayTra;

    public DonDatPhong(String maDonDatPhong) {
        this.maDon = maDonDatPhong;
    }
    public DonDatPhong() {
    }

    public DonDatPhong(String maDon, LocalDate ngayTao, String phuongThucThanhToan, String trangThaiDonDatPhong, boolean trangThaiDatCoc, double tienDatCoc, NhanVien nhanVien, KhachHang khachHang, double tongTien, String moTa, LocalDate ngayNhanPhong, LocalDate ngayTra) {
        this.maDon = maDon;
        this.ngayTao = ngayTao;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.trangThaiDonDatPhong = trangThaiDonDatPhong;
        this.trangThaiDatCoc = trangThaiDatCoc;
        this.tienDatCoc = tienDatCoc;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.tongTien = tongTien;
        this.moTa = moTa;
        this.ngayNhanPhong = ngayNhanPhong;
        this.ngayTra = ngayTra;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public LocalDate getNgayNhanPhong() {
        return ngayNhanPhong;
    }

    public void setNgayNhanPhong(LocalDate ngayNhanPhong) {
        this.ngayNhanPhong = ngayNhanPhong;
    }

    public LocalDate getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(LocalDate ngayTra) {
        this.ngayTra = ngayTra;
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
                ", nhanVien=" + nhanVien +
                ", khachHang=" + khachHang +
                ", tongTien=" + tongTien +
                ", moTa='" + moTa + '\'' +
                ", ngayNhanPhong=" + ngayNhanPhong +
                ", ngayTra=" + ngayTra +
                '}';
    }
}
