/*
    *MayHotel  day creative: 10/1/2024
    version: 2023.2  IntelliJ IDEA
    author: Tô Hoàng Thành
*/
/*
   *class description:
        Class TaiKhoan đại diện cho thông tin tài khoản bao gồm tên tài khoản và mật khẩu.
*/

package entity;

import java.util.Objects;

public class TaiKhoan {
    private String tenTaiKhoan;
    private String matKhau;

    // Constructor with validation
    public TaiKhoan(String tenTaiKhoan, String matKhau) {
        setTenTaiKhoan(tenTaiKhoan);
        setMatKhau(matKhau);
    }

    // Getter and setter for tenTaiKhoan
    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        if (tenTaiKhoan == null || tenTaiKhoan.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên tài khoản không được rỗng");
        }
        this.tenTaiKhoan = tenTaiKhoan.trim();
    }

    // Getter and setter for matKhau
    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        if (matKhau == null || matKhau.trim().isEmpty()) {
            throw new IllegalArgumentException("Mật khẩu không được rỗng");
        }

        // Improved password validation (minimum 8 characters, at least one letter and one number)
        if (!matKhau.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
            throw new IllegalArgumentException("Mật khẩu phải có ít nhất 8 ký tự, bao gồm cả chữ và số");
        }
        this.matKhau = matKhau;
    }

    // Override hashCode() and equals() based on tenTaiKhoan
    @Override
    public int hashCode() {
        return Objects.hash(tenTaiKhoan);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TaiKhoan taiKhoan = (TaiKhoan) obj;
        return Objects.equals(tenTaiKhoan, taiKhoan.tenTaiKhoan);
    }

    // Override toString() to hide the password and only show the username
    @Override
    public String toString() {
        return "TaiKhoan { " +
                "tenTaiKhoan='" + tenTaiKhoan + '\'' +
                ", matKhau='********'" + // Password is hidden for security
                " }";
    }
}
