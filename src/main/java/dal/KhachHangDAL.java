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

            // Nếu có kết quả, tạo đối tượng KhachHang từ dữ liệu truy vấn
            if (rs.next()) {
                String ma = rs.getString(1);
                String hoTen = rs.getString(2);
                String soDienThoai = rs.getString(3);
                String soCanCuoc = rs.getString(4);
                String email = rs.getString(5);
                double tienTichLuy = rs.getDouble(6);
                LoaiKhachHang loaiKhachHang = LoaiKhachHang.valueOf(rs.getString(10)); // Chuyển đổi từ String thành enum

                // Tạo đối tượng KhachHang với dữ liệu từ cơ sở dữ liệu
                khachHang = new KhachHang(ma, hoTen, soDienThoai, soCanCuoc, email, tienTichLuy, loaiKhachHang);
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
                // Lấy dữ liệu
                String maKH = rs.getString(1);
                String hoTen = rs.getString(2);
                String soDienThoai = rs.getString(3);
                String soCanCuoc = rs.getString(4);
                String email = rs.getString(5);
                double tienTichLuy = rs.getDouble(6);
                LoaiKhachHang loaiKhachHang = LoaiKhachHang.valueOf(rs.getString(10));       // Chuyển đổi từ String thành enum 
                // Tạo đối tượng KhachHang
                KhachHang khachHang = new KhachHang(maKH, hoTen, soDienThoai, soCanCuoc, email, tienTichLuy, loaiKhachHang);
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
            String sql = "INSERT INTO KhachHang (maKH, hoTen, soDienThoai, soCanCuoc, email, tienTichLuy, loaiKhachHang) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, khachHang.getMaKH());
            stmt.setString(2, khachHang.getHoTen());
            stmt.setString(3, khachHang.getSoDienThoai());
            stmt.setString(4, khachHang.getSoCanCuoc());
            stmt.setString(5, khachHang.getEmail());
            stmt.setDouble(6, khachHang.getTienTichLuy());
            // Chuyển đổi từ enum thành String
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
            String sql = "UPDATE KhachHang SET hoTen = ?, soDienThoai = ?, soCanCuoc = ?, email = ?, tienTichLuy = ?, loaiKhachHang = ? WHERE maKH = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, khachHang.getHoTen());
            stmt.setString(2, khachHang.getSoDienThoai());
            stmt.setString(3, khachHang.getSoCanCuoc());
            stmt.setString(4, khachHang.getEmail());
            stmt.setDouble(5, khachHang.getTienTichLuy());
            // Chuyển đổi từ enum thành String
            stmt.setString(6, khachHang.getLoaiKhachHang().name());
            stmt.setString(7, maKH);

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public static void main(String[] args) {
        KhachHangDAL khachHangDAL = new KhachHangDAL();
        ArrayList<KhachHang> danhSachKhachHang = khachHangDAL.getAllKhachHang();
        
        // Duyệt qua danh sách và in ra thông tin từng khách hàng
        for (KhachHang khachHang : danhSachKhachHang) {
            System.out.println("Mã khách hàng: " + khachHang.getMaKH());
            System.out.println("Họ tên: " + khachHang.getHoTen());
            System.out.println("Số điện thoại: " + khachHang.getSoDienThoai());
            System.out.println("Số căn cước: " + khachHang.getSoCanCuoc());
            System.out.println("Email: " + khachHang.getEmail());
            System.out.println("Tiền tích lũy: " + khachHang.getTienTichLuy());
            System.out.println("Loại khách hàng: " + khachHang.getLoaiKhachHang());
            System.out.println("-------------------------------------");
        }

    }
}
