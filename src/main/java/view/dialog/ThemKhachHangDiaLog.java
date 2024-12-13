package view.dialog;

import javax.swing.*;

import dal.KhachHangDAL;
import entity.LoaiKhachHang;
import entity.KhachHang;
import view.panel.QuanLyKhachHangPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThemKhachHangDiaLog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtMaDV;
	private JTextField txtDonGia;
	private JTextField txtDonVi;
	private JTextField txtTenDV;
	private JTextField txtSoLuong;
	private JComboBox<String> cboHoatDong;
	private JButton btnLuu;
	private JButton btnHuyBo;
	private JTextField txtMaKhachHang;
	private JTextField txtTenKhachHang;
	private JTextField txtLoaiKhachHang;
	private JComboBox cboTrangThai;
	private JTextArea txtMoTa;
	private JTextField txtTầng;
	private JTextField txtTang;
	private JTextField txtSDT;
	private JTextField txtCCCD;
	private JTextField txtEmail;
	private JComboBox cboLoaiKH;
	private JTextField txtTienTichLuy;

	public ThemKhachHangDiaLog(String maKH, QuanLyKhachHangPanel quanLyKhachHangPanel) {
		setTitle("Thêm Khách Hàng");
		setSize(400, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7, 2, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Mã Dịch Vụ
		panel.add(new JLabel("Mã Khách Hàng:"));
		txtMaKhachHang = new JTextField(maKH); // Set mã dịch vụ
		txtMaKhachHang.setEditable(false);
		panel.add(txtMaKhachHang);

		// Tên Dịch Vụ
		panel.add(new JLabel("Họ Tên:"));
		txtTenKhachHang = new JTextField();
		panel.add(txtTenKhachHang);

		// Đơn Giá
		panel.add(new JLabel("Số điện thoại:"));
		txtSDT = new JTextField();
		panel.add(txtSDT);

		// Đơn Vị
		panel.add(new JLabel("Tiền tích lũy:"));
		txtTienTichLuy = new JTextField();
		panel.add(txtTienTichLuy);
		

		// Tạo JTextArea và thiết lập tính năng tự động xuống dòng
		

		// Bọc JTextArea trong JScrollPane
		// Thêm JScrollPane chứa JTextArea vào panel
		

		// Số Lượng
		panel.add(new JLabel("Số CCCD/CMND:"));
		txtCCCD = new JTextField();
		panel.add(txtCCCD);
		
		panel.add(new JLabel("Email:"));
		txtEmail = new JTextField();
		panel.add(txtEmail);
		// Hoạt Động
		panel.add(new JLabel("Loại khách hàng:"));
		cboLoaiKH = new JComboBox<>(new String[] { "NGUOIMOI" });
		cboLoaiKH.setEnabled(false);
		
		panel.add(cboLoaiKH);
		

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
		        String nextMaKhachHang = generateKHCode();
		        txtMaKhachHang.setText(nextMaKhachHang);

		        String tenKhachHang = txtTenKhachHang.getText().trim();
		        String sdt = txtSDT.getText().trim();
		        String tienTichLuy = txtTienTichLuy.getText().trim();
		        String soCanCuoc = txtCCCD.getText().trim();
		        String email = txtEmail.getText().trim();
		        String selectedLoaiKH = cboLoaiKH.getSelectedItem().toString();

		        LoaiKhachHang loaiKH = LoaiKhachHang.valueOf(selectedLoaiKH);

		        // Kiểm tra các trường bắt buộc
		        if (nextMaKhachHang.isEmpty() || tenKhachHang.isEmpty() || loaiKH == null || soCanCuoc.isEmpty() || email.isEmpty()) {
		            JOptionPane.showMessageDialog(ThemKhachHangDiaLog.this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi",
		                    JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Kiểm tra tên khách hàng chỉ chứa chữ
		        if (!tenKhachHang.matches("^[a-zA-Z\\sÀ-ỹ]+$")) {
		            JOptionPane.showMessageDialog(ThemKhachHangDiaLog.this, "Tên khách hàng chỉ được chứa chữ cái và khoảng trắng!", 
		                    "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Kiểm tra số điện thoại
		        if (!sdt.matches("^(03|08|07|05|09)\\d{8}$")) {
		            JOptionPane.showMessageDialog(ThemKhachHangDiaLog.this, "Số điện thoại không hợp lệ! Vui lòng nhập số bắt đầu bằng 03, 08, 07, 05 hoặc 09, có 10 chữ số.", 
		                    "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        

		        // **Kiểm tra số điện thoại đã tồn tại**
		        KhachHangDAL KhachHangDAL = new KhachHangDAL();
		        if (KhachHangDAL.checkKhachHangTonTaiTheoSDT(sdt)) {
		            JOptionPane.showMessageDialog(ThemKhachHangDiaLog.this, "Số điện thoại đã tồn tại! Vui lòng nhập số khác.", 
		                    "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

                if (KhachHangDAL.kiemTraCCCDTonTai(soCanCuoc)) {
                    JOptionPane.showMessageDialog(ThemKhachHangDiaLog.this, "Số căn cước công dân đã tồn tại trong hệ thống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (KhachHangDAL.kiemTraEmailTonTai(email)) {
                    JOptionPane.showMessageDialog(ThemKhachHangDiaLog.this, "Email đã tồn tại trong hệ thống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

		        // Kiểm tra CCCD
		        if (!soCanCuoc.matches("^(00[1-9]|0[1-9]\\d|09[0-6])\\d{9}$")) {
		            JOptionPane.showMessageDialog(ThemKhachHangDiaLog.this, "CCCD không hợp lệ! Vui lòng nhập đúng định dạng: 12 chữ số, bắt đầu từ 001 đến 096.", 
		                    "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Kiểm tra email
		        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com$")) {
		            JOptionPane.showMessageDialog(ThemKhachHangDiaLog.this, "Email không hợp lệ! Vui lòng nhập email hợp lệ bắt đầu bằng ký tự thông thường, có '@', tên miền và kết thúc bằng '.com'.", 
		                    "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        try {
		            double tienTich = Double.parseDouble(tienTichLuy);

		            KhachHang khachHang = new KhachHang(nextMaKhachHang, tenKhachHang, sdt, tienTich, soCanCuoc, email, loaiKH);

		            boolean isSuccess = KhachHangDAL.themKhachHang(khachHang);

		            if (isSuccess) {
		                JOptionPane.showMessageDialog(ThemKhachHangDiaLog.this, "Thêm khách hàng thành công!", "Thông báo",
		                        JOptionPane.INFORMATION_MESSAGE);
		                dispose();
		                quanLyKhachHangPanel.capNhatTable();
		            } else {
		                JOptionPane.showMessageDialog(ThemKhachHangDiaLog.this, "Thêm khách hàng thất bại!", "Lỗi",
		                        JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(ThemKhachHangDiaLog.this, "Vui lòng nhập số hợp lệ cho các trường số!", "Lỗi",
		                    JOptionPane.ERROR_MESSAGE);
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(ThemKhachHangDiaLog.this, "Lỗi khi thêm phòng: " + ex.getMessage(), "Lỗi",
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
	
	public String generateKHCode() {
	    String lastKHCode = new KhachHangDAL().getLastMaKH(); // Lấy mã khách hàng cuối cùng
	    int newKHNumber = 1; // Mặc định bắt đầu từ 1 nếu không có mã khách hàng nào

	    if (lastKHCode != null && lastKHCode.startsWith("KH")) {
	        // Tách phần số từ mã khách hàng (bỏ qua "KH")
	        String numberPart = lastKHCode.substring(2); // Bỏ qua ký tự "KH"
	        try {
	            newKHNumber = Integer.parseInt(numberPart) + 1; // Tăng số lên 1
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	            // Nếu có lỗi trong việc chuyển đổi, giữ nguyên newKHNumber là 1
	        }
	    }

	    // Tạo mã khách hàng mới theo định dạng "KHXXXX" (với 4 chữ số)
	    return String.format("KH%06d", newKHNumber); // Định dạng thành 4 chữ số
	}



}
