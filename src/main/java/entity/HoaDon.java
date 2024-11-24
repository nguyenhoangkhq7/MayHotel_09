package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class HoaDon {
	private String maHoaDon;
	private boolean trangThai;
	private double thanhTien;
	private NhanVien nhanVien;
	private KhuyenMai khuyenMai;
	private DonDatPhong donDatPhong;
	private LocalDateTime ngayTao;


	// Constructor không tham số
	public HoaDon() {
	}
	// Constructor đầy đủ


	public HoaDon(String maHoaDon, boolean trangThai, double thanhTien, NhanVien nhanVien, KhuyenMai khuyenMai, DonDatPhong donDatPhong, LocalDateTime ngayTao) {
		this.maHoaDon = maHoaDon;
		this.trangThai = trangThai;
		this.thanhTien = thanhTien;
		this.nhanVien = nhanVien;
		this.khuyenMai = khuyenMai;
		this.donDatPhong = donDatPhong;
		this.ngayTao = ngayTao;
	}

	public DonDatPhong getDonDatPhong() {
		return donDatPhong;
	}


	public void setDonDatPhong(DonDatPhong donDatPhong) {
		this.donDatPhong = donDatPhong;
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

	public LocalDateTime getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(LocalDateTime ngayTao) {
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
				", trangThai=" + trangThai +
				", thanhTien=" + thanhTien +
				", nhanVien=" + nhanVien +
				", khuyenMai=" + khuyenMai +
				", donDatPhong=" + donDatPhong +
				", ngayTao=" + ngayTao +
				'}';
	}
}
