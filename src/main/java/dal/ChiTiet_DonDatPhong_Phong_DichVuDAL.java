package dal;

import entity.ChiTiet_DonDatPhong_Phong_DichVu;
import entity.ChiTiet_DonDatPhong_Phong;
import entity.DichVu;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import database.ConnectDB;

public class ChiTiet_DonDatPhong_Phong_DichVuDAL {
    private ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> dsChiTiet;
    private Connection con;

    public ChiTiet_DonDatPhong_Phong_DichVuDAL() {
        dsChiTiet = new ArrayList<>();
    }

    // Helper method to handle connection and setup
    private Connection getConnection() throws SQLException {
        ConnectDB.getInstance().connect();
        return ConnectDB.getConnection();
    }

    // Get all ChiTiet_DonDatPhong_Phong_DichVu records
    public ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> getAllChiTietDonDatPhongPhongDichVu() {
        ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> chiTietList = new ArrayList<>();
        String sql = "SELECT * FROM ChiTiet_DonDatPhong_Phong_DichVu";
        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String maCT_DDP_P_DV = rs.getString(1);
                int soLuongDat = rs.getInt(2);
                LocalDate ngayTao = rs.getDate(3).toLocalDate();
                String maDichVu = rs.getString(4);
                String maCT_DDP_P = rs.getString(5);
                String moTa = rs.getString(6);

                DichVu dichVu = new DichVuDAL().getDichVuTheoMa(maDichVu);
                ChiTiet_DonDatPhong_Phong cT_DDP_P = new ChiTiet_DonDatPhong_PhongDAL().getChiTietDonDatPhongPhongTheoMa(maCT_DDP_P);

                chiTietList.add(new ChiTiet_DonDatPhong_Phong_DichVu(maCT_DDP_P_DV, soLuongDat, ngayTao, dichVu, cT_DDP_P, moTa));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all records: " + e.getMessage());
        }
        return chiTietList;
    }

    // Fetch by ID
    public ChiTiet_DonDatPhong_Phong_DichVu getChiTietDonDatPhongPhongDichVuTheoMa(String maCT_DDP_P_DV) {
        ChiTiet_DonDatPhong_Phong_DichVu chiTiet = null;
        String sql = "SELECT * FROM ChiTiet_DonDatPhong_Phong_DichVu WHERE maCT_DDP_P_DV = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, maCT_DDP_P_DV);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int soLuongDat = rs.getInt(2);
                LocalDate ngayTao = rs.getDate(3).toLocalDate();
                String maDichVu = rs.getString(4);
                String maCT_DDP_P = rs.getString(5);
                String moTa = rs.getString(6);

                DichVu dichVu = new DichVuDAL().getDichVuTheoMa(maDichVu);
                ChiTiet_DonDatPhong_Phong cT_DDP_P = new ChiTiet_DonDatPhong_PhongDAL().getChiTietDonDatPhongPhongTheoMa(maCT_DDP_P);

                chiTiet = new ChiTiet_DonDatPhong_Phong_DichVu(maCT_DDP_P_DV, soLuongDat, ngayTao, dichVu, cT_DDP_P, moTa);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching record by ID: " + e.getMessage());
        }
        return chiTiet;
    }

    // Insert new record
    public boolean themChiTiet(ChiTiet_DonDatPhong_Phong_DichVu chiTiet) {
        String sql = "INSERT INTO ChiTiet_DonDatPhong_Phong_DichVu VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, chiTiet.getMaCT_DDP_P_DV());
            stmt.setInt(2, chiTiet.getSoLuongDat());
            stmt.setDate(3, Date.valueOf(chiTiet.getNgayTao()));
            stmt.setString(4, chiTiet.getDichVu().getMaDichVu());
            stmt.setString(5, chiTiet.getCT_DDP_P().getMaCT_DDP_P());
            stmt.setString(6, chiTiet.getMoTa());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting record: " + e.getMessage());
            return false;
        }
    }

    // Update record
    public boolean suaChiTiet(String maCT_DDP_P_DV, ChiTiet_DonDatPhong_Phong_DichVu chiTiet) {
        String sql = "UPDATE ChiTiet_DonDatPhong_Phong_DichVu SET soLuongDat = ?, ngayTao = ?, maDichVu = ?, maCT_DDP_P = ?, moTa = ? WHERE maCT_DDP_P_DV = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, chiTiet.getSoLuongDat());
            stmt.setDate(2, Date.valueOf(chiTiet.getNgayTao()));
            stmt.setString(3, chiTiet.getDichVu().getMaDichVu());
            stmt.setString(4, chiTiet.getCT_DDP_P().getMaCT_DDP_P());
            stmt.setString(5, chiTiet.getMoTa());
            stmt.setString(6, maCT_DDP_P_DV);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating record: " + e.getMessage());
            return false;
        }
    }

    // Delete record
    public boolean xoaChiTiet(String maCT_DDP_P_DV) {
        String sql = "DELETE FROM ChiTiet_DonDatPhong_Phong_DichVu WHERE maCT_DDP_P_DV = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, maCT_DDP_P_DV);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting record: " + e.getMessage());
            return false;
        }
    }

    // Test main method
    public static void main(String[] args) {
        ChiTiet_DonDatPhong_Phong_DichVuDAL dal = new ChiTiet_DonDatPhong_Phong_DichVuDAL();
        ChiTiet_DonDatPhong_Phong_DichVu chiTiet = new ChiTiet_DonDatPhong_Phong_DichVu(
                "CT_DDP_P_DV_001", 2, LocalDate.now(), new DichVu("DV001"),
                new ChiTiet_DonDatPhong_Phong("CT_DDP_P_001"), "Mô tả dịch vụ"
        );
        boolean result = dal.themChiTiet(chiTiet);
        System.out.println("Thêm mới thành công: " + result);
    }
}
