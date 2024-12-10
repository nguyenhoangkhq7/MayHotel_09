package view.dialog;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import dal.DichVuDAL;
import dal.DonDatPhongDAL;
import dal.LoaiPhongDAL;
import dal.NhanVienDAL;
import dal.HoaDonDAL;
import dal.KhuyenMaiDAL;
import database.ConnectDB;
import entity.DichVu;
import entity.DonDatPhong;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.HoaDon;
import entity.KhuyenMai;
import view.panel.DichVuPanel;
import view.panel.QuanLyHoaDonPanel;
import view.panel.QuanLyKhuyenMaiPanel;
import view.panel.QuanLyHoaDonPanel;
import view.panel.QuanLyHoaDonPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ThemKhuyenMaiDiaLog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JButton btnLuu;
	private JButton btnHuyBo;
	

	private JTextField txtMaKhuyenMai;
	private JTextField txtTenKhuyenMai;
	private JTextField txtGiaTri;
	private JComboBox cboConHoatDong;
	private JTextField txtLoaiKhachHang;

	private JTextField txtSoLuong;

	public ThemKhuyenMaiDiaLog(String maKhuyenMai, QuanLyKhuyenMaiPanel quanLyKhuyenMaiPanel) {
		setTitle("Thêm Khuyến Mãi");
		setSize(400, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		// Panel chứa các thành phần
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(8, 2, 10, 10)); // Đặt layout dạng lưới với 8 hàng và 2 cột

		// Mã Khuyến Mãi
		panel.add(new JLabel("Mã Khuyến Mãi:"));
		txtMaKhuyenMai = new JTextField();
		panel.add(txtMaKhuyenMai);
		txtMaKhuyenMai.setEditable(false);

		// Tên Khuyến Mãi
		panel.add(new JLabel("Tên Khuyến Mãi:"));
		txtTenKhuyenMai = new JTextField();
		panel.add(txtTenKhuyenMai);
		

		// Giá Trị
		panel.add(new JLabel("Giá Trị:"));
		txtGiaTri = new JTextField();
		panel.add(txtGiaTri);

		// Ngày Bắt Đầu
		panel.add(new JLabel("Ngày Bắt Đầu:"));
		JDateChooser txtNgayBatDau = new JDateChooser();
		txtNgayBatDau.setDateFormatString("dd/MM/yyyy"); // Đặt định dạng ngày
		panel.add(txtNgayBatDau);

		// Hoạt Động
		panel.add(new JLabel("Còn Hoạt Động:"));
		cboConHoatDong = new JComboBox<>(new String[] { "Có", "Không" });
		panel.add(cboConHoatDong);

		// Số Lượng
		panel.add(new JLabel("Số Lượng:"));
		txtSoLuong = new JTextField();
		panel.add(txtSoLuong);

		// Ngày Kết Thúc
		panel.add(new JLabel("Ngày Kết Thúc:"));
		JDateChooser txtNgayKetThuc = new JDateChooser();
		txtNgayKetThuc.setDateFormatString("dd/MM/yyyy"); // Đặt định dạng ngày
		panel.add(txtNgayKetThuc);

		// Loại Khách Hàng Áp Dụng
		panel.add(new JLabel("Loại Khách Hàng Áp Dụng:"));
		txtLoaiKhachHang = new JTextField();
		panel.add(txtLoaiKhachHang);



		
		
		

		// Buttons
		btnLuu = new JButton("Lưu");
		btnLuu.setBackground(new Color(243, 125, 0));
		btnLuu.setForeground(new Color(255, 255, 255));

		btnHuyBo = new JButton("Hủy Bỏ");
		btnHuyBo.setBackground(new Color(243, 125, 0));
		btnHuyBo.setForeground(new Color(255, 255, 255));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		buttonPanel.add(btnLuu);
		buttonPanel.add(btnHuyBo);

		// Add panels to dialog
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		

		btnLuu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nextMaKM = generateKhuyenMaiCode();
			    txtMaKhuyenMai.setText(nextMaKM);
			    String tenKM = txtTenKhuyenMai.getText();
				String giaTri = txtGiaTri.getText();
				String hoatDong = cboConHoatDong.getSelectedItem().toString();
				Date ngayBatDau = txtNgayBatDau.getDate(); // Ngày kết thúc khuyến mãi
				LocalDateTime localNgayBatDau = convertToLocalDateTime(ngayBatDau);

				String soLuong = txtSoLuong.getText();
				Date ngayKetThuc = txtNgayKetThuc.getDate(); // Ngày kết thúc khuyến mãi
				LocalDateTime localNgayKetThuc = convertToLocalDateTime(ngayKetThuc);
				String loaiKH = txtLoaiKhachHang.getText();
				
				
				

				// Sử dụng loaiHD
				



				

				if (nextMaKM.isEmpty() || tenKM.isEmpty() || ngayBatDau == null) {
					JOptionPane.showMessageDialog(ThemKhuyenMaiDiaLog.this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					
					return;
				}

				try {
					double gT = Double.parseDouble(giaTri);
					int sl = Integer.parseInt(soLuong);
					boolean isHoatDong = "Có".equals(hoatDong);

		        
					KhuyenMai khuyenMai = new KhuyenMai(nextMaKM, tenKM, gT, localNgayBatDau,isHoatDong, sl, localNgayKetThuc,loaiKH);


		            // Gọi service để lưu vào cơ sở dữ liệu
		            KhuyenMaiDAL khuyenMaiDAL = new KhuyenMaiDAL(); // Giả định có lớp service để lưu phòng
		            boolean isSuccess = khuyenMaiDAL.themKhuyenMai(khuyenMai);

		            if (isSuccess) {
		                JOptionPane.showMessageDialog(ThemKhuyenMaiDiaLog.this, "Thêm khuyến mãi thành công!", "Thông báo",
		                        JOptionPane.INFORMATION_MESSAGE);
		                dispose(); // Đóng dialog
		                quanLyKhuyenMaiPanel.capNhatTableKhuyenMai(); // Cập nhật bảng phòng
		            } else {
		                JOptionPane.showMessageDialog(ThemKhuyenMaiDiaLog.this, "Thêm Khuyến Mãi thất bại!", "Lỗi",
		                        JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(ThemKhuyenMaiDiaLog.this, "Vui lòng nhập số hợp lệ cho các trường số!", "Lỗi",
		                    JOptionPane.ERROR_MESSAGE);
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(ThemKhuyenMaiDiaLog.this, "Lỗi khi thêm khuyến mãi: " + ex.getMessage(), "Lỗi",
		                    JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		btnHuyBo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
	}
	private LocalDateTime convertToLocalDateTime(Date date) {
	    if (date != null) {
	        return date.toInstant()
	                   .atZone(ZoneId.systemDefault())
	                   .toLocalDateTime();
	    }
	    return null;
	}
	
	public String generateKhuyenMaiCode() {
	    KhuyenMaiDAL khuyenMaiDAL = new KhuyenMaiDAL(); // Đối tượng DAL để truy xuất dữ liệu
	    String lastKhuyenMaiCode = khuyenMaiDAL.getLastKhuyenMaiCode(); // Lấy mã khuyến mãi cuối cùng từ DB
	    int newKhuyenMaiNumber = 1; // Mặc định bắt đầu từ 1 nếu không có mã khuyến mãi nào

	    if (lastKhuyenMaiCode != null && lastKhuyenMaiCode.startsWith("KM")) {
	        try {
	            // Tách phần số từ mã khuyến mãi (bỏ tiền tố "KM")
	            String numberPart = lastKhuyenMaiCode.substring(2); // Lấy từ ký tự thứ 3 trở đi
	            newKhuyenMaiNumber = Integer.parseInt(numberPart) + 1; // Tăng số lên 1
	        } catch (NumberFormatException e) {
	            System.err.println("Lỗi: Mã khuyến mãi cuối cùng không hợp lệ - " + lastKhuyenMaiCode);
	        }
	    }

	    // Tạo mã khuyến mãi mới theo định dạng "KM00001"
	    return String.format("KM%05d", newKhuyenMaiNumber); // Định dạng thành 5 chữ số (KM00001, KM00002,...)
	}




}
