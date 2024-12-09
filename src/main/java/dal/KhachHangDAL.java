package dal;

import java.sql.*;
import java.util.ArrayList;

import database.ConnectDB;
import entity.KhachHang;
import entity.LoaiKhachHang;
import entity.Phong;

public class KhachHangDAL {
	 public ArrayList<KhachHang> dsKhachHang;
	    private Connection con;

	    public KhachHangDAL() {
	        dsKhachHang = new ArrayList<>();
	    }


    public String getLastKH() {
        String lastOrder = null;

        String query = "SELECT MAX(maKH) FROM KhachHang"; // Truy vấn để lấy mã đơn lớn nhất
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                lastOrder = rs.getString(1); // Lấy mã đơn lớn nhất
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastOrder;
    }
    // Lấy tất cả khách hàng từ cơ sở dữ liệu
    public ArrayList<KhachHang> getAllKhachHang() {
        ArrayList<KhachHang> dsKhachHang = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM KhachHang";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                KhachHang khachHang = new KhachHang(
                        rs.getString(1), 
                        rs.getString(2), 
                        rs.getString(3), 
                        rs.getDouble(4), 
                        rs.getString(5), 
                        rs.getString(6), 
                        LoaiKhachHang.valueOf(rs.getString(7))
                );
                dsKhachHang.add(khachHang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsKhachHang;
    }
 // Lấy khách hàng theo mã khách hàng
    public KhachHang getKhachHangTheoMa(String maKH) {
        KhachHang khachHang = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM KhachHang WHERE maKH = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maKH);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                khachHang = new KhachHang(
                        rs.getString(1),  // maKH
                        rs.getString(2),  // hoTen
                        rs.getString(3),  // soDienThoai
                        rs.getDouble(4),  // tienTichLuy
                        rs.getString(5),  // soCanCuoc
                        rs.getString(6),  // email
                        LoaiKhachHang.valueOf(rs.getString(7))  // loaiKhachHang
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khachHang;
    }


    // Thêm khách hàng mới vào cơ sở dữ liệu
    public boolean themKhachHang(KhachHang khachHang) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO KhachHang (maKH, hoTen, soDienThoai, tienTichLuy, soCanCuoc, email, loaiKhachHang) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, khachHang.getMaKH());
            stmt.setString(2, khachHang.getHoTen());
            stmt.setString(3, khachHang.getSoDienThoai());
            stmt.setDouble(4, khachHang.getTienTichLuy());
            stmt.setString(5, khachHang.getSoCanCuoc());
            stmt.setString(6, khachHang.getEmail());
            stmt.setString(7, khachHang.getLoaiKhachHang().name());

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
    // Kiểm tra khách hàng có tồn tại theo số điện thoại
    public boolean checkKhachHangTonTaiTheoSDT(String sdt) {
        boolean tonTai = false;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT COUNT(*) FROM KhachHang WHERE soDienThoai = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, sdt);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1); // Lấy số lượng khách hàng tìm thấy
                tonTai = count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tonTai;
    }
    // Lấy khách hàng theo số điện thoại
    public KhachHang getKhachHangTheoSoDienThoai(String soDienThoai) {
        KhachHang khachHang = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM KhachHang WHERE soDienThoai = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, soDienThoai);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                khachHang = new KhachHang(
                        rs.getString(1),  // maKH
                        rs.getString(2),  // hoTen
                        rs.getString(3),  // soDienThoai
                        rs.getDouble(4),  // tienTichLuy
                        rs.getString(5),  // soCanCuoc
                        rs.getString(6),  // email
                        LoaiKhachHang.valueOf(rs.getString(7))  // loaiKhachHang
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khachHang;
    }

    // Cập nhật khách hàng trong cơ sở dữ liệu
    public boolean suaKhachHang(KhachHang khachHang) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE KhachHang SET hoTen = ?, soDienThoai = ?, tienTichLuy = ?, soCanCuoc = ?, email = ?, loaiKhachHang = ? WHERE maKH = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, khachHang.getHoTen());
            stmt.setString(2, khachHang.getSoDienThoai());
            stmt.setDouble(3, khachHang.getTienTichLuy());
            stmt.setString(4, khachHang.getSoCanCuoc());
            stmt.setString(5, khachHang.getEmail());
            stmt.setString(6, khachHang.getLoaiKhachHang().name());
            stmt.setString(7, khachHang.getMaKH());

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
    public boolean kiemTraTrungThongTin(KhachHang khachHang) {
        boolean exists = false;
        try {
            String sql = "SELECT COUNT(*) FROM KhachHang WHERE hoTen = ? AND soDienThoai = ? AND soCanCuoc = ? AND email = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, khachHang.getHoTen());
            stmt.setString(2, khachHang.getSoDienThoai());
            stmt.setString(3, khachHang.getSoCanCuoc());
            stmt.setString(4, khachHang.getEmail());

            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                exists = true; // Nếu có bản ghi trùng, set exists = true
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

 // Xóa khách hàng theo mã khách hàng
    public boolean xoaKhachHang(String maKH) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();

            // Kiểm tra xem khách hàng có đơn đặt phòng nào không
            String checkSQL = "SELECT COUNT(*) FROM DonDatPhong WHERE maKH = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkSQL);
            checkStmt.setString(1, maKH);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                // Nếu có đơn đặt phòng liên quan, không cho phép xóa
                return false;
            }

            // Nếu không có đơn đặt phòng, tiến hành xóa khách hàng
            String sql = "DELETE FROM KhachHang WHERE maKH = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maKH);

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }


    

    // Lấy mã khách hàng cuối cùng trong cơ sở dữ liệu
    public String getLastMaKH() {
        String lastKHCode = null;

        String query = "SELECT MAX(maKH) FROM KhachHang"; // Truy vấn để lấy mã phòng lớn nhất
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                lastKHCode = rs.getString(1); // Lấy mã phòng lớn nhất
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return lastKHCode;
    }
    

    public static void main(String[] args) {
		KhachHangDAL dal = new KhachHangDAL();
        ArrayList<KhachHang> khs = new ArrayList<>();
		khs = dal.getAllKhachHang();
		for(KhachHang kh : khs) {
			System.out.println(kh);;
		}
	}
}
