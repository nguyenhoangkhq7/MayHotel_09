package view;

import utils.UIHelpers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ChiTietDonDatPhong_PhongGUI extends JDialog {

    private JTextField txtTenKhachHang;
    private JTextField txtSoDienThoai;
    private JTextField txtSoCCCD;
    private JTextField txtEmail;
    private JTextField txtLoaiKH;

    private JTable bangDichVu;

    public ChiTietDonDatPhong_PhongGUI(JFrame parent) {
        super(parent, "Chi Tiết Đơn Đặt Phòng", true);
        setLayout(new BorderLayout());
        showHeader();

        // Sử dụng BoxLayout cho mainPanel để sắp xếp các phần theo chiều dọc
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel, BorderLayout.CENTER);

        // Panel chứa thông tin khách hàng ở phần đầu
        JPanel customerInfoPanel = new JPanel(new BorderLayout());
        showKhachHangInfo(customerInfoPanel);
        mainPanel.add(customerInfoPanel);

        // Danh sách phòng ở giữa
        String[] roomNames = {"Phòng 101", "Phòng 102", "Phòng 103"};
        JList<String> roomList = new JList<>(roomNames);
        roomList.addListSelectionListener(e -> showRoomDetails(roomList.getSelectedValue()));
        JScrollPane roomListScrollPane = new JScrollPane(roomList);
        roomListScrollPane.setBorder(BorderFactory.createTitledBorder("Danh Sách Phòng"));
        mainPanel.add(roomListScrollPane);

        // Bảng dịch vụ ở cuối
        bangDichVu = new JTable();
        JScrollPane serviceTableScrollPane = new JScrollPane(bangDichVu);
        serviceTableScrollPane.setBorder(BorderFactory.createTitledBorder("Dịch Vụ"));
        mainPanel.add(serviceTableScrollPane);

        setSize(800, 1000);
        setLocationRelativeTo(parent);
    }

    public void showHeader() {
        // Nút đóng
        JPanel jpnClose = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton closeButton = new JButton("Đóng");
        UIHelpers.set_Orange_Blue_Style(closeButton);
        jpnClose.add(closeButton);
        closeButton.addActionListener(e -> dispose());
        add(jpnClose, BorderLayout.NORTH);
    }

    private void showKhachHangInfo(JPanel container) {
        JPanel jpnContainInfoKhachHang = new JPanel(new GridLayout(0, 2));
        container.add(jpnContainInfoKhachHang, BorderLayout.NORTH);

        // Khởi tạo các trường nhập liệu
        txtTenKhachHang = new JTextField();
        txtSoDienThoai = new JTextField();
        txtSoCCCD = new JTextField();
        txtEmail = new JTextField();
        txtLoaiKH = new JTextField();

        // Thêm nhãn và trường vào panel
        jpnContainInfoKhachHang.add(UIHelpers.create_Form_Label_JTextField("Tên khách hàng", txtTenKhachHang));
        jpnContainInfoKhachHang.add(UIHelpers.create_Form_Label_JTextField("Số điện thoại", txtSoDienThoai));
        jpnContainInfoKhachHang.add(UIHelpers.create_Form_Label_JTextField("CMND", txtSoCCCD));
        jpnContainInfoKhachHang.add(UIHelpers.create_Form_Label_JTextField("Email", txtEmail));
        jpnContainInfoKhachHang.add(UIHelpers.create_Form_Label_JTextField("Loại khách hàng", txtLoaiKH));
    }

    private void showRoomDetails(String selectedRoom) {
        if (selectedRoom != null) {
            // Giả lập thông tin khách hàng
            txtTenKhachHang.setText("Nguyễn Văn A");
            txtSoDienThoai.setText("0123456789");
            txtSoCCCD.setText("123456789");
            txtEmail.setText("nguyenvana@example.com");
            txtLoaiKH.setText("Thường");

            // Cập nhật thông tin dịch vụ
            String[][] services = {
                    {"Dịch vụ A", "100.000", "2", "Lần"},
                    {"Dịch vụ B", "200.000", "1", "Lần"}
            };
            String[] columnNames = {"Tên Dịch Vụ", "Đơn Giá", "Số Lượng", "Đơn Vị"};
            DefaultTableModel model = new DefaultTableModel(services, columnNames);
            bangDichVu.setModel(model);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        // Hiển thị dialog modal
        ChiTietDonDatPhong_PhongGUI dialog = new ChiTietDonDatPhong_PhongGUI(frame);
        dialog.setVisible(true);
    }
}
