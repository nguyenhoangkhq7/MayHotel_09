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

            // Câu lệnh SQL để lấy dữ liệu từ bảng
            String sql = "SELECT * FROM CT_DonDatPhong_Phong WHERE maDonDatPhong = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maDonDatPhong);  // Truyền mã đơn đặt phòng vào câu truy vấn

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Lấy dữ liệu từ kết quả truy vấn
                String maPhong = rs.getString("maPhong"); // Cột "maPhong"
                LocalDateTime ngayNhanPhong = rs.getTimestamp("ngayNhanPhong").toLocalDateTime();
                LocalDateTime ngayTraPhong = rs.getTimestamp("ngayTraPhong").toLocalDateTime();
                boolean laPhongChuyen = rs.getBoolean("laPhongChuyen");
                double chietKhau = rs.getDouble("chietKhau");

                // Lấy đối tượng DonDatPhong và Phong từ DAL tương ứng
                DonDatPhong donDatPhong = new DonDatPhongDAL().getDonDatPhongTheoMa(maDonDatPhong);
                Phong phong = new PhongDAL().getPhongTheoMa(maPhong);

                // Tạo đối tượng ChiTiet_DonDatPhong_Phong
                ChiTiet_DonDatPhong_Phong chiTiet = new ChiTiet_DonDatPhong_Phong(
                        donDatPhong, phong, ngayNhanPhong, ngayTraPhong, laPhongChuyen, chietKhau
                );

                // Thêm vào danh sách
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
            String sql = "SELECT * FROM CT_DonDatPhong_Phong"; // Truy vấn toàn bộ dữ liệu
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Lấy dữ liệu từ các cột
                String maDonDatPhong = rs.getString("maDonDatPhong");
                String maPhong = rs.getString("maPhong");

                // Lấy ngày nhận phòng và ngày trả phòng (kiểm tra null)
                LocalDateTime ngayNhanPhong = rs.getTimestamp("ngayNhanPhong") != null
                        ? rs.getTimestamp("ngayNhanPhong").toLocalDateTime()
                        : null;
                LocalDateTime ngayTraPhong = rs.getTimestamp("ngayTraPhong") != null
                        ? rs.getTimestamp("ngayTraPhong").toLocalDateTime()
                        : null;

                boolean laPhongChuyen = rs.getBoolean("laPhongChuyen");
                double chietKhau = rs.getDouble("chietKhau");

                // Lấy đối tượng DonDatPhong và Phong từ DAL
                DonDatPhong donDatPhong = new DonDatPhongDAL().getDonDatPhongTheoMa(maDonDatPhong);
                Phong phong = new PhongDAL().getPhongTheoMa(maPhong);

                // Tạo đối tượng ChiTiet_DonDatPhong_Phong
                ChiTiet_DonDatPhong_Phong chiTiet = new ChiTiet_DonDatPhong_Phong(
                        donDatPhong, phong, ngayNhanPhong, ngayTraPhong, laPhongChuyen, chietKhau
                );

                // Thêm vào danh sách
                chiTietList.add(chiTiet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chiTietList;
    }

    public ChiTiet_DonDatPhong_Phong getChiTietDonDatPhongPhongTheoMa(String maDonDatPhong, String maPhong) {
        ChiTiet_DonDatPhong_Phong chiTiet = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();

            // Truy vấn dựa trên mã đơn đặt phòng và mã phòng
            String sql = "SELECT * FROM CT_DonDatPhong_Phong WHERE maDonDatPhong = ? AND maPhong = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maDonDatPhong); // Gán giá trị cho tham số `maDonDatPhong`
            stmt.setString(2, maPhong);       // Gán giá trị cho tham số `maPhong`

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Lấy dữ liệu từ các cột
                LocalDateTime ngayNhanPhong = rs.getTimestamp("ngayNhanPhong") != null
                        ? rs.getTimestamp("ngayNhanPhong").toLocalDateTime()
                        : null;
                LocalDateTime ngayTraPhong = rs.getTimestamp("ngayTraPhong") != null
                        ? rs.getTimestamp("ngayTraPhong").toLocalDateTime()
                        : null;

                boolean laPhongChuyen = rs.getBoolean("laPhongChuyen");
                double chietKhau = rs.getDouble("chietKhau");

                // Lấy đối tượng DonDatPhong và Phong từ DAL
                DonDatPhong donDatPhong = new DonDatPhongDAL().getDonDatPhongTheoMa(maDonDatPhong);
                Phong phong = new PhongDAL().getPhongTheoMa(maPhong);

                // Khởi tạo đối tượng ChiTiet_DonDatPhong_Phong
                chiTiet = new ChiTiet_DonDatPhong_Phong(
                        donDatPhong, phong, ngayNhanPhong, ngayTraPhong, laPhongChuyen, chietKhau
                );
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

            // Câu lệnh SQL không cần maCT_DDP_P nữa
            String sql = "INSERT INTO CT_DonDatPhong_Phong(maDonDatPhong, maPhong, ngayNhanPhong, ngayTraPhong, laPhongChuyen, chietKhau) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, chiTiet.getDonDatPhong().getMaDon());
            stmt.setString(2, chiTiet.getPhong().getMaPhong());
            stmt.setTimestamp(3, Timestamp.valueOf(chiTiet.getNgayNhanPhong()));
            stmt.setTimestamp(4, Timestamp.valueOf(chiTiet.getNgayTraPhong()));
            stmt.setBoolean(5, chiTiet.isLaPhongChuyen());
            stmt.setDouble(6, chiTiet.getChietKhau());

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }



    // Update ChiTiet_DonDatPhong_Phong
    public boolean suaChiTiet(String maDonDatPhong, String maPhong, ChiTiet_DonDatPhong_Phong chiTiet) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();

            // Câu lệnh SQL để cập nhật dữ liệu theo mã đơn đặt phòng và mã phòng
            String sql = "UPDATE CT_DonDatPhong_Phong SET maDonDatPhong = ?, maPhong = ?, ngayNhanPhong = ?, ngayTraPhong = ?, laPhongChuyen = ?, chietKhau = ? WHERE maDonDatPhong = ? AND maPhong = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            // Set các giá trị cho câu lệnh SQL
            stmt.setString(1, chiTiet.getDonDatPhong().getMaDon());
            stmt.setString(2, chiTiet.getPhong().getMaPhong());
            stmt.setTimestamp(3, Timestamp.valueOf(chiTiet.getNgayNhanPhong()));
            stmt.setTimestamp(4, Timestamp.valueOf(chiTiet.getNgayTraPhong()));
            stmt.setBoolean(5, chiTiet.isLaPhongChuyen());
            stmt.setDouble(6, chiTiet.getChietKhau());


            stmt.setString(7, maDonDatPhong);
            stmt.setString(8, maPhong);

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
