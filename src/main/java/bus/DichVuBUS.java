package bus;

import dal.DichVuDAL;
import entity.DichVu;
import java.util.ArrayList;

public class DichVuBUS {
    private DichVuDAL dichVuDAL;

    public DichVuBUS() {
        dichVuDAL = new DichVuDAL();
    }

    // Lấy danh sách tất cả dịch vụ
    public ArrayList<DichVu> getAllDichVu() {
        return dichVuDAL.getAllDichVu();
    }

    // Thêm dịch vụ mới (kiểm tra dữ liệu)
    public boolean themDichVu(DichVu dichVu) {
        if (dichVu != null && dichVu.getDonGia() > 0 && dichVu.getSoLuongTon() >= 0) {
            return dichVuDAL.themDichVu(dichVu);
        }
        return false;
    }

    // Sửa dịch vụ
    public boolean suaDichVu(DichVu dichVu) {
        if (dichVu != null && dichVu.getMaDichVu() != null && !dichVu.getMaDichVu().isEmpty()) {
            return dichVuDAL.suaDichVu(dichVu);
        }
        return false;
    }
}
