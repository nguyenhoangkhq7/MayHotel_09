/*
    *MayHotel  day creative: 10/21/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here   
     */


package view.panel;

import bus.ThemDonDatPhongBUS;
import com.toedter.calendar.JDateChooser;
import constant.CommonConstants;
import dal.LoaiPhongDAL;
import dal.PhongDAL;
import entity.*;
import helper.UIHelpers;
import view.component.PhongDonDatPhongPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

public class ThemDonDatPhongPanel extends JPanel implements ActionListener {
//Local variable
//  Cac biến component
    private JButton btnReset,btnClose, btnChonPhong, btnCheckThongTinKhachHang, btnDatPhong, btnThemDichVu;
    private JDateChooser jdcCheckIn, jdcCheckout;
    private JTextField jtfTongThoiGianO, jtfSoDienThoai, jtfHoTen, jtfCCCD, jtfEmail;
    private JComboBox cboLoaiPhong, cboSoPhong, cboPhuongThucThanhToan;
    private JTextArea jtaMoTa;
    private JLabel lblTongSoPhong, lblTongThoiGianO, lblTongTien;
    JPanel mainRightPanel;
    JCheckBox chkTrangThaiDatCoc;
//  Biến xử lý


//  Biến bên lưu giữ tạm thời
    ArrayList<Phong> dsPhong = new ArrayList<>();

//  Biên container
    JPanel jpnHeader, jpnContainContent;
    Box boxContainDanhSachPhong;

    NhanVien nhanVienDangTruc;

    public NhanVien getNhanVienDangTruc() {
        return nhanVienDangTruc;
    }

    public ThemDonDatPhongPanel(NhanVien nhanVienDangTruc) {
        this.nhanVienDangTruc = nhanVienDangTruc;
        this.setLayout(new BorderLayout());
//        setBackground(Color.WHITE);
//      thêm header
        jpnHeader = new JPanel(new GridLayout(1,2));
        jpnHeader.setBackground(CommonConstants.BACKGROUND);
        JLabel lblSubTitle = new JLabel("Đặt phòng");
        JLabel lblTitle = new JLabel("Thêm đơn đặt phòng");
        lblSubTitle.setFont(new Font("Arial", Font.ITALIC, CommonConstants.TEXT_SIZE)); lblSubTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 30)); lblTitle.setForeground(Color.WHITE);
        JPanel jpn1 = new JPanel(new FlowLayout(FlowLayout.LEFT)); jpn1.add(lblSubTitle);
        JPanel jpn2 = new JPanel(new FlowLayout(FlowLayout.LEFT)); jpn2.add(lblTitle);
        Box headerLeft = Box.createVerticalBox();
        headerLeft.add(jpn1);
        headerLeft.add(jpn2);
        JPanel headerRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnReset = new JButton("Reset"); btnReset.setFont(new Font("Arial", Font.BOLD, CommonConstants.TEXT_SIZE));
        btnClose = new JButton("Close"); btnClose.setFont(new Font("Arial", Font.BOLD, CommonConstants.TEXT_SIZE));
        headerRight.add(btnReset); UIHelpers.set_Button_Orange_Outline_Style(btnReset);
        headerRight.add(btnClose); UIHelpers.set_Button_Orange_Outline_Style(btnClose);
        jpnHeader.add(headerLeft);
        jpnHeader.add(headerRight);
        jpn1.setOpaque(false);
        jpn2.setOpaque(false);
        headerRight.setOpaque(false);
        this.add(jpnHeader, BorderLayout.NORTH);

//      Thêm nội dung cho content
        jpnContainContent = new JPanel(new BorderLayout());
        this.add(jpnContainContent, BorderLayout.CENTER);
        jpnContainContent.add(showLeftContent(),BorderLayout.CENTER);
        jpnContainContent.add(showRightContent(), BorderLayout.EAST);

//      Thêm loại phòng sẵn vào combobox
        themLoaiPhongVaoCombobox();

//      thêm sự kiện
        btnClose.addActionListener(this);
        btnReset.addActionListener(this);
        btnDatPhong.addActionListener(this);
        btnCheckThongTinKhachHang.addActionListener(this);
        btnChonPhong.addActionListener(this);
        cboLoaiPhong.addActionListener(this);
        jdcCheckout.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                // Lấy ngày Check-in
                Date checkInDate = jdcCheckIn.getDate();
                // Lấy ngày Check-out
                Date checkOutDate = jdcCheckout.getDate();

                // Kiểm tra xem cả hai ngày có giá trị không
                if (checkInDate != null && checkOutDate != null) {
                    LocalDateTime tgCheckin = checkInDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    LocalDateTime tgCheckout = checkOutDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

                    // Tính số ngày giữa hai ngày
                    long tongThoiGianO = ChronoUnit.DAYS.between(tgCheckin, tgCheckout);

                    // Cập nhật giá trị vào trường văn bản
                    jtfTongThoiGianO.setText(String.valueOf(tongThoiGianO));

                } else {
                    // Nếu một trong hai ngày là null, có thể đặt tổng thời gian ở về 0 hoặc để trống
                    jtfTongThoiGianO.setText("");
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày checkin", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    jdcCheckIn.addPropertyChangeListener("date", new PropertyChangeListener() {
                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                            // Lấy ngày Check-in
                            Date checkInDate = jdcCheckIn.getDate();
                            // Lấy ngày Check-out
                            Date checkOutDate = jdcCheckout.getDate();

                            // Kiểm tra xem cả hai ngày có giá trị không
                            if (checkInDate != null && checkOutDate != null) {
                                LocalDateTime tgCheckin = checkInDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                                LocalDateTime tgCheckout = checkOutDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

                                // Tính số ngày giữa hai ngày
                                long tongThoiGianO = ChronoUnit.DAYS.between(tgCheckin, tgCheckout);

                                // Cập nhật giá trị vào trường văn bản
                                jtfTongThoiGianO.setText(String.valueOf(tongThoiGianO));

                            } else {
                                // Nếu một trong hai ngày là null, có thể đặt tổng thời gian ở về 0 hoặc để trống
                                jtfTongThoiGianO.setText("");
                            }
                        }
                    });
                }
            }
        });
        // lấy tất cả phòng tuwf loại phòng ban đầu
        capNhatPhongKhiChonLoaiPhong();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o.equals(btnClose)) {
            this.setVisible(false);
        }
        else if(o.equals(btnReset)) {
            resetFields();
        }
        else if (o.equals(btnDatPhong)) {
            xuLySKDatPhong();
        }
        else if (o.equals(btnCheckThongTinKhachHang)) {
            xuLySKCheckThongTinKhachHang();
        }
        else if(o.equals(btnChonPhong)) {
            xuLySKChonPhong();
        }
        else if (o.equals(cboLoaiPhong)) {
            capNhatPhongKhiChonLoaiPhong();
        }
    }

    private void xuLySKChonPhong() {
        String tenPhong = cboSoPhong.getSelectedItem().toString();
        ThemDonDatPhongBUS themDonDatPhongBUS = new ThemDonDatPhongBUS();
        dsPhong.add(themDonDatPhongBUS.getPhongTheoTenPhong(tenPhong));
        updateDanhSachPhong();
    }
    public void updateDanhSachPhong() {
            JPanel jpnPhong = new PhongDonDatPhongPanel(dsPhong.get(dsPhong.size()-1));
            boxContainDanhSachPhong.add(jpnPhong);
            boxContainDanhSachPhong.repaint();
            boxContainDanhSachPhong.revalidate();
    }
    private void resetFields() {
        jtfSoDienThoai.setText("");
        jtfHoTen.setText("");
        jtfCCCD.setText("");
        jtfEmail.setText("");
        jtfTongThoiGianO.setText("");
        cboLoaiPhong.setSelectedIndex(0);
        cboSoPhong.removeAllItems();
        jdcCheckIn.setDate(null);
        jdcCheckout.setDate(null);
        boxContainDanhSachPhong.removeAll();
    }
    public void xuLySKCheckThongTinKhachHang() {
        String soDienThoai = jtfSoDienThoai.getText();

        ThemDonDatPhongBUS themDonDatPhongBUS = new ThemDonDatPhongBUS();
        KhachHang khachHang = themDonDatPhongBUS.getKhachHangTheoSoDienThoai(soDienThoai);

        if (khachHang != null) {
            jtfHoTen.setText(khachHang.getHoTen());
            jtfCCCD.setText(khachHang.getSoCanCuoc());
            jtfEmail.setText(khachHang.getEmail());
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin khách hàng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }
    public void xuLySKDatPhong() {
        ThemDonDatPhongBUS themDonDatPhongBUS = new ThemDonDatPhongBUS();
        // 1. Thu thập thông tin từ các trường nhập liệu
        Date dateCheckin = jdcCheckIn.getDate();
        Date dateCheckout = jdcCheckout.getDate();
        String loaiPhong = cboLoaiPhong.getSelectedItem().toString();
        String soPhong = cboSoPhong.getSelectedItem().toString();
        String soDienThoai = jtfSoDienThoai.getText();
        String hoTen = jtfHoTen.getText();
        String cccd = jtfCCCD.getText();
        String email = jtfEmail.getText();
        String moTa = jtaMoTa.getText();
        String phuongThucThanhToan = cboPhuongThucThanhToan.getSelectedItem().toString();
        boolean trangThaiDatCoc = chkTrangThaiDatCoc.isSelected();


        jtfTongThoiGianO.setText(String.valueOf(themDonDatPhongBUS.tongThoiGianO(dateCheckin, dateCheckout)));

        // thêm đơn đặt phòng
        boolean isSuccess = themDonDatPhongBUS.themDonDatPhong(this.nhanVienDangTruc ,dateCheckin, dateCheckout, loaiPhong, soPhong, soDienThoai, hoTen, cccd, email, moTa, phuongThucThanhToan, trangThaiDatCoc, dsPhong);

        // Cập nhật giao diện
        if (isSuccess) {
            JOptionPane.showMessageDialog(null, "Đặt phòng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            resetFields(); // Hàm để làm mới các trường nhập liệu
        } else {
            JOptionPane.showMessageDialog(null, "Đặt phòng thất bại! Vui lòng thử lại.", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void themLoaiPhongVaoCombobox() {
        LoaiPhongDAL loaiPhongDAL = new LoaiPhongDAL();
        ArrayList<LoaiPhong> dsLoaiPhong = loaiPhongDAL.getAllLoaiPhong() ;
        for(LoaiPhong lp : dsLoaiPhong) {
            cboLoaiPhong.addItem(lp.getTenLoaiPhong());
        }
        cboLoaiPhong.setSelectedItem(0);
    }
    private void capNhatPhongKhiChonLoaiPhong() {
        LoaiPhongDAL loaiPhongDAL = new LoaiPhongDAL();
        PhongDAL phongDAL = new PhongDAL();
        cboSoPhong.removeAllItems();
        String loaiPhong = loaiPhongDAL.getMaLoaiPhongTheoTen((String) cboLoaiPhong.getSelectedItem());
        if (loaiPhong != null) {
            ArrayList<Phong> dsPhong = phongDAL.getAllPhongByMaLoaiPhong(loaiPhong);
            for (Phong p : dsPhong) {
                cboSoPhong.addItem(p.getTenPhong());
            }
        }
    }

    public JPanel showRightContent() {
        mainRightPanel = new JPanel(new BorderLayout());

//        Thêm danh sách phòng
        JPanel jpnContain = new JPanel(new BorderLayout());
        mainRightPanel.add(jpnContain, BorderLayout.CENTER);
        this.boxContainDanhSachPhong = Box.createVerticalBox();
        JScrollPane scroll = new JScrollPane(this.boxContainDanhSachPhong);
        jpnContain.add(UIHelpers.create_Title_Panel("Danh sách phòng đã thêm"), BorderLayout.NORTH);
        jpnContain.add(scroll, BorderLayout.CENTER);
//      boxContainDanhSachPhong sẽ chứa các phòng // thêm phòng vô sau ở phần xử lý sự kiện

//      Thêm tạm tính tiền
        Box boxContainTamTinh = Box.createVerticalBox();
        mainRightPanel.add(boxContainTamTinh, BorderLayout.SOUTH);
        boxContainTamTinh.add(UIHelpers.create_Title_Panel("Tạm tính"));
        JPanel containCalculate = new JPanel(new GridLayout(3,3));
        containCalculate.add(new JLabel("Tổng số phòng"));
        containCalculate.add(new JPanel());
        containCalculate.add(lblTongSoPhong = new JLabel("4"));
        containCalculate.add(new JLabel("Tổng thời gian ở"));
        containCalculate.add(new JPanel());
        containCalculate.add(lblTongThoiGianO = new JLabel("2"));
        containCalculate.add(lblTongTien = new JLabel("Tổng tiền: 3080"));
        containCalculate.add(new JPanel());
        containCalculate.add(UIHelpers.create_Form_Label_Checkbox("Đặt cọc",chkTrangThaiDatCoc = new JCheckBox()));
        boxContainTamTinh.add(containCalculate);
        boxContainTamTinh.add(UIHelpers.create_Title_Panel("Thanh toán"));
        boxContainTamTinh.add(new JPanel().add(UIHelpers.create_Form_Label_JComboBox("Phương thức thanh toán", cboPhuongThucThanhToan = new JComboBox())));
        cboPhuongThucThanhToan.addItem("Tiền mặt");
        cboPhuongThucThanhToan.addItem("Thẻ tín dụng");
        cboPhuongThucThanhToan.addItem("Chuyển khoản ngân hàng");
        JPanel containBtnDatPhong = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        containBtnDatPhong.add(btnDatPhong = new JButton("Đặt phòng"));
        UIHelpers.set_Orange_Blue_Style(btnDatPhong);
        boxContainTamTinh.add(containBtnDatPhong);

        return mainRightPanel;
    }

    public Box showLeftContent() {
        Box boxContain = Box.createVerticalBox();

        Box boxContain1 = Box.createHorizontalBox();
        boxContain1.add(UIHelpers.create_Form_Label_JDateChooser("Check in", jdcCheckIn = new JDateChooser()));
        boxContain1.add(Box.createHorizontalStrut(20));
        boxContain1.add(UIHelpers.create_Form_Label_JDateChooser("Check out", jdcCheckout = new JDateChooser()));
        boxContain1.add(Box.createHorizontalStrut(20));
        boxContain1.add(UIHelpers.create_Form_Label_JTextField("Tổng thời gian ở", jtfTongThoiGianO = new JTextField()));
        jtfTongThoiGianO.setEditable(false);
        boxContain.add(boxContain1);

        boxContain.add(Box.createVerticalStrut(CommonConstants.VERTICAL_STRUT));
        boxContain.add(UIHelpers.create_Title_Panel("Chi tiết phòng"));

        Box boxContain2 = Box.createHorizontalBox();
        boxContain2.add(UIHelpers.create_Form_Label_JComboBox("Loại phòng", cboLoaiPhong = new JComboBox()));
        boxContain2.add(Box.createHorizontalStrut(20));
        boxContain2.add(UIHelpers.create_Form_Label_JComboBox("Số phòng", cboSoPhong = new JComboBox()));
        boxContain2.add(Box.createHorizontalStrut(20));
        boxContain2.add(btnChonPhong = new JButton("Chọn phòng"));
        UIHelpers.set_Button_Blue_Style(btnChonPhong);
        boxContain.add(boxContain2);

        boxContain.add(Box.createVerticalStrut(CommonConstants.VERTICAL_STRUT));
        boxContain.add(UIHelpers.create_Title_Panel("Thông tin khách hàng"));

        Box boxContain3 = Box.createHorizontalBox();
        boxContain3.add(UIHelpers.create_Form_Label_JTextField("Số điện thoại", jtfSoDienThoai = new JTextField()));
        boxContain3.add(Box.createHorizontalStrut(30));
        boxContain3.add(btnCheckThongTinKhachHang = new JButton("Check"));
        UIHelpers.set_Button_Blue_Style(btnCheckThongTinKhachHang);
        boxContain.add(boxContain3);
        boxContain.add(Box.createVerticalStrut(CommonConstants.VERTICAL_STRUT));

        Box boxContain4 = Box.createHorizontalBox();
        boxContain4.add(UIHelpers.create_Form_Label_JTextField("Họ tên", jtfHoTen = new JTextField()));
        boxContain4.add(Box.createHorizontalStrut(20));
        boxContain4.add(UIHelpers.create_Form_Label_JTextField("CCCD", jtfCCCD = new JTextField()));
        boxContain4.add(Box.createHorizontalStrut(20));
        boxContain4.add(UIHelpers.create_Form_Label_JTextField("Email", jtfEmail = new JTextField()));
        boxContain.add(boxContain4);
        boxContain.add(Box.createVerticalStrut(CommonConstants.VERTICAL_STRUT));

        Box boxContain5 = Box.createHorizontalBox();
        boxContain5.add(UIHelpers.create_Form_Label_JTextArea("Mô tả", jtaMoTa = new JTextArea()));
        boxContain.add(boxContain5);

        boxContain.add(Box.createVerticalStrut(CommonConstants.VERTICAL_STRUT));
        boxContain.add(UIHelpers.create_Title_Panel("Dịch vụ"));

//        Checkbox dich vụ
        btnThemDichVu = new JButton("Thêm dịch vụ");
        UIHelpers.set_Button_Blue_Style(btnThemDichVu);
        boxContain.add(btnThemDichVu);

        return boxContain;
    }








    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1920,1080);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(new ThemDonDatPhongPanel(new NhanVien()));
    }


}
