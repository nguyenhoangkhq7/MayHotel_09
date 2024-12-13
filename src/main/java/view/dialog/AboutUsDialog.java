/*
 * MayHotel_09
 * Date created: 12/13/2024
 * Version: 2023.2  IntelliJ IDEA
 * Author: Nguyễn Hoàng Khang
 */

/*
 * Class Description:
 * Dialog giới thiệu thông tin ứng dụng quản lý khách sạn.
 */

package view.dialog;

import constant.CommonConstants;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AboutUsDialog extends JDialog {
    private static final String APP_VERSION = "Version: 1.0.0"; // Phiên bản ứng dụng
    private static final String CONTACT_INFO = "Liên hệ: contact@mayhotel09.com"; // Thông tin liên hệ

    public AboutUsDialog(JFrame parent) {
        super(parent, "Giới thiệu ứng dụng", true);

        // Tạo tiêu đề và thông tin ứng dụng
        JLabel lblTitle = new JLabel("Ứng dụng Quản Lý Đặt Phòng Khách Sạn", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
        lblTitle.setForeground(CommonConstants.ORANGE);

        JLabel lblDescription = new JLabel("Phần mềm hỗ trợ quản lý thông tin khách sạn hiệu quả và tiện lợi", JLabel.CENTER);
        lblDescription.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDescription.setBorder(BorderFactory.createEmptyBorder(0, 10, 15, 10));

        // Thêm thông tin phiên bản JDK
        String jdkVersion = "JDK Version: " + System.getProperty("java.version");
        JLabel lblJdkVersion = new JLabel(jdkVersion, JLabel.CENTER);
        lblJdkVersion.setFont(new Font("Arial", Font.PLAIN, 14));
        lblJdkVersion.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));

        // Thêm thông tin phiên bản ứng dụng
        JLabel lblAppVersion = new JLabel(APP_VERSION, JLabel.CENTER);
        lblAppVersion.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAppVersion.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));

        // Thêm thông tin "Powered by"
        JLabel lblPoweredBy = new JLabel("Phát hành và phát triển bởi nhóm 09", JLabel.CENTER);
        lblPoweredBy.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 14));
        lblPoweredBy.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));

        // Thêm thông tin liên hệ
        JLabel lblContact = new JLabel(CONTACT_INFO, JLabel.CENTER);
        lblContact.setFont(new Font("Arial", Font.PLAIN, 14));
        lblContact.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));

        // Dữ liệu cho JTable
        String[] columnNames = {"MSSV", "Họ Tên", "Lớp Học Phần"};
        Object[][] data = {
                {"001", "Nguyễn Hoàng Khang", "DHKTPM18B"},
                {"002", "Trần Thị B", "DHKTPM18B"},
                {"003", "Lê Văn C", "DHKTPM18B"},
                {"004", "Phạm Thị D", "DHKTPM18B"}
        };

        // Tạo JTable với dữ liệu
        JTable table = new JTable(new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Vô hiệu hóa chỉnh sửa ô trong bảng
            }
        });
        table.setSelectionBackground(CommonConstants.ORANGE);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(550, 150)); // Thiết lập kích thước cho JScrollPane

        // Panel chứa thông tin
        JPanel panelContent = new JPanel();
        panelContent.setLayout(new BoxLayout(panelContent, BoxLayout.Y_AXIS)); // Sử dụng BoxLayout để sắp xếp theo chiều dọc
        panelContent.add(lblTitle);
        panelContent.add(lblDescription);
        panelContent.add(lblJdkVersion);
        panelContent.add(lblAppVersion);
        panelContent.add(lblPoweredBy);
        panelContent.add(lblContact);
        panelContent.add(Box.createRigidArea(new Dimension(0, 10))); // Khoảng cách giữa thông tin liên hệ và bảng
        panelContent.add(scrollPane);

        panelContent.add(Box.createVerticalGlue()); // Đẩy các thành phần lên trên

        // Thiết lập dialog
        this.setLayout(new BorderLayout());
        this.add(panelContent, BorderLayout.CENTER);

        JButton btnClose = new JButton("Đóng");
        btnClose.setFont(new Font("Arial", Font.PLAIN, 14));
        btnClose.setPreferredSize(new Dimension(100, 35));
        btnClose.addActionListener(e -> dispose());

        JPanel panelButton = new JPanel();
        panelButton.add(btnClose);
        panelButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Thêm khoảng cách cho panel nút
        this.add(panelButton, BorderLayout.SOUTH);
        this.setSize(600, 500); // Điều chỉnh kích thước dialog
        this.setLocationRelativeTo(parent);
        this.setResizable(false); // Không cho phép thay đổi kích thước dialog
    }

    public static void main(String[] args) {
        // Tạo và hiển thị dialog
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            AboutUsDialog dialog = new AboutUsDialog(frame);
            dialog.setVisible(true);
        });
    }
}