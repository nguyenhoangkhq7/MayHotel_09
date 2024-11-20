package bus;

import dal.KhachHangDAL;
import entity.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class KhachHang_BUS {
    private KhachHangDAL khachHangDAL;
    private List<KhachHang> dsKhachHang;

    public KhachHang_BUS() {
        khachHangDAL = new KhachHangDAL();
        dsKhachHang = new ArrayList<>();
    }

    // Lấy tất cả khách hàng (có thể lấy từ bộ nhớ nếu có, hoặc từ cơ sở dữ liệu nếu chưa)
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
        boolean success = khachHangDAL.capNhatKhachHang(khachHang);
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
}
