package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFramever2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFramever2 frame = new MainFramever2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFramever2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1537, 931);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel menu = new JPanel();
		menu.setBackground(new Color(69, 96, 117));
		contentPane.add(menu, BorderLayout.WEST);
		
		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.setIcon(new ImageIcon("D:\\Code\\Demo\\src\\main\\java\\icon\\Microsoft-Fluentui-Emoji-Mono-Hotel.48.png"));
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_6 = new JLabel("May Hotel");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Segoe UI", Font.BOLD, 35));
		
		JLabel lblNewLabel_7 = new JLabel("The best place to be");
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		JButton btnNewButton = new JButton("Đăng xuất");
		btnNewButton.setForeground(new Color(255, 128, 0));
		
		JButton btnNewButton_1 = new JButton("Trang chủ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNewButton_1_2_4_1 = new JButton("Hóa đơn");
		
		JLabel lblNewLabel_5_1_2_4_1 = new JLabel("____________________________________________");
		lblNewLabel_5_1_2_4_1.setForeground(Color.LIGHT_GRAY);
		
		JButton btnNewButton_1_1 = new JButton("Khuyến mãi");
		
		JButton btnNewButton_1_2 = new JButton("Phòng");
		
		JButton btnNewButton_1_3 = new JButton("Nhân viên");

		
		JButton btnNewButton_1_4 = new JButton("Dịch vụ");

		
		JButton btnNewButton_1_6 = new JButton("Doanh thu");
		
		JButton btnNewButton_1_7 = new JButton("Hóa đơn");
		
		JButton btnNewButton_1_8 = new JButton("Khách hàng");
		GroupLayout gl_menu = new GroupLayout(menu);
		gl_menu.setHorizontalGroup(
			gl_menu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menu.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNewLabel_3_1)
					.addGap(18)
					.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_menu.createSequentialGroup()
					.addGap(96)
					.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_menu.createSequentialGroup()
					.addGap(43)
					.addComponent(btnNewButton_1_2_4_1, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
				.addComponent(lblNewLabel_5_1_2_4_1, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
				.addGroup(Alignment.TRAILING, gl_menu.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_menu.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_menu.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton_1_2, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_menu.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton_1_3, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_menu.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton_1_4, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_menu.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton_1_6, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_menu.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton_1_7, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_menu.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton_1_8, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_menu.createSequentialGroup()
					.addContainerGap(163, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_menu.setVerticalGroup(
			gl_menu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menu.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_menu.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3_1)
						.addComponent(lblNewLabel_6))
					.addGap(5)
					.addComponent(lblNewLabel_7)
					.addGap(18)
					.addComponent(btnNewButton)
					.addGap(52)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addComponent(btnNewButton_1_2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(btnNewButton_1_3, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addComponent(btnNewButton_1_4, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addComponent(btnNewButton_1_6, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(btnNewButton_1_7, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addComponent(btnNewButton_1_8, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(645)
					.addComponent(btnNewButton_1_2_4_1)
					.addGap(1)
					.addComponent(lblNewLabel_5_1_2_4_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
		);
		menu.setLayout(gl_menu);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new CardLayout(0, 0));
		
		JPanel dich_vu = new JPanel();
		panel_1.add(dich_vu, "name_1517434717296100");
		dich_vu.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		dich_vu.add(panel_3, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel(" Dịch vụ");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
		
		
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\Code\\Demo\\src\\main\\java\\icon\\Ampeross-Qetto-2-Search.48.png"));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(18)
					.addComponent(lblNewLabel)
					.addGap(515)
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1)
					.addGap(31))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
							.addGap(17))))
		);
		panel_3.setLayout(gl_panel_3);
		
		JPanel panel_4 = new JPanel();
		dich_vu.add(panel_4, BorderLayout.WEST);
		
		JLabel lblNewLabel_9_7 = new JLabel("Mã dịch vụ");
		lblNewLabel_9_7.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		JLabel lblNewLabel_9_1_1 = new JLabel("Tên dịch vụ");
		lblNewLabel_9_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_9_1_1_1 = new JLabel("Đơn giá");
		lblNewLabel_9_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		JLabel lblNewLabel_9_1_1_1_1 = new JLabel("Số lượng tồn");
		lblNewLabel_9_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		JLabel lblNewLabel_9_1_1_1_2 = new JLabel("Mô tả");
		lblNewLabel_9_1_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		JLabel lblNewLabel_9_1_1_1_3 = new JLabel("Ngày tạo");
		lblNewLabel_9_1_1_1_3.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		JLabel lblNewLabel_9_1_1_1_2_1 = new JLabel("Còn hoạt động");
		lblNewLabel_9_1_1_1_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		JButton btnNewButton_1_5 = new JButton("Thêm");
		btnNewButton_1_5.setForeground(Color.WHITE);
		btnNewButton_1_5.setBackground(new Color(255, 128, 0));
		
		JButton btnNewButton_1_5_1 = new JButton("Sửa");
		btnNewButton_1_5_1.setForeground(Color.WHITE);
		btnNewButton_1_5_1.setBackground(new Color(255, 128, 0));
		
		JButton btnNewButton_1_5_2 = new JButton("Xóa");
		btnNewButton_1_5_2.setForeground(Color.WHITE);
		btnNewButton_1_5_2.setBackground(new Color(255, 128, 0));
		
		JButton btnNewButton_1_5_3 = new JButton("Lưu");
		btnNewButton_1_5_3.setForeground(Color.WHITE);
		btnNewButton_1_5_3.setBackground(new Color(255, 128, 0));
		
		JButton btnNewButton_1_5_4 = new JButton("Hủy");
		btnNewButton_1_5_4.setForeground(Color.WHITE);
		btnNewButton_1_5_4.setBackground(new Color(255, 128, 0));
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(24)
					.addComponent(lblNewLabel_9_1_1)
					.addGap(309)
					.addComponent(lblNewLabel_9_7))
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(24)
					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
					.addGap(185)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(24)
					.addComponent(lblNewLabel_9_1_1_1, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(309)
					.addComponent(lblNewLabel_9_1_1_1_1, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(24)
					.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
					.addGap(185)
					.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(24)
					.addComponent(lblNewLabel_9_1_1_1_2, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(309)
					.addComponent(lblNewLabel_9_1_1_1_3, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(24)
					.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
					.addGap(185)
					.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(24)
					.addComponent(lblNewLabel_9_1_1_1_2_1))
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(24)
					.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
					.addGap(221))
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(41)
					.addComponent(btnNewButton_1_5, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(94)
					.addComponent(btnNewButton_1_5_1, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(98)
					.addComponent(btnNewButton_1_5_2, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(187)
					.addComponent(btnNewButton_1_5_3, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addComponent(btnNewButton_1_5_4, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(8)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_9_1_1)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_9_7)))
					.addGap(3)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_9_1_1_1)
						.addComponent(lblNewLabel_9_1_1_1_1))
					.addGap(6)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_9_1_1_1_2)
						.addComponent(lblNewLabel_9_1_1_1_3))
					.addGap(6)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblNewLabel_9_1_1_1_2_1)
					.addGap(11)
					.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_1_5, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_5_1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_5_2, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_1_5_3, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_5_4, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
		);
		panel_4.setLayout(gl_panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(null);
		dich_vu.add(panel_5, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"T\u00EAn d\u1ECBch v\u1EE5", "M\u00E3 d\u1ECBch v\u1EE5", "\u0110\u01A1n gi\u00E1", "S\u1ED1 l\u01B0\u1EE3ng t\u1ED3n", "M\u00F4 t\u1EA3", "Ng\u00E0y t\u1EA1o", "C\u00F2n ho\u1EA1t \u0111\u1ED9ng"
			}
		));
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGap(48)
					.addComponent(scrollPane)
					.addGap(49))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGap(5)
					.addComponent(scrollPane)
					.addGap(662))
		);
		panel_5.setLayout(gl_panel_5);
		
		JPanel nhan_vien = new JPanel();
		panel_1.add(nhan_vien, "name_261494422499");
		nhan_vien.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		nhan_vien.add(panel, BorderLayout.NORTH);
		
		JLabel lblNhnVin = new JLabel("Nhân viên");
		lblNhnVin.setFont(new Font("Segoe UI", Font.BOLD, 40));
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		
		btnNewButton_1_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			//	trangchu.setVisible(false);
				dich_vu.setVisible(true);
				nhan_vien.setVisible(false);
			}
		});
		
		btnNewButton_1_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//	trangchu.setVisible(false);
				dich_vu.setVisible(false);
				nhan_vien.setVisible(true);
			}
		});
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon("D:\\Code\\Demo\\src\\main\\java\\icon\\Ampeross-Qetto-2-Search.48.png"));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNhnVin)
					.addGap(594)
					.addComponent(textField_8, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(lblNewLabel_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(24))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(23)
					.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(33, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1_1)
						.addComponent(lblNhnVin))
					.addGap(11))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_2 = new JPanel();
		nhan_vien.add(panel_2, BorderLayout.WEST);
		
		JLabel lblNewLabel_9_1_1_2 = new JLabel("Mã nhân viên");
		lblNewLabel_9_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		
		JLabel lblNewLabel_9_1_1_2_1 = new JLabel("Họ tên");
		lblNewLabel_9_1_1_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		JLabel lblNewLabel_9_1_1_2_2 = new JLabel("Số điện thoại");
		lblNewLabel_9_1_1_2_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		
		JLabel lblNewLabel_9_1_1_2_3 = new JLabel("CCCD");
		lblNewLabel_9_1_1_2_3.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		
		JLabel lblNewLabel_9_1_1_2_4 = new JLabel("Email");
		lblNewLabel_9_1_1_2_4.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		JLabel lblNewLabel_9_1_1_2_5 = new JLabel("Địa chỉ");
		lblNewLabel_9_1_1_2_5.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		
		JLabel lblNewLabel_9_1_1_2_6 = new JLabel("Vai trò");
		lblNewLabel_9_1_1_2_6.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		JLabel lblNewLabel_9_1_1_2_7 = new JLabel("Còn hoạt động");
		lblNewLabel_9_1_1_2_7.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		textField_15 = new JTextField();
		textField_15.setColumns(10);
		
		textField_16 = new JTextField();
		textField_16.setColumns(10);
		
		JButton btnNewButton_1_9 = new JButton("Thêm");
		btnNewButton_1_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_9.setForeground(Color.WHITE);
		btnNewButton_1_9.setBackground(new Color(255, 128, 0));
		
		JButton btnNewButton_1_9_1 = new JButton("Sửa");
		btnNewButton_1_9_1.setForeground(Color.WHITE);
		btnNewButton_1_9_1.setBackground(new Color(255, 128, 0));
		
		JButton btnNewButton_1_9_2 = new JButton("Xóa");
		btnNewButton_1_9_2.setForeground(Color.WHITE);
		btnNewButton_1_9_2.setBackground(new Color(255, 128, 0));
		
		JButton btnNewButton_1_9_3 = new JButton("Lưu");
		btnNewButton_1_9_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_9_3.setForeground(Color.WHITE);
		btnNewButton_1_9_3.setBackground(new Color(255, 128, 0));
		
		JButton btnNewButton_1_9_4 = new JButton("Hủy bỏ");
		btnNewButton_1_9_4.setForeground(Color.WHITE);
		btnNewButton_1_9_4.setBackground(new Color(255, 128, 0));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_9_1_1_2)
								.addComponent(lblNewLabel_9_1_1_2_2, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_9_1_1_2_4, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_9_1_1_2_6, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_9, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
								.addComponent(textField_11)
								.addComponent(textField_13)
								.addComponent(textField_15))
							.addGap(70)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_9_1_1_2_7, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_9_1_1_2_5, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_16, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
								.addComponent(textField_14, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
								.addComponent(textField_12, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
								.addComponent(lblNewLabel_9_1_1_2_3, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_9_1_1_2_1, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_10, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(166)
							.addComponent(btnNewButton_1_9_3, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(39)
							.addComponent(btnNewButton_1_9_4, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(29)
					.addComponent(btnNewButton_1_9, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addGap(102)
					.addComponent(btnNewButton_1_9_1, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
					.addComponent(btnNewButton_1_9_2, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addGap(35))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_9_1_1_2)
						.addComponent(lblNewLabel_9_1_1_2_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblNewLabel_9_1_1_2_2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblNewLabel_9_1_1_2_3, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_9_1_1_2_4, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_9_1_1_2_5, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_14, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_9_1_1_2_6, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_9_1_1_2_7, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_16, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_15, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1_9, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_9_1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_9_2, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1_9_4, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_9_3, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(662, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_6 = new JPanel();
		nhan_vien.add(panel_6, BorderLayout.CENTER);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 nh\u00E2n vi\u00EAn", "H\u1ECD t\u00EA", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "CCCD", "Email", "\u0110\u1ECBa ch\u1EC9", "Vai tr\u00F2", "C\u00F2n ho\u1EA1t \u0111\u1ED9n"
			}
		));
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(111)
					.addComponent(scrollPane_1)
					.addGap(112))
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(5)
					.addComponent(scrollPane_1)
					.addGap(662))
		);
		panel_6.setLayout(gl_panel_6);

	}
}
