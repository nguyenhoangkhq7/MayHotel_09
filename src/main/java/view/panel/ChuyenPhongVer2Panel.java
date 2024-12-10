package view.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dal.ChiTiet_DonDatPhong_PhongDAL;
import dal.DichVuDAL;
import dal.DonDatPhongDAL;
import dal.NhanVienDAL;
import database.ConnectDB;
import entity.ChiTiet_DonDatPhong_Phong;
import entity.DichVu;
import entity.DonDatPhong;
import entity.Phong;
import view.MainGUI;
import view.dialog.SuaDichVuDialog;
import view.dialog.ThemDichVuDialog;

public class ChuyenPhongVer2Panel extends JPanel {
	

	private JTable table_2;
	private DefaultTableModel tableModel;
	private JTextField txtMaDV;
	private JTextField txtTenDV;
	private JButton btnSua;
	private JPanel panelForm;
	private JComboBox<String> cboHoatDong;
	private JButton btnThem;
	private MenuPanel menuPanel;
	private JButton btnChuyenPhong;
	private JTextField txtMaDDP;
	private JTextField txtMaPhong;
	private JTextField txtNgayNhanPhong;
	private JTextField txtNgayTraPhong;
	private JComboBox cboPhongChuyen;
	private JTextField txtChietKhau;
	
	
	/**
	 * Launch the application.
	 */

	public MenuPanel getMenuPanel() {
	
		return menuPanel;
	}

	/**
	 * Create the frame.
	 */
	public ChuyenPhongVer2Panel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(new Color(255, 255, 255));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel pnlTieuDe = new JPanel();
		pnlTieuDe.setBackground(new Color(255, 255, 255));
		add(pnlTieuDe);
		pnlTieuDe.setLayout(new FlowLayout());

		JLabel lblTieuDeTrang = new JLabel("Dịch vụ");
		lblTieuDeTrang.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDeTrang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblTieuDeTrang.setPreferredSize(new Dimension(600, 60));
		pnlTieuDe.add(lblTieuDeTrang);
		
		JPanel pnlBoLoc = new JPanel();
		pnlBoLoc.setBackground(new Color(255, 255, 255));
		pnlBoLoc.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE), "Bộ lọc dịch vụ",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));

		add(pnlBoLoc);
		pnlBoLoc.setLayout(new GridLayout(1, 1, 0, 0));

		panelForm = new JPanel();
		panelForm.setLayout(new GridLayout(3, 3, 10, 10));
		

		// Mã đơn đặt phòng
		JLabel lbMaDDP = new JLabel("Mã đơn đặt phòng:");
		lbMaDDP.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelForm.add(lbMaDDP);

		txtMaDDP = new JTextField();
		txtMaDDP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panelForm.add(txtMaDDP);

		// Phòng
		JLabel lbPhong = new JLabel("Mã phòng:");
		lbPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelForm.add(lbPhong);

		txtMaPhong = new JTextField();
		txtMaPhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panelForm.add(txtMaPhong);

		// Ngày nhận phòng
		JLabel lbNgayNhanPhong = new JLabel("Ngày nhận phòng:");
		lbNgayNhanPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelForm.add(lbNgayNhanPhong);

		txtNgayNhanPhong = new JTextField();
		txtNgayNhanPhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panelForm.add(txtNgayNhanPhong);

		// Ngày trả phòng
		JLabel lbNgayTraPhong = new JLabel("Ngày trả phòng:");
		lbNgayTraPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelForm.add(lbNgayTraPhong);

		txtNgayTraPhong = new JTextField();
		txtNgayTraPhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panelForm.add(txtNgayTraPhong);

		// Phòng chuyển
		JLabel lbLaPhongChuyen = new JLabel("Phòng chuyển:");
		lbLaPhongChuyen.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelForm.add(lbLaPhongChuyen);

		cboPhongChuyen = new JComboBox<>();
		cboPhongChuyen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboPhongChuyen.addItem("Có");
		cboPhongChuyen.addItem("Không");
		panelForm.add(cboPhongChuyen);

		// Chiết khấu
		JLabel lbChietKhau = new JLabel("Chiết khấu:");
		lbChietKhau.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelForm.add(lbChietKhau);

		txtChietKhau = new JTextField();
		txtChietKhau.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panelForm.add(txtChietKhau);

		// Thêm panel vào giao diện chính
		pnlBoLoc.add(panelForm);



		pnlBoLoc.add(panelForm);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE), "Chức năng",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

		JPanel panelTop = new JPanel();
		panelTop.setLayout(new FlowLayout()); 
		panelTop.setBackground(new Color(255, 255, 255));
		btnChuyenPhong = new JButton("ChuyenPhong");
		btnChuyenPhong.setBackground(new Color(243, 125, 0));
		btnChuyenPhong.setForeground(new Color(255, 255, 255));
		btnChuyenPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelTop.add(btnChuyenPhong);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTim.setForeground(new Color(255, 255, 255));
		btnTim.setBackground(new Color(253, 125, 0));
		panelTop.add(btnTim);

		panel_3.add(panelTop);
		

		add(panel_3);
		

		// Tạo một JPanel chứa cả pnlBoLoc và panel_3
		JPanel pnlContainer = new JPanel();
		pnlContainer.setLayout(new GridLayout(1, 2, 10, 0)); 
		pnlContainer.setBackground(new Color(255, 255, 255));

		pnlContainer.add(pnlBoLoc);
		pnlContainer.add(panel_3);

		add(pnlContainer);

	

		JPanel pnlBang1 = new JPanel();
		pnlBang1.setBackground(new Color(255, 255, 255));
		pnlBang1.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE), "Danh sách chi tiết đơn đặt phòng",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		add(pnlBang1);
		pnlBang1.setLayout(new BorderLayout());

		JScrollPane scrDSNV = new JScrollPane();
		pnlBang1.add(scrDSNV, BorderLayout.CENTER);

	
		tableModel = new DefaultTableModel(
				new String[] {  "Mã đơn đặt phòng", "Mã phòng", "Ngày nhận phòng", "Ngày nhận phòng", "Là phòng chuyển","Chiết khấu" }, 0);
		table_2 = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table_2);

		table_2.setForeground(new Color(0, 0, 0));
		table_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		table_2.setRowHeight(25);
		table_2.setSelectionBackground(new Color(0, 204, 204));
		table_2.setSelectionForeground(new Color(255, 255, 255));
		table_2.setFillsViewportHeight(true);

		scrDSNV.setViewportView(scrollPane);

		loadCT_DonDatPhongToTable();

		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table_2.getSelectedRow(); // Lấy dòng được chọn từ bảng
				if (selectedRow != -1) { // Kiểm tra nếu có dòng được chọn
					// Chuyển đổi chỉ số dòng từ bảng sang mô hình
					int modelRow = table_2.convertRowIndexToModel(selectedRow);

					// Lấy dữ liệu từ mô hình dựa trên chỉ số thực
					String maDonDat = Objects.toString(tableModel.getValueAt(modelRow, 0), "");
					String maPhong = Objects.toString(tableModel.getValueAt(modelRow, 1), "");
					String ngayNhanPhong = Objects.toString(tableModel.getValueAt(modelRow, 2), "");
					String ngayTraPhong = Objects.toString(tableModel.getValueAt(modelRow, 3), "");
					String laPhongChuyen = Objects.toString(tableModel.getValueAt(modelRow, 4), "");
					String chietKhau = Objects.toString(tableModel.getValueAt(modelRow, 5), "");
					

					// Hiển thị dữ liệu trong các JTextField và JComboBox
					txtMaDDP.setText(maDonDat);
					txtMaPhong.setText(maPhong);
					txtNgayNhanPhong.setText(ngayNhanPhong);
					txtNgayTraPhong.setText(ngayTraPhong);
					cboPhongChuyen.setSelectedItem(laPhongChuyen);
					txtChietKhau.setText(chietKhau);
					
				}
			}
		});
//Sự kiện cho btnTim
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
		table_2.setRowSorter(sorter);

		btnTim.addActionListener(e -> {
		    String maDV = txtMaDV.getText().trim();       // Lấy dữ liệu từ txtMaDV
		    String tenDV = txtTenDV.getText().trim();     // Lấy dữ liệu từ txtTenDV
		    String hoatDong = cboHoatDong.getSelectedItem().toString(); // Lấy dữ liệu từ JComboBox

		    List<RowFilter<Object, Object>> filters = new ArrayList<>();
		    try {
		        // Tạo các bộ lọc nếu có dữ liệu nhập
		        if (!maDV.isEmpty()) {
		            filters.add(RowFilter.regexFilter("(?i)" + maDV, 0)); // Cột 0: Mã DV
		        }
		        if (!tenDV.isEmpty()) {
		            filters.add(RowFilter.regexFilter("(?i)" + tenDV, 2)); // Cột 1: Tên DV
		        }
		        if (!hoatDong.equals("Không chọn")) {
		            filters.add(RowFilter.regexFilter(hoatDong, 4)); // Cột 2: Hoạt động
		        }

		        // Kết hợp tất cả các bộ lọc
		        if (filters.isEmpty()) {
		            sorter.setRowFilter(null); // Không lọc nếu không có điều kiện nào
		        } else {
		            sorter.setRowFilter(RowFilter.andFilter(filters)); // Áp dụng lọc
		        }
		    } catch (PatternSyntaxException ex) {
		        JOptionPane.showMessageDialog(null, "Lỗi trong từ khóa tìm kiếm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		    }
		});

//Sự kiện thêm
		

//Sự kiện sửa
//		btnSua.addActionListener(e -> {
//	
//				// Kiểm tra xem có dòng nào được chọn trong bảng không
//				int selectedRow = table_2.getSelectedRow();
//				if (selectedRow != -1) {
//					// Lấy thông tin từ bảng vào các trường text của SuaDichVuDialog
//					String maDichVu = table_2.getValueAt(selectedRow, 0).toString();
//					double donGia = Double.parseDouble(table_2.getValueAt(selectedRow, 1).toString());
//					String tenDichVu = table_2.getValueAt(selectedRow, 2).toString();
//					int soLuongTon = Integer.parseInt(table_2.getValueAt(selectedRow, 3).toString());
//					String conHoatDongString = table_2.getValueAt(selectedRow, 4).toString();
//					boolean conHoatDong = "Có".equals(conHoatDongString);
//					String donVi = table_2.getValueAt(selectedRow, 5).toString();
//
//					// Tạo đối tượng DichVu và truyền vào dialog
//					DichVu dichVu = new DichVu(maDichVu, donGia, tenDichVu, soLuongTon, conHoatDong, donVi);
//					SuaDichVuDialog suaDialog = new SuaDichVuDialog(ChuyenPhongVer2Panel.this, dichVu);
//					suaDialog.setVisible(true);
//				} else {
//					JOptionPane.showMessageDialog(ChuyenPhongVer2Panel.this, "Vui lòng chọn dịch vụ để sửa!", "Lỗi",
//							JOptionPane.ERROR_MESSAGE);
//				}
//			
//		});

	}

	

	private void loadCT_DonDatPhongToTable() {
	    ChiTiet_DonDatPhong_PhongDAL CT_donDatPhongDAL = new ChiTiet_DonDatPhong_PhongDAL(); // Giả sử đây là lớp chứa hàm getAllDonDatPhong()
	    ArrayList<ChiTiet_DonDatPhong_Phong> danhSachCTDonDatPhong = CT_donDatPhongDAL.getAllChiTietDonDatPhongPhong(); // Lấy danh sách đơn đặt phòng từ cơ sở dữ liệu

	    // Xóa dữ liệu cũ trên bảng (nếu có)
	    tableModel.setRowCount(0);

	    // Duyệt qua danh sách đơn đặt phòng và thêm vào TableModel
	    for (ChiTiet_DonDatPhong_Phong chiTietDonDatPhong : danhSachCTDonDatPhong) {
	        Object[] rowData = { 
	        		chiTietDonDatPhong.getDonDatPhong().getMaDon(), 
	        		chiTietDonDatPhong.getPhong().getMaPhong(), // Mã phòng từ đối tượng Phong
	        		chiTietDonDatPhong.getNgayNhanPhong(), 
	        		chiTietDonDatPhong.getNgayTraPhong(), 
	        		chiTietDonDatPhong.isLaPhongChuyen() ? "Có" : "Không", 
	        		chiTietDonDatPhong.getChietKhau() };
	        tableModel.addRow(rowData); // Thêm hàng vào mô hình bảng
	    }
	}


	public void capNhatTable() {
	    // Giả sử danh sách DonDatPhong có dữ liệu đã được truy xuất từ cơ sở dữ liệu hoặc bộ dữ liệu khác
	    List<ChiTiet_DonDatPhong_Phong> danhSachCTDonDatPhong = new ChiTiet_DonDatPhong_PhongDAL().getAllChiTietDonDatPhongPhong();

	    // Cập nhật dữ liệu cho bảng
	    DefaultTableModel model = (DefaultTableModel) table_2.getModel();
	    model.setRowCount(0); // Xóa các hàng cũ

	    for (ChiTiet_DonDatPhong_Phong chiTietDonDatPhong : danhSachCTDonDatPhong) {
	        // Lấy thông tin từ đối tượng DonDatPhong và Phong
	    	DonDatPhong donDatPhong = chiTietDonDatPhong.getDonDatPhong();
	        Phong phong = chiTietDonDatPhong.getPhong(); // Giả sử đối tượng Phong được lấy từ DonDatPhong

	        // Chuyển đổi thời gian LocalDateTime thành chuỗi để hiển thị
	        String ngayNhanPhong = donDatPhong.getNgayNhanPhong().toLocalDate().toString();
	        String ngayTraPhong = donDatPhong.getNgayTraPhong().toLocalDate().toString();

	        // Thêm một hàng vào bảng với các thông tin từ DonDatPhong, Phong và các thuộc tính khác
	        model.addRow(new Object[] {
	            donDatPhong.getMaDon(),
	            phong.getMaPhong(),
	            ngayNhanPhong,
	            ngayTraPhong,
	            chiTietDonDatPhong.isLaPhongChuyen() ? "Có" : "Không",
	            chiTietDonDatPhong.getChietKhau(),
	        });
	    }
	}


	private void quayVeTrangThaiBanDau() {
	    // Đặt lại các trường nhập liệu về trạng thái ban đầu

	    // Đặt lại các trường nhập liệu cho thông tin về đơn đặt phòng và phòng
	    txtMaDDP.setText(""); // Đặt lại mã đơn đặt phòng
	    txtMaPhong.setText(""); // Đặt lại mã phòng
	    txtNgayNhanPhong.setText(""); // Đặt lại ngày nhận phòng
	    txtNgayTraPhong.setText(""); // Đặt lại ngày trả phòng
	    cboPhongChuyen.setSelectedIndex(0); // Đặt lại combo box "Phòng chuyển"
	    txtChietKhau.setText(""); // Đặt lại chiết khấu

	    // Đặt các trường không thể chỉnh sửa về trạng thái ban đầu
	    txtMaDDP.setEditable(false); // Không cho phép chỉnh sửa mã đơn đặt phòng
	    txtMaPhong.setEditable(false); // Không cho phép chỉnh sửa mã phòng
	    txtNgayNhanPhong.setEditable(false); // Không cho phép chỉnh sửa ngày nhận phòng
	    txtNgayTraPhong.setEditable(false); // Không cho phép chỉnh sửa ngày trả phòng
	    cboPhongChuyen.setEnabled(false); // Không cho phép chỉnh sửa trạng thái phòng chuyển
	    txtChietKhau.setEditable(false); // Không cho phép chỉnh sửa chiết khấu
	}



	public static void main(String[] args) {
		JFrame frame = new JFrame("Quản lý Chuyển phòng");
        ChuyenPhongVer2Panel panel = new ChuyenPhongVer2Panel();
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 600);
	}
}
