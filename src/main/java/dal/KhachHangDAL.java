package dal;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.ConnectDB;
import entity.KhachHang;
import entity.LoaiKhachHang;

public class KhachHangDAL {
    public ArrayList<KhachHang> dsKhachHang;
    private Connection con;

    public KhachHangDAL() {
        dsKhachHang = new ArrayList<>();
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
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        LoaiKhachHang.valueOf(rs.getString(7))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khachHang;
    }

    // Lấy danh sách khách hàng từ cơ sở dữ liệu
    public ArrayList<KhachHang> getAllKhachHang() {
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

    // Thêm mới khách hàng
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

    // Sửa thông tin khách hàng
    public boolean suaKhachHang(String maKH, KhachHang khachHang) {
        khachHang.setMaKH(maKH); // Đặt mã khách hàng vào đối tượng trước khi gọi saveCustomerToDatabase
        return saveCustomerToDatabase(khachHang); // Gọi phương thức cập nhật
    }

    // Cập nhật thông tin khách hàng vào cơ sở dữ liệu
    public boolean saveCustomerToDatabase(KhachHang khachHang) {
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
            stmt.setString(7, khachHang.getMaKH()); // Sử dụng mã khách hàng từ đối tượng khachHang

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Xóa khách hàng
    public boolean xoaKhachHang(String maKH) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "DELETE FROM KhachHang WHERE maKH = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maKH);

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Phương thức lấy mã khách hàng cuối cùng từ cơ sở dữ liệu
    public String getLastMaKH() {
        String lastMaKH = null;
        String sql = "SELECT TOP 1 maKH FROM KhachHang ORDER BY maKH DESC"; // Lấy mã khách hàng cuối cùng

        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                lastMaKH = rs.getString("maKH"); // Lấy mã khách hàng cuối cùng
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving last customer ID: " + e.getMessage());
        }

        return lastMaKH;
    }

    public static void main(String[] args) {
        Connection con = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            System.out.println(new KhachHangDAL().getAllKhachHang());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}