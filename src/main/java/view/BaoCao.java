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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

public class BaoCao extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JPanel content; // Khai báo JPanel con


	public BaoCao() {
	     setLayout(new BorderLayout());
			
	 	


		
		contentPane = new JPanel();

		contentPane.setLayout(new BorderLayout(0, 0));
		add(contentPane, BorderLayout.CENTER);

		// menu
		//content
		content = new JPanel();
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
        btnmuc1.setFocusPainted(false);
        btnmuc1.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Đặt con trỏ chuột thành dạng tay

	    btnmuc1.setText("Bảng báo cáo");
	    btnmuc1.setForeground(new Color(0, 0, 0));
	    btnmuc1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		btnmuc1.setHorizontalAlignment(SwingConstants.LEFT);
	     // ActionListener for btnmuc1
		  btnmuc1.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                showBangBaoCao(); // Gọi phương thức để hiển thị BangBaoCao
	            }
	        });
		  btnmuc1.addMouseListener(new java.awt.event.MouseAdapter() {
			    @Override
			    public void mouseEntered(MouseEvent evt) {
			        btnmuc1.setForeground(new Color(245, 126, 0)); // Đổi màu chữ thành cam khi di chuột vào
			    }

			    @Override
			    public void mouseExited(MouseEvent evt) {
			        btnmuc1.setForeground(new Color(0, 0, 0)); // Trở về màu đen khi chuột rời đi
			    }
			});

		JButton btnmuc2 = new JButton("Thu chi");
		ImageIcon icon2 = new ImageIcon("D:\\GITHTB_PTUD\\MayHotel_09\\src\\main\\java\\icon\\thuchi.png"); // Đường dẫn tới icon
	    btnmuc2.setIcon(icon2);
	    btnmuc2.setFocusPainted(false);
	    btnmuc2.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Đặt con trỏ chuột thành dạng tay

		btnmuc2.setForeground(new Color(0, 0, 0));
		btnmuc2.setFont(new Font("Times New Roman", Font.BOLD, 30));
		btnmuc2.setHorizontalAlignment(SwingConstants.LEFT);
		
		// Thêm ActionListener cho btnThuChi
		btnmuc2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        showPhieuThuChi(); // Gọi phương thức để hiển thị PhieuThuChi
		    }
		});
		  btnmuc2.addMouseListener(new java.awt.event.MouseAdapter() {
			    @Override
			    public void mouseEntered(MouseEvent evt) {
			        btnmuc2.setForeground(new Color(245, 126, 0)); // Đổi màu chữ thành cam khi di chuột vào
			    }

			    @Override
			    public void mouseExited(MouseEvent evt) {
			        btnmuc2.setForeground(new Color(0, 0, 0)); // Trở về màu đen khi chuột rời đi
			    }
			});

		
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
	// Phương thức để quay lại giao diện chính
    public void showMainContent() {
        contentPane.removeAll(); // Xóa toàn bộ nội dung hiện tại
        contentPane.add(content, BorderLayout.CENTER); // Thêm lại giao diện chính
        contentPane.revalidate(); // Cập nhật layout
        contentPane.repaint(); // Vẽ lại panel
    }
    
    // Phương thức showBangBaoCao, được cập nhật để truyền tham chiếu
    private void showBangBaoCao() {
        BangBaoCao bangBaoCaoPanel = new BangBaoCao(this); // Truyền 'this' để có thể gọi lại BaoCao
        contentPane.removeAll(); 
        contentPane.add(bangBaoCaoPanel, BorderLayout.CENTER);  	  	  
        contentPane.revalidate(); 
        contentPane.repaint(); 
    }
	
	 // Phương thức để hiển thị PhieuThuChi
    private void showPhieuThuChi() {
        PhieuThuChi phieuThuChi = new PhieuThuChi(this); // Tạo đối tượng PhieuThuChi
        contentPane.removeAll(); // Xóa tất cả các thành phần hiện có trong contentPane
        contentPane.add(phieuThuChi, BorderLayout.CENTER); // Thêm contentPane của PhieuThuChi vào
        contentPane.revalidate(); // Cập nhật lại layout
        contentPane.repaint(); // Vẽ lại panel
    }
}
