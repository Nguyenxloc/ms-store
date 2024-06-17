//
//package com.example.java4.controller.controller_tai;
//
//import com.example.java4.entities.*;
//import com.example.java4.repositories.*;
//import com.example.java4.repositories.repo_tai.IHoaDonRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//@RequestMapping("/ban-hang-tai-quay")
//public class BanTaiQuayController {
//
//    @Autowired
//    private HoaDonRepository hoaDonRepository;
//
//    @Autowired
//    private IHoaDonRepository _hoaDonRepo;
//
//    @Autowired
//    private HDCTRepository hoaDonChiTietRepository;
//
//
//    @Autowired
//    private SPCTRepository sanPhamChiTietRepository;
//
//    @Autowired
//    private KhachHangRepository khachHangRepository;
//
//    @Autowired
//    private MauSacRepository mauSacRepository;
//
//    @Autowired
//    SanPhamRepository sanPhamRepo;
//
//    @Autowired
//    KichThuocRepository kichThuocRepo;
//
//    @Autowired
//    ChatLieuRepository chatLieuRepo;
//
//    @Autowired
//    KieuTayRepository kieuTayRepo;
//
//    @Autowired
//    NhanVienRepository nhanVienRepo;
//
//    private List<HoaDon> listHoaDon;
//    private List<ChiTietHoaDon> listHDCT;
//    private List<ChiTietSanPham> listCTSP;
//    private List<KhachHang> listKH;
//    private List<MauSac> listMauSac;
//    private List<KichThuoc> listKichThuoc;
//    private List<SanPham> listSanPham;
//    private List<KieuTay> listKieuTay;
//    private List<ChatLieu> listChatLieu;
//    private String idNV = "2084A73E-F64A-4570-8362-D241442F2868";
//
//    @GetMapping("")
//    public String hienThi(Model model, @RequestParam(value = "page", defaultValue = "0") String pageParam) {
//        System.out.println("========================================= test paa" + pageParam);
//        Pageable pageable = PageRequest.of(Integer.valueOf(pageParam), 10);
//        listHoaDon = hoaDonRepository.selectTop5();
//        Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.findByTrangThai(1, pageable);
//        listKH = khachHangRepository.findAll();
//        listMauSac = mauSacRepository.findAll();
//        listKichThuoc = kichThuocRepo.findAll();
//        listKieuTay = kieuTayRepo.findAll();
//        listSanPham = sanPhamRepo.findAll();
//        listChatLieu = chatLieuRepo.findAll();
//        model.addAttribute("listMauSac", listMauSac);
//        model.addAttribute("listKichThuoc", listKichThuoc);
//        model.addAttribute("listChatLieu", listChatLieu);
//        model.addAttribute("listKieuTay", listKieuTay);
//        model.addAttribute("listSanPham", listSanPham);
//        model.addAttribute("listHoaDon", listHoaDon);
//        model.addAttribute("listCTSP", listCTSP);
//        model.addAttribute("listKH", listKH);
//        System.out.println(listMauSac);
//        return "/view/view_payment_counter/banHangTaiQuay.jsp";
//    }
//
//    @GetMapping("detail-hoa-don/{idHD}")
//    public String detailHoaDon(@PathVariable String idHD, @RequestParam Optional<Integer> pageParam, Model model) {
//        Optional<HoaDon> hoaDon = hoaDonRepository.findById(idHD);
//        model.addAttribute("hoaDon", hoaDon.get());
//        listHoaDon = hoaDonRepository.selectTop5();
//        listHDCT = hoaDonChiTietRepository.findAll();
//        listKH = khachHangRepository.findAll();
//        listMauSac = mauSacRepository.findAll();
//        listKichThuoc = kichThuocRepo.findAll();
//        listKieuTay = kieuTayRepo.findAll();
//        listSanPham = sanPhamRepo.findAll();
//        listChatLieu = chatLieuRepo.findAll();
//        Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);
//        Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.findAll(pageable);
//        model.addAttribute("listHoaDon", listHoaDon);
//        model.addAttribute("listCTSP", listCTSP);
//        model.addAttribute("listHDCT", listHDCT);
//        model.addAttribute("listKH", listKH);
//        model.addAttribute("listMauSac", listMauSac);
//        model.addAttribute("listKichThuoc", listKichThuoc);
//        model.addAttribute("listChatLieu", listChatLieu);
//        model.addAttribute("listKieuTay", listKieuTay);
//        model.addAttribute("listSanPham", listSanPham);
//        //Lọc hóa đơn chi tiết theo id hóa đơn
//        List<ChiTietHoaDon> gioHangTheoHoaDon = new ArrayList<>();
//        for (ChiTietHoaDon chiTietHoaDon : listHDCT) {
//            if (chiTietHoaDon.getIdHoaDon().getId().equals(idHD)) {
//                gioHangTheoHoaDon.add(chiTietHoaDon);
//                //Tính tổng tiền từng hóa đơn
//                BigDecimal tongTien = BigDecimal.ZERO;
//                for (ChiTietHoaDon hd : gioHangTheoHoaDon) {
//                    int sL = hd.getSoLuong();
//                    BigDecimal donGia = hd.getDonGia();
//                    BigDecimal thanhTien = donGia.multiply(BigDecimal.valueOf(sL));
//                    tongTien = tongTien.add(thanhTien);
//                }
//                model.addAttribute("tongTien", tongTien);
//            }
//        }
//        model.addAttribute("listHDCT", gioHangTheoHoaDon);
//        return "/view/view_payment_counter/banHangTaiQuay.jsp";
//    }
//
//
//    @PostMapping("/delete-hdct/{idHDCT}")
//    public String deleteHDCT(@PathVariable String idHDCT) {
//        HoaDon hd = new HoaDon();
//        int count = 0;
//        ChiTietSanPham ctsp = new ChiTietSanPham();
//        for (ChiTietHoaDon hdct : listHDCT) {
//            if (hdct.getId().equals(idHDCT)) {
//                hoaDonChiTietRepository.delete(hdct);
//                hd = hdct.getIdHoaDon();
//                ctsp = hdct.getIdCTSP();
//                count = hdct.getSoLuong();
//                System.out.println("test data :========================== ");
//            }
//        }
//        ctsp.setSoLuong(ctsp.getSoLuong() + count);
//        sanPhamChiTietRepository.save(ctsp);
//        return "redirect:/ban-hang-tai-quay/detail-hoa-don/" + hd.getId();
//    }
//
//    @PostMapping("/add-hoa-don")
//    public String themHoaDon(RedirectAttributes redirectAttributes) {
//        List<HoaDon> hoaDonList = this.hoaDonRepository.selectTop5();
//        int currentHoaDonCount = hoaDonList.size();
//        // Nếu số lượng hóa đơn lớn hơn 5, gửi dữ liệu Error từ Controller sang View(file.jsp)
//        if (currentHoaDonCount >= 5) {
//            redirectAttributes.addFlashAttribute("currentHoaDonCount", currentHoaDonCount);
//            redirectAttributes.addFlashAttribute("errorBillMax", "Bạn chỉ có thể tạo tối đa 5 đơn hàng");
//            return "redirect:/ban-hang-tai-quay";
//        }
//        String ma1 = "HD";
//        Integer sum = hoaDonRepository.countHD() + 1;
//        String ma = ma1 + sum;
//        System.out.println("==============test hoa don:" + ma);
//        LocalDateTime now = LocalDateTime.now();
//        HoaDon hoaDon = new HoaDon();
//        //Tạo mã tự sinh
//        hoaDon.setNgayTao(now);
//        Optional<NhanVien> nv = nhanVienRepo.findById(idNV);
//        hoaDon.setIdNhanVien(nv.get());
//        hoaDon.setMa(ma);
//        hoaDon.setTrangThai(0);
//        try {
//            //gửi dữ liệu success từ Controller sang View(file.jsp)
//            this.hoaDonRepository.save(hoaDon);
//            redirectAttributes.addFlashAttribute("success", "Hóa đơn được tạo thành công!");
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("error", "Đã xảy ra lỗi khi tạo hóa đơn.");
//        }
//        hoaDonRepository.save(hoaDon);
//        return "redirect:/ban-hang-tai-quay";
//    }
//
//    @PostMapping("giam-so-luong/{idCTSP}")
//    public String giamSoLuong(@PathVariable String idCTSP, @RequestParam String idHoaDon) {
//        //Giảm số lượng sản phẩm -1 khi ấn vào button giảm trong giỏ hàng
//        listCTSP = sanPhamChiTietRepository.findAll();
//        for (ChiTietHoaDon chiTietHoaDon : listHDCT) {
//            if (chiTietHoaDon.getIdCTSP().getId().equals(idCTSP) && chiTietHoaDon.getIdHoaDon().getId().equals(idHoaDon)) {
//                chiTietHoaDon.setSoLuong(chiTietHoaDon.getSoLuong() - 1);
//                hoaDonChiTietRepository.save(chiTietHoaDon);
//                //Khi số lượng sản phẩm trong giở hàng <= 0 thì set số lượng = 1
//                if (chiTietHoaDon.getSoLuong() <= 0) {
//                    chiTietHoaDon.setSoLuong(1);
//                    hoaDonChiTietRepository.save(chiTietHoaDon);
//                } else {
//                    //Số lượng của sản phẩm chi tiết được +1 khi ấn vào button thêm trong giỏ hàng
//                    for (ChiTietSanPham chiTietSanPham : listCTSP) {
//                        if (chiTietSanPham.getId().equals(idCTSP)) {
//                            chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() + 1);
//                            sanPhamChiTietRepository.save(chiTietSanPham);
//                        }
//                    }
//                }
//            }
//        }
//        return "redirect:/ban-hang-tai-quay/detail-hoa-don/" + idHoaDon;
//    }
//
//
//    @PostMapping("them-so-luong/{idCTSP}")
//    public String themSoLuong(@PathVariable String idCTSP, @RequestParam String idHoaDon, RedirectAttributes redirectAttributes) {
//        ChiTietSanPham chiTietSanPham = sanPhamChiTietRepository.findByIdCTSP(idCTSP);
//        for (ChiTietHoaDon chiTietHoaDon : listHDCT) {
//            //Nếu số lượng trong spct = 0 thì không đưuọc thêm sản phẩm nữa
//            if (chiTietSanPham.getSoLuong() <= 0) {
//                chiTietHoaDon.setSoLuong(chiTietHoaDon.getSoLuong());
//                try {
//                    hoaDonChiTietRepository.save(chiTietHoaDon);
//                    redirectAttributes.addFlashAttribute("error", "Hết hàng");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            } else {
//                //Thêm số lượng sản phẩm +1 khi ấn vào button thêm trong giỏ hàng
//                if (chiTietHoaDon.getIdCTSP().getId().equals(idCTSP) && chiTietHoaDon.getIdHoaDon().getId().equals(idHoaDon)) {
//                    chiTietHoaDon.setSoLuong(chiTietHoaDon.getSoLuong() + 1);
//                    hoaDonChiTietRepository.save(chiTietHoaDon);
//
//                    //Số lượng của sản phẩm chi tiết -1 khi ấn vào button thêm trong giỏ hàng
//                    if (chiTietSanPham.getId().equals(idCTSP)) {
//                        chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - 1);
//                        sanPhamChiTietRepository.save(chiTietSanPham);
//                    }
//                }
//
//            }
//        }
//        return "redirect:/ban-hang-tai-quay/detail-hoa-don/" + idHoaDon;
//    }
//
//
//    @PostMapping("add-san-pham/{idCTSP}")
//    public String addSanPhamVaoGioHang(@PathVariable String idCTSP, @RequestParam("page") Optional<Integer> pageParam,
//                                       @RequestParam String idHoaDon, RedirectAttributes redirectAttributes) {
//        ChiTietHoaDon hdct = new ChiTietHoaDon();
//        //Tìm sản phẩm trong giỏ hàng
//        Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);
//        Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.findAll(pageable);
//        boolean spTonTaiTrongGioHang = false;
//        Integer slBanDau = 1;
//        ChiTietSanPham chiTietSanPham = sanPhamChiTietRepository.findByIdCTSP(idCTSP);
//        for (ChiTietHoaDon chiTietHoaDon : listHDCT) {
//            //Nếu số lượng trong spct = 0 thì không đưuọc thêm sản phẩm nữa
//            if (chiTietSanPham.getSoLuong() <= 0) {
//                chiTietHoaDon.setSoLuong(chiTietHoaDon.getSoLuong());
//                try {
//                    hoaDonChiTietRepository.save(chiTietHoaDon);
//                    spTonTaiTrongGioHang = true;
//                    hdct.setSoLuong(hdct.getSoLuong() + 1);
//                    hoaDonChiTietRepository.save(hdct);
//                    redirectAttributes.addFlashAttribute("error", "Hết hàng");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                break;
//            } else {
//                if (chiTietHoaDon.getIdCTSP().getId().equals(idCTSP) && chiTietHoaDon.getIdHoaDon().getId().equals(idHoaDon)) {
//                    //Thêm số lượng sản phẩm +1 khi ấn vào button thêm trong giỏ hàng
//                    chiTietHoaDon.setSoLuong(chiTietHoaDon.getSoLuong() + 1);
//                    hoaDonChiTietRepository.save(chiTietHoaDon);
//
//                    //Số lượng của sản phẩm chi tiết bị -1 khi ấn vào button thêm trong giỏ hàng
//                    if (chiTietSanPham.getId().equals(idCTSP)) {
//                        chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - 1);
//                        sanPhamChiTietRepository.save(chiTietSanPham);
//                    }
//                    spTonTaiTrongGioHang = true;
//                    break;
//                }
//            }
//        }
//
//        BigDecimal donGia = null;
//        if (!spTonTaiTrongGioHang) {
//            for (ChiTietSanPham sp : listCTSP) {
//                if (sp.getId().equals(idCTSP)) {
//                    donGia = sp.getGiaBan();
//                }
//            }
//            ChiTietSanPham ctsp = new ChiTietSanPham();
//            ctsp.setId(idCTSP);
//            hdct.setIdCTSP(ctsp);
//            HoaDon hoaDon = new HoaDon();
//            hoaDon.setId(idHoaDon);
//            hdct.setIdHoaDon(hoaDon);
//            hdct.setSoLuong(1);
//            hdct.setDonGia(donGia);
//            hoaDonChiTietRepository.save(hdct);
//            //Số lượng của sản phẩm chi tiết bị giảm 1 khi ấn vào button thêm trong giỏ hàng
//            if (chiTietSanPham.getId().equals(idCTSP)) {
//                chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - 1);
//                sanPhamChiTietRepository.save(chiTietSanPham);
//            }
//        }
//        return "redirect:/ban-hang-tai-quay/detail-hoa-don/" + idHoaDon;
//    }
//}
