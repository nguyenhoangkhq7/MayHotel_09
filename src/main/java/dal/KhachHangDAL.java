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
            if (rs.next()) { // Thêm điều kiện kiểm tra để đảm bảo có dữ liệu
                khachHang = new KhachHang(
                        rs.getString(1),  // maKH
                        rs.getString(2),  // hoTen
                        rs.getString(3),  // soDienThoai
                        rs.getDouble(4),  // tienTichLuy (đã chuyển từ cột 6 sang cột 4)
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
                        rs.getString(1),  // maKH
                        rs.getString(2),  // hoTen
                        rs.getString(3),  // soDienThoai
                        rs.getDouble(4),  // tienTichLuy (đã chuyển từ cột 6 sang cột 4)
                        rs.getString(5),  // soCanCuoc
                        rs.getString(6),  // email
                        LoaiKhachHang.valueOf(rs.getString(7))  // loaiKhachHang
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
            stmt.setDouble(4, khachHang.getTienTichLuy()); // đã chuyển từ cột 6 sang cột 4
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
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE KhachHang SET hoTen = ?, soDienThoai = ?, tienTichLuy = ?, soCanCuoc = ?, email = ?, loaiKhachHang = ? WHERE maKH = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, khachHang.getHoTen());
            stmt.setString(2, khachHang.getSoDienThoai());
            stmt.setDouble(3, khachHang.getTienTichLuy()); // đã chuyển từ cột 6 sang cột 4
            stmt.setString(4, khachHang.getSoCanCuoc());
            stmt.setString(5, khachHang.getEmail());
            stmt.setString(6, khachHang.getLoaiKhachHang().name());
            stmt.setString(7, maKH);

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
