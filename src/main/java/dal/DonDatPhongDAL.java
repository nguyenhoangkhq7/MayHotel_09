package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import database.ConnectDB;
import entity.DonDatPhong;
import entity.KhachHang;
import entity.NhanVien;

// thêm sửa xóa lấy dữ liệu entity
public class DonDatPhongDAL {
	public ArrayList<DonDatPhong> dsDonDatPhong;
	Connection con;
	private static int soLuongDonDatPhong = 0;
	public DonDatPhongDAL() {
		dsDonDatPhong = new ArrayList<>();
	}
// 
//public ArrayList<DonDatPhong> getAllDonDatPhong() {
//	ArrayList<DonDatPhong> dsDonDatPhong = new ArrayList<>(); // Khởi tạo danh sách DonDatPhong
//	try {
//		ConnectDB.getInstance().connect(); // Kết nối đến cơ sở dữ liệu
//		con = ConnectDB.getConnection(); // Lấy kết nối
//		String sql = "SELECT * FROM DonDatPhong"; // Câu lệnh SQL
//		Statement stmt = con.createStatement(); // Tạo Statement
//		ResultSet rs = stmt.executeQuery(sql); // Thực hiện truy vấn
//
//		while (rs.next()) { // Duyệt qua từng kết quả trong ResultSet
//			String maDonDatPhong = rs.getString(1); // Lấy mã đơn đặt phòng
//			LocalDate ngayTao = rs.getDate(2).toLocalDate(); // Lấy ngày tạo
//			String phuongThucThanhToan = rs.getString(3); // Lấy phương thức thanh toán
//			String trangThaiDonDatPhong = rs.getString(4); // Lấy trạng thái đơn đặt phòng
//			double tienDatCoc = rs.getDouble(5); // Lấy tiền đặt cọc
//			LocalDate ngayThanhToan = rs.getDate(6) != null ? rs.getDate(6).toLocalDate() : null; // Lấy ngày thanh toán (có thể null)
//
//			// Lấy thông tin nhân viên và khách hàng (giả định có phương thức tương tự)
//			NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(rs.getString(7)); // Lấy nhân viên theo mã
//			KhachHang khachHang = new KhachHangDAL().getKhachHangTheoMa(rs.getString(8)); // Lấy khách hàng theo mã
//
//			// Tạo một đối tượng DonDatPhong mới từ dữ liệu truy vấn
//			DonDatPhong donDatPhong = new DonDatPhong(maDonDatPhong, ngayTao, phuongThucThanhToan, trangThaiDonDatPhong, tienDatCoc, ngayThanhToan, nhanVien, khachHang);
//			dsDonDatPhong.add(donDatPhong); // Thêm đơn đặt phòng vào danh sách
//		}
//	} catch (SQLException e) {
//		e.printStackTrace(); // Xử lý lỗi
//	}
//	return dsDonDatPhong; // Trả về danh sách các đơn đặt phòng
//}

//get don dat phòng theo mã
//public DonDatPhong getDonDatPhongTheoMa(String maDonDatPhong) {
//	DonDatPhong donDatPhong = null; // Khởi tạo biến để lưu đối tượng DonDatPhong
//	try {
//		ConnectDB.getInstance().connect(); // Kết nối đến cơ sở dữ liệu
//		con = ConnectDB.getConnection(); // Lấy kết nối
//		String sql = "SELECT * FROM DonDatPhong WHERE maDonDatPhong = ?"; // Câu lệnh SQL với điều kiện
//		PreparedStatement pstmt = con.prepareStatement(sql); // Sử dụng PreparedStatement
//		pstmt.setString(1, maDonDatPhong); // Thiết lập tham số
//
//		ResultSet rs = pstmt.executeQuery(); // Thực hiện truy vấn
//
//		if (rs.next()) { // Nếu có kết quả
//			LocalDate ngayTao = rs.getDate(2).toLocalDate(); // Lấy ngày tạo
//			String phuongThucThanhToan = rs.getString(3); // Lấy phương thức thanh toán
//			String trangThaiDonDatPhong = rs.getString(4); // Lấy trạng thái đơn đặt phòng
//			double tienDatCoc = rs.getDouble(5); // Lấy tiền đặt cọc
//			LocalDate ngayThanhToan = rs.getDate(6) != null ? rs.getDate(6).toLocalDate() : null; // Lấy ngày thanh toán (có thể null)
//
//			// Lấy thông tin nhân viên và khách hàng (giả định có phương thức tương tự)
//			NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(rs.getString(7)); // Lấy nhân viên theo mã
//			KhachHang khachHang = new KhachHangDAL().getKhachHangTheoMa(rs.getString(8)); // Lấy khách hàng theo mã
//
//			// Tạo đối tượng DonDatPhong từ dữ liệu truy vấn
//			donDatPhong = new DonDatPhong(maDonDatPhong, ngayTao, phuongThucThanhToan, trangThaiDonDatPhong, tienDatCoc, ngayThanhToan, nhanVien, khachHang);
//		}
//	} catch (SQLException e) {
//		e.printStackTrace(); // Xử lý lỗi
//	}
//	return donDatPhong; // Trả về đối tượng DonDatPhong hoặc null nếu không tìm thấy
//}

	public boolean themDonDatPhong(DonDatPhong ddp) {
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			con = ConnectDB.getConnection();
			String sql = "insert into DonDatPhong values(?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, ddp.getMaDonDatPhong());
			stmt.setDate(2, Date.valueOf(ddp.getNgayTao()));
			stmt.setString(3, ddp.getPhuongThucThanhToan());
			stmt.setString(4, ddp.getTrangThaiDonDatPhong());
			stmt.setDouble(5, ddp.getTienDatCoc());
			stmt.setDate(6, Date.valueOf(ddp.getNgayThanhToan()));
			stmt.setString(7, ddp.getNhanVien().getMaNV());
			stmt.setString(8, ddp.getKhachHang().getMaKH());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
	public boolean huyDonDatPhong(String maDDP) {
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			con = ConnectDB.getConnection();
			String sql = "update DonDatPhong set trangThaiDonDatPhong = N'Đã hủy' where maDon = ? ";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maDDP);
			n = stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}

	public boolean suaDonDatPhong(String maDDP, DonDatPhong ddp) {
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			con = ConnectDB.getConnection();
			String sql = "update DonDatPhong set phuongThucThanhToan = ?, trangThaiDonDatPhong = ?, tienDatCoc = ?, ngayThanhToan = ?, where maDon = ? ";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, ddp.getPhuongThucThanhToan());
			stmt.setString(2, ddp.getTrangThaiDonDatPhong());
			stmt.setDouble(3, ddp.getTienDatCoc());
			stmt.setDate(4, Date.valueOf(ddp.getNgayThanhToan()));
			stmt.setString(5, maDDP);
			n = stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
	
	public static void main(String[] args) {
		DonDatPhongDAL tmp = new DonDatPhongDAL(); // tạo đối tượng cho DonDatPhongDAL để sử dụng phương thức của đối tượng đó
//		boolean tt = tmp.themDonDatPhong(new DonDatPhong("DDP000011", LocalDate.now(), "Tiền mặt", "Đặt trước", 1000000, LocalDate.now(), "NV001", "KH001"));
		tmp.huyDonDatPhong("DDP001");
	}
}

