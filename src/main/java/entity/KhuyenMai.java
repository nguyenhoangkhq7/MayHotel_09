package entity;
import java.time.LocalDate;
import java.util.Objects;

public class KhuyenMai {
    private String maKhuyenMai;
    private String tenKhuyenMai;
    private double giaTri;
    private LocalDate ngayTao;
    private Boolean conHoatDong;
    private int soLuong;
    private LocalDate ngayHetHan;

    private static int maKhuyenMaiCounter = 1;  // Đếm số khuyến mãi để tạo mã

    // Constructor không tham số
    public KhuyenMai() {
    }

    // Constructor đầy đủ
    public KhuyenMai(String tenKhuyenMai, double giaTri, Boolean conHoatDong, int soLuong, LocalDate ngayHetHan) {
        setTenKhuyenMai(tenKhuyenMai);
        setGiaTri(giaTri);
        setConHoatDong(conHoatDong);
        setSoLuong(soLuong);
        setNgayHetHan(ngayHetHan);
        this.ngayTao = LocalDate.now();  // Ngày hiện tại
        this.maKhuyenMai = generateMaKhuyenMai();  // Tạo mã khuyến mãi
    }
    // Constructor 1 tham so maKhuyenMai
    public KhuyenMai(String maKhuyenMai)
    {
    	this.maKhuyenMai= maKhuyenMai;
    }

    // Phương thức tạo mã khuyến mãi
    private String generateMaKhuyenMai() {
        String maKhuyenMai = "KM" + String.format("%04d", maKhuyenMaiCounter);
        maKhuyenMaiCounter++;  // Tăng bộ đếm để đảm bảo mã duy nhất
        return maKhuyenMai;
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

    public Boolean getConHoatDong() {
        return conHoatDong;
    }

    public void setConHoatDong(Boolean conHoatDong) {
        if (conHoatDong == null) {
            throw new IllegalArgumentException("Trạng thái hoạt động không được để trống");
        }
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

    public LocalDate getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(LocalDate ngayHetHan) {
        if (ngayHetHan == null || ngayHetHan.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Ngày hết hạn phải lớn hơn hoặc bằng ngày hiện tại");
        }
        this.ngayHetHan = ngayHetHan;
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
                ", ngayHetHan=" + ngayHetHan +
                '}';
    }
}

