package view.panel;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import constant.CommonConstants;
import entity.ChiTiet_DonDatPhong_Phong;
import entity.NhanVien;
import entity.Phong;
import helper.UIHelpers;
import view.MainGUI;
import dal.ChiTiet_DonDatPhong_PhongDAL;
import dal.PhongDAL; // Import PhongDAL class

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ChuyenPhongPanel extends JPanel {
    private JTextField txtMaDDP;
    private JTextField txtMaPhongCu;
    private JTextField txtMaPhongMoi;
    private JTextField txtTenPhongCu;
    private JTextField txtTenPhongMoi;
    private JTextField txtDonGiaCu;
    private JTextField txtDonGiaMoi;
    private JTextField txtNgayNhan;
    private JTextField txtNgayTra;
    private JButton btnChuyenPhong;
    private JButton btnClose;
    private JTextField txtMaCTDDPPhong;
    private JComboBox<String> comboMaCTDDPPhong;

    /**
     * Create the panel.
     */
    public ChuyenPhongPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(255, 255, 255));
        setBorder(new EmptyBorder(5, 5, 5, 5));

        // Title Panel
        JPanel pnlTieuDe = new JPanel();
        pnlTieuDe.setBackground(new Color(255, 255, 255));
        add(pnlTieuDe);
        pnlTieuDe.setLayout(new FlowLayout());

        JLabel lblTieuDeTrang = new JLabel("Chuyển Phòng");
        lblTieuDeTrang.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDeTrang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
        pnlTieuDe.add(lblTieuDeTrang);

        // Information Panel
        JPanel pnlThongTin = new JPanel();
        pnlThongTin.setBackground(new Color(255, 255, 255));
        pnlThongTin.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE),
                "Thông tin chuyển phòng", TitledBorder.LEADING, TitledBorder.TOP, null, CommonConstants.ORANGE));
        add(pnlThongTin);
        pnlThongTin.setLayout(new GridLayout(1, 1, 0, 0));

        JPanel panelForm = new JPanel();
        panelForm.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel lbMaCTDDPPhong = new JLabel("Mã CTDDP Phòng:");
        lbMaCTDDPPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbMaCTDDPPhong);

        comboMaCTDDPPhong = new JComboBox<>();
        comboMaCTDDPPhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
       
        panelForm.add(comboMaCTDDPPhong);

        // Nạp dữ liệu cho ComboBox MaCTDDP
        loadMaCTDDPComboBox();
        // Old and new room details
        JLabel lbMaPhongCu = new JLabel("Mã phòng cũ:");
        lbMaPhongCu.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbMaPhongCu);
        txtMaPhongCu = new JTextField();
        txtMaPhongCu.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panelForm.add(txtMaPhongCu);

        JLabel lbMaPhongMoi = new JLabel("Mã phòng mới:");
        lbMaPhongMoi.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbMaPhongMoi);
        txtMaPhongMoi = new JTextField();
        txtMaPhongMoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panelForm.add(txtMaPhongMoi);

        JLabel lbTenPhongCu = new JLabel("Tên phòng cũ:");
        lbTenPhongCu.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbTenPhongCu);
        txtTenPhongCu = new JTextField();
        txtTenPhongCu.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtTenPhongCu.setEditable(false);
        panelForm.add(txtTenPhongCu);

        JLabel lbTenPhongMoi = new JLabel("Tên phòng mới:");
        lbTenPhongMoi.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbTenPhongMoi);
        txtTenPhongMoi = new JTextField();
        txtTenPhongMoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtTenPhongMoi.setEditable(false);
        panelForm.add(txtTenPhongMoi);

        pnlThongTin.add(panelForm);

        // Action buttons panel
        JPanel panel_3 = new JPanel();
        panel_3.setBackground(new Color(255, 255, 255));
        panel_3.setBorder(
                new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE), "Chức năng", TitledBorder.LEADING, TitledBorder.TOP, null, CommonConstants.ORANGE));
        add(panel_3);
        panel_3.setLayout(new FlowLayout());

        btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(btnClose);
                currentFrame.setVisible(false);

                NhanVien nhanVien = new NhanVien();  // Tạo đối tượng NhanVien hoặc lấy từ đâu đó
                MainGUI mainGUI = new MainGUI(nhanVien);
                JFrame frame = new JFrame("Màn hình chính");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(mainGUI);
                frame.setSize(1920, 1080);
                frame.setVisible(true);
            }
        });
        btnClose.setBackground(new Color(243, 125, 0));
        btnClose.setForeground(new Color(255, 255, 255));
        btnClose.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_3.add(btnClose);

        btnChuyenPhong = new JButton("Chuyển phòng");
        btnChuyenPhong.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get old and new room details from PhongDAL
                PhongDAL phongDAL = new PhongDAL();
                String maPhongCu = txtMaPhongCu.getText();
                String maPhongMoi = txtMaPhongMoi.getText();

                Phong tenPhongCu = phongDAL.getPhongTheoMa(maPhongCu);
                Phong tenPhongMoi = phongDAL.getPhongTheoMa(maPhongMoi);

                // Update information into text fields
                if (tenPhongCu != null) {
                    txtTenPhongCu.setText(tenPhongCu.getTenPhong());  // Get room name
                } else {
                    JOptionPane.showMessageDialog(null, "Mã phòng cũ không hợp lệ.");
                    return;
                }

                if (tenPhongMoi != null) {
                    txtTenPhongMoi.setText(tenPhongMoi.getTenPhong());  // Get new room name
                } else {
                    JOptionPane.showMessageDialog(null, "Mã phòng mới không hợp lệ.");
                    return;
                }

                // Confirmation dialog
                int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn chuyển phòng không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Chuyển phòng thành công!");
                    // Update database if necessary
                } else {
                    System.out.println("Quay về trang chuyển phòng.");
                }
            }
        });

        btnChuyenPhong.setBackground(new Color(243, 125, 0));
        btnChuyenPhong.setForeground(new Color(255, 255, 255));
        btnChuyenPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_3.add(btnChuyenPhong);

        // Load MaCTDDP ComboBox
        loadMaCTDDPComboBox();
    }

    

       
    private void loadMaCTDDPComboBox() {
        // Lấy tất cả dữ liệu từ cơ sở dữ liệu
        ChiTiet_DonDatPhong_PhongDAL chiTietDAL = new ChiTiet_DonDatPhong_PhongDAL();
        ArrayList<ChiTiet_DonDatPhong_Phong> chiTietList = chiTietDAL.getAllChiTietDonDatPhongPhong();

        // Duyệt qua danh sách và thêm mã CTDDP vào ComboBox
        for (ChiTiet_DonDatPhong_Phong chiTiet : chiTietList) {
            String maCTDDP = chiTiet.getDonDatPhong().getMaDon();  // Giả sử sử dụng mã đơn đặt phòng
            comboMaCTDDPPhong.addItem(maCTDDP);
        }
    }

    // Main method to run the panel
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chuyển Phòng");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ChuyenPhongPanel());
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
