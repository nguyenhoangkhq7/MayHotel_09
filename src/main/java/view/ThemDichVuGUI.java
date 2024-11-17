package view;

import java.awt.EventQueue;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import constraints.CONSTRAINTS;
import dal.ChiTiet_DonDatPhong_PhongDAL;
import dal.ChiTiet_DonDatPhong_Phong_DichVuDAL;
import dal.DichVuDAL;
import entity.ChiTiet_DonDatPhong_Phong;
import entity.ChiTiet_DonDatPhong_Phong_DichVu;
import entity.DichVu;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class ThemDichVuGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox comboBox_1;
	private JTextArea textArea ;
	private JComboBox<String> comboBox;
	
	
	/**
	 * Launch the application.
	 */
	  private ChiTiet_DonDatPhong_Phong chitietphong; // Thuộc tính lưu chi tiết phòng
	  public static void main(String[] args) {
		  JFrame frame = new JFrame();
	        frame.setSize(1920,1080);
	        frame.setVisible(true);
	        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        frame.add(new ThemDichVuGUI(new ChiTiet_DonDatPhong_PhongDAL().getAllChiTietDonDatPhongPhong().get(0)));
	    }
	public ThemDichVuGUI(ChiTiet_DonDatPhong_Phong chitietphong) {
        this.chitietphong = chitietphong; // Gán tham số cho thuộc tính

   	 setLayout(new BorderLayout());
		contentPane = new JPanel();

		contentPane.setLayout(new BorderLayout(0, 0));
        add(contentPane, BorderLayout.CENTER);
		JPanel content = new JPanel();
		contentPane.add(content, BorderLayout.CENTER);
		content.setLayout(new BorderLayout(0, 0));
		
		JPanel tieude = new JPanel();
		tieude.setForeground(new Color(255, 255, 255));
		tieude.setBackground(new Color(69, 96, 117));
		content.add(tieude, BorderLayout.NORTH);
		
		JLabel lblThmDchV = new JLabel("Thêm dịch vụ cho phòng");
		lblThmDchV.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblThmDchV.setForeground(new Color(255, 255, 255));
		lblThmDchV.setBackground(new Color(255, 255, 255));
		lblThmDchV.setHorizontalAlignment(SwingConstants.CENTER);
		

		
		
		
		JButton btnNewButton_1 = new JButton("Thoát");
		btnNewButton_1.setForeground(new Color(243, 125, 0));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBorder(new LineBorder(CONSTRAINTS.ORANGE, 2));
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Đặt con trỏ chuột thành dạng tay
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			 @Override
	            public void mouseEntered(MouseEvent e) {
             // Thêm đường viền và đổi màu nền khi rê chuột vào
				 btnNewButton_1.setBackground(new Color(255,230,202)); // Đổi màu nền khi chuột vào
         }

         @Override
         public void mouseExited(MouseEvent e) {
             // Khôi phục viền và màu nền gốc khi chuột rời đi
        	 btnNewButton_1.setBackground(Color.WHITE); // Khôi phục màu nền ban đầu
         }
         
	          
		 } )
		;
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		GroupLayout gl_tieude = new GroupLayout(tieude);
		gl_tieude.setHorizontalGroup(
			gl_tieude.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tieude.createSequentialGroup()
					.addComponent(lblThmDchV, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addGap(18))
		);
		gl_tieude.setVerticalGroup(
			gl_tieude.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_tieude.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_tieude.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
						.addComponent(lblThmDchV, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
					.addContainerGap())
		);
		tieude.setLayout(gl_tieude);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(217, 217, 217));
		content.add(panel_1, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 252, 254));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 982, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE))
							.addGap(41)
							.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
							.addGap(0, 0, Short.MAX_VALUE))
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JLabel lblNewLabel_2 = new JLabel("Dịch vụ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(ThemDichVuGUI.class.getResource("/icon/icondichvu.png"))); // Đường dẫn tới icon của bạn
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.RIGHT); // Đặt vị trí văn bản sang bên phải biểu tượng

		 comboBox = new JComboBox();
	
		loadComboBoxDichVu();
		
		
		JLabel lblNewLabel_3 = new JLabel("Số lượng tồn");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon(ThemDichVuGUI.class.getResource("/icon/tonkho.png"))); // Đường dẫn tới icon của bạn
		lblNewLabel_3.setHorizontalTextPosition(SwingConstants.RIGHT); // Đặt vị trí văn bản sang bên phải biểu tượng
		
		textField = new JTextField();
		textField.setEditable(false); // Đảm bảo trường này có thể chỉnh sửa

		textField.setColumns(10);
		// Lắng nghe sự kiện chọn dịch vụ
		comboBox.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Lấy dịch vụ được chọn
		      laySoLuongTon();
		    }
		});

		
		JLabel lblNewLabel_4 = new JLabel("Số lượng đặt");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setIcon(new ImageIcon(ThemDichVuGUI.class.getResource("/icon/soluongdat.png"))); // Đường dẫn tới icon của bạn
		lblNewLabel_4.setHorizontalTextPosition(SwingConstants.RIGHT); // Đặt vị trí văn bản sang bên phải biểu tượng
		
	 comboBox_1 = new JComboBox();
		comboBox_1.setEnabled(false); // Đặt thành không khả dụng ban đầu

		
		JLabel lblNewLabel_5 = new JLabel("Thời gian");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_5.setForeground(new Color(0, 0, 0));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setIcon(new ImageIcon(ThemDichVuGUI.class.getResource("/icon/time.png"))); // Đường dẫn tới icon của bạn
		lblNewLabel_5.setHorizontalTextPosition(SwingConstants.RIGHT); // Đặt vị trí văn bản sang bên phải biểu tượng
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setEditable(false); // Đảm bảo trường này có thể chỉnh sửa
		 // Lấy ngày hiện tại
	    LocalDate currentDate = LocalDate.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    String formattedDate = currentDate.format(formatter);
	    
	    // Cập nhật JTextField với ngày hiện tại
	    textField_1.setText(formattedDate);

		JLabel lblNewLabel_6 = new JLabel("Mô tả");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_6.setIcon(new ImageIcon(ThemDichVuGUI.class.getResource("/icon/ghichu.png"))); // Đường dẫn tới icon của bạn
		lblNewLabel_6.setHorizontalTextPosition(SwingConstants.RIGHT); // Đặt vị trí văn bản sang bên phải biểu tượng
		
		 textArea = new JTextArea();
		textArea.setBackground(new Color(240, 240, 240));
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
								.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
								.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblNewLabel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox, 0, 400, Short.MAX_VALUE)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
								.addGroup(gl_panel_4.createSequentialGroup()
									.addComponent(comboBox_1, 0, 398, Short.MAX_VALUE)
									.addGap(2))
								.addGroup(gl_panel_4.createSequentialGroup()
									.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
									.addGap(1))))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(lblNewLabel_6, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING, false)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textField)
						.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(lblNewLabel_6, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
							.addGap(110))
						.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel_4.setLayout(gl_panel_4);
		
		JLabel hinhminhhoa2 = new JLabel(new ImageIcon(ThemDichVuGUI.class.getResource("/icon/hinhminhhoa2.jpg")));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addComponent(hinhminhhoa2, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addComponent(hinhminhhoa2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
		);
		panel_3.setLayout(gl_panel_3);
		
		JLabel hinhminhhoa1 = new JLabel("");
		hinhminhhoa1.setHorizontalAlignment(SwingConstants.CENTER);
		hinhminhhoa1.setIcon(new ImageIcon(ThemDichVuGUI.class.getResource("/icon/hinhminhhoa1.jpg")));
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addComponent(hinhminhhoa1, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE)
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addComponent(hinhminhhoa1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 188, Short.MAX_VALUE)
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblNewLabel = new JLabel(chitietphong.getMaCT_DDP_P());
		lblNewLabel.setForeground(new Color(255, 128, 64));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblNewLabel_1 = new JLabel(chitietphong.getPhong().getMaPhong());
		lblNewLabel_1.setForeground(new Color(255, 128, 64));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		// Giả sử có các icon dondatphong.png và phong.png trong package icon
		JLabel icondondatphong = new JLabel(new ImageIcon(getClass().getResource("/icon/icondondatphong.png")));
		JLabel iconphong = new JLabel(new ImageIcon(getClass().getResource("/icon/iconphong.png")));

		
		JButton btnNewButton = new JButton("Thêm");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(243, 125, 0));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Đặt con trỏ chuột thành dạng tay
		 // Xử lý sự kiện khi bấm nút thêm
		btnNewButton.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // Đổi màu chữ khi rê chuột vào
		    	btnNewButton.setForeground(Color.BLACK);
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		        // Khôi phục màu chữ gốc khi chuột rời đi
		    	btnNewButton.setForeground(new Color(255, 255, 255));
		    }
		});
		btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themChiTiet();
                
                loadComboBoxDichVu();

                // Cập nhật lại JTextField và JComboBox cho số lượng đặt
                textField.setText(""); // Xóa giá trị cũ
                comboBox_1.removeAllItems(); // Xóa mục cũ trong comboBox_1
                comboBox_1.setEnabled(false); // Đặt comboBox_1 thành không khả dụng
            }
        });

   
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(icondondatphong, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(iconphong, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
					.addGap(283)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(icondondatphong, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
							.addComponent(iconphong, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		panel_1.setLayout(gl_panel_1);
		
	}
	private void themChiTiet() {
        // Lấy dữ liệu từ các trường nhập liệu
		ChiTiet_DonDatPhong_Phong_DichVuDAL dal = new ChiTiet_DonDatPhong_Phong_DichVuDAL();
		 String maCT_DDP_P_DV = dal.getNextMaCT_DDP_P_DV();
		 int soLuongDat = (Integer) comboBox_1.getSelectedItem(); // Lấy số lượng đặt từ comboBox
		    String maDichVu = (String) comboBox.getSelectedItem(); // Lấy mã dịch vụ từ comboBox
		    String moTa = textArea.getText(); // Lấy mô tả từ JTextArea
        DichVu dichVu = new DichVu();
        DichVuDAL dalDV = new DichVuDAL();
        dichVu = dalDV.getDichVuTheoMa(maDichVu);
        
        ChiTiet_DonDatPhong_Phong_DichVu chiTiet = new ChiTiet_DonDatPhong_Phong_DichVu(maCT_DDP_P_DV, soLuongDat, LocalDate.now(), dichVu, chitietphong, moTa);
        // Gọi phương thức thêm chi tiết
        ChiTiet_DonDatPhong_Phong_DichVuDAL dalCTDV = new ChiTiet_DonDatPhong_Phong_DichVuDAL();
        boolean result = dalCTDV.themChiTiet(chiTiet);
        // Thông báo kết quả
        if (result) {
            JOptionPane.showMessageDialog(this, "Thêm chi tiết thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm chi tiết không thành công.");
        }
        boolean result_2 = dalDV.suaDichVu(new DichVu(dichVu.getMaDichVu(),dichVu.getDonGia(),dichVu.getTenDichVu() ,dichVu.getSoLuongTon()-soLuongDat,dichVu.isConHoatDong(), dichVu.getDonVi()));
        if (result_2) {
            JOptionPane.showMessageDialog(this, "Cập nhật số lượng dịch vụ thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật số lượng dịch vụ không thành công.");
        }
	}
	
	private void loadComboBoxDichVu() {
	    comboBox.removeAllItems(); // Xóa tất cả các mục hiện có

	    DichVuDAL dal = new DichVuDAL();
	    ArrayList<DichVu> danhSachDichVu = dal.getAllDichVu();
	    
	    // Thêm dịch vụ vào JComboBox
	    for (DichVu dichVu : danhSachDichVu) {
	        comboBox.addItem(dichVu.getMaDichVu()); // Giả sử có phương thức getMaDichVu()
	    }
	}
	

  	private void laySoLuongTon() {
  	  String maDichVu = (String) comboBox.getSelectedItem();
      if (maDichVu != null) {
      	DichVuDAL dal = new DichVuDAL();
        ArrayList<DichVu> danhSachDichVu = dal.getAllDichVu();
          // Tìm dịch vụ tương ứng trong danh sách
          for (DichVu dichVu : danhSachDichVu) {
              if (dichVu.getMaDichVu().equals(maDichVu)) {
                  // Cập nhật số lượng tồn vào JTextField
                  int soLuongTon = dichVu.getSoLuongTon();
                  textField.setText(String.valueOf(soLuongTon));

                  // Cập nhật JComboBox số lượng đặt
                  comboBox_1.removeAllItems(); // Xóa các mục cũ
                  for (int i = 1; i <= soLuongTon; i++) {
                      comboBox_1.addItem(i); // Thêm từ 1 đến số lượng tồn
                  }
                  comboBox_1.setEnabled(true); // Kích hoạt JComboBox cho số lượng đặt
                  break;
              }
          }
      }
  	}

}

