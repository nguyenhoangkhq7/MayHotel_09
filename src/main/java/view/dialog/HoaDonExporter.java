package view.dialog;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;

import javax.swing.*;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;

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
    	        String maDonDat = table_1.getValueAt(selectedRow,5).toString();
    	        String tenKhachHang = table_1.getValueAt(selectedRow, 6).toString(); // Tên khách hàng (cột 6)
    	        String ngayTao = table_1.getValueAt(selectedRow, 7).toString(); // Ngày tạo (cột 7)
    	        String ngayNhanPhong = table_1.getValueAt(selectedRow, 8).toString();
                String ngayTraPhong = table_1.getValueAt(selectedRow, 9).toString();// Ngày tạo (cột 7)

    	        // Tạo file PDF
    	        try {
    	            // Đường dẫn file PDF
    	            String fileName = "HoaDon_" + maHoaDon + ".pdf";
    	            File pdfFile = new File(fileName);

    	            // Tạo đối tượng Document để viết nội dung PDF
    	            Document document = new Document(PageSize.A4, 36, 36, 36, 36); // Thiết lập kích thước và lề
    	            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));

    	            // Mở document để viết nội dung
    	            document.open();

    	            // Sử dụng font tùy chỉnh
    	            BaseFont baseFont = BaseFont.createFont(
    	                    "C:\\Users\\MSI\\Desktop\\IUH\\PTUD\\Roboto\\Roboto-Regular.ttf",
    	                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    	            Font titleFont = new Font(baseFont, 16, Font.BOLD);
    	            Font headerFont = new Font(baseFont, 12, Font.BOLD);
    	            Font normalFont = new Font(baseFont, 12);
    	            Font footerFont = new Font(baseFont, 10, Font.ITALIC);

    	            // Thêm tiêu đề hóa đơn
    	            Paragraph hotelName = new Paragraph("MAY HOTEL", titleFont);
    	            hotelName.setAlignment(Element.ALIGN_LEFT);
    	            hotelName.setSpacingAfter(10f);
    	            document.add(hotelName);

    	            Paragraph title = new Paragraph("HÓA ĐƠN THANH TOÁN", titleFont);
    	            title.setAlignment(Element.ALIGN_CENTER);
    	            title.setSpacingAfter(10f);
    	            document.add(title);

    	            // Thêm thông tin cơ bản của hóa đơn với đường kẻ
    	            Paragraph info = new Paragraph();
    	            info.setFont(normalFont);
    	            info.add("Mã Hóa Đơn: " + maHoaDon + "\n");
    	            info.add("Trạng Thái: " + trangThai + "\n");
    	            info.add("Ngày Tạo: " + ngayTao + "\n");
    	            info.add("Tên Khách Hàng: " + tenKhachHang + "\n");
    	            info.setSpacingAfter(10f);
    	            document.add(info);
    	            document.add(new LineSeparator()); // Thêm đường kẻ ngang

    	            // Thêm bảng chi tiết hóa đơn
    	            PdfPTable table = new PdfPTable(7); // 7 cột bao gồm "Mã Hóa Đơn", "Trạng Thái", "Ngày Nhận Phòng", "Ngày Trả Phòng", "Thành Tiền", "Ngày Tạo", "Tổng Tiền"
    	            table.setWidthPercentage(100);
    	            table.setSpacingBefore(10f);
    	            table.setWidths(new float[]{3, 2, 3, 3, 3, 3, 3}); // Đặt tỷ lệ cho từng cột

    	            // Thêm header cho bảng
    	            PdfPCell headerCell;
    	            String[] headers = {"Mã Hóa Đơn", "Trạng Thái", "Ngày Nhận Phòng", "Ngày Trả Phòng", "Thành Tiền", "Ngày Tạo", "Tổng Tiền"};
    	            for (String header : headers) {
    	                headerCell = new PdfPCell(new Phrase(header, headerFont));
    	                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	                headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
    	                headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    	                headerCell.setPadding(5f);
    	                table.addCell(headerCell);
    	            }

    	            // Thêm dữ liệu vào bảng
    	            table.addCell(new PdfPCell(new Phrase(maHoaDon, normalFont)));
    	            table.addCell(new PdfPCell(new Phrase(trangThai, normalFont)));

    	            // Thêm Ngày Nhận Phòng và Ngày Trả Phòng
    	            
    	            table.addCell(new PdfPCell(new Phrase(ngayNhanPhong, normalFont)));
    	            table.addCell(new PdfPCell(new Phrase(ngayTraPhong, normalFont)));

    	            table.addCell(new PdfPCell(new Phrase(thanhTien, normalFont)));
    	            table.addCell(new PdfPCell(new Phrase(ngayTao, normalFont)));

    	            // Tính toán Tổng tiền và thêm vào cột
    	            double tongTien = Double.parseDouble(thanhTien); // Thay bằng cách tính toán phù hợp
    	            table.addCell(new PdfPCell(new Phrase(String.valueOf(tongTien), normalFont)));

    	            // Thêm bảng vào document
    	            document.add(table);


    	            // Thêm khu vực chữ ký
    	            Paragraph signatureSection = new Paragraph();
    	            signatureSection.setSpacingBefore(20f);
    	            signatureSection.add("Nhân viên ký tên                                                                           Khách hàng ký tên\n\n\n\n");
    	            signatureSection.setFont(normalFont);
    	            document.add(signatureSection);

    	            // Thêm dòng chữ ĐÃ BAO GỒM VAT
    	            Paragraph vatNotice = new Paragraph("ĐÃ BAO GỒM VAT", footerFont);
    	            vatNotice.setAlignment(Element.ALIGN_CENTER);
    	            vatNotice.setSpacingBefore(20f);
    	            document.add(vatNotice);

    	            // Thêm lời cảm ơn
    	            Paragraph footer = new Paragraph("Cảm ơn quý khách đã sử dụng dịch vụ của chúng tôi!", footerFont);
    	            footer.setAlignment(Element.ALIGN_CENTER);
    	            footer.setSpacingBefore(10f);
    	            document.add(footer);

    	            // Đóng file PDF
    	            document.close();

    	            // Hiển thị thông báo thành công
    	            JOptionPane.showMessageDialog(null,
    	                    "Hóa đơn đã được xuất ra file PDF thành công! File được lưu tại: " + fileName,
    	                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);

    	            // Mở file PDF ngay sau khi tạo
    	            if (Desktop.isDesktopSupported()) {
    	                Desktop.getDesktop().open(pdfFile);
    	            } else {
    	                JOptionPane.showMessageDialog(null,
    	                        "Không hỗ trợ mở file PDF tự động trên hệ thống này!",
    	                        "Thông báo", JOptionPane.WARNING_MESSAGE);
    	            }

    	        } catch (Exception ex) {
    	            ex.printStackTrace();
    	            JOptionPane.showMessageDialog(null,
    	                    "Có lỗi xảy ra khi xuất hóa đơn! Vui lòng thử lại.",
    	                    "Lỗi", JOptionPane.ERROR_MESSAGE);
    	        }


    	    } else {
    	        // Nếu không có dòng nào được chọn trong bảng, hiển thị thông báo lỗi
    	        JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn cần xuất!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    	    }
    	});

    }
}
