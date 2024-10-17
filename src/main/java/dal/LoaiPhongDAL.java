/*
    *MayHotel  day creative: 10/15/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here   
     */


package dal;

import entity.LoaiPhong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.ConnectDB;

public class LoaiPhongDAL {
    public ArrayList<LoaiPhong> dsLoaiPhong;
    Connection con;

    public LoaiPhongDAL() {
        dsLoaiPhong = new ArrayList<>();
    }

    // Lấy danh sách tất cả LoaiPhong
    public ArrayList<LoaiPhong> getAllLoaiPhong() {
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM LoaiPhong";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maLoaiPhong = rs.getString(1);
                String tenLoaiPhong = rs.getString(2);
                int sucChua = rs.getInt(3);
                double donGia = rs.getDouble(4);
                String mota = rs.getString(5);
                double chietKhau = rs.getDouble(6);
                // Tạo một đối tượng LoaiPhong mới từ dữ liệu truy vấn
                LoaiPhong loaiPhong = new LoaiPhong(maLoaiPhong, tenLoaiPhong, sucChua, donGia, mota, chietKhau);
                dsLoaiPhong.add(loaiPhong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsLoaiPhong;
    }
    // get LoaiPhong theo mã
    public LoaiPhong getLoaiPhongTheoMa(String maLoaiPhong) {
        LoaiPhong loaiPhong = null; // Khởi tạo biến LoaiPhong
        try {
            ConnectDB.getInstance().connect(); // Kết nối đến cơ sở dữ liệu
            con = ConnectDB.getConnection(); // Lấy kết nối
            String sql = "SELECT * FROM LoaiPhong WHERE maLoaiPhong = ?"; // Câu lệnh SQL với tham số
            PreparedStatement pstmt = con.prepareStatement(sql); // Tạo PreparedStatement
            pstmt.setString(1, maLoaiPhong); // Gán giá trị cho tham số

            ResultSet rs = pstmt.executeQuery(); // Thực hiện truy vấn
            if (rs.next()) { // Kiểm tra kết quả
                // Lấy dữ liệu từ ResultSet
                String tenLoaiPhong = rs.getString(2);
                int sucChua = rs.getInt(3);
                double donGia = rs.getDouble(4);
                String mota = rs.getString(5);
                double chietKhau = rs.getDouble(6);
                // Tạo một đối tượng LoaiPhong mới từ dữ liệu truy vấn
                loaiPhong = new LoaiPhong(maLoaiPhong, tenLoaiPhong, sucChua, donGia, mota, chietKhau);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi
        }
        return loaiPhong; // Trả về đối tượng LoaiPhong
    }

    // Thêm mới LoaiPhong
    public boolean themLoaiPhong(LoaiPhong loaiPhong) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO LoaiPhong VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, loaiPhong.getMaLoaiPhong());
            stmt.setString(2, loaiPhong.getTenLoaiPhong());
            stmt.setInt(3, loaiPhong.getSucChua());
            stmt.setDouble(4, loaiPhong.getDonGia());
            stmt.setString(5, loaiPhong.getMota());
            stmt.setDouble(6, loaiPhong.getChietKhau());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Cập nhật thông tin LoaiPhong
    public boolean suaLoaiPhong(String maLoaiPhong, LoaiPhong loaiPhong) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE LoaiPhong SET tenLoaiPhong = ?, sucChua = ?, donGia = ?, mota = ?, chietKhau = ? WHERE maLoaiPhong = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, loaiPhong.getTenLoaiPhong());
            stmt.setInt(2, loaiPhong.getSucChua());
            stmt.setDouble(3, loaiPhong.getDonGia());
            stmt.setString(4, loaiPhong.getMota());
            stmt.setDouble(5, loaiPhong.getChietKhau());
            stmt.setString(6, maLoaiPhong);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Xóa LoaiPhong theo mã
    public boolean xoaLoaiPhong(String maLoaiPhong) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "DELETE FROM LoaiPhong WHERE maLoaiPhong = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maLoaiPhong);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public static void main(String[] args) {
        LoaiPhongDAL dal = new LoaiPhongDAL();
        // Thực hiện các thao tác kiểm tra ở đây, ví dụ thêm mới, cập nhật, xóa...
        boolean result = dal.themLoaiPhong(new LoaiPhong("LP001", "Phòng Đơn", 2, 500000, "Phòng đơn tiện nghi", 0.1));
        System.out.println("Thêm mới thành công: " + result);
    }
}
