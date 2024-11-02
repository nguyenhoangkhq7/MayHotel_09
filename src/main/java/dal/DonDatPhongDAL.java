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
                String maNV = rs.getString("maNV");
                String maKH = rs.getString("maKH");
                double tongTien = rs.getDouble("tongTien");
                String moTa = rs.getString("moTa");
                LocalDate ngayNhanPhong = rs.getDate("ngayNhanPhong") != null ? rs.getDate("ngayNhanPhong").toLocalDate() : null;
                LocalDate ngayTra = rs.getDate("ngayTra") != null ? rs.getDate("ngayTra").toLocalDate() : null;

                NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(maNV);
                KhachHang khachHang = new KhachHangDAL().getKhachHangTheoMa(maKH);

                DonDatPhong donDatPhong = new DonDatPhong(maDon, ngayTao, phuongThucThanhToan, trangThaiDonDatPhong,
                        trangThaiDatCoc, nhanVien, khachHang, tongTien, moTa, ngayNhanPhong, ngayTra);
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
            String sql = "INSERT INTO DonDatPhong (maDon, ngayTao, phuongThucThanhToan, trangThaiDonDatPhong, trangThaiDatCoc, maNV, maKH, tongTien, moTa, ngayNhanPhong, ngayTra) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, donDatPhong.getMaDon());
            stmt.setDate(2, java.sql.Date.valueOf(donDatPhong.getNgayTao()));
            stmt.setString(3, donDatPhong.getPhuongThucThanhToan());
            stmt.setString(4, donDatPhong.getTrangThaiDonDatPhong());
            stmt.setBoolean(5, donDatPhong.isTrangThaiDatCoc());
            stmt.setString(6, donDatPhong.getNhanVien().getMaNV());
            stmt.setString(7, donDatPhong.getKhachHang().getMaKH());
            stmt.setDouble(8, donDatPhong.getTongTien());
            stmt.setString(9, donDatPhong.getMoTa());
            stmt.setDate(10, java.sql.Date.valueOf(donDatPhong.getNgayNhanPhong()));
            stmt.setDate(11, java.sql.Date.valueOf(donDatPhong.getNgayTra()));
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return n > 0;
    }

    // Cập nhật thông tin đơn đặt phòng
    public boolean suaDonDatPhong(DonDatPhong donDatPhong) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE DonDatPhong SET ngayTao = ?, phuongThucThanhToan = ?, trangThaiDonDatPhong = ?, trangThaiDatCoc = ?, maNV = ?, maKH = ?, tongTien = ?, moTa = ?, ngayNhanPhong = ?, ngayTra = ? WHERE maDon = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf(donDatPhong.getNgayTao()));
            stmt.setString(2, donDatPhong.getPhuongThucThanhToan());
            stmt.setString(3, donDatPhong.getTrangThaiDonDatPhong());
            stmt.setBoolean(4, donDatPhong.isTrangThaiDatCoc());
            stmt.setString(5, donDatPhong.getNhanVien().getMaNV());
            stmt.setString(6, donDatPhong.getKhachHang().getMaKH());
            stmt.setDouble(7, donDatPhong.getTongTien());
            stmt.setString(8, donDatPhong.getMoTa());
            stmt.setDate(9, java.sql.Date.valueOf(donDatPhong.getNgayNhanPhong()));
            stmt.setDate(10, java.sql.Date.valueOf(donDatPhong.getNgayTra()));
            stmt.setString(11, donDatPhong.getMaDon());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return n > 0;
    }

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
                String maNV = rs.getString("maNV");
                String maKH = rs.getString("maKH");
                double tongTien = rs.getDouble("tongTien");
                String moTa = rs.getString("moTa");
                LocalDate ngayNhanPhong = rs.getDate("ngayNhanPhong") != null ? rs.getDate("ngayNhanPhong").toLocalDate() : null;
                LocalDate ngayTra = rs.getDate("ngayTra") != null ? rs.getDate("ngayTra").toLocalDate() : null;

                NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(maNV);
                KhachHang khachHang = new KhachHangDAL().getKhachHangTheoMa(maKH);

                donDatPhong = new DonDatPhong(maDon, ngayTao, phuongThucThanhToan, trangThaiDonDatPhong,
                        trangThaiDatCoc, nhanVien, khachHang, tongTien, moTa, ngayNhanPhong, ngayTra);
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
        // Bạn có thể viết mã kiểm tra ở đây
    }
}
