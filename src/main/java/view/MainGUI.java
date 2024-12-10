package view;

import constant.CommonConstants;
import entity.NhanVien;
import view.panel.MenuPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainGUI extends JFrame {
	private JPanel jpnContainContent;
	private NhanVien nhanVienDangTruc;

	public NhanVien getNhanVienDangTruc() {
		return nhanVienDangTruc;
	}

	public JPanel getJpnContainContent() {
		return jpnContainContent;
	}

	public MainGUI(NhanVien nhanVienDangTruc) {
		this.nhanVienDangTruc = nhanVienDangTruc;
		setupMainFrame();
		jpnContainContent = new JPanel(new BorderLayout());
		this.add(jpnContainContent, BorderLayout.CENTER);
		this.add(new MenuPanel(this ,this.nhanVienDangTruc), BorderLayout.WEST);
	}

	private void setupMainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(1920,1080);
//		setBounds(100, 100, 1440, 900);
	}

	public static void main(String[] args) {
		new MainGUI(new NhanVien());
	}
}
