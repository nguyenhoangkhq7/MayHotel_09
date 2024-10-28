package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class HoaDon {
	private String maHoaDon;
	private LocalDate ngayTao;
	private boolean trangThai;
	private double thanhTien;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	private DichVu dichVu;
	private KhuyenMai khuyenMai;

//	    private static int maHoaDonCounter = 1;  // Đếm số hóa đơn để tạo mã

	// Constructor không tham số
	public HoaDon() {
	}
	// Constructor đầy đủ
	public HoaDon(String maHoaDon, LocalDate ngayTao, boolean trangThai, double thanhTien, NhanVien nhanVien, KhachHang khachHang, DichVu dichVu, KhuyenMai khuyenMai) {
		this.maHoaDon = maHoaDon;
		this.ngayTao = ngayTao;
		this.trangThai = trangThai;
		this.thanhTien = thanhTien;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.dichVu = dichVu;
		this.khuyenMai = khuyenMai;
	}

	// Constructor 1 tham so maHoaDon
	public HoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}


	// Getters and Setters with validation
	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public LocalDate getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(LocalDate ngayTao) {
		this.ngayTao = ngayTao;
	}

	public boolean getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public double getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
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

	public DichVu getDichVu() {
		return dichVu;
	}

	public void setDichVu(DichVu dichVu) {
		this.dichVu = dichVu;
	}

	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}

	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}

	// Override equals and hashCode
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		HoaDon hoaDon = (HoaDon) o;
		return Objects.equals(maHoaDon, hoaDon.maHoaDon);
	}

	@Override
	public int hashCode() {
		return Objects.hash(maHoaDon);
	}

	// toString method


	@Override
	public String toString() {
		return "HoaDon{" +
				"maHoaDon='" + maHoaDon + '\'' +
				", ngayTao=" + ngayTao +
				", trangThai=" + trangThai +
				", thanhTien=" + thanhTien +
				", nhanVien=" + nhanVien +
				", khachHang=" + khachHang +
				", dichVu=" + dichVu +
				", khuyenMai=" + khuyenMai +
				'}';
	}
}
