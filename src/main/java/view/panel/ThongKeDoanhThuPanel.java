package view.panel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import constant.CommonConstants;
import view.dialog.BoLocThongKeDoanhThuDialog;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import bus.ThongKeDoanhThuBUS;

import java.util.List;



public class ThongKeDoanhThuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	ThongKeDoanhThuBUS thongKeDoanhThuBUS = new ThongKeDoanhThuBUS();
	private ChartPanel chartPanel;
	private JLabel giaTriHoaDonHuyLabel;
	private JLabel giaTriHoaDonLabel;
	private JLabel giaTriDoanhThuLabel;
	private JLabel giaTriKhuyenMaiLabel;
	private JLabel giaTriTienMatLabel;
	private JLabel giaTriNganHangLabel;
	private JLabel giaTriKhoanThuKhacLabel;
	private JLabel giaTriKhoanChiKhacLabel;
	private LocalDate startDate;
	private LocalDate endDate;
	private JLabel giaTriPhongHang1Label;
	private JLabel giaTriPhongHang2Label;
	private JLabel giaTriPhongHang3Label;
	private JLabel giaTriPhongHang4Label;
	private JLabel giaTriPhongHang5Label;
	private JLabel giaTriPhongHang6Label;
	private JLabel phongHang6Label;
	private JLabel phongHang5Label;
	private JLabel phongHang4Label;
	private JLabel phongHang3Label;
	private JLabel phongHang2Label;
	private JLabel phongHang1Label;

	/**
	 * Create the panel.
	 */
	public ThongKeDoanhThuPanel() {
		setLayout(new BorderLayout(0, 0));

		// Create header panel
		JPanel headerPanel = createHeaderPanel();
		add(headerPanel, BorderLayout.NORTH);

		// Create central panel
		JPanel centralPanel = createCentralPanel();
		add(centralPanel, BorderLayout.CENTER);
		//Update date
		endDate = LocalDate.now();
		startDate = endDate.minusDays(6);
		capNhatDuLieu(startDate, endDate);
	}

	private JPanel createCentralPanel() {
		JPanel centralPanel = new JPanel();
		centralPanel.setBackground(new Color(206, 206, 206));
		//8 ô thống kê
		JPanel hoaDonHuyPanel = new JPanel();
		hoaDonHuyPanel.setBackground(new Color(255, 255, 255));
		JPanel khuyenMaiPanel = new JPanel();
		khuyenMaiPanel.setBackground(new Color(255, 255, 255));
		JPanel nganHangPanel = new JPanel();
		nganHangPanel.setBackground(new Color(255, 255, 255));
		JPanel khoanChiKhacPanel = new JPanel();
		khoanChiKhacPanel.setBackground(new Color(255, 255, 255));
		JPanel hoaDonPanel = new JPanel();
		hoaDonPanel.setBackground(new Color(255, 255, 255));
		JPanel doanhThuPanel = new JPanel();
		doanhThuPanel.setBackground(new Color(255, 255, 255));
		JPanel tienMatPanel = new JPanel();
		tienMatPanel.setBackground(new Color(255, 255, 255));
		JPanel khoanThuKhacPanel = new JPanel();
		khoanThuKhacPanel.setBackground(new Color(255, 255, 255));
		// 2 phần thống kê khác
		JPanel doanhThuBarChartPanel = new JPanel();
		doanhThuBarChartPanel.setBackground(new Color(255, 255, 255));
		JPanel phongYeuThichPanel = new JPanel(new BorderLayout());
		phongYeuThichPanel.setForeground(new Color(0, 123, 255));
		phongYeuThichPanel.setBackground(new Color(255, 255, 255));
		//group của labal 3 phần trên
		GroupLayout gl_centralPanel = new GroupLayout(centralPanel);
		gl_centralPanel.setHorizontalGroup(
				gl_centralPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_centralPanel.createSequentialGroup()
								.addGap(20)
								.addGroup(gl_centralPanel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_centralPanel.createSequentialGroup()
												.addComponent(doanhThuBarChartPanel, GroupLayout.DEFAULT_SIZE, 1303, Short.MAX_VALUE)
												.addGap(18)
												.addComponent(phongYeuThichPanel, GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE))
										.addGroup(gl_centralPanel.createSequentialGroup()
												.addGroup(gl_centralPanel.createParallelGroup(Alignment.TRAILING)
														.addComponent(hoaDonHuyPanel, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
														.addComponent(hoaDonPanel, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE))
												.addGap(15)
												.addGroup(gl_centralPanel.createParallelGroup(Alignment.LEADING)
														.addComponent(khuyenMaiPanel, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
														.addComponent(doanhThuPanel, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE))
												.addGap(15)
												.addGroup(gl_centralPanel.createParallelGroup(Alignment.LEADING)
														.addComponent(nganHangPanel, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
														.addComponent(tienMatPanel, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE))
												.addGap(15)
												.addGroup(gl_centralPanel.createParallelGroup(Alignment.LEADING)
														.addComponent(khoanChiKhacPanel, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
														.addComponent(khoanThuKhacPanel, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE))))
								.addGap(20))
		);
		gl_centralPanel.setVerticalGroup(
				gl_centralPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_centralPanel.createSequentialGroup()
								.addGap(13)
								.addGroup(gl_centralPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(khoanThuKhacPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(tienMatPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(doanhThuPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(hoaDonPanel, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
								.addGap(20)
								.addGroup(gl_centralPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(khoanChiKhacPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(nganHangPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(khuyenMaiPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(gl_centralPanel.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(hoaDonHuyPanel, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
								.addGap(33)
								.addGroup(gl_centralPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(phongYeuThichPanel, GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
										.addComponent(doanhThuBarChartPanel, GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE))
								.addContainerGap())
		);

		doanhThuBarChartPanel.setLayout(new BorderLayout(0, 0));
		// Thêm biểu đồ vào paneldoanhthubieudo trong constructor hoặc khối khởi tạo
		// Tạo ngày bắt đầu và kết thúc dưới dạng LocalDate
		startDate = LocalDate.of(2024, 01, 01); //Thời gian mẫu
		endDate = LocalDate.of(2024, 01, 01);
		CategoryDataset dataset = createDataset(startDate,endDate);
		JFreeChart barChart = createChart(dataset);
		chartPanel = new ChartPanel(barChart);
		chartPanel.setBackground(new Color(255, 255, 255));
		chartPanel.setPreferredSize(new Dimension(800, 600));

		doanhThuBarChartPanel.add(chartPanel, BorderLayout.CENTER);
		////Phần phònga yêu thích
		JPanel conPhongYeuThichPanel = new JPanel();
		conPhongYeuThichPanel.setPreferredSize(new Dimension(500, 600));

		conPhongYeuThichPanel.setBackground(new Color(255, 255, 255));

		JLabel phongYeuThichLabel = new JLabel("Phòng yêu thích");
		phongYeuThichLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		phongYeuThichLabel.setFont(new Font("Tahoma", Font.BOLD, 22));

		JLabel chuThichPhongYeuThichLabel = new JLabel("danh sách phòng sử dụng nhiều nhất");
		chuThichPhongYeuThichLabel.setVerticalAlignment(SwingConstants.TOP);
		chuThichPhongYeuThichLabel.setForeground(new Color(74, 74, 74));
		chuThichPhongYeuThichLabel.setFont(new Font("Tahoma", Font.ITALIC, 18));

		JPanel phongHang1Panel = new JPanel();
		phongHang1Panel.setBackground(new Color(217, 217, 217));

		JPanel phongHang3Panel = new JPanel();
		phongHang3Panel.setBackground(new Color(217, 217, 217));

		JPanel phongHang2Panel = new JPanel();
		phongHang2Panel.setBackground(new Color(217, 217, 217));

		JPanel phongHang4Panel = new JPanel();
		phongHang4Panel.setBackground(new Color(217, 217, 217));

		JPanel phongHang5Panel = new JPanel();
		phongHang5Panel.setBackground(new Color(217, 217, 217));

		JPanel phongHang6Panel = new JPanel();
		phongHang6Panel.setBackground(new Color(217, 217, 217));

		JPanel giaTriPhongHang1Panel = new JPanel();
		giaTriPhongHang1Panel.setBackground(new Color(93, 196, 240));

		JPanel giaTriPhongHang2Panel = new JPanel();
		giaTriPhongHang2Panel.setForeground(new Color(0, 123, 255));
		giaTriPhongHang2Panel.setBackground(new Color(93, 196, 240));

		JPanel giaTriPhongHang3Panel = new JPanel();
		giaTriPhongHang3Panel.setForeground(new Color(0, 123, 255));
		giaTriPhongHang3Panel.setBackground(new Color(93, 196, 240));

		JPanel giaTriPhongHang4Panel = new JPanel();
		giaTriPhongHang4Panel.setForeground(new Color(0, 123, 255));
		giaTriPhongHang4Panel.setBackground(new Color(93, 196, 240));

		JPanel giaTriPhongHang5Panel = new JPanel();
		giaTriPhongHang5Panel.setForeground(new Color(0, 123, 255));
		giaTriPhongHang5Panel.setBackground(new Color(93, 196, 240));

		JPanel giaTriPhongHang6Panel = new JPanel();
		giaTriPhongHang6Panel.setForeground(new Color(0, 123, 255));
		giaTriPhongHang6Panel.setBackground(new Color(93, 196, 240));
		GroupLayout gl_conPhongYeuThichPanel = new GroupLayout(conPhongYeuThichPanel);
		gl_conPhongYeuThichPanel.setHorizontalGroup(
				gl_conPhongYeuThichPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_conPhongYeuThichPanel.createSequentialGroup()
								.addGap(10)
								.addGroup(gl_conPhongYeuThichPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(phongYeuThichLabel, GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
										.addComponent(chuThichPhongYeuThichLabel, GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE))
								.addGap(10))
						.addGroup(gl_conPhongYeuThichPanel.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_conPhongYeuThichPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(phongHang1Panel, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
										.addComponent(phongHang2Panel, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
										.addComponent(phongHang3Panel, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
										.addComponent(phongHang4Panel, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
										.addComponent(phongHang5Panel, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
										.addComponent(phongHang6Panel, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED, 413, Short.MAX_VALUE)
								.addGroup(gl_conPhongYeuThichPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_conPhongYeuThichPanel.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(giaTriPhongHang2Panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(giaTriPhongHang1Panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
												.addComponent(giaTriPhongHang4Panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(giaTriPhongHang5Panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(giaTriPhongHang3Panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(giaTriPhongHang6Panel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
								.addGap(38))
		);
		gl_conPhongYeuThichPanel.setVerticalGroup(
				gl_conPhongYeuThichPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_conPhongYeuThichPanel.createSequentialGroup()
								.addComponent(phongYeuThichLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(chuThichPhongYeuThichLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addGap(50)
								.addGroup(gl_conPhongYeuThichPanel.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_conPhongYeuThichPanel.createSequentialGroup()
												.addComponent(phongHang1Panel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(phongHang2Panel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(phongHang3Panel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_conPhongYeuThichPanel.createSequentialGroup()
												.addComponent(giaTriPhongHang1Panel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(giaTriPhongHang2Panel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(giaTriPhongHang3Panel, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
								.addGap(18)
								.addGroup(gl_conPhongYeuThichPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_conPhongYeuThichPanel.createSequentialGroup()
												.addComponent(phongHang4Panel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(phongHang5Panel, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_conPhongYeuThichPanel.createSequentialGroup()
												.addComponent(giaTriPhongHang4Panel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(giaTriPhongHang5Panel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)))
								.addGap(18)
								.addGroup(gl_conPhongYeuThichPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(phongHang6Panel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
										.addComponent(giaTriPhongHang6Panel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
								.addContainerGap(117, Short.MAX_VALUE))
		);

		giaTriPhongHang6Label = new JLabel("0");
		giaTriPhongHang6Label.setFont(new Font("Tahoma", Font.BOLD, 22));
		giaTriPhongHang6Label.setForeground(new Color(0, 123, 255));
		giaTriPhongHang6Label.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_giaTriPhongHang6Panel = new GroupLayout(giaTriPhongHang6Panel);
		gl_giaTriPhongHang6Panel.setHorizontalGroup(
				gl_giaTriPhongHang6Panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_giaTriPhongHang6Panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(giaTriPhongHang6Label, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
		);
		gl_giaTriPhongHang6Panel.setVerticalGroup(
				gl_giaTriPhongHang6Panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_giaTriPhongHang6Panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(giaTriPhongHang6Label, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
								.addContainerGap())
		);
		giaTriPhongHang6Panel.setLayout(gl_giaTriPhongHang6Panel);

		giaTriPhongHang5Label = new JLabel("0");
		giaTriPhongHang5Label.setFont(new Font("Tahoma", Font.BOLD, 22));
		giaTriPhongHang5Label.setForeground(new Color(0, 123, 255));
		giaTriPhongHang5Label.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_giaTriPhongHang5Panel = new GroupLayout(giaTriPhongHang5Panel);
		gl_giaTriPhongHang5Panel.setHorizontalGroup(
				gl_giaTriPhongHang5Panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_giaTriPhongHang5Panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(giaTriPhongHang5Label, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
		);
		gl_giaTriPhongHang5Panel.setVerticalGroup(
				gl_giaTriPhongHang5Panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_giaTriPhongHang5Panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(giaTriPhongHang5Label, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
								.addContainerGap())
		);
		giaTriPhongHang5Panel.setLayout(gl_giaTriPhongHang5Panel);

		giaTriPhongHang4Label = new JLabel("0");
		giaTriPhongHang4Label.setFont(new Font("Tahoma", Font.BOLD, 22));
		giaTriPhongHang4Label.setForeground(new Color(0, 123, 255));
		giaTriPhongHang4Label.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_giaTriPhongHang4Panel = new GroupLayout(giaTriPhongHang4Panel);
		gl_giaTriPhongHang4Panel.setHorizontalGroup(
				gl_giaTriPhongHang4Panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_giaTriPhongHang4Panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(giaTriPhongHang4Label, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
		);
		gl_giaTriPhongHang4Panel.setVerticalGroup(
				gl_giaTriPhongHang4Panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_giaTriPhongHang4Panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(giaTriPhongHang4Label, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
								.addContainerGap())
		);
		giaTriPhongHang4Panel.setLayout(gl_giaTriPhongHang4Panel);

		giaTriPhongHang3Label = new JLabel("0");
		giaTriPhongHang3Label.setFont(new Font("Tahoma", Font.BOLD, 22));
		giaTriPhongHang3Label.setForeground(new Color(0, 123, 255));
		giaTriPhongHang3Label.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_giaTriPhongHang3Panel = new GroupLayout(giaTriPhongHang3Panel);
		gl_giaTriPhongHang3Panel.setHorizontalGroup(
				gl_giaTriPhongHang3Panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_giaTriPhongHang3Panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(giaTriPhongHang3Label, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
		);
		gl_giaTriPhongHang3Panel.setVerticalGroup(
				gl_giaTriPhongHang3Panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_giaTriPhongHang3Panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(giaTriPhongHang3Label, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
								.addContainerGap())
		);
		giaTriPhongHang3Panel.setLayout(gl_giaTriPhongHang3Panel);

		giaTriPhongHang2Label = new JLabel("0");
		giaTriPhongHang2Label.setFont(new Font("Tahoma", Font.BOLD, 22));
		giaTriPhongHang2Label.setForeground(new Color(0, 123, 255));
		giaTriPhongHang2Label.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_giaTriPhongHang2Panel = new GroupLayout(giaTriPhongHang2Panel);
		gl_giaTriPhongHang2Panel.setHorizontalGroup(
				gl_giaTriPhongHang2Panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_giaTriPhongHang2Panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(giaTriPhongHang2Label, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
		);
		gl_giaTriPhongHang2Panel.setVerticalGroup(
				gl_giaTriPhongHang2Panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_giaTriPhongHang2Panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(giaTriPhongHang2Label, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
								.addContainerGap())
		);
		giaTriPhongHang2Panel.setLayout(gl_giaTriPhongHang2Panel);

		giaTriPhongHang1Label = new JLabel("0");
		giaTriPhongHang1Label.setForeground(new Color(0, 123, 255));
		giaTriPhongHang1Label.setFont(new Font("Tahoma", Font.BOLD, 22));
		giaTriPhongHang1Label.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_giaTriPhongHang1Panel = new GroupLayout(giaTriPhongHang1Panel);
		gl_giaTriPhongHang1Panel.setHorizontalGroup(
				gl_giaTriPhongHang1Panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_giaTriPhongHang1Panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(giaTriPhongHang1Label, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
		);
		gl_giaTriPhongHang1Panel.setVerticalGroup(
				gl_giaTriPhongHang1Panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_giaTriPhongHang1Panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(giaTriPhongHang1Label, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
								.addContainerGap())
		);
		giaTriPhongHang1Panel.setLayout(gl_giaTriPhongHang1Panel);

		phongHang6Label = new JLabel("Phòng P7");
		phongHang6Label.setFont(new Font("Tahoma", Font.ITALIC, 20));
		phongHang6Label.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_phongHang6Panel = new GroupLayout(phongHang6Panel);
		gl_phongHang6Panel.setHorizontalGroup(
				gl_phongHang6Panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_phongHang6Panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(phongHang6Label, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
								.addContainerGap())
		);
		gl_phongHang6Panel.setVerticalGroup(
				gl_phongHang6Panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_phongHang6Panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(phongHang6Label, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
								.addContainerGap())
		);
		phongHang6Panel.setLayout(gl_phongHang6Panel);

		phongHang5Label = new JLabel("Phòng P6");
		phongHang5Label.setFont(new Font("Tahoma", Font.ITALIC, 20));
		phongHang5Label.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_phongHang5Panel = new GroupLayout(phongHang5Panel);
		gl_phongHang5Panel.setHorizontalGroup(
				gl_phongHang5Panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_phongHang5Panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(phongHang5Label, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
								.addContainerGap())
		);
		gl_phongHang5Panel.setVerticalGroup(
				gl_phongHang5Panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_phongHang5Panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(phongHang5Label, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
								.addContainerGap())
		);
		phongHang5Panel.setLayout(gl_phongHang5Panel);

		phongHang4Label = new JLabel("Phòng P4");
		phongHang4Label.setFont(new Font("Tahoma", Font.ITALIC, 20));
		phongHang4Label.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_phongHang4Panel = new GroupLayout(phongHang4Panel);
		gl_phongHang4Panel.setHorizontalGroup(
				gl_phongHang4Panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_phongHang4Panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(phongHang4Label, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
								.addContainerGap())
		);
		gl_phongHang4Panel.setVerticalGroup(
				gl_phongHang4Panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_phongHang4Panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(phongHang4Label, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
								.addContainerGap())
		);
		phongHang4Panel.setLayout(gl_phongHang4Panel);

		phongHang3Label = new JLabel("Phòng P3");
		phongHang3Label.setFont(new Font("Tahoma", Font.ITALIC, 20));
		phongHang3Label.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_phongHang3Panel = new GroupLayout(phongHang3Panel);
		gl_phongHang3Panel.setHorizontalGroup(
				gl_phongHang3Panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_phongHang3Panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(phongHang3Label, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
								.addContainerGap())
		);
		gl_phongHang3Panel.setVerticalGroup(
				gl_phongHang3Panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_phongHang3Panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(phongHang3Label, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
								.addContainerGap())
		);
		phongHang3Panel.setLayout(gl_phongHang3Panel);

		phongHang2Label = new JLabel("Phòng P2");
		phongHang2Label.setFont(new Font("Tahoma", Font.ITALIC, 20));
		phongHang2Label.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_phongHang2Panel = new GroupLayout(phongHang2Panel);
		gl_phongHang2Panel.setHorizontalGroup(
				gl_phongHang2Panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_phongHang2Panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(phongHang2Label, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
		);
		gl_phongHang2Panel.setVerticalGroup(
				gl_phongHang2Panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_phongHang2Panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(phongHang2Label, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
								.addContainerGap())
		);
		phongHang2Panel.setLayout(gl_phongHang2Panel);

		phongHang1Label = new JLabel("Phòng P1");
		phongHang1Label.setFont(new Font("Tahoma", Font.ITALIC, 20));
		phongHang1Label.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_phongHang1Panel = new GroupLayout(phongHang1Panel);
		gl_phongHang1Panel.setHorizontalGroup(
				gl_phongHang1Panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_phongHang1Panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(phongHang1Label, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_phongHang1Panel.setVerticalGroup(
				gl_phongHang1Panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_phongHang1Panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(phongHang1Label, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
								.addContainerGap())
		);
		phongHang1Panel.setLayout(gl_phongHang1Panel);
		conPhongYeuThichPanel.setLayout(gl_conPhongYeuThichPanel);


		JScrollPane scrollPane = new JScrollPane(conPhongYeuThichPanel);
		scrollPane.setBorder(null);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		phongYeuThichPanel.add(scrollPane,BorderLayout.CENTER); // Hoặc container.add(scrollPane);
		///
		///
		JLabel iconKhoanChiKhacLabel = new JLabel("");
		iconKhoanChiKhacLabel.setHorizontalAlignment(SwingConstants.CENTER);
		iconKhoanChiKhacLabel.setIcon(new ImageIcon("src/main/java/icon/khoanchikhac.png"));

		JLabel khoanChiKhacLabel = new JLabel("Khoản chi khác");
		khoanChiKhacLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		khoanChiKhacLabel.setVerticalAlignment(SwingConstants.BOTTOM);

		giaTriKhoanChiKhacLabel = new JLabel("10000000");
		giaTriKhoanChiKhacLabel.setForeground(new Color(236, 160, 47));
		giaTriKhoanChiKhacLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		GroupLayout gl_khoanChiKhacPanel = new GroupLayout(khoanChiKhacPanel);
		gl_khoanChiKhacPanel.setHorizontalGroup(
				gl_khoanChiKhacPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_khoanChiKhacPanel.createSequentialGroup()
								.addComponent(iconKhoanChiKhacLabel, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_khoanChiKhacPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(khoanChiKhacLabel, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
										.addComponent(giaTriKhoanChiKhacLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)))
		);
		gl_khoanChiKhacPanel.setVerticalGroup(
				gl_khoanChiKhacPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_khoanChiKhacPanel.createSequentialGroup()
								.addComponent(khoanChiKhacLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(giaTriKhoanChiKhacLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(14, Short.MAX_VALUE))
						.addComponent(iconKhoanChiKhacLabel, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
		);
		khoanChiKhacPanel.setLayout(gl_khoanChiKhacPanel);
		////
		////
		JLabel iconKhoanThuKhacLabel = new JLabel("");
		iconKhoanThuKhacLabel.setHorizontalAlignment(SwingConstants.CENTER);
		iconKhoanThuKhacLabel.setIcon(new ImageIcon("src/main/java/icon/khoanthukhac.png"));


		JLabel khoanThuKhacLabel = new JLabel("Khoản thu khác");
		khoanThuKhacLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		khoanThuKhacLabel.setVerticalAlignment(SwingConstants.BOTTOM);

		giaTriKhoanThuKhacLabel = new JLabel("10000000");
		giaTriKhoanThuKhacLabel.setForeground(new Color(0, 123, 255));
		giaTriKhoanThuKhacLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		GroupLayout gl_khoanThuKhacPanel = new GroupLayout(khoanThuKhacPanel);
		gl_khoanThuKhacPanel.setHorizontalGroup(
				gl_khoanThuKhacPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_khoanThuKhacPanel.createSequentialGroup()
								.addComponent(iconKhoanThuKhacLabel, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_khoanThuKhacPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(khoanThuKhacLabel, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
										.addComponent(giaTriKhoanThuKhacLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)))
		);
		gl_khoanThuKhacPanel.setVerticalGroup(
				gl_khoanThuKhacPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_khoanThuKhacPanel.createSequentialGroup()
								.addComponent(khoanThuKhacLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(giaTriKhoanThuKhacLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(14, Short.MAX_VALUE))
						.addComponent(iconKhoanThuKhacLabel, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
		);
		khoanThuKhacPanel.setLayout(gl_khoanThuKhacPanel);
		////
		///
		JLabel iconNganHangLabel = new JLabel("");
		iconNganHangLabel.setHorizontalAlignment(SwingConstants.CENTER);
		iconNganHangLabel.setIcon(new ImageIcon("src/main/java/icon/nganhang.png"));


		JLabel nganHangLabel = new JLabel("Ngân hàng");
		nganHangLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		nganHangLabel.setVerticalAlignment(SwingConstants.BOTTOM);

		giaTriNganHangLabel = new JLabel("10000000");
		giaTriNganHangLabel.setForeground(new Color(12, 181, 60));
		giaTriNganHangLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		GroupLayout gl_nganHangPanel = new GroupLayout(nganHangPanel);
		gl_nganHangPanel.setHorizontalGroup(
				gl_nganHangPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_nganHangPanel.createSequentialGroup()
								.addComponent(iconNganHangLabel, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_nganHangPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(nganHangLabel, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
										.addComponent(giaTriNganHangLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)))
		);
		gl_nganHangPanel.setVerticalGroup(
				gl_nganHangPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_nganHangPanel.createSequentialGroup()
								.addComponent(nganHangLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(giaTriNganHangLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(14, Short.MAX_VALUE))
						.addComponent(iconNganHangLabel, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
		);
		nganHangPanel.setLayout(gl_nganHangPanel);
		////
		///
		JLabel iconTienMatLabel = new JLabel("");
		iconTienMatLabel.setHorizontalAlignment(SwingConstants.CENTER);
		iconTienMatLabel.setIcon(new ImageIcon("src/main/java/icon/tienmat.png"));


		JLabel tienMatLabel = new JLabel("Tiền mặt");
		tienMatLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		tienMatLabel.setVerticalAlignment(SwingConstants.BOTTOM);

		giaTriTienMatLabel = new JLabel("10000000");
		giaTriTienMatLabel.setForeground(new Color(12, 181, 60));
		giaTriTienMatLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		GroupLayout gl_tienMatPanel = new GroupLayout(tienMatPanel);
		gl_tienMatPanel.setHorizontalGroup(
				gl_tienMatPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tienMatPanel.createSequentialGroup()
								.addComponent(iconTienMatLabel, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_tienMatPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(tienMatLabel, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
										.addComponent(giaTriTienMatLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)))
		);
		gl_tienMatPanel.setVerticalGroup(
				gl_tienMatPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tienMatPanel.createSequentialGroup()
								.addComponent(tienMatLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(giaTriTienMatLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(14, Short.MAX_VALUE))
						.addComponent(iconTienMatLabel, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
		);
		tienMatPanel.setLayout(gl_tienMatPanel);
		////
		/////
		JLabel iconKhuyenMaiLabel = new JLabel("");
		iconKhuyenMaiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		iconKhuyenMaiLabel.setIcon(new ImageIcon("src/main/java/icon/khuyenmai.png"));


		JLabel khuyenMaiLabel = new JLabel("Khuyến mãi");
		khuyenMaiLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		khuyenMaiLabel.setVerticalAlignment(SwingConstants.BOTTOM);

		giaTriKhuyenMaiLabel = new JLabel("10000000");
		giaTriKhuyenMaiLabel.setForeground(new Color(236, 160, 47));
		giaTriKhuyenMaiLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		GroupLayout gl_khuyenMaiPanel = new GroupLayout(khuyenMaiPanel);
		gl_khuyenMaiPanel.setHorizontalGroup(
				gl_khuyenMaiPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_khuyenMaiPanel.createSequentialGroup()
								.addComponent(iconKhuyenMaiLabel, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_khuyenMaiPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(khuyenMaiLabel, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
										.addComponent(giaTriKhuyenMaiLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)))
		);
		gl_khuyenMaiPanel.setVerticalGroup(
				gl_khuyenMaiPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_khuyenMaiPanel.createSequentialGroup()
								.addComponent(khuyenMaiLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(giaTriKhuyenMaiLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(14, Short.MAX_VALUE))
						.addComponent(iconKhuyenMaiLabel, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
		);
		khuyenMaiPanel.setLayout(gl_khuyenMaiPanel);

		//////
		JLabel iconDoanhThuLabel = new JLabel("");
		iconDoanhThuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		iconDoanhThuLabel.setIcon(new ImageIcon("src/main/java/icon/doanhthu.png"));


		JLabel doanhThuLabel = new JLabel("Doanh thu");
		doanhThuLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		doanhThuLabel.setVerticalAlignment(SwingConstants.BOTTOM);

		giaTriDoanhThuLabel = new JLabel("10000000");
		giaTriDoanhThuLabel.setForeground(new Color(12, 181, 60));
		giaTriDoanhThuLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		GroupLayout gl_doanhThuPanel = new GroupLayout(doanhThuPanel);
		gl_doanhThuPanel.setHorizontalGroup(
				gl_doanhThuPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_doanhThuPanel.createSequentialGroup()
								.addComponent(iconDoanhThuLabel, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_doanhThuPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(doanhThuLabel, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
										.addComponent(giaTriDoanhThuLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)))
		);
		gl_doanhThuPanel.setVerticalGroup(
				gl_doanhThuPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_doanhThuPanel.createSequentialGroup()
								.addComponent(doanhThuLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(giaTriDoanhThuLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(14, Short.MAX_VALUE))
						.addComponent(iconDoanhThuLabel, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
		);
		doanhThuPanel.setLayout(gl_doanhThuPanel);
		///////

		JLabel iconHoaDonHuyLabel = new JLabel("");
		iconHoaDonHuyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		iconHoaDonHuyLabel.setIcon(new ImageIcon("src/main/java/icon/hoadonhuy.png"));


		JLabel hoaDonHuyLabel = new JLabel("Hóa đơn hủy");
		hoaDonHuyLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		hoaDonHuyLabel.setFont(new Font("Tahoma", Font.BOLD, 24));

		giaTriHoaDonHuyLabel = new JLabel("0");
		giaTriHoaDonHuyLabel.setForeground(new Color(250, 49, 36));
		giaTriHoaDonHuyLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		GroupLayout gl_hoaDonHuyPanel = new GroupLayout(hoaDonHuyPanel);
		gl_hoaDonHuyPanel.setHorizontalGroup(
				gl_hoaDonHuyPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_hoaDonHuyPanel.createSequentialGroup()
								.addComponent(iconHoaDonHuyLabel, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_hoaDonHuyPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(giaTriHoaDonHuyLabel, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
										.addComponent(hoaDonHuyLabel, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)))
		);
		gl_hoaDonHuyPanel.setVerticalGroup(
				gl_hoaDonHuyPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(iconHoaDonHuyLabel, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
						.addGroup(gl_hoaDonHuyPanel.createSequentialGroup()
								.addComponent(hoaDonHuyLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(giaTriHoaDonHuyLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addGap(14))
		);
		hoaDonHuyPanel.setLayout(gl_hoaDonHuyPanel);
		//////////

		///ô Số hóa đơn
		JLabel iconHoaDonLabel = new JLabel("");
		iconHoaDonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		iconHoaDonLabel.setIcon(new ImageIcon("src/main/java/icon/hoadon.png"));

		JLabel hoaDonLabel = new JLabel("Hóa đơn");
		hoaDonLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		hoaDonLabel.setVerticalAlignment(SwingConstants.BOTTOM);

		giaTriHoaDonLabel = new JLabel("35");
		giaTriHoaDonLabel.setForeground(new Color(0, 123, 255));
		giaTriHoaDonLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		GroupLayout gl_hoaDonPanel = new GroupLayout(hoaDonPanel);
		gl_hoaDonPanel.setHorizontalGroup(
				gl_hoaDonPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_hoaDonPanel.createSequentialGroup()
								.addComponent(iconHoaDonLabel, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_hoaDonPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(hoaDonLabel, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
										.addComponent(giaTriHoaDonLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)))
		);
		gl_hoaDonPanel.setVerticalGroup(
				gl_hoaDonPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_hoaDonPanel.createSequentialGroup()
								.addComponent(hoaDonLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(giaTriHoaDonLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(14, Short.MAX_VALUE))
						.addComponent(iconHoaDonLabel, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
		);
		hoaDonPanel.setLayout(gl_hoaDonPanel);
		centralPanel.setLayout(gl_centralPanel);
		//////////

		return centralPanel;
	}
	private JPanel createHeaderPanel() {
		JPanel headerPanel = new JPanel();
		headerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		headerPanel.setBackground(new Color(69, 96, 115));

		JLabel titleLabel = new JLabel("Thống kê doanh thu");
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));

		JButton filterButton = createFilterButton();

		// Layout header
		GroupLayout layout = new GroupLayout(headerPanel);
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
						.addGap(34)
						.addComponent(titleLabel)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 1606, Short.MAX_VALUE)
						.addComponent(filterButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
						.addGap(20)
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup()
								.addGap(20)
								.addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
								.addGap(30)
								.addComponent(filterButton, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
								.addGap(20))
		);
		headerPanel.setLayout(layout);

		return headerPanel;
	}
	/**
	 * Tạo nút lọc.
	 */
	private JButton createFilterButton() {
		JButton filterButton = new JButton("Bộ lọc");
		filterButton.setForeground(new Color(243, 125, 0));
		filterButton.setBackground(Color.WHITE);
		filterButton.setBorder(new LineBorder(CommonConstants.ORANGE, 2));
		filterButton.setFocusPainted(false);
		filterButton.setToolTipText("Bấm vào để chọn cách lọc");
		filterButton.setFont(new Font("Times New Roman", Font.BOLD, 20));

		// Hiệu ứng rê chuột
		filterButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				filterButton.setBackground(new Color(255, 230, 202)); // Đổi màu nền
			}

			@Override
			public void mouseExited(MouseEvent e) {
				filterButton.setBackground(Color.WHITE); // Khôi phục màu nền
			}

		});
		filterButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showFilterDialog();
			}
		});

		return filterButton;
	}
	private void showFilterDialog() {
		BoLocThongKeDoanhThuDialog dialog = new BoLocThongKeDoanhThuDialog();

		// Cài đặt callback để nhận thời gian từ dialog
		dialog.setThoiGianListener((startDate, endDate) -> {
			// Cập nhật dữ liệu với thời gian đã chọn
			if (startDate != null && endDate != null) {

				capNhatDuLieu(startDate, endDate);
			}
		});

		dialog.setBounds(100, 100, 500, 300);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
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

	// Hàm tạo dataset doanh thu
	private CategoryDataset createDataset(LocalDate startDate, LocalDate endDate) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		// Duyệt qua từng ngày từ startDate đến endDate
		LocalDate currentDate = startDate;
		while (!currentDate.isAfter(endDate)) {
			// Lấy ngày hiện tại và chuyển thành chuỗi (ví dụ: "Ngày 1", "Ngày 2", ...)
			String dayLabel = currentDate.getDayOfMonth() + "/" + currentDate.getMonthValue();

			// Mô phỏng dữ liệu doanh thu (Bạn có thể thay thế bằng dữ liệu thực tế từ cơ sở dữ liệu)
			double revenue = thongKeDoanhThuBUS.layDoanhThuTheoNgay(currentDate);  // Truyền LocalDate vào phương thức

			// Thêm dữ liệu vào dataset
			dataset.addValue(revenue, "Doanh thu", dayLabel);

			// Chuyển sang ngày tiếp theo
			currentDate = currentDate.plusDays(1);
		}
		return dataset;
	}
	private void capNhatDuLieu(LocalDate startDate, LocalDate endDate) {

		// Lấy doanh thu từ BUS
		double giaTriHoaDon = thongKeDoanhThuBUS.layGiaTriCacMuc(1, startDate, endDate);
		double giaTriHoaDonHuy = thongKeDoanhThuBUS.layGiaTriCacMuc(2, startDate, endDate);
		double giaTriDoanhThu = thongKeDoanhThuBUS.layGiaTriCacMuc(3, startDate, endDate);
		double giaTriKhuyenMai = thongKeDoanhThuBUS.layGiaTriCacMuc(4, startDate, endDate);
		double giaTriTienMat = thongKeDoanhThuBUS.layGiaTriCacMuc(5, startDate, endDate);
		double giaTriNganHang = thongKeDoanhThuBUS.layGiaTriCacMuc(6, startDate, endDate);
		double giaTriKhoanThuKhac = thongKeDoanhThuBUS.layGiaTriCacMuc(7, startDate, endDate);
		double giaTriKhoanChiKhac = thongKeDoanhThuBUS.layGiaTriCacMuc(8, startDate, endDate);
		List<String> giaTriPhongHang1 = thongKeDoanhThuBUS.layGiaTriPhongTheoHang(1, startDate, endDate);
		List<String> giaTriPhongHang2 = thongKeDoanhThuBUS.layGiaTriPhongTheoHang(2, startDate, endDate);
		List<String> giaTriPhongHang3 = thongKeDoanhThuBUS.layGiaTriPhongTheoHang(3, startDate, endDate);
		List<String> giaTriPhongHang4 = thongKeDoanhThuBUS.layGiaTriPhongTheoHang(4, startDate, endDate);
		List<String> giaTriPhongHang5 = thongKeDoanhThuBUS.layGiaTriPhongTheoHang(5, startDate, endDate);
		List<String> giaTriPhongHang6 = thongKeDoanhThuBUS.layGiaTriPhongTheoHang(6, startDate, endDate);

		// Lấy doanh thu vào barChart
		CategoryDataset dataset = createDataset(startDate, endDate);


		// Cập nhật giá trị cho các JLabel
		giaTriHoaDonLabel.setText(String.format("%.0f", giaTriHoaDon)); // Hiển thị số lượng hóa đơn
		giaTriHoaDonHuyLabel.setText(String.format("%.0f", giaTriHoaDonHuy)); // Hiển thị số lượng hóa đơn hủy
		giaTriDoanhThuLabel.setText(String.format("%.0f", giaTriDoanhThu)); // Hiển thị số lượng hóa đơn hủy
		giaTriKhuyenMaiLabel.setText(String.format("%.0f", giaTriKhuyenMai)); // Hiển thị số lượng hóa đơn hủy
		giaTriTienMatLabel.setText(String.format("%.0f", giaTriTienMat)); // Hiển thị số lượng hóa đơn hủy
		giaTriNganHangLabel.setText(String.format("%.0f", giaTriNganHang)); // Hiển thị số lượng hóa đơn hủy
		giaTriKhoanChiKhacLabel.setText(String.format("%.0f", giaTriKhoanChiKhac)); // Hiển thị số lượng hóa đơn hủy
		giaTriKhoanThuKhacLabel.setText(String.format("%.0f", giaTriKhoanThuKhac)); // Hiển thị số lượng hóa đơn hủy
		//
		phongHang1Label.setText(giaTriPhongHang1.get(0));
		giaTriPhongHang1Label.setText(giaTriPhongHang1.get(1));
		phongHang2Label.setText(giaTriPhongHang2.get(0));
		giaTriPhongHang2Label.setText(giaTriPhongHang2.get(1));
		phongHang3Label.setText(giaTriPhongHang3.get(0));
		giaTriPhongHang3Label.setText(giaTriPhongHang3.get(1));
		phongHang4Label.setText(giaTriPhongHang4.get(0));
		giaTriPhongHang4Label.setText(giaTriPhongHang4.get(1));
		phongHang5Label.setText(giaTriPhongHang5.get(0));
		giaTriPhongHang5Label.setText(giaTriPhongHang5.get(1));
		phongHang6Label.setText(giaTriPhongHang6.get(0));
		giaTriPhongHang6Label.setText(giaTriPhongHang6.get(1));

		//
		JFreeChart barChart = createChart(dataset); // Phương thức tạo biểu đồ
		chartPanel.setChart(barChart); // Gán biểu đồ vào ChartPanel
	}


}
