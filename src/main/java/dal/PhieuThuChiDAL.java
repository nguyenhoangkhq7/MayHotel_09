package dal;

import java.util.ArrayList;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import database.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.PhieuThuChi;
import entity.TaiKhoan;

public class PhieuThuChiDAL {
	public ArrayList<PhieuThuChi> dsPhieuThuChi;
    Connection con;
    public PhieuThuChiDAL() {
        dsPhieuThuChi = new ArrayList<>();
    }

    // Lấy tất cả các danh sách phiếu thu chi từ cơ sở dữ liệu
    public ArrayList<PhieuThuChi> getAllPhieuThuChi() {
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM PhieuThuChi";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maPhieu = rs.getString(1);
                String loaiPhieu = rs.getString(2);
                String moTa = rs.getString(3);
            	LocalDate ngayTao = rs.getDate(4)!= null ? rs.getDate(4).toLocalDate() : null;
                double soTien = rs.getDouble(5);
                String phuongThuc = rs.getString(6);
                boolean conHoatDong = rs.getBoolean(7);
                NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(rs.getString(8)); 

                PhieuThuChi phieuThuChi = new PhieuThuChi(maPhieu, loaiPhieu, moTa, ngayTao, soTien, phuongThuc, conHoatDong, nhanVien);
                dsPhieuThuChi.add(phieuThuChi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsPhieuThuChi;
    }

    // Thêm một phiếu thu chi mới vào cơ sở dữ liệu
    public boolean themPhieuThuChi(PhieuThuChi phieuThuChi) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO PhieuThuChi (maPhieu, loaiPhieu, moTa, ngayTao, soTien, phuongThuc, conHoatDong, maNV) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, phieuThuChi.getMaPhieu());
            stmt.setString(2, phieuThuChi.getLoaiPhieu());
            stmt.setString(3, phieuThuChi.getMoTa()); 
            stmt.setDate(4, Date.valueOf(phieuThuChi.getNgayTao()));
            stmt.setDouble(5, phieuThuChi.getSoTien());
            stmt.setString(6, phieuThuChi.getPhuongThuc());
            stmt.setBoolean(7, phieuThuChi.isConHoatDong());
            stmt.setString(8, phieuThuChi.getNhanVien().getMaNV());

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
    
    public PhieuThuChi getPhieuThuChiTheoMa(String maPhieu) {
        PhieuThuChi phieuThuChi = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM PhieuThuChi WHERE maPhieu = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maPhieu);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	String loaiPhieu = rs.getString("loaiPhieu");
            	String moTa= rs.getString("moTa");
                LocalDate ngayTao = rs.getDate("ngayTao").toLocalDate();
                double soTien = rs.getDouble("soTien");
            	String phuongThuc = rs.getString("phuongThuc");
                boolean conHoatDong = rs.getBoolean("conHoatDong");
                NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(rs.getString("maNV"));

                phieuThuChi = new PhieuThuChi(maPhieu, loaiPhieu,moTa, ngayTao, soTien, phuongThuc, conHoatDong, nhanVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return phieuThuChi;
    }

    // Đóng kết nối
    private void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Sửa thông tin phòng trong cơ sở dữ liệu
    public boolean suaPhieuThuChi(String maPhieuThuChi, PhieuThuChi phieuThuChi) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE PhieuThuChi SET loaiPhieu = ?, moTa = ?, ngayTao = ?, soTien = ?, phuongThuc = ?, conHoatdong = ?, maNV = ? WHERE maPhieu = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, phieuThuChi.getLoaiPhieu());
            stmt.setString(2, phieuThuChi.getMoTa());
            stmt.setDate(3, Date.valueOf(phieuThuChi.getNgayTao()));
            stmt.setDouble(4, phieuThuChi.getSoTien());
            stmt.setString(5, phieuThuChi.getPhuongThuc());
            stmt.setString(6, phieuThuChi.getMaPhieu());
            stmt.setBoolean(7, phieuThuChi.isConHoatDong());
            stmt.setString(8, phieuThuChi.getNhanVien().getMaNV());
            
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
    
    // Xóa phiếu thu chi ra khỏi cơ sở dữ liệu
//  public boolean xoaPhieuThuChi(String maPhieu) {
//      int n = 0;
//      try {
//          ConnectDB.getInstance().connect();
//          con = ConnectDB.getConnection();
//          String sql = "DELETE FROM PhieuThuChi WHERE maPhieu = ?";
//          PreparedStatement stmt = con.prepareStatement(sql);
//          stmt.setString(1, maPhieu);
//
//          n = stmt.executeUpdate();
//      } catch (SQLException e) {
//          e.printStackTrace();
//      }
//      return n > 0;
//  }

    // Hủy phiếu thu chi khỏi cơ sở dữ liệu
	public boolean huyPhieuThuChi(String maPhieu) {
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			con = ConnectDB.getConnection();
			String sql = "update PhieuThuChi set trangThai = N'false' where maPhieu = ? ";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maPhieu);
			n = stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
    
    //Tìm phi

    public static void main(String[] args) {
    	PhieuThuChiDAL dal = new PhieuThuChiDAL();
    	
//        boolean result = dal.themPhieuThuChi(new PhieuThuChi("PTC004", "Phiếu thu", "abc", LocalDate.now(), 300000.00, "chuyen khoan", true, "NV2827" ));
//        System.out.println("Thêm mới thành công: " + result);
//    	dal.xoaPhieuThuChi("PTC004");
//        phieuThuChiDAL.xoaPhieuThuChi("PTC004");
// test sửa phiếu thu chi
//    	dal.suaPhieuThuChi("PTC004", new PhieuThuChi("PTC003", "Phiếu thu", "abc123", LocalDate.now(), 200000.00, "chuyen khoan"));
    }
}
