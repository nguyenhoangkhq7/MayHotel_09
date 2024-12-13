package view.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import bus.QuanLyDonDatPhongBUS;
import bus.ThemDonDatPhongBUS;
import com.toedter.calendar.JDateChooser;
import constant.CommonConstants;
import dal.DonDatPhongDAL;
import entity.*;
import helper.UIHelpers;
import net.miginfocom.swing.MigLayout;
import view.MainGUI;
import view.component.DonDatPhongPanel;

public class QuanLyDonDatPhongPanel extends JPanel implements ActionListener {
    // frame
    JButton btnTraCuu;
    public static JButton btnDatPhong;
    JDateChooser jdcNgayDen, jdcNgayDi;
    JComboBox cboTang, cboTrangThai;
    private JPanel jpnHeader, jpnContent, jpnContainSearchTrangThai, jpnDanhSachDDP,jpnSearch,jpnDetail, jpnTrangThai;
    private DonDatPhongDAL donDatPhongDAL;
    MenuPanel menuPanel;
    JLabel lblDatTruoc, lblDangO, lblCoPhongChuyen, lblSapCheckIn, lblQuaHanCheckin, lblQuaHanCheckout;
    private Map<String, ArrayList<DonDatPhong>> mapTrangThai; // Biến toàn cục
    private ArrayList<DonDatPhongPanel> dsDonDatPhongPanel = new ArrayList<>();
    public JButton getBtnDatPhong() {
        return btnDatPhong;
    }
    public JPanel getJpnDanhSachDDP() {
        return jpnDanhSachDDP;
    }
    public MainGUI getParentFrame() {
        return menuPanel.getMainGUI();
    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    public QuanLyDonDatPhongPanel(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;
        setLayout(new BorderLayout()); // Sử dụng BorderLayout cho JFrame

        // hiển thị header
        jpnHeader = new JPanel(new GridLayout(1,2));
        jpnHeader.setBackground(CommonConstants.BACKGROUND);
        JLabel lblSubTitle = new JLabel("Đặt phòng");
        JLabel lblTitle = new JLabel("Quản lý đơn đặt phòng");
        lblSubTitle.setFont(new Font("Arial", Font.ITALIC, CommonConstants.TEXT_SIZE)); lblSubTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 30)); lblTitle.setForeground(Color.WHITE);
        JPanel jpn1 = new JPanel(new FlowLayout(FlowLayout.LEFT)); jpn1.add(lblSubTitle);
        JPanel jpn2 = new JPanel(new FlowLayout(FlowLayout.LEFT)); jpn2.add(lblTitle);
        Box headerLeft = Box.createVerticalBox();
        headerLeft.add(jpn1);
        headerLeft.add(jpn2);
        JPanel headerRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnDatPhong = UIHelpers.createButtonStyle("Đặt phòng", CommonConstants.BUTTON_SIZE, Color.WHITE, CommonConstants.ORANGE);
        headerRight.add(btnDatPhong);
        jpnHeader.add(headerLeft);
        jpnHeader.add(headerRight);
        jpn1.setOpaque(false);
        jpn2.setOpaque(false);
        headerRight.setOpaque(false);
        this.add(jpnHeader, BorderLayout.NORTH);

        // Hiển thị phần content
        // content nội dung chính chứa search, trạng thái và danh sách đơn đặt phòng
        // jpnContainSearchTrangThai chứa search và trạng thái
        jpnContent = new JPanel(new BorderLayout());
        this.add(jpnContent, BorderLayout.CENTER);
        jpnContainSearchTrangThai = new JPanel(new BorderLayout());
        jpnSearch = new JPanel(new BorderLayout());
        jpnTrangThai = new JPanel(new GridLayout(1,6));
        jpnContainSearchTrangThai.add(jpnSearch, BorderLayout.NORTH);
        jpnContainSearchTrangThai.add(jpnTrangThai, BorderLayout.SOUTH);
        jpnDanhSachDDP = new JPanel(new BorderLayout());

        jpnContent.add(jpnContainSearchTrangThai, BorderLayout.NORTH);
        jpnContent.add(jpnDanhSachDDP, BorderLayout.CENTER);

        // thêm component cho search
        jpnSearch.setLayout(new GridLayout(3,2));
        jpnSearch.add(UIHelpers.create_Form_Label_JDateChooser("Từ ngày",jdcNgayDen = new JDateChooser()));
        jpnSearch.add(UIHelpers.create_Form_Label_JDateChooser("Đến ngày", jdcNgayDi = new JDateChooser()));
        jpnSearch.add(UIHelpers.create_Form_Label_JComboBox("Tầng", cboTang =  new JComboBox()));
        jpnSearch.add(UIHelpers.create_Form_Label_JComboBox("Trạng thái", cboTrangThai =  new JComboBox()));
        jpnSearch.add(new JPanel());

        cboTang.addItem("Chọn tầng");
        cboTang.addItem("1");
        cboTang.addItem("2");
        cboTang.addItem("3");
        cboTang.addItem("4");
        cboTang.addItem("5");

        cboTrangThai.addItem("Chọn trạng thái");
        cboTrangThai.addItem("Đã đặt trước");
        cboTrangThai.addItem("Đang ở");
        cboTrangThai.addItem("Có phòng chuyển");
        cboTrangThai.addItem("Sắp checkin");
        cboTrangThai.addItem("Quá hạn checkin");
        cboTrangThai.addItem("Quá hạn checkout");

        JPanel jpnTmp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnTraCuu = UIHelpers.createButtonStyle("Tra cứu", CommonConstants.BUTTON_SIZE, Color.WHITE, Color.BLUE);
        jpnTmp.add(btnTraCuu);
        jpnSearch.add(jpnTmp);

        lblDatTruoc = createStatusLabel("0", CommonConstants.DAT_TRUOC_COLOR);
        lblDangO = createStatusLabel("0", CommonConstants.DANG_O_COLOR);
        lblCoPhongChuyen = createStatusLabel("0", CommonConstants.CO_PHONG_CHUYEN_COLOR);
        lblSapCheckIn = createStatusLabel("0", CommonConstants.SAP_CHECKIN_COLOR);
        lblQuaHanCheckin = createStatusLabel("0", CommonConstants.QUA_HAN_CHECKIN_COLOR);
        lblQuaHanCheckout = createStatusLabel("0", CommonConstants.QUA_HAN_CHECKOUT_COLOR);

        jpnTrangThai.add(createStatusPanel(lblDatTruoc, "Đã đặt trước"));
        jpnTrangThai.add(createStatusPanel(lblDangO, "Đang ở"));
        jpnTrangThai.add(createStatusPanel(lblCoPhongChuyen, "Có phòng chuyển"));
        jpnTrangThai.add(createStatusPanel(lblSapCheckIn, "Sắp checkin (<2h)"));
        jpnTrangThai.add(createStatusPanel(lblQuaHanCheckin, "Quá hạn checkin (<1h)"));
        jpnTrangThai.add(createStatusPanel(lblQuaHanCheckout, "Quá hạn checkout (> 15 phút)"));

        // khởi tạo các giá trị
        khoiTaoMapTrangThai(new DonDatPhongDAL().getAllDonDatPhong());
        updateTrangThaiDon();
        initDanhSachDonDatPhongPanel();
        // thêm component cho danh sách đơn đặt phòng
        jpnDanhSachDDP.add(showDanhSachDDPAll(), BorderLayout.CENTER);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        // Hoặc chạy định kỳ mỗi 15 phút
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                // khởi tạo map trạng thái
                khoiTaoMapTrangThai(new DonDatPhongDAL().getAllDonDatPhong());
                updateTrangThaiDon();
                initDanhSachDonDatPhongPanel();
                // thêm component cho danh sách đơn đặt phòng
                jpnDanhSachDDP.add(showDanhSachDDPAll(), BorderLayout.CENTER);
            }
        }, 0, 15, TimeUnit.MINUTES);

        setVisible(true);
        donDatPhongDAL = new DonDatPhongDAL();

        // thêm sự kiện cho các nút
        btnDatPhong.addActionListener(this);
        btnTraCuu.addActionListener(this);
    }
    // Tạo Timer để cập nhật trạng thái đơn mỗi 15 phút

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o.equals(btnDatPhong)) {
            xuLySKDatPhong();
        } else if(o.equals(btnTraCuu)) {
            xuLySKTraCuu();
        }
    }

    // Phương thức khởi tạo Map khi ứng dụng khởi chạy
    private void khoiTaoMapTrangThai(ArrayList<DonDatPhong> dsDonDatPhong) {
        // Khởi tạo mapTrangThai với các trạng thái cần thiết
            mapTrangThai = new HashMap<>(Map.of(
                    "Đã đặt trước", new ArrayList<>(),
                    "Đang ở", new ArrayList<>(),
                    "Có phòng chuyển", new ArrayList<>(),
                    "Sắp checkin", new ArrayList<>(),
                    "Quá hạn checkin", new ArrayList<>(),
                    "Quá hạn checkout", new ArrayList<>()
            ));

        // Tạo instance của QuanLyDonDatPhongBUS
        QuanLyDonDatPhongBUS qlDonDatPhongBUS = new QuanLyDonDatPhongBUS();

        // Phân loại đơn đặt phòng vào mapTrangThai
        for (DonDatPhong don : dsDonDatPhong) {
            String trangThai = don.getTrangThaiDonDatPhong();

            // Bỏ qua các đơn không cần xử lý
            if ("Đã hủy".equals(trangThai) || "Đã hoàn thành".equals(trangThai)) {
                continue;
            }

            // Thêm đơn vào các trạng thái tương ứng
            switch (trangThai) {
                case "Đã đặt trước":
                    mapTrangThai.get("Đã đặt trước").add(don);
                    break;
                case "Đang ở":
                    mapTrangThai.get("Đang ở").add(don);
                    break;
            }

            // Kiểm tra các trạng thái đặc biệt
            if (qlDonDatPhongBUS.checkCoPhongChuyen(don)) {
                mapTrangThai.get("Có phòng chuyển").add(don);
            }
            if (qlDonDatPhongBUS.checkSapDenHanCheckin(don)) {
                mapTrangThai.get("Sắp checkin").add(don);
            }
            if (qlDonDatPhongBUS.checkQuaHanCheckin(don)) {
                mapTrangThai.get("Quá hạn checkin").add(don);
            }
            if (qlDonDatPhongBUS.checkQuaHanCheckout(don)) {
                mapTrangThai.get("Quá hạn checkout").add(don);
            }
        }
    }




    private void xuLySKTraCuu() {
        // Danh sách kết quả
        ArrayList<DonDatPhong> ketQua = new ArrayList<>();

        // Lấy dữ liệu từ giao diện
        String tang = cboTang.getSelectedItem().toString(); // Tầng
        String trangThai = cboTrangThai.getSelectedItem().toString(); // Trạng thái
        LocalDateTime ngayDen = new ThemDonDatPhongBUS().convertDateToLocalDateTime(jdcNgayDen.getDate(), 14, 00); // Ngày đến
        LocalDateTime ngayDi = new ThemDonDatPhongBUS().convertDateToLocalDateTime(jdcNgayDi.getDate(), 12, 00); // Ngày đi

        // Trường hợp không có bất kỳ tiêu chí nào
        if (trangThai.equals("Chọn trạng thái") && tang.equals("Chọn tầng") && ngayDen == null && ngayDi == null) {
            ketQua.addAll(new DonDatPhongDAL().getAllDonDatPhong());
        }

        // Trường hợp chỉ chọn trạng thái
        else if (!trangThai.equals("Chọn trạng thái") && tang.equals("Chọn tầng") && ngayDen == null && ngayDi == null) {
            ketQua.addAll(mapTrangThai.getOrDefault(trangThai, new ArrayList<>()));
        }

        // Trường hợp chỉ chọn tầng
        else if (trangThai.equals("Chọn trạng thái") && !tang.equals("Chọn tầng") && ngayDen == null && ngayDi == null) {
            ArrayList<DonDatPhong> dsDon = new DonDatPhongDAL().getAllDonDatPhong();
            ArrayList<Phong> dsPhong = null;
            for(DonDatPhong ddp : dsDon) {
                dsPhong = new DonDatPhongDAL().getDanhSachPhongTheoMaDonDatPhong(ddp.getMaDon());
                for (Phong p : dsPhong) {
                    if (p.getTang().equals(tang)) {
                        ketQua.add(ddp);
                    }
                    break;
                }
            }
        }

        // Trường hợp chỉ chọn ngày (ngày đến và ngày đi)
        else if (ngayDen != null && ngayDi != null && trangThai.equals("Chọn trạng thái") && tang.equals("Chọn tầng")) {
            ArrayList<DonDatPhong> dsDon = new DonDatPhongDAL().getAllDonDatPhong();
                for (DonDatPhong don : dsDon) {
                    if (!don.getNgayNhanPhong().isBefore(ngayDen) && !don.getNgayTraPhong().isAfter(ngayDi)) {
                        ketQua.add(don);
                    }
                }
        }

        // Trường hợp chọn trạng thái và tầng
        else if (!trangThai.equals("Chọn trạng thái") && !tang.equals("Chọn tầng") && ngayDen == null && ngayDi == null) {
            ArrayList<DonDatPhong> dsDDP = mapTrangThai.getOrDefault(trangThai, new ArrayList<>());
            for (DonDatPhong don : dsDDP) {
                ArrayList<Phong> dsPhong = new DonDatPhongDAL().getDanhSachPhongTheoMaDonDatPhong(don.getMaDon());
                for (Phong p : dsPhong) {
                    if (p.getTang().equals(tang)) {
                        ketQua.add(don);
                    }
                    break;
                }
            }
        }

        // Trường hợp chọn trạng thái và ngày (ngày đến và ngày đi)
        else if (!trangThai.equals("Chọn trạng thái") && tang.equals("Chọn tầng") && ngayDen != null && ngayDi != null) {
            ArrayList<DonDatPhong> dsDDP = mapTrangThai.getOrDefault(trangThai, new ArrayList<>());
            for (DonDatPhong don : dsDDP) {
                if (!don.getNgayNhanPhong().isBefore(ngayDen) && !don.getNgayTraPhong().isAfter(ngayDi)) {
                    ketQua.add(don);
                }
            }
        }

        // Trường hợp chọn tầng và ngày (ngày đến và ngày đi)
        else if (trangThai.equals("Chọn trạng thái") && !tang.equals("Chọn tầng") && ngayDen != null && ngayDi != null) {
            ArrayList<DonDatPhong> dsDon = new DonDatPhongDAL().getAllDonDatPhong();
            for (DonDatPhong don : dsDon) {
                ArrayList<Phong> dsPhong = new DonDatPhongDAL().getDanhSachPhongTheoMaDonDatPhong(don.getMaDon());
                for (Phong p : dsPhong) {
                    if (p.getTang().equals(tang) && !don.getNgayNhanPhong().isBefore(ngayDen) && !don.getNgayTraPhong().isAfter(ngayDi)) {
                        ketQua.add(don);
                    }
                    break;
                }
            }
        }

        // Trường hợp chọn tất cả các tiêu chí
        else if (!trangThai.equals("Chọn trạng thái") && !tang.equals("Chọn tầng") && ngayDen != null && ngayDi != null) {
            ArrayList<DonDatPhong> dsDDP = mapTrangThai.getOrDefault(trangThai, new ArrayList<>());
            for (DonDatPhong don : dsDDP) {
                ArrayList<Phong> dsPhong = new DonDatPhongDAL().getDanhSachPhongTheoMaDonDatPhong(don.getMaDon());
                for (Phong p : dsPhong) {
                    if (p.getTang().equals(tang) && !don.getNgayNhanPhong().isBefore(ngayDen) && !don.getNgayTraPhong().isAfter(ngayDi)) {
                        ketQua.add(don);
                    }
                    break;
                }
            }
        }
        System.out.println(ketQua.size());
        // Hiển thị kết quả
        if (ketQua.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy đơn đặt phòng nào phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            jpnDanhSachDDP.removeAll();
            jpnDanhSachDDP.add(ketQua.size() == dsDonDatPhongPanel.size() ? showDanhSachDDPAll() : showDanhSachDDP(ketQua));
            jpnDanhSachDDP.repaint();
            jpnDanhSachDDP.revalidate();
        }
    }

    private void xuLySKDatPhong() {
        JPanel jpnThemDonDatPhong = new ThemDonDatPhongPanel(menuPanel);
        menuPanel.getJpnContain().add(jpnThemDonDatPhong, "Thêm đơn đặt phòng");
        menuPanel.getCardLayout().show(menuPanel.getJpnContain(), "Thêm đơn đặt phòng");
    }

    // đã tối ưu
    public void updateTrangThaiDon() {
        // Khởi tạo Map để đếm số lượng trạng thái
        Map<String, Integer> statusCounts = new HashMap<>(Map.of(
                "Đã đặt trước", 0,
                "Đang ở", 0,
                "Có phòng chuyển", 0,
                "Sắp checkin", 0,
                "Quá hạn checkin", 0,
                "Quá hạn checkout", 0
        ));

        // Duyệt qua mapTrangThai và cập nhật số lượng
        mapTrangThai.forEach((trangThai, dsDon) -> {
            if (statusCounts.containsKey(trangThai)) {
                statusCounts.put(trangThai, dsDon.size());
            }
        });

        // Cập nhật các JLabel giao diện
        lblDatTruoc.setText(String.valueOf(statusCounts.get("Đã đặt trước")));
        lblDangO.setText(String.valueOf(statusCounts.get("Đang ở")));
        lblCoPhongChuyen.setText(String.valueOf(statusCounts.get("Có phòng chuyển")));
        lblSapCheckIn.setText(String.valueOf(statusCounts.get("Sắp checkin")));
        lblQuaHanCheckin.setText(String.valueOf(statusCounts.get("Quá hạn checkin")));
        lblQuaHanCheckout.setText(String.valueOf(statusCounts.get("Quá hạn checkout")));
    }

    // Hàm làm mới danh sách hiển thị


    private void initDanhSachDonDatPhongPanel() {

        ArrayList<DonDatPhong> dsDonDatPhong = new DonDatPhongDAL().getAllDonDatPhong();
        for(DonDatPhong don : dsDonDatPhong) {
            dsDonDatPhongPanel.add(new DonDatPhongPanel(this, don));
        }
    }

    public JScrollPane showDanhSachDDPAll() {
        JPanel jpnShowDanhSachDDP = new JPanel();
        jpnShowDanhSachDDP.setLayout(new MigLayout(
                "wrap 4, insets 10 10 10 10", // Bố cục 4 cột, thêm khoảng cách biên ngoài
                "[grow, fill][grow, fill][grow, fill][grow, fill]", // 4 cột đều nhau, kéo giãn
                "[]20[]" // Hàng có khoảng cách 20px giữa các hàng
        ));

        JScrollPane scroll = new JScrollPane(jpnShowDanhSachDDP);

        // Kiểm tra xem mapTrangThai có rỗng không
        if (dsDonDatPhongPanel.isEmpty()) {
            JLabel lbl = new JLabel("Không có đơn đặt phòng nào để hiển thị", SwingConstants.CENTER);
            lbl.setFont(new Font("Arial", Font.ITALIC, CommonConstants.TEXT_SIZE));
            lbl.setForeground(CommonConstants.GRAY);
            JPanel emptyPanel = new JPanel(new BorderLayout());
            emptyPanel.add(lbl, BorderLayout.CENTER);
            jpnShowDanhSachDDP.add(emptyPanel); // Hiển thị thông báo nếu không có đơn đặt phòng nào
            return scroll;
        }


        for(DonDatPhongPanel ddpPanel : dsDonDatPhongPanel) {
            jpnShowDanhSachDDP.add(ddpPanel);
        }

        return scroll;
    }

    public JScrollPane showDanhSachDDP(ArrayList<DonDatPhong> dsDonDatPhong) {
        JPanel jpnShowDanhSachDDP = new JPanel();
        jpnShowDanhSachDDP.setLayout(new MigLayout(
                "wrap 4, insets 10 10 10 10", // Bố cục 4 cột, thêm khoảng cách biên ngoài
                "[grow, fill][grow, fill][grow, fill][grow, fill]", // 4 cột đều nhau, kéo giãn
                "[]20[]" // Hàng có khoảng cách 20px giữa các hàng
        ));

        JScrollPane scroll = new JScrollPane(jpnShowDanhSachDDP);

        // Kiểm tra xem mapTrangThai có rỗng không
        if (dsDonDatPhong.isEmpty()) {
            JLabel lbl = new JLabel("Không có đơn đặt phòng nào để hiển thị", SwingConstants.CENTER);
            lbl.setFont(new Font("Arial", Font.ITALIC, CommonConstants.TEXT_SIZE));
            lbl.setForeground(CommonConstants.GRAY);
            JPanel emptyPanel = new JPanel(new BorderLayout());
            emptyPanel.add(lbl, BorderLayout.CENTER);
            jpnShowDanhSachDDP.add(emptyPanel); // Hiển thị thông báo nếu không có đơn đặt phòng nào
            return scroll;
        }

        for(DonDatPhongPanel dDPPanel : dsDonDatPhongPanel) {
            for(DonDatPhong donDatPhong : dsDonDatPhong) {
                if(donDatPhong.getMaDon().equals(dDPPanel.getDonDatPhong().getMaDon())) {
                    jpnShowDanhSachDDP.add(dDPPanel);
                }
            }
        }

        return scroll;
    }


    private JLabel createStatusLabel(String text, Color backgroundColor) {
        JLabel label = new JLabel("0");
        label.setPreferredSize(CommonConstants.TRANGTHAI_SIZE);
        label.setBackground(backgroundColor);
        label.setOpaque(true);
        label.setText("0");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        return label;
    }

    private JPanel createStatusPanel(JLabel label, String description) {
        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(new Label(description));
        return panel;
    }
}
