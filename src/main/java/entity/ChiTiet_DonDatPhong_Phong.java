/*
    *MayHotel  day creative: 10/1/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang
*/

/*
   *class description:
        write description right here
*/

package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class ChiTiet_DonDatPhong_Phong {
    private DonDatPhong donDatPhong;
    private Phong phong;
    private LocalDateTime ngayNhanPhong;
    private LocalDateTime ngayTraPhong;
    private boolean laPhongChuyen;
    private double chietKhau;

    // Constructor đầy đủ tham số
    public ChiTiet_DonDatPhong_Phong(DonDatPhong donDatPhong, Phong phong, LocalDateTime ngayNhanPhong, LocalDateTime ngayTraPhong, boolean laPhongChuyen, double chietKhau) {
        this.donDatPhong = donDatPhong;
        this.phong = phong;
        this.ngayTraPhong = ngayTraPhong;
        this.laPhongChuyen = laPhongChuyen;
        this.ngayNhanPhong = ngayNhanPhong;
        this.chietKhau = chietKhau;
    }

    public ChiTiet_DonDatPhong_Phong() {
    }

    public DonDatPhong getDonDatPhong() {
        return donDatPhong;
    }

    public void setDonDatPhong(DonDatPhong donDatPhong) {
        this.donDatPhong = donDatPhong;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    public LocalDateTime getNgayNhanPhong() {
        return ngayNhanPhong;
    }

    public void setNgayNhanPhong(LocalDateTime ngayNhanPhong) {
        this.ngayNhanPhong = ngayNhanPhong;
    }

    public LocalDateTime getNgayTraPhong() {
        return ngayTraPhong;
    }

    public void setNgayTraPhong(LocalDateTime ngayTraPhong) {
        this.ngayTraPhong = ngayTraPhong;
    }

    public boolean isLaPhongChuyen() {
        return laPhongChuyen;
    }

    public void setLaPhongChuyen(boolean laPhongChuyen) {
        this.laPhongChuyen = laPhongChuyen;
    }

    public double getChietKhau() {
        return chietKhau;
    }

    public void setChietKhau(double chietKhau) {
        this.chietKhau = chietKhau;
    }

    // Override phương thức equals và hashCode


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChiTiet_DonDatPhong_Phong that)) return false;
        return Objects.equals(donDatPhong, that.donDatPhong) && Objects.equals(phong, that.phong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(donDatPhong, phong);
    }

    // Override phương thức toString

    @Override
    public String toString() {
        return "ChiTiet_DonDatPhong_Phong{" +
                "donDatPhong=" + donDatPhong +
                ", phong=" + phong +
                ", ngayNhanPhong=" + ngayNhanPhong +
                ", ngayTraPhong=" + ngayTraPhong +
                ", laPhongChuyen=" + laPhongChuyen +
                ", chietKhau=" + chietKhau +
                '}';
    }
}
