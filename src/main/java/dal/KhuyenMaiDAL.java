package dal;

import entity.KhuyenMai;
import database.ConnectDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
        String sql = "INSERT INTO KhuyenMai (maKhuyenMai, tenKhuyenMai, giaTri, ngayBatDau, conHoatDong, soLuong, ngayKetThuc, loaiKhachHangApDung) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        // Mở kết nối và thực thi câu lệnh SQL
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            // Set parameters for the PreparedStatement
            stmt.setString(1, khuyenMai.getMaKhuyenMai());
            stmt.setString(2, khuyenMai.getTenKhuyenMai());
            stmt.setDouble(3, khuyenMai.getGiaTri());
            stmt.setTimestamp(4, Timestamp.valueOf(khuyenMai.getNgayBatDau()));
            stmt.setBoolean(5, khuyenMai.isConHoatDong());
            stmt.setInt(6, khuyenMai.getSoLuong());
            stmt.setTimestamp(7, Timestamp.valueOf(khuyenMai.getNgayKetThuc()));
            stmt.setString(8, khuyenMai.getLoaiKhachHangApDung());

            // In ra câu lệnh SQL và các tham số để debug
            

            // Thực thi câu lệnh
            n = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception: " + e.getMessage());
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
    public String getLastKhuyenMaiCode() {
        String lastKhuyenMaiCode = null;

        String query = "SELECT MAX(maKhuyenMai) FROM KhuyenMai"; // Truy vấn để lấy mã phòng lớn nhất
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
            	lastKhuyenMaiCode = rs.getString(1); // Lấy mã phòng lớn nhất
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return lastKhuyenMaiCode;
    }

    // Xóa khuyến mãi khỏi cơ sở dữ liệu
    public boolean xoaKhuyenMai(String maKhuyenMai) {
        int n = 0;
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            // Mở kết nối cơ sở dữ liệu
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            
            // Câu lệnh SQL cập nhật khuyến mãi
            String sql = "UPDATE KhuyenMai SET conHoatDong = 0 WHERE maKhuyenMai = ?";
            stmt = con.prepareStatement(sql);
            
            // Gán giá trị tham số cho câu lệnh SQL
            stmt.setString(1, maKhuyenMai);
            
            // Thực hiện câu lệnh cập nhật
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            // Ghi lỗi ra console hoặc log
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa khuyến mãi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Đảm bảo đóng kết nối và PreparedStatement để tránh rò rỉ tài nguyên
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        // Trả về true nếu cập nhật thành công, false nếu không thành công
        return n > 0;
    }



    public static void main(String[] args) {

    }
}
