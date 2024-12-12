/*
    *MayHotel  day creative: 11/4/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here   
     */


package bus;

import dal.*;
import entity.*;

import javax.swing.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class ThemDonDatPhongBUS {

    public ThemDonDatPhongBUS() {
    }
    public String formatHoTen(String hoTen) {
        String[] words = hoTen.trim().toLowerCase().split("\\s+");
        StringBuilder formattedName = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                formattedName.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
            }
        }
        return formattedName.toString().trim();
    }

    public boolean validateFields(String sdt, String hoTen, String email, String cccd) {
        // Kiểm tra số điện thoại
        String regSDT = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        if (!sdt.matches(regSDT)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra email
        String regEmail = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        if (!email.matches(regEmail)) {
            JOptionPane.showMessageDialog(null, "Email phải thuộc dạng @gmail.com!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra họ tên
        String regHoTen = "^[\\p{L}]+(\\s[\\p{L}]+)*\\s[\\p{L}]+(\\s[\\p{L}]+)?$";
        if (!hoTen.matches(regHoTen)) {
            JOptionPane.showMessageDialog(null, "Họ tên không hợp lệ! Phải viết hoa chữ cái đầu và không chứa số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra CCCD (12 số)
        String regCCCD = "^\\d{12}$";  // 12 chữ số
        if (!cccd.matches(regCCCD)) {
            JOptionPane.showMessageDialog(null, "CCCD phải là 12 số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Tất cả các kiểm tra đều hợp lệ
        return true;
    }
    public ChiTiet_DonDatPhong_Phong_DichVu taoCTDDPPDV(int soLuongDat, LocalDateTime tgSuDung,DichVu dichVu, DonDatPhong donDatPhong, Phong p, String moTa) {
        return new ChiTiet_DonDatPhong_Phong_DichVu(soLuongDat, tgSuDung, dichVu, donDatPhong, p, moTa);
    }
    public ChiTiet_DonDatPhong_Phong taoCTDDPP(DonDatPhong donDatPhong, Phong phong, LocalDateTime ngayNhanPhong, LocalDateTime ngayTraPhong) {
        return new ChiTiet_DonDatPhong_Phong(donDatPhong, phong, ngayNhanPhong, ngayTraPhong, false, 0.0);
    }
    public DonDatPhong taoDonDatPhong (NhanVien nhanVien, String sdt, String hoTen, String email, String soCanCuoc, double tongTien,String moTa, String phuongThucThanhToan, boolean trangThaiDatCoc, LocalDateTime ngayNhanPhong, LocalDateTime ngayTraPhong) {
        // check và tạo khách hàng
        KhachHang khachHang = taoKhachHang(sdt, hoTen, email, soCanCuoc);
        if(khachHang == null) {
            return null;
        }

        if(nhanVien == null) {
            JOptionPane.showMessageDialog(null, "Lỗi khi không có nhân viên", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (ngayNhanPhong == null || ngayTraPhong == null) {
            JOptionPane.showMessageDialog(null, "Ngày nhận phòng hoặc ngày trả phòng không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (ngayNhanPhong.isAfter(ngayTraPhong)) {
            JOptionPane.showMessageDialog(null, "Ngày nhận phòng phải trước ngày trả phòng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return new DonDatPhong(new QuanLyDonDatPhongBUS().generateOrderCode(), LocalDateTime.now(), phuongThucThanhToan, "Đã đặt trước", trangThaiDatCoc, nhanVien, khachHang, tongTien, moTa, ngayTraPhong, ngayNhanPhong);
    }
    public KhachHang taoKhachHang(String sdt, String hoTen, String email, String soCanCuoc) {
        if(!validateFields(sdt, hoTen, email, soCanCuoc)) {
            return null;
        }
        KhachHangDAL khachHangDAL = new KhachHangDAL();
        KhachHang khachHang = khachHangDAL.getKhachHangTheoSoDienThoai(sdt);
        if(khachHang!=null) {
            return khachHang;
        } else {
            KhachHangBUS khachHangBUS = new KhachHangBUS();
            return new KhachHang(khachHangBUS.generateCustomerCode(), hoTen, sdt, 0, soCanCuoc, email, LoaiKhachHang.NGUOIMOI);
        }
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
        if(date==null) return null;
        // Chuyển đổi Date sang LocalDateTime
        Instant instant = date.toInstant(); // Lấy thời điểm (instant) từ Date
        ZoneId zoneId = ZoneId.systemDefault(); // Sử dụng múi giờ hệ thống
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

        // Tạo LocalDateTime mới với giờ và phút được chỉ định
        return LocalDateTime.of(
                localDateTime.getYear(),
                localDateTime.getMonth(),
                localDateTime.getDayOfMonth(),
                hour,
                minute
        );
    }

    public boolean datPhong(String phuongThucThanhToan, boolean isDatCoc, double tongTien,DonDatPhong donDatPhong, KhachHang khachHang, ArrayList<Phong> dsPhong, ArrayList<ChiTiet_DonDatPhong_Phong> dsCTDDPP, ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> dsCTDDPPDV) {
        if(donDatPhong!=null && khachHang!=null && !dsPhong.isEmpty() && !dsCTDDPP.isEmpty()) {
            // check
            if(phuongThucThanhToan.equals("--Chọn phương thức thanh toán--")) {
                JOptionPane.showMessageDialog(null, "Hãy chọn phương thức thanh toán", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // update lại đơn
            donDatPhong.setPhuongThucThanhToan(phuongThucThanhToan);
            donDatPhong.setTrangThaiDatCoc(isDatCoc);
            donDatPhong.setTongTien(tongTien);

            // thêm khách hàng vào cơ sở dữ liệu
            new DonDatPhongDAL().themDonDatPhong(donDatPhong);
            KhachHangDAL khachHangDAL = new KhachHangDAL();
            if(!khachHangDAL.checkKhachHangTonTaiTheoSDT(khachHang.getSoDienThoai())) {
                khachHangDAL.themKhachHang(khachHang);
            }

            // thêm chi tiết vào cơ sở dữ liệu
            ChiTiet_DonDatPhong_PhongDAL chiTietDonDatPhongPhongDAL = new ChiTiet_DonDatPhong_PhongDAL();
            for(ChiTiet_DonDatPhong_Phong ct : dsCTDDPP) {
                chiTietDonDatPhongPhongDAL.themChiTiet(ct);
            }

            // thêm chi tiết vào cơ sở dữ liệu
            ChiTiet_DonDatPhong_Phong_DichVuDAL chiTietDonDatPhongPhongDichVuDAL = new ChiTiet_DonDatPhong_Phong_DichVuDAL();
            for(ChiTiet_DonDatPhong_Phong_DichVu ct : dsCTDDPPDV) {
                chiTietDonDatPhongPhongDichVuDAL.themChiTiet(ct);
            }

            return true;
        }
        return false;
    }
}
