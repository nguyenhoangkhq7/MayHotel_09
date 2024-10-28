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

    // Lấy danh sách tất cả ChiTiet_DonDatPhong_Phong_DichVu
    public ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> getAllChiTietDonDatPhongPhongDichVu() {
        ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> chiTietList = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM ChiTiet_DonDatPhong_Phong_DichVu";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maCT_DDP_P_DV = rs.getString(1);
                int soLuongDat = rs.getInt(2);
                LocalDate ngayTao = rs.getDate(3).toLocalDate();
                String maDichVu = rs.getString(4);
                String maCT_DDP_P = rs.getString(5);
                String moTa = rs.getString(6);

                // Lấy đối tượng DichVu và ChiTiet_DonDatPhong_Phong từ mã
                DichVu dichVu = new DichVuDAL().getDichVuTheoMa(maDichVu);
                ChiTiet_DonDatPhong_Phong cT_DDP_P = new ChiTiet_DonDatPhong_PhongDAL().getChiTietDonDatPhongPhongTheoMa(maCT_DDP_P);

                // Tạo đối tượng ChiTiet_DonDatPhong_Phong_DichVu từ dữ liệu truy vấn
                ChiTiet_DonDatPhong_Phong_DichVu chiTiet = new ChiTiet_DonDatPhong_Phong_DichVu(maCT_DDP_P_DV, soLuongDat, ngayTao, dichVu, cT_DDP_P, moTa);
                chiTietList.add(chiTiet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chiTietList;
    }

    // Lấy ChiTiet_DonDatPhong_Phong_DichVu theo mã
    public ChiTiet_DonDatPhong_Phong_DichVu getChiTietDonDatPhongPhongDichVuTheoMa(String maCT_DDP_P_DV) {
        ChiTiet_DonDatPhong_Phong_DichVu chiTiet = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM ChiTiet_DonDatPhong_Phong_DichVu WHERE maCT_DDP_P_DV = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
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
            e.printStackTrace();
        }
        return chiTiet;
    }

    // Thêm mới ChiTiet_DonDatPhong_Phong_DichVu
    public boolean themChiTiet(ChiTiet_DonDatPhong_Phong_DichVu chiTiet) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO ChiTiet_DonDatPhong_Phong_DichVu VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, chiTiet.getMaCT_DDP_P_DV());
            stmt.setInt(2, chiTiet.getSoLuongDat());
            stmt.setDate(3, java.sql.Date.valueOf(chiTiet.getNgayTao()));
            stmt.setString(4, chiTiet.getDichVu().getMaDichVu());
            stmt.setString(5, chiTiet.getCT_DDP_P().getMaCT_DDP_P());
            stmt.setString(6, chiTiet.getMoTa());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Cập nhật ChiTiet_DonDatPhong_Phong_DichVu
    public boolean suaChiTiet(String maCT_DDP_P_DV, ChiTiet_DonDatPhong_Phong_DichVu chiTiet) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE ChiTiet_DonDatPhong_Phong_DichVu SET soLuongDat = ?, ngayTao = ?, maDichVu = ?, maCT_DDP_P = ?, moTa = ? WHERE maCT_DDP_P_DV = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, chiTiet.getSoLuongDat());
            stmt.setDate(2, java.sql.Date.valueOf(chiTiet.getNgayTao()));
            stmt.setString(3, chiTiet.getDichVu().getMaDichVu());
            stmt.setString(4, chiTiet.getCT_DDP_P().getMaCT_DDP_P());
            stmt.setString(5, chiTiet.getMoTa());
            stmt.setString(6, maCT_DDP_P_DV);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Xóa ChiTiet_DonDatPhong_Phong_DichVu theo mã
    public boolean xoaChiTiet(String maCT_DDP_P_DV) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "DELETE FROM ChiTiet_DonDatPhong_Phong_DichVu WHERE maCT_DDP_P_DV = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maCT_DDP_P_DV);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

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
