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
                LocalDate ngayThanhToan = rs.getDate("ngayThanhToan") != null ? rs.getDate("ngayThanhToan").toLocalDate() : null;

                // Giả định rằng bạn đã có các phương thức tìm kiếm nhân viên và khách hàng
                NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(rs.getString("maNV"));
                KhachHang khachHang = new KhachHangDAL().getKhachHangTheoMa(rs.getString("maKH"));
                String moTa = rs.getString("moTa");

                // Tạo đối tượng DonDatPhong từ dữ liệu truy vấn
                DonDatPhong donDatPhong = new DonDatPhong(maDon, ngayTao, phuongThucThanhToan, trangThaiDonDatPhong, trangThaiDatCoc, tienDatCoc, ngayThanhToan, nhanVien, khachHang, moTa);
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
            String sql = "INSERT INTO DonDatPhong (maDon, ngayTao, phuongThucThanhToan, trangThaiDonDatPhong, trangThaiDatCoc, tienDatCoc, ngayThanhToan, maNV, maKH, moTa) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, donDatPhong.getMaDon());
            stmt.setDate(2, java.sql.Date.valueOf(donDatPhong.getNgayTao()));
            stmt.setString(3, donDatPhong.getPhuongThucThanhToan());
            stmt.setString(4, donDatPhong.getTrangThaiDonDatPhong());
            stmt.setBoolean(5, donDatPhong.isTrangThaiDatCoc());
            stmt.setDouble(6, donDatPhong.getTienDatCoc());
            stmt.setDate(7, donDatPhong.getNgayThanhToan() != null ? java.sql.Date.valueOf(donDatPhong.getNgayThanhToan()) : null);
            stmt.setString(8, donDatPhong.getNhanVien().getMaNV());
            stmt.setString(9, donDatPhong.getKhachHang().getMaKH());
            stmt.setString(10, donDatPhong.getMoTa());
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
            String sql = "UPDATE DonDatPhong SET ngayTao = ?, phuongThucThanhToan = ?, trangThaiDonDatPhong = ?, trangThaiDatCoc = ?, tienDatCoc = ?, ngayThanhToan = ?, maNV = ?, maKH = ?, moTa = ? WHERE maDon = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf(donDatPhong.getNgayTao()));
            stmt.setString(2, donDatPhong.getPhuongThucThanhToan());
            stmt.setString(3, donDatPhong.getTrangThaiDonDatPhong());
            stmt.setBoolean(4, donDatPhong.isTrangThaiDatCoc());
            stmt.setDouble(5, donDatPhong.getTienDatCoc());
            stmt.setDate(6, donDatPhong.getNgayThanhToan() != null ? java.sql.Date.valueOf(donDatPhong.getNgayThanhToan()) : null);
            stmt.setString(7, donDatPhong.getNhanVien().getMaNV());
            stmt.setString(8, donDatPhong.getKhachHang().getMaKH());
            stmt.setString(9, donDatPhong.getMoTa());
            stmt.setString(10, donDatPhong.getMaDon());
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
                double tienDatCoc = rs.getDouble("tienDatCoc");
                LocalDate ngayThanhToan = rs.getDate("ngayThanhToan") != null ? rs.getDate("ngayThanhToan").toLocalDate() : null;

                NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(rs.getString("maNV"));
                KhachHang khachHang = new KhachHangDAL().getKhachHangTheoMa(rs.getString("maKH"));
                String moTa = rs.getString("moTa");

                donDatPhong = new DonDatPhong(maDon, ngayTao, phuongThucThanhToan, trangThaiDonDatPhong, trangThaiDatCoc, tienDatCoc, ngayThanhToan, nhanVien, khachHang, moTa);
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
        DonDatPhongDAL dal = new DonDatPhongDAL();
        // Ví dụ thêm đơn đặt phòng mới
        NhanVien nhanVien = new NhanVien("NV01", "Nguyen Van A", "0123456789", "123456789012", true, "example@gmail.com", "123 Street", 1, new TaiKhoan("user1", "password123"));
        KhachHang khachHang = new KhachHang();

        DonDatPhong donDatPhong = new DonDatPhong("D001", LocalDate.now(), "Tiền mặt", "Đang chờ", true, 500000, null, nhanVien, khachHang, "Đặt phòng cho khách.");
        boolean result = dal.themDonDatPhong(donDatPhong);
        System.out.println("Thêm đơn đặt phòng thành công: " + result);

        // Cập nhật thông tin đơn đặt phòng
        donDatPhong.setTrangThaiDonDatPhong("Đã xác nhận");
        boolean updateResult = dal.suaDonDatPhong(donDatPhong);
        System.out.println("Cập nhật đơn đặt phòng thành công: " + updateResult);

        // Tìm đơn đặt phòng
        DonDatPhong foundDonDatPhong = dal.getDonDatPhongTheoMa("D001");
        System.out.println("Tìm đơn đặt phòng: " + foundDonDatPhong);

        // Xóa đơn đặt phòng
        boolean deleteResult = dal.xoaDonDatPhong("D001");
        System.out.println("Xóa đơn đặt phòng thành công: " + deleteResult);
    }
}
