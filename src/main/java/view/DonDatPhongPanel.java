package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
    private JPanel jpnContent, jpnSearch_Detail, jpnDanhSachDDP,jpnSearch,jpnDetail, jpnTrangThai;

    public JButton getBtnDatPhong() {
        return btnDatPhong;
    }

    public void setBtnDatPhong(JButton btnDatPhong) {
        this.btnDatPhong = btnDatPhong;
    }

    public DonDatPhongPanel() {
        setLayout(new BorderLayout()); // Sử dụng BorderLayout cho JFrame

        showHeader();
        showContent();

        setVisible(true); // Đặt setVisible ở cuối
    }

//Xử lý sự kiện



    // Hàm hỗ trợ để hiển thị danh sách kết quả tìm kiếm
    public void showDanhSachTimKiem(ArrayList<DonDatPhong> ketQuaTimKiem) {
        jpnDanhSachDDP.removeAll();
        jpnDanhSachDDP.add(showDanhSachDDP(ketQuaTimKiem), BorderLayout.CENTER);

    }


    public void showContent() {
        jpnContent = new JPanel(new BorderLayout());
        this.add(jpnContent, BorderLayout.CENTER);

        jpnSearch_Detail = new JPanel(new BorderLayout());
        jpnDanhSachDDP = new JPanel(new BorderLayout());

        jpnContent.add(jpnSearch_Detail, BorderLayout.NORTH);
        jpnContent.add(jpnDanhSachDDP, BorderLayout.CENTER);

        showSearch_Detail(jpnSearch_Detail);
        jpnDanhSachDDP.add(showDanhSachDDP(new DonDatPhongDAL().getAllDonDatPhong()), BorderLayout.CENTER);
    }


    public void showSearch_Detail(JPanel container) {
        jpnSearch = new JPanel();
        jpnDetail = new JPanel();
        jpnTrangThai = new JPanel(new GridLayout(1,5));

        JPanel jpn1 = new JPanel();
        JPanel jpn2 = new JPanel();
        JPanel jpn3 = new JPanel();
        JPanel jpn4 = new JPanel();
        JPanel jpn5 = new JPanel();
        jpnTrangThai.add(jpn1);
        jpnTrangThai.add(jpn2);
        jpnTrangThai.add(jpn3);
        jpnTrangThai.add(jpn4);
        jpnTrangThai.add(jpn5);

        JLabel lbl1 = new JLabel();
        lbl1.setPreferredSize(CommonConstants.TRANGTHAI_SIZE);
        lbl1.setBackground(CommonConstants.DAT_TRUOC_COLOR);
        lbl1.setOpaque(true); // Thêm dòng này
        lbl1.setText("0");
        lbl1.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
        lbl1.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc

        JLabel lbl2 = new JLabel();
        lbl2.setPreferredSize(CommonConstants.TRANGTHAI_SIZE);
        lbl2.setBackground(CommonConstants.DANG_O_COLOR);
        lbl2.setOpaque(true); // Thêm dòng này
        lbl2.setText("0");
        lbl2.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
        lbl2.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc

        JLabel lbl3 = new JLabel();
        lbl3.setPreferredSize(CommonConstants.TRANGTHAI_SIZE);
        lbl3.setBackground(CommonConstants.DA_DAT_COC_COLOR);
        lbl3.setOpaque(true); // Thêm dòng này
        lbl3.setText("0");
        lbl3.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
        lbl3.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc

        JLabel lbl4 = new JLabel();
        lbl4.setPreferredSize(CommonConstants.TRANGTHAI_SIZE);
        lbl4.setBackground(CommonConstants.SAP_CHECKIN_COLOR);
        lbl4.setOpaque(true); // Thêm dòng này
        lbl4.setText("0");
        lbl4.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
        lbl4.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc

        JLabel lbl5 = new JLabel();
        lbl5.setPreferredSize(CommonConstants.TRANGTHAI_SIZE);
        lbl5.setBackground(CommonConstants.DEN_HAN_COLOR);
        lbl5.setOpaque(true); // Thêm dòng này
        lbl5.setText("0");
        lbl5.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
        lbl5.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc

        jpn1.add(lbl1); jpn1.add(new Label("Đặt trước"));
        jpn2.add(lbl2); jpn2.add(new Label("Đang ở"));
        jpn3.add(lbl3); jpn3.add(new Label("Đã đặt cọc"));
        jpn4.add(lbl4); jpn4.add(new Label("Sắp checkin"));
        jpn5.add(lbl5); jpn5.add(new Label("Đến hạn checkout"));

        container.add(jpnSearch, BorderLayout.CENTER);
        showSearchComponent(jpnSearch);
        container.add(jpnTrangThai, BorderLayout.SOUTH);
//        container.add(jpnDetail, BorderLayout.CENTER);
//        showDetailComponent(jpnDetail);
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
            if(ddp.getTrangThaiDonDatPhong().equals("Da huy")) continue;
            // lấy 1 chi tiết đơn đặt phòng phòng để có thông tin đơn đặt phòng và phòng
            DDPPanel ddpPanel = createJPNDonDatPhong(ddp);
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
                        ddp.setTrangThaiDonDatPhong("Dang o");
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
//                    jpnContent.add(new ThemDichVuGUI(new ChiTiet_DonDatPhong_PhongDAL().getChiTietDonDatPhongPhongTheoMaDDP(ddp.getMaDon())));
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




    public DDPPanel createJPNDonDatPhong(DonDatPhong donDatPhong) {

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

        // Thiết lập các nút và màu nền dựa trên trạng thái đặt cọc
        switch (donDatPhong.getTrangThaiDonDatPhong()) {
            case "Dat truoc":
                jpnButton.add(btnCheckin);
                jpnButton.add(btnHuy);
                jpnDDP.setBackground(CommonConstants.DAT_TRUOC_COLOR);
                break;
            case "Dang o":
                jpnButton.add(btnThemDichVu);
                jpnButton.add(btnCheckout);
                jpnDDP.setBackground(CommonConstants.DANG_O_COLOR);
                break;
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



    public void showSearchComponent(JPanel container) {

        Box boxContain = Box.createVerticalBox(); //
        container.add(boxContain);

        JPanel jpn1 = new JPanel(new GridLayout(3, 2));
        boxContain.add(jpn1);

        jpn1.add(UIHelpers.create_Form_Label_JDateChooser("Từ ngày",jdcNgayDen = new JDateChooser()));
        jpn1.add(UIHelpers.create_Form_Label_JDateChooser("Đến ngày", jdcNgayDi = new JDateChooser()));
        jpn1.add(UIHelpers.create_Form_Label_JComboBox("Tầng", cboTang =  new JComboBox()));
        jpn1.add(UIHelpers.create_Form_Label_JComboBox("Trạng thái", cboTrangThai =  new JComboBox()));
        jpn1.add(new JPanel());

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

        jpn1.add(jpnTmp);

    }

    public void showDetailComponent(JPanel container) {
        JPanel jpnDetail = new JPanel(new GridLayout(6, 2));
        container.add(jpnDetail);

        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Tên khách hàng", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Số điện thoại", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Số căn cước", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Email", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Tên phòng", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Tầng", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Trạng thái phòng", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Ngày checkin", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Ngày checkout", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Phương thức thanh toán", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Trạng thái đặt cọc", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Số tiền", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Số tiền", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Số tiền", new JTextField()));

    }

    public void showHeader() {
        JPanel jpnHeader = new JPanel(new GridLayout(1,2));
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
    }
}
