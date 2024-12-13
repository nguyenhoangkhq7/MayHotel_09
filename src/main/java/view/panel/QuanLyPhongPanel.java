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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.PatternSyntaxException;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import constant.CommonConstants;
import dal.DichVuDAL;
import dal.LoaiPhongDAL;
import dal.PhongDAL;
import database.ConnectDB;
import entity.DichVu;
import entity.LoaiPhong;
import entity.Phong;
import view.dialog.SuaDichVuDialog;
import view.dialog.SuaPhongDialong;
import view.dialog.ThemDichVuDialog;
import view.dialog.ThemPhongDiaLog;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class QuanLyPhongPanel extends JPanel {

    private JTextField txtMaKH;
    private JTextField txtTenKH;
    private JTextField txtDiaChi;
    private JTextField txtSDT;
    private JTextField txtLoaiPhong;
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
	private JTable table_1;
	private DefaultTableModel tableModel;
	private JTextField txtTang;
	private JButton btnLamMoi;


    public QuanLyPhongPanel() {
    	setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 255));
        setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel pnlTieuDe = new JPanel();
        pnlTieuDe.setBackground(new Color(255, 255, 255));
        
        pnlTieuDe.setLayout(new FlowLayout());

        JLabel lblTieuDeTrang = new JLabel("QUẢN LÝ PHÒNG");
        lblTieuDeTrang.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDeTrang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        pnlTieuDe.add(lblTieuDeTrang);

        JPanel pnlThongTin = new JPanel();
        pnlThongTin.setBackground(new Color(255, 255, 255));
        pnlThongTin.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE),
                "Thiết lập thông tin phòng", TitledBorder.LEADING, TitledBorder.TOP, null, CommonConstants.ORANGE));
       
        add(pnlThongTin);
        pnlThongTin.setLayout(new BorderLayout());
        
        panelForm = new JPanel();
        panelForm.setLayout(new GridLayout(3,3,10,10));
        
        JLabel lbMaPhong = new JLabel("Mã phòng:");
		lbMaPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelForm.add(lbMaPhong);

		txtMaPhong = new JTextField("");
		txtMaPhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panelForm.add(txtMaPhong);
		
		
		JLabel lbTenPhong = new JLabel("Tên phòng:");
        lbTenPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbTenPhong.setBounds(10, 100, 136, 14);
        panelForm.add(lbTenPhong);
        
        txtTenPhong = new JTextField();
        txtTenPhong.setFont(new Font("Dialog", Font.BOLD, 13));
        txtTenPhong.setBounds(145, 97, 205, 20);
        panelForm.add(txtTenPhong);
        
        JLabel lbLoaiPhong = new JLabel("Loại Phòng :");
        lbLoaiPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbLoaiPhong.setBounds(10, 151, 105, 14);
        panelForm.add(lbLoaiPhong);
        txtLoaiPhong = new JTextField();
        txtLoaiPhong.setFont(new Font("Dialog", Font.BOLD, 13));
        txtLoaiPhong.setBounds(145, 97, 205, 20);
        panelForm.add(txtLoaiPhong);
        

        JLabel lbTrangThaiPhong = new JLabel("Trạng Thái Phòng :");
        lbTrangThaiPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbTrangThaiPhong.setBounds(10, 151, 105, 14);
        panelForm.add(lbTrangThaiPhong);
        cboTrangThaiPhong = new JComboBox<>();
        cboTrangThaiPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
        cboTrangThaiPhong.addItem("");
        cboTrangThaiPhong.addItem("Trống");
        cboTrangThaiPhong.addItem("Đang ở");
        panelForm.add(cboTrangThaiPhong);

        JLabel lbTang = new JLabel("Tầng :");
        lbTang.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbTang.setBounds(10, 151, 105, 14);
        panelForm.add(lbTang);
        
        cboTang = new JComboBox<>();
        cboTang.setFont(new Font("Tahoma", Font.BOLD, 13));
        cboTang.addItem("");
        cboTang.addItem("Tầng 1");
        cboTang.addItem("Tầng 2");
        cboTang.addItem("Tầng 3");
        cboTang.addItem("Tầng 4");
        cboTang.addItem("Tầng 5");
        panelForm.add(cboTang);
        
        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 255, 255));
        pnlThongTin.add(panelForm);
        pnlThongTin.add(panel_2);
        
        
        JPanel panel_ThongTinLoaiPhong = new JPanel();
        panel_ThongTinLoaiPhong.setBackground(new Color(255, 255, 255));
        panel_ThongTinLoaiPhong.setBorder(new TitledBorder(null, "Chức năng", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        // Sử dụng GridLayout với 2 hàng và 2 cột
        panel_ThongTinLoaiPhong.setLayout(new GridLayout(2, 2, 10, 10)); // 2 hàng, 2 cột, khoảng cách giữa các nút là 10px

        // Tạo và thêm nút "Thêm"
        btnThem = new JButton("Thêm");
        btnThem.setBackground(new Color(0, 255, 255));
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_ThongTinLoaiPhong.add(btnThem);

        // Tạo và thêm nút "Sửa"
        btnSua = new JButton("Sửa");
        btnSua.setBackground(new Color(0, 255, 255));
        btnSua.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_ThongTinLoaiPhong.add(btnSua);

        // Tạo và thêm nút "Xóa"
        btnXoa = new JButton("Xóa");
        btnXoa.setBackground(new Color(0, 255, 255));
        btnXoa.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_ThongTinLoaiPhong.add(btnXoa);

        // Tạo và thêm nút "Làm mới"
        btnLamMoi = new JButton("Làm mới");
        btnLamMoi.setBackground(new Color(0, 255, 255));
        btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_ThongTinLoaiPhong.add(btnLamMoi);


        pnlThongTin.add(panelForm, BorderLayout.CENTER);
        pnlThongTin.add(panel_ThongTinLoaiPhong, BorderLayout.EAST);
        pnlThongTin.add(pnlTieuDe,BorderLayout.NORTH);
        

        
        

        

        JPanel pnlBang = new JPanel();
        pnlBang.setBackground(new Color(255, 255, 255));
        pnlBang.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE),
                "Chi tết quản lý phòng", TitledBorder.LEADING, TitledBorder.TOP, null, CommonConstants.ORANGE));
        add(pnlBang);
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

        

        // Thêm Box ngang vào vị trí phía trên (NORTH) của pnlBang
        pnlBang.add(hBox, BorderLayout.NORTH);
        
        add(pnlThongTin, BorderLayout.NORTH); // Thêm pnlThongTin vào vùng NORTH
        add(pnlBang, BorderLayout.CENTER);
        
        
        tableModel = new DefaultTableModel(
				new String[] { "Mã Phòng", "Tên Phòng","Loại Phòng", "Trạng Thái Phòng", "Mô Tả", "Tầng"}, 0);
		table_1 = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table_1);

		table_1.setForeground(new Color(0, 0, 0));
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		table_1.setRowHeight(25);
		table_1.setSelectionBackground(new Color(0, 204, 204));
		table_1.setSelectionForeground(new Color(255, 255, 255));
		table_1.setFillsViewportHeight(true);

		scrDSKH.setViewportView(scrollPane);
		table_1.setVisible(false);


        table_1.setForeground(new Color(0, 0, 0));
        table_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        table_1.setRowHeight(25);
        table_1.setSelectionBackground(new Color(0, 204, 204));
        table_1.setSelectionForeground(new Color(255, 255, 255));
        table_1.setFillsViewportHeight(true);

        // Thêm JTable vào JScrollPane
        scrDSKH.setViewportView(table_1);
        loadDichVuToTable();
        
     // Thêm MouseListener cho nút Sửa
     // Đăng ký MouseListener cho bảng
        table_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table_1.getSelectedRow(); // Lấy dòng được chọn từ bảng
                if (selectedRow != -1) { // Kiểm tra nếu có dòng được chọn
                    // Chuyển đổi chỉ số dòng từ bảng sang mô hình
                    int modelRow = table_1.convertRowIndexToModel(selectedRow);

                    // Lấy dữ liệu từ mô hình dựa trên chỉ số thực
                    String maPhong = Objects.toString(tableModel.getValueAt(modelRow, 0), "");
                    String tenPhong = Objects.toString(tableModel.getValueAt(modelRow, 1), "");
                    String loaiPhong = Objects.toString(tableModel.getValueAt(modelRow, 2), "");
                    String trangThaiPhong = Objects.toString(tableModel.getValueAt(modelRow, 3), "");
                    String moTa = Objects.toString(tableModel.getValueAt(modelRow, 4), "");
                    String tang = Objects.toString(tableModel.getValueAt(modelRow, 5), "");


                }
            }
        });
        btnLamMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xóa nội dung của các trường JTextField
                txtMaPhong.setText("");
                txtLoaiPhong.setText("");
                txtTenPhong.setText("");
                cboTrangThaiPhong.setSelectedIndex(0); // Reset trạng thái về giá trị mặc định (ví dụ: "Tất cả")
                cboTang.setSelectedIndex(0); // Reset tầng về giá trị mặc định (ví dụ: "Tất cả")
                table_1.setVisible(true);
            }
        });
        txtTenPhong.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                // Lấy giá trị nhập vào từ trường txtTenPhong
                String keyword = txtTenPhong.getText().trim();

                // Sử dụng TableRowSorter để lọc theo từ khóa
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
                table_1.setRowSorter(sorter);

                // Kiểm tra nếu từ khóa trống, không lọc
                if (keyword.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    try {
                        // Lọc chỉ theo cột "Tên phòng" (giả sử cột "Tên phòng" là cột thứ 1)
                        RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter("(?i)" + keyword, 1);  // 1 là chỉ số cột "Tên phòng"
                        sorter.setRowFilter(rf); // Áp dụng bộ lọc vào sorter
                    } catch (PatternSyntaxException ex) {
                        JOptionPane.showMessageDialog(null, "Từ khóa không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
     // Sự kiện tìm kiếm tự động cho txtLoaiPhong
        txtLoaiPhong.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                // Lấy giá trị nhập vào từ trường txtLoaiPhong
                String keyword = txtLoaiPhong.getText().trim();

                // Sử dụng TableRowSorter để lọc theo từ khóa
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
                table_1.setRowSorter(sorter);

                // Kiểm tra nếu từ khóa trống, không lọc
                if (keyword.isEmpty()) {
                    sorter.setRowFilter(null); // Nếu từ khóa rỗng, không lọc
                } else {
                    try {
                        // Thực hiện lọc dữ liệu trong bảng, chỉ lọc cột "Loại phòng" (giả sử là cột 2)
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword, 2)); // 2 là chỉ số cột "Loại phòng"
                    } catch (PatternSyntaxException ex) {
                        JOptionPane.showMessageDialog(null, "Từ khóa không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

     // Tạo TableRowSorter cho bảng
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table_1.setRowSorter(sorter);

        // Thêm sự kiện KeyListener vào txtMaPhong để lọc khi người dùng nhập
        txtMaPhong.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String maPhongKeyword = txtMaPhong.getText().trim(); // Lấy nội dung mã phòng từ txtMaPhong
                String selectedTrangThai = (String) cboTrangThaiPhong.getSelectedItem(); // Lấy trạng thái phòng được chọn
                String selectedTang = (String) cboTang.getSelectedItem(); // Lấy tầng được chọn

                // Tạo danh sách các bộ lọc
                List<RowFilter<Object, Object>> filters = new ArrayList<>();

                // Lọc theo mã phòng (cột 0)
                if (!maPhongKeyword.isEmpty()) {
                    filters.add(RowFilter.regexFilter("(?i)^" + maPhongKeyword, 0));
                }

                // Lọc theo trạng thái phòng (cột 3)
                if (selectedTrangThai != null && !selectedTrangThai.isEmpty()) {
                    filters.add(RowFilter.regexFilter("(?i)" + selectedTrangThai, 3));
                }

                // Lọc theo tầng (cột 5)
                if (selectedTang != null && !selectedTang.isEmpty()) {
                    filters.add(RowFilter.regexFilter("(?i)" + selectedTang, 5));
                }

                // Áp dụng bộ lọc kết hợp
                if (filters.isEmpty()) {
                    sorter.setRowFilter(null); // Nếu không có bộ lọc, hiển thị tất cả
                } else {
                    RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filters);
                    sorter.setRowFilter(combinedFilter);
                }
            }
        });


        

      //Sự kiện cho btnTim
      		

      		btnTim.addActionListener(e -> {
      			String keyword = txtTim.getText().trim();
      			if (keyword.isEmpty()) {
      				sorter.setRowFilter(null);
      			} else {
      				try {

      					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
      				} catch (PatternSyntaxException ex) {
      					JOptionPane.showMessageDialog(null, "Từ khóa không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
      				}
      			}
      		});
      		//Trạng thái phòng
      		cboTrangThaiPhong.addActionListener(e -> {
      		    String selectedTrangThai = (String) cboTrangThaiPhong.getSelectedItem(); // Lấy trạng thái phòng được chọn
      		    String maPhongKeyword = txtMaPhong.getText().trim(); // Lấy nội dung mã phòng từ txtMaPhong
      		    String selectedTang = (String) cboTang.getSelectedItem(); // Lấy tầng được chọn

      		    // Tạo danh sách các bộ lọc
      		    List<RowFilter<Object, Object>> filters = new ArrayList<>();

      		    // Lọc theo mã phòng
      		    if (!maPhongKeyword.isEmpty()) {
      		        filters.add(RowFilter.regexFilter("(?i)^" + maPhongKeyword, 0)); // Lọc theo mã phòng (cột 0)
      		    }

      		    // Lọc theo trạng thái phòng
      		    if (selectedTrangThai != null && !selectedTrangThai.isEmpty()) {
      		        filters.add(RowFilter.regexFilter("(?i)" + selectedTrangThai, 3)); // Lọc trạng thái phòng (cột 3)
      		    }

      		    // Lọc theo tầng
      		    if (selectedTang != null && !selectedTang.isEmpty()) {
      		        filters.add(RowFilter.regexFilter("(?i)" + selectedTang, 5)); // Lọc tầng (cột 5)
      		    }

      		    // Áp dụng bộ lọc kết hợp
      		    if (filters.isEmpty()) {
      		        sorter.setRowFilter(null); // Nếu không có bộ lọc, hiển thị tất cả
      		    } else {
      		        RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filters);
      		        sorter.setRowFilter(combinedFilter);
      		    }
      		});
      		


      	// Sự kiện cho Tầng
      		cboTang.addActionListener(e -> {
      		    String selectedTang = (String) cboTang.getSelectedItem(); // Lấy tầng được chọn
      		    String selectedTrangThai = (String) cboTrangThaiPhong.getSelectedItem(); // Lấy trạng thái phòng
      		    String maPhongKeyword = txtMaPhong.getText().trim(); // Lấy nội dung mã phòng từ txtMaPhong

      		    // Tạo danh sách các bộ lọc
      		    List<RowFilter<Object, Object>> filters = new ArrayList<>();

      		    // Lọc theo mã phòng
      		    if (!maPhongKeyword.isEmpty()) {
      		        filters.add(RowFilter.regexFilter("(?i)^" + maPhongKeyword, 0)); // Lọc theo mã phòng (cột 0)
      		    }

      		    // Lọc theo trạng thái phòng
      		    if (selectedTrangThai != null && !selectedTrangThai.isEmpty()) {
      		        filters.add(RowFilter.regexFilter("(?i)" + selectedTrangThai, 3)); // Lọc trạng thái phòng (cột 3)
      		    }

      		    // Lọc theo tầng
      		    if (selectedTang != null && !selectedTang.isEmpty()) {
      		        filters.add(RowFilter.regexFilter("(?i)" + selectedTang, 5)); // Lọc tầng (cột 5)
      		    }

      		    // Áp dụng bộ lọc kết hợp
      		    if (filters.isEmpty()) {
      		        sorter.setRowFilter(null); // Nếu không có bộ lọc, hiển thị tất cả
      		    } else {
      		        RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filters);
      		        sorter.setRowFilter(combinedFilter);
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
      		btnThem.addActionListener(e -> {
    		    String nextMaPhong = generateRoomCode();
    		    ThemPhongDiaLog themPhongDiaLog = new ThemPhongDiaLog(generateRoomCode(), QuanLyPhongPanel.this);
    		    themPhongDiaLog.setVisible(true);
    		});
      		btnSua.addActionListener(e -> {
      			
				// Kiểm tra xem có dòng nào được chọn trong bảng không
				int selectedRow = table_1.getSelectedRow();
				if (selectedRow != -1) {
					// Lấy thông tin từ bảng vào các trường text của SuaDichVuDialog
					String maPhong = table_1.getValueAt(selectedRow, 0).toString();
					String tenPhong = table_1.getValueAt(selectedRow, 1).toString();
					LoaiPhong loaiPhong = new LoaiPhongDAL().getLoaiPhongTheoMa(table_1.getValueAt(selectedRow, 2).toString());
					String trangThaiPhongString = table_1.getValueAt(selectedRow, 3).toString();
					boolean trangThaiPhong = "Trống".equals(trangThaiPhongString);
					String moTa = table_1.getValueAt(selectedRow, 4).toString();
					String tang = table_1.getValueAt(selectedRow, 5).toString();
					// Tạo đối tượng DichVu và truyền vào dialog
					Phong phong = new Phong(maPhong, tenPhong, loaiPhong, trangThaiPhong, moTa, tang);
					SuaPhongDialong suaDialog = new SuaPhongDialong(QuanLyPhongPanel.this, phong);
					suaDialog.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(QuanLyPhongPanel.this, "Vui lòng chọn phòng để sửa!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				}
			
		});
      		btnXoa.addActionListener(e -> {
      		    // Kiểm tra xem có dòng nào được chọn trong bảng không
      		    int selectedRow = table_1.getSelectedRow();
      		    if (selectedRow != -1) {
      		        // Lấy thông tin từ bảng
      		        String maPhong = table_1.getValueAt(selectedRow, 0).toString();
      		        String tenPhong = table_1.getValueAt(selectedRow, 1).toString();
      		        
      		        // Hiển thị hộp thoại xác nhận xóa
      		        int confirm = JOptionPane.showConfirmDialog(QuanLyPhongPanel.this, 
      		            "Bạn chắc chắn muốn xóa phòng " + tenPhong + "?", "Xác nhận xóa", 
      		            JOptionPane.YES_NO_OPTION);

      		        if (confirm == JOptionPane.YES_OPTION) {
      		            // Nếu người dùng đồng ý, gọi phương thức xóa phòng
      		            PhongDAL phongDAL = new PhongDAL();
      		            boolean isSuccess = phongDAL.xoaPhong(maPhong);

      		            if (isSuccess) {
      		                JOptionPane.showMessageDialog(QuanLyPhongPanel.this, "Xóa phòng thành công!", 
      		                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
      		                // Cập nhật lại bảng sau khi xóa
      		                capNhatTable();
      		            } else {
      		                JOptionPane.showMessageDialog(QuanLyPhongPanel.this, "Xóa phòng thất bại!", 
      		                    "Lỗi", JOptionPane.ERROR_MESSAGE);
      		            }
      		        }
      		    } else {
      		        JOptionPane.showMessageDialog(QuanLyPhongPanel.this, "Vui lòng chọn phòng để xóa!", 
      		            "Lỗi", JOptionPane.ERROR_MESSAGE);
      		    }
      		});



    }
    
    public String generateRoomCode() {
        String lastRoomCode = new PhongDAL().getLastRoomCode(); // Lấy mã phòng cuối cùng
        int newRoomNumber = 1; // Mặc định bắt đầu từ 1 nếu không có mã phòng nào

        if (lastRoomCode != null) {
            // Tách phần số từ mã phòng
            String numberPart = lastRoomCode.substring(1); // Bỏ qua ký tự 'P'
            newRoomNumber = Integer.parseInt(numberPart) + 1; // Tăng số lên 1
        }

        // Tạo mã phòng mới theo định dạng "PXXX"
        return String.format("P%03d", newRoomNumber); // Định dạng thành 3 chữ số
    }
    private void loadDichVuToTable() {
        PhongDAL phongDAL = new PhongDAL(); // Giả sử đây là lớp chứa hàm 
        ArrayList<Phong> dsPhong = phongDAL.getAllPhong(); // Lấy danh sách dịch vụ từ cơ sở dữ liệu

        // Xóa dữ liệu cũ trên bảng (nếu có)
        tableModel.setRowCount(0);

        // Duyệt qua danh sách dịch vụ và thêm vào TableModel
        for (Phong phong : dsPhong) {
            // Lấy mã loại phòng thay vì đối tượng loại phòng
            String maLoaiPhong = phong.getLoaiPhong().getMaLoaiPhong(); // Giả sử getLoaiPhong() trả về đối tượng LoaiPhong

            Object[] rowData = { 
                phong.getMaPhong(), 
                phong.getTenPhong(), 
                maLoaiPhong, // Chỉ hiển thị maLoaiPhong
                phong.isTrangThaiPhong() ? "Trống" : "Đang ở", 
                phong.getMoTa(),
                phong.getTang() 
            };
            tableModel.addRow(rowData); // Thêm hàng vào mô hình bảng
        }
    }

   
    public void capNhatTable() {
		List<Phong> dsPhong = new PhongDAL().getAllPhong();

		// Cập nhật dữ liệu cho bảng
		DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		model.setRowCount(0); // Xóa các hàng cũ

		for (Phong p : dsPhong) {
			
			model.addRow(new Object[] { p.getMaPhong(), p.getTenPhong(), p.getLoaiPhong().getMaLoaiPhong(),
					p.isTrangThaiPhong() ? "Trống" : "Đang ở", p.getMoTa(),p.getTang() });
		}

	}
    
    private void quayVeTrangThaiBanDau() {
		txtMaPhong.setText("");
		txtTenPhong.setText("");
		txtLoaiPhong.setText("");
		cboTrangThaiPhong.setSelectedIndex(0);
		txtMoTa.setText("");
		txtTang.setText("");

		txtMaPhong.setEditable(false);
		txtTenPhong.setEditable(false);
		txtLoaiPhong.setEnabled(false);
		cboTrangThaiPhong.setEnabled(false);
		txtMoTa.setEnabled(false);
		txtTang.setEnabled(false);
		btnSua.setVisible(true);
		btnThem.setVisible(true);
	}

    public static void main(String[] args) {
    	EventQueue.invokeLater(() -> {
            try {
                JFrame frame = new JFrame("Quản lí phòng");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(new QuanLyPhongPanel());
                frame.pack();
                frame.setSize(800, 600);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
