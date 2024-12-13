package view.dialog;



import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import dal.KhuyenMaiDAL;
import entity.DichVu;
import entity.KhuyenMai;
import view.panel.QuanLyDichVuPanel;
import view.panel.QuanLyKhuyenMaiPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class SuaKhuyenMaiDiaLog extends JDialog {
    private JTextField txtMaDV, txtTenDV, txtDonGia, txtSoLuong, txtDonVi;
    private JComboBox<String> cboHoatDong;
    private JButton btnLuu, btnHuyBo;
    private DichVu dichVu;
    private QuanLyDichVuPanel quanLyDichVuPanel;
	private QuanLyKhuyenMaiPanel quanLyKhuyenMaiPanel;
	private KhuyenMai khuyenMai;
	private JTextField txtMaKhuyenMai;
	private JTextField txtTenKhuyenMai;
	private JTextField txtGiaTri;
	private JDateChooser dateChooserNgayBatDau;
	private JDateChooser dateChooserNgayKetThuc;
	private JTextField txtLoaiKhachHang;

    public SuaKhuyenMaiDiaLog(QuanLyKhuyenMaiPanel quanLyKhuyenMaiPanel, KhuyenMai khuyenMai) {
        this.quanLyKhuyenMaiPanel = quanLyKhuyenMaiPanel; // Gán tham chiếu
        this.khuyenMai = khuyenMai; // Gán tham chiếu

        setTitle("Sửa Dịch Vụ");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

     // Mã Khuyến Mãi
        panel.add(new JLabel("Mã Khuyến Mãi:"));
        txtMaKhuyenMai = new JTextField(khuyenMai.getMaKhuyenMai());
        txtMaKhuyenMai.setEditable(false); // Không cho chỉnh sửa mã khuyến mãi
        panel.add(txtMaKhuyenMai);

        // Tên Khuyến Mãi
        panel.add(new JLabel("Tên Khuyến Mãi:"));
        txtTenKhuyenMai = new JTextField(khuyenMai.getTenKhuyenMai());
        panel.add(txtTenKhuyenMai);

        // Giá Trị
        panel.add(new JLabel("Giá Trị:"));
        txtGiaTri = new JTextField(String.valueOf(khuyenMai.getGiaTri()));
        panel.add(txtGiaTri);

        // Ngày Bắt Đầu
        panel.add(new JLabel("Ngày Bắt Đầu:"));
        dateChooserNgayBatDau = new JDateChooser();
        dateChooserNgayBatDau.setDate(java.sql.Timestamp.valueOf(khuyenMai.getNgayBatDau())); // Chuyển đổi LocalDateTime sang Timestamp
        panel.add(dateChooserNgayBatDau);

        // Số Lượng
        panel.add(new JLabel("Số Lượng:"));
        txtSoLuong = new JTextField(String.valueOf(khuyenMai.getSoLuong()));
        panel.add(txtSoLuong);

        // Trạng Thái Hoạt Động
        panel.add(new JLabel("Hoạt Động:"));
        cboHoatDong = new JComboBox<>(new String[]{"Có", "Không"});
        cboHoatDong.setSelectedItem(khuyenMai.isConHoatDong() ? "Có" : "Không");
        panel.add(cboHoatDong);

        // Ngày Kết Thúc
        panel.add(new JLabel("Ngày Kết Thúc:"));
        dateChooserNgayKetThuc = new JDateChooser();
        dateChooserNgayKetThuc.setDate(java.sql.Timestamp.valueOf(khuyenMai.getNgayKetThuc())); // Chuyển đổi LocalDateTime sang Timestamp
        panel.add(dateChooserNgayKetThuc);

        // Loại Khách Hàng Áp Dụng
        panel.add(new JLabel("Loại Khách Hàng Áp Dụng:"));
        txtLoaiKhachHang = new JTextField(khuyenMai.getLoaiKhachHangApDung());
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

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maKhuyenMai = txtMaKhuyenMai.getText().trim();  // Thêm mã khuyến mãi
                String tenKhuyenMai = txtTenKhuyenMai.getText().trim();
                String giaTri = txtGiaTri.getText().trim();
                String soLuong = txtSoLuong.getText().trim();
                String hoatDong = cboHoatDong.getSelectedItem().toString();
                String loaiKhachHang = txtLoaiKhachHang.getText().trim();
                LocalDateTime ngayBatDau = dateChooserNgayBatDau.getDate() != null ? dateChooserNgayBatDau.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() : null;
                LocalDateTime ngayKetThuc = dateChooserNgayKetThuc.getDate() != null ? dateChooserNgayKetThuc.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() : null;

                // Kiểm tra các trường bắt buộc
                if (maKhuyenMai.isEmpty() || tenKhuyenMai.isEmpty() || giaTri.isEmpty() || soLuong.isEmpty() || loaiKhachHang.isEmpty() || ngayBatDau == null || ngayKetThuc == null) {
                    JOptionPane.showMessageDialog(SuaKhuyenMaiDiaLog.this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra ngày bắt đầu không được trước ngày hiện tại

                // Kiểm tra ngày kết thúc phải sau ngày bắt đầu
                if (!ngayKetThuc.isAfter(ngayBatDau)) {
                    JOptionPane.showMessageDialog(SuaKhuyenMaiDiaLog.this, "Ngày kết thúc phải sau ngày bắt đầu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    double giaTriDouble = Double.parseDouble(giaTri);
                    int soLuongInt = Integer.parseInt(soLuong);
                    boolean isHoatDong = "Có".equals(hoatDong);
                    if (giaTriDouble < 0) {
		                JOptionPane.showMessageDialog(SuaKhuyenMaiDiaLog.this, "Giá trị khuyến mãi phải là số không âm!", "Lỗi",
		                        JOptionPane.ERROR_MESSAGE);
		                return;
		            }
                    if (soLuongInt <= 0) {
		                JOptionPane.showMessageDialog(SuaKhuyenMaiDiaLog.this, "Số lượng phải là số nguyên dương!", "Lỗi",
		                        JOptionPane.ERROR_MESSAGE);
		                return;
		            }

                    // Tạo đối tượng Khuyến Mãi mới theo thứ tự yêu cầu
                    KhuyenMai updatedKhuyenMai = new KhuyenMai(
                        maKhuyenMai,  // Mã khuyến mãi
                        tenKhuyenMai, // Tên khuyến mãi
                        giaTriDouble, // Giá trị khuyến mãi
                        ngayBatDau,   // Ngày bắt đầu
                        isHoatDong,   // Trạng thái hoạt động
                        soLuongInt,   // Số lượng
                        ngayKetThuc,  // Ngày kết thúc
                        loaiKhachHang // Loại khách hàng áp dụng
                    );

                    KhuyenMaiDAL khuyenMaiDAL = new KhuyenMaiDAL();
                    boolean isSuccess = khuyenMaiDAL.capNhatKhuyenMai(updatedKhuyenMai);

                    if (isSuccess) {
                        JOptionPane.showMessageDialog(SuaKhuyenMaiDiaLog.this, "Sửa khuyến mãi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        dispose(); // Đóng dialog
                        // Cập nhật lại bảng trong KhuyenMaiPanel
                        if (quanLyKhuyenMaiPanel != null) {
                            quanLyKhuyenMaiPanel.capNhatTableKhuyenMai();
                        }
                    } else {
                        JOptionPane.showMessageDialog(SuaKhuyenMaiDiaLog.this, "Sửa khuyến mãi thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(SuaKhuyenMaiDiaLog.this, "Giá trị và số lượng phải là số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
