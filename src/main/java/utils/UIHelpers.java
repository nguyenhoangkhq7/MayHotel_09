/*
    *MayHotel  day creative: 10/22/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here   
     */


package utils;

import com.toedter.calendar.JDateChooser;
import constraints.CONSTRAINTS;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class UIHelpers {


//    Custom button
    public static void set_Button_Orange_Outline_Style(JButton btn) {
        btn.setOpaque(true);
        btn.setPreferredSize(CONSTRAINTS.BUTTON_SIZE);
        btn.setForeground(CONSTRAINTS.ORANGE);
        btn.setBorder(new LineBorder(CONSTRAINTS.ORANGE, 2));
        btn.setBackground(Color.WHITE);
        btn.setFocusPainted(false);
    }
    public static void set_Button_Blue_Style(JButton btn) {
        btn.setOpaque(true);
        btn.setPreferredSize(CONSTRAINTS.BUTTON_SIZE);
        btn.setMinimumSize(CONSTRAINTS.BUTTON_SIZE);
        btn.setMaximumSize(CONSTRAINTS.BUTTON_SIZE);
        btn.setForeground(Color.WHITE);
        btn.setBackground(CONSTRAINTS.BLUE);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
    }

    public static void set_Orange_Blue_Style(JButton btn) {
        btn.setOpaque(true);
        btn.setPreferredSize(CONSTRAINTS.BUTTON_SIZE);
        btn.setMinimumSize(CONSTRAINTS.BUTTON_SIZE);
        btn.setMaximumSize(CONSTRAINTS.BUTTON_SIZE);
        btn.setForeground(Color.WHITE);
        btn.setBackground(CONSTRAINTS.ORANGE);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
    }
    public static void set_Small_Button_Style(JButton btn) {
        btn.setOpaque(true);
        btn.setPreferredSize(CONSTRAINTS.BUTTON_SIZE);
        btn.setMinimumSize(CONSTRAINTS.BUTTON_SIZE);
        btn.setMaximumSize(CONSTRAINTS.BUTTON_SIZE);
        btn.setForeground(Color.BLACK);
        btn.setBackground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 10));
        btn.setFocusPainted(false);
    }

//    create component
private static Box createLabelAndComponent(String labelTitle, JComponent component) {
    Box box = Box.createVerticalBox(); // Tạo Box dọc

    // Tạo JLabel với tiêu đề
    JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel label = new JLabel(labelTitle);
    label.setFont(new Font("Arial", Font.PLAIN, CONSTRAINTS.TEXT_SIZE));
    labelPanel.add(label);
    box.add(labelPanel);

    // Tạo JPanel với padding 10px cho component và thêm vào box
    JPanel componentPanel = new JPanel(new BorderLayout());
    componentPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding 10px
    componentPanel.add(component, BorderLayout.CENTER);

    box.add(componentPanel);
    return box;
}

    public static Box create_Form_Label_JTextField(String labelTitle, JTextField jtf) {
        jtf.setPreferredSize(CONSTRAINTS.INPUT_SIZE); // Thiết lập kích thước
        return createLabelAndComponent(labelTitle, jtf); // Trả về Box chứa label và JTextField
    }

    public static Box create_Form_Label_JDateChooser(String labelTitle, JDateChooser jdc) {
        jdc.setDateFormatString("dd/MM/yyyy"); // Định dạng ngày tháng
        jdc.setPreferredSize(CONSTRAINTS.INPUT_SIZE);
        jdc.getJCalendar().setPreferredSize(CONSTRAINTS.DATEPICKER_SIZE); // Thay đổi kích thước popup lịch
        return createLabelAndComponent(labelTitle, jdc);
    }

    public static Box create_Form_Label_JComboBox(String labelTitle, JComboBox cbo) {
        cbo.setPreferredSize(new Dimension());
        return createLabelAndComponent(labelTitle, cbo);
    }
    public static Box create_Form_Label_JTextArea(String labelTitle, JTextArea jta) {
        jta.setPreferredSize(CONSTRAINTS.TEXTAREA_SIZE); // Thiết lập kích thước cho JTextArea
        jta.setLineWrap(true); // Cho phép tự động xuống dòng
        jta.setWrapStyleWord(true); // Tự động xuống dòng tại các từ

        return createLabelAndComponent(labelTitle, jta); // Trả về Box chứa label và JTextArea
    }

    public static Box create_Form_Label_Checkbox (String labelTitle, JCheckBox checkBox) {
        Box box = Box.createHorizontalBox();
        JLabel lbl = new JLabel(labelTitle);
        lbl.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, CONSTRAINTS.TEXT_SIZE));
        box.add(checkBox);
        checkBox.setPreferredSize(new Dimension(50, 50));
        box.add(lbl);
        return box;
    }

    public static JPanel create_Title_Panel(String title) {
        JLabel lbl = new JLabel(title);
        lbl.setFont(new Font("Arial", Font.BOLD, CONSTRAINTS.TEXT_TITLE_SIZE));
        JPanel tmp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tmp.add(lbl);
        tmp.setOpaque(false);
        return tmp;
    }

    public static JPanel create_Phong_Da_Them(String tenPhong, String loaiPhong) {
        JPanel jpn = new JPanel(new GridLayout(1,3));
        jpn.setBorder(new EmptyBorder(10,10,10,10));
        jpn.setBackground(new Color(191,190,190,255));
        JLabel lblTenPhong = new JLabel(tenPhong); lblTenPhong.setFont(new Font("Arial", Font.BOLD,CONSTRAINTS.TEXT_SIZE));
        JLabel lblLoaiPhong = new JLabel(loaiPhong); lblLoaiPhong.setFont(new Font("Arial", Font.BOLD,CONSTRAINTS.TEXT_SIZE));
        jpn.add(lblTenPhong);
        jpn.add(lblLoaiPhong);

        Box boxContainButton = Box.createHorizontalBox();
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");
        boxContainButton.add(btnSua);
        boxContainButton.add(btnXoa);

        jpn.add(boxContainButton);

        return jpn;
    }

    //    hàm tạo title border
    public static TitledBorder create_Title_Border(String title, Color color) {
        TitledBorder titledBorder = new TitledBorder(BorderFactory.createLineBorder(CONSTRAINTS.ORANGE, 1), title, TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), color);
        return titledBorder;
    }
}
