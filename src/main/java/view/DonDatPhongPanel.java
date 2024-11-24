package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import com.toedter.calendar.JDateChooser;
import constant.CommonConstants;
import dal.ChiTiet_DonDatPhong_PhongDAL;
import dal.DonDatPhongDAL;
import entity.*;
import custom.UIHelpers;

public class DonDatPhongPanel extends JPanel {
    // frame
    JButton btnTraCuu;
    public static JButton btnDatPhong;
    JDateChooser jdcNgayDen, jdcNgayDi;
    JComboBox cboTang, cboTrangThai;
    private JPanel jpnHeader, jpnContent, jpnContainSearchTrangThai, jpnDanhSachDDP,jpnSearch,jpnDetail, jpnTrangThai;
    private DonDatPhongDAL donDatPhongDAL;
    public JButton getBtnDatPhong() {
        return btnDatPhong;
    }

    public void setBtnDatPhong(JButton btnDatPhong) {
        this.btnDatPhong = btnDatPhong;
    }

    public DonDatPhongPanel() {
        setLayout(new BorderLayout()); // Sử dụng BorderLayout cho JFrame

//      hiển thị header
        jpnHeader = new JPanel(new GridLayout(1,2));
        jpnHeader.setBackground(CommonConstants.BACKGROUND);
        JLabel lblSubTitle = new JLabel("Đặt phòng");
        JLabel lblTitle = new JLabel("Quản lý đơn đặt phòng");
        lblSubTitle.setFont(new Font("Arial", Font.ITALIC, CommonConstants.TEXT_SIZE)); lblSubTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 30)); lblTitle.setForeground(Color.WHITE);
        JPanel jpn1 = new JPanel(new FlowLayout(FlowLayout.LEFT)); jpn1.add(lblSubTitle);
        JPanel jpn2 = new JPanel(new FlowLayout(FlowLayout.LEFT)); jpn2.add(lblTitle);
        Box headerLeft = Box.createVerticalBox();
        headerLeft.add(jpn1);
        headerLeft.add(jpn2);
        JPanel headerRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnDatPhong = new JButton("Đặt phòng"); btnDatPhong.setFont(new Font("Arial", Font.BOLD, CommonConstants.TEXT_SIZE));
        headerRight.add(btnDatPhong); UIHelpers.set_Button_Orange_Outline_Style(btnDatPhong);
        jpnHeader.add(headerLeft);
        jpnHeader.add(headerRight);
        jpn1.setOpaque(false);
        jpn2.setOpaque(false);
        headerRight.setOpaque(false);
        this.add(jpnHeader, BorderLayout.NORTH);

//  Hiển thị phần content
//  content nội dung chính chứa search, trạng thái và danh sách đơn đặt phòng
//  jpnContainSearchTrangThai chứa search và trạng thái
        jpnContent = new JPanel(new BorderLayout());
        this.add(jpnContent, BorderLayout.CENTER);
        jpnContainSearchTrangThai = new JPanel(new BorderLayout());
        jpnSearch = new JPanel(new BorderLayout());
        jpnTrangThai = new JPanel(new GridLayout(1,5));
        jpnContainSearchTrangThai.add(jpnSearch, BorderLayout.NORTH);
        jpnContainSearchTrangThai.add(jpnTrangThai, BorderLayout.SOUTH);
        jpnDanhSachDDP = new JPanel(new BorderLayout());

        jpnContent.add(jpnContainSearchTrangThai, BorderLayout.NORTH);
        jpnContent.add(jpnDanhSachDDP, BorderLayout.CENTER);

//  thêm component cho search
        jpnSearch.setLayout(new GridLayout(3,2));
        jpnSearch.add(UIHelpers.create_Form_Label_JDateChooser("Từ ngày",jdcNgayDen = new JDateChooser()));
        jpnSearch.add(UIHelpers.create_Form_Label_JDateChooser("Đến ngày", jdcNgayDi = new JDateChooser()));
        jpnSearch.add(UIHelpers.create_Form_Label_JComboBox("Tầng", cboTang =  new JComboBox()));
        jpnSearch.add(UIHelpers.create_Form_Label_JComboBox("Trạng thái", cboTrangThai =  new JComboBox()));
        jpnSearch.add(new JPanel());

        cboTang.addItem("1");
        cboTang.addItem("2");
        cboTang.addItem("3");
        cboTang.addItem("4");
        cboTang.addItem("5");

        cboTrangThai.addItem("Đã đặt trước");
        cboTrangThai.addItem("Đang ở");
        cboTrangThai.addItem("Chưa đặt cọc");
        cboTrangThai.addItem("Sắp checkin");
        cboTrangThai.addItem("Đến hạn checkin");

        JPanel jpnTmp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jpnTmp.add(btnTraCuu = new JButton("Tra cứu"));
        UIHelpers.set_Button_Blue_Style(btnTraCuu);

        jpnSearch.add(jpnTmp);

// thêm component cho trạng thái
        JPanel jpn3 = new JPanel();
        JPanel jpn4 = new JPanel();
        JPanel jpn5 = new JPanel();
        JPanel jpn6 = new JPanel();
        JPanel jpn7 = new JPanel();
        jpnTrangThai.add(jpn3);
        jpnTrangThai.add(jpn4);
        jpnTrangThai.add(jpn5);
        jpnTrangThai.add(jpn6);
        jpnTrangThai.add(jpn7);

        JLabel lbl3 = new JLabel();
        lbl3.setPreferredSize(CommonConstants.TRANGTHAI_SIZE);
        lbl3.setBackground(CommonConstants.DAT_TRUOC_COLOR);
        lbl3.setOpaque(true); // Thêm dòng này
        lbl3.setText("0");
        lbl3.setHorizontalAlignment(SwingConstants.CENTER);
        lbl3.setVerticalAlignment(SwingConstants.CENTER);

        JLabel lbl4 = new JLabel();
        lbl4.setPreferredSize(CommonConstants.TRANGTHAI_SIZE);
        lbl4.setBackground(CommonConstants.DANG_O_COLOR);
        lbl4.setOpaque(true); // Thêm dòng này
        lbl4.setText("0");
        lbl4.setHorizontalAlignment(SwingConstants.CENTER);
        lbl4.setVerticalAlignment(SwingConstants.CENTER);

        JLabel lbl5 = new JLabel();
        lbl5.setPreferredSize(CommonConstants.TRANGTHAI_SIZE);
        lbl5.setBackground(CommonConstants.DA_DAT_COC_COLOR);
        lbl5.setOpaque(true); // Thêm dòng này
        lbl5.setText("0");
        lbl5.setHorizontalAlignment(SwingConstants.CENTER);
        lbl5.setVerticalAlignment(SwingConstants.CENTER);

        JLabel lbl6 = new JLabel();
        lbl6.setPreferredSize(CommonConstants.TRANGTHAI_SIZE);
        lbl6.setBackground(CommonConstants.SAP_CHECKIN_COLOR);
        lbl6.setOpaque(true); // Thêm dòng này
        lbl6.setText("0");
        lbl6.setHorizontalAlignment(SwingConstants.CENTER);
        lbl6.setVerticalAlignment(SwingConstants.CENTER);

        JLabel lbl7 = new JLabel();
        lbl7.setPreferredSize(CommonConstants.TRANGTHAI_SIZE);
        lbl7.setBackground(CommonConstants.DEN_HAN_COLOR);
        lbl7.setOpaque(true); // Thêm dòng này
        lbl7.setText("0");
        lbl7.setHorizontalAlignment(SwingConstants.CENTER);
        lbl7.setVerticalAlignment(SwingConstants.CENTER);

        jpn3.add(lbl3); jpn3.add(new Label("Đặt trước"));
        jpn4.add(lbl4); jpn4.add(new Label("Đang ở"));
        jpn5.add(lbl5); jpn5.add(new Label("Đã đặt cọc"));
        jpn6.add(lbl6); jpn6.add(new Label("Sắp checkin"));
        jpn7.add(lbl7); jpn7.add(new Label("Đến hạn checkout"));


// thêm component cho danh sách đơn đặt phòng
        donDatPhongDAL = new DonDatPhongDAL();
        jpnDanhSachDDP.add(showDanhSachDDP(donDatPhongDAL.getAllDonDatPhong()), BorderLayout.CENTER);

        setVisible(true);
    }

    public void showDanhSachTimKiem(ArrayList<DonDatPhong> ketQuaTimKiem) {
        jpnDanhSachDDP.removeAll();
        jpnDanhSachDDP.add(showDanhSachDDP(ketQuaTimKiem), BorderLayout.CENTER);

    }




    private JScrollPane showDanhSachDDP(ArrayList<DonDatPhong> dsDDP) {
        JPanel jpnShowDanhSachDDP = new JPanel();
        jpnShowDanhSachDDP.setLayout(new GridLayout(0, 3, 150, 30));

        JScrollPane scroll = new JScrollPane(jpnShowDanhSachDDP);

        if (dsDDP.isEmpty()) {
            JLabel lbl = new JLabel("Không có đơn đặt phòng nào để hiển thị");
            lbl.setFont(new Font("Arial", Font.ITALIC, CommonConstants.TEXT_SIZE));
            lbl.setForeground(CommonConstants.GRAY);
            lbl.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
            lbl.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc
            jpnShowDanhSachDDP.setLayout(new BorderLayout());
            jpnShowDanhSachDDP.add(lbl);
        }

        for (DonDatPhong ddp : dsDDP) {
            if(ddp.getTrangThaiDonDatPhong().equals("Đã hủy") || ddp.getTrangThaiDonDatPhong().equals("Đã hoàn thành")) continue;
            // lấy 1 chi tiết đơn đặt phòng phòng để có thông tin đơn đặt phòng và phòng
            DDPPanel ddpPanel = createJPanelDonDatPhong(ddp);
            jpnShowDanhSachDDP.add(ddpPanel.getPanel());

            ArrayList<ChiTiet_DonDatPhong_Phong> dsChiTiet = new ChiTiet_DonDatPhong_PhongDAL().getChiTietDonDatPhongPhongTheoMaDDP(ddp.getMaDon());
            ddpPanel.getBtnXemChiTiet().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ChiTietDonDatPhong_PhongDialog dialog = new ChiTietDonDatPhong_PhongDialog(ddpPanel.getPanel(), dsChiTiet); // truyền `this` nếu bạn đang ở JFrame
                    dialog.setVisible(true); // Hiển thị dialog
                }
            });
            ddpPanel.getBtnCheckout().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Hiển thị hộp thoại xác nhận
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc chắn muốn checkout phòng này?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

                    // Nếu người dùng chọn "YES"
                    if (confirm == JOptionPane.YES_OPTION) {
                        // Ghi lại thay đổi vào database hoặc nguồn dữ liệu
                        if(donDatPhongDAL.suaTrangThaiDonDatPhong(ddp.getMaDon(), "Đã hoàn thành")) {
                            JOptionPane.showMessageDialog(null, "Checkout thành công");
                        } else {
                            JOptionPane.showMessageDialog(null, "Checkout thất bại. Gặp lỗi!");
                        }

                        // Cập nhật lại danh sách đơn đặt phòng trên giao diện
                        jpnDanhSachDDP.removeAll();
                        jpnDanhSachDDP.add(showDanhSachDDP(donDatPhongDAL.getAllDonDatPhong()), BorderLayout.CENTER);
                        jpnDanhSachDDP.revalidate();
                        jpnDanhSachDDP.repaint();
                    }
                }
            });

            ddpPanel.getBtnCheckin().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc chắn muốn Checkin?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        ddp.setTrangThaiDonDatPhong("Đang ở");
                        new DonDatPhongDAL().suaDonDatPhong(ddp);
                        jpnDanhSachDDP.removeAll();
                        jpnDanhSachDDP.add(showDanhSachDDP(new DonDatPhongDAL().getAllDonDatPhong()), BorderLayout.CENTER);
                    } else if (confirm == JOptionPane.NO_OPTION) {

                    }

                }
            });

            ddpPanel.getBtnThemDichVu().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            ddpPanel.getBtnHuy().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc chắn muốn Hủy đơn?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == JOptionPane.YES_OPTION) {
                        new DonDatPhongDAL().xoaDonDatPhong(ddp.getMaDon());
                        jpnDanhSachDDP.removeAll();
                        jpnDanhSachDDP.add(showDanhSachDDP(new DonDatPhongDAL().getAllDonDatPhong()), BorderLayout.CENTER);
                    } else if (confirm == JOptionPane.NO_OPTION) {

                    }

                }
            });
        }
        return scroll;
    }




    public DDPPanel createJPanelDonDatPhong(DonDatPhong donDatPhong) {

        JPanel jpnDDP = new JPanel();
        jpnDDP.setLayout(new BoxLayout(jpnDDP, BoxLayout.Y_AXIS));
        jpnDDP.setPreferredSize(new Dimension(250, 280));
        jpnDDP.setBorder(BorderFactory.createLineBorder(CommonConstants.ORANGE, 2));

        JPanel jpn1 = new JPanel();
        JPanel jpn2 = new JPanel();
        JPanel jpn3 = new JPanel();
        JPanel jpn4 = new JPanel();
        JPanel jpn5 = new JPanel();
        JPanel jpnButton = new JPanel();
        JPanel jpnXemChiTiet = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // Đặt màu nền trong suốt cho các panel con
        jpn1.setOpaque(false);
        jpn2.setOpaque(false);
        jpn3.setOpaque(false);
        jpn4.setOpaque(false);
        jpn5.setOpaque(false);
        jpnButton.setOpaque(false);
        jpnXemChiTiet.setOpaque(false);

        JButton btnCheckin = new JButton("Checkin");
        btnCheckin.setFocusPainted(false);

        JButton btnCheckout = new JButton("Checkout");
        btnCheckout.setFocusPainted(false);

        JButton btnThemDichVu = new JButton("Thêm dịch vụ");
        btnThemDichVu.setFocusPainted(false);

        JButton btnHuy = new JButton("Hủy đơn");
        btnHuy.setFocusPainted(false);

        JButton btnXemChiTiet = new JButton("Chi tiết");
        btnXemChiTiet.setFocusPainted(false);

        jpnXemChiTiet.add(btnXemChiTiet);

        // Lấy thông tin từ DonDatPhong
        String tenKhachHang = donDatPhong.getKhachHang().getHoTen();
        String soDienThoai = donDatPhong.getKhachHang().getSoDienThoai();
        String trangThaiDatCoc = donDatPhong.isTrangThaiDatCoc() ? "Đã đặt cọc" : "Chưa đặt cọc";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String ngayNhanPhong = donDatPhong.getNgayNhanPhong().format(formatter);
        String ngayTraPhong = donDatPhong.getNgayTraPhong().format(formatter);

        // Tạo các label thông tin
        JLabel lblTenKhachHang = new JLabel("Tên khách hàng: " + tenKhachHang);
        JLabel lblSoDienThoai = new JLabel("Số điện thoại: " + soDienThoai);
        JLabel lblTrangThaiDatCoc = new JLabel("Trạng thái đặt cọc: " + trangThaiDatCoc);
        JLabel lblNgayNhanPhong = new JLabel("Ngày nhận phòng: " + ngayNhanPhong);
        JLabel lblNgayTraPhong = new JLabel("Ngày trả phòng: " + ngayTraPhong);

        jpn1.add(lblTenKhachHang);
        jpn2.add(lblSoDienThoai);
        jpn3.add(lblTrangThaiDatCoc);
        jpn4.add(lblNgayNhanPhong);
        jpn5.add(lblNgayTraPhong);

        LocalDateTime ngayHienTai = LocalDateTime.now();

        // Kiểm tra ngày tạo so với hiện tại có nhỏ hơn 1 ngày không
        long soNgay = ChronoUnit.DAYS.between(donDatPhong.getNgayTao(), ngayHienTai);
        System.out.println(donDatPhong.getNgayTao());
        System.out.println(ngayHienTai);
        System.out.println(soNgay);
        if (soNgay < 1) {
            System.out.println("Ngày tạo nhỏ hơn 1 ngày so với hiện tại.");
        } else {
            System.out.println("Ngày tạo lớn hơn hoặc bằng 1 ngày so với hiện tại.");
        }
//        boolean checkDenHan =

        // Thiết lập các nút và màu nền dựa trên trạng thái đặt cọc
        switch (donDatPhong.getTrangThaiDonDatPhong()) {
            case "Đặt trước":
                jpnButton.add(btnCheckin);
                jpnButton.add(btnHuy);
                jpnDDP.setBackground(CommonConstants.DAT_TRUOC_COLOR);
                break;
            case "Đang ở":
                jpnButton.add(btnThemDichVu);
                jpnButton.add(btnCheckout);
                jpnDDP.setBackground(CommonConstants.DANG_O_COLOR);
                break;
            case "Đã hoàn thành":

            default:
                jpnButton.add(btnThemDichVu);
                jpnButton.add(btnCheckout);
                jpnDDP.setBackground(CommonConstants.ORANGE);
                break;
        }

        jpnDDP.add(jpnXemChiTiet);
        jpnDDP.add(jpn1);
        jpnDDP.add(jpn2);
        jpnDDP.add(jpn3);
        jpnDDP.add(jpn4);
        jpnDDP.add(jpn5);
        jpnDDP.add(jpnButton);

        return new DDPPanel(jpnDDP, btnCheckin, btnCheckout, btnThemDichVu, btnHuy, btnXemChiTiet);
    }

}
