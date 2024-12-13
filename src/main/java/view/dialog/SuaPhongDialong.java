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

public class SuaPhongDialong extends JDialog {
    private JTextField txtMaPhong, txtTenPhong, txtDonGia, txtSoLuong, txtDonVi,txtLoaiPhong;
    private JComboBox<String> cboHoatDong;
    private JButton btnLuu, btnHuyBo;

    private QuanLyPhongPanel quanLyPhongPanel;
	private Phong phong;
	private JComboBox cboTrangThaiPhong;
	private JTextField txtMoTa;
	private JTextField txtTang;

    public SuaPhongDialong(QuanLyPhongPanel quanLyPhongPanel, Phong phong) {
        this.quanLyPhongPanel = quanLyPhongPanel; // Gán tham chiếu
        this.phong = phong; // Gán tham chiếu

        setTitle("Sửa Phòng");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Mã Phòng:"));
        txtMaPhong = new JTextField(phong.getMaPhong());
        txtMaPhong.setEditable(false); // Không cho chỉnh sửa mã dịch vụ
        panel.add(txtMaPhong);

        panel.add(new JLabel("Tên Phòng:"));
        txtTenPhong = new JTextField(phong.getTenPhong());
        panel.add(txtTenPhong);

        panel.add(new JLabel("Loại Phòng:"));
        txtLoaiPhong = new JTextField(phong.getLoaiPhong().getMaLoaiPhong());
        panel.add(txtLoaiPhong);

        panel.add(new JLabel("Trạng Thái Phòng:"));
        cboTrangThaiPhong = new JComboBox<>(new String[]{"Trống", "Đã Đặt"});
        cboTrangThaiPhong.setSelectedItem(phong.isTrangThaiPhong() ? "Trống" : "Đã Đặt");
        panel.add(cboTrangThaiPhong);
        
        panel.add(new JLabel("Mô tả:"));
        txtMoTa = new JTextField(phong.getMoTa());
        panel.add(txtMoTa);

        panel.add(new JLabel("Tầng:"));
        txtTang = new JTextField(String.valueOf(phong.getTang()));
        panel.add(txtTang);

        

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

                // Lấy giá trị từ các trường nhập liệu
                String tenPhong = txtTenPhong.getText().trim();
                LoaiPhong loaiPhong = new LoaiPhongDAL().getLoaiPhongTheoMa(txtLoaiPhong.getText());
                String trangThaiPhong = cboTrangThaiPhong.getSelectedItem().toString();
                String moTa = txtMoTa.getText().trim();
                String tang = txtTang.getText().trim();

                // Kiểm tra các trường nhập liệu để đảm bảo không bỏ trống
                if (tenPhong.isEmpty() || loaiPhong == null || moTa.isEmpty() || tang.isEmpty()) {
                    JOptionPane.showMessageDialog(SuaPhongDialong.this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    return; // Dừng lại nếu có trường thiếu
                }

                // Kiểm tra định dạng tên phòng, phải bắt đầu với "Phòng"
                if (!tenPhong.matches("^Phòng .*$")) {
                    JOptionPane.showMessageDialog(SuaPhongDialong.this, "Tên phòng phải bắt đầu bằng 'Phòng'!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return; // Dừng lại nếu định dạng không hợp lệ
                }

                // Kiểm tra định dạng tầng, phải bắt đầu với "Tầng" và có một dấu cách, tiếp theo là số từ 1 đến 5
                if (!tang.matches("^Tầng [1-5]$")) {
                    JOptionPane.showMessageDialog(SuaPhongDialong.this, "Tầng phải nằm trong phạm vi từ Tầng 1 đến Tầng 5!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return; // Dừng lại nếu định dạng không hợp lệ
                }

                // Kiểm tra trạng thái phòng, chỉ chấp nhận "Trống" hoặc "Đã đặt"
                if (!trangThaiPhong.equals("Trống") && !trangThaiPhong.equals("Đã đặt")) {
                    JOptionPane.showMessageDialog(SuaPhongDialong.this, "Trạng thái phòng phải là 'Trống' hoặc 'Đã đặt'!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return; // Dừng lại nếu trạng thái không hợp lệ
                }

                try {
                    // Chuyển trạng thái phòng thành giá trị boolean
                    boolean isTrangThaiPhong = "Trống".equals(trangThaiPhong);

                    // Tạo đối tượng Phong với thông tin đã nhập
                    Phong updatedphong = new Phong(phong.getMaPhong(), tenPhong, loaiPhong, isTrangThaiPhong, moTa, tang);

                    // Gọi dịch vụ để cập nhật thông tin phòng
                    PhongDAL phongDAL = new PhongDAL();
                    boolean isSuccess = phongDAL.suaPhong(updatedphong);

                    // Thông báo kết quả
                    if (isSuccess) {
                        JOptionPane.showMessageDialog(SuaPhongDialong.this, "Sửa phòng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        dispose(); // Đóng dialog
                        if (quanLyPhongPanel != null) {
                            quanLyPhongPanel.capNhatTable(); // Cập nhật bảng phòng
                        }
                    } else {
                        JOptionPane.showMessageDialog(SuaPhongDialong.this, "Sửa thông tin thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(SuaPhongDialong.this, "Số không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
