package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HoaDon extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField maHDs;
    private JTextField tenKHs;
    private JTextField sdtKHs;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                HoaDon frame = new HoaDon();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public HoaDon() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600); // Thay đổi kích thước nếu cần
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Giãn cửa sổ tối đa
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel northPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel westPanel = new JPanel();
        
        // Thiết lập màu nền cho các panel
        mainPanel.setBackground(new Color(211, 211, 211));
        northPanel.setBackground(new Color(69, 96, 117));
        centerPanel.setBackground(new Color(206, 206, 206));
        westPanel.setBackground(new Color(69, 96, 117));

        // Thiết lập kích thước cho các panel
        northPanel.setPreferredSize(new Dimension(800, 100));
        westPanel.setPreferredSize(new Dimension(200, 600));
        
        // Khởi tạo các JTextField
        maHDs = new JTextField(15);
        tenKHs = new JTextField(15);
        sdtKHs = new JTextField(15);

        // Tạo Box chứa các label và text field
        Box box = Box.createVerticalBox();

        // Tạo các JLabel và thêm vào box
        JLabel timKiemGD = new JLabel("Tìm Kiếm Giao Dịch");
        JLabel maHD = new JLabel("Mã hóa đơn");
        JLabel tenKH = new JLabel("Tên Khách Hàng");
        JLabel sdt = new JLabel("Số Điện Thoại");
        
        box.add(timKiemGD);
        box.add(Box.createVerticalStrut(10)); // Khoảng cách giữa các thành phần
        box.add(maHD);
        box.add(maHDs);
        box.add(Box.createVerticalStrut(10));
        box.add(tenKH);
        box.add(tenKHs);
        box.add(Box.createVerticalStrut(10));
        box.add(sdt);
        box.add(sdtKHs);

        // Thêm box vào centerPanel
        centerPanel.add(box);
        
        // Thêm các panel vào mainPanel
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(westPanel, BorderLayout.WEST);

        // Thêm mainPanel vào JFrame
        getContentPane().add(mainPanel);
    }
}
