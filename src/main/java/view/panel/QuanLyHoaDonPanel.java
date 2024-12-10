package view.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.FontFactory;


import java.io.FileOutputStream;


import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.io.FileOutputStream;


import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import com.toedter.calendar.JDateChooser;

import constant.CommonConstants;
import dal.DichVuDAL;
import dal.DonDatPhongDAL;
import dal.HoaDonDAL;
import dal.KhuyenMaiDAL;
import dal.NhanVienDAL;
import dal.PhongDAL;
import database.ConnectDB;
import entity.DichVu;
import entity.DonDatPhong;
import entity.HoaDon;
import entity.KhuyenMai;
import entity.NhanVien;
import entity.Phong;
import view.dialog.HoaDonExporter;
import view.dialog.ThemHoaDonDiaLog;
import view.dialog.ThemKhachHangDiaLog;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.PatternSyntaxException;

public class QuanLyHoaDonPanel extends JPanel {

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

	private JTextField txtmaNV;
	private JComboBox<String> comboBox;
	JComboBox<String> cboNhanVien = new JComboBox<String>();
	JComboBox<String> cboDonDatPhong = new JComboBox<String>();
	JComboBox<String> cboKhuyenMai = new JComboBox<String>();
	JComboBox<String> cboTrangThai = new JComboBox<String>(); // Khởi tạo
	private JTextField txtmaHD;
	private JTextField txtTrangThai;
	private JTextField txtThanhTien2;
	private JComboBox<String> cboTrangThai2;
	private JButton btnLuu;
	private DefaultTableModel tableModel;
	private JTextField txtTim;
	private JButton btnXuat;


    public QuanLyHoaDonPanel() {
    	setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel pnlTieuDe = new JPanel();
        pnlTieuDe.setBackground(Color.WHITE);
        
        pnlTieuDe.setLayout(new FlowLayout());

        JLabel lblTieuDeTrang = new JLabel("QUẢN LÝ KHÁCH HÀNG");
        lblTieuDeTrang.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDeTrang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        pnlTieuDe.add(lblTieuDeTrang);

        JPanel pnlThongTin = new JPanel();
        pnlThongTin.setBackground(Color.WHITE);
        pnlThongTin.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE),
                "Thiết lập thông tin khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, CommonConstants.ORANGE));

        add(pnlThongTin);
        pnlThongTin.setLayout(new BorderLayout());

        panelForm = new JPanel();
        panelForm.setLayout(new GridLayout(3, 1, 10, 10));

        JLabel lbMaHD = new JLabel("Mã hóa đơn:");
        lbMaHD.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbMaHD);

        txtMaHD = new JTextField("");
        txtMaHD.setFont(new Font("Tahoma", Font.PLAIN, 13));
        
        panelForm.add(txtMaHD);
        
        JLabel lbConHoatDong = new JLabel("Trạng Thái Hóa Đơn:");
        lbConHoatDong.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbConHoatDong);

        cboTrangThai = new JComboBox<>();
        cboTrangThai.setFont(new Font("Tahoma", Font.BOLD, 13));
        cboTrangThai.addItem(""); // Đảm bảo có mục mặc định
        cboTrangThai.addItem("Đã thanh toán");
        cboTrangThai.addItem("Đã Hủy");
        
        panelForm.add(cboTrangThai);
        
        JLabel lbNgayTao = new JLabel("Ngày Tạo:");
        lbNgayTao.setFont(new Font("Tahoma", Font.BOLD, 13)); // Đặt font chữ cho JLabel
        panelForm.add(lbNgayTao); // Thêm JLabel vào panel

        // Tạo JDateChooser cho ngày tạo
        JDateChooser txtNgayTao = new JDateChooser();
        txtNgayTao.setDateFormatString("dd/MM/yyyy"); // Đặt định dạng ngày
        panelForm.add(txtNgayTao); // Thêm JDateChooser vào panel
        
        
        
        
        
        

        

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        panel_2.setBorder(new TitledBorder(null, "Chức năng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        add(panel_2);
        panel_2.setLayout(new GridLayout(2, 2, 10, 10));

        btnThem = new JButton("Thêm");
        btnThem.setBackground(Color.CYAN);
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_2.add(btnThem);

        btnXoa = new JButton("Hủy Hóa Đơn");
        btnXoa.setBackground(Color.RED);
        btnXoa.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_2.add(btnXoa);

        btnLamMoi = new JButton("Làm mới");
        btnLamMoi.setBackground(Color.LIGHT_GRAY);
        btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_2.add(btnLamMoi);
        
        btnXuat = new JButton("Xuất");
        btnXuat.setBackground(Color.LIGHT_GRAY);
        btnXuat.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_2.add(btnXuat);
        
        pnlThongTin.add(panelForm, BorderLayout.CENTER);
        pnlThongTin.add(panel_2, BorderLayout.EAST);
        pnlThongTin.add(pnlTieuDe,BorderLayout.NORTH);

        JPanel pnlBang = new JPanel();
        pnlBang.setBackground(Color.WHITE);
        pnlBang.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE),
                "Danh sách khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, CommonConstants.ORANGE));
        add(pnlBang);
        pnlBang.setLayout(new BorderLayout());

        JScrollPane scrDSKH = new JScrollPane();
        pnlBang.add(scrDSKH, BorderLayout.CENTER);

        Box hBox = Box.createHorizontalBox();
        JLabel lbTimTenKH = new JLabel("Mã khách hàng:");
        lbTimTenKH.setFont(new Font("Tahoma", Font.BOLD, 13));

        txtTim = new JTextField();
        txtTim.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtTim.setPreferredSize(new Dimension(150, 20));
        txtTim.setMaximumSize(new Dimension(150, 20));

        JButton btnTim = new JButton("Tìm");
        btnTim.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnTim.setBackground(Color.CYAN);

        JButton btnXemTatCa = new JButton("Xem tất cả");
        btnXemTatCa.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnXemTatCa.setBackground(new Color(0, 255, 255));

        // Thêm các thành phần vào Box ngang
        

        pnlBang.add(hBox, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
				new String[] { "Mã hóa đơn", "Trạng Thái","Thành Tiên", "Mã nhân viên", "Mã khuyến mãi", "Mã đơn đặt phòng","Ngày Tạo"}, 0);
		table_1 = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table_1);

		table_1.setForeground(new Color(0, 0, 0));
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		table_1.setRowHeight(25);
		table_1.setSelectionBackground(new Color(0, 204, 204));
		table_1.setSelectionForeground(new Color(255, 255, 255));
		table_1.setFillsViewportHeight(true);

		scrDSKH.setViewportView(scrollPane);
        table_1.setForeground(new Color(0, 0, 0));
        table_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        table_1.setRowHeight(25);
        table_1.setSelectionBackground(new Color(0, 204, 204));
        table_1.setSelectionForeground(new Color(255, 255, 255));
        table_1.setFillsViewportHeight(true);

        // Thêm JTable vào JScrollPane
        add(pnlThongTin, BorderLayout.NORTH); // Thêm pnlThongTin vào vùng NORTH
        add(pnlBang, BorderLayout.CENTER);
        scrDSKH.setViewportView(table_1);
        loadHoaDonToTable();
        
        table_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table_1.getSelectedRow(); // Lấy dòng được chọn từ bảng
                if (selectedRow != -1) { // Kiểm tra nếu có dòng được chọn
                    // Chuyển đổi chỉ số dòng từ bảng sang mô hình
                    int modelRow = table_1.convertRowIndexToModel(selectedRow);

                    // Lấy dữ liệu từ mô hình dựa trên chỉ số thực
                    String maHD = Objects.toString(tableModel.getValueAt(modelRow, 0), "");
                    String trangThai = Objects.toString(tableModel.getValueAt(modelRow, 1), "");
                    String thanhTien = Objects.toString(tableModel.getValueAt(modelRow, 2), "");
                    String maNV = Objects.toString(tableModel.getValueAt(modelRow, 3), "");
                    String maKM = Objects.toString(tableModel.getValueAt(modelRow, 4), "");
                    String maDonDat = Objects.toString(tableModel.getValueAt(modelRow, 5), "");
                    String ngayTao = Objects.toString(tableModel.getValueAt(modelRow, 6), "");

                    
            }
            }
        });

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
  		table_1.setRowSorter(sorter);
  		
  		btnThem.addActionListener(e -> {
		    String nextMaHD = generateHoaDonCode();
		    ThemHoaDonDiaLog themHoaDonDiaLog = new ThemHoaDonDiaLog(generateHoaDonCode(), QuanLyHoaDonPanel.this);
		    themHoaDonDiaLog.setVisible(true);
		});
  		btnXuat.addActionListener(e -> {
  			HoaDonExporter.exportHoaDon(table_1, btnXuat);

  		});



  		
  		//Làm mới
  		btnLamMoi.addActionListener(new ActionListener() {
  		    @Override
  		    public void actionPerformed(ActionEvent e) {
  		        // Xóa trắng các JTextField
  		        txtMaHD.setText("");   // Xóa văn bản trong JTextField txtMaHD
  		        txtNgayTao.setDate(null);  // Nếu txtNgayTao là JDateChooser, đặt lại ngày
  		        cboTrangThai.setSelectedIndex(0);  // Đặt lại giá trị mặc định cho ComboBox
  		    }
  		});
  		//Mã hóa đơn// Thêm KeyListener vào txtMaHD
  		txtMaHD.addKeyListener(new KeyAdapter() {
  		    @Override
  		    public void keyReleased(KeyEvent e) {
  		        String maHDKeyword = txtMaHD.getText().trim(); // Lấy nội dung mã hóa đơn từ txtMaHD

  		        // Tạo danh sách các bộ lọc
  		        List<RowFilter<Object, Object>> filters = new ArrayList<>();

  		        // Lọc theo mã hóa đơn (tìm bất kỳ ký tự nào có trong cột maHD)
  		        if (!maHDKeyword.isEmpty()) {
  		            filters.add(RowFilter.regexFilter("(?i)" + maHDKeyword, 0)); // Lọc theo mã hóa đơn (cột 0)
  		        }

  		        // Áp dụng bộ lọc kết hợp
  		        if (filters.isEmpty()) {
  		            sorter.setRowFilter(null); // Nếu không có bộ lọc, hiển thị tất cả
  		        } else {
  		            RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filters);
  		            sorter.setRowFilter(combinedFilter); // Áp dụng bộ lọc
  		        }
  		    }
  		});
  		//Trạng thái
  	// Sự kiện khi chọn một giá trị trong cboTrangThai
  		cboTrangThai.addActionListener(e -> {
  		    // Lấy giá trị từ các trường lọc
  		    String maHDKeyword = txtMaHD.getText().trim();  // Mã hóa đơn
  		    String selectedTrangThai = (String) cboTrangThai.getSelectedItem();  // Trạng thái

  		    // Tạo danh sách các bộ lọc
  		    List<RowFilter<Object, Object>> filters = new ArrayList<>();

  		    // Lọc theo mã hóa đơn (cột 0)
  		    if (!maHDKeyword.isEmpty()) {
  		        filters.add(RowFilter.regexFilter("(?i)" + maHDKeyword, 0));
  		    }

  		    // Lọc theo trạng thái (cột 2)
  		    if (selectedTrangThai != null && !selectedTrangThai.isEmpty()) {
  		        filters.add(RowFilter.regexFilter("(?i)" + selectedTrangThai, 1));
  		    }

  		    // Áp dụng bộ lọc kết hợp
  		    if (filters.isEmpty()) {
  		        sorter.setRowFilter(null); // Hiển thị tất cả nếu không có bộ lọc
  		    } else {
  		        RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filters);
  		        sorter.setRowFilter(combinedFilter); // Áp dụng bộ lọc
  		    }
  		});

  		
  		
  		
  		txtNgayTao.addPropertyChangeListener("date", new PropertyChangeListener() {
  		    public void propertyChange(PropertyChangeEvent evt) {
  		        // Lấy giá trị từ các trường lọc
  		        String maHDKeyword = txtMaHD.getText().trim(); // Mã hóa đơn
  		        String selectedTrangThai = (String) cboTrangThai.getSelectedItem(); // Trạng thái
  		        Date selectedDate = txtNgayTao.getDate(); // Ngày tạo được chọn từ JDateChooser
  		        System.out.println("Selected Date: " + selectedDate);
  		        // Tạo danh sách các bộ lọc
  		        List<RowFilter<Object, Object>> filters = new ArrayList<>();

  		        // Lọc theo mã hóa đơn (cột 0)
  		        if (!maHDKeyword.isEmpty()) {
  		            filters.add(RowFilter.regexFilter("(?i)" + maHDKeyword, 0));
  		        }

  		        // Lọc theo trạng thái (cột 2)
  		        if (selectedTrangThai != null && !selectedTrangThai.isEmpty()) {
  		            filters.add(RowFilter.regexFilter("(?i)" + selectedTrangThai, 2));
  		        }

  		        // Lọc theo ngày tạo (cột 6)
  		        if (selectedDate != null) {
  		            filters.add(new RowFilter<Object, Object>() {
  		                @Override
  		                public boolean include(Entry<? extends Object, ? extends Object> entry) {
  		                    // Lấy giá trị ngày từ cột 6 trong bảng
  		                    Object rowValue = entry.getValue(6); // Lấy giá trị từ cột 6

  		                    // Kiểm tra nếu giá trị là LocalDateTime
  		                    if (rowValue instanceof LocalDateTime) {
  		                        LocalDateTime rowDateTime = (LocalDateTime) rowValue;
  		                        // Chuyển LocalDateTime thành java.util.Date
  		                        Date rowDate = Date.from(rowDateTime.atZone(ZoneId.systemDefault()).toInstant());
  		                        return isSameDay(rowDate, selectedDate);
  		                    } else if (rowValue instanceof Date) {
  		                        Date rowDate = (Date) rowValue;
  		                        return isSameDay(rowDate, selectedDate);
  		                    }
  		                    return false;
  		                }
  		            });
  		        }

  		        // Áp dụng bộ lọc kết hợp
  		        if (filters.isEmpty()) {
  		            sorter.setRowFilter(null); // Hiển thị tất cả nếu không có bộ lọc
  		        } else {
  		            RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filters);
  		            sorter.setRowFilter(combinedFilter); // Áp dụng bộ lọc
  		        }
  		    }

  		    // Hàm kiểm tra nếu 2 ngày là cùng một ngày
  		    private boolean isSameDay(Date date1, Date date2) {
  		        // Tạo các đối tượng Calendar để so sánh ngày
  		        Calendar cal1 = Calendar.getInstance();
  		        cal1.setTime(date1);
  		        Calendar cal2 = Calendar.getInstance();
  		        cal2.setTime(date2);

  		        // So sánh ngày, tháng và năm
  		        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
  		               cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
  		               cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
  		    }
  		});






  		
  		btnXemTatCa.addMouseListener(new MouseAdapter() {
  		    @Override
  		    public void mouseClicked(MouseEvent e) {
  		        sorter.setRowFilter(null); // Hiển thị toàn bộ dữ liệu
  		        txtTim.setText(""); // Xóa nội dung trong trường tìm kiếm
  		    }
  		});
  		btnXoa.addActionListener(e -> {
  		    // Kiểm tra xem có dòng nào được chọn trong bảng không
  		    int selectedRow = table_1.getSelectedRow();
  		    if (selectedRow != -1) {
  		        // Lấy thông tin từ bảng
  		        String maHoaDon = table_1.getValueAt(selectedRow, 0).toString();
  		        
  		        // Hiển thị hộp thoại xác nhận xóa
  		        int confirm = JOptionPane.showConfirmDialog(QuanLyHoaDonPanel.this, 
  		            "Bạn chắc chắn muốn hủy hóa đơn " + maHoaDon + "?", "Xác nhận xóa", 
  		            JOptionPane.YES_NO_OPTION);

  		        if (confirm == JOptionPane.YES_OPTION) {
  		            // Nếu người dùng đồng ý, gọi phương thức xóa khuyến mãi
  		            HoaDonDAL hoaDonDAL = new HoaDonDAL();
  		            boolean isSuccess = hoaDonDAL.xoaHoaDonTheoMa(maHoaDon);

  		            if (isSuccess) {
  		                // Nếu xóa thành công, hiển thị thông báo thành công
  		                JOptionPane.showMessageDialog(QuanLyHoaDonPanel.this, 
  		                    "Hủy hóa đơn thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
  		                // Cập nhật lại bảng sau khi xóa
  		                capNhatTable();
  		            } else {
  		                // Nếu xóa thất bại, hiển thị thông báo lỗi
  		                JOptionPane.showMessageDialog(QuanLyHoaDonPanel.this, 
  		                    "Hủy hóa đơn thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
  		            }
  		        }
  		    } else {
  		        // Nếu không có dòng nào được chọn trong bảng, hiển thị thông báo lỗi
  		        JOptionPane.showMessageDialog(QuanLyHoaDonPanel.this, 
  		            "Vui lòng chọn hóa đơn cần hủy!", "Lỗi", JOptionPane.ERROR_MESSAGE);
  		    }
  		});



    }
    private void loadHoaDonToTable() {
        HoaDonDAL hoaDonDAL = new HoaDonDAL(); // Giả sử đây là lớp chứa hàm getAllHoaDon()
        ArrayList<HoaDon> dsHoaDon = hoaDonDAL.getAllHoaDon(); // Lấy danh sách hóa đơn từ cơ sở dữ liệu

        // Xóa dữ liệu cũ trên bảng (nếu có)
        tableModel.setRowCount(0);

        // Duyệt qua danh sách hóa đơn và thêm vào TableModel
        for (HoaDon hoaDon : dsHoaDon) {
            // Kiểm tra null cho các đối tượng và lấy dữ liệu tương ứng
            String maNhanVien = (hoaDon.getNhanVien() != null) ? hoaDon.getNhanVien().getMaNV() : "N/A";
            String maKM = (hoaDon.getKhuyenMai() != null) ? hoaDon.getKhuyenMai().getMaKhuyenMai() : "N/A";
            String maDonDat = (hoaDon.getDonDatPhong() != null) ? hoaDon.getDonDatPhong().getMaDon() : "N/A";

            // Thêm dữ liệu vào bảng
            Object[] rowData = { 
                    hoaDon.getMaHoaDon(),  // Mã hóa đơn
                    hoaDon.getTrangThai() ? "Đã Thanh Toán" : "Đã Hủy",  // Trạng thái
                    hoaDon.getThanhTien(),  // Thành tiền
                    maNhanVien,             // Mã nhân viên (hoặc "N/A" nếu null)
                    maKM,                   // Mã khuyến mãi (hoặc "N/A" nếu null)
                    maDonDat,               // Mã đơn đặt phòng (hoặc "N/A" nếu null)
                    hoaDon.getNgayTao()     // Ngày tạo
            };

            // Thêm hàng vào mô hình bảng
            tableModel.addRow(rowData);
        }
    }
    public String generateHoaDonCode() {
        HoaDonDAL hoaDonDAL = new HoaDonDAL(); // Đối tượng DAL để truy xuất dữ liệu
        String lastHoaDonCode = hoaDonDAL.getLastHoaDonCode(); // Lấy mã hóa đơn cuối cùng từ DB
        int newHoaDonNumber = 1; // Mặc định bắt đầu từ 1 nếu không có mã hóa đơn nào

        if (lastHoaDonCode != null && lastHoaDonCode.startsWith("HD")) {
            try {
                // Tách phần số từ mã hóa đơn (bỏ tiền tố "HD")
                String numberPart = lastHoaDonCode.substring(2); // Lấy từ ký tự thứ 3 trở đi
                newHoaDonNumber = Integer.parseInt(numberPart) + 1; // Tăng số lên 1
            } catch (NumberFormatException e) {
                System.err.println("Lỗi: Mã hóa đơn cuối cùng không hợp lệ - " + lastHoaDonCode);
            }
        }

        // Tạo mã hóa đơn mới theo định dạng "HDXXX"
        return String.format("HD%05d", newHoaDonNumber); // Định dạng thành 3 chữ số (HD001, HD002,...)
    }



    public void capNhatTable() {
        List<HoaDon> dsHoaDon = new HoaDonDAL().getAllHoaDon();

        // Cập nhật dữ liệu cho bảng
        DefaultTableModel model = (DefaultTableModel) table_1.getModel();
        model.setRowCount(0); // Xóa các hàng cũ

        for (HoaDon hd : dsHoaDon) {
            // Kiểm tra null cho các đối tượng và lấy dữ liệu tương ứng
            String maNhanVien = (hd.getNhanVien() != null) ? hd.getNhanVien().getMaNV() : "N/A";
            String maKM = (hd.getKhuyenMai() != null) ? hd.getKhuyenMai().getMaKhuyenMai() : "N/A";
            String maDonDat = (hd.getDonDatPhong() != null) ? hd.getDonDatPhong().getMaDon() : "N/A";

            // Thêm hàng vào bảng
            model.addRow(new Object[] { 
                hd.getMaHoaDon(), 
                hd.getTrangThai() ? "Đã Thanh Toán" : "Đã Hủy", 
                hd.getThanhTien(),
                maNhanVien, 
                maKM,
                maDonDat,
                hd.getNgayTao()
            });
        }
    }

    private void quayVeTrangThaiBanDau() {
		txtMaHD.setText("");
		cboTrangThai.setSelectedIndex(0);
		txtthanhTien.setText("");
		txtmaNV.setText("");
		txtMaKM.setText("");
		txtmaDon.setText("");
		txtNgayTao.setDate(null);

		txtMaHD.setEditable(false);       // Không cho phép chỉnh sửa trường mã khách hàng
	    cboTrangThai.setEditable(false);      // Không cho phép chỉnh sửa trường tên khách hàng
	    txtthanhTien.setEnabled(false); // Tắt trường tiền tích lũy
	    txtmaNV.setEnabled(false);        // Tắt trường CCCD
	    txtMaKM.setEnabled(false);       // Tắt trường email
	    txtmaDon.setEnabled(false);      // Tắt combo box loại khách hàng           // Giữ nút thêm hiển thị
	    txtNgayTao.setEnabled(false);
	    btnSua.setVisible(true);
		btnThem.setVisible(true);
	}
    public String getNextMaHD() {
		String nextMaHD = "HD0001"; // Giá trị mặc định nếu chưa có dữ liệu
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT MAX(maHD) FROM HoaDon";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String currentMax = rs.getString(1);
				if (currentMax != null) {
					// Chuyển đổi mã thành số để tăng lên 1
					int numericPart = Integer.parseInt(currentMax.substring(2));
					nextMaHD = String.format("HD%04d", numericPart + 1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nextMaHD;
	}
    public static void main(String[] args) {
        JFrame frame = new JFrame("Quản lý Hóa Đơn");
        QuanLyHoaDonPanel panel = new QuanLyHoaDonPanel();
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 600);
        System.out.println(System.getProperty("java.class.path"));

        
    }
}
