/*
    *MayHotel  day creative: 11/4/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here   
     */


package bus;

import dal.KhachHangDAL;

public class KhachHangBUS {
    public String generateCustomerCode() {
        String lastCode = new KhachHangDAL().getLastKhachHang();
        if (lastCode != null) {
            int lastNumber = Integer.parseInt(lastCode.substring(2));
            lastNumber++;
            return String.format("KH%03d", lastNumber);
        } else {
            return "KH001";
        }
    }
}
