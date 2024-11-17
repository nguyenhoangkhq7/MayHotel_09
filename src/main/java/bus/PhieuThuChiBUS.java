package bus;

import java.text.DecimalFormat;
import java.time.LocalDate;
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


public class PhieuThuChiBUS {
	
	    private PhieuThuChiDAL phieuThuChiDAL = new PhieuThuChiDAL();
	   private DecimalFormat df = new DecimalFormat("#,##0.00"); // Định dạng: 222221313.13
	    public boolean themPhieuThuChi(PhieuThuChi phieuChi) {
	        return phieuThuChiDAL.themPhieuThuChi(phieuChi);
	    }
	

	public Object[][] layDuLieuBangTim(String maPhieu) {
	    // Lấy danh sách phiếu thu chi từ DAL
		 PhieuThuChiDAL phieuThuChiDAL = new PhieuThuChiDAL(); // Khởi tạo lớp DAL
		 PhieuThuChi phieuThuChi = new PhieuThuChi();
		   phieuThuChi = phieuThuChiDAL.getPhieuThuChiTheoMa(maPhieu); // Gọi phương thức lấy phiếu thu chi
		if (phieuThuChi != null) {
			 Object[][] data = new Object[1][8]; // Chỉ có một phiếu thu chi được tìm thấy
	           data[0][0] = phieuThuChi.getMaPhieu();
	           data[0][1] = phieuThuChi.getNhanVien().getHoten(); // Lấy tên nhân viên
	           data[0][2] = phieuThuChi.getNgayLap();
	           data[0][3] = phieuThuChi.getLoaiPhieu();
	           data[0][4] = phieuThuChi.isConHoatDong() ? "Còn hoạt động" : "Đã hủy";
	           data[0][5] = phieuThuChi.getPhuongThucThanhToan();

	        // Gán giá trị đã định dạng vào data
	           data[0][6] = df.format(phieuThuChi.getSoTien());
	           data[0][7] = phieuThuChi.getMoTa();
	           
		    return data; // Trả về mảng dữ liệu
		}
		else {
			return null;
		}
	}
	public Object[][] layDuLieuBang(LocalDate startDate, LocalDate endDate) {
	    // Lấy danh sách phiếu thu chi từ DAL
		
	    ArrayList<PhieuThuChi> dsPhieuThuChi = new PhieuThuChiDAL().getPhieuThuChiByDateRange(startDate, endDate);

	    // Khởi tạo mảng hai chiều với kích thước bằng với số lượng phiếu thu chi
	    Object[][] data = new Object[dsPhieuThuChi.size()][8]; // 8 cột tương ứng với các thuộc tính của PhieuThuChi

	    for (int i = 0; i < dsPhieuThuChi.size(); i++) {
	        PhieuThuChi phieuThuChi = dsPhieuThuChi.get(i);
	        data[i][0] = phieuThuChi.getMaPhieu();          // Mã phiếu thu chi
	        data[i][1] = phieuThuChi.getNhanVien().getHoten(); // Tên nhân viên
	        data[i][2] = phieuThuChi.getNgayLap();          // Ngày tạo
	        data[i][3] = phieuThuChi.getLoaiPhieu();        // Loại phiếu
	        data[i][4] = phieuThuChi.isConHoatDong()? "Còn hoạt động" : "Đã hủy";     // Trạng thái
	        data[i][5] = phieuThuChi.getPhuongThucThanhToan();       // Phương thức
	      

		        // Gán giá trị đã định dạng vào data
		           data[i][6] = df.format(phieuThuChi.getSoTien());
	        data[i][7] = phieuThuChi.getMoTa();             // Mô tả
	    }

	    return data; // Trả về mảng dữ liệu
	}
}
