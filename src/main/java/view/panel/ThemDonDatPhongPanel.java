/*
    *MayHotel  day creative: 10/21/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here   
     */
package view.panel;
import bus.DonDatPhongBUS;
import bus.ThemDonDatPhongBUS;
import com.toedter.calendar.JDateChooser;
import constant.CommonConstants;
import custom.TextFieldCustom;
import dal.LoaiPhongDAL;
import dal.PhongDAL;
import entity.*;
import helper.UIHelpers;
import view.component.DichVuPanel;
import view.component.PhongDaThemVaoDonDatPhongPanel;
import view.dialog.ChonDichVuDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ThemDonDatPhongPanel extends JPanel implements ActionListener, PropertyChangeListener {
//Local variable
//  Cac biến component
    private JButton btnXoaRong,btnClose, btnChonPhong, btnCheckThongTinKhachHang, btnDatPhong, btnThemDichVu;
    private JDateChooser jdcCheckIn, jdcCheckout;
    private TextFieldCustom jtfTongThoiGianO, jtfSoDienThoai, jtfHoTen, jtfCCCD, jtfEmail;
    private JComboBox cboLoaiPhong, cboSoPhong, cboPhuongThucThanhToan;
    private JTextArea jtaMoTa;
    private JLabel lblTongSoPhong, lblTongThoiGianO, lblTongTien;
    JPanel mainRightPanel;
    JCheckBox chkTrangThaiDatCoc;
//  Biến bên lưu giữ tạm thời
    DonDatPhong donDatPhong;
    KhachHang khachHang;
    ArrayList<Phong> dsPhong = new ArrayList<>();
    ArrayList<ChiTiet_DonDatPhong_Phong> dsChiTietDDP_P = new ArrayList<>();
    ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> dsChiTietDDP_Phong_DV = new ArrayList<>();
    private boolean isSuaDichVu;

//  Biên container
    JPanel jpnHeader, jpnContainContent, jpnDichVu;
    Box boxContainDanhSachPhong;
    MenuPanel menuPanel;
    private double tongTien = 0;
    public void setSuaDichVu(boolean suaDichVu) {
        isSuaDichVu = suaDichVu;
    }

    public double getTongTien() {
        return tongTien;
    }

    public Box getBoxContainDanhSachPhong() {
        return boxContainDanhSachPhong;
    }

    public JDateChooser getJdcCheckIn() {
        return jdcCheckIn;
    }

    public JDateChooser getJdcCheckout() {
        return jdcCheckout;
    }

    public JComboBox getCboSoPhong() {
        return cboSoPhong;
    }

    public void setDonDatPhong(DonDatPhong donDatPhong) {
        this.donDatPhong = donDatPhong;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public TextFieldCustom getJtfSoDienThoai() {
        return jtfSoDienThoai;
    }

    public TextFieldCustom getJtfHoTen() {
        return jtfHoTen;
    }

    public TextFieldCustom getJtfCCCD() {
        return jtfCCCD;
    }

    public TextFieldCustom getJtfEmail() {
        return jtfEmail;
    }

    public JTextArea getJtaMoTa() {
        return jtaMoTa;
    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    public DonDatPhong getDonDatPhong() {
        return donDatPhong;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public ArrayList<ChiTiet_DonDatPhong_Phong> getDsChiTietDDP_P() {
        return dsChiTietDDP_P;
    }

    public ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> getDsChiTietDDP_Phong_DV() {
        return dsChiTietDDP_Phong_DV;
    }

    public ArrayList<Phong> getDsPhong() {
        return dsPhong;
    }

    public JButton getBtnChonPhong() {
        return btnChonPhong;
    }

    public JPanel getJpnDichVu() {
        return jpnDichVu;
    }

    public JComboBox getCboLoaiPhong() {
        return cboLoaiPhong;
    }

    public JComboBox getCboPhuongThucThanhToan() {
        return cboPhuongThucThanhToan;
    }

    public JCheckBox getChkTrangThaiDatCoc() {
        return chkTrangThaiDatCoc;
    }

    public ThemDonDatPhongPanel(MenuPanel menuPanel) {
        this.setLayout(new BorderLayout());
        this.menuPanel = menuPanel;
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
        btnXoaRong = UIHelpers.createButtonStyle("Xóa rỗng", CommonConstants.BUTTON_SIZE, Color.WHITE, CommonConstants.ORANGE);
        btnClose = UIHelpers.createButtonStyle("Đóng", CommonConstants.BUTTON_SIZE, Color.WHITE, CommonConstants.ORANGE);
        headerRight.add(btnXoaRong);
        headerRight.add(btnClose);
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
        btnXoaRong.addActionListener(this);
        btnDatPhong.addActionListener(this);
        btnCheckThongTinKhachHang.addActionListener(this);
        btnChonPhong.addActionListener(this);
        cboLoaiPhong.addActionListener(this);
        jdcCheckout.addPropertyChangeListener("date", this);
        jdcCheckIn.addPropertyChangeListener("date", this);
        btnThemDichVu.addActionListener(this);
        // lấy tất cả phòng tuwf loại phòng ban đầu
        capNhatPhongKhiChonLoaiPhong();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o.equals(btnClose)) {
            this.setVisible(false);
            menuPanel.getCardLayout().show(menuPanel.getJpnContain(), "Đơn đặt phòng");
        }
        else if(o.equals(btnXoaRong)) {
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
        else if(o.equals(btnThemDichVu)) {
            xuLySKThemDichVu();
        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object o = evt.getSource();
        if(o.equals(jdcCheckout)) {
            xuLySuKienChonNgayCheckOut();
        }
        else if(o.equals(jdcCheckIn)) {
            xuLySuKienChonNgayCheckIn();
        }
    }
    private void xuLySKThemDichVu() {
        if(!checkThongTinTruocKhiThemDV()) return;
        // Mở Dialog chọn dịch vụ
        ChonDichVuDialog chonDichVuDialog = new ChonDichVuDialog(menuPanel.getMainGUI(), this);
        chonDichVuDialog.setVisible(true);
        if(!chonDichVuDialog.isChonDichVu()) return;
        updateJPanelDichVuTheoPhong(new PhongDAL().getPhongTheoTenPhong(cboSoPhong.getSelectedItem().toString()));
    }

    public void updateJPanelDichVuTheoPhong(Phong phong) {
        jpnDichVu.removeAll();
        for(ChiTiet_DonDatPhong_Phong_DichVu ct : dsChiTietDDP_Phong_DV) {
            if(ct.getPhong().equals(phong)) {
                jpnDichVu.add(new DichVuPanel(this, ct));
            }
        }
    }
    public void disableUI() {
        // Vô hiệu hóa tất cả các thành phần
        btnDatPhong.setEnabled(false);
        btnCheckThongTinKhachHang.setEnabled(false);
        cboLoaiPhong.setEnabled(false);
        jdcCheckout.setEnabled(false);
        jdcCheckIn.setEnabled(false);
        cboSoPhong.setEnabled(false);
        jtfSoDienThoai.setEnabled(false);
        jtfHoTen.setEnabled(false);
        jtfCCCD.setEnabled(false);
        jtfEmail.setEnabled(false);
        jtaMoTa.setEnabled(false);
        // Giữ nguyên các nút "Thêm Dịch Vụ" và "Chọn Phòng"
        btnThemDichVu.setEnabled(true);
        btnChonPhong.setEnabled(true);
    }
    public void enabledUI() {
        // Vô hiệu hóa tất cả các thành phần
        btnDatPhong.setEnabled(true);
        btnCheckThongTinKhachHang.setEnabled(true);
        cboLoaiPhong.setEnabled(true);
        jdcCheckout.setEnabled(true);
        jdcCheckIn.setEnabled(true);
        cboSoPhong.setEnabled(true);
        jtfSoDienThoai.setEnabled(true);
        jtfHoTen.setEnabled(true);
        jtfCCCD.setEnabled(true);
        jtfEmail.setEnabled(true);
        jtaMoTa.setEnabled(true);
        // Giữ nguyên các nút "Thêm Dịch Vụ" và "Chọn Phòng"
        btnThemDichVu.setEnabled(true);
        btnChonPhong.setEnabled(true);
    }

    private boolean checkThongTinTruocKhiThemDV() {
        // Lấy ngày check-in và check-out
        Date checkInDate = jdcCheckIn.getDate();
        Date checkOutDate = jdcCheckout.getDate();
        // Lấy tên phòng
        String tenPhong = (cboSoPhong.getSelectedItem() != null) ? cboSoPhong.getSelectedItem().toString() : "";
        // Kiểm tra phòng được chọn
        if (tenPhong.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có phòng nào được chọn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Kiểm tra ngày check-in và check-out
        if (checkInDate == null || checkOutDate == null) {
            JOptionPane.showMessageDialog(this, "Hãy điền đầy đủ ngày check-in và check-out.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Lấy thông tin phòng từ database
        Phong phong = new PhongDAL().getPhongTheoTenPhong(tenPhong);
        if (phong == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin phòng trong cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void xuLySKChonPhong() {
        ThemDonDatPhongBUS themDonDatPhongBUS = new ThemDonDatPhongBUS();

        // kiểm tra xem có phải là đang sửa dịch vụ không
        // nếu sửa thì không thực hiện hành động thêm phòng
        if(!isSuaDichVu) {

            // kiểm tra xem đơn có tồn tại chưa
            // nếu chưa thì tạo đơn, tạo khách hàng
            // thêm phòng vào danh sách
            // cập nhật giao diện
            // tạo chi tiết đơn
            if(donDatPhong == null) {

                // lấy thông tin từ giao diện
                LocalDateTime checkin = themDonDatPhongBUS.convertDateToLocalDateTime(jdcCheckIn.getDate(), 14, 00);
                LocalDateTime checkout = themDonDatPhongBUS.convertDateToLocalDateTime(jdcCheckout.getDate(), 12, 00);
                String sdt = jtfSoDienThoai.getText();
                String hoTen = themDonDatPhongBUS.formatHoTen(jtfHoTen.getText());
                String cccd = jtfCCCD.getText();
                String email = jtfEmail.getText();
                String moTa = jtaMoTa.getText();
                String phuongThucTT = cboPhuongThucThanhToan.getSelectedItem().toString();
                boolean isDatCoc = chkTrangThaiDatCoc.isSelected();

                // tạo đơn
                this.donDatPhong = themDonDatPhongBUS.taoDonDatPhong(this.menuPanel.getNhanVienDangTruc(), sdt, hoTen, email, cccd, this.tongTien, moTa, phuongThucTT, isDatCoc, checkin, checkout);

                // tạo khách hàng
                this.khachHang = donDatPhong.getKhachHang();
            }
            // lấy ra thông tin phòng
            String tenPhong = cboSoPhong.getSelectedItem().toString();
            Phong p = new PhongDAL().getPhongTheoTenPhong(tenPhong);

            // kiểm tra nếu chi tiết chưa được tạo lúc chọn dịch vụ thì tạo
            // tạo chi tiết đơn đặt phòng phòng
            if (!dsPhong.contains(p)) { // kiểm tra phòng đó có nằm trong danh sách phòng

                // thêm chi tiết
                dsChiTietDDP_P.add(themDonDatPhongBUS.taoCTDDPP(donDatPhong, p, donDatPhong.getNgayNhanPhong(), donDatPhong.getNgayTraPhong()));

                // thêm phòng vào danh sách
                dsPhong.add(p);
            }


            // cập nhật giao diện
            cboSoPhong.removeItem(tenPhong);
            updateDanhSachPhong();
            jpnDichVu.removeAll();

        }
        this.setSuaDichVu(false);
        updateTongSoPhong();
        updateTongTGO();
        updateTongTien();
    }
    public void updateTongTien() {
        if(dsPhong.isEmpty()) {
            lblTongTien.setText("0");
            return;
        }
        double tongTien = 0;
        PhongDAL phongDAL = new PhongDAL();
        double donGia = 0.0;
        int tongTGO = Integer.parseInt(jtfTongThoiGianO.getText());
        for(Phong p : dsPhong) {
             donGia = phongDAL.getLoaiPhongTheoMaPhong(p.getMaPhong()).getDonGia();
            tongTien += donGia*tongTGO;
        }
        this.tongTien = tongTien;
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String tongTienFormat = formatter.format(tongTien);
        lblTongTien.setText("Tổng tiền: " + String.valueOf(tongTienFormat));
    }
    public void updateTongSoPhong() {
        lblTongSoPhong.setText(String.valueOf(dsPhong.size()));
    }
    public void updateTongTGO() {
        lblTongThoiGianO.setText(jtfTongThoiGianO.getText());
    }
    public void updateDanhSachPhong() {
        if(dsPhong.isEmpty()) return;
        boxContainDanhSachPhong.removeAll();
        for(Phong p : dsPhong) {
            JPanel jpnPhong = new PhongDaThemVaoDonDatPhongPanel(this, p);
            boxContainDanhSachPhong.add(jpnPhong);
        }
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
// Tạm thời loại bỏ PropertyChangeListener
        jdcCheckIn.removePropertyChangeListener("date", this);
        jdcCheckout.removePropertyChangeListener("date", this);

// Thực hiện thay đổi giá trị ngày
        jdcCheckIn.setDate(null);
        jdcCheckout.setDate(null);

// Thêm lại PropertyChangeListener
        jdcCheckIn.addPropertyChangeListener("date", this);
        jdcCheckout.addPropertyChangeListener("date", this);

        boxContainDanhSachPhong.removeAll();
        boxContainDanhSachPhong.repaint();
        boxContainDanhSachPhong.revalidate();
        jtaMoTa.setText("");
        dsPhong.clear();
        dsChiTietDDP_P.clear();
        dsChiTietDDP_Phong_DV.clear();
        donDatPhong = null;
        khachHang = null;
        cboPhuongThucThanhToan.setSelectedIndex(0);
        chkTrangThaiDatCoc.setEnabled(false);
        jpnDichVu.removeAll();
        boxContainDanhSachPhong.removeAll();
        lblTongThoiGianO.setText("0");
        lblTongSoPhong.setText("0");
        lblTongTien.setText("Tổng tiền:");
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
        boolean isSuccess = false;

        // yêu cầu xác nhận đặt phòng
        int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đặt phòng? Hãy check lại kĩ!",
                "Xác nhận", JOptionPane.YES_NO_OPTION);

        // nếu không xác nhận thì trở về
        if (option == JOptionPane.NO_OPTION) {
            return;
        }

        // kiểm tra xem có phòng nào được chọn chưa
        if(dsPhong.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Chưa chọn phòng nào!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // nếu đã có phòng thì cập nhật lại đơn và tạo phòng
        isSuccess = themDonDatPhongBUS.datPhong(cboPhuongThucThanhToan.getSelectedItem().toString() ,chkTrangThaiDatCoc.isSelected(), this.tongTien, donDatPhong, khachHang, dsPhong, dsChiTietDDP_P, dsChiTietDDP_Phong_DV);

        // thông báo người dùng
        if (isSuccess) {
            JOptionPane.showMessageDialog(null, "Đặt phòng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            resetFields();
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
        String tenLoaiPhong = loaiPhongDAL.getMaLoaiPhongTheoTen((String) cboLoaiPhong.getSelectedItem());
        if (tenLoaiPhong != null) {
            ArrayList<Phong> danhSachPhongCuaLoaiPhong = phongDAL.getPhongTheoLoaiPhongChuaDuocDat(tenLoaiPhong);
            for (Phong p : danhSachPhongCuaLoaiPhong) {
                if(!dsPhong.contains(p))
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
        containCalculate.add(lblTongSoPhong = new JLabel("0"));
        containCalculate.add(new JLabel("Tổng thời gian ở"));
        containCalculate.add(new JPanel());
        containCalculate.add(lblTongThoiGianO = new JLabel("0"));
        containCalculate.add(lblTongTien = new JLabel("Tổng tiền: 0đ"));
        containCalculate.add(new JPanel());
        containCalculate.add(UIHelpers.create_Form_Label_Checkbox("Đặt cọc",chkTrangThaiDatCoc = new JCheckBox()));
        boxContainTamTinh.add(containCalculate);
        boxContainTamTinh.add(UIHelpers.create_Title_Panel("Thanh toán"));
        boxContainTamTinh.add(new JPanel().add(UIHelpers.create_Form_Label_JComboBox("Phương thức thanh toán", cboPhuongThucThanhToan = new JComboBox())));
        cboPhuongThucThanhToan.addItem("--Chọn phương thức thanh toán--");
        cboPhuongThucThanhToan.addItem("Tiền mặt");
        cboPhuongThucThanhToan.addItem("Thẻ tín dụng - Visa");
        cboPhuongThucThanhToan.addItem("Thẻ tín dụng - MasterCard");
        cboPhuongThucThanhToan.addItem("Thẻ tín dụng - American Express");
        cboPhuongThucThanhToan.addItem("Thẻ ghi nợ - Visa Debit");
        cboPhuongThucThanhToan.addItem("Thẻ ghi nợ - MasterCard Debit");
        cboPhuongThucThanhToan.addItem("Chuyển khoản ngân hàng nội địa");
        cboPhuongThucThanhToan.addItem("Chuyển khoản ngân hàng quốc tế");
        cboPhuongThucThanhToan.addItem("Ví điện tử - Momo");
        cboPhuongThucThanhToan.addItem("Ví điện tử - ZaloPay");
        cboPhuongThucThanhToan.addItem("Ví điện tử - ViettelPay");
        cboPhuongThucThanhToan.addItem("Thanh toán qua QR code");
        cboPhuongThucThanhToan.addItem("Thanh toán trả sau");

        JPanel containBtnDatPhong = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnDatPhong = UIHelpers.createButtonStyle("Đặt phòng", CommonConstants.BUTTON_SIZE, Color.WHITE, CommonConstants.ORANGE);
        containBtnDatPhong.add(btnDatPhong);
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
        boxContain1.add(UIHelpers.create_Form_Label_JTextField("Tổng thời gian ở", jtfTongThoiGianO = new TextFieldCustom("",2)));
        jtfTongThoiGianO.setEditable(false);
        boxContain.add(boxContain1);

        boxContain.add(Box.createVerticalStrut(CommonConstants.VERTICAL_STRUT));
        boxContain.add(UIHelpers.create_Title_Panel("Thông tin khách hàng"));
        Box boxContain3 = Box.createHorizontalBox();
        boxContain3.add(UIHelpers.create_Form_Label_JTextField("Số điện thoại", jtfSoDienThoai = new TextFieldCustom("Nhập số điện thoại", 11)));
        boxContain3.add(Box.createHorizontalStrut(30));
        btnCheckThongTinKhachHang = UIHelpers.createButtonStyle("Check", CommonConstants.BUTTON_SIZE, Color.WHITE, Color.BLUE);
        boxContain3.add(btnCheckThongTinKhachHang);
        boxContain.add(boxContain3);
        boxContain.add(Box.createVerticalStrut(CommonConstants.VERTICAL_STRUT));
        Box boxContain4 = Box.createHorizontalBox();
        boxContain4.add(UIHelpers.create_Form_Label_JTextField("Họ tên", jtfHoTen = new TextFieldCustom("Nhập họ tên", 50)));
        boxContain4.add(Box.createHorizontalStrut(20));
        boxContain4.add(UIHelpers.create_Form_Label_JTextField("CCCD", jtfCCCD = new TextFieldCustom("Nhập số CCCD", 12)));
        boxContain4.add(Box.createHorizontalStrut(20));
        boxContain4.add(UIHelpers.create_Form_Label_JTextField("Email", jtfEmail = new TextFieldCustom("Nhập email", 50)));
        boxContain.add(boxContain4);
        boxContain.add(Box.createVerticalStrut(CommonConstants.VERTICAL_STRUT));
        Box boxContain5 = Box.createHorizontalBox();
        boxContain5.add(UIHelpers.create_Form_Label_JTextArea("Mô tả đơn đặt phòng", jtaMoTa = new JTextArea()));
        boxContain.add(boxContain5);
        boxContain.add(Box.createVerticalStrut(CommonConstants.VERTICAL_STRUT));
        boxContain.add(UIHelpers.create_Title_Panel("Chi tiết phòng"));
        Box boxContain2 = Box.createHorizontalBox();
        boxContain2.add(UIHelpers.create_Form_Label_JComboBox("Loại phòng", cboLoaiPhong = new JComboBox()));
        boxContain2.add(Box.createHorizontalStrut(20));
        boxContain2.add(UIHelpers.create_Form_Label_JComboBox("Số phòng", cboSoPhong = new JComboBox()));
        boxContain2.add(Box.createHorizontalStrut(20));
        boxContain.add(boxContain2);
        boxContain.add(Box.createVerticalStrut(CommonConstants.VERTICAL_STRUT));
        boxContain.add(UIHelpers.create_Title_Panel("Dịch vụ"));
//        Checkbox dich vụ
        btnThemDichVu = UIHelpers.createButtonStyle("Thêm dịch vụ", CommonConstants.BUTTON_SIZE, Color.WHITE, Color.BLUE);
        btnChonPhong = UIHelpers.createButtonStyle("Chọn phòng", CommonConstants.BUTTON_SIZE, Color.WHITE, Color.BLUE);
        JPanel jpnSouth = new JPanel(new BorderLayout()); // container của south
        Box boxContainDichVu = Box.createHorizontalBox();
        boxContainDichVu.add(Box.createHorizontalStrut(10));
        boxContainDichVu.add(btnThemDichVu);
        boxContainDichVu.add(Box.createHorizontalStrut(20));
        // panel chứa DichVu
        jpnDichVu = new JPanel();
        boxContainDichVu.add(new JScrollPane(jpnDichVu));
        boxContainDichVu.add(Box.createHorizontalStrut(10));
        jpnSouth.add(boxContainDichVu, BorderLayout.CENTER);
        Box boxChonPhong = Box.createVerticalBox();
        boxChonPhong.add(Box.createVerticalGlue());
        boxChonPhong.add(btnChonPhong);
        boxChonPhong.add(Box.createVerticalGlue());
        jpnSouth.add(boxChonPhong, BorderLayout.EAST);
        boxContain.add(jpnSouth);
        return boxContain;
    }
    // Phương thức cập nhật tổng thời gian ở chung
    private void updateTongThoiGianO() {
        Date checkInDate = jdcCheckIn.getDate();
        Date checkOutDate = jdcCheckout.getDate();

        if (checkInDate == null || checkOutDate == null) {
            jtfTongThoiGianO.setText("");
            return;
        }

        // Chuyển đổi Date thành LocalDate để tính toán
        LocalDate tgCheckin = checkInDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate tgCheckout = checkOutDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Tính tổng số ngày ở
        long tongThoiGianO = ChronoUnit.DAYS.between(tgCheckin, tgCheckout);
        if (tongThoiGianO <= 0) {
            JOptionPane.showMessageDialog(null,
                    "Ngày check-out phải sau ngày check-in!",
                    "Lỗi",
                    JOptionPane.WARNING_MESSAGE);
            jtfTongThoiGianO.setText("");
            return;
        }
        updateTongTGO();
        jtfTongThoiGianO.setText(String.valueOf(tongThoiGianO));
    }

    // Phương thức xử lý sự kiện khi chọn ngày check-in
    private void xuLySuKienChonNgayCheckIn() {
        Date checkInDate = jdcCheckIn.getDate();

        if (checkInDate == null) {
            JOptionPane.showMessageDialog(null,
                    "Vui lòng nhập ngày check-in",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Đặt minSelectableDate cho ngày check-out
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkInDate);
        calendar.add(Calendar.DATE, 1); // Ngày check-out tối thiểu là ngày sau check-in
        jdcCheckout.setMinSelectableDate(calendar.getTime());

        // Cập nhật tổng thời gian ở
        updateTongThoiGianO();
    }

    // Phương thức xử lý sự kiện khi chọn ngày check-out
    private void xuLySuKienChonNgayCheckOut() {
        Date checkOutDate = jdcCheckout.getDate();

        if (checkOutDate == null) {
            JOptionPane.showMessageDialog(null,
                    "Vui lòng nhập ngày check-out",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Đặt maxSelectableDate cho ngày check-in
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkOutDate);
        calendar.add(Calendar.DATE, -1); // Ngày check-in tối đa là ngày trước check-out
        jdcCheckIn.setMaxSelectableDate(calendar.getTime());

        // Cập nhật tổng thời gian ở
        updateTongThoiGianO();
    }

}
