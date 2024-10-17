package entity;

import java.time.LocalDate;
import java.util.Objects;

public class PhieuThuChi {
	private String maPhieu;
	private String loaiPhieu;
	private String moTa;
	private LocalDate ngayTao;
	private double soTien;
	private String phuongThuc;
	 private static int maPhieuCounter = 1;  // Đếm số phiếu để tạo mã
	// Constructor không tham số
	public PhieuThuChi(){};
	// Constructor đầy đủ

    // Constructor 1 tham so maPhieuThuChi
	public PhieuThuChi(String maPhieu){
		this.maPhieu= maPhieu;
	};
	 public PhieuThuChi(String maPhieu, String loaiPhieu, String moTa, LocalDate ngayTao, double soTien,
			String phuongThuc) {
		super();
		this.maPhieu = maPhieu;
		this.loaiPhieu = loaiPhieu;
		this.moTa = moTa;
		this.ngayTao = ngayTao;
		this.soTien = soTien;
		this.phuongThuc = phuongThuc;
	}
//	private String generateMaPhieu() {
//	        String prefix = loaiPhieu.equalsIgnoreCase("PT") ? "PT" : "PC";
//	        String maPhieu = prefix + String.format("%04d", maPhieuCounter);
//	        maPhieuCounter++; 
//	        return maPhieu;
//	    }
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
	public LocalDate getNgayTao() {
		return ngayTao;
	}
	public void setNgayTao(LocalDate ngayTao) {
		this.ngayTao = ngayTao;
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
	public String getPhuongThuc() {
		return phuongThuc;
	}
	public void setPhuongThuc(String phuongThuc) {
	    if (phuongThuc == null || phuongThuc.isEmpty()) {
            throw new IllegalArgumentException("Phương thức không được để trống");
        }
        this.phuongThuc = phuongThuc;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maPhieu);
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhieuThuChi that = (PhieuThuChi) o;
        return Objects.equals(maPhieu, that.maPhieu);	}
	//toString
    @Override
    public String toString() {
        return "PhieuThuChi{" +
                "maPhieu='" + maPhieu + '\'' +
                ", loaiPhieu='" + loaiPhieu + '\'' +
                ", moTa='" + moTa + '\'' +
                ", ngayTao=" + ngayTao +
                ", soTien=" + soTien +
                ", phuongThuc='" + phuongThuc + '\'' +
                '}';
    }
	
}
