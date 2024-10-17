/*
    *MayHotel  day creative: 10/15/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here   
     */


package dal;

import entity.ChiTiet_DonDatPhong_Phong;
import entity.DonDatPhong;
import entity.Phong;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import database.ConnectDB;

public class ChiTiet_DonDatPhong_PhongDAL {
    private ArrayList<ChiTiet_DonDatPhong_Phong> dsChiTiet;
    private Connection con;

    public ChiTiet_DonDatPhong_PhongDAL() {
        dsChiTiet = new ArrayList<>();
    }

    // Lấy danh sách tất cả ChiTiet_DonDatPhong_Phong
//    public ArrayList<ChiTiet_DonDatPhong_Phong> getAllChiTiet() {
//        try {
//            ConnectDB.getInstance().connect();
//            con = ConnectDB.getConnection();
//            String sql = "SELECT * FROM ChiTiet_DonDatPhong_Phong";
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                String maCT_DDP_P = rs.getString(1);
//                LocalDate ngayTra = rs.getDate(2).toLocalDate();
//                String maDonDatPhong = rs.getString(3);
//                String maPhong = rs.getString(4);
//                LocalDate ngayNhanPhong = rs.getDate(5).toLocalDate();
//
//                // Tạo các đối tượng DonDatPhong và Phong từ mã
//                DonDatPhong donDatPhong = new DonDatPhongDAL().getDonDatPhongTheoMa(maDonDatPhong);
//                Phong phong = new PhongDAL().getPhongTheoMa(maPhong);
//
//                // Tạo đối tượng ChiTiet_DonDatPhong_Phong từ dữ liệu truy vấn
//                ChiTiet_DonDatPhong_Phong chiTiet = new ChiTiet_DonDatPhong_Phong(maCT_DDP_P, ngayTra, donDatPhong, phong, ngayNhanPhong);
//                dsChiTiet.add(chiTiet);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return dsChiTiet;
//    }

    // Thêm mới ChiTiet_DonDatPhong_Phong
    public boolean themChiTiet(ChiTiet_DonDatPhong_Phong chiTiet) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO ChiTiet_DonDatPhong_Phong VALUES(?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, chiTiet.getMaCT_DDP_P());
            stmt.setDate(2, java.sql.Date.valueOf(chiTiet.getNgayTra()));
            stmt.setString(3, chiTiet.getDonDatPhong().getMaDonDatPhong());
            stmt.setString(4, chiTiet.getPhong().getMaPhong());
            stmt.setDate(5, java.sql.Date.valueOf(chiTiet.getNgayNhanPhong()));
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Cập nhật ChiTiet_DonDatPhong_Phong
    public boolean suaChiTiet(String maCT_DDP_P, ChiTiet_DonDatPhong_Phong chiTiet) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE ChiTiet_DonDatPhong_Phong SET ngayTra = ?, maDonDatPhong = ?, maPhong = ?, ngayNhanPhong = ? WHERE maCT_DDP_P = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf(chiTiet.getNgayTra()));
            stmt.setString(2, chiTiet.getDonDatPhong().getMaDonDatPhong());
            stmt.setString(3, chiTiet.getPhong().getMaPhong());
            stmt.setDate(4, java.sql.Date.valueOf(chiTiet.getNgayNhanPhong()));
            stmt.setString(5, maCT_DDP_P);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Xóa ChiTiet_DonDatPhong_Phong theo mã
    public boolean xoaChiTiet(String maCT_DDP_P) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "DELETE FROM ChiTiet_DonDatPhong_Phong WHERE maCT_DDP_P = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maCT_DDP_P);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public static void main(String[] args) {
        ChiTiet_DonDatPhong_PhongDAL dal = new ChiTiet_DonDatPhong_PhongDAL();
        // Thực hiện các thao tác kiểm tra ở đây, ví dụ thêm mới, cập nhật, xóa...
        boolean result = dal.themChiTiet(new ChiTiet_DonDatPhong_Phong("CT001", LocalDate.now().plusDays(3), new DonDatPhong("DDP001"), new Phong("P001"), LocalDate.now()));
        System.out.println("Thêm mới thành công: " + result);
    }
}

