package view;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;
import constraints.CONSTRAINTS;
import entity.ChiTiet_DonDatPhong_Phong;
import entity.DonDatPhong;
import entity.KhachHang;
import entity.Phong;
import utils.UIHelpers;

public class DonDatPhongGUI extends JPanel {
    // frame
    JButton btnTraCuu, btnDatPhong;
    JDateChooser jdcNgayDen, jdcNgayDi;
    JComboBox cboTang, cboTrangThai;


    public DonDatPhongGUI() {
        setLayout(new BorderLayout()); // Sử dụng BorderLayout cho JFrame

        showHeader();
        showContent();

        setVisible(true); // Đặt setVisible ở cuối
    }

    public void showContent() {
        JPanel jpnContent = new JPanel(new BorderLayout());
        this.add(jpnContent, BorderLayout.CENTER);

        JPanel jpnSearch_Detail = new JPanel(new BorderLayout());
        JPanel jpnDanhSachDDP = new JPanel(new BorderLayout());

        jpnContent.add(jpnSearch_Detail, BorderLayout.NORTH);
        jpnContent.add(jpnDanhSachDDP, BorderLayout.CENTER);

        showSearch_Detail(jpnSearch_Detail);
        showDanhSachDDP(jpnDanhSachDDP);
    }


    public void showSearch_Detail(JPanel container) {
        JPanel jpnSearch = new JPanel();
        JPanel jpnDetail = new JPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jpnSearch, jpnDetail);
        splitPane.setResizeWeight(0.4); // Đặt tỷ lệ 60% cho panel trái và 40% cho panel phải
        splitPane.setDividerSize(5);    // Độ rộng của thanh chia
        splitPane.setContinuousLayout(true);

        container.add(splitPane);

        showSearchComponent(jpnSearch);
        showDetailComponent(jpnDetail);
    }



    private void showDanhSachDDP(JPanel container) {
        JPanel jpnDanhSachDDP = new JPanel();
        jpnDanhSachDDP.setLayout(new GridLayout(0, 6, 25, 30));
        JScrollPane scroll = new JScrollPane(jpnDanhSachDDP);
        container.add(scroll);
    }


    public JPanel createJPNDonDatPhong(ChiTiet_DonDatPhong_Phong ct) {
        JPanel jpnDDP = new JPanel();
        jpnDDP.setLayout(new BoxLayout(jpnDDP, BoxLayout.Y_AXIS));
        jpnDDP.setPreferredSize(new Dimension(30, 60));
        jpnDDP.setBorder(BorderFactory.createLineBorder(CONSTRAINTS.ORANGE, 2));

        JPanel jpn1 = new JPanel();
        JPanel jpn2 = new JPanel();
        JPanel jpn3 = new JPanel();
        JPanel jpn4 = new JPanel();
        JPanel jpn5 = new JPanel();

        JLabel lblMaPhong = new JLabel("Mã phòng: " + ct.getPhong().getMaPhong());
        JLabel lblTang = new JLabel("Tầng: " + ct.getPhong().getTang());
        JLabel lblTenKhachHang = new JLabel("Tên khách hàng: " + ct.getDonDatPhong().getKhachHang().getMaKH());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        JLabel lblNgayNhanPhong = new JLabel("Ngày nhận phòng: " + ct.getNgayNhanPhong().format(formatter));
        JLabel lblNgayTraPhong = new JLabel("Ngày trả phòng: " + ct.getNgayTra().format(formatter));

        jpn1.add(lblMaPhong);
        jpn2.add(lblTang);
        jpn3.add(lblTenKhachHang);
        jpn4.add(lblNgayNhanPhong);
        jpn5.add(lblNgayTraPhong);

        jpnDDP.add(jpn1);
        jpnDDP.add(jpn2);
        jpnDDP.add(jpn3);
        jpnDDP.add(jpn4);
        jpnDDP.add(jpn5);

        return jpnDDP;
    }

    public void showSearchComponent(JPanel container) {

        Box boxContain = Box.createVerticalBox(); //
        container.add(boxContain);

        JPanel jpn1 = new JPanel(new GridLayout(3, 2));
        jpn1.add(UIHelpers.create_Form_Label_JDateChooser("Từ ngày",jdcNgayDen = new JDateChooser()));
        jpn1.add(UIHelpers.create_Form_Label_JDateChooser("Đến ngày", jdcNgayDi = new JDateChooser()));
        jpn1.add(UIHelpers.create_Form_Label_JComboBox("Tầng", cboTang =  new JComboBox()));
        jpn1.add(UIHelpers.create_Form_Label_JComboBox("Trạng thái", cboTrangThai =  new JComboBox()));
        jpn1.add(new JPanel());
        jpn1.add(btnTraCuu = new JButton("Tra cứu"));
        UIHelpers.set_Button_Blue_Style(btnTraCuu);

<<<<<<< HEAD
        jpnSearch.add(jpn1);
        jpnSearch.add(Box.createVerticalGlue());

        // Tạo GridBagConstraints để thêm jpnSearch
        Grid BagConstraints gbcSearch = new GridBagConstraints();
        gbcSearch.fill = GridBagConstraints.BOTH; // Chiếm toàn bộ không gian
        gbcSearch.weightx = 1; // Tỷ lệ chiều ngang
        gbcSearch.weighty = 1; // Tỷ lệ chiều dọc
        gbcSearch.gridx = 0; // Cột 0
        gbcSearch.gridy = 0; // Hàng 0
        gbcSearch.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
        jpnContain_Search_Detail.add(jpnSearch, gbcSearch);
=======
        boxContain.add(jpn1);
        boxContain.add(Box.createVerticalGlue());
>>>>>>> 25d810d27645ba6326964de7c86535893336cfe2
    }

    public void showDetailComponent(JPanel container) {
        JPanel jpnDetail = new JPanel(new GridLayout(6, 2));
        container.add(jpnDetail);

        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Mã đơn", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Tên khách hàng", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Số phòng", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Ngày nhận phòng", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Ngày trả phòng", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Số tiền", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Số tiền", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Số tiền", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Số tiền", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Số tiền", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Số tiền", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Số tiền", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Số tiền", new JTextField()));
        jpnDetail.add(UIHelpers.create_Form_Label_JTextField("Số tiền", new JTextField()));

    }

    public void showHeader() {
        JPanel jpnHeader = new JPanel(new GridLayout(1,2));
        jpnHeader.setBackground(CONSTRAINTS.BACKGROUND);

        JLabel lblSubTitle = new JLabel("Đặt phòng");
        JLabel lblTitle = new JLabel("Quản lý đơn đặt phòng");
        lblSubTitle.setFont(new Font("Arial", Font.ITALIC, CONSTRAINTS.TEXT_SIZE)); lblSubTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 30)); lblTitle.setForeground(Color.WHITE);

        JPanel jpn1 = new JPanel(new FlowLayout(FlowLayout.LEFT)); jpn1.add(lblSubTitle);
        JPanel jpn2 = new JPanel(new FlowLayout(FlowLayout.LEFT)); jpn2.add(lblTitle);
        Box headerLeft = Box.createVerticalBox();
        headerLeft.add(jpn1);
        headerLeft.add(jpn2);

        JPanel headerRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnDatPhong = new JButton("Đặt phòng"); btnDatPhong.setFont(new Font("Arial", Font.BOLD, CONSTRAINTS.TEXT_SIZE));
        headerRight.add(btnDatPhong); UIHelpers.set_Button_Orange_Outline_Style(btnDatPhong);

        jpnHeader.add(headerLeft);
        jpnHeader.add(headerRight);

        jpn1.setOpaque(false);
        jpn2.setOpaque(false);
        headerRight.setOpaque(false);

        this.add(jpnHeader, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        new DonDatPhongGUI();
    }
}
