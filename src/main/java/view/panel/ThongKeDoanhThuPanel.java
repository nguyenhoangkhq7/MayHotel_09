package view.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import constant.CommonConstants;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class ThongKeDoanhThuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ThongKeDoanhThuPanel frame = new ThongKeDoanhThuPanel();
//					frame.setVisible(true);
//			        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  // Đặt JFrame ở trạng thái toàn màn hình
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}
	// Phương thức tạo dữ liệu cho biểu đồ doanh thu theo ngày
	private CategoryDataset createDataset() {
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    dataset.addValue(200, "Doanh thu", "Ngày 1");
	    dataset.addValue(300, "Doanh thu", "Ngày 2");
	    dataset.addValue(250, "Doanh thu", "Ngày 3");
	    dataset.addValue(400, "Doanh thu", "Ngày 4");
	    dataset.addValue(500, "Doanh thu", "Ngày 5");
	    dataset.addValue(450, "Doanh thu", "Ngày 6");
	    // Thêm dữ liệu cho các ngày tiếp theo nếu cần
	    return dataset;
	}

	// Phương thức tạo biểu đồ cột với màu nền nhạt
	private JFreeChart createChart(CategoryDataset dataset) {
	    JFreeChart barChart = ChartFactory.createBarChart(
	            "Doanh thu hàng ngày",       // Tiêu đề biểu đồ
	            "Ngày",                      // Nhãn trục X
	            "Doanh thu (VNĐ)",           // Nhãn trục Y
	            dataset                       // Dữ liệu
	    );

	    // Tùy chỉnh màu nền nhạt cho toàn bộ biểu đồ và vùng vẽ
	    CategoryPlot plot = (CategoryPlot) barChart.getPlot();
	    plot.setBackgroundPaint(new Color(217, 217, 217));     // Màu nền nhạt cho vùng vẽ (plot area)
	    
	    // Tùy chỉnh màu sắc cột
	    BarRenderer renderer = (BarRenderer) plot.getRenderer();
	    renderer.setSeriesPaint(0, new Color(0, 123, 255));    // Đặt màu cho cột đầu tiên
	    renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	    renderer.setDefaultItemLabelsVisible(true);

	    return barChart;
	}

	/**
	 * Create the frame.
	 */
	public ThongKeDoanhThuPanel() {
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.add(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		JPanel menu = new JPanel();
		contentPane.add(menu, BorderLayout.WEST);
		
		JPanel content = new JPanel();
		contentPane.add(content, BorderLayout.CENTER);
		content.setLayout(new BorderLayout(0, 0));
		
		
		/////////head	
		JPanel header = new JPanel();
		header.setBorder(new LineBorder(new Color(0, 0, 0)));
		header.setBackground(new Color(69, 96, 115));
		content.add(header, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Thống kê doanh thu");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
		
		JButton btninbaocao = new JButton("Bộ lọc");
		btninbaocao.setForeground(new Color(243, 125, 0));
		btninbaocao.setBackground(Color.WHITE);
        btninbaocao.setBorder(new LineBorder(CommonConstants.ORANGE, 2));
		btninbaocao.setFocusPainted(false);
		btninbaocao.setToolTipText("bấm vào để chọn cách lọc");
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
			gl_header.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_header.createSequentialGroup()
					.addGap(34)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 1606, Short.MAX_VALUE)
					.addComponent(btninbaocao, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(20))
		);
		gl_header.setVerticalGroup(
			gl_header.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_header.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_header.createSequentialGroup()
					.addGap(30)
					.addComponent(btninbaocao, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
					.addGap(20))
		);
		header.setLayout(gl_header);
		//////
		
		//phần nội dung
		JPanel noidung = new JPanel();
		noidung.setBackground(new Color(206, 206, 206));
		content.add(noidung, BorderLayout.CENTER);
		//8 ô thống kê
		JPanel panelhoadonhuy = new JPanel();
		panelhoadonhuy.setBackground(new Color(255, 255, 255));
		JPanel panel_khuyenmai = new JPanel();
		panel_khuyenmai.setBackground(new Color(255, 255, 255));
		JPanel panel_nganhang = new JPanel();
		panel_nganhang.setBackground(new Color(255, 255, 255));
		JPanel panelkhoanchikhac = new JPanel();
		panelkhoanchikhac.setBackground(new Color(255, 255, 255));
		JPanel panelhoadon = new JPanel();
		panelhoadon.setBackground(new Color(255, 255, 255));
		JPanel panel_doanhthu = new JPanel();
		panel_doanhthu.setBackground(new Color(255, 255, 255));
		JPanel panel_tienmat = new JPanel();
		panel_tienmat.setBackground(new Color(255, 255, 255));
		JPanel panelkhoanthukhac = new JPanel();
		panelkhoanthukhac.setBackground(new Color(255, 255, 255));
		// 2 phần thống kê khác
		JPanel paneldoanhthubieudo = new JPanel();
		paneldoanhthubieudo.setBackground(new Color(255, 255, 255));
		JPanel panelphongyeuthich = new JPanel(new BorderLayout());
		panelphongyeuthich.setForeground(new Color(0, 123, 255));
		panelphongyeuthich.setBackground(new Color(255, 255, 255));
		//group của labal 3 phần trên
		GroupLayout gl_noidung = new GroupLayout(noidung);
		gl_noidung.setHorizontalGroup(
			gl_noidung.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_noidung.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_noidung.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_noidung.createSequentialGroup()
							.addComponent(paneldoanhthubieudo, GroupLayout.DEFAULT_SIZE, 1303, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(panelphongyeuthich, GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE))
						.addGroup(gl_noidung.createSequentialGroup()
							.addGroup(gl_noidung.createParallelGroup(Alignment.TRAILING)
								.addComponent(panelhoadonhuy, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
								.addComponent(panelhoadon, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE))
							.addGap(15)
							.addGroup(gl_noidung.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_khuyenmai, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
								.addComponent(panel_doanhthu, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE))
							.addGap(15)
							.addGroup(gl_noidung.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_nganhang, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
								.addComponent(panel_tienmat, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE))
							.addGap(15)
							.addGroup(gl_noidung.createParallelGroup(Alignment.LEADING)
								.addComponent(panelkhoanchikhac, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
								.addComponent(panelkhoanthukhac, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE))))
					.addGap(20))
		);
		gl_noidung.setVerticalGroup(
			gl_noidung.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_noidung.createSequentialGroup()
					.addGap(13)
					.addGroup(gl_noidung.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panelkhoanthukhac, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_tienmat, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_doanhthu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panelhoadon, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
					.addGap(20)
					.addGroup(gl_noidung.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panelkhoanchikhac, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_nganhang, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_khuyenmai, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_noidung.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelhoadonhuy, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
					.addGap(33)
					.addGroup(gl_noidung.createParallelGroup(Alignment.LEADING)
						.addComponent(panelphongyeuthich, GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
						.addComponent(paneldoanhthubieudo, GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		paneldoanhthubieudo.setLayout(new BorderLayout(0, 0));
		// Thêm biểu đồ vào paneldoanhthubieudo trong constructor hoặc khối khởi tạo
		CategoryDataset dataset = createDataset();
		JFreeChart barChart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setBackground(new Color(255, 255, 255));
		chartPanel.setPreferredSize(new Dimension(800, 600)); // Điều chỉnh kích thước nếu cần

		paneldoanhthubieudo.add(chartPanel, BorderLayout.CENTER);
		////Phần phònga yêu thích
		JPanel panelconphongyeuthich = new JPanel();
		panelconphongyeuthich.setPreferredSize(new Dimension(500, 600));

		panelconphongyeuthich.setBackground(new Color(255, 255, 255));
		
		JLabel lblphongyeuthich = new JLabel("Phòng yêu thích");
		lblphongyeuthich.setVerticalAlignment(SwingConstants.BOTTOM);
		lblphongyeuthich.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		JLabel lblgiaithichphongyeuthich = new JLabel("danh sách phòng sử dụng nhiều nhất");
		lblgiaithichphongyeuthich.setVerticalAlignment(SwingConstants.TOP);
		lblgiaithichphongyeuthich.setForeground(new Color(74, 74, 74));
		lblgiaithichphongyeuthich.setFont(new Font("Tahoma", Font.ITALIC, 18));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(217, 217, 217));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(217, 217, 217));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(217, 217, 217));
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(new Color(217, 217, 217));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(217, 217, 217));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(217, 217, 217));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(93, 196, 240));
		
		JPanel panel_4 = new JPanel();
		panel_4.setForeground(new Color(0, 123, 255));
		panel_4.setBackground(new Color(93, 196, 240));
		
		JPanel panel_6 = new JPanel();
		panel_6.setForeground(new Color(0, 123, 255));
		panel_6.setBackground(new Color(93, 196, 240));
		
		JPanel panel_8 = new JPanel();
		panel_8.setForeground(new Color(0, 123, 255));
		panel_8.setBackground(new Color(93, 196, 240));
		
		JPanel panel_9 = new JPanel();
		panel_9.setForeground(new Color(0, 123, 255));
		panel_9.setBackground(new Color(93, 196, 240));
		
		JPanel panel_10 = new JPanel();
		panel_10.setForeground(new Color(0, 123, 255));
		panel_10.setBackground(new Color(93, 196, 240));
		GroupLayout gl_panelconphongyeuthich = new GroupLayout(panelconphongyeuthich);
		gl_panelconphongyeuthich.setHorizontalGroup(
			gl_panelconphongyeuthich.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelconphongyeuthich.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panelconphongyeuthich.createParallelGroup(Alignment.LEADING)
						.addComponent(lblphongyeuthich, GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
						.addComponent(lblgiaithichphongyeuthich, GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE))
					.addGap(10))
				.addGroup(gl_panelconphongyeuthich.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelconphongyeuthich.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2_1, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 413, Short.MAX_VALUE)
					.addGroup(gl_panelconphongyeuthich.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelconphongyeuthich.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(panel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
							.addComponent(panel_8, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(panel_9, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(panel_6, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(panel_10, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
					.addGap(38))
		);
		gl_panelconphongyeuthich.setVerticalGroup(
			gl_panelconphongyeuthich.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelconphongyeuthich.createSequentialGroup()
					.addComponent(lblphongyeuthich, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(lblgiaithichphongyeuthich, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(50)
					.addGroup(gl_panelconphongyeuthich.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panelconphongyeuthich.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelconphongyeuthich.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_panelconphongyeuthich.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelconphongyeuthich.createSequentialGroup()
							.addComponent(panel_2_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelconphongyeuthich.createSequentialGroup()
							.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel_9, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_panelconphongyeuthich.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_10, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(117, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_12 = new JLabel("0");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_12.setForeground(new Color(0, 123, 255));
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_10 = new GroupLayout(panel_10);
		gl_panel_10.setHorizontalGroup(
			gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_12, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_10.setVerticalGroup(
			gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_10.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_12, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_10.setLayout(gl_panel_10);
		
		JLabel lblNewLabel_11 = new JLabel("0");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_11.setForeground(new Color(0, 123, 255));
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_9 = new GroupLayout(panel_9);
		gl_panel_9.setHorizontalGroup(
			gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_9.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_9.setVerticalGroup(
			gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_9.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_11, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_9.setLayout(gl_panel_9);
		
		JLabel lblNewLabel_10 = new JLabel("0");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_10.setForeground(new Color(0, 123, 255));
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_8.setVerticalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_8.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_10, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_8.setLayout(gl_panel_8);
		
		JLabel lblNewLabel_9 = new JLabel("0");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_9.setForeground(new Color(0, 123, 255));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_9, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_6.setLayout(gl_panel_6);
		
		JLabel lblNewLabel_8 = new JLabel("0");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_8.setForeground(new Color(0, 123, 255));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_8, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_4.setLayout(gl_panel_4);
		
		JLabel lblNewLabel_7 = new JLabel("0");
		lblNewLabel_7.setForeground(new Color(0, 123, 255));
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_7, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel_6 = new JLabel("Phòng P7");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_6, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_7.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_6, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_7.setLayout(gl_panel_7);
		
		JLabel lblNewLabel_5 = new JLabel("Phòng P6");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Phòng P4");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_2_1 = new GroupLayout(panel_2_1);
		gl_panel_2_1.setHorizontalGroup(
			gl_panel_2_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_2_1.setVerticalGroup(
			gl_panel_2_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_2_1.setLayout(gl_panel_2_1);
		
		JLabel lblNewLabel_3 = new JLabel("Phòng P3");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblNewLabel_2 = new JLabel("Phòng P2");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_5.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_5.setLayout(gl_panel_5);
		
		JLabel lblNewLabel_1 = new JLabel("Phòng P1");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		panelconphongyeuthich.setLayout(gl_panelconphongyeuthich);
		JScrollPane scrollPane = new JScrollPane(panelconphongyeuthich);
		scrollPane.setBorder(null);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelphongyeuthich.add(scrollPane,BorderLayout.CENTER); // Hoặc container.add(scrollPane);
		/////
		

		////
		JLabel lblkhoanchikhacicon = new JLabel("");
		lblkhoanchikhacicon.setHorizontalAlignment(SwingConstants.CENTER);
		lblkhoanchikhacicon.setIcon(new ImageIcon("D:\\\\GITHTB_PTUD\\\\MayHotel_09\\\\src\\\\main\\\\java\\\\icon\\\\khoanchikhac.png"));

		
		JLabel lbltenkhoanchikhac = new JLabel("Khoản chi khác");
		lbltenkhoanchikhac.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbltenkhoanchikhac.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JLabel lblgiatrikhoanchikhac = new JLabel("10000000");
		lblgiatrikhoanchikhac.setForeground(new Color(236, 160, 47));
		lblgiatrikhoanchikhac.setFont(new Font("Times New Roman", Font.BOLD, 30));
		GroupLayout gl_panelkhoanchikhac = new GroupLayout(panelkhoanchikhac);
		gl_panelkhoanchikhac.setHorizontalGroup(
			gl_panelkhoanchikhac.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelkhoanchikhac.createSequentialGroup()
					.addComponent(lblkhoanchikhacicon, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelkhoanchikhac.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbltenkhoanchikhac, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
						.addComponent(lblgiatrikhoanchikhac, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)))
		);
			gl_panelkhoanchikhac.setVerticalGroup(
			gl_panelkhoanchikhac.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelkhoanchikhac.createSequentialGroup()
					.addComponent(lbltenkhoanchikhac, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblgiatrikhoanchikhac, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
				.addComponent(lblkhoanchikhacicon, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
		);
		panelkhoanchikhac.setLayout(gl_panelkhoanchikhac);
		////
		////
		JLabel lblkhoanthukhacicon = new JLabel("");
		lblkhoanthukhacicon.setHorizontalAlignment(SwingConstants.CENTER);
		lblkhoanthukhacicon.setIcon(new ImageIcon("D:\\\\GITHTB_PTUD\\\\MayHotel_09\\\\src\\\\main\\\\java\\\\icon\\\\khoanthukhac.png"));

		
		JLabel lbltenkhoanthukhac = new JLabel("Khoản thu khác");
		lbltenkhoanthukhac.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbltenkhoanthukhac.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JLabel lblgiatrikhoanthukhac = new JLabel("10000000");
		lblgiatrikhoanthukhac.setForeground(new Color(0, 123, 255));
		lblgiatrikhoanthukhac.setFont(new Font("Times New Roman", Font.BOLD, 30));
		GroupLayout gl_panelkhoanthukhac = new GroupLayout(panelkhoanthukhac);
		gl_panelkhoanthukhac.setHorizontalGroup(
			gl_panelkhoanthukhac.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelkhoanthukhac.createSequentialGroup()
					.addComponent(lblkhoanthukhacicon, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelkhoanthukhac.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbltenkhoanthukhac, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
						.addComponent(lblgiatrikhoanthukhac, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)))
		);
			gl_panelkhoanthukhac.setVerticalGroup(
			gl_panelkhoanthukhac.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelkhoanthukhac.createSequentialGroup()
					.addComponent(lbltenkhoanthukhac, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblgiatrikhoanthukhac, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
				.addComponent(lblkhoanthukhacicon, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
		);
		panelkhoanthukhac.setLayout(gl_panelkhoanthukhac);
		////
		///
		JLabel lblnganhangicon = new JLabel("");
		lblnganhangicon.setHorizontalAlignment(SwingConstants.CENTER);
		lblnganhangicon.setIcon(new ImageIcon("D:\\\\GITHTB_PTUD\\\\MayHotel_09\\\\src\\\\main\\\\java\\\\icon\\\\nganhang.png"));

		
		JLabel lbltennganhang = new JLabel("Ngân hàng");
		lbltennganhang.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbltennganhang.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JLabel lblgiatringanhang = new JLabel("10000000");
		lblgiatringanhang.setForeground(new Color(12, 181, 60));
		lblgiatringanhang.setFont(new Font("Times New Roman", Font.BOLD, 30));
		GroupLayout gl_panel_nganhang = new GroupLayout(panel_nganhang);
		gl_panel_nganhang.setHorizontalGroup(
			gl_panel_nganhang.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_nganhang.createSequentialGroup()
					.addComponent(lblnganhangicon, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_nganhang.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbltennganhang, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
						.addComponent(lblgiatringanhang, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)))
		);
			gl_panel_nganhang.setVerticalGroup(
			gl_panel_nganhang.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_nganhang.createSequentialGroup()
					.addComponent(lbltennganhang, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblgiatringanhang, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
				.addComponent(lblnganhangicon, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
		);
		panel_nganhang.setLayout(gl_panel_nganhang);
		////
		///
		JLabel lbltienmaticon = new JLabel("");
		lbltienmaticon.setHorizontalAlignment(SwingConstants.CENTER);
		lbltienmaticon.setIcon(new ImageIcon("D:\\\\GITHTB_PTUD\\\\MayHotel_09\\\\src\\\\main\\\\java\\\\icon\\\\tienmat.png"));

		
		JLabel lbltentienmat = new JLabel("Tiền mặt");
		lbltentienmat.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbltentienmat.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JLabel lblgiatritienmat = new JLabel("10000000");
		lblgiatritienmat.setForeground(new Color(12, 181, 60));
		lblgiatritienmat.setFont(new Font("Times New Roman", Font.BOLD, 30));
		GroupLayout gl_panel_tienmat = new GroupLayout(panel_tienmat);
		gl_panel_tienmat.setHorizontalGroup(
			gl_panel_tienmat.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_tienmat.createSequentialGroup()
					.addComponent(lbltienmaticon, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_tienmat.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbltentienmat, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
						.addComponent(lblgiatritienmat, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)))
		);
			gl_panel_tienmat.setVerticalGroup(
			gl_panel_tienmat.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_tienmat.createSequentialGroup()
					.addComponent(lbltentienmat, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblgiatritienmat, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
				.addComponent(lbltienmaticon, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
		);
		panel_tienmat.setLayout(gl_panel_tienmat);
		////
		/////
		JLabel lblkhuyenmaiicon = new JLabel("");
		lblkhuyenmaiicon.setHorizontalAlignment(SwingConstants.CENTER);
		lblkhuyenmaiicon.setIcon(new ImageIcon("D:\\\\GITHTB_PTUD\\\\MayHotel_09\\\\src\\\\main\\\\java\\\\icon\\\\khuyenmai.png"));

		
		JLabel lbltenkhuyenmai = new JLabel("Khuyến mãi");
		lbltenkhuyenmai.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbltenkhuyenmai.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JLabel lblgiatrikhuyenmai = new JLabel("10000000");
		lblgiatrikhuyenmai.setForeground(new Color(236, 160, 47));
		lblgiatrikhuyenmai.setFont(new Font("Times New Roman", Font.BOLD, 30));
		GroupLayout gl_panel_khuyenmai = new GroupLayout(panel_khuyenmai);
		gl_panel_khuyenmai.setHorizontalGroup(
			gl_panel_khuyenmai.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_khuyenmai.createSequentialGroup()
					.addComponent(lblkhuyenmaiicon, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_khuyenmai.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbltenkhuyenmai, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
						.addComponent(lblgiatrikhuyenmai, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)))
		);
			gl_panel_khuyenmai.setVerticalGroup(
			gl_panel_khuyenmai.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_khuyenmai.createSequentialGroup()
					.addComponent(lbltenkhuyenmai, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblgiatrikhuyenmai, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
				.addComponent(lblkhuyenmaiicon, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
		);
		panel_khuyenmai.setLayout(gl_panel_khuyenmai);
		
		//////
		JLabel lbldoanhthuicon = new JLabel("");
		lbldoanhthuicon.setHorizontalAlignment(SwingConstants.CENTER);
		lbldoanhthuicon.setIcon(new ImageIcon("D:\\\\GITHTB_PTUD\\\\MayHotel_09\\\\src\\\\main\\\\java\\\\icon\\\\doanhthu.png"));

		
		JLabel lbltendoanhthu = new JLabel("Doanh thu");
		lbltendoanhthu.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbltendoanhthu.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JLabel lblgiatridoanhthu = new JLabel("10000000");
		lblgiatridoanhthu.setForeground(new Color(12, 181, 60));
		lblgiatridoanhthu.setFont(new Font("Times New Roman", Font.BOLD, 30));
		GroupLayout gl_panel_doanhthu = new GroupLayout(panel_doanhthu);
		gl_panel_doanhthu.setHorizontalGroup(
			gl_panel_doanhthu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_doanhthu.createSequentialGroup()
					.addComponent(lbldoanhthuicon, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_doanhthu.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbltendoanhthu, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
						.addComponent(lblgiatridoanhthu, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)))
		);
			gl_panel_doanhthu.setVerticalGroup(
			gl_panel_doanhthu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_doanhthu.createSequentialGroup()
					.addComponent(lbltendoanhthu, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblgiatridoanhthu, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
				.addComponent(lbldoanhthuicon, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
		);
		panel_doanhthu.setLayout(gl_panel_doanhthu);
		///////
		
		JLabel lblhoadonhuyicon = new JLabel("");
		lblhoadonhuyicon.setHorizontalAlignment(SwingConstants.CENTER);
		lblhoadonhuyicon.setIcon(new ImageIcon("D:\\\\GITHTB_PTUD\\\\MayHotel_09\\\\src\\\\main\\\\java\\\\icon\\\\hoadonhuy.png"));

		
		JLabel lblhoadonhuy = new JLabel("Hóa đơn hủy");
		lblhoadonhuy.setVerticalAlignment(SwingConstants.BOTTOM);
		lblhoadonhuy.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		JLabel lblgiatrihoadonhuy = new JLabel("0");
		lblgiatrihoadonhuy.setForeground(new Color(250, 49, 36));
		lblgiatrihoadonhuy.setFont(new Font("Times New Roman", Font.BOLD, 30));
		GroupLayout gl_panelhoadonhuy = new GroupLayout(panelhoadonhuy);
		gl_panelhoadonhuy.setHorizontalGroup(
			gl_panelhoadonhuy.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelhoadonhuy.createSequentialGroup()
					.addComponent(lblhoadonhuyicon, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelhoadonhuy.createParallelGroup(Alignment.LEADING)
						.addComponent(lblgiatrihoadonhuy, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
						.addComponent(lblhoadonhuy, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)))
		);
		gl_panelhoadonhuy.setVerticalGroup(
			gl_panelhoadonhuy.createParallelGroup(Alignment.TRAILING)
				.addComponent(lblhoadonhuyicon, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
				.addGroup(gl_panelhoadonhuy.createSequentialGroup()
					.addComponent(lblhoadonhuy, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblgiatrihoadonhuy, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(14))
		);
		panelhoadonhuy.setLayout(gl_panelhoadonhuy);
		//////////
		
		///ô Số hóa đơn
		JLabel lblhoadonicon = new JLabel("");
		lblhoadonicon.setHorizontalAlignment(SwingConstants.CENTER);
		lblhoadonicon.setIcon(new ImageIcon("D:\\\\GITHTB_PTUD\\\\MayHotel_09\\\\src\\\\main\\\\java\\\\icon\\\\hoadon.png"));

		JLabel lbltenohoadon = new JLabel("Hóa đơn");
		lbltenohoadon.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbltenohoadon.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JLabel lblgiatrihoadon = new JLabel("35");
		lblgiatrihoadon.setForeground(new Color(0, 123, 255));
		lblgiatrihoadon.setFont(new Font("Times New Roman", Font.BOLD, 30));
		GroupLayout gl_panelhoadon = new GroupLayout(panelhoadon);
		gl_panelhoadon.setHorizontalGroup(
			gl_panelhoadon.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelhoadon.createSequentialGroup()
					.addComponent(lblhoadonicon, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelhoadon.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbltenohoadon, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
						.addComponent(lblgiatrihoadon, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)))
		);
		gl_panelhoadon.setVerticalGroup(
			gl_panelhoadon.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelhoadon.createSequentialGroup()
					.addComponent(lbltenohoadon, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblgiatrihoadon, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
				.addComponent(lblhoadonicon, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
		);
		panelhoadon.setLayout(gl_panelhoadon);
		noidung.setLayout(gl_noidung);
	//////////
	
	}
}
