package view.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import constant.CommonConstants;
import dal.DichVuDAL;
import dal.KhachHangDAL;
import dal.PhongDAL;
import database.ConnectDB;
import entity.DichVu;
import entity.KhachHang;
import entity.LoaiKhachHang;
import entity.Phong;
import view.dialog.SuaDichVuDialog;
import view.dialog.SuaKhachHangDiaLog;
import view.dialog.SuaPhongDialong;
import view.dialog.ThemKhachHangDiaLog;
import view.dialog.ThemPhongDiaLog;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.PatternSyntaxException;

public class QuanLyKhachHangPanel extends JPanel {

    private JTextField txtMaKH;
    private JTextField txtTenKH;
    private JTextField txtDiaChi;
    private JTextField txtSDT;
    private JTextField txtCCCD;
    private JTextField txtEmail;
    private JTextField txtDTL;
    private JTable table_1 = new JTable();
    private JComboBox<String> cboLoaiKhachHang;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;      // Nút Xóa
    private JButton btnLamMoi;   // Nút Làm mới
    private JButton btnXuatCSV;  // Nút Xuất CSV
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
    private JTextField txtTienTichLuy;
    private JTextField txtTim;
	private JTable table_CTHD;
	DefaultTableModel model = new DefaultTableModel();
	private JButton btnLuu;
	private DefaultTableModel tableModel;
	private JTextField txtLoaiKH;

    public QuanLyKhachHangPanel() {
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
        panelForm.setLayout(new GridLayout(3, 3, 10, 10));

        JLabel lbMaKH = new JLabel("Mã khách hàng:");
        lbMaKH.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbMaKH);

        txtMaKH = new JTextField("");
        txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
        
        panelForm.add(txtMaKH);
        
        JLabel lbTenKH = new JLabel("Tên khách hàng:");
        lbTenKH.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbTenKH);

        txtTenKH = new JTextField();
        txtTenKH.setFont(new Font("Dialog", Font.BOLD, 13));
        
        panelForm.add(txtTenKH);
        
        JLabel lbSDT = new JLabel("Số điện thoại:");
        lbSDT.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbSDT);

        txtSDT = new JTextField();
        txtSDT.setFont(new Font("Dialog", Font.BOLD, 13));
        
        panelForm.add(txtSDT);

        
        
        JLabel lbCCCD = new JLabel("CMND/CCCD:");
        lbCCCD.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbCCCD);

        txtCCCD = new JTextField();
        txtCCCD.setFont(new Font("Dialog", Font.BOLD, 13));
        
        panelForm.add(txtCCCD);
        
        JLabel lbEmail = new JLabel("Email:");
        lbEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbEmail);

        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Dialog", Font.BOLD, 13));
        
        panelForm.add(txtEmail);

        JLabel lbLoaiKhach = new JLabel("Loại Khách Hàng:");
        lbLoaiKhach.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbLoaiKhach);

        cboLoaiKhachHang = new JComboBox<>();
        cboLoaiKhachHang.setFont(new Font("Tahoma", Font.BOLD, 13));
        cboLoaiKhachHang.addItem("");
        cboLoaiKhachHang.addItem("NGUOIMOI");
        cboLoaiKhachHang.addItem("HANGDONG");
        cboLoaiKhachHang.addItem("HANGBAC");
        cboLoaiKhachHang.addItem("HANGVANG");
        cboLoaiKhachHang.addItem("HANGKIMCUONG");
        panelForm.add(cboLoaiKhachHang);
        
        

        

        

        


        

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        panel_2.setBorder(new TitledBorder(null, "Chức năng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        add(panel_2);
        panel_2.setLayout(new GridLayout(2, 2, 10, 10));

        btnThem = new JButton("Thêm");
        btnThem.setBackground(Color.CYAN);
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_2.add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setBackground(Color.CYAN);
        btnSua.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_2.add(btnSua);

        btnXoa = new JButton("Xóa");
        btnXoa.setBackground(Color.RED);
        btnXoa.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_2.add(btnXoa);

        btnLamMoi = new JButton("Làm mới");
        btnLamMoi.setBackground(Color.LIGHT_GRAY);
        btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_2.add(btnLamMoi);
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

        
        
        

        pnlBang.add(hBox, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
				new String[] { "Mã khách hàng", "Họ Tên","Số điện thoại", "Tiền tích lũy", "CCCD or CMND", "Email","Loại khách hàng"}, 0);
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
        loadDichVuToTable();

        // ActionListeners for buttons
        table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table_1.getSelectedRow(); // Lấy dòng được chọn từ bảng
				if (selectedRow != -1) { // Kiểm tra nếu có dòng được chọn
					// Chuyển đổi chỉ số dòng từ bảng sang mô hình
					int modelRow = table_1.convertRowIndexToModel(selectedRow);

					// Lấy dữ liệu từ mô hình dựa trên chỉ số thực
					String maKH = Objects.toString(tableModel.getValueAt(modelRow, 0), "");
					String hoTen = Objects.toString(tableModel.getValueAt(modelRow, 1), "");
					String sdt = Objects.toString(tableModel.getValueAt(modelRow, 2), "");
					String tienTichLuy = Objects.toString(tableModel.getValueAt(modelRow, 3), "");
					String soCanCuoc = Objects.toString(tableModel.getValueAt(modelRow, 4), "");
					String email = Objects.toString(tableModel.getValueAt(modelRow, 5), "");
					String loaiKH = Objects.toString(tableModel.getValueAt(modelRow, 6), "");

					
					
				}
			}
		});
        
      //Sự kiện cho btnTim
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table_1.setRowSorter(sorter);

        btnTim.addActionListener(e -> {
            String keyword = txtTim.getText().trim();
            
            if (keyword.length() < 1) { // Kiểm tra xem chuỗi có ít nhất một ký tự không
                JOptionPane.showMessageDialog(
                    null, 
                    "Vui lòng nhập ít nhất một ký tự để tìm kiếm!", 
                    "Thông báo", 
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            
            try {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword)); // Thực hiện tìm kiếm
            } catch (PatternSyntaxException ex) {
                JOptionPane.showMessageDialog(null, "Từ khóa không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnLamMoi.addActionListener(e -> {
            // Xóa nội dung của các JTextField
            txtMaKH.setText("");
            txtSDT.setText("");
            txtTenKH.setText("");
            txtCCCD.setText("");
            txtEmail.setText("");

            // Đặt lại combobox về trạng thái mặc định
            cboLoaiKhachHang.setSelectedIndex(0);

            // Bỏ tất cả bộ lọc khỏi bảng
            sorter.setRowFilter(null);
        });

        
        
        //Mã kh
        txtMaKH.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String maKHKeyword = txtMaKH.getText().trim(); // Lấy nội dung mã khách hàng từ txtMaKH

                // Tạo danh sách các bộ lọc
                List<RowFilter<Object, Object>> filters = new ArrayList<>();

                // Lọc theo mã khách hàng (tìm bất kỳ ký tự nào có trong cột maKH)
                if (!maKHKeyword.isEmpty()) {
                    filters.add(RowFilter.regexFilter("(?i)" + maKHKeyword, 0)); // Lọc theo mã khách hàng (cột 0)
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


        
        //Loại khách
        
        cboLoaiKhachHang.addActionListener(e -> {
            // Lấy giá trị từ các trường lọc
            String maKHKeyword = txtMaKH.getText().trim(); // Mã khách hàng
            String sdtKeyword = txtSDT.getText().trim();   // Số điện thoại
            String tenKHKeyword = txtTenKH.getText().trim(); // Tên khách hàng
            String cccdKeyword = txtCCCD.getText().trim();   // CMND/CCCD
            String selectedLoaiKhachHang = (String) cboLoaiKhachHang.getSelectedItem(); // Loại khách hàng được chọn

            // Tạo danh sách các bộ lọc
            List<RowFilter<Object, Object>> filters = new ArrayList<>();

            // Lọc theo mã khách hàng (cột 0)
            if (!maKHKeyword.isEmpty()) {
                filters.add(RowFilter.regexFilter("(?i)" + maKHKeyword, 0));
            }

            // Lọc theo số điện thoại (cột 1)
            if (!sdtKeyword.isEmpty()) {
                filters.add(RowFilter.regexFilter("(?i)" + sdtKeyword, 1));
            }

            // Lọc theo tên khách hàng (cột 2)
            if (!tenKHKeyword.isEmpty()) {
                // Lọc cả có dấu và không dấu
                String tenKHKeywordNormalized = normalizeVietnamese(tenKHKeyword);
                filters.add(RowFilter.regexFilter("(?i)" + tenKHKeywordNormalized, 2));
            }

            // Lọc theo CMND/CCCD (cột 3)
            if (!cccdKeyword.isEmpty()) {
                filters.add(RowFilter.regexFilter("(?i)" + cccdKeyword, 3));
            }

            // Lọc theo loại khách hàng (cột 6)
            if (selectedLoaiKhachHang != null && !selectedLoaiKhachHang.isEmpty()) {
                filters.add(RowFilter.regexFilter("(?i)" + selectedLoaiKhachHang, 6));
            }

            // Áp dụng bộ lọc kết hợp
            if (filters.isEmpty()) {
                sorter.setRowFilter(null); // Hiển thị tất cả nếu không có bộ lọc
            } else {
                RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filters);
                sorter.setRowFilter(combinedFilter); // Áp dụng bộ lọc
            }
        });

        //Ten KH
        txtTenKH.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String maKHKeyword = txtMaKH.getText().trim(); // Lấy nội dung mã khách hàng từ txtMaKH
                String sdtKeyword = txtSDT.getText().trim(); // Lấy nội dung số điện thoại từ txtSDT
                String tenKHKeyword = txtTenKH.getText().trim(); // Lấy nội dung tên khách hàng từ txtTenKH

                // Tạo danh sách các bộ lọc
                List<RowFilter<Object, Object>> filters = new ArrayList<>();

                // Lọc theo mã khách hàng (cột 0)
                if (!maKHKeyword.isEmpty()) {
                    filters.add(RowFilter.regexFilter("(?i)" + maKHKeyword, 0)); // Lọc theo mã khách hàng (cột 0)
                }

                // Lọc theo số điện thoại (cột 1)
                if (!sdtKeyword.isEmpty()) {
                    filters.add(RowFilter.regexFilter("(?i)" + sdtKeyword, 2)); // Lọc theo số điện thoại (cột 1)
                }

                // Lọc theo tên khách hàng (cột 2, giả sử cột 2 chứa tên khách hàng)
                if (!tenKHKeyword.isEmpty()) {
                    filters.add(RowFilter.regexFilter("(?i)" + tenKHKeyword, 1)); // Lọc theo tên khách hàng (cột 2)
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

        
        
        //SDT
        txtSDT.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String maKHKeyword = txtMaKH.getText().trim(); // Lấy nội dung mã khách hàng từ txtMaKH
                String sdtKeyword = txtSDT.getText().trim(); // Lấy nội dung số điện thoại từ txtSDT

                // Tạo danh sách các bộ lọc
                List<RowFilter<Object, Object>> filters = new ArrayList<>();

                // Lọc theo mã khách hàng (cột 0)
                if (!maKHKeyword.isEmpty()) {
                    filters.add(RowFilter.regexFilter("(?i)" + maKHKeyword, 0)); // Lọc theo mã khách hàng (cột 0)
                }

                // Lọc theo số điện thoại (cột 1, giả sử cột 1 chứa số điện thoại)
                if (!sdtKeyword.isEmpty()) {
                    filters.add(RowFilter.regexFilter("(?i)" + sdtKeyword, 2)); // Lọc theo số điện thoại (cột 1)
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
        //CCCD
        txtCCCD.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String cccdKeyword = txtCCCD.getText().trim(); // Lấy nội dung CMND/CCCD từ txtCCCD

                // Tạo danh sách các bộ lọc
                List<RowFilter<Object, Object>> filters = new ArrayList<>();

                // Lọc theo CMND/CCCD (Giả sử cột chứa CMND/CCCD là cột 3, bạn cần điều chỉnh lại cột phù hợp với dữ liệu của mình)
                if (!cccdKeyword.isEmpty()) {
                    filters.add(RowFilter.regexFilter("(?i)" + cccdKeyword, 4)); // Lọc theo CMND/CCCD (cột 3)
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
        //sdt
        txtSDT.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String sdtKeyword = txtSDT.getText().trim(); // Lấy nội dung số điện thoại từ txtSDT

                // Tạo danh sách các bộ lọc
                List<RowFilter<Object, Object>> filters = new ArrayList<>();

                // Lọc theo số điện thoại (Giả sử cột chứa số điện thoại là cột 2, hãy thay đổi số cột cho phù hợp)
                if (!sdtKeyword.isEmpty()) {
                    filters.add(RowFilter.regexFilter("(?i)" + sdtKeyword,5 )); // Lọc theo số điện thoại (cột 2)
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
        
        

        
        
        btnThem.addActionListener(e -> {
		    String nextMaKH = generateKHCode();
		    ThemKhachHangDiaLog themKhachHangDiaLog = new ThemKhachHangDiaLog(generateKHCode(), QuanLyKhachHangPanel.this);
		    themKhachHangDiaLog.setVisible(true);
		});

      	// Sự kiện cho btnXoa
      		btnXoa.addMouseListener(new MouseAdapter() {
      		    @Override
      		    public void mouseClicked(MouseEvent e) {
      		        if (e.getButton() == MouseEvent.BUTTON1) { // Kiểm tra chuột trái
      		            int selectedRow = table_1.getSelectedRow(); // Lấy dòng được chọn trong bảng
      		            if (selectedRow != -1) { // Kiểm tra nếu có dòng được chọn
      		                // Hiển thị hộp thoại xác nhận
      		                int confirm = JOptionPane.showConfirmDialog(
      		                    null,
      		                    "Bạn có chắc chắn muốn xóa khách hàng này?",
      		                    "Xác nhận Xóa",
      		                    JOptionPane.YES_NO_OPTION
      		                );

      		                if (confirm == JOptionPane.YES_OPTION) {
      		                    // Chuyển đổi chỉ số dòng từ bảng sang mô hình
      		                    int modelRow = table_1.convertRowIndexToModel(selectedRow);

      		                    // Xóa dòng khỏi mô hình dữ liệu
      		                    tableModel.removeRow(modelRow);

      		                    // Hiển thị thông báo thành công
      		                    JOptionPane.showMessageDialog(
      		                        null,
      		                        "Xóa khách hàng thành công!",
      		                        "Thông báo",
      		                        JOptionPane.INFORMATION_MESSAGE
      		                    );

      		                    // Xóa các trường dữ liệu hiển thị
      		                    txtMaKH.setText("");
      		                    txtTenKH.setText("");
      		                    txtSDT.setText("");
      		                    txtTienTichLuy.setText("");
      		                    txtCCCD.setText("");
      		                    txtEmail.setText("");
      		                    txtLoaiKH.setText("");
      		                }
      		            } else {
      		                // Hiển thị cảnh báo nếu không chọn dòng nào
      		                JOptionPane.showMessageDialog(
      		                    null,
      		                    "Vui lòng chọn một dòng để xóa!",
      		                    "Cảnh báo",
      		                    JOptionPane.WARNING_MESSAGE
      		                );
      		            }
      		        }
      		    }
      		});
      	// Sự kiện chuột cho btnXemTatCa
      		btnXemTatCa.addMouseListener(new MouseAdapter() {
      		    @Override
      		    public void mouseClicked(MouseEvent e) {
      		        sorter.setRowFilter(null); // Hiển thị toàn bộ dữ liệu
      		        txtTim.setText(""); // Xóa nội dung trong trường tìm kiếm
      		    }
      		});
        // Tải dữ liệu vào bảng (thêm hàm loadDataToTable() ở đây nếu cần thiết)
      		btnSua.addActionListener(e -> {
      			
				// Kiểm tra xem có dòng nào được chọn trong bảng không
				int selectedRow = table_1.getSelectedRow();
				if (selectedRow != -1) {
					// Lấy thông tin từ bảng vào các trường text của SuaDichVuDialog
					String maKH = table_1.getValueAt(selectedRow, 0).toString();
					String tenKH = table_1.getValueAt(selectedRow, 1).toString();
					String sdt = table_1.getValueAt(selectedRow, 2).toString();
					double tienTichLuy = Double.parseDouble(table_1.getValueAt(selectedRow, 3).toString());
					String soCanCuoc = table_1.getValueAt(selectedRow,4).toString();
					String email = table_1.getValueAt(selectedRow, 5).toString();
					// Lấy giá trị chuỗi từ cột 6 trong bảng
					String loaiKhString = table_1.getValueAt(selectedRow, 6).toString();

					// Kiểm tra và chuyển chuỗi thành enum LoaiKhachHang
					LoaiKhachHang loaiKH = LoaiKhachHang.valueOf(loaiKhString);  // Đây sẽ ném ra IllegalArgumentException nếu không tìm thấy giá trị hợp lệ

					
					

					// Tạo đối tượng DichVu và truyền vào dialog
					KhachHang khachHang= new KhachHang(maKH,tenKH,sdt,tienTichLuy,soCanCuoc,email,loaiKH);
					SuaKhachHangDiaLog suaDialog = new SuaKhachHangDiaLog(QuanLyKhachHangPanel.this, khachHang);
					suaDialog.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(QuanLyKhachHangPanel.this, "Vui lòng chọn khách hàng để sửa!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				}
			
		});
      		btnXoa.addActionListener(e -> {
      		    // Kiểm tra xem có dòng nào được chọn trong bảng không
      		    int selectedRow = table_1.getSelectedRow();
      		    if (selectedRow != -1) {
      		        // Lấy mã khách hàng từ cột 0 (mã khách hàng) của dòng đã chọn
      		        String maKH = table_1.getValueAt(selectedRow, 0).toString();

      		        // Hiển thị hộp thoại xác nhận trước khi xóa
      		        int confirm = JOptionPane.showConfirmDialog(QuanLyKhachHangPanel.this, 
      		                "Bạn có chắc chắn muốn xóa khách hàng này?", 
      		                "Xác nhận xóa", 
      		                JOptionPane.YES_NO_OPTION);

      		        // Nếu người dùng chọn "Yes", thực hiện xóa khách hàng
      		        if (confirm == JOptionPane.YES_OPTION) {
      		            KhachHangDAL dal = new KhachHangDAL();
      		            boolean result = dal.xoaKhachHang(maKH); // Gọi phương thức xóa khách hàng

      		            if (result) {
      		                // Nếu xóa thành công, hiển thị thông báo và cập nhật lại bảng
      		                JOptionPane.showMessageDialog(QuanLyKhachHangPanel.this, "Xóa khách hàng thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
      		                capNhatTable(); // Cập nhật lại bảng sau khi xóa
      		            } else {
      		                // Nếu không xóa được vì khách hàng đang đặt phòng, hiển thị thông báo lỗi
      		                JOptionPane.showMessageDialog(QuanLyKhachHangPanel.this, 
      		                        "Không thể xóa khách hàng này vì họ đang có đơn đặt phòng.", 
      		                        "Lỗi", 
      		                        JOptionPane.ERROR_MESSAGE);
      		            }
      		        }
      		    } else {
      		        JOptionPane.showMessageDialog(QuanLyKhachHangPanel.this, "Vui lòng chọn khách hàng để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
      		    }
      		});




        
    }
    

    
    


    // Phương thức sinh mã khách hàng mới dựa trên mã cuối cùng
    public static String normalizeVietnamese(String text) {
        String vietnameseSigns = "ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂĐÊÔƠƯ";
        String noSigns = "AAAAEEEIIOOOOUUAADIUOAaaaeiioooouuaduoUADOU";

        // Thay thế từng ký tự có dấu thành không dấu
        StringBuilder normalized = new StringBuilder();
        for (char c : text.toCharArray()) {
            int index = vietnameseSigns.indexOf(c);
            if (index >= 0) {
                normalized.append(noSigns.charAt(index));
            } else {
                normalized.append(c);
            }
        }
        return normalized.toString();
    }

    


    

    
    private void loadDichVuToTable() {
		KhachHangDAL khachHangDAL = new KhachHangDAL(); // Giả sử đây là lớp chứa hàm getAllDichVu()
		ArrayList<KhachHang> dsKhachHang = khachHangDAL.getAllKhachHang(); // Lấy danh sách dịch vụ từ cơ sở dữ liệu

		// Xóa dữ liệu cũ trên bảng (nếu có)
		tableModel.setRowCount(0);

		// Duyệt qua danh sách dịch vụ và thêm vào TableModel
		for (KhachHang khachHang : dsKhachHang) {
			Object[] rowData = { khachHang.getMaKH(), khachHang.getHoTen(), khachHang.getSoDienThoai(),
					khachHang.getTienTichLuy(), khachHang.getSoCanCuoc(),khachHang.getEmail(), khachHang.getLoaiKhachHang() };
			tableModel.addRow(rowData); // Thêm hàng vào mô hình bảng
		}
	}
    public void capNhatTable() {
		List<KhachHang> dsKhachHang = new KhachHangDAL().getAllKhachHang();

		// Cập nhật dữ liệu cho bảng
		DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		model.setRowCount(0); // Xóa các hàng cũ

		for (KhachHang kh : dsKhachHang) {
			model.addRow(new Object[] { kh.getMaKH(), kh.getHoTen(), kh.getSoDienThoai(),kh.getTienTichLuy(),kh.getSoCanCuoc(),kh.getEmail(),
					kh.getLoaiKhachHang()  });
		}

	}


    private void quayVeTrangThaiBanDau() {
		txtMaKH.setText("");
		txtTenKH.setText("");
		txtSDT.setText("");
		txtTienTichLuy.setText("");
		txtCCCD.setText("");
		txtEmail.setText("");
		txtLoaiKH.setText("");
		

		txtMaKH.setEditable(false);          // Không cho phép chỉnh sửa trường mã khách hàng
		txtTenKH.setEditable(false);         // Không cho phép chỉnh sửa trường tên khách hàng
		txtTienTichLuy.setEnabled(false);    // Tắt trường tiền tích lũy
		txtCCCD.setEnabled(false);           // Tắt trường CCCD
		txtEmail.setEnabled(false);          // Tắt trường email
		txtLoaiKH.setEnabled(false);  // Tắt combo box loại khách hàng
		btnSua.setVisible(true);             // Giữ nút sửa hiển thị
		btnThem.setVisible(true);            // Giữ nút thêm hiển thị

	}
    public String generateKHCode() {
        String lastKHCode = new KhachHangDAL().getLastMaKH(); // Lấy mã khách hàng cuối cùng
        int newKHNumber = 1; // Mặc định bắt đầu từ 1 nếu không có mã khách hàng nào

        if (lastKHCode != null && lastKHCode.startsWith("KH")) {
            // Tách phần số từ mã khách hàng (bỏ qua "KH")
            String numberPart = lastKHCode.substring(2); // Bỏ qua ký tự "KH"
            try {
                newKHNumber = Integer.parseInt(numberPart) + 1; // Tăng số lên 1
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Nếu có lỗi trong việc chuyển đổi, giữ nguyên newKHNumber là 1
            }
        }

        // Tạo mã khách hàng mới theo định dạng "KHXXXX" (với 4 chữ số)
        return String.format("KH%06d", newKHNumber); // Định dạng thành 4 chữ số
    }

    
    

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JFrame frame = new JFrame("Quản lí khách hàng");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(new QuanLyKhachHangPanel());
                frame.pack();
                frame.setSize(1920, 1080);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
