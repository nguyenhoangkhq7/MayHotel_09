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
            	LocalDate ngayTao = rs.getDate(4)!= null ? rs.getDate(4).toLocalDate() : null;
                Boolean conHoatDong = rs.getBoolean(5);
                int soLuong = rs.getInt(6);
                LocalDate ngayHetHan = rs.getDate(7)!= null ? rs.getDate(7).toLocalDate() : null;
                KhuyenMai khuyenMai = new KhuyenMai(maKhuyenMai, tenKhuyenMai, giaTri, ngayTao, conHoatDong, soLuong, ngayHetHan);
                dsKhuyenMai.add(khuyenMai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsKhuyenMai;
    }

    // Thêm một khuyến mãi mới vào cơ sở dữ liệu
    public boolean themKhuyenMai(KhuyenMai khuyenMai) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO KhuyenMai (maKM, tenKhuyenMai, giaTri, ngayTao, conHoatDong, soLuong, ngayHetHan) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, khuyenMai.getMaKhuyenMai());
            stmt.setString(2, khuyenMai.getTenKhuyenMai());
            stmt.setDouble(3, khuyenMai.getGiaTri()); 
            stmt.setDate(4, Date.valueOf(khuyenMai.getNgayTao()));
            stmt.setBoolean(5, khuyenMai.getConHoatDong());
            stmt.setInt(6, khuyenMai.getSoLuong());
            stmt.setDate(7, Date.valueOf(khuyenMai.getNgayHetHan()));
            

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Sửa thông tin khuyến mãi trong cơ sở dữ liệu
    public boolean suaKhuyenMai(String maKhuyenMai, KhuyenMai khuyenMai) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE KhuyenMai SET tenKhuyenMai = ?, giaTri = ?, ngayTao = ?, conHoatDong = ?, soLuong = ?, ngayHetHan = ? WHERE maKM = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, khuyenMai.getTenKhuyenMai());
            stmt.setDouble(2, khuyenMai.getGiaTri()); 
            stmt.setDate(3, Date.valueOf(khuyenMai.getNgayTao()));
            stmt.setBoolean(4, khuyenMai.getConHoatDong());
            stmt.setInt(5, khuyenMai.getSoLuong());
            stmt.setDate(6, Date.valueOf(khuyenMai.getNgayHetHan()));
            stmt.setString(7, khuyenMai.getMaKhuyenMai());

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Xóa khuyến mãi khỏi cơ sở dữ liệu
//    public boolean xoaKhuyenMai(String maKhuyenMai) {
//        int n = 0;
//        try {
//            ConnectDB.getInstance().connect();
//            con = ConnectDB.getConnection();
//            String sql = "DELETE FROM KhuyenMai WHERE maKM = ?";
//            PreparedStatement stmt = con.prepareStatement(sql);
//            stmt.setString(1, maKhuyenMai);
//
//            n = stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return n > 0;
//    }

    public static void main(String[] args) {
          KhuyenMaiDAL khuyenMaiDAL = new KhuyenMaiDAL();
//          khuyenMaiDAL.themKhuyenMai(new KhuyenMai("KM006", "Giam 70%", 0.7, LocalDate.now(), true, 2, LocalDate.now()));
// in danh sach khuyen mai
//   	   ArrayList<KhuyenMai> dsKhuyenMai = khuyenMaiDAL.getAllKhuyenMai();
//        for (KhuyenMai khuyenMai : dsKhuyenMai) {
//            System.out.println(khuyenMai);
//    }
// test suakm          
          khuyenMaiDAL.suaKhuyenMai("H006", new KhuyenMai("KM006", "Giam 80%", 0.8, LocalDate.now(), true, 2, LocalDate.now()));
    }

}
