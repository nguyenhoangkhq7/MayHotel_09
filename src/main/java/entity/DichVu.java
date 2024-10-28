/*
    *MayHotel  day creative: 10/1/2024
    version: 2023.2  IntelliJ IDEA
    author: Đăng Thái Bảo  */
    /*
       *class description:
            write description right here   
     */


package entity;

import java.time.LocalDate;
import java.util.Objects;

public class DichVu {
    private String maDichVu;  // Mã dịch vụ
    private double donGia;  // Đơn giá của dịch vụ
    private String tenDichVu;  // Tên của dịch vụ
    private int soLuongTon;  // Số lượng tồn kho của dịch vụ
    private String moTa;  // Mô tả chi tiết của dịch vụ
    private LocalDate ngayTao;  // Ngày tạo dịch vụ
    private boolean conHoatDong;  // Trạng thái hoạt động của dịch vụ
    private String donVi;

    // Constructor đầy đủ tham số
    public DichVu(String maDichVu, double donGia, String tenDichVu, int soLuongTon, String moTa, LocalDate ngayTao, boolean conHoatDong, String donVi) {
        setMaDichVu(maDichVu);
        setDonGia(donGia);
        setTenDichVu(tenDichVu);
        setSoLuongTon(soLuongTon);
        this.moTa = moTa;
        this.ngayTao = ngayTao;
        this.conHoatDong = conHoatDong;
        this.donVi = donVi;
    }
    // Constructor thuoc tinh ma 
    public DichVu(String maDichVu){
    	this.maDichVu = maDichVu;
    }  	
  
	// Getter và Setter cho các thuộc tính
    public String getMaDichVu() {
        return maDichVu;
    }

    public void setMaDichVu(String maDichVu) {
        if (maDichVu == null || maDichVu.isEmpty()) {
            throw new IllegalArgumentException("Mã dịch vụ không được rỗng");
        }
        this.maDichVu = maDichVu;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        if (donGia < 0) {
            throw new IllegalArgumentException("Đơn giá phải lớn hơn hoặc bằng 0");
        }
        this.donGia = donGia;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        if (tenDichVu == null || tenDichVu.isEmpty()) {
            throw new IllegalArgumentException("Tên dịch vụ không được rỗng");
        }
        this.tenDichVu = tenDichVu;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        if (soLuongTon < 0) {
            throw new IllegalArgumentException("Số lượng tồn phải lớn hơn hoặc bằng 0");
        }
        this.soLuongTon = soLuongTon;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public LocalDate getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

    public boolean isConHoatDong() {
        return conHoatDong;
    }

    public void setConHoatDong(boolean conHoatDong) {
        this.conHoatDong = conHoatDong;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    // Override phương thức equals và hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DichVu that)) return false;
        return Objects.equals(maDichVu, that.maDichVu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maDichVu);
    }

    // Override phương thức toString

    @Override
    public String toString() {
        return "DichVu{" +
                "maDichVu='" + maDichVu + '\'' +
                ", donGia=" + donGia +
                ", tenDichVu='" + tenDichVu + '\'' +
                ", soLuongTon=" + soLuongTon +
                ", moTa='" + moTa + '\'' +
                ", ngayTao=" + ngayTao +
                ", conHoatDong=" + conHoatDong +
                ", donVi='" + donVi + '\'' +
                '}';
    }
}
