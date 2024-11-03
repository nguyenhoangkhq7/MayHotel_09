/*
    *MayHotel  day creative: 11/3/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here   
     */


package view;

import javax.swing.*;

public class DonDatPhongPanel {
    private JPanel panel;
    private JButton btnCheckin;
    private JButton btnCheckout;
    private JButton btnThemDichVu;
    private JButton btnHuy;
    private JButton btnXemChiTiet;

    // Hàm tạo (constructor) của lớp DonDatPhongPanel để khởi tạo các thuộc tính
    public DonDatPhongPanel(JPanel panel, JButton btnCheckin, JButton btnCheckout, JButton btnThemDichVu, JButton btnHuy, JButton btnXemChiTiet) {
        this.panel = panel;
        this.btnCheckin = btnCheckin;
        this.btnCheckout = btnCheckout;
        this.btnThemDichVu = btnThemDichVu;
        this.btnHuy = btnHuy;
        this.btnXemChiTiet = btnXemChiTiet;
    }

    // Getter để lấy các thành phần từ bên ngoài
    public JPanel getPanel() { return panel; }
    public JButton getBtnCheckin() { return btnCheckin; }
    public JButton getBtnCheckout() { return btnCheckout; }
    public JButton getBtnThemDichVu() { return btnThemDichVu; }
    public JButton getBtnHuy() { return btnHuy; }
    public JButton getBtnXemChiTiet() { return btnXemChiTiet; }
}
