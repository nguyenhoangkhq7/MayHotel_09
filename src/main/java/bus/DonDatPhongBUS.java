package bus;

import dal.ChiTiet_DonDatPhong_PhongDAL;
import dal.DonDatPhongDAL;
import entity.ChiTiet_DonDatPhong_Phong;
import entity.DonDatPhong;
import entity.Phong;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DonDatPhongBUS {
    public String generateOrderCode() {
        String lastOrder = new DonDatPhongDAL().getLastDDP(); // Lấy mã đơn cuối cùng
        int newOrderNumber = 1; // Mặc định bắt đầu từ 1 nếu không có mã đơn nào

        if (lastOrder != null) {
            // Tách phần số từ mã đơn
            String numberPart = lastOrder.substring(3); // Bỏ qua "DDP"
            newOrderNumber = Integer.parseInt(numberPart) + 1; // Tăng số lên 1
        }

        // Tạo mã đơn mới theo định dạng "DDPXXXXXX"
        return String.format("DDP%06d", newOrderNumber); // Định dạng thành 6 chữ số
    }

    public boolean checkPhongDaThem(ArrayList<Phong> dsPhong, Phong phong) {
        if(dsPhong.contains(phong)) return true;
        return false;
    }

    public boolean checkCoPhongChuyen(DonDatPhong donDatPhong) {
        ChiTiet_DonDatPhong_PhongDAL chiTietDonDatPhongPhongDAL = new ChiTiet_DonDatPhong_PhongDAL();
        ArrayList<ChiTiet_DonDatPhong_Phong> dsChiTietDDPPhong = chiTietDonDatPhongPhongDAL.getChiTietDonDatPhongPhongTheoMaDDP(donDatPhong.getMaDon());
        for(ChiTiet_DonDatPhong_Phong ct : dsChiTietDDPPhong) {
            if(ct.isLaPhongChuyen()) {
                return true;
            }
        }
        return false;
    }
    public boolean checkSapDenHanCheckin (DonDatPhong donDatPhong) {
        LocalDateTime hienTai = LocalDateTime.now();
        LocalDateTime thoiGianCheckin = donDatPhong.getNgayNhanPhong();
        Duration duration = Duration.between(hienTai, thoiGianCheckin);
        long hoursDifference = duration.toHours();
        // Kiểm tra nếu thời gian chênh lệch nhỏ hơn 3 giờ và chưa quá hạn checkin
        return hoursDifference < 3 && hoursDifference >= 0;
    }
    public boolean checkQuaHanCheckin (DonDatPhong donDatPhong) {
        LocalDateTime hienTai = LocalDateTime.now();
        LocalDateTime thoiGianCheckin = donDatPhong.getNgayNhanPhong();
        Duration duration = Duration.between(hienTai, thoiGianCheckin);
        long hoursDifference = duration.toHours();
        // Kiểm tra nếu thời gian chênh lệch nhỏ hơn 3 giờ và chưa quá hạn checkin
        return hoursDifference < 0;
    }
    public boolean checkQuaHanCheckout(DonDatPhong donDatPhong) {
        LocalDateTime hienTai = LocalDateTime.now();
        LocalDateTime thoiGianCheckout = donDatPhong.getNgayTraPhong();
        if(donDatPhong.getTrangThaiDonDatPhong().equals("Đang ở")) {
            long hoursDifference = Duration.between(hienTai, thoiGianCheckout).toHours();
            return hoursDifference < 0;
        }
        return false;
    }
    public static void main(String[] args) {
//        System.out.println(new DonDatPhongBUS().checkSapDenHanCheckin(new DonDatPhongDAL().getDonDatPhongTheoMa("DDP000001")));
//        LocalDateTime hienTai = LocalDateTime.of(2024,11,26,6,0);
//        LocalDateTime ngayNhanPhong = LocalDateTime.of(2024,11,26,4,0);
//        System.out.println(Duration.between(hienTai, ngayNhanPhong).toHours());
        System.out.println(new DonDatPhongBUS().generateOrderCode());
    }
}
