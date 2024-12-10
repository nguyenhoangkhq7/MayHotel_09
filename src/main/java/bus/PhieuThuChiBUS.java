package bus;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	public boolean them(PhieuThuChi phieuThuChi) {
		PhieuThuChiDAL dal = new PhieuThuChiDAL();
		return dal.themPhieuThuChi(phieuThuChi);
	}
	public boolean xoaPhieuThuChi(String maPhieuThuChi) {
		PhieuThuChiDAL phieuThuChiDAL = new PhieuThuChiDAL();
		return phieuThuChiDAL.xoaPhieuThuChi(maPhieuThuChi);
	}
	public Object[][] layDuLieuBang(LocalDateTime startDate, LocalDateTime endDate,
									String maPhieu, String trangThai, String loaiPhieu, String phuongThuc) {
		ArrayList<PhieuThuChi> dsPhieuThuChi = new PhieuThuChiDAL().getPhieuThuChiByDateRange(startDate, endDate);
		ArrayList<PhieuThuChi> ketQua = new ArrayList<>();
		for (PhieuThuChi phieu : dsPhieuThuChi) {
			if (maPhieu != null && !maPhieu.trim().isEmpty() && !phieu.getMaPhieu().contains(maPhieu)) {
				continue;
			}
			String trangThaiPhieu = phieu.isConHoatDong() ? "Còn hoạt động" : "Đã hủy";
			if (trangThai != null && !trangThai.trim().isEmpty() && !trangThaiPhieu.equalsIgnoreCase(trangThai)) {
				continue;
			}
			if (loaiPhieu != null && !loaiPhieu.trim().isEmpty() && !phieu.getLoaiPhieu().equalsIgnoreCase(loaiPhieu)) {
				continue;
			}
			if (phuongThuc != null && !phuongThuc.trim().isEmpty() && !phieu.getPhuongThucThanhToan().equalsIgnoreCase(phuongThuc)) {
				continue;}
			ketQua.add(phieu);
		}
		Object[][] data = new Object[ketQua.size()][8]; // 8 cột tương ứng với các thuộc tính
		for (int i = 0; i < ketQua.size(); i++) {
			PhieuThuChi phieu = ketQua.get(i);
			data[i][0] = phieu.getMaPhieu();                                // Mã phiếu thu chi
			data[i][1] = phieu.getNhanVien().getMaNV();                    // Tên nhân viên
			data[i][2] = phieu.getNgayLap();                                // Ngày tạo
			data[i][3] = phieu.getLoaiPhieu();                              // Loại phiếu
			data[i][4] = phieu.isConHoatDong() ? "Còn hoạt động" : "Đã hủy"; // Trạng thái
			data[i][5] = phieu.getPhuongThucThanhToan();                    // Phương thức
			data[i][6] = new DecimalFormat("#,###").format(phieu.getSoTien()); // Số tiền (định dạng)
			data[i][7] = phieu.getMoTa();                                   // Mô tả
		}
		return data; // Trả về mảng dữ liệu
	}

	public static void main(String[] args) {
		PhieuThuChiBUS phieuThuChiBUS = new PhieuThuChiBUS();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime startDate = LocalDateTime.parse("2024-12-09 00:00", formatter);
		LocalDateTime endDate = LocalDateTime.parse("2024-12-09 23:59", formatter);
		String maPhieu = "";  // Mã phiếu
		String trangThai = "";  // Trạng thái
		String loaiPhieu = "";  // Loại phiếu
		String phuongThuc = "";  // Phương thức thanh toán
		Object[][] data = phieuThuChiBUS.layDuLieuBang(startDate, endDate, maPhieu, trangThai, loaiPhieu, phuongThuc);
		for (Object[] row : data) {
			for (Object column : row) {
				System.out.print(column + "\t");
			}
			System.out.println();
		}
	}
}
