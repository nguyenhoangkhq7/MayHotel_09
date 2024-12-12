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
import entity.ChiTiet_DonDatPhong_Phong;
import entity.ChiTiet_DonDatPhong_Phong_DichVu;
import entity.Phong;
import view.panel.ThemDonDatPhongPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class PhongDaThemVaoDonDatPhongPanel extends JPanel implements ActionListener {
    private JButton btnXoa, btnSua;
    Phong phong;
    public JButton getBtnXoa() {
        return btnXoa;
    }
    public JButton getBtnSua() {
        return btnSua;
    }
    private ThemDonDatPhongPanel themDonDatPhongPanel;
    public PhongDaThemVaoDonDatPhongPanel(ThemDonDatPhongPanel themDonDatPhongPanel, Phong phong) {
        this.themDonDatPhongPanel = themDonDatPhongPanel;
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel container = new JPanel(new BorderLayout());
        container.setPreferredSize(new Dimension(400,80));
        this.phong = phong;
        container.setBorder(new LineBorder(Color.GRAY, 2));
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
        btnXoa = customButton("Xóa");
        btnSua = customButton("Cập nhật dịch vụ");

        boxContain.add(Box.createHorizontalStrut(10));
        boxContain.add(lblLoaiPhong);
        boxContain.add(Box.createHorizontalGlue());
        boxContain.add(btnSua);
        boxContain.add(Box.createHorizontalStrut(5));
        boxContain.add(btnXoa);
        boxContain.add(Box.createHorizontalStrut(5));

//        thêm các thành phần vào panel
        container.add(boxContain, BorderLayout.CENTER);
        container.add(lblTenPhong, BorderLayout.SOUTH);
        this.add(container);
        btnSua.addActionListener(this);
        btnXoa.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o.equals(btnSua)) {
            // hiển thị lại jpnDichVu
            // sửa lại button chọn dịch vụ thành lưu thay đổi
            // hiển thị loại phòng và tên phòng trong combobox
            // cập nhật lại danh sách chi tiết dịch vụ
            // xóa jpnDichVu loaiPhong, tên phòng trong combobox
            themDonDatPhongPanel.getBtnThemDichVu().setEnabled(true);
            // add các dịch vụ vào jpnDichVu
            ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> dsChiTiet = themDonDatPhongPanel.getDsChiTietDDP_Phong_DV();
            JPanel jpnDV = themDonDatPhongPanel.getJpnDichVu();
            jpnDV.setEnabled(true);
            jpnDV.removeAll();
            for(ChiTiet_DonDatPhong_Phong_DichVu ct : dsChiTiet) {
                if(ct.getPhong().equals(this.phong)) {
                    jpnDV.add(new DichVuPanel(themDonDatPhongPanel, ct));
                }
            }
            jpnDV.repaint();
            jpnDV.revalidate();
            // setButton thành lưu thay đổi
            themDonDatPhongPanel.getBtnChonPhong().setText("Lưu thay đổi");

            // hiển thị loaiPhong và tenPhong trong combobox
            String loaiPhong = phong.getLoaiPhong().getTenLoaiPhong();
            String tenPhong = phong.getTenPhong();
            themDonDatPhongPanel.getCboLoaiPhong().setSelectedItem(loaiPhong);
            themDonDatPhongPanel.getCboSoPhong().addItem(tenPhong);
            themDonDatPhongPanel.getCboSoPhong().setSelectedItem(tenPhong);
            themDonDatPhongPanel.getCboLoaiPhong().setEnabled(false);
            themDonDatPhongPanel.getCboSoPhong().setEnabled(false);
            btnXoa.setEnabled(false);

            // Thêm logic để xử lý khi "Lưu thay đổi" được nhấn
            themDonDatPhongPanel.getBtnChonPhong().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (themDonDatPhongPanel.getBtnChonPhong().getText().equals("Lưu thay đổi")) {
                        // Xử lý lưu thay đổi ở đây
                        ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> dsChiTietDV = themDonDatPhongPanel.getDsChiTietDDP_Phong_DV();

                        // Cập nhật danh sách dịch vụ từ jpnDichVu
                        Component[] components = jpnDV.getComponents();
                        for (Component component : components) {
                            if (component instanceof DichVuPanel) {
                                DichVuPanel dichVuPanel = (DichVuPanel) component;
                                ChiTiet_DonDatPhong_Phong_DichVu chiTietDV = dichVuPanel.getChiTiet();

                                // Tìm và cập nhật chi tiết trong danh sách
                                for (int i = 0; i < dsChiTietDV.size(); i++) {
                                    if (dsChiTietDV.get(i).equals(chiTietDV)) {
                                        dsChiTietDV.set(i, chiTietDV);
                                        break;
                                    }
                                }
                            }
                        }

                        // Đặt lại trạng thái nút thành "Chọn phòng"
                        themDonDatPhongPanel.getBtnChonPhong().setText("Chọn phòng");
                        themDonDatPhongPanel.getCboSoPhong().removeItem(tenPhong);
                        themDonDatPhongPanel.getCboLoaiPhong().setEnabled(true);
                        themDonDatPhongPanel.getCboSoPhong().setEnabled(true);
                        themDonDatPhongPanel.getBtnThemDichVu().setEnabled(false);
                        btnXoa.setEnabled(true);
                        themDonDatPhongPanel.setSuaDichVu(true);
                        // Xóa các thành phần cũ trong jpnDichVu
                        jpnDV.removeAll();
                        jpnDV.repaint();
                        jpnDV.revalidate();
                    }
                }
            });
        }
        else if(o.equals(btnXoa)) {
            // xóa phòng trong ds phòng và xóa chi tiết phòng, chi tiết dịch vụ của phòng đó
            // kiểm tra nếu phòng đó đang được chọn thì xóa các jpnDichVu và xóa trong jpnPhong,
            // còn không thì chỉ xóa trong jpnPhong
            ArrayList<ChiTiet_DonDatPhong_Phong_DichVu> dsChiTietDV = themDonDatPhongPanel.getDsChiTietDDP_Phong_DV();
            ArrayList<ChiTiet_DonDatPhong_Phong> dsChiTietPhong = themDonDatPhongPanel.getDsChiTietDDP_P();
            ArrayList<Phong> dsPhong = themDonDatPhongPanel.getDsPhong();
            Box boxContainDSPhong = themDonDatPhongPanel.getBoxContainDanhSachPhong();
            JPanel jpnDV = themDonDatPhongPanel.getJpnDichVu();

            // check nếu phòng đó đang được chọn thì xóa các nội dung trong JPanel Dich vụ
            Component[] components = jpnDV.getComponents();
            for(Component component : components) {
                if(component instanceof DichVuPanel) {
                    DichVuPanel dichVuPanel = (DichVuPanel) component;
                    if(dichVuPanel.getChiTiet().getPhong().equals(this.phong)) {
                        jpnDV.removeAll();
                        break;
                    }
                }
            }
            // xóa JPanel phòng theo thứ tự của nó
            boxContainDSPhong.remove(dsPhong.indexOf(phong));
            boxContainDSPhong.repaint();
            boxContainDSPhong.revalidate();

            // cập nhật combobox của tên phòng khi xóa
            String loaiPhongDangDuocChon = themDonDatPhongPanel.getCboLoaiPhong().getSelectedItem().toString();
            String loaiPhongCuaPhongXoa = phong.getLoaiPhong().getTenLoaiPhong();
            System.out.println(loaiPhongDangDuocChon);
            System.out.println(loaiPhongCuaPhongXoa);
            if(loaiPhongDangDuocChon.equals(loaiPhongCuaPhongXoa)) {
                themDonDatPhongPanel.getCboSoPhong().addItem(phong.getTenPhong());
                themDonDatPhongPanel.getCboSoPhong().repaint();
                themDonDatPhongPanel.getCboSoPhong().revalidate();
            }

            // xóa chi tiết dịch vụ
            Iterator<ChiTiet_DonDatPhong_Phong_DichVu> iteratorDV = dsChiTietDV.iterator();
            while (iteratorDV.hasNext()) {
                ChiTiet_DonDatPhong_Phong_DichVu ct = iteratorDV.next();
                if (ct.getPhong().equals(phong)) {
                    iteratorDV.remove();
                }
            }

            // xóa chi tiết phòng
            Iterator<ChiTiet_DonDatPhong_Phong> iteratorPhong = dsChiTietPhong.iterator();
            while (iteratorPhong.hasNext()) {
                ChiTiet_DonDatPhong_Phong ct = iteratorPhong.next();
                if (ct.getPhong().equals(phong)) {
                    iteratorPhong.remove();
                }
            }

            // xóa phòng
            Iterator<Phong> iteratorPhongList = dsPhong.iterator();
            while (iteratorPhongList.hasNext()) {
                Phong p = iteratorPhongList.next();
                if (p.equals(phong)) {
                    iteratorPhongList.remove();
                }
            }

            themDonDatPhongPanel.updateTongSoPhong();
            themDonDatPhongPanel.updateTongTGO();
            themDonDatPhongPanel.updateTongTien();
        }
    }
    public JButton customButton(String ten) {
        JButton btn = new JButton(ten);
        btn.setOpaque(true);
        if(ten.equals("Xóa")) {
            btn.setPreferredSize(new Dimension(45,28));
        } else {
            btn.setPreferredSize(new Dimension(85,28));
        }
        btn.setForeground(CommonConstants.ORANGE);
        btn.setBorder(new LineBorder(CommonConstants.ORANGE, 2));
        btn.setBackground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }


    public static void main(String[] args) {
    }

}
