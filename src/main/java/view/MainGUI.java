package view;

import entity.NhanVien;
import view.panel.MenuPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
		this.add(new MenuPanel(this), BorderLayout.WEST);
	}

	private void setupMainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 1440, 900);

		jpnContainContent = new JPanel(new BorderLayout());
		jpnContainContent.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(jpnContainContent);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	public static void main(String[] args) {
		new MainGUI(new NhanVien());
	}
}
