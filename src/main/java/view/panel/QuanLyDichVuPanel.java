package view.panel;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dal.DichVuDAL;
import dal.NhanVienDAL;
import database.ConnectDB;
import entity.DichVu;
import view.MainGUI;
import view.dialog.SuaDichVuDialog;
import view.dialog.ThemDichVuDialog;

public class QuanLyDichVuPanel extends JPanel {
	

	private JTable table_2;
	private DefaultTableModel tableModel;
	private JTextField txtMaDV;
	private JTextField txtTenDV;
	private JButton btnSua;
	private JPanel panelForm;
	private JComboBox<String> cboHoatDong;
	private JButton btnThem;
	private JButton btnTim;
	private JButton btnLamMoi;
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
	public QuanLyDichVuPanel(MenuPanel menuPanel) {
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
		
		panelTop.setLayout(new GridLayout(2, 2, 10, 10)); 
		
		btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(243, 125, 0));
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setIcon(new ImageIcon("src/main/java/icon/add.png"));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		btnSua = new JButton("Sửa");
		btnSua.setBackground(new Color(243, 125, 0));
		btnSua.setForeground(new Color(255, 255, 255));
		btnSua.setIcon(new ImageIcon("src/main/java/icon/pen.png"));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 13));
		if(menuPanel.getNhanVienDangTruc().getVaiTro().equals("Nhân viên"))
		{
			btnThem.setEnabled(false);
			btnSua.setEnabled(false);
			
		}
		
		btnTim = new JButton("Tìm");
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTim.setForeground(new Color(255, 255, 255));
		btnTim.setBackground(new Color(253, 125, 0));
		btnTim.setIcon(new ImageIcon("src/main/java/icon/search.png"));

		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLamMoi.setForeground(new Color(255, 255, 255));
		btnLamMoi.setBackground(new Color(253, 125, 0));
		btnLamMoi.setIcon(new ImageIcon("src/main/java/icon/loading-arrow.png"));

		panelTop.add(btnThem);
		panelTop.add(btnSua);
		panelTop.add(btnTim);
		panelTop.add(btnLamMoi);

		panel_3.add(panelTop);
		add(panel_3);

		JPanel pnlContainer = new JPanel();
		pnlContainer = new JPanel();
		pnlContainer.setLayout(new BoxLayout(pnlContainer, BoxLayout.X_AXIS)); 
		pnlContainer.setBackground(new Color(255, 255, 255));

		pnlContainer.add(pnlBoLoc);
		pnlContainer.add(Box.createRigidArea(new Dimension(10, 0)));
		pnlContainer.add(panel_3);
		pnlBoLoc.setPreferredSize(new Dimension(800, 125)); 

		panel_3.setPreferredSize(new Dimension(100, 125)); 

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
		table_2.getColumnModel().getColumn(1).setCellRenderer(new NumberRenderer());


		table_2.setForeground(new Color(0, 0, 0));
		table_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		table_2.setRowHeight(25);
		table_2.setSelectionBackground(new Color(0, 204, 204));
		table_2.setSelectionForeground(new Color(255, 255, 255));
		table_2.setFillsViewportHeight(true);

		scrDSNV.setViewportView(scrollPane);

		loadDichVuToTable();

//Sự kiện cho btnTim
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
		table_2.setRowSorter(sorter);

		btnTim.addActionListener(e -> {
		    String maDV = txtMaDV.getText().trim();     
		    String tenDV = txtTenDV.getText().trim();    
		    String hoatDong = cboHoatDong.getSelectedItem().toString(); 

		    List<RowFilter<Object, Object>> filters = new ArrayList<>();
		    try {

		        if (!maDV.isEmpty()) {
		            filters.add(RowFilter.regexFilter("(?i)" + maDV, 0)); 
		        }
		        if (!tenDV.isEmpty()) {
		            filters.add(RowFilter.regexFilter("(?i)" + tenDV, 2)); 
		        }
		        if (!hoatDong.equals("Không chọn")) {
		            filters.add(RowFilter.regexFilter(hoatDong, 4)); 
		        }

		        if (filters.isEmpty()) {
		            sorter.setRowFilter(null); 
		        } else {
		            sorter.setRowFilter(RowFilter.andFilter(filters)); 
		        }
		    } catch (PatternSyntaxException ex) {
		        JOptionPane.showMessageDialog(null, "Lỗi trong từ khóa tìm kiếm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		    }
		});

//Sự kiện thêm
		btnThem.addActionListener(e -> {
		    String nextMaDV = getNextMaDichVu();
		    ThemDichVuDialog themDichVuDialog = new ThemDichVuDialog(nextMaDV, QuanLyDichVuPanel.this);
		    themDichVuDialog.setVisible(true);
		});

//Sự kiện sửa
		btnSua.addActionListener(e -> {
				int selectedRow = table_2.getSelectedRow();
				if (selectedRow != -1) {
					String maDichVu = table_2.getValueAt(selectedRow, 0).toString();
					double donGia = Double.parseDouble(table_2.getValueAt(selectedRow, 1).toString());
					String tenDichVu = table_2.getValueAt(selectedRow, 2).toString();
					int soLuongTon = Integer.parseInt(table_2.getValueAt(selectedRow, 3).toString());
					String conHoatDongString = table_2.getValueAt(selectedRow, 4).toString();
					boolean conHoatDong = "Có".equals(conHoatDongString);
					String donVi = table_2.getValueAt(selectedRow, 5).toString();

					DichVu dichVu = new DichVu(maDichVu, donGia, tenDichVu, soLuongTon, conHoatDong, donVi);
					SuaDichVuDialog suaDialog = new SuaDichVuDialog(QuanLyDichVuPanel.this, dichVu);
					suaDialog.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(QuanLyDichVuPanel.this, "Vui lòng chọn dịch vụ để sửa!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				}
			
		});
// Sự kiện cho btnLamMoi		
		btnLamMoi.addActionListener(e -> {
			lamMoi();
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
		DichVuDAL dichVuDAL = new DichVuDAL();
		ArrayList<DichVu> dsDichVu = dichVuDAL.getAllDichVu(); 

		tableModel.setRowCount(0);

		for (DichVu dichVu : dsDichVu) {
			Object[] rowData = { dichVu.getMaDichVu(), dichVu.getDonGia(), dichVu.getTenDichVu(),
					dichVu.getSoLuongTon(), dichVu.isConHoatDong() ? "Có" : "Không", dichVu.getDonVi() };
			tableModel.addRow(rowData); 
		}
	}

	public void capNhatTable() {
		List<DichVu> danhSachDichVu = new DichVuDAL().getAllDichVu();

		// Cập nhật dữ liệu cho bảng
		DefaultTableModel model = (DefaultTableModel) table_2.getModel();
		model.setRowCount(0);

		for (DichVu dv : danhSachDichVu) {
			model.addRow(new Object[] { dv.getMaDichVu(), dv.getDonGia(), dv.getTenDichVu(), dv.getSoLuongTon(),
					dv.isConHoatDong() ? "Có" : "Không", dv.getDonVi() });
		}

	}

	private void lamMoi() {
		txtMaDV.setText("");
		txtTenDV.setText("");
		cboHoatDong.setSelectedIndex(0);
		
	    TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) table_2.getRowSorter();
	    if (sorter != null) {
	        sorter.setRowFilter(null);
	    }

	    loadDichVuToTable();
	}
	public class NumberRenderer extends DefaultTableCellRenderer {
	    private static final DecimalFormat formatter = new DecimalFormat("#,###");

	    @Override
	    protected void setValue(Object value) {
	        if (value instanceof Number) {
	            setText(formatter.format(value));
	        } else {
	            super.setValue(value);
	        }
	    }
	}


	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				JFrame frame = new JFrame("Dịch vụ");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new QuanLyDichVuPanel(new MenuPanel(new MainGUI(new NhanVienDAL().getNhanVienTheoMa("NV001")))));
				frame.pack();
				frame.setSize(1920, 1080);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
}

