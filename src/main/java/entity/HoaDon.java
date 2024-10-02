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

public class HoaDon {
    private String maHoaDon;
    private String ngayTao;
    private Boolean trangThai;
    private double thanhTien;
    private KhachHang khachHang;
    private NhanVien nhanVien;
    private DichVu dichVu;
    private KhuyenMai khuyenMai;
    private double diemSuDung;
    private double tienKhachDua;

    public HoaDon(String maHoaDon, String ngayTao, Boolean trangThai, double thanhTien,
                  KhachHang khachHang, NhanVien nhanVien, DichVu dichVu, KhuyenMai khuyenMai,
                  double diemSuDung, double tienKhachDua) {
        setMaHoaDon(maHoaDon);
        setNgayTao(ngayTao);
        setTrangThai(trangThai);
        setThanhTien(thanhTien);
        setKhachHang(khachHang);
        setNhanVien(nhanVien);
        setDichVu(dichVu);
        setKhuyenMai(khuyenMai);
        setDiemSuDung(diemSuDung);
        setTienKhachDua(tienKhachDua);
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public DichVu getDichVu() {
        return dichVu;
    }

    public KhuyenMai getKhuyenMai() {
        return khuyenMai;
    }

    public double getDiemSuDung() {
        return diemSuDung;
    }

    public double getTienKhachDua() {
        return tienKhachDua;
    }

    public void setMaHoaDon(String maHoaDon) {
        if (maHoaDon == null || maHoaDon.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã hóa đơn không được để trống.");
        }
        this.maHoaDon = maHoaDon;
    }

    public void setNgayTao(String ngayTao) {
        if (ngayTao == null || ngayTao.trim().isEmpty()) {
            throw new IllegalArgumentException("Ngày tạo không được để trống.");
        }
        this.ngayTao = ngayTao;
    }

    public void setTrangThai(Boolean trangThai) {
        if (trangThai == null) {
            throw new IllegalArgumentException("Trạng thái không được null.");
        }
        this.trangThai = trangThai;
    }

    public void setThanhTien(double thanhTien) {
        if (thanhTien < 0) {
            throw new IllegalArgumentException("Thành tiền phải là số không âm.");
        }
        this.thanhTien = thanhTien;
    }

    public void setKhachHang(KhachHang khachHang) {
        if (khachHang == null) {
            throw new IllegalArgumentException("Khách hàng không được để trống.");
        }
        this.khachHang = khachHang;
    }

    public void setNhanVien(NhanVien nhanVien) {
        if (nhanVien == null) {
            throw new IllegalArgumentException("Nhân viên không được để trống.");
        }
        this.nhanVien = nhanVien;
    }

    public void setDichVu(DichVu dichVu) {
        if (dichVu == null) {
            throw new IllegalArgumentException("Dịch vụ không được để trống.");
        }
        this.dichVu = dichVu;
    }

    public void setKhuyenMai(KhuyenMai khuyenMai) {
        if (khuyenMai == null) {
            throw new IllegalArgumentException("Khuyến mãi không được để trống.");
        }
        this.khuyenMai = khuyenMai;
    }

    public void setDiemSuDung(double diemSuDung) {
        if (diemSuDung < 0) {
            throw new IllegalArgumentException("Điểm sử dụng không được âm.");
        }
        this.diemSuDung = diemSuDung;
    }

    public void setTienKhachDua(double tienKhachDua) {
        if (tienKhachDua < 0) {
            throw new IllegalArgumentException("Tiền khách đưa không được âm.");
        }
        this.tienKhachDua = tienKhachDua;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maHoaDon);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HoaDon hoaDon = (HoaDon) obj;
        return Objects.equals(maHoaDon, hoaDon.maHoaDon);
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "maHoaDon='" + maHoaDon + '\'' +
                ", ngayTao='" + ngayTao + '\'' +
                ", trangThai=" + (trangThai ? "Còn hoạt động" : "Đã hủy") +
                ", thanhTien=" + thanhTien +
                ", khachHang=" + khachHang +
                ", nhanVien=" + nhanVien +
                ", dichVu=" + dichVu +
                ", khuyenMai=" + khuyenMai +
                ", diemSuDung=" + diemSuDung +
                ", tienKhachDua=" + tienKhachDua +
                '}';
    }
}

