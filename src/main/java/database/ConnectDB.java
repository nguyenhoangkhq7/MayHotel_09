package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection con = null;  // biến con là biến kết nối với sql
	private static ConnectDB instance = new ConnectDB();

	public static ConnectDB getInstance() {  // mỗi instance sẽ có biến con, 
		return instance; 					//nếu lấy được instance ứng với biến con đã kết nối thì có thể sử dụng kết nối
	}
	public void connect() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databasename=QuanLyKS;encrypt=false";
		String user = "sa";

		String password = "Thaibao123"; // đổi thành pass word của mọi ng vào


		con = DriverManager.getConnection(url, user, password);
	}
	public void disconnect() {
		if (con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
	public static Connection getConnection() {
		return con;
	}
	public static void main(String[] args) {
		try {
			ConnectDB.getInstance().connect();
			System.out.println("Kết nối thành công");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		System.out.println(con);
	}
}