package view.dialog;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import dal.*;
import entity.*;
import view.MainGUI;
import view.panel.MenuPanel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;

public class TraPhongDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JTextField tongTienTextField;
	private JTextField chietKhauTienPhongTextField;
	private JTextField thanhTienTextField;
	private JTextField tienDaCocTextField;
	private JTextField tienThuKhachTextField;
	private JTextField maDonDatPhongTextField;
	private JTextField nhanVienTextField;
	private JTextField ngayTextField;
	private JTextField tenKhachHangTextField;
	private JComboBox phuongThucThanhToanComboBox;
	private ArrayList<ChiTiet_DonDatPhong_Phong> danhSachChiTiet;
    private ChiTiet_DonDatPhong_PhongDAL chiTietDonDatPhongPhongDAL = new ChiTiet_DonDatPhong_PhongDAL();
    private ChiTiet_DonDatPhong_Phong_DichVuDAL chiTietDonDatPhongPhongDichVuDAL = new ChiTiet_DonDatPhong_Phong_DichVuDAL();
    private JTable bangDichVu;
    private MenuPanel menuPanel;
    private JComboBox khuyenMaiComboBox;

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DonDatPhong don = new DonDatPhongDAL().getDonDatPhongTheoMa("DDP000012");
			TraPhongDialog dialog = new TraPhongDialog(don
					,new MenuPanel(new MainGUI(new NhanVienDAL().getNhanVienTheoMa("NV001")))
					);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TraPhongDialog(DonDatPhong donDatPhong
			, MenuPanel menuPanel
			) {
        this.danhSachChiTiet = chiTietDonDatPhongPhongDAL.getChiTietDonDatPhongPhongTheoMaDDP(donDatPhong.getMaDon());
        this.menuPanel = menuPanel;	
        setBounds(100, 100, 809, 850);
		setTitle("Trả phòng");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel thongTinPanel = new JPanel();
		thongTinPanel.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Thông tin Đơn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(245, 124, 0)));
		
		JPanel danhSachPhongPanel = new JPanel();
        danhSachPhongPanel.setBorder(
                new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
                        "Danh Sách Phòng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(245, 124, 0)));
        
        // Tạo mô hình bảng với 2 cột: Mã Phòng và Tiền Phòng
        String[] columnNames = {"Mã Phòng", "Tiền Phòng"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        
        // Thêm dữ liệu vào bảng
        for (ChiTiet_DonDatPhong_Phong ct : danhSachChiTiet) {
            String maPhong = ct.getPhong().getMaPhong();
            double tienPhongdouble = tinhTienChiTietDonDatPhong_Phong(ct);

         // Định dạng tiền theo kiểu Việt Nam
         NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
         String tienPhong = numberFormat.format(tienPhongdouble);
            tableModel.addRow(new Object[]{maPhong, tienPhong});
        }

        // Tạo JTable từ model
        JTable roomTable = new JTable(tableModel);
        roomTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Cài đặt listener cho sự kiện chọn dòng trong bảng
        roomTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = roomTable.getSelectedRow();
                if (selectedRow != -1) {
                    ChiTiet_DonDatPhong_Phong selectedRoomDetail = danhSachChiTiet.get(selectedRow);
                    showRoomDetails(selectedRoomDetail);
                }
            }
        });

        // Tạo JScrollPane để có thể cuộn khi danh sách phòng dài
        JScrollPane scrollPane = new JScrollPane(roomTable);

        // Cài đặt layout và thêm JScrollPane vào JPanel
        danhSachPhongPanel.setLayout(new BorderLayout());
        danhSachPhongPanel.add(scrollPane, BorderLayout.CENTER);
		
		JPanel dichVuPanel = new JPanel();
		dichVuPanel.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh Sách Dịch Vụ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(245, 124, 0)));
		dichVuPanel.setLayout(new BorderLayout(0, 0));
		bangDichVu = new JTable();
        dichVuPanel.add(new JScrollPane(bangDichVu));
		
		JPanel tinhTienPanel = new JPanel();
		tinhTienPanel.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(245, 124, 0)), "Tính Tiền", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(245, 124, 0)));
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(thongTinPanel, GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
				.addComponent(danhSachPhongPanel, GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
				.addComponent(tinhTienPanel, GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
				.addComponent(dichVuPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addComponent(thongTinPanel, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(danhSachPhongPanel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(dichVuPanel, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tinhTienPanel, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
		);
		
		JLabel maDonDatPhongLabel = new JLabel("Mã Đơn Đặt Phòng:");
		
		maDonDatPhongTextField = new JTextField();
		maDonDatPhongTextField.setEditable(false);
		maDonDatPhongTextField.setColumns(10);
		
		JLabel nhanVienLabel = new JLabel("Nhân Viên:");
		
		nhanVienTextField = new JTextField();
		nhanVienTextField.setEditable(false);
		nhanVienTextField.setColumns(10);
		
		JLabel ngayLabel = new JLabel("Ngày:");
		
		ngayTextField = new JTextField();
		ngayTextField.setEditable(false);
		ngayTextField.setColumns(10);
		
		JLabel tenKhachHangLabel = new JLabel("Tên Khách Hàng:");
		
		tenKhachHangTextField = new JTextField();
		tenKhachHangTextField.setEditable(false);
		tenKhachHangTextField.setColumns(10);
		GroupLayout gl_thongTinPanel = new GroupLayout(thongTinPanel);
		gl_thongTinPanel.setHorizontalGroup(
			gl_thongTinPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_thongTinPanel.createSequentialGroup()
					.addGroup(gl_thongTinPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_thongTinPanel.createSequentialGroup()
							.addComponent(maDonDatPhongLabel, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
							.addGap(18))
						.addGroup(gl_thongTinPanel.createSequentialGroup()
							.addComponent(nhanVienLabel, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
							.addGap(18)))
					.addGroup(gl_thongTinPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(nhanVienTextField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
						.addComponent(maDonDatPhongTextField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
					.addGroup(gl_thongTinPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_thongTinPanel.createSequentialGroup()
							.addGap(31)
							.addComponent(ngayLabel, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(ngayTextField, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
						.addGroup(gl_thongTinPanel.createSequentialGroup()
							.addGap(29)
							.addComponent(tenKhachHangLabel, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(tenKhachHangTextField, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_thongTinPanel.setVerticalGroup(
			gl_thongTinPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_thongTinPanel.createSequentialGroup()
					.addGroup(gl_thongTinPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(maDonDatPhongTextField)
						.addGroup(gl_thongTinPanel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(maDonDatPhongLabel, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
							.addComponent(ngayLabel, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
							.addComponent(ngayTextField, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)))
					.addGap(18)
					.addGroup(gl_thongTinPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(tenKhachHangTextField, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(tenKhachHangLabel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(nhanVienLabel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(nhanVienTextField, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		thongTinPanel.setLayout(gl_thongTinPanel);
		
		JLabel tongTienLabel = new JLabel("Tổng Tiền:");
		tongTienLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel khuyenMaiLabel = new JLabel("Khuyến Mãi:");
		khuyenMaiLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel chietKhauTienPhongLabel = new JLabel("Chiết Khấu Tiền Phòng:");
		chietKhauTienPhongLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel thanhTienLabel = new JLabel("Thành Tiền:");
		thanhTienLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel tienDaCocLabel = new JLabel("Tiền Đã Cọc:");
		tienDaCocLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel tienThuKhachLabel = new JLabel("Tiền Thu Khách:");
		tienThuKhachLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		tongTienTextField = new JTextField();
		tongTienTextField.setEditable(false);
		tongTienTextField.setColumns(10);
		
		 khuyenMaiComboBox = new JComboBox();
		
		// Khởi tạo JTextField
		 chietKhauTienPhongTextField = new JTextField();
		 chietKhauTienPhongTextField.setColumns(10);

		 // Gán giá trị mặc định là 0
		 chietKhauTienPhongTextField.setText("0");

		 // Thêm DocumentListener để kiểm tra và giới hạn giá trị nhập vào
		// Thêm DocumentListener để kiểm tra và giới hạn giá trị nhập vào
		 chietKhauTienPhongTextField.getDocument().addDocumentListener(new DocumentListener() {
		     @Override
		     public void insertUpdate(DocumentEvent e) {
		         validateAndFixText();
		     }

		     @Override
		     public void removeUpdate(DocumentEvent e) {
		         validateAndFixText();
		     }

		     @Override
		     public void changedUpdate(DocumentEvent e) {
		         validateAndFixText();
		     }

		     // Kiểm tra và sửa giá trị nhập vào
		     private void validateAndFixText() {
		         // Dùng invokeLater để tránh thay đổi văn bản trực tiếp trong DocumentListener
		         SwingUtilities.invokeLater(() -> {
		             try {
		                 String text = chietKhauTienPhongTextField.getText();
		                 int value = Integer.parseInt(text);

		                 // Kiểm tra nếu giá trị không hợp lệ, đặt lại về 0
		                 if (value < 0) {
		                     chietKhauTienPhongTextField.setText("0");
		                 } else if (value > 100) {
		                     chietKhauTienPhongTextField.setText("100");
		                 }

		                 // Sau khi nhập giá trị hợp lệ, gọi lại phương thức loadThanhTien() để tính lại thành tiền
		                 loadThanhTien(donDatPhong); // Bạn cần đảm bảo donDatPhong đã có giá trị
		                 loadTienThuKhach();
		             } catch (NumberFormatException e) {
		                 // Nếu không phải số, gán về 0
		                 chietKhauTienPhongTextField.setText("0");
		             }
		         });
		     }
		 });

		khuyenMaiComboBox.addActionListener(e -> {
			// Lấy giá trị được chọn từ ComboBox
			String tenKhuyenMai = (String) khuyenMaiComboBox.getSelectedItem();

			// Kiểm tra và xử lý khi giá trị thay đổi
			if (tenKhuyenMai != null && !tenKhuyenMai.isEmpty()) {
				// Gọi phương thức loadThanhTien với đối tượng DonDatPhong
				loadThanhTien(donDatPhong);
			}
		});
		thanhTienTextField = new JTextField();
		thanhTienTextField.setEditable(false);
		thanhTienTextField.setColumns(10);
		
		tienDaCocTextField = new JTextField();
		tienDaCocTextField.setEditable(false);
		tienDaCocTextField.setColumns(10);
		
		tienThuKhachTextField = new JTextField();
		tienThuKhachTextField.setEditable(false);
		tienThuKhachTextField.setColumns(10);
		
		JLabel phuongThucThanhToanLabel = new JLabel("Phương Thức Thanh Toán:");
		phuongThucThanhToanLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		phuongThucThanhToanComboBox = new JComboBox();
		phuongThucThanhToanComboBox.setModel(new DefaultComboBoxModel(new String[] {"Tiền mặt","Thẻ tín dụng - Visa","Thẻ tín dụng - MasterCard","Thẻ tín dụng - American Express","Thẻ ghi nợ - Visa Debit","Thẻ ghi nợ - MasterCard Debit","Chuyển khoản ngân hàng nội địa","Chuyển khoản ngân hàng quốc tế","Ví điện tử - Momo","Ví điện tử - ZaloPay","Ví điện tử - ViettelPay","Thanh toán qua QR code","Thanh toán trả sau"}));
		// Sứa lại dòng phuongThucThanhToan này để thêm mấy cái phương thức của ông dô nha
		GroupLayout gl_tinhTienPanel = new GroupLayout(tinhTienPanel);
		gl_tinhTienPanel.setHorizontalGroup(
			gl_tinhTienPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_tinhTienPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_tinhTienPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(phuongThucThanhToanLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
						.addComponent(tienThuKhachLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
						.addComponent(tienDaCocLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
						.addComponent(thanhTienLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
						.addComponent(chietKhauTienPhongLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
						.addComponent(khuyenMaiLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
						.addComponent(tongTienLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_tinhTienPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(khuyenMaiComboBox, 0, 367, Short.MAX_VALUE)
						.addComponent(tongTienTextField, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
						.addComponent(chietKhauTienPhongTextField, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
						.addComponent(thanhTienTextField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
						.addComponent(tienDaCocTextField, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
						.addComponent(tienThuKhachTextField, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
						.addComponent(phuongThucThanhToanComboBox, 0, 367, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_tinhTienPanel.setVerticalGroup(
			gl_tinhTienPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tinhTienPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_tinhTienPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(tongTienLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(tongTienTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_tinhTienPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(khuyenMaiComboBox)
						.addComponent(khuyenMaiLabel, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_tinhTienPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_tinhTienPanel.createSequentialGroup()
							.addComponent(chietKhauTienPhongLabel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(thanhTienLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_tinhTienPanel.createSequentialGroup()
							.addComponent(chietKhauTienPhongTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(thanhTienTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_tinhTienPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_tinhTienPanel.createSequentialGroup()
							.addComponent(tienDaCocLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tienThuKhachLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_tinhTienPanel.createSequentialGroup()
							.addComponent(tienDaCocTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(tienThuKhachTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_tinhTienPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(phuongThucThanhToanLabel, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(phuongThucThanhToanComboBox, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		tinhTienPanel.setLayout(gl_tinhTienPanel);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("CHECKOUT");
				okButton.setFont(new Font("Tahoma", Font.BOLD, 13));
				okButton.setForeground(new Color(255, 255, 255));
				okButton.setBackground(new Color(254, 124, 0));
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				        // Gọi phương thức xử lý sự kiện checkout
				        xuLySKCheckout(donDatPhong); // Thay donDatPhong bằng đối tượng cụ thể
				    }
				});

				
			}
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGap(224)
						.addComponent(okButton, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
						.addGap(240))
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.TRAILING)
					.addComponent(okButton, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
			);
			buttonPane.setLayout(gl_buttonPane);
		}
		setDataFromDonDatPhong(donDatPhong);
	}
	 private void showRoomDetails(ChiTiet_DonDatPhong_Phong ct) {
	        ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> dichVuList = new ChiTiet_DonDatPhong_Phong_DichVuDAL().getDSChiTietDonDatPhongPhongDichVuTheoMaDonDatPhongMaPhong(ct.getDonDatPhong().getMaDon(), ct.getPhong().getMaPhong());
	        String[][] services = new String[dichVuList.size()][4];
	        for (int i = 0; i < dichVuList.size(); i++) {
	            ChiTiet_DonDatPhong_Phong_DichVu dv = dichVuList.get(i);
	            services[i][0] = dv.getDichVu().getTenDichVu();
	            services[i][1] = String.valueOf(dv.getDichVu().getDonGia());
	            services[i][2] = String.valueOf(dv.getSoLuongDat());
	            services[i][3] = dv.getDichVu().getDonVi();
	        }
	        String[] columnNames = {"Tên Dịch Vụ", "Đơn Giá", "Số Lượng", "Đơn Vị"};
	        DefaultTableModel model = new DefaultTableModel(services, columnNames);
	        bangDichVu.setModel(model);
	    }
	 private void setDataFromDonDatPhong(DonDatPhong donDatPhong) {
		 DecimalFormat formatterTien = new DecimalFormat("#,###.##");
		    if (donDatPhong != null) {
		        try {
		            // Thông tin cơ bản
		            maDonDatPhongTextField.setText(donDatPhong.getMaDon());
		            
		            // Kiểm tra nếu menuPanel hoặc nhân viên đang trực là null
		            if (menuPanel != null && menuPanel.getNhanVienDangTruc() != null) {
		                nhanVienTextField.setText(menuPanel.getNhanVienDangTruc().getHoten());
		            } else {
		                JOptionPane.showMessageDialog(this, "Nhân viên đang trực không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            }
		            // Định dạng ngày giờ
		            LocalDateTime now = LocalDateTime.now();
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		            String formattedDate = now.format(formatter);
		            ngayTextField.setText(formattedDate);

		            // Thông tin khách hàng
		            tenKhachHangTextField.setText(donDatPhong.getKhachHang().getHoTen());

		            // Tính tiền (giải nén các giá trị thanh toán từ đơn đặt phòng)
		            double tongTien = tinhTongTien(donDatPhong);
		            String formattedTongTien = formatterTien.format(tongTien);

		            // Cập nhật giá trị vào TextField
		            tongTienTextField.setText(formattedTongTien);
		            loadKhuyenMaiToComboBox(donDatPhong);
		            double tienDatCocDouble = tinhTienDatCoc(donDatPhong);
		            String tienDatCoc = formatterTien.format(tienDatCocDouble);
		            tienDaCocTextField.setText(tienDatCoc);
		            loadThanhTien(donDatPhong);
		            loadTienThuKhach();
		        } catch (Exception e) {
		            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra trong quá trình lấy dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		        }
		    } else {
		        JOptionPane.showMessageDialog(this, "Đối tượng đơn đặt phòng không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		    }
		}
	private double tinhTienChiTietDonDatPhong_Phong(ChiTiet_DonDatPhong_Phong ct){
		double donGia = ct.getPhong().getLoaiPhong().getDonGia(); // Giá phòng theo loại phòng
		Double chietKhau = ct.getChietKhau(); // Lấy chiết khấu từ ct (lưu ý chietKhau là đối tượng kiểu Double, không phải kiểu double nguyên thủy)

		LocalDateTime ngayNhanPhongDuKien = ct.getDonDatPhong().getNgayNhanPhong(); // Ngày nhận phòng dự kiến
		LocalDateTime ngayTraPhongDuKien =ct.getDonDatPhong().getNgayTraPhong();// Ngày trả phòng dự kiến
		LocalDateTime ngayNhanPhongThucTe =  ct.getNgayNhanPhong(); // Ngày nhận phòng thực tế
		LocalDateTime ngayTraPhongThucTe = ct.getNgayTraPhong();
		
        LocalDateTime currentTime = LocalDateTime.now();
        if (ngayTraPhongThucTe.equals(ngayTraPhongDuKien) && ngayTraPhongThucTe.isBefore(currentTime)) {
            ngayTraPhongThucTe = currentTime;
        }

		// Tính số ngày ở (tối thiểu 1 ngày)
		long soNgayO = Math.max(ChronoUnit.DAYS.between(ngayNhanPhongThucTe, ngayTraPhongThucTe), 1);
		double tienPhong = donGia * soNgayO * (1 - chietKhau); // Tiền phòng cơ bản

		// Xử lý nhận phòng sớm
		if (ngayNhanPhongThucTe.isBefore(ngayNhanPhongDuKien)) {
			long soPhutNhanSom = ChronoUnit.MINUTES.between(ngayNhanPhongThucTe, ngayNhanPhongDuKien);
			double donGiaPhut = donGia / (24 * 60); // Giá mỗi phút
			double tienNhanSom = soPhutNhanSom * donGiaPhut * 3; // Tính tiền phút nhận sớm
			tienPhong += tienNhanSom; // Cộng tiền nhận phòng sớm vào tổng tiền phòng
		}

		// Xử lý trả phòng muộn
		if (ngayTraPhongThucTe.isAfter(ngayTraPhongDuKien)) {
			long soPhutTraMuon = ChronoUnit.MINUTES.between(ngayTraPhongDuKien, ngayTraPhongThucTe);
			double donGiaPhut = donGia / (24 * 60); // Giá mỗi phút
			double tienTraMuon = soPhutTraMuon * donGiaPhut * 3; // Tính tiền phút trả muộn
			tienPhong += tienTraMuon; // Cộng tiền trả phòng muộn vào tổng tiền phòng
		}

		return tienPhong;
	 }
	private double tinhTongTien(DonDatPhong donDatPhong) {
	    double tongTien = 0;
	    ArrayList<ChiTiet_DonDatPhong_Phong> chiTietList = chiTietDonDatPhongPhongDAL.getChiTietDonDatPhongPhongTheoMaDDP(donDatPhong.getMaDon());

	    // Duyệt qua danh sách chi tiết đơn đặt phòng
	    for (ChiTiet_DonDatPhong_Phong ct : chiTietList) {
	        // Tính tiền cho từng chi tiết phòng và cộng vào tổng tiền
	        tongTien += tinhTienChiTietDonDatPhong_Phong(ct);
		    ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> chiTietDichVuList = chiTietDonDatPhongPhongDichVuDAL.getDanhSachChiTietTheoMa(donDatPhong.getMaDon(),ct.getPhong().getMaPhong());
		    for(ChiTiet_DonDatPhong_Phong_DichVu ctdv : chiTietDichVuList) {
		    	tongTien += ctdv.getDichVu().getDonGia() * ctdv.getSoLuongDat(); 
		    }

	    }

	    // Nếu có các khoản phí khác, có thể cộng vào đây
	    // Ví dụ: phí dịch vụ, phí ăn uống, v.v.
	    // tongTien += donDatPhong.getTienDichVu(); // Giả sử bạn có phương thức tính phí dịch vụ

	    return tongTien;
	}
	private void loadKhuyenMaiToComboBox(DonDatPhong donDatPhong) {
	    // Lấy loại khách hàng từ đơn đặt phòng
	    String loaiKhachHang = donDatPhong.getKhachHang().getLoaiKhachHang().name(); // Giả sử có phương thức getLoaiKhachHang trong lớp KhachHang

	    // Lấy danh sách khuyến mãi từ cơ sở dữ liệu hoặc các đối tượng có sẵn
	    List<KhuyenMai> danhSachKhuyenMai = new KhuyenMaiDAL().getAllKhuyenMai(); // Giả sử bạn có lớp KhuyenMaiDAL để truy vấn dữ liệu

	    // Xóa các mục cũ trong ComboBox trước khi thêm mới
	    khuyenMaiComboBox.removeAllItems();

	    // Duyệt qua danh sách khuyến mãi và thêm khuyến mãi phù hợp vào ComboBox
	    for (KhuyenMai km : danhSachKhuyenMai) {
	        if (km.getLoaiKhachHangApDung().equals(loaiKhachHang)) {
	            // Thêm khuyến mãi vào ComboBox
	            khuyenMaiComboBox.addItem(km.getTenKhuyenMai());
	        }
	    }

	   
	}
	public double tinhTienDatCoc(DonDatPhong donDatPhong) {
	    // Kiểm tra nếu đơn đặt phòng yêu cầu đặt cọc
	    if (donDatPhong.isTrangThaiDatCoc()) {
	        // Lấy số tiền phòng từ đơn đặt phòng (có thể tính theo từng chi tiết phòng)
	        double tongTienPhong = 0;
		    ArrayList<ChiTiet_DonDatPhong_Phong> chiTietList = chiTietDonDatPhongPhongDAL.getChiTietDonDatPhongPhongTheoMaDDP(donDatPhong.getMaDon());

	        // Duyệt qua các chi tiết đơn đặt phòng và tính tổng tiền phòng
	        for (ChiTiet_DonDatPhong_Phong ct : chiTietList) {
	            tongTienPhong += tinhTienChiTietDonDatPhong_Phong(ct);
	        }
	        
	        // Tính tiền đặt cọc (50% của tổng tiền phòng)
	        double tienDatCoc = tongTienPhong * 0.5;
	        return tienDatCoc;
	    } else {
	        // Nếu không có yêu cầu đặt cọc, trả về 0
	        return 0;
	    }
	}
	public void loadThanhTien(DonDatPhong donDatPhong) {
	    // Lấy giá trị chiết khấu từ chietKhauTextField (đảm bảo giá trị là kiểu Double)
	    double chietKhau = 0;
	    try {
	        chietKhau = Double.parseDouble(chietKhauTienPhongTextField.getText());  // Lấy chiết khấu từ text field
	    } catch (NumberFormatException e) {
	        chietKhau = 0;  // Nếu không phải số hợp lệ, set chiết khấu là 0
	    }

	    // Lấy giá trị khuyến mãi từ khuyenMaiComboBox
	    String selectedKhuyenMaiString = (String) khuyenMaiComboBox.getSelectedItem();  // Lấy khuyến mãi đã chọn
	    
	    double giaTriKhuyenMai = 0 ; 
	    KhuyenMai selectedKhuyenMai = new KhuyenMaiDAL().getKhuyenMaiTheoTen(selectedKhuyenMaiString);
	    if (selectedKhuyenMai != null) {
	        giaTriKhuyenMai = selectedKhuyenMai.getGiaTri();  // Lấy giá trị khuyến mãi (tỷ lệ giảm hoặc số tiền giảm)
	    }

	    // Lấy tổng tiền phòng từ đơn đặt phòng (bạn có thể tính từ các chi tiết phòng)
	    double tongTienPhong = 0;
	    ArrayList<ChiTiet_DonDatPhong_Phong> chiTietList = chiTietDonDatPhongPhongDAL.getChiTietDonDatPhongPhongTheoMaDDP(donDatPhong.getMaDon());
	    double tongTienDichVu = 0;
	    // Duyệt qua các chi tiết đơn đặt phòng và tính tổng tiền phòng
	    for (ChiTiet_DonDatPhong_Phong ct : chiTietList) {
	        tongTienPhong += tinhTienChiTietDonDatPhong_Phong(ct);  // Tính tiền từ các chi tiết phòng
	        ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> chiTietDichVuList = chiTietDonDatPhongPhongDichVuDAL.getDanhSachChiTietTheoMa(donDatPhong.getMaDon(),ct.getPhong().getMaPhong());
	        for(ChiTiet_DonDatPhong_Phong_DichVu ctdv : chiTietDichVuList) {
		    	tongTienDichVu += ctdv.getDichVu().getDonGia() * ctdv.getSoLuongDat(); 
		    }
	    }

	    // Tính thành tiền
	    double thanhTien = tongTienPhong * (1 - chietKhau/100);
	    thanhTien = (thanhTien + tongTienDichVu ) * (1 - giaTriKhuyenMai);

	    DecimalFormat formatterTien = new DecimalFormat("#,###.##");
	    String thanhTienFormatted = formatterTien.format(thanhTien);

	    // Đưa giá trị thành tiền đã định dạng vào text field
	    thanhTienTextField.setText(thanhTienFormatted); 
	 // In ra các giá trị này trong console (nếu cần thiết)



	
	}
	private void loadTienThuKhach() {
	    DecimalFormat formatterTien = new DecimalFormat("#,###.##");

	    // Lấy và xử lý giá trị thành tiền từ textField
	    double thanhTien = parseTienFromTextField(thanhTienTextField);

	    // Lấy và xử lý giá trị tiền đã cọc từ textField
	    double tienDatCoc = parseTienFromTextField(tienDaCocTextField);

	    // Tính tiền thu khách: Thành tiền - Tiền đã cọc
	    double tienThuKhach = thanhTien - tienDatCoc;
	    tienThuKhachTextField.setText(formatterTien.format(tienThuKhach));
	}

	// Hàm hỗ trợ chuyển đổi giá trị từ JTextField thành double
	private double parseTienFromTextField(JTextField textField) {
	    String text = textField.getText().replace(",", "").replace(".", "");
	    try {
	        return Double.parseDouble(text);
	    } catch (NumberFormatException e) {
	        return 0; // Nếu không phải số hợp lệ, trả về 0
	    }
	}

	public void xuLySKCheckout(DonDatPhong donDatPhong) {
		int confirm = JOptionPane.showConfirmDialog(
				null,
				"Bạn có chắc chắn muốn checkout phòng này?",
				"Xác nhận",
				JOptionPane.YES_NO_OPTION
		);

		if (confirm == JOptionPane.YES_OPTION) {
			// Thay đổi trạng thái đơn đặt phòng
			boolean capNhatTrangThai = new DonDatPhongDAL().suaTrangThaiDonDatPhong(donDatPhong.getMaDon(), "Đã hoàn thành");

			if (capNhatTrangThai) {
				ArrayList<ChiTiet_DonDatPhong_Phong> chiTietList = chiTietDonDatPhongPhongDAL.getChiTietDonDatPhongPhongTheoMaDDP(donDatPhong.getMaDon());

				double chietKhau = 0;
				try {
					chietKhau = Double.parseDouble(chietKhauTienPhongTextField.getText().replace(",", "").replace(".", ""));
				} catch (NumberFormatException e) {
					// Nếu không thể chuyển đổi, giá trị chiết khấu là 0
					chietKhau = 0;
				}
				System.out.println(chietKhau);
				for(ChiTiet_DonDatPhong_Phong ct : chiTietList) {
					LocalDateTime ngayTraPhongThucTe = ct.getNgayTraPhong();
					LocalDateTime currentTime = LocalDateTime.now();
					// Nếu ngày trả phòng thực tế nhỏ hơn ngày hiện tại và ngày dự kiến, gán bằng thời gian hiện tại
					if (ngayTraPhongThucTe == null || ngayTraPhongThucTe.isBefore(currentTime)) {
						ngayTraPhongThucTe = currentTime;
					}
					// Lấy giá trị chiết khấu từ TextField


//		            public boolean suaChiTiet(String maDonDatPhong, String maPhong, ChiTiet_DonDatPhong_Phong chiTiet)
					boolean capNhatThoiGianTraPhong = new ChiTiet_DonDatPhong_PhongDAL().suaChiTiet(donDatPhong.getMaDon(), ct.getPhong().getMaPhong(), new ChiTiet_DonDatPhong_Phong(
							donDatPhong, ct.getPhong(), ct.getNgayNhanPhong(), ngayTraPhongThucTe, ct.isLaPhongChuyen(), chietKhau
					));
					if (!capNhatThoiGianTraPhong) {
						JOptionPane.showMessageDialog(null, "Cập nhật thời gian trả phòng thất bại!");
					}
				}


				// Tạo hóa đơn
				String maHoaDon = layMaHoaDonTiepTheo();
				double thanhTien = Double.parseDouble(thanhTienTextField.getText().replace(",", "").replace(".", ""));
				NhanVien nhanVienDangTruc = menuPanel.getNhanVienDangTruc();
				KhuyenMai khuyenMai = new KhuyenMaiDAL().getKhuyenMaiTheoTen((String) khuyenMaiComboBox.getSelectedItem());
				LocalDateTime ngayTao = LocalDateTime.now();

				HoaDon hoaDon = new HoaDon(maHoaDon, true, thanhTien, nhanVienDangTruc, khuyenMai, donDatPhong, ngayTao);

				if (new HoaDonDAL().themHoaDon(hoaDon)) {// Giảm số lượng khuyến mãi
					// Cập nhật tiền tích lũy cho khách hàng
					KhachHang khachHang = donDatPhong.getKhachHang();
					double tienTichLuyMoi = khachHang.getTienTichLuy() + thanhTien;
					khachHang.setTienTichLuy(tienTichLuyMoi);

					// Cập nhật loại khách hàng nếu cần
					capNhatLoaiKhachHang(khachHang);
					boolean capNhatKH = new KhachHangDAL().suaKhachHang(khachHang);
					if (!capNhatKH) {
						JOptionPane.showMessageDialog(null, "Cập nhật tiền tích lũy hoặc loại khách hàng thất bại!");
					}
					if (khuyenMai != null && khuyenMai.getSoLuong() > 0) {
						boolean capNhatKhuyenMai = new KhuyenMaiDAL().giamSoLuongKhuyenMai(khuyenMai.getMaKhuyenMai());
						if (!capNhatKhuyenMai) {
							JOptionPane.showMessageDialog(null, "Cập nhật số lượng khuyến mãi thất bại!");
						}
					}

					// Tạo phiếu thu
					String maPhieuThu = new PhieuThuChiDAL().getMaPhieuTiepTheo("PT");

					String tienDatCoc = (String) tienDaCocTextField.getText();


					String moTa = "Phiếu thu cho hóa đơn " + maHoaDon +
							(donDatPhong.isTrangThaiDatCoc()
									? " có đặt cọc với số tiền: " + tienDatCoc
									: " không đặt cọc");
					double soTien = thanhTien;
					String phuongThucThanhToan = (String) phuongThucThanhToanComboBox.getSelectedItem();
					PhieuThuChi phieuThu = new PhieuThuChi(maPhieuThu, "Phiếu Thu", moTa, ngayTao, soTien, phuongThucThanhToan, true, nhanVienDangTruc);

					if (new PhieuThuChiDAL().themPhieuThuChi(phieuThu)) {
						JOptionPane.showMessageDialog(null, "Checkout và tạo hóa đơn, phiếu thu thành công");
					} else {
						JOptionPane.showMessageDialog(null, "Checkout thành công, nhưng tạo phiếu thu thất bại!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Checkout thất bại! Không thể tạo hóa đơn.");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Checkout thất bại. Gặp lỗi khi cập nhật trạng thái!");
			}
		}
	}

	public void capNhatLoaiKhachHang(KhachHang khachHang) {
		double tienTichLuy = khachHang.getTienTichLuy();
		if (tienTichLuy >= 200_000_000) {
			khachHang.setLoaiKhachHang(LoaiKhachHang.HANGKIMCUONG);
		} else if (tienTichLuy >= 50_000_000) {
			khachHang.setLoaiKhachHang(LoaiKhachHang.HANGVANG);
		} else if (tienTichLuy >= 20_000_000) {
			khachHang.setLoaiKhachHang(LoaiKhachHang.HANGBAC);
		} else if (tienTichLuy >= 10_000_000) {
			khachHang.setLoaiKhachHang(LoaiKhachHang.HANGDONG);
		} else {
			khachHang.setLoaiKhachHang(LoaiKhachHang.NGUOIMOI);
		}
	}
	public String layMaHoaDonTiepTheo() {
	    // Lấy mã hóa đơn cuối cùng
	    String lastHoaDonCode = new HoaDonDAL().getLastHoaDonCode();

	    // Nếu không có hóa đơn nào trong cơ sở dữ liệu, trả về mã hóa đơn đầu tiên
	    if (lastHoaDonCode == null) {
	        return "HD000001";
	    }

	    // Xử lý mã hóa đơn cuối cùng để tạo mã mới
	    try {
	        // Tách phần số từ mã hóa đơn, bỏ đi tiền tố "HD"
	        int currentNumber = Integer.parseInt(lastHoaDonCode.substring(2)); // Ví dụ: HD000015 -> 15
	        int nextNumber = currentNumber + 1; // Tăng số thứ tự lên 1

	        // Định dạng mã hóa đơn mới với 6 chữ số, thêm tiền tố "HD"
	        return String.format("HD%06d", nextNumber); // Ví dụ: 16 -> HD000016
	    } catch (NumberFormatException e) {
	        e.printStackTrace();
	        // Nếu gặp lỗi khi chuyển đổi, trả về mã mặc định
	        return "HD000001";
	    }
	}

	}
