package bus;

import dal.PhieuThuChiDAL;
import entity.PhieuThuChi;

import java.util.ArrayList;
import java.util.List;

public class PhieuThuChi_BUS {
    private PhieuThuChiDAL phieuThuChiDAL;
    private List<PhieuThuChi> dsPhieuThuChi;

    public PhieuThuChi_BUS() {
        phieuThuChiDAL = new PhieuThuChiDAL();
        dsPhieuThuChi = new ArrayList<>();
    }

    // Lấy tất cả phiếu thu chi (có thể lấy từ bộ nhớ nếu có, hoặc từ cơ sở dữ liệu nếu chưa)
    public List<PhieuThuChi> getAllPhieuThuChi() {
        if (dsPhieuThuChi.isEmpty()) {
            // Lấy từ cơ sở dữ liệu nếu danh sách rỗng
            dsPhieuThuChi = phieuThuChiDAL.getAllPhieuThuChi();
        }
        return dsPhieuThuChi;
    }

    // Thêm phiếu thu chi và cập nhật danh sách trong bộ nhớ
    public boolean themPhieuThuChi(PhieuThuChi phieuThuChi) {
        if (phieuThuChi == null) {
            throw new IllegalArgumentException("Phiếu thu chi không hợp lệ");
        }
        boolean success = phieuThuChiDAL.themPhieuThuChi(phieuThuChi);
        if (success) {
            dsPhieuThuChi.add(phieuThuChi); // Thêm vào bộ nhớ
        }
        return success;
    }

    // Cập nhật phiếu thu chi và cập nhật danh sách trong bộ nhớ
    public boolean suaPhieuThuChi(String maPhieuThuChi, PhieuThuChi phieuThuChi) {
        if (phieuThuChi == null || !dsPhieuThuChi.contains(phieuThuChi)) {
            throw new IllegalArgumentException("Phiếu thu chi không tồn tại trong danh sách");
        }

        // Thực hiện cập nhật trong cơ sở dữ liệu thông qua DAL
        boolean success = phieuThuChiDAL.suaPhieuThuChi(maPhieuThuChi, phieuThuChi);
        
        if (success) {
            // Cập nhật danh sách trong bộ nhớ
            for (int i = 0; i < dsPhieuThuChi.size(); i++) {
                PhieuThuChi p = dsPhieuThuChi.get(i);
                if (p.getMaPhieu().equals(maPhieuThuChi)) {
                    dsPhieuThuChi.set(i, phieuThuChi); // Cập nhật phần tử
                    break;
                }
            }
        }

        return success;
    }



    // Xóa phiếu thu chi và cập nhật danh sách trong bộ nhớ
    public boolean xoaPhieuThuChi(String maPhieu) {
        if (maPhieu == null || maPhieu.isEmpty()) {
            throw new IllegalArgumentException("Mã phiếu không hợp lệ");
        }

        boolean success = phieuThuChiDAL.xoaPhieuThuChi(maPhieu);
        if (success) {
            // Xóa khỏi bộ nhớ
            dsPhieuThuChi.removeIf(p -> p.getMaPhieu().equals(maPhieu));
        }
        return success;
    }

    // Kiểm tra phiếu thu chi có tồn tại trong danh sách bộ nhớ không
    public PhieuThuChi getPhieuThuChiByMaPhieu(String maPhieu) {
        for (PhieuThuChi phieu : dsPhieuThuChi) {
            if (phieu.getMaPhieu().equals(maPhieu)) {
                return phieu;
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }
}
