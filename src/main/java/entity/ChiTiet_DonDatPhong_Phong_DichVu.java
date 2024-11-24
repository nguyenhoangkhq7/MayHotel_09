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
import java.time.LocalDateTime;
import java.util.Objects;

public class ChiTiet_DonDatPhong_Phong_DichVu {
    private String maCT_DDP_P_DV; // Mã chi tiết đơn đặt phòng - dịch vụ
    private int soLuongDat;       // Số lượng dịch vụ đặt
    private LocalDateTime ngayTao;    // Ngày tạo đơn đặt dịch vụ
    private DichVu dichVu;        // Đối tượng dịch vụ
    private ChiTiet_DonDatPhong_Phong cT_DDP_P; // Đối tượng chi tiết đơn đặt phòng - phòng
    private String moTa;          // Mô tả

    // Constructor
    public ChiTiet_DonDatPhong_Phong_DichVu(String maCT_DDP_P_DV, int soLuongDat, LocalDateTime ngayTao, DichVu dichVu, ChiTiet_DonDatPhong_Phong cT_DDP_P, String moTa) {
        setMaCT_DDP_P_DV(maCT_DDP_P_DV);
        setSoLuongDat(soLuongDat);
        this.ngayTao = ngayTao;
        setDichVu(dichVu);
        setCT_DDP_P(cT_DDP_P);
        this.moTa = moTa;
    }

    public ChiTiet_DonDatPhong_Phong_DichVu(String maCT_DDP_P_DV) {
        this.maCT_DDP_P_DV = maCT_DDP_P_DV;
    }

    public ChiTiet_DonDatPhong_Phong_DichVu() {
    }

    // Getters and Setters
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

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }

    public ChiTiet_DonDatPhong_Phong getcT_DDP_P() {
        return cT_DDP_P;
    }

    public void setcT_DDP_P(ChiTiet_DonDatPhong_Phong cT_DDP_P) {
        this.cT_DDP_P = cT_DDP_P;
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

    public ChiTiet_DonDatPhong_Phong getCT_DDP_P() {
        return cT_DDP_P;
    }

    public void setCT_DDP_P(ChiTiet_DonDatPhong_Phong cT_DDP_P) {
        if (cT_DDP_P == null) {
            throw new IllegalArgumentException("Chi tiết đơn đặt phòng - phòng không được null");
        }
        this.cT_DDP_P = cT_DDP_P;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    // Override equals and hashCode
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

    // Override toString method
    @Override
    public String toString() {
        return "ChiTiet_DonDatPhong_Phong_DichVu{" +
                "maCT_DDP_P_DV='" + maCT_DDP_P_DV + '\'' +
                ", soLuongDat=" + soLuongDat +
                ", ngayTao=" + ngayTao +
                ", dichVu=" + dichVu +
                ", cT_DDP_P=" + cT_DDP_P +
                ", moTa='" + moTa + '\'' +
                '}';
    }
}
