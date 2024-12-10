package view.dialog;

import javax.swing.*;

import dal.LoaiPhongDAL;
import dal.PhongDAL;
import entity.LoaiPhong;
import entity.Phong;
import view.panel.QuanLyPhongPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThemPhongDiaLog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtMaDV;
	private JTextField txtDonGia;
	private JTextField txtDonVi;
	private JTextField txtTenDV;
	private JTextField txtSoLuong;
	private JComboBox<String> cboHoatDong;
	private JButton btnLuu;
	private JButton btnHuyBo;
	private JTextField txtMaPhong;
	private JTextField txtTenPhong;
	private JTextField txtLoaiPhong;
	private JComboBox cboTrangThai;
	private JTextField txtMoTa;
	private JTextField txtTầng;
	private JTextField txtTang;

	public ThemPhongDiaLog(String maPhong, QuanLyPhongPanel quanLyPhongPanel) {
		setTitle("Thêm phòng");
		setSize(400, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7, 2, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Mã Dịch Vụ
		panel.add(new JLabel("Mã Phòng:"));
		txtMaPhong = new JTextField(maPhong); // Set mã dịch vụ
		txtMaPhong.setEditable(false);
		panel.add(txtMaPhong);

		// Tên Dịch Vụ
		panel.add(new JLabel("Tên Phòng:"));
		txtTenPhong = new JTextField();
		panel.add(txtTenPhong);

		// Đơn Giá
		panel.add(new JLabel("Loại Phòng:"));
		txtLoaiPhong = new JTextField();
		panel.add(txtLoaiPhong);

		// Đơn Vị
		panel.add(new JLabel("Trạng Thái Phòng:"));
		cboTrangThai = new JComboBox<>(new String[] { "Trống", "Đã đặt" });
		
		panel.add(cboTrangThai);
		panel.add(new JLabel("Mô tả:"));
		txtMoTa = new JTextField();
		panel.add(txtMoTa);

		// Số Lượng
		panel.add(new JLabel("Tầng:"));
		txtTang = new JTextField();
		panel.add(txtTang);

		// Hoạt Động
		

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
		        String nextMaPhong = generateRoomCode(); // Giả định có hàm này để tạo mã phòng tự động
		        txtMaPhong.setText(nextMaPhong); // Điền mã phòng vào JTextField

		        // Lấy giá trị từ các trường nhập liệu
		        String tenPhong = txtTenPhong.getText().trim();
		        LoaiPhong loaiPhong = new LoaiPhongDAL().getLoaiPhongTheoMa(txtLoaiPhong.getText());
		        String trangThaiPhong = cboTrangThai.getSelectedItem().toString();
		        String moTa = txtMoTa.getText().trim();
		        String tang = txtTang.getText().trim();
		        String loaiPhongText = txtLoaiPhong.getText().trim(); // Lấy giá trị loại phòng từ trường nhập liệu

		        // Kiểm tra các trường nhập liệu để đảm bảo không bỏ trống
		        if (nextMaPhong.isEmpty() || tenPhong.isEmpty() || loaiPhong == null || trangThaiPhong.isEmpty() || moTa.isEmpty() || tang.isEmpty() || loaiPhongText.isEmpty()) {
		            JOptionPane.showMessageDialog(ThemPhongDiaLog.this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return; // Dừng lại nếu có trường thiếu
		        }

		        // Kiểm tra định dạng tên phòng, phải bắt đầu với "Phòng" và có thể chứa tất cả ký tự
		        if (!tenPhong.matches("^Phòng .*$")) {
		            JOptionPane.showMessageDialog(ThemPhongDiaLog.this, "Tên phòng phải bắt đầu bằng 'Phòng' !", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return; // Dừng lại nếu định dạng không hợp lệ
		        }

		        // Kiểm tra định dạng loại phòng, phải bắt đầu bằng "LP" và có một dấu cách, tiếp theo là số từ 01 đến 06
		        if (!loaiPhongText.matches("^LP(0[1-6])$")) {
		            JOptionPane.showMessageDialog(ThemPhongDiaLog.this, "Loại phòng phải có định dạng 'LP01', 'LP02', ..., 'LP06'!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return; // Dừng lại nếu định dạng không hợp lệ
		        }

		        // Kiểm tra định dạng tầng, phải bắt đầu bằng "Tầng" và có một dấu cách, tiếp theo là số từ 1 đến 5
		        if (!tang.matches("^Tầng [1-5]$")) {
		            JOptionPane.showMessageDialog(ThemPhongDiaLog.this, "Tầng phải có định dạng 'Tầng 1', 'Tầng 2', ..., 'Tầng 5'!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return; // Dừng lại nếu định dạng không hợp lệ
		        }

		        try {
		            // Kiểm tra trạng thái phòng (Có/Không)
		            boolean isTrangThaiPhong = "Có".equals(trangThaiPhong); // Chỉ chấp nhận "Có" là trạng thái hợp lệ

		            // Tạo đối tượng Phong (phòng) với các thông tin đã nhập
		            Phong phong = new Phong(nextMaPhong, tenPhong, loaiPhong, isTrangThaiPhong, moTa, tang);

		            // Gọi service để lưu vào cơ sở dữ liệu
		            PhongDAL phongDAL = new PhongDAL(); // Giả định có lớp service để lưu phòng
		            boolean isSuccess = phongDAL.themPhong(phong);

		            // Thông báo cho người dùng về kết quả
		            if (isSuccess) {
		                JOptionPane.showMessageDialog(ThemPhongDiaLog.this, "Thêm phòng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		                dispose(); // Đóng dialog
		                quanLyPhongPanel.capNhatTable(); // Cập nhật bảng phòng
		            } else {
		                JOptionPane.showMessageDialog(ThemPhongDiaLog.this, "Thêm phòng thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (Exception ex) {
		            // Xử lý các lỗi không mong muốn
		            JOptionPane.showMessageDialog(ThemPhongDiaLog.this, "Lỗi khi thêm phòng: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
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
	
	public String generateRoomCode() {
	    String lastRoomCode = new PhongDAL().getLastRoomCode(); // Lấy mã phòng cuối cùng
	    int newRoomNumber = 1; // Mặc định bắt đầu từ 1 nếu không có mã phòng nào

	    if (lastRoomCode != null) {
	        // Tách phần số từ mã phòng
	        String numberPart = lastRoomCode.substring(1); // Bỏ qua ký tự 'P'
	        newRoomNumber = Integer.parseInt(numberPart) + 1; // Tăng số lên 1
	    }

	    // Tạo mã phòng mới theo định dạng "PXXX"
	    return String.format("P%03d", newRoomNumber); // Định dạng thành 3 chữ số
	}


}
