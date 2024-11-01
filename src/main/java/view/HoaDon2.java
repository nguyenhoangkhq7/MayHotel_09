package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import com.toedter.calendar.JDateChooser;
import constraints.CONSTRAINTS;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

public class HoaDon2 extends JPanel {

    private JTextField txtMaKH;
    private JTextField txtTenKH;
    private JTextField txtDiaChi;
    private JTextField txtSDT;
    private JTextField txtCCCD;
    private JTextField txtEmail;
    private JTextField txtDTL;
    private JTable table_1;
    private JComboBox<String> cboLoaiKhachHang;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnLamMoi; // Để làm mới thông tin
    private JLabel lblLoi;
    private JLabel lblLoi_Ten;
    private JLabel lblLoi_SDT;
    private JLabel lblLoi_Email;
    private JLabel lblLoi_CCCD;
    private JPanel panelForm;
    private JTextField txtMaHD;
    private JButton btnTimKiem;
    private JPanel pnlTableCTHD;
    private JScrollPane scrDSHD;
    private JTable table_CTHD;
    private JPanel pnlThongTinHD;
    private JPanel pnlKhachHang;
    private JLabel lblTenKH;
    private JLabel lblSDT;
    private JPanel pnlHoaDon;
    private JLabel lblMaHD;
    private JLabel lblNgayLap;
    private JLabel lblNhanVien;
    private JLabel lblSoLuong;
    private JLabel lblDiemTichDuoc;
    private JLabel lblTongThanhToan;
    private JTextField txtNgayLap;
    private JTextField txtNhanVien;
    private JTextField txtTongSL;
    private JTextField txtDiemTichDuoc;
    private JTextField txtTongThanhToan;

    public HoaDon2() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        // Title Panel
        JPanel pnlTieuDe = new JPanel();
        pnlTieuDe.setBackground(new Color(255, 255, 255));
        add(pnlTieuDe);
        pnlTieuDe.setLayout(new FlowLayout());

        JLabel lblTieuDeTrang = new JLabel("QUẢN LÝ HÓA ĐƠN");
        lblTieuDeTrang.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDeTrang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        pnlTieuDe.add(lblTieuDeTrang);

        // Search Panel
        JPanel pnlThongTin = new JPanel();
        pnlThongTin.setBackground(new Color(255, 255, 255));
        pnlThongTin.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE),
                "TÌM KIẾM GIAO DỊCH", TitledBorder.LEADING, TitledBorder.TOP, null, CONSTRAINTS.ORANGE));
        add(pnlThongTin);
        pnlThongTin.setLayout(new GridLayout(1, 1, 0, 0));

        // Form Panel
        panelForm = new JPanel();
        panelForm.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel lbMaHD = new JLabel("Mã hóa đơn:");
        lbMaHD.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbMaHD);

        txtMaHD = new JTextField();
        txtMaHD.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panelForm.add(txtMaHD);
        txtMaHD.setColumns(10);

        JLabel lbTuNgay = new JLabel("Từ ngày:");
        lbTuNgay.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbTuNgay);

        JDateChooser dateChooserTuNgay = new JDateChooser();
        panelForm.add(dateChooserTuNgay);

        JLabel lbTenKH = new JLabel("Tên khách hàng:");
        lbTenKH.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbTenKH);

        txtTenKH = new JTextField();
        txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panelForm.add(txtTenKH);
        txtTenKH.setColumns(10);

        JLabel lbDenNgay = new JLabel("Đến ngày:");
        lbDenNgay.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbDenNgay);

        JDateChooser dateChooserDenNgay = new JDateChooser();
        panelForm.add(dateChooserDenNgay);

        JLabel lbSDTKH = new JLabel("Số Điện Thoại KH:");
        lbSDTKH.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbSDTKH);

        txtSDT = new JTextField();
        txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panelForm.add(txtSDT);
        txtSDT.setColumns(10);

        pnlThongTin.add(panelForm);

        // Button Panel
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 255, 255));
        panel_2.setBorder(new TitledBorder(null, "Chức năng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_2.setLayout(new FlowLayout());

        btnTimKiem = new JButton("TÌM KIẾM");
        btnTimKiem.setBackground(new Color(0, 255, 255));
        btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 40));
        panel_2.add(btnTimKiem);

        pnlThongTin.add(panel_2);

        // Customer Info Panel
        JPanel pnlBang = new JPanel();
        pnlBang.setBackground(new Color(255, 255, 255));
        pnlBang.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE),
                "Thiết lập thông tin khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
        add(pnlBang);
        pnlBang.setLayout(new BorderLayout());

        pnlTableCTHD = new JPanel();
        pnlTableCTHD.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
                "Thông tin chi tiết hóa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        pnlTableCTHD.setBackground(new Color(255, 255, 255));
        pnlTableCTHD.setLayout(new BorderLayout());
        pnlBang.add(pnlTableCTHD, BorderLayout.CENTER);

        scrDSHD = new JScrollPane();
        scrDSHD.setPreferredSize(new Dimension(0, 200));
        pnlTableCTHD.add(scrDSHD, BorderLayout.CENTER);

        table_CTHD = new JTable();
        table_CTHD.setBackground(new Color(224, 255, 255));

        DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] { "Mã HĐ", "Tên Khách Hàng", "SĐT Khách Hàng" });
        model.addRow(new Object[] { "HD001", "Nguyễn Văn A", "0123456789" });
        model.addRow(new Object[] { "HD002", "Trần Thị B", "0987654321" });
        model.addRow(new Object[] { "HD003", "Lê Văn C", "0345678901" });
        model.addRow(new Object[] { "HD004", "Phạm Thị D", "0912345678" });
        model.addRow(new Object[] { "HD005", "Đỗ Văn E", "0789123456" });

        table_CTHD.setModel(model);
        scrDSHD.setViewportView(table_CTHD);

        btnTimKiem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String maHD = txtMaHD.getText().trim();
                String tenKH = txtTenKH.getText().trim();
                String sdtKH = txtSDT.getText().trim();

                System.out.println("Tìm kiếm với:");
                System.out.println("Mã hóa đơn: " + maHD);
                System.out.println("Tên khách hàng: " + tenKH);
                System.out.println("Số điện thoại: " + sdtKH);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.add(new HoaDon2());
        frame.setVisible(true);
    }
}
