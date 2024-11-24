package bus;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

import dal.HoaDonDAL;
import entity.HoaDon;

public class HoaDonBUS {
    private HoaDonDAL hoaDonDAL = new HoaDonDAL();
    private DecimalFormat df = new DecimalFormat("#,##0.00"); // Định dạng: 222221313.13

    // Thêm hóa đơn
    public boolean themHoaDon(HoaDon hoaDon) {
        return hoaDonDAL.themHoaDon(hoaDon);
    }

    // Lấy dữ liệu hóa đơn theo mã
    public Object[][] layDuLieuBangTim(String maHoaDon) {
        HoaDon hoaDon = hoaDonDAL.getHoaDonTheoMa(maHoaDon);
        if (hoaDon != null) {
            Object[][] data = new Object[1][7]; // 7 cột tương ứng với các thuộc tính của HoaDon
            data[0][0] = hoaDon.getMaHoaDon(); // Mã hóa đơn
            data[0][1] = hoaDon.getNhanVien().getHoten(); // Tên nhân viên
            data[0][2] = hoaDon.getNgayTao(); // Ngày tạo
            data[0][3] = hoaDon.getTrangThai() ? "Đã thanh toán" : "Chưa thanh toán"; // Trạng thái
            data[0][4] = df.format(hoaDon.getThanhTien()); // Thành tiền
            data[0][5] = hoaDon.getKhuyenMai() != null ? hoaDon.getKhuyenMai().getTenKhuyenMai() : "Không có"; // Tên khuyến mãi
            data[0][6] = hoaDon.getDonDatPhong() != null ? hoaDon.getDonDatPhong().getMaDon() : "Không có"; // Mã đơn đặt phòng
            return data;
        } else {
            return null; // Không tìm thấy hóa đơn
        }
    }

    // Lấy dữ liệu hóa đơn theo khoảng thời gian
    public Object[][] layDuLieuBang(LocalDateTime startDate, LocalDateTime endDate) {
        ArrayList<HoaDon> dsHoaDon = hoaDonDAL.getHoaDonByDateRange(startDate, endDate);

        // Khởi tạo mảng hai chiều với kích thước bằng với số lượng hóa đơn
        Object[][] data = new Object[dsHoaDon.size()][7]; // 7 cột tương ứng với các thuộc tính của HoaDon

        for (int i = 0; i < dsHoaDon.size(); i++) {
            HoaDon hoaDon = dsHoaDon.get(i);
            data[i][0] = hoaDon.getMaHoaDon(); // Mã hóa đơn
            data[i][1] = hoaDon.getNhanVien().getHoten(); // Tên nhân viên
            data[i][2] = hoaDon.getNgayTao(); // Ngày tạo
            data[i][3] = hoaDon.getTrangThai() ? "Đã thanh toán" : "Chưa thanh toán"; // Trạng thái
            data[i][4] = df.format(hoaDon.getThanhTien()); // Thành tiền
            data[i][5] = hoaDon.getKhuyenMai() != null ? hoaDon.getKhuyenMai().getTenKhuyenMai() : "Không có"; // Tên khuyến mãi
            data[i][6] = hoaDon.getDonDatPhong() != null ? hoaDon.getDonDatPhong().getMaDon() : "Không có"; // Mã đơn đặt phòng
        }

        return data; // Trả về mảng dữ liệu
    }
}
