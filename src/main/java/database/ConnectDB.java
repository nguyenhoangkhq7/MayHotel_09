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
		String url = "jdbc:sqlserver://localhost:1433;databasename=QuanLyDatPhongKS;encrypt=false";
		String user = "sa";
<<<<<<< HEAD
		String password = "123456"; // đổi thành pass word của mọi ng vào
=======
		String password = "Thaibao123"; // đổi thành pass word của mọi ng vào
>>>>>>> ccf4c304ca4601214b3d5571ab2cf7e2deb09b2f
		con = DriverManager.getConnection(url, user, password);
		
//		con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;instanceName=SQLEXPRESS;databaseName=QuanLyDatPhongKS;encrypt=false", "sa", "sapassword");

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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con = ConnectDB.getConnection();
		System.out.println(con);
	}
}
