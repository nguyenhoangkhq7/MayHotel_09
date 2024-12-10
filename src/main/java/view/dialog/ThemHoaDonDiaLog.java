package view.dialog;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import dal.DonDatPhongDAL;
import dal.NhanVienDAL;
import dal.HoaDonDAL;
import dal.KhuyenMaiDAL;
import entity.DonDatPhong;
import entity.NhanVien;
import entity.HoaDon;
import entity.KhuyenMai;
import view.panel.QuanLyHoaDonPanel;

import java.util.List;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class ThemHoaDonDiaLog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtMaDV;
	private JTextField txtDonGia;
	private JTextField txtDonVi;
	private JTextField txtTenDV;
	private JTextField txtSoLuong;
	private JComboBox<String> cboHoatDong;
	private JButton btnLuu;
	private JButton btnHuyBo;
	private JTextField txtMaHoaDon;
	private JTextField txtTenHoaDon;
	private JTextField txtLoaiHoaDon;
	private JComboBox cboTrangThai;
	private JTextField txtMoTa;
	private JTextField txtTầng;
	private JTextField txtTang;
	private JTextField txtSDT;
	private JTextField txtCCCD;
	private JTextField txtEmail;
	private JComboBox cboLoaiHD;
	private JTextField txtTienTichLuy;
	private JTextField txtThanhTien;
	private JTextField txtMaNV;
	private JTextField txtMaKM;
	private JTextField txtDonDat;
	private JComboBox cboDonDat;

	public ThemHoaDonDiaLog(String maHD, QuanLyHoaDonPanel quanLyHoaDonPanel) {
		setTitle("Thêm Hóa Đơn");
		setSize(400, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(8, 2, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Mã Dịch Vụ
		panel.add(new JLabel("Mã Hóa Đơn:"));
		txtMaHoaDon = new JTextField(maHD); // Set mã dịch vụ
		txtMaHoaDon.setEditable(false);
		panel.add(txtMaHoaDon);
		
		panel.add(new JLabel("Trạng Thái:"));
		cboTrangThai = new JComboBox<>(new String[] { "Đã thanh toán ","Chưa thanh toán" });
		cboTrangThai.setEnabled(false);
		
		panel.add(cboTrangThai);

		// Đơn Vị
		panel.add(new JLabel("Thành Tiền:"));
		txtThanhTien = new JTextField();
		panel.add(txtThanhTien);
		
		// Tạo ComboBox cho mã nhân viên
		JComboBox<String> cboMaNV = new JComboBox<>();

		// Lấy danh sách mã nhân viên từ cơ sở dữ liệu
		List<NhanVien> listNhanVien = new NhanVienDAL().getAllNhanVien();

		// Thêm mã nhân viên vào ComboBox
		for (NhanVien nhanVien : listNhanVien) {
		    cboMaNV.addItem(nhanVien.getMaNV());  // Giả sử MaNV là thuộc tính của đối tượng NhanVien
		}

		// Thêm ComboBox vào panel
		panel.add(new JLabel("Mã Nhân Viên:"));
		panel.add(cboMaNV);

		// Lấy mã nhân viên được chọn khi lưu hóa đơn
		String maNV = (String) cboMaNV.getSelectedItem();


		//Khuyến Mãi
		panel.add(new JLabel("Mã Khuyến Mãi:"));
		txtMaKM = new JTextField();
		panel.add(txtMaKM);
		
		
		
		//Mã đơn đặt phòng
		JComboBox<String> cboMaDDP = new JComboBox<>();

		// Lấy danh sách mã nhân viên từ cơ sở dữ liệu
		List<DonDatPhong> listDDP= new DonDatPhongDAL().getAllDonDatPhong();

		// Thêm mã nhân viên vào ComboBox
		for (DonDatPhong donDatPhong : listDDP) {
		    cboMaDDP.addItem(donDatPhong.getMaDon());  // Giả sử MaNV là thuộc tính của đối tượng NhanVien
		}

		// Thêm ComboBox vào panel
		panel.add(new JLabel("Mã Đơn Đặt Phòng:"));
		panel.add(cboMaDDP);

		// Lấy mã nhân viên được chọn khi lưu hóa đơn
		String maDDP = (String) cboMaDDP.getSelectedItem();

		// Hoạt Động
		JLabel lbNgayTao = new JLabel("Ngày Tạo:");
        lbNgayTao.setFont(new Font("Tahoma", Font.BOLD, 13)); // Đặt font chữ cho JLabel
        panel.add(lbNgayTao); // Thêm JLabel vào panel
        
        JDateChooser txtNgayTao = new JDateChooser();
        txtNgayTao.setDateFormatString("dd/MM/yyyy"); // Đặt định dạng ngày
        panel.add(txtNgayTao); // Thêm JDateChooser vào panel


		
		
		

		// Buttons
		btnLuu = new JButton("Lưu");
		btnLuu.setBackground(new Color(243, 125, 0));
		btnLuu.setForeground(new Color(255, 255, 255));

		btnHuyBo = new JButton("Hủy Bỏ");
		btnHuyBo.setBackground(new Color(243, 125, 0));
		btnHuyBo.setForeground(new Color(255, 255, 255));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		buttonPanel.add(btnLuu);
		buttonPanel.add(btnHuyBo);

		// Add panels to dialog
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		btnLuu.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String nextMaHoaDon = generateHDCode();
		        txtMaHoaDon.setText(nextMaHoaDon);
		        String thanhTien = txtThanhTien.getText().trim();
		        String maNV = (String) cboMaNV.getSelectedItem();  // Lấy mã nhân viên từ ComboBox
		        String maDDP = (String) cboMaDDP.getSelectedItem();  // Lấy mã đơn đặt phòng từ ComboBox
		        
		        // Ràng buộc cho thành tiền chỉ được nhập số
		        if (!thanhTien.matches("\\d+")) {
		            JOptionPane.showMessageDialog(ThemHoaDonDiaLog.this, "Vui lòng nhập số hợp lệ cho Thành Tiền!", "Lỗi",
		                    JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Ràng buộc cho mã nhân viên phải có định dạng NVXXX (trong trường hợp mã nhân viên đã chọn không hợp lệ)
		        if (maNV == null || maNV.isEmpty()) {
		            JOptionPane.showMessageDialog(ThemHoaDonDiaLog.this, "Vui lòng chọn mã nhân viên!", "Lỗi",
		                    JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Ràng buộc cho mã đơn đặt phòng phải có định dạng DDPXXXXXX
		        if (maDDP == null || maDDP.isEmpty()) {
		            JOptionPane.showMessageDialog(ThemHoaDonDiaLog.this, "Vui lòng chọn mã đơn đặt phòng!", "Lỗi",
		                    JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Kiểm tra mã nhân viên có tồn tại trong cơ sở dữ liệu hay không
		        NhanVien nhanVien = new NhanVienDAL().getNhanVienTheoMa(maNV);
		        if (nhanVien == null) {
		            JOptionPane.showMessageDialog(ThemHoaDonDiaLog.this, "Mã nhân viên không tồn tại!", "Lỗi",
		                    JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Kiểm tra mã đơn đặt phòng có tồn tại trong cơ sở dữ liệu hay không
		        DonDatPhong donDatPhong = new DonDatPhongDAL().getDonDatPhongTheoMa(maDDP);
		        if (donDatPhong == null) {
		            JOptionPane.showMessageDialog(ThemHoaDonDiaLog.this, "Mã đơn đặt phòng không tồn tại!", "Lỗi",
		                    JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Khuyến mãi (Nếu có)
		        KhuyenMai khuyenMai = new KhuyenMaiDAL().getKhuyenMaiTheoMa(txtMaKM.getText());
		        
		        LocalDateTime ngayTao = LocalDateTime.now();

		        if (nextMaHoaDon.isEmpty() || thanhTien.isEmpty() || ngayTao == null) {
		            JOptionPane.showMessageDialog(ThemHoaDonDiaLog.this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi",
		                    JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        try {
		            double tien = Double.parseDouble(thanhTien);

		            // Thiết lập trạng thái là true (Đã Thanh Toán) mặc định
		            boolean isTrangThai = true;

		            HoaDon HoaDon = new HoaDon(nextMaHoaDon, isTrangThai, tien, nhanVien, khuyenMai, donDatPhong, ngayTao);

		            // Gọi service để lưu vào cơ sở dữ liệu
		            HoaDonDAL HoaDonDAL = new HoaDonDAL(); // Giả định có lớp service để lưu hóa đơn
		            boolean isSuccess = HoaDonDAL.themHoaDon(HoaDon);

		            if (isSuccess) {
		                JOptionPane.showMessageDialog(ThemHoaDonDiaLog.this, "Thêm Hóa Đơn thành công!", "Thông báo",
		                        JOptionPane.INFORMATION_MESSAGE);
		                dispose(); // Đóng dialog
		                quanLyHoaDonPanel.capNhatTable(); // Cập nhật bảng hóa đơn
		            } else {
		                JOptionPane.showMessageDialog(ThemHoaDonDiaLog.this, "Thêm Hóa Đơn thất bại!", "Lỗi",
		                        JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(ThemHoaDonDiaLog.this, "Vui lòng nhập số hợp lệ cho các trường số!", "Lỗi",
		                    JOptionPane.ERROR_MESSAGE);
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(ThemHoaDonDiaLog.this, "Lỗi khi thêm hóa đơn: " + ex.getMessage(), "Lỗi",
		                    JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});


		



		btnHuyBo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
	}
	
	public String generateHDCode() {
	    String lastHDCode = new HoaDonDAL().getLastHoaDonCode(); // Lấy mã HDách hàng cuối cùng
	    int newHDNumber = 1; // Mặc định bắt đầu từ 1 nếu HDông có mã HDách hàng nào

	    if (lastHDCode != null && lastHDCode.startsWith("HD")) {
	        // Tách phần số từ mã HDách hàng (bỏ qua "HD")
	        String numberPart = lastHDCode.substring(2); // Bỏ qua ký tự "HD"
	        try {
	            newHDNumber = Integer.parseInt(numberPart) + 1; // Tăng số lên 1
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	            // Nếu có lỗi trong việc chuyển đổi, giữ nguyên newHDNumber là 1
	        }
	    }

	    // Tạo mã HDách hàng mới theo định dạng "HDXXXX" (với 4 chữ số)
	    return String.format("HD%05d", newHDNumber); // Định dạng thành 4 chữ số
	}



}
