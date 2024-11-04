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
import dal.NhanVienDAL;
import entity.DonDatPhong;
import entity.HoaDon;
import entity.KhuyenMai;
import entity.NhanVien;

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

        txtmaHoaDon = new JTextField();
        txtmaHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panelForm.add(txtmaHoaDon);
        txtmaHoaDon.setColumns(10);

        JLabel lbngayTao = new JLabel("Từ ngày:");
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
        cboTrangThai.addItem("Còn");
        cboTrangThai.addItem("Hết hạn");
        panelForm.add(cboTrangThai);
        
        JLabel lbSDTKH = new JLabel("Mã Đơn Đặt Phòng");
        lbSDTKH.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbSDTKH);
        txtmaDon = new JTextField();
        txtmaDon.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panelForm.add(txtmaDon);
        txtmaDon.setColumns(10);
        
        
        JLabel lbnaNV = new JLabel("Mã Nhân Viên");
        lbnaNV.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbnaNV);
        txtmaNV = new JTextField();
        txtmaNV.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panelForm.add(txtmaNV);
        txtmaNV.setColumns(10);
        

        

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
        JLabel lbMaKM = new JLabel("Mã KM:");
        lbMaKM.setFont(new Font("Tahoma", Font.BOLD, 13));
        
        panelForm.add(lbMaKM);

        txtMaKM = new JTextField("KH******");
        txtMaKM.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtMaKM.setEditable(false);
        panelForm.add(txtMaKM);
        txtMaKM.setColumns(10);

        // Tên KM
        JLabel lbTenKM = new JLabel("Tên KM:");
        lbTenKM.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbTenKM);

        txtTenKM = new JTextField("");
        txtTenKM.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panelForm.add(txtTenKM);
        txtTenKM.setColumns(10);

        // Giá trị
        JLabel lbGiaTri = new JLabel("Giá trị:");
        lbGiaTri.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbGiaTri);

        txtGiaTri = new JTextField();
        txtGiaTri.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(txtGiaTri);
        txtGiaTri.setColumns(10);

        // Số lượng
        JLabel lbSoLuong = new JLabel("Số lượng:");
        lbSoLuong.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbSoLuong);

        txtSoLuong = new JTextField();
        txtSoLuong.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(txtSoLuong);
        txtSoLuong.setColumns(10);

        // Trạng Thái Khuyến Mãi
        JLabel lbTrangThaiKM = new JLabel("Trạng Thái Khuyến Mãi:");
        lbTrangThaiKM.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbTrangThaiKM);

        cboTrangThaiKM = new JComboBox<String>();
        cboTrangThaiKM.setFont(new Font("Tahoma", Font.BOLD, 13));
        cboTrangThaiKM.addItem("Còn");
        cboTrangThaiKM.addItem("Hết hạn");
        panelForm.add(cboTrangThaiKM);

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
        
        btnTimKiem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String maHD = txtmaHoaDon.getText().trim(); // Lấy mã phiếu từ JTextField

		        // Kiểm tra nếu mã phiếu rỗng hoặc là giá trị mặc định
		        if (maHD.isEmpty() || maHD.equals("nhập vào mã hóa đơn")) {
		            // Nếu mã phiếu rỗng, gọi hàm updateTable() với ngày đầu và ngày cuối
		            updateTable();
		        } else {
		        	HoaDonBUS hoaDonBUS = new HoaDonBUS();
		        	   Object[][] data = new Object[1][8];
		        	   data = hoaDonBUS.layDuLieuBangTim(maHD);
		            if (data != null) {
		              
		                table_1.setModel(new DefaultTableModel(data, new String[]{
		                		"Mã phiếu thu chi", // Column 1
		                	    "Tên nhân viên",     // Column 2
		                	    "Ngày tạo",         // Column 3
		                	    "Loại phiếu",       // Column 4
		                	    "Trạng thái",       // Column 5
		                	    "Phương thức",      // Column 6
		                	    "Số tiền",          // Column 7
		                	    "Mô tả"             // Column 8
		                	    }));
		            } else {
		                // Thông báo nếu không tìm thấy phiếu thu chi
		                JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn với mã này.");
		            }
		        }
		    }
		});

        // Add boxes to panel
        lpnlDeTimKiemKM.add(hBox1);
        lpnlDeTimKiemKM.add(hBox2);

        // JTable setup
        
        model.addColumn("Mã HĐ");
        model.addColumn("Tên KH");
        model.addColumn("Địa chỉ");
        model.addColumn("Số ĐT");
        model.addColumn("Ngày lập");
        model.addColumn("Tổng thanh toán");
        table_CTHD = new JTable(model);
        scrDSHD.setViewportView(table_CTHD);
        hienThiDuLieu();
        // Thêm hành động cho nút tìm kiếm
        btnThem.addActionListener(new ActionListener() {
            private String maNV;
			private KhuyenMai khuyenMai;
			private DonDatPhong donDatPhong;
			private double thanhTien;
			private boolean trangThai;

			@Override
            public void actionPerformed(ActionEvent e) {
                // Tạo một đối tượng HoaDonDAL để thao tác với cơ sở dữ liệu
                HoaDonDAL hoaDonDAL = new HoaDonDAL();

                // Lấy thông tin từ các trường nhập liệu (như JTextField, JComboBox, v.v.)
                String maHoaDon = txtmaHoaDon.getText(); // Giả sử có JTextField cho mã hóa đơn             
                String maKM = txtMaKM.getText(); // Giả sử có JTextField cho mã khuyến mãi
                String maDon = txtmaDon.getText(); // Giả sử có JTextField cho mã đơn đặt phòng

             // Lấy nhân viên theo mã
                NhanVienDAL nvDal = new NhanVienDAL();
                NhanVien nhanVien = nvDal.getNhanVienTheoMa(maNV);
                
                if (nhanVien == null) {
                    JOptionPane.showMessageDialog(null, "Mã hóa đơn không hợp lệ.");
                    return;
                }
                LocalDate ngayTao = LocalDate.now();
                double soTien;
                
                // Lấy số tiền và xử lý
                try {
                    String soTienStr = txtthanhTien.getText().trim();
                    if (soTienStr.equals("nhập tiền tại đây") || soTienStr.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập số tiền.");
                        return;
                    }
                    soTien = Double.parseDouble(soTienStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Số tiền không hợp lệ.");
                    return;
                }
                entity.HoaDon hoaDon = new entity.HoaDon(maHoaDon,trangThai,thanhTien,nhanVien,khuyenMai,donDatPhong,ngayTao);
                
                HoaDonBUS hoaDonBUS = new HoaDonBUS();
                boolean isSuccess = hoaDonBUS.themHoaDon(hoaDon);
                
                if (isSuccess) {
                    JOptionPane.showMessageDialog(null, "Thêm phiếu chi thành công.");
                    // Cập nhật lại bảng nếu cần
                    txtthanhTien.setText(""); // Đặt ô số tiền về rỗng
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm phiếu chi thất bại.");
                }
            }
        });

        
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
    private void updateTable() {
        
        
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Mã Hóa Đơn");
            model.addColumn("Ngày Tạo");
            model.addColumn("Thành Tiền");
            model.addColumn("Trạng Thái");
            table_CTHD.setModel(model);


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
