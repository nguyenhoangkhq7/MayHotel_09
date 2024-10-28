/*
    *MayHotel  day creative: 10/1/2024
    version: 2023.2  IntelliJ IDEA
    author: Đặng Thái Bảo  */
    /*
       *class description:
            write description right here   
     */


package entity;

import java.time.LocalDate;
import java.util.Objects;

public class ChiTiet_DonDatPhong_Phong_DichVu {
    private String maCT_DDP_P_DV;  // Mã chi tiết đơn đặt phòng - dịch vụ
    private int soLuongDat;  // Số lượng dịch vụ đặt
    private LocalDate ngayTao;  // Ngày tạo đơn đặt dịch vụ
    private DichVu dichVu;  // Đối tượng dịch vụ
    private DonDatPhong donDatPhong;  // Đối tượng đơn đặt phòng
    private String moTa;
    // Constructor 
    public ChiTiet_DonDatPhong_Phong_DichVu(String maCT_DDP_DV, int soLuongDat, LocalDate ngayTao, DichVu dichVu, DonDatPhong donDatPhong,String moTa) {
        setMaCT_DDP_P_DV(maCT_DDP_DV);
        setSoLuongDat(soLuongDat);
        this.ngayTao = ngayTao;
        setDichVu(dichVu);
        setDonDatPhong(donDatPhong);
        this.moTa = moTa;
    }

    public ChiTiet_DonDatPhong_Phong_DichVu(String maCT_DDP_DV) {
		this.maCT_DDP_P_DV = maCT_DDP_DV;
	}

    public ChiTiet_DonDatPhong_Phong_DichVu() {
    }

    // Getter và Setter cho các thuộc tính
    public String getMaCT_DDP_P_DV() {
        return maCT_DDP_P_DV;
    }

    public void setMaCT_DDP_P_DV(String maCT_DDP_P_DV) {
        if (maCT_DDP_P_DV == null || maCT_DDP_P_DV.isEmpty()) {
            throw new IllegalArgumentException("Mã chi tiết đơn đặt phòng - dịch vụ không được rỗng");
        }
        this.maCT_DDP_P_DV = maCT_DDP_P_DV;
    }

    public int getSoLuongDat() {
        return soLuongDat;
    }

    public void setSoLuongDat(int soLuongDat) {
        if (soLuongDat <= 0) {
            throw new IllegalArgumentException("Số lượng đặt phải lớn hơn 0");
        }
        this.soLuongDat = soLuongDat;
    }

    public LocalDate getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

    public DichVu getDichVu() {
        return dichVu;
    }

    public void setDichVu(DichVu dichVu) {
        if (dichVu == null) {
            throw new IllegalArgumentException("Dịch vụ không được null");
        }
        this.dichVu = dichVu;
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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    // Override phương thức equals và hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChiTiet_DonDatPhong_Phong_DichVu that)) return false;
        return Objects.equals(maCT_DDP_P_DV, that.maCT_DDP_P_DV);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maCT_DDP_P_DV);
    }

    // Override phương thức toString

    @Override
    public String toString() {
        return "ChiTiet_DonDatPhong_Phong_DichVu{" +
                "maCT_DDP_P_DV='" + maCT_DDP_P_DV + '\'' +
                ", soLuongDat=" + soLuongDat +
                ", ngayTao=" + ngayTao +
                ", dichVu=" + dichVu +
                ", donDatPhong=" + donDatPhong +
                ", moTa='" + moTa + '\'' +
                '}';
    }
}
