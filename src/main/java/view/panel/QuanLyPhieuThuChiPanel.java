package view.panel;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.swing.text.*;           // Cung cấp AbstractDocument và DocumentFilter để ràng buộc nhập liệu

import java.awt.event.ActionEvent; // Để sử dụng ActionEvent
import java.awt.event.ActionListener; // Để sử dụng ActionListener
import java.awt.event.FocusAdapter;  // Cho FocusAdapter (xử lý sự kiện khi JTextField được focus)
import java.awt.event.FocusEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import com.toedter.calendar.JDateChooser;

import java.awt.event.*; // Thư viện cho các sự kiện như MouseListener, ActionListener
import java.util.Date; // Thư viện cho kiểu dữ liệu Date


import bus.PhieuThuChiBUS;

import java.util.Calendar;

import constant.CommonConstants;
import dal.NhanVienDAL;
import dal.PhieuThuChiDAL;
import entity.NhanVien;
import view.panel.BaoCaoPanel;

import javax.swing.JTextField;
import javax.swing.JLabel;

import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

public class QuanLyPhieuThuChiPanel extends JPanel {

    private JComboBox<String> cboNhanVien;
    private JComboBox comboBox_loaiphieu;
    private JTextField txtMota;
    private   JComboBox comboBox_PhuongThuc;
    private JComboBox<String> cboTrangThai;
    private JComboBox<String> cboLoaiPhieu;
    private JComboBox<String> cboPhuongThuc;
    private JComboBox    comboBoxtranthai;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table_1 = new JTable();
	private JTextField textField_maphieu;
	private JTextField textField_maNV;
    private JDateChooser dateChooser_ngaytao;
	private JTextField textField_SoTien;
	private JTextField textField_mota;
	private JTextField textField_6;
    private JPanel content;
    private JButton btnNewButton_1;
    private JButton btnNewButton_2;
    private JButton btnNewButton;
    private JDateChooser dateChooser_1_1_1 = new JDateChooser();
    private JDateChooser dateChooser_1_1_1_1 = new JDateChooser();
    


    public QuanLyPhieuThuChiPanel(BaoCaoPanel baoCaoFrame) {

    	 setLayout(new BorderLayout());
 		
    		
    	 JPanel content = new JPanel();
    	        content.setLayout(new BorderLayout(0, 0));
    	        add(content, BorderLayout.CENTER);

    			
    			
    			JPanel header = new JPanel();
    			header.setBorder(new LineBorder(new Color(0, 0, 0)));
    			header.setBackground(new Color(69, 96, 115));
    			content.add(header, BorderLayout.NORTH);
    			
		
		JLabel lblNewLabel = new JLabel("Phiếu thu chi");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon("D:\\\\GITHTB_PTUD\\\\MayHotel_09\\\\src\\\\main\\\\java\\\\icon\\\\back.png"));
		lblNewLabel_1.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Đặt con trỏ chuột thành dạng tay
	
		lblNewLabel_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                baoCaoFrame.showMainContent(); // Quay về màn hình chính
            }

            public void mouseEntered(MouseEvent evt) {
            	lblNewLabel_1.setIcon(new ImageIcon("D:\\\\GITHTB_PTUD\\\\MayHotel_09\\\\src\\\\main\\\\java\\\\icon\\\\backcam.png")); // Đổi icon sang màu cam khi trỏ vào
            }

            public void mouseExited(MouseEvent evt) {
            	lblNewLabel_1.setIcon(new ImageIcon("D:\\\\GITHTB_PTUD\\\\MayHotel_09\\\\src\\\\main\\\\java\\\\icon\\\\back.png")); // Trở về icon ban đầu khi trỏ ra
            }
            
          
        });    
		  
		//Group của head
		GroupLayout gl_header = new GroupLayout(header);
		gl_header.setHorizontalGroup(
			gl_header.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_header.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel)
					.addContainerGap(1694, Short.MAX_VALUE))
		);
		gl_header.setVerticalGroup(
			gl_header.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_header.createSequentialGroup()
					.addContainerGap(32, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_header.createSequentialGroup()
					.addContainerGap(36, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		header.setLayout(gl_header);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		content.add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE),
                "Thiết lập thông tin phiếu thu chi", TitledBorder.LEADING, TitledBorder.TOP, null, CommonConstants.ORANGE));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(
	                new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ch\u1EE9c n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(69, 96, 115)));		
		panel_3.setBackground(new Color(255, 255, 255));
		
		JPanel panel_6 = new JPanel();
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 2005, Short.MAX_VALUE)
				.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 2005, Short.MAX_VALUE)
				.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 2005, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		panel_6.setLayout(new BorderLayout(0, 0));
		panel_6.setBackground(new Color(255, 255, 255));
		panel_6.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE),
	                "Thiết lập bảng thông tin thu chi", TitledBorder.LEADING, TitledBorder.TOP, null, CommonConstants.ORANGE));
		panel_6.setLayout(new BorderLayout());
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(255, 255, 255));
		panel_6.add(panel_7, BorderLayout.NORTH);
		JLabel lblNewLabel_7 = new JLabel("Ngày đầu:");
        lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 15));

        // Tạo JDateChooser
         dateChooser_1_1_1 = new JDateChooser();
        
        // Đặt ngày hiện tại cho dateChooser_1_1_1
        dateChooser_1_1_1.setDate(new Date());
        dateChooser_1_1_1.setMaxSelectableDate(new Date()); // Ngày tối đa là ngày hiện tại

        // Lấy ngày hiện tại và trừ 30 ngày
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date()); // Ngày hiện tại
        calendar.add(Calendar.DAY_OF_MONTH, -30); // Trừ 30 ngày

        // Đặt ngày tối thiểu cho dateChooser
        dateChooser_1_1_1.setMinSelectableDate(calendar.getTime()); // Chuyển đổi Calendar sang Date
        dateChooser_1_1_1.addPropertyChangeListener(evt -> {
            if ("date".equals(evt.getPropertyName())) {
                updateTable();
            }
        });

        JLabel lblNewLabel_7_1 = new JLabel("Ngày cuối:");
        lblNewLabel_7_1.setFont(new Font("Tahoma", Font.BOLD, 15));

        dateChooser_1_1_1_1 = new JDateChooser();
        // Đặt ngày hiện tại cho dateChooser_1_1_1_1
        dateChooser_1_1_1_1.setDate(new Date());
        dateChooser_1_1_1_1.setMaxSelectableDate(new Date()); // Ngày tối đa là ngày hiện tại
        
     // Lắng nghe sự kiện thay đổi ngày cuối
        dateChooser_1_1_1_1.addPropertyChangeListener(evt -> {
            if ("date".equals(evt.getPropertyName())) {
                Date endDate = dateChooser_1_1_1_1.getDate();
                if (endDate != null) {
                    // Cập nhật ngày tối đa cho dateChooser_1_1_1
                    dateChooser_1_1_1.setMaxSelectableDate(endDate);
                    // Cập nhật ngày tối thiểu cho dateChooser_1_1_1
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.setTime(endDate);
                    calendar1.add(Calendar.DAY_OF_MONTH, -30); // Trừ 30 ngày
                    dateChooser_1_1_1.setMinSelectableDate(calendar1.getTime());
                }
                updateTable();
            }
        });
        
		JLabel lblNewLabel_7_1_1 = new JLabel("Mã phiếu chi:");
		lblNewLabel_7_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));

		textField_6 = new JTextField("nhập vào mã phiếu chi");
		textField_6.setColumns(10);

		// Xóa placeholder khi người dùng nhấp vào JTextField
		textField_6.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (textField_6.getText().equals("nhập vào mã phiếu chi")) {
		            textField_6.setText("");
		        }
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		        if (textField_6.getText().isEmpty()) {
		            textField_6.setText("nhập vào mã phiếu chi");
		        }
		    }
		});
		
		JButton btnNewButton_3 = new JButton("Tìm");
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setBackground(new Color(243, 125, 0));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_3.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Đặt con trỏ chuột thành dạng tay
		
		btnNewButton_3.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String maPhieu = textField_6.getText().trim(); // Lấy mã phiếu từ JTextField

		        // Kiểm tra nếu mã phiếu rỗng hoặc là giá trị mặc định
		        if (maPhieu.isEmpty() || maPhieu.equals("nhập vào mã phiếu chi")) {
		            // Nếu mã phiếu rỗng, gọi hàm updateTable() với ngày đầu và ngày cuối
		            updateTable();
		        } else {
		        	PhieuThuChiBUS bus = new PhieuThuChiBUS();
		        	   Object[][] data = new Object[1][8];
		        	   data = bus.layDuLieuBangTim(maPhieu);
		            if (data != null) {
		              
		                table_1.setModel(new DefaultTableModel(data, new String[]{
		                    "Mã phiếu thu chi", "Tên nhân viên", "Ngày tạo", "Loại phiếu", 
		                    "Trạng thái", "Phương thức", "Số tiền", "Mô tả"}));
		            } else {
		                // Thông báo nếu không tìm thấy phiếu thu chi
		                JOptionPane.showMessageDialog(null, "Không tìm thấy phiếu thu chi với mã này.");
		            }
		        }
		    }
		});
		
JPanel panel_8 = new JPanel();
panel_8.setBackground(new Color(255, 255, 255));

		
		JPanel panel_8_1 = new JPanel();
		 panel_8_1.setBackground(new Color(255, 255, 255));

		
		JPanel panel_8_2 = new JPanel();
		panel_8_2.setBackground(new Color(255, 255, 255));

		JPanel panel_8_3 = new JPanel();
		panel_8_3.setBackground(new Color(255, 255, 255));

		JPanel panel_9 = new JPanel();
		panel_9 .setBackground(new Color(255, 255, 255));

		
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addComponent(lblNewLabel_7, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(dateChooser_1_1_1, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_7_1, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(dateChooser_1_1_1_1, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
					.addGap(71)
					.addComponent(lblNewLabel_7_1_1, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField_6, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(panel_8_1, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
					.addGap(54)
					.addComponent(panel_8_2, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
					.addGap(55)
					.addComponent(panel_8_3, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
					.addGap(46)
					.addComponent(panel_8, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(panel_9, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
					.addGap(24))
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNewLabel_7, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addContainerGap()
					.addComponent(dateChooser_1_1_1_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_panel_7.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_9, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_8_3, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_8_2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_8_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_7.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(dateChooser_1_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_7_1_1, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
								.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
							.addComponent(lblNewLabel_7_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)))
					.addGap(11))
		);
		panel_7.setLayout(gl_panel_7);
		
		JPanel bang = new JPanel();
		panel_6.add(bang, BorderLayout.CENTER);
		 // Tạo JTable và cấu hình
         table_1 = new JTable();
        table_1.setBackground(new Color(240, 240, 240));
    	// Xác định khoảng thời gian (có thể thay đổi theo yêu cầu)
        LocalDateTime startDate = LocalDateTime.now(); // Ngày bắt đầu
        LocalDateTime endDate = LocalDateTime.now();   // Ngày kết thúc
       
        
        PhieuThuChiBUS phieuThuChiBUS = new PhieuThuChiBUS();
		Object[][] layDuLieuBang = phieuThuChiBUS.layDuLieuBang(startDate, endDate);
        table_1.setModel(new DefaultTableModel(layDuLieuBang,
                new String[]{"Mã phiếu thu chi", "Tên nhân viên", "Ngày tạo", "Loại phiếu", "Trạng thái", "Phương thức", "Số tiền", "Mô tả"}) {
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
        
        JScrollPane scrollPane = new JScrollPane(table_1);
		bang.setLayout(new BorderLayout());
		bang.add(scrollPane, BorderLayout.CENTER);  
		
		// Giả sử bạn đã tạo JTable với tên là table
		
		table_1.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int selectedRow = table_1.getSelectedRow(); // Lấy chỉ số hàng được chọn
		        if (selectedRow != -1) {
		            // Lấy các giá trị từ hàng đã chọn
		            String maPhieu = table_1.getValueAt(selectedRow, 0).toString(); // Lấy mã phiếu
		            String tenNV = table_1.getValueAt(selectedRow, 1).toString(); // Lấy tên nhân viên
		            String dateString = table_1.getValueAt(selectedRow, 2).toString(); // Lấy ngày tạo
		            String loaiPhieu = table_1.getValueAt(selectedRow, 3).toString(); // Lấy loại phiếu
		            String trangThai = table_1.getValueAt(selectedRow, 4).toString(); // Lấy trạng thái
		            String phuongThuc = table_1.getValueAt(selectedRow, 5).toString(); // Lấy phương thức
		            String soTien = table_1.getValueAt(selectedRow, 6).toString(); // Lấy số tiền
		            String moTa = table_1.getValueAt(selectedRow, 7).toString(); // Lấy mô tả

		            NhanVienDAL  dal = new NhanVienDAL();
		            NhanVien nv = new NhanVien();
		            ///////can fix
		            nv = dal.getNhanVienTheoMa(tenNV);
		            String maNV = nv.getMaNV();
		            // Cập nhật các trường nhập liệu
		            String soTienfm = formatSoTien(soTien);
		            
		            textField_maphieu.setText(maPhieu); // Cập nhật mã phiếu
		            textField_SoTien.setText(soTienfm); // Cập nhật số tiền
		            textField_mota.setText(moTa); // Cập nhật mô tả
		            
		            // Cập nhật ngày tạo và trạng thái (nếu cần)
		            try {
		                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng ngày là yyyy-MM-dd
		                dateChooser_ngaytao.setDate(sdf.parse(dateString)); // Phân tích ngày
		            } catch (ParseException ex) {
		                ex.printStackTrace(); // In ra lỗi nếu có
		                JOptionPane.showMessageDialog(null, "Lỗi khi phân tích ngày: " + ex.getMessage());
		            }

		            textField_maNV.setText(maNV); // Cập nhật mã NV
		            comboBox_loaiphieu.setSelectedItem(loaiPhieu); // Cập nhật loại phiếu
		            comboBox_PhuongThuc.setSelectedItem(phuongThuc); // Cập nhật phương thức
		            
		            // Kiểm tra trạng thái và cập nhật comboBoxtranthai
		            if ("Đã hủy".equalsIgnoreCase(trangThai)) {
		                comboBoxtranthai.setSelectedItem("Đã Hủy");
		                // Khóa các nút sửa và xóa
		                btnNewButton_1.setEnabled(false);
		                btnNewButton_2.setEnabled(false);
		            } else {
		                comboBoxtranthai.setSelectedItem("Còn Hoạt Động");
		                // Mở khóa các nút sửa và xóa
		                btnNewButton_1.setEnabled(true);
		                btnNewButton_2.setEnabled(true);
		            }
		        }
		    }
		});




		
		
		
		
		btnNewButton = new JButton("Thêm");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(243, 125, 0));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Đặt con trỏ chuột thành dạng tay

        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy dữ liệu từ các trường nhập liệu
            	PhieuThuChiDAL phieuThuChiDAL = new PhieuThuChiDAL();
            	String maPhieu = phieuThuChiDAL.layMaPhieuTiepTheo();
                String loaiPhieuCB = (String) comboBox_loaiphieu.getSelectedItem();
                String loaiPhieu = "";
                if(loaiPhieuCB.equals("Phiếu Thu")) {
                	loaiPhieu = "Thu";
                }             
                else {
                	loaiPhieu = "Chi";
                }
                String moTa = textField_mota.getText().trim();
                String maNV = textField_maNV.getText().trim();
                
                // Lấy nhân viên theo mã
                NhanVienDAL nvDal = new NhanVienDAL();
                NhanVien nhanVien = nvDal.getNhanVienTheoMa(maNV);
                
                if (nhanVien == null) {
                    JOptionPane.showMessageDialog(null, "Mã nhân viên không hợp lệ.");
                    return;
                }

                // Lấy ngày từ dateChooser
                LocalDateTime ngayTao = LocalDateTime.now();

                double soTien;
                
                // Lấy số tiền và xử lý
                try {
                    String soTienStr = textField_SoTien.getText().trim();
                    if (soTienStr.equals("nhập tiền tại đây") || soTienStr.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập số tiền.");
                        return;
                    }
                    soTien = Double.parseDouble(soTienStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Số tiền không hợp lệ.");
                    return;
                }

                // Lấy phương thức thanh toán
                String phuongThuc = (String) comboBox_PhuongThuc.getSelectedItem();
                boolean conHoatDong = true;

                // Tạo đối tượng phiếu chi
                entity.PhieuThuChi phieuChi = new entity.PhieuThuChi(maPhieu, loaiPhieu, moTa, ngayTao, soTien, phuongThuc, conHoatDong, nhanVien);
                
                // Thêm phiếu chi vào cơ sở dữ liệu thông qua BUS
                PhieuThuChiBUS phieuThuChiBUS = new PhieuThuChiBUS();
                boolean isSuccess = phieuThuChiBUS.themPhieuThuChi(phieuChi);
                
                if (isSuccess) {
                    JOptionPane.showMessageDialog(null, "Thêm phiếu chi thành công.");
                    // Cập nhật lại bảng nếu cần
                    updateTable();
                    textField_SoTien.setText(""); // Đặt ô số tiền về rỗng
                    textField_mota.setText("");   // Đặt ô mô tả về rỗng
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm phiếu chi thất bại.");
                }
            }
        });
		
		 btnNewButton_1 = new JButton("Sửa");
        btnNewButton_1.setForeground(new Color(243, 125, 0));
        btnNewButton_1.setBackground(new Color(255, 255, 255));
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Đặt con trỏ chuột thành dạng tay

        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gọi phương thức để thực hiện sửa phiếu thu chi
                suaPhieuThuChi();
                
            }
        });
        
		 btnNewButton_2 = new JButton("Xóa");
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBackground(new Color(208, 13, 32));
		btnNewButton_2.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Đặt con trỏ chuột thành dạng tay

		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Lấy mã phiếu từ trường nhập liệu (textField_maphieu)
		        String maPhieu = textField_maphieu.getText().trim();

		        // Kiểm tra xem mã phiếu có hợp lệ không
		        if (!maPhieu.isEmpty()) {
		            PhieuThuChiDAL phieuThuChiDAL = new PhieuThuChiDAL();

		            // Gọi phương thức hủy phiếu thu chi
		            boolean success = phieuThuChiDAL.xoaPhieuThuChi(maPhieu);

		            if (success) {
		                JOptionPane.showMessageDialog(null, "Phiếu thu chi đã được hủy thành công!");
		                // Cập nhật lại giao diện, ví dụ như xóa các trường nhập liệu
		                textField_maphieu.setText("");
		                textField_SoTien.setText("");
		                textField_mota.setText("");
		                // ... Xóa các trường cần thiết khác hoặc làm mới danh sách bảng, v.v.
		               updateTable();
		            } else {
		                JOptionPane.showMessageDialog(null, "Không thể hủy phiếu thu chi. Vui lòng thử lại.");
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu thu chi cần xóa.");
		        }
		    }
		});

		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 255, 255));
        
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(1)
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
					.addGap(8))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		
		JPanel panel_2 = new JPanel();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 1975, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblNewLabel_2 = new JLabel("Mã phiếu thu/chi:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblNewLabel_3 = new JLabel("Mã nhân viên:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		textField_maphieu = new JTextField();
		textField_maphieu = new JTextField("PTC******");
		textField_maphieu.setEditable(false);

		textField_maphieu.setColumns(10);

//		 
		JLabel lblNewLabel_6 = new JLabel("Ngày tạo:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		 dateChooser_ngaytao = new JDateChooser();
		dateChooser_ngaytao.setDate(new java.util.Date()); // Thiết lập ngày hiện tại
		dateChooser_ngaytao.setDateFormatString("dd-MM-yyyy"); // Định dạng ngày hiển thị
		dateChooser_ngaytao.setEnabled(false);
		
		textField_maNV = new JTextField("NV0001");
		textField_maNV.setEditable(false);

		textField_maNV.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Trạng thái:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		 comboBoxtranthai = new JComboBox<String>();
		 comboBoxtranthai.setFont(new Font("Tahoma", Font.BOLD, 13));
		 comboBoxtranthai.setEnabled(false); // Đặt JComboBox ở chế độ không cho phép chỉnh sửa
		 comboBoxtranthai.addItem("Còn Hoạt Động");
		 comboBoxtranthai.addItem("Đã Hủy");
		
		JLabel lblNewLabel_2_1 = new JLabel("Loại phiếu:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblNewLabel_3_1 = new JLabel("Phương thức:");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		comboBox_loaiphieu = new JComboBox();
		 comboBox_loaiphieu = new JComboBox<String>();
		 comboBox_loaiphieu.setFont(new Font("Tahoma", Font.BOLD, 13)); 
		comboBox_loaiphieu.addItem("Phiếu Thu");
		comboBox_loaiphieu.addItem("Phiếu Chi");
		
		comboBox_PhuongThuc = new JComboBox<String>();
		comboBox_PhuongThuc.setFont(new Font("Tahoma", Font.BOLD, 13));
	        comboBox_PhuongThuc.addItem("Tiền mặt");
	        comboBox_PhuongThuc.addItem("Chuyển khoản");
	        JLabel lblNewLabel_2_1_1 = new JLabel("Số tiền:");
	        lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));

	        textField_SoTien = new JTextField("nhập tiền tại đây");
	        textField_SoTien.setColumns(10);

	     // Thêm DocumentFilter để chỉ cho phép nhập số
	        ((AbstractDocument) textField_SoTien.getDocument()).setDocumentFilter(new DocumentFilter() {
	            @Override
	            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
	                if (string != null && string.matches("[0-9]*")) {
	                    String currentText = textField_SoTien.getText();
	                    String newText = currentText.substring(0, offset) + string + currentText.substring(offset);
	                    if (isValidAmount(newText)) {
	                        super.insertString(fb, offset, string, attr);
	                    }
	                }
	            }

	            @Override
	            public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException {
	                if (string != null && string.matches("[0-9]*")) {
	                    String currentText = textField_SoTien.getText();
	                    String newText = currentText.substring(0, offset) + string + currentText.substring(offset + length);
	                    if (isValidAmount(newText)) {
	                        super.replace(fb, offset, length, string, attr);
	                    }
	                }
	            }

	            private boolean isValidAmount(String text) {
	                // Kiểm tra xem số chữ số có ít hơn 10 không
	                return text.length() < 10 || text.equals("nhập tiền tại đây"); // Cho phép giữ nguyên placeholder
	            }
	        });
	        // Xóa placeholder khi người dùng nhấp vào JTextField
	        textField_SoTien.addFocusListener(new FocusAdapter() {
	            @Override
	            public void focusGained(FocusEvent e) {
	                if (textField_SoTien.getText().equals("nhập tiền tại đây")) {
	                    textField_SoTien.setText("");
	                }
	            }

	            @Override
	            public void focusLost(FocusEvent e) {
	                if (textField_SoTien.getText().isEmpty()) {
	                    textField_SoTien.setText("nhập tiền tại đây");
	                }
	            }
	        });

	        JLabel lblNewLabel_2_1_1_1 = new JLabel("Mô tả:");
	        lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));

	        textField_mota = new JTextField("nhập mô tả tại đây");
	        textField_mota.setColumns(10);

	        // Xóa placeholder khi người dùng nhấp vào JTextField
	        textField_mota.addFocusListener(new FocusAdapter() {
	            @Override
	            public void focusGained(FocusEvent e) {
	                if (textField_mota.getText().equals("nhập mô tả tại đây")) {
	                    textField_mota.setText("");
	                }
	            }

	            @Override
	            public void focusLost(FocusEvent e) {
	                if (textField_mota.getText().isEmpty()) {
	                    textField_mota.setText("nhập mô tả tại đây");
	                }
	            }
	        });
		
		JLabel lblNewLabel_5 = new JLabel("vnd");
		lblNewLabel_5.setForeground(new Color(243, 125, 0));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_maNV, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
						.addComponent(textField_maphieu, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblNewLabel_6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(9))
						.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBoxtranthai, 0, 300, Short.MAX_VALUE)
						.addComponent(dateChooser_ngaytao, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblNewLabel_3_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(18))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(34)))
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(comboBox_PhuongThuc, 0, 300, Short.MAX_VALUE)
						.addComponent(comboBox_loaiphieu, 0, 300, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2_1_1_1, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(textField_SoTien, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
							.addGap(117))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(textField_mota, GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
							.addGap(12))))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2_1_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_SoTien, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox_loaiphieu, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addContainerGap())
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
											.addComponent(lblNewLabel_3_1, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
											.addComponent(comboBox_PhuongThuc, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
											.addComponent(lblNewLabel_2_1_1_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
											.addComponent(textField_mota, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addGap(1)
									.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
										.addComponent(dateChooser_ngaytao, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
										.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
											.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
											.addComponent(textField_maphieu, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblNewLabel_6, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_2.createSequentialGroup()
											.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
												.addComponent(textField_maNV, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
											.addGap(1))
										.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
											.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
											.addComponent(comboBoxtranthai, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))))
							.addGap(14))))
		);
		panel_2.setLayout(gl_panel_2);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		
		}
    
    public QuanLyPhieuThuChiPanel(String maPhieu, String loaiPhieu, String moTa, LocalDateTime ngayTao, double soTien,
								  String phuongThuc, boolean conHoatDong, NhanVien nhanVien) {
		// TODO Auto-generated constructor stub
	}
    private String formatSoTien(String soTien) {
        // Kiểm tra xem chuỗi có rỗng không
        if (soTien == null || soTien.isEmpty()) {
            return "0"; // Trả về 0 nếu không có giá trị
        }
        
        // Bỏ dấu phẩy và phần thập phân
        String formattedSoTien = soTien.replace(".", ""); // Bỏ dấu phẩy
        int indexOfDot = formattedSoTien.indexOf(",");
        
        if (indexOfDot != -1) {
            // Nếu có dấu chấm, lấy phần trước dấu chấm
            formattedSoTien = formattedSoTien.substring(0, indexOfDot);
        }
        
        return formattedSoTien; // Trả về số tiền đã định dạng
    }
    private void suaPhieuThuChi() {
        String maPhieu = textField_maphieu.getText().trim();
        // Kiểm tra mã phiếu
        if (maPhieu == null || maPhieu.isEmpty()|| maPhieu.equals("PTC******")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu.");
            return;
        }// Lấy mã phiếu từ trường nhập liệu
        String tenNV = textField_maNV.getText().trim(); // Lấy tên nhân viên
        String moTa = textField_mota.getText().trim(); // Lấy mô tả
        String phuongThuc = comboBox_PhuongThuc.getSelectedItem().toString(); // Lấy phương thức
        String soTien = textField_SoTien.getText().trim(); // Định dạng số tiền
        PhieuThuChiDAL dal = new PhieuThuChiDAL();
        
      
        // Kiểm tra nếu `soTien` rỗng hoặc chứa giá trị mặc định
        if (soTien.isEmpty() || soTien.equals("nhập tiền tại đây")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số tiền hợp lệ.");
            return;
        }

        try {
        	  // Kiểm tra nếu `soTien` rỗng hoặc chứa giá trị mặc định
            if (soTien.isEmpty() || soTien.equals("nhập tiền tại đây")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập số tiền hợp lệ.");
                return;
            }
            // Kiểm tra số tiền là số hợp lệ
            Double.parseDouble(soTien);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Số tiền không hợp lệ.");
            return;
        }

        // Tạo đối tượng phiếu thu chi mới
        entity.PhieuThuChi phieuThuChiMoi = new entity.PhieuThuChi();
        NhanVienDAL nhanVienDAL = new NhanVienDAL();
        NhanVien nhanVien = nhanVienDAL.getNhanVienTheoMa(tenNV);
        
        phieuThuChiMoi.setMaPhieu(dal.layMaPhieuTiepTheo()); // Lấy mã phiếu mới
        phieuThuChiMoi.setLoaiPhieu(comboBox_loaiphieu.getSelectedItem().toString());
        phieuThuChiMoi.setMoTa(moTa);
        phieuThuChiMoi.setNgayLap(LocalDateTime.now()); // Hoặc lấy ngày từ date chooser
        phieuThuChiMoi.setSoTien(Double.parseDouble(soTien));
        phieuThuChiMoi.setPhuongThucThanhToan(phuongThuc);
        phieuThuChiMoi.setConHoatDong(true); // Mới tạo nên sẽ là true
        phieuThuChiMoi.setNhanVien(nhanVien);

        // Thay đổi trạng thái phiếu thu chi hiện tại
        boolean isHuySuccess = dal.xoaPhieuThuChi(maPhieu);
        
        if (isHuySuccess) {
            // Thêm phiếu thu chi mới
            boolean isThemSuccess = dal.themPhieuThuChi(phieuThuChiMoi);
            if (isThemSuccess) {
                JOptionPane.showMessageDialog(null, "Cập nhật phiếu thu chi thành công!");
                // Xóa các trường nhập liệu và cập nhật bảng
                textField_maphieu.setText("");
                textField_SoTien.setText("");
                textField_mota.setText("");
                updateTable(); // Gọi hàm cập nhật lại bảng nếu cần
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi khi thêm phiếu thu chi mới!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Lỗi khi hủy phiếu thu chi!");
        }
    }


	private void updateTable() {
        LocalDateTime startDate = dateChooser_1_1_1.getDate() != null ?
            dateChooser_1_1_1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() : null;
        LocalDateTime endDate = dateChooser_1_1_1_1.getDate() != null ?
            dateChooser_1_1_1_1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() : null;

        // Kiểm tra ngày hợp lệ
        if (startDate != null && endDate != null && !startDate.isAfter(endDate)) {
            // Lấy dữ liệu từ PhieuThuChiBUS
            Object[][] data = new PhieuThuChiBUS().layDuLieuBang(startDate, endDate);
            
            // Cập nhật bảng
            table_1.setModel(new DefaultTableModel(data, new String[]{
                "Mã phiếu thu chi", "Tên nhân viên", "Ngày tạo", "Loại phiếu", 
                "Trạng thái", "Phương thức", "Số tiền", "Mô tả"}));


        }
    }
	
}


