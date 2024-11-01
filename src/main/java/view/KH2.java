package view;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import com.toedter.calendar.JDateChooser;
import constraints.CONSTRAINTS;
import javax.swing.table.DefaultTableModel;

public class KH2 extends JPanel {

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
    private ButtonGroup buttonGroupTK;
    private ButtonGroup buttonGroupGT;
    private JLabel lblLoi;
    private JLabel lblLoi_Ten;
    private JLabel lblLoi_SDT;
    private JLabel lblLoi_Email;
    private JLabel lblLoi_CCCD;
    private JPanel panelForm;
    private JPanel panelDetail_KH;
    private JTextField txtCMND;
    private JComboBox<String> cboLoaiKhach;
    private JTextField txtTienTichLuy;
    private JTextField txtTim;

    public KH2() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(255, 255, 255));
        setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel pnlTieuDe = new JPanel();
        pnlTieuDe.setBackground(new Color(255, 255, 255));
        add(pnlTieuDe);
        pnlTieuDe.setLayout(new FlowLayout());

        JLabel lblTieuDeTrang = new JLabel("QUẢN LÝ KHÁCH HÀNG");
        lblTieuDeTrang.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDeTrang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        pnlTieuDe.add(lblTieuDeTrang);

        JPanel pnlThongTin = new JPanel();
        pnlThongTin.setBackground(new Color(255, 255, 255));
        pnlThongTin.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE),
                "Thiết lập thông tin khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, CONSTRAINTS.ORANGE));

        add(pnlThongTin);
        pnlThongTin.setLayout(new GridLayout(1, 1, 0, 0));
        
        panelForm = new JPanel();
        panelForm.setLayout(new GridLayout(3, 3, 10, 10));

        JLabel lbMaKH = new JLabel("Mã khách hàng:");
        lbMaKH.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbMaKH);

        txtMaKH = new JTextField("KH******");
        txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtMaKH.setEditable(false);
        panelForm.add(txtMaKH);

        JLabel lbTienTichLuy = new JLabel("Tiền Tích Lũy:");
        lbTienTichLuy.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbTienTichLuy);

        txtTienTichLuy = new JTextField();
        txtTienTichLuy.setFont(new Font("Dialog", Font.BOLD, 13));
        panelForm.add(txtTienTichLuy);

        JLabel lbLoaiKhach = new JLabel("Loại Khách Hàng:");
        lbLoaiKhach.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbLoaiKhach);

        cboLoaiKhach = new JComboBox<>();
        cboLoaiKhach.setFont(new Font("Tahoma", Font.BOLD, 13));
        cboLoaiKhach.addItem("Hạng Đồng");
        cboLoaiKhach.addItem("Hạng Bạc");
        cboLoaiKhach.addItem("Hạng Vàng");
        cboLoaiKhach.addItem("Hạng Kim Cương");
        panelForm.add(cboLoaiKhach);

        JLabel lbTenKH = new JLabel("Tên khách hàng:");
        lbTenKH.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbTenKH);

        txtTenKH = new JTextField();
        txtTenKH.setFont(new Font("Dialog", Font.BOLD, 13));
        panelForm.add(txtTenKH);

        JLabel lbCMND = new JLabel("CMND/CCCD:");
        lbCMND.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbCMND);

        txtCMND = new JTextField();
        txtCMND.setFont(new Font("Dialog", Font.BOLD, 13));
        panelForm.add(txtCMND);
        
        JLabel lbNull1 = new JLabel();
        panelForm.add(lbNull1);
        
        JLabel lbNull2 = new JLabel();
        panelForm.add(lbNull2);

        JLabel lbSDT = new JLabel("Số điện thoại:");
        lbSDT.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbSDT);

        txtSDT = new JTextField();
        txtSDT.setFont(new Font("Dialog", Font.BOLD, 13));
        panelForm.add(txtSDT);

        JLabel lbEmail = new JLabel("Email:");
        lbEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbEmail);

        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Dialog", Font.BOLD, 13));
        panelForm.add(txtEmail);

        panelDetail_KH = new JPanel();

        pnlThongTin.add(panelForm);
        pnlThongTin.add(panelDetail_KH);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 255, 255));
        panel_2.setBorder(
                new TitledBorder(null, "Chức năng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        add(panel_2);
        panel_2.setLayout(new FlowLayout());

        btnThem = new JButton("Thêm");
        btnThem.setBackground(new Color(0, 255, 255));
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_2.add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setBackground(new Color(0, 255, 255));
        btnSua.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_2.add(btnSua);

        JPanel pnlBang = new JPanel();
        pnlBang.setBackground(new Color(255, 255, 255));
        pnlBang.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE),
                "Danh sách khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, CONSTRAINTS.ORANGE));
        add(pnlBang);
        pnlBang.setLayout(new BorderLayout());

        JScrollPane scrDSKH = new JScrollPane();
        pnlBang.add(scrDSKH, BorderLayout.CENTER);

        Box hBox = Box.createHorizontalBox();
        JLabel lbTimTenKH = new JLabel("Tên khách hàng:");
        lbTimTenKH.setFont(new Font("Tahoma", Font.BOLD, 13));

        txtTim = new JTextField();
        txtTim.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtTim.setPreferredSize(new Dimension(150, 20));
        txtTim.setMaximumSize(new Dimension(150, 20));

        JButton btnTim = new JButton("Tìm");
        btnTim.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnTim.setBackground(new Color(0, 255, 255));

        hBox.add(lbTimTenKH);
        hBox.add(Box.createHorizontalStrut(10));
        hBox.add(txtTim);
        hBox.add(Box.createHorizontalStrut(10));
        hBox.add(btnTim);

        pnlBang.add(hBox, BorderLayout.NORTH);

        table_1 = new JTable();
        table_1.setModel(new DefaultTableModel(new Object[][] {},
                new String[]{"Mã KH", "Tên KH", "SĐT", "CCCD", "Email", "Điểm tích lũy", "Loại KH"}) {
            boolean[] columnEditables = new boolean[]{false, false, false, false, false, false, false};

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });

        table_1.setForeground(new Color(0, 0, 0));
        table_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        table_1.setRowHeight(25);
        table_1.setSelectionBackground(new Color(0, 204, 204));
        table_1.setSelectionForeground(new Color(255, 255, 255));
        table_1.setFillsViewportHeight(true);

        scrDSKH.setViewportView(table_1);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JFrame frame = new JFrame("Quản lí khách hàng");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(new KH2());
                frame.pack();
                frame.setSize(800, 600);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
