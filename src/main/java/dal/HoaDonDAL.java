package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import database.ConnectDB;
import entity.*;

public class HoaDonDAL {

    public ArrayList<HoaDon> dsHoaDon;
    Connection con;

    public HoaDonDAL() {
        dsHoaDon = new ArrayList<>();
    }

    public ArrayList<HoaDon> getHoaDonByDateRange(LocalDate startDate, LocalDate endDate) {
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();

        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM HoaDon WHERE ngayTao BETWEEN ? AND ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            // Thiết lập tham số cho câu truy vấn
            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();

            // Lặp qua từng hàng kết quả
            while (rs.next()) {
                String maHoaDon = rs.getString("maHoaDon");
                LocalDate ngayTao = rs.getDate("ngayTao") != null ? rs.getDate("ngayTao").toLocalDate() : null;
                Boolean trangThai = rs.getBoolean("trangThai");
                double thanhTien = rs.getDouble("thanhTien");
                
                // Lấy thông tin từ các bảng liên quan
                NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(rs.getString("maNV"));
                String maKhuyenMai = rs.getString("maKhuyenMai"); // Lấy mã khuyến mãi
                KhuyenMai khuyenMai = null; // Khởi tạo biến khuyến mãi là null

                // Kiểm tra xem mã khuyến mãi có phải là null không
                if (maKhuyenMai != null) { // Sử dụng null thay vì "NULL"
                    khuyenMai = new KhuyenMaiDAL().getKhuyenMaiTheoMa(maKhuyenMai);
                }
                
                DonDatPhong donDatPhong = new DonDatPhongDAL().getDonDatPhongTheoMa(rs.getString("maDon"));
                
                // Tạo đối tượng HoaDon
                HoaDon hoaDon = new HoaDon(maHoaDon, trangThai, thanhTien, nhanVien, khuyenMai, donDatPhong, ngayTao);
                dsHoaDon.add(hoaDon);
            }
            
        } catch (SQLException e) {
            e.printStackTrace(); // Log lỗi hoặc xử lý ngoại lệ
        } finally {
            // Đảm bảo rằng kết nối được đóng sau khi sử dụng
            try {
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return dsHoaDon;
    }
    // Lấy tất cả các danh sách hóa đơn từ cơ sở dữ liệu
    public ArrayList<HoaDon> getAllHoaDon() {
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM HoaDon";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maHoaDon = rs.getString(1);
                LocalDate ngayTao = rs.getDate(2) != null ? rs.getDate(2).toLocalDate() : null;
                Boolean trangThai = rs.getBoolean(3);
                double thanhTien = rs.getDouble(4);
                NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(rs.getString(5));
                KhuyenMai khuyenMai = new KhuyenMaiDAL().getKhuyenMaiTheoMa(rs.getString(6));
                DonDatPhong donDatPhong = new DonDatPhongDAL().getDonDatPhongTheoMa(rs.getString(7));

                HoaDon hoaDon = new HoaDon(maHoaDon, trangThai, thanhTien, nhanVien, khuyenMai, donDatPhong, ngayTao);
                dsHoaDon.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsHoaDon;
    }

    // Thêm một hoá đơn mới vào cơ sở dữ liệu
    public boolean themHoaDon(HoaDon hoaDon) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO HoaDon (maHoaDon, ngayTao, trangThai, thanhTien, maNV, maKM) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, hoaDon.getMaHoaDon());
            stmt.setDate(2, Date.valueOf(hoaDon.getNgayTao()));
            stmt.setBoolean(3, hoaDon.getTrangThai());
            stmt.setDouble(4, hoaDon.getThanhTien());
            stmt.setString(5, hoaDon.getNhanVien().getMaNV());
            stmt.setString(6, hoaDon.getKhuyenMai().getMaKhuyenMai());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Sửa thông tin hóa đơn trong cơ sở dữ liệu
    public boolean suaHoaDon(String maHoaDon, HoaDon hoaDon) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE HoaDon SET ngayTao = ?, trangThai = ?, thanhTien = ?, maNV = ?, maKM = ? WHERE maHoaDon = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(hoaDon.getNgayTao()));
            stmt.setBoolean(2, hoaDon.getTrangThai());
            stmt.setDouble(3, hoaDon.getThanhTien());
            stmt.setString(4, hoaDon.getNhanVien().getMaNV());
            stmt.setString(5, hoaDon.getKhuyenMai().getMaKhuyenMai());
            stmt.setString(6, maHoaDon);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Hủy hóa đơn
    public boolean huyHoaDon(String maHoaDon) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE HoaDon SET trangThai = ? WHERE maHoaDon = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setBoolean(1, false);  // Giả sử 'false' là trạng thái hủy
            stmt.setString(2, maHoaDon);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Tìm hóa đơn theo mã hóa đơn
    public HoaDon getHoaDonTheoMa(String maHoaDon) {
        HoaDon hoaDon = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM HoaDon WHERE maHoaDon = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maHoaDon);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                LocalDate ngayTao = rs.getDate("ngayTao").toLocalDate();
                boolean trangThai = rs.getBoolean("trangThai");
                double thanhTien = rs.getDouble("thanhTien");
                NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(rs.getString("maNV"));
                KhuyenMai khuyenMai = new KhuyenMaiDAL().getKhuyenMaiTheoMa(rs.getString("maKM"));

                hoaDon = new HoaDon(maHoaDon, trangThai, thanhTien, nhanVien, khuyenMai, null, ngayTao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return hoaDon;
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
        // Test các phương thức ở đây nếu cần
    }
}
