package view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import constant.CommonConstants;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import bus.BangBaoCaoBUS;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.Cursor;

public class BangBaoCaoPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private JPanel content;
     private JPanel contentPane;
     private JLabel  lbltennguoithongke;
     private JLabel lbngaythongke;
    private BangBaoCaoBUS bangBaoCaoBUS;

    public BangBaoCaoPanel(BaoCaoPanel baoCaoFrame) {
	     setLayout(new BorderLayout());
		
	
 JPanel content = new JPanel();
        content.setLayout(new BorderLayout(0, 0));
        add(content, BorderLayout.CENTER);

		
		
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
        btninbaocao.setBorder(new LineBorder(CommonConstants.ORANGE, 2));
		btninbaocao.setFocusPainted(false);
		btninbaocao.setToolTipText("bấm vào để in báo cáo ra file pdf");
		btninbaocao.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btninbaocao.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Đặt con trỏ chuột thành dạng tay

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
         public void mouseClicked(MouseEvent e) {
             inBaoCao(); // Gọi phương thức in báo cáo khi nhấn nút
         }
	          
		 } );
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon("D:\\\\GITHTB_PTUD\\\\MayHotel_09\\\\src\\\\main\\\\java\\\\icon\\\\back.png"));
		lblNewLabel_1.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Đặt con trỏ chuột thành dạng tay
		lblNewLabel_1.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	                baoCaoFrame.showMainContent(); // Quay về màn hình chính
	            }
	            public void mouseEntered(MouseEvent evt) {
	            	lblNewLabel_1.setIcon(new ImageIcon("D:\\\\GITHTB_PTUD\\\\MayHotel_09\\\\src\\\\main\\\\java\\\\icon\\\\backcam.png")); // Đổi icon sang màu cam khi trỏ vào
	            }

	            public void mouseExited(MouseEvent evt) {
	            	lblNewLabel_1.setIcon(new ImageIcon("D:\\\\GITHTB_PTUD\\\\MayHotel_09\\\\src\\\\main\\\\java\\\\icon\\\\back.png")); // Trở về icon ban đầu khi trỏ ra
	            }
	        });


		//Group của head
		GroupLayout gl_header = new GroupLayout(header);
		gl_header.setHorizontalGroup(
			gl_header.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_header.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 1524, Short.MAX_VALUE)
					.addComponent(btninbaocao, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(20))
		);
		gl_header.setVerticalGroup(
			gl_header.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_header.createSequentialGroup()
					.addContainerGap(32, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_header.createSequentialGroup()
					.addGap(30)
					.addComponent(btninbaocao, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
					.addGap(20))
				.addGroup(Alignment.TRAILING, gl_header.createSequentialGroup()
					.addContainerGap(36, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
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
		
		lbngaythongke = new JLabel(layThoiGianThongKeBang());
		lbngaythongke.setHorizontalAlignment(SwingConstants.CENTER);
		lbngaythongke.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
	    lbltennguoithongke = new JLabel("Nhân viên: Thái Bảo");
		lbltennguoithongke.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lbltennguoithongke.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_tieudebang = new GroupLayout(tieudebang);
		gl_tieudebang.setHorizontalGroup(
			gl_tieudebang.createParallelGroup(Alignment.LEADING)
				.addComponent(lbngaythongke, GroupLayout.DEFAULT_SIZE, 1996, Short.MAX_VALUE)
				.addComponent(lbltennguoithongke, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1996, Short.MAX_VALUE)
				.addComponent(lbtentieudebang, GroupLayout.DEFAULT_SIZE, 1996, Short.MAX_VALUE)
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
		bangBaoCaoBUS = new BangBaoCaoBUS();
		
		// Xác định khoảng thời gian (có thể thay đổi theo yêu cầu)
        LocalDateTime startDate = LocalDateTime.now(); // Ngày bắt đầu
        LocalDateTime endDate = LocalDateTime.now();   // Ngày kết thúc

		Object[][] layDuLieuBang = bangBaoCaoBUS.layDuLieuBang(startDate, endDate);
			
		table.setModel(new DefaultTableModel(
			layDuLieuBang,
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
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(900);
		table.getColumnModel().getColumn(2).setPreferredWidth(900);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(30); // Thay đổi 40 thành chiều cao bạn muốn
		
		// Đặt phông chữ tiêu đề thành in đậm và kích thước 25
        JTableHeader headerbang = table.getTableHeader();
        headerbang.setFont(new Font("Times New Roman", Font.BOLD, 22));
     // Căn giữa cột STT
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Áp dụng cho cột "STT"


     // Tạo renderer căn phải cho cột "Tổng"
     DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
     rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
     table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer); // Áp dụng cho cột "Tổng"


  // Tạo renderer in nghiêng cho cột "Các mục"
  DefaultTableCellRenderer italicRenderer = new DefaultTableCellRenderer() {
      @Override
      public void setValue(Object value) {
          setFont(new Font("Times New Roman", Font.ITALIC, 18)); // Thiết lập font in nghiêng
          setText(value != null ? value.toString() : "");
      }
  };
  table.getColumnModel().getColumn(1).setCellRenderer(italicRenderer); // Áp dụng cho cột "Các mục"

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
    
 // Phương thức để in báo cáo
    public void inBaoCao() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new PrintTable());
        boolean doPrint = job.printDialog();
        if (doPrint) {
            try {
                job.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }

    // Lớp nội bộ để thực hiện việc in bảng
    private class PrintTable implements Printable {
        @Override
        public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
            if (pageIndex > 0) {
                return NO_SUCH_PAGE;
            }

            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

            // Quốc hiệu
            g2d.setFont(new Font("Arial", Font.BOLD, 14));
            g2d.drawString("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM", 150, 30);
            g2d.setFont(new Font("Arial", Font.PLAIN, 14));
            g2d.drawString("Độc lập - Tự do - Hạnh phúc", 190, 50);

            // Biên bản bàn giao ca
            g2d.setFont(new Font("Arial", Font.BOLD, 18));
            g2d.drawString("BIÊN BẢN BÀN GIAO CA", 170, 80);
            
            String tenNhanVien = lbltennguoithongke.getText();
           
            String date = lbngaythongke.getText(); // Lấy chuỗi từ JLabel
            String[] parts = date.split("-"); // Tách chuỗi tại dấu gạch ngang

            // Kiểm tra xem phần tử thứ hai có tồn tại không và lấy giá trị
            String datePart = (parts.length > 1) ? parts[1].trim() : ""; // Lấy phần sau dấu gạch ngang và loại bỏ khoảng trắng

            
            // Thông tin nhân viên và ngày in
            g2d.setFont(new Font("Arial", Font.PLAIN, 12));
            g2d.drawString(tenNhanVien , 50, 120);
            g2d.drawString(datePart, 400, 120);
            
            
            // Sao chép mô hình dữ liệu từ bảng `table`
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            DefaultTableModel printModel = new DefaultTableModel();

            // Sao chép cấu trúc cột
            for (int i = 0; i < model.getColumnCount(); i++) {
                printModel.addColumn(model.getColumnName(i));
            }

            // Sao chép từng hàng dữ liệu
            for (int i = 0; i < model.getRowCount(); i++) {
                Object[] row = new Object[model.getColumnCount()];
                for (int j = 0; j < model.getColumnCount(); j++) {
                    row[j] = model.getValueAt(i, j);
                }
                printModel.addRow(row);
            }

            // Tạo bảng mới với dữ liệu sao chép
            JTable printTable = new JTable(printModel);

            // Thiết lập độ rộng cột cho bảng in
            printTable.getColumnModel().getColumn(1).setPreferredWidth(300);
            printTable.getColumnModel().getColumn(1).setMinWidth(250);
            printTable.getColumnModel().getColumn(1).setMaxWidth(400);

            // Độ rộng cột thứ 3
            printTable.getColumnModel().getColumn(2).setPreferredWidth(150);
            printTable.getColumnModel().getColumn(2).setMinWidth(100);
            printTable.getColumnModel().getColumn(2).setMaxWidth(200);

            // Đặt kích thước bảng in
            printTable.setSize((int) pageFormat.getImageableWidth(), printTable.getPreferredSize().height);

            
            // Dịch bảng vào vị trí mong muốn
            int yPosition = 80;
            g2d.translate(80, yPosition);
         // Vẽ tiêu đề các cột
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            g2d.drawString("STT", 35, yPosition + 20);
            g2d.drawString("Các mục", 170, yPosition + 20);
            g2d.drawString("Tổng", 370, yPosition + 20);

            // Vẽ đường kẻ dưới tiêu đề cột
            g2d.drawLine(0, yPosition + 25, 425, yPosition + 25); // Dòng kẻ dưới tiêu đề

            // Dịch tiếp xuống dưới để in nội dung bảng sau tiêu đề
            yPosition += 30; // Cập nhật vị trí y để in bảng
            g2d.translate(0, yPosition);

            printTable.printAll(g2d); // In nội dung bảng
            // Vẽ phần chữ ký dưới bảng in
            int signaturePositionY = yPosition + printTable.getHeight() ; // Sử dụng chiều cao của `printTable`
            g2d.setFont(new Font("Arial", Font.ITALIC, 12));
            g2d.drawString("Người bàn giao", 50, signaturePositionY);
            g2d.drawString("Người nhận bàn giao", 350, signaturePositionY);

            // Vẽ dòng kẻ dưới chữ ký
            g2d.drawLine(40, signaturePositionY + 20, 100, signaturePositionY + 20);   // Dòng kẻ dưới "Người bàn giao"
            g2d.drawLine(340, signaturePositionY + 20,430 , signaturePositionY + 20);  // Dòng kẻ dưới "Người nhận bàn giao"
            
            return PAGE_EXISTS;
        }
    }



    ///////////////////////////////
    private String layThoiGianThongKeBang() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfDay = now.toLocalDate().atStartOfDay();
        return startOfDay.format(formatter) + " - " + now.format(formatter);
    }

}
