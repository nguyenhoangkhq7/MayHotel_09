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
    // Get ChiTiet by maPhong
    public ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> getDanhSachChiTietTheoMa(String maDonDatPhong , String maPhong) {
        ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> chiTietList = new ArrayList<>();
        String sql = "SELECT * FROM CT_DonDatPhong_Phong_DichVu WHERE maDonDatPhong = ? AND maPhong = ?";

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            // Gán giá trị cho tham số maPhong
            pstmt.setString(1, maDonDatPhong);
            pstmt.setString(2, maPhong);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int soLuongDat = rs.getInt("soLuongDat");
                    LocalDateTime ngayTao = rs.getTimestamp("ngayTao").toLocalDateTime();
                    String moTa = rs.getString("moTa");
                    String maDichVu = rs.getString("maDichVu");

                    // Lấy các đối tượng liên quan từ DAL
                    DichVu dichVu = new DichVuDAL().getDichVuTheoMa(maDichVu);
                    ChiTiet_DonDatPhong_Phong chiTietDonDatPhongPhong = new ChiTiet_DonDatPhong_PhongDAL()
                            .getChiTietDonDatPhongPhongTheoMa(maDonDatPhong, maPhong);

                    // Tạo đối tượng ChiTiet_DonDatPhong_Phong_DichVu và thêm vào danh sách
                    chiTietList.add(new ChiTiet_DonDatPhong_Phong_DichVu(
                            soLuongDat,
                            ngayTao,
                            dichVu,
                            chiTietDonDatPhongPhong.getDonDatPhong(),
                            chiTietDonDatPhongPhong.getPhong(),
                            moTa
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }

        return chiTietList;
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
    public ChiTiet_DonDatPhong_Phong_DichVu getChiTietDonDatPhongPhongDichVuTheoMa(String maDonDatPhong, String maPhong, String maDichVu) {
        ChiTiet_DonDatPhong_Phong_DichVu chiTiet = null;
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
                    chiTiet = new ChiTiet_DonDatPhong_Phong_DichVu(soLuongDat, ngayTao, dichVu, chiTietDonDatPhongPhong.getDonDatPhong(), chiTietDonDatPhongPhong.getPhong(), moTa);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }

        return chiTiet;
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
    // Delete ChiTiet by maDonDatPhong, maPhong, maDichVu
    public boolean xoaChiTiet(String maDonDatPhong, String maPhong, String maDichVu) {
        String sql = "DELETE FROM CT_DonDatPhong_Phong_DichVu WHERE maDonDatPhong = ? AND maPhong = ? AND maDichVu = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, maDonDatPhong);
            stmt.setString(2, maPhong);
            stmt.setString(3, maDichVu);

            // Execute update and return whether a record was deleted
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi: " + e.getMessage());
            return false;
        }
    }

    // Insert new record
    public boolean themChiTiet(ChiTiet_DonDatPhong_Phong_DichVu chiTiet) {
        String sql = "INSERT INTO CT_DonDatPhong_Phong_DichVu (soLuongDat, ngayTao, maDichVu, maDonDatPhong, maPhong, moTa) VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, chiTiet.getSoLuongDat());
            stmt.setTimestamp(2, Timestamp.valueOf(chiTiet.getTgSuDungDV()));
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
