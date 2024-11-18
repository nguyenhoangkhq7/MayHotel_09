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
    public static void main(String[] args) {
        PhongDAL dal = new PhongDAL();


        // Lấy tất cả loại phòng
        ArrayList<Phong> phongs = dal.getAllPhong();
        for (Phong p : phongs) {
            System.out.println(p);
        }
//        Phong phong = dal.getPhongTheoTenPhong("Phòng Delux King 1");
//        System.out.println(phong);


    }
}
