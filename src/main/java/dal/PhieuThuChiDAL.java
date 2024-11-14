package dal;

import java.util.ArrayList;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import database.ConnectDB;
import entity.NhanVien;
import entity.PhieuThuChi;

public class PhieuThuChiDAL {
    public ArrayList<PhieuThuChi> dsPhieuThuChi;
    Connection con;

    public PhieuThuChiDAL() {
        dsPhieuThuChi = new ArrayList<>();
    }

    // Lấy mã phiếu tiếp theo
    public String layMaPhieuTiepTheo() {
        String maPhieuCuoi = "";

        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT TOP 1 maPhieu FROM PhieuThuChi ORDER BY maPhieu DESC";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                maPhieuCuoi = rs.getString("maPhieu");
            }

            if (maPhieuCuoi != null && !maPhieuCuoi.isEmpty()) {
                int soHienTai = Integer.parseInt(maPhieuCuoi.replaceAll("[^0-9]", ""));
                soHienTai++;
                return String.format("PTC%04d", soHienTai);
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }

        return "PTC0001"; // Nếu không tìm thấy, bắt đầu từ PTC0001
    }

    // Lấy danh sách phiếu thu chi theo khoảng thời gian
    public ArrayList<PhieuThuChi> getPhieuThuChiByDateRange(LocalDate startDate, LocalDate endDate) {
        ArrayList<PhieuThuChi> dsPhieuThuChi = new ArrayList<>();

        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM PhieuThuChi WHERE ngayTao BETWEEN ? AND ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String maPhieu = rs.getString(1);
                String loaiPhieu = rs.getString(2);
                String moTa = rs.getString(3);
                LocalDate ngayLap = rs.getDate(4) != null ? rs.getDate(4).toLocalDate() : null;
                double soTien = rs.getDouble(5);
                String phuongThucThanhToan = rs.getString(6);
                boolean conHoatDong = rs.getBoolean(7);
                NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(rs.getString(8));

                PhieuThuChi phieuThuChi = new PhieuThuChi(maPhieu, loaiPhieu, moTa, ngayLap, soTien, phuongThucThanhToan, conHoatDong, nhanVien);
                dsPhieuThuChi.add(phieuThuChi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dsPhieuThuChi;
    }

    // Lấy tất cả phiếu thu chi
    public ArrayList<PhieuThuChi> getAllPhieuThuChi() {
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM PhieuThuChi";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maPhieu = rs.getString(1);
                String loaiPhieu = rs.getString(2);
                String moTa = rs.getString(3);
                LocalDate ngayLap = rs.getDate(4) != null ? rs.getDate(4).toLocalDate() : null;
                double soTien = rs.getDouble(5);
                String phuongThucThanhToan = rs.getString(6);
                boolean conHoatDong = rs.getBoolean(7);
                NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(rs.getString(8));

                PhieuThuChi phieuThuChi = new PhieuThuChi(maPhieu, loaiPhieu, moTa, ngayLap, soTien, phuongThucThanhToan, conHoatDong, nhanVien);
                dsPhieuThuChi.add(phieuThuChi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dsPhieuThuChi;
    }

    // Thêm phiếu thu chi
    public boolean themPhieuThuChi(PhieuThuChi phieuThuChi) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO PhieuThuChi (maPhieu, loaiPhieu, moTa, ngayTao, soTien, phuongThuc, conHoatDong, maNV) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, phieuThuChi.getMaPhieu());
            stmt.setString(2, phieuThuChi.getLoaiPhieu());
            stmt.setString(3, phieuThuChi.getMoTa());
            stmt.setDate(4, Date.valueOf(phieuThuChi.getNgayLap()));
            stmt.setDouble(5, phieuThuChi.getSoTien());
            stmt.setString(6, phieuThuChi.getPhuongThucThanhToan());
            stmt.setBoolean(7, phieuThuChi.isConHoatDong());
            stmt.setString(8, phieuThuChi.getNhanVien().getMaNV());

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return n > 0;
    }

    // Lấy phiếu thu chi theo mã
    public PhieuThuChi getPhieuThuChiTheoMa(String maPhieu) {
        PhieuThuChi phieuThuChi = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM PhieuThuChi WHERE maPhieu = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maPhieu);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String loaiPhieu = rs.getString("loaiPhieu");
                String moTa = rs.getString("moTa");
                LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
                double soTien = rs.getDouble("soTien");
                String phuongThucThanhToan = rs.getString("phuongThucThanhToan");
                boolean conHoatDong = rs.getBoolean("conHoatDong");
                NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(rs.getString("maNV"));

                phieuThuChi = new PhieuThuChi(maPhieu, loaiPhieu, moTa, ngayLap, soTien, phuongThucThanhToan, conHoatDong, nhanVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return phieuThuChi;
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

    // Sửa phiếu thu chi
    public boolean suaPhieuThuChi(String maPhieuThuChi, PhieuThuChi phieuThuChi) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE PhieuThuChi SET loaiPhieu = ?, moTa = ?, ngayTao = ?, soTien = ?, phuongThuc = ?, conHoatDong = ?, maNV = ? WHERE maPhieu = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, phieuThuChi.getLoaiPhieu());
            stmt.setString(2, phieuThuChi.getMoTa());
            stmt.setDate(3, Date.valueOf(phieuThuChi.getNgayLap()));
            stmt.setDouble(4, phieuThuChi.getSoTien());
            stmt.setString(5, phieuThuChi.getPhuongThucThanhToan());
            stmt.setBoolean(6, phieuThuChi.isConHoatDong());
            stmt.setString(7, phieuThuChi.getNhanVien().getMaNV());
            stmt.setString(8, maPhieuThuChi);

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return n > 0;
    }

    // Xóa phiếu thu chi
    public boolean xoaPhieuThuChi(String maPhieuThuChi) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "DELETE FROM PhieuThuChi WHERE maPhieu = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maPhieuThuChi);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return n > 0;
    }
}
