package bus;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import entity.ChiTiet_DonDatPhong_Phong;
import entity.DonDatPhong;
import entity.HoaDon;
import entity.KhuyenMai;
import entity.PhieuThuChi;
import entity.Phong;
import dal.ChiTiet_DonDatPhong_PhongDAL;
import dal.HoaDonDAL;
import dal.PhieuThuChiDAL;


public class ThongKeDoanhThuBUS {
	
	   // Hàm lấy doanh thu theo ngày
	  // Hàm lấy doanh thu theo ngày
    public double layDoanhThuTheoNgay(LocalDate localDate) {
        // Tạo đối tượng LocalDateTime cho ngày bắt đầu và kết thúc trong ngày
        LocalDateTime startDate = localDate.atStartOfDay();  // Bắt đầu ngày (00:00:00)
        LocalDateTime endDate = startDate.plusDays(1).minusNanos(1);  // Kết thúc ngày (23:59:59.999999999)

        // Lấy danh sách hóa đơn trong ngày
        ArrayList<HoaDon> dsHoaDon = new HoaDonDAL().getHoaDonByDateRange(startDate, endDate);

        // Tính tổng doanh thu
        double doanhThu = 0.0;
        for (HoaDon hoaDon : dsHoaDon) {
            doanhThu += hoaDon.getThanhTien();  // Cộng doanh thu của từng hóa đơn
        }

        return doanhThu;  // Trả về tổng doanh thu trong ngày
    }
    
    public double layGiaTriCacMuc(int loaiMuc, LocalDate startDate, LocalDate endDate) {
        double ketQua = 0.0;

        // Chuyển đổi LocalDate sang LocalDateTime để truy vấn theo khoảng thời gian
        LocalDateTime startDateTime = startDate.atStartOfDay();  // 00:00:00 của ngày bắt đầu
        LocalDateTime endDateTime = endDate.atStartOfDay().plusDays(1).minusNanos(1);  // 23:59:59.999 của ngày kết thúc

        // Lấy danh sách hóa đơn trong khoảng thời gian
        ArrayList<HoaDon> dsHoaDon = new HoaDonDAL().getHoaDonByDateRange(startDateTime, endDateTime);
        ArrayList<PhieuThuChi> dsPhieuThuChi = new PhieuThuChiDAL().getPhieuThuChiByDateRange(startDateTime, endDateTime);
        // Xử lý các loại mục
        switch (loaiMuc) {
            case 1: // Số lượng hóa đơn còn hoạt động
                for (HoaDon hoaDon : dsHoaDon) {
                    if (hoaDon.getTrangThai()) {  // Đếm số hóa đơn còn hoạt động
                        ketQua++;
                    }
                }
                break;

            case 2: // Số lượng hóa đơn đã hủy
                for (HoaDon hoaDon : dsHoaDon) {
                    if (!hoaDon.getTrangThai()) {  // Đếm số hóa đơn đã hủy
                        ketQua++;
                    }
                }
                break;

            case 3: // Tổng số tiền từ hóa đơn còn hoạt động
                for (HoaDon hoaDon : dsHoaDon) {
                    if (hoaDon.getTrangThai()) {  // Nếu hóa đơn còn hoạt động
                        ketQua += hoaDon.getThanhTien();  // Cộng dồn giá trị thanh toán
                    }
                }
                break;

            case 4: // Tổng số tiền đã khuyến mãi
                for (HoaDon hoaDon : dsHoaDon) {
                    if (hoaDon.getTrangThai() && hoaDon.getKhuyenMai() != null) {  // Hóa đơn còn hoạt động và có khuyến mãi
                    	KhuyenMai khuyenMai = hoaDon.getKhuyenMai();
                        ketQua += hoaDon.getThanhTien() / (1 - khuyenMai.getGiaTri()) ;  // Cộng dồn giá trị thanh toán đã khuyến mãi
                    }
                }
                break;

            case 5: // Tổng doanh thu từ hóa đơn có phương thức thanh toán là "Tiền mặt"
                for (HoaDon hoaDon : dsHoaDon) {
                    if (hoaDon.getTrangThai()) {  // Kiểm tra nếu hóa đơn còn hoạt động
                        // Kiểm tra xem hóa đơn có liên kết với DonDatPhong và phương thức thanh toán là "Tiền mặt"
                        DonDatPhong donDatPhong = hoaDon.getDonDatPhong();
                        if (donDatPhong != null && "Tiền mặt".equals(donDatPhong.getPhuongThucThanhToan())) {
                            ketQua += hoaDon.getThanhTien();  // Cộng dồn tổng tiền của hóa đơn
                        }
                    }
                }
                break;

            case 6: // Tổng doanh thu từ hóa đơn có phương thức thanh toán không phải "Tiền mặt"
                for (HoaDon hoaDon : dsHoaDon) {
                    if (hoaDon.getTrangThai()) {  // Kiểm tra nếu hóa đơn còn hoạt động
                        // Kiểm tra xem hóa đơn có liên kết với DonDatPhong và phương thức thanh toán không phải là "Tiền mặt"
                        DonDatPhong donDatPhong = hoaDon.getDonDatPhong();
                        if (donDatPhong != null && !"Tiền mặt".equals(donDatPhong.getPhuongThucThanhToan())) {
                            ketQua += hoaDon.getThanhTien();  // Cộng dồn tổng tiền của hóa đơn
                        }
                    }
                }
                break;
            case 7: // Khoản thu khác
                for (PhieuThuChi phieu : dsPhieuThuChi) {
                    if ("Phiếu Thu".equals(phieu.getLoaiPhieu()) && phieu.isConHoatDong()) {  // Phiếu thu còn hoạt động
                        ketQua += phieu.getSoTien();  // Tính tổng các khoản thu khác
                    }
                }
                break;

            case 8: // Khoản chi khác
                for (PhieuThuChi phieu : dsPhieuThuChi) {
                    if ("Phiếu Chi".equals(phieu.getLoaiPhieu()) && phieu.isConHoatDong()) {  // Phiếu chi còn hoạt động
                        ketQua += phieu.getSoTien();  // Tính tổng các khoản chi khác
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("Loại mục không hợp lệ: " + loaiMuc);
        }

        return ketQua;
    }
    public List<Map.Entry<String, Integer>> layTopPhongTheoSoLuong(LocalDate startDate, LocalDate endDate) {
        // Bản đồ lưu số lượng đơn đặt phòng cho mỗi mã phòng
        Map<String, Integer> phongCountMap = new HashMap<>();
        
        // Chuyển ngày bắt đầu và kết thúc thành LocalDateTime để so sánh với thời gian của hóa đơn
        LocalDateTime startDateTime = startDate.atStartOfDay();  // 00:00:00 của ngày bắt đầu
        LocalDateTime endDateTime = endDate.atStartOfDay().plusDays(1).minusNanos(1);  // 23:59:59.999 của ngày kết thúc
        
        // Lấy danh sách các hóa đơn trong khoảng thời gian
        ArrayList<HoaDon> dsHoaDon = new HoaDonDAL().getHoaDonByDateRange(startDateTime, endDateTime);

        // Duyệt qua tất cả các hóa đơn trong danh sách
        for (HoaDon hoaDon : dsHoaDon) {
            // Kiểm tra nếu hóa đơn còn hoạt động (trạng thái hợp lệ)
            if (hoaDon.getTrangThai()) {
                DonDatPhong donDatPhong = hoaDon.getDonDatPhong();
                if (donDatPhong != null) {
                    // Lấy danh sách chi tiết đơn đặt phòng của đơn đặt phòng này
                    ArrayList<ChiTiet_DonDatPhong_Phong> chiTietList = new ChiTiet_DonDatPhong_PhongDAL().getChiTietDonDatPhongPhongTheoMaDDP(donDatPhong.getMaDon());

                    // Duyệt qua từng chi tiết đơn đặt phòng để đếm số lần xuất hiện của mỗi mã phòng
                    for (ChiTiet_DonDatPhong_Phong chiTiet : chiTietList) {
                        Phong phong = chiTiet.getPhong();
                        if (phong != null) {
                            String maPhong = phong.getMaPhong();  // Mã phòng

                            // Cập nhật số lượng đơn đặt phòng cho mã phòng
                            phongCountMap.put(maPhong, phongCountMap.getOrDefault(maPhong, 0) + 1);
                        }
                    }
                }
            }
        }

        // Sắp xếp các mã phòng theo số lượng đơn đặt phòng giảm dần và lấy các mã phòng với số lượt cao nhất
        return phongCountMap.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))  // Sắp xếp theo giá trị giảm dần
                .collect(Collectors.toList());  // Chuyển đổi thành danh sách các mã phòng và số lượt đặt
    }

 // Hàm lấy số lượng phòng theo hạng
    public List<String> layGiaTriPhongTheoHang(int hang, LocalDate startDate, LocalDate endDate) {
        List<Map.Entry<String, Integer>> sortedPhongList = layTopPhongTheoSoLuong(startDate, endDate);

        // Nếu danh sách rỗng hoặc hạng không hợp lệ
        if (sortedPhongList.isEmpty() || hang <= 0 || hang > sortedPhongList.size()) {
            // Trả về giá trị mặc định
            List<String> defaultResult = new ArrayList<>();
            defaultResult.add("P00"); // Mã phòng mặc định
            defaultResult.add("0");   // Số lượt đặt phòng mặc định
            return defaultResult;
        }

        // Lấy mã phòng theo hạng
        Map.Entry<String, Integer> selectedEntry = sortedPhongList.get(hang - 1); // Lấy hạng thứ `hang`
        List<String> result = new ArrayList<>();
        result.add(selectedEntry.getKey());  // Mã phòng
        result.add(String.valueOf(selectedEntry.getValue()));  // Số lượt đặt phòng

        return result;
    }

    
    public static void main(String[] args) {
        ThongKeDoanhThuBUS thongKeDoanhThuBUS = new ThongKeDoanhThuBUS();
        
       
            LocalDate startDate = LocalDate.of(2024, 11, 1);
            LocalDate endDate = LocalDate.of(2024, 11, 30);

            // Lấy mã phòng và số lượt đặt phòng của phòng có số lượt cao nhất (hạng 1)
            List<String> topPhongHang1 = thongKeDoanhThuBUS.layGiaTriPhongTheoHang(1, startDate, endDate);
            System.out.println("Phòng hạng 1: " + topPhongHang1);
            
            // Lấy mã phòng và số lượt đặt phòng của phòng có số lượt thứ 2 (hạng 2)
            List<String> topPhongHang2 = thongKeDoanhThuBUS.layGiaTriPhongTheoHang(2, startDate, endDate);
            System.out.println("Phòng hạng 2: " + topPhongHang2);
        }
}
