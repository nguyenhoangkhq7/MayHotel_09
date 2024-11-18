package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import constant.CommonConstants;
import dal.KhachHangDAL;
import entity.KhachHang;
import entity.LoaiKhachHang;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.util.ArrayList;

public class KhachHangPanel extends JPanel {

    private JTextField txtMaKH;
    private JTextField txtTenKH;
    private JTextField txtDiaChi;
    private JTextField txtSDT;
    private JTextField txtCCCD;
    private JTextField txtEmail;
    private JTextField txtDTL;
    private JTable table_1 = new JTable();
    private JComboBox<String> cboLoaiKhachHang;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;      // Nút Xóa
    private JButton btnLamMoi;   // Nút Làm mới
    private JButton btnXuatCSV;  // Nút Xuất CSV
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
    private JTextField txtTienTichLuy;
    private JTextField txtTim;
	private JTable table_CTHD;
	DefaultTableModel model = new DefaultTableModel();
	private JButton btnLuu;

    public KhachHangPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel pnlTieuDe = new JPanel();
        pnlTieuDe.setBackground(Color.WHITE);
        add(pnlTieuDe);
        pnlTieuDe.setLayout(new FlowLayout());

        JLabel lblTieuDeTrang = new JLabel("QUẢN LÝ KHÁCH HÀNG");
        lblTieuDeTrang.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDeTrang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        pnlTieuDe.add(lblTieuDeTrang);

        JPanel pnlThongTin = new JPanel();
        pnlThongTin.setBackground(Color.WHITE);
        pnlThongTin.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE),
                "Thiết lập thông tin khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, CommonConstants.ORANGE));

        add(pnlThongTin);
        pnlThongTin.setLayout(new GridLayout(1, 1, 0, 0));

        panelForm = new JPanel();
        panelForm.setLayout(new GridLayout(4, 3, 10, 10));

        JLabel lbMaKH = new JLabel("Mã khách hàng:");
        lbMaKH.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbMaKH);

        txtMaKH = new JTextField("KH******");
        txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtMaKH.setEditable(false);
        panelForm.add(txtMaKH);

        JLabel lbTienTichLuy = new JLabel("Tiền Tích Lũy:");
        lbTienTichLuy.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbTienTichLuy);

        txtTienTichLuy = new JTextField();
        txtTienTichLuy.setFont(new Font("Dialog", Font.BOLD, 13));
        panelForm.add(txtTienTichLuy);

        JLabel lbLoaiKhach = new JLabel("Loại Khách Hàng:");
        lbLoaiKhach.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbLoaiKhach);

        cboLoaiKhachHang = new JComboBox<>();
        cboLoaiKhachHang.setFont(new Font("Tahoma", Font.BOLD, 13));
        cboLoaiKhachHang.addItem("Hạng Đồng");
        cboLoaiKhachHang.addItem("Hạng Bạc");
        cboLoaiKhachHang.addItem("Hạng Vàng");
        cboLoaiKhachHang.addItem("Hạng Kim Cương");
        cboLoaiKhachHang.setEnabled(false);
        panelForm.add(cboLoaiKhachHang);

        JLabel lbTenKH = new JLabel("Tên khách hàng:");
        lbTenKH.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbTenKH);

        txtTenKH = new JTextField();
        txtTenKH.setFont(new Font("Dialog", Font.BOLD, 13));
        panelForm.add(txtTenKH);

        JLabel lbCMND = new JLabel("CMND/CCCD:");
        lbCMND.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbCMND);

        txtCMND = new JTextField();
        txtCMND.setFont(new Font("Dialog", Font.BOLD, 13));
        panelForm.add(txtCMND);

        JLabel lbSDT = new JLabel("Số điện thoại:");
        lbSDT.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbSDT);

        txtSDT = new JTextField();
        txtSDT.setFont(new Font("Dialog", Font.BOLD, 13));
        panelForm.add(txtSDT);

        JLabel lbEmail = new JLabel("Email:");
        lbEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelForm.add(lbEmail);

        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Dialog", Font.BOLD, 13));
        panelForm.add(txtEmail);

        pnlThongTin.add(panelForm);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        panel_2.setBorder(new TitledBorder(null, "Chức năng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        add(panel_2);
        panel_2.setLayout(new FlowLayout());

        btnThem = new JButton("Thêm");
        btnThem.setBackground(Color.CYAN);
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_2.add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setBackground(Color.CYAN);
        btnSua.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_2.add(btnSua);

        btnXoa = new JButton("Xóa");
        btnXoa.setBackground(Color.RED);
        btnXoa.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_2.add(btnXoa);

        btnLuu = new JButton("Lưu");
        btnLuu.setBackground(Color.LIGHT_GRAY);
        btnLuu.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel_2.add(btnLuu);

        JPanel pnlBang = new JPanel();
        pnlBang.setBackground(Color.WHITE);
        pnlBang.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE),
                "Danh sách khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, CommonConstants.ORANGE));
        add(pnlBang);
        pnlBang.setLayout(new BorderLayout());

        JScrollPane scrDSKH = new JScrollPane();
        pnlBang.add(scrDSKH, BorderLayout.CENTER);

        Box hBox = Box.createHorizontalBox();
        JLabel lbTimTenKH = new JLabel("Mã khách hàng:");
        lbTimTenKH.setFont(new Font("Tahoma", Font.BOLD, 13));

        txtTim = new JTextField();
        txtTim.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtTim.setPreferredSize(new Dimension(150, 20));
        txtTim.setMaximumSize(new Dimension(150, 20));

        JButton btnTim = new JButton("Tìm");
        btnTim.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnTim.setBackground(Color.CYAN);

        hBox.add(lbTimTenKH);
        hBox.add(Box.createHorizontalStrut(10));
        hBox.add(txtTim);
        hBox.add(Box.createHorizontalStrut(10));
        hBox.add(btnTim);

        pnlBang.add(hBox, BorderLayout.NORTH);

        model.addColumn("Mã Khách Hàng");
        model.addColumn("Tên Khách Hàng");
        model.addColumn("Số Điện Thoại");
        model.addColumn("CMND/CCCD");
        model.addColumn("Email");
        model.addColumn("Tiền Tích Lũy");
        model.addColumn("Loại Khách");
        
        
        table_CTHD = new JTable(model);
        scrDSKH.setViewportView(table_CTHD);

        // ActionListeners for buttons
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCustomer(); // Gọi phương thức thêm khách hàng
            }
        });
        btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteCustomer();
				
			}
		});
        btnSua.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_CTHD.getSelectedRow();
			    if (selectedRow != -1) {
			        // Hiển thị hộp thoại xác nhận
			        int confirm = JOptionPane.showConfirmDialog(null, 
			            "Bạn có chắc chắn muốn sửa thông tin khách hàng này?", 
			            "Xác nhận sửa", 
			            JOptionPane.YES_NO_OPTION);
			        
			        if (confirm == JOptionPane.YES_OPTION) {
			            // Gọi phương thức editCustomer để điền thông tin vào các trường
			            editCustomer();
			        }
			    } else {
			        JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng để sửa.");
			    }
				
			}
		});
        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy từ khóa tìm kiếm từ trường nhập liệu
                String keyword = txtTim.getText().trim(); // Giả sử txtSearch là JTextField nhập mã khách hàng

                // Gọi phương thức tìm kiếm
                searchCustomer(keyword);
            }
        });

//        btnSua.addActionListener(e -> editCustomer());
//        btnXoa.addActionListener(e -> deleteCustomer());
//        btnLamMoi.addActionListener(e -> clearForm());
//        btnTim.addActionListener(e -> searchCustomer());
        hienThiDuLieu();
        
    }
    

    private void addCustomer() {
        try {
            // Sinh mã khách hàng tự động
            KhachHangDAL khachHangDAL = new KhachHangDAL();
            String lastMaKH = khachHangDAL.getLastMaKH();
            String newMaKH = generateNewMaKH(lastMaKH);

            String hoTen = txtTenKH.getText().trim(); 
            String soDienThoai = txtSDT.getText().trim();
            String soCanCuoc = txtCMND.getText().trim();
            String email = txtEmail.getText().trim();

            if (newMaKH.isEmpty() || hoTen.isEmpty() || soDienThoai.isEmpty() || soCanCuoc.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin khách hàng.");
                return;
            }

            double tienTichLuy;
            try {
                tienTichLuy = Double.parseDouble(txtTienTichLuy.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Tiền tích lũy không hợp lệ. Vui lòng nhập số hợp lệ.");
                return;
            }

            LoaiKhachHang loaiKH = getLoaiKhachHang(cboLoaiKhachHang.getSelectedItem().toString());
            KhachHang khachHang = new KhachHang(newMaKH, hoTen, soDienThoai, tienTichLuy, soCanCuoc, email, loaiKH);

            boolean isSuccess = khachHangDAL.themKhachHang(khachHang);

            if (isSuccess) {
                model.addRow(new Object[]{newMaKH, hoTen, soDienThoai, soCanCuoc, email, tienTichLuy, loaiKH});
                clearForm();
                JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công!");
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi khi thêm khách hàng.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi xảy ra: " + ex.getMessage());
        }
    }
    private void editCustomer() {
        int selectedRow = table_CTHD.getSelectedRow();
        if (selectedRow != -1) {
            txtMaKH.setText(model.getValueAt(selectedRow, 0).toString());
            txtTenKH.setText(model.getValueAt(selectedRow, 1).toString());
            txtSDT.setText(model.getValueAt(selectedRow, 2).toString());
            txtCMND.setText(model.getValueAt(selectedRow, 3).toString());
            txtEmail.setText(model.getValueAt(selectedRow, 4).toString());
            txtTienTichLuy.setText(model.getValueAt(selectedRow, 5).toString());
            cboLoaiKhachHang.setSelectedItem(model.getValueAt(selectedRow, 6).toString());
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng để sửa.");
        }
    }


    // Phương thức sinh mã khách hàng mới dựa trên mã cuối cùng
    private String generateNewMaKH(String lastMaKH) {
        if (lastMaKH == null) {
            return "KH000001"; // Nếu chưa có khách hàng nào
        }

        String numericPart = lastMaKH.substring(2); // Bỏ đi 'KH'
        int newMaKHInt = Integer.parseInt(numericPart) + 1;
        return String.format("KH%06d", newMaKHInt); // Tăng mã lên và định dạng lại
    }


    // Phương thức ánh xạ tên loại khách hàng thành enum
    private LoaiKhachHang getLoaiKhachHang(String loaiKHStr) {
        switch (loaiKHStr) {
            case "Hạng Đồng":
                return LoaiKhachHang.HANGDONG;
            case "Hạng Bạc":
                return LoaiKhachHang.HANGBAC;
            case "Hạng Vàng":
                return LoaiKhachHang.HANGVANG;
            case "Hạng Kim Cương":
                return LoaiKhachHang.HANGKIMCUONG;
            default:
                return null; // Hoặc có thể xử lý lỗi nếu cần
        }
    }
    private void deleteCustomer() {
        int selectedRow = table_CTHD.getSelectedRow();
        if (selectedRow != -1) {
            String maKH = model.getValueAt(selectedRow, 0).toString();
            // Call to DAL method to delete customer in the database
            KhachHangDAL khachHangDAL = new KhachHangDAL();
            boolean isDeleted = khachHangDAL.xoaKhachHang(maKH);
            if (isDeleted) {
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(null, "Xóa khách hàng thành công!");
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi khi xóa khách hàng.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng để xóa.");
        }
    }
    private void searchCustomer(String keyword) {
        // Kiểm tra nếu từ khóa tìm kiếm là rỗng
        if (keyword.isEmpty()) {
            hienThiDuLieu(); // Hiển thị tất cả dữ liệu nếu không có từ khóa
            return;
        }

        // Lọc dữ liệu trong bảng dựa trên từ khóa
        DefaultTableModel model = (DefaultTableModel) table_CTHD.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table_CTHD.setRowSorter(sorter);

        // Tạo một bộ lọc để tìm kiếm theo mã khách hàng
        // Cột 0 là cột "Mã Khách Hàng", sử dụng biểu thức chính quy không phân biệt chữ hoa chữ thường
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword, 0)); 
    }



    private void clearForm() {
        txtMaKH.setText("KH******");
        txtTenKH.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtCMND.setText("");
        txtTienTichLuy.setText("");
        cboLoaiKhachHang.setSelectedIndex(0);
    }
    public void hienThiDuLieu() {
        ArrayList<KhachHang> dsKhachHang; // Thay đổi loại dữ liệu từ HoaDon sang KhachHang
        dsKhachHang = new KhachHangDAL().getAllKhachHang(); // Gọi phương thức lấy danh sách khách hàng
        for (KhachHang kh : dsKhachHang) {
            Object[] row = {
                kh.getMaKH(), // Mã khách hàng
                kh.getHoTen(), // Họ tên khách hàng
                kh.getSoDienThoai(), // Số điện thoại khách hàng
                kh.getSoCanCuoc(), // Số căn cước
                kh.getEmail(), // Email
                kh.getTienTichLuy(), // Tiền tích lũy
                kh.getLoaiKhachHang() != null ? kh.getLoaiKhachHang().toString() : "Không xác định" // Loại khách hàng
            };
            model.addRow(row); // Thêm hàng vào model
        }
    }
    

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JFrame frame = new JFrame("Quản lí khách hàng");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(new KhachHangPanel());
                frame.pack();
                frame.setSize(1920, 1080);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
