package dal;

import java.sql.*;
import java.util.ArrayList;
import entity.DichVu;
import database.ConnectDB;

public class DichVuDAL {
    private Connection con;

    // Lấy dịch vụ theo mã
    public DichVu getDichVuTheoMa(String maDichVu) {
        DichVu dichVu = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM DichVu WHERE maDichVu = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maDichVu);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                dichVu = new DichVu(
                    rs.getString(1),
                    rs.getDouble(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getBoolean(5),
                    rs.getString(6)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dichVu;
    }

    // Lấy tất cả dịch vụ
    public ArrayList<DichVu> getAllDichVu() {
        ArrayList<DichVu> dsDichVu = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM DichVu";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                dsDichVu.add(new DichVu(
                    rs.getString(1),
                    rs.getDouble(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getBoolean(5),
                    rs.getString(6)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDichVu;
    }

    // Thêm mới dịch vụ
    public boolean themDichVu(DichVu dichVu) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO DichVu (maDichVu, donGia, tenDichVu, soLuongTon, conHoatDong, donVi) VALUES (?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, dichVu.getMaDichVu());
            stmt.setDouble(2, dichVu.getDonGia());
            stmt.setString(3, dichVu.getTenDichVu());
            stmt.setInt(4, dichVu.getSoLuongTon());
            stmt.setBoolean(5, dichVu.isConHoatDong());
            stmt.setString(6, dichVu.getDonVi());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Sửa dịch vụ
    public boolean suaDichVu(DichVu dichVu) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE DichVu SET donGia = ?, tenDichVu = ?, soLuongTon = ?, conHoatDong = ?, donVi = ? WHERE maDichVu = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDouble(1, dichVu.getDonGia());
            stmt.setString(2, dichVu.getTenDichVu());
            stmt.setInt(3, dichVu.getSoLuongTon());
            stmt.setBoolean(4, dichVu.isConHoatDong());
            stmt.setString(5, dichVu.getDonVi());
            stmt.setString(6, dichVu.getMaDichVu());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
    public static void main(String[] args) {
		DichVuDAL dal = new DichVuDAL();
		ArrayList<DichVu> ds = dal.getAllDichVu();
		for(DichVu dv : ds) {
			System.out.println(dv);
		}
	}
}
