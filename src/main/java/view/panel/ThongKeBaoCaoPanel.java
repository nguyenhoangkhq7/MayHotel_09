package view.panel;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.*;
import constant.CommonConstants;
import dal.NhanVienDAL;
import bus.BangBaoCaoBUS;
import view.MainGUI;

public class ThongKeBaoCaoPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private JLabel lblTenNguoiThongKe;
    private JLabel lbNgayThongKe;
    private BangBaoCaoBUS bangBaoCaoBUS;
    private MenuPanel menuPanel;
    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    public ThongKeBaoCaoPanel(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;
        setLayout(new BorderLayout());
        
        JPanel content = new JPanel(new BorderLayout(0, 0));
        add(content, BorderLayout.CENTER);
        
        // Header Panel
        JPanel header = createHeaderPanel();
        content.add(header, BorderLayout.NORTH);
        
        // Content Panel (Body)
        JPanel noidung = new JPanel(new BorderLayout(0, 0));
        content.add(noidung, BorderLayout.CENTER);
        
        // Title Section
        JPanel tieudebang = createTitlePanel();
        noidung.add(tieudebang, BorderLayout.NORTH);
        
        // Table Section
        JPanel bang = createTablePanel();
        noidung.add(bang, BorderLayout.CENTER);
        
    }    
    private JPanel createHeaderPanel() {
        JPanel header = new JPanel();
        header.setBorder(new LineBorder(new Color(0, 0, 0)));
        header.setBackground(new Color(69, 96, 115));

        JLabel lblNewLabel = new JLabel("Bảng báo cáo");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));

        JButton btnInBaoCao = createPrintButton();

        GroupLayout gl_header = new GroupLayout(header);
        gl_header.setHorizontalGroup(
            gl_header.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addGroup(gl_header.createSequentialGroup()
                    .addGap(26)
                    .addComponent(lblNewLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                    .addComponent(btnInBaoCao, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                    .addGap(20))
        );
        gl_header.setVerticalGroup(
            gl_header.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addGroup(gl_header.createSequentialGroup()
                    .addGap(30)
                    .addGroup(gl_header.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnInBaoCao, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                    .addGap(20))
        );
        header.setLayout(gl_header);
        
        return header;
    }

    private JButton createPrintButton() {
        JButton btnInBaoCao = new JButton("In báo cáo");
        btnInBaoCao.setForeground(new Color(243, 125, 0));
        btnInBaoCao.setBackground(Color.WHITE);
        btnInBaoCao.setBorder(new LineBorder(CommonConstants.ORANGE, 2));
        btnInBaoCao.setFocusPainted(false);
        btnInBaoCao.setToolTipText("Bấm vào để in báo cáo ra file PDF");
        btnInBaoCao.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnInBaoCao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        btnInBaoCao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnInBaoCao.setBackground(new Color(255, 230, 202));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnInBaoCao.setBackground(Color.WHITE);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                inBaoCao(); // Print the report when clicked
            }
        });

        return btnInBaoCao;
    }

    private JPanel createTitlePanel() {
        JPanel tieudebang = new JPanel();
        tieudebang.setBackground(Color.WHITE);

        JLabel lbTieuDeBang = new JLabel("TỔNG KẾT CUỐI NGÀY");
        lbTieuDeBang.setFont(new Font("Times New Roman", Font.BOLD, 32));
        lbTieuDeBang.setHorizontalAlignment(SwingConstants.CENTER);

        lbNgayThongKe = new JLabel(layThoiGianThongKeBang());
        lbNgayThongKe.setHorizontalAlignment(SwingConstants.CENTER);
        lbNgayThongKe.setFont(new Font("Times New Roman", Font.BOLD, 20));

        lblTenNguoiThongKe = new JLabel("Nhân viên: Thái Bảo");
        String tenNhanVien = menuPanel.getNhanVienDangTruc().getHoten();
        lblTenNguoiThongKe.setText("Nhân viên: " + tenNhanVien);
        
        lblTenNguoiThongKe.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        lblTenNguoiThongKe.setHorizontalAlignment(SwingConstants.CENTER);

        GroupLayout gl_tieudebang = new GroupLayout(tieudebang);
        gl_tieudebang.setHorizontalGroup(
            gl_tieudebang.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lbNgayThongKe, GroupLayout.DEFAULT_SIZE, 1996, Short.MAX_VALUE)
                .addComponent(lblTenNguoiThongKe, GroupLayout.DEFAULT_SIZE, 1996, Short.MAX_VALUE)
                .addComponent(lbTieuDeBang, GroupLayout.DEFAULT_SIZE, 1996, Short.MAX_VALUE)
        );
        gl_tieudebang.setVerticalGroup(
            gl_tieudebang.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_tieudebang.createSequentialGroup()
                    .addComponent(lbTieuDeBang, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                    .addGap(10)
                    .addComponent(lbNgayThongKe, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblTenNguoiThongKe)
                    .addContainerGap(30, Short.MAX_VALUE))
        );
        tieudebang.setLayout(gl_tieudebang);

        return tieudebang;
    }

    private JPanel createTablePanel() {
        JPanel bang = new JPanel(new BorderLayout());
        table = new JTable();
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 18));

        bangBaoCaoBUS = new BangBaoCaoBUS();
        
        // Sample data
        LocalDate currentDate = LocalDate.now();
        LocalDateTime startDate = currentDate.atStartOfDay();;
        LocalDateTime endDate = LocalDateTime.now();
        Object[][] data = bangBaoCaoBUS.layDuLieuBang(menuPanel.getNhanVienDangTruc(),startDate, endDate);
        
        table.setModel(new DefaultTableModel(
            data,
            new String[] { "STT", "Các mục", "Tổng" }
        ) {
            Class[] columnTypes = new Class[] { String.class, String.class, Object.class };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            boolean[] columnEditables = new boolean[] { false, false, false };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        
        customizeTable();

        JScrollPane scrollPane = new JScrollPane(table);
        bang.add(scrollPane, BorderLayout.CENTER);

        return bang;
    }

    private void customizeTable() {
        // Customize columns
        table.getColumnModel().getColumn(1).setPreferredWidth(900);
        table.getColumnModel().getColumn(2).setPreferredWidth(900);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(30);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Times New Roman", Font.BOLD, 22));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        DefaultTableCellRenderer italicRenderer = new DefaultTableCellRenderer() {
            @Override
            public void setValue(Object value) {
                setFont(new Font("Times New Roman", Font.ITALIC, 18));
                setText(value != null ? value.toString() : "");
            }
        };
        table.getColumnModel().getColumn(1).setCellRenderer(italicRenderer);
    }

    // Method to generate the report
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

    // Internal class to handle printing of the table
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
            
            String tenNhanVien = lblTenNguoiThongKe.getText();
           
            String date = lbNgayThongKe.getText(); // Lấy chuỗi từ JLabel
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

    // Method to get the formatted report date
    public String layThoiGianThongKeBang() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfDay = now.toLocalDate().atStartOfDay();
        return startOfDay.format(formatter) + " - " + now.format(formatter);
    }
 

  
    
    public static void main(String[] args) {
        // Khởi tạo giao diện trong một thread riêng để đảm bảo GUI chạy mượt mà
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Tạo đối tượng MenuPanel (giả sử bạn đã tạo nó trong project)
                    MenuPanel menuPanel = new MenuPanel(new MainGUI(new NhanVienDAL().getNhanVienTheoMa("NV001")));

                    // Tạo đối tượng BangBaoCao và truyền menuPanel vào constructor
                    ThongKeBaoCaoPanel bangBaoCao = new ThongKeBaoCaoPanel(menuPanel);

                    // Tạo JFrame để chứa BangBaoCao
                    JFrame frame = new JFrame("Bảng Báo Cáo");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(800, 600); // Kích thước cửa sổ
                    frame.setLocationRelativeTo(null); // Đặt cửa sổ vào giữa màn hình
                    frame.setContentPane(bangBaoCao); // Thêm BangBaoCao vào cửa sổ
                    frame.setVisible(true); // Hiển thị cửa sổ
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
  


}
