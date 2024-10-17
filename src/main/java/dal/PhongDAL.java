/*
    *MayHotel  day creative: 10/15/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here
     */


package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.ConnectDB;
import entity.LoaiPhong;
import entity.Phong;

public class PhongDAL {
    public ArrayList<Phong> dsPhong;
    Connection con;
    public PhongDAL() {
        dsPhong = new ArrayList<>();
    }

    // Lấy tất cả các phòng từ cơ sở dữ liệu
    public ArrayList<Phong> getAllPhong() {
        ArrayList<Phong> dsPhong = new ArrayList<>(); // Khởi tạo danh sách Phong
        try {
            ConnectDB.getInstance().connect(); // Kết nối đến cơ sở dữ liệu
            con = ConnectDB.getConnection(); // Lấy kết nối
            String sql = "SELECT * FROM Phong"; // Câu lệnh SQL
            Statement stmt = con.createStatement(); // Tạo Statement
            ResultSet rs = stmt.executeQuery(sql); // Thực hiện truy vấn

            while (rs.next()) { // Duyệt qua từng kết quả trong ResultSet
                String maPhong = rs.getString(1); // Lấy mã phòng
                String tenPhong = rs.getString(2); // Lấy tên phòng
                String tang = rs.getString(3); // Lấy tầng
                LoaiPhong loaiPhong = new LoaiPhongDAL().getLoaiPhongTheoMa(rs.getString(4)); // Lấy loại phòng theo mã
                boolean trangThaiPhong = rs.getBoolean(5); // Lấy trạng thái phòng
                String moTa = rs.getString(6); // Lấy mô tả

                // Tạo một đối tượng Phong mới từ dữ liệu truy vấn
                Phong phong = new Phong(maPhong, tenPhong, loaiPhong, trangThaiPhong, moTa, tang);
                dsPhong.add(phong); // Thêm phòng vào danh sách
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi
        }
        return dsPhong; // Trả về danh sách các phòng
    }

    // Thêm một phòng mới vào cơ sở dữ liệu
    public boolean themPhong(Phong phong) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO Phong (maPhong, tenPhong, loaiPhong, trangThaiPhong, moTa) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, phong.getMaPhong());
            stmt.setString(2, phong.getTenPhong());
            stmt.setString(3, phong.getLoaiPhong().getMaLoaiPhong());
            stmt.setBoolean(4, phong.isTrangThaiPhong());
            stmt.setString(5, phong.getMoTa());

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
    // lấy phòng từ mã phòng
    public Phong getPhongTheoMa(String maPhong) {
        Phong phong = null; // Khởi tạo biến Phong
        try {
            ConnectDB.getInstance().connect(); // Kết nối đến cơ sở dữ liệu
            con = ConnectDB.getConnection(); // Lấy kết nối
            String sql = "SELECT * FROM Phong WHERE maPhong = ?"; // Câu lệnh SQL với tham số
            PreparedStatement stmt = con.prepareStatement(sql); // Tạo PreparedStatement
            stmt.setString(1, maPhong); // Gán giá trị cho tham số

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String tenPhong = rs.getString(2);
                LoaiPhong loaiPhong = new LoaiPhongDAL().getLoaiPhongTheoMa(rs.getString(3)); // Lấy loại phòng theo mã
                boolean trangThaiPhong = rs.getBoolean(4);
                String moTa = rs.getString(5);
                String tang = rs.getString(6);
                // Tạo một đối tượng Phong mới từ dữ liệu truy vấn
                phong = new Phong(maPhong, tenPhong, loaiPhong, trangThaiPhong, moTa, tang);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi
        }
        return phong; // Trả về đối tượng Phong
    }

    // Sửa thông tin phòng trong cơ sở dữ liệu
    public boolean suaPhong(String maPhong, Phong phong) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE Phong SET tenPhong = ?, loaiPhong = ?, trangThaiPhong = ?, moTa = ? WHERE maPhong = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, phong.getTenPhong());
            stmt.setString(2, phong.getLoaiPhong().toString()); // chuyển đổi enum sang chuỗi
            stmt.setBoolean(3, phong.isTrangThaiPhong());
            stmt.setString(4, phong.getMoTa());
            stmt.setString(5, maPhong);

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Xóa phòng khỏi cơ sở dữ liệu
    public boolean xoaPhong(String maPhong) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "DELETE FROM Phong WHERE maPhong = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maPhong);

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public static void main(String[] args) {
        PhongDAL phongDAL = new PhongDAL();
        phongDAL.xoaPhong("P002");
    }
}
