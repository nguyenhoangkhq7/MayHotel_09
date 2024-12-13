package view.panel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import com.toedter.calendar.JDateChooser;

import dal.KhuyenMaiDAL;
import entity.*;
import view.dialog.SuaKhuyenMaiDiaLog;
import view.dialog.ThemKhuyenMaiDiaLog;

import java.util.ArrayList;
import java.util.List;

import constant.CommonConstants;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class QuanLyKhuyenMaiPanel extends JPanel {

    private JTextField txtMaKhuyenMai,txtTenKhuyenMai,txtLoaiKhachHang;


    private JTable table_1;
    private JComboBox<String> cboLoaiKhachHang;
    private JButton btnThem;
    private JButton btnSua;
	private JPanel panelForm;
	private JTextField txtCMND;
	private JComboBox<String> cboLoaiKhach;
	private JTextField txtTienTichLuy;
	private JTextField txtTim;
	private DefaultTableModel tablemodel;
	private JTable table_SP;
	private JButton btnTimKiem;
	private JButton btnLamMoi;
	private JTextField txtMaKM;
	private JTextField txtGiaTri;
	private JTextField txtSoLuong;
	private JComboBox<String> cboAD;
	private JDateChooser txtNgayKetThuc;
	private JTextField txtTenKM;
	private JTextField txtGiaTriKM;
	private JComboBox cboHoatDong;
	private JTextField txtLoaiKH;
	private DefaultTableModel tableModel;
	private JButton btnXoa;
	private JDateChooser txtBatDau;

    public QuanLyKhuyenMaiPanel() {
    	setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 255));
        setBorder(new EmptyBorder(5, 5, 5, 5));

        

        JPanel pnlTieuDe = new JPanel();
        pnlTieuDe.setBackground(new Color(255, 255, 255));
        
        pnlTieuDe.setLayout(new FlowLayout());

        JLabel lblTieuDeTrang = new JLabel("QUẢN LÝ KHUYẾN MÃI");
        lblTieuDeTrang.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDeTrang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        pnlTieuDe.add(lblTieuDeTrang);

        JPanel pnlThongTin = new JPanel();
        pnlThongTin.setBackground(new Color(255, 255, 255));
        pnlThongTin.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE),
                "Thiết lập thông tin khuyến mãi", TitledBorder.LEADING, TitledBorder.TOP, null, CommonConstants.ORANGE));
       
        add(pnlThongTin);
        pnlThongTin.setLayout(new BorderLayout());
        
        panelForm = new JPanel();
        panelForm.setLayout(new GridLayout(3,2,10,10));
        
        //Ten KM
        
        JLabel lbMaKM = new JLabel("Mã Khuyến Mãi:");
		lbMaKM.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelForm.add(lbMaKM);

		txtMaKhuyenMai = new JTextField();
		txtMaKhuyenMai.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panelForm.add(txtMaKhuyenMai);

//		JLabel lbNgayBatDau = new JLabel("Ngày Tạo:");
//		lbNgayBatDau.setFont(new Font("Tahoma", Font.BOLD, 13)); // Đặt font chữ cho JLabel
//        panelForm.add(lbNgayBatDau); // Thêm JLabel vào panel
//
//        // Tạo JDateChooser cho ngày tạo
//        txtBatDau = new JDateChooser();
//        txtBatDau.setDateFormatString("dd/MM/yyyy"); // Đặt định dạng ngày
//        panelForm.add(txtBatDau); // Thêm JDateChooser vào panel
        
        
        JLabel lbConHoatDong = new JLabel("ConHoatDong:");
		lbConHoatDong.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelForm.add(lbConHoatDong);
        
		cboHoatDong = new JComboBox<>();
		cboHoatDong.setFont(new Font("Tahoma", Font.BOLD, 13));
		cboHoatDong.addItem("");
		cboHoatDong.addItem("Có");
		cboHoatDong.addItem("Không");
		panelForm.add(cboHoatDong);
		
       
        
//        JLabel lbNgayKetThuc = new JLabel("Ngày Kết Thúc:");
//        lbNgayKetThuc.setFont(new Font("Tahoma", Font.BOLD, 13)); // Đặt font chữ cho JLabel
//        panelForm.add(lbNgayKetThuc); // Thêm JLabel vào panel
//
//        // Tạo JDateChooser cho ngày tạo
//        JDateChooser txtNgayKetThuc = new JDateChooser();
//        txtNgayKetThuc.setDateFormatString("dd/MM/yyyy"); // Đặt định dạng ngày
//        panelForm.add(txtNgayKetThuc); // Thêm JDateChooser vào panel
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 255, 255));
        panel_2.setBorder(
                new TitledBorder(null, "Chức năng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        add(panel_2);
        panel_2.setLayout(new GridLayout(2, 2, 10, 10));

        btnThem = new JButton("Thêm");
        btnThem.setBackground(new Color(0, 255, 255));
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_2.add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setBackground(new Color(0, 255, 255));
        btnSua.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_2.add(btnSua);
        
        btnXoa = new JButton("Xóa");
        btnXoa.setBackground(new Color(0, 255, 255));
        btnXoa.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnLamMoi = new JButton("Làm mới");
        
        btnLamMoi.setBackground(Color.LIGHT_GRAY);
        btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_2.add(btnLamMoi);
        panel_2.add(btnXoa);
        
        
        pnlThongTin.add(panelForm, BorderLayout.CENTER);
        pnlThongTin.add(panel_2, BorderLayout.EAST);
        pnlThongTin.add(pnlTieuDe,BorderLayout.NORTH);
        

        

        JPanel pnlBang = new JPanel();
        pnlBang.setBackground(new Color(255, 255, 255));
        pnlBang.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE),
                "Thiết lập thông tin phòng", TitledBorder.LEADING, TitledBorder.TOP, null, CommonConstants.ORANGE));
        add(pnlBang);
        pnlBang.setLayout(new BorderLayout());
        
        
        JScrollPane scrDSKM = new JScrollPane();
        pnlBang.add(scrDSKM, BorderLayout.CENTER);

        Box hBox = Box.createHorizontalBox();
        pnlBang.add(hBox, BorderLayout.NORTH);
        
        // Tạo JTable và cấu hình
        table_1 = new JTable();
        tableModel = new DefaultTableModel(
				new String[] { "Mã Khuyến Mãi", "Tên Khuyến Mãi","Giá Trị", "Ngày Bắt Đầu", "Còn Hoạt Động", "Số lượng","Ngày kết thúc","Loại Khách Hàng áp dụng"}, 0);
		table_1 = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table_1);

		table_1.setForeground(new Color(0, 0, 0));
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		table_1.setRowHeight(25);
		table_1.setSelectionBackground(new Color(0, 204, 204));
		table_1.setSelectionForeground(new Color(255, 255, 255));
		table_1.setFillsViewportHeight(true);

		scrDSKM.setViewportView(scrollPane);
        table_1.setForeground(new Color(0, 0, 0));
        table_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        table_1.setRowHeight(25);
        table_1.setSelectionBackground(new Color(0, 204, 204));
        table_1.setSelectionForeground(new Color(255, 255, 255));
        table_1.setFillsViewportHeight(true);

        // Thêm JTable vào JScrollPane
        scrDSKM.setViewportView(table_1);
        loadKhuyenMaiToTable();
        add(pnlThongTin, BorderLayout.NORTH); // Thêm pnlThongTin vào vùng NORTH
        add(pnlBang, BorderLayout.CENTER);
        

        table_1.addMouseListener(new MouseAdapter() {
            
			@Override
			public void mouseClicked(MouseEvent e) {
			    int selectedRow = table_1.getSelectedRow(); // Lấy dòng được chọn từ bảng
			    if (selectedRow != -1) { // Kiểm tra nếu có dòng được chọn
			        int modelRow = table_1.convertRowIndexToModel(selectedRow); // Chuyển đổi chỉ số dòng từ bảng sang mô hình

			        try {
			            // Lấy dữ liệu từ mô hình
			            String maKhuyenMai = getStringValueAt(modelRow, 0);
			            String tenKhuyenMai = getStringValueAt(modelRow, 1);
			            double giaTri = getDoubleValueAt(modelRow, 2, 0.0);
			            String ngayBatDau = Objects.toString(tableModel.getValueAt(modelRow, 3), "");
			            String hoatDong = Objects.toString(tableModel.getValueAt(modelRow, 4), "");
			            int soLuong = getIntValueAt(modelRow, 5, 0);
			            String ngayKetThucString = getStringValueAt(modelRow, 6);
			            String loaiKhachHangApDung = getStringValueAt(modelRow, 7);
			        } catch (Exception ex) {
			            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
			            ex.printStackTrace();
			        }
			    }
			}



        });


        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
  		table_1.setRowSorter(sorter);
  		
  		btnThem.addActionListener(e -> {
		    String nextMaKM = generateKhuyenMaiCode();
		    ThemKhuyenMaiDiaLog themKhuyenMaiDiaLog = new ThemKhuyenMaiDiaLog(generateKhuyenMaiCode(), QuanLyKhuyenMaiPanel.this);
		    themKhuyenMaiDiaLog.setVisible(true);
		});
  		btnLamMoi.addActionListener(new ActionListener() {
  		    @Override
  		    public void actionPerformed(ActionEvent e) {
  		        // Xóa trắng các JTextField
  		        txtMaKhuyenMai.setText("");   // Xóa văn bản trong JTextField txtMaHD
  		    }
  		});
  		
  		
  		btnXoa.addActionListener(e -> {
  		    // Kiểm tra xem có dòng nào được chọn trong bảng không
  		    int selectedRow = table_1.getSelectedRow();
  		    if (selectedRow != -1) {
  		        String maKM = table_1.getValueAt(selectedRow, 0).toString();
  		        
  		        // Hiển thị hộp thoại xác nhận xóa
  		        int confirm = JOptionPane.showConfirmDialog(QuanLyKhuyenMaiPanel.this, 
  		            "Bạn chắc chắn muốn xóa khuyến mãi " + maKM + "?", "Xác nhận xóa", 
  		            JOptionPane.YES_NO_OPTION);

  		        if (confirm == JOptionPane.YES_OPTION) {
  		            // Nếu người dùng đồng ý, gọi phương thức xóa khuyến mãi
  		            KhuyenMaiDAL khuyenMaiDAL = new KhuyenMaiDAL();
  		            boolean isSuccess = khuyenMaiDAL.xoaKhuyenMai(maKM);

  		            if (isSuccess) {
  		                // Nếu xóa thành công, hiển thị thông báo thành công
  		                JOptionPane.showMessageDialog(QuanLyKhuyenMaiPanel.this, 
  		                    "Xóa khuyến mãi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
  		                
  		                // Cập nhật lại bảng sau khi xóa
  		                capNhatTableKhuyenMai();
  		                
  		                // Nếu có JTextField tìm kiếm, cập nhật lại giá trị trong trường tìm kiếm
  		                if (txtTim != null) {
  		                    txtTim.setText("");  // Xóa nội dung tìm kiếm
  		                }
  		            } else {
  		                // Nếu xóa thất bại, hiển thị thông báo lỗi
  		                JOptionPane.showMessageDialog(QuanLyKhuyenMaiPanel.this, 
  		                    "Xóa khuyến mãi thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
  		            }
  		        }
  		    } else {
  		        // Nếu không có dòng nào được chọn trong bảng, hiển thị thông báo lỗi
  		        JOptionPane.showMessageDialog(QuanLyKhuyenMaiPanel.this, 
  		            "Vui lòng chọn khuyến mãi để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
  		    }
  		});
  		//Sửa
  		btnSua.addActionListener(e -> {
  		    // Kiểm tra xem có dòng nào được chọn trong bảng không
  		    int selectedRow = table_1.getSelectedRow();
  		    if (selectedRow != -1) {
  		        try {
  		            // Lấy thông tin từ bảng vào các trường text của SuaKhuyenMaiDialog
  		            String maKhuyenMai = table_1.getValueAt(selectedRow, 0).toString();
  		            String tenKhuyenMai = table_1.getValueAt(selectedRow, 1).toString();
  		            double giaTri = Double.parseDouble(table_1.getValueAt(selectedRow, 2).toString());
  		            String loaiKhachHang = table_1.getValueAt(selectedRow, 7).toString();
  		            
  		            // Kiểm tra và chuyển đổi số lượng
  		            int soLuong = 0;
  		            try {
  		                soLuong = Integer.parseInt(table_1.getValueAt(selectedRow, 5).toString());
  		            } catch (NumberFormatException ex) {
  		                JOptionPane.showMessageDialog(QuanLyKhuyenMaiPanel.this, "Số lượng không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
  		                return; // Dừng xử lý nếu có lỗi
  		            }

  		            String conHoatDongString = table_1.getValueAt(selectedRow, 4).toString();
  		            boolean conHoatDong = "Có".equals(conHoatDongString);
  		            
  		            // Kiểm tra và chuyển đổi ngày bắt đầu và ngày kết thúc
  		            LocalDateTime ngayBatDau = null, ngayKetThuc = null;
  		            try {
  		                ngayBatDau = LocalDateTime.parse(table_1.getValueAt(selectedRow, 3).toString());
  		                ngayKetThuc = LocalDateTime.parse(table_1.getValueAt(selectedRow, 6).toString());
  		            } catch (Exception ex) {
  		                JOptionPane.showMessageDialog(QuanLyKhuyenMaiPanel.this, "Ngày tháng không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
  		                return; // Dừng xử lý nếu có lỗi
  		            }

  		            // Tạo đối tượng KhuyenMai và truyền vào dialog
  		            KhuyenMai khuyenMai = new KhuyenMai(
  		                maKhuyenMai, 
  		                tenKhuyenMai, 
  		                giaTri, 
  		                ngayBatDau, 
  		                conHoatDong, 
  		                soLuong, 
  		                ngayKetThuc, 
  		                loaiKhachHang
  		            );

  		            // Mở dialog sửa khuyến mãi
  		            SuaKhuyenMaiDiaLog suaDialog = new SuaKhuyenMaiDiaLog(QuanLyKhuyenMaiPanel.this, khuyenMai);
  		            suaDialog.setVisible(true);
  		        } catch (Exception ex) {
  		            // Xử lý bất kỳ ngoại lệ nào khác
  		            JOptionPane.showMessageDialog(QuanLyKhuyenMaiPanel.this, "Đã xảy ra lỗi khi xử lý dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
  		        }
  		    } else {
  		        JOptionPane.showMessageDialog(QuanLyKhuyenMaiPanel.this, "Vui lòng chọn khuyến mãi để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
  		    }
  		});



  		//MaKM
  		txtMaKhuyenMai.addKeyListener(new KeyAdapter() {
  		    public void keyReleased(KeyEvent e) {
  		        String maKMKeyword = txtMaKhuyenMai.getText().trim(); // Lấy nội dung mã khuyến mãi từ txtMaKhuyenMai

  		        // Tạo danh sách các bộ lọc
  		        List<RowFilter<Object, Object>> filters = new ArrayList<>();

  		        // Lọc theo mã khuyến mãi (tìm bất kỳ ký tự nào có trong cột maKM)
  		        if (!maKMKeyword.isEmpty()) {
  		            filters.add(RowFilter.regexFilter("(?i)" + maKMKeyword, 0)); // Lọc theo mã khuyến mãi (cột 0)
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
  		
  		cboHoatDong.addActionListener(e -> {
  		    // Lấy giá trị từ các trường lọc
  		    String maKMKeyword = txtMaKhuyenMai.getText().trim();  // Mã hóa đơn
  		    String selectedTrangThai = (String) cboHoatDong.getSelectedItem();  // Trạng thái

  		    // Tạo danh sách các bộ lọc
  		    List<RowFilter<Object, Object>> filters = new ArrayList<>();

  		    // Lọc theo mã hóa đơn (cột 0)
  		    if (!maKMKeyword.isEmpty()) {
  		        filters.add(RowFilter.regexFilter("(?i)" + maKMKeyword, 0));
  		    }

  		    // Lọc theo trạng thái (cột 2)
  		    if (selectedTrangThai != null && !selectedTrangThai.isEmpty()) {
  		        filters.add(RowFilter.regexFilter("(?i)" + selectedTrangThai, 4));
  		    }

  		    // Áp dụng bộ lọc kết hợp
  		    if (filters.isEmpty()) {
  		        sorter.setRowFilter(null); // Hiển thị tất cả nếu không có bộ lọc
  		    } else {
  		        RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filters);
  		        sorter.setRowFilter(combinedFilter); // Áp dụng bộ lọc
  		    }
  		});
  		
  		//NgayBatDauKM
  	// Thêm PropertyChangeListener vào JDateChooser
  		
  		
//  		txtBatDau.addPropertyChangeListener("date", new PropertyChangeListener() {
//  		    public void propertyChange(PropertyChangeEvent evt) {
//  		        String maKMKeyword = txtMaKhuyenMai.getText().trim();
//  		        Date selectedDate = txtBatDau.getDate();
//
//  		        List<RowFilter<Object, Object>> filters = new ArrayList<>();
//
//  		        
//
//  		        if (selectedDate != null) {
//  		            filters.add(new RowFilter<Object, Object>() {
//  		                @Override
//  		                public boolean include(Entry<? extends Object, ? extends Object> entry) {
//  		                    Object rowValue = entry.getValue(3);
//  		                    if (rowValue instanceof Date) {
//  		                        Date rowDate = (Date) rowValue;
//  		                        return isSameDay(rowDate, selectedDate);
//  		                    }
//  		                    return false;
//  		                }
//  		            });
//  		        }
//
//  		        if (filters.isEmpty()) {
//  		            sorter.setRowFilter(null);
//  		        } else {
//  		            RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filters);
//  		            sorter.setRowFilter(combinedFilter);
//  		        }
//  		    }
//
//  		    private boolean isSameDay(Date date1, Date date2) {
//  		        Calendar cal1 = Calendar.getInstance();
//  		        cal1.setTime(date1);
//  		        cal1.set(Calendar.HOUR_OF_DAY, 0);
//  		        cal1.set(Calendar.MINUTE, 0);
//  		        cal1.set(Calendar.SECOND, 0);
//  		        cal1.set(Calendar.MILLISECOND, 0);
//
//  		        Calendar cal2 = Calendar.getInstance();
//  		        cal2.setTime(date2);
//  		        cal2.set(Calendar.HOUR_OF_DAY, 0);
//  		        cal2.set(Calendar.MINUTE, 0);
//  		        cal2.set(Calendar.SECOND, 0);
//  		        cal2.set(Calendar.MILLISECOND, 0);
//
//  		        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
//  		               cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
//  		               cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
//  		    }
//  		});




  		




    }
 // Lấy giá trị chuỗi từ bảng
    private String getStringValueAt(int row, int column) {
        Object value = tableModel.getValueAt(row, column);
        return value != null ? value.toString().trim() : "";
    }

    // Lấy giá trị số nguyên từ bảng
    private int getIntValueAt(int row, int column, int defaultValue) {
        try {
            Object value = tableModel.getValueAt(row, column);
            return value != null && !value.toString().isEmpty() ? Integer.parseInt(value.toString()) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    // Lấy giá trị số thực từ bảng
    private double getDoubleValueAt(int row, int column, double defaultValue) {
        try {
            Object value = tableModel.getValueAt(row, column);
            return value != null && !value.toString().isEmpty() ? Double.parseDouble(value.toString()) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    // Lấy giá trị boolean từ bảng
    private boolean getBooleanValueAt(int row, int column, boolean defaultValue) {
        Object value = tableModel.getValueAt(row, column);
        return value != null && Boolean.parseBoolean(value.toString());
    }

//     Phân tích chuỗi ngày giờ


    private void loadKhuyenMaiToTable() {
        // Khởi tạo lớp KhuyenMaiDAL để lấy dữ liệu
        KhuyenMaiDAL khuyenMaiDAL = new KhuyenMaiDAL(); // Giả sử đây là lớp chứa hàm getAllKhuyenMai()
        ArrayList<KhuyenMai> dsKhuyenMai = khuyenMaiDAL.getAllKhuyenMai(); // Lấy danh sách khuyến mãi từ cơ sở dữ liệu

        // Xóa dữ liệu cũ trên bảng (nếu có)
        tableModel.setRowCount(0);

        // Duyệt qua danh sách khuyến mãi và thêm vào TableModel
        for (KhuyenMai khuyenMai : dsKhuyenMai) {
            // Lấy các thông tin của khuyến mãi và chuẩn bị dữ liệu cho bảng
            Object[] rowData = { 
                khuyenMai.getMaKhuyenMai(),               // Mã khuyến mãi
                khuyenMai.getTenKhuyenMai(),
                khuyenMai.getGiaTri(),
                khuyenMai.getNgayBatDau().toString(),     // Ngày bắt đầu
                khuyenMai.isConHoatDong() ? "Có" : "Không", // Trạng thái hoạt động (Có/Không)
                khuyenMai.getSoLuong(), 
                khuyenMai.getNgayKetThuc().toString(),    // Ngày kết thúc
                                  // Số lượng khuyến mãi
                khuyenMai.getLoaiKhachHangApDung()        // Loại khách hàng áp dụng
            };
            // Thêm hàng vào mô hình bảng
            tableModel.addRow(rowData);
        }
    }

    public void capNhatTableKhuyenMai() {
        // Lấy danh sách các khuyến mãi từ cơ sở dữ liệu
        List<KhuyenMai> dsKhuyenMai = new KhuyenMaiDAL().getAllKhuyenMai();

        // Cập nhật dữ liệu cho bảng
        DefaultTableModel model = (DefaultTableModel) table_1.getModel();  // Giả sử table_1 là bảng khuyến mãi
        model.setRowCount(0);  // Xóa các hàng cũ trong bảng

        // Lặp qua danh sách khuyến mãi và thêm vào bảng
        for (KhuyenMai km : dsKhuyenMai) {
            // Kiểm tra null cho các đối tượng liên quan và lấy dữ liệu tương ứng
            

            // Thêm hàng vào bảng với dữ liệu của khuyến mãi
            model.addRow(new Object[] { 
                km.getMaKhuyenMai(), 
                km.getTenKhuyenMai(), 
                km.getGiaTri(),
                km.getNgayBatDau(),
                km.isConHoatDong() ? "Có" : "Không",
                km.getSoLuong(),
                km.getNgayKetThuc(), // Thêm Ngày Kết Thúc vào bảng
                 // Thêm Số lượng vào bảng
                km.getLoaiKhachHangApDung() // Thêm Loại Khách Hàng Áp Dụng vào bảng
            });
        }
    }


    private void quayVeTrangThaiBanDau() {
        // Đặt lại các giá trị mặc định
        txtMaKM.setText("");
        txtTenKM.setText("");
        txtGiaTriKM.setText("");
        txtSoLuong.setText("");
        txtLoaiKH.setText("");
        
        // Đặt lại các trường ngày tạo và ngày kết thúc
        txtBatDau.setDate(null);
        txtNgayKetThuc.setDate(null);
        
        // Đặt trạng thái ban đầu cho các trường nhập liệu
        txtMaKM.setEditable(false);           // Không cho phép chỉnh sửa trường mã khuyến mãi
        txtTenKM.setEditable(false);          // Không cho phép chỉnh sửa trường tên khuyến mãi
        txtGiaTriKM.setEditable(false);       // Không cho phép chỉnh sửa trường giá trị khuyến mãi
        txtSoLuong.setEditable(false);        // Không cho phép chỉnh sửa trường số lượng
        txtLoaiKH.setEditable(false);         // Không cho phép chỉnh sửa trường loại khách hàng
        
        txtBatDau.setEnabled(false);         // Tắt trường ngày tạo
        txtNgayKetThuc.setEnabled(false);    // Tắt trường ngày kết thúc
        cboHoatDong.setEnabled(false);       // Tắt combo box "Hoạt Động"
        
        // Đặt trạng thái ban đầu cho các nút
        btnSua.setVisible(true);              // Hiển thị nút sửa
        btnThem.setVisible(true);             // Hiển thị nút thêm
    }
    public String generateKhuyenMaiCode() {
        KhuyenMaiDAL khuyenMaiDAL = new KhuyenMaiDAL(); // Đối tượng DAL để truy xuất dữ liệu
        String lastKhuyenMaiCode = khuyenMaiDAL.getLastKhuyenMaiCode(); // Lấy mã khuyến mãi cuối cùng từ DB
        int newKhuyenMaiNumber = 1; // Mặc định bắt đầu từ 1 nếu không có mã khuyến mãi nào

        if (lastKhuyenMaiCode != null && lastKhuyenMaiCode.startsWith("KM")) {
            try {
                // Tách phần số từ mã khuyến mãi (bỏ tiền tố "KM")
                String numberPart = lastKhuyenMaiCode.substring(2); // Lấy từ ký tự thứ 3 trở đi
                newKhuyenMaiNumber = Integer.parseInt(numberPart) + 1; // Tăng số lên 1
            } catch (NumberFormatException e) {
                System.err.println("Lỗi: Mã khuyến mãi cuối cùng không hợp lệ - " + lastKhuyenMaiCode);
            }
        }

        // Tạo mã khuyến mãi mới theo định dạng "KM00001"
        return String.format("KM%05d", newKhuyenMaiNumber); // Định dạng thành 5 chữ số (KM00001, KM00002,...)
    }


    public static void main(String[] args) {
    	EventQueue.invokeLater(() -> {
            try {
            	
                JFrame frame = new JFrame("Quản lí khuyến mãi");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(new QuanLyKhuyenMaiPanel());
                frame.pack();
                frame.setSize(800, 600);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
