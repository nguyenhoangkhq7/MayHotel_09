package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class PhieuThuChi {
    private String maPhieu; // Mã phiếu
    private String loaiPhieu; // Loại phiếu (Thu hoặc Chi)
    private String moTa; // Mô tả về phiếu
    private LocalDateTime ngayLap; // Ngày lập phiếu
    private double soTien; // Số tiền trong phiếu
    private String phuongThucThanhToan; // Phương thức thanh toán (tiền mặt, chuyển khoản...)
    private boolean conHoatDong; // Trạng thái hoạt động của phiếu (còn hoạt động hay không)
    private NhanVien nhanVien; // Nhân viên thực hiện phiếu

    // Constructor 1 tham số maPhieu
    public PhieuThuChi(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    // Constructor không tham số
    public PhieuThuChi() {}

    // Constructor đầy đủ
    public PhieuThuChi(String maPhieu, String loaiPhieu, String moTa, LocalDateTime ngayLap, double soTien,
                       String phuongThucThanhToan, boolean conHoatDong, NhanVien nhanVien) {
        this.maPhieu = maPhieu;
        this.loaiPhieu = loaiPhieu;
        this.moTa = moTa;
        this.ngayLap = ngayLap;
        this.soTien = soTien;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.conHoatDong = conHoatDong;
        this.nhanVien = nhanVien;
    }

    // Getter và Setter
    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public String getLoaiPhieu() {
        return loaiPhieu;
    }

    public void setLoaiPhieu(String loaiPhieu) {
        if (loaiPhieu == null || loaiPhieu.isEmpty()) {
            throw new IllegalArgumentException("Loại phiếu không được để trống");
        }
        this.loaiPhieu = loaiPhieu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        if (moTa == null || moTa.isEmpty()) {
            throw new IllegalArgumentException("Mô tả không được để trống");
        }
        this.moTa = moTa;
    }

    public LocalDateTime getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDateTime ngayLap) {
        this.ngayLap = ngayLap;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        if (soTien <= 0) {
            throw new IllegalArgumentException("Số tiền phải lớn hơn 0");
        }
        this.soTien = soTien;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        if (phuongThucThanhToan == null || phuongThucThanhToan.isEmpty()) {
            throw new IllegalArgumentException("Phương thức thanh toán không được để trống");
        }
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public boolean isConHoatDong() {
        return conHoatDong;
    }

    public void setConHoatDong(boolean conHoatDong) {
        this.conHoatDong = conHoatDong;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    // Các phương thức equals và hashCode dựa trên maPhieu
    @Override
    public int hashCode() {
        return Objects.hash(maPhieu);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhieuThuChi that = (PhieuThuChi) o;
        return Objects.equals(maPhieu, that.maPhieu);
    }

    // Phương thức toString để in thông tin đối tượng
    @Override
    public String toString() {
        return "PhieuThuChi{" +
                "maPhieu='" + maPhieu + '\'' +
                ", loaiPhieu='" + loaiPhieu + '\'' +
                ", moTa='" + moTa + '\'' +
                ", ngayLap=" + ngayLap +
                ", soTien=" + soTien +
                ", phuongThucThanhToan='" + phuongThucThanhToan + '\'' +
                ", conHoatDong=" + conHoatDong +
                ", nhanVien=" + nhanVien +
                '}';
    }
}
