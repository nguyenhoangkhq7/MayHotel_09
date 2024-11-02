/*
    *MayHotel  day creative: 10/1/2024
    version: 2023.2  IntelliJ IDEA
    author: Tô Hoàng Thành  */
    /*
       *class description:
            write description right here   
     */


package entity;


import java.util.Objects;

public class TaiKhoan {
    private String tenDangNhap;
    private String matKhau;

    public TaiKhoan(String tenDangNhap, String matKhau) {
//        setTenDangNhap(tenDangNhap);
//        setMatKhau(matKhau);
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        if (tenDangNhap == null || tenDangNhap.isEmpty()) {
            throw new IllegalArgumentException("Tên đăng nhập không được rỗng");
        }
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        if (matKhau == null || matKhau.isEmpty()) {
            throw new IllegalArgumentException("Mật khẩu không được rỗng");
        }

        if (!matKhau.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8}$")) {
            throw new IllegalArgumentException("Mật khẩu phải có đúng 8 ký tự bao gồm cả chữ và số");
        }
        this.matKhau = matKhau;
    }


    @Override
    public int hashCode() {
        return Objects.hash(tenDangNhap);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; 
        if (obj == null || getClass() != obj.getClass()) return false; 
        TaiKhoan taiKhoan = (TaiKhoan) obj; // Ép kiểu đối tượng
        return Objects.equals(tenDangNhap, taiKhoan.tenDangNhap); 
    }


    @Override
    public String toString() {
        return "TaiKhoan { " +
                "tenDangNhap='" + tenDangNhap + '\'' +
                ", matKhau='" + matKhau + '\'' +
                " }";
    }
}