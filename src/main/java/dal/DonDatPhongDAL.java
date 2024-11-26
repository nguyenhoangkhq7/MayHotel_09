package dal;

import java.sql.*;
import java.time.LocalDateTime;
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
                LocalDateTime ngayTao = rs.getTimestamp("ngayTao").toLocalDateTime();
                String phuongThucThanhToan = rs.getString("phuongThucThanhToan");
                String trangThaiDonDatPhong = rs.getString("trangThaiDonDatPhong");
                boolean trangThaiDatCoc = rs.getBoolean("trangThaiDatCoc");
                String maNV = rs.getString("maNV");
                String maKH = rs.getString("maKH");
                double tongTien = rs.getDouble("tongTien");
                String moTa = rs.getString("moTa");
                LocalDateTime ngayTraPhong = rs.getTimestamp("ngayTraPhong") != null ? rs.getTimestamp("ngayTraPhong").toLocalDateTime() : null;
                LocalDateTime ngayNhanPhong = rs.getTimestamp("ngayNhanPhong") != null ? rs.getTimestamp("ngayNhanPhong").toLocalDateTime() : null;

                NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(maNV);
                KhachHang khachHang = new KhachHangDAL().getKhachHangTheoMa(maKH);

                DonDatPhong donDatPhong = new DonDatPhong(maDon, ngayTao, phuongThucThanhToan, trangThaiDonDatPhong,
                        trangThaiDatCoc, nhanVien, khachHang, tongTien, moTa, ngayTraPhong, ngayNhanPhong );
                dsDonDatPhong.add(donDatPhong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDonDatPhong;
    }

    // Thêm đơn đặt phòng mới
    public boolean themDonDatPhong(DonDatPhong donDatPhong) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO DonDatPhong (maDon, ngayTao, phuongThucThanhToan, trangThaiDonDatPhong, trangThaiDatCoc, maNV, maKH, tongTien, moTa, ngayTraPhong, ngayNhanPhong) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, donDatPhong.getMaDon());
            stmt.setTimestamp(2, Timestamp.valueOf(donDatPhong.getNgayTao()));
            stmt.setString(3, donDatPhong.getPhuongThucThanhToan());
            stmt.setString(4, donDatPhong.getTrangThaiDonDatPhong());
            stmt.setBoolean(5, donDatPhong.isTrangThaiDatCoc());
            stmt.setString(6, donDatPhong.getNhanVien().getMaNV());
            stmt.setString(7, donDatPhong.getKhachHang().getMaKH());
            stmt.setDouble(8, donDatPhong.getTongTien());
            stmt.setString(9, donDatPhong.getMoTa());
            stmt.setTimestamp(10, Timestamp.valueOf(donDatPhong.getNgayTraPhong()));
            stmt.setTimestamp(11, Timestamp.valueOf(donDatPhong.getNgayNhanPhong()));
            
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return n > 0;
    }
    // Cập nhật trạng thái đơn đặt phòng theo mã đơn
    public boolean suaTrangThaiDonDatPhong(String maDon, String trangThaiMoi) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE DonDatPhong SET trangThaiDonDatPhong = ? WHERE maDon = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, trangThaiMoi); // Trạng thái mới
            stmt.setString(2, maDon);       // Mã đơn cần thay đổi trạng thái
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0; // Trả về true nếu cập nhật thành công
    }

    // Cập nhật thông tin đơn đặt phòng
    public boolean suaDonDatPhong(DonDatPhong donDatPhong) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE DonDatPhong SET ngayTao = ?, phuongThucThanhToan = ?, trangThaiDonDatPhong = ?, trangThaiDatCoc = ?, maNV = ?, maKH = ?, tongTien = ?, moTa = ?,  ngayTraPhong = ?, ngayNhanPhong = ? WHERE maDon = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setTimestamp(1, Timestamp.valueOf(donDatPhong.getNgayTao()));
            stmt.setString(2, donDatPhong.getPhuongThucThanhToan());
            stmt.setString(3, donDatPhong.getTrangThaiDonDatPhong());
            stmt.setBoolean(4, donDatPhong.isTrangThaiDatCoc());
            stmt.setString(5, donDatPhong.getNhanVien().getMaNV());
            stmt.setString(6, donDatPhong.getKhachHang().getMaKH());
            stmt.setDouble(7, donDatPhong.getTongTien());
            stmt.setString(8, donDatPhong.getMoTa());
            stmt.setTimestamp(9, Timestamp.valueOf(donDatPhong.getNgayTraPhong()));
            stmt.setTimestamp(10, Timestamp.valueOf(donDatPhong.getNgayNhanPhong()));
            
            stmt.setString(11, donDatPhong.getMaDon());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
    // Hàm lấy mã đơn đặt phòng cuối cùng
    public String getLastDDP() {
        String lastOrder = null;

        String query = "SELECT MAX(maDon) FROM DonDatPhong"; // Truy vấn để lấy mã đơn lớn nhất
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                lastOrder = rs.getString(1); // Lấy mã đơn lớn nhất
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return lastOrder;
    }

    // Xóa đơn đặt phòng theo mã đơn
    // Cập nhật trạng thái đơn đặt phòng thành "Đã hủy" theo mã đơn
    public boolean huyDonDatPhong(String maDon) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE DonDatPhong SET trangThaiDonDatPhong = ? WHERE maDon = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "Đã hủy");  // Set trạng thái thành "Đã hủy"
            stmt.setString(2, maDon);     // Điều kiện theo mã đơn
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
                LocalDateTime ngayTao = rs.getTimestamp("ngayTao").toLocalDateTime();
                String phuongThucThanhToan = rs.getString("phuongThucThanhToan");
                String trangThaiDonDatPhong = rs.getString("trangThaiDonDatPhong");
                boolean trangThaiDatCoc = rs.getBoolean("trangThaiDatCoc");
                String maNV = rs.getString("maNV");
                String maKH = rs.getString("maKH");
                double tongTien = rs.getDouble("tongTien");
                String moTa = rs.getString("moTa");
                LocalDateTime ngayTraPhong = rs.getTimestamp("ngayTraPhong") != null ? rs.getTimestamp("ngayTraPhong").toLocalDateTime() : null;

                LocalDateTime ngayNhanPhong = rs.getTimestamp("ngayNhanPhong") != null ? rs.getTimestamp("ngayNhanPhong").toLocalDateTime() : null;

                NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(maNV);
                KhachHang khachHang = new KhachHangDAL().getKhachHangTheoMa(maKH);

                donDatPhong = new DonDatPhong(maDon, ngayTao, phuongThucThanhToan, trangThaiDonDatPhong,
                        trangThaiDatCoc, nhanVien, khachHang, tongTien, moTa,ngayTraPhong, ngayNhanPhong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return donDatPhong;
    }

}
