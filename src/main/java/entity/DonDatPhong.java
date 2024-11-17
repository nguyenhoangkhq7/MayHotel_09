package entity;

import java.time.LocalDate;

public class DonDatPhong {
    private String maDon;
    private LocalDate ngayTao;
    private String phuongThucThanhToan;
    private String trangThaiDonDatPhong;
    private boolean trangThaiDatCoc;
    private NhanVien nhanVien;
    private KhachHang khachHang;
    private double tongTien;
    private String moTa;
    private LocalDate ngayTraPhong;
    private LocalDate ngayNhanPhong;

    public DonDatPhong(String maDon, LocalDate ngayTao, String phuongThucThanhToan,
                       String trangThaiDonDatPhong, boolean trangThaiDatCoc,
                       NhanVien nhanVien, KhachHang khachHang, double tongTien,
                       String moTa, LocalDate ngayTraPhong, LocalDate ngayNhanPhong) {
        this.maDon = maDon;
        this.ngayTao = ngayTao;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.trangThaiDonDatPhong = trangThaiDonDatPhong;
        this.trangThaiDatCoc = trangThaiDatCoc;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.tongTien = tongTien;
        this.moTa = moTa;
        this.ngayTraPhong = ngayTraPhong;
        this.ngayNhanPhong = ngayNhanPhong;
    }

    public DonDatPhong(String maDon) {
        this.maDon = maDon;
    }

    public DonDatPhong() {
    }

    // Getters và Setters

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

    public boolean isTrangThaiDatCoc() {
        return trangThaiDatCoc;
    }

    public void setTrangThaiDatCoc(boolean trangThaiDatCoc) {
        this.trangThaiDatCoc = trangThaiDatCoc;
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

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public LocalDate getNgayTraPhong() {
        return ngayTraPhong;
    }

    public void setNgayTraPhong(LocalDate ngayTraPhong) {
        this.ngayTraPhong = ngayTraPhong;
    }

    public LocalDate getNgayNhanPhong() {
        return ngayNhanPhong;
    }

    public void setNgayNhanPhong(LocalDate ngayNhanPhong) {
        this.ngayNhanPhong = ngayNhanPhong;
    }

    @Override
    public String toString() {
        return "DonDatPhong{" +
                "maDon='" + maDon + '\'' +
                ", ngayTao=" + ngayTao +
                ", phuongThucThanhToan='" + phuongThucThanhToan + '\'' +
                ", trangThaiDonDatPhong='" + trangThaiDonDatPhong + '\'' +
                ", trangThaiDatCoc=" + trangThaiDatCoc +
                ", nhanVien=" + nhanVien +
                ", khachHang=" + khachHang +
                ", tongTien=" + tongTien +
                ", moTa='" + moTa + '\'' +
                ", ngayTraPhong=" + ngayTraPhong +
                ", ngayNhanPhong=" + ngayNhanPhong +
                '}';
    }
}
