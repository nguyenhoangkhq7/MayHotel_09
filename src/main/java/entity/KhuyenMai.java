package entity;

import java.time.LocalDate;
import java.util.Objects;

public class KhuyenMai {
    private String maKhuyenMai;          // Mã khuyến mãi
    private String tenKhuyenMai;         // Tên khuyến mãi
    private double giaTri;               // Giá trị khuyến mãi
    private LocalDate ngayBatDau;        // Ngày bắt đầu khuyến mãi
    private boolean conHoatDong;         // Trạng thái hoạt động
    private int soLuong;                 // Số lượng khuyến mãi
    private LocalDate ngayKetThuc;       // Ngày kết thúc khuyến mãi
    private String loaiKhachHangApDung;  // Loại khách hàng áp dụng

    // Constructor không tham số
    public KhuyenMai() {
    }

    // Constructor 1 tham số maKhuyenMai
    public KhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    // Constructor đầy đủ
    public KhuyenMai(String maKhuyenMai, String tenKhuyenMai, double giaTri, LocalDate ngayBatDau, boolean conHoatDong,
                     int soLuong, LocalDate ngayKetThuc, String loaiKhachHangApDung) {
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.giaTri = giaTri;
        this.ngayBatDau = ngayBatDau;
        this.conHoatDong = conHoatDong;
        this.soLuong = soLuong;
        this.ngayKetThuc = ngayKetThuc;
        this.loaiKhachHangApDung = loaiKhachHangApDung;
    }

    // Getters and Setters
    public String getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        if (tenKhuyenMai == null || tenKhuyenMai.isEmpty()) {
            throw new IllegalArgumentException("Tên khuyến mãi không được để trống");
        }
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public double getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(double giaTri) {
        if (giaTri <= 0) {
            throw new IllegalArgumentException("Giá trị khuyến mãi phải lớn hơn 0");
        }
        this.giaTri = giaTri;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public boolean isConHoatDong() {
        return conHoatDong;
    }

    public void setConHoatDong(boolean conHoatDong) {
        this.conHoatDong = conHoatDong;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        if (soLuong <= 0) {
            throw new IllegalArgumentException("Số lượng phải lớn hơn 0");
        }
        this.soLuong = soLuong;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        if (ngayKetThuc == null || ngayKetThuc.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Ngày kết thúc phải lớn hơn hoặc bằng ngày hiện tại");
        }
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getLoaiKhachHangApDung() {
        return loaiKhachHangApDung;
    }

    public void setLoaiKhachHangApDung(String loaiKhachHangApDung) {
        this.loaiKhachHangApDung = loaiKhachHangApDung;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KhuyenMai khuyenMai = (KhuyenMai) o;
        return Objects.equals(maKhuyenMai, khuyenMai.maKhuyenMai);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maKhuyenMai);
    }

    // toString method
    @Override
    public String toString() {
        return "KhuyenMai{" +
                "maKhuyenMai='" + maKhuyenMai + '\'' +
                ", tenKhuyenMai='" + tenKhuyenMai + '\'' +
                ", giaTri=" + giaTri +
                ", ngayBatDau=" + ngayBatDau +
                ", conHoatDong=" + conHoatDong +
                ", soLuong=" + soLuong +
                ", ngayKetThuc=" + ngayKetThuc +
                ", loaiKhachHangApDung='" + loaiKhachHangApDung + '\'' +
                '}';
    }
}
