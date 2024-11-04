package entity;

public class RoomOption {
    private String maDon;
    private String ngayTao;
    private String phuongThucThanhToan;
    private String trangThaiDonDatPhong;
    private String trangThaiDatCoc;
    private String maNV;
    private String maKH;
    private double tongTien;
    private String moTa;
    private String ngayNhanPhong;
    private String ngayTra;

    public RoomOption(String maDon, String ngayTao, String phuongThucThanhToan,
                      String trangThaiDonDatPhong, String trangThaiDatCoc,
                      String maNV, String maKH, double tongTien,
                      String moTa, String ngayNhanPhong, String ngayTra) {
        this.maDon = maDon;
        this.ngayTao = ngayTao;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.trangThaiDonDatPhong = trangThaiDonDatPhong;
        this.trangThaiDatCoc = trangThaiDatCoc;
        this.maNV = maNV;
        this.maKH = maKH;
        this.tongTien = tongTien;
        this.moTa = moTa;
        this.ngayNhanPhong = ngayNhanPhong;
        this.ngayTra = ngayTra;
    }

    @Override
    public String toString() {
        // Hiển thị thông tin bạn muốn trong JComboBox
        return maDon + " - " + ngayTao; // Bạn có thể thay đổi định dạng này theo ý muốn
    }
    
    // Getter và Setter nếu cần
}

