package dal;

import entity.KhuyenMai;
import database.ConnectDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class KhuyenMaiDAL {
	 public ArrayList<KhuyenMai> dsKhuyenMai;
	    Connection con;

	    public KhuyenMaiDAL() {
	        dsKhuyenMai = new ArrayList<>();
	    }

    // Lấy tất cả khuyến mãi từ cơ sở dữ liệu
    public ArrayList<KhuyenMai> getAllKhuyenMai() {
        ArrayList<KhuyenMai> dsKhuyenMai = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM KhuyenMai";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String maKhuyenMai = rs.getString("maKhuyenMai");
                String tenKhuyenMai = rs.getString("tenKhuyenMai");
                double giaTri = rs.getDouble("giaTri");
                LocalDateTime ngayBatDau = rs.getTimestamp("ngayBatDau").toLocalDateTime();
                boolean conHoatDong = rs.getBoolean("conHoatDong");
                int soLuong = rs.getInt("soLuong");
                LocalDateTime ngayKetThuc = rs.getTimestamp("ngayKetThuc").toLocalDateTime();
                String loaiKhachHangApDung = rs.getString("loaiKhachHangApDung");

                KhuyenMai khuyenMai = new KhuyenMai(maKhuyenMai, tenKhuyenMai, giaTri, ngayBatDau,
                        conHoatDong, soLuong, ngayKetThuc, loaiKhachHangApDung);
                dsKhuyenMai.add(khuyenMai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsKhuyenMai;
    }

    // Lấy khuyến mãi theo mã khuyến mãi
    public KhuyenMai getKhuyenMaiTheoMa(String maKhuyenMai) {
        KhuyenMai khuyenMai = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM KhuyenMai WHERE maKhuyenMai = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maKhuyenMai);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String tenKhuyenMai = rs.getString("tenKhuyenMai");
                double giaTri = rs.getDouble("giaTri");
                LocalDateTime ngayBatDau = rs.getTimestamp("ngayBatDau").toLocalDateTime();
                boolean conHoatDong = rs.getBoolean("conHoatDong");
                int soLuong = rs.getInt("soLuong");
                LocalDateTime ngayKetThuc = rs.getTimestamp("ngayKetThuc").toLocalDateTime();
                String loaiKhachHangApDung = rs.getString("loaiKhachHangApDung");

                khuyenMai = new KhuyenMai(maKhuyenMai, tenKhuyenMai, giaTri, ngayBatDau,
                        conHoatDong, soLuong, ngayKetThuc, loaiKhachHangApDung);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khuyenMai;
    }

    // Thêm khuyến mãi mới vào cơ sở dữ liệu
    public boolean themKhuyenMai(KhuyenMai khuyenMai) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO KhuyenMai (maKhuyenMai, tenKhuyenMai, giaTri, ngayBatDau, conHoatDong, soLuong, ngayKetThuc, loaiKhachHangApDung) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, khuyenMai.getMaKhuyenMai());
            stmt.setString(2, khuyenMai.getTenKhuyenMai());
            stmt.setDouble(3, khuyenMai.getGiaTri());
            stmt.setTimestamp(4, Timestamp.valueOf(khuyenMai.getNgayBatDau()));
            stmt.setBoolean(5, khuyenMai.isConHoatDong());
            stmt.setInt(6, khuyenMai.getSoLuong());
            stmt.setTimestamp(7, Timestamp.valueOf(khuyenMai.getNgayKetThuc()));
            stmt.setString(8, khuyenMai.getLoaiKhachHangApDung());

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Cập nhật khuyến mãi trong cơ sở dữ liệu
    public boolean capNhatKhuyenMai(KhuyenMai khuyenMai) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE KhuyenMai SET tenKhuyenMai = ?, giaTri = ?, ngayBatDau = ?, conHoatDong = ?, soLuong = ?, ngayKetThuc = ?, loaiKhachHangApDung = ? WHERE maKhuyenMai = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, khuyenMai.getTenKhuyenMai());
            stmt.setDouble(2, khuyenMai.getGiaTri());
            stmt.setTimestamp(3, Timestamp.valueOf(khuyenMai.getNgayBatDau()));
            stmt.setBoolean(4, khuyenMai.isConHoatDong());
            stmt.setInt(5, khuyenMai.getSoLuong());
            stmt.setTimestamp(6, Timestamp.valueOf(khuyenMai.getNgayKetThuc()));
            stmt.setString(7, khuyenMai.getLoaiKhachHangApDung());
            stmt.setString(8, khuyenMai.getMaKhuyenMai());

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Xóa khuyến mãi khỏi cơ sở dữ liệu
    public boolean xoaKhuyenMai(String maKhuyenMai) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE KhuyenMai SET conHoatDong = 0 WHERE maKhuyenMai = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maKhuyenMai);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }


    public static void main(String[] args) {

    }
}
