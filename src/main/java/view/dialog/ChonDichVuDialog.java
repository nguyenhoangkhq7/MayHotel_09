package view.dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import bus.ThemDonDatPhongBUS;
import dal.DichVuDAL;
import dal.PhongDAL;
import entity.*;
import view.panel.ThemDonDatPhongPanel;

public class ChonDichVuDialog extends JDialog implements ActionListener{

    private int soLuongDat;
    private LocalDateTime ngayGioDat;
    private String moTaDichVu, tenDichVu;
    private JLabel lblMaPhong, lblTenPhong, lblDonVi;
    private JComboBox<String> cboDichVu, cboSoLuong, cboNgaySuDungDV, cboGioSuDungDV;
    private JTextArea moTaArea;
    private JButton btnThemService;
    private ArrayList<DichVu> dsDichVu;
    Phong phong;
    private boolean isChonDichVu = false;
    ThemDonDatPhongPanel themDonDatPhongPanel;

    public String getTenDichVu() {
        return tenDichVu;
    }

    public int getSoLuongDat() {
        return soLuongDat;
    }

    public LocalDateTime getNgayGioDat() {
        return ngayGioDat;
    }

    public String getMoTaDichVu() {
        return moTaDichVu;
    }

    public boolean isChonDichVu() {
        return isChonDichVu;
    }

    private Date getNgayDen() {
        return themDonDatPhongPanel.getJdcCheckIn().getDate();
    }
    private Date getNgayDi() {
        return themDonDatPhongPanel.getJdcCheckout().getDate();
    }
    public ChonDichVuDialog(JFrame parent, ThemDonDatPhongPanel themDonDatPhongPanel) {
        super(parent, "Chọn dịch vụ cho phòng", true);
        this.themDonDatPhongPanel = themDonDatPhongPanel;
        dsDichVu = new DichVuDAL().getAllDichVu();
        phong = new PhongDAL().getPhongTheoTenPhong(themDonDatPhongPanel.getCboSoPhong().getSelectedItem().toString());
        initComponents(phong);
        setupLayout();
        cboDichVu.addActionListener(this);
        cboNgaySuDungDV.addActionListener(this);
        btnThemService.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o.equals(cboDichVu)) {
            xuLySKChonDichVu();
        }
        else if(o.equals(cboNgaySuDungDV)) {
            updateAvailableHours();
        }
        else if(o.equals(btnThemService)) {
            xuLySKThemDichVu();
        }
    }
    private void xuLySKChonDichVu() {
        int selectedIndex = cboDichVu.getSelectedIndex();
        if (selectedIndex == 0) {
            resetFields();
        }
        if (selectedIndex != -1 && selectedIndex != 0) {
            selectedIndex -= 1;
            DichVu selectedService = dsDichVu.get(selectedIndex);
//          Cập nhật lại chi tiết các dịch vụ
            cboSoLuong.setEnabled(true);
            cboNgaySuDungDV.setEnabled(true);
            cboGioSuDungDV.setEnabled(true);
            updateServiceDetails(selectedService);
            updateAddServiceButtonState();
        }
    }
    private void xuLySKThemDichVu() {
        try {
            // Kiểm tra dịch vụ được chọn
            DichVuDAL dichVuDAL = new DichVuDAL();
            DichVu dichVuDangChon = dichVuDAL.getDichVuTheoTen(cboDichVu.getSelectedItem().toString());
            if(!checkDichVuDangDuocChon(dichVuDangChon)) return;
            getThongTinTuFields();
            if(!themDonDatDichVu()) return; // thêm đơn đặt phòng và khách hàng
            // Hiển thị thông tin xác nhận
            JOptionPane.showMessageDialog(this,
                    "Dịch vụ đã được thêm thành công!" +
                            "\nTên dịch vụ: " + tenDichVu +
                            "\nSố lượng: " + soLuongDat +
                            "\nThời gian sử dụng: " + ngayGioDat +
                            "\nMô tả: " + moTaDichVu,
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            isChonDichVu = true;
            // Đóng dialog
            dispose();

        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi khi nhập thông tin không hợp lệ
            JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    private boolean checkDichVuDangDuocChon(DichVu selectedService) {
        if (selectedService == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dịch vụ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra xem dịch vụ có tồn tại trong danh sách dịch vụ
        if (!dsDichVu.contains(selectedService)) {
            JOptionPane.showMessageDialog(this, "Dịch vụ không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    private void getThongTinTuFields() throws ParseException {
        this.tenDichVu = (String) cboDichVu.getSelectedItem();
        this.soLuongDat = Integer.parseInt((String) cboSoLuong.getSelectedItem());
        String selectedDay = (String) cboNgaySuDungDV.getSelectedItem();
        String selectedHour = (String) cboGioSuDungDV.getSelectedItem();
        this.moTaDichVu = moTaArea.getText();
        // Kết hợp ngày và giờ để tạo LocalDateTime cho `ngayGioDat`
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date dateTime = dateFormat.parse(selectedDay + " " + selectedHour);
        this.ngayGioDat = dateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    private boolean themDonDatDichVu() { // bao gồm thêm đơn đặt phòng và khách hàng
        // lấy dữ liệu từ giao diện
        ThemDonDatPhongBUS themDonDatPhongBUS = new ThemDonDatPhongBUS();
        LocalDateTime checkin = themDonDatPhongBUS.convertDateToLocalDateTime(themDonDatPhongPanel.getJdcCheckIn().getDate(), 14, 00);
        LocalDateTime checkout = themDonDatPhongBUS.convertDateToLocalDateTime(themDonDatPhongPanel.getJdcCheckout().getDate(), 12, 00);
        String sdt = themDonDatPhongPanel.getJtfSoDienThoai().getText();
        String hoTen = themDonDatPhongBUS.formatHoTen(themDonDatPhongPanel.getJtfHoTen().getText());
        String cccd = themDonDatPhongPanel.getJtfCCCD().getText();
        String email = themDonDatPhongPanel.getJtfEmail().getText();
        String moTa = themDonDatPhongPanel.getJtaMoTa().getText();
        String pttt = themDonDatPhongPanel.getCboPhuongThucThanhToan().getSelectedItem().toString();
        boolean datCoc = themDonDatPhongPanel.getChkTrangThaiDatCoc().isSelected();

        //nếu chưa có đơn đặt thì tạo
        if(themDonDatPhongPanel.getDonDatPhong() == null) {
            DonDatPhong ddp = themDonDatPhongBUS.taoDonDatPhong(themDonDatPhongPanel.getMenuPanel().getNhanVienDangTruc(), sdt, hoTen, email, cccd, themDonDatPhongPanel.getTongTien(), moTa,  pttt, datCoc, checkin, checkout);
            if(ddp==null) {
                JOptionPane.showMessageDialog(this, "Lỗi khi tạo đơn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            themDonDatPhongPanel.setDonDatPhong(ddp);
            themDonDatPhongPanel.setKhachHang(themDonDatPhongPanel.getDonDatPhong().getKhachHang());
        }

        // kiểm tra nếu chưa được thêm thì thêm chi tiết và add vào danh sách phòng của dsPhong
        if(!themDonDatPhongPanel.getDsPhong().contains(this.phong)) {
            // phòng này chưa được thêm
            themDonDatPhongPanel.getDsChiTietDDP_P().add(themDonDatPhongBUS.taoCTDDPP(themDonDatPhongPanel.getDonDatPhong(), phong, themDonDatPhongPanel.getDonDatPhong().getNgayNhanPhong(), themDonDatPhongPanel.getDonDatPhong().getNgayTraPhong()));
            themDonDatPhongPanel.getDsPhong().add(this.phong);
        }

        // kiểm tra dịch vụ đang chọn có trong danh sách dịch vụ của phòng đó chưa
        DichVu dichVuDangChon = new DichVuDAL().getDichVuTheoTen(cboDichVu.getSelectedItem().toString());
        if (checkDichVuDangDuocChon(dichVuDangChon)) {
            for (ChiTiet_DonDatPhong_Phong_DichVu ctddp : themDonDatPhongPanel.getDsChiTietDDP_Phong_DV()) {
                if (ctddp.getDichVu().equals(dichVuDangChon) && ctddp.getPhong().equals(this.phong)) {
                    int response = JOptionPane.showConfirmDialog(this, "Dịch vụ đã có trong phòng. Bạn có muốn cộng dồn thêm số lượng không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        ctddp.setSoLuongDat(ctddp.getSoLuongDat() + soLuongDat);
                        soLuongDat = ctddp.getSoLuongDat();
                        JOptionPane.showMessageDialog(this, "Đã cập nhật số lượng dịch vụ.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        return true;
                    } else {
                        return false;
                    }
                }
            }

        // Nếu dịch vụ chưa có, thêm mới vào danh sách
        themDonDatPhongPanel.getDsChiTietDDP_Phong_DV().add(themDonDatPhongBUS.taoCTDDPPDV(soLuongDat, this.ngayGioDat,dichVuDangChon,themDonDatPhongPanel.getDonDatPhong(), phong, this.moTaDichVu));
        }
        return true;
    }

    private void initComponents(Phong phong) {
        lblMaPhong = new JLabel("Mã phòng: " + phong.getMaPhong());
        lblTenPhong = new JLabel("Tên phòng: " + phong.getTenPhong());
        String[] serviceNames = dsDichVu.stream().map(DichVu::getTenDichVu).toArray(String[]::new);
        cboDichVu = new JComboBox<>();
        cboDichVu.addItem("--Chọn dịch vụ--");
        for (String serviceName : serviceNames) {
            cboDichVu.addItem(serviceName);
        }
        cboSoLuong = new JComboBox<>();
        cboSoLuong.setEnabled(false);
        moTaArea = new JTextArea(5, 30);
        moTaArea.setLineWrap(true);
        moTaArea.setWrapStyleWord(true);
        moTaArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btnThemService = new JButton("Thêm dịch vụ");
        btnThemService.setEnabled(false);
        cboNgaySuDungDV = new JComboBox<>();
        cboNgaySuDungDV.setEnabled(false);
        cboGioSuDungDV = new JComboBox<>();
        cboGioSuDungDV.setEnabled(false);
        lblDonVi = new JLabel("Đơn vị: ");
    }

    private void setupLayout() {
        this.setLayout(new BorderLayout());
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        JPanel northPanel = createNorthPanel();
        JPanel centerPanel = createCenterPanel();
        JPanel southPanel = createSouthPanel();
        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    private JPanel createNorthPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(lblMaPhong);
        panel.add(lblTenPhong);
        panel.setBackground(Color.LIGHT_GRAY);
        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        int row = 0;
        // Dịch vụ
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Chọn dịch vụ:"), gbc);
        gbc.gridx = 1;
        panel.add(cboDichVu, gbc);
        row++;
        // Số lượng
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Số lượng:"), gbc);
        gbc.gridx = 1;
        panel.add(cboSoLuong, gbc);
        row++;
        // Đơn vị
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(lblDonVi, gbc);
        row++;
        // Chọn ngày
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Chọn ngày:"), gbc);
        gbc.gridx = 1;
        panel.add(cboNgaySuDungDV, gbc);
        row++;
        // Chọn giờ
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Chọn giờ:"), gbc);
        gbc.gridx = 1;
        panel.add(cboGioSuDungDV, gbc);
        row++;
        // Mô tả yêu cầu
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Mô tả yêu cầu:"), gbc);
        gbc.gridx = 1;
        panel.add(moTaArea, gbc);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Thêm padding cho panel
        return panel;
    }


    private JPanel createSouthPanel() {
        JPanel panel = new JPanel();
        panel.add(btnThemService);
        panel.setBackground(Color.LIGHT_GRAY);
        return panel;
    }

    private void updateAddServiceButtonState() {
        btnThemService.setEnabled(cboDichVu.getSelectedIndex() != -1 &&
                cboSoLuong.getSelectedIndex() != -1 &&
                cboNgaySuDungDV.getSelectedIndex() != -1 &&
                cboGioSuDungDV.getSelectedIndex() != -1);
    }
    private void resetFields() {
        cboSoLuong.removeAllItems();
        lblDonVi.setText("Đơn vị:");
        cboNgaySuDungDV.removeAllItems();
        cboGioSuDungDV.removeAllItems();
    }
    private void updateServiceDetails(DichVu selectedService) {
        resetFields();
//      cập nhật số lượng
        int stockQuantity = selectedService.getSoLuongTon();
        for (int i = 1; i <= stockQuantity; i++) {
            cboSoLuong.addItem(String.valueOf(i));
        }
//      Cập nhật đơn vị
        lblDonVi.setText("Đơn vị: " + selectedService.getDonVi().toUpperCase());
//      Cập nhật ngày
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getNgayDen());
        while (!calendar.getTime().after(getNgayDi())) {
            cboNgaySuDungDV.addItem(dateFormat.format(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }
//      Cập nhật giờ
        updateAvailableHours();
    }

    private void updateAvailableHours() {
        // Xóa các giờ cũ
        cboGioSuDungDV.removeAllItems();
        // Lấy ngày được chọn
        if (cboNgaySuDungDV.getSelectedItem() == null) {
            return;
        }
        String selectedDate = cboNgaySuDungDV.getSelectedItem().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date selected = dateFormat.parse(selectedDate);
            if (isSameDay(selected, getNgayDen())) {
                for (int i = 14; i < 24; i++) {
                    cboGioSuDungDV.addItem(String.format("%02d:00", i));
                }
            } else if (isSameDay(selected, getNgayDi())) {
                for (int i = 0; i < 12; i++) {
                    cboGioSuDungDV.addItem(String.format("%02d:00", i));
                }
            } else {
                for (int i = 0; i < 24; i++) {
                    cboGioSuDungDV.addItem(String.format("%02d:00", i));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private boolean isSameDay(Date date1, Date date2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date1).equals(dateFormat.format(date2));
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("Test Dialog");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Phong phong = new PhongDAL().getPhongTheoMa("P001");
//        ChonDichVuDialog dialog = new ChonDichVuDialog(frame, phong);
//        dialog.setVisible(true);
    }
}