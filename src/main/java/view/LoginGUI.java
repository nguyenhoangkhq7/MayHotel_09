package view;

import dal.NhanVienDAL;
import database.ConnectDB;
import entity.NhanVien;
import view.dialog.QuenMatKhauDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.awt.FlowLayout;
import java.util.prefs.Preferences;

public class LoginGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtTaiKhoan;
    private JPasswordField txtMatKhau;
    private JCheckBox chckbxRememberPassword;
    private Preferences prefs;

    public LoginGUI() {
        prefs = Preferences.userNodeForPackage(LoginGUI.class);

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
        lblNewLabel.setIcon(new ImageIcon("src/main/java/icon/1.jpg"));
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
        lblIconUser.setIcon(new ImageIcon("src/main/java/icon/icons8_user_20px_1.png"));
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
        disable.setIcon(new ImageIcon("src/main/java/icon/icons8_invisible_20px_1.png"));
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
            }
        });
        show.setIcon(new ImageIcon("src/main/java/icon/icons8_eye_20px_1.png"));
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
            }
        });
        
        chckbxRememberPassword = new JCheckBox("Nhớ mật khẩu");
        chckbxRememberPassword.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        chckbxRememberPassword.setForeground(new Color(199, 225, 255));
        chckbxRememberPassword.setBackground(new Color(69, 96, 117));
        chckbxRememberPassword.setBounds(50, 392, 156, 42);
        panelLogin.add(chckbxRememberPassword);

      
        txtTaiKhoan = new JTextField(prefs.get("savedUsername", ""));
        txtTaiKhoan.setBounds(40, 231, 435, 30);
        panelLogin.add(txtTaiKhoan);
        txtTaiKhoan.setColumns(10);

        txtMatKhau = new JPasswordField(prefs.get("savedPassword", ""));
        txtMatKhau.setBounds(40, 327, 435, 30);
        panelLogin.add(txtMatKhau);

        if (!txtTaiKhoan.getText().isEmpty() && !txtMatKhau.getText().isEmpty()) {
            chckbxRememberPassword.setSelected(false);
        }

        JButton btnLogin = new JButton("Đăng nhập");
        btnLogin.setForeground(new Color(243, 125, 0));
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username, password, query, passDb = null;
                int found = 0;
                try {
                    ConnectDB.getInstance().connect();
                    Connection con = ConnectDB.getConnection();
                    if ("".equals(txtTaiKhoan.getText())) {
                        JOptionPane.showMessageDialog(new JFrame(), "Tên đăng nhập không được rỗng", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else if ("".equals(txtMatKhau.getText())) {
                        JOptionPane.showMessageDialog(new JFrame(), "Mật khẩu không được rỗng", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else { // kiểm tra đúng hay chưa
                        username = txtTaiKhoan.getText();
                        password = txtMatKhau.getText();

                        query = "SELECT * FROM TaiKhoan WHERE tenTaiKhoan = ?";
                        PreparedStatement pstmt = con.prepareStatement(query);
                        pstmt.setString(1, username);
                        ResultSet rs = pstmt.executeQuery();

                        if (rs.next()) {
                            passDb = rs.getString("matKhau");
                            if (passDb != null && passDb.equals(password)) {
                                found = 1;
                            }
                        }

                        if (found == 1) {
                            NhanVienDAL nhanVienDAL = new NhanVienDAL();
                            NhanVien nhanVienDangTruc = nhanVienDAL.getNhanVienTheoTenTaiKhoan(username);
                            MainGUI mainGUI = new MainGUI(nhanVienDangTruc);
                            mainGUI.setVisible(true);
                            mainGUI.pack();
                            mainGUI.setLocationRelativeTo(null);
                            if (chckbxRememberPassword.isSelected()) {
                                prefs.put("savedUsername", username);
                                prefs.put("savedPassword", password);
                                JOptionPane.showMessageDialog(null, "Thông tin đăng nhập đã được ghi nhớ");
                            } else {
                                prefs.remove("savedUsername");
                                prefs.remove("savedPassword");
                            }
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu!");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu");
                    ex.printStackTrace();
                }
            }
        });
        btnLogin.setBounds(41, 465, 435, 42);
        panelLogin.add(btnLogin);
        
        JLabel lblQuenMatKhau = new JLabel("Quên mật khẩu");
        lblQuenMatKhau.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                QuenMatKhauDialog quenMatKhau = new QuenMatKhauDialog(null);
                quenMatKhau.setVisible(true); // Dialog đã tự căn giữa từ constructor.
            }
        });

		lblQuenMatKhau.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblQuenMatKhau.setForeground(new Color(199, 225, 255));
		lblQuenMatKhau.setBounds(335, 402, 149, 23);
		panelLogin.add(lblQuenMatKhau);
    }
}
