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
import dal.ChiTiet_DonDatPhong_Phong_DichVuDAL;
import dal.DichVuDAL;
import dal.PhongDAL;
import entity.*;

public class ChonDichVuDialog extends JDialog implements ActionListener{

    private int soLuongDat;
    private LocalDateTime ngayGioDat;
    private String moTaDichVu, tenDichVu;
    private JLabel lblMaPhong, lblTenPhong, lblDonVi;
    private JComboBox<String> cboDichVu, cboSoLuong, cboNgaySuDungDV, cboGioSuDungDV;
    private JTextArea moTaArea;
    private JButton btnThemService;
    private ArrayList<DichVu> dsDichVu;
    private ChiTiet_DonDatPhong_Phong_DichVu cTDDPP_DichVu;
    Phong phong;
    private boolean isChonDichVu = false;
    DonDatPhong donDatPhong;

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

    public ChiTiet_DonDatPhong_Phong_DichVu getcTDDPP_DichVu() {
        return cTDDPP_DichVu;
    }

    public ChonDichVuDialog(JFrame parent, DonDatPhong donDatPhong, Phong p) {
        super(parent, "Chọn dịch vụ cho phòng", true);
        this.donDatPhong = donDatPhong;
        dsDichVu = new DichVuDAL().getAllDichVu();
        phong = p;
        initComponents(phong);
        setupLayout();
        cboDichVu.addActionListener(this);
        cboNgaySuDungDV.addActionListener(this);
        btnThemService.addActionListener(this);
        System.out.println(donDatPhong);
        System.out.println(p);
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
    private boolean themDonDatDichVu() {
        // lấy ra các dịch vụ từ phòng và đơn đặt phòng
        ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> dsChiTietDDP_P_DichVu = new ChiTiet_DonDatPhong_Phong_DichVuDAL().getDanhSachChiTietTheoMa(donDatPhong.getMaDon(), phong.getMaPhong());
        System.out.println("Danh sách dịch vụ");
        // duyệt qua các dịch vụ xem xem dịch vụ đó đã có chưa
        for (ChiTiet_DonDatPhong_Phong_DichVu ctddp_p_dv : dsChiTietDDP_P_DichVu) {
            if (ctddp_p_dv.getDichVu().getTenDichVu().equals(tenDichVu)) {
                int response = JOptionPane.showConfirmDialog(this, "Dịch vụ đã có trong phòng. Bạn có muốn cộng dồn thêm số lượng không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    ctddp_p_dv.setSoLuongDat(ctddp_p_dv.getSoLuongDat() + soLuongDat);
                    soLuongDat = ctddp_p_dv.getSoLuongDat();
                    JOptionPane.showMessageDialog(this, "Đã cập nhật số lượng dịch vụ.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }
            }
        }
        ThemDonDatPhongBUS themDonDatPhongBUS = new ThemDonDatPhongBUS();
        cTDDPP_DichVu = themDonDatPhongBUS.taoCTDDPPDV(soLuongDat, ngayGioDat, new DichVuDAL().getDichVuTheoTen(tenDichVu), donDatPhong, phong, moTaDichVu);
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
        Date ngayDen = convertToDate(donDatPhong.getNgayNhanPhong());
        Date ngayDi = convertToDate(donDatPhong.getNgayTraPhong());
        calendar.setTime(ngayDen);
        while (!calendar.getTime().after(ngayDi)) {
            cboNgaySuDungDV.addItem(dateFormat.format(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }
//      Cập nhật giờ
        updateAvailableHours();
    }
    public Date convertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
    private void updateAvailableHours() {
        Date ngayDen = convertToDate(donDatPhong.getNgayNhanPhong());
        Date ngayDi = convertToDate(donDatPhong.getNgayTraPhong());
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
            if (isSameDay(selected, ngayDen)) {
                for (int i = 14; i < 24; i++) {
                    cboGioSuDungDV.addItem(String.format("%02d:00", i));
                }
            } else if (isSameDay(selected, ngayDi)) {
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