package view.dialog;

import dal.ChiTiet_DonDatPhong_Phong_DichVuDAL;
import dal.DonDatPhongDAL;
import dal.PhongDAL;
import entity.ChiTiet_DonDatPhong_Phong_DichVu;
import entity.DonDatPhong;
import entity.Phong;
import view.MainGUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CapNhatDichVuDialog extends JDialog {

    private JTable roomTable, dichVuTable;
    private JButton btnThemDichVu, btnSuaDichVu, btnXoaDichVu;
    private JScrollPane scrollPanePhong, scrollPaneDichVu;
    private DonDatPhong donDatPhong;
    private MainGUI mainGUI;
    private Box boxCenter;
    private Phong selectedPhong = null;

    public CapNhatDichVuDialog(MainGUI mainGui, DonDatPhong donDatPhong) {
        super(mainGui, "Chọn phòng để thêm dịch vụ", true);
        this.donDatPhong = donDatPhong;
        this.mainGUI = mainGui;

        initializeUI();
        addEventListeners();
    }

    private void initializeUI() {
        // Setup giao diện
        setTitle("Chọn phòng để thêm dịch vụ");
        setSize(400, 300);
        setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Danh sách phòng", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        boxCenter = Box.createVerticalBox();
        roomTable = createRoomTable();
        dichVuTable = createDichVuTable();

        scrollPanePhong = new JScrollPane(roomTable);
        scrollPaneDichVu = new JScrollPane(dichVuTable);

        boxCenter.add(scrollPanePhong);
        boxCenter.add(scrollPaneDichVu);

        JPanel buttonPanel = new JPanel();
        btnThemDichVu = createButton("Thêm dịch vụ", false);
        btnSuaDichVu = createButton("Sửa dịch vụ", false);
        btnXoaDichVu = createButton("Xóa dịch vụ", false);
        buttonPanel.add(btnThemDichVu);
        buttonPanel.add(btnSuaDichVu);
        buttonPanel.add(btnXoaDichVu);

        add(new JLabel("Chọn một phòng để thêm dịch vụ", JLabel.CENTER), BorderLayout.NORTH);
        add(boxCenter, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JTable createRoomTable() {
        String[] columnNames = {"Mã phòng", "Tên phòng", "Loại phòng"};
        Object[][] data = getRoomDataFromDonDatPhong(donDatPhong);
        JTable table = new JTable(data, columnNames);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return table;
    }

    private JTable createDichVuTable() {
        String[] columnNames = {"Mã dịch vụ", "Tên dịch vụ", "Số lượng", "Thời gian sử dụng"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return table;
    }

    private JButton createButton(String label, boolean enabled) {
        JButton button = new JButton(label);
        button.setEnabled(enabled);
        return button;
    }

    private void addEventListeners() {
        roomTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && roomTable.getSelectedRow() != -1) {
                String roomName = roomTable.getValueAt(roomTable.getSelectedRow(), 1).toString();
                selectedPhong = new PhongDAL().getPhongTheoTenPhong(roomName);
                loadDataDichVuCuaPhong(selectedPhong);
                btnThemDichVu.setEnabled(true);
            } else {
                btnThemDichVu.setEnabled(false);
            }
        });
        // Sự kiện cho bảng dịch vụ (dichVuTable)
        dichVuTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && dichVuTable.getSelectedRow() != -1) {
                    // Khi có dòng được chọn trong bảng dịch vụ
                    btnSuaDichVu.setEnabled(true);
                    btnXoaDichVu.setEnabled(true);
                } else {
                    // Khi không có dòng nào được chọn trong bảng dịch vụ
                    btnSuaDichVu.setEnabled(false);
                    btnXoaDichVu.setEnabled(false);
                }
            }
        });

        btnThemDichVu.addActionListener(e -> handleAddDichVu());
        btnSuaDichVu.addActionListener(e -> handleEditDichVu());
        btnXoaDichVu.addActionListener(e -> handleDeleteDichVu());
    }

    private void handleAddDichVu() {
        if (selectedPhong != null) {
            JOptionPane.showMessageDialog(this, "Bạn đã chọn phòng: " + selectedPhong.getMaPhong(), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            ChonDichVuDialog dialog = new ChonDichVuDialog(mainGUI, donDatPhong, selectedPhong);
            dialog.setVisible(true);

            if (dialog.getcTDDPP_DichVu() != null) {
                new ChiTiet_DonDatPhong_Phong_DichVuDAL().themChiTiet(dialog.getcTDDPP_DichVu());
            }
            dispose();
        }
    }

    private void handleEditDichVu() {
        int selectedRowDichVu = dichVuTable.getSelectedRow();
        if (selectedRowDichVu != -1) {
            String serviceId = dichVuTable.getValueAt(selectedRowDichVu, 0).toString();
            String serviceName = dichVuTable.getValueAt(selectedRowDichVu, 1).toString();
            JOptionPane.showMessageDialog(this, "Bạn đã chọn sửa dịch vụ: " + serviceId + " - " + serviceName, "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            // Handle service editing
            int selectedRowPhong = roomTable.getSelectedRow();
            if (selectedRowPhong != -1) {
                ChiTiet_DonDatPhong_Phong_DichVu ct = new ChiTiet_DonDatPhong_Phong_DichVuDAL()
                        .getChiTietDonDatPhongPhongDichVuTheoMa(donDatPhong.getMaDon(), selectedPhong.getMaPhong(), serviceId);

                SuaDichVuDonDatPhongDialog suaDialog = new SuaDichVuDonDatPhongDialog(mainGUI, ct);
                suaDialog.setVisible(true);

                if (new ChiTiet_DonDatPhong_Phong_DichVuDAL()
                        .suaChiTiet(donDatPhong.getMaDon(), selectedPhong.getMaPhong(), serviceId, suaDialog.getcTDDPP_DichVu())) {
                    JOptionPane.showMessageDialog(null, "Sửa dịch vụ thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                    // Cập nhật lại bảng dịch vụ
                    updateDichVuTable(selectedPhong.getMaPhong());

                } else {
                    JOptionPane.showMessageDialog(null, "Sửa dịch vụ thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }

                suaDialog.dispose();
            }
        }
    }
    private void updateDichVuTable(String maPhong) {
        // Lấy danh sách dịch vụ đã cập nhật
        List<ChiTiet_DonDatPhong_Phong_DichVu> danhSachChiTietDichVu = new ChiTiet_DonDatPhong_Phong_DichVuDAL()
                .getDanhSachChiTietTheoMa(donDatPhong.getMaDon(), maPhong);

        // Tạo một TableModel mới
        DefaultTableModel model = (DefaultTableModel) dichVuTable.getModel();
        model.setRowCount(0); // Xóa toàn bộ dữ liệu cũ

        // Thêm dữ liệu mới vào bảng
        for (ChiTiet_DonDatPhong_Phong_DichVu dv : danhSachChiTietDichVu) {
            model.addRow(new Object[]{
                    dv.getDichVu().getMaDichVu(),
                    dv.getDichVu().getTenDichVu(),
                    dv.getSoLuongDat(),
                    dv.getTgSuDungDV()
            });
        }

        // Làm mới bảng
        dichVuTable.repaint();
        dichVuTable.validate();
    }


    private void handleDeleteDichVu() {
        int selectedRow = dichVuTable.getSelectedRow();
        if (selectedRow != -1) {
            String serviceId = dichVuTable.getValueAt(selectedRow, 0).toString();
            String serviceName = dichVuTable.getValueAt(selectedRow, 1).toString();
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa dịch vụ?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if(new ChiTiet_DonDatPhong_Phong_DichVuDAL().xoaChiTiet(donDatPhong.getMaDon(), selectedPhong.getMaPhong(), serviceId)) {
                    JOptionPane.showMessageDialog(null, "Xóa dịch vụ thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    updateDichVuTable(serviceId);
                } else {
                    JOptionPane.showMessageDialog(null, "Xóa dịch vụ thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void loadDataDichVuCuaPhong(Phong phong) {
        Object[][] dichVuData = getDichVuDataFromDonDatPhongPhong(donDatPhong, phong);
        DefaultTableModel model = (DefaultTableModel) dichVuTable.getModel();
        model.setRowCount(0);
        for (Object[] row : dichVuData) {
            model.addRow(row);
        }
    }

    private Object[][] getRoomDataFromDonDatPhong(DonDatPhong donDatPhong) {
        java.util.List<Phong> danhSachPhong = new DonDatPhongDAL().getDanhSachPhongTheoMaDonDatPhong(donDatPhong.getMaDon());
        Object[][] data = new Object[danhSachPhong.size()][3];
        for (int i = 0; i < danhSachPhong.size(); i++) {
            Phong phong = danhSachPhong.get(i);
            data[i][0] = phong.getMaPhong();
            data[i][1] = phong.getTenPhong();
            data[i][2] = phong.getLoaiPhong().getTenLoaiPhong();
        }
        return data;
    }

    private Object[][] getDichVuDataFromDonDatPhongPhong(DonDatPhong donDatPhong, Phong p) {
        List<ChiTiet_DonDatPhong_Phong_DichVu> danhSachChiTietDichVu = new ChiTiet_DonDatPhong_Phong_DichVuDAL().getDanhSachChiTietTheoMa(donDatPhong.getMaDon(), p.getMaPhong());
        Object[][] data = new Object[danhSachChiTietDichVu.size()][4];
        for (int i = 0; i < danhSachChiTietDichVu.size(); i++) {
            ChiTiet_DonDatPhong_Phong_DichVu chiTiet = danhSachChiTietDichVu.get(i);
            data[i][0] = chiTiet.getDichVu().getMaDichVu();
            data[i][1] = chiTiet.getDichVu().getTenDichVu();
            data[i][2] = chiTiet.getSoLuongDat();
            data[i][3] = chiTiet.getTgSuDungDV();
        }
        return data;
    }
}
