/*
    *MayHotel  day creative: 11/4/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here   
     */


package bus;

import dal.KhachHangDAL;
import dal.PhongDAL;
import entity.DonDatPhong;
import entity.KhachHang;
import entity.LoaiKhachHang;
import entity.Phong;
import view.App;

import javax.swing.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

public class ThemDonDatPhongBUS {


    public ThemDonDatPhongBUS() {
    }
    public KhachHang getKhachHangTheoSoDienThoai(String soDienThoai) {
        if (soDienThoai == null || soDienThoai.isEmpty()) {
            throw new IllegalArgumentException("Số điện thoại không được để trống.");
        }
        // Gọi DAL để lấy thông tin khách hàng
        KhachHangDAL khachHangDAL = new KhachHangDAL();
        return khachHangDAL.getKhachHangTheoSoDienThoai(soDienThoai);
    }
    public LocalDateTime convertDateToLocalDateTime(Date date, int hour, int minute) {
        return LocalDateTime.of(date.getYear(),date.getMonth(),date.getDay(), hour, minute);
    }
    public long tongThoiGianO(Date dateCheckin, Date dateCheckout) {
        ThemDonDatPhongBUS themDonDatPhongBUS = new ThemDonDatPhongBUS();

        LocalDateTime tgCheckin = null,tgCheckout= null;

        if(dateCheckin!=null) {
            tgCheckin = themDonDatPhongBUS.convertDateToLocalDateTime(dateCheckin, 14, 00);
        } else {
            JOptionPane.showMessageDialog(null, "Hãy nhập thời gian checkin", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        if(dateCheckout!=null) {
            tgCheckout = themDonDatPhongBUS.convertDateToLocalDateTime(dateCheckout, 12, 00);
        } else {
            JOptionPane.showMessageDialog(null, "Hãy nhập thời gian checkin", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        long tongThoiGianO = ChronoUnit.DAYS.between(tgCheckin, tgCheckout);
        return tongThoiGianO;
    }
    public boolean themDonDatPhong(Date dateCheckin, Date dateCheckout, String loaiPhong, String soPhong, String soDienThoai, String hoTen, String cccd, String email, String moTa, String phuongThucThanhToan, boolean trangThaiDatCoc, ArrayList<Phong> danhSacPhong) {
        DonDatPhongBUS donDatPhongBUS = new DonDatPhongBUS();
        KhachHangDAL khachHangDAL = new KhachHangDAL();
        KhachHangBUS khachHangBUS = new KhachHangBUS();
        LocalDateTime localDateTimeCheckin = null,localDateTimeCheckout = null;

        if(dateCheckin!=null) {
            localDateTimeCheckin = convertDateToLocalDateTime(dateCheckin, 14, 00);
            //                tgCheckin = jdcCheckIn.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        } else {
            JOptionPane.showMessageDialog(null, "Hãy nhập thời gian checkin", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        if(dateCheckout!=null) {
            localDateTimeCheckout = convertDateToLocalDateTime(dateCheckout, 12, 00);
            //                tgCheckout = jdcCheckout.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        } else {
            JOptionPane.showMessageDialog(null, "Hãy nhập thời gian checkin", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }

        // 2. Kiểm tra thông tin
        if ( soDienThoai.isEmpty() || hoTen.isEmpty() || cccd.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin khách hàng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // 3. Tạo đối tượng đơn đặt phòng
        DonDatPhong ddp = new DonDatPhong();
        ddp.setMaDon(donDatPhongBUS.generateOrderCode());
        ddp.setNgayTao(LocalDateTime.now());
        ddp.setTrangThaiDonDatPhong("Đặt trước");
        ddp.setPhuongThucThanhToan(phuongThucThanhToan);
        ddp.setTrangThaiDatCoc(trangThaiDatCoc);
        ddp.setNhanVien(App.nhanVienDangTruc);
        ddp.setMoTa(moTa!=null ? moTa : "");
        ddp.setNgayNhanPhong(localDateTimeCheckin);
        ddp.setNgayTraPhong(localDateTimeCheckout);

//            kiểm tra nếu khách hàng chưa tồn tại thì tạo khách hàng mới
        if(khachHangDAL.checkKhachHangTonTaiTheoSDT(soDienThoai)) {
            ddp.setKhachHang(khachHangDAL.getKhachHangTheoSoDienThoai(soDienThoai));
        } else {
            KhachHang khachHang = new KhachHang();

            khachHang.setMaKH(khachHangBUS.generateCustomerCode());
            khachHang.setHoTen(hoTen);
            khachHang.setSoDienThoai(soDienThoai);
            khachHang.setSoCanCuoc(cccd);
            khachHang.setEmail(email);
            khachHang.setTienTichLuy(0); // Thiết lập tiền tích lũy ban đầu
            khachHang.setLoaiKhachHang(LoaiKhachHang.NGUOIMOI); // Hoặc giá trị phù hợp
            ddp.setKhachHang(khachHang);
            khachHangDAL.themKhachHang(khachHang);
        }

//        tính tổng tiền phòng
        Duration tongThoiGianO = Duration.between(localDateTimeCheckin,localDateTimeCheckout);
        double tongTien = tinhTongTienPhong(danhSacPhong, tongThoiGianO);

        ddp.setTongTien(tongTien); // Tạm thời gán 0, cần tính toán tổng tiền
        return false;
    }
    public double tinhTongTienPhong(ArrayList<Phong> dsPhong, Duration thoiGianO) {
        PhongDAL phongDAL = new PhongDAL();
        double tongTien = 0;
        for(Phong phong : dsPhong) {
            double donGia = phongDAL.getLoaiPhongTheoMaPhong(phong.getMaPhong()).getDonGia();
             tongTien += (donGia * thoiGianO.toHours() + donGia * thoiGianO.toMinutes()*1.0/60);
        }
        return tongTien;
    }
    public Phong getPhongTheoTenPhong(String tenPhong) {
        PhongDAL phongDAL = new PhongDAL();
        return phongDAL.getPhongTheoTenPhong(tenPhong);
    }
}
