/*
    *MayHotel_09  day creative: 11/26/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here   
     */


package view.component;

import bus.DonDatPhongBUS;
import constant.CommonConstants;
import dal.DonDatPhongDAL;
import entity.DonDatPhong;
import view.dialog.ChiTietDonDatPhong_PhongDialog;
import view.panel.QuanLyDonDatPhongPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class DonDatPhongPanel extends JPanel implements ActionListener {
    private JButton btnCheckin,btnCheckout,btnThemDichVu,btnHuy,btnXemChiTiet;
    private DonDatPhong donDatPhong;
    private QuanLyDonDatPhongPanel quanLyDonDatPhongPanel;
    private DonDatPhongBUS donDatPhongBUS;
    private DonDatPhongDAL donDatPhongDAL = new DonDatPhongDAL();

    public JButton getBtnCheckin() {
        return btnCheckin;
    }


    public JButton getBtnCheckout() {
        return btnCheckout;
    }


    public JButton getBtnThemDichVu() {
        return btnThemDichVu;
    }


    public JButton getBtnHuy() {
        return btnHuy;
    }


    public JButton getBtnXemChiTiet() {
        return btnXemChiTiet;
    }


    public DonDatPhong getDonDatPhong() {
        return donDatPhong;
    }

    public DonDatPhongPanel(QuanLyDonDatPhongPanel quanLyDonDatPhongPanel ,DonDatPhong ddp) {
        donDatPhongBUS = new DonDatPhongBUS();
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
        btnHuy = new JButton("Hủy đơn");
        btnHuy.setFocusPainted(false);
        btnXemChiTiet = new JButton("Chi tiết");
        btnXemChiTiet.setFocusPainted(false);
        jpnXemChiTiet.add(btnXemChiTiet);

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

        jpn1.add(lblTenKhachHang);
        jpn2.add(lblSoDienThoai);
        jpn3.add(lblTrangThaiDatCoc);
        jpn4.add(lblNgayNhanPhong);
        jpn5.add(lblNgayTraPhong);

        if(donDatPhong.getTrangThaiDonDatPhong().equals("Đặt trước")) {
            jpnButton.add(btnCheckin);
            jpnButton.add(btnHuy);
            this.setBackground(CommonConstants.DAT_TRUOC_COLOR);
        } else {
            jpnButton.add(btnThemDichVu);
            jpnButton.add(btnCheckout);
            this.setBackground(CommonConstants.DANG_O_COLOR);
        }

        if(donDatPhongBUS.checkCoPhongChuyen(donDatPhong)) {
            this.setBackground(CommonConstants.CO_PHONG_CHUYEN_COLOR);
        }
        if(donDatPhongBUS.checkSapDenHanCheckin(donDatPhong)) {{
            this.setBackground(CommonConstants.SAP_CHECKIN_COLOR);
        }}
        if(donDatPhongBUS.checkQuaHanCheckin(donDatPhong)) {
            this.setBackground(CommonConstants.QUA_HAN_CHECKIN_COLOR);
        }
        if(donDatPhongBUS.checkQuaHanCheckout(donDatPhong)) {
            this.setBackground(CommonConstants.QUA_HAN_CHECKOUT_COLOR);

}


        this.add(jpnXemChiTiet);
        this.add(jpn1);
        this.add(jpn2);
        this.add(jpn3);
        this.add(jpn4);
        this.add(jpn5);
        this.add(jpnButton);

        btnCheckin.addActionListener(this);
        btnCheckout.addActionListener(this);
        btnXemChiTiet.addActionListener(this);
        btnHuy.addActionListener(this);
        btnThemDichVu.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel jpnDanhSachDDP = quanLyDonDatPhongPanel.getJpnDanhSachDDP();
        Object o = e.getSource();

        if(o.equals(btnCheckin)) {
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
                jpnDanhSachDDP.add(quanLyDonDatPhongPanel.showDanhSachDDP(new DonDatPhongDAL().getAllDonDatPhong()), BorderLayout.CENTER);
            } else if (confirm == JOptionPane.NO_OPTION) {

            }
        }else if(o.equals(btnCheckout)) {

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Bạn có chắc chắn muốn checkout phòng này?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION
            );

            // Nếu người dùng chọn "YES"
            if (confirm == JOptionPane.YES_OPTION) {
                // Ghi lại thay đổi vào database hoặc nguồn dữ liệu
                if(donDatPhongDAL.suaTrangThaiDonDatPhong(this.donDatPhong.getMaDon(), "Đã hoàn thành")) {
                    JOptionPane.showMessageDialog(null, "Checkout thành công");
                } else {
                    JOptionPane.showMessageDialog(null, "Checkout thất bại. Gặp lỗi!");
                }

                // Cập nhật lại danh sách đơn đặt phòng trên giao diện
                jpnDanhSachDDP.removeAll();
                jpnDanhSachDDP.add(quanLyDonDatPhongPanel.showDanhSachDDP(donDatPhongDAL.getAllDonDatPhong()), BorderLayout.CENTER);
                jpnDanhSachDDP.revalidate();
                jpnDanhSachDDP.repaint();
            }
        }else if(o.equals(btnXemChiTiet)) {
            ChiTietDonDatPhong_PhongDialog dialog = new ChiTietDonDatPhong_PhongDialog(this.donDatPhong);
            dialog.setVisible(true); // Hiển thị dialog
        }else if(o.equals(btnHuy)) {
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
        } else if(o.equals(btnThemDichVu)) {

        }
    }

}
