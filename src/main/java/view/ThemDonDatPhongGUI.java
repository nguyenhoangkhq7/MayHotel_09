/*
    *MayHotel  day creative: 10/21/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here   
     */


package view;

import com.toedter.calendar.JDateChooser;
import constraints.CONSTRAINTS;
import utils.UIHelpers;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ThemDonDatPhongGUI extends JPanel {
// Local variable
    private JButton btnReset,btnClose, btnChonPhong, btnCheckThongTinKhachHang, btnDatPhong;
    private JDateChooser jdcCheckIn, jdcCheckout;
    private JTextField jtfTongThoiGianO, jtfSoDienThoai, jtfHoTen, jtfCCCD, jtfEmail;
    private JComboBox cboLoaiPhong, cboSoPhong, cboPhuongThucThanhToan;
    private JTextArea jtaMoTa;
    private JLabel lblTongSoPhong, lblTongThoiGianO, lblTongTien;



    public ThemDonDatPhongGUI() {
        this.setBackground(Color.WHITE);
        showHeader();
        showContent();
//
//        setSize(1440,860);git sta
    }

    public void showContent() {
        // Tạo panel trái và phải
        JPanel jpnContentLeft = new JPanel(new BorderLayout());
        jpnContentLeft.setBackground(Color.WHITE);
        JPanel jpnContentRight = new JPanel(new BorderLayout());
        jpnContentRight.setBackground(Color.WHITE);


        // Thêm nội dung vào các panel bên trái và phải
        showLeftContent(jpnContentLeft);
        showRightContent(jpnContentRight);

        // Sử dụng JSplitPane với tỷ lệ 60-40
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jpnContentLeft, jpnContentRight);
        splitPane.setResizeWeight(0.6); // Đặt tỷ lệ 60% cho panel trái và 40% cho panel phải
        splitPane.setDividerSize(5);    // Độ rộng của thanh chia
        splitPane.setContinuousLayout(true);

        // Thêm splitPane vào frame
        this.add(splitPane, BorderLayout.CENTER);
    }






    public void showRightContent(JPanel container) {
        JPanel main = new JPanel(new GridLayout(2,1));
        container.add(main);

        showDanhSachPhong(main);
        showTamTinh(main);
    }
    public void showDanhSachPhong(JPanel container) {
        Box boxContain = Box.createVerticalBox();
        container.add(boxContain);
        boxContain.add(UIHelpers.create_Title_Panel("Danh sách phòng đã thêm"));

        // Tạo JPanel chính chứa danh sách các phòng
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS)); // Sắp xếp các phòng theo chiều dọc

        // Thêm các phòng vào listPanel
        listPanel.add(UIHelpers.create_Phong_Da_Them("Phòng số 013", "Delux Suites"));
        listPanel.add(Box.createVerticalStrut(15));
        listPanel.add(UIHelpers.create_Phong_Da_Them("Phòng số 014", "Standard Room"));
        listPanel.add(Box.createVerticalStrut(15));
        listPanel.add(UIHelpers.create_Phong_Da_Them("Phòng số 015", "Executive Suite"));
        listPanel.add(Box.createVerticalStrut(15));
        listPanel.add(UIHelpers.create_Phong_Da_Them("Phòng số 015", "Executive Suite"));
        listPanel.add(Box.createVerticalStrut(15));
        listPanel.add(UIHelpers.create_Phong_Da_Them("Phòng số 015", "Executive Suite"));
        listPanel.add(Box.createVerticalStrut(15));
        listPanel.add(UIHelpers.create_Phong_Da_Them("Phòng số 015", "Executive Suite"));
        listPanel.add(Box.createVerticalStrut(15));
        listPanel.add(UIHelpers.create_Phong_Da_Them("Phòng số 015", "Executive Suite"));
        listPanel.add(Box.createVerticalStrut(15));
        listPanel.add(UIHelpers.create_Phong_Da_Them("Phòng số 015", "Executive Suite"));

        // Đặt listPanel vào JScrollPane
        JScrollPane scroll = new JScrollPane(listPanel);
//        scroll.setPreferredSize(new Dimension(400, 200)); // Kích thước của JScrollPane

        // Thêm JScrollPane vào boxContain
        boxContain.add(scroll);
    }

    public void showTamTinh(JPanel container) {
        Box boxContain = Box.createVerticalBox();
        container.add(boxContain);
        boxContain.add(UIHelpers.create_Title_Panel("Tạm tính"));

        JPanel containCalculate = new JPanel(new GridLayout(3,3));
        containCalculate.add(new JLabel("Tổng số phòng"));
        containCalculate.add(new JPanel());
        containCalculate.add(lblTongSoPhong = new JLabel("4"));
        containCalculate.add(new JLabel("Tổng thời gian ở"));
        containCalculate.add(new JPanel());
        containCalculate.add(lblTongThoiGianO = new JLabel("2"));
        containCalculate.add(lblTongTien = new JLabel("Tổng tiền: 3080"));
        containCalculate.add(new JPanel());
        containCalculate.add(UIHelpers.create_Form_Label_Checkbox("Đặt cọc", new JCheckBox()));
        boxContain.add(containCalculate);

        boxContain.add(UIHelpers.create_Title_Panel("Thanh toán"));
        boxContain.add(UIHelpers.create_Form_Label_JComboBox("Phương thức thanh toán", cboPhuongThucThanhToan = new JComboBox()));

        JPanel containBtnDatPhong = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        containBtnDatPhong.add(btnDatPhong = new JButton("Đặt phòng"));
        UIHelpers.set_Orange_Blue_Style(btnDatPhong);

        boxContain.add(containBtnDatPhong);
    }
    public void showLeftContent(JPanel container) {
        Box boxContain = Box.createVerticalBox();
        container.add(boxContain);

        Box boxContain1 = Box.createHorizontalBox();
        boxContain1.add(UIHelpers.create_Form_Label_JDateChooser("Check in", jdcCheckIn = new JDateChooser()));
        boxContain1.add(Box.createHorizontalStrut(20));
        boxContain1.add(UIHelpers.create_Form_Label_JDateChooser("Check out", jdcCheckout = new JDateChooser()));
        boxContain1.add(Box.createHorizontalStrut(20));
        boxContain1.add(UIHelpers.create_Form_Label_JTextField("Tổng thời gian ở", jtfTongThoiGianO = new JTextField()));
        jtfTongThoiGianO.setEditable(false);
        boxContain.add(boxContain1);

        boxContain.add(Box.createVerticalStrut(CONSTRAINTS.VERTICAL_STRUT));
        boxContain.add(UIHelpers.create_Title_Panel("Chi tiết phòng"));

        Box boxContain2 = Box.createHorizontalBox();
        boxContain2.add(UIHelpers.create_Form_Label_JComboBox("Loại phòng", cboLoaiPhong = new JComboBox()));
        boxContain2.add(Box.createHorizontalStrut(20));
        boxContain2.add(UIHelpers.create_Form_Label_JComboBox("Số phòng", cboLoaiPhong = new JComboBox()));
        boxContain2.add(Box.createHorizontalStrut(20));
        boxContain2.add(btnChonPhong = new JButton("Chọn phòng"));
        UIHelpers.set_Button_Blue_Style(btnChonPhong);
        boxContain.add(boxContain2);

        boxContain.add(Box.createVerticalStrut(CONSTRAINTS.VERTICAL_STRUT));
        boxContain.add(UIHelpers.create_Title_Panel("Thông tin khách hàng"));

        Box boxContain3 = Box.createHorizontalBox();
        boxContain3.add(UIHelpers.create_Form_Label_JTextField("Số điện thoại", jtfSoDienThoai = new JTextField()));
        boxContain3.add(Box.createHorizontalStrut(30));
        boxContain3.add(btnCheckThongTinKhachHang = new JButton("Check"));
        UIHelpers.set_Button_Blue_Style(btnCheckThongTinKhachHang);
        boxContain.add(boxContain3);
        boxContain.add(Box.createVerticalStrut(CONSTRAINTS.VERTICAL_STRUT));

        Box boxContain4 = Box.createHorizontalBox();
        boxContain4.add(UIHelpers.create_Form_Label_JTextField("Họ tên", jtfHoTen = new JTextField()));
        boxContain4.add(Box.createHorizontalStrut(20));
        boxContain4.add(UIHelpers.create_Form_Label_JTextField("CCCD", jtfCCCD = new JTextField()));
        boxContain4.add(Box.createHorizontalStrut(20));
        boxContain4.add(UIHelpers.create_Form_Label_JTextField("Email", jtfEmail = new JTextField()));
        boxContain.add(boxContain4);
        boxContain.add(Box.createVerticalStrut(CONSTRAINTS.VERTICAL_STRUT));

        Box boxContain5 = Box.createHorizontalBox();
        boxContain5.add(UIHelpers.create_Form_Label_JTextArea("Mô tả", jtaMoTa = new JTextArea()));
        boxContain.add(boxContain5);

        boxContain.add(Box.createVerticalStrut(CONSTRAINTS.VERTICAL_STRUT));
        boxContain.add(UIHelpers.create_Title_Panel("Dịch vụ"));

//        Checkbox dich vụ
        JPanel jpnContainDichVu = new JPanel(new GridLayout(0,5));
        jpnContainDichVu.add(UIHelpers.create_Form_Label_Checkbox("Dịch vụ", new JCheckBox()));
        jpnContainDichVu.add(UIHelpers.create_Form_Label_Checkbox("Dịch vụ", new JCheckBox()));
        jpnContainDichVu.add(UIHelpers.create_Form_Label_Checkbox("Dịch vụ", new JCheckBox()));
        jpnContainDichVu.add(UIHelpers.create_Form_Label_Checkbox("Dịch vụ", new JCheckBox()));
        jpnContainDichVu.add(UIHelpers.create_Form_Label_Checkbox("Dịch vụ", new JCheckBox()));
        jpnContainDichVu.add(UIHelpers.create_Form_Label_Checkbox("Dịch vụ", new JCheckBox()));
        jpnContainDichVu.add(UIHelpers.create_Form_Label_Checkbox("Dịch vụ", new JCheckBox()));
        jpnContainDichVu.add(UIHelpers.create_Form_Label_Checkbox("Dịch vụ", new JCheckBox()));
        jpnContainDichVu.add(UIHelpers.create_Form_Label_Checkbox("Dịch vụ", new JCheckBox()));
        jpnContainDichVu.add(UIHelpers.create_Form_Label_Checkbox("Dịch vụ", new JCheckBox()));
        jpnContainDichVu.add(UIHelpers.create_Form_Label_Checkbox("Dịch vụ", new JCheckBox()));
        jpnContainDichVu.add(UIHelpers.create_Form_Label_Checkbox("Dịch vụ", new JCheckBox()));
        jpnContainDichVu.add(UIHelpers.create_Form_Label_Checkbox("Dịch vụ", new JCheckBox()));

        boxContain.add(jpnContainDichVu);
    }


    public void showHeader() {
        JPanel jpnHeader = new JPanel(new GridLayout(1,2));
        jpnHeader.setBackground(CONSTRAINTS.BACKGROUND);

        JLabel lblSubTitle = new JLabel("Đặt phòng");
        JLabel lblTitle = new JLabel("Thêm đơn đặt phòng");
        lblSubTitle.setFont(new Font("Arial", Font.ITALIC, CONSTRAINTS.TEXT_SIZE)); lblSubTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 30)); lblTitle.setForeground(Color.WHITE);

        JPanel jpn1 = new JPanel(new FlowLayout(FlowLayout.LEFT)); jpn1.add(lblSubTitle);
        JPanel jpn2 = new JPanel(new FlowLayout(FlowLayout.LEFT)); jpn2.add(lblTitle);
        Box headerLeft = Box.createVerticalBox();
        headerLeft.add(jpn1);
        headerLeft.add(jpn2);

        JPanel headerRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnReset = new JButton("Reset"); btnReset.setFont(new Font("Arial", Font.BOLD, CONSTRAINTS.TEXT_SIZE));
        btnClose = new JButton("Close"); btnClose.setFont(new Font("Arial", Font.BOLD, CONSTRAINTS.TEXT_SIZE));
        headerRight.add(btnReset); UIHelpers.set_Button_Orange_Outline_Style(btnReset);
        headerRight.add(btnClose); UIHelpers.set_Button_Orange_Outline_Style(btnClose);

        jpnHeader.add(headerLeft);
        jpnHeader.add(headerRight);

        jpn1.setOpaque(false);
        jpn2.setOpaque(false);
        headerRight.setOpaque(false);

        this.add(jpnHeader, BorderLayout.NORTH);
    }




    public static void main(String[] args) {
        new ThemDonDatPhongGUI();
    }
}
