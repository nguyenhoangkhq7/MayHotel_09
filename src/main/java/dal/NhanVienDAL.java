package dal;

import java.security.Principal;
import java.sql.*;
import java.util.ArrayList;
import database.ConnectDB;
import entity.NhanVien;
import entity.Phong;
import entity.TaiKhoan;

public class NhanVienDAL {

   
    public ArrayList<NhanVien> dsNhanVien;
    Connection con;
    public NhanVienDAL() {
        dsNhanVien = new ArrayList<>();
    }


    // Lấy tất cả nhân viên từ cơ sở dữ liệu
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
                String vaiTro = rs.getString("vaiTro");

                TaiKhoan taiKhoan = new TaiKhoanDAL().getTaiKhoanTheoTen(rs.getString("tenTaiKhoan"));

                // Tạo đối tượng NhanVien từ dữ liệu truy vấn
                NhanVien nhanVien = new NhanVien(maNV, hoten, soDienThoai, soCanCuoc, conHoatDong, email, diaChi, vaiTro, taiKhoan);
                dsNhanVien.add(nhanVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return dsNhanVien;
    }
 // Lấy nhân viên theo mã nhân viên
    public NhanVien getNhanVienTheoMa(String maNV) {
        NhanVien nhanVien = null;
        try {
        		ConnectDB.getInstance().connect();
                con = ConnectDB.getConnection();
        		String sql = "SELECT * FROM NhanVien WHERE maNV = ?";
        		PreparedStatement stmt = con.prepareStatement(sql);
        		stmt.setString(1, maNV);
        		ResultSet rs = stmt.executeQuery();
        		while (rs.next()) {
        			 String hoten = rs.getString("hoTen");
                     String soDienThoai = rs.getString("soDienThoai");
                     String soCanCuoc = rs.getString("soCanCuoc");
                     boolean conHoatDong = rs.getBoolean("conHoatDong");
                     String email = rs.getString("email");
                     String diaChi = rs.getString("diaChi");
                     String vaiTro = rs.getString("vaiTro");

                     // Lấy thông tin tài khoản của nhân viên
                     TaiKhoanDAL taiKhoanDAL = new TaiKhoanDAL(); // Tạo đối tượng TaiKhoanDAL
                     TaiKhoan taiKhoan = taiKhoanDAL.getTaiKhoanTheoTen(rs.getString("tenTaiKhoan")); // Gọi phương thức không tĩnh
                     // Tạo đối tượng NhanVien
                     nhanVien = new NhanVien(maNV, hoten, soDienThoai, soCanCuoc, conHoatDong, email, diaChi, vaiTro, taiKhoan);
                }
             
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhanVien;
    }


    // Thêm nhân viên mới vào cơ sở dữ liệu
    public boolean themNhanVien(NhanVien nhanVien) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO NhanVien (maNV, hoTen, soDienThoai, soCanCuoc, conHoatDong, email, diaChi, vaiTro, tenTaiKhoan) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nhanVien.getMaNV());
            stmt.setString(2, nhanVien.getHoten());
            stmt.setString(3, nhanVien.getSoDienThoai());
            stmt.setString(4, nhanVien.getSoCanCuoc());
            stmt.setBoolean(5, nhanVien.isConHoatDong());
            stmt.setString(6, nhanVien.getEmail());
            stmt.setString(7, nhanVien.getDiaChi());
            stmt.setString(8, nhanVien.getVaiTro());
            stmt.setString(9, nhanVien.getTaiKhoan().getTenTaiKhoan());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return n > 0;
    }
    // Sửa thông tin nhân viên theo mã nhân viên
    public boolean suaNhanVien(NhanVien nhanVien) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE NhanVien SET hoTen = ?, soDienThoai = ?, soCanCuoc = ?, conHoatDong = ?, email = ?, diaChi = ?, vaiTro = ?, tenTaiKhoan = ? WHERE maNV = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nhanVien.getHoten());
            stmt.setString(2, nhanVien.getSoDienThoai());
            stmt.setString(3, nhanVien.getSoCanCuoc());
            stmt.setBoolean(4, nhanVien.isConHoatDong());
            stmt.setString(5, nhanVien.getEmail());
            stmt.setString(6, nhanVien.getDiaChi());
            stmt.setString(7, nhanVien.getVaiTro());
            stmt.setString(8, nhanVien.getTaiKhoan().getTenTaiKhoan());
            stmt.setString(10, nhanVien.getMaNV());  // You need the employee ID to identify which record to update
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return n > 0;
    }

   public static void main(String[] args) {
	NhanVienDAL dal = new NhanVienDAL();
//	ArrayList<NhanVien>  dsNhanVien = dal.getAllNhanVien();
//for (NhanVien nv : dsNhanVien) {
//	System.out.println(nv);
//}
	NhanVien nv = dal.getNhanVienTheoMa("NV001");
	System.out.println(nv);
		
		
}
}
