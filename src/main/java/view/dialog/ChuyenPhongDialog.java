package view.dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ChuyenPhongDialog extends JDialog {

    private JComboBox<String> phongHienTaiComboBox;
    private JComboBox<String> loaiPhongComboBox;
    private JComboBox<String> phongComboBox;
    private JButton chuyenPhongButton;
    private JButton huyButton;

    private String selectedPhongHienTai;
    private String selectedLoaiPhong;
    private String selectedPhong;

    public ChuyenPhongDialog(Frame owner, List<String> danhSachPhongHienTai, List<String> danhSachLoaiPhong, List<String> danhSachPhong) {
        super(owner, "Chọn và chuyển phòng", true);
        initComponents(danhSachPhongHienTai, danhSachLoaiPhong, danhSachPhong);
    }

    private void initComponents(List<String> danhSachPhongHienTai, List<String> danhSachLoaiPhong, List<String> danhSachPhong) {
        // Panel chính
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10));

        // Label và combobox Phòng hiện tại
        JLabel phongHienTaiLabel = new JLabel("Phòng hiện tại:");
        phongHienTaiComboBox = new JComboBox<>(danhSachPhongHienTai.toArray(new String[0]));
        phongHienTaiComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy phòng hiện tại được chọn
                selectedPhongHienTai = (String) phongHienTaiComboBox.getSelectedItem();
            }
        });

        // Label và combobox Loại phòng
        JLabel loaiPhongLabel = new JLabel("Loại phòng mới:");
        loaiPhongComboBox = new JComboBox<>(danhSachLoaiPhong.toArray(new String[0]));
        loaiPhongComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy loại phòng được chọn
                selectedLoaiPhong = (String) loaiPhongComboBox.getSelectedItem();
                // Cập nhật danh sách phòng dựa trên loại phòng được chọn
                updatePhongComboBox(selectedLoaiPhong);
            }
        });

        // Label và combobox Phòng mới
        JLabel phongLabel = new JLabel("Phòng mới:");
        phongComboBox = new JComboBox<>(danhSachPhong.toArray(new String[0]));
        phongComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy phòng mới được chọn
                selectedPhong = (String) phongComboBox.getSelectedItem();
            }
        });

        // Thêm vào panel chính
        mainPanel.add(phongHienTaiLabel);
        mainPanel.add(phongHienTaiComboBox);
        mainPanel.add(loaiPhongLabel);
        mainPanel.add(loaiPhongComboBox);
        mainPanel.add(phongLabel);
        mainPanel.add(phongComboBox);

        // Nút chuyển phòng và hủy
        chuyenPhongButton = new JButton("Chuyển phòng");
        chuyenPhongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý chuyển phòng
                if (selectedPhongHienTai == null || selectedLoaiPhong == null || selectedPhong == null) {
                    JOptionPane.showMessageDialog(ChuyenPhongDialog.this,
                            "Vui lòng chọn đầy đủ thông tin trước khi chuyển phòng!",
                            "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(ChuyenPhongDialog.this,
                            "Chuyển từ phòng: " + selectedPhongHienTai + " sang phòng: " + selectedPhong + " (Loại: " + selectedLoaiPhong + ")",
                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            }
        });

        huyButton = new JButton("Hủy");
        huyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Đóng dialog
                dispose();
            }
        });

        // Panel cho nút
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(chuyenPhongButton);
        buttonPanel.add(huyButton);

        // Thêm các panel vào dialog
        this.setLayout(new BorderLayout(10, 10));
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        // Thiết lập dialog
        this.setSize(400, 250);
        this.setLocationRelativeTo(null);
    }

    private void updatePhongComboBox(String loaiPhong) {
        // TODO: Cập nhật danh sách phòng dựa trên loại phòng được chọn
        // Ví dụ tạm thời, bạn cần thay bằng dữ liệu thực tế
        phongComboBox.removeAllItems();
        if ("Loại 1".equals(loaiPhong)) {
            phongComboBox.addItem("Phòng 101");
            phongComboBox.addItem("Phòng 102");
        } else if ("Loại 2".equals(loaiPhong)) {
            phongComboBox.addItem("Phòng 201");
            phongComboBox.addItem("Phòng 202");
        }
    }

    public static void main(String[] args) {
        // Dữ liệu ví dụ
        List<String> danhSachPhongHienTai = List.of("Phòng 100", "Phòng 101", "Phòng 102");
        List<String> danhSachLoaiPhong = List.of("Loại 1", "Loại 2", "Loại 3");
        List<String> danhSachPhong = List.of("Phòng 101", "Phòng 102", "Phòng 201", "Phòng 202");

        // Hiển thị dialog
        ChuyenPhongDialog dialog = new ChuyenPhongDialog(null, danhSachPhongHienTai, danhSachLoaiPhong, danhSachPhong);
        dialog.setVisible(true);
    }
}
