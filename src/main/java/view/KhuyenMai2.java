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

public class KhuyenMai2 extends JPanel {

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
	private JButton btnLamMoi;
	private JRadioButton rdMa;
	private JRadioButton rdTen;
	private JRadioButton rdPhanTram;
	private JRadioButton rdTrangThai;
	private ButtonGroup gr1;
	private ButtonGroup gr2;
	private JDateChooser txtTimStartDate;
	private JDateChooser txtTimEndDate;
	private JTextField txtMaKM;
	private JTextField txtGiaTri;
	private JTextField txtSoLuong;
	private JComboBox<String> cboTrangThaiKM;
	private JComboBox<String> cboAD;
	private JDateChooser txtNgayTao;
	private JDateChooser txtNgayHetHan;
	private JTextField txtTenKM;

    public KhuyenMai2() {
    	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(255, 255, 255));
        setBorder(new EmptyBorder(5, 5, 5, 5));

        

        JPanel pnlTieuDe = new JPanel();
        pnlTieuDe.setBackground(new Color(255, 255, 255));
        add(pnlTieuDe);
        pnlTieuDe.setLayout(new FlowLayout());

        JLabel lblTieuDeTrang = new JLabel("QUẢN LÝ KHUYẾN MÃI");
        lblTieuDeTrang.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDeTrang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        pnlTieuDe.add(lblTieuDeTrang);

        JPanel pnlThongTin = new JPanel();
        pnlThongTin.setBackground(new Color(255, 255, 255));
        pnlThongTin.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE),
                "Thiết lập thông tin khuyến mãi", TitledBorder.LEADING, TitledBorder.TOP, null,CONSTRAINTS.ORANGE));
       
        add(pnlThongTin);
        pnlThongTin.setLayout(new GridLayout(1, 1, 0, 0));
        
        panelForm = new JPanel();
        panelForm.setLayout(new GridLayout(8,1,10,10));
        //Ma KM
        JLabel lbMaKM = new JLabel("Mã KM:");
        lbMaKM.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbMaKM.setBounds(10, 21, 50, 14);
        panelForm.add(lbMaKM);

        txtMaKM = new JTextField("KH******");
        txtMaKM.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtMaKM.setEditable(false);
        txtMaKM.setBounds(145, 18, 205, 20);
        panelForm.add(txtMaKM);
        txtMaKM.setColumns(10);
        //Ten KM
        
        JLabel lbTenKM = new JLabel("Tên KM:");
        lbTenKM.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbTenKM.setBounds(10, 21, 50, 14);
        panelForm.add(lbTenKM);

        txtTenKM = new JTextField("");
        txtTenKM.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtTenKM.setEditable(true);
        txtTenKM.setBounds(145, 18, 205, 20);
        panelForm.add(txtTenKM);
        txtTenKM.setColumns(10);
        
        
        JLabel lbGiaTri = new JLabel("Giá trị:");
        lbGiaTri.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbGiaTri.setBounds(10, 100, 50, 14);
        panelForm.add(lbGiaTri);
        
        txtGiaTri = new JTextField();
        txtGiaTri.setFont(new Font("Tahoma", Font.BOLD, 13));
        txtGiaTri.setBounds(145, 97, 205, 20);
        panelForm.add(txtGiaTri);
        txtGiaTri.setColumns(10);
        
        JLabel lbSoLuong = new JLabel("Số lượng:");
        lbSoLuong.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbSoLuong.setBounds(10, 100, 50, 14);
        panelForm.add(lbSoLuong);
        
        txtSoLuong = new JTextField();
        txtSoLuong.setFont(new Font("Tahoma", Font.BOLD, 13));
        txtSoLuong.setBounds(145, 97, 205, 20);
        panelForm.add(txtSoLuong);
        txtSoLuong.setColumns(10);

        
        
        
        
        JLabel lbTrangThaiKM = new JLabel("Trạng Thái Khuyến Mãi:");
        lbTrangThaiKM.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbTrangThaiKM.setBounds(10, 151, 50, 14);
        panelForm.add(lbTrangThaiKM);
        cboTrangThaiKM = new JComboBox<String>();
        cboTrangThaiKM.setFont(new Font("Tahoma", Font.BOLD, 13));
        cboTrangThaiKM.setBounds(145, 154, 205, 20);
        cboTrangThaiKM.addItem("Còn");
        cboTrangThaiKM.addItem("Hết hạn");
        panelForm.add(cboTrangThaiKM);
        
        JLabel lbAD = new JLabel("Khách hàng áp dụng :");
        lbAD.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbAD.setBounds(10, 151, 50, 14);
        panelForm.add(lbAD);
        cboAD = new JComboBox<String>();
        cboAD.setFont(new Font("Tahoma", Font.BOLD, 13));
        cboAD.setBounds(145, 154, 205, 20);
        cboAD.addItem("VIP");
        cboAD.addItem("Regular");
        panelForm.add(cboAD);
        
        JLabel lblNgayTao = new JLabel("Ngày tạo khuyến mãi");
        lblNgayTao.setFont(new Font("Times New Roman", Font.BOLD, 12));
        panelForm.add(lblNgayTao);

        txtNgayTao = new JDateChooser();
        txtNgayTao.setDateFormatString("dd/MM/yyyy");
        panelForm.add(txtNgayTao);
		
     // Ngày hết hạn khuyến mãi
        JLabel lblNgayHetHan = new JLabel("Ngày hết hạn khuyến mãi");
        lblNgayHetHan.setFont(new Font("Times New Roman", Font.BOLD, 12));
        panelForm.add(lblNgayHetHan);
        
        txtNgayHetHan = new JDateChooser();
        txtNgayHetHan.setDateFormatString("dd/MM/yyyy");
        panelForm.add(txtNgayHetHan);

        

        panel_DSSP = new JPanel();
		panel_DSSP.setBorder(new TitledBorder(null, "Danh s\u00E1ch s\u1EA3n ph\u1EA9m", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_DSSP.setBackground(new Color(255, 255, 255));
		panel_DSSP.setBounds(498, 22, 537, 255);
		panel_DSSP.setLayout(new BorderLayout());

		lblTimKiemSP = new JLabel("T\u00ECm ki\u1EBFm s\u1EA3n ph\u1EA9m");
		lblTimKiemSP.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblTimKiemSP.setBounds(10, 20, 148, 15);
		panel_DSSP.add(lblTimKiemSP , BorderLayout.NORTH);

		cbbTimKiemSP = new JComboBox();
		cbbTimKiemSP.setEditable(true);
		cbbTimKiemSP.setBounds(131, 15, 396, 25);
		panel_DSSP.add(cbbTimKiemSP);

		JScrollPane scrDSSP;
		String[] head = new String[] { "Mã sản phẩm", "Tên sản phẩm", "Chọn" };
		tablemodel = new DefaultTableModel(head, 0);
		table_SP = new JTable(tablemodel);
		table_SP.setEnabled(false);

		table_SP.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		table_SP.setBackground(new Color(204, 255, 255));
		table_SP.setForeground(new Color(0, 0, 0));
		add(scrDSSP = new JScrollPane(table_SP, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);
		scrDSSP.setBounds(10, 45, 920, 200);
		panel_DSSP.add(scrDSSP,BorderLayout.CENTER);
		scrDSSP.setPreferredSize(new Dimension(0, 250));
        
        
        pnlThongTin.add(panelForm);
        pnlThongTin.add(panel_DSSP);
        
        
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
                "Thiết lập thông tin phòng", TitledBorder.LEADING, TitledBorder.TOP, null,CONSTRAINTS.ORANGE));
        add(pnlBang);
        pnlBang.setLayout(new BorderLayout());
        
        
        JScrollPane scrDSKM = new JScrollPane();
        pnlBang.add(scrDSKM, BorderLayout.CENTER);

        JPanel pnlTimKiemKM = new JPanel();
        FlowLayout flowLayout = (FlowLayout) pnlTimKiemKM.getLayout();
        flowLayout.setAlignment(FlowLayout.LEADING);
        JPanel rpnlDeTimKiemKM = new JPanel();
        pnlTimKiemKM.setBackground(new Color(240, 240, 240));
        pnlBang.add(pnlTimKiemKM,BorderLayout.NORTH);
        JPanel lpnlDeTimKiemKM = new JPanel();
        pnlTimKiemKM.add(lpnlDeTimKiemKM);
        pnlTimKiemKM.add(Box.createHorizontalStrut(50));
        pnlTimKiemKM.add(rpnlDeTimKiemKM);
        lpnlDeTimKiemKM.setLayout(new BoxLayout(lpnlDeTimKiemKM, BoxLayout.Y_AXIS));
        
        Box hBox1 = Box.createHorizontalBox();
        Box hBox2 = Box.createHorizontalBox();
        

        JLabel lbTimTenKH = new JLabel("Mã phòng:");
        lbTimTenKH.setFont(new Font("Tahoma", Font.BOLD, 13));
        
                JTextField txtTim_1 = new JTextField();
                txtTim_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
                txtTim_1.setPreferredSize(new Dimension(465, 25));
                txtTim_1.setMaximumSize(new Dimension(465, 25));
                
                hBox1.add(lbTimTenKH);
                hBox1.add(Box.createHorizontalStrut(395));
                hBox1.add(txtTim_1);
                
                //BOX2
                JLabel lblTimTheo = new JLabel("Tìm theo:");
                lblTimTheo.setFont(new Font("Tahoma", Font.BOLD, 13));
                lblTimTheo.setBounds(20, 54, 99, 15);
                hBox2.add(lblTimTheo);
                hBox2.add(Box.createHorizontalStrut(400));
                
                rdMa = new JRadioButton("Mã khuyến mãi");
                rdMa.setBackground(Color.WHITE);
                rdMa.setBounds(172, 51, 116, 21);
                hBox2.add(rdMa);
		
				rdTen = new JRadioButton("Tên khuyến mãi");
				rdTen.setBackground(Color.WHITE);
				rdTen.setBounds(290, 52, 134, 21);
				hBox2.add(rdTen);
				
				rdPhanTram = new JRadioButton("Phần trăm khuyến mãi");
				rdPhanTram.setBackground(Color.WHITE);
				rdPhanTram.setBounds(426, 51, 164, 21);
				hBox2.add(rdPhanTram);
						
				rdTrangThai = new JRadioButton("Trạng thái");
				rdTrangThai.setBackground(Color.WHITE);
				rdTrangThai.setBounds(592, 52, 91, 21);
				hBox2.add(rdTrangThai);
				
				
				gr2 = new ButtonGroup();
				gr2.add(rdMa);
				gr2.add(rdTen);
				gr2.add(rdPhanTram);
				gr2.add(rdTrangThai);
								
								
        lpnlDeTimKiemKM.add(hBox1);
        lpnlDeTimKiemKM.add(Box.createVerticalStrut(20));
        lpnlDeTimKiemKM.add(hBox2);
        
        rpnlDeTimKiemKM.setLayout(new GridLayout(2,3,20,20));

		

		
        
        JLabel lblNgayBatDau = new JLabel("Ng\u00E0y b\u1EAFt \u0111\u1EA7u");
		lblNgayBatDau.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNgayBatDau.setBounds(729, 22, 84, 15);
		rpnlDeTimKiemKM.add(lblNgayBatDau);

		
        
        
        txtTimStartDate = new JDateChooser();
		txtTimStartDate.getCalendarButton().setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtTimStartDate.setLocale(new Locale("vi", "VN"));
		txtTimStartDate.setForeground(Color.BLACK);
		txtTimStartDate.setDateFormatString("dd/MM/yyyy");
		txtTimStartDate.setBounds(834, 22, 140, 18);
		rpnlDeTimKiemKM.add(txtTimStartDate);

		
        
        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setBackground(new Color(0, 255, 255));
        btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 13));
        rpnlDeTimKiemKM.add(btnTimKiem);

        btnLamMoi = new JButton("Làm mới");
        btnLamMoi.setBackground(new Color(0, 255, 255));
        btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 13));
        rpnlDeTimKiemKM.add(btnLamMoi);
        
        JLabel lblNgayKetThuc = new JLabel("Ng\u00E0y k\u1EBFt th\u00FAc");
		lblNgayKetThuc.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNgayKetThuc.setBounds(729, 54, 84, 15);
		rpnlDeTimKiemKM.add(lblNgayKetThuc);
        txtTimEndDate = new JDateChooser();
		txtTimEndDate.getCalendarButton().setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtTimEndDate.setLocale(new Locale("vi", "VN"));
		txtTimEndDate.setForeground(Color.BLACK);
		txtTimEndDate.setDateFormatString("dd/MM/yyyy");
		txtTimEndDate.setBounds(834, 51, 140, 18);
		rpnlDeTimKiemKM.add(txtTimEndDate);
        
        JLabel lbNull1 = new JLabel();
        rpnlDeTimKiemKM.add(lbNull1);
        
        JLabel lbNull2 = new JLabel();
        rpnlDeTimKiemKM.add(lbNull2);
        
        // Tạo JTable và cấu hình
        JTable table_1 = new JTable();
        table_1.setModel(new DefaultTableModel(new Object[][] {},
                new String[]{"Mã KM", "Tên KM", "Khách Hàng Áp Dụng", "Ngày Tạo", "Ngày Hết Hạn"}) {
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
        scrDSKM.setViewportView(table_1);

        // Tải dữ liệu vào bảng (thêm hàm loadDataToTable() ở đây nếu cần thiết)
    }

    public static void main(String[] args) {
    	EventQueue.invokeLater(() -> {
            try {
                JFrame frame = new JFrame("Quản lí khuyến mãi");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(new KhuyenMai2());
                frame.pack();
                frame.setSize(800, 600);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
