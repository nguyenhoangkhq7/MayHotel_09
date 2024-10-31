/*
    *MayHotel  day creative: 10/15/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang
 */
/*
    *class description:
    Data Access Layer (DAL) for managing LoaiPhong objects in the database.
*/

package dal;

import entity.LoaiPhong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.ConnectDB;

public class LoaiPhongDAL {
    private ArrayList<LoaiPhong> dsLoaiPhong;
    private Connection con;

    public LoaiPhongDAL() {
        dsLoaiPhong = new ArrayList<>();
    }

    // Get all room types
    public ArrayList<LoaiPhong> getAllLoaiPhong() {
        dsLoaiPhong.clear(); // Clear the existing list
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM LoaiPhong";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                LoaiPhong loaiPhong = new LoaiPhong(
                        rs.getString("maLoaiPhong"),
                        rs.getString("tenLoaiPhong"),
                        rs.getInt("sucChua"),
                        rs.getDouble("donGia"),
                        rs.getString("mota"),
                        rs.getDouble("chietKhau")
                );
                dsLoaiPhong.add(loaiPhong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dsLoaiPhong;
    }

    // Get room type by code
    public LoaiPhong getLoaiPhongTheoMa(String maLoaiPhong) {
        LoaiPhong loaiPhong = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM LoaiPhong WHERE maLoaiPhong = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, maLoaiPhong);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                loaiPhong = new LoaiPhong(
                        rs.getString("maLoaiPhong"),
                        rs.getString("tenLoaiPhong"),
                        rs.getInt("sucChua"),
                        rs.getDouble("donGia"),
                        rs.getString("mota"),
                        rs.getDouble("chietKhau")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return loaiPhong;
    }

    // Add new room type
    public boolean themLoaiPhong(LoaiPhong loaiPhong) {
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "INSERT INTO LoaiPhong (maLoaiPhong, tenLoaiPhong, sucChua, donGia, mota, chietKhau) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, loaiPhong.getMaLoaiPhong());
            stmt.setString(2, loaiPhong.getTenLoaiPhong());
            stmt.setInt(3, loaiPhong.getSucChua());
            stmt.setDouble(4, loaiPhong.getDonGia());
            stmt.setString(5, loaiPhong.getMota());
            stmt.setDouble(6, loaiPhong.getChietKhau());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    // Update room type
    public boolean suaLoaiPhong(String maLoaiPhong, LoaiPhong loaiPhong) {
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "UPDATE LoaiPhong SET tenLoaiPhong = ?, sucChua = ?, donGia = ?, mota = ?, chietKhau = ? WHERE maLoaiPhong = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, loaiPhong.getTenLoaiPhong());
            stmt.setInt(2, loaiPhong.getSucChua());
            stmt.setDouble(3, loaiPhong.getDonGia());
            stmt.setString(4, loaiPhong.getMota());
            stmt.setDouble(5, loaiPhong.getChietKhau());
            stmt.setString(6, maLoaiPhong);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    // Delete room type
    public boolean xoaLoaiPhong(String maLoaiPhong) {
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            String sql = "DELETE FROM LoaiPhong WHERE maLoaiPhong = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maLoaiPhong);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    private void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
