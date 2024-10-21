package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Dimension;

public class BaoCao extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BaoCao frame = new BaoCao();
					frame.setVisible(true);
			        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  // Đặt JFrame ở trạng thái toàn màn hình

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BaoCao() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		// menu
		
		//JPanelMenu
		JPanel JPanelMenu = new JPanel();
		JPanelMenu.setBackground(new Color(69, 96, 117));
		contentPane.add(JPanelMenu, BorderLayout.WEST);
		JPanelMenu.setLayout(new BorderLayout(0, 0));
		
		JPanel panelLogo = new JPanel();
		panelLogo.setBackground(new Color(69, 96, 117));
		JPanelMenu.add(panelLogo, BorderLayout.NORTH);
		panelLogo.setLayout(new BorderLayout(0, 0));
		
		JPanel panelIcon = new JPanel();
		panelIcon.setBackground(new Color(69, 96, 117));
		panelLogo.add(panelIcon, BorderLayout.WEST);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon("D:\\Code\\Demo\\src\\main\\java\\icon\\Microsoft-Fluentui-Emoji-Mono-Hotel.48.png"));
		panelIcon.add(lblIcon);
		
		JPanel panelNameHotel = new JPanel();
		panelNameHotel.setBackground(new Color(69, 96, 117));
		panelLogo.add(panelNameHotel, BorderLayout.CENTER);
		panelNameHotel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblHotelName = new JLabel("MAY HOTEL");
		lblHotelName.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 45));
		panelNameHotel.add(lblHotelName, BorderLayout.NORTH);
		
		JLabel lblSlogan = new JLabel("The best place to be");
		lblSlogan.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 37));
		panelNameHotel.add(lblSlogan, BorderLayout.CENTER);
		
		JPanel panelButtonDX = new JPanel();
		panelButtonDX.setBorder(new EmptyBorder(15, 10, 10, 10));
		panelButtonDX.setBackground(new Color(69, 96, 117));
		panelLogo.add(panelButtonDX, BorderLayout.SOUTH);
		panelButtonDX.setLayout(new BorderLayout(0, 0));
		
		JButton btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.setForeground(new Color(243, 125, 0));
		btnDangXuat.setBackground(new Color(48, 47, 47));
		panelButtonDX.add(btnDangXuat, BorderLayout.EAST);
		
		JLabel lblLine = new JLabel("_________________________________________________________________");
		lblLine.setForeground(new Color(192, 192, 192));
		panelButtonDX.add(lblLine, BorderLayout.SOUTH);

//Panel Menu	
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(69, 96, 117));
		JPanelMenu.add(panelMenu, BorderLayout.CENTER);
		GridBagLayout gbl_panelMenu = new GridBagLayout();
		gbl_panelMenu.columnWidths = new int[] {0, 0, 0, 0, 0, 1};
		gbl_panelMenu.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panelMenu.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelMenu.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelMenu.setLayout(gbl_panelMenu);
		
		
		//btntrangchu
		JButton btnTrangChu = new JButton("Trang chủ");
	
		btnTrangChu.setBackground(new Color(48, 47, 47));
		btnTrangChu.setForeground(new Color(255, 255, 255));
		btnTrangChu.setFont(new Font("Segoe UI", Font.BOLD, 25));
		GridBagConstraints gbc_btnTrangChu = new GridBagConstraints();
		gbc_btnTrangChu.fill = GridBagConstraints.VERTICAL;
		gbc_btnTrangChu.insets = new Insets(30, 5, 60, 5);
		gbc_btnTrangChu.weightx = 1.0;
		gbc_btnTrangChu.gridx = 0;
		gbc_btnTrangChu.gridy = 0;
		btnTrangChu.setPreferredSize(new Dimension(270, 40));
		panelMenu.add(btnTrangChu, gbc_btnTrangChu);
		
		
		//btnDonDatPhong
		JButton btnDonDatPhong = new JButton("Đơn đặt phòng");
		btnDonDatPhong.setBackground(new Color(48, 47, 47));
		btnDonDatPhong.setForeground(new Color(255, 255, 255));
		btnDonDatPhong.setFont(new Font("Segoe UI", Font.BOLD, 25));
		GridBagConstraints gbc_btnDonDatPhong = new GridBagConstraints();
		gbc_btnDonDatPhong.fill = GridBagConstraints.VERTICAL;
		gbc_btnDonDatPhong.insets = new Insets(0, 5, 60, 5);
		gbc_btnDonDatPhong.gridx = 0;
		gbc_btnDonDatPhong.gridy = 1;
		btnDonDatPhong.setPreferredSize(new Dimension(270, 40));
		panelMenu.add(btnDonDatPhong, gbc_btnDonDatPhong);
		
		
		//btnHoaDon
		JButton btnHoaDon = new JButton("Hóa đơn");
		btnHoaDon.setForeground(new Color(255, 255, 255));
		btnHoaDon.setBackground(new Color(48, 47, 47));
		btnHoaDon.setPreferredSize(new Dimension(270, 40));
		btnHoaDon.setFont(new Font("Segoe UI", Font.BOLD, 25));
		GridBagConstraints gbc_btnHoaDon = new GridBagConstraints();
		gbc_btnHoaDon.fill = GridBagConstraints.VERTICAL;
		gbc_btnHoaDon.insets = new Insets(0, 5, 60, 5);
		gbc_btnHoaDon.gridx = 0;
		gbc_btnHoaDon.gridy = 2;
		panelMenu.add(btnHoaDon, gbc_btnHoaDon);
		
		//btnKhachHang
		JButton btnKhachHang = new JButton("Khách hàng");
		btnKhachHang.setForeground(new Color(255, 255, 255));
		btnKhachHang.setBackground(new Color(48, 47, 47));
		btnKhachHang.setPreferredSize(new Dimension(270, 40));
		btnKhachHang.setFont(new Font("Segoe UI", Font.BOLD, 25));
		GridBagConstraints gbc_btnKhachHang = new GridBagConstraints();
		gbc_btnKhachHang.fill = GridBagConstraints.VERTICAL;
		gbc_btnKhachHang.insets = new Insets(0, 5, 60, 5);
		gbc_btnKhachHang.gridx = 0;
		gbc_btnKhachHang.gridy = 3;
		panelMenu.add(btnKhachHang, gbc_btnKhachHang);
		
		
		//btnBaoCao
		JButton btnBaoCao = new JButton("Báo cáo");
		btnBaoCao.setForeground(new Color(255, 255, 255));
		btnBaoCao.setBackground(new Color(48, 47, 47));
		btnBaoCao.setPreferredSize(new Dimension(270, 40));
		btnBaoCao.setFont(new Font("Segoe UI", Font.BOLD, 25));
		GridBagConstraints gbc_btnBaoCao = new GridBagConstraints();
		gbc_btnBaoCao.fill = GridBagConstraints.VERTICAL;
		gbc_btnBaoCao.insets = new Insets(0, 5, 5, 5);
		gbc_btnBaoCao.gridx = 0;
		gbc_btnBaoCao.gridy = 4;
		panelMenu.add(btnBaoCao, gbc_btnBaoCao);
		
		//panelUser
		JPanel panelUser = new JPanel();
		panelUser.setBackground(new Color(69, 96, 117));
		JPanelMenu.add(panelUser, BorderLayout.SOUTH);
		panelUser.setLayout(new BorderLayout(0, 0));
		JLabel lblUser = new JLabel("USER");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelUser.add(lblUser);
		
		JLabel lblIconUser = new JLabel("");
		lblIconUser.setIcon(new ImageIcon("D:\\Code\\Demo\\src\\main\\java\\icon\\icons8_user_20px_1.png"));
		panelUser.add(lblIconUser, BorderLayout.WEST);
		//content
		JPanel content = new JPanel();
		contentPane.add(content, BorderLayout.CENTER);
		content.setLayout(new BorderLayout(0, 0));
		
		JPanel header = new JPanel();
		header.setBackground(new Color(69, 96, 115));
		content.add(header, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Quản lý báo cáo");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
		
		//Group của head
		GroupLayout gl_header = new GroupLayout(header);
		gl_header.setHorizontalGroup(
			gl_header.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_header.createSequentialGroup()
					.addGap(34)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(1550, Short.MAX_VALUE))
		);
		gl_header.setVerticalGroup(
			gl_header.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_header.createSequentialGroup()
					.addGap(34)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
		);
		header.setLayout(gl_header);
		//het phan group 1
		
		JPanel cacmuc = new JPanel();
		cacmuc.setBackground(new Color(206, 206, 206));
		content.add(cacmuc, BorderLayout.CENTER);
		
        JButton btnmuc1 = new JButton();
        ImageIcon icon1 = new ImageIcon("D:\\\\\\\\GITHTB_PTUD\\\\\\\\MayHotel_09\\\\\\\\src\\\\\\\\main\\\\\\\\java\\\\\\\\icon\\\\\\\\baocao.png"); // Đường dẫn tới icon
        btnmuc1.setIcon(icon1);
	    btnmuc1.setText("Bảng báo cáo");
	    btnmuc1.setForeground(new Color(243, 125, 0));
	    btnmuc1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		btnmuc1.setHorizontalAlignment(SwingConstants.LEFT);

		
		JButton btnmuc2 = new JButton("Thu chi");
		ImageIcon icon2 = new ImageIcon("D:\\GITHTB_PTUD\\MayHotel_09\\src\\main\\java\\icon\\thuchi.png"); // Đường dẫn tới icon
	    btnmuc2.setIcon(icon2);
		btnmuc2.setForeground(new Color(243, 125, 0));
		btnmuc2.setFont(new Font("Times New Roman", Font.BOLD, 30));
		btnmuc2.setHorizontalAlignment(SwingConstants.LEFT);
		GroupLayout gl_cacmuc = new GroupLayout(cacmuc);
		gl_cacmuc.setHorizontalGroup(
			gl_cacmuc.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_cacmuc.createSequentialGroup()
					.addGap(39)
					.addGroup(gl_cacmuc.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnmuc1, GroupLayout.DEFAULT_SIZE, 1875, Short.MAX_VALUE)
						.addComponent(btnmuc2, GroupLayout.DEFAULT_SIZE, 1875, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_cacmuc.setVerticalGroup(
			gl_cacmuc.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cacmuc.createSequentialGroup()
					.addGap(20)
					.addComponent(btnmuc1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnmuc2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(737, Short.MAX_VALUE))
		);
		cacmuc.setLayout(gl_cacmuc);
	}
	
}
