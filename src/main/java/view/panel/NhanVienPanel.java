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
import javax.swing.JPasswordField;
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
import entity.NhanVien;
import entity.TaiKhoan;
import view.dialog.SuaDichVuDialog;
import view.dialog.SuaNhanVienDialog;
import view.dialog.ThemDichVuDialog;
import view.dialog.ThemNhanVienDialog;

//import constraints.CONSTRAINTS;

public class NhanVienPanel extends JPanel {
	private JTable table_2;
	private DefaultTableModel tableModel;
	private JTextField txtMaNV;
	private JTextField txtHoTen;
	private JTextField txtSDT;
	private JComboBox<String> cboVaiTro;
	private JButton btnSua;
	private JPanel panelForm;
	private JComboBox<String> cboHoatDong;
	private JButton btnThem;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public NhanVienPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(new Color(255, 255, 255));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel pnlTieuDe = new JPanel();
		pnlTieuDe.setBackground(new Color(255, 255, 255));
		add(pnlTieuDe);
		pnlTieuDe.setLayout(new FlowLayout());

		JLabel lblTieuDeTrang = new JLabel("Nhân viên");
		lblTieuDeTrang.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDeTrang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblTieuDeTrang.setPreferredSize(new Dimension(600, 60));
		pnlTieuDe.add(lblTieuDeTrang);

		JPanel pnlBoLoc = new JPanel();
		pnlBoLoc.setBackground(new Color(255, 255, 255));
		pnlBoLoc.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE), "Bộ lọc nhân viên",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));

		add(pnlBoLoc);
		pnlBoLoc.setLayout(new GridLayout(1, 1, 0, 0));

		panelForm = new JPanel();
		panelForm.setLayout(new GridLayout(3, 3, 10, 10));

		JLabel lbMaNV = new JLabel("Mã nhân viên:");
		lbMaNV.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelForm.add(lbMaNV);

		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panelForm.add(txtMaNV);

		JLabel lbHoTen = new JLabel("Họ tên:");
		lbHoTen.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelForm.add(lbHoTen);

		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Dialog", Font.BOLD, 13));
		panelForm.add(txtHoTen);

		JLabel lbSDT = new JLabel("Số điện thoại:");
		lbSDT.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelForm.add(lbSDT);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Dialog", Font.BOLD, 13));
		panelForm.add(txtSDT);


		JLabel lbConHoatDong = new JLabel("ConHoatDong:");
		lbConHoatDong.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelForm.add(lbConHoatDong);

		cboHoatDong = new JComboBox<>();
		cboHoatDong.setFont(new Font("Tahoma", Font.BOLD, 13));
		cboHoatDong.addItem("");
		cboHoatDong.addItem("Có");
		cboHoatDong.addItem("Không");
		panelForm.add(cboHoatDong);



		JLabel lbVaiTro = new JLabel("Vai trò:");
		lbVaiTro.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelForm.add(lbVaiTro);

		cboVaiTro = new JComboBox<>();
		cboVaiTro.setFont(new Font("Tahoma", Font.BOLD, 13));
		cboVaiTro.addItem("");
		cboVaiTro.addItem("Nhân viên");
		cboVaiTro.addItem("Quản lý");
		panelForm.add(cboVaiTro);

		JLabel lbNull1 = new JLabel();
		panelForm.add(lbNull1);

		JLabel lbNull2 = new JLabel();
		panelForm.add(lbNull2);

		pnlBoLoc.add(panelForm);
		
		JPanel panelChucNang = new JPanel();
		panelChucNang.setBackground(new Color(255, 255, 255));
		panelChucNang.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE), "Chức năng",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		add(panelChucNang);
		panelChucNang.setLayout(new FlowLayout());

		btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(243, 125, 0));
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelChucNang.add(btnThem);

		btnSua = new JButton("Sửa");
		btnSua.setBackground(new Color(243, 125, 0));
		btnSua.setForeground(new Color(255, 255, 255));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelChucNang.add(btnSua);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTim.setForeground(new Color(255, 255, 255));
		btnTim.setBackground(new Color(253, 125, 0));
		panelChucNang.add(btnTim);

		JPanel pnlContainer = new JPanel();
		pnlContainer.setLayout(new GridLayout(1, 2, 10, 0)); 
		pnlContainer.setBackground(new Color(255, 255, 255));

		pnlContainer.add(pnlBoLoc);
		pnlContainer.add(panelChucNang);

		add(pnlContainer);

		JPanel pnlBang1 = new JPanel();
		pnlBang1.setBackground(new Color(255, 255, 255));
		pnlBang1.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE), "Danh sách nhân viên",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		add(pnlBang1);
		pnlBang1.setLayout(new BorderLayout());

		JScrollPane scrDSNV = new JScrollPane();
		pnlBang1.add(scrDSNV, BorderLayout.CENTER);


		tableModel = new DefaultTableModel(new String[] { "Mã nhân viên", "Họ tên", "Số điện thoại", "Số căn cước",
				"Còn hoạt động", "Email", "Địa chỉ", "Vai trò", "Tên tài khoản" }, 0);
		table_2 = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table_2);
		loadNhanVienToTable();

		table_2.setForeground(new Color(0, 0, 0));
		table_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		table_2.setRowHeight(25);
		table_2.setSelectionBackground(new Color(0, 204, 204));
		table_2.setSelectionForeground(new Color(255, 255, 255));
		table_2.setFillsViewportHeight(true);

		scrDSNV.setViewportView(scrollPane);

				
		//Sự kiện cho btnTim
		btnTim.addActionListener(e -> {
		
		    String maNV = txtMaNV.getText().trim();
		    String hoTen = txtHoTen.getText().trim();
		    String sdt = txtSDT.getText().trim();
		    String hoatDong = cboHoatDong.getSelectedItem().toString();
		    String vaiTro = cboVaiTro.getSelectedItem().toString();

		    List<RowFilter<Object, Object>> filters = new ArrayList<>();

		    if (!maNV.isEmpty()) {
		        filters.add(RowFilter.regexFilter("(?i)" + maNV, 0)); 
		    }
		    if (!hoTen.isEmpty()) {
		        filters.add(RowFilter.regexFilter("(?i)" + hoTen, 1)); 
		    }
		    if (!sdt.isEmpty()) {
		        filters.add(RowFilter.regexFilter("(?i)" + sdt, 2)); 
		    }
		    if (!hoatDong.isEmpty()) {
		        filters.add(RowFilter.regexFilter("(?i)" + hoatDong, 4)); 
		    }
		    if (!vaiTro.isEmpty()) {
		        filters.add(RowFilter.regexFilter("(?i)" + vaiTro, 7)); 
		    }

		
		    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
		    table_2.setRowSorter(sorter);

		    if (filters.isEmpty()) {
		        sorter.setRowFilter(null);
		    } else {
		        sorter.setRowFilter(RowFilter.andFilter(filters)); 
		    }
		});

		// Sự kiện btnThem

		btnThem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ThemNhanVienDialog themDichVuDialog = new ThemNhanVienDialog(NhanVienPanel.this);
				themDichVuDialog.setVisible(true);

			}
		});
		//Sự kiện btnSua
		btnSua.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Kiểm tra xem có dòng nào được chọn trong bảng không
				int selectedRow = table_2.getSelectedRow();
				if (selectedRow != -1) {
					String maNV = table_2.getValueAt(selectedRow, 0).toString();
					String tenNhanVien = table_2.getValueAt(selectedRow, 1).toString();
					String soDienThoai = table_2.getValueAt(selectedRow, 2).toString();
					String soCanCuoc = table_2.getValueAt(selectedRow, 3).toString();
					String conHoatDongString = table_2.getValueAt(selectedRow, 4).toString();
					boolean conHoatDong = "Có".equals(conHoatDongString);
					String email = table_2.getValueAt(selectedRow, 5).toString();
					String diaChi = table_2.getValueAt(selectedRow, 6).toString();
					String vaiTro = table_2.getValueAt(selectedRow, 7).toString();
					TaiKhoan taiKhoan = (TaiKhoan) table_2.getValueAt(selectedRow, 8);
					
					NhanVien nhanVien = new NhanVien(maNV, tenNhanVien, soDienThoai, soCanCuoc, conHoatDong, email, diaChi, vaiTro, taiKhoan);
					
					SuaNhanVienDialog suaDialog = new SuaNhanVienDialog(NhanVienPanel.this, nhanVien);
					suaDialog.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(NhanVienPanel.this, "Vui lòng chọn nhân viên để sửa!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public String getNextMaNhanVien() {
		String nextMaNV = "NV001"; 
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT MAX(maNV) FROM NhanVien";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String currentMax = rs.getString(1);
				if (currentMax != null) {
					// Chuyển đổi mã thành số để tăng lên 1
					int numericPart = Integer.parseInt(currentMax.substring(2));
					nextMaNV = String.format("NV%03d", numericPart + 1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nextMaNV;
	}

	public void capNhatTable() {
		List<NhanVien> danhSachNhanVien = new NhanVienDAL().getAllNhanVien();

		// Cập nhật dữ liệu cho bảng
		DefaultTableModel model = (DefaultTableModel) table_2.getModel();
		model.setRowCount(0);

		for (NhanVien nv : danhSachNhanVien) {
			model.addRow(new Object[] { nv.getMaNV(), nv.getHoten(), nv.getSoDienThoai(), nv.getSoCanCuoc(),
					nv.isConHoatDong() ? "Có" : "Không", nv.getEmail(), nv.getDiaChi(), nv.getVaiTro(), nv.getTaiKhoan() });
		}

	}

	private void loadNhanVienToTable() {
		NhanVienDAL nhanVienDAL = new NhanVienDAL(); 
		ArrayList<NhanVien> dsNhanVien = nhanVienDAL.getAllNhanVien(); 

		tableModel.setRowCount(0);

		for (NhanVien nhanVien : dsNhanVien) {
			Object[] rowData = { nhanVien.getMaNV(), nhanVien.getHoten(), nhanVien.getSoDienThoai(),
					nhanVien.getSoCanCuoc(), nhanVien.isConHoatDong() ? "Có" : "Không", nhanVien.getEmail(),
					nhanVien.getDiaChi(), nhanVien.getVaiTro(), nhanVien.getTaiKhoan() };
			tableModel.addRow(rowData); // Thêm hàng vào mô hình bảng
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				JFrame frame = new JFrame("Nhân viên");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new NhanVienPanel());
				frame.pack();
				frame.setSize(1920, 1080);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
