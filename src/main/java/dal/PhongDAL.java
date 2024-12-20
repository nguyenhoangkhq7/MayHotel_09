/*
    *MayHotel  day creative: 10/15/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here
     */


package dal;

import java.sql.*;
import java.time.LocalDateTime;
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

    // Lấy LoaiPhong từ mã phòng
    public LoaiPhong getLoaiPhongTheoMaPhong(String maPhong) {
        LoaiPhong loaiPhong = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT loaiPhong FROM Phong WHERE maPhong = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maPhong);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String maLoaiPhong = rs.getString("loaiPhong");
                loaiPhong = new LoaiPhongDAL().getLoaiPhongTheoMa(maLoaiPhong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loaiPhong;
    }
    public ArrayList<Phong> getPhongTheoLoaiPhongChuaDuocDat(String maLoaiPhong, LocalDateTime ngayNhanPhong, LocalDateTime ngayTraPhong) {
        ArrayList<Phong> dsPhong = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();

            String sql = """
            SELECT p.*
            FROM Phong p
            LEFT JOIN CT_DonDatPhong_Phong ct ON p.maPhong = ct.maPhong
            LEFT JOIN DonDatPhong ddp ON ct.maDonDatPhong = ddp.maDon
            WHERE p.loaiPhong = ?
              AND (ct.maPhong IS NULL OR (
                    ddp.trangThaiDonDatPhong = N'Đã hoàn tất'
                    OR (
                        ddp.ngayTraPhong <= ?
                        OR ddp.ngayNhanPhong >= ?
                    )
              ))
        """;

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maLoaiPhong);
            stmt.setTimestamp(2, Timestamp.valueOf(ngayNhanPhong));
            stmt.setTimestamp(3, Timestamp.valueOf(ngayTraPhong));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maPhong = rs.getString("maPhong");
                String tenPhong = rs.getString("tenPhong");
                LoaiPhong loaiPhong = new LoaiPhongDAL().getLoaiPhongTheoMa(rs.getString("loaiPhong"));
                boolean trangThaiPhong = rs.getBoolean("trangThaiPhong");
                String moTa = rs.getString("moTa");
                String tang = rs.getString("tang");

                Phong phong = new Phong(maPhong, tenPhong, loaiPhong, trangThaiPhong, moTa, tang);
                dsPhong.add(phong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsPhong;
    }



    // Lấy tất cả phòng theo mã loại phòng
    public ArrayList<Phong> getAllPhongByMaLoaiPhong(String maLoaiPhong) {
        ArrayList<Phong> dsPhongTheoLoai = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM Phong WHERE loaiPhong = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maLoaiPhong);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String maPhong = rs.getString("maPhong");
                String tenPhong = rs.getString("tenPhong");
                LoaiPhong loaiPhong = new LoaiPhongDAL().getLoaiPhongTheoMa(maLoaiPhong);
                boolean trangThaiPhong = rs.getBoolean("trangThaiPhong");
                String moTa = rs.getString("moTa");
                String tang = rs.getString("tang");

                Phong phong = new Phong(maPhong, tenPhong, loaiPhong, trangThaiPhong, moTa, tang);
                dsPhongTheoLoai.add(phong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsPhongTheoLoai;
    }
    // Lấy phòng theo tên phòng
    public Phong getPhongTheoTenPhong(String tenPhong) {
        Phong phong = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM Phong WHERE tenPhong = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, tenPhong);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String maPhong = rs.getString(1);
                LoaiPhong loaiPhong = new LoaiPhongDAL().getLoaiPhongTheoMa(rs.getString(3));
                boolean trangThaiPhong = rs.getBoolean(4);
                String moTa = rs.getString(5);
                String tang = rs.getString(6);

                phong = new Phong(maPhong, tenPhong, loaiPhong, trangThaiPhong, moTa, tang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return phong;
    }


    public ArrayList<Phong> getAllPhong() {
        ArrayList<Phong> dsPhong = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM Phong";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenPhong = rs.getString(2);
                LoaiPhong loaiPhong = new LoaiPhongDAL().getLoaiPhongTheoMa(rs.getString(3));
                boolean trangThaiPhong = rs.getBoolean(4);
                String moTa = rs.getString(5);
                String tang = rs.getString(6);

                Phong phong = new Phong(maPhong, tenPhong, loaiPhong, trangThaiPhong, moTa, tang);
                dsPhong.add(phong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsPhong;
    }

    // Thêm một phòng mới vào cơ sở dữ liệu
    public boolean themPhong(Phong phong) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO Phong (maPhong, tenPhong, loaiPhong, trangThaiPhong, moTa, tang) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, phong.getMaPhong());
            stmt.setString(2, phong.getTenPhong());
            stmt.setString(3, phong.getLoaiPhong().getMaLoaiPhong());
            stmt.setBoolean(4, phong.isTrangThaiPhong());
            stmt.setString(5, phong.getMoTa());
            stmt.setString(6, phong.getTang());

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // lấy phòng từ mã phòng
    public Phong getPhongTheoMa(String maPhong) {
        Phong phong = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM Phong WHERE maPhong = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maPhong);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String tenPhong = rs.getString(2);
                LoaiPhong loaiPhong = new LoaiPhongDAL().getLoaiPhongTheoMa(rs.getString(3));
                boolean trangThaiPhong = rs.getBoolean(4);
                String moTa = rs.getString(5);
                String tang = rs.getString(6);

                phong = new Phong(maPhong, tenPhong, loaiPhong, trangThaiPhong, moTa, tang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phong;
    }

    // Sửa thông tin phòng trong cơ sở dữ liệu
    public boolean suaPhong(Phong phong) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE Phong SET tenPhong = ?, loaiPhong = ?, trangThaiPhong = ?, moTa = ?, tang = ? WHERE maPhong = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, phong.getTenPhong());
            stmt.setString(2, phong.getLoaiPhong().getMaLoaiPhong());
            stmt.setBoolean(3, phong.isTrangThaiPhong());
            stmt.setString(4, phong.getMoTa());
            stmt.setString(5, phong.getTang());
            stmt.setString(6, phong.getMaPhong());

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
 // Xóa phòng theo mã phòng
    public boolean xoaPhong(String maPhong) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            // Truy vấn SQL để xóa phòng theo mã phòng
            String sql = "DELETE FROM Phong WHERE maPhong = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maPhong);

            // Thực thi câu lệnh xóa
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0; // Nếu xóa thành công, trả về true
    }

    public String getLastRoomCode() {
        String lastRoomCode = null;

        String query = "SELECT MAX(maPhong) FROM Phong"; // Truy vấn để lấy mã phòng lớn nhất
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                lastRoomCode = rs.getString(1); // Lấy mã phòng lớn nhất
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return lastRoomCode;
    }
    public static void main(String[] args) {
        PhongDAL dal = new PhongDAL();
        System.out.println(dal.getPhongTheoLoaiPhongChuaDuocDat("LP01", LocalDateTime.of(2024,12,5,14,00), LocalDateTime.of(2024,12,10,12,00)).size());
    }
}
