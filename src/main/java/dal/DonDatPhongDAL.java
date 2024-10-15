package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.ConnectDB;
import entity.DonDatPhong;
// thêm sửa xóa lấy dữ liệu entity 
public class DonDatPhongDAL {
	public ArrayList<DonDatPhong> dsDonDatPhong;
	Connection con;
	private static int soLuongDonDatPhong = 0;
	public DonDatPhongDAL() {
		dsDonDatPhong = new ArrayList<>();
	}
// 
	public ArrayList<DonDatPhong> getAllDonDatPhong() { // lấy danh sách tất cả đơn đặt phòng
		try {
			ConnectDB.getInstance().connect();
			con = ConnectDB.getConnection();
			String sql = "select * from DonDatPhong";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maDon = rs.getString(1);
				LocalDate ngayTao = rs.getDate(2)!= null ? rs.getDate(2).toLocalDate() : null;
				String phuongThucThanhToan = rs.getString(3);
				String trangThaiDDP = rs.getString(4);
				double tienDatCoc = rs.getDouble(5);
				LocalDate ngayThanhToan = rs.getDate(6)!= null ? rs.getDate(6).toLocalDate() : null;
				String maNV = rs.getString(7);
				String maKH = rs.getString(8);
				// từ dữ liệu đã có tạo đơn đặt phòng tạm
				DonDatPhong tmp = new DonDatPhong(maDon,ngayTao,phuongThucThanhToan,trangThaiDDP,tienDatCoc,ngayThanhToan,maNV,maKH);
				dsDonDatPhong.add(tmp);
			} 
		} catch (SQLException e){
			e.printStackTrace();
		}
		return dsDonDatPhong;
	}

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
			stmt.setString(7, ddp.getMaNhanVien());
			stmt.setString(8, ddp.getMaKhachHang());
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

