package bus;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
	
	public double tinhTienPhong(ChiTiet_DonDatPhong_Phong ctdddpp) {
	    // Lấy thông tin giá phòng và chiết khấu
	    double donGia = ctdddpp.getPhong().getLoaiPhong().getDonGia();
	    double chietKhauCTDDPP = ctdddpp.getChietKhau(); // Chiết khấu cụ thể cho chi tiết đơn đặt phòng

	    // Tính số ngày ở, đảm bảo tối thiểu là 1
	    long soNgayO = Math.max(ChronoUnit.DAYS.between(ctdddpp.getNgayNhanPhong(), ctdddpp.getNgayTraPhong()), 1);

	    // Tính tiền phòng
	    double tienPhong = donGia * soNgayO; // Tiền phòng chưa chiết khấu
	    tienPhong -= tienPhong * (chietKhauCTDDPP / 100); // Áp dụng chiết khấu của chi tiết đơn đặt phòng

	    return tienPhong;
	}
	
	public Object[][] layDuLieuBang(LocalDateTime startDate, LocalDateTime endDate) {
	    // Lấy danh sách hóa đơn trong khoảng thời gian
	    ArrayList<HoaDon> dsHoaDon = new HoaDonDAL().getHoaDonByDateRange(startDate, endDate);

	    // Khởi tạo các biến để lưu trữ số liệu
	    int soHoaDon = 0; // Số hóa đơn có trạng thái true
	    int soHoaDonHuy = 0; // Số hóa đơn có trạng thái false
	    double tongDoanhThu = 0; // Tổng doanh thu
	    double tongDoanhThuRong = 0; // Tổng doanh thu ròng

	    double doanhThuPhong = 0; // Doanh thu phòng
	    double tienNganHang = 0;
	    double tienMat = 0;
	    double dichVu = 0; // Doanh thu từ dịch vụ
	    double khuyenMai = 0; // Tổng khuyến mãi
	    double thucThu = 0; // Thực thu

	    // Biến đếm số phòng
	    int soPhongDuocThue = 0;
	    

        // Biến đếm cho từng loại phòng
        int soLoaiDeluxKing = 0;
        int soLoaiDeluxTwin = 0;
        int soLoaiDeluxTriple = 0;
        int soLoaiExecutiveSuite = 0;
        int soLoaiExecutiveTwin = 0;
        int soLoaiSuiteFamily = 0;

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

	    	    double giaTriKhuyenMai = 0; // Giá trị mặc định nếu không có khuyến mãi
	    	    if (hoaDon.getKhuyenMai() != null) {
	    	        giaTriKhuyenMai = hoaDon.getKhuyenMai().getGiaTri();
	    	    }

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
                for (ChiTiet_DonDatPhong_Phong ct : chiTietList) {
                	doanhThuPhong += tinhTienPhong(ct);
                	
                	// Cập nhật số lượng theo loại phòng
                    String loaiPhong = ct.getPhong().getLoaiPhong().getTenLoaiPhong(); // Giả sử có phương thức getTen()
                    switch (loaiPhong) {
                        case "Delux King":
                            soLoaiDeluxKing++;
                            break;
                        case "Delux TWin":
                            soLoaiDeluxTwin++;
                            break;
                        case "Delux Triple":
                            soLoaiDeluxTriple++;
                            break;
                        case "Executive Suite":
                            soLoaiExecutiveSuite++;
                            break;
                        case "Executive TWin":
                            soLoaiExecutiveTwin++;
                            break;
                        case "Suite Family":
                            soLoaiSuiteFamily++;
                            break;
                        default:
                            break;
                    }
                    
                    ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> danhSachDichVu = new ChiTiet_DonDatPhong_Phong_DichVuDAL().getDSChiTietDonDatPhongPhongDichVuTheoMaDonDatPhongMaPhong(ct.getDonDatPhong().getMaDon(), ct.getPhong().getMaPhong());;
                    
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
	        {"4", "Doanh thu phòng", df.format(doanhThuPhong)},
	        {"5", "Dịch vụ", df.format(dichVu)}, 
	        {"6", "Khoản thu khác", df.format(khoanThuKhac)},
	        {"7", "Ngân hàng", df.format(tienNganHang)},
	        {"8", "Tiền mặt", df.format(tienMat)},
	        {"9", "Khuyến mãi", df.format(khuyenMai)},
	        {"10", "Khoản chi khác", df.format(khoanChiKhac)},
	        {"11", "Tổng doanh thu ròng", df.format(tongDoanhThuRong)},
	        {"12", "Thực thu", df.format(thucThu)},
	        {"13", "Số phòng được thuê", String.valueOf(soPhongDuocThue)}, // Số phòng được thuê
	        {"14", "Loại Delux King", String.valueOf(soLoaiDeluxKing)},
            {"15", "Loại Delux Twin", String.valueOf(soLoaiDeluxTwin)},
            {"16", "Loại Delux Triple", String.valueOf(soLoaiDeluxTriple)},
            {"17", "Loại Executive Suite", String.valueOf(soLoaiExecutiveSuite)},
            {"18", "Loại Executive Twin", String.valueOf(soLoaiExecutiveTwin)},
            {"19", "Loại Suite Family", String.valueOf(soLoaiSuiteFamily)}
	    };
	}



	   public static void main(String[] args) {
	        // Tạo đối tượng của BangBaoCaoBUS
	        BangBaoCaoBUS bangBaoCaoBUS = new BangBaoCaoBUS();
	        
	        // Đặt khoảng thời gian cho báo cáo
		   LocalDateTime startDate = LocalDateTime.of(2024, 10, 10, 5,3,2); // Ngày bắt đầu
		   LocalDateTime endDate = LocalDateTime.of(2024, 10, 10,5,3,2); // Ngày kết thúc
	        
	        // Gọi phương thức để lấy dữ liệu báo cáo
	        Object[][] duLieuBang = bangBaoCaoBUS.layDuLieuBang(startDate, endDate);
	        
	        // In ra kết quả
	        System.out.println("Bảng báo cáo từ " + startDate + " đến " + endDate + ":");
	        for (Object[] row : duLieuBang) {
	            System.out.printf("%s - %s: %s%n", row[0], row[1], row[2]);
	        }
	    }
}
