package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManHinhChinh extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel JPanelMain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManHinhChinh frame = new ManHinhChinh();
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
	public ManHinhChinh() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 1440, 900);
		JPanelMain = new JPanel();
		JPanelMain.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(JPanelMain);
		JPanelMain.setLayout(new BorderLayout(0, 0));

		JPanel JPanelContent = new JPanel();
		JPanelMain.add(JPanelContent, BorderLayout.CENTER);
		JPanelContent.setLayout(new BorderLayout(0, 0));
	
		//panelManHinhChinh
		JPanel panelManHinhChinh = new JPanel();
		JPanelContent.add(panelManHinhChinh);
		panelManHinhChinh.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon("src/main/java/icon/Backround Welcome.png"));
		panelManHinhChinh.add(lblBackground);
		
	}
    public static void main2(String[] args) {
		new ManHinhChinh ();
    }
}
