package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;

public class Hoa extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField maHDs;
    private JTextField tenKHs;
    private JTextField sdtKHs;
    private JPanel box;
    

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Hoa frame = new Hoa();
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
    public Hoa() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 756, 494);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        JPanel panel = new JPanel(); // Main panel
        JPanel panel_1 = new JPanel(); // North panel
        JPanel panel_2 = new JPanel(); // South panel
        JPanel panel_3 = new JPanel(); // Center panel
        JPanel panel_5 = new JPanel(); // West panel
        JPanel panel_6 = new JPanel(); // Panel 6
        FlowLayout flowLayout = (FlowLayout) panel_6.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        JPanel panel_7 = new JPanel(); // Panel 7
        JPanel panel_8 = new JPanel(); // Panel 8
        JPanel panel_9 = new JPanel(); // Panel 8
        JPanel panel_10 = new JPanel(); // Panel 8
        JPanel top = new JPanel();
        JPanel left = new JPanel();
        JPanel right = new JPanel();
        JPanel bot = new JPanel();
        JPanel spacerPanel = new JPanel();
        
        // Set Color Background
        panel.setBackground(new Color(211, 211, 211));
        panel_3.setBackground(new Color(211, 211, 211));
        panel_1.setBackground(new Color(69, 96, 117));
        panel_5.setBackground(new Color(69, 96, 117));
        
        // Set size for panels
        panel_1.setPreferredSize(new Dimension(200, 200));
        panel_5.setPreferredSize(new Dimension(240, 100));
        panel_6.setPreferredSize(new Dimension(1670, 200));
        panel_7.setPreferredSize(new Dimension(1670, 700));
        panel_9.setPreferredSize(new Dimension(835, 590));
        panel_10.setPreferredSize(new Dimension(835, 590));
        spacerPanel.setPreferredSize(new Dimension(10, 30));
        top.setPreferredSize(new Dimension(10, 30));
        left.setPreferredSize(new Dimension(10, 30));
        right.setPreferredSize(new Dimension(10, 30));
        bot.setPreferredSize(new Dimension(10, 30));
        
        // Set Layout for the main panel
        panel.setLayout(new BorderLayout());
        panel.add(panel_1, BorderLayout.NORTH);
        panel.add(panel_3, BorderLayout.CENTER);
        panel.add(panel_5, BorderLayout.WEST);
        
        // Create a container for panel_6, panel_7, and panel_8
        JPanel panelContainer_1 = new JPanel();
        panelContainer_1.setLayout(new BoxLayout(panelContainer_1, BoxLayout.Y_AXIS));
        
        JPanel panelContainer_2 = new JPanel();
        panelContainer_2.setLayout(new BoxLayout(panelContainer_2, BoxLayout.X_AXIS));
        
     // Initialize text fields
        maHDs = new JTextField(15);
        tenKHs = new JTextField(15);
        sdtKHs = new JTextField(15);
        
        // Create box panel to hold labels and text fields
        box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        
        // Set Color Background for sub-panels
        panel.setBackground(new Color(206,206,206));
        panel_6.setBackground(new Color(255,255,255));
        panel_7.setBackground(new Color(206,206,206));
        panel_9.setBackground(new Color(255,255,255));
        panel_10.setBackground(new Color(255,255,255));
        
        
        top.setBackground(new Color(206,206,206));
        left.setBackground(new Color(206,206,206));
        right.setBackground(new Color(206,206,206));
        bot.setBackground(new Color(206,206,206));
        spacerPanel.setBackground(new Color(206,206,206));

       //ThemThongTin
        JLabel maHD = new JLabel("Mã hóa đơn");
        JLabel tenKH = new JLabel("Tên Khách Hàng");
        JLabel sdtKH = new JLabel("SĐT Khách Hàng");
        // Khởi tạo các JTextField
           maHDs = new JTextField(15);
           tenKHs = new JTextField(15);
           sdtKHs = new JTextField(15);
           
           
           
           
           JPanel line2 = new JPanel();
           line2.add(maHD);
           line2.add(maHDs);
           
           JPanel line3 = new JPanel();
           line3.add(tenKH);
           line3.add(tenKHs);
           
           JPanel line4 = new JPanel();
           line4.add(sdtKH);
           line4.add(sdtKHs);
        
        Box vbox1 = Box.createHorizontalBox();
        panel_6.add(vbox1);

        vbox1.add(Box.createVerticalStrut(5));
        vbox1.add(line2);
        vbox1.add(Box.createVerticalStrut(5));
        vbox1.add(line3);
        vbox1.add(Box.createVerticalStrut(5));
        vbox1.add(line4);
        panel_6.add(box,BorderLayout.CENTER);
        
        
        
        
        
        
     // Tạo Box vertical và thêm các nút vào đó
        
       
        
        
        
        
        // Add the sub-panels to the container
        panelContainer_1.add(panel_6);
        panelContainer_1.add(panel_7);
        panelContainer_2.add(panel_9);
        panelContainer_2.add(spacerPanel);
        panelContainer_2.add(panel_10);
        panelContainer_2.add(right);
        
        // Add the container to panel_3
        panel_3.add(panelContainer_1);
        panel_7.add(panelContainer_2);
        
        // Set border for other panels
        
        // Add the main panel to the JFrame
        getContentPane().add(panel);  
        
        // Optional: Set the background color of the main panel
        panel.setBackground(Color.LIGHT_GRAY);
    }
}
