package bus;

import dal.DonDatPhongDAL;

public class DonDatPhongBUS {
    public static String generateOrderCode() {
        String lastOrder = new DonDatPhongDAL().getLastDDP(); // Lấy mã đơn cuối cùng
        int newOrderNumber = 1; // Mặc định bắt đầu từ 1 nếu không có mã đơn nào

        if (lastOrder != null) {
            // Tách phần số từ mã đơn
            String numberPart = lastOrder.substring(3); // Bỏ qua "DDP"
            newOrderNumber = Integer.parseInt(numberPart) + 1; // Tăng số lên 1
        }

        // Tạo mã đơn mới theo định dạng "DDPXXXXXX"
        return String.format("DDP%06d", newOrderNumber); // Định dạng thành 6 chữ số
    }
}
