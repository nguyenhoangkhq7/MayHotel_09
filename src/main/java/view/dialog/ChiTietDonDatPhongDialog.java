package view.dialog;

import constant.CommonConstants;
import custom.TextFieldCustom;
import dal.ChiTiet_DonDatPhong_PhongDAL;
import dal.ChiTiet_DonDatPhong_Phong_DichVuDAL;
import entity.ChiTiet_DonDatPhong_Phong;
import entity.ChiTiet_DonDatPhong_Phong_DichVu;
import helper.UIHelpers;
import entity.DonDatPhong;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ChiTietDonDatPhongDialog extends JDialog {

    private TextFieldCustom txtTenKhachHang, txtSoDienThoai, txtSoCCCD, txtEmail, txtLoaiKH;
    private JTable bangDichVu;
    private ArrayList<ChiTiet_DonDatPhong_Phong> danhSachChiTiet;
    private ChiTiet_DonDatPhong_PhongDAL chiTietDonDatPhongPhongDAL = new ChiTiet_DonDatPhong_PhongDAL();

    public ChiTietDonDatPhongDialog(DonDatPhong donDatPhong) {
        this.danhSachChiTiet = chiTietDonDatPhongPhongDAL.getChiTietDonDatPhongPhongTheoMaDDP(donDatPhong.getMaDon());

        setTitle("Chi Tiết Đơn Đặt Phòng");
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        // Header
        showHeader();

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(mainPanel, BorderLayout.CENTER);

        // Thông tin khách hàng
        JPanel customerInfoPanel = new JPanel(new BorderLayout());
        customerInfoPanel.setBorder(BorderFactory.createTitledBorder("Thông Tin Khách Hàng"));
        if (!danhSachChiTiet.isEmpty()) {
            showKhachHangInfo(customerInfoPanel, danhSachChiTiet.get(0));
        }
        mainPanel.add(customerInfoPanel, BorderLayout.NORTH);

        // container của dsPhong và DichVu
        Box boxContain = Box.createVerticalBox();
        // Danh sách phòng
        JPanel roomListPanel = new JPanel(new BorderLayout());
        roomListPanel.setBorder(BorderFactory.createTitledBorder("Danh Sách Phòng"));
        JList<String> roomList = new JList<>(danhSachChiTiet.stream()
                .map(ct -> ct.getPhong().getMaPhong())
                .toArray(String[]::new));
        roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        roomList.addListSelectionListener(e -> showRoomDetails(danhSachChiTiet.get(roomList.getSelectedIndex())));
        roomListPanel.add(new JScrollPane(roomList), BorderLayout.CENTER);
        boxContain.add(roomListPanel);
        // Bảng dịch vụ
        JPanel serviceTablePanel = new JPanel(new BorderLayout());
        serviceTablePanel.setBorder(BorderFactory.createTitledBorder("Dịch Vụ"));
        bangDichVu = new JTable();
        serviceTablePanel.add(new JScrollPane(bangDichVu), BorderLayout.CENTER);
        boxContain.add(serviceTablePanel);
        mainPanel.add(boxContain, BorderLayout.CENTER);

        // Thiết lập kích thước và vị trí
        setSize(600, 1000);
        setLocationRelativeTo(null);
    }

    private void showKhachHangInfo(JPanel container, ChiTiet_DonDatPhong_Phong ct) {
        JPanel infoPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        container.add(infoPanel, BorderLayout.NORTH);

        txtTenKhachHang = new TextFieldCustom("Nhập tên khách hàng", 50); txtTenKhachHang.setEnabled(false);
        txtSoDienThoai = new TextFieldCustom("Nhập số điện thoại", 11); txtSoDienThoai.setEnabled(false);
        txtSoCCCD = new TextFieldCustom("Nhập số căn cước", 12); txtSoCCCD.setEnabled(false);
        txtEmail = new TextFieldCustom("Nhập email", 50); txtEmail.setEnabled(false);
        txtLoaiKH = new TextFieldCustom("", 30); txtLoaiKH.setEnabled(false);

        txtTenKhachHang.setText(ct.getDonDatPhong().getKhachHang().getHoTen());
        txtSoDienThoai.setText(ct.getDonDatPhong().getKhachHang().getSoDienThoai());
        txtSoCCCD.setText(ct.getDonDatPhong().getKhachHang().getSoCanCuoc());
        txtEmail.setText(ct.getDonDatPhong().getKhachHang().getEmail());
        txtLoaiKH.setText(ct.getDonDatPhong().getKhachHang().getLoaiKhachHang().toString());

        infoPanel.add(UIHelpers.create_Form_Label_JTextField("Tên khách hàng", txtTenKhachHang));
        infoPanel.add(UIHelpers.create_Form_Label_JTextField("Số điện thoại", txtSoDienThoai));
        infoPanel.add(UIHelpers.create_Form_Label_JTextField("CMND", txtSoCCCD));
        infoPanel.add(UIHelpers.create_Form_Label_JTextField("Email", txtEmail));
        infoPanel.add(UIHelpers.create_Form_Label_JTextField("Loại khách hàng", txtLoaiKH));
    }

    private void showRoomDetails(ChiTiet_DonDatPhong_Phong ct) {
        ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> dichVuList = new ChiTiet_DonDatPhong_Phong_DichVuDAL().getDSChiTietDonDatPhongPhongDichVuTheoMaDonDatPhongMaPhong(ct.getDonDatPhong().getMaDon(), ct.getPhong().getMaPhong());
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
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton closeButton = UIHelpers.createButtonStyle("Đóng", CommonConstants.BUTTON_SIZE, Color.WHITE, Color.ORANGE);
        headerPanel.add(closeButton);
        closeButton.addActionListener(e -> dispose());
        add(headerPanel, BorderLayout.NORTH);
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