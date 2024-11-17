package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.swing.border.TitledBorder;
import com.toedter.calendar.JDateChooser;

import bus.HoaDonBUS;
import bus.PhieuThuChiBUS;
import constraints.CONSTRAINTS;
import dal.DonDatPhongDAL;
import dal.HoaDonDAL;
import dal.KhachHangDAL;
import dal.KhuyenMaiDAL;
import dal.NhanVienDAL;
import dal.PhongDAL;
import entity.DonDatPhong;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.LoaiKhachHang;
import entity.NhanVien;
import entity.Phong;

import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

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
    private JButton btnXoa; // Nút xóa
    private JButton btnLamMoi; // Để làm mới thông tin
    private JButton btnTimKiem;
    private JLabel lblLoi;
    private JLabel lblLoi_Ten;
    private JLabel lblLoi_SDT;
    private JLabel lblLoi_Email;
    private JLabel lblLoi_CCCD;
    private JPanel panelForm;
    private JTextField txtMaHD;
    private JPanel pnlTableCTHD;
    private JScrollPane scrDSHD;
    private JTable table_CTHD;
    private JPanel pnlBang;
    private JTextField txtTongSL;
    private JTextField txtDiemTichDuoc;
    private JTextField txtTongThanhToan;
    private JDateChooser txtTimStartDate;
    private JDateChooser txtTimEndDate;
    private JRadioButton rdMa;
    private JRadioButton rdTen;
    private JRadioButton rdPhanTram;
    private JRadioButton rdTrangThai;
    private ButtonGroup gr2;
	private JTextField txtMaKM;
	private JTextField txtTenKM;
	private JTextField txtGiaTri;
	private JTextField txtSoLuong;
	private JComboBox<String> cboTrangThaiKM;
	private JComboBox<String> cboAD;
	private JDateChooser txtNgayTao;
	private JDateChooser txtNgayHetHan;
	DefaultTableModel model = new DefaultTableModel();
	private JButton btnLamLai;
	private JTextField txtmaDon;
	private JTextField txtmaHoaDon;
	private JTextField txtthanhTien;
	private JComboBox<String> cboTrangThai;
	private JTextField txtmaNV;
	private JComboBox<String> comboBox;
	JComboBox<String> cboNhanVien = new JComboBox<String>();
	JComboBox<String> cboDonDatPhong = new JComboBox<String>();
	JComboBox<String> cboKhuyenMai = new JComboBox<String>();
	private JTextField txtmaHD;
	private JTextField txtTrangThai;
	private JTextField txtThanhTien2;
	private JComboBox<String> cboTrangThai2;


    public HoaDon2() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        
        loadDonDatPhongOptions();
        
        loadNhanVienOptions();
        loadKhuyenMaiOptions();
        
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
                "Hóa Đơn", TitledBorder.LEADING, TitledBorder.TOP, null, CONSTRAINTS.ORANGE));
        add(pnlThongTin);
        pnlThongTin.setLayout(new GridLayout(1, 1, 0, 0));

        // Form Panel
        panelForm = new JPanel();
        panelForm.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel lbMaHD = new JLabel("Mã hóa đơn:");
        lbMaHD.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbMaHD);

        txtmaHoaDon = new JTextField();
        txtmaHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panelForm.add(txtmaHoaDon);
        txtmaHoaDon.setColumns(10);

        JLabel lbngayTao = new JLabel("Tạo ngày:");
        lbngayTao.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbngayTao);

        JDateChooser ngayTao = new JDateChooser();
        panelForm.add(ngayTao);

        JLabel lbthanhTien = new JLabel("Thành tiền:");
        lbthanhTien.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbthanhTien);

        txtthanhTien = new JTextField();
        txtthanhTien.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panelForm.add(txtthanhTien);
        txtthanhTien.setColumns(10);

        JLabel lbTrangThai = new JLabel("Trạng Thái Hóa Đơn:");
        lbTrangThai.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbTrangThai.setBounds(10, 151, 50, 14);
        panelForm.add(lbTrangThai);
        cboTrangThai = new JComboBox<String>();
        cboTrangThai.setFont(new Font("Tahoma", Font.BOLD, 13));
        cboTrangThai.setBounds(145, 154, 205, 20);
        cboTrangThai.addItem("Đã Thanh Toán");
        cboTrangThai.addItem("Chưa Thanh Toán");
        panelForm.add(cboTrangThai);
        
        JLabel lbDonDatPhong = new JLabel("Mã Đơn Đặt Phòng");
        lbDonDatPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbDonDatPhong);
        cboDonDatPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
        cboDonDatPhong.setBounds(145, 154, 205, 20);
        panelForm.add(cboDonDatPhong);
        
        
        JLabel lbNhanVien = new JLabel("Mã Nhân Viên:");
        lbNhanVien.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbNhanVien.setBounds(10, 151, 50, 14);
        panelForm.add(lbNhanVien);
        
        cboNhanVien.setFont(new Font("Tahoma", Font.BOLD, 13));
        cboNhanVien.setBounds(145, 154, 205, 20);
        panelForm.add(cboNhanVien);
        
        JLabel lbKhuyenMai = new JLabel("Mã Khuyến Mãi:");
        lbKhuyenMai.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbKhuyenMai.setBounds(10, 151, 50, 14);
        panelForm.add(lbKhuyenMai);
        
        cboKhuyenMai.setFont(new Font("Tahoma", Font.BOLD, 13));
        cboKhuyenMai.setBounds(145, 154, 205, 20);
        panelForm.add(cboKhuyenMai);
        
        JLabel null1 = new JLabel();
        panelForm.add(null1);
        JLabel null2 = new JLabel();
        panelForm.add(null2);
        

        

        pnlThongTin.add(panelForm);

        // Button Panel
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 255, 255));
        panel_2.setBorder(new TitledBorder(null, "Chức năng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_2.setLayout(new FlowLayout());

        btnThem = new JButton("THÊM");
        btnThem.setBackground(new Color(0, 255, 0));
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 19));
        panel_2.add(btnThem);

        btnSua = new JButton("SỬA");
        btnSua.setBackground(new Color(255, 255, 0));
        btnSua.setFont(new Font("Tahoma", Font.BOLD, 19));
        panel_2.add(btnSua);

        btnXoa = new JButton("XÓA");
        btnXoa.setBackground(new Color(255, 0, 0));
        btnXoa.setFont(new Font("Tahoma", Font.BOLD, 19));
        panel_2.add(btnXoa);

         btnLamLai = new JButton("Làm lại");
        btnLamLai.setBackground(new Color(0, 204, 204));
        btnLamLai.setFont(new Font("Tahoma", Font.BOLD, 19));
        panel_2.add(btnLamLai);

        pnlThongTin.add(panel_2);

        // Main Table Panel
        pnlBang = new JPanel();
        pnlBang.setBackground(new Color(255, 255, 255));
        pnlBang.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE),
                "Thiết lập thông tin hóa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, CONSTRAINTS.ORANGE));
        add(pnlBang);
        pnlBang.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Tạo JScrollPane
        JScrollPane scrDSHD = new JScrollPane();
        mainPanel.add(scrDSHD, BorderLayout.CENTER);

        // Tạo JPanel cho phần bên phải
        JPanel pnlCT = new JPanel();
        pnlCT.setBackground(new Color(240, 240, 240));
        pnlCT.setPreferredSize(new Dimension(900, 600));
        mainPanel.add(pnlCT, BorderLayout.EAST);
        pnlCT.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE),
                "Thiết lập thông tin chi tiết hóa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, CONSTRAINTS.ORANGE));
        
     // Tạo JPanel cho phần form
        JPanel panelForm = new JPanel();
        panelForm.setLayout(new GridLayout(13, 2, 10, 10)); // 8 hàng, 2 cột với khoảng cách 10 giữa các thành phần
        panelForm.setPreferredSize(new Dimension(900, 600));
        
        JLabel lbnull1 = new JLabel();
        JLabel lbnull2 = new JLabel();
        JLabel lbnull3 = new JLabel();
        JLabel lbnull4 = new JLabel();

        panelForm.add(lbnull1);
        panelForm.add(lbnull2);
        panelForm.add(lbnull3);
        panelForm.add(lbnull4);
        // Mã KM
//        JLabel lbMaKM = new JLabel("Mã KM:");
//        lbMaKM.setFont(new Font("Tahoma", Font.BOLD, 13));
//        
//        panelForm.add(lbMaKM);
//
//        txtMaKM = new JTextField("KH******");
//        txtMaKM.setFont(new Font("Tahoma", Font.PLAIN, 13));
//        txtMaKM.setEditable(false);
//        panelForm.add(txtMaKM);
//        txtMaKM.setColumns(10);

        // Tên KM
        JLabel lbMaHĐ = new JLabel("Mã Hóa Đơn:");
        lbMaHĐ.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbMaHĐ);

        txtmaHD = new JTextField("");
        txtmaHD.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panelForm.add(txtmaHD);
        txtmaHD.setColumns(10);

        // Giá trị
        JLabel lbTrangThai2 = new JLabel("Trạng Thái Hóa Đơn:");
        lbTrangThai2.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbTrangThai2.setBounds(10, 151, 50, 14);
        panelForm.add(lbTrangThai2);
        cboTrangThai2 = new JComboBox<String>();
        cboTrangThai2.setFont(new Font("Tahoma", Font.BOLD, 13));
        cboTrangThai2.setBounds(145, 154, 205, 20);
        cboTrangThai2.addItem("Đã Thanh Toán");
        cboTrangThai2.addItem("Chưa Thanh Toán");
        cboTrangThai2.setEnabled(false);
        panelForm.add(cboTrangThai);

        // Số lượng
        JLabel lbThanhTien = new JLabel("ThanhTien:");
        lbThanhTien.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbThanhTien);

        txtThanhTien2 = new JTextField();
        txtThanhTien2.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(txtThanhTien2);
        txtThanhTien2.setColumns(10);

        // Trạng Thái Khuyến Mãi
        

        // Khách hàng áp dụng
        JLabel lbAD = new JLabel("Khách hàng áp dụng :");
        lbAD.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbAD);

        cboAD = new JComboBox<String>();
        cboAD.setFont(new Font("Tahoma", Font.BOLD, 13));
        cboAD.addItem("VIP");
        cboAD.addItem("Regular");
        panelForm.add(cboAD);

        // Ngày tạo khuyến mãi
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
        
        JButton btnXuatHoaDon = new JButton("Xuất Hóa Đơn");
        btnXuatHoaDon.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(btnXuatHoaDon);
        btnXuatHoaDon.setPreferredSize(new Dimension(400, 20)); // Thay đổi kích thước theo ý muốn


        // Thêm panelForm vào pnlCT
        pnlCT.add(panelForm);

        // Tạo một JPanel để chia đôi màn hình
        JPanel splitPanel = new JPanel();
        splitPanel.setLayout(new GridLayout(1, 2)); // Chia thành 2 cột

        // Thêm vào splitPanel các panel bạn cần
        splitPanel.add(scrDSHD);
        splitPanel.add(pnlCT);

        // Thêm splitPanel vào pnlBang
        pnlBang.add(splitPanel, BorderLayout.CENTER);
        
        // Search Panel for Table
        JPanel pnlTimKiemKM = new JPanel();
        FlowLayout flowLayout = (FlowLayout) pnlTimKiemKM.getLayout();
        flowLayout.setAlignment(FlowLayout.LEADING);
        JPanel rpnlDeTimKiemKM = new JPanel();
        pnlTimKiemKM.setBackground(new Color(240, 240, 240));
        pnlBang.add(pnlTimKiemKM, BorderLayout.NORTH);
        JPanel lpnlDeTimKiemKM = new JPanel();
        pnlTimKiemKM.add(lpnlDeTimKiemKM);
        pnlTimKiemKM.add(Box.createHorizontalStrut(50));
        pnlTimKiemKM.add(rpnlDeTimKiemKM);
        lpnlDeTimKiemKM.setLayout(new BoxLayout(lpnlDeTimKiemKM, BoxLayout.Y_AXIS));

        Box hBox1 = Box.createHorizontalBox();
        Box hBox2 = Box.createHorizontalBox();

        JLabel lbTimTenKH = new JLabel("Mã Hóa Đơn:");
        lbTimTenKH.setFont(new Font("Tahoma", Font.BOLD, 13));

        JTextField txtTim_1 = new JTextField();
        txtTim_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtTim_1.setPreferredSize(new Dimension(465, 25));
        txtTim_1.setMaximumSize(new Dimension(465, 25));

        hBox1.add(lbTimTenKH);
        hBox1.add(Box.createHorizontalStrut(395));
        hBox1.add(txtTim_1);

        // Box 2
        JLabel lblTimTheo = new JLabel("Tìm theo:");
        lblTimTheo.setFont(new Font("Tahoma", Font.BOLD, 13));
        hBox2.add(lblTimTheo);
        hBox2.add(Box.createHorizontalStrut(400));

        rdMa = new JRadioButton("Mã khuyến mãi");
        rdMa.setBackground(Color.WHITE);
        hBox2.add(rdMa);

        rdTen = new JRadioButton("Tên khuyến mãi");
        rdTen.setBackground(Color.WHITE);
        hBox2.add(rdTen);

        rdPhanTram = new JRadioButton("Phần trăm khuyến mãi");
        rdPhanTram.setBackground(Color.WHITE);
        hBox2.add(rdPhanTram);

        rdTrangThai = new JRadioButton("Trạng thái");
        rdTrangThai.setBackground(Color.WHITE);
        hBox2.add(rdTrangThai);

        gr2 = new ButtonGroup();
        gr2.add(rdMa);
        gr2.add(rdTen);
        gr2.add(rdPhanTram);
        gr2.add(rdTrangThai);

        JButton btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setBackground(new Color(0, 153, 255));
        btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 13));
        hBox2.add(btnTimKiem);
        
        

        // Add boxes to panel
        lpnlDeTimKiemKM.add(hBox1);
        lpnlDeTimKiemKM.add(hBox2);

        // JTable setup
        
        model.addColumn("Mã HĐ");
        model.addColumn("Trạng Thái");
        model.addColumn("Thành Tiền");
        model.addColumn("Mã Nhân Viên");
        model.addColumn("Mã Khuyến Mãi");
        model.addColumn("Mã Đơn Đặt Phòng");
        model.addColumn("Ngày Tạo");

        
        table_CTHD = new JTable(model);
        scrDSHD.setViewportView(table_CTHD);
        btnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				themHoaDon();
				
			}
		});
        hienThiDuLieu();
        // Thêm hành động cho nút tìm kiếm

    }
    public void hienThiDuLieu() {
    	ArrayList<HoaDon> dsHoaDon;
    	dsHoaDon = new HoaDonDAL().getAllHoaDon();
    	for (HoaDon hd : dsHoaDon) {
            Object[] row = {
                hd.getMaHoaDon(),
                hd.getTrangThai() ? "Đã thanh toán" : "Chưa thanh toán",
                hd.getThanhTien(),
                hd.getNhanVien() != null ? hd.getNhanVien().getMaNV() : "Không có nhân viên",
                hd.getKhuyenMai() != null ? hd.getKhuyenMai().getMaKhuyenMai() : "Không có khuyến mãi",
                hd.getDonDatPhong() != null ? hd.getDonDatPhong().getMaDon() : "Không có đơn đặt phòng",
                hd.getNgayTao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            };
            model.addRow(row);
        }
    }
    private void loadNhanVienOptions() {
        // Assuming you have a NhanVienDAL class to handle the database operations
        NhanVienDAL nhanVienDAL = new NhanVienDAL(); // Create an instance of the DAL
        ArrayList<NhanVien> dsNhanVien = nhanVienDAL.getAllNhanVien(); // Get all employees from the DAL

        // Clear existing items in the combo box (optional, if you want to refresh it)
        cboNhanVien.removeAllItems();

        // Add each NhanVien object to the combo box
        for (NhanVien nhanVien : dsNhanVien) {
            cboNhanVien.addItem(nhanVien.getMaNV()); // Assuming getMaNV() returns the employee ID
        }
    }
    private void loadKhuyenMaiOptions() {
        // Assuming you have a NhanVienDAL class to handle the database operations
        KhuyenMaiDAL khuyenMaiDAL = new KhuyenMaiDAL(); // Create an instance of the DAL
        ArrayList<KhuyenMai> dskhuyenMai = khuyenMaiDAL.getAllKhuyenMai(); // Get all employees from the DAL

        // Clear existing items in the combo box (optional, if you want to refresh it)
        cboKhuyenMai.removeAllItems();

        // Add each NhanVien object to the combo box
        for (KhuyenMai khuyenMai : dskhuyenMai) {
            cboNhanVien.addItem(khuyenMai.getMaKhuyenMai()); // Assuming getMaNV() returns the employee ID
        }
    }
    private void loadDonDatPhongOptions() {
        // Assuming you have a NhanVienDAL class to handle the database operations
        DonDatPhongDAL donDatPhongDAL = new DonDatPhongDAL(); // Create an instance of the DAL
        ArrayList<DonDatPhong> dsDonDatPhong = donDatPhongDAL.getAllDonDatPhong(); // Get all employees from the DAL

        // Clear existing items in the combo box (optional, if you want to refresh it)
        cboNhanVien.removeAllItems();

        // Add each NhanVien object to the combo box
        for (DonDatPhong donDatPhong : dsDonDatPhong) {
            cboDonDatPhong.addItem(donDatPhong.getMaDon()); // Assuming getMaNV() returns the employee ID
        }
    }


 // Inside the HoaDon2 class (or relevant class)
    private String generateNewMaHD(String lastMaHD) {
        // Assuming the invoice code is of the format "HD000001", "HD000002", etc.
        if (lastMaHD == null || lastMaHD.isEmpty()) {
            return "HD000001"; // Start with a default code if there is no last code.
        }
        
        // Extract the numeric part from the last invoice code
        String numericPart = lastMaHD.substring(2); // Skip the "HD" prefix
        int newNumber = Integer.parseInt(numericPart) + 1; // Increment the number

        // Format the new number to maintain the same length
        return String.format("HD%06d", newNumber); // Format to "HDxxxxxx"
    }

    private void themHoaDon() {
        try {
            HoaDonDAL hoaDonDAL = new HoaDonDAL();
            String lastMaHD = hoaDonDAL.getLastMaHD();
            String newMaHD = generateNewMaHD(lastMaHD);

            // Lấy thông tin từ giao diện người dùng
            String maHoaDon = newMaHD; // Sử dụng mã hóa đơn mới được tạo
            double thanhTien = Double.parseDouble(txtthanhTien.getText());
            boolean trangThai = cboTrangThai.getSelectedItem().equals("Đã thanh toán");

            // Lấy các đối tượng liên quan
            NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa((String) cboNhanVien.getSelectedItem());
            KhuyenMai khuyenMai = new KhuyenMaiDAL().getKhuyenMaiTheoMa((String) cboKhuyenMai.getSelectedItem());
            DonDatPhong donDatPhong = new DonDatPhongDAL().getDonDatPhongTheoMa((String) cboDonDatPhong.getSelectedItem());
            LocalDate ngayTao = LocalDate.now();

            // Tạo đối tượng hóa đơn
            HoaDon hoaDon = new HoaDon(maHoaDon, trangThai, thanhTien, nhanVien, khuyenMai, donDatPhong, ngayTao);
            
            // Thêm hóa đơn vào cơ sở dữ liệu
            boolean isSuccess = hoaDonDAL.themHoaDon(hoaDon);
            if (isSuccess) {
                model.addRow(new Object[]{
                    maHoaDon,
                    thanhTien,
                    trangThai,
                    nhanVien.getHoten(), // Lấy tên nhân viên
                    khuyenMai != null ? khuyenMai.getMaKhuyenMai() : null, // Kiểm tra khuyến mãi
                    donDatPhong.getMaDon(), // Mã đơn đặt phòng
                    ngayTao
                });
                clearFields(); // Gọi phương thức xóa các trường
                JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công!");
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi khi thêm hóa đơn.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi xảy ra: " + ex.getMessage());
        }
    }


    private void updateTable() {
        
        
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Mã Hóa Đơn");
            model.addColumn("Ngày Tạo");
            model.addColumn("Thành Tiền");
            model.addColumn("Trạng Thái");
            table_CTHD.setModel(model);


        }
    private void clearFields() {
        txtmaHoaDon.setText("");
        txtthanhTien.setText("");
        cboTrangThai.setSelectedIndex(0);
        cboNhanVien.setSelectedIndex(0);
        cboKhuyenMai.setSelectedIndex(0);
        cboDonDatPhong.setSelectedIndex(0);
    }
    

    public static void main(String[] args) {
        JFrame frame = new JFrame("Quản lý Hóa Đơn");
        HoaDon2 panel = new HoaDon2();
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 600);
        
    }
}
