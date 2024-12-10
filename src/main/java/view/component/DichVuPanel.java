/*
    *MayHotel_09  day creative: 12/8/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here   
     */


package view.component;

import constant.CommonConstants;
import dal.ChiTiet_DonDatPhong_Phong_DichVuDAL;
import entity.ChiTiet_DonDatPhong_Phong_DichVu;
import helper.UIHelpers;
import view.dialog.ChonDichVuDialog;
import view.dialog.SuaDichVuDialog;
import view.dialog.SuaDichVuDonDatPhongDialog;
import view.panel.ThemDonDatPhongPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class DichVuPanel extends JPanel implements ActionListener {
    private ChiTiet_DonDatPhong_Phong_DichVu chiTiet;
    private JButton btnSua, btnXoa;

    public ChiTiet_DonDatPhong_Phong_DichVu getChiTiet() {
        return chiTiet;
    }
    private ThemDonDatPhongPanel themDonDatPhongPanel;
    public DichVuPanel(ThemDonDatPhongPanel themDonDatPhongPanel, ChiTiet_DonDatPhong_Phong_DichVu chiTiet) {
        this.themDonDatPhongPanel = themDonDatPhongPanel;
        this.chiTiet = chiTiet;
        initComponents();
        btnSua.addActionListener(this);
        btnXoa.addActionListener(this);
    }

    private void initComponents() {
        setPreferredSize(new Dimension(250,100));
        setBackground(Color.GRAY); // Đặt nền trắng
        // Định dạng ngày/giờ
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        // Tên dịch vụ
        JLabel lblTenDichVu = createLabel(chiTiet.getDichVu().getTenDichVu(), 16, Font.BOLD);
        // Thời gian sử dụng dịch vụ
        JLabel lblTGSuDungDV = createLabel(chiTiet.getTgSuDungDV().format(formatter), 14, Font.ITALIC);

        // Nút sửa và xóa
        btnSua = UIHelpers.createButtonStyle("Sửa", new Dimension(60, 30), Color.WHITE, CommonConstants.ORANGE);
        btnXoa = UIHelpers.createButtonStyle("Xóa", new Dimension(60, 30), Color.WHITE, Color.RED);
        // Thêm các thành phần vào bố cục
        Box buttonBox = Box.createVerticalBox();
        buttonBox.add(btnSua);
        buttonBox.add(Box.createVerticalStrut(5));
        buttonBox.add(btnXoa);

        // Thêm vào JPanel
        setLayout(new BorderLayout(10, 10)); // Khoảng cách giữa các phần
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Lề cho panel
        Box boxContainLeft = Box.createVerticalBox();
        boxContainLeft.add(lblTenDichVu);
        boxContainLeft.add(Box.createVerticalStrut(20));
        boxContainLeft.add(lblTGSuDungDV);
        this.add(boxContainLeft, BorderLayout.CENTER);
        add(buttonBox, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o.equals(btnSua)) {
            SuaDichVuDonDatPhongDialog dialog = new SuaDichVuDonDatPhongDialog(themDonDatPhongPanel.getMenuPanel().getMainGUI(), themDonDatPhongPanel, chiTiet);
            dialog.setVisible(true);
            this.repaint();
            this.revalidate();
        }
        else if(o.equals(btnXoa)) {
            // xóa trên giao diện
            // xóa trong danh sách chi tiết
            JPanel jpnDV = themDonDatPhongPanel.getJpnDichVu();
            Component[] components = jpnDV.getComponents();
            for (Component component : components) {
                if (component instanceof DichVuPanel) {
                    DichVuPanel dichVuPanel = (DichVuPanel) component;
                    ChiTiet_DonDatPhong_Phong_DichVu chiTietDV = dichVuPanel.getChiTiet();
                    if(chiTietDV.equals(this.chiTiet)) {
                        jpnDV.remove(component); // xóa trên giao diện
                        // xóa trong danh sách
                        themDonDatPhongPanel.getDsChiTietDDP_Phong_DV().remove(this.chiTiet);
                        jpnDV.revalidate();
                        jpnDV.repaint();
                        break;  // Đảm bảo dừng lại sau khi đã xóa thành công
                    }
                }
            }

        }
    }
    private JLabel createLabel(String text, int fontSize, int fontStyle) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", fontStyle, fontSize));
        label.setHorizontalAlignment(SwingConstants.LEFT);
        return label;
    }

    public JButton getBtnSua() {
        return btnSua;
    }

    public JButton getBtnXoa() {
        return btnXoa;
    }

    public static void main(String[] args) {
        // Test panel
        JFrame frame = new JFrame("Dịch Vụ Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 150);
        // Mock data
        ChiTiet_DonDatPhong_Phong_DichVu chiTietMock = new ChiTiet_DonDatPhong_Phong_DichVuDAL().getAllChiTietDonDatPhongPhongDichVu().get(1);
        // TODO: Thiết lập dữ liệu giả (mock) cho `chiTietMock`

        frame.setVisible(true);
    }

}

