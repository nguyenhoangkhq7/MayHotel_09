package view.dialog;

import dal.ChiTiet_DonDatPhong_PhongDAL;
import dal.DonDatPhongDAL;
import dal.LoaiPhongDAL;
import dal.PhongDAL;
import entity.ChiTiet_DonDatPhong_Phong;
import entity.DonDatPhong;
import entity.LoaiPhong;
import entity.Phong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ChuyenPhongDialog extends JDialog implements ActionListener{

    private JComboBox<String> cboPhongHienTai;
    private JComboBox<String> cboLoaiPhong;
    private JComboBox<String> cboPhongChuyen;
    private JButton btnChuyenPhong;
    private JButton btnHuy;
    private DonDatPhong donDatPhong;
    private String selectedPhongHienTai;
    private String selectedLoaiPhong;
    private String selectedPhong;

    public ChuyenPhongDialog(Frame owner, DonDatPhong donDatPhong) {
        super(owner, "Chọn và chuyển phòng", true);
        this.donDatPhong = donDatPhong;
        initComponents();
        cboPhongHienTai.addActionListener(this);
        cboPhongChuyen.addActionListener(this);
        cboLoaiPhong.addActionListener(this);
        btnChuyenPhong.addActionListener(this);
        btnHuy.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o.equals(cboPhongHienTai)) {
            xuLySKChonPhongHienTai();
        } else if(o.equals(cboLoaiPhong)) {
            xuLySKChonLoaiPhong();
        } else if(o.equals(cboPhongChuyen)) {
            xuLySKChonPhongChuyen();
        } else if(o.equals(btnChuyenPhong)) {
            xuLySKBTNChuyenPhong();
        } else if(o.equals(btnHuy)) {
            dispose();
        }
    }
    private void xuLySKBTNChuyenPhong() {

        // Hỏi yêu cầu xác nhận
        if (selectedPhongHienTai != null && selectedLoaiPhong != null && selectedPhong != null) {
            JOptionPane.showMessageDialog(this, "Chuyển từ phòng: " + selectedPhongHienTai + " sang phòng: " + selectedPhong,
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            // để chuyển thì thêm một chi tiết ddp phòng có thời gian từ hiên tại đến ngày trả phòng
            // set lại thời gian của chi tiết cũ có ngày trả phòng là hiện tại
            // set isPhongChuyen của cả 2 là true

            // thời gian hiện tại
            LocalDateTime now = LocalDateTime.now();

            // lấy thông tin
            Phong phongHienTai = new PhongDAL().getPhongTheoTenPhong(selectedPhongHienTai);
            Phong phongChuyen = new PhongDAL().getPhongTheoTenPhong(selectedPhong);
            ChiTiet_DonDatPhong_Phong cTDDPPHienTai = new ChiTiet_DonDatPhong_PhongDAL().getChiTietDonDatPhongPhongTheoMa(donDatPhong.getMaDon(), phongHienTai.getMaPhong());


            // thêm chi tiết
            ChiTiet_DonDatPhong_Phong chiTiet = new ChiTiet_DonDatPhong_Phong(donDatPhong, phongChuyen, now, donDatPhong.getNgayTraPhong(), true, 0.0);

            // chỉnh lại thời gian
            cTDDPPHienTai.setLaPhongChuyen(true);
            cTDDPPHienTai.setNgayTraPhong(now);

            // update lại cơ sở dữ liệu
            ChiTiet_DonDatPhong_PhongDAL chiTietDonDatPhongPhongDAL =  new ChiTiet_DonDatPhong_PhongDAL();
            chiTietDonDatPhongPhongDAL.suaChiTiet(donDatPhong.getMaDon(), phongHienTai.getMaPhong(), cTDDPPHienTai);
            chiTietDonDatPhongPhongDAL.themChiTiet(chiTiet);

            JOptionPane.showMessageDialog(null, "Chuyển phòng thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đầy đủ thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void xuLySKChonLoaiPhong() {
        selectedLoaiPhong = cboLoaiPhong.getSelectedItem().toString();
        updateCboPhongChuyen();
    }
    private void xuLySKChonPhongChuyen() {
        if (cboPhongChuyen.getSelectedItem() == null || "Chọn phòng chuyển".equals(cboPhongChuyen.getSelectedItem().toString())) {
            btnChuyenPhong.setEnabled(false);
            return;
        }
        selectedPhong = cboPhongChuyen.getSelectedItem().toString();
        btnChuyenPhong.setEnabled(true);
    }

    private void updateCboPhongChuyen() {
        // Kiểm tra trước khi update phòng
        if (cboLoaiPhong.getSelectedItem().toString().equals("Chọn loại phòng") || cboLoaiPhong.getSelectedItem() == null) {
            cboPhongChuyen.removeAllItems(); // Xóa tất cả các item hiện có trong combobox
            cboPhongChuyen.addItem("Chọn phòng chuyển"); // Thêm item mặc định
            return;
        }

        // Xóa các item cũ trước khi thêm mới
        cboPhongChuyen.removeAllItems();

        // Thêm item mặc định đầu tiên
        cboPhongChuyen.addItem("Chọn phòng chuyển");

        // Cập nhật lại danh sách phòng dựa trên loại phòng
        ArrayList<Phong> danhSachPhongCuaLoaiPhong = new PhongDAL().getPhongTheoLoaiPhongChuaDuocDat(new LoaiPhongDAL().getMaLoaiPhongTheoTen(selectedLoaiPhong), LocalDateTime.now(), donDatPhong.getNgayTraPhong());
        // Thêm phòng vào combobox
        for (Phong phong : danhSachPhongCuaLoaiPhong) {
            cboPhongChuyen.addItem(phong.getTenPhong());
        }
    }

    private void updateCboLoaiPhong() {
        ArrayList<LoaiPhong> dsLoaiPhong = new LoaiPhongDAL().getAllLoaiPhong();
        for(LoaiPhong loaiPhong : dsLoaiPhong) {
            cboLoaiPhong.addItem(loaiPhong.getTenLoaiPhong());
        }
    }
    private void updateCboPhongHienTai() {
        ArrayList<Phong> dsPhong = new DonDatPhongDAL().getDanhSachPhongTheoMaDonDatPhong(donDatPhong.getMaDon());
        for(Phong phong : dsPhong) {
            cboPhongHienTai.addItem(phong.getTenPhong());
        }
    }

    private void xuLySKChonPhongHienTai() {
        if (cboPhongHienTai.getSelectedItem() == null || "Chọn phòng muốn chuyển".equals(cboPhongHienTai.getSelectedItem().toString())) {
            cboLoaiPhong.setEnabled(false);
            cboPhongChuyen.setEnabled(false);
            return;
        }
        selectedPhongHienTai = cboPhongHienTai.getSelectedItem().toString();
        cboLoaiPhong.setEnabled(true);
        cboPhongChuyen.setEnabled(true);
        btnChuyenPhong.setEnabled(true);
        cboLoaiPhong.setSelectedIndex(0);
        cboPhongChuyen.removeAllItems();
        cboPhongChuyen.addItem("Chọn phòng chuyển");

    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Thêm khoảng cách

        // Label và combobox Phòng hiện tại
        JLabel phongHienTaiLabel = new JLabel("Phòng hiện tại:");
        cboPhongHienTai = new JComboBox<>();
        cboPhongHienTai.setPreferredSize(new Dimension(300, 40)); // Kích thước lớn hơn
        cboPhongHienTai.addItem("Chọn phòng muốn chuyển");
        updateCboPhongHienTai();

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(phongHienTaiLabel, gbc);

        gbc.gridx = 1;
        add(cboPhongHienTai, gbc);

        // Loại phòng
        JLabel loaiPhongLabel = new JLabel("Loại phòng:");
        cboLoaiPhong = new JComboBox<>();
        cboLoaiPhong.setPreferredSize(new Dimension(300, 40)); // Kích thước lớn hơn
        cboLoaiPhong.addItem("Chọn loại phòng");
        updateCboLoaiPhong();

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(loaiPhongLabel, gbc);

        gbc.gridx = 1;
        add(cboLoaiPhong, gbc);

        // Phòng chuyển
        JLabel phongLabel = new JLabel("Phòng mới:");
        cboPhongChuyen = new JComboBox<>();
        cboPhongChuyen.setPreferredSize(new Dimension(300, 40)); // Kích thước lớn hơn
        cboPhongChuyen.addItem("Chọn phòng chuyển");

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(phongLabel, gbc);

        gbc.gridx = 1;
        add(cboPhongChuyen, gbc);

        // Nút chuyển phòng và hủy
        btnChuyenPhong = new JButton("Chuyển phòng");
        btnChuyenPhong.setPreferredSize(new Dimension(150, 40)); // Kích thước nút


        btnHuy = new JButton("Hủy");
        btnHuy.setPreferredSize(new Dimension(150, 40)); // Kích thước nút

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnChuyenPhong);
        buttonPanel.add(btnHuy);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Chiếm toàn bộ chiều rộng
        add(buttonPanel, gbc);

        // disbale các nút
        cboLoaiPhong.setEnabled(false);
        cboPhongChuyen.setEnabled(false);
        btnChuyenPhong.setEnabled(false);

        // Thiết lập dialog
        this.setSize(500, 400); // Kích thước của dialog
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.WHITE); // Màu nền trắng
        setTitle("Chuyển phòng");
    }



    public static void main(String[] args) {
        DonDatPhong donDatPhong = new DonDatPhongDAL().getDonDatPhongTheoMa("DDP000001"); // Giả sử bạn đã có dữ liệu
        ChuyenPhongDialog dialog = new ChuyenPhongDialog(null, donDatPhong);
        dialog.setVisible(true);
    }


}