package dal;

import entity.ChiTiet_DonDatPhong_DichVu;
import entity.DichVu;
import entity.DonDatPhong;
import database.ConnectDB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class ChiTiet_DonDatPhong_DichVuDAL {
    private ArrayList<ChiTiet_DonDatPhong_DichVu> dsChiTiet;
    private Connection con;

    public ChiTiet_DonDatPhong_DichVuDAL() {
        dsChiTiet = new ArrayList<>();
    }

    // Lấy danh sách tất cả ChiTiet_DonDatPhong_DichVu
    public ArrayList<ChiTiet_DonDatPhong_DichVu> getAllChiTiet() {
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM ChiTiet_DonDatPhong_DichVu";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maCT_DDP_DV = rs.getString(1);
                int soLuongDat = rs.getInt(2);
                LocalDate ngayTao = rs.getDate(3).toLocalDate();
                String maDichVu = rs.getString(4);
                String maDonDatPhong = rs.getString(5);

                // Tạo các đối tượng DichVu và DonDatPhong từ mã
                DichVu dichVu = new DichVu(maDichVu); // Giả định có constructor nhận mã
                DonDatPhong donDatPhong = new DonDatPhong(maDonDatPhong);

                // Tạo đối tượng ChiTiet_DonDatPhong_DichVu từ dữ liệu truy vấn
                ChiTiet_DonDatPhong_DichVu chiTiet = new ChiTiet_DonDatPhong_DichVu(maCT_DDP_DV, soLuongDat, ngayTao, dichVu, donDatPhong);
                dsChiTiet.add(chiTiet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsChiTiet;
    }

    // Thêm mới ChiTiet_DonDatPhong_DichVu
    public boolean themChiTiet(ChiTiet_DonDatPhong_DichVu chiTiet) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO ChiTiet_DonDatPhong_DichVu VALUES(?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, chiTiet.getMaCT_DDP_DV());
            stmt.setInt(2, chiTiet.getSoLuongDat());
            stmt.setDate(3, Date.valueOf(chiTiet.getNgayTao()));
            stmt.setString(4, chiTiet.getDichVu().getMaDichVu()); 
            stmt.setString(5, chiTiet.getDonDatPhong().getMaDonDatPhong());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Cập nhật ChiTiet_DonDatPhong_DichVu
    public boolean suaChiTiet(String maCT_DDP_DV, ChiTiet_DonDatPhong_DichVu chiTiet) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE ChiTiet_DonDatPhong_DichVu SET soLuongDat = ?, ngayTao = ?, maDichVu = ?, maDonDatPhong = ? WHERE maCT_DDP_DV = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, chiTiet.getSoLuongDat());
            stmt.setDate(2, java.sql.Date.valueOf(chiTiet.getNgayTao()));
            stmt.setString(3, chiTiet.getDichVu().getMaDichVu());
            stmt.setString(4, chiTiet.getDonDatPhong().getMaDonDatPhong());
            stmt.setString(5, maCT_DDP_DV);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Xóa ChiTiet_DonDatPhong_DichVu theo mã
    public boolean xoaChiTiet(String maCT_DDP_DV) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "DELETE FROM ChiTiet_DonDatPhong_DichVu WHERE maCT_DDP_DV = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maCT_DDP_DV);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public static void main(String[] args) {
        ChiTiet_DonDatPhong_DichVuDAL dal = new ChiTiet_DonDatPhong_DichVuDAL();
        // Thực hiện các thao tác kiểm tra ở đây, ví dụ thêm mới, cập nhật, xóa...
        boolean result = dal.themChiTiet(new ChiTiet_DonDatPhong_DichVu("CT001", 2, LocalDate.now(), new DichVu("DV001"), new DonDatPhong("DDP001")));
        System.out.println("Thêm mới thành công: " + result);
    }
}
