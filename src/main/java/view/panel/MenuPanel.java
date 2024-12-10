/*
    *MayHotel_09  day creative: 11/29/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here
     */


package view.panel;

import constant.CommonConstants;
import entity.NhanVien;
import view.LoginGUI;
import view.MainGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MenuPanel extends JPanel {
    private NhanVien nhanVienDangTruc;
    private MainGUI mainGUI;
    private JLabel lblDangXuat, lblManHinhChinh, lblDonDatPhong, lblHoaDon, lblKhachHang, lblBaoCao, lblKhuyenMai, lblPhong, lblNhanVien, lblDichVu, lblThongKe, lblThuChi, lblDateTime;
    private CardLayout cardLayout;  // CardLayout để quản lý các panel
    private JPanel jpnContain;  // Panel chính chứa các thẻ
    private ArrayList<JLabel> menuItems = new ArrayList<>();

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public NhanVien getNhanVienDangTruc() {
        return nhanVienDangTruc;
    }

    public JPanel getJpnContain() {
        return jpnContain;
    }

    public MainGUI getMainGUI() {
        return mainGUI;
    }

    public MenuPanel(MainGUI mainGUI) {

        this.mainGUI = mainGUI;
        this.nhanVienDangTruc = mainGUI.getNhanVienDangTruc();
        this.setBackground(CommonConstants.BACKGROUND);
        this.setLayout(new BorderLayout());

        // Khởi tạo CardLayout
        cardLayout = new CardLayout();
        jpnContain = new JPanel(cardLayout); // Sử dụng CardLayout cho container chính

        // Tạo và thêm các panel con vào jpnContain
        addPanelToCardLayout("Màn hình chính", new ManHinhChinhPanel());
        addPanelToCardLayout("Đơn đặt phòng", new QuanLyDonDatPhongPanel(this));
        addPanelToCardLayout("Hóa đơn", new QuanLyHoaDonPanel());
        addPanelToCardLayout("Khách hàng", new QuanLyKhachHangPanel());
//        addPanelToCardLayout("Báo cáo", new BangBaoCao()); // Giả sử có BangBaoCao Panel
        addPanelToCardLayout("Khuyến mãi", new QuanLyKhuyenMaiPanel()); // Giả sử có QuanLyKhuyenMaiPanel
        addPanelToCardLayout("Phòng", new QuanLyPhongPanel());
        addPanelToCardLayout("Nhân viên", new QuanLyNhanVienPanel()); // Giả sử có QuanLyNhanVienPanel
        addPanelToCardLayout("Dịch vụ", new QuanLyDichVuPanel(this)); // Giả sử có QuanLyDichVuPanel
        addPanelToCardLayout("Phiếu thu chi", new QuanLyPhieuThuChiPanel(this)); // Giả sử có QuanLyPhieuThuChiPanel
        addPanelToCardLayout("Báo cáo", new ThongKeBaoCaoPanel(this)); // Giả sử có QuanLyThongKePanel
        addPanelToCardLayout("Thống kê", new ThongKeDoanhThuPanel()); // Giả sử có QuanLyThongKePanel

        // Thêm jpnContain vào trong BorderLayout.CENTER
        this.mainGUI.getJpnContainContent().add(jpnContain, BorderLayout.CENTER);

        // thêm header cho menu
        JPanel jpnHeader = new JPanel(new BorderLayout()); // Panel chứa header
        jpnHeader.setBackground(new Color(69, 96, 117)); // Màu nền chính cho toàn bộ header
// Tạo panel logo với BoxLayout (xếp dọc logo và slogan)
        JPanel panelLogo = new JPanel();
        panelLogo.setLayout(new BoxLayout(panelLogo, BoxLayout.Y_AXIS));
        panelLogo.setOpaque(false); // Không có màu nền, đồng bộ với nền của jpnHeader
        panelLogo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Thêm padding trên/dưới
// Tạo label cho tên khách sạn
        JLabel lblHotelName = new JLabel("MAY HOTEL");
        lblHotelName.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 41));
        lblHotelName.setForeground(Color.WHITE); // Màu chữ trắng để nổi bật
        lblHotelName.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo chiều ngang
// Thêm icon
        JLabel logo = new JLabel(resizeImageIcon("src/main/java/icon/logoforMay Hotel.png", 50, 50)); // Đặt kích thước tùy chỉnh (200x100)
        JPanel jpnContain_Logo_HotelName = new JPanel(); jpnContain_Logo_HotelName.setOpaque(false);
        jpnContain_Logo_HotelName.add(lblHotelName);
        jpnContain_Logo_HotelName.add(logo);
// Tạo label cho slogan
        JLabel lblSlogan = new JLabel("The best place to be");
        lblSlogan.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 30));
        lblSlogan.setForeground(Color.LIGHT_GRAY); // Màu chữ xám nhạt cho slogan
        lblSlogan.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo chiều ngang
// Thêm các thành phần vào panel logo
        panelLogo.add(jpnContain_Logo_HotelName);
        panelLogo.add(Box.createVerticalStrut(10)); // Thêm khoảng cách giữa tên khách sạn và slogan
        panelLogo.add(lblSlogan);
// Thêm nhân viên và nút đăng xuất
        Box boxContainNV_DX = Box.createHorizontalBox();
        boxContainNV_DX.setPreferredSize(new Dimension(400, 100)); // Kích thước cố định của container
        boxContainNV_DX.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

// Tạo JLabel hiển thị tên nhân viên
        JLabel lblNhanVien = new JLabel(this.nhanVienDangTruc.getHoten(), SwingConstants.LEFT);
        lblNhanVien.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        lblNhanVien.setIcon(resizeImageIcon("src/main/java/icon/user.png", 30, 30)); // Resize icon
        lblNhanVien.setPreferredSize(new Dimension(200, 50)); // Đặt kích thước cố định cho JLabel
        lblNhanVien.setOpaque(true);
        lblNhanVien.setBackground(CommonConstants.BACKGROUND);
        lblNhanVien.setForeground(Color.WHITE);

// Tạo JLabel nút Đăng xuất
        lblDangXuat = new JLabel("Đăng xuất", SwingConstants.CENTER);
        lblDangXuat.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding: top, left, bottom, right
        lblDangXuat.setFont(new Font("Arial", Font.BOLD, 16));
        lblDangXuat.setPreferredSize(new Dimension(120, 45)); // Đặt kích thước cố định
        lblDangXuat.setBackground(CommonConstants.ORANGE); // Màu nền cam
        lblDangXuat.setOpaque(true); // Bắt buộc để hiển thị màu nền
        lblDangXuat.setForeground(Color.BLACK); // Màu chữ đen
        lblDangXuat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Con trỏ chuột dạng link

// Hiệu ứng hover cho nút Đăng xuất
        lblDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                lblDangXuat.setBackground(new Color(85, 119, 145)); // Màu khi hover
                lblDangXuat.setForeground(Color.WHITE); // Chữ đổi sang trắng
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                lblDangXuat.setBackground(CommonConstants.ORANGE); // Quay lại màu nền ban đầu
                lblDangXuat.setForeground(Color.BLACK); // Quay lại màu chữ ban đầu
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                mainGUI.dispose();
                LoginGUI login = new LoginGUI();
            }
        });

// Sắp xếp các thành phần vào Box
        boxContainNV_DX.setAlignmentX(CENTER_ALIGNMENT);
        boxContainNV_DX.add(lblNhanVien); // Thêm label nhân viên
        boxContainNV_DX.add(Box.createHorizontalStrut(20)); // Khoảng cách linh hoạt giữa nhân viên và nút đăng xuất
        boxContainNV_DX.add(lblDangXuat); // Thêm nút đăng xuất

// Thêm panel logo vào jpnHeader
        jpnHeader.add(panelLogo, BorderLayout.CENTER);
        jpnHeader.add(boxContainNV_DX, BorderLayout.SOUTH);
        this.add(jpnHeader, BorderLayout.NORTH);

        // thêm các menu item
        Box boxContainMenuItem = Box.createVerticalBox();
        boxContainMenuItem.add(Box.createVerticalStrut(20));
        lblManHinhChinh = addAndReturnMenuItem(boxContainMenuItem, "Màn hình chính");
        lblManHinhChinh.setForeground(Color.BLUE); // Màu của menu được chọn
        lblManHinhChinh.setFont(lblManHinhChinh.getFont().deriveFont(Font.BOLD | Font.ITALIC)); // Chữ đậm
        lblDonDatPhong = addAndReturnMenuItem(boxContainMenuItem, "Đơn đặt phòng");
        lblKhachHang = addAndReturnMenuItem(boxContainMenuItem, "Khách hàng");
        lblHoaDon = addAndReturnMenuItem(boxContainMenuItem, "Hóa đơn");
        lblThuChi = addAndReturnMenuItem(boxContainMenuItem, "Phiếu thu chi");
        lblPhong = addAndReturnMenuItem(boxContainMenuItem, "Phòng");
        lblKhuyenMai = addAndReturnMenuItem(boxContainMenuItem, "Khuyến mãi");
        if(!nhanVienDangTruc.getVaiTro().equals("Nhân viên")) {
            lblNhanVien = addAndReturnMenuItem(boxContainMenuItem, "Nhân viên");
        }
        lblDichVu = addAndReturnMenuItem(boxContainMenuItem, "Dịch vụ");
        lblBaoCao = addAndReturnMenuItem(boxContainMenuItem, "Báo cáo");
        lblThongKe = addAndReturnMenuItem(boxContainMenuItem, "Thống kê");
        this.add(boxContainMenuItem, BorderLayout.CENTER);

        // thêm phần south
        lblDateTime = new JLabel("Loading time...", SwingConstants.CENTER);
        lblDateTime.setFont(new Font("Arial", Font.BOLD, 20));
        lblDateTime.setForeground(Color.WHITE);
        this.add(lblDateTime, BorderLayout.SOUTH);
        startClock();
    }
    private void addPanelToCardLayout(String name, JPanel panel) {
        jpnContain.add(panel, name); // Thêm panel với tên tương ứng
    }
    // Phương thức resize ảnh
    private ImageIcon resizeImageIcon(String path, int width, int height) {
        // Tải ảnh từ file
        ImageIcon originalIcon = new ImageIcon(path);
        // Resize ảnh
        Image resizedImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        // Trả về ImageIcon mới với kích thước thay đổi
        return new ImageIcon(resizedImage);
    }
        private void startClock() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Timer timer = new Timer(1000, e -> lblDateTime.setText(sdf.format(new Date())));
        timer.start();
    }
    // Gắn sự kiện cho các nút trong menu
    private JLabel addAndReturnMenuItem(Box container, String text) {
        JLabel label = new JLabel(text);
        customMenuItemStyle(label);
        menuItems.add(label);
        container.add(label);
        container.add(Box.createVerticalStrut(10));
        // Xử lý sự kiện click
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                switch (text) {
                    case "Màn hình chính":
                        cardLayout.show(jpnContain, "Màn hình chính");
                        break;
                    case "Đơn đặt phòng":
                        cardLayout.show(jpnContain, "Đơn đặt phòng");
                        break;
                    case "Hóa đơn":
                        cardLayout.show(jpnContain, "Hóa đơn");
                        break;
                    case "Khách hàng":
                        cardLayout.show(jpnContain, "Khách hàng");
                        break;
                    case "Khuyến mãi":
                        cardLayout.show(jpnContain, "Khuyến mãi");
                        break;
                    case "Phòng":
                        cardLayout.show(jpnContain, "Phòng");
                        break;
                    case "Dịch vụ":
                        cardLayout.show(jpnContain, "Dịch vụ");
                        break;
                    case "Báo cáo":
                        cardLayout.show(jpnContain, "Báo cáo");
                        break;
                    case "Thống kê":
                        cardLayout.show(jpnContain, "Thống kê");
                        break;
                    case "Phiếu thu chi":
                        cardLayout.show(jpnContain, "Phiếu thu chi");
                        break;
                    default:
                        break;
                }
                updateMenuEffectAfterClick(label);
            }
        });
        return label;
    }

    // Hàm cập nhật hiệu ứng menu sau khi click
    private void updateMenuEffectAfterClick(JLabel clickedLabel) {
        for (JLabel menuItem : menuItems) {
            if (menuItem.getText().equals(clickedLabel.getText())) {
                // Đổi màu menu được chọn
                menuItem.setForeground(Color.BLUE); // Màu của menu được chọn
                menuItem.setFont(menuItem.getFont().deriveFont(Font.BOLD | Font.ITALIC)); // Chữ đậm
            } else {
                // Khôi phục màu menu khác
                menuItem.setForeground(Color.WHITE); // Màu mặc định
                menuItem.setFont(menuItem.getFont().deriveFont(Font.BOLD)); // Chữ thường
            }
        }
    }

    // Phương thức tạo JPanel tương ứng với menu item

    public void customMenuItemStyle(JLabel lbl) {
        // Thiết lập font chữ
        lbl.setFont(new Font("Segoe UI",  Font.BOLD, 24));
        lbl.setForeground(Color.WHITE);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setOpaque(true);
        lbl.setBackground(CommonConstants.ORANGE);
        lbl.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo trục dọc
        lbl.setPreferredSize(new Dimension(300, 35)); // Đặt kích thước cố định (rộng x cao)
        lbl.setMinimumSize(new Dimension(300, 35));   // Kích thước nhỏ nhất
        lbl.setMaximumSize(new Dimension(300, 35));   // Kích thước lớn nhất

        // Thêm hiệu ứng hover
        lbl.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                lbl.setBackground(new Color(85, 119, 145));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                lbl.setBackground(CommonConstants.ORANGE);
            }
        });
    }


}
