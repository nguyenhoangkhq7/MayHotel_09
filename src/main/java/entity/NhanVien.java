/*
    *MayHotel  day creative: 9/26/2024
    version: 2023.2  IntelliJ IDEA
    author: Tô Hoàng Thành  */
    /*
       *class description:
            write description right here   
     */


package entity;


import java.util.Objects;

public class NhanVien {

    private String maNV;
    private String hoten;
    private String soDienThoai;
    private String cancuoc;
    private boolean conHoatDong;
    private String email;
    private String diaChi;
    private Integer vaiTro;

    // Constructor với đầy đủ tham số
    public NhanVien(String maNV, String hoten, String soDienThoai, String cancuoc, boolean conHoatDong,
                    String email, String diaChi, Integer vaiTro) {
        setMaNV(maNV); // Sử dụng setter để đảm bảo tính hợp lệ
        setHoten(hoten);
        setSoDienThoai(soDienThoai);
        setCancuoc(cancuoc);
        this.conHoatDong = conHoatDong;
        setEmail(email);
        setDiaChi(diaChi);
        setVaiTro(vaiTro);
    }

    // Constructor mặc định
    public NhanVien() {}

    // Getter và Setter cho mã nhân viên
    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV; // Ràng buộc có thể được thêm nếu cần
    }

    // Getter và Setter cho họ tên
    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        if (hoten == null || hoten.isEmpty()) {
            throw new IllegalArgumentException("Họ tên không được rỗng");
        }
        this.hoten = hoten;
    }

    // Getter và Setter cho số điện thoại
    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        if (!soDienThoai.matches("^0\\d{9}$")) {
            throw new IllegalArgumentException("Số điện thoại không hợp lệ");
        }
        this.soDienThoai = soDienThoai;
    }

    // Getter và Setter cho căn cước công dân
    public String getCancuoc() {
        return cancuoc;
    }

    public void setCancuoc(String cancuoc) {
        if (!cancuoc.matches("\\d{12}")) {
            throw new IllegalArgumentException("Căn cước công dân không hợp lệ");
        }
        this.cancuoc = cancuoc;
    }

    // Getter cho trạng thái hoạt động
    public boolean isConHoatDong() {
        return conHoatDong;
    }

    // Setter cho trạng thái hoạt động
    public void setConHoatDong(boolean conHoatDong) {
        this.conHoatDong = conHoatDong;
    }

    // Getter cho trạng thái hoạt động
    public String getTrangThaiHoatDong() {
        return conHoatDong ? "Hoạt động" : "Không còn hoạt động";
    }

    // Getter và Setter cho email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[\\w]+@gmail\\.com$")) {
            throw new IllegalArgumentException("Email không hợp lệ");
        }
        this.email = email;
    }

    // Getter và Setter cho địa chỉ
    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    // Getter và Setter cho vai trò
    public Integer getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(Integer vaiTro) {
        if (vaiTro != 1 && vaiTro != 2) {
            throw new IllegalArgumentException("Vai trò không hợp lệ");
        }
        this.vaiTro = vaiTro;
    }

    // Phương thức hashCode() chỉ sử dụng maNV
    @Override
    public int hashCode() {
        return Objects.hash(maNV);
    }

    // Phương thức equals() để so sánh đối tượng dựa trên maNV
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // So sánh địa chỉ tham chiếu
        if (obj == null || getClass() != obj.getClass()) return false; // Kiểm tra kiểu dữ liệu
        NhanVien nhanVien = (NhanVien) obj; // Ép kiểu đối tượng
        return Objects.equals(maNV, nhanVien.maNV); // So sánh mã nhân viên
    }

    // Phương thức toString() để trả về thông tin nhân viên
    @Override
    public String toString() {
        return "NhanVien{" +
                "maNV='" + maNV + '\'' +
                ", hoten='" + hoten + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", cancuoc='" + cancuoc + '\'' +
                ", conHoatDong=" + conHoatDong +
                ", email='" + email + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", vaiTro=" + vaiTro +
                '}';
    }
}

