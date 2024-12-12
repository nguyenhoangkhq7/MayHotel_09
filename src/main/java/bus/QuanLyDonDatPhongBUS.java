package bus;

import dal.ChiTiet_DonDatPhong_PhongDAL;
import dal.DonDatPhongDAL;
import entity.ChiTiet_DonDatPhong_Phong;
import entity.DonDatPhong;
import entity.Phong;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class QuanLyDonDatPhongBUS {
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
        return dsPhong.contains(phong);
    }


    public boolean checkCoPhongChuyen(DonDatPhong donDatPhong) {
        ChiTiet_DonDatPhong_PhongDAL chiTietDonDatPhongPhongDAL = new ChiTiet_DonDatPhong_PhongDAL();
        ArrayList<ChiTiet_DonDatPhong_Phong> dsChiTietDDPPhong = chiTietDonDatPhongPhongDAL.getChiTietDonDatPhongPhongTheoMaDDP(donDatPhong.getMaDon());
        return dsChiTietDDPPhong.stream().anyMatch(ChiTiet_DonDatPhong_Phong::isLaPhongChuyen);
    }

    public boolean checkSapDenHanCheckin(DonDatPhong donDatPhong) {
        // kiểm tra nếu không phải trạng thái là đã đặt trước thì trả về false
        if(!donDatPhong.getTrangThaiDonDatPhong().equals("Đã đặt trước")) return false;
        LocalDateTime hienTai = LocalDateTime.now();
        LocalDateTime thoiGianCheckin = donDatPhong.getNgayNhanPhong();
        // Kiểm tra null
        if (thoiGianCheckin == null) return false;
        Duration duration = Duration.between(hienTai, thoiGianCheckin);
        long hoursDifference = duration.toHours();
        return hoursDifference < 2 && hoursDifference >= 0;
    }

    public boolean checkQuaHanCheckin(DonDatPhong donDatPhong) {
        // Kiểm tra trạng thái đơn đặt phòng
        if(donDatPhong.getTrangThaiDonDatPhong().equals("Đang ở")) return false;

        LocalDateTime hienTai = LocalDateTime.now();
        LocalDateTime thoiGianCheckin = donDatPhong.getNgayNhanPhong();

        // Kiểm tra null cho thời gian checkin
        if (thoiGianCheckin == null) return false;

        // Tính toán sự khác biệt giữa thời gian hiện tại và thời gian checkin
        Duration duration = Duration.between(hienTai, thoiGianCheckin);
        long minutesDifference = duration.toMinutes(); // Lấy sự khác biệt tính theo phút

        // Kiểm tra nếu sự khác biệt nhỏ hơn 0 và dưới 60 phút (1 giờ)
        return minutesDifference < 0 && Math.abs(minutesDifference) < 60;
    }


    public boolean checkQuaHanCheckout(DonDatPhong donDatPhong) {
        LocalDateTime hienTai = LocalDateTime.now();
        LocalDateTime thoiGianCheckout = donDatPhong.getNgayTraPhong();

        // Kiểm tra nếu thời gian checkout là null
        if (thoiGianCheckout == null) return false;

        // Kiểm tra trạng thái "Đang ở" để xem khách hàng vẫn chưa checkout
        if (donDatPhong.getTrangThaiDonDatPhong().equals("Đang ở")) {
            // Tính sự khác biệt giữa thời gian hiện tại và thời gian checkout theo phút
            long minutesDifference = Duration.between(hienTai, thoiGianCheckout).toMinutes();

            // Kiểm tra nếu thời gian checkout quá hạn hơn 15 phút
            return minutesDifference < -15; // Nếu khác biệt âm và lớn hơn 15 phút
        }

        return false;
    }


    public static void main(String[] args) {
//        System.out.println(new DonDatPhongBUS().checkSapDenHanCheckin(new DonDatPhongDAL().getDonDatPhongTheoMa("DDP000001")));
//        LocalDateTime hienTai = LocalDateTime.of(2024,11,26,6,0);
//        LocalDateTime ngayNhanPhong = LocalDateTime.of(2024,11,26,4,0);
//        System.out.println(Duration.between(hienTai, ngayNhanPhong).toHours());
        System.out.println(new QuanLyDonDatPhongBUS().generateOrderCode());
    }
}
