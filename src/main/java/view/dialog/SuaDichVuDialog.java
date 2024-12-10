package view.dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import bus.ThemDonDatPhongBUS;
import dal.DichVuDAL;
import dal.PhongDAL;
import entity.*;
import view.panel.ThemDonDatPhongPanel;

public class SuaDichVuDialog extends JDialog implements ActionListener{

    private int soLuongDat;
    private LocalDateTime ngayGioDat;
    private String moTaDichVu, tenDichVu;
    private JLabel lblMaPhong, lblTenPhong, lblDonVi;
    private JComboBox<String> cboDichVu, cboSoLuong, cboNgaySuDungDV, cboGioSuDungDV;
    private JTextArea moTaArea;
    private JButton btnSua, btnHuy;
    private ChiTiet_DonDatPhong_Phong_DichVu ctDichVu;
    Phong phong;
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

    private Date getNgayDen() {
        return themDonDatPhongPanel.getJdcCheckIn().getDate();
    }
    private Date getNgayDi() {
        return themDonDatPhongPanel.getJdcCheckout().getDate();
    }
    public SuaDichVuDialog(JFrame parent, ThemDonDatPhongPanel themDonDatPhongPanel, ChiTiet_DonDatPhong_Phong_DichVu ctDichVu) {
        super(parent, "Chọn dịch vụ cho phòng", true);
        this.ctDichVu = ctDichVu;
        this.themDonDatPhongPanel = themDonDatPhongPanel;
        phong = ctDichVu.getPhong();
        initComponents();
        setupLayout();
        updateChiTietDichVu();
        cboDichVu.addActionListener(this);
        cboNgaySuDungDV.addActionListener(this);
        btnSua.addActionListener(this);
        btnHuy.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o.equals(cboNgaySuDungDV)) {
            updateAvailableHours();
        }
        else if(o.equals(btnSua)) {
            xuLySKSuaDichVu();
        }
        else if(o.equals(btnHuy)) {
            this.dispose();
        }
    }
    private void updateChiTietDichVu() {
//          Cập nhật lại chi tiết các dịch vụ
            cboDichVu.setEnabled(false);
            cboSoLuong.setEnabled(true);
            cboNgaySuDungDV.setEnabled(true);
            cboGioSuDungDV.setEnabled(true);
            updateServiceDetails();
    }
    private void xuLySKSuaDichVu() {
        try {
            // Lấy thông tin từ các trường nhập liệu
            getThongTinTuFields();

            // Hiển thị hộp thoại xác nhận
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc chắn muốn sửa dịch vụ này?\n" +
                            "Tên dịch vụ: " + tenDichVu + "\n" +
                            "Số lượng: " + soLuongDat + "\n" +
                            "Thời gian sử dụng: " + ngayGioDat + "\n" +
                            "Mô tả: " + moTaDichVu,
                    "Xác nhận sửa dịch vụ",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            // Nếu người dùng chọn "Yes", thực hiện sửa
            if (confirm == JOptionPane.YES_OPTION) {
                // Sửa đơn đặt dịch vụ
                suaDonDatDichVu();

                // Hiển thị thông báo sửa thành công
                JOptionPane.showMessageDialog(this,
                        "Dịch vụ đã được sửa thành công!\n" +
                                "Tên dịch vụ: " + tenDichVu + "\n" +
                                "Số lượng: " + soLuongDat + "\n" +
                                "Thời gian sử dụng: " + ngayGioDat + "\n" +
                                "Mô tả: " + moTaDichVu,
                        "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);

                // Đóng dialog
                dispose();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi khi nhập thông tin không hợp lệ
            JOptionPane.showMessageDialog(this,
                    "Thông tin không hợp lệ. Vui lòng kiểm tra lại.",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void getThongTinTuFields() throws ParseException {
        this.tenDichVu = (String) cboDichVu.getSelectedItem();
        this.soLuongDat = Integer.parseInt((String) cboSoLuong.getSelectedItem());
        String selectedDay = (String) cboNgaySuDungDV.getSelectedItem();
        String selectedHour = (String) cboGioSuDungDV.getSelectedItem();
        this.moTaDichVu = moTaArea.getText();
        // Kết hợp ngày và giờ để tạo LocalDateThbg time cho `ngayGioDat`
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date dateTime = dateFormat.parse(selectedDay + " " + selectedHour);
        this.ngayGioDat = dateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    private void suaDonDatDichVu() { // bao gồm thêm đơn đặt phòng và khách hàng
        ctDichVu.setSoLuongDat(soLuongDat);
        ctDichVu.setTgSuDungDV(ngayGioDat);
        ctDichVu.setMoTa(moTaDichVu);
    }

    private void initComponents() {
        lblMaPhong = new JLabel("Mã phòng: " + phong.getMaPhong());
        lblTenPhong = new JLabel("Tên phòng: " + phong.getTenPhong());
        cboDichVu = new JComboBox<>();
        cboDichVu.addItem(ctDichVu.getDichVu().getTenDichVu());
        cboSoLuong = new JComboBox<>();
        cboSoLuong.setEnabled(false);
        moTaArea = new JTextArea(5, 30);
        moTaArea.setLineWrap(true);
        moTaArea.setWrapStyleWord(true);
        moTaArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btnSua= new JButton("Sửa dịch vụ");
        btnHuy = new JButton("Hủy");
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
        panel.add(btnSua);
        panel.add(btnHuy);
        panel.setBackground(Color.LIGHT_GRAY);
        return panel;
    }

    private void resetFields() {
        cboSoLuong.removeAllItems();
        lblDonVi.setText("Đơn vị:");
        cboNgaySuDungDV.removeAllItems();
        cboGioSuDungDV.removeAllItems();
    }
    private void updateServiceDetails() {
        resetFields();

        // cập nhật số lượng
        int stockQuantity = ctDichVu.getDichVu().getSoLuongTon();
        for (int i = 1; i <= stockQuantity; i++) {
            cboSoLuong.addItem(String.valueOf(i));
        }
        cboSoLuong.setSelectedItem(String.valueOf(ctDichVu.getSoLuongDat()));

        // Cập nhật đơn vị
        lblDonVi.setText("Đơn vị: " + ctDichVu.getDichVu().getDonVi().toUpperCase());

        // Cập nhật ngày
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getNgayDen());
        while (!calendar.getTime().after(getNgayDi())) {
            cboNgaySuDungDV.addItem(dateFormat.format(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }
        Date ngaySD = Date.from(ctDichVu.getTgSuDungDV().atZone(ZoneId.systemDefault()).toInstant());
        cboNgaySuDungDV.setSelectedItem(dateFormat.format(ngaySD));

        moTaArea.setText(ctDichVu.getMoTa());
        // Cập nhật giờ
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

        LocalDateTime tgSuDungDV = ctDichVu.getTgSuDungDV();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String gioSuDung = tgSuDungDV.format(timeFormatter);
        cboGioSuDungDV.setSelectedItem(gioSuDung);
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