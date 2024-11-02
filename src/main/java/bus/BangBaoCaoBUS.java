package bus;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import dal.ChiTiet_DonDatPhong_PhongDAL;
import dal.ChiTiet_DonDatPhong_Phong_DichVuDAL;
import dal.HoaDonDAL;
import dal.PhieuThuChiDAL;
import entity.ChiTiet_DonDatPhong_Phong;
import entity.ChiTiet_DonDatPhong_Phong_DichVu;
import entity.HoaDon;
import entity.PhieuThuChi;

public class BangBaoCaoBUS {
	
	public Object[][] layDuLieuBang(LocalDate startDate, LocalDate endDate) {
	    // Lấy danh sách hóa đơn trong khoảng thời gian
	    ArrayList<HoaDon> dsHoaDon = new HoaDonDAL().getHoaDonByDateRange(startDate, endDate);

	    // Khởi tạo các biến để lưu trữ số liệu
	    int soHoaDon = 0; // Số hóa đơn có trạng thái true
	    int soHoaDonHuy = 0; // Số hóa đơn có trạng thái false
	    double tongDoanhThu = 0; // Tổng doanh thu
	    double tongDoanhThuRong = 0; // Tổng doanh thu ròng

	    double vat = 0; // VAT (nếu cần tính toán riêng)
	    double doanhThuPhong = 0; // Doanh thu phòng
	    double tienNganHang = 0;
	    double tienMat = 0;
	    double dichVu = 0; // Doanh thu từ dịch vụ
	    double khuyenMai = 0; // Tổng khuyến mãi
	    double thucThu = 0; // Thực thu

	    // Biến đếm số phòng
	    int soPhongDuocThue = 0;

	    // Tính toán khoản thu và khoản chi từ phiếu thu chi
	    double khoanThu = 0; // Tổng tiền phiếu có loại phiếu là Thu
	    double khoanChiKhac = 0; // Tổng tiền phiếu có loại phiếu là Chi
	    
	    // Lấy danh sách phiếu thu chi trong khoảng thời gian
	    ArrayList<PhieuThuChi> dsPhieuThuChi = new PhieuThuChiDAL().getPhieuThuChiByDateRange(startDate, endDate);
	    for (PhieuThuChi phieu : dsPhieuThuChi) {
	        if (phieu.getLoaiPhieu().equalsIgnoreCase("Thu")) {
	            khoanThu += phieu.getSoTien(); // Cộng tiền phiếu thu
	        } else if (phieu.getLoaiPhieu().equalsIgnoreCase("Chi")) {
	            khoanChiKhac += phieu.getSoTien(); // Cộng tiền phiếu chi
	        }
	    }
	    
	    // Lặp qua danh sách hóa đơn để tính toán
	    for (HoaDon hoaDon : dsHoaDon) {
	        if (hoaDon.getTrangThai()) { // Kiểm tra trạng thái hóa đơn
	            soHoaDon++; // Tăng số hóa đơn
	            tongDoanhThuRong += hoaDon.getThanhTien(); // Giả sử getThanhTien() trả về tổng tiền của hóa đơn

	            double giaTriKhuyenMai = hoaDon.getKhuyenMai().getGiaTri(); // Lấy giá trị khuyến mãi
	            double tienKhuyenMai = hoaDon.getThanhTien() / (1 - (giaTriKhuyenMai / 100)) - hoaDon.getThanhTien(); 
	            khuyenMai += tienKhuyenMai;

	            // Kiểm tra phương thức thanh toán
	            if (hoaDon.getDonDatPhong().getPhuongThucThanhToan().equals("Credit Card")) {
	                tienNganHang += hoaDon.getThanhTien();
	            } else {
	                tienMat += hoaDon.getThanhTien();
	            }

	            // Tính số phòng được thuê từ chi tiết đơn đặt phòng
                ChiTiet_DonDatPhong_PhongDAL chiTietDAL = new ChiTiet_DonDatPhong_PhongDAL();
                ArrayList<ChiTiet_DonDatPhong_Phong> chiTietList = chiTietDAL.getChiTietDonDatPhongPhongTheoMaDDP(hoaDon.getDonDatPhong().getMaDon());
                soPhongDuocThue += chiTietList.size();
                ///
                ChiTiet_DonDatPhong_Phong_DichVuDAL dichVuDAL = new ChiTiet_DonDatPhong_Phong_DichVuDAL();
                for (ChiTiet_DonDatPhong_Phong chiTiet : chiTietList) {
                    ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> danhSachDichVu = dichVuDAL.getDSChiTietDonDatPhongPhongDichVuTheoMa(chiTiet.getMaCT_DDP_P());
                    
                    for (ChiTiet_DonDatPhong_Phong_DichVu ctDV : danhSachDichVu) {
                        double tienDichVu = ctDV.getSoLuongDat() * ctDV.getDichVu().getDonGia();
                        dichVu += tienDichVu; // Cộng dồn vào doanh thu từ dịch vụ
                    }
                }
	        
	        } else {
	            soHoaDonHuy++; // Tăng số hóa đơn hủy
	        }
	    }

	    // Tính khoản thu khác
	    double khoanThuKhac = khoanThu - tongDoanhThuRong;
	    thucThu = tongDoanhThuRong + khoanThuKhac - khoanChiKhac;
	    tongDoanhThu = tongDoanhThuRong + khuyenMai;

	    // Làm tròn đến hàng chục
	    tongDoanhThu = Math.round(tongDoanhThu / 100) * 100;
	    tienNganHang = Math.round(tienNganHang / 100) * 100;
	    tienMat = Math.round(tienMat / 100) * 100;
	    khuyenMai = Math.round(khuyenMai / 100) * 100;
	    thucThu = Math.round(thucThu / 100) * 100;

	    DecimalFormat df = new DecimalFormat("#,###"); // Định dạng số với dấu phân cách

	    return new Object[][] {
	        {"1", "Số hóa đơn", String.valueOf(soHoaDon)},
	        {"2", "Số hóa đơn hủy", String.valueOf(soHoaDonHuy)},
	        {"3", "Tổng doanh thu (đã bao gồm VAT)", df.format(tongDoanhThu)},
	        {"4", "VAT", String.valueOf(vat)},
	        {"5", "Doanh thu phòng", String.valueOf(doanhThuPhong)},
	        {"6", "Dịch vụ", df.format(dichVu)}, 
	        {"7", "Khoản thu khác", df.format(khoanThuKhac)},
	        {"8", "Ngân hàng", df.format(tienNganHang)},
	        {"9", "Tiền mặt", df.format(tienMat)},
	        {"10", "Khuyến mãi", df.format(khuyenMai)},
	        {"11", "Khoản chi khác", df.format(khoanChiKhac)},
	        {"12", "Tổng doanh thu ròng", df.format(tongDoanhThuRong)},
	        {"13", "Thực thu", df.format(thucThu)},
	        {"14", "Số phòng được thuê", String.valueOf(soPhongDuocThue)}, // Số phòng được thuê
	        {"15", "Loại 1", "0"},
	        {"16", "Loại 2", "0"},
	        {"17", "Loại 3", "0"},
	        {"18", "Loại 4", "0"},
	        {"19", "Loại 5", "0"},
	        {"20", "Loại 6", "0"}
	    };
	}



	   public static void main(String[] args) {
	        // Tạo đối tượng của BangBaoCaoBUS
	        BangBaoCaoBUS bangBaoCaoBUS = new BangBaoCaoBUS();
	        
	        // Đặt khoảng thời gian cho báo cáo
	        LocalDate startDate = LocalDate.of(2024, 10, 12); // Thay đổi theo ngày bắt đầu
	        LocalDate endDate = LocalDate.of(2024, 10, 12); // Thay đổi theo ngày kết thúc
	        
	        // Gọi phương thức để lấy dữ liệu báo cáo
	        Object[][] duLieuBang = bangBaoCaoBUS.layDuLieuBang(startDate, endDate);
	        
	        // In ra kết quả
	        System.out.println("Bảng báo cáo từ " + startDate + " đến " + endDate + ":");
	        for (Object[] row : duLieuBang) {
	            System.out.printf("%s - %s: %s%n", row[0], row[1], row[2]);
	        }
	    }
}
