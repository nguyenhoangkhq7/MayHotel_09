package bus;

import dal.KhachHangDAL;
import entity.KhachHang;

import java.util.ArrayList;

public class KhachHang_BUS {
    private KhachHangDAL khachHangDAL;

    public KhachHang_BUS() {
        khachHangDAL = new KhachHangDAL();
    }

    public ArrayList<KhachHang> getAllKhachHang() {
        return khachHangDAL.getAllKhachHang();
    }

    public KhachHang getKhachHangTheoMa(String maKH) {
        return khachHangDAL.getKhachHangTheoMa(maKH);
    }

    public boolean themKhachHang(KhachHang khachHang) {
        return khachHangDAL.themKhachHang(khachHang);
    }

    public boolean suaKhachHang(String maKH, KhachHang khachHang) {
        return khachHangDAL.suaKhachHang(maKH, khachHang);
    }

    public boolean xoaKhachHang(String maKH) {
        return khachHangDAL.xoaKhachHang(maKH);
    }
}
