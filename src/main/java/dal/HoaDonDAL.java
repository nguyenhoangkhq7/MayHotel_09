package dal;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import database.ConnectDB;
import entity.*;

public class HoaDonDAL {

    public ArrayList<HoaDon> dsHoaDon;
    Connection con;

    public HoaDonDAL() {
        dsHoaDon = new ArrayList<>();
    }

    public ArrayList<HoaDon> getHoaDonByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();

        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM HoaDon WHERE ngayTao BETWEEN ? AND ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            // Thiết lập tham số cho câu truy vấn
            stmt.setTimestamp(1, Timestamp.valueOf(startDate));
            stmt.setTimestamp(2, Timestamp.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();

            // Lặp qua từng hàng kết quả
            while (rs.next()) {
                String maHoaDon = rs.getString("maHoaDon");
                LocalDateTime ngayTao = rs.getDate("ngayTao") != null ? rs.getTimestamp("ngayTao").toLocalDateTime() : null;
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
                
                DonDatPhong donDatPhong = new DonDatPhongDAL().getDonDatPhongTheoMa(rs.getString("maDonDatPhong"));
                
                // Tạo đối tượng HoaDon
                HoaDon hoaDon = new HoaDon(maHoaDon, trangThai, thanhTien, nhanVien, khuyenMai, donDatPhong, ngayTao);
                dsHoaDon.add(hoaDon);
            }
            
        } catch (SQLException e) {
            e.printStackTrace(); // Log lỗi hoặc xử lý ngoại lệ
        } 
        return dsHoaDon;
    }
 // Thêm một hóa đơn mới vào cơ sở dữ liệu
    public boolean themHoaDon(HoaDon hoaDon) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO HoaDon (maHoaDon, trangThai, thanhTien, maNV, maKhuyenMai, maDonDatPhong, ngayTao) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            // Thiết lập các tham số cho câu lệnh SQL
            stmt.setString(1, hoaDon.getMaHoaDon()); // Mã hóa đơn
            stmt.setBoolean(2, hoaDon.getTrangThai()); // Trạng thái hóa đơn
            stmt.setDouble(3, hoaDon.getThanhTien()); // Thành tiền
            stmt.setString(4, hoaDon.getNhanVien().getMaNV()); // Mã nhân viên
            stmt.setString(5, hoaDon.getKhuyenMai() != null ? hoaDon.getKhuyenMai().getMaKhuyenMai() : null); // Mã khuyến mãi
            stmt.setString(6, hoaDon.getDonDatPhong() != null ? hoaDon.getDonDatPhong().getMaDon() : null); // Mã đơn đặt
            stmt.setTimestamp(7, Timestamp.valueOf(hoaDon.getNgayTao())); // Ngày tạo

            // Thực thi câu lệnh SQL
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
                LocalDateTime ngayTao = null;
                if (rs.getDate("ngayTao") != null) { // Kiểm tra nếu giá trị không phải là null
                    ngayTao = rs.getTimestamp("ngayTao").toLocalDateTime();
                }

                Boolean trangThai = rs.getBoolean("trangThai");
                double thanhTien = rs.getDouble("thanhTien");
                NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(rs.getString("maNV"));
                KhuyenMai khuyenMai = new KhuyenMaiDAL().getKhuyenMaiTheoMa(rs.getString("maKhuyenMai"));
                DonDatPhong donDatPhong = new DonDatPhongDAL().getDonDatPhongTheoMa(rs.getString("maDonDatPhong"));

                HoaDon hoaDon = new HoaDon(maHoaDon, trangThai, thanhTien, nhanVien, khuyenMai, donDatPhong, ngayTao);
                dsHoaDon.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsHoaDon;
    }
 // Inside HoaDonDAL class
    

    // Sửa thông tin hóa đơn trong cơ sở dữ liệu
    public boolean suaHoaDon(String maHoaDon, HoaDon hoaDon) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE HoaDon SET trangThai = ?, thanhTien = ?, maNV = ?, maKhuyenMai = ?, maDonDatPhong = ?, ngayTao = ? WHERE maHoaDon = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setBoolean(1, hoaDon.getTrangThai());
            stmt.setDouble(2, hoaDon.getThanhTien());
            stmt.setString(3, hoaDon.getNhanVien().getMaNV());
            stmt.setString(4, hoaDon.getKhuyenMai() != null ? hoaDon.getKhuyenMai().getMaKhuyenMai() : null);
            stmt.setString(5, hoaDon.getDonDatPhong() != null ? hoaDon.getDonDatPhong().getMaDon() : null);
            stmt.setTimestamp(6, Timestamp.valueOf(hoaDon.getNgayTao()));
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
                LocalDateTime ngayTao = rs.getDate("ngayTao") != null ? rs.getTimestamp("ngayTao").toLocalDateTime() : null;
                boolean trangThai = rs.getBoolean("trangThai");
                double thanhTien = rs.getDouble("thanhTien");
                NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(rs.getString("maNV"));
                KhuyenMai khuyenMai = new KhuyenMaiDAL().getKhuyenMaiTheoMa(rs.getString("maKhuyenMai"));
                DonDatPhong donDatPhong = new DonDatPhongDAL().getDonDatPhongTheoMa(rs.getString("maDonDatPhong"));

                hoaDon = new HoaDon(maHoaDon, trangThai, thanhTien, nhanVien, khuyenMai, donDatPhong, ngayTao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return hoaDon;
    }
 // Xóa hóa đơn theo mã hóa đơn
    public boolean xoaHoaDon(String maHoaDon) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "DELETE FROM HoaDon WHERE maHoaDon = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maHoaDon); // Thiết lập mã hóa đơn cần xóa
            n = stmt.executeUpdate(); // Thực thi câu lệnh SQL
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0; // Trả về true nếu việc xóa thành công
    }

    public String getLastHoaDonCode() {
        String lastHoaDonCode = null;

        String query = "SELECT MAX(maHoaDon) FROM HoaDon"; // Truy vấn để lấy mã phòng lớn nhất
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
            	lastHoaDonCode = rs.getString(1); // Lấy mã phòng lớn nhất
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return lastHoaDonCode;
    }
    public boolean xoaHoaDonTheoMa(String maHoaDon) {
        int n = 0;  // Biến lưu số lượng bản ghi bị ảnh hưởng bởi câu lệnh SQL
        Connection con = null;  // Biến kết nối cơ sở dữ liệu
        PreparedStatement stmt = null;  // Biến PreparedStatement để thực thi câu lệnh SQL

        try {
            // Mở kết nối cơ sở dữ liệu
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();

            // Câu lệnh SQL cập nhật trạng thái của hóa đơn (thay đổi trạng thái thành 0)
            String sql = "UPDATE HoaDon SET trangThai = 0 WHERE maHoaDon = ?";
            stmt = con.prepareStatement(sql);  // Tạo PreparedStatement từ câu lệnh SQL

            // Gán giá trị tham số cho câu lệnh SQL (truyền maHoaDon vào tham số thứ nhất)
            stmt.setString(1, maHoaDon);

            // Thực hiện câu lệnh cập nhật
            n = stmt.executeUpdate();  // Số bản ghi bị ảnh hưởng sẽ được lưu vào biến n
        } catch (SQLException e) {
            // Xử lý lỗi nếu có khi thực hiện câu lệnh SQL
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa hóa đơn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Đảm bảo đóng kết nối và PreparedStatement để tránh rò rỉ tài nguyên
            try {
                if (stmt != null) {
                    stmt.close();  // Đóng PreparedStatement
                }
                if (con != null) {
                    con.close();  // Đóng kết nối
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        // Trả về true nếu có ít nhất 1 bản ghi bị cập nhật (n > 0), ngược lại trả về false
        return n > 0;
    }


   
    public static void main(String[] args) {
        HoaDonDAL hoaDonDAL = new HoaDonDAL();
        
        // Định nghĩa khoảng ngày để tìm hóa đơn
        LocalDateTime startDate = LocalDateTime.of(2024, 10, 10, 5,3,2); // Ngày bắt đầu
        LocalDateTime endDate = LocalDateTime.of(2024, 10, 10,5,3,2); // Ngày kết thúc
        
        // Gọi phương thức getHoaDonByDateRange
        ArrayList<HoaDon> hoaDons = hoaDonDAL.getHoaDonByDateRange(startDate, endDate);
        
        // In kết quả ra console
        if (hoaDons.isEmpty()) {
            System.out.println("Không có hóa đơn nào trong khoảng thời gian này.");
        } else {
            System.out.println("Danh sách hóa đơn từ " + startDate + " đến " + endDate + ":");
            for (HoaDon hoaDon : hoaDons) {
                System.out.println(hoaDon);
            }
        }
    }

}
