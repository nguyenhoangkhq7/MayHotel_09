package entity;

import java.util.Objects;

public class LoaiPhong {
    private String maLoaiPhong;    // Mã loại phòng
    private String tenLoaiPhong;   // Tên loại phòng
    private int sucChua;           // Sức chứa
    private double donGia;         // Đơn giá

    // Constructor
    public LoaiPhong(String maLoaiPhong, String tenLoaiPhong, int sucChua, double donGia) {
        setMaLoaiPhong(maLoaiPhong);
        this.tenLoaiPhong = tenLoaiPhong;
        setSucChua(sucChua);
        setDonGia(donGia);
    }

    public LoaiPhong(String maLoaiPhong) {
        this(maLoaiPhong, null, 0, 0.0);
    }

    // Getter and Setter methods
    public String getMaLoaiPhong() {
        return maLoaiPhong;
    }

    public void setMaLoaiPhong(String maLoaiPhong) {
        if (maLoaiPhong == null || maLoaiPhong.isEmpty()) {
            throw new IllegalArgumentException("Mã loại phòng không được rỗng");
        }
        this.maLoaiPhong = maLoaiPhong;
    }

    public String getTenLoaiPhong() {
        return tenLoaiPhong;
    }

    public void setTenLoaiPhong(String tenLoaiPhong) {
        this.tenLoaiPhong = tenLoaiPhong;
    }

    public int getSucChua() {
        return sucChua;
    }

    public void setSucChua(int sucChua) {
        if (sucChua <= 0) {
            throw new IllegalArgumentException("Sức chứa phải lớn hơn 0");
        }
        this.sucChua = sucChua;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        if (donGia <= 0) {
            throw new IllegalArgumentException("Đơn giá phải lớn hơn 0");
        }
        this.donGia = donGia;
    }

    // hashCode and equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoaiPhong)) return false;
        LoaiPhong loaiPhong = (LoaiPhong) o;
        return Objects.equals(maLoaiPhong, loaiPhong.maLoaiPhong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maLoaiPhong);
    }

    // toString
    @Override
    public String toString() {
        return "LoaiPhong{" +
                "maLoaiPhong='" + maLoaiPhong + '\'' +
                ", tenLoaiPhong='" + tenLoaiPhong + '\'' +
                ", sucChua=" + sucChua +
                ", donGia=" + donGia +
                '}';
    }
}
