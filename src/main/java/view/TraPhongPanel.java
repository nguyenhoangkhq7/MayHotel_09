package view;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import com.toedter.calendar.JDateChooser;
import constant.CommonConstants;
import dal.ChiTiet_DonDatPhong_PhongDAL;
import dal.ChiTiet_DonDatPhong_Phong_DichVuDAL;
import entity.ChiTiet_DonDatPhong_Phong;
import entity.ChiTiet_DonDatPhong_Phong_DichVu;
import utils.UIHelpers;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;

public class TraPhongPanel extends JPanel {
	private JTextField txtMaDDP;
	private JTextField txtMaPhong;
	private JTextField txtMaKhuyenMai;
	private JTextField txtNgayNhan;
	private JTextField txtDonGia;
	private JTextField txtNgayTra;
	private JTextField txtDTL;
	private JTable table_1;
	private JComboBox<String> cboLoaiKhachHang;
	private JComboBox<String> cboLoaiKhuyenMai;
	private JButton btnTraPhong;
	private JButton btnTinhTien;
	private ButtonGroup buttonGroupTK;
	private ButtonGroup buttonGroupGT;
	private JLabel lblLoi;
	private JLabel lblLoi_Ten;
	private JLabel lblLoi_SDT;
	private JLabel lblLoi_Email;
	private JLabel lblLoi_CCCD;
	private JPanel panelForm;
	private JPanel panelDetail_KH;
	private JTextField txtCMND;
	private JComboBox<String> cboLoaiChietKhau;
	private JTextField txtTenPhong;
	private JTextField txtTim;
	private JButton btnClose;
	JDateChooser jdcNgayNhan, jdcNgayTra;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public TraPhongPanel(ChiTiet_DonDatPhong_Phong chiTiet, ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> chiTietList) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(new Color(255, 255, 255));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel pnlTieuDe = new JPanel();
		pnlTieuDe.setBackground(new Color(255, 255, 255));
		add(pnlTieuDe);
		pnlTieuDe.setLayout(new FlowLayout());

		JLabel lblTieuDeTrang = new JLabel("Trả Phòng");
		lblTieuDeTrang.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDeTrang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		pnlTieuDe.add(lblTieuDeTrang);
		

		JPanel pnlThongTin = new JPanel();
		pnlThongTin.setBackground(new Color(255, 255, 255));
		pnlThongTin.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE),
				"Thông tin phòng", TitledBorder.LEADING, TitledBorder.TOP, null, CommonConstants.ORANGE));

		add(pnlThongTin);
		pnlThongTin.setLayout(new GridLayout(1, 1, 0, 0));
		
        panelForm = new JPanel();
        panelForm.setLayout(new GridLayout(3, 3, 10, 10));

        JLabel lbMaDDP = new JLabel("Mã đơn đặt phòng:");
        lbMaDDP.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbMaDDP);

        txtMaDDP = new JTextField("DDP*****");
        txtMaDDP.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtMaDDP.setEditable(false);
        panelForm.add(txtMaDDP);

        JLabel lbTenPhong = new JLabel("Tên phòng:");
        lbTenPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbTenPhong);

        txtTenPhong = new JTextField();
        txtTenPhong.setFont(new Font("Dialog", Font.BOLD, 13));
        txtTenPhong.setEditable(false);
        panelForm.add(txtTenPhong);

        JLabel lbChietKhau = new JLabel("Chiết khấu(%):");
        lbChietKhau.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbChietKhau);

        cboLoaiChietKhau = new JComboBox<>();
        cboLoaiChietKhau.setFont(new Font("Tahoma", Font.BOLD, 13));
        cboLoaiChietKhau.addItem("0");
        cboLoaiChietKhau.addItem("20");
        cboLoaiChietKhau.addItem("50");
        cboLoaiChietKhau.addItem("100");
        panelForm.add(cboLoaiChietKhau);

   
        

        JLabel lbMaPhong = new JLabel("Mã phòng:");
        lbMaPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbMaPhong);

        txtMaPhong = new JTextField();
        txtMaPhong.setFont(new Font("Dialog", Font.BOLD, 13));
        txtMaPhong.setEditable(false);
        panelForm.add(txtMaPhong);

        JLabel lbDonGia = new JLabel("Đơn giá:");
        lbDonGia.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbDonGia);

        txtDonGia = new JTextField();
        txtDonGia.setFont(new Font("Dialog", Font.BOLD, 13));
        txtDonGia.setEditable(false);
        panelForm.add(txtDonGia);
        
        JLabel lbKhuyenMai = new JLabel("Khuyến mãi:");
        panelForm.add(lbKhuyenMai);
        
        cboLoaiKhuyenMai = new JComboBox<>();
        cboLoaiKhuyenMai.setFont(new Font("Tahoma", Font.BOLD, 13));
        cboLoaiKhuyenMai.addItem("0");
        cboLoaiKhuyenMai.addItem("20");
        cboLoaiKhuyenMai.addItem("50");
        cboLoaiKhuyenMai.addItem("100");
        panelForm.add(cboLoaiKhuyenMai);
        


        JLabel lbNgayNhan = new JLabel("Ngày nhận:");
        lbNgayNhan.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbNgayNhan);

        panelForm.add(UIHelpers.create_Form_Label_JDateChooser(null ,jdcNgayNhan = new JDateChooser()));
      

        JLabel lbNgayTra = new JLabel("Ngày trả:");
        lbNgayTra.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbNgayTra);

        panelForm.add(UIHelpers.create_Form_Label_JDateChooser(null ,jdcNgayTra = new JDateChooser()));


          pnlThongTin.add(panelForm);
          
          //Lấy dữ liệu
          txtMaDDP.setText(chiTiet.getDonDatPhong().getMaDon());
          txtMaPhong.setText(chiTiet.getPhong().getMaPhong());
          txtTenPhong.setText(chiTiet.getPhong().getTenPhong());
          txtDonGia.setText(String.valueOf(chiTiet.getPhong().getLoaiPhong().getDonGia()));
          jdcNgayNhan.setDate(java.sql.Date.valueOf(chiTiet.getNgayNhanPhong()));
          jdcNgayTra.setDate(java.sql.Date.valueOf(chiTiet.getNgayTra()));
 

        JPanel pnlBang = new JPanel();
        pnlBang.setBackground(new Color(255, 255, 255));
        pnlBang.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE),
                "Danh sách dịch vụ đã sử dụng", TitledBorder.LEADING, TitledBorder.TOP, null, CommonConstants.ORANGE));
        add(pnlBang);
        pnlBang.setLayout(new BorderLayout());

        JScrollPane scrDSDV = new JScrollPane();
        pnlBang.add(scrDSDV, BorderLayout.CENTER);

        table_1 = new JTable();
        table_1.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"M\u00E3 d\u1ECBch v\u1EE5", "T\u00EAn d\u1ECBch v\u1EE5", "\u0110\u01A1n gi\u00E1", "S\u1ED1 l\u01B0\u1EE3ng", "Th\u00E0nh ti\u1EC1n"
        	}
        ) {
        	boolean[] columnEditables = new boolean[] {
        		false, false, false, false, false
        	};
        	public boolean isCellEditable(int row, int column) {
        		return columnEditables[column];
        	}
        });

        table_1.setForeground(new Color(0, 0, 0));
        table_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        table_1.setRowHeight(25);
        table_1.setSelectionBackground(new Color(0, 204, 204));
        table_1.setSelectionForeground(new Color(255, 255, 255));
        table_1.setFillsViewportHeight(true);

        scrDSDV.setViewportView(table_1);
        loadDataToTable(chiTietList);

        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 255, 255));
        panel_2.setBorder(
                new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE), "Tổng thanh toán", TitledBorder.LEADING, TitledBorder.TOP, null, CommonConstants.ORANGE));
        add(panel_2);
        panel_2.setLayout(new FlowLayout());
        
        JLabel lblNewLabel = new JLabel("Tổng tiền:");
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 30));
        panel_2.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("(Số tiền phải trả)");
        lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 30));
        panel_2.add(lblNewLabel_1);

        
        JPanel panel_3 = new JPanel();
        panel_3.setBackground(new Color(255, 255, 255));
        panel_3.setBorder(
                new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE), "Chức năng", TitledBorder.LEADING, TitledBorder.TOP, null, CommonConstants.ORANGE));
        add(panel_3);
        panel_3.setLayout(new FlowLayout());
        
        btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(btnClose);
                currentFrame.setVisible(false); 
           
                MainGUI mainGUI = new MainGUI();
                JFrame frame = new JFrame("Màn hình chính");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(mainGUI);
                frame.setSize(1920, 1080);
                frame.setVisible(true);
        	}
        });
        btnClose.setBackground(new Color(243, 125, 0));
        btnClose.setForeground(new Color(255,255,255));
        btnClose.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_3.add(btnClose);
        
        btnTinhTien = new JButton("Tính tiền");
        btnTinhTien.setBackground(new Color(243, 125, 0));
        btnTinhTien.setForeground(new Color(255,255,255));
        btnTinhTien.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_3.add(btnTinhTien);

        btnTraPhong = new JButton("Trả phòng");
        btnTraPhong.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Hiển thị hộp thoại xác nhận
                int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn trả phòng không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                	
            		JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(btnTraPhong);
                    currentFrame.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Trả phòng thành công!");
                    MainGUI mainGUI = new MainGUI();
                    JFrame frame = new JFrame("Thêm đơn đặt phòng");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.getContentPane().add(mainGUI);
                    frame.setSize(1920, 1080);
                    frame.setVisible(true);
                    
                } else {
                    System.out.println("Quay về trang trả phòng.");
                }
            }
        });
        btnTraPhong.setBackground(new Color(243, 125, 0));
        btnTraPhong.setForeground(new Color(255,255,255));
        btnTraPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_3.add(btnTraPhong);
        
        btnTinhTien.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
                    // Lấy thông tin từ các trường trong form
                    String maDDP = txtMaDDP.getText();
                    String tenPhong = txtTenPhong.getText();
                    String maPhong = txtMaPhong.getText();
                    double donGia = Double.parseDouble(txtDonGia.getText());
                    int chietKhau = Integer.parseInt((String) cboLoaiChietKhau.getSelectedItem());
                    int khuyenMai = Integer.parseInt((String) cboLoaiKhuyenMai.getSelectedItem());

                    // Lấy ngày nhận và ngày trả từ JDateChooser
                    Date ngayNhanDate = jdcNgayNhan.getDate();
                    Date ngayTraDate = jdcNgayTra.getDate();
                    if (ngayNhanDate == null || ngayTraDate == null) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày nhận và ngày trả.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    // Tính toán tổng số ngày ở
                    long diffInMillies = Math.abs(ngayTraDate.getTime() - ngayNhanDate.getTime());
                    long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

                    // Tính toán tổng chi phí phòng
                    double thanhTien = donGia * (1 - chietKhau / 100.0) * (1 - khuyenMai / 100.0);
                    double tongTienPhong = thanhTien * diffInDays;

                    // Tính tổng tiền dịch vụ đã sử dụng
                    double tongTienDichVu = 0;
                    DefaultTableModel model = (DefaultTableModel) table_1.getModel();
                    for (int i = 0; i < model.getRowCount(); i++) {
                        double donGiaDV = Double.parseDouble(model.getValueAt(i, 2).toString());
                        int soLuongDV = Integer.parseInt(model.getValueAt(i, 3).toString());
                        tongTienDichVu += donGiaDV * soLuongDV;
                    }

                    // Tổng tiền phải trả
                    double tongTien = tongTienPhong + tongTienDichVu;
                    // Định dạng số tiền để hiển thị
                    NumberFormat formatter = NumberFormat.getInstance(Locale.getDefault());
                    formatter.setGroupingUsed(true); // Bật định dạng nhóm
                    String formattedTotal = formatter.format(tongTien) + " VND";

                    // Hiển thị kết quả tổng tiền trong JLabel
                    lblNewLabel_1.setText(formattedTotal);

                    // Thực hiện các hành động tiếp theo (ví dụ: cập nhật trạng thái phòng, lưu dữ liệu vào CSDL)
                    // Code lưu vào CSDL hoặc cập nhật trạng thái phòng...

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Có lỗi xảy ra. Vui lòng kiểm tra lại thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        
        
	}

	private void loadDataToTable(ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> chiTietList) {
		DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		model.setRowCount(0); // Xóa dữ liệu cũ trong bảng

		for (ChiTiet_DonDatPhong_Phong_DichVu chiTiet : chiTietList) {
			String maDichVu = chiTiet.getDichVu().getMaDichVu();
			String tenDichVu = chiTiet.getDichVu().getTenDichVu();
			double donGia = chiTiet.getDichVu().getDonGia();
			int soLuong = chiTiet.getSoLuongDat();
			double thanhTien = donGia * soLuong;

			model.addRow(new Object[] { maDichVu, tenDichVu, donGia, soLuong, thanhTien });
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				JFrame frame = new JFrame("Trả Phòng");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				ChiTiet_DonDatPhong_Phong cT_DDP_P = new ChiTiet_DonDatPhong_PhongDAL()
						.getChiTietDonDatPhongPhongTheoMa("CTDDPP003");

				ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> chiTietList = new ArrayList<>();
				ChiTiet_DonDatPhong_Phong_DichVu ctddppdv = new ChiTiet_DonDatPhong_Phong_DichVuDAL()
						.getChiTietDonDatPhongPhongDichVuTheoMa("CTDDP_DV001");

				chiTietList.add(ctddppdv);

				frame.setContentPane(new TraPhongPanel(cT_DDP_P, chiTietList));
				frame.pack();
				frame.setSize(1920, 1080);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
