package view;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;

public class ManHinhChinh extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel jpnMain;

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
		jpnMain = new JPanel();
		jpnMain.setBorder(new EmptyBorder(0, 0, 0, 0));

		add(jpnMain);
		jpnMain.setLayout(new BorderLayout(0, 0));

		JPanel JPanelContent = new JPanel();
		jpnMain.add(JPanelContent, BorderLayout.CENTER);
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
