package view;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class DonDatPhongGUI extends JFrame{
	public DonDatPhongGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		
		JPanel panelMain = new JPanel();
		getContentPane().add(panelMain, BorderLayout.CENTER);
		
		JButton btnNewButton_3 = new JButton("New button");
		panelMain.add(btnNewButton_3);
		
		JButton btnNewButton_2 = new JButton("New button");
		panelMain.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("New button");
		panelMain.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("New button");
		panelMain.add(btnNewButton);
		setVisible(true);
		
		
	}

	public static void main(String[] args) {
		new DonDatPhongGUI();
	}
}
