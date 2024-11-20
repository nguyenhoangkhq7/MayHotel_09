package bus;

import dal.TaiKhoanDAL;
import entity.TaiKhoan;
import java.util.List;
import java.util.ArrayList;

public class TaiKhoanBUS {
    private List<TaiKhoan> dsTaiKhoan; // Lưu trữ danh sách tài khoản trong bộ nhớ
    private TaiKhoanDAL taiKhoanDAL;

    public TaiKhoanBUS() {
        dsTaiKhoan = new ArrayList<>();
        taiKhoanDAL = new TaiKhoanDAL();
        loadAllTaiKhoan(); // Tải danh sách tài khoản khi khởi tạo
    }

    // Lấy tất cả tài khoản từ bộ nhớ (nếu có) hoặc từ cơ sở dữ liệu nếu chưa có
    public List<TaiKhoan> getAllTaiKhoan() {
        if (dsTaiKhoan.isEmpty()) {
            loadAllTaiKhoan(); // Nếu bộ nhớ trống, tải từ cơ sở dữ liệu
        }
        return dsTaiKhoan;
    }

    // Thêm tài khoản
    public boolean themTaiKhoan(TaiKhoan taiKhoan) {
        boolean result = taiKhoanDAL.themTaiKhoan(taiKhoan);
        if (result) {
            dsTaiKhoan.add(taiKhoan); // Cập nhật danh sách tạm thời sau khi thêm
        }
        return result;
    }

    // Cập nhật tài khoản
    public boolean suaTaiKhoan(String tenTaiKhoan, String matKhauMoi) {
        boolean result = taiKhoanDAL.suaTaiKhoan(new TaiKhoan(tenTaiKhoan, matKhauMoi));
        if (result) {
            // Cập nhật trong danh sách tạm thời
            TaiKhoan taiKhoan = getTaiKhoanTheoMa(tenTaiKhoan);
            if (taiKhoan != null) {
                taiKhoan.setMatKhau(matKhauMoi);
            }
        }
        return result;
    }

    // Xóa tài khoản
//    public boolean xoaTaiKhoan(String tenTaiKhoan) {
//        boolean result = taiKhoanDAL.xoaTaiKhoan(tenTaiKhoan);
//        if (result) {
//            // Xóa khỏi danh sách tạm thời
//            dsTaiKhoan.removeIf(tk -> tk.getTenTaiKhoan().equals(tenTaiKhoan));
//        }
//        return result;
//    }

    // Tìm tài khoản theo tên đăng nhập
    public TaiKhoan getTaiKhoanTheoMa(String tenTaiKhoan) {
        for (TaiKhoan taiKhoan : dsTaiKhoan) {
            if (taiKhoan.getTenTaiKhoan().equals(tenTaiKhoan)) {
                return taiKhoan;
            }
        }
        return taiKhoanDAL.getTaiKhoanTheoTen(tenTaiKhoan); // Nếu không tìm thấy trong bộ nhớ thì tìm từ cơ sở dữ liệu
    }

    // Tải tất cả tài khoản từ cơ sở dữ liệu vào danh sách tạm thời
    private void loadAllTaiKhoan() {
        List<TaiKhoan> allTaiKhoan = taiKhoanDAL.getAllTaiKhoan(); 
        dsTaiKhoan.addAll(allTaiKhoan); // Thêm vào bộ nhớ
    }
}
