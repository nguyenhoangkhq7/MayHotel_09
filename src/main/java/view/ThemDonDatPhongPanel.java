/*
    *MayHotel  day creative: 10/21/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here   
     */


package view;

import bus.DonDatPhongBUS;
import com.toedter.calendar.JDateChooser;
import constant.CommonConstants;
import dal.*;
import entity.*;
import custom.UIHelpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

public class ThemDonDatPhongPanel extends JPanel implements ActionListener {
// Local variable
    private JButton btnReset,btnClose, btnChonPhong, btnCheckThongTinKhachHang, btnDatPhong;
    private JDateChooser jdcCheckIn, jdcCheckout;
    private JTextField jtfTongThoiGianO, jtfSoDienThoai, jtfHoTen, jtfCCCD, jtfEmail;
    private JComboBox cboLoaiPhong, cboSoPhong, cboPhuongThucThanhToan;
    private JTextArea jtaMoTa;
    private JLabel lblTongSoPhong, lblTongThoiGianO, lblTongTien;
    ArrayList<JPanel> listDanhSachPhongPanel = new ArrayList<>();
    JPanel mainRightPanel;
    JCheckBox chkTrangThaiDatCoc;
    ArrayList<Phong> dsPhong = new ArrayList<>();
    KhachHangDAL khachHangDAL = new KhachHangDAL();
    DonDatPhongDAL donDatPhongDAL = new DonDatPhongDAL();
    PhongDAL phongDAL = new PhongDAL();
    LoaiPhongDAL loaiPhongDAL = new LoaiPhongDAL();

    public ThemDonDatPhongPanel() {
        this.setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        add(showHeader(), BorderLayout.NORTH);
        showContent();

        themLoaiPhongCbo();

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
                    LocalDate tgCheckin = checkInDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate tgCheckout = checkOutDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    // Tính số ngày giữa hai ngày
                    long tongThoiGianO = ChronoUnit.DAYS.between(tgCheckin, tgCheckout);

                    // Cập nhật giá trị vào trường văn bản
                    jtfTongThoiGianO.setText(String.valueOf(tongThoiGianO));
                    System.out.println("Tổng thời gian ở: " + tongThoiGianO + " ngày");
                } else {
                    // Nếu một trong hai ngày là null, có thể đặt tổng thời gian ở về 0 hoặc để trống
                    jtfTongThoiGianO.setText("");
                    System.out.println("Vui lòng chọn cả hai ngày.");
                }
            }
        });
        // lấy tất cả phòng tuwf loại phòng ban đầu
        updateSoPhong();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o.equals(btnClose)) {
            System.out.println("HEHEE");
            this.setVisible(false);
        }
        else if(o.equals(btnReset)) {

        }
        else if (o.equals(btnDatPhong)) {
            // 1. Thu thập thông tin từ các trường nhập liệu
            LocalDate tgCheckin = jdcCheckIn.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate tgCheckout = jdcCheckout.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long tongThoiGianO = ChronoUnit.DAYS.between(tgCheckin, tgCheckout);
            jtfTongThoiGianO.setText(String.valueOf(tongThoiGianO));

            String loaiPhong = cboLoaiPhong.getSelectedItem().toString();
            String soPhong = cboSoPhong.getSelectedItem().toString();

            String soDienThoai = jtfSoDienThoai.getText();
            String hoTen = jtfHoTen.getText();
            String cccd = jtfCCCD.getText();
            String email = jtfEmail.getText();
            String moTa = jtaMoTa.getText();
            String phuongThucThanhToan = cboPhuongThucThanhToan.getSelectedItem().toString();
            boolean trangThaiDatCoc = chkTrangThaiDatCoc.isSelected();

            // 2. Kiểm tra thông tin
            if (soDienThoai.isEmpty() || hoTen.isEmpty() || cccd.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin khách hàng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 3. Tạo đối tượng đơn đặt phòng
            DonDatPhong ddp = new DonDatPhong();
            ddp.setMaDon(DonDatPhongBUS.generateOrderCode()); // Tạo mã đơn mới
            ddp.setNgayTao(LocalDate.now()); // Ngày tạo đơn
            ddp.setTrangThaiDonDatPhong("Đặt trước"); // Cần xác định nếu có
            ddp.setPhuongThucThanhToan(phuongThucThanhToan);
            ddp.setTrangThaiDatCoc(trangThaiDatCoc); // Hoặc true nếu có đặt cọc
            ddp.setNhanVien(App.nhanVienDangTruc);
            if(khachHangDAL.checkKhachHangTonTaiTheoSDT(soDienThoai)) {
                ddp.setKhachHang(khachHangDAL.getKhachHangTheoSoDienThoai(soDienThoai)); // Tạo đối tượng khách hàng
            } else {
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKH(soDienThoai); // Giả sử mã khách hàng là số điện thoại
                khachHang.setHoTen(hoTen);
                khachHang.setSoDienThoai(soDienThoai);
                khachHang.setSoCanCuoc(cccd);
                khachHang.setEmail(email);
                khachHang.setTienTichLuy(0); // Thiết lập tiền tích lũy ban đầu
                khachHang.setLoaiKhachHang(LoaiKhachHang.NGUOIMOI); // Hoặc giá trị phù hợp
                ddp.setKhachHang(khachHang);
                khachHangDAL.themKhachHang(khachHang);
            }
            double tongTien = 0;
            for(Phong p : dsPhong) {
                tongTien += p.getLoaiPhong().getDonGia() * tongThoiGianO;
            }
            ddp.setTongTien(tongTien); // Tạm thời gán 0, cần tính toán tổng tiền
            ddp.setMoTa(moTa);
            ddp.setNgayNhanPhong(tgCheckin);
            ddp.setNgayTraPhong(tgCheckout);

            // 4. Lưu đơn đặt phòng vào cơ sở dữ liệu
            boolean isSuccess = donDatPhongDAL.themDonDatPhong(ddp);

            // 5. Cập nhật giao diện
            if (isSuccess) {
                JOptionPane.showMessageDialog(null, "Đặt phòng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                resetFields(); // Hàm để làm mới các trường nhập liệu
            } else {
                JOptionPane.showMessageDialog(null, "Đặt phòng thất bại! Vui lòng thử lại.", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }

        else if(o.equals(btnCheckThongTinKhachHang)) {
            String soDienThoai = jtfSoDienThoai.getText();
            KhachHang khachHang = khachHangDAL.getKhachHangTheoSoDienThoai(soDienThoai);
            if(khachHang!=null) {
                jtfHoTen.setText(khachHang.getHoTen());
                jtfCCCD.setText(khachHang.getSoCanCuoc());
                jtfEmail.setText(khachHang.getEmail());
            }
        }
        else if(o.equals(btnChonPhong)) {
            String loaiPhong = cboLoaiPhong.getSelectedItem().toString();
            String soPhong = cboSoPhong.getSelectedItem().toString();

            dsPhong.add(phongDAL.getPhongTheoTenPhong(soPhong)); // lấy phòng bỏ vào danh sách

            // Thêm phòng mới vào danh sách
            listDanhSachPhongPanel.add(UIHelpers.create_Phong_Da_Them(soPhong, loaiPhong));

            // Xóa nội dung cũ trong mainRightPanel
            mainRightPanel.removeAll();

            // Hiển thị danh sách phòng đã thêm
            mainRightPanel.add(showDanhSachPhong(listDanhSachPhongPanel));

            // Thêm phần tạm tính
            mainRightPanel.add(showTamTinh());

            // Cập nhật giao diện
            mainRightPanel.revalidate();
            mainRightPanel.repaint();
        }
        else if (o.equals(cboLoaiPhong)) {
            updateSoPhong();
        }
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
        listDanhSachPhongPanel.clear();
        mainRightPanel.removeAll();
        mainRightPanel.add(showDanhSachPhong(listDanhSachPhongPanel));
        mainRightPanel.add(showTamTinh());
        mainRightPanel.revalidate();
        mainRightPanel.repaint();
    }


    public void themLoaiPhongCbo() {
        ArrayList<LoaiPhong> dsLoaiPhong = loaiPhongDAL.getAllLoaiPhong();
        for(LoaiPhong lp : dsLoaiPhong) {
            cboLoaiPhong.addItem(lp.getTenLoaiPhong());
        }
        cboLoaiPhong.setSelectedItem(0);
    }
    private void updateSoPhong() {
        // Xóa tất cả các mục hiện có trong cboSoPhong
        cboSoPhong.removeAllItems();

        // Lấy loại phòng được chọn
        String loaiPhong = loaiPhongDAL.getMaLoaiPhongTheoTen((String) cboLoaiPhong.getSelectedItem());
        // Kiểm tra nếu loại phòng không null
        if (loaiPhong != null) {
            // Lấy danh sách phòng dựa trên loại phòng được chọn
            ArrayList<Phong> dsPhong = phongDAL.getAllPhongByMaLoaiPhong(loaiPhong);
            // Thêm các phòng vào cboSoPhong
            for (Phong p : dsPhong) {
                cboSoPhong.addItem(p.getTenPhong());
            }
        }
    }

    public void showContent() {

        JPanel jpnMainContent = new JPanel(new BorderLayout());

        // Tạo panel trái và phải
        JPanel jpnContentLeft = new JPanel(new BorderLayout());
        jpnContentLeft.setBackground(Color.WHITE);
        JPanel jpnContentRight = new JPanel(new BorderLayout());
        jpnContentRight.setBackground(Color.WHITE);

        jpnMainContent.add(jpnContentLeft,BorderLayout.CENTER);
        jpnMainContent.add(jpnContentRight, BorderLayout.EAST);


        // Thêm nội dung vào các panel bên trái và phải
        showLeftContent(jpnContentLeft);
        showRightContent(jpnContentRight);


        // Thêm splitPane vào frame
        this.add(jpnMainContent, BorderLayout.CENTER);
    }

    public void showRightContent(JPanel container) {
        mainRightPanel = new JPanel(new GridLayout(2,1));
        container.add(mainRightPanel);

        mainRightPanel.add(showDanhSachPhong(listDanhSachPhongPanel));
        mainRightPanel.add(showTamTinh());
    }
    public Box showDanhSachPhong(ArrayList<JPanel> listDanhSachPhongPanel) {
        Box boxContainDanhSachPhong = Box.createVerticalBox();
        boxContainDanhSachPhong.add(UIHelpers.create_Title_Panel("Danh sách phòng đã thêm"));

        JPanel panelDanhSachPhong = new JPanel();
        panelDanhSachPhong.setLayout(new BoxLayout(panelDanhSachPhong, BoxLayout.Y_AXIS));

        for(JPanel jpn : listDanhSachPhongPanel) {
            panelDanhSachPhong.add(jpn);
        }

        JScrollPane scrollDanhSachPhong = new JScrollPane(panelDanhSachPhong);
        boxContainDanhSachPhong.add(scrollDanhSachPhong);

        return boxContainDanhSachPhong;
    }


    public Box showTamTinh() {
        Box boxContain = Box.createVerticalBox();
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
        containCalculate.add(UIHelpers.create_Form_Label_Checkbox("Đặt cọc",chkTrangThaiDatCoc = new JCheckBox()));
        boxContain.add(containCalculate);

        boxContain.add(UIHelpers.create_Title_Panel("Thanh toán"));
        boxContain.add(new JPanel().add(UIHelpers.create_Form_Label_JComboBox("Phương thức thanh toán", cboPhuongThucThanhToan = new JComboBox())));
        cboPhuongThucThanhToan.addItem("Tiền mặt");
        cboPhuongThucThanhToan.addItem("Visa");
        cboPhuongThucThanhToan.addItem("Chuyển khoản ngân hàng");


        JPanel containBtnDatPhong = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        containBtnDatPhong.add(btnDatPhong = new JButton("Đặt phòng"));
        UIHelpers.set_Orange_Blue_Style(btnDatPhong);

        boxContain.add(containBtnDatPhong);

        return boxContain;
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
        JPanel jpnContainDichVu = new JPanel(new GridLayout(0,5));
//        jpnContainDichVu.add(UIHelpers.create_Form_Label_Checkbox("Dịch vụ", new JCheckBox()));
        ArrayList<DichVu> dsDichVu = new DichVuDAL().getAllDichVu();
        for(DichVu dv : dsDichVu) {
            jpnContainDichVu.add(UIHelpers.create_Form_Label_Checkbox(dv.getTenDichVu(), new JCheckBox()));
        }

        boxContain.add(jpnContainDichVu);

    }



    public JPanel showHeader() {
        JPanel jpnHeader = new JPanel(new GridLayout(1,2));
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

        return jpnHeader;
    }




    public static void main(String[] args) {
        new ThemDonDatPhongPanel();
    }


}
