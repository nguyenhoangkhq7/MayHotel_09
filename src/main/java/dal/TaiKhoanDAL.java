package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.ConnectDB;
import entity.ChiTiet_DonDatPhong_Phong;
import entity.TaiKhoan;

public class TaiKhoanDAL {
	private ArrayList<TaiKhoan> dsTaiKhoan;
    private Connection con;
    
    public TaiKhoanDAL(){
    	dsTaiKhoan = new ArrayList<>();
    }
    public ArrayList<TaiKhoan> getAllTaiKhoan() {
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM TaiKhoan";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String tenTaiKhoan = rs.getString(1);
                String matKhau = rs.getString(2);

                // Tạo đối tượng TaiKhoan từ dữ liệu truy vấn
                TaiKhoan taiKhoan = new TaiKhoan(tenTaiKhoan, matKhau);
                dsTaiKhoan.add(taiKhoan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsTaiKhoan;
    }
 // Thêm tài khoản mới
    public boolean themTaiKhoan(TaiKhoan taiKhoan) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO TaiKhoan (tenTaiKhoan, matKhau) VALUES(?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, taiKhoan.getTenDangNhap());
            stmt.setString(2, taiKhoan.getMatKhau());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Cập nhật mật khẩu tài khoản
    public boolean suaTaiKhoan(String tenTaiKhoan, String matKhauMoi) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE TaiKhoan SET matKhau = ? WHERE tenTaiKhoan = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, matKhauMoi);
            stmt.setString(2, tenTaiKhoan);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Xóa tài khoản theo tên đăng nhập
    public boolean xoaTaiKhoan(String tenTaiKhoan) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "DELETE FROM TaiKhoan WHERE tenTaiKhoan = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, tenTaiKhoan);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Tìm tài khoản theo tên đăng nhập
    public TaiKhoan timTaiKhoan(String tenTaiKhoan) {
        TaiKhoan taiKhoan = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM TaiKhoan WHERE tenTaiKhoan = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, tenTaiKhoan);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String matKhau = rs.getString(2);
                taiKhoan = new TaiKhoan(tenTaiKhoan, matKhau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taiKhoan;
    }

    public static void main(String[] args) {
        TaiKhoanDAL dal = new TaiKhoanDAL();
        // Thực hiện các thao tác kiểm tra, ví dụ thêm tài khoản mới
        boolean result = dal.themTaiKhoan(new TaiKhoan("user1", "password123"));
        System.out.println("Thêm tài khoản thành công: " + result);

        // Kiểm tra cập nhật mật khẩu
        boolean updateResult = dal.suaTaiKhoan("user1", "newpassword456");
        System.out.println("Cập nhật mật khẩu thành công: " + updateResult);

        // Kiểm tra xóa tài khoản
        boolean deleteResult = dal.xoaTaiKhoan("user1");
        System.out.println("Xóa tài khoản thành công: " + deleteResult);
    }
}
