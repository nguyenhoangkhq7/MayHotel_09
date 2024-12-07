package view.dialog;

import javax.swing.*;

import dal.DichVuDAL;
import database.ConnectDB;
import entity.DichVu;
import view.panel.DichVuPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThemDichVuDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtMaDV;
	private JTextField txtDonGia;
	private JTextField txtDonVi;
	private JTextField txtTenDV;
	private JTextField txtSoLuong;
	private JComboBox<String> cboHoatDong;
	private JButton btnLuu;
	private JButton btnHuyBo;

	public ThemDichVuDialog(String maDichVu, DichVuPanel dichVuPanel) {
		setTitle("Thêm Dịch Vụ");
		setSize(400, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7, 2, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Mã Dịch Vụ
		panel.add(new JLabel("Mã Dịch Vụ:"));
		txtMaDV = new JTextField(maDichVu); // Set mã dịch vụ
	    txtMaDV.setEditable(false);
		panel.add(txtMaDV);

		// Tên Dịch Vụ
		panel.add(new JLabel("Tên Dịch Vụ:"));
		txtTenDV = new JTextField();
		panel.add(txtTenDV);

		// Đơn Giá
		panel.add(new JLabel("Đơn Giá:"));
		txtDonGia = new JTextField();
		panel.add(txtDonGia);

		// Đơn Vị
		panel.add(new JLabel("Đơn Vị:"));
		txtDonVi = new JTextField();
		panel.add(txtDonVi);

		// Số Lượng
		panel.add(new JLabel("Số Lượng:"));
		txtSoLuong = new JTextField();
		panel.add(txtSoLuong);

		// Hoạt Động
		panel.add(new JLabel("Hoạt Động:"));
		cboHoatDong = new JComboBox<>(new String[] { "Có", "Không" });
		
		panel.add(cboHoatDong);

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

		// Action Listeners
//		btnLuu.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				dispose();
//			}
//		});

		btnLuu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nextMaDV = getNextMaDichVu();
			    txtMaDV.setText(nextMaDV);
				String tenDV = txtTenDV.getText().trim();
				String donGia = txtDonGia.getText().trim();
				String donVi = txtDonVi.getText().trim();
				String soLuong = txtSoLuong.getText().trim();
				String hoatDong = cboHoatDong.getSelectedItem().toString();

				if (nextMaDV.isEmpty() || tenDV.isEmpty() || donGia.isEmpty() || donVi.isEmpty() || soLuong.isEmpty()) {
					JOptionPane.showMessageDialog(ThemDichVuDialog.this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					double gia = Double.parseDouble(donGia);
					int sl = Integer.parseInt(soLuong);
					boolean isHoatDong = "Có".equals(hoatDong);

					// Tạo đối tượng DichVu
					DichVu dichVu = new DichVu(nextMaDV, gia, tenDV, sl, isHoatDong, donVi);

					// Gọi service để lưu vào cơ sở dữ liệu
					DichVuDAL dichVuDAL = new DichVuDAL(); // Giả định có lớp service
					boolean isSuccess = dichVuDAL.themDichVu(dichVu);

					if (isSuccess) {
						JOptionPane.showMessageDialog(ThemDichVuDialog.this, "Thêm dịch vụ thành công!", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
						dispose(); // Đóng dialog
						dichVuPanel.capNhatTable();
					} else {
						JOptionPane.showMessageDialog(ThemDichVuDialog.this, "Thêm dịch vụ thất bại!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(ThemDichVuDialog.this, "Đơn giá và số lượng phải là số hợp lệ!",
							"Lỗi", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(ThemDichVuDialog.this, "Lỗi khi thêm dịch vụ: " + ex.getMessage(),
							"Lỗi", JOptionPane.ERROR_MESSAGE);
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
	
	public String getNextMaDichVu() {
	    String nextMaDV = "DV0001"; // Giá trị mặc định nếu chưa có dữ liệu
	    try {
	        ConnectDB.getInstance().connect();
	        Connection con = ConnectDB.getConnection();
	        String sql = "SELECT MAX(maDichVu) FROM DichVu";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            String currentMax = rs.getString(1);
	            if (currentMax != null) {
	                // Chuyển đổi mã thành số để tăng lên 1
	                int numericPart = Integer.parseInt(currentMax.substring(2));
	                nextMaDV = String.format("DV%04d", numericPart + 1);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return nextMaDV;
	}


}
