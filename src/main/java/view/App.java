/*
    *MayHotel  day creative: 9/26/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here   
     */


package view;

import dal.NhanVienDAL;
import entity.NhanVien;

import javax.swing.*;
import java.awt.*;

// nơi chạy chương trình
public class App {
    public static NhanVien nhanVienDangTruc ;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginGUI frame = new LoginGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
