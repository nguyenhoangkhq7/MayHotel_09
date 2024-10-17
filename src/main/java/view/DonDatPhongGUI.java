package view;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;
import constraints.CONSTRAINTS;
import entity.ChiTiet_DonDatPhong_Phong;


public class DonDatPhongGUI extends JFrame {
    // frame
    JPanel jpnMenu;
    JPanel jpnContainMain;
    JPanel jpnContent;
    JPanel jpnContain_Search_Detail;
    JButton btnTraCuu;

    public DonDatPhongGUI() {
        setTitle("Khách sạn MAY");
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        jpnContainMain = new JPanel();
        jpnContainMain.setLayout(new BorderLayout());
        add(jpnContainMain, BorderLayout.CENTER);

        jpnMenu = new JPanel();
        add(jpnMenu, BorderLayout.WEST);

        jpnContent = new JPanel();
        jpnContent.setLayout(new BorderLayout());
        jpnContainMain.add(jpnContent, BorderLayout.CENTER);

        jpnContain_Search_Detail = new JPanel();
        jpnContent.add(jpnContain_Search_Detail, BorderLayout.NORTH);
        jpnContain_Search_Detail.setLayout(new GridBagLayout()); // Đổi sang GridBagLayout


        showMenuComponent();
        showHeaderComponent();
        showSearchComponent();
        showDetailComponent();
        showDanhSachDDP();
        setSize(1920, 1080);
    }

    private void showDanhSachDDP() {
        JPanel jpnDanhSachDDP = new JPanel();
        jpnDanhSachDDP.setLayout(new GridLayout(0,6, 25,30));
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(jpnDanhSachDDP);
        jpnContent.add(scroll, BorderLayout.CENTER);

        // thêm danh sách đơn đặt phòng vào
//        jpnDanhSachDDP.add(new ChiTiet_DonDatPhong_Phong())

// Thêm jpnListDonDatPhong vào khung chính hoặc JScrollPane nếu cần

    }

    public JPanel createJPNDonDatPhong(ChiTiet_DonDatPhong_Phong ct) {
//        String maPhong, String tang, String tenKhachHang, LocalDate ngayNhanPhong, LocalDate ngayTraPhong
        // Tạo JPanel cho đơn đặt phòng
        JPanel jpnDDP = new JPanel();
        jpnDDP.setLayout(new BoxLayout(jpnDDP, BoxLayout.Y_AXIS));
        jpnDDP.setPreferredSize(new Dimension(30, 60)); // Đặt kích thước cho JPanel
        jpnDDP.setBorder(BorderFactory.createLineBorder(CONSTRAINTS.ORANGE, 2)); // Thêm border cho JPanel
        // set màu nền

        // Tạo các JLabel để hiển thị thông tin
        JPanel jpn1 = new JPanel();
        JPanel jpn2 = new JPanel();
        JPanel jpn3 = new JPanel();
        JPanel jpn4 = new JPanel();
        JPanel jpn5 = new JPanel();

        JLabel lblMaPhong = new JLabel("Mã phòng: " + ct.getPhong().getMaPhong());
        JLabel lblTang = new JLabel("Tầng: " + ct.getPhong().getTang());
        JLabel lblTenKhachHang = new JLabel("Tên khách hàng: " + ct.getDonDatPhong().getKhachHang().getMaKH());
        // Định dạng ngày để hiển thị
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        JLabel lblNgayNhanPhong = new JLabel("Ngày nhận phòng: " + ct.getNgayNhanPhong().format(formatter));
        JLabel lblNgayTraPhong = new JLabel("Ngày trả phòng: " + ct.getNgayTra().format(formatter));

        jpn1.add(lblMaPhong);
        jpn2.add(lblTang);
        jpn3.add(lblTenKhachHang);
        jpn4.add(lblNgayNhanPhong);
        jpn5.add(lblNgayTraPhong);

        // Thêm các JLabel vào JPanel
        jpnDDP.add(jpn1);
        jpnDDP.add(jpn2);
        jpnDDP.add(jpn3);
        jpnDDP.add(jpn4);
        jpnDDP.add(jpn5);

        return jpnDDP;
    }

    public void showSearchComponent() {
        Box jpnSearch = Box.createVerticalBox();
        jpnContain_Search_Detail.add(jpnSearch);
        TitledBorder titledBorder = new TitledBorder(BorderFactory.createLineBorder(CONSTRAINTS.ORANGE, 1), "Tìm kiếm", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), CONSTRAINTS.ORANGE);
        jpnSearch.setBorder(titledBorder);
        jpnSearch.setBorder(BorderFactory.createCompoundBorder(
                jpnSearch.getBorder(),
                new EmptyBorder(10, 10, 10, 10) // Khoảng cách 10 pixel
        ));

        JPanel jpn1 = new JPanel(new GridLayout(3, 2, 100, 20));
        jpn1.add(DonDatPhongGUI.formCreate_Label_TypeInput("Từ ngày", JDateChooser.class, true));
        jpn1.add(DonDatPhongGUI.formCreate_Label_TypeInput("Đến ngày", JDateChooser.class, true));
        jpn1.add(DonDatPhongGUI.formCreate_Label_TypeInput("Tầng", JComboBox.class, true));
        jpn1.add(DonDatPhongGUI.formCreate_Label_TypeInput("Trạng thái", JComboBox.class, true));

        btnTraCuu = new JButton("Tra cứu");
        btnTraCuu = setButtonColor(btnTraCuu);
        JPanel jpnButton = new JPanel();
        jpnButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpnButton.add(btnTraCuu);
        jpn1.add(jpnButton);

        jpnSearch.add(jpn1);
        jpnSearch.add(Box.createVerticalGlue());

        // Tạo GridBagConstraints để thêm jpnSearch
        GridBagConstraints gbcSearch = new GridBagConstraints();
        gbcSearch.fill = GridBagConstraints.BOTH; // Chiếm toàn bộ không gian
        gbcSearch.weightx = 1; // Tỷ lệ chiều ngang
        gbcSearch.weighty = 1; // Tỷ lệ chiều dọc
        gbcSearch.gridx = 0; // Cột 0
        gbcSearch.gridy = 0; // Hàng 0
        gbcSearch.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
        jpnContain_Search_Detail.add(jpnSearch, gbcSearch);
    }

    public void showDetailComponent() {
        JPanel jpnDetail = new JPanel(new GridLayout(6,2,100,0));
        TitledBorder titledBorder = new TitledBorder(BorderFactory.createLineBorder(CONSTRAINTS.ORANGE, 1), "Chi tiết đơn đặt phòng", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14),CONSTRAINTS.ORANGE);
        jpnDetail.setBorder(titledBorder);
        jpnDetail.setBorder(BorderFactory.createCompoundBorder(
                jpnDetail.getBorder(),
                new EmptyBorder(10, 10, 10, 10) // Khoảng cách 10 pixel
        ));

        jpnDetail.setBorder(BorderFactory.createCompoundBorder(
                jpnDetail.getBorder(),
                new EmptyBorder(00, 50, 00, 50) // Khoảng cách 10 pixel
        ));

        jpnDetail.add(DonDatPhongGUI.formCreate_Label_TypeInput("Mã đơn", JTextField.class, false));
        jpnDetail.add(DonDatPhongGUI.formCreate_Label_TypeInput("Mã đơn", JTextField.class, false));
        jpnDetail.add(DonDatPhongGUI.formCreate_Label_TypeInput("Mã đơn", JTextField.class, false));
        jpnDetail.add(DonDatPhongGUI.formCreate_Label_TypeInput("Mã đơn", JTextField.class, false));
        jpnDetail.add(DonDatPhongGUI.formCreate_Label_TypeInput("Mã đơn", JTextField.class, false));
        jpnDetail.add(DonDatPhongGUI.formCreate_Label_TypeInput("Mã đơn", JTextField.class, false));
        jpnDetail.add(DonDatPhongGUI.formCreate_Label_TypeInput("Mã đơn", JTextField.class, false));
        jpnDetail.add(DonDatPhongGUI.formCreate_Label_TypeInput("Mã đơn", JTextField.class, false));
        jpnDetail.add(DonDatPhongGUI.formCreate_Label_TypeInput("Mã đơn", JTextField.class, false));
        jpnDetail.add(DonDatPhongGUI.formCreate_Label_TypeInput("Mã đơn", JTextField.class, false));

        // Tạo GridBagConstraints để thêm jpnDetail
        GridBagConstraints gbcDetail = new GridBagConstraints();
        gbcDetail.fill = GridBagConstraints.BOTH; // Chiếm toàn bộ không gian
        gbcDetail.weightx = 2; // Tỷ lệ chiều ngang
        gbcDetail.weighty = 1; // Tỷ lệ chiều dọc
        gbcDetail.gridx = 1; // Cột 1
        gbcDetail.gridy = 0; // Hàng 0
        gbcDetail.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
        jpnContain_Search_Detail.add(jpnDetail, gbcDetail);
    }

    // Hàm tạo các thành phần trong form
    public static Box formCreate_Label_TypeInput(String labelTitle, Class<?> inputType,Boolean isEnable) {
        Box box = Box.createVerticalBox(); // Tạo Box dọc
        // Tạo JLabel với tiêu đề
        JPanel tmp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(labelTitle);
        box.add(Box.createVerticalStrut(10));
        tmp.add(label);
        box.add(tmp);
        box.add(Box.createVerticalStrut(5)); // Khoảng cách giữa Label và Input
        // Tạo thành phần đầu vào dựa trên loại
        JComponent input = null;
        try {
            if (inputType == JTextField.class) {
                input = new JTextField(15);
            } else if (inputType == JDateChooser.class) {
                input = new JDateChooser(); // Tạo JDateChooser
                ((JDateChooser) input).setDateFormatString("dd/MM/yyyy"); // Đặt định dạng ngày
            } else if (inputType == JComboBox.class) {
                input = new JComboBox<>();
            } else {
                input = (JComponent) inputType.getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (input != null) {
            input.setEnabled(isEnable);
            box.add(input);
        }
        return box;
    }

    // setColor button
    public JButton setButtonColor(JButton btn) {
        btn.setOpaque(true);
        btn.setPreferredSize(new Dimension(120, 45));
        btn.setForeground(CONSTRAINTS.ORANGE);
        btn.setBorder(new LineBorder(CONSTRAINTS.ORANGE, 2));
        btn.setBackground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }

    private void showMenuComponent() {

    }

    public void showHeaderComponent() {
        JPanel jpnHeader = new JPanel();
        jpnHeader.setLayout(new GridLayout(1, 2));
        jpnContainMain.add(jpnHeader, BorderLayout.NORTH);

        JPanel jpn1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel jpn2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jpnHeader.add(jpn1);
        jpnHeader.add(jpn2);
        JLabel lblTitle = new JLabel("Quản lý đơn đặt phòng");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        jpn1.add(lblTitle);
        JButton btnDatPhong = new JButton("Đặt phòng");
        btnDatPhong = setButtonColor(btnDatPhong);
        jpn2.add(btnDatPhong);
    }

    public static void main(String[] args) {
        new DonDatPhongGUI();
    }
}
