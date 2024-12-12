package view.dialog;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import helper.EmailSender;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class QuenMatKhauDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTaiKhoan;
	private JTextField txtEmail;
	private JLabel lblThongBao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    EventQueue.invokeLater(() -> {
	        try {
	            JFrame parent = new JFrame(); // Tạo một JFrame để làm parent
	            QuenMatKhauDialog dialog = new QuenMatKhauDialog(parent);
	            dialog.setVisible(true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    });
	}


	/**
	 * Create the frame.
	 */
	public QuenMatKhauDialog(JFrame parent) {
	    super(parent, "Quên Mật Khẩu", true); // "true" để làm dialog dạng modal
	    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    setBounds(100, 100, 1000, 447);
	    initUI();
	}

private void initUI() {
    contentPane = new JPanel();
    contentPane.setBackground(new Color(69, 96, 117));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

    setResizable(false);
    setLocationRelativeTo(null);

    setContentPane(contentPane);
    contentPane.setLayout(null);

    JLabel labelQuenMatKhau = new JLabel("Quên mật khẩu");
    labelQuenMatKhau.setForeground(new Color(255, 255, 255));
    labelQuenMatKhau.setFont(new Font("Segoe UI", Font.PLAIN, 30));
    labelQuenMatKhau.setBounds(390, 11, 217, 47);
    contentPane.add(labelQuenMatKhau);

    txtTaiKhoan = new JTextField();
    txtTaiKhoan.setBounds(390, 88, 281, 20);
    contentPane.add(txtTaiKhoan);
    txtTaiKhoan.setColumns(10);

    JLabel labelTaiKhoan = new JLabel("Tài khoản:");
    labelTaiKhoan.setForeground(new Color(199, 225, 255));
    labelTaiKhoan.setFont(new Font("Segoe UI", Font.PLAIN, 19));
    labelTaiKhoan.setBounds(290, 86, 90, 20);
    contentPane.add(labelTaiKhoan);

    JButton btnXacNhan = new JButton("Xác nhận");
    btnXacNhan.setForeground(new Color(255, 255, 255));
    btnXacNhan.setBackground(new Color(253, 125, 0));
    btnXacNhan.setBounds(430, 190, 89, 23);
    contentPane.add(btnXacNhan);

    lblThongBao = new JLabel("");
    lblThongBao.setBounds(390, 153, 300, 14);
    contentPane.add(lblThongBao);

    txtEmail = new JTextField();
    txtEmail.setColumns(10);
    txtEmail.setBounds(390, 123, 281, 20);
    contentPane.add(txtEmail);

    JLabel labelEmail = new JLabel("Email:");
    labelEmail.setForeground(new Color(199, 225, 255));
    labelEmail.setFont(new Font("Segoe UI", Font.PLAIN, 19));
    labelEmail.setBounds(290, 121, 55, 20);
    contentPane.add(labelEmail);

    JButton btnClose = new JButton("Đóng");
    btnClose.addActionListener(e -> dispose());
    btnClose.setForeground(Color.WHITE);
    btnClose.setBackground(new Color(253, 125, 0));
    btnClose.setBounds(529, 190, 89, 23);
    contentPane.add(btnClose);

    btnXacNhan.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            handleXacNhan();
        }
    });
}

private void handleXacNhan() {
    String taiKhoan = txtTaiKhoan.getText().trim();
    String email = txtEmail.getText().trim();

    if (taiKhoan.isEmpty() || email.isEmpty()) {
        lblThongBao.setText("Vui lòng nhập đầy đủ thông tin.");
        lblThongBao.setForeground(Color.RED);
        return;
    }

    String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyKS;encrypt=false;user=sa;password=123456";
    try (Connection conn = DriverManager.getConnection(dbURL)) {
        String query = "SELECT tk.matKhau FROM TaiKhoan tk JOIN NhanVien nv ON tk.tenTaiKhoan = nv.tenTaiKhoan WHERE nv.email = ? AND tk.tenTaiKhoan = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, taiKhoan);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String matKhau = rs.getString("matKhau");

                    // Gửi email mật khẩu
                    String subject = "Khôi phục mật khẩu";
                    String content = "Xin chào " + taiKhoan + ",\n\nMật khẩu của bạn là: " + matKhau;
                    EmailSender.sendEmail(email, subject, content);

                    lblThongBao.setText("Mật khẩu đã được gửi về email.");
                    lblThongBao.setForeground(Color.GREEN);
                } else {
                    lblThongBao.setText("Tên tài khoản hoặc email không đúng.");
                    lblThongBao.setForeground(Color.RED);
                }
            }
        }
    } catch (SQLException ex) {
        lblThongBao.setText("Lỗi kết nối cơ sở dữ liệu!");
        lblThongBao.setForeground(Color.RED);
        ex.printStackTrace();
    } catch (MessagingException ex) {
        lblThongBao.setText("Lỗi khi gửi email!");
        lblThongBao.setForeground(Color.RED);
        ex.printStackTrace();
    }
}
}




