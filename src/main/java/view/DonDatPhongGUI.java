package view;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;
import constraints.CONSTRAINTS;
import dal.ChiTiet_DonDatPhong_PhongDAL;
import dal.DonDatPhongDAL;
import entity.ChiTiet_DonDatPhong_Phong;
import entity.DonDatPhong;
import entity.KhachHang;
import entity.Phong;
import utils.UIHelpers;

public class DonDatPhongGUI extends JPanel {
    // frame
    JButton btnTraCuu;
    public static JButton btnDatPhong;
    JDateChooser jdcNgayDen, jdcNgayDi;
    JComboBox cboTang, cboTrangThai;

    public JButton getBtnDatPhong() {
        return btnDatPhong;
    }

    public void setBtnDatPhong(JButton btnDatPhong) {
        this.btnDatPhong = btnDatPhong;
    }

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

        container.add(jpnSearch, BorderLayout.CENTER);
//        container.add(jpnDetail, BorderLayout.CENTER);

        showSearchComponent(jpnSearch);
//        showDetailComponent(jpnDetail);
    }



    private void showDanhSachDDP(JPanel container) {
        JPanel jpnDanhSachDDP = new JPanel();
        jpnDanhSachDDP.setLayout(new GridLayout(0, 5, 25, 30));
        JScrollPane scroll = new JScrollPane(jpnDanhSachDDP);

        container.add(scroll, BorderLayout.CENTER);

        ArrayList<ChiTiet_DonDatPhong_Phong> chiTiet = new ChiTiet_DonDatPhong_PhongDAL().getAllChiTietDonDatPhongPhong();
        for (ChiTiet_DonDatPhong_Phong ct : chiTiet) {
            jpnDanhSachDDP.add(createJPNDonDatPhong(ct));
        }
    }



    public JPanel createJPNDonDatPhong(ChiTiet_DonDatPhong_Phong ct) {
        JPanel jpnDDP = new JPanel();
        jpnDDP.setLayout(new BoxLayout(jpnDDP, BoxLayout.Y_AXIS));
        jpnDDP.setPreferredSize(new Dimension(250, 242));
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
        boxContain.add(jpn1);

        jpn1.add(UIHelpers.create_Form_Label_JDateChooser("Từ ngày",jdcNgayDen = new JDateChooser()));
        jpn1.add(UIHelpers.create_Form_Label_JDateChooser("Đến ngày", jdcNgayDi = new JDateChooser()));
        jpn1.add(UIHelpers.create_Form_Label_JComboBox("Tầng", cboTang =  new JComboBox()));
        jpn1.add(UIHelpers.create_Form_Label_JComboBox("Trạng thái", cboTrangThai =  new JComboBox()));
        jpn1.add(new JPanel());

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
}
