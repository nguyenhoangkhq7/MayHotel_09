/*
    *MayHotel  day creative: 10/22/2024
    version: 2023.2  IntelliJ IDEA
    author: Nguyễn Hoàng Khang  */
    /*
       *class description:
            write description right here   
     */


package helper;

import com.toedter.calendar.JDateChooser;
import constant.CommonConstants;
import custom.TextFieldCustom;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class UIHelpers {


//    Custom button
    public static JButton createButtonStyle(String text, Dimension size, Color foreground, Color background) {
        JButton btn = new JButton(text);
        btn.setOpaque(true);
        btn.setPreferredSize(size);
        btn.setMinimumSize(size);
        btn.setMaximumSize(size);
        btn.setForeground(foreground);
        btn.setBackground(background);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBorder(new LineBorder(CommonConstants.ORANGE, 2));
        btn.setFocusPainted(false);
        return btn;
    }

//    create component
private static Box createLabelAndComponent(String labelTitle, JComponent component) {
    Box box = Box.createVerticalBox(); // Tạo Box dọc

    // Tạo JLabel với tiêu đề
    JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel label = new JLabel(labelTitle);
    label.setFont(new Font("Arial", Font.PLAIN, CommonConstants.TEXT_SIZE));
    labelPanel.add(label);
    box.add(labelPanel);

    // Tạo JPanel với padding 10px cho component và thêm vào box
    JPanel componentPanel = new JPanel(new BorderLayout());
    componentPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding 10px
    componentPanel.add(component, BorderLayout.CENTER);

    box.add(componentPanel);
    return box;
}

    public static Box create_Form_Label_JTextField(String labelTitle, TextFieldCustom jtf) {
        jtf.setPreferredSize(CommonConstants.INPUT_SIZE); // Thiết lập kích thước
        return createLabelAndComponent(labelTitle, jtf); // Trả về Box chứa label và JTextField
    }

    public static Box create_Form_Label_JDateChooser(String labelTitle, JDateChooser jdc) {
        jdc.setDateFormatString("dd/MM/yyyy"); // Định dạng ngày tháng
        jdc.setPreferredSize(CommonConstants.INPUT_SIZE);
        jdc.getJCalendar().setPreferredSize(CommonConstants.DATEPICKER_SIZE); // Thay đổi kích thước popup lịch
        return createLabelAndComponent(labelTitle, jdc);
    }

    public static Box create_Form_Label_JComboBox(String labelTitle, JComboBox cbo) {
        cbo.setPreferredSize(CommonConstants.INPUT_SIZE);
        return createLabelAndComponent(labelTitle, cbo);
    }
    public static Box create_Form_Label_JTextArea(String labelTitle, JTextArea jta) {
        jta.setPreferredSize(CommonConstants.TEXTAREA_SIZE); // Thiết lập kích thước cho JTextArea
        jta.setLineWrap(true); // Cho phép tự động xuống dòng
        jta.setWrapStyleWord(true); // Tự động xuống dòng tại các từ

        return createLabelAndComponent(labelTitle, jta); // Trả về Box chứa label và JTextArea
    }

    public static Box create_Form_Label_Checkbox (String labelTitle, JCheckBox checkBox) {
        Box box = Box.createHorizontalBox();
        JLabel lbl = new JLabel(labelTitle);
        lbl.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, CommonConstants.TEXT_SIZE));
        box.add(checkBox);
        checkBox.setPreferredSize(new Dimension(50, 50));
        box.add(lbl);
        return box;
    }

    public static JPanel create_Title_Panel(String title) {
        JLabel lbl = new JLabel(title);
        lbl.setFont(new Font("Arial", Font.BOLD, CommonConstants.TEXT_TITLE_SIZE));
        JPanel tmp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tmp.add(lbl);
        tmp.setOpaque(false);
        return tmp;
    }

    public static JPanel create_Phong_Da_Them(String tenPhong, String loaiPhong) {
        JPanel jpn = new JPanel(new GridLayout(1,3,20,10));
        jpn.setBorder(new EmptyBorder(10,10,10,10));
        jpn.setBackground(new Color(191,190,190,255));
        JLabel lblTenPhong = new JLabel(tenPhong); lblTenPhong.setFont(new Font("Arial", Font.BOLD, CommonConstants.TEXT_SIZE));
        JLabel lblLoaiPhong = new JLabel(loaiPhong); lblLoaiPhong.setFont(new Font("Arial", Font.BOLD, CommonConstants.TEXT_SIZE));
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
        TitledBorder titledBorder = new TitledBorder(BorderFactory.createLineBorder(CommonConstants.ORANGE, 1), title, TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), color);
        return titledBorder;
    }
}
