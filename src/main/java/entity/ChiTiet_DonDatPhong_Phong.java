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
import java.util.Objects;

public class ChiTiet_DonDatPhong_Phong {
    private String maCT_DDP_P;
    private DonDatPhong donDatPhong;
    private Phong phong;
    private LocalDate ngayNhanPhong;
    private LocalDate ngayTraPhong; // Đã đổi từ ngayTra sang ngayTraPhong
    private boolean laPhongChuyen;
    private double chietKhau;

    // Constructor đầy đủ tham số
    public ChiTiet_DonDatPhong_Phong(String maCT_DDP_P, DonDatPhong donDatPhong, Phong phong, LocalDate ngayNhanPhong, LocalDate ngayTraPhong, boolean laPhongChuyen, double chietKhau) {
        this.maCT_DDP_P = maCT_DDP_P;
        this.donDatPhong = donDatPhong;
        this.phong = phong;
        this.ngayTraPhong = ngayTraPhong;
        this.laPhongChuyen = laPhongChuyen;
        this.ngayNhanPhong = ngayNhanPhong;
        this.chietKhau = chietKhau;
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

    public ChiTiet_DonDatPhong_Phong(String maCT_DDP_P) {
        this.maCT_DDP_P = maCT_DDP_P;
    }

    public ChiTiet_DonDatPhong_Phong() {
    }

    // Getter và Setter
    public String getMaCT_DDP_P() {
        return maCT_DDP_P;
    }

    public void setMaCT_DDP_P(String maCT_DDP_P) {
        if (maCT_DDP_P.equals("")) {
            throw new IllegalArgumentException("Mã đặt phòng không được rỗng");
        }
        this.maCT_DDP_P = maCT_DDP_P;
    }

    public LocalDate getNgayTraPhong() {
        return ngayTraPhong;
    }

    public void setNgayTraPhong(LocalDate ngayTraPhong) {
        this.ngayTraPhong = ngayTraPhong;
    }

    public DonDatPhong getDonDatPhong() {
        return donDatPhong;
    }

    public void setDonDatPhong(DonDatPhong donDatPhong) {
        if (donDatPhong == null) {
            throw new IllegalArgumentException("Đơn đặt phòng không được null");
        }
        this.donDatPhong = donDatPhong;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        if (phong == null) {
            throw new IllegalArgumentException("Phòng không được null");
        }
        this.phong = phong;
    }

    public LocalDate getNgayNhanPhong() {
        return ngayNhanPhong;
    }

    public void setNgayNhanPhong(LocalDate ngayNhanPhong) {
        this.ngayNhanPhong = ngayNhanPhong;
    }

    // Override phương thức equals và hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChiTiet_DonDatPhong_Phong that)) return false;
        return Objects.equals(maCT_DDP_P, that.maCT_DDP_P);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maCT_DDP_P);
    }

    // Override phương thức toString
    @Override
    public String toString() {
        return "ChiTiet_DonDatPhong_Phong{" +
                "maCT_DDP_P='" + maCT_DDP_P + '\'' +
                ", donDatPhong=" + donDatPhong +
                ", phong=" + phong +
                ", ngayNhanPhong=" + ngayNhanPhong +
                ", ngayTraPhong=" + ngayTraPhong +
                ", laPhongChuyen=" + laPhongChuyen +
                ", chietKhau=" + chietKhau +
                '}';
    }
}
