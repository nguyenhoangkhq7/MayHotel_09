package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import constraints.CONSTRAINTS;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

public class BangBaoCao extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BangBaoCao frame = new BangBaoCao();
					frame.setVisible(true);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  // Đặt JFrame ở trạng thái toàn màn hình
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private String layThoiGianThongKeBang() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        // Lấy thời gian hiện tại và thời gian bắt đầu của ngày
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfDay = now.toLocalDate().atStartOfDay();

        // Định dạng thời gian để hiển thị
        return startOfDay.format(formatter) + " - " + now.format(formatter);
    }
	/**
	 * Create the frame.
	 */
	public BangBaoCao() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel menu = new JPanel();
		contentPane.add(menu, BorderLayout.WEST);
		
		JPanel content = new JPanel();
		contentPane.add(content, BorderLayout.CENTER);
		content.setLayout(new BorderLayout(0, 0));
		
		
		
		JPanel header = new JPanel();
		header.setBorder(new LineBorder(new Color(0, 0, 0)));
		header.setBackground(new Color(69, 96, 115));
		content.add(header, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Bảng báo cáo");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
		
	/////////head
		JButton btninbaocao = new JButton("In báo cáo");
		btninbaocao.setForeground(new Color(243, 125, 0));
		btninbaocao.setBackground(Color.WHITE);
        btninbaocao.setBorder(new LineBorder(CONSTRAINTS.ORANGE, 2));
		btninbaocao.setFocusPainted(false);
		btninbaocao.setToolTipText("bấm vào để in báo cáo ra file pdf");
		btninbaocao.setFont(new Font("Times New Roman", Font.BOLD, 20));
		 btninbaocao.addMouseListener(new MouseAdapter() {
			 @Override
	            public void mouseEntered(MouseEvent e) {
             // Thêm đường viền và đổi màu nền khi rê chuột vào
             btninbaocao.setBackground(new Color(255,230,202)); // Đổi màu nền khi chuột vào
         }

         @Override
         public void mouseExited(MouseEvent e) {
             // Khôi phục viền và màu nền gốc khi chuột rời đi
             btninbaocao.setBackground(Color.WHITE); // Khôi phục màu nền ban đầu
         }
	          
		 } );
		
		//Group của head
		GroupLayout gl_header = new GroupLayout(header);
		gl_header.setHorizontalGroup(
			gl_header.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_header.createSequentialGroup()
					.addGap(34)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 1494, Short.MAX_VALUE)
					.addComponent(btninbaocao, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(20))
		);
		gl_header.setVerticalGroup(
			gl_header.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_header.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.TRAILING, gl_header.createSequentialGroup()
					.addGap(30)
					.addComponent(btninbaocao, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
					.addGap(20))
		);
		header.setLayout(gl_header);
	
//////////	
		
		
		
	

		JPanel noidung = new JPanel();
		content.add(noidung, BorderLayout.CENTER);
		noidung.setLayout(new BorderLayout(0, 0));
		
////tieu de bang 
		JPanel tieudebang = new JPanel();
		tieudebang.setBackground(new Color(255, 255, 255));
		noidung.add(tieudebang, BorderLayout.NORTH);
		
		JLabel lbtentieudebang = new JLabel("TỔNG KẾT CUỐI NGÀY");
		lbtentieudebang.setBackground(new Color(255, 255, 255));
		lbtentieudebang.setFont(new Font("Times New Roman", Font.BOLD, 32));
		lbtentieudebang.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lbngaythongke = new JLabel(layThoiGianThongKeBang());
		lbngaythongke.setHorizontalAlignment(SwingConstants.CENTER);
		lbngaythongke.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel lbltennguoithongke = new JLabel("Nhân viên: Thái Bảo");
		lbltennguoithongke.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lbltennguoithongke.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_tieudebang = new GroupLayout(tieudebang);
		gl_tieudebang.setHorizontalGroup(
			gl_tieudebang.createParallelGroup(Alignment.LEADING)
				.addComponent(lbtentieudebang, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1996, Short.MAX_VALUE)
				.addComponent(lbngaythongke, GroupLayout.DEFAULT_SIZE, 1996, Short.MAX_VALUE)
				.addComponent(lbltennguoithongke, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1996, Short.MAX_VALUE)
		);
		gl_tieudebang.setVerticalGroup(
			gl_tieudebang.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tieudebang.createSequentialGroup()
					.addComponent(lbtentieudebang, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(lbngaythongke, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbltennguoithongke)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		tieudebang.setLayout(gl_tieudebang);
//////
		
		
///bảng
		JPanel bang = new JPanel();
		noidung.add(bang, BorderLayout.CENTER);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "S\u1ED1 h\u00F3a \u0111\u01A1n", "0"},
				{"2", "S\u1ED1 h\u00F3a \u0111\u01A1n h\u1EE7y", "0"},
				{"3", "T\u1ED5ng doanh thu (\u0111\u00E3 bao g\u1ED3m VAT)", "0"},
				{"4", "VAT", "0"},
				{"5", "Doanh thu ph\u00F2ng", "0"},
				{"6", "D\u1ECBch v\u1EE5", "0"},
				{"7", "Kho\u1EA3n thu kh\u00E1c", "0"},
				{"8", "Ng\u00E2n h\u00E0ng", "0"},
				{"9", "Ti\u1EC1n m\u1EB7t", "0"},
				{"10", "Khuy\u1EBFn m\u00E3i", "0"},
				{"11", "Kho\u1EA3n chi kh\u00E1c", "0"},
				{"12", "T\u1ED5ng doanh thu r\u00F2ng", "0"},
				{"13", "Th\u1EF1c thu", "0"},
				{"14", "S\u1ED1 ph\u00F2ng \u0111\u01B0\u1EE3c thu\u00EA", "0"},
				{"15", "Loai 1", "0"},
				{"16", "Lo\u1EA1i 2", "0"},
				{"17", "Lo\u1EA1i 3", "0"},
				{"18", "Lo\u1EA1i 4", "0"},
				{"19", "Loai 5", "0"},
				{"20", "Lo\u1EA1i 6", "0"},
			},
			new String[] {
				"STT", "C\u00E1c m\u1EE5c", "T\u1ED5ng"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(900);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(900);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(30); // Thay đổi 40 thành chiều cao bạn muốn
		
		// Đặt phông chữ tiêu đề thành in đậm và kích thước 25
        JTableHeader headerbang = table.getTableHeader();
        headerbang.setFont(new Font("Times New Roman", Font.BOLD, 22));
     // Căn giữa cột STT
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Áp dụng cho cột STT (cột 0)
      // Căn giữa cột STT
        DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
        centerRenderer1.setHorizontalAlignment(SwingConstants.RIGHT);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer1); // Áp dụng cho cột STT (cột 0)
     // Tạo renderer in nghiêng cho cột 2
        DefaultTableCellRenderer italicRenderer = new DefaultTableCellRenderer() {
            @Override
            public void setValue(Object value) {
                setFont(new Font("Times New Roman", Font.ITALIC, 18)); // Thiết lập font in nghiêng
                setText(value != null ? value.toString() : "");
            }
        };
        table.getColumnModel().getColumn(1).setCellRenderer(italicRenderer); // Áp dụng cho cột "Các mục" (cột 1)
        
		JScrollPane scrollPane = new JScrollPane(table);
		bang.setLayout(new BorderLayout());
		bang.add(scrollPane, BorderLayout.CENTER);  
		JPanel taolechobang = new JPanel();
		bang.add(taolechobang, BorderLayout.SOUTH);
		JPanel taolechobang1 = new JPanel();
		bang.add(taolechobang1, BorderLayout.EAST);
		JPanel taolechobang2 = new JPanel();
		bang.add(taolechobang2, BorderLayout.WEST);
	}
	/////
}
