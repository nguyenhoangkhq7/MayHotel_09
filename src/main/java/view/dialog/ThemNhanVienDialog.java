package view.dialog;

import javax.swing.*;

import dal.NhanVienDAL;
import dal.TaiKhoanDAL;
import entity.NhanVien;
import entity.TaiKhoan;
import view.panel.QuanLyNhanVienPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThemNhanVienDialog extends JDialog {
	private JTextField txtMaNV;
	private JTextField txtHoTen;
	private JTextField txtEmail;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JTextField txtTenTaiKhoan;
	private JTextField txtSoCanCuoc;
	private JPasswordField txtMatKhau;
	private JComboBox<String> cboVaiTro;
	private JComboBox<String> cboHoatDong;
	private JButton btnLuu;
	private JButton btnHuyBo;

	public ThemNhanVienDialog(QuanLyNhanVienPanel quanLyNhanVienPanel) {
		setTitle("Thêm nhân viên");
		setSize(400, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(10, 2, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		panel.add(new JLabel("Vai Trò:"));
		cboVaiTro = new JComboBox<>(new String[] { "","Quản lý", "Nhân viên" });
		cboVaiTro.setSelectedIndex(0);
		panel.add(cboVaiTro);

		panel.add(new JLabel("Mã Nhân Viên:"));
		txtMaNV = new JTextField(); 
		txtMaNV.setEditable(false);
		panel.add(txtMaNV);

		panel.add(new JLabel("Họ Tên:"));
		txtHoTen = new JTextField();
		panel.add(txtHoTen);

		panel.add(new JLabel("Số Điện Thoại:"));
		txtSDT = new JTextField();
		panel.add(txtSDT);

		panel.add(new JLabel("Số Căn Cước:"));
		txtSoCanCuoc = new JTextField();
		panel.add(txtSoCanCuoc);

		panel.add(new JLabel("Hoạt Động:"));
		cboHoatDong = new JComboBox<>(new String[] { "","Có", "Không" });
		cboHoatDong.setSelectedIndex(0);
		panel.add(cboHoatDong);

		panel.add(new JLabel("Email:"));
		txtEmail = new JTextField();
		panel.add(txtEmail);

		panel.add(new JLabel("Địa Chỉ:"));
		txtDiaChi = new JTextField();
		panel.add(txtDiaChi);

		panel.add(new JLabel("Tên Tài Khoản:"));
		txtTenTaiKhoan = new JTextField();
		txtTenTaiKhoan.setEditable(false);
		panel.add(txtTenTaiKhoan);

		panel.add(new JLabel("Mật khẩu:"));
		txtMatKhau = new JPasswordField();
		panel.add(txtMatKhau);

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

		btnHuyBo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		cboVaiTro.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		            String vaiTro = (String) cboVaiTro.getSelectedItem();
		            
		            if (vaiTro == null || vaiTro.isEmpty()) {
		                txtMaNV.setText("");
		                txtTenTaiKhoan.setText("");
		                return;
		            }

		            String prefix = vaiTro.equalsIgnoreCase("Quản lý") ? "QL" : "NV";
		            
		            String maNV = generateMaNV(prefix);
		            
		            txtMaNV.setText(maNV);
		            txtTenTaiKhoan.setText(maNV);
		        } catch (Exception ex) {
		            ex.printStackTrace(); // In lỗi ra console để debug
		            JOptionPane.showMessageDialog(null, "Lỗi xảy ra: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		
//Sự kiện btnLuu
		btnLuu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String maNV = txtMaNV.getText().trim();
				String tenNV = txtHoTen.getText().trim();
				String soDienThoai = txtSDT.getText().trim();
				String soCanCuoc = txtSoCanCuoc.getText().trim();
				String hoatDong = cboHoatDong.getSelectedItem().toString();
				String email = txtEmail.getText().trim();
				String diaChi = txtDiaChi.getText().trim();
				String vaiTro = cboVaiTro.getSelectedItem().toString();
				String tenTaiKhoan = txtTenTaiKhoan.getText().trim();
				String matKhau = txtMatKhau.getText().trim();

				if (maNV.isEmpty() || tenNV.isEmpty() || soDienThoai.isEmpty() || soCanCuoc.isEmpty() || email.isEmpty() || diaChi.isEmpty() || tenTaiKhoan.isEmpty() || matKhau.isEmpty()) {
					JOptionPane.showMessageDialog(ThemNhanVienDialog.this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
				
					boolean isHoatDong = "Có".equals(hoatDong);
					TaiKhoan taiKhoan = new TaiKhoan(tenTaiKhoan, matKhau);
					NhanVien nhanVien = new NhanVien (maNV, tenNV, soDienThoai, soCanCuoc, isHoatDong, email, diaChi, vaiTro, taiKhoan);

					TaiKhoanDAL taiKhoanDAL = new TaiKhoanDAL();
					boolean isSuccess = taiKhoanDAL.themTaiKhoan(taiKhoan);
					
					NhanVienDAL nhanVienDAL = new NhanVienDAL(); 
					boolean isSuccess1 = nhanVienDAL.themNhanVien(nhanVien);

					if (isSuccess && isSuccess1) {
						JOptionPane.showMessageDialog(ThemNhanVienDialog.this, "Thêm nhân viên thành công!", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
						dispose(); // Đóng dialog
						quanLyNhanVienPanel.capNhatTable();
					} else {
						JOptionPane.showMessageDialog(ThemNhanVienDialog.this, "Thêm nhân viên thất bại!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				} 
				 catch (Exception ex) {
					JOptionPane.showMessageDialog(ThemNhanVienDialog.this, "Lỗi khi thêm nhân viên: " + ex.getMessage(),
							"Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

	}


	
	private String generateMaNV(String prefix) {
	    int newNumber = 1; // Giá trị mặc định

	    try (Connection con = ConnectDB.getConnection(); // Kết nối sẽ tự động đóng sau khi khối try kết thúc
	         PreparedStatement stmt = con.prepareStatement(
	             "SELECT MAX(CAST(SUBSTRING(MaNV, 3, LEN(MaNV) - 2) AS INT)) FROM NhanVien WHERE MaNV LIKE ?")) {

	        stmt.setString(1, prefix + "%");
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                Integer maxNumber = rs.getInt(1);
	                if (rs.wasNull()) {
	                    newNumber = 1; // Nếu chưa có dữ liệu, bắt đầu từ 1
	                } else {
	                    newNumber = maxNumber + 1;
	                }
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        throw new RuntimeException("Lỗi kết nối hoặc truy vấn cơ sở dữ liệu", ex);
	    }

	    return prefix + String.format("%03d", newNumber);
	}

	
	public class ConnectDB {
	    public static Connection getConnection() throws SQLException {
	        String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyKS;encrypt=false";
	        String username = "sa";
	        String password = "123456";
	        Connection conn = DriverManager.getConnection(url, username, password);
	        return conn;
	    }
	}

	
	






}
