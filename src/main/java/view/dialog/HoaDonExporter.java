package view.dialog;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javax.swing.*;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;

public class HoaDonExporter {

    public static void exportHoaDon(JTable table_1, JButton btnXuat) {
        btnXuat.addActionListener(e -> {
            // Kiểm tra xem có dòng nào được chọn trong bảng không
            int selectedRow = table_1.getSelectedRow();
            if (selectedRow != -1) {
                // Lấy thông tin từ bảng
                String maHoaDon = table_1.getValueAt(selectedRow, 0).toString(); // Mã hóa đơn
                String trangThai = table_1.getValueAt(selectedRow, 1).toString(); // Trạng thái
                String thanhTien = table_1.getValueAt(selectedRow, 2).toString(); // Thành tiền
                String maNhanVien = table_1.getValueAt(selectedRow, 3).toString(); // Mã nhân viên
                String maKhuyenMai = table_1.getValueAt(selectedRow, 4).toString(); // Mã khuyến mãi
                String maDonDatPhong = table_1.getValueAt(selectedRow, 5).toString(); // Mã đơn đặt phòng
                String ngayTao = table_1.getValueAt(selectedRow, 6).toString(); // Ngày tạo

                // Tạo file PDF
                try {
                    // Đường dẫn file PDF
                    String fileName = "HoaDon_" + maHoaDon + ".pdf";
                    File pdfFile = new File(fileName);

                    // Tạo đối tượng Document để viết nội dung PDF
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(pdfFile));

                    // Sử dụng font tùy chỉnh
                    try {
                        BaseFont baseFont = BaseFont.createFont("C:\\Users\\MSI\\Desktop\\IUH\\PTUD\\Roboto\\Roboto-Regular.ttf", 
                        BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                        Font font = new Font(baseFont, 12);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Không thể tải font: " + ex.getMessage(), "Lỗi Font", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }


                    // Mở document để viết
                    document.open();

                    // Thêm tiêu đề và thông tin hóa đơn vào file PDF
                    document.add(new Paragraph("                          HOA DON", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
                    document.add(new Paragraph("-----------------------------------------------------------------"));
                    document.add(new Paragraph("Mã Hóa Đơn: " + maHoaDon));
                    document.add(new Paragraph("Trạng Thái: " + trangThai));
                    document.add(new Paragraph("Thành Tiền: " + thanhTien));
                    document.add(new Paragraph("Ngày Tạo: " + ngayTao));
                    document.add(new Paragraph("\n"));

                    // Thêm bảng chi tiết vào PDF
                    PdfPTable table = new PdfPTable(4); 
                    table.setWidthPercentage(100);
                    table.setSpacingBefore(10f);
                    table.setSpacingAfter(10f);

                    // Thêm header cho bảng
                    table.addCell("Mã Hóa Đơn");
                    table.addCell("Trạng Thái");
                    table.addCell("Thành Tiền");
                    table.addCell("Ngày Tạo");

                    // Thêm dữ liệu từ bảng vào bảng trong PDF
                    table.addCell(maHoaDon);
                    table.addCell(trangThai);
                    table.addCell(thanhTien);
                    table.addCell(ngayTao);

                    // Thêm bảng vào document
                    document.add(table);

                    // Đóng file PDF sau khi thêm hết thông tin
                    document.close();

                    // Hiển thị thông báo xuất file PDF thành công
                    JOptionPane.showMessageDialog(null, "Hóa đơn đã được xuất ra file PDF thành công! File được lưu tại: " + fileName, 
                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                    // Mở file PDF ngay sau khi tạo
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(pdfFile);
                    } else {
                        JOptionPane.showMessageDialog(null, "Không hỗ trợ mở file PDF tự động trên hệ thống này!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi xuất hóa đơn! Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Nếu không có dòng nào được chọn trong bảng, hiển thị thông báo lỗi
                JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn cần xuất!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
