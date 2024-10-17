package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class HoaDon {
	private String maHoaDon;
	private LocalDate ngayTao;
	private Boolean trangThai;
	private double thanhTien;
	private String maNhanVien;
	private String maKhachHang;
	private String maDichVu;
	private String maKhuyenMai;

//	    private static int maHoaDonCounter = 1;  // Đếm số hóa đơn để tạo mã

	// Constructor không tham số
	public HoaDon() {
	}

	// Constructor đầy đủ
	public HoaDon(String maHoaDon, LocalDate ngayTao, Boolean trangThai, double thanhTien, String maKhachHang,
			String maNhanVien, String maDichVu, String maKhuyenMai) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayTao = ngayTao;
		this.trangThai = trangThai;
		this.thanhTien = thanhTien;
		this.maNhanVien = maNhanVien;
		this.maKhachHang = maKhachHang;
		this.maDichVu = maDichVu;
		this.maKhuyenMai = maKhuyenMai;
	}

	// Constructor 1 tham so maHoaDon
	public HoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	// Phương thức tạo mã hóa đơn theo định dạng yêu cầu
//	    private String generateMaHoaDon() {
//	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
//	        String datePart = this.ngayTao.format(formatter);  // DDMMYY
//	        String maHoaDon = "HD" + String.format("%04d", maHoaDonCounter) + nhanVien.getMaNhanVien() + datePart;
//	        maHoaDonCounter++;  // Tăng bộ đếm để đảm bảo mã duy nhất
//	        return maHoaDon;
//	    }

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

	public Boolean getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Boolean trangThai) {
		if (trangThai == null) {
			throw new IllegalArgumentException("Trạng thái không được để trống");
		}
		this.trangThai = trangThai;
	}

	
	public double getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
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

	public String getMaDichVu() {
		return maDichVu;
	}

	public void setMaDichVu(String maDichVu) {
		this.maDichVu = maDichVu;
	}

	public String getMaKhuyenMai() {
		return maKhuyenMai;
	}

	public void setMaKhuyenMai(String maKhuyenMai) {
		this.maKhuyenMai = maKhuyenMai;
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
		return "HoaDon [maHoaDon=" + maHoaDon + ", ngayTao=" + ngayTao + ", trangThai=" + trangThai + ", thanhTien="
				+ thanhTien + ", maNhanVien=" + maNhanVien + ", maKhachHang=" + maKhachHang + ", maDichVu=" + maDichVu
				+ ", maKhuyenMai=" + maKhuyenMai + "]";
	}

}
