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

public class HoaDon2 extends JFrame {

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
        setTitle("Quản lý hóa đơn");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        // Title Panel
        JPanel pnlTieuDe = new JPanel();
        pnlTieuDe.setBackground(new Color(255, 255, 255));
        contentPane.add(pnlTieuDe);
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
        contentPane.add(pnlThongTin);
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
        contentPane.add(pnlBang);
        pnlBang.setLayout(new BorderLayout());

        pnlTableCTHD = new JPanel();
        pnlTableCTHD.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
                "Thông tin chi tiết hóa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        pnlTableCTHD.setBackground(new Color(255, 255, 255));
        pnlTableCTHD.setBounds(20, 48, 800, 300);
        pnlBang.add(pnlTableCTHD, BorderLayout.CENTER); // Center of pnlBang
        pnlTableCTHD.setLayout(new BorderLayout()); // Set layout to BorderLayout

        // Scroll pane for the table
        scrDSHD = new JScrollPane();
        scrDSHD.setPreferredSize(new Dimension(0, 200)); // Chiếm ít không gian hơn
        scrDSHD.setBounds(12, 21, 1071, 200); // Điều chỉnh kích thước nếu cần
        pnlTableCTHD.add(scrDSHD, BorderLayout.CENTER); // Center of pnlTableCTHD

        // Table for detail information
        table_CTHD = new JTable();
        table_CTHD.setBackground(new Color(224, 255, 255));

        // Tạo DefaultTableModel để quản lý dữ liệu cho table_CTHD
        DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] { "Mã HĐ", "Tên Khách Hàng", "SĐT Khách Hàng" });

        // Thêm một số dữ liệu mẫu
        model.addRow(new Object[] { "HD001", "Nguyễn Văn A", "0123456789" });
        model.addRow(new Object[] { "HD002", "Trần Thị B", "0987654321" });
        model.addRow(new Object[] { "HD003", "Lê Văn C", "0345678901" });
        model.addRow(new Object[] { "HD004", "Phạm Thị D", "0912345678" });
        model.addRow(new Object[] { "HD005", "Đỗ Văn E", "0789123456" });

        // Gán model cho table_CTHD
        table_CTHD.setModel(model);

        // Thêm bảng vào scroll pane
        scrDSHD.setViewportView(table_CTHD);


        // Panel for invoice information
        pnlThongTinHD = new JPanel();
        pnlThongTinHD.setBackground(new Color(255, 255, 255));
        pnlThongTinHD.setBounds(12, 21, 849, 200); // Điều chỉnh kích thước nếu cần
        pnlThongTinHD.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
                "Thông tin hóa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        pnlThongTinHD.setLayout(new BorderLayout());
        pnlTableCTHD.add(pnlThongTinHD, BorderLayout.EAST); // Add to East of pnlTableCTHD

        // Customer info panel
        pnlKhachHang = new JPanel();
        pnlKhachHang.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlKhachHang.setBackground(new Color(255, 255, 255));
        pnlThongTinHD.add(pnlKhachHang, BorderLayout.NORTH); // Add to North of pnlThongTinHD
        pnlKhachHang.setLayout(new GridLayout(3, 2,0,40)); // Use GridLayout for labels and text fields
        
        lblMaHD = new JLabel("Mã hóa đơn");
        lblMaHD.setFont(new Font("Times New Roman", Font.BOLD, 15));
        pnlKhachHang.add(lblMaHD);

        txtMaHD = new JTextField();
        txtMaHD.setEditable(false);
        pnlKhachHang.add(txtMaHD);

        lblTenKH = new JLabel("Tên khách hàng");
        lblTenKH.setFont(new Font("Times New Roman", Font.BOLD, 15));
        pnlKhachHang.add(lblTenKH);

        txtTenKH = new JTextField();
        txtTenKH.setEditable(false);
        pnlKhachHang.add(txtTenKH);

        lblSDT = new JLabel("Số điện thoại");
        lblSDT.setFont(new Font("Times New Roman", Font.BOLD, 15));
        pnlKhachHang.add(lblSDT);

        txtSDT = new JTextField();
        txtSDT.setEditable(false);
        pnlKhachHang.add(txtSDT);

        

        btnTimKiem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // Lấy thông tin từ các trường tìm kiếm
                String maHD = txtMaHD.getText().trim();
                String tenKH = txtTenKH.getText().trim();
                String sdtKH = txtSDT.getText().trim();
                
                // Thực hiện tìm kiếm ở đây. 
                // Ví dụ: In thông tin tìm kiếm ra console (có thể thay thế bằng logic tìm kiếm thực tế)
                System.out.println("Tìm kiếm với:");
                System.out.println("Mã hóa đơn: " + maHD);
                System.out.println("Tên khách hàng: " + tenKH);
                System.out.println("Số điện thoại: " + sdtKH);
                
                // Logic tìm kiếm trong bảng hoặc trong dữ liệu của bạn
                // Cập nhật bảng kết quả nếu cần thiết
            }
        });


        // Final Settings
        setVisible(true);
    }

    public static void main(String[] args) {
        new HoaDon2();
    }
}
