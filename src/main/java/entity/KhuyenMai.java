package entity;

import java.time.LocalDate;
import java.util.Objects;

public class KhuyenMai {
    private String maKhuyenMai;          // Mã khuyến mãi
    private String tenKhuyenMai;         // Tên khuyến mãi
    private double giaTri;               // Giá trị khuyến mãi
    private LocalDate ngayTao;           // Ngày tạo khuyến mãi
    private boolean conHoatDong;         // Trạng thái hoạt động
    private int soLuong;                 // Số lượng khuyến mãi
    private String donViTinh;            // Đơn vị tính
    private String moTa;                 // Mô tả khuyến mãi
    private LocalDate ngayHetHan;        // Ngày hết hạn
    private String khachHangApDung;      // Điều kiện áp dụng

    // Constructor không tham số
    public KhuyenMai() {
    }

    // Constructor 1 tham số maKhuyenMai
    public KhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    // Constructor đầy đủ
    public KhuyenMai(String maKhuyenMai, String tenKhuyenMai, double giaTri, LocalDate ngayTao, boolean conHoatDong,
            int soLuong, LocalDate ngayHetHan, String dieuKienApDung) {
super();
this.maKhuyenMai = maKhuyenMai;
this.tenKhuyenMai = tenKhuyenMai;
this.giaTri = giaTri;
this.ngayTao = ngayTao;
this.conHoatDong = conHoatDong;
this.soLuong = soLuong;
this.ngayHetHan = ngayHetHan;
this.khachHangApDung = dieuKienApDung;
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

    public LocalDate getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

    public boolean isConHoatDong() {  // Đổi tên phương thức để tuân thủ quy ước đặt tên
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

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public LocalDate getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(LocalDate ngayHetHan) {
        if (ngayHetHan == null || ngayHetHan.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Ngày hết hạn phải lớn hơn hoặc bằng ngày hiện tại");
        }
        this.ngayHetHan = ngayHetHan;
    }

    public String getKhachHangApDung() {
        return khachHangApDung;
    }

    public void setKhachHangApDung(String dieuKienApDung) {
        this.khachHangApDung = dieuKienApDung;
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
                ", ngayTao=" + ngayTao +
                ", conHoatDong=" + conHoatDong +
                ", soLuong=" + soLuong +
                ", donViTinh='" + donViTinh + '\'' +
                ", moTa='" + moTa + '\'' +
                ", ngayHetHan=" + ngayHetHan +
                ", dieuKienApDung='" + khachHangApDung + '\'' +
                '}'; 
    }
}
