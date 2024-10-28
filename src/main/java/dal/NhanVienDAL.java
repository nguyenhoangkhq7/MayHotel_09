package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import database.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;

public class NhanVienDAL {
    private ArrayList<NhanVien> dsNhanVien;
    private Connection con;

    public NhanVienDAL() {
        dsNhanVien = new ArrayList<>();
    }

    // Lấy tất cả nhân viên
    public ArrayList<NhanVien> getAllNhanVien() {
        ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM NhanVien";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maNV = rs.getString("maNV");
                String hoten = rs.getString("hoTen");
                String soDienThoai = rs.getString("soDienThoai");
                String soCanCuoc = rs.getString("soCanCuoc");
                boolean conHoatDong = rs.getBoolean("conHoatDong");
                String email = rs.getString("email");
                String diaChi = rs.getString("diaChi");
                Integer vaiTro = rs.getInt("vaiTro");
                
                TaiKhoan taiKhoan = new TaiKhoanDAL().getTaiKhoanTheoMa(rs.getString("tenTaiKhoan"));

                // Tạo đối tượng NhanVien từ dữ liệu truy vấn
                NhanVien nhanVien = new NhanVien(maNV, hoten, soDienThoai, soCanCuoc, conHoatDong, email, diaChi, vaiTro, taiKhoan);
                dsNhanVien.add(nhanVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dsNhanVien;
    }

    // Thêm nhân viên mới
    public boolean themNhanVien(NhanVien nhanVien) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO NhanVien (maNV, hoTen, soDienThoai, soCanCuoc, conHoatDong, email, diaChi, vaiTro, tenTaiKhoan, matKhau) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nhanVien.getMaNV());
            stmt.setString(2, nhanVien.getHoten());
            stmt.setString(3, nhanVien.getSoDienThoai());
            stmt.setString(4, nhanVien.getSoCanCuoc());
            stmt.setBoolean(5, nhanVien.isConHoatDong());
            stmt.setString(6, nhanVien.getEmail());
            stmt.setString(7, nhanVien.getDiaChi());
            stmt.setInt(8, nhanVien.getVaiTro());
            stmt.setString(9, nhanVien.getTaiKhoan().getTenDangNhap());
            stmt.setString(10, nhanVien.getTaiKhoan().getMatKhau());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return n > 0;
    }

    // Cập nhật thông tin nhân viên
    public boolean suaNhanVien(NhanVien nhanVien) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE NhanVien SET hoTen = ?, soDienThoai = ?, soCanCuoc = ?, conHoatDong = ?, email = ?, diaChi = ?, vaiTro = ? WHERE maNV = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nhanVien.getHoten());
            stmt.setString(2, nhanVien.getSoDienThoai());
            stmt.setString(3, nhanVien.getSoCanCuoc());
            stmt.setBoolean(4, nhanVien.isConHoatDong());
            stmt.setString(5, nhanVien.getEmail());
            stmt.setString(6, nhanVien.getDiaChi());
            stmt.setInt(7, nhanVien.getVaiTro());
            stmt.setString(8, nhanVien.getMaNV());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return n > 0;
    }

    // Xóa nhân viên theo mã nhân viên
    public boolean xoaNhanVien(String maNV) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "DELETE FROM NhanVien WHERE maNV = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maNV);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return n > 0;
    }

    // Tìm nhân viên theo mã nhân viên
    public NhanVien getNhanVienTheoMa(String maNV) {
        NhanVien nhanVien = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM NhanVien WHERE maNV = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maNV);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String hoten = rs.getString("hoTen");
                String soDienThoai = rs.getString("soDienThoai");
                String soCanCuoc = rs.getString("soCanCuoc");
                boolean conHoatDong = rs.getBoolean("conHoatDong");
                String email = rs.getString("email");
                String diaChi = rs.getString("diaChi");
                Integer vaiTro = rs.getInt("vaiTro");
                
                TaiKhoan taiKhoan = new TaiKhoan(rs.getString("tenTaiKhoan"), rs.getString("matKhau"));

                nhanVien = new NhanVien(maNV, hoten, soDienThoai, soCanCuoc, conHoatDong, email, diaChi, vaiTro, taiKhoan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return nhanVien;
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
        NhanVienDAL dal = new NhanVienDAL();
        // Thêm nhân viên mới
        TaiKhoan taiKhoan = new TaiKhoan("user1", "password123");
        NhanVien nhanVien = new NhanVien("NV01", "Nguyen Van A", "0123456789", "123456789012", true, "example@gmail.com", "123 Street", 1, taiKhoan);
        boolean result = dal.themNhanVien(nhanVien);
        System.out.println("Thêm nhân viên thành công: " + result);

        // Cập nhật thông tin nhân viên
        nhanVien.setHoten("Nguyen Van B");
        boolean updateResult = dal.suaNhanVien(nhanVien);
        System.out.println("Cập nhật nhân viên thành công: " + updateResult);

        // Tìm nhân viên
        NhanVien foundNhanVien = dal.getNhanVienTheoMa("NV01");
        System.out.println("Tìm nhân viên: " + foundNhanVien);

        // Xóa nhân viên
        boolean deleteResult = dal.xoaNhanVien("NV01");
        System.out.println("Xóa nhân viên thành công: " + deleteResult);
    }
}
