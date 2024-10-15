/*
    *MayHotel  day creative: 9/26/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here   
     */


package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.ChiTiet_DonDatPhong_Phong;
import entity.NhanVien;

public class NhanVienDAL {
	private ArrayList<NhanVien> dsNhanVien;
	private Connection con;
	
	public NhanVienDAL() {
		dsNhanVien = new ArrayList<NhanVien>();
	}
	// Lấy danh sách tất cả nhân viên
    public ArrayList<NhanVien> getAllNhanVien() {
        ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maNV = rs.getString("maNV");
                String hoten = rs.getString("hoten");
                String soDienThoai = rs.getString("soDienThoai");
                String cancuoc = rs.getString("cancuoc");
                boolean conHoatDong = rs.getBoolean("conHoatDong");
                String email = rs.getString("email");
                String diaChi = rs.getString("diaChi");
                Integer vaiTro = rs.getInt("vaiTro");

                NhanVien nhanVien = new NhanVien(maNV, hoten, soDienThoai, cancuoc, conHoatDong, email, diaChi, vaiTro);
                dsNhanVien.add(nhanVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNhanVien;
    }
 // Thêm mới nhân viên
    public boolean themNhanVien(NhanVien nhanVien) {
        int n = 0;
        String sql = "INSERT INTO NhanVien(maNV, hoten, soDienThoai, cancuoc, conHoatDong, email, diaChi, vaiTro) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nhanVien.getMaNV());
            stmt.setString(2, nhanVien.getHoten());
            stmt.setString(3, nhanVien.getSoDienThoai());
            stmt.setString(4, nhanVien.getCancuoc());
            stmt.setBoolean(5, nhanVien.isConHoatDong());
            stmt.setString(6, nhanVien.getEmail());
            stmt.setString(7, nhanVien.getDiaChi());
            stmt.setInt(8, nhanVien.getVaiTro());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

 // Sửa thông tin nhân viên
    public boolean suaNhanVien(String maNV, NhanVien nhanVien) {
        int n = 0;
        String sql = "UPDATE NhanVien SET hoten = ?, soDienThoai = ?, cancuoc = ?, conHoatDong = ?, email = ?, diaChi = ?, vaiTro = ? WHERE maNV = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nhanVien.getHoten());
            stmt.setString(2, nhanVien.getSoDienThoai());
            stmt.setString(3, nhanVien.getCancuoc());
            stmt.setBoolean(4, nhanVien.isConHoatDong());
            stmt.setString(5, nhanVien.getEmail());
            stmt.setString(6, nhanVien.getDiaChi());
            stmt.setInt(7, nhanVien.getVaiTro());
            stmt.setString(8, maNV);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
 // Tìm kiếm nhân viên theo mã hoặc tên
    public ArrayList<NhanVien> timKiemNhanVien(String tuKhoa) {
        ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE maNV LIKE ? OR hoten LIKE ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + tuKhoa + "%");
            stmt.setString(2, "%" + tuKhoa + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("maNV");
                String hoten = rs.getString("hoten");
                String soDienThoai = rs.getString("soDienThoai");
                String cancuoc = rs.getString("cancuoc");
                boolean conHoatDong = rs.getBoolean("conHoatDong");
                String email = rs.getString("email");
                String diaChi = rs.getString("diaChi");
                Integer vaiTro = rs.getInt("vaiTro");

                NhanVien nhanVien = new NhanVien(maNV, hoten, soDienThoai, cancuoc, conHoatDong, email, diaChi, vaiTro);
                dsNhanVien.add(nhanVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNhanVien;
    }

    // Test main
    public static void main(String[] args) {
        NhanVienDAL dal = new NhanVienDAL();
        
        // Test thêm mới nhân viên
        NhanVien nv = new NhanVien("NV001", "Nguyễn Văn A", "0123456789", "123456789012", true, "a@gmail.com", "Hà Nội", 1);
        boolean result = dal.themNhanVien(nv);
        System.out.println("Thêm mới thành công: " + result);
        
        // Test lấy danh sách nhân viên
        ArrayList<NhanVien> dsNhanVien = dal.getAllNhanVien();
        for (NhanVien nhanVien : dsNhanVien) {
            System.out.println(nhanVien);
        }
    }
}
