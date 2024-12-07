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
	private JTextField txtDonGia;
	private JTextField txtDonVi;
	private JTextField txtTenDV;
	private JTextField txtSoLuong;
	private JTextField txtTim;
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

		JPanel pnlThongTin = new JPanel();
		pnlThongTin.setBackground(new Color(255, 255, 255));
		pnlThongTin.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE), "Thông tin dịch vụ",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));

		add(pnlThongTin);
		pnlThongTin.setLayout(new GridLayout(1, 1, 0, 0));

		panelForm = new JPanel();
		panelForm.setLayout(new GridLayout(3, 3, 10, 10));

		JLabel lbMaDV = new JLabel("Mã dịch vụ:");
		lbMaDV.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelForm.add(lbMaDV);

		txtMaDV = new JTextField("DV*****");
		txtMaDV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaDV.setEditable(false);
		panelForm.add(txtMaDV);

		JLabel lbDonGia = new JLabel("Đơn giá:");
		lbDonGia.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelForm.add(lbDonGia);

		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Dialog", Font.BOLD, 13));
		txtDonGia.setEditable(false);
		panelForm.add(txtDonGia);

		JLabel lbTenDV = new JLabel("Tên dịch vụ:");
		lbTenDV.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelForm.add(lbTenDV);

		txtTenDV = new JTextField();
		txtTenDV.setFont(new Font("Dialog", Font.BOLD, 13));
		txtTenDV.setEditable(false);
		panelForm.add(txtTenDV);

		JLabel lbSoLuong = new JLabel("Số lượng:");
		lbSoLuong.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelForm.add(lbSoLuong);

		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Dialog", Font.BOLD, 13));
		txtSoLuong.setEditable(false);
		panelForm.add(txtSoLuong);

		JLabel lbConHoatDong = new JLabel("ConHoatDong:");
		lbConHoatDong.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelForm.add(lbConHoatDong);

		cboHoatDong = new JComboBox<>();
		cboHoatDong.setFont(new Font("Tahoma", Font.BOLD, 13));
		cboHoatDong.addItem("Có");
		cboHoatDong.addItem("Không");
		panelForm.add(cboHoatDong);

		JLabel lbDonVi = new JLabel("Đơn vị:");
		lbDonVi.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelForm.add(lbDonVi);

		txtDonVi = new JTextField();
		txtDonVi.setFont(new Font("Dialog", Font.BOLD, 13));
		txtDonVi.setEditable(false);
		panelForm.add(txtDonVi);

		pnlThongTin.add(panelForm);

		JPanel pnlBang1 = new JPanel();
		pnlBang1.setBackground(new Color(255, 255, 255));
		pnlBang1.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE), "Danh sách dịch vụ",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		add(pnlBang1);
		pnlBang1.setLayout(new BorderLayout());

		JScrollPane scrDSNV = new JScrollPane();
		pnlBang1.add(scrDSNV, BorderLayout.CENTER);

		Box hBox = Box.createHorizontalBox();
		JLabel lbTraCuu = new JLabel("Tra cứu:");
		lbTraCuu.setFont(new Font("Tahoma", Font.BOLD, 13));

		txtTim = new JTextField();
		txtTim.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTim.setPreferredSize(new Dimension(150, 20));
		txtTim.setMaximumSize(new Dimension(150, 20));

		JButton btnTim = new JButton("Tìm");
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTim.setForeground(new Color(255, 255, 255));
		btnTim.setBackground(new Color(253, 125, 0));

		hBox.add(lbTraCuu);
		hBox.add(Box.createHorizontalStrut(10));
		hBox.add(txtTim);
		hBox.add(Box.createHorizontalStrut(10));
		hBox.add(btnTim);

		pnlBang1.add(hBox, BorderLayout.NORTH);

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

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE), "Chức năng",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS)); // Thêm BoxLayout để phân chia theo chiều dọc

		// Tạo panel con cho các nút ở hàng trên
		JPanel panelTop = new JPanel();
		panelTop.setLayout(new FlowLayout()); // Layout cho hàng trên
		panelTop.setBackground(new Color(255, 255, 255));

		// Tạo các nút và thêm vào panelTop
		btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(243, 125, 0));
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 13));
		if(menuPanel.getNhanVienDangTruc().getVaiTro().equals("Nhân viên"))
		{
			btnThem.setEnabled(false);
		}
		panelTop.add(btnThem);

		btnSua = new JButton("Sửa");
		btnSua.setBackground(new Color(243, 125, 0));
		btnSua.setForeground(new Color(255, 255, 255));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 13));
		if(menuPanel.getNhanVienDangTruc().getVaiTro().equals("Nhân viên"))
		{
			btnSua.setEnabled(false);
		}
		panelTop.add(btnSua);

		// Thêm panelTop vào panel_3
		panel_3.add(panelTop);
		// Thêm panel_3 vào frame hoặc container chính của bạn
		add(panel_3);

//Sự kiện cho click row
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table_2.getSelectedRow(); // Lấy dòng được chọn từ bảng
				if (selectedRow != -1) { // Kiểm tra nếu có dòng được chọn
					// Chuyển đổi chỉ số dòng từ bảng sang mô hình
					int modelRow = table_2.convertRowIndexToModel(selectedRow);

					// Lấy dữ liệu từ mô hình dựa trên chỉ số thực
					String maDV = Objects.toString(tableModel.getValueAt(modelRow, 0), "");
					String donGia = Objects.toString(tableModel.getValueAt(modelRow, 1), "");
					String tenDV = Objects.toString(tableModel.getValueAt(modelRow, 2), "");
					String soLuong = Objects.toString(tableModel.getValueAt(modelRow, 3), "");
					String hoatDong = Objects.toString(tableModel.getValueAt(modelRow, 4), "");
					String donVi = Objects.toString(tableModel.getValueAt(modelRow, 5), "");

					// Hiển thị dữ liệu trong các JTextField và JComboBox
					txtMaDV.setText(maDV);
					txtTenDV.setText(tenDV);
					txtDonGia.setText(donGia);
					txtSoLuong.setText(soLuong);
					cboHoatDong.setSelectedItem(hoatDong);
					txtDonVi.setText(donVi);
				}
			}
		});
//Sự kiện cho btnTim
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
		table_2.setRowSorter(sorter);

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
					quayVeTrangThaiBanDau();
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

	private void quayVeTrangThaiBanDau() {
		txtMaDV.setText("");
		txtTenDV.setText("");
		txtDonGia.setText("");
		txtSoLuong.setText("");
		cboHoatDong.setSelectedIndex(0);
		txtDonVi.setText("");

		txtMaDV.setEditable(false);
		txtTenDV.setEditable(false);
		txtDonGia.setEditable(false);
		txtSoLuong.setEditable(false);
		cboHoatDong.setEnabled(false);
		txtDonVi.setEditable(false);
		btnSua.setVisible(true);
		btnThem.setVisible(true);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				JFrame frame = new JFrame("Dịch vụ");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new DichVuPanel(new MenuPanel(new MainGUI(new NhanVienDAL().getNhanVienTheoMa("NV001")))));
				frame.pack();
				frame.setSize(1920, 1080);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
