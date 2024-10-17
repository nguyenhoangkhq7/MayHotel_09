/*
    *MayHotel  day creative: 10/15/2024
    version: 2023.2  IntelliJ IDEA
    author: Đặng Thái Bảo  */
    /*
       *class description:
            write description right here   
     */

package dal;

// import các thư viện
import java.util.ArrayList;
import java.lang.classfile.attribute.StackMapTableAttribute;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

//import các lớp emtity và lop database
import entity.DichVu;
import database.ConnectDB;

public class DichVuDAL {
	//Khai báo ds chứa các phòng và contructor khởi tạo danh sách phòng
	public ArrayList<DichVu> dsDichVu;
	public DichVuDAL() {
		dsDichVu = new ArrayList<>();
	}
	//Kết nói với cơ sở dữ liệu
	Connection con;
	
	//Lấy danh sách dịch vụ từ cơ sở dữ liệu
	public ArrayList<DichVu> getAllDichVu(){
		try {
			ConnectDB.getInstance().connect(); // kết nói đến cở sở dữ liệu
			con = ConnectDB.getConnection(); // gan lay ket noi
			String sql = "SELECT * FROM DichVu"; // tao cau lenh
			Statement stmt = con.createStatement(); // Tao mot lenh thu thi
			ResultSet rs = stmt.executeQuery(sql); //thuc thi lenh lay ket qua vao resultSet
			
			// Duyet qua cac ket qua de tao doi tuong 
			while (rs.next()) {
				//Lay du lieu
				String maDichVu = rs.getString(1);
				double donGia = rs.getDouble(2);
				String tenDichVu = rs.getString(3);
				int soLuongTon = rs.getInt(4);
				String moTa = rs.getString(5);
				LocalDate ngayTao = rs.getDate(6).toLocalDate();
				boolean conHoatDong = rs.getBoolean(7);
				//Tao doi tuong dich vu
				DichVu dichVu = new DichVu(maDichVu, donGia, tenDichVu, soLuongTon, moTa, ngayTao, conHoatDong);
				dsDichVu.add(dichVu);
			}
		
		} catch (SQLException e) {
			e.printStackTrace(); //in ra ngoại lệ nếu có lỗi
		}
		return dsDichVu; // trả về danh sách phòng
	}
	
	//Them moi dich vu
	public boolean themDichVu(DichVu dichVu) {
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			con = ConnectDB.getConnection();
			String sql = "INSERT INTO DichVu (maDichVu, donGia, tenDichVu, soLuongTon, moTa, ngayTao, conHoatDong) VALUES (?,?,?,?,?,?,?)" ;
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, dichVu.getMaDichVu());
			stmt.setDouble(2, dichVu.getDonGia());
			stmt.setString(3, dichVu.getTenDichVu());
			stmt.setInt(4, dichVu.getSoLuongTon());
			stmt.setString(5, dichVu.getMoTa());
			stmt.setDate(6, Date.valueOf(dichVu.getNgayTao()));
			stmt.setBoolean(7, dichVu.isConHoatDong());
			n = stmt.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	//Sửa dich vu
	public boolean suaDichVu(String maDichVu, DichVu dichVu) {
		int n = 0 ; 
		try {
			ConnectDB.getInstance().connect();
			con = ConnectDB.getConnection();
			String sql = "UPDATE DichVu SET donGia = ?, tenDichVu = ?, soLuongTon = ? , moTa = ?, conHoatDong = ? WHERE maDichVu = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setDouble(1, dichVu.getDonGia());
			stmt.setString(2, dichVu.getTenDichVu());
			stmt.setInt(3, dichVu.getSoLuongTon());
			stmt.setString(4, dichVu.getMoTa());
			stmt.setBoolean(5, dichVu.isConHoatDong());
			stmt.setNString(6, maDichVu);
			
			n= stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	//Xóa dịch vụ
	public boolean huyDichVu(String maDichVu) {
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			con = ConnectDB.getConnection();
			String sql = "UPDATE DICHVU SET conHoatDong = 0 WHERE maDichVu = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maDichVu);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	//thử
	public static void main(String[] args) {
		DichVuDAL dichVuDAL = new DichVuDAL();
		dichVuDAL.getAllDichVu();
		 ArrayList<DichVu> danhSachDichVu = dichVuDAL.getAllDichVu();
		        // Duyệt qua danh sách và in ra thông tin từng dịch vụ
		        for (DichVu dichVu : danhSachDichVu) {
		            System.out.println("Mã dịch vụ: " + dichVu.getMaDichVu());
		            System.out.println("Tên dịch vụ: " + dichVu.getTenDichVu());
		            System.out.println("Đơn giá: " + dichVu.getDonGia());
		            System.out.println("Số lượng tồn: " + dichVu.getSoLuongTon());
		            System.out.println("Mô tả: " + dichVu.getMoTa());
		            System.out.println("Ngày tạo: " + dichVu.getNgayTao());
		            System.out.println("Còn hoạt động: " + dichVu.isConHoatDong());
		            System.out.println("-------------------------------------");
		        }
//		        DichVu dichVuMoi = new DichVu("123",12, "123", 23, "123", LocalDate.now(), false);
//		    	boolean checkThem = dichVuDAL.themDichVu(dichVuMoi);
//		       System.out.println(checkThem);
//		       DichVu dichVuMoi1 = new DichVu("123",23, "456", 45, "567", LocalDate.now(), true);
//		       boolean checkSua = dichVuDAL.suaDichVu("123", dichVuMoi1);
		        boolean checkHuy = dichVuDAL.huyDichVu("123");
}
	
	
}

