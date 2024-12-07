/*
    *MayHotel_09  day creative: 11/27/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here   
     */


package view.component;

import constant.CommonConstants;
import dal.PhongDAL;
import entity.Phong;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PhongDonDatPhongPanel extends JPanel {
    private JButton btnXoa, btnSua;
    Phong phong;
    public JButton getBtnXoa() {
        return btnXoa;
    }
    public JButton getBtnSua() {
        return btnSua;
    }

    public PhongDonDatPhongPanel(Phong phong) {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel container = new JPanel(new BorderLayout());
        container.setPreferredSize(new Dimension(300,80));
        this.phong = phong;
        container.setBorder(BorderFactory.createTitledBorder("Phong"));
//        tạo các thành phần

        // lấy tên loại phòng từ mã phòng
        Box boxContain = Box.createHorizontalBox();

        String tenLoaiPhong = new PhongDAL().getLoaiPhongTheoMaPhong(phong.getMaPhong()).getTenLoaiPhong();
        JLabel lblLoaiPhong = new JLabel(tenLoaiPhong);
        lblLoaiPhong.setFont(new Font("Arial", Font.ITALIC, 16));
        lblLoaiPhong.setForeground(Color.GRAY);
        JLabel lblTenPhong = new JLabel(phong.getTenPhong());
        lblTenPhong.setHorizontalAlignment(SwingConstants.CENTER);
        lblTenPhong.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
        btnXoa = new JButton("Xóa");
        customButton(btnXoa);
        btnSua = new JButton("Sửa");
        customButton(btnSua);

        boxContain.add(Box.createHorizontalStrut(10));
        boxContain.add(lblLoaiPhong);
        boxContain.add(Box.createHorizontalGlue());
        boxContain.add(btnSua);
        boxContain.add(Box.createHorizontalStrut(10));
        boxContain.add(btnXoa);

//        thêm các thành phần vào panel
        container.add(boxContain, BorderLayout.CENTER);
        container.add(lblTenPhong, BorderLayout.SOUTH);
        this.add(container);
    }
    public void customButton(JButton btn) {
        btn.setOpaque(true);
        btn.setPreferredSize(new Dimension(64,28));
        btn.setForeground(CommonConstants.ORANGE);
        btn.setBorder(new LineBorder(CommonConstants.ORANGE, 2));
        btn.setBackground(Color.WHITE);
        btn.setFocusPainted(false);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.add(new PhongDonDatPhongPanel(new PhongDAL().getAllPhong().get(0)));
        frame.setSize(400,400);
    }

}
