/*
    *MayHotel_09  day creative: 12/2/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang
*/

/*
   *class description:
        Dialog để chọn phòng trước khi thêm dịch vụ
*/

package view.dialog;

import entity.LoaiPhong;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChonPhongDeThemDichVuDialog extends JDialog {

    private JTable roomTable; // Bảng hiển thị danh sách các phòng
    private JButton btnThemDichVu; // Nút thêm dịch vụ
    private JScrollPane scrollPane; // Thanh cuộn cho bảng

    public ChonPhongDeThemDichVuDialog(JFrame parent) {
        super(parent, "Chọn phòng để thêm dịch vụ", true); // Modal = true
        JLabel titleLabel = new JLabel("Danh sách phòng", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Dữ liệu mẫu cho bảng (bạn sẽ thay bằng dữ liệu thật)
        String[] columnNames = {"Mã phòng", "Tên phòng", "Loại phòng"};
        Object[][] sampleData = {
                {"P001", "Phòng 101", "Deluxe"},
                {"P002", "Phòng 102", "Standard"},
                {"P003", "Phòng 201", "Suite"},
                {"P004", "Phòng 202", "Standard"}
        };

        // Tạo JTable với dữ liệu mẫu
        roomTable = new JTable(sampleData, columnNames);
        roomTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Chọn 1 hàng duy nhất
        scrollPane = new JScrollPane(roomTable); // Thêm bảng vào thanh cuộn

        // Nút "Thêm dịch vụ"
        btnThemDichVu = new JButton("Thêm dịch vụ");
        btnThemDichVu.setEnabled(false); // Disabled ban đầu


        setupLayout();
        addEventListeners();
    }

    private void initComponents() {
        // Tiêu đề

    }

    private void setupLayout() {
        // Cấu hình bố cục
        this.setLayout(new BorderLayout());
        this.setSize(400, 300);
        this.setLocationRelativeTo(null); // Hiển thị ở giữa màn hình

        // Thêm các thành phần vào JDialog
        this.add(new JLabel("Chọn một phòng để thêm dịch vụ", JLabel.CENTER), BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnThemDichVu);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addEventListeners() {
        // Lắng nghe sự kiện khi người dùng chọn phòng
        roomTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Chỉ kích hoạt nút nếu có một hàng được chọn
                if (!e.getValueIsAdjusting() && roomTable.getSelectedRow() != -1) {
                    btnThemDichVu.setEnabled(true); // Bật nút
                } else {
                    btnThemDichVu.setEnabled(false); // Tắt nút
                }
            }
        });

        // Hành động khi bấm nút "Thêm dịch vụ"
        btnThemDichVu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = roomTable.getSelectedRow(); // Lấy hàng được chọn
                if (selectedRow != -1) {
                    String roomId = roomTable.getValueAt(selectedRow, 0).toString(); // Mã phòng
                    String roomName = roomTable.getValueAt(selectedRow, 1).toString(); // Tên phòng
                    JOptionPane.showMessageDialog(ChonPhongDeThemDichVuDialog.this,
                            "Bạn đã chọn phòng: " + roomId + " - " + roomName,
                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Đóng dialog sau khi chọn phòng
                }
            }
        });
    }

    public static void main(String[] args) {
        // Chạy thử dialog
        JFrame frame = new JFrame("Test Dialog");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        ChonPhongDeThemDichVuDialog dialog = new ChonPhongDeThemDichVuDialog(frame);
        dialog.setVisible(true); // Hiển thị dialog
    }
}
