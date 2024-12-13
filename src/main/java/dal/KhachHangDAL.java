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
    public boolean kiemTraSoDienThoaiTonTai(String sdt) {
        // Giả sử bạn có một kết nối cơ sở dữ liệu tên `connection`
        String query = "SELECT COUNT(*) FROM KhachHang WHERE soDienThoai = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, sdt);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Số điện thoại đã tồn tại nếu số lượng > 0
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false; // Mặc định trả về false nếu xảy ra lỗi
    }
    public boolean kiemTraCCCDTonTai(String CCCD) {
        int n = 0;  // Biến lưu số lượng bản ghi tìm được
        try {
            // Kiểm tra kết nối
            if (con == null || con.isClosed()) {
                ConnectDB.getInstance().connect();  // Kết nối lại nếu chưa có kết nối
                con = ConnectDB.getConnection();    // Lấy kết nối từ đối tượng ConnectDB
                System.out.println("Kết nối cơ sở dữ liệu thành công!");
            }

            // Câu lệnh SQL kiểm tra sự tồn tại của CCCD trong bảng KhachHang
            String sql = "SELECT COUNT(*) FROM KhachHang WHERE soCanCuoc = ?";
            
            // Tạo PreparedStatement và thiết lập tham số
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, CCCD);  // Gán giá trị cho tham số CCCD

            // Thực thi câu lệnh SQL và lấy kết quả
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Lấy số lượng bản ghi trả về, nếu lớn hơn 0 tức là CCCD đã tồn tại
                n = rs.getInt(1);
                System.out.println("Số lượng bản ghi tìm thấy: " + n);
            }
        } catch (SQLException ex) {
            // In ra thông báo lỗi nếu có lỗi xảy ra
            System.err.println("Lỗi khi kiểm tra CCCD: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("ErrorCode: " + ex.getErrorCode());
            ex.printStackTrace();
        } finally {
            try {
                // Đảm bảo đóng kết nối sau khi thực hiện xong
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Lỗi khi đóng kết nối: " + e.getMessage());
            }
        }
        
        // Trả về true nếu CCCD tồn tại, ngược lại trả về false
        return n > 0;
    }
    public boolean kiemTraEmailTonTai(String email) {
        int n = 0;  // Biến lưu số lượng bản ghi tìm được
        try {
            // Kiểm tra kết nối
            if (con == null || con.isClosed()) {
                ConnectDB.getInstance().connect();  // Kết nối lại nếu chưa có kết nối
                con = ConnectDB.getConnection();    // Lấy kết nối từ đối tượng ConnectDB
                System.out.println("Kết nối cơ sở dữ liệu thành công!");
            }

            // Câu lệnh SQL kiểm tra sự tồn tại của email trong bảng KhachHang
            String sql = "SELECT COUNT(*) FROM KhachHang WHERE email = ?";
            
            // Tạo PreparedStatement và thiết lập tham số
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);  // Gán giá trị cho tham số email

            // Thực thi câu lệnh SQL và lấy kết quả
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Lấy số lượng bản ghi trả về, nếu lớn hơn 0 tức là email đã tồn tại
                n = rs.getInt(1);
                System.out.println("Số lượng bản ghi tìm thấy: " + n);
            }
        } catch (SQLException ex) {
            // In ra thông báo lỗi nếu có lỗi xảy ra
            System.err.println("Lỗi khi kiểm tra email: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("ErrorCode: " + ex.getErrorCode());
            ex.printStackTrace();
        } finally {
            try {
                // Đảm bảo đóng kết nối sau khi thực hiện xong
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Lỗi khi đóng kết nối: " + e.getMessage());
            }
        }
        
        // Trả về true nếu email tồn tại, ngược lại trả về false
        return n > 0;
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
            // Kiểm tra kết nối
            if (con == null || con.isClosed()) {
                ConnectDB.getInstance().connect();
                con = ConnectDB.getConnection();
                System.out.println("Kết nối cơ sở dữ liệu thành công!");
            }

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
            System.out.println("Thêm khách hàng thành công: " + n + " bản ghi đã được thêm.");

        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm khách hàng: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("ErrorCode: " + e.getErrorCode());
            e.printStackTrace();
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Lỗi khi đóng kết nối: " + e.getMessage());
            }
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
