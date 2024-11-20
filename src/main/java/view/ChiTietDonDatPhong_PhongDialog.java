package view;

import dal.ChiTiet_DonDatPhong_Phong_DichVuDAL;
import entity.ChiTiet_DonDatPhong_Phong;
import entity.ChiTiet_DonDatPhong_Phong_DichVu;
import custom.UIHelpers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ChiTietDonDatPhong_PhongDialog extends JDialog {

    private JTextField txtTenKhachHang;
    private JTextField txtSoDienThoai;
    private JTextField txtSoCCCD;
    private JTextField txtEmail;
    private JTextField txtLoaiKH;
    private JTable bangDichVu;
    private ArrayList<ChiTiet_DonDatPhong_Phong> danhSachChiTiet;

    public ChiTietDonDatPhong_PhongDialog(JPanel parent, ArrayList<ChiTiet_DonDatPhong_Phong> danhSachChiTiet) {
        this.danhSachChiTiet = danhSachChiTiet;
        setLayout(new BorderLayout());
        showHeader();

        // Sử dụng BoxLayout cho mainPanel để sắp xếp các phần theo chiều dọc
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel, BorderLayout.CENTER);

        // Panel chứa thông tin khách hàng ở phần đầu
        JPanel customerInfoPanel = new JPanel(new BorderLayout());
        if (!danhSachChiTiet.isEmpty()) {
            showKhachHangInfo(customerInfoPanel, danhSachChiTiet.get(0));
        }
        mainPanel.add(customerInfoPanel);

        // Danh sách phòng ở giữa
        JList<String> roomList = new JList<>(danhSachChiTiet.stream()
                .map(ct -> ct.getPhong().getMaPhong())
                .toArray(String[]::new));
        roomList.addListSelectionListener(e -> showRoomDetails(danhSachChiTiet.get(roomList.getSelectedIndex())));
        JScrollPane roomListScrollPane = new JScrollPane(roomList);
        roomListScrollPane.setBorder(BorderFactory.createTitledBorder("Danh Sách Phòng"));
        mainPanel.add(roomListScrollPane);

        // Bảng dịch vụ ở cuối
        bangDichVu = new JTable();
        JScrollPane serviceTableScrollPane = new JScrollPane(bangDichVu);
        serviceTableScrollPane.setBorder(BorderFactory.createTitledBorder("Dịch Vụ"));
        mainPanel.add(serviceTableScrollPane);

        setSize(800, 1000);
        setLocationRelativeTo(null);
    }

    private void showKhachHangInfo(JPanel container, ChiTiet_DonDatPhong_Phong ct) {
        JPanel jpnContainInfoKhachHang = new JPanel(new GridLayout(0, 2));
        container.add(jpnContainInfoKhachHang, BorderLayout.NORTH);

        // Khởi tạo các trường nhập liệu
        txtTenKhachHang = new JTextField();
        txtSoDienThoai = new JTextField();
        txtSoCCCD = new JTextField();
        txtEmail = new JTextField();
        txtLoaiKH = new JTextField();

        // Thiết lập thông tin khách hàng
        txtTenKhachHang.setText(ct.getDonDatPhong().getKhachHang().getHoTen());
        txtSoDienThoai.setText(ct.getDonDatPhong().getKhachHang().getSoDienThoai());
        txtSoCCCD.setText(ct.getDonDatPhong().getKhachHang().getSoCanCuoc());
        txtEmail.setText(ct.getDonDatPhong().getKhachHang().getEmail());
        txtLoaiKH.setText(ct.getDonDatPhong().getKhachHang().getLoaiKhachHang().toString());

        // Thêm nhãn và trường vào panel
        jpnContainInfoKhachHang.add(UIHelpers.create_Form_Label_JTextField("Tên khách hàng", txtTenKhachHang));
        jpnContainInfoKhachHang.add(UIHelpers.create_Form_Label_JTextField("Số điện thoại", txtSoDienThoai));
        jpnContainInfoKhachHang.add(UIHelpers.create_Form_Label_JTextField("CMND", txtSoCCCD));
        jpnContainInfoKhachHang.add(UIHelpers.create_Form_Label_JTextField("Email", txtEmail));
        jpnContainInfoKhachHang.add(UIHelpers.create_Form_Label_JTextField("Loại khách hàng", txtLoaiKH));
    }

    private void showRoomDetails(ChiTiet_DonDatPhong_Phong ct) {
        // Lấy thông tin dịch vụ từ danh sách dịch vụ của đối tượng ChiTiet_DonDatPhong_Phong
        ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> dichVuList = new ChiTiet_DonDatPhong_Phong_DichVuDAL().getDSChiTietDonDatPhongPhongDichVuTheoMaCT_DDP_P(ct.getMaCT_DDP_P());
        String[][] services = new String[dichVuList.size()][4];
        for (int i = 0; i < dichVuList.size(); i++) {
            ChiTiet_DonDatPhong_Phong_DichVu dv = dichVuList.get(i);
            services[i][0] = dv.getDichVu().getTenDichVu();
            services[i][1] = String.valueOf(dv.getDichVu().getDonGia());
            services[i][2] = String.valueOf(dv.getSoLuongDat());
            services[i][3] = dv.getDichVu().getDonVi();
        }
        String[] columnNames = {"Tên Dịch Vụ", "Đơn Giá", "Số Lượng", "Đơn Vị"};
        DefaultTableModel model = new DefaultTableModel(services, columnNames);
        bangDichVu.setModel(model);
    }

    private void showHeader() {
        // Nút đóng
        JPanel jpnClose = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton closeButton = new JButton("Đóng");
        UIHelpers.set_Orange_Blue_Style(closeButton);
        jpnClose.add(closeButton);
        closeButton.addActionListener(e -> dispose());
        add(jpnClose, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(600, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        // Tạo dữ liệu mẫu để kiểm tra

    }
}
