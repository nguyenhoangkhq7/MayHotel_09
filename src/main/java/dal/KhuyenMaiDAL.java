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
import entity.KhuyenMai;

public class KhuyenMaiDAL {
    public ArrayList<KhuyenMai> dsKhuyenMai;
    Connection con;

    public KhuyenMaiDAL() {
        dsKhuyenMai = new ArrayList<>();
    }

    // Lấy tất cả các danh sách khuyến mãi từ cơ sở dữ liệu
    public ArrayList<KhuyenMai> getAllKhuyenMai() {
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM KhuyenMai";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maKhuyenMai = rs.getString(1);
                String tenKhuyenMai = rs.getString(2);
                double giaTri = rs.getDouble(3);
                LocalDate ngayTao = rs.getDate(4) != null ? rs.getDate(4).toLocalDate() : null;
                boolean conHoatDong = rs.getBoolean(5);
                int soLuong = rs.getInt(6);
                LocalDate ngayHetHan = rs.getDate(7) != null ? rs.getDate(7).toLocalDate() : null;
                String dieuKienApDung = rs.getString(8);
                KhuyenMai khuyenMai = new KhuyenMai(maKhuyenMai, tenKhuyenMai, giaTri, ngayTao, conHoatDong, soLuong, ngayHetHan, dieuKienApDung);
                dsKhuyenMai.add(khuyenMai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dsKhuyenMai;
    }

    // Thêm một khuyến mãi mới vào cơ sở dữ liệu
    public boolean themKhuyenMai(KhuyenMai khuyenMai) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO KhuyenMai (maKhuyenMai, tenKhuyenMai, giaTri, ngayTao, conHoatDong, soLuong,donViTinh,moTa, ngayHetHan, dieuKienApDung) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, khuyenMai.getMaKhuyenMai()); // Mã khuyến mãi
            stmt.setString(2, khuyenMai.getTenKhuyenMai()); // Tên khuyến mãi
            stmt.setDouble(3, khuyenMai.getGiaTri()); // Giá trị khuyến mãi
            stmt.setDate(4, Date.valueOf(khuyenMai.getNgayTao())); // Ngày tạo
            stmt.setBoolean(5, khuyenMai.isConHoatDong()); // Trạng thái hoạt động
            stmt.setInt(6, khuyenMai.getSoLuong()); // Số lượng khuyến mãi
            stmt.setString(7, khuyenMai.getDonViTinh()); // Đơn vị tính
            stmt.setString(8, khuyenMai.getMoTa()); // Mô tả khuyến mãi
            stmt.setDate(9, Date.valueOf(khuyenMai.getNgayHetHan())); // Ngày hết hạn
            stmt.setString(10, khuyenMai.getKhachHangApDung()); // Khách hàng áp dụng

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return n > 0;
    }

// Sửa thông tin khuyến mãi trong cơ sở dữ liệu
public boolean suaKhuyenMai(String maKhuyenMai, KhuyenMai khuyenMai) {
    int n = 0;
    try {
        ConnectDB.getInstance().connect();
        con = ConnectDB.getConnection();
        String sql = "UPDATE KhuyenMai SET tenKhuyenMai = ?, giaTri = ?, ngayTao = ?, conHoatDong = ?, soLuong = ?, ngayHetHan = ?, dieuKienApDung = ? WHERE maKM = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, khuyenMai.getTenKhuyenMai());
        stmt.setDouble(2, khuyenMai.getGiaTri());
        stmt.setDate(3, Date.valueOf(khuyenMai.getNgayTao()));
        stmt.setBoolean(4, khuyenMai.isConHoatDong());
        stmt.setInt(5, khuyenMai.getSoLuong());
        stmt.setDate(6, Date.valueOf(khuyenMai.getNgayHetHan()));
        stmt.setString(7, khuyenMai.getKhachHangApDung());
        stmt.setString(8, maKhuyenMai);  // Fixed the order here

        n = stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeConnection();
    }
    return n > 0;
}

    // Tìm khuyến mãi theo mã khuyến mãi
public KhuyenMai getKhuyenMaiTheoMa(String maKhuyenMai) {
    KhuyenMai khuyenMai = null;
    Connection con = null; // Khai báo con ở đây
    PreparedStatement stmt = null; // Khai báo stmt ở đây
    ResultSet rs = null; // Khai báo rs ở đây

    try {
        ConnectDB.getInstance().connect();
        con = ConnectDB.getConnection();
        String sql = "SELECT * FROM KhuyenMai WHERE maKhuyenMai = ?"; // Đảm bảo tên cột đúng
        stmt = con.prepareStatement(sql);
        stmt.setString(1, maKhuyenMai);
        rs = stmt.executeQuery();
        if (rs.next()) {
            String tenKhuyenMai = rs.getString("tenKhuyenMai");
            double giaTri = rs.getDouble("giaTri");
            LocalDate ngayTao = rs.getDate("ngayTao").toLocalDate();
            boolean conHoatDong = rs.getBoolean("conHoatDong");
            int soLuong = rs.getInt("soLuong");
            LocalDate ngayHetHan = rs.getDate("ngayHetHan").toLocalDate();
            String dieuKienApDung = rs.getString("khachHangApDung"); // Kiểm tra tên cột
            String donViTinh = rs.getString("donViTinh"); // Lấy thông tin donViTinh nếu có
            String moTa = rs.getString("moTa"); // Lấy thông tin moTa nếu có

            // Tạo đối tượng KhuyenMai với các tham số đầy đủ
            khuyenMai = new KhuyenMai(maKhuyenMai, tenKhuyenMai, giaTri, ngayTao, conHoatDong, soLuong, ngayHetHan, dieuKienApDung);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close(); // Đóng ResultSet
            if (stmt != null) stmt.close(); // Đóng PreparedStatement
            if (con != null) con.close(); // Đóng Connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return khuyenMai;
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

    public static void main(String[] args) {

    }
}