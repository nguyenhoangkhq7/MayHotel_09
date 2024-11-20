package bus;

import java.util.ArrayList;
import dal.NhanVienDAL;
import entity.NhanVien;

public class NhanVienBUS {
    private ArrayList<NhanVien> dsNhanVien;
    private NhanVienDAL nhanVienDAL;

    public NhanVienBUS() {
        nhanVienDAL = new NhanVienDAL();
        dsNhanVien = new ArrayList<>();
    }

    // Lấy tất cả nhân viên và lưu vào bộ nhớ tạm
    public ArrayList<NhanVien> getAllNhanVien() {
        // Nếu dsNhanVien còn trống, ta tải dữ liệu từ cơ sở dữ liệu
        if (dsNhanVien.isEmpty()) {
            dsNhanVien = nhanVienDAL.getAllNhanVien();
        }
        return dsNhanVien;
    }

    // Thêm nhân viên và cập nhật danh sách bộ nhớ tạm
    public boolean themNhanVien(NhanVien nhanVien) {
        boolean result = nhanVienDAL.themNhanVien(nhanVien);
        if (result) {
            dsNhanVien.add(nhanVien); // Thêm vào danh sách bộ nhớ tạm
        }
        return result;
    }

    // Cập nhật nhân viên và cập nhật danh sách bộ nhớ tạm
    public boolean suaNhanVien(NhanVien nhanVien) {
        boolean result = nhanVienDAL.suaNhanVien(nhanVien);
        if (result) {
            // Tìm nhân viên trong bộ nhớ tạm và cập nhật thông tin
            for (int i = 0; i < dsNhanVien.size(); i++) {
                if (dsNhanVien.get(i).getMaNV().equals(nhanVien.getMaNV())) {
                    dsNhanVien.set(i, nhanVien);
                    break;
                }
            }
        }
        return result;
    }

    // Xóa nhân viên và cập nhật danh sách bộ nhớ tạm
    /*public boolean xoaNhanVien(String maNV) {
        boolean result = nhanVienDAL.xoaNhanVien(maNV);
        if (result) {
            // Xóa nhân viên từ bộ nhớ tạm
            dsNhanVien.removeIf(nv -> nv.getMaNV().equals(maNV));
        }
        return result;
    }*/
}
