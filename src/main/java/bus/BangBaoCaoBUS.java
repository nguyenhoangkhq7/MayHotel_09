package bus;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.stream.Collectors;

import dal.ChiTiet_DonDatPhong_PhongDAL;
import dal.ChiTiet_DonDatPhong_Phong_DichVuDAL;
import dal.HoaDonDAL;
import dal.NhanVienDAL;
import dal.PhieuThuChiDAL;
import entity.ChiTiet_DonDatPhong_Phong;
import entity.ChiTiet_DonDatPhong_Phong_DichVu;
import entity.HoaDon;
import entity.NhanVien;
import entity.PhieuThuChi;

public class BangBaoCaoBUS {

	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###");
	// Tính tiền phòng từ chi tiết đơn đặt phòng
	public double tinhTienPhong(ChiTiet_DonDatPhong_Phong ctddpp) {
		double donGia = ctddpp.getPhong().getLoaiPhong().getDonGia(); // Giá phòng theo loại phòng
		double chietKhau = ctddpp.getChietKhau(); // Chiết khấu
		LocalDateTime ngayNhanPhongDuKien = ctddpp.getNgayNhanPhong(); // Ngày nhận phòng dự kiến
		LocalDateTime ngayTraPhongDuKien = ctddpp.getNgayTraPhong(); // Ngày trả phòng dự kiến
		LocalDateTime ngayNhanPhongThucTe = ctddpp.getDonDatPhong().getNgayNhanPhong(); // Ngày nhận phòng thực tế
		LocalDateTime ngayTraPhongThucTe = ctddpp.getDonDatPhong().getNgayTraPhong(); // Ngày trả phòng thực tế

		// Tính số ngày ở (tối thiểu 1 ngày)
		long soNgayO = Math.max(ChronoUnit.DAYS.between(ngayNhanPhongDuKien, ngayTraPhongDuKien), 1);
		double tienPhong = donGia * soNgayO * (1 - chietKhau); // Tiền phòng cơ bản

		// Xử lý nhận phòng sớm
		if (ngayNhanPhongThucTe.isBefore(ngayNhanPhongDuKien)) {
			long soPhutNhanSom = ChronoUnit.MINUTES.between(ngayNhanPhongThucTe, ngayNhanPhongDuKien);
			double donGiaPhut = donGia / (24 * 60); // Giá mỗi phút
			double tienNhanSom = soPhutNhanSom * donGiaPhut * 3; // Tính tiền phút nhận sớm
			tienPhong += tienNhanSom; // Cộng tiền nhận phòng sớm vào tổng tiền phòng
		}

		// Xử lý trả phòng muộn
		if (ngayTraPhongThucTe.isAfter(ngayTraPhongDuKien)) {
			long soPhutTraMuon = ChronoUnit.MINUTES.between(ngayTraPhongDuKien, ngayTraPhongThucTe);
			double donGiaPhut = donGia / (24 * 60); // Giá mỗi phút
			double tienTraMuon = soPhutTraMuon * donGiaPhut * 3; // Tính tiền phút trả muộn
			tienPhong += tienTraMuon; // Cộng tiền trả phòng muộn vào tổng tiền phòng
		}

		return tienPhong;
	}


	public Object[][] layDuLieuBang(NhanVien nhanVien,LocalDateTime startDate, LocalDateTime endDate) {
		// Lấy danh sách HoaDon trong khoảng thời gian
		ArrayList<HoaDon> hoaDonsFiltered = new HoaDonDAL().getHoaDonByDateRange(startDate, endDate);
		// Lọc danh sách HoaDon theo nhân viên và chuyển sang ArrayList
		ArrayList<HoaDon> hoaDons = hoaDonsFiltered.stream()
				.filter(hd -> hd.getNhanVien().getMaNV().equals(nhanVien.getMaNV()))  // Lọc theo mã nhân viên
				.collect(Collectors.toCollection(ArrayList::new));

		// Lấy danh sách PhieuThuChi trong khoảng thời gian
		ArrayList<PhieuThuChi> phieuThuChisFiltered = new PhieuThuChiDAL().getPhieuThuChiByDateRange(startDate, endDate);
		// Lọc danh sách PhieuThuChi theo nhân viên và chuyển sang ArrayList
		ArrayList<PhieuThuChi>phieuThuChis = phieuThuChisFiltered.stream()
				.filter(ptc -> ptc.getNhanVien().getMaNV().equals(nhanVien.getMaNV()))  // Lọc theo mã nhân viên
				.collect(Collectors.toCollection(ArrayList::new));


		int soHoaDon = 0, soHoaDonHuy = 0, soPhongDuocThue = 0;
		double tongDoanhThu = 0, doanhThuPhong = 0, doanhThuDichVu = 0, khuyenMai = 0, thucThu = 0;
		double tongDoanhThuRong = 0, tienMat = 0, tienNganHang = 0;
		double khoanThu = 0, khoanChiKhac = 0;

		// Biến đếm cho từng loại phòng
		int soLoaiDeluxKing = 0;
		int soLoaiDeluxTwin = 0;
		int soLoaiDeluxTriple = 0;
		int soLoaiExecutiveSuite = 0;
		int soLoaiExecutiveTwin = 0;
		int soLoaiSuiteFamily = 0;
		// Xử lý danh sách phiếu thu chi
		for (PhieuThuChi phieu : phieuThuChis) {
			if (phieu.getLoaiPhieu().equalsIgnoreCase("Phiếu Thu")) {
				khoanThu += phieu.getSoTien();
			} else {
				khoanChiKhac += phieu.getSoTien();
			}
		}

		// Xử lý danh sách hóa đơn
		ChiTiet_DonDatPhong_PhongDAL chiTietDAL = new ChiTiet_DonDatPhong_PhongDAL();
		ChiTiet_DonDatPhong_Phong_DichVuDAL dichVuDAL = new ChiTiet_DonDatPhong_Phong_DichVuDAL();
		// Lặp qua danh sách hóa đơn để tính toán
		for (HoaDon hoaDon : hoaDons) {
			if (hoaDon.getTrangThai()) { // Kiểm tra trạng thái hóa đơn
				soHoaDon++;
				tongDoanhThuRong += hoaDon.getThanhTien();

				double giaTriKhuyenMai = 0; // Giá trị mặc định nếu không có khuyến mãi
				if (hoaDon.getKhuyenMai() != null) {
					giaTriKhuyenMai = hoaDon.getKhuyenMai().getGiaTri();
				}
				double tienKhuyenMai = hoaDon.getThanhTien() / (1 - giaTriKhuyenMai) - hoaDon.getThanhTien();
				khuyenMai += tienKhuyenMai;

				if (hoaDon.getDonDatPhong().getPhuongThucThanhToan().equalsIgnoreCase("Tiền mặt")) {
					tienMat += hoaDon.getThanhTien();
				} else {
					tienNganHang += hoaDon.getThanhTien();
				}

				// Tính số phòng được thuê từ chi tiết đơn đặt phòng
				ArrayList<ChiTiet_DonDatPhong_Phong> chiTietList = chiTietDAL.getChiTietDonDatPhongPhongTheoMaDDP(hoaDon.getDonDatPhong().getMaDon());
				soPhongDuocThue += chiTietList.size();

				///
				for (ChiTiet_DonDatPhong_Phong ct : chiTietList) {
					doanhThuPhong += tinhTienPhong(ct);

					// Cập nhật số lượng theo loại phòng
					String loaiPhong = ct.getPhong().getLoaiPhong().getTenLoaiPhong(); // Giả sử có phương thức getTen()
					switch (loaiPhong) {
						case "Delux King":
							soLoaiDeluxKing++;
							break;
						case "Delux Twin":
							soLoaiDeluxTwin++;
							break;
						case "Delux Triple":
							soLoaiDeluxTriple++;
							break;
						case "Executive Suite":
							soLoaiExecutiveSuite++;
							break;
						case "Executive Twin":
							soLoaiExecutiveTwin++;
							break;
						case "Suite Family":
							soLoaiSuiteFamily++;
							break;
						default:
							break;
					}

					ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> danhSachDichVu = dichVuDAL.getDSChiTietDonDatPhongPhongDichVuTheoMaDonDatPhongMaPhong(ct.getDonDatPhong().getMaDon(), ct.getPhong().getMaPhong());;

					for (ChiTiet_DonDatPhong_Phong_DichVu ctDV : danhSachDichVu) {
						double tienDichVu = ctDV.getSoLuongDat() * ctDV.getDichVu().getDonGia();
						doanhThuDichVu += tienDichVu; // Cộng dồn vào doanh thu từ dịch vụ
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
				{"3", "Tổng doanh thu", df.format(tongDoanhThu)},
				{"4", "Doanh thu phòng", df.format(doanhThuPhong)},
				{"5", "Dịch vụ", df.format(doanhThuDichVu)},
				{"6", "Ngân hàng", df.format(tienNganHang)},
				{"7", "Tiền mặt", df.format(tienMat)},
				{"8", "Khuyến mãi", df.format(khuyenMai)},
				{"9","Tổng doanh thu ròng", df.format(tongDoanhThuRong)},
				{"10", "Khoản thu khác", df.format(khoanThuKhac)},
				{"11", "Khoản chi khác", df.format(khoanChiKhac)},
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
		LocalDateTime startDate = LocalDateTime.of(2024, 11, 01, 0,0,0); // Ngày bắt đầu
		LocalDateTime endDate = LocalDateTime.of(2024, 11, 05,22,22,22); // Ngày kết thúc

		// Gọi phương thức để lấy dữ liệu báo cáo
		NhanVienDAL dal = new NhanVienDAL();
		Object[][] duLieuBang = bangBaoCaoBUS.layDuLieuBang(dal.getNhanVienTheoMa("NV015"),startDate, endDate);

		// In ra kết quả
		System.out.println("Bảng báo cáo từ " + startDate + " đến " + endDate + ":");
		for (Object[] row : duLieuBang) {
			System.out.printf("%s - %s: %s%n", row[0], row[1], row[2]);
		}
	}
}
