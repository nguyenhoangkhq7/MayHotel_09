package view.dialog;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import dal.KhachHangDAL;
import dal.LoaiPhongDAL;
import dal.PhongDAL;
import entity.KhachHang;
import entity.LoaiKhachHang;
import entity.LoaiPhong;
import entity.Phong;
import view.panel.QuanLyKhachHangPanel;
import view.panel.QuanLyPhongPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuaKhachHangDiaLog extends JDialog {
    private JTextField txtMaPhong, txtTenPhong, txtDonGia, txtSoLuong, txtDonVi,txtLoaiPhong;
    private JComboBox<String> cboHoatDong;
    private JButton btnLuu, btnHuyBo;

    private QuanLyPhongPanel quanLyPhongPanel;
	private Phong phong;
	private JComboBox cboTrangThaiPhong;
	private JTextField txtMoTa;
	private JTextField txtTang;
	private QuanLyKhachHangPanel quanLyKhachHangPanel;
	private KhachHang khachHang;
	private JTextField txtMaKH;
	private JTextField txtTenKH;
	private JTextField txtSDT;
	private JTextField txtCCCD;
	private JTextField txtEmail;
	private JComboBox cboLoaiKH;
	private JTextField txtTienTichLuy;

    public SuaKhachHangDiaLog(QuanLyKhachHangPanel quanLyKhachHangPanel, KhachHang khachHang) {
        this.quanLyKhachHangPanel = quanLyKhachHangPanel; // Gán tham chiếu
        this.khachHang = khachHang; // Gán tham chiếu

        setTitle("Sửa Thông Tin Khách Hàng");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Mã Khách Hàng:"));
        txtMaKH = new JTextField(khachHang.getMaKH());
        txtMaKH.setEditable(false); // Không cho chỉnh sửa mã dịch vụ
        panel.add(txtMaKH);

        panel.add(new JLabel("Họ Tên Khách Hàng:"));
        txtTenKH = new JTextField(khachHang.getHoTen());
        panel.add(txtTenKH);

        panel.add(new JLabel("Số Điện Thoại:"));
        txtSDT = new JTextField(khachHang.getSoDienThoai());
        panel.add(txtSDT);
        
        panel.add(new JLabel("Tiền tích lũy:"));
        txtTienTichLuy = new JTextField(String.valueOf(khachHang.getTienTichLuy()));
        panel.add(txtTienTichLuy);
        
        
        
        panel.add(new JLabel("Số CCCD/CMNCD:"));
        txtCCCD = new JTextField(khachHang.getSoCanCuoc());
        panel.add(txtCCCD);
        
        panel.add(new JLabel("Email:"));
        txtEmail = new JTextField(khachHang.getEmail());
        panel.add(txtEmail);

        panel.add(new JLabel("Loại Khách Hàng:"));

     // Tạo JComboBox với các lựa chọn cho LoaiKhachHang
     cboLoaiKH = new JComboBox<>(new String[]{"NGUOIMOI", "HANGDONG", "HANGBAC", "HANGVANG", "HANGKIMCUONG"});

     // Lấy giá trị LoaiKhachHang từ đối tượng khachHang và chuyển đổi thành chuỗi tương ứng
     LoaiKhachHang loaiKH = khachHang.getLoaiKhachHang(); // giả sử getLoaiKhachHang() trả về LoaiKhachHang enum
     if (loaiKH != null) {
         // Chuyển enum thành chuỗi và thiết lập giá trị mặc định cho JComboBox
         cboLoaiKH.setSelectedItem(loaiKH.name());
     }

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

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        btnLuu.addActionListener(new ActionListener() {
            

			@Override
            public void actionPerformed(ActionEvent e) {
            	
				String tenKH = txtTenKH.getText().trim();
				String sdt = txtSDT.getText().trim();
				String tienTichLuy = txtTienTichLuy.getText().trim();
				String soCanCuoc = txtCCCD.getText().trim();
				String email = txtEmail.getText().trim();
				String selectedLoaiKH = cboLoaiKH.getSelectedItem().toString();

				// Ép kiểu thành enum LoaiKhachHang
				LoaiKhachHang loaiKH = LoaiKhachHang.valueOf(selectedLoaiKH);
				
				

				if ( tenKH.isEmpty() || loaiKH == null || soCanCuoc.isEmpty()) {
					JOptionPane.showMessageDialog(SuaKhachHangDiaLog.this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					
					return;
				}

                try {
                	double tienTich = Double.parseDouble(tienTichLuy);
                    
                    

                    KhachHang updatedKhachHang = new KhachHang(khachHang.getMaKH(), tenKH, sdt, tienTich, soCanCuoc, email, loaiKH);

                    KhachHangDAL khachHangDAL = new KhachHangDAL();
                    boolean isSuccess = khachHangDAL.suaKhachHang(updatedKhachHang);

                    if (isSuccess) {
                        JOptionPane.showMessageDialog(SuaKhachHangDiaLog.this, "Sửa thông tin khách hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        dispose(); 
                        if (quanLyKhachHangPanel != null) {
                            quanLyKhachHangPanel.capNhatTable();
                        }
                    } else {
                        JOptionPane.showMessageDialog(SuaKhachHangDiaLog.this, "Sửa thông tin thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(SuaKhachHangDiaLog.this, "không số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
}
