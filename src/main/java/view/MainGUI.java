/*
    *MayHotel  day creative: 10/21/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here   
     */


package view;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

// nơi chứa các giao diện khác
public class MainGUI extends JFrame {
	private JPanel jpnMain;
	private JPanel jpnMenu;
	private JButton btnDangXuat;
	private JButton btnManHinhChinh;
	private JButton btnDonDatPhong;
	private JButton btnHoaDon;
	private JButton btnKhachHang;
	private JButton btnBaoCao;
	private JButton btnKhuyenMai;
	private JButton btnPhong;
	private JButton btnNhanVien;
	private JButton btnDichVu;
	private JButton btnDoanhThu;
	private JLabel lblUser;
    public MainGUI() {
  
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		jpnMain = new JPanel();
		jpnMain.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(jpnMain);
		jpnMain.setLayout(new BorderLayout(0, 0));
		//JPanelMenu
		jpnMenu = new JPanel();
		jpnMenu.setPreferredSize(new Dimension(350, jpnMenu.getPreferredSize().height));
		jpnMenu.setBackground(new Color(69, 96, 117));
		jpnMain.add(jpnMenu, BorderLayout.WEST);
		jpnMenu.setLayout(new BorderLayout(0, 0));
		
		JPanel panelLogo = new JPanel();
		panelLogo.setBackground(new Color(69, 96, 117));
		jpnMenu.add(panelLogo, BorderLayout.NORTH);
		panelLogo.setLayout(new BorderLayout(0, 0));
		
		JPanel panelIcon = new JPanel();
		panelIcon.setBackground(new Color(69, 96, 117));
		panelLogo.add(panelIcon, BorderLayout.WEST);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon("src/main/java/icon/Microsoft-Fluentui-Emoji-Mono-Hotel.48.png"));
		panelIcon.add(lblIcon);
		
		JPanel panelNameHotel = new JPanel();
		panelNameHotel.setBackground(new Color(69, 96, 117));
		panelLogo.add(panelNameHotel, BorderLayout.CENTER);
		panelNameHotel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblHotelName = new JLabel("MAY HOTEL");
		lblHotelName.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 41));
		panelNameHotel.add(lblHotelName, BorderLayout.NORTH);
		
		JLabel lblSlogan = new JLabel("The best place to be");
		lblSlogan.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 30));
		panelNameHotel.add(lblSlogan, BorderLayout.CENTER);
		
		JPanel panelButtonDX = new JPanel();
		panelButtonDX.setBorder(new EmptyBorder(15, 10, 10, 10));
		panelButtonDX.setBackground(new Color(69, 96, 117));
		panelLogo.add(panelButtonDX, BorderLayout.SOUTH);
		panelButtonDX.setLayout(new BorderLayout(0, 0));
		
		btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.setBorderPainted(false);
		btnDangXuat.setFocusPainted(false); 
		btnDangXuat.setForeground(new Color(243, 125, 0));
		btnDangXuat.setBackground(new Color(48, 47, 47));
		panelButtonDX.add(btnDangXuat, BorderLayout.EAST);
		
		JLabel lblLine = new JLabel("_________________________________________________");
		lblLine.setForeground(new Color(192, 192, 192));
		panelButtonDX.add(lblLine, BorderLayout.SOUTH);
		
		showMenuNVLT();

//		showMenuNVQL();

		addOtherJPanel();

		setBounds(100, 100, 1920, 1080);
		
    }

	public void addOtherJPanel() {
		CardLayout cardLayout = new CardLayout();
		JPanel jpnContent = new JPanel(cardLayout);

		jpnMain.add(jpnContent, BorderLayout.CENTER);

//		các gui
		JPanel themDonDatPhongGUI = new ThemDonDatPhongGUI();
		JPanel donDatPhongGUI = new DonDatPhongGUI();
		JPanel manHinHChinh = new ManHinhChinh();
		JPanel hoaDonGUI = new HoaDon2();
		JPanel khachHangGUI = new KH2();
		JPanel baoCaoGUI = new BaoCao();

		jpnContent.add(manHinHChinh, "Màn hình chính");
		jpnContent.add(donDatPhongGUI, "Đơn đặt phòng");
		jpnContent.add(themDonDatPhongGUI, "Thêm đơn đặt phòng");
		jpnContent.add(hoaDonGUI, "Hóa đơn");
		jpnContent.add(khachHangGUI, "Khách hàng");
		jpnContent.add(baoCaoGUI, "Báo cáo");

		btnBaoCao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(jpnContent, "Báo cáo");
			}
		});
		btnKhachHang.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(jpnContent, "Khách hàng");
			}
		});
		btnHoaDon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(jpnContent, "Hóa đơn");
			}
		});
		btnManHinhChinh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(jpnContent, "Màn hình chính");
			}
		});

		btnDonDatPhong.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(jpnContent, "Đơn đặt phòng");
			}
		});



		DonDatPhongGUI.btnDatPhong.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(jpnContent, "Thêm đơn đặt phòng");
				System.out.println("Click thêm đơn");
			}
		});

	}

    public void showMenuNVLT()
    {
    	//Panel Menu	
    			JPanel panelMenu = new JPanel();
    			panelMenu.setBackground(new Color(69, 96, 117));
    			jpnMenu.add(panelMenu, BorderLayout.CENTER);
    			GridBagLayout gbl_panelMenu = new GridBagLayout();
    			gbl_panelMenu.columnWidths = new int[] {0, 0, 0, 0, 0, 1};
    			gbl_panelMenu.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
    			gbl_panelMenu.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    			gbl_panelMenu.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    			panelMenu.setLayout(gbl_panelMenu);
    			
    			
    			//btnManHinhChinh
    			btnManHinhChinh = new JButton("Màn hình chính");
    			btnManHinhChinh.setBorderPainted(false);
    			btnManHinhChinh.setFocusPainted(false); 
    			btnManHinhChinh.setIcon(new ImageIcon("src/main/java/icon/house.png"));
    			btnManHinhChinh.addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent e) {
    				}
    			});
    			btnManHinhChinh.setBackground(new Color(74, 74, 74));
    			btnManHinhChinh.setForeground(new Color(255, 255, 255));
    			btnManHinhChinh.setFont(new Font("Segoe UI", Font.BOLD, 25));
    			GridBagConstraints gbc_btnManHinhChinh = new GridBagConstraints();
    			gbc_btnManHinhChinh.fill = GridBagConstraints.VERTICAL;
    			gbc_btnManHinhChinh.insets = new Insets(60, 5, 60, 5);
    			gbc_btnManHinhChinh.weightx = 1.0;
    			gbc_btnManHinhChinh.gridx = 0;
    			gbc_btnManHinhChinh.gridy = 0;
    			btnManHinhChinh.setPreferredSize(new Dimension(300, 60));
    			panelMenu.add(btnManHinhChinh, gbc_btnManHinhChinh);
    			//Sự kiện btn Màn hình chính
    			btnManHinhChinh.addMouseListener(new MouseAdapter() {
    				@Override
    				public void mouseClicked(MouseEvent e) {
    					btnManHinhChinh.setBackground(new Color(48,47,47));
    					btnManHinhChinh.setForeground(new Color(243,125,0));
    					btnDonDatPhong.setBackground(new Color(74,74,74));
    					btnDonDatPhong.setForeground(new Color(255,255,255));
    					btnHoaDon.setBackground(new Color(74,74,74));
    					btnHoaDon.setForeground(new Color(255,255,255));
    					btnKhachHang.setBackground(new Color(74,74,74));
    					btnKhachHang.setForeground(new Color(255,255,255));
    					btnBaoCao.setBackground(new Color(74,74,74));
    					btnBaoCao.setForeground(new Color(255,255,255));
    				}
    			});
    			
    			
    			//btnDonDatPhong
    			btnDonDatPhong = new JButton("Đơn đặt phòng");
    			btnDonDatPhong.setBorderPainted(false);
    			btnDonDatPhong.setFocusPainted(false); 
    			btnDonDatPhong.setIcon(new ImageIcon("src/main/java/icon/door.png"));
    			btnDonDatPhong.setBackground(new Color(74, 74, 74));
    			btnDonDatPhong.setForeground(new Color(255, 255, 255));
    			btnDonDatPhong.setFont(new Font("Segoe UI", Font.BOLD, 25));
    			GridBagConstraints gbc_btnDonDatPhong = new GridBagConstraints();
    			gbc_btnDonDatPhong.fill = GridBagConstraints.VERTICAL;
    			gbc_btnDonDatPhong.insets = new Insets(0, 5, 60, 5);
    			gbc_btnDonDatPhong.gridx = 0;
    			gbc_btnDonDatPhong.gridy = 1;
    			btnDonDatPhong.setPreferredSize(new Dimension(300, 60));
    			panelMenu.add(btnDonDatPhong, gbc_btnDonDatPhong);
    			//Sự kiện btn Đơn đặt phòng
    			btnDonDatPhong.addMouseListener(new MouseAdapter() {
    				@Override
    				public void mouseClicked(MouseEvent e) {
    					btnManHinhChinh.setBackground(new Color(74,74,74));
    					btnManHinhChinh.setForeground(new Color(255,255,255));
    					btnDonDatPhong.setBackground(new Color(48,47,47));
    					btnDonDatPhong.setForeground(new Color(243,125,0));
    					btnHoaDon.setBackground(new Color(74,74,74));
    					btnHoaDon.setForeground(new Color(255,255,255));
    					btnKhachHang.setBackground(new Color(74,74,74));
    					btnKhachHang.setForeground(new Color(255,255,255));
    					btnBaoCao.setBackground(new Color(74,74,74));
    					btnBaoCao.setForeground(new Color(255,255,255));
    				}
    			});
    			
    			
    			//btnHoaDon
    			btnHoaDon = new JButton("Hóa đơn");
    			btnHoaDon.setBorderPainted(false);
    			btnHoaDon.setFocusPainted(false); 
    			btnHoaDon.setIcon(new ImageIcon("src/main/java/icon/checklist.png"));
    			btnHoaDon.setForeground(new Color(255, 255, 255));
    			btnHoaDon.setBackground(new Color(74, 74, 74));
    			btnHoaDon.setPreferredSize(new Dimension(300, 60));
    			btnHoaDon.setFont(new Font("Segoe UI", Font.BOLD, 25));
    			GridBagConstraints gbc_btnHoaDon = new GridBagConstraints();
    			gbc_btnHoaDon.fill = GridBagConstraints.VERTICAL;
    			gbc_btnHoaDon.insets = new Insets(0, 5, 60, 5);
    			gbc_btnHoaDon.gridx = 0;
    			gbc_btnHoaDon.gridy = 2;
    			panelMenu.add(btnHoaDon, gbc_btnHoaDon);
    			//Sự kiện btn Hóa đơn
    			btnHoaDon.addMouseListener(new MouseAdapter() {
    				@Override
    				public void mouseClicked(MouseEvent e) {
    					btnManHinhChinh.setBackground(new Color(74,74,74));
    					btnManHinhChinh.setForeground(new Color(255,255,255));
    					btnDonDatPhong.setBackground(new Color(74,74,74));
    					btnDonDatPhong.setForeground(new Color(255,255,255));
    					btnHoaDon.setBackground(new Color(48,47,47));
    					btnHoaDon.setForeground(new Color(243,125,0));
    					btnKhachHang.setBackground(new Color(74,74,74));
    					btnKhachHang.setForeground(new Color(255,255,255));
    					btnBaoCao.setBackground(new Color(74,74,74));
    					btnBaoCao.setForeground(new Color(255,255,255));
    				}
    			});
    				
    			//btnKhachHang
    			btnKhachHang = new JButton("Khách hàng");
    			btnKhachHang.setBorderPainted(false);
    			btnKhachHang.setFocusPainted(false); 
    			btnKhachHang.setIcon(new ImageIcon("src/main/java/icon/rating.png"));
    			btnKhachHang.setForeground(new Color(255, 255, 255));
    			btnKhachHang.setBackground(new Color(74, 74, 74));
    			btnKhachHang.setPreferredSize(new Dimension(300, 60));
    			btnKhachHang.setFont(new Font("Segoe UI", Font.BOLD, 25));
    			GridBagConstraints gbc_btnKhachHang = new GridBagConstraints();
    			gbc_btnKhachHang.fill = GridBagConstraints.VERTICAL;
    			gbc_btnKhachHang.insets = new Insets(0, 5, 60, 5);
    			gbc_btnKhachHang.gridx = 0;
    			gbc_btnKhachHang.gridy = 3;
    			panelMenu.add(btnKhachHang, gbc_btnKhachHang);
    			//Sự kiện btn Khách hàng
    			btnKhachHang.addMouseListener(new MouseAdapter() {
    				@Override
    				public void mouseClicked(MouseEvent e) {
    					btnManHinhChinh.setBackground(new Color(74,74,74));
    					btnManHinhChinh.setForeground(new Color(255,255,255));
    					btnDonDatPhong.setBackground(new Color(74,74,74));
    					btnDonDatPhong.setForeground(new Color(255,255,255));
    					btnHoaDon.setBackground(new Color(74,74,74));
    					btnHoaDon.setForeground(new Color(255,255,255));
    					btnKhachHang.setBackground(new Color(48,47,47));
    					btnKhachHang.setForeground(new Color(243,125,0));
    					btnBaoCao.setBackground(new Color(74,74,74));
    					btnBaoCao.setForeground(new Color(255,255,255));
    				}
    			});

    			//btnBaoCao
    			btnBaoCao = new JButton("Báo cáo");
    			btnBaoCao.setBorderPainted(false);
    			btnBaoCao.setFocusPainted(false); 
    			btnBaoCao.setIcon(new ImageIcon("D:\\Code\\Demo\\src\\main\\java\\icon\\report.png"));
    			btnBaoCao.addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent e) {
    				}
    			});
    			btnBaoCao.setForeground(new Color(255, 255, 255));
    			btnBaoCao.setBackground(new Color(74, 74, 74));
    			btnBaoCao.setPreferredSize(new Dimension(300, 60));
    			btnBaoCao.setFont(new Font("Segoe UI", Font.BOLD, 25));
    			GridBagConstraints gbc_btnBaoCao = new GridBagConstraints();
    			gbc_btnBaoCao.fill = GridBagConstraints.VERTICAL;
    			gbc_btnBaoCao.insets = new Insets(0, 5, 5, 5);
    			gbc_btnBaoCao.gridx = 0;
    			gbc_btnBaoCao.gridy = 4;
    			panelMenu.add(btnBaoCao, gbc_btnBaoCao);
    			//Sự kiện btn Báo cáo	
    			btnBaoCao.addMouseListener(new MouseAdapter() {
    				@Override
    				public void mouseClicked(MouseEvent e) {
    					btnManHinhChinh.setBackground(new Color(74,74,74));
    					btnManHinhChinh.setForeground(new Color(255,255,255));
    					btnDonDatPhong.setBackground(new Color(74,74,74));
    					btnDonDatPhong.setForeground(new Color(255,255,255));
    					btnHoaDon.setBackground(new Color(74,74,74));
    					btnHoaDon.setForeground(new Color(255,255,255));
    					btnKhachHang.setBackground(new Color(74,74,74));
    					btnKhachHang.setForeground(new Color(255,255,255));
    					btnBaoCao.setBackground(new Color(48,47,47));
    					btnBaoCao.setForeground(new Color(243,125,0));
    				}
    			});
    			//panelUser
    			JPanel panelUser = new JPanel();
    			panelUser.setBackground(new Color(69, 96, 117));
    			jpnMenu.add(panelUser, BorderLayout.SOUTH);
    			panelUser.setLayout(new BorderLayout(0, 0));
    			
    			lblUser = new JLabel("");
    			lblUser.setFont(new Font("Segoe UI", Font.BOLD, 30));
    			panelUser.add(lblUser);
    			
    			JLabel lblIconUser = new JLabel("");
    			lblIconUser.setIcon(new ImageIcon("D:\\Code\\Demo\\src\\main\\java\\icon\\icons8_user_20px_1.png"));
    			panelUser.add(lblIconUser, BorderLayout.WEST);
    			
    			JLabel lblDateTime = new JLabel();
    			lblDateTime.setFont(new Font("Segoe UI", Font.BOLD, 16));
    			lblDateTime.setForeground(new Color(255, 255, 255));
    			lblDateTime.setHorizontalAlignment(SwingConstants.CENTER);
    			panelUser.add(lblDateTime, BorderLayout.SOUTH);
    			
    			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    			
    			// Tạo Timer để cập nhật thời gian
    			Timer timer = new Timer(1000, new ActionListener() {
    			    @Override
    			    public void actionPerformed(ActionEvent e) {
    			        // Lấy thời gian hiện hành và cập nhật JLabel
    			        lblDateTime.setText(sdf.format(new Date()));
    			    }
    			});
    			timer.start();

    }
    public void showMenuNVQL()
    {
    	//Panel Menu	
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(69, 96, 117));
		jpnMenu.add(panelMenu, BorderLayout.CENTER);
		GridBagLayout gbl_panelMenu = new GridBagLayout();
		gbl_panelMenu.columnWidths = new int[] {0, 0, 0, 0, 0, 1};
		gbl_panelMenu.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panelMenu.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelMenu.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelMenu.setLayout(gbl_panelMenu);
		
		
		//btnManHinhChinh
		btnManHinhChinh = new JButton("Màn hình chính");
		btnManHinhChinh.setBorderPainted(false);
		btnManHinhChinh.setFocusPainted(false); 
		btnManHinhChinh.setIcon(new ImageIcon("src/main/java/icon/house.png"));
		btnManHinhChinh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnManHinhChinh.setBackground(new Color(74, 74, 74));
		btnManHinhChinh.setForeground(new Color(255, 255, 255));
		btnManHinhChinh.setFont(new Font("Segoe UI", Font.BOLD, 25));
		GridBagConstraints gbc_btnManHinhChinh = new GridBagConstraints();
		gbc_btnManHinhChinh.fill = GridBagConstraints.VERTICAL;
		gbc_btnManHinhChinh.insets = new Insets(60, 5, 60, 5);
		gbc_btnManHinhChinh.weightx = 1.0;
		gbc_btnManHinhChinh.gridx = 0;
		gbc_btnManHinhChinh.gridy = 0;
		btnManHinhChinh.setPreferredSize(new Dimension(300, 60));
		panelMenu.add(btnManHinhChinh, gbc_btnManHinhChinh);
		//Sự kiện btn Màn hình chính
		btnManHinhChinh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnManHinhChinh.setBackground(new Color(48,47,47));
				btnManHinhChinh.setForeground(new Color(243,125,0));
				btnKhuyenMai.setBackground(new Color(74,74,74));
				btnKhuyenMai.setForeground(new Color(255,255,255));
				btnPhong.setBackground(new Color(74,74,74));
				btnPhong.setForeground(new Color(255,255,255));
				btnNhanVien.setBackground(new Color(74,74,74));
				btnNhanVien.setForeground(new Color(255,255,255));
				btnDichVu.setBackground(new Color(74,74,74));
				btnDichVu.setForeground(new Color(255,255,255));
				btnDoanhThu.setBackground(new Color(74,74,74));
				btnDoanhThu.setForeground(new Color(255,255,255));
				btnHoaDon.setBackground(new Color(74,74,74));
				btnHoaDon.setForeground(new Color(255,255,255));
				btnKhachHang.setBackground(new Color(74,74,74));
				btnKhachHang.setForeground(new Color(255,255,255));

			}
		});
		
		
		//btnKhuyenMai
		btnKhuyenMai = new JButton("Khuyến mãi");
		btnKhuyenMai.setBorderPainted(false);
		btnKhuyenMai.setFocusPainted(false); 
		btnKhuyenMai.setIcon(new ImageIcon("src/main/java/icon/tag.png"));
		btnKhuyenMai.setBackground(new Color(74, 74, 74));
		btnKhuyenMai.setForeground(new Color(255, 255, 255));
		btnKhuyenMai.setFont(new Font("Segoe UI", Font.BOLD, 25));
		GridBagConstraints gbc_btnKhuyenMai = new GridBagConstraints();
		gbc_btnKhuyenMai.fill = GridBagConstraints.VERTICAL;
		gbc_btnKhuyenMai.insets = new Insets(0, 5, 60, 5);
		gbc_btnKhuyenMai.gridx = 0;
		gbc_btnKhuyenMai.gridy = 1;
		btnKhuyenMai.setPreferredSize(new Dimension(300, 60));
		panelMenu.add(btnKhuyenMai, gbc_btnKhuyenMai);
		//Sự kiện btn Khuyến mãi
		btnKhuyenMai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnManHinhChinh.setBackground(new Color(74,74,74));
				btnManHinhChinh.setForeground(new Color(255,255,255));
				btnKhuyenMai.setBackground(new Color(48,47,47));
				btnKhuyenMai.setForeground(new Color(243,125,0));
				btnPhong.setBackground(new Color(74,74,74));
				btnPhong.setForeground(new Color(255,255,255));
				btnNhanVien.setBackground(new Color(74,74,74));
				btnNhanVien.setForeground(new Color(255,255,255));
				btnDichVu.setBackground(new Color(74,74,74));
				btnDichVu.setForeground(new Color(255,255,255));
				btnDoanhThu.setBackground(new Color(74,74,74));
				btnDoanhThu.setForeground(new Color(255,255,255));
				btnHoaDon.setBackground(new Color(74,74,74));
				btnHoaDon.setForeground(new Color(255,255,255));
				btnKhachHang.setBackground(new Color(74,74,74));
				btnKhachHang.setForeground(new Color(255,255,255));
			}
		});
		
		//btnPhong
		btnPhong = new JButton("Phòng");
		btnPhong.setBorderPainted(false);
		btnPhong.setFocusPainted(false); 
		btnPhong.setIcon(new ImageIcon("D:\\Code\\Demo\\src\\main\\java\\icon\\door.png"));
		btnPhong.setBackground(new Color(74, 74, 74));
		btnPhong.setForeground(new Color(255, 255, 255));
		btnPhong.setFont(new Font("Segoe UI", Font.BOLD, 25));
		GridBagConstraints gbc_btnPhong = new GridBagConstraints();
		gbc_btnPhong.fill = GridBagConstraints.VERTICAL;
		gbc_btnPhong.insets = new Insets(0, 5, 60, 5);
		gbc_btnPhong.gridx = 0;
		gbc_btnPhong.gridy = 2;
		btnPhong.setPreferredSize(new Dimension(300, 60));
		panelMenu.add(btnPhong, gbc_btnPhong);
		//Sự kiện btn phòng
		btnPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnManHinhChinh.setBackground(new Color(74,74,74));
				btnManHinhChinh.setForeground(new Color(255,255,255));
				btnKhuyenMai.setBackground(new Color(74,74,74));
				btnKhuyenMai.setForeground(new Color(255,255,255));
				btnPhong.setBackground(new Color(48,47,47));
				btnPhong.setForeground(new Color(243,125,0));
				btnNhanVien.setBackground(new Color(74,74,74));
				btnNhanVien.setForeground(new Color(255,255,255));
				btnDichVu.setBackground(new Color(74,74,74));
				btnDichVu.setForeground(new Color(255,255,255));
				btnDoanhThu.setBackground(new Color(74,74,74));
				btnDoanhThu.setForeground(new Color(255,255,255));
				btnHoaDon.setBackground(new Color(74,74,74));
				btnHoaDon.setForeground(new Color(255,255,255));
				btnKhachHang.setBackground(new Color(74,74,74));
				btnKhachHang.setForeground(new Color(255,255,255));
			}
		});
		
		//btnNhanVien
		btnNhanVien = new JButton("Nhân viên");
		btnNhanVien.setBorderPainted(false);
		btnNhanVien.setFocusPainted(false); 
		btnNhanVien.setIcon(new ImageIcon("src/main/java/icon/division.png"));
		btnNhanVien.setBackground(new Color(74, 74, 74));
		btnNhanVien.setForeground(new Color(255, 255, 255));
		btnNhanVien.setFont(new Font("Segoe UI", Font.BOLD, 25));
		GridBagConstraints gbc_btnNhanVien = new GridBagConstraints();
		gbc_btnNhanVien.fill = GridBagConstraints.VERTICAL;
		gbc_btnNhanVien.insets = new Insets(0, 5, 60, 5);
		gbc_btnNhanVien.gridx = 0;
		gbc_btnNhanVien.gridy = 3;
		btnNhanVien.setPreferredSize(new Dimension(300, 60));
		panelMenu.add(btnNhanVien, gbc_btnNhanVien);
		//Sự kiện btn nhân viên
		btnNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnManHinhChinh.setBackground(new Color(74,74,74));
				btnManHinhChinh.setForeground(new Color(255,255,255));
				btnKhuyenMai.setBackground(new Color(74,74,74));
				btnKhuyenMai.setForeground(new Color(255,255,255));
				btnPhong.setBackground(new Color(74,74,74));
				btnPhong.setForeground(new Color(255,255,255));
				btnNhanVien.setBackground(new Color(48,47,47));
				btnNhanVien.setForeground(new Color(243,125,0));
				btnDichVu.setBackground(new Color(74,74,74));
				btnDichVu.setForeground(new Color(255,255,255));
				btnDoanhThu.setBackground(new Color(74,74,74));
				btnDoanhThu.setForeground(new Color(255,255,255));
				btnHoaDon.setBackground(new Color(74,74,74));
				btnHoaDon.setForeground(new Color(255,255,255));
				btnKhachHang.setBackground(new Color(74,74,74));
				btnKhachHang.setForeground(new Color(255,255,255));
			}
		});
		
		//btnDichVu
		btnDichVu = new JButton("Dịch vụ");
		btnDichVu.setBorderPainted(false);
		btnDichVu.setFocusPainted(false); 
		btnDichVu.setIcon(new ImageIcon("src/main/java/icon/diet.png"));
		btnDichVu.setBackground(new Color(74, 74, 74));
		btnDichVu.setForeground(new Color(255, 255, 255));
		btnDichVu.setFont(new Font("Segoe UI", Font.BOLD, 25));
		GridBagConstraints gbc_btnDichVu = new GridBagConstraints();
		gbc_btnDichVu.fill = GridBagConstraints.VERTICAL;
		gbc_btnDichVu.insets = new Insets(0, 5, 60, 5);
		gbc_btnDichVu.gridx = 0;
		gbc_btnDichVu.gridy = 4;
		btnDichVu.setPreferredSize(new Dimension(300, 60));
		panelMenu.add(btnDichVu, gbc_btnDichVu);
		//Sự kiện btn dịch vụ
		btnDichVu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnManHinhChinh.setBackground(new Color(74,74,74));
				btnManHinhChinh.setForeground(new Color(255,255,255));
				btnKhuyenMai.setBackground(new Color(74,74,74));
				btnKhuyenMai.setForeground(new Color(255,255,255));
				btnPhong.setBackground(new Color(74,74,74));
				btnPhong.setForeground(new Color(255,255,255));
				btnNhanVien.setBackground(new Color(74,74,74));
				btnNhanVien.setForeground(new Color(255,255,255));
				btnDichVu.setBackground(new Color(48,47,47));
				btnDichVu.setForeground(new Color(243,125,0));
				btnDoanhThu.setBackground(new Color(74,74,74));
				btnDoanhThu.setForeground(new Color(255,255,255));
				btnHoaDon.setBackground(new Color(74,74,74));
				btnHoaDon.setForeground(new Color(255,255,255));
				btnKhachHang.setBackground(new Color(74,74,74));
				btnKhachHang.setForeground(new Color(255,255,255));
			}
		});
		
		//btnDoanhThu
		btnDoanhThu = new JButton("Doanh thu");
		btnDoanhThu.setBorderPainted(false);
		btnDoanhThu.setFocusPainted(false); 
		btnDoanhThu.setIcon(new ImageIcon("D:\\Code\\Demo\\src\\main\\java\\icon\\door.png"));
		btnDoanhThu.setBackground(new Color(74, 74, 74));
		btnDoanhThu.setForeground(new Color(255, 255, 255));
		btnDoanhThu.setFont(new Font("Segoe UI", Font.BOLD, 25));
		GridBagConstraints gbc_btnDoanhThu = new GridBagConstraints();
		gbc_btnDoanhThu.fill = GridBagConstraints.VERTICAL;
		gbc_btnDoanhThu.insets = new Insets(0, 5, 60, 5);
		gbc_btnDoanhThu.gridx = 0;
		gbc_btnDoanhThu.gridy = 5;
		btnDoanhThu.setPreferredSize(new Dimension(300, 60));
		panelMenu.add(btnNhanVien, gbc_btnDoanhThu);
		//Sự kiện btn doanh thu
		btnDoanhThu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnManHinhChinh.setBackground(new Color(74,74,74));
				btnManHinhChinh.setForeground(new Color(255,255,255));
				btnKhuyenMai.setBackground(new Color(74,74,74));
				btnKhuyenMai.setForeground(new Color(255,255,255));
				btnPhong.setBackground(new Color(74,74,74));
				btnPhong.setForeground(new Color(255,255,255));
				btnNhanVien.setBackground(new Color(74,74,74));
				btnNhanVien.setForeground(new Color(255,255,255));
				btnDichVu.setBackground(new Color(74,74,74));
				btnDichVu.setForeground(new Color(255,255,255));
				btnDoanhThu.setBackground(new Color(48,47,47));
				btnDoanhThu.setForeground(new Color(243,125,0));
				btnHoaDon.setBackground(new Color(74,74,74));
				btnHoaDon.setForeground(new Color(255,255,255));
				btnKhachHang.setBackground(new Color(74,74,74));
				btnKhachHang.setForeground(new Color(255,255,255));
			}
		});			
		
		//btnHoaDon
		btnHoaDon = new JButton("Hóa đơn");
		btnHoaDon.setBorderPainted(false);
		btnHoaDon.setFocusPainted(false); 
		btnHoaDon.setIcon(new ImageIcon("src/main/java/icon/checklist.png"));
		btnHoaDon.setForeground(new Color(255, 255, 255));
		btnHoaDon.setBackground(new Color(74, 74, 74));
		btnHoaDon.setPreferredSize(new Dimension(300, 60));
		btnHoaDon.setFont(new Font("Segoe UI", Font.BOLD, 25));
		GridBagConstraints gbc_btnHoaDon = new GridBagConstraints();
		gbc_btnHoaDon.fill = GridBagConstraints.VERTICAL;
		gbc_btnHoaDon.insets = new Insets(0, 5, 60, 5);
		gbc_btnHoaDon.gridx = 0;
		gbc_btnHoaDon.gridy = 6;
		panelMenu.add(btnHoaDon, gbc_btnHoaDon);
		//Sự kiện btn Hóa đơn
		btnHoaDon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnManHinhChinh.setBackground(new Color(74,74,74));
				btnManHinhChinh.setForeground(new Color(255,255,255));
				btnKhuyenMai.setBackground(new Color(74,74,74));
				btnKhuyenMai.setForeground(new Color(255,255,255));
				btnPhong.setBackground(new Color(74,74,74));
				btnPhong.setForeground(new Color(255,255,255));
				btnNhanVien.setBackground(new Color(74,74,74));
				btnNhanVien.setForeground(new Color(255,255,255));
				btnDichVu.setBackground(new Color(74,74,74));
				btnDichVu.setForeground(new Color(255,255,255));
				btnDoanhThu.setBackground(new Color(74,74,74));
				btnDoanhThu.setForeground(new Color(255,255,255));
				btnHoaDon.setBackground(new Color(48,47,47));
				btnHoaDon.setForeground(new Color(243,125,0));
				btnKhachHang.setBackground(new Color(74,74,74));
				btnKhachHang.setForeground(new Color(255,255,255));
			}
		});
			
		//btnKhachHang
		btnKhachHang = new JButton("Khách hàng");
		btnKhachHang.setBorderPainted(false);
		btnKhachHang.setFocusPainted(false); 
		btnKhachHang.setIcon(new ImageIcon("src/main/java/icon/rating.png"));
		btnKhachHang.setForeground(new Color(255, 255, 255));
		btnKhachHang.setBackground(new Color(74, 74, 74));
		btnKhachHang.setPreferredSize(new Dimension(300, 60));
		btnKhachHang.setFont(new Font("Segoe UI", Font.BOLD, 25));
		GridBagConstraints gbc_btnKhachHang = new GridBagConstraints();
		gbc_btnKhachHang.fill = GridBagConstraints.VERTICAL;
		gbc_btnKhachHang.insets = new Insets(0, 5, 60, 5);
		gbc_btnKhachHang.gridx = 0;
		gbc_btnKhachHang.gridy = 7;
		panelMenu.add(btnKhachHang, gbc_btnKhachHang);
		//Sự kiện btn Khách hàng
		btnKhachHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnManHinhChinh.setBackground(new Color(74,74,74));
				btnManHinhChinh.setForeground(new Color(255,255,255));
				btnKhuyenMai.setBackground(new Color(74,74,74));
				btnKhuyenMai.setForeground(new Color(255,255,255));
				btnPhong.setBackground(new Color(74,74,74));
				btnPhong.setForeground(new Color(255,255,255));
				btnNhanVien.setBackground(new Color(74,74,74));
				btnNhanVien.setForeground(new Color(255,255,255));
				btnDichVu.setBackground(new Color(74,74,74));
				btnDichVu.setForeground(new Color(255,255,255));
				btnDoanhThu.setBackground(new Color(74,74,74));
				btnDoanhThu.setForeground(new Color(255,255,255));
				btnHoaDon.setBackground(new Color(74,74,74));
				btnHoaDon.setForeground(new Color(255,255,255));
				btnKhachHang.setBackground(new Color(48,47,47));
				btnKhachHang.setForeground(new Color(243,125,0));
			}
		});
		//panelUser
		JPanel panelUser = new JPanel();
		panelUser.setBackground(new Color(69, 96, 117));
		jpnMenu.add(panelUser, BorderLayout.SOUTH);
		panelUser.setLayout(new BorderLayout(0, 0));
		
		lblUser = new JLabel("");
		lblUser.setFont(new Font("Segoe UI", Font.BOLD, 30));
		panelUser.add(lblUser);
		

		
		JLabel lblIconUser = new JLabel("");
		lblIconUser.setIcon(new ImageIcon("D:\\Code\\Demo\\src\\main\\java\\icon\\icons8_user_20px_1.png"));
		panelUser.add(lblIconUser, BorderLayout.WEST);
		
		JLabel lblDateTime = new JLabel();
		lblDateTime.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblDateTime.setForeground(new Color(255, 255, 255));
		lblDateTime.setHorizontalAlignment(SwingConstants.CENTER);
		panelUser.add(lblDateTime, BorderLayout.SOUTH);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		// Tạo Timer để cập nhật thời gian
		Timer timer = new Timer(1000, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Lấy thời gian hiện hành và cập nhật JLabel
		        lblDateTime.setText(sdf.format(new Date()));
		    }
		});
		timer.start();
		

    }
    public void setUser(String name) {
        lblUser.setText(name);
    }

    public static void main(String[] args) {
		new MainGUI ();
	}
}

