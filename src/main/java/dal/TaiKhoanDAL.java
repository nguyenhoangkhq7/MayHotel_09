package dal;

import entity.TaiKhoan;
import database.ConnectDB;

import java.sql.*;
import java.util.ArrayList;

public class TaiKhoanDAL {
    private Connection con;

    public TaiKhoanDAL() {
        // Không lưu trữ danh sách tài khoản trong DAL
    }

    // Lấy tài khoản theo tên tài khoản
    public TaiKhoan getTaiKhoanTheoTen(String tenTaiKhoan) {
        TaiKhoan taiKhoan = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM TaiKhoan WHERE tenTaiKhoan = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, tenTaiKhoan);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                taiKhoan = new TaiKhoan(
                        rs.getString(1),  // tenTaiKhoan
                        rs.getString(2)   // matKhau
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return taiKhoan;
    }

    // Cập nhật tài khoản trong cơ sở dữ liệu
    public boolean capNhatTaiKhoan(TaiKhoan taiKhoan) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE TaiKhoan SET matKhau = ? WHERE tenTaiKhoan = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, taiKhoan.getMatKhau()); // Đảm bảo mật khẩu đã được mã hóa
            stmt.setString(2, taiKhoan.getTenTaiKhoan());

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return n > 0;
    }

    // Đóng kết nối với cơ sở dữ liệu
    private void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Đây là nơi bạn có thể thử nghiệm với các phương thức
        TaiKhoanDAL taiKhoanDAL = new TaiKhoanDAL();

        // Lấy tài khoản theo tên
        TaiKhoan taiKhoanLayDuoc = taiKhoanDAL.getTaiKhoanTheoTen("admin");
        System.out.println("Tài khoản lấy được: " + taiKhoanLayDuoc);


    }
}
