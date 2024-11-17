package dal;

import entity.KhuyenMai;
import database.ConnectDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class KhuyenMaiDAL {

    private Connection con;

    // Constructor
    public KhuyenMaiDAL() {
        // Không cần khởi tạo gì trong constructor
    }

    // Lấy tất cả khuyến mãi từ cơ sở dữ liệu
    public ArrayList<KhuyenMai> getAllKhuyenMai() {
        ArrayList<KhuyenMai> dsKhuyenMai = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM KhuyenMai";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String maKhuyenMai = rs.getString("maKhuyenMai");
                String tenKhuyenMai = rs.getString("tenKhuyenMai");
                double giaTri = rs.getDouble("giaTri");
                Date ngayBatDau = rs.getDate("ngayBatDau");
                boolean conHoatDong = rs.getBoolean("conHoatDong");
                int soLuong = rs.getInt("soLuong");
                Date ngayKetThuc = rs.getDate("ngayKetThuc");
                String loaiKhachHangApDung = rs.getString("loaiKhachHangApDung");

                KhuyenMai khuyenMai = new KhuyenMai(maKhuyenMai, tenKhuyenMai, giaTri, ngayBatDau.toLocalDate(),
                        conHoatDong, soLuong, ngayKetThuc.toLocalDate(), loaiKhachHangApDung);
                dsKhuyenMai.add(khuyenMai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsKhuyenMai;
    }

    // Lấy khuyến mãi theo mã khuyến mãi
    public KhuyenMai getKhuyenMaiTheoMa(String maKhuyenMai) {
        KhuyenMai khuyenMai = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM KhuyenMai WHERE maKhuyenMai = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maKhuyenMai);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String tenKhuyenMai = rs.getString("tenKhuyenMai");
                double giaTri = rs.getDouble("giaTri");
                Date ngayBatDau = rs.getDate("ngayBatDau");
                boolean conHoatDong = rs.getBoolean("conHoatDong");
                int soLuong = rs.getInt("soLuong");
                Date ngayKetThuc = rs.getDate("ngayKetThuc");
                String loaiKhachHangApDung = rs.getString("loaiKhachHangApDung");

                khuyenMai = new KhuyenMai(maKhuyenMai, tenKhuyenMai, giaTri, ngayBatDau.toLocalDate(),
                        conHoatDong, soLuong, ngayKetThuc.toLocalDate(), loaiKhachHangApDung);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khuyenMai;
    }

    // Thêm khuyến mãi mới vào cơ sở dữ liệu
    public boolean themKhuyenMai(KhuyenMai khuyenMai) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO KhuyenMai (maKhuyenMai, tenKhuyenMai, giaTri, ngayBatDau, conHoatDong, soLuong, ngayKetThuc, loaiKhachHangApDung) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, khuyenMai.getMaKhuyenMai());
            stmt.setString(2, khuyenMai.getTenKhuyenMai());
            stmt.setDouble(3, khuyenMai.getGiaTri());
            stmt.setDate(4, Date.valueOf(khuyenMai.getNgayBatDau()));
            stmt.setBoolean(5, khuyenMai.isConHoatDong());
            stmt.setInt(6, khuyenMai.getSoLuong());
            stmt.setDate(7, Date.valueOf(khuyenMai.getNgayKetThuc()));
            stmt.setString(8, khuyenMai.getLoaiKhachHangApDung());

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Cập nhật khuyến mãi trong cơ sở dữ liệu
    public boolean capNhatKhuyenMai(KhuyenMai khuyenMai) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE KhuyenMai SET tenKhuyenMai = ?, giaTri = ?, ngayBatDau = ?, conHoatDong = ?, soLuong = ?, ngayKetThuc = ?, loaiKhachHangApDung = ? WHERE maKhuyenMai = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, khuyenMai.getTenKhuyenMai());
            stmt.setDouble(2, khuyenMai.getGiaTri());
            stmt.setDate(3, Date.valueOf(khuyenMai.getNgayBatDau()));
            stmt.setBoolean(4, khuyenMai.isConHoatDong());
            stmt.setInt(5, khuyenMai.getSoLuong());
            stmt.setDate(6, Date.valueOf(khuyenMai.getNgayKetThuc()));
            stmt.setString(7, khuyenMai.getLoaiKhachHangApDung());
            stmt.setString(8, khuyenMai.getMaKhuyenMai());

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Xóa khuyến mãi khỏi cơ sở dữ liệu
    public boolean xoaKhuyenMai(String maKhuyenMai) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE KhuyenMai SET conHoatDong = 0 WHERE maKhuyenMai = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maKhuyenMai);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Đóng kết nối sau khi thực hiện các thao tác
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
        KhuyenMaiDAL khuyenMaiDAL = new KhuyenMaiDAL();

        // Thêm khuyến mãi mới
        KhuyenMai khuyenMai1 = new KhuyenMai("KM001", "Khuyến mãi Tết", 20, LocalDate.of(2024, 1, 1), true, 100, LocalDate.of(2024, 1, 31), "VIP");
        if (khuyenMaiDAL.themKhuyenMai(khuyenMai1)) {
            System.out.println("Thêm khuyến mãi thành công");
        }

        // Lấy tất cả khuyến mãi
        ArrayList<KhuyenMai> dsKhuyenMai = khuyenMaiDAL.getAllKhuyenMai();
        for (KhuyenMai km : dsKhuyenMai) {
            System.out.println(km);
        }

        // Lấy khuyến mãi theo mã
        KhuyenMai km = khuyenMaiDAL.getKhuyenMaiTheoMa("KM001");
        if (km != null) {
            System.out.println("Khuyến mãi tìm được: " + km);
        }

        // Cập nhật khuyến mãi
        khuyenMai1.setTenKhuyenMai("Khuyến mãi Tết 2024");
        khuyenMai1.setGiaTri(25);
        if (khuyenMaiDAL.capNhatKhuyenMai(khuyenMai1)) {
            System.out.println("Cập nhật khuyến mãi thành công");
        }

        // Xóa khuyến mãi
        if (khuyenMaiDAL.xoaKhuyenMai("KM001")) {
            System.out.println("Xóa khuyến mãi thành công");
        }
    }
}
