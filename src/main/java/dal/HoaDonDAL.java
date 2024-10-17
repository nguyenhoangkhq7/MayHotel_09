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
import entity.HoaDon;
import entity.NhanVien;
import entity.PhieuThuChi;


public class HoaDonDAL {
	
	public ArrayList<HoaDon> dsHoaDon;
    Connection con;
    public HoaDonDAL() {
        dsHoaDon = new ArrayList<>();
    }

    // Lấy tất cả các danh sách hóa đơn từ cơ sở dữ liệu
    public ArrayList<HoaDon> getAllHoaDon() {
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM HoaDon";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maHoaDon = rs.getString(1);
                LocalDate ngayTao = rs.getDate(2)!= null ? rs.getDate(2).toLocalDate() : null;
                Boolean trangThai = rs.getBoolean(3);
                double thanhTien = rs.getDouble(4);
                String maKH = rs.getString(5);
                String maNV = rs.getString(6);
                String maDV = rs.getString(7);
                String maKM = rs.getString(8);
                HoaDon hoaDon = new HoaDon(maHoaDon, ngayTao, trangThai, thanhTien, maKH, maNV, maDV, maKM);
                dsHoaDon.add(hoaDon);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsHoaDon;
    }

    // Thêm một hoá đơn mới vào cơ sở dữ liệu
    public boolean themHoaDon(HoaDon hoaDon) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO HoaDon (maHoaDon, ngayTao, trangThai, thanhTien, maKH, maNV, maDV, maKM) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql); 
            stmt.setString(1, hoaDon.getMaHoaDon());
            stmt.setDate(2, Date.valueOf(hoaDon.getNgayTao()));
            stmt.setBoolean(3, hoaDon.getTrangThai());
            stmt.setDouble(4, hoaDon.getThanhTien());
            stmt.setString(5, hoaDon.getMaKhachHang());
            stmt.setString(6, hoaDon.getMaNhanVien());
            stmt.setString(7, hoaDon.getMaDichVu());
            stmt.setString(8, hoaDon.getMaKhuyenMai());           
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Sửa thông tin hóa đơn trong cơ sở dữ liệu
    public boolean suaHoaDon(String maHoaDon, HoaDon hoaDon) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE HoaDon SET ngayTao = ?, trangThai = ?, thanhTien = ?, maKH = ?, maNV = ?, maDV = ?, maKM = ?, WHERE maHoaDon = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(hoaDon.getNgayTao()));
            stmt.setBoolean(2, hoaDon.getTrangThai());
            stmt.setDouble(3, hoaDon.getThanhTien());
            stmt.setString(4, hoaDon.getMaKhachHang());
            stmt.setString(5, hoaDon.getMaNhanVien());
            stmt.setString(6, hoaDon.getMaDichVu());
            stmt.setString(7, hoaDon.getMaKhuyenMai());  

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Hủy hóa đơn 
	public boolean huyHoaDon(String maHoaDon) {
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			con = ConnectDB.getConnection();
			String sql = "update HoaDon set trangThai = N'false' where maHoaDon = ? ";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maHoaDon);
			n = stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}

    // Tìm hóa đơn
    public ArrayList<HoaDon> timKiemHoaDon(String tuKhoa) {
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon WHERE maHoaDon LIKE ? OR maKH LIKE ?";
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + tuKhoa + "%");
            stmt.setString(2, "%" + tuKhoa + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maHoaDon = rs.getString("maHoaDon");
                LocalDate ngayTao = rs.getDate("ngayTao").toLocalDate();
                Boolean trangThai  = rs.getBoolean("trangThai");
                double thanhTien = rs.getDouble("thanhTien");
                String maKH = rs.getString("maKH");
                String maNV = rs.getString("maNV");
                String maDV = rs.getString("maDV");
                String maKM = rs.getString("maKM");

                HoaDon hoaDon = new HoaDon(maHoaDon, ngayTao, trangThai, thanhTien, maKH, maNV, maDV, maKM);
                dsHoaDon.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsHoaDon;
    }

    public static void main(String[] args) {
    	   HoaDonDAL dal = new HoaDonDAL();

//    	   HoaDon hd = new HoaDon("HD006", LocalDate.now(), true, 400000.00, "KH001", "NV001","DV001" , "KM002");
//           boolean result = dal.themHoaDon(hd);
//    	   System.out.println("Thêm mới thành công: " + result);

// get dshoadon
//    	   ArrayList<HoaDon> dsHoaDon = dal.getAllHoaDon();
//           for (HoaDon hoaDon : dsHoaDon) {
//               System.out.println(hoaDon);
   //        }
//tim hoadon 

//	   ArrayList<HoaDon> dsHoaDon = dal.timKiemHoaDon("KH003");
//	   for (HoaDon hoaDon : dsHoaDon) {
//             System.out.println(hoaDon);
//         
//test huy hoa don
    	   dal.huyHoaDon("HD004");
    }


}
