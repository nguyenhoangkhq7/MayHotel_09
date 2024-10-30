/*
    *MayHotel  day creative: 10/21/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here   
     */


package view;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

// nơi chứa các giao diện khác
public class MainGUI extends JFrame {
	private JPanel JPanelMain;
    public MainGUI() {
    //
  
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		JPanelMain = new JPanel();
		JPanelMain.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(JPanelMain);
		JPanelMain.setLayout(new BorderLayout(0, 0));
		//JPanelMenu
		JPanel JPanelMenu = new JPanel();
		JPanelMenu.setPreferredSize(new Dimension(350, JPanelMenu.getPreferredSize().height));
		JPanelMenu.setBackground(new Color(69, 96, 117));
		JPanelMain.add(JPanelMenu, BorderLayout.WEST);
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
		
		JButton btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.setForeground(new Color(243, 125, 0));
		btnDangXuat.setBackground(new Color(48, 47, 47));
		panelButtonDX.add(btnDangXuat, BorderLayout.EAST);
		
		JLabel lblLine = new JLabel("_________________________________________________");
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
		
		
		//btnManHinhChinh
		JButton btnManHinhChinh = new JButton("Màn hình chính");
		btnManHinhChinh.setIcon(new ImageIcon("D:\\Code\\Demo\\src\\main\\java\\icon\\house.png"));
		btnManHinhChinh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnManHinhChinh.setBackground(new Color(48, 47, 47));
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
		
		
		//btnDonDatPhong
		JButton btnDonDatPhong = new JButton("Đơn đặt phòng");
		btnDonDatPhong.setIcon(new ImageIcon("D:\\Code\\Demo\\src\\main\\java\\icon\\door.png"));
		btnDonDatPhong.setBackground(new Color(48, 47, 47));
		btnDonDatPhong.setForeground(new Color(255, 255, 255));
		btnDonDatPhong.setFont(new Font("Segoe UI", Font.BOLD, 25));
		GridBagConstraints gbc_btnDonDatPhong = new GridBagConstraints();
		gbc_btnDonDatPhong.fill = GridBagConstraints.VERTICAL;
		gbc_btnDonDatPhong.insets = new Insets(0, 5, 60, 5);
		gbc_btnDonDatPhong.gridx = 0;
		gbc_btnDonDatPhong.gridy = 1;
		btnDonDatPhong.setPreferredSize(new Dimension(300, 60));
		panelMenu.add(btnDonDatPhong, gbc_btnDonDatPhong);
		
		
		//btnHoaDon
		JButton btnHoaDon = new JButton("Hóa đơn");
		btnHoaDon.setIcon(new ImageIcon("D:\\Code\\Demo\\src\\main\\java\\icon\\checklist.png"));
		btnHoaDon.setForeground(new Color(255, 255, 255));
		btnHoaDon.setBackground(new Color(48, 47, 47));
		btnHoaDon.setPreferredSize(new Dimension(300, 60));
		btnHoaDon.setFont(new Font("Segoe UI", Font.BOLD, 25));
		GridBagConstraints gbc_btnHoaDon = new GridBagConstraints();
		gbc_btnHoaDon.fill = GridBagConstraints.VERTICAL;
		gbc_btnHoaDon.insets = new Insets(0, 5, 60, 5);
		gbc_btnHoaDon.gridx = 0;
		gbc_btnHoaDon.gridy = 2;
		panelMenu.add(btnHoaDon, gbc_btnHoaDon);
		
		//btnKhachHang
		JButton btnKhachHang = new JButton("Khách hàng");
		btnKhachHang.setIcon(new ImageIcon("D:\\Code\\Demo\\src\\main\\java\\icon\\rating.png"));
		btnKhachHang.setForeground(new Color(255, 255, 255));
		btnKhachHang.setBackground(new Color(48, 47, 47));
		btnKhachHang.setPreferredSize(new Dimension(300, 60));
		btnKhachHang.setFont(new Font("Segoe UI", Font.BOLD, 25));
		GridBagConstraints gbc_btnKhachHang = new GridBagConstraints();
		gbc_btnKhachHang.fill = GridBagConstraints.VERTICAL;
		gbc_btnKhachHang.insets = new Insets(0, 5, 60, 5);
		gbc_btnKhachHang.gridx = 0;
		gbc_btnKhachHang.gridy = 3;
		panelMenu.add(btnKhachHang, gbc_btnKhachHang);
		
		
		//btnBaoCao
		JButton btnBaoCao = new JButton("Báo cáo");
		btnBaoCao.setIcon(new ImageIcon("D:\\Code\\Demo\\src\\main\\java\\icon\\report.png"));
		btnBaoCao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBaoCao.setForeground(new Color(255, 255, 255));
		btnBaoCao.setBackground(new Color(48, 47, 47));
		btnBaoCao.setPreferredSize(new Dimension(300, 60));
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

		setBounds(100, 100, 1440, 900);
		
		
		
		/* Menu dành cho nhân viên quản lý
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		JPanelMain = new JPanel();
		JPanelMain.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(JPanelMain);
		JPanelMain.setLayout(new BorderLayout(0, 0));
		//JPanelMenu
		JPanel JPanelMenu = new JPanel();
		JPanelMenu.setBackground(new Color(69, 96, 117));
		JPanelMain.add(JPanelMenu, BorderLayout.WEST);
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
		btnTrangChu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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
		
		
		//btnPhong
		JButton btnPhong = new JButton("Phòng");
		btnPhong.setBackground(new Color(48, 47, 47));
		btnPhong.setForeground(new Color(255, 255, 255));
		btnPhong.setFont(new Font("Segoe UI", Font.BOLD, 25));
		GridBagConstraints gbc_btnPhong = new GridBagConstraints();
		gbc_btnPhong.fill = GridBagConstraints.VERTICAL;
		gbc_btnPhong.insets = new Insets(0, 5, 60, 5);
		gbc_btnPhong.gridx = 0;
		gbc_btnPhong.gridy = 1;
		btnPhong.setPreferredSize(new Dimension(270, 40));
		panelMenu.add(btnPhong, gbc_btnPhong);
		
		
		//btnDichVu
		JButton btnDichVu = new JButton("Dịch vụ");
		btnDichVu.setForeground(new Color(255, 255, 255));
		btnDichVu.setBackground(new Color(48, 47, 47));
		btnDichVu.setPreferredSize(new Dimension(270, 40));
		btnDichVu.setFont(new Font("Segoe UI", Font.BOLD, 25));
		GridBagConstraints gbc_btnDichVu = new GridBagConstraints();
		gbc_btnDichVu.fill = GridBagConstraints.VERTICAL;
		gbc_btnDichVu.insets = new Insets(0, 5, 60, 5);
		gbc_btnDichVu.gridx = 0;
		gbc_btnDichVu.gridy = 2;
		panelMenu.add(btnDichVu, gbc_btnDichVu);
		
		//btnKhuyenMai
		JButton btnKhuyenMai = new JButton("Khuyến mãi");
		btnKhuyenMai.setForeground(new Color(255, 255, 255));
		btnKhuyenMai.setBackground(new Color(48, 47, 47));
		btnKhuyenMai.setPreferredSize(new Dimension(270, 40));
		btnKhuyenMai.setFont(new Font("Segoe UI", Font.BOLD, 25));
		GridBagConstraints gbc_btnKhuyenMai = new GridBagConstraints();
		gbc_btnKhuyenMai.fill = GridBagConstraints.VERTICAL;
		gbc_btnKhuyenMai.insets = new Insets(0, 5, 60, 5);
		gbc_btnKhuyenMai.gridx = 0;
		gbc_btnKhuyenMai.gridy = 3;
		panelMenu.add(btnKhuyenMai, gbc_btnKhuyenMai);
		
		
		//btnNhanVien
		JButton btnNhanVien = new JButton("Nhân viên");
		btnNhanVien.setForeground(new Color(255, 255, 255));
		btnNhanVien.setBackground(new Color(48, 47, 47));
		btnNhanVien.setPreferredSize(new Dimension(270, 40));
		btnNhanVien.setFont(new Font("Segoe UI", Font.BOLD, 25));
		GridBagConstraints gbc_btnNhanVien = new GridBagConstraints();
		gbc_btnNhanVien.fill = GridBagConstraints.VERTICAL;
		gbc_btnNhanVien.insets = new Insets(0, 5, 60, 5);
		gbc_btnNhanVien.gridx = 0;
		gbc_btnNhanVien.gridy = 4;
		panelMenu.add(btnNhanVien, gbc_btnNhanVien);
		
		//btnDoanhThu
		JButton btnDoanhThu = new JButton("Doanh thu");
		btnDoanhThu.setPreferredSize(new Dimension(270, 40));
		btnDoanhThu.setForeground(Color.WHITE);
		btnDoanhThu.setFont(new Font("Segoe UI", Font.BOLD, 25));
		btnDoanhThu.setBackground(new Color(48, 47, 47));
		GridBagConstraints gbc_btnDoanhThu = new GridBagConstraints();
		gbc_btnDoanhThu.insets = new Insets(0, 5, 0, 5);
		gbc_btnDoanhThu.gridx = 0;
		gbc_btnDoanhThu.gridy = 5;
		panelMenu.add(btnDoanhThu, gbc_btnDoanhThu);
		
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
		
		JPanel JPanelContent = new JPanel();
		JPanelMain.add(JPanelContent, BorderLayout.CENTER);
		JPanelContent.setLayout(new CardLayout(0, 0));
		setBounds(100, 100, 1440, 900);
		 */
    }
    public static void main(String[] args) {
		new MainGUI ();
	}
}

