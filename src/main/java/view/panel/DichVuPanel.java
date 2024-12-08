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

import dal.DichVuDAL;
import dal.NhanVienDAL;
import database.ConnectDB;
import entity.DichVu;
import view.MainGUI;
import view.dialog.SuaDichVuDialog;
import view.dialog.ThemDichVuDialog;

public class DichVuPanel extends JPanel {
	

	private JTable table_2;
	private DefaultTableModel tableModel;
	private JTextField txtMaDV;
	private JTextField txtTenDV;
	private JButton btnSua;
	private JPanel panelForm;
	private JComboBox<String> cboHoatDong;
	private JButton btnThem;
	private MenuPanel menuPanel;
	
	/**
	 * Launch the application.
	 */

	public MenuPanel getMenuPanel() {
	
		return menuPanel;
	}

	/**
	 * Create the frame.
	 */
	public DichVuPanel(MenuPanel menuPanel) {
		this.menuPanel = menuPanel;
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

		JLabel lbMaDV = new JLabel("Mã dịch vụ:");
		lbMaDV.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelForm.add(lbMaDV);

		txtMaDV = new JTextField();
		txtMaDV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panelForm.add(txtMaDV);

		JLabel lbTenDV = new JLabel("Tên dịch vụ:");
		lbTenDV.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelForm.add(lbTenDV);

		txtTenDV = new JTextField();
		txtTenDV.setFont(new Font("Dialog", Font.BOLD, 13));
		panelForm.add(txtTenDV);


		JLabel lbConHoatDong = new JLabel("ConHoatDong:");
		lbConHoatDong.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelForm.add(lbConHoatDong);

		cboHoatDong = new JComboBox<>();
		cboHoatDong.setFont(new Font("Tahoma", Font.BOLD, 13));
		cboHoatDong.addItem("");
		cboHoatDong.addItem("Có");
		cboHoatDong.addItem("Không");
		panelForm.add(cboHoatDong);


		pnlBoLoc.add(panelForm);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE), "Chức năng",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

		JPanel panelTop = new JPanel();
		panelTop.setLayout(new FlowLayout()); 
		panelTop.setBackground(new Color(255, 255, 255));

		btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(243, 125, 0));
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelTop.add(btnThem);

		btnSua = new JButton("Sửa");
		btnSua.setBackground(new Color(243, 125, 0));
		btnSua.setForeground(new Color(255, 255, 255));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 13));
		if(menuPanel.getNhanVienDangTruc().getVaiTro().equals("Nhân viên"))
		{
			btnThem.setEnabled(false);
			btnSua.setEnabled(false);
			
		}
		panelTop.add(btnSua);
		
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
		pnlBang1.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE), "Danh sách dịch vụ",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		add(pnlBang1);
		pnlBang1.setLayout(new BorderLayout());

		JScrollPane scrDSNV = new JScrollPane();
		pnlBang1.add(scrDSNV, BorderLayout.CENTER);

	
		tableModel = new DefaultTableModel(
				new String[] { "Mã Dịch Vụ", "Đơn Giá", "Tên Dịch Vụ", "Số Lượng Tồn", "Còn Hoạt Động", "Đơn Vị" }, 0);
		table_2 = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table_2);

		table_2.setForeground(new Color(0, 0, 0));
		table_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		table_2.setRowHeight(25);
		table_2.setSelectionBackground(new Color(0, 204, 204));
		table_2.setSelectionForeground(new Color(255, 255, 255));
		table_2.setFillsViewportHeight(true);

		scrDSNV.setViewportView(scrollPane);

		loadDichVuToTable();

//Sự kiện cho click row
//		table_2.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				int selectedRow = table_2.getSelectedRow(); // Lấy dòng được chọn từ bảng
//				if (selectedRow != -1) { // Kiểm tra nếu có dòng được chọn
//					// Chuyển đổi chỉ số dòng từ bảng sang mô hình
//					int modelRow = table_2.convertRowIndexToModel(selectedRow);
//
//					// Lấy dữ liệu từ mô hình dựa trên chỉ số thực
//					String maDV = Objects.toString(tableModel.getValueAt(modelRow, 0), "");
//					String donGia = Objects.toString(tableModel.getValueAt(modelRow, 1), "");
//					String tenDV = Objects.toString(tableModel.getValueAt(modelRow, 2), "");
//					String soLuong = Objects.toString(tableModel.getValueAt(modelRow, 3), "");
//					String hoatDong = Objects.toString(tableModel.getValueAt(modelRow, 4), "");
//					String donVi = Objects.toString(tableModel.getValueAt(modelRow, 5), "");
//
//					// Hiển thị dữ liệu trong các JTextField và JComboBox
//					txtMaDV.setText(maDV);
//					txtTenDV.setText(tenDV);
//					txtDonGia.setText(donGia);
//					txtSoLuong.setText(soLuong);
//					cboHoatDong.setSelectedItem(hoatDong);
//					txtDonVi.setText(donVi);
//				}
//			}
//		});
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
		btnThem.addActionListener(e -> {
		    String nextMaDV = getNextMaDichVu();
		    ThemDichVuDialog themDichVuDialog = new ThemDichVuDialog(nextMaDV, DichVuPanel.this);
		    themDichVuDialog.setVisible(true);
		});

//Sự kiện sửa
		btnSua.addActionListener(e -> {
	
				// Kiểm tra xem có dòng nào được chọn trong bảng không
				int selectedRow = table_2.getSelectedRow();
				if (selectedRow != -1) {
					// Lấy thông tin từ bảng vào các trường text của SuaDichVuDialog
					String maDichVu = table_2.getValueAt(selectedRow, 0).toString();
					double donGia = Double.parseDouble(table_2.getValueAt(selectedRow, 1).toString());
					String tenDichVu = table_2.getValueAt(selectedRow, 2).toString();
					int soLuongTon = Integer.parseInt(table_2.getValueAt(selectedRow, 3).toString());
					String conHoatDongString = table_2.getValueAt(selectedRow, 4).toString();
					boolean conHoatDong = "Có".equals(conHoatDongString);
					String donVi = table_2.getValueAt(selectedRow, 5).toString();

					// Tạo đối tượng DichVu và truyền vào dialog
					DichVu dichVu = new DichVu(maDichVu, donGia, tenDichVu, soLuongTon, conHoatDong, donVi);
					SuaDichVuDialog suaDialog = new SuaDichVuDialog(DichVuPanel.this, dichVu);
					suaDialog.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(DichVuPanel.this, "Vui lòng chọn dịch vụ để sửa!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				}
			
		});

	}

	public String getNextMaDichVu() {
		String nextMaDV = "DV0001"; // Giá trị mặc định nếu chưa có dữ liệu
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT MAX(maDichVu) FROM DichVu";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String currentMax = rs.getString(1);
				if (currentMax != null) {
					// Chuyển đổi mã thành số để tăng lên 1
					int numericPart = Integer.parseInt(currentMax.substring(2));
					nextMaDV = String.format("DV%04d", numericPart + 1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nextMaDV;
	}

	private void loadDichVuToTable() {
		DichVuDAL dichVuDAL = new DichVuDAL(); // Giả sử đây là lớp chứa hàm getAllDichVu()
		ArrayList<DichVu> dsDichVu = dichVuDAL.getAllDichVu(); // Lấy danh sách dịch vụ từ cơ sở dữ liệu

		// Xóa dữ liệu cũ trên bảng (nếu có)
		tableModel.setRowCount(0);

		// Duyệt qua danh sách dịch vụ và thêm vào TableModel
		for (DichVu dichVu : dsDichVu) {
			Object[] rowData = { dichVu.getMaDichVu(), dichVu.getDonGia(), dichVu.getTenDichVu(),
					dichVu.getSoLuongTon(), dichVu.isConHoatDong() ? "Có" : "Không", dichVu.getDonVi() };
			tableModel.addRow(rowData); // Thêm hàng vào mô hình bảng
		}
	}

	public void capNhatTable() {
		List<DichVu> danhSachDichVu = new DichVuDAL().getAllDichVu();

		// Cập nhật dữ liệu cho bảng
		DefaultTableModel model = (DefaultTableModel) table_2.getModel();
		model.setRowCount(0); // Xóa các hàng cũ

		for (DichVu dv : danhSachDichVu) {
			model.addRow(new Object[] { dv.getMaDichVu(), dv.getDonGia(), dv.getTenDichVu(), dv.getSoLuongTon(),
					dv.isConHoatDong() ? "Có" : "Không", dv.getDonVi() });
		}

	}

//	private void quayVeTrangThaiBanDau() {
//		txtMaDV.setText("");
//		txtTenDV.setText("");
//		cboHoatDong.setSelectedIndex(0);
//		
//		txtMaDV.setEditable(false);
//		txtTenDV.setEditable(false);
//		cboHoatDong.setEnabled(false);
//
//	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				JFrame frame = new JFrame("Dịch vụ");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new DichVuPanel(new MenuPanel(new MainGUI(new NhanVienDAL().getNhanVienTheoMa("QL001")))));
				frame.pack();
				frame.setSize(1920, 1080);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
