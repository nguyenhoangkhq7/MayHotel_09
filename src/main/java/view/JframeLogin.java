package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.FlowLayout;

public class JframeLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTaiKhoan;
	private JPasswordField txtMatKhau;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JframeLogin frame = new JframeLogin();
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

	public JframeLogin() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 1319, 852);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		setResizable(false);
		setLocationRelativeTo(null);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelIconLogin = new JPanel();
		panelIconLogin.setBounds(0, 0, 714, 815);
		contentPane.add(panelIconLogin);
		panelIconLogin.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(JframeLogin.class.getResource("/icon/1.jpg")));
		panelIconLogin.add(lblNewLabel);
		
		JPanel panelLogin = new JPanel();
		panelLogin.setBackground(new Color(69, 96, 117));
		panelLogin.setBounds(714, 0, 591, 815);
		contentPane.add(panelLogin);
		panelLogin.setLayout(null);
		
		JLabel lblTitleLogin = new JLabel("Đăng nhập");
		lblTitleLogin.setForeground(new Color(255, 255, 255));
		lblTitleLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleLogin.setFont(new Font("Segoe UI", Font.BOLD, 38));
		lblTitleLogin.setBounds(0, 124, 577, 50);
		panelLogin.add(lblTitleLogin);
		
		JLabel lblUserName = new JLabel("Tài khoản");
		lblUserName.setBackground(new Color(199, 226, 255));
		lblUserName.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblUserName.setForeground(new Color(199, 226, 255));
		lblUserName.setBounds(40, 203, 479, 25);
		panelLogin.add(lblUserName);
		
		JLabel lblLineUserName = new JLabel("_________________________________________________________________");
		lblLineUserName.setForeground(new Color(255, 255, 255));
		lblLineUserName.setBounds(41, 240, 434, 42);
		panelLogin.add(lblLineUserName);
		
		JLabel lblIconUser = new JLabel("");
		lblIconUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconUser.setIcon(new ImageIcon(JframeLogin.class.getResource("/icon/icons8_user_20px_1.png")));
		lblIconUser.setBounds(480, 220, 56, 50);
		panelLogin.add(lblIconUser);
		
		JLabel lblPassword = new JLabel("Mật khẩu");
		lblPassword.setForeground(new Color(199, 226, 255));
		lblPassword.setBackground(new Color(199, 226, 255));
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblPassword.setBounds(40, 297, 423, 30);
		panelLogin.add(lblPassword);
		
		JLabel lblLinePassword = new JLabel("_________________________________________________________________");
		lblLinePassword.setForeground(new Color(255, 255, 255));
		lblLinePassword.setBounds(41, 350, 435, 14);
		panelLogin.add(lblLinePassword);
		
		JLabel disable = new JLabel("");

		disable.setHorizontalAlignment(SwingConstants.CENTER);
		disable.setIcon(new ImageIcon(JframeLogin.class.getResource("/icon/icons8_invisible_20px_1.png")));
		disable.setBounds(480, 327, 49, 37);
		panelLogin.add(disable);
		
		JLabel show = new JLabel("");
		show.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMatKhau.setEchoChar((char) 8226);
				disable.setVisible(true);
				disable.setEnabled(true);
				show.setEnabled(false);
				show.setEnabled(false);
			}
		});
		show.setIcon(new ImageIcon(JframeLogin.class.getResource("/icon/icons8_eye_20px_1.png")));
		show.setHorizontalAlignment(SwingConstants.CENTER);
		show.setBounds(480, 327, 49, 37);
		panelLogin.add(show);
		
		disable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMatKhau.setEchoChar((char) 0);
				disable.setVisible(false);
				disable.setEnabled(false);
				show.setEnabled(true);
				show.setEnabled(true);
			}
		});
		
		
		
		JCheckBox chckbxRememberPassword = new JCheckBox("Nhớ mật khẩu");
		chckbxRememberPassword.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		chckbxRememberPassword.setForeground(new Color(199, 225, 255));
		chckbxRememberPassword.setBackground(new Color(69, 96, 117));
		chckbxRememberPassword.setBounds(50, 392, 156, 42);
		panelLogin.add(chckbxRememberPassword);
		
		JButton btnLogin = new JButton("Đăng nhập");
		btnLogin.setForeground(new Color(243, 125, 0));
		btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		        String Username, Password, query, fname = null, passDb = null;
		        String SUrl, SUser, SPass;
		        SUrl = "jdbc:sqlserver://localhost:1433;databasename=QLKS;encrypt=false";
		        SUser = "sa";
		        SPass = "123456";
		        int notFound = 0;
		        try {
		            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
		            Statement st = con.createStatement();
		            if("".equals(txtTaiKhoan.getText())){
		                JOptionPane.showMessageDialog(new JFrame(), "Tên đăng nhập không được rỗng", "Error",
		                        JOptionPane.ERROR_MESSAGE);
		            }else if("".equals(txtMatKhau.getText())){
		                JOptionPane.showMessageDialog(new JFrame(), "Mật khẩu không được rỗng", "Error",
		                        JOptionPane.ERROR_MESSAGE);
		            }else {
		            Username = txtTaiKhoan.getText();
		            Password = txtMatKhau.getText();
		            
		            query = "SELECT * FROM TaiKhoan WHERE tenTaiKhoan= '"+ Username+"'";
		       
		            ResultSet rs = st.executeQuery(query);
		            while(rs.next()){
		                passDb = rs.getString("matKhau");
		                fname = rs.getString("tenTaiKhoan");
		                notFound = 1;
		            }
		            if(notFound == 1 && Password.equals(passDb)){
		                MainGUI MainFrame = new MainGUI();
		                MainFrame.setUser(fname);
		                MainFrame.setVisible(true);
		                MainFrame.pack();
		                MainFrame.setLocationRelativeTo(null); 
		                if(chckbxRememberPassword.isSelected())
							{
								JOptionPane.showMessageDialog(btnLogin, "Thông tin đăng nhập đã được ghi nhớ");
							}
		                ((JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource())).dispose();
		            }else{
		               JOptionPane.showMessageDialog(new JFrame(), "Tên đăng nhập hoặc mật khẩu không đúng", "Error",
		                        JOptionPane.ERROR_MESSAGE);
		            }
		            txtMatKhau.setText("");
		            
		            }
		        }catch(Exception ex){
		           System.out.println("Error!" + ex.getMessage()); 
		        }
			}
		});
		btnLogin.setBounds(76, 479, 423, 56);
		panelLogin.add(btnLogin);
		
		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setBounds(40, 231, 435, 30);
		panelLogin.add(txtTaiKhoan);
		txtTaiKhoan.setColumns(10);
		
		txtMatKhau = new JPasswordField();
		txtMatKhau.setBounds(40, 327, 435, 30);
		panelLogin.add(txtMatKhau);
		
		JLabel lblExit = new JLabel("X");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblExit.setForeground(new Color(255, 255, 255));
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblExit.setBounds(528, 11, 49, 25);
		panelLogin.add(lblExit);
		
		JLabel lblIconHotel = new JLabel("");
		lblIconHotel.setIcon(new ImageIcon("src/main/java/icon/Microsoft-Fluentui-Emoji-Mono-Hotel.48.png"));
		lblIconHotel.setBounds(265, 11, 49, 42);
		panelLogin.add(lblIconHotel);
		
		JLabel lblHotelName = new JLabel("MAY HOTEL");
		lblHotelName.setHorizontalAlignment(SwingConstants.CENTER);
		lblHotelName.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 41));
		lblHotelName.setBounds(0, 54, 591, 56);
		panelLogin.add(lblHotelName);
	}
	 public static void main1(String[] args) {
		 new JframeLogin();
		}
}

