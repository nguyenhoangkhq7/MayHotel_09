package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import database.ConnectDB;
import entity.DonDatPhong;
import entity.KhachHang;
import entity.NhanVien;
import entity.TaiKhoan;

public class DonDatPhongDAL {
    private ArrayList<DonDatPhong> dsDonDatPhong;
    private Connection con;

    public DonDatPhongDAL() {
        dsDonDatPhong = new ArrayList<>();
    }

    // Lấy tất cả đơn đặt phòng
    public ArrayList<DonDatPhong> getAllDonDatPhong() {
        ArrayList<DonDatPhong> dsDonDatPhong = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM DonDatPhong";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maDon = rs.getString("maDon");
                LocalDate ngayTao = rs.getDate("ngayTao").toLocalDate();
                String phuongThucThanhToan = rs.getString("phuongThucThanhToan");
                String trangThaiDonDatPhong = rs.getString("trangThaiDonDatPhong");
                boolean trangThaiDatCoc = rs.getBoolean("trangThaiDatCoc");
                double tienDatCoc = rs.getDouble("tienDatCoc");
                double tongTien = rs.getDouble("tongTien"); // New addition
                LocalDate ngayNhanPhong = rs.getDate("ngayNhanPhong").toLocalDate(); // New addition
                LocalDate ngayTra = rs.getDate("ngayTra").toLocalDate(); // New addition
                String moTa = rs.getString("moTa");

                NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(rs.getString("maNV"));
                KhachHang khachHang = new KhachHangDAL().getKhachHangTheoMa(rs.getString("maKH"));

                DonDatPhong donDatPhong = new DonDatPhong(maDon, ngayTao, phuongThucThanhToan, trangThaiDonDatPhong,
                        trangThaiDatCoc, tienDatCoc, nhanVien, khachHang, tongTien, moTa, ngayNhanPhong, ngayTra);
                dsDonDatPhong.add(donDatPhong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dsDonDatPhong;
    }


    // Thêm đơn đặt phòng mới
    public boolean themDonDatPhong(DonDatPhong donDatPhong) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO DonDatPhong (maDon, ngayTao, phuongThucThanhToan, trangThaiDonDatPhong, trangThaiDatCoc, tienDatCoc, tongTien, ngayNhanPhong, ngayTra, maNV, maKH, moTa) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, donDatPhong.getMaDon());
            stmt.setDate(2, java.sql.Date.valueOf(donDatPhong.getNgayTao()));
            stmt.setString(3, donDatPhong.getPhuongThucThanhToan());
            stmt.setString(4, donDatPhong.getTrangThaiDonDatPhong());
            stmt.setBoolean(5, donDatPhong.isTrangThaiDatCoc());
            stmt.setDouble(6, donDatPhong.getTienDatCoc());
            stmt.setDouble(7, donDatPhong.getTongTien()); // New addition
            stmt.setDate(8, java.sql.Date.valueOf(donDatPhong.getNgayNhanPhong())); // New addition
            stmt.setDate(9, java.sql.Date.valueOf(donDatPhong.getNgayTra())); // New addition
            stmt.setString(10, donDatPhong.getNhanVien().getMaNV());
            stmt.setString(11, donDatPhong.getKhachHang().getMaKH());
            stmt.setString(12, donDatPhong.getMoTa());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return n > 0;
    }


    // Cập nhật thông tin đơn đặt phòng
    // Cập nhật thông tin đơn đặt phòng
    public boolean suaDonDatPhong(DonDatPhong donDatPhong) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE DonDatPhong SET ngayTao = ?, phuongThucThanhToan = ?, trangThaiDonDatPhong = ?, trangThaiDatCoc = ?, tienDatCoc = ?, ngayNhanPhong = ?, ngayTra = ?, maNV = ?, maKH = ?, moTa = ?, tongTien = ? WHERE maDon = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf(donDatPhong.getNgayTao()));
            stmt.setString(2, donDatPhong.getPhuongThucThanhToan());
            stmt.setString(3, donDatPhong.getTrangThaiDonDatPhong());
            stmt.setBoolean(4, donDatPhong.isTrangThaiDatCoc());
            stmt.setDouble(5, donDatPhong.getTienDatCoc());
            stmt.setDate(6, java.sql.Date.valueOf(donDatPhong.getNgayNhanPhong()));
            stmt.setDate(7, java.sql.Date.valueOf(donDatPhong.getNgayTra()));
            stmt.setString(8, donDatPhong.getNhanVien().getMaNV());
            stmt.setString(9, donDatPhong.getKhachHang().getMaKH());
            stmt.setString(10, donDatPhong.getMoTa());
            stmt.setDouble(11, donDatPhong.getTongTien());
            stmt.setString(12, donDatPhong.getMaDon());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return n > 0;
    }


    // Xóa đơn đặt phòng theo mã đơn
    // Xóa đơn đặt phòng theo mã đơn
    public boolean xoaDonDatPhong(String maDon) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "DELETE FROM DonDatPhong WHERE maDon = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maDon);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return n > 0;
    }


    // Tìm đơn đặt phòng theo mã đơn
    // Tìm đơn đặt phòng theo mã đơn
    public DonDatPhong getDonDatPhongTheoMa(String maDon) {
        DonDatPhong donDatPhong = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM DonDatPhong WHERE maDon = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maDon);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                LocalDate ngayTao = rs.getDate("ngayTao").toLocalDate();
                String phuongThucThanhToan = rs.getString("phuongThucThanhToan");
                String trangThaiDonDatPhong = rs.getString("trangThaiDonDatPhong");
                boolean trangThaiDatCoc = rs.getBoolean("trangThaiDatCoc");
                double tienDatCoc = rs.getDouble("tienDatCoc");
                LocalDate ngayNhanPhong = rs.getDate("ngayNhanPhong") != null ? rs.getDate("ngayNhanPhong").toLocalDate() : null;
                LocalDate ngayTra = rs.getDate("ngayTra") != null ? rs.getDate("ngayTra").toLocalDate() : null;
                double tongTien = rs.getDouble("tongTien");

                NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(rs.getString("maNV"));
                KhachHang khachHang = new KhachHangDAL().getKhachHangTheoMa(rs.getString("maKH"));
                String moTa = rs.getString("moTa");

                donDatPhong = new DonDatPhong(maDon, ngayTao, phuongThucThanhToan, trangThaiDonDatPhong, trangThaiDatCoc, tienDatCoc, nhanVien, khachHang, tongTien, moTa, ngayNhanPhong, ngayTra);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return donDatPhong;
    }


    // Đóng kết nối
    private void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
