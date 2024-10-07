package entity;
	import java.time.LocalDate;
	import java.time.format.DateTimeFormatter;
	import java.util.Objects;
	public class HoaDon {
	    private String maHoaDon;
	    private LocalDate ngayTao;
	    private Boolean trangThai;
	    private String thanhTien;
	    private KhachHang khachHang;
	    private NhanVien nhanVien;
	    private DichVu dichVu;
	    private KhuyenMai khuyenMai;

	    private static int maHoaDonCounter = 1;  // Đếm số hóa đơn để tạo mã

	    // Constructor không tham số
	    public HoaDon() {
	    }

	    // Constructor đầy đủ
	    public HoaDon(Boolean trangThai, String thanhTien, KhachHang khachHang, NhanVien nhanVien, DichVu dichVu, KhuyenMai khuyenMai) {
	        setTrangThai(trangThai);
	        setThanhTien(thanhTien);
	        setKhachHang(khachHang);
	        setNhanVien(nhanVien);
	        setDichVu(dichVu);
	        setKhuyenMai(khuyenMai);
	        this.ngayTao = LocalDate.now();  // Ngày hiện tại
	        this.maHoaDon = generateMaHoaDon();  // Tạo mã hóa đơn
	    }
	    // Constructor 1 tham so maHoaDon
	    public HoaDon(String maHoaDon)
	    {
	    	this.maHoaDon= maHoaDon;
	    }
	    // Phương thức tạo mã hóa đơn theo định dạng yêu cầu
	    private String generateMaHoaDon() {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
	        String datePart = this.ngayTao.format(formatter);  // DDMMYY
	        String maHoaDon = "HD" + String.format("%04d", maHoaDonCounter) + nhanVien.getMaNhanVien() + datePart;
	        maHoaDonCounter++;  // Tăng bộ đếm để đảm bảo mã duy nhất
	        return maHoaDon;
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

	    public Boolean getTrangThai() {
	        return trangThai;
	    }

	    public void setTrangThai(Boolean trangThai) {
	        if (trangThai == null) {
	            throw new IllegalArgumentException("Trạng thái không được để trống");
	        }
	        this.trangThai = trangThai;
	    }

	    public String getThanhTien() {
	        return thanhTien;
	    }

	    public void setThanhTien(String thanhTien) {
	        if (thanhTien == null || thanhTien.isEmpty()) {
	            throw new IllegalArgumentException("Thành tiền không được để trống");
	        }
	        this.thanhTien = thanhTien;
	    }

	    public KhachHang getKhachHang() {
	        return khachHang;
	    }

	    public void setKhachHang(KhachHang khachHang) {
	        this.khachHang = khachHang;
	    }

	    public NhanVien getNhanVien() {
	        return nhanVien;
	    }

	    public void setNhanVien(NhanVien nhanVien) {
	        this.nhanVien = nhanVien;
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
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
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
	                ", thanhTien='" + thanhTien + '\'' +
	                ", khachHang=" + khachHang +
	                ", nhanVien=" + nhanVien +
	                ", dichVu=" + dichVu +
	                ", khuyenMai=" + khuyenMai +
	                '}';
	    }
	}



