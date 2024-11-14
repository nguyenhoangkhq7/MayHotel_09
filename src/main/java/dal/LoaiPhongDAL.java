package dal;

import java.sql.*;
import java.util.ArrayList;
import database.ConnectDB;
import entity.LoaiPhong;

public class LoaiPhongDAL {
    private Connection con;

    public LoaiPhongDAL() {
        // Constructor mặc định, không lưu trữ danh sách loại phòng trong DAL
    }

    // Lấy tất cả loại phòng từ cơ sở dữ liệu
    public ArrayList<LoaiPhong> getAllLoaiPhong() {
        ArrayList<LoaiPhong> dsLoaiPhong = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM LoaiPhong";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                LoaiPhong loaiPhong = new LoaiPhong(
                        rs.getString(1),  // maLoaiPhong
                        rs.getString(2),  // tenLoaiPhong
                        rs.getInt(3),     // sucChua
                        rs.getDouble(4)   // donGia
                );
                dsLoaiPhong.add(loaiPhong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsLoaiPhong;
    }

    // Lấy loại phòng theo mã loại phòng
    public LoaiPhong getLoaiPhongTheoMa(String maLoaiPhong) {
        LoaiPhong loaiPhong = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM LoaiPhong WHERE maLoaiPhong = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maLoaiPhong);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                loaiPhong = new LoaiPhong(
                        rs.getString(1),  // maLoaiPhong
                        rs.getString(2),  // tenLoaiPhong
                        rs.getInt(3),     // sucChua
                        rs.getDouble(4)   // donGia
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loaiPhong;
    }

    // Thêm loại phòng mới vào cơ sở dữ liệu
    public boolean themLoaiPhong(LoaiPhong loaiPhong) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO LoaiPhong (maLoaiPhong, tenLoaiPhong, sucChua, donGia) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, loaiPhong.getMaLoaiPhong());
            stmt.setString(2, loaiPhong.getTenLoaiPhong());
            stmt.setInt(3, loaiPhong.getSucChua());
            stmt.setDouble(4, loaiPhong.getDonGia());

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Cập nhật loại phòng trong cơ sở dữ liệu
    public boolean capNhatLoaiPhong(LoaiPhong loaiPhong) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE LoaiPhong SET tenLoaiPhong = ?, sucChua = ?, donGia = ? WHERE maLoaiPhong = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, loaiPhong.getTenLoaiPhong());
            stmt.setInt(2, loaiPhong.getSucChua());
            stmt.setDouble(3, loaiPhong.getDonGia());
            stmt.setString(4, loaiPhong.getMaLoaiPhong());

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Xóa loại phòng khỏi cơ sở dữ liệu
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

    // Đóng kết nối sau khi thực hiện các thao tác
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
        LoaiPhongDAL dal = new LoaiPhongDAL();

        // Thêm loại phòng mới
        LoaiPhong loaiPhong1 = new LoaiPhong("LP01", "Phòng Đơn", 2, 500000);
        if (dal.themLoaiPhong(loaiPhong1)) {
            System.out.println("Thêm loại phòng thành công");
        }

        // Lấy tất cả loại phòng
        ArrayList<LoaiPhong> loaiPhongs = dal.getAllLoaiPhong();
        for (LoaiPhong lp : loaiPhongs) {
            System.out.println(lp);
        }

        // Lấy loại phòng theo mã
        LoaiPhong loaiPhong = dal.getLoaiPhongTheoMa("LP01");
        if (loaiPhong != null) {
            System.out.println("Loại phòng tìm được: " + loaiPhong);
        }

        // Cập nhật loại phòng
        loaiPhong.setTenLoaiPhong("Phòng Đôi");
        loaiPhong.setSucChua(4);
        if (dal.capNhatLoaiPhong(loaiPhong)) {
            System.out.println("Cập nhật loại phòng thành công");
        }

        // Xóa loại phòng
        if (dal.xoaLoaiPhong("LP01")) {
            System.out.println("Xóa loại phòng thành công");
        }
    }
}
