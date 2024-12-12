/*
    *MayHotel_09  day creative: 11/26/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here
     */


package view.component;

import bus.QuanLyDonDatPhongBUS;
import constant.CommonConstants;
import dal.DonDatPhongDAL;
import entity.DonDatPhong;
import view.dialog.ChiTietDonDatPhongDialog;
import view.dialog.CapNhatDichVuDialog;
import view.dialog.ChuyenPhongDialog;
import view.panel.QuanLyDonDatPhongPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class DonDatPhongPanel extends JPanel implements ActionListener {
    private JButton btnCheckin,btnCheckout,btnThemDichVu, btnHuyDon,btnXemChiTiet, btnChuyenPhong;
    private DonDatPhong donDatPhong;
    private QuanLyDonDatPhongPanel quanLyDonDatPhongPanel;

    public DonDatPhong getDonDatPhong() {
        return donDatPhong;
    }

    public DonDatPhongPanel(QuanLyDonDatPhongPanel quanLyDonDatPhongPanel , DonDatPhong ddp) {
        QuanLyDonDatPhongBUS quanLyDonDatPhongBUS = new QuanLyDonDatPhongBUS();
        this.quanLyDonDatPhongPanel = quanLyDonDatPhongPanel;
        this.donDatPhong = ddp;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(250, 280));
        this.setBorder(BorderFactory.createLineBorder(CommonConstants.ORANGE, 2));

        JPanel jpn1 = new JPanel();
        JPanel jpn2 = new JPanel();
        JPanel jpn3 = new JPanel();
        JPanel jpn4 = new JPanel();
        JPanel jpn5 = new JPanel();
        JPanel jpnButton = new JPanel();
        JPanel jpnXemChiTiet = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // Đặt màu nền trong suốt cho các panel con
        jpn1.setOpaque(false);
        jpn2.setOpaque(false);
        jpn3.setOpaque(false);
        jpn4.setOpaque(false);
        jpn5.setOpaque(false);
        jpnButton.setOpaque(false);
        jpnXemChiTiet.setOpaque(false);

        btnCheckin = new JButton("Checkin");
        btnCheckin.setFocusPainted(false);
        btnCheckout = new JButton("Checkout");
        btnCheckout.setFocusPainted(false);
        btnThemDichVu = new JButton("Thêm dịch vụ");
        btnThemDichVu.setFocusPainted(false);
        btnHuyDon = new JButton("Hủy đơn");
        btnHuyDon.setFocusPainted(false);
        btnXemChiTiet = new JButton("Chi tiết");
        btnXemChiTiet.setFocusPainted(false);
        btnChuyenPhong = new JButton("Chuyển phòng");
        btnChuyenPhong.setFocusPainted(false);

        jpnXemChiTiet.add(btnChuyenPhong);
        jpnXemChiTiet.add(btnXemChiTiet);
        JPanel jpnNorth = new JPanel(new BorderLayout());
        jpnNorth.add(jpnXemChiTiet);

        // Lấy thông tin từ DonDatPhong
        String tenKhachHang = ddp.getKhachHang().getHoTen();
        String soDienThoai = ddp.getKhachHang().getSoDienThoai();
        String trangThaiDatCoc = ddp.isTrangThaiDatCoc() ? "Đã đặt cọc" : "Chưa đặt cọc";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String ngayNhanPhong = ddp.getNgayNhanPhong().format(formatter);
        String ngayTraPhong = ddp.getNgayTraPhong().format(formatter);

        // Tạo các label thông tin
        JLabel lblTenKhachHang = new JLabel("Tên khách hàng: " + tenKhachHang);
        JLabel lblSoDienThoai = new JLabel("Số điện thoại: " + soDienThoai);
        JLabel lblTrangThaiDatCoc = new JLabel("Trạng thái đặt cọc: " + trangThaiDatCoc);
        JLabel lblNgayNhanPhong = new JLabel("Ngày nhận phòng: " + ngayNhanPhong);
        JLabel lblNgayTraPhong = new JLabel("Ngày trả phòng: " + ngayTraPhong);

        // thêm các thành phần vào
        jpn1.add(lblTenKhachHang);
        jpn2.add(lblSoDienThoai);
        jpn3.add(lblTrangThaiDatCoc);
        jpn4.add(lblNgayNhanPhong);
        jpn5.add(lblNgayTraPhong);

        // kiểm tra để quyết định là thêm checkin, checkout, ...
        if(donDatPhong.getTrangThaiDonDatPhong().equals("Đã đặt trước")) {
            jpnButton.add(btnCheckin);
            jpnButton.add(btnHuyDon);
            this.setBackground(CommonConstants.DAT_TRUOC_COLOR);
        } else {
            jpnButton.add(btnThemDichVu);
            jpnButton.add(btnCheckout);
            this.setBackground(CommonConstants.DANG_O_COLOR);
        }

        // set color cho các nút theo trạng thái của đơn
        if(quanLyDonDatPhongBUS.checkCoPhongChuyen(donDatPhong)) {
            this.setBackground(CommonConstants.CO_PHONG_CHUYEN_COLOR);
        }
        if(quanLyDonDatPhongBUS.checkSapDenHanCheckin(donDatPhong)) {{
            this.setBackground(CommonConstants.SAP_CHECKIN_COLOR);
        }}
        if(quanLyDonDatPhongBUS.checkQuaHanCheckin(donDatPhong)) {
            this.setBackground(CommonConstants.QUA_HAN_CHECKIN_COLOR);
        }
        if(quanLyDonDatPhongBUS.checkQuaHanCheckout(donDatPhong)) {
            this.setBackground(CommonConstants.QUA_HAN_CHECKOUT_COLOR);

}

        this.setLayout(new BorderLayout());
        jpnNorth.setOpaque(false);
        this.add(jpnNorth, BorderLayout.NORTH);
        Box boxCenter = Box.createVerticalBox();
        boxCenter.setOpaque(false);
        boxCenter.add(Box.createVerticalStrut(5));
        boxCenter.add(jpn1);
        boxCenter.add(Box.createVerticalStrut(5));
        boxCenter.add(jpn2);
        boxCenter.add(Box.createVerticalStrut(5));
        boxCenter.add(jpn3);
        boxCenter.add(Box.createVerticalStrut(5));
        boxCenter.add(jpn4);
        boxCenter.add(Box.createVerticalStrut(5));
        boxCenter.add(jpn5);
        boxCenter.add(Box.createVerticalStrut(5));
        this.add(boxCenter, BorderLayout.CENTER);
        this.add(jpnButton, BorderLayout.SOUTH);

        btnCheckin.addActionListener(this);
        btnCheckout.addActionListener(this);
        btnXemChiTiet.addActionListener(this);
        btnHuyDon.addActionListener(this);
        btnThemDichVu.addActionListener(this);
        btnChuyenPhong.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object o = e.getSource();

        if(o.equals(btnCheckin)) {
            xuLySKCheckin();
        } else if(o.equals(btnCheckout)) {
            xuLySKCheckout();
        } else if(o.equals(btnXemChiTiet)) {
            xuLySKXemChiTiet();
        }else if(o.equals(btnHuyDon)) {
            xuLySKHuyDon();
        } else if(o.equals(btnThemDichVu)) {
            xuLySKThemDichVu();
        } else if(o.equals(btnChuyenPhong)) {
            xuLySKChuyenPhong();
        }
    }
    private void xuLySKChuyenPhong(){
        ChuyenPhongDialog chuyenPhongDialog = new ChuyenPhongDialog(quanLyDonDatPhongPanel.getParentFrame(), this.donDatPhong);
        chuyenPhongDialog.setVisible(true);
    }
    private void xuLySKThemDichVu() {
        CapNhatDichVuDialog dialog = new CapNhatDichVuDialog(this.quanLyDonDatPhongPanel.getParentFrame(),donDatPhong);
        dialog.setVisible(true);
    }
    private void xuLySKHuyDon() {
        JPanel jpnDanhSachDDP = quanLyDonDatPhongPanel.getJpnDanhSachDDP();
        int confirm = JOptionPane.showConfirmDialog(
                null,
                "Bạn có chắc chắn muốn Hủy đơn?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION
        );
        if (confirm == JOptionPane.YES_OPTION) {
            new DonDatPhongDAL().huyDonDatPhong(this.donDatPhong.getMaDon());
            jpnDanhSachDDP.removeAll();
            jpnDanhSachDDP.add(quanLyDonDatPhongPanel.showDanhSachDDP(new DonDatPhongDAL().getAllDonDatPhong()), BorderLayout.CENTER);
        } else if (confirm == JOptionPane.NO_OPTION) {

        }
    }
    private void xuLySKCheckout() {
        JPanel jpnDanhSachDDP = quanLyDonDatPhongPanel.getJpnDanhSachDDP();
        int confirm = JOptionPane.showConfirmDialog(
                null,
                "Bạn có chắc chắn muốn checkout phòng này?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION
        );

        // Nếu người dùng chọn "YES"
        if (confirm == JOptionPane.YES_OPTION) {
            // Ghi lại thay đổi vào database hoặc nguồn dữ liệu
            if(new DonDatPhongDAL().suaTrangThaiDonDatPhong(this.donDatPhong.getMaDon(), "Đã hoàn thành")) {
                JOptionPane.showMessageDialog(null, "Checkout thành công");
            } else {
                JOptionPane.showMessageDialog(null, "Checkout thất bại. Gặp lỗi!");
            }

            // Cập nhật lại danh sách đơn đặt phòng trên giao diện
            jpnDanhSachDDP.removeAll();
            jpnDanhSachDDP.add(quanLyDonDatPhongPanel.showDanhSachDDP(new DonDatPhongDAL().getAllDonDatPhong()), BorderLayout.CENTER);
            quanLyDonDatPhongPanel.updateTrangThaiDon();
            jpnDanhSachDDP.revalidate();
            jpnDanhSachDDP.repaint();
        }
    }
    private void xuLySKCheckin() {
        JPanel jpnDanhSachDDP = quanLyDonDatPhongPanel.getJpnDanhSachDDP();
        int confirm = JOptionPane.showConfirmDialog(
                null,
                "Bạn có chắc chắn muốn Checkin?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            this.donDatPhong.setTrangThaiDonDatPhong("Đang ở");
            new DonDatPhongDAL().suaDonDatPhong(this.donDatPhong);
            jpnDanhSachDDP.removeAll();
            quanLyDonDatPhongPanel.updateTrangThaiDon();
            jpnDanhSachDDP.add(quanLyDonDatPhongPanel.showDanhSachDDP(new DonDatPhongDAL().getAllDonDatPhong()), BorderLayout.CENTER);
            quanLyDonDatPhongPanel.updateTrangThaiDon();
        }
    }
    private void xuLySKXemChiTiet() {
        ChiTietDonDatPhongDialog dialog = new ChiTietDonDatPhongDialog(this.donDatPhong);
        dialog.setVisible(true); // Hiển thị dialog
    }

}
