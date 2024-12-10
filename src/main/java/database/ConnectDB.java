package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    public static Connection con = null;  // Biến con là biến kết nối với SQL
    private static ConnectDB instance = new ConnectDB();

    // Đảm bảo chỉ có một instance của ConnectDB
    public static ConnectDB getInstance() {
        return instance;
    }

    // Phương thức kết nối với cơ sở dữ liệu
    public void connect() throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyKS;trustServerCertificate=true;encrypt=true";
        String user = "sa";
        String password = "sapassword";
        
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;  // Ném lại SQLException sau khi xử lý
        }
    }

    // Phương thức ngắt kết nối với cơ sở dữ liệu
    public void disconnect() {
        if (con != null) {
            try {
                if (!con.isClosed()) {  // Kiểm tra nếu kết nối chưa đóng
                    con.close();
                    System.out.println("Kết nối đã được đóng.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Phương thức lấy kết nối hiện tại
    public static Connection getConnection() {
        return con;
    }

    // Phương thức main để kiểm tra kết nối
    public static void main(String[] args) {
        try {
            // Kết nối đến cơ sở dữ liệu
            ConnectDB.getInstance().connect();
            System.out.println("Kết nối thành công");

            // Lấy kết nối và in ra thông tin kết nối
            Connection con = ConnectDB.getConnection();
            System.out.println(con);

            // Ngắt kết nối sau khi sử dụng
            ConnectDB.getInstance().disconnect();
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối: " + e.getMessage());
        }
    }
}
