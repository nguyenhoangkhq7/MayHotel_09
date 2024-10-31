package view;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import com.toedter.calendar.JDateChooser;

import constraints.CONSTRAINTS;

import javax.swing.table.DefaultTableModel;

public class QL_Phong extends JFrame {

    private JTextField txtMaKH;
    private JTextField txtTenKH;
    private JTextField txtDiaChi;
    private JTextField txtSDT;
    private JTextField txtCCCD;
    private JTextField txtEmail;
    private JTextField txtDTL;
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
	private JTextField txtMaPhong;
	private JTextField txtTenPhong;
	private JComboBox<String> cboLoaiPhong;
	private JComboBox<String> cboTrangThaiPhong;
	private JTextField txtMoTa;
	private JComboBox<String> cboTang;
	private JPanel panel_DSSP;
	private Container panel_CTKM;
	private JComboBox cbbTimKiemSP;
	private JLabel lblTimKiemSP;
	private DefaultTableModel tablemodel;
	private JTable table_SP;
	private JButton btnTimKiem;
	private JButton btnXoa;
	private JButton btnLamLai;
	private JButton btnLuu;

    public QL_Phong() {
        setTitle("Quản lí khách hàng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JPanel pnlTieuDe = new JPanel();
        pnlTieuDe.setBackground(new Color(255, 255, 255));
        contentPane.add(pnlTieuDe);
        pnlTieuDe.setLayout(new FlowLayout());

        JLabel lblTieuDeTrang = new JLabel("QUẢN LÝ PHÒNG");
        lblTieuDeTrang.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDeTrang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        pnlTieuDe.add(lblTieuDeTrang);

        JPanel pnlThongTin = new JPanel();
        pnlThongTin.setBackground(new Color(255, 255, 255));
        pnlThongTin.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE),
                "Thiết lập thông tin phòng", TitledBorder.LEADING, TitledBorder.TOP, null,CONSTRAINTS.ORANGE));
       
        contentPane.add(pnlThongTin);
        pnlThongTin.setLayout(new GridLayout(1, 1, 0, 0));
        
        panelForm = new JPanel();
        panelForm.setLayout(new GridLayout(3,2,10,10));
        
        JLabel lbMaPhong = new JLabel("Mã phòng:");
        lbMaPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbMaPhong.setBounds(10, 21, 100, 14);
        panelForm.add(lbMaPhong);

        txtMaPhong = new JTextField("KH******");
        txtMaPhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtMaPhong.setEditable(false);
        txtMaPhong.setBounds(145, 18, 205, 20);
        panelForm.add(txtMaPhong);
        txtMaPhong.setColumns(10);
        
        JLabel lbLoaiPhong = new JLabel("Loại Phòng :");
        lbLoaiPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbLoaiPhong.setBounds(10, 151, 105, 14);
        panelForm.add(lbLoaiPhong);
        cboLoaiPhong = new JComboBox<String>();
        cboLoaiPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
        cboLoaiPhong.setBounds(145, 154, 205, 20);
        cboLoaiPhong.addItem("VIP");
        cboLoaiPhong.addItem("Thường");
        panelForm.add(cboLoaiPhong);
        
        JLabel lbTenPhong = new JLabel("Tên phòng:");
        lbTenPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbTenPhong.setBounds(10, 100, 136, 14);
        panelForm.add(lbTenPhong);
        
        txtTenPhong = new JTextField();
        txtTenPhong.setFont(new Font("Dialog", Font.BOLD, 13));
        txtTenPhong.setBounds(145, 97, 205, 20);
        panelForm.add(txtTenPhong);
        txtTenPhong.setColumns(10);

        
        
        
        
        JLabel lbTrangThaiPhong = new JLabel("Trạng Thái Phòng :");
        lbTrangThaiPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbTrangThaiPhong.setBounds(10, 151, 105, 14);
        panelForm.add(lbTrangThaiPhong);
        cboTrangThaiPhong = new JComboBox<String>();
        cboTrangThaiPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
        cboTrangThaiPhong.setBounds(145, 154, 205, 20);
        cboTrangThaiPhong.addItem("Đang ở");
        cboTrangThaiPhong.addItem("Trống");
        panelForm.add(cboTrangThaiPhong);
        
        JLabel lbMoTa = new JLabel("Mô tả:");
        lbMoTa.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbMoTa.setBounds(10, 100, 136, 14);
        panelForm.add(lbMoTa);
        
        txtMoTa = new JTextField();
        txtMoTa.setFont(new Font("Dialog", Font.BOLD, 13));
        txtMoTa.setBounds(145, 97, 205, 20);
        panelForm.add(txtMoTa);
        txtMoTa.setColumns(10);
        
        JLabel lbTang = new JLabel("Tầng :");
        lbTang.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbTang.setBounds(10, 151, 105, 14);
        panelForm.add(lbTang);
        cboTang = new JComboBox<String>();
        cboTang.setFont(new Font("Tahoma", Font.BOLD, 13));
        cboTang.setBounds(145, 154, 205, 20);
        cboTang.addItem("1");
        cboTang.addItem("2");
        cboTang.addItem("3");
        cboTang.addItem("4");
        cboTang.addItem("5");
        panelForm.add(cboTang);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 255, 255));
//        panel_2.setBorder(new TitledBorder(null, "Chức năng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//        panel_2.setLayout(new FlowLayout());

//        btnThem = new JButton("Thêm");
//        btnThem.setBackground(new Color(0, 255, 255));
//        btnThem.setFont(new Font("Tahoma", Font.BOLD, 11));
//        btnThem.setBorder(new EmptyBorder(10, 20, 10, 20)); // Khoảng cách trên và dưới, 20 pixel bên trái
//        panel_2.add(btnThem);
//
//        panel_2.add(Box.createRigidArea(new Dimension(0, 10))); // Khoảng cách giữa các nút
//
//        btnSua = new JButton("Sửa");
//        btnSua.setBackground(new Color(0, 255, 255));
//        btnSua.setFont(new Font("Tahoma", Font.BOLD, 11));
//        btnSua.setBorder(new EmptyBorder(10, 20, 10, 20)); // Khoảng cách trên và dưới, 20 pixel bên trái
//        panel_2.add(btnSua);
//
//        panel_2.add(Box.createRigidArea(new Dimension(0, 10))); // Khoảng cách giữa các nút
//
//        btnXoa = new JButton("Xóa");
//        btnXoa.setBackground(new Color(0, 255, 255));
//        btnXoa.setFont(new Font("Tahoma", Font.BOLD, 11));
//        btnXoa.setBorder(new EmptyBorder(10, 20, 10, 20)); // Khoảng cách trên và dưới, 20 pixel bên trái
//        panel_2.add(btnXoa);
//
//        panel_2.add(Box.createRigidArea(new Dimension(0, 10))); // Khoảng cách giữa các nút
//
//        btnLamLai = new JButton("Làm lại");
//        btnLamLai.setBackground(new Color(0, 255, 255));
//        btnLamLai.setFont(new Font("Tahoma", Font.BOLD, 11));
//        btnLamLai.setBorder(new EmptyBorder(10, 20, 10, 20)); // Khoảng cách trên và dưới, 20 pixel bên trái
//        panel_2.add(btnLamLai);

		
        
        
        pnlThongTin.add(panelForm);
        pnlThongTin.add(panel_2);
        
        
        JPanel panel_ThongTinLoaiPhong = new JPanel();
        panel_ThongTinLoaiPhong.setBackground(new Color(255, 255, 255));
        panel_ThongTinLoaiPhong.setBorder(
                new TitledBorder(null, "Chức năng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPane.add(panel_ThongTinLoaiPhong);
        panel_ThongTinLoaiPhong.setLayout(new FlowLayout());

        btnThem = new JButton("Thêm");
        btnThem.setBackground(new Color(0, 255, 255));
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_ThongTinLoaiPhong.add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setBackground(new Color(0, 255, 255));
        btnSua.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_ThongTinLoaiPhong.add(btnSua);
        
        btnXoa = new JButton("Xóa");
        btnXoa.setBackground(new Color(0, 255, 255));
        btnXoa.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_ThongTinLoaiPhong.add(btnXoa);
        
        btnLuu = new JButton("Lưu");
        btnLuu.setBackground(new Color(0, 255, 255));
        btnLuu.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_ThongTinLoaiPhong.add(btnLuu);


        
        

        

        JPanel pnlBang = new JPanel();
        pnlBang.setBackground(new Color(255, 255, 255));
        pnlBang.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE),
                "Chi tết quản lý phòng", TitledBorder.LEADING, TitledBorder.TOP, null,CONSTRAINTS.ORANGE));
        contentPane.add(pnlBang);
        pnlBang.setLayout(new BorderLayout());

        // Tạo JScrollPane để chứa JTable
        JScrollPane scrDSKH = new JScrollPane();
        pnlBang.add(scrDSKH, BorderLayout.CENTER);

        // Tạo Box ngang để chứa phần tìm kiếm
        Box hBox = Box.createHorizontalBox();

        // Tạo JLabel, JTextField, JButton
        JLabel lbTimTenKH = new JLabel("Mã phòng:");
        lbTimTenKH.setFont(new Font("Tahoma", Font.BOLD, 13));

        JTextField txtTim = new JTextField();
        txtTim.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtTim.setPreferredSize(new Dimension(150, 20));
        txtTim.setMaximumSize(new Dimension(150, 20));

        JButton btnTim = new JButton("Tìm");
        btnTim.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnTim.setBackground(new Color(0, 255, 255));
        
        JButton btnXemTatCa = new JButton("Xem tất cả");
        btnXemTatCa.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnXemTatCa.setBackground(new Color(0, 255, 255));

        // Thêm các thành phần vào Box ngang
        hBox.add(lbTimTenKH);
        hBox.add(Box.createHorizontalStrut(10));
        hBox.add(txtTim);
        hBox.add(Box.createHorizontalStrut(10));
        hBox.add(btnTim);
        hBox.add(Box.createHorizontalStrut(10));
        hBox.add(btnXemTatCa);

        // Thêm Box ngang vào vị trí phía trên (NORTH) của pnlBang
        pnlBang.add(hBox, BorderLayout.NORTH);

        // Tạo JTable và cấu hình
        JTable table_1 = new JTable();
        table_1.setModel(new DefaultTableModel(new Object[][] {},
                new String[]{"Mã Phòng", "Tên Phòng", "Trạng Thái Phòng", "Mô Tả", "Tầng"}) {
            boolean[] columnEditables = new boolean[]{false, false, false, false, false};

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

        // Thêm JTable vào JScrollPane
        scrDSKH.setViewportView(table_1);

        // Tải dữ liệu vào bảng (thêm hàm loadDataToTable() ở đây nếu cần thiết)
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                QL_Phong frame = new QL_Phong();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
