package view.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.toedter.calendar.JDateChooser;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

public class BoLocThongKeDoanhThuDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JButton okButton;
    private JDateChooser dateChooserStart;
    private JDateChooser dateChooserEnd;
    private ThoiGianListener thoiGianListener;

    public static void main(String[] args) {
        try {
            BoLocThongKeDoanhThuDialog dialog = new BoLocThongKeDoanhThuDialog();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BoLocThongKeDoanhThuDialog() {
        initialize();
    }

    private void initialize() {
        setBounds(100, 100, 480, 304);
        setTitle("Bộ Lọc Thống Kê Doanh Thu");
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);

        dateChooserStart = createDateChooser();
        dateChooserEnd = createDateChooser();

        JLabel lblStartDate = createLabel("Ngày bắt đầu:");
        JLabel lblEndDate = createLabel("Ngày kết thúc:");

        dateChooserEnd.addPropertyChangeListener(evt -> {
            if ("date".equals(evt.getPropertyName())) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateChooserEnd.getDate());
                calendar.add(Calendar.DAY_OF_MONTH, -30);
                dateChooserStart.setMaxSelectableDate(dateChooserEnd.getDate());
                dateChooserStart.setMinSelectableDate(calendar.getTime());
            }
        });

        dateChooserStart.addPropertyChangeListener(evt -> {
            if ("date".equals(evt.getPropertyName())) {
                dateChooserEnd.setMinSelectableDate(dateChooserStart.getDate());
            }
        });

        JButton loc3NgayButton = createButton("3 Ngày", e -> setDateRange(-2, 0));
        JButton loc5NgayButton = createButton("5 Ngày", e -> setDateRange(-4, 0));
        JButton loc1ThangButton = createButton("1 Tháng", e -> setDateRange(-29, 0));
        JButton loc7NgayButton = createButton("7 Ngày", e -> setDateRange(-6, 0));

        JSeparator separator = createSeparator();
        JSeparator separator_1 = createSeparator();

        GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
        gl_contentPanel.setHorizontalGroup(createHorizontalLayout(gl_contentPanel, loc3NgayButton, loc5NgayButton, loc1ThangButton, loc7NgayButton, lblStartDate, lblEndDate, separator, separator_1));
        gl_contentPanel.setVerticalGroup(createVerticalLayout(gl_contentPanel, loc3NgayButton, loc5NgayButton, loc1ThangButton, loc7NgayButton, lblStartDate, lblEndDate, separator, separator_1));
        contentPanel.setLayout(gl_contentPanel);

        JPanel buttonPane = createButtonPanel();
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
    }

    private JDateChooser createDateChooser() {
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDate(new Date());
        dateChooser.setMaxSelectableDate(new Date());
        return dateChooser;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Tahoma", Font.BOLD, 15));
        return label;
    }

    private JButton createButton(String text, java.awt.event.ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.BOLD, 13));
        button.setForeground(new Color(243, 125, 0));
        button.setBackground(Color.WHITE);
        button.addActionListener(actionListener);
        return button;
    }

    private JSeparator createSeparator() {
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(243, 125, 0));
        return separator;
    }

    private GroupLayout.ParallelGroup createHorizontalLayout(GroupLayout gl_contentPanel, JButton loc3NgayButton, JButton loc5NgayButton, JButton loc1ThangButton, JButton loc7NgayButton, JLabel lblStartDate, JLabel lblEndDate, JSeparator separator, JSeparator separator_1) {
        return gl_contentPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPanel.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_contentPanel.createSequentialGroup()
                                        .addComponent(loc3NgayButton, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                        .addGap(38)
                                        .addComponent(loc5NgayButton, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                        .addGap(36)
                                        .addComponent(loc7NgayButton, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                        .addGap(36)
                                        .addComponent(loc1ThangButton, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                        .addContainerGap())
                                .addGroup(gl_contentPanel.createSequentialGroup()
                                        .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                                                .addComponent(lblStartDate)
                                                .addComponent(lblEndDate))
                                        .addGap(23)
                                        .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                                                .addComponent(dateChooserEnd, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                                                .addComponent(dateChooserStart, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
                                        .addGap(25))))


                .addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                .addComponent(separator_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE);
    }

    private GroupLayout.SequentialGroup createVerticalLayout(GroupLayout gl_contentPanel, JButton loc3NgayButton, JButton loc5NgayButton, JButton loc1ThangButton, JButton loc7NgayButton, JLabel lblStartDate, JLabel lblEndDate, JSeparator separator, JSeparator separator_1) {
        return gl_contentPanel.createSequentialGroup()
                .addGap(19)
                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addComponent(loc1ThangButton, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
                        .addComponent(loc7NgayButton, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
                        .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                                .addComponent(loc3NgayButton, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
                                .addComponent(loc5NgayButton, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addComponent(separator, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                .addGap(13)
                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblStartDate)
                        .addComponent(dateChooserStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18)
                .addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
                        .addComponent(lblEndDate)
                        .addComponent(dateChooserEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE);
    }

    private JPanel createButtonPanel() {
        JPanel buttonPane = new JPanel();
        okButton = createButton("Áp Dụng", e -> applyFilter());
        okButton.setBackground(new Color(243, 125, 0));
        okButton.setForeground(Color.WHITE);
        GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
        gl_buttonPane.setHorizontalGroup(
                gl_buttonPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, gl_buttonPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(okButton, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                                .addContainerGap())
        );
        gl_buttonPane.setVerticalGroup(
                gl_buttonPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_buttonPane.createSequentialGroup()
                                .addComponent(okButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(22, Short.MAX_VALUE))
        );
        buttonPane.setLayout(gl_buttonPane);
        return buttonPane;
    }

    private void applyFilter() {
        Date startDate = dateChooserStart.getDate();
        Date endDate = dateChooserEnd.getDate();

        if (startDate == null || endDate == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đầy đủ ngày bắt đầu và ngày kết thúc!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LocalDate localStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (localStartDate.isAfter(localEndDate)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước hoặc bằng ngày kết thúc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(localStartDate, localEndDate);
        if (daysBetween > 30) {
            JOptionPane.showMessageDialog(this, "Khoảng cách giữa ngày bắt đầu và ngày kết thúc không được vượt quá 30 ngày!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (thoiGianListener != null) {
            thoiGianListener.onThoiGianChon(localStartDate, localEndDate);
        }
        dispose();
    }


    private void setDateRange(int startDaysOffset, int endDaysOffset) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, startDaysOffset);
        dateChooserStart.setDate(calendar.getTime());

        calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, endDaysOffset);
        dateChooserEnd.setDate(calendar.getTime());
    }

    public interface ThoiGianListener {
        void onThoiGianChon(LocalDate startDate, LocalDate endDate);
    }

    public void setThoiGianListener(ThoiGianListener listener) {
        this.thoiGianListener = listener;
    }


}
