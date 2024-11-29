package view.panel;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.*;

import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import dal.PhongDAL;
import entity.Phong;

public class ChuyenPhongPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JComboBox<String> comboBox;
    private JLabel lblCurrentRoom, lblTotalCost;
    private JButton btnChuyenPhong, btnTinhTien;
    private JDateChooser jdcNgayNhan, jdcNgayTra;

    public ChuyenPhongPanel(String currentRoom) {
        setLayout(null);
        
        initializeComponents(currentRoom);
        loadRoomOptions();
    }

    private void initializeComponents(String currentRoom) {
        // Tiêu đề
        JLabel lblSelectRoom = new JLabel("Chọn phòng chuyển đến");
        lblSelectRoom.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblSelectRoom.setBounds(12, 28, 465, 15);
        add(lblSelectRoom);
        
        // Phòng hiện tại
        JLabel lblCurrentRoomLabel = new JLabel("Phòng hiện tại :");
        lblCurrentRoomLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblCurrentRoomLabel.setBounds(39, 71, 108, 15);
        add(lblCurrentRoomLabel);
        
        lblCurrentRoom = new JLabel(currentRoom);
        lblCurrentRoom.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblCurrentRoom.setBounds(159, 71, 57, 15);
        add(lblCurrentRoom);
        
        // Phòng chuyển đến
        JLabel lblNewRoom = new JLabel("Phòng chuyển đến");
        lblNewRoom.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewRoom.setBounds(39, 114, 118, 15);
        add(lblNewRoom);
        
        comboBox = new JComboBox<>();
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
        comboBox.setBounds(159, 110, 159, 23);
        add(comboBox);
        
        // Nút chuyển phòng
        btnChuyenPhong = new JButton("Chuyển Phòng");
        btnChuyenPhong.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnChuyenPhong.setBounds(159, 160, 159, 30);
        btnChuyenPhong.addActionListener(e -> handleRoomChange());
        add(btnChuyenPhong);
        
        // Nút tính tiền
        btnTinhTien = new JButton("Tính tiền");
        btnTinhTien.setBackground(new Color(243, 125, 0));
        btnTinhTien.setForeground(Color.WHITE);
        btnTinhTien.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnTinhTien.setBounds(12, 200, 306, 30);
        btnTinhTien.addActionListener(e -> calculateTotalCost());
        add(btnTinhTien);
        
        // Nhãn hiển thị tổng tiền
        lblTotalCost = new JLabel("Tổng tiền: 0 VND");
        lblTotalCost.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTotalCost.setForeground(Color.RED);
        lblTotalCost.setBounds(245, 68, 306, 20);
        add(lblTotalCost);
        
        // Ngày nhận và trả phòng
        JLabel lblNgayNhan = new JLabel("Ngày nhận:");
        lblNgayNhan.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNgayNhan.setBounds(12, 280, 100, 15);
        add(lblNgayNhan);
        
        jdcNgayNhan = new JDateChooser();
        jdcNgayNhan.setBounds(120, 280, 150, 20);
        add(jdcNgayNhan);
        
        JLabel lblNgayTra = new JLabel("Ngày trả:");
        lblNgayTra.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNgayTra.setBounds(290, 280, 100, 15);
        add(lblNgayTra);
        
        jdcNgayTra = new JDateChooser();
        jdcNgayTra.setBounds(370, 280, 150, 20);
        add(jdcNgayTra);
    }

    private void loadRoomOptions() {
        ArrayList<Phong> dsPhong = new PhongDAL().getAllPhong();
        for (Phong phong : dsPhong) {
            comboBox.addItem(phong.getMaPhong());
        }
    }

    private void handleRoomChange() {
        String selectedRoom = (String) comboBox.getSelectedItem();
        String currentRoom = lblCurrentRoom.getText();
        
        if (!currentRoom.equals(selectedRoom)) {
            // Thực hiện chuyển phòng trong cơ sở dữ liệu (cần thêm logic thực tế)
            JOptionPane.showMessageDialog(this, "Chuyển từ " + currentRoom + " đến " + selectedRoom + " thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phòng khác!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void calculateTotalCost() {
        try {
            Date checkInDate = jdcNgayNhan.getDate();
            Date checkOutDate = jdcNgayTra.getDate();

            if (checkInDate == null || checkOutDate == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn cả ngày nhận và trả!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            long diff = checkOutDate.getTime() - checkInDate.getTime();
            long daysBetween = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

            if (daysBetween < 0) {
                JOptionPane.showMessageDialog(this, "Ngày trả phải sau ngày nhận!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double roomRate = 500000; // Giả sử giá phòng mỗi ngày là 500.000 VND
            double totalCost = daysBetween * roomRate;

            // Hiển thị tổng tiền trong lblTotalCost
            NumberFormat formatter = NumberFormat.getInstance(Locale.getDefault());
            String formattedTotal = formatter.format(totalCost) + " VND";
            lblTotalCost.setText("Tổng tiền: " + formattedTotal);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra. Vui lòng kiểm tra lại thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void createAndShowGUI(String currentRoom) {
        JFrame frame = new JFrame("Chuyển Phòng");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 600, 400);
        frame.setContentPane(new ChuyenPhongPanel(currentRoom));
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> createAndShowGUI("P101")); // Giả sử phòng hiện tại là P101
    }
}
