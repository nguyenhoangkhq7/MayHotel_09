/*
    *MayHotel  day creative: 10/15/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang
*/

package dal;

import entity.ChiTiet_DonDatPhong_Phong;
import entity.DonDatPhong;
import entity.Phong;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import database.ConnectDB;

public class ChiTiet_DonDatPhong_PhongDAL {
    private ArrayList<ChiTiet_DonDatPhong_Phong> dsChiTiet;
    private Connection con;


    public ChiTiet_DonDatPhong_PhongDAL() {
        dsChiTiet = new ArrayList<>();
    }
    public ArrayList<ChiTiet_DonDatPhong_Phong> getChiTietDonDatPhongPhongTheoMaDDP(String maDonDatPhong) {
        ArrayList<ChiTiet_DonDatPhong_Phong> chiTietList = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            
            // Câu lệnh SQL có điều kiện WHERE
            String sql = "SELECT * FROM CT_DonDatPhong_Phong WHERE maDonDatPhong = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maDonDatPhong);  // Truyền giá trị mã đơn đặt phòng vào câu lệnh SQL
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                String maCT_DDP_P = rs.getString(1);
                LocalDateTime ngayTra = rs.getTimestamp(5).toLocalDateTime();
                String maPhong = rs.getString(3);
                LocalDateTime ngayNhanPhong = rs.getTimestamp(4).toLocalDateTime();
                boolean laPhongChuyen = rs.getBoolean(6);
                double chietKhau = rs.getDouble(7);

                // Lấy đối tượng DonDatPhong và Phong
                DonDatPhong donDatPhong = new DonDatPhongDAL().getDonDatPhongTheoMa(maDonDatPhong);
                Phong phong = new PhongDAL().getPhongTheoMa(maPhong);

                // Tạo đối tượng ChiTiet_DonDatPhong_Phong với dữ liệu đã lấy
                ChiTiet_DonDatPhong_Phong chiTiet = new ChiTiet_DonDatPhong_Phong(
                        maCT_DDP_P, donDatPhong, phong, ngayNhanPhong, ngayTra, laPhongChuyen, chietKhau
                );
                chiTietList.add(chiTiet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chiTietList;
    }
 // Retrieve all ChiTiet_DonDatPhong_Phong records
    public ArrayList<ChiTiet_DonDatPhong_Phong> getAllChiTietDonDatPhongPhong() {
        ArrayList<ChiTiet_DonDatPhong_Phong> chiTietList = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM CT_DonDatPhong_Phong"; // Updated table name
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maCT_DDP_P = rs.getString(1);
                String maDonDatPhong = rs.getString(2);
                String maPhong = rs.getString(3);
                
                // Lấy ngày nhận phòng và ngày trả phòng với kiểm tra null
                LocalDateTime ngayNhanPhong = null;
                LocalDateTime ngayTra = null;

                LocalDateTime sqlNgayNhanPhong = rs.getTimestamp(4).toLocalDateTime();
                if (sqlNgayNhanPhong != null) {
                    ngayNhanPhong = sqlNgayNhanPhong;
                } else {
                    // Xử lý trường hợp ngày nhận phòng là null
                    // Ví dụ: gán giá trị mặc định hoặc ném một ngoại lệ
                }

                LocalDateTime sqlNgayTra = rs.getTimestamp(5).toLocalDateTime();
                if (sqlNgayTra != null) {
                    ngayTra = sqlNgayTra;
                } else {
                    // Xử lý trường hợp ngày trả phòng là null
                    // Ví dụ: gán giá trị mặc định hoặc ném một ngoại lệ
                }

                boolean laPhongChuyen = rs.getBoolean(6);
                double chietKhau = rs.getDouble(7);

                // Retrieve DonDatPhong and Phong objects
                DonDatPhong donDatPhong = new DonDatPhongDAL().getDonDatPhongTheoMa(maDonDatPhong);
                Phong phong = new PhongDAL().getPhongTheoMa(maPhong);

                // Create ChiTiet_DonDatPhong_Phong object with retrieved data
                ChiTiet_DonDatPhong_Phong chiTiet = new ChiTiet_DonDatPhong_Phong(
                        maCT_DDP_P, donDatPhong, phong, ngayNhanPhong, ngayTra, laPhongChuyen, chietKhau
                );
                chiTietList.add(chiTiet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chiTietList;
    }



    // Retrieve ChiTiet_DonDatPhong_Phong by ID
    public ChiTiet_DonDatPhong_Phong getChiTietDonDatPhongPhongTheoMa(String maCT_DDP_P) {
        ChiTiet_DonDatPhong_Phong chiTiet = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM CT_DonDatPhong_Phong WHERE maCT_DDP_P = ?"; // Updated table name
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maCT_DDP_P);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String maDonDatPhong = rs.getString(2);
                String maPhong = rs.getString(3);
                LocalDateTime ngayNhanPhong = rs.getTimestamp(4).toLocalDateTime();
                LocalDateTime ngayTra = rs.getTimestamp(5).toLocalDateTime();
                boolean laPhongChuyen = rs.getBoolean(6);
                double chietKhau = rs.getDouble(7);

                // Retrieve DonDatPhong and Phong objects
                DonDatPhong donDatPhong = new DonDatPhongDAL().getDonDatPhongTheoMa(maDonDatPhong);
                Phong phong = new PhongDAL().getPhongTheoMa(maPhong);

                // Initialize ChiTiet_DonDatPhong_Phong object
                chiTiet = new ChiTiet_DonDatPhong_Phong(maCT_DDP_P, donDatPhong, phong, ngayNhanPhong, ngayTra, laPhongChuyen, chietKhau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chiTiet;
    }


    // Add new ChiTiet_DonDatPhong_Phong
    public boolean themChiTiet(ChiTiet_DonDatPhong_Phong chiTiet) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO CT_DonDatPhong_Phong VALUES(?, ?, ?, ?, ?, ?, ?)"; // Updated table name
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, chiTiet.getMaCT_DDP_P());
            stmt.setString(2, chiTiet.getDonDatPhong().getMaDon());
            stmt.setString(3, chiTiet.getPhong().getMaPhong());
            stmt.setTimestamp(5, Timestamp.valueOf(chiTiet.getNgayTraPhong()));
            stmt.setTimestamp(4, Timestamp.valueOf(chiTiet.getNgayNhanPhong()));
            stmt.setBoolean(6, chiTiet.isLaPhongChuyen());
            stmt.setDouble(7, chiTiet.getChietKhau());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }


    // Update ChiTiet_DonDatPhong_Phong
    public boolean suaChiTiet(String maCT_DDP_P, ChiTiet_DonDatPhong_Phong chiTiet) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE CT_DonDatPhong_Phong SET maDonDatPhong = ?, maPhong = ?, ngayNhanPhong = ?, ngayTraPhong = ?, laPhongChuyen = ?, chietKhau = ? WHERE maCT_DDP_P = ?"; // Updated table name
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, chiTiet.getDonDatPhong().getMaDon());
            stmt.setString(2, chiTiet.getPhong().getMaPhong());
            stmt.setTimestamp(3, Timestamp.valueOf(chiTiet.getNgayNhanPhong()));
            stmt.setTimestamp(4, Timestamp.valueOf(chiTiet.getNgayTraPhong()));
            stmt.setBoolean(5, chiTiet.isLaPhongChuyen());
            stmt.setDouble(6, chiTiet.getChietKhau());
            stmt.setString(7, maCT_DDP_P);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    
    // Delete ChiTiet_DonDatPhong_Phong by ID
    public boolean xoaChiTiet(String maCT_DDP_P) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "DELETE FROM CT_DonDatPhong_Phong WHERE maCT_DDP_P = ?"; // Updated table name
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maCT_DDP_P);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
    
    public static void main(String[] args) {
        ChiTiet_DonDatPhong_PhongDAL dal = new ChiTiet_DonDatPhong_PhongDAL();
        ArrayList<ChiTiet_DonDatPhong_Phong> cts = dal.getChiTietDonDatPhongPhongTheoMaDDP("DDP000001");
        for(ChiTiet_DonDatPhong_Phong ct  : cts) {
        	System.out.println(ct);
        }
        
    }
}
