package view;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import com.toedter.calendar.JDateChooser;
import java.util.List;
import constraints.CONSTRAINTS;
import javax.swing.table.DefaultTableModel;

public class PhieuThuChi extends JPanel {

    private JTextField txtMaPhieu;
    private JTextField txtSoTien;
    private JButton btnThem;
    private JButton btnSua;
    private JPanel panelForm;
    private JPanel panelDetail_KH;
    private JComboBox<String> cboNhanVien;
    private JTextField txtMota;
    private JComboBox<String> cboTrangThai;
    private JComboBox<String> cboLoaiPhieu;
    private JComboBox<String> cboPhuongThuc;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_maphieu;
	private JTextField textField_tenNV;
	private JTextField textField_SoTien;
	private JTextField textField_mota;
	private JTextField textField_6;
    private JPanel content;


    public PhieuThuChi(BaoCao baoCaoFrame) {

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
                "Thiết lập thông tin phiếu thu chi", TitledBorder.LEADING, TitledBorder.TOP, null,CONSTRAINTS.ORANGE));
		
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
	                "Thiết lập bảng thông tin thu chi", TitledBorder.LEADING, TitledBorder.TOP, null,CONSTRAINTS.ORANGE));
		panel_6.setLayout(new BorderLayout());
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(255, 255, 255));
		panel_6.add(panel_7, BorderLayout.NORTH);
		
		JLabel lblNewLabel_7 = new JLabel("Ngày đầu:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JDateChooser dateChooser_1_1_1 = new JDateChooser();
		
		JLabel lblNewLabel_7_1 = new JLabel("Ngày đầu:");
		lblNewLabel_7_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JDateChooser dateChooser_1_1_1_1 = new JDateChooser();
		
		JLabel lblNewLabel_7_1_1 = new JLabel("Mã phiếu chi:");
		lblNewLabel_7_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Tìm");
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setBackground(new Color(243, 125, 0));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		
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
        JTable table_1 = new JTable();
        table_1.setBackground(new Color(240, 240, 240));
        table_1.setModel(new DefaultTableModel(new Object[][] {},
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
		
		
		
		
		
		
		JButton btnNewButton = new JButton("Thêm");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(243, 125, 0));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JButton btnNewButton_1 = new JButton("Sửa");
        btnNewButton_1.setForeground(new Color(243, 125, 0));
        btnNewButton_1.setBackground(new Color(255, 255, 255));
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JButton btnNewButton_2 = new JButton("Xóa");
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBackground(new Color(208, 13, 32));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
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
		
		JLabel lblNewLabel_3 = new JLabel("Tên nhân viên:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		textField_maphieu = new JTextField();
		textField_maphieu = new JTextField("PTC******");
		textField_maphieu.setEditable(false);

		textField_maphieu.setColumns(10);

//		 
		JLabel lblNewLabel_6 = new JLabel("Ngày tạo:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JDateChooser dateChooser_ngaytao = new JDateChooser();
		dateChooser_ngaytao.setDate(new java.util.Date()); // Thiết lập ngày hiện tại
		dateChooser_ngaytao.setDateFormatString("dd-MM-yyyy"); // Định dạng ngày hiển thị
		dateChooser_ngaytao.setEnabled(false);
		
		textField_tenNV = new JTextField();
		textField_tenNV.setEditable(false);

		textField_tenNV.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Trạng thái:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JComboBox    comboBoxtranthai = new JComboBox<String>();
		 comboBoxtranthai.setFont(new Font("Tahoma", Font.BOLD, 13));
		 comboBoxtranthai.setEnabled(false); // Đặt JComboBox ở chế độ không cho phép chỉnh sửa
		 comboBoxtranthai.addItem("Còn Hoạt Động");
		 comboBoxtranthai.addItem("Đã Hủy");
		
		JLabel lblNewLabel_2_1 = new JLabel("Loại phiếu:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblNewLabel_3_1 = new JLabel("Phương thức:");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JComboBox comboBox_loaiphieu = new JComboBox();
		 comboBox_loaiphieu = new JComboBox<String>();
		 comboBox_loaiphieu.setFont(new Font("Tahoma", Font.BOLD, 13)); 
		comboBox_loaiphieu.addItem("Phiếu Thu");
		comboBox_loaiphieu.addItem("Phiếu Chi");
		
		JComboBox comboBox_PhuongThuc = new JComboBox<String>();
		comboBox_PhuongThuc.setFont(new Font("Tahoma", Font.BOLD, 13));
	        comboBox_PhuongThuc.addItem("Tiền mặt");
	        comboBox_PhuongThuc.addItem("Chuyển khoản");
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Số tiền:");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		textField_SoTien = new JTextField();
		textField_SoTien.setColumns(10);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Mô tả:");
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		textField_mota = new JTextField();
		textField_mota.setColumns(10);
		
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
						.addComponent(textField_tenNV, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
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
												.addComponent(textField_tenNV, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
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
}
