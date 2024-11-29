package dal;

import entity.ChiTiet_DonDatPhong_Phong_DichVu;
import entity.DichVu;
import entity.ChiTiet_DonDatPhong_Phong;
import database.ConnectDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ChiTiet_DonDatPhong_Phong_DichVuDAL {
    private Connection con;

    public ChiTiet_DonDatPhong_Phong_DichVuDAL() {
    }

    // Helper method to handle connection and setup
    private Connection getConnection() throws SQLException {
        ConnectDB.getInstance().connect();
        return ConnectDB.getConnection();
    }

    // Get all ChiTiet records
    public ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> getAllChiTietDonDatPhongPhongDichVu() {
        ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> chiTietList = new ArrayList<>();
        String sql = "SELECT * FROM CT_DonDatPhong_Phong_DichVu";

        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String maDonDatPhong = rs.getString("maDonDatPhong");
                String maPhong = rs.getString("maPhong");
                String maDichVu = rs.getString("maDichVu");
                int soLuongDat = rs.getInt("soLuongDat");
                LocalDateTime ngayTao = rs.getTimestamp("ngayTao").toLocalDateTime();
                String moTa = rs.getString("moTa");

                // Lấy đối tượng DichVu và ChiTiet_DonDatPhong_Phong từ các mã
                DichVu dichVu = new DichVuDAL().getDichVuTheoMa(maDichVu);
                ChiTiet_DonDatPhong_Phong chiTiet = new ChiTiet_DonDatPhong_PhongDAL().getChiTietDonDatPhongPhongTheoMa(maDonDatPhong, maPhong);

                // Thêm vào danh sách kết quả
                chiTietList.add(new ChiTiet_DonDatPhong_Phong_DichVu(soLuongDat, ngayTao, dichVu, chiTiet.getDonDatPhong(), chiTiet.getPhong(), moTa));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }

        return chiTietList;
    }

    // Get ChiTiet by maDonDatPhong, maPhong, maDichVu
    public ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> getDSChiTietDonDatPhongPhongDichVuTheoMa(String maDonDatPhong, String maPhong, String maDichVu) {
        ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> chiTietList = new ArrayList<>();
        String sql = "SELECT * FROM CT_DonDatPhong_Phong_DichVu WHERE maDonDatPhong = ? AND maPhong = ? AND maDichVu = ?";

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, maDonDatPhong);
            pstmt.setString(2, maPhong);
            pstmt.setString(3, maDichVu);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int soLuongDat = rs.getInt("soLuongDat");
                    LocalDateTime ngayTao = rs.getTimestamp("ngayTao").toLocalDateTime();
                    String moTa = rs.getString("moTa");

                    // Lấy đối tượng DichVu và ChiTiet_DonDatPhong_Phong từ DAL
                    DichVu dichVu = new DichVuDAL().getDichVuTheoMa(maDichVu);
                    ChiTiet_DonDatPhong_Phong chiTietDonDatPhongPhong = new ChiTiet_DonDatPhong_PhongDAL().getChiTietDonDatPhongPhongTheoMa(maDonDatPhong, maPhong);

                    // Thêm chi tiết vào danh sách
                    chiTietList.add(new ChiTiet_DonDatPhong_Phong_DichVu(soLuongDat, ngayTao, dichVu, chiTietDonDatPhongPhong.getDonDatPhong(), chiTietDonDatPhongPhong.getPhong(), moTa));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }

        return chiTietList;
    }
    public ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> getDSChiTietDonDatPhongPhongDichVuTheoMaDonDatPhongMaPhong(String maDonDatPhong, String maPhong) {
        ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> chiTietList = new ArrayList<>();
        String sql = "SELECT * FROM CT_DonDatPhong_Phong_DichVu WHERE maDonDatPhong = ? AND maPhong = ?";

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, maDonDatPhong);
            pstmt.setString(2, maPhong);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int soLuongDat = rs.getInt("soLuongDat");
                    LocalDateTime ngayTao = rs.getTimestamp("ngayTao").toLocalDateTime();
                    String moTa = rs.getString("moTa");
                    String maDichVu = rs.getString("maDichVu");
                    // Lấy đối tượng DichVu và ChiTiet_DonDatPhong_Phong từ DAL
                    DichVu dichVu = new DichVuDAL().getDichVuTheoMa(maDichVu);
                    ChiTiet_DonDatPhong_Phong chiTietDonDatPhongPhong = new ChiTiet_DonDatPhong_PhongDAL().getChiTietDonDatPhongPhongTheoMa(maDonDatPhong, maPhong);

                    // Thêm chi tiết vào danh sách
                    chiTietList.add(new ChiTiet_DonDatPhong_Phong_DichVu(soLuongDat, ngayTao, dichVu, chiTietDonDatPhongPhong.getDonDatPhong(), chiTietDonDatPhongPhong.getPhong(), moTa));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }

        return chiTietList;
    }

    // Insert new record
    public boolean themChiTiet(ChiTiet_DonDatPhong_Phong_DichVu chiTiet) {
        String sql = "INSERT INTO CT_DonDatPhong_Phong_DichVu (soLuongDat, ngayTao, maDichVu, maDonDatPhong, maPhong, moTa) VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, chiTiet.getSoLuongDat());
            stmt.setTimestamp(2, Timestamp.valueOf(chiTiet.getNgayTao()));
            stmt.setString(3, chiTiet.getDichVu().getMaDichVu());
            stmt.setString(4, chiTiet.getDonDatPhong().getMaDon());
            stmt.setString(5, chiTiet.getPhong().getMaPhong());
            stmt.setString(6, chiTiet.getMoTa());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi: " + e.getMessage());
            return false;
        }
    }

    // Update ChiTiet
    public boolean suaChiTiet(String maDonDatPhong, String maPhong, String maDichVu, ChiTiet_DonDatPhong_Phong_DichVu chiTiet) {
        String sql = "UPDATE CT_DonDatPhong_Phong_DichVu SET soLuongDat = ?, moTa = ? WHERE maDonDatPhong = ? AND maPhong = ? AND maDichVu = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, chiTiet.getSoLuongDat());
            stmt.setString(2, chiTiet.getMoTa());
            stmt.setString(3, maDonDatPhong);
            stmt.setString(4, maPhong);
            stmt.setString(5, maDichVu);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi: " + e.getMessage());
            return false;
        }
    }
}
