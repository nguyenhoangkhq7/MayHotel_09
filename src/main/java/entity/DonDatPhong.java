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
    private LocalDate ngayNhanPhong;
    private LocalDate ngayTra;

    public DonDatPhong(String maDon, LocalDate ngayTao, String phuongThucThanhToan,
                       String trangThaiDonDatPhong, boolean trangThaiDatCoc,
                       NhanVien nhanVien, KhachHang khachHang, double tongTien,
                       String moTa, LocalDate ngayNhanPhong, LocalDate ngayTra) {
        this.maDon = maDon;
        this.ngayTao = ngayTao;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.trangThaiDonDatPhong = trangThaiDonDatPhong;
        this.trangThaiDatCoc = trangThaiDatCoc;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.tongTien = tongTien;
        this.moTa = moTa;
        this.ngayNhanPhong = ngayNhanPhong;
        this.ngayTra = ngayTra;
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

    public LocalDate getNgayTao() {
        return ngayTao;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public String getTrangThaiDonDatPhong() {
        return trangThaiDonDatPhong;
    }

    public boolean isTrangThaiDatCoc() {
        return trangThaiDatCoc;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public double getTongTien() {
        return tongTien;
    }

    public String getMoTa() {
        return moTa;
    }

    public LocalDate getNgayNhanPhong() {
        return ngayNhanPhong;
    }

    public LocalDate getNgayTra() {
        return ngayTra;
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
                ", ngayNhanPhong=" + ngayNhanPhong +
                ", ngayTra=" + ngayTra +
                '}';
    }

}
