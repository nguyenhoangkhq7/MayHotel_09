package view.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import bus.PhieuThuChiBUS;
import dal.NhanVienDAL;
import entity.NhanVien;
import entity.PhieuThuChi;
import view.panel.QuanLyDichVuPanel;
import view.panel.QuanLyPhieuThuChiPanel;

import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Cursor;
import java.util.Date;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class SuaPhieuThuChiDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton luuButton;
	private JTextField maPhieuChiTextField;
	private JTextField maNhanVienTextField;
	private JTextField soTienTextField;
	private JDateChooser ngayTaoDateChooser;
	private JComboBox<String> trangThaiComboBox; 
	private JComboBox<String> phuongThucComboBox; 
	private JComboBox<String> loaiPhieuComboBox;
	private JTextArea moTaTextArea;
	
	public SuaPhieuThuChiDialog(QuanLyPhieuThuChiPanel phieuThuChiPanel, PhieuThuChi phieuThuChi) {
		setTitle("Sửa Phiếu Thu/Chi");
		setBounds(100, 100, 481, 531);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel maPhieuThuChiPanel = new JLabel("Mã phiếu thu/chi:");
		
		JLabel maNhanVienLabel = new JLabel("Mã nhân viên:");
		
		JLabel ngayTaoLabel = new JLabel("Ngày tạo:");
		
		JLabel trangThaiLabel = new JLabel("Trạng thái:");
		
		JLabel phuongThucLabel = new JLabel("Phương thức:");
		
		JLabel loaiPhieuLabel = new JLabel("Loại phiếu:");
		
		JLabel soTienLabel = new JLabel("Số tiền:");
		
		JLabel moTaLabel = new JLabel("Mô tả:");
		
		maPhieuChiTextField = new JTextField();
		maPhieuChiTextField.setColumns(10);
		maPhieuChiTextField.setEditable(false);
		
		maNhanVienTextField = new JTextField();
		maNhanVienTextField.setColumns(10);
		maNhanVienTextField.setEditable(false);
		String maNhanVienDangTruc = phieuThuChiPanel.getMenuPanel().getNhanVienDangTruc().getMaNV();
		maNhanVienTextField.setText(maNhanVienDangTruc); // Đặt mã nhân viên vào TextField

		
		ngayTaoDateChooser = new JDateChooser();
		ngayTaoDateChooser.setEnabled(false);
		ngayTaoDateChooser.setDateFormatString("dd-MM-yyyy");
		ngayTaoDateChooser.setDate(new Date());
		
		
		
		trangThaiComboBox = new JComboBox<String>();
		trangThaiComboBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		trangThaiComboBox.setEnabled(false);
		trangThaiComboBox.addItem("Còn Hoạt Động");

		
		phuongThucComboBox = new JComboBox<String>();
		phuongThucComboBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		phuongThucComboBox.addItem("Tiền mặt");
		phuongThucComboBox.addItem("Chuyển khoản");
		
		
		loaiPhieuComboBox = new JComboBox<String>();
		loaiPhieuComboBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		loaiPhieuComboBox.addItem("Phiếu Thu");
		loaiPhieuComboBox.addItem("Phiếu Chi");
		

		
		soTienTextField = new JTextField("");
		soTienTextField.setColumns(10);
        ((AbstractDocument) soTienTextField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string != null && string.matches("[0-9]*")) {
                    String currentText = soTienTextField.getText();
                    String newText = currentText.substring(0, offset) + string + currentText.substring(offset);
                    if (isValidAmount(newText)) {
                        super.insertString(fb, offset, string, attr);
                    }
                }
            }
            @Override
            public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException {
                if (string != null && string.matches("[0-9]*")) {
                    String currentText = soTienTextField.getText();
                    String newText = currentText.substring(0, offset) + string + currentText.substring(offset + length);
                    if (isValidAmount(newText)) {
                        super.replace(fb, offset, length, string, attr);
                    }
                }
            }
            private boolean isValidAmount(String text) {
                return text.length() < 10 || text.equals("nhập tiền tại đây"); // Cho phép giữ nguyên placeholder
            }
        });
        soTienTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (soTienTextField.getText().equals("nhập tiền tại đây")) {
                    soTienTextField.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (soTienTextField.getText().isEmpty()) {
                    soTienTextField.setText("nhập tiền tại đây");
                }
            }
        });
    	JLabel lblNewLabel_5_1 = new JLabel("vnd");
		lblNewLabel_5_1.setForeground(new Color(243, 125, 0));
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		moTaTextArea = new JTextArea();
	
		
	
		//Group
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(maPhieuThuChiPanel, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(maPhieuChiTextField, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(maNhanVienLabel, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(maNhanVienTextField, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(phuongThucLabel, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(phuongThucComboBox, 0, 292, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(loaiPhieuLabel, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(loaiPhieuComboBox, 0, 292, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(soTienLabel, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(soTienTextField, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_5_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(moTaLabel, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(moTaTextArea, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(ngayTaoLabel, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
								.addComponent(trangThaiLabel, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(trangThaiComboBox, 0, 292, Short.MAX_VALUE)
								.addComponent(ngayTaoDateChooser, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(maPhieuThuChiPanel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(maPhieuChiTextField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(maNhanVienLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(maNhanVienTextField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(ngayTaoLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(ngayTaoDateChooser, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(trangThaiComboBox, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
						.addComponent(trangThaiLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(phuongThucComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(phuongThucLabel, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(loaiPhieuLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(loaiPhieuComboBox, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(soTienLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(soTienTextField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(moTaLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(moTaTextArea, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		//Fotter
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			{
				luuButton = new JButton("Lưu");
				luuButton.setActionCommand("Lưu");
				luuButton.setForeground(new Color(255, 255, 255));
				luuButton.setBackground(new Color(243, 125, 0));
				luuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				luuButton.addActionListener(e -> {
				    try {
				    	String maPhieu = maPhieuChiTextField.getText();
				    	String maNhanVien = phieuThuChiPanel.getMenuPanel().getNhanVienDangTruc().getMaNV(); // Mã nhân viên nhập vào
				    	LocalDateTime ngayLap =LocalDateTime.now();
				    	String phuongThuc = phuongThucComboBox.getSelectedItem().toString();
				    	String loaiPhieu = loaiPhieuComboBox.getSelectedItem().toString();
				    	String soTienStr = soTienTextField.getText().trim();
				    	String moTa = moTaTextArea.getText().trim();
				    	 String maPhieuThem = "PT000000"; 
				    	if (soTienStr.isEmpty() || soTienStr.equals("nhập tiền tại đây")) {
				    	    throw new IllegalArgumentException("Số tiền không được để trống.");
				    	}
				    	double soTien = Double.parseDouble(soTienStr);
				    	if ("Phiếu Thu".equals(loaiPhieu)) {
				    	    maPhieuThem = getMaPhieuTiepTheo("PT"); // Lấy mã phiếu PT tiếp theo
				    	} else if ("Phiếu Chi".equals(loaiPhieu)) {
				    	    maPhieuThem = getMaPhieuTiepTheo("PC"); // Lấy mã phiếu PC tiếp theo
				    	} else {
				    	    // Trường hợp không hợp lệ, thông báo lỗi hoặc xử lý khác
				    	    JOptionPane.showMessageDialog(null, "Vui lòng chọn loại phiếu hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				    	    return; // Dừng hàm nếu không có loại phiếu hợp lệ
				    	}
				    	NhanVienDAL nhanVienDAL = new NhanVienDAL();
				    	NhanVien nhanVien = nhanVienDAL.getNhanVienTheoMa(maNhanVien);
				    
				    	if (nhanVien == null) {
				    	    JOptionPane.showMessageDialog(this, "Mã nhân viên không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				    	    return;
				    	}
				    	PhieuThuChi phieuThuChi1 = new PhieuThuChi(maPhieuThem, loaiPhieu, moTa, ngayLap, soTien, phuongThuc, true, nhanVien);
				    	PhieuThuChiBUS bus = new PhieuThuChiBUS();
				    	if (bus.them(phieuThuChi1)) {
				    	    if(bus.xoaPhieuThuChi(maPhieu)) { 
				    	    	JOptionPane.showMessageDialog(this, "Sửa phiếu thu/chi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					    	    dispose(); // Đóng dialog sau khi thêm thành công
					    	    phieuThuChiPanel.timDuLieu();
					    	    }
				    	    else {
					    	    JOptionPane.showMessageDialog(this, "Sửa phiếu thu/chi thất bại khi vô hiệu hóa phiếu cũ!", "Lỗi", JOptionPane.ERROR_MESSAGE);

				    	    }
				    	   
				    	} else {
				    	    JOptionPane.showMessageDialog(this, "Sửa phiếu thu/chi thất bại khi tạo mới!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				    	}
} 
				    catch (IllegalArgumentException ex) {
				        JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
				    } catch (Exception ex) {
				        ex.printStackTrace();
				        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				    }
				});
				
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_buttonPane.createSequentialGroup()
						.addGap(81)
						.addComponent(luuButton, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
						.addGap(64))
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(Alignment.LEADING, gl_buttonPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(luuButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			buttonPane.setLayout(gl_buttonPane);
		}
	}
		
		setDefaultValues(phieuThuChi);
		}
	private String getMaPhieuTiepTheo(String prefix) {
	    int newNumber = 1; 
	    try (Connection con = ConnectDB.getConnection();
	         PreparedStatement stmt = con.prepareStatement(
	             "SELECT MAX(CAST(SUBSTRING(maPhieu, 3, LEN(maPhieu) - 2) AS INT)) FROM PhieuThuChi WHERE maPhieu LIKE ?")) {

	        stmt.setString(1, prefix + "%");
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                Integer maxNumber = rs.getInt(1);
	                if (rs.wasNull()) {
	                    newNumber = 1;
	                } else {
	                    newNumber = maxNumber + 1;
	                }
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        throw new RuntimeException("Lỗi kết nối hoặc truy vấn cơ sở dữ liệu", ex);
	    }

	    return prefix + String.format("%06d", newNumber); 
	}
	public void setDefaultValues(PhieuThuChi phieuThuChi) {
		 Date date = Date.from(phieuThuChi.getNgayLap().atZone(ZoneId.systemDefault()).toInstant());
	     ngayTaoDateChooser.setDate(date);
	    trangThaiComboBox.setSelectedItem(phieuThuChi.isConHoatDong()? "Còn Hoạt Động" : "Đã Hủy");
	    phuongThucComboBox.setSelectedItem(phieuThuChi.getPhuongThucThanhToan());
	    loaiPhieuComboBox.setSelectedItem(phieuThuChi.getLoaiPhieu());
	    maPhieuChiTextField.setText(phieuThuChi.getMaPhieu());
	    double soTien = phieuThuChi.getSoTien();
	    long soTienNguyen = Math.round(soTien);
	    soTienTextField.setText(String.valueOf(soTienNguyen)); 
	    moTaTextArea.setText(phieuThuChi.getMoTa());
	}

	public class ConnectDB {
	    public static Connection getConnection() throws SQLException {
	        String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyKS;encrypt=false";
	        String username = "sa";
	        String password = "Thaibao123";
	        Connection conn = DriverManager.getConnection(url, username, password);
	        return conn;
	    }

}

	}
