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


    public String layMaPhieuTiepTheo() {
        String maPhieuCuoi = ""; // Biến để lưu mã phiếu cuối cùng

        try {
            // Kết nối tới cơ sở dữ liệu
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT TOP 1 maPhieu FROM PhieuThuChi ORDER BY maPhieu DESC"; // Lấy mã phiếu mới nhất
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                maPhieuCuoi = rs.getString("maPhieu");
            }

            // Kiểm tra xem maPhieuCuoi có hợp lệ không
            if (maPhieuCuoi != null && !maPhieuCuoi.isEmpty()) {
                // Tăng mã phiếu lên 1
                int soHienTai = Integer.parseInt(maPhieuCuoi.replaceAll("[^0-9]", "")); // Lấy số từ mã phiếu
                soHienTai++;
                return String.format("PTC%04d", soHienTai); // Định dạng lại thành PTC0002, PTC0003, ...
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Xử lý lỗi khi chuyển đổi số
        }

        return "PTC0001"; // Nếu không tìm thấy, bắt đầu từ PTC0001
    }


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
                LocalDate ngayTao = rs.getDate(4) != null ? rs.getDate(4).toLocalDate() : null;
                double soTien = rs.getDouble(5);
                String phuongThuc = rs.getString(6);
                boolean conHoatDong = rs.getBoolean(7);
                NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(rs.getString(8));

                PhieuThuChi phieuThuChi = new PhieuThuChi(maPhieu, loaiPhieu, moTa, ngayTao, soTien, phuongThuc, conHoatDong, nhanVien);
                dsPhieuThuChi.add(phieuThuChi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dsPhieuThuChi;
    }
    
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
                LocalDate ngayTao = rs.getDate(4) != null ? rs.getDate(4).toLocalDate() : null;
                double soTien = rs.getDouble(5);
                String phuongThuc = rs.getString(6);
                boolean conHoatDong = rs.getBoolean(7);
                NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(rs.getString(8));

                PhieuThuChi phieuThuChi = new PhieuThuChi(maPhieu, loaiPhieu, moTa, ngayTao, soTien, phuongThuc, conHoatDong, nhanVien);
                dsPhieuThuChi.add(phieuThuChi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dsPhieuThuChi;
    }

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
            stmt.setDate(4, Date.valueOf(phieuThuChi.getNgayTao()));
            stmt.setDouble(5, phieuThuChi.getSoTien());
            stmt.setString(6, phieuThuChi.getPhuongThuc());
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
                LocalDate ngayTao = rs.getDate("ngayTao").toLocalDate();
                double soTien = rs.getDouble("soTien");
                String phuongThuc = rs.getString("phuongThuc");
                boolean conHoatDong = rs.getBoolean("conHoatDong");
                NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(rs.getString("maNV"));

                phieuThuChi = new PhieuThuChi(maPhieu, loaiPhieu, moTa, ngayTao, soTien, phuongThuc, conHoatDong, nhanVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return phieuThuChi;
    }

    private void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean suaPhieuThuChi(String maPhieuThuChi, PhieuThuChi phieuThuChi) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE PhieuThuChi SET loaiPhieu = ?, moTa = ?, ngayTao = ?, soTien = ?, phuongThuc = ?, conHoatDong = ?, maNV = ? WHERE maPhieu = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, phieuThuChi.getLoaiPhieu());
            stmt.setString(2, phieuThuChi.getMoTa());
            stmt.setDate(3, Date.valueOf(phieuThuChi.getNgayTao()));
            stmt.setDouble(4, phieuThuChi.getSoTien());
            stmt.setString(5, phieuThuChi.getPhuongThuc());
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

    public boolean huyPhieuThuChi(String maPhieuThuChi) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE PhieuThuChi SET conHoatDong = 0 WHERE maPhieu = ?";
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
    public static void main(String[] args) {
        // Tạo đối tượng của lớp PhieuThuChiDAL
        PhieuThuChiDAL phieuThuChiDAL = new PhieuThuChiDAL();

        // Định nghĩa khoảng thời gian ngày bắt đầu và ngày kết thúc
        LocalDate startDate = LocalDate.of(2024, 1, 1); // Ngày bắt đầu
        LocalDate endDate = LocalDate.of(2024, 12, 31); // Ngày kết thúc

        // Gọi hàm getPhieuThuChiByDateRange và nhận kết quả
        ArrayList<PhieuThuChi> dsPhieuThuChi = phieuThuChiDAL.getPhieuThuChiByDateRange(startDate, endDate);

        // In ra danh sách phiếu thu chi nhận được
        for (PhieuThuChi phieu : dsPhieuThuChi) {
            System.out.println("Mã phiếu: " + phieu.getMaPhieu());
            System.out.println("Loại phiếu: " + phieu.getLoaiPhieu());
            System.out.println("Mô tả: " + phieu.getMoTa());
            System.out.println("Ngày tạo: " + phieu.getNgayTao());
            System.out.println("Số tiền: " + phieu.getSoTien());
            System.out.println("Phương thức: " + phieu.getPhuongThuc());
            System.out.println("Trạng thái: " + (phieu.isConHoatDong() ? "Còn hoạt động" : "Đã hủy"));
            System.out.println("--------------------------------");
        }
    
}
}

