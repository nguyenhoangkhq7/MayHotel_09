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
                if (maKhuyenMai != null) {
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
            closeConnection();
        }
        return dsHoaDon;
    }
 // Thêm một hóa đơn mới vào cơ sở dữ liệu
    public boolean themHoaDon(HoaDon hoaDon) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO HoaDon (maHoaDon, trangThai, thanhTien, maNV, maKM, maDon, ngayTao) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            // Thiết lập các tham số cho câu lệnh SQL
            stmt.setString(1, hoaDon.getMaHoaDon()); // Mã hóa đơn
            stmt.setBoolean(2, hoaDon.getTrangThai()); // Trạng thái hóa đơn
            stmt.setDouble(3, hoaDon.getThanhTien()); // Thành tiền
            stmt.setString(4, hoaDon.getNhanVien().getMaNV()); // Mã nhân viên
            stmt.setString(5, hoaDon.getKhuyenMai() != null ? hoaDon.getKhuyenMai().getMaKhuyenMai() : null); // Mã khuyến mãi
            stmt.setString(6, hoaDon.getDonDatPhong() != null ? hoaDon.getDonDatPhong().getMaDon() : null); // Mã đơn đặt
            stmt.setDate(7, Date.valueOf(hoaDon.getNgayTao())); // Ngày tạo

            // Thực thi câu lệnh SQL
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(); // Đóng kết nối sau khi thực hiện
        }
        return n > 0; // Trả về true nếu thành công
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
                String maHoaDon = rs.getString("maHoaDon");
                LocalDate ngayTao = null;
                if (rs.getDate("ngayTao") != null) { // Kiểm tra nếu giá trị không phải là null
                    ngayTao = rs.getDate("ngayTao").toLocalDate();
                }

                Boolean trangThai = rs.getBoolean("trangThai");
                double thanhTien = rs.getDouble("thanhTien");
                NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(rs.getString("maNV"));
                KhuyenMai khuyenMai = new KhuyenMaiDAL().getKhuyenMaiTheoMa(rs.getString("maKhuyenMai"));
                DonDatPhong donDatPhong = new DonDatPhongDAL().getDonDatPhongTheoMa(rs.getString("maDon"));

                HoaDon hoaDon = new HoaDon(maHoaDon, trangThai, thanhTien, nhanVien, khuyenMai, donDatPhong, ngayTao);
                dsHoaDon.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsHoaDon;
    }
 // Inside HoaDonDAL class
    public String getLastMaHD() {
        String lastMaHD = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT maHoaDon FROM HoaDon ORDER BY maHoaDon DESC LIMIT 1";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                lastMaHD = rs.getString("maHoaDon");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastMaHD;
    }

    // Sửa thông tin hóa đơn trong cơ sở dữ liệu
    public boolean suaHoaDon(String maHoaDon, HoaDon hoaDon) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE HoaDon SET trangThai = ?, thanhTien = ?, maNV = ?, maKM = ?, maDon = ?, ngayTao = ? WHERE maHoaDon = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setBoolean(1, hoaDon.getTrangThai());
            stmt.setDouble(2, hoaDon.getThanhTien());
            stmt.setString(3, hoaDon.getNhanVien().getMaNV());
            stmt.setString(4, hoaDon.getKhuyenMai() != null ? hoaDon.getKhuyenMai().getMaKhuyenMai() : null);
            stmt.setString(5, hoaDon.getDonDatPhong() != null ? hoaDon.getDonDatPhong().getMaDon() : null);
            stmt.setDate(6, Date.valueOf(hoaDon.getNgayTao()));
            stmt.setString(7, maHoaDon);
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
                LocalDate ngayTao = rs.getDate("ngayTao") != null ? rs.getDate("ngayTao").toLocalDate() : null;
                boolean trangThai = rs.getBoolean("trangThai");
                double thanhTien = rs.getDouble("thanhTien");
                NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(rs.getString("maNV"));
                KhuyenMai khuyenMai = new KhuyenMaiDAL().getKhuyenMaiTheoMa(rs.getString("maKM"));
                DonDatPhong donDatPhong = new DonDatPhongDAL().getDonDatPhongTheoMa(rs.getString("maDon"));

                hoaDon = new HoaDon(maHoaDon, trangThai, thanhTien, nhanVien, khuyenMai, donDatPhong, ngayTao);
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
		System.out.println(new HoaDonDAL().getAllHoaDon());
	}
}
