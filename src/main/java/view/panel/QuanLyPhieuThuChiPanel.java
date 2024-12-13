package view.panel;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.awt.event.ActionEvent; // Để sử dụng ActionEvent
import java.awt.event.ActionListener; // Để sử dụng ActionListener
import java.awt.event.FocusAdapter;  // Cho FocusAdapter (xử lý sự kiện khi JTextField được focus)
import java.awt.event.FocusEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JDateChooser;

import java.util.Date; // Thư viện cho kiểu dữ liệu Date


import bus.PhieuThuChiBUS;

import java.util.Calendar;

import constant.CommonConstants;
import dal.NhanVienDAL;
import entity.NhanVien;
import entity.PhieuThuChi;
import view.dialog.SuaPhieuThuChiDialog;
import view.dialog.ThemPhieuThuChiDialog;

import javax.swing.JTextField;
import javax.swing.JLabel;

import javax.swing.table.DefaultTableModel;

public class QuanLyPhieuThuChiPanel extends JPanel {

	private JComboBox comboBox_loaiphieu;
	private JComboBox loaiPhieuComboBox;
	private   JComboBox phuongThucComboBox;
	private JComboBox    trangThaiComboBox;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table_1 = new JTable();
	private JTextField textField_maphieu;
	private JTextField maPhieuThuChiTextField;
	private JButton suaButton;
	private JButton xoaButton;
	private JButton themButton;
	private JDateChooser ngayDauDateChooser;
	private JDateChooser ngayCuoiDateChooser;
	private MenuPanel menuPanel;
	public MenuPanel getMenuPanel() {

		return menuPanel;
	}

	public QuanLyPhieuThuChiPanel(MenuPanel menuPanel) {
		this.menuPanel = menuPanel;
		setLayout(new BorderLayout());

		JPanel content = new JPanel();
		content.setLayout(new BorderLayout(0, 0));
		add(content, BorderLayout.CENTER);
		JPanel header = new JPanel();
		header.setBorder(new LineBorder(new Color(0, 0, 0)));
		header.setBackground(new Color(69, 96, 115));
		content.add(header, BorderLayout.NORTH);
		JLabel tieuDeLabel = new JLabel("Phiếu thu chi");
		tieuDeLabel.setForeground(new Color(255, 255, 255));
		tieuDeLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
		//Group của head
		GroupLayout gl_header = new GroupLayout(header);
		gl_header.setHorizontalGroup(
				gl_header.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_header.createSequentialGroup()
								.addContainerGap()
								.addComponent(tieuDeLabel)
								.addContainerGap(1810, Short.MAX_VALUE))
		);
		gl_header.setVerticalGroup(
				gl_header.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_header.createSequentialGroup()
								.addContainerGap(32, Short.MAX_VALUE)
								.addComponent(tieuDeLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
		);
		header.setLayout(gl_header);

		JPanel centralPanel = new JPanel();
		centralPanel.setBackground(new Color(255, 255, 255));
		content.add(centralPanel, BorderLayout.CENTER);
		JPanel boLocPanel = new JPanel();
		boLocPanel.setBackground(new Color(255, 255, 255));
		boLocPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE),
				"Bộ lọc thông tin phiếu thu chi", TitledBorder.LEADING, TitledBorder.TOP, null, CommonConstants.ORANGE));
		JPanel chucNangPanel = new JPanel();
		chucNangPanel.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ch\u1EE9c n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(69, 96, 115)));
		chucNangPanel.setBackground(new Color(255, 255, 255));

		JPanel bangThongtinPanel = new JPanel();
		GroupLayout gl_centralPanel = new GroupLayout(centralPanel);
		gl_centralPanel.setHorizontalGroup(
				gl_centralPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_centralPanel.createSequentialGroup()
								.addContainerGap()
								.addComponent(boLocPanel, GroupLayout.DEFAULT_SIZE, 1236, Short.MAX_VALUE)
								.addGap(18)
								.addComponent(chucNangPanel, GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
								.addContainerGap())
						.addComponent(bangThongtinPanel, GroupLayout.DEFAULT_SIZE, 2005, Short.MAX_VALUE)
		);
		gl_centralPanel.setVerticalGroup(
				gl_centralPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_centralPanel.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_centralPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(boLocPanel, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
										.addComponent(chucNangPanel, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(bangThongtinPanel, GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
								.addContainerGap())
		);

		bangThongtinPanel.setLayout(new BorderLayout(0, 0));
		bangThongtinPanel.setBackground(new Color(255, 255, 255));
		bangThongtinPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE),
				"Thiết lập bảng thông tin thu chi", TitledBorder.LEADING, TitledBorder.TOP, null, CommonConstants.ORANGE));
		bangThongtinPanel.setLayout(new BorderLayout());

		JPanel bangPanel = new JPanel();
		bangThongtinPanel.add(bangPanel, BorderLayout.CENTER);
		table_1 = new JTable();
		table_1.setBackground(new Color(240, 240, 240));
		LocalDateTime startDate = LocalDate.now().atStartOfDay();
		LocalDateTime endDate = LocalDate.now().atTime(23, 59, 59, 999_999_999);
		PhieuThuChiBUS phieuThuChiBUS = new PhieuThuChiBUS();
		Object[][] layDuLieuBang = phieuThuChiBUS.layDuLieuBang(startDate, endDate,"","","","");
		table_1.setModel(new DefaultTableModel(layDuLieuBang,
				new String[]{"Mã phiếu thu chi", "Mã nhân viên", "Ngày tạo", "Loại phiếu", "Trạng thái", "Phương thức", "Số tiền", "Mô tả"}) {
			boolean[] columnEditables = new boolean[]{false, false, false, false, false, false, false, false};

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.setForeground(new Color(0, 0, 0));
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		table_1.setRowHeight(25);
		table_1.setSelectionBackground(new Color(0, 204, 204));
		table_1.setSelectionForeground(new Color(255, 255, 255));
		table_1.setFillsViewportHeight(true);
		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int selectedRow = table_1.getSelectedRow();
				if (table_1.getSelectedRow() != -1 && !menuPanel.getNhanVienDangTruc().getVaiTro().equals("Nhân viên") && !"Đã hủy".equals(table_1.getValueAt(selectedRow, 4))) {
					suaButton.setEnabled(true);
					xoaButton.setEnabled(true);
				} else {
					suaButton.setEnabled(false);
					xoaButton.setEnabled(false);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(table_1);
		bangPanel.setLayout(new BorderLayout());
		bangPanel.add(scrollPane, BorderLayout.CENTER);
		suaButton = new JButton("Sửa");
		suaButton.setForeground(new Color(243, 125, 0));
		suaButton.setBackground(new Color(255, 255, 255));
		suaButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		suaButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		suaButton.setEnabled(false);
		suaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_1.getSelectedRow();
				if (selectedRow != -1) {
					String trangThai = (String) table_1.getValueAt(selectedRow, 4);
					String maPhieu = (String) table_1.getValueAt(selectedRow, 0);
					String maNhanVien = (String) table_1.getValueAt(selectedRow, 1);
					LocalDateTime ngayTao = (LocalDateTime) table_1.getValueAt(selectedRow, 2);
					String loaiPhieu = (String) table_1.getValueAt(selectedRow, 3);
					String phuongThuc = (String) table_1.getValueAt(selectedRow, 5);
					String soTienString = table_1.getValueAt(selectedRow, 6).toString();
					String moTa = (String) table_1.getValueAt(selectedRow, 7);

					soTienString = soTienString.replace(",", "");
					if (soTienString.contains(".")) {
						soTienString = soTienString.replace(".", "");
					}
					try {
						double soTien1 = Double.parseDouble(soTienString);
						NhanVienDAL nhanVienDAL = new NhanVienDAL();
						NhanVien nhanVien = nhanVienDAL.getNhanVienTheoMa(maNhanVien);
						boolean trangThaiBoolean = "Còn Hoạt Động".equals(trangThai);
						PhieuThuChi phieuThuChi = new PhieuThuChi(maPhieu, loaiPhieu, moTa, ngayTao, soTien1, phuongThuc, trangThaiBoolean, nhanVien);
						SuaPhieuThuChiDialog suaPhieuThuChiDialog = new SuaPhieuThuChiDialog(QuanLyPhieuThuChiPanel.this, phieuThuChi);
						suaPhieuThuChiDialog.setVisible(true);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Số tiền không hợp lệ. Vui lòng kiểm tra lại.", "Lỗi Số Tiền", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		JButton timButton = new JButton("Tìm");
		timButton.setForeground(new Color(255, 255, 255));
		timButton.setBackground(new Color(243, 125, 0));
		timButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		timButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Đặt con trỏ chuột thành dạng tay
		timButton.addActionListener(e -> timDuLieu());
		themButton = new JButton("Thêm");
		themButton.setForeground(new Color(255, 255, 255));
		themButton.setBackground(new Color(243, 125, 0));
		themButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		themButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		themButton.addActionListener(e -> {
			ThemPhieuThuChiDialog themPhieuThuChiDialog = new ThemPhieuThuChiDialog(QuanLyPhieuThuChiPanel.this);
			themPhieuThuChiDialog.setVisible(true);
		});
		xoaButton = new JButton("Xóa");
		xoaButton.setForeground(new Color(255, 255, 255));
		xoaButton.setBackground(new Color(208, 13, 32));
		xoaButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		xoaButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		xoaButton.setEnabled(false);
		xoaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Gọi phương thức xóa phiếu thu chi
				xoaPhieuThuChi();
			}
		});
		GroupLayout gl_chucNangPanel = new GroupLayout(chucNangPanel);
		gl_chucNangPanel.setHorizontalGroup(
				gl_chucNangPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_chucNangPanel.createSequentialGroup()
								.addGap(56)
								.addGroup(gl_chucNangPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(themButton, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
										.addComponent(timButton, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
								.addGap(18)
								.addGroup(gl_chucNangPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_chucNangPanel.createSequentialGroup()
												.addComponent(suaButton, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
												.addGap(74))
										.addGroup(gl_chucNangPanel.createSequentialGroup()
												.addComponent(xoaButton, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
												.addGap(73))))
		);
		gl_chucNangPanel.setVerticalGroup(
				gl_chucNangPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_chucNangPanel.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_chucNangPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(suaButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
										.addComponent(themButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_chucNangPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(timButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
										.addComponent(xoaButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
								.addContainerGap(19, Short.MAX_VALUE))
		);
		chucNangPanel.setLayout(gl_chucNangPanel);

		JPanel chiTietBoLocPanel = new JPanel();
		GroupLayout gl_boLocPanel = new GroupLayout(boLocPanel);
		gl_boLocPanel.setHorizontalGroup(
				gl_boLocPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_boLocPanel.createSequentialGroup()
								.addContainerGap()
								.addComponent(chiTietBoLocPanel, GroupLayout.DEFAULT_SIZE, 1975, Short.MAX_VALUE)
								.addContainerGap())
		);
		gl_boLocPanel.setVerticalGroup(
				gl_boLocPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_boLocPanel.createSequentialGroup()
								.addGap(5)
								.addComponent(chiTietBoLocPanel, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
								.addContainerGap())
		);

		textField_maphieu = new JTextField();

		JLabel trangThaiLabel = new JLabel("Trạng thái:");
		trangThaiLabel.setFont(new Font("Tahoma", Font.BOLD, 15));

		trangThaiComboBox = new JComboBox<String>();
		trangThaiComboBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		trangThaiComboBox.addItem("");
		trangThaiComboBox.addItem("Còn Hoạt Động");
		trangThaiComboBox.addItem("Đã Hủy");

		JLabel loaiPhieuLabel = new JLabel("Loại phiếu:");
		loaiPhieuLabel.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel phuongThucLabel = new JLabel("Phương thức:");
		phuongThucLabel.setFont(new Font("Tahoma", Font.BOLD, 15));

		comboBox_loaiphieu = new JComboBox();
		loaiPhieuComboBox = new JComboBox<String>();
		loaiPhieuComboBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		loaiPhieuComboBox.addItem("");
		loaiPhieuComboBox.addItem("Phiếu Thu");
		loaiPhieuComboBox.addItem("Phiếu Chi");

		phuongThucComboBox = new JComboBox<String>();
		phuongThucComboBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		phuongThucComboBox.addItem("");
		phuongThucComboBox.addItem("Tiền mặt");
		phuongThucComboBox.addItem("Chuyển khoản");

		JLabel maPhieuThuChiLabel = new JLabel("Mã phiếu thu chi:");
		maPhieuThuChiLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		maPhieuThuChiTextField = new JTextField("nhập vào mã phiếu thu chi");
		maPhieuThuChiTextField.setColumns(10);
		maPhieuThuChiTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (maPhieuThuChiTextField.getText().equals("nhập vào mã phiếu thu chi")) {
					maPhieuThuChiTextField.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (maPhieuThuChiTextField.getText().isEmpty()) {
					maPhieuThuChiTextField.setText("nhập vào mã phiếu thu chi");
				}
			}
		});

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -30);
		JLabel ngayCuoiLabel = new JLabel("Ngày cuối:");
		ngayCuoiLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		ngayCuoiDateChooser = new JDateChooser();
		ngayCuoiDateChooser.setDate(new Date());
		ngayCuoiDateChooser.setMaxSelectableDate(new Date());
		ngayCuoiDateChooser.addPropertyChangeListener(evt -> {
			if ("date".equals(evt.getPropertyName())) {
				if (endDate != null) {
					calendar.setTime(ngayCuoiDateChooser.getDate());
					calendar.add(Calendar.DAY_OF_MONTH, -30);
					ngayDauDateChooser.setMaxSelectableDate(ngayCuoiDateChooser.getDate());
					ngayDauDateChooser.setMinSelectableDate(calendar.getTime());

				}
			}
		});
		JLabel ngayDauLabel = new JLabel("Ngày đầu:");
		ngayDauLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		ngayDauDateChooser = new JDateChooser();
		ngayDauDateChooser.setDate(new Date());
		ngayDauDateChooser.setMaxSelectableDate(new Date());
		ngayDauDateChooser.setMinSelectableDate(calendar.getTime());
		ngayDauDateChooser.addPropertyChangeListener(evt -> {
			if ("date".equals(evt.getPropertyName())) {
			}
		});
		GroupLayout gl_chiTietBoLocPanel = new GroupLayout(chiTietBoLocPanel);
		gl_chiTietBoLocPanel.setHorizontalGroup(
				gl_chiTietBoLocPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_chiTietBoLocPanel.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_chiTietBoLocPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(ngayCuoiLabel)
										.addComponent(ngayDauLabel))
								.addGap(18)
								.addGroup(gl_chiTietBoLocPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(ngayCuoiDateChooser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(ngayDauDateChooser, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_chiTietBoLocPanel.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(gl_chiTietBoLocPanel.createSequentialGroup()
												.addGap(14)
												.addComponent(maPhieuThuChiLabel))
										.addGroup(gl_chiTietBoLocPanel.createSequentialGroup()
												.addGap(11)
												.addComponent(trangThaiLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addGap(18)
								.addGroup(gl_chiTietBoLocPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(trangThaiComboBox, 0, 311, Short.MAX_VALUE)
										.addComponent(maPhieuThuChiTextField, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
								.addGap(18)
								.addGroup(gl_chiTietBoLocPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(loaiPhieuLabel)
										.addComponent(phuongThucLabel))
								.addGap(18)
								.addGroup(gl_chiTietBoLocPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(phuongThucComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(loaiPhieuComboBox, 0, 312, Short.MAX_VALUE))
								.addGap(23))
		);
		gl_chiTietBoLocPanel.setVerticalGroup(
				gl_chiTietBoLocPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_chiTietBoLocPanel.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_chiTietBoLocPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_chiTietBoLocPanel.createSequentialGroup()
												.addGroup(gl_chiTietBoLocPanel.createParallelGroup(Alignment.BASELINE)
														.addComponent(maPhieuThuChiLabel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
														.addComponent(maPhieuThuChiTextField, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
														.addComponent(loaiPhieuLabel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
														.addComponent(loaiPhieuComboBox, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
												.addGap(27))
										.addGroup(gl_chiTietBoLocPanel.createSequentialGroup()
												.addGroup(gl_chiTietBoLocPanel.createParallelGroup(Alignment.LEADING, false)
														.addComponent(ngayDauLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(ngayDauDateChooser, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
												.addGap(20)
												.addGroup(gl_chiTietBoLocPanel.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_chiTietBoLocPanel.createSequentialGroup()
																.addGroup(gl_chiTietBoLocPanel.createParallelGroup(Alignment.LEADING)
																		.addComponent(ngayCuoiLabel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
																		.addGroup(gl_chiTietBoLocPanel.createParallelGroup(Alignment.BASELINE)
																				.addComponent(trangThaiLabel, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
																				.addComponent(trangThaiComboBox, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
																				.addComponent(phuongThucLabel, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
																				.addComponent(phuongThucComboBox, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
																.addGap(20))
														.addGroup(gl_chiTietBoLocPanel.createSequentialGroup()
																.addComponent(ngayCuoiDateChooser, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
																.addContainerGap())))))
		);
		chiTietBoLocPanel.setLayout(gl_chiTietBoLocPanel);
		boLocPanel.setLayout(gl_boLocPanel);
		centralPanel.setLayout(gl_centralPanel);
	}

	private void updateTable(LocalDateTime startDate, LocalDateTime endDate,
							 String maPhieu, String trangThai, String loaiPhieu, String phuongThuc) {

		// Lấy dữ liệu từ PhieuThuChiBUS với các tiêu chí lọc
		PhieuThuChiBUS phieuThuChiBUS = new PhieuThuChiBUS();
		Object[][] data = phieuThuChiBUS.layDuLieuBang(startDate, endDate, maPhieu, trangThai, loaiPhieu, phuongThuc);

		// Cập nhật bảng
		table_1.setModel(new DefaultTableModel(data, new String[]{
				"Mã phiếu thu chi", "Mã nhân viên", "Ngày tạo", "Loại phiếu",
				"Trạng thái", "Phương thức", "Số tiền", "Mô tả"}));
	}
	public void timDuLieu() {
		try {
			// Lấy ngày đầu từ ngayDauDateChooser
			Date ngayDauDate = ngayDauDateChooser.getDate();
			LocalDateTime localStartDate = null;
			if (ngayDauDate != null) {
				localStartDate = ngayDauDate.toInstant()
						.atZone(ZoneId.systemDefault())
						.toLocalDate()
						.atStartOfDay();
			}

			Date ngayCuoiDate = ngayCuoiDateChooser.getDate();
			LocalDateTime localEndDate = null;
			if (ngayCuoiDate != null) {
				localEndDate = ngayCuoiDate.toInstant()
						.atZone(ZoneId.systemDefault())
						.toLocalDate()
						.atTime(23, 59, 59, 999000000);
			}

			if (localStartDate != null && localEndDate != null && localStartDate.isAfter(localEndDate)) {
				JOptionPane.showMessageDialog(null,
						"Thời gian đầu phải trước thời gian cuối. Vui lòng chọn lại.",
						"Lỗi thời gian", JOptionPane.ERROR_MESSAGE);
				return;
			}
			long daysBetween = Duration.between(localStartDate, localEndDate).toDays();
			if (daysBetween > 30) {
				JOptionPane.showMessageDialog(null,
						"Khoảng cách giữa ngày đầu và ngày cuối phải nhỏ hơn hoặc bằng 30 ngày. Vui lòng chọn lại.",
						"Lỗi khoảng cách ngày", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Lấy giá trị mã phiếu chi từ TextField
			String maPhieu = maPhieuThuChiTextField.getText();
			if ("nhập vào mã phiếu thu chi".equals(maPhieu)) {
				maPhieu = "";
			}

			String trangThai = (String) trangThaiComboBox.getSelectedItem();
			String loaiPhieu = (String) loaiPhieuComboBox.getSelectedItem();
			String phuongThuc = (String) phuongThucComboBox.getSelectedItem();
			updateTable(localStartDate, localEndDate, maPhieu, trangThai, loaiPhieu, phuongThuc);
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"Đã xảy ra lỗi khi lấy dữ liệu! Vui lòng kiểm tra lại.",
					"Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void xoaPhieuThuChi() {
		int row = table_1.getSelectedRow();
		if (row != -1) {
			String maPhieuThuChi = table_1.getValueAt(row, 0).toString();
			PhieuThuChiBUS phieuThuChiBUS = new PhieuThuChiBUS();
			boolean isDeleted = phieuThuChiBUS.xoaPhieuThuChi(maPhieuThuChi);
			if (isDeleted) {
				JOptionPane.showMessageDialog(null, "Xóa phiếu thu chi thành công.");
				timDuLieu();
			} else {
				JOptionPane.showMessageDialog(null, "Xóa phiếu thu chi thất bại.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để xóa.");
		}
	}
}




