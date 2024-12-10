package bus;

import dal.KhachHangDAL;
import entity.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class KhachHangBUS {
    private KhachHangDAL khachHangDAL;
    private List<KhachHang> dsKhachHang;

    public KhachHangBUS() {
        khachHangDAL = new KhachHangDAL();
        dsKhachHang = new ArrayList<>();
    }

    public String generateCustomerCode() {
        String lastOrder = new KhachHangDAL().getLastKH(); // Lấy mã đơn cuối cùng
        int newOrderNumber = 1; // Mặc định bắt đầu từ 1 nếu không có mã đơn nào

        if (lastOrder != null) {
            // Tách phần số từ mã đơn
            String numberPart = lastOrder.substring(3); // Bỏ qua "DDP"
            newOrderNumber = Integer.parseInt(numberPart) + 1; // Tăng số lên 1
        }

        // Tạo mã đơn mới theo định dạng "DDPXXXXXX"
        return String.format("KH%06d", newOrderNumber); // Định dạng thành 6 chữ số
    }

    public List<KhachHang> getAllKhachHang() {
        if (dsKhachHang.isEmpty()) {
            dsKhachHang = khachHangDAL.getAllKhachHang();
        }
        return dsKhachHang;
    }

    // Thêm khách hàng và cập nhật danh sách trong bộ nhớ
    public boolean themKhachHang(KhachHang khachHang) {
        boolean success = khachHangDAL.themKhachHang(khachHang);
        if (success) {
            dsKhachHang.add(khachHang); // Thêm vào bộ nhớ
        }
        return success;
    }

    // Cập nhật khách hàng và cập nhật danh sách trong bộ nhớ
    public boolean capNhatKhachHang(KhachHang khachHang) {
        boolean success = khachHangDAL.suaKhachHang(khachHang);
        if (success) {
            // Cập nhật danh sách trong bộ nhớ
            for (KhachHang kh : dsKhachHang) {
                if (kh.getMaKH().equals(khachHang.getMaKH())) {
                    dsKhachHang.set(dsKhachHang.indexOf(kh), khachHang);
                    break;
                }
            }
        }
        return success;
    }

    public static void main(String[] args) {
        System.out.println(new KhachHangBUS().generateCustomerCode());
    }
}
