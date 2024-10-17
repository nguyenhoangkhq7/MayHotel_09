/*
    *MayHotel  day creative: 10/1/2024
    version: 2023.2  IntelliJ IDEA
    author: Đặng Thái Bảo  */
    /*
       *class description:
            write description right here   
     */

package entity;

import java.util.Objects;

public class KhachHang {
    private String maKH;         // Mã khách hàng
    private String hoTen;        // Họ tên khách hàng
    private String soDienThoai;  // Số điện thoại khách hàng
    private String soCanCuoc;    // Số căn cước công dân hoặc chứng minh nhân dân
    private String email;        // Email khách hàng
    private double tienTichLuy;  // Tiền tích lũy của khách hàng
    private LoaiKhachHang loaiKhachHang;  // Loại khách hàng (có thể là Regular, VIP, vv.)

    // Constructor đầy đủ tham số
    public KhachHang(String maKH, String hoTen, String soDienThoai, String soCanCuoc, String email, double tienTichLuy, LoaiKhachHang loaiKhachHang) {
        setMaKH(maKH);
        setHoTen(hoTen);
        setSoDienThoai(soDienThoai);
        setSoCanCuoc(soCanCuoc);
        setEmail(email);
        setTienTichLuy(tienTichLuy);
        setLoaiKhachHang(loaiKhachHang);
    }
    // Constructor ma
    public KhachHang(String maKH) {
    	this.maKH = maKH;
    }

    // Getter và Setter cho các thuộc tính
    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        if (maKH == null || maKH.isEmpty()) {
            throw new IllegalArgumentException("Mã khách hàng không được rỗng");
        }
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        if (hoTen == null || hoTen.isEmpty()) {
            throw new IllegalArgumentException("Họ tên không được rỗng");
        }
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        if (soDienThoai == null || soDienThoai.isEmpty()) {
            throw new IllegalArgumentException("Số điện thoại không được rỗng");
        }
        this.soDienThoai = soDienThoai;
    }

    public String getSoCanCuoc() {
        return soCanCuoc;
    }

    public void setSoCanCuoc(String soCanCuoc) {
        if (soCanCuoc == null || soCanCuoc.isEmpty()) {
            throw new IllegalArgumentException("Số căn cước công dân không được rỗng");
        }
        this.soCanCuoc = soCanCuoc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email không được rỗng");
        }
        this.email = email;
    }

    public double getTienTichLuy() {
        return tienTichLuy;
    }

    public void setTienTichLuy(double tienTichLuy) {
        if (tienTichLuy < 0) {
            throw new IllegalArgumentException("Tiền tích lũy không được âm");
        }
        this.tienTichLuy = tienTichLuy;
    }

    public LoaiKhachHang getLoaiKhachHang() {
        return loaiKhachHang;
    }

    public void setLoaiKhachHang(LoaiKhachHang loaiKhachHang) {
        if (loaiKhachHang == null) {
            throw new IllegalArgumentException("Loại khách hàng không được null");
        }
        this.loaiKhachHang = loaiKhachHang;
    }

    // Override phương thức equals và hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KhachHang that)) return false;
        return Objects.equals(maKH, that.maKH);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maKH);
    }

    // Override phương thức toString
    @Override
    public String toString() {
        return "KhachHang{" +
                "maKH='" + maKH + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", soCanCuoc='" + soCanCuoc + '\'' +
                ", email='" + email + '\'' +
                ", tienTichLuy=" + tienTichLuy +
                ", loaiKhachHang=" + loaiKhachHang +
                '}';
    }
}
