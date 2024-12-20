package view.dialog;

import javax.swing.*;

import dal.NhanVienDAL;
import entity.NhanVien;
import entity.TaiKhoan;
import view.panel.QuanLyNhanVienPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuaNhanVienDialog extends JDialog {
	// Khai báo các thành phần giao diện
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
    private NhanVien nhanVien;
    private QuanLyNhanVienPanel quanLyNhanVienPanel;


	public SuaNhanVienDialog(QuanLyNhanVienPanel quanLyNhanVienPanel, NhanVien nhanVien) {
		setTitle("Sửa nhân viên");
		setSize(400, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		this.quanLyNhanVienPanel = quanLyNhanVienPanel;
        this.nhanVien = nhanVien; 


		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(10, 2, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		panel.add(new JLabel("Mã Nhân Viên:"));
        txtMaNV = new JTextField(nhanVien.getMaNV());
		txtMaNV.setEditable(false);
		panel.add(txtMaNV);

		panel.add(new JLabel("Họ Tên:"));
        txtHoTen = new JTextField(nhanVien.getHoten());
		panel.add(txtHoTen);

		panel.add(new JLabel("Số Điện Thoại:"));
        txtSDT = new JTextField(nhanVien.getSoDienThoai());
		panel.add(txtSDT);

		panel.add(new JLabel("Số Căn Cước:"));
		txtSoCanCuoc = new JTextField(nhanVien.getSoCanCuoc());
		panel.add(txtSoCanCuoc);

		panel.add(new JLabel("Hoạt Động:"));
		cboHoatDong = new JComboBox<>(new String[] { "Có", "Không" });
        cboHoatDong.setSelectedItem(nhanVien.isConHoatDong() ? "Có" : "Không");
		panel.add(cboHoatDong);

		panel.add(new JLabel("Email:"));
		txtEmail = new JTextField(nhanVien.getEmail());
		panel.add(txtEmail);
		
		panel.add(new JLabel("Địa Chỉ:"));
		txtDiaChi = new JTextField(nhanVien.getDiaChi());
		panel.add(txtDiaChi);
		
		panel.add(new JLabel("Vai Trò:"));
		cboVaiTro = new JComboBox<>(new String[] { "Quản lý", "Nhân viên" });
        cboVaiTro.setSelectedItem(nhanVien.getVaiTro());
        cboVaiTro.setEnabled(false);
		panel.add(cboVaiTro);
		
		panel.add(new JLabel("Tên Tài Khoản:"));
		txtTenTaiKhoan = new JTextField(nhanVien.getTaiKhoan().getTenTaiKhoan());
		txtTenTaiKhoan.setEditable(false);
		panel.add(txtTenTaiKhoan);
		
		panel.add(new JLabel("Mật khẩu:"));
		txtMatKhau = new JPasswordField(nhanVien.getTaiKhoan().getMatKhau());
		txtMatKhau.setEditable(false);
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

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		btnLuu.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (!validateInput()) {
		            return; // Nếu dữ liệu không hợp lệ, dừng lại
		        }

		        String tenNV = txtHoTen.getText().trim();
		        String soDienThoai = txtSDT.getText().trim();
		        String soCanCuoc = txtSoCanCuoc.getText().trim();
		        String hoatDong = cboHoatDong.getSelectedItem().toString();
		        String email = txtEmail.getText().trim();
		        String diaChi = txtDiaChi.getText().trim();
		        String vaiTro = cboVaiTro.getSelectedItem().toString();
		        String tenTaiKhoan = txtTenTaiKhoan.getText().trim();
		        String matKhau = new String(txtMatKhau.getPassword()).trim();

		        try {
		            boolean isHoatDong = "Có".equals(hoatDong);
		            TaiKhoan taiKhoan = new TaiKhoan(tenTaiKhoan, matKhau);
		            NhanVien updateNhanVien = new NhanVien(
		                nhanVien.getMaNV(), tenNV, soDienThoai, soCanCuoc, isHoatDong, email, diaChi, vaiTro, taiKhoan
		            );

		            NhanVienDAL nhanVienDAL = new NhanVienDAL();
		            boolean isSuccess = nhanVienDAL.suaNhanVien(updateNhanVien);

		            if (isSuccess) {
		                JOptionPane.showMessageDialog(SuaNhanVienDialog.this, "Sửa nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		                dispose(); // Đóng dialog
		                if (quanLyNhanVienPanel != null) {
		                    quanLyNhanVienPanel.capNhatTable();
		                }
		            } else {
		                JOptionPane.showMessageDialog(SuaNhanVienDialog.this, "Sửa nhân viên thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(SuaNhanVienDialog.this, "Lỗi khi sửa nhân viên: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
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
	private boolean validateInput() {
	    if (txtHoTen.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Họ tên không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        txtHoTen.requestFocus();
	        return false;
	    }
	    if (txtSDT.getText().trim().isEmpty() || !txtSDT.getText().matches("\\d{10}")) {
	        JOptionPane.showMessageDialog(this, "Số điện thoại phải là 10 chữ số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        txtSDT.requestFocus();
	        return false;
	    }
	    if (txtSoCanCuoc.getText().trim().isEmpty() || !txtSoCanCuoc.getText().matches("\\d{12}")) {
	        JOptionPane.showMessageDialog(this, "Số căn cước phải là 12 chữ số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        txtSoCanCuoc.requestFocus();
	        return false;
	    }
	    if (txtEmail.getText().trim().isEmpty() || !txtEmail.getText().matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
	        JOptionPane.showMessageDialog(this, "Email không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        txtEmail.requestFocus();
	        return false;
	    }
	    if (txtDiaChi.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        txtDiaChi.requestFocus();
	        return false;
	    }
	    return true;
	}

}
