/*
    *MayHotel_09  day creative: 12/10/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here   
     */


package view.dialog;


import dal.DichVuDAL;
import entity.DichVu;
import view.panel.QuanLyDichVuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuaDichVuDialog extends JDialog {
    private JTextField txtMaDV, txtTenDV, txtDonGia, txtSoLuong, txtDonVi;
    private JComboBox<String> cboHoatDong;
    private JButton btnLuu, btnHuyBo;
    private DichVu dichVu;
    private QuanLyDichVuPanel quanLyDichVuPanel;

    public SuaDichVuDialog(QuanLyDichVuPanel quanLyDichVuPanel, DichVu dichVu) {
        this.quanLyDichVuPanel = quanLyDichVuPanel; // Gán tham chiếu
        this.dichVu = dichVu; // Gán tham chiếu

        setTitle("Sửa Dịch Vụ");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Mã Dịch Vụ:"));
        txtMaDV = new JTextField(dichVu.getMaDichVu());
        txtMaDV.setEditable(false); // Không cho chỉnh sửa mã dịch vụ
        panel.add(txtMaDV);

        panel.add(new JLabel("Tên Dịch Vụ:"));
        txtTenDV = new JTextField(dichVu.getTenDichVu());
        panel.add(txtTenDV);

        panel.add(new JLabel("Đơn Giá:"));
        txtDonGia = new JTextField(String.valueOf(dichVu.getDonGia()));
        panel.add(txtDonGia);

        panel.add(new JLabel("Đơn Vị:"));
        txtDonVi = new JTextField(dichVu.getDonVi());
        panel.add(txtDonVi);

        panel.add(new JLabel("Số Lượng:"));
        txtSoLuong = new JTextField(String.valueOf(dichVu.getSoLuongTon()));
        panel.add(txtSoLuong);

        panel.add(new JLabel("Hoạt Động:"));
        cboHoatDong = new JComboBox<>(new String[]{"Có", "Không"});
        cboHoatDong.setSelectedItem(dichVu.isConHoatDong() ? "Có" : "Không");
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

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tenDV = txtTenDV.getText().trim();
                String donGia = txtDonGia.getText().trim();
                String donVi = txtDonVi.getText().trim();
                String soLuong = txtSoLuong.getText().trim();
                String hoatDong = cboHoatDong.getSelectedItem().toString();

                if (tenDV.isEmpty() || donGia.isEmpty() || donVi.isEmpty() || soLuong.isEmpty()) {
                    JOptionPane.showMessageDialog(SuaDichVuDialog.this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    double gia = Double.parseDouble(donGia);
                    int sl = Integer.parseInt(soLuong);
                    boolean isHoatDong = "Có".equals(hoatDong);

                    DichVu updatedDichVu = new DichVu(dichVu.getMaDichVu(), gia, tenDV, sl, isHoatDong, donVi);

                    DichVuDAL dichVuDAL = new DichVuDAL();
                    boolean isSuccess = dichVuDAL.suaDichVu(updatedDichVu);

                    if (isSuccess) {
                        JOptionPane.showMessageDialog(SuaDichVuDialog.this, "Sửa dịch vụ thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        dispose(); // Đóng dialog
                        // Cập nhật lại bảng trong DichVuPanel
                        if (quanLyDichVuPanel != null) {
                            quanLyDichVuPanel.capNhatTable();
                        }
                    } else {
                        JOptionPane.showMessageDialog(SuaDichVuDialog.this, "Sửa dịch vụ thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(SuaDichVuDialog.this, "Đơn giá và số lượng phải là số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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

