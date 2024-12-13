package view.dialog;

import javax.swing.*;

import dal.KhachHangDAL;
import entity.KhachHang;
import entity.LoaiKhachHang;
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

                // Kiểm tra loại khách hàng
                LoaiKhachHang loaiKH;
                try {
                    loaiKH = LoaiKhachHang.valueOf(selectedLoaiKH);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(SuaKhachHangDiaLog.this, "Loại khách hàng không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra các trường thông tin
                if (tenKH.isEmpty() || sdt.isEmpty() || tienTichLuy.isEmpty() || soCanCuoc.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(SuaKhachHangDiaLog.this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra tên khách hàng (Không chứa ký tự đặc biệt và số)
                if (!tenKH.matches("^[a-zA-Z\\sÀ-ỹ]+$")) {
                    JOptionPane.showMessageDialog(SuaKhachHangDiaLog.this, "Tên khách hàng không hợp lệ! Chỉ được chứa chữ cái và khoảng trắng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra số điện thoại (10-11 chữ số, không chứa ký tự đặc biệt, không phải số giả định, có thể kiểm tra mã vùng)
                if (!sdt.matches("^(03|08|07|05|09)\\d{8}$") || sdt.contains(" ") || sdt.matches("1234567890")) {
                    JOptionPane.showMessageDialog(SuaKhachHangDiaLog.this, "Số điện thoại không hợp lệ! Vui lòng nhập số bắt đầu bằng 03, 08, 07, 05 hoặc 09, có 10 chữ số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra mã vùng nếu cần thiết (Ví dụ: chỉ cho phép số điện thoại bắt đầu bằng "09" hoặc "08")
                if (!sdt.startsWith("09") && !sdt.startsWith("08")) {
                    JOptionPane.showMessageDialog(SuaKhachHangDiaLog.this, "Số điện thoại không thuộc khu vực hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra số căn cước công dân (12 chữ số, không có ký tự khác ngoài số)
                if (!soCanCuoc.matches("\\d{12}")) {
                    JOptionPane.showMessageDialog(SuaKhachHangDiaLog.this, "Số căn cước công dân không hợp lệ! Vui lòng nhập 12 chữ số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra trùng lặp số căn cước công dân trong cơ sở dữ liệu
                KhachHangDAL khachHangDAL = new KhachHangDAL();

                // Kiểm tra tiền tích lũy (Phải là số thực hợp lệ và không âm)
                try {
                    double tienTich = Double.parseDouble(tienTichLuy);
                    if (tienTich < 0) {
                        JOptionPane.showMessageDialog(SuaKhachHangDiaLog.this, "Tiền tích lũy không hợp lệ! Vui lòng nhập số không âm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(SuaKhachHangDiaLog.this, "Tiền tích lũy không hợp lệ! Vui lòng nhập số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra email (Đúng định dạng và không sử dụng tên miền tạm thời)
                if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
                    JOptionPane.showMessageDialog(SuaKhachHangDiaLog.this, "Email không hợp lệ! Vui lòng nhập đúng định dạng email.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra nếu email sử dụng tên miền tạm thời
                String[] tempMailDomains = {"tempmail.com", "mailinator.com", "10minutemail.com"};
                for (String domain : tempMailDomains) {
                    if (email.contains(domain)) {
                        JOptionPane.showMessageDialog(SuaKhachHangDiaLog.this, "Email không hợp lệ! Không chấp nhận tên miền tạm thời.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Kiểm tra nếu số điện thoại hoặc email đã tồn tại trong hệ thống
                if (khachHangDAL.kiemTraSoDienThoaiTonTai(sdt)) {
                    JOptionPane.showMessageDialog(SuaKhachHangDiaLog.this, "Số điện thoại đã tồn tại trong hệ thống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (khachHangDAL.kiemTraSoDienThoaiTonTai(email)) {
                    JOptionPane.showMessageDialog(SuaKhachHangDiaLog.this, "Email đã tồn tại trong hệ thống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Cập nhật thông tin khách hàng nếu tất cả các ràng buộc đều hợp lệ
                KhachHang updatedKhachHang = new KhachHang(khachHang.getMaKH(), tenKH, sdt, Double.parseDouble(tienTichLuy), soCanCuoc, email, loaiKH);
                
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
