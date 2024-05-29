package com.example.java4.controller.controller_ban_tai_quay;

import com.example.java4.entities.ChiTietHoaDon;
import com.example.java4.entities.ChiTietSanPham;
import com.example.java4.entities.HoaDon;
import com.example.java4.entities.KhachHang;
import com.example.java4.repositories.HDCTRepository;
import com.example.java4.repositories.HoaDonRepository;
import com.example.java4.repositories.KhachHangRepository;
import com.example.java4.repositories.SPCTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ban-hang")
public class BanTaiQuayController {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private HDCTRepository hoaDonChiTietRepository;

    @Autowired
    private SPCTRepository sanPhamChiTietRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    private List<HoaDon> listHoaDon;
    private List<ChiTietHoaDon> listHDCT;
    private List<ChiTietSanPham> listCTSP;
    private List<KhachHang> listKH;

    @GetMapping("")
    public String hienThi(Model model,
                          @RequestParam("page") Optional<Integer> reqParam,
                          RedirectAttributes redirectAttributes) {


        int page = reqParam.orElse(0);
        Pageable p = PageRequest.of(page, 5);
        List<HoaDon> listHoaDon = hoaDonRepository.findByTrangThai(HoaDonRepository.INACTIVE);
        listCTSP = sanPhamChiTietRepository.findAll();
        listHDCT = hoaDonChiTietRepository.findAll();
        listKH = khachHangRepository.findAll();
        model.addAttribute("listHoaDon", listHoaDon);
        model.addAttribute("listCTSP", listCTSP);
        model.addAttribute("listHDCT", listHDCT);
        model.addAttribute("listKH", listKH);
        return "/view/view_ban_tai_quay/index.jsp";
    }

    // Thêm mới hóa d
    @PostMapping("/add-hoa-don")
    public String themHoaDon(RedirectAttributes redirectAttributes) {
        List<HoaDon> hoaDonList = this.hoaDonRepository.findByTrangThai(this.hoaDonRepository.INACTIVE);
        int currentHoaDonCount = hoaDonList.size();

        if (currentHoaDonCount >= 5) {
            redirectAttributes.addFlashAttribute("currentHoaDonCount", currentHoaDonCount);
            redirectAttributes.addFlashAttribute("errorBillMax", "Bạn chỉ có thể tạo tối đa 5 đơn hàng");
            return "redirect:/ban-hang";
        }

        HoaDon hoaDon = new HoaDon();
        hoaDon.setTrangThai(0);
        hoaDon.setNgayTao(new Date());
        try {
            this.hoaDonRepository.save(hoaDon);
            redirectAttributes.addFlashAttribute("success", "Hóa đơn được tạo thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Đã xảy ra lỗi khi tạo hóa đơn.");
        }

        return "redirect:/ban-hang";
    }

//    @GetMapping("/delete-hoa-don/{idHD}")
//    public String xoaHoaDon(Model model, @PathVariable("idHD") String idHD,
//                            RedirectAttributes redirectAttributes) {
//
//        try {
//            hoaDonRepository.deleteById(idHD);
//            redirectAttributes.addFlashAttribute("success", "Xóa hóa đơn thành công!");
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("error", "Đã xảy ra lỗi khi xóa hóa đơn.");
//        }
//        return "redirect:/ban-hang";
//
//    }


    @GetMapping("detail-hoa-don/{idHD}")
    public String detailHoaDon(@PathVariable String idHD, Model model){
        Optional<HoaDon> hoaDon = hoaDonRepository.findById(idHD);
        model.addAttribute("hoaDon",hoaDon.get());

        listHoaDon = hoaDonRepository.findAll();
        listCTSP = sanPhamChiTietRepository.findAll();
        listHDCT = hoaDonChiTietRepository.findAll();
        listKH = khachHangRepository.findAll();

        model.addAttribute("listHoaDon",listHoaDon);
        model.addAttribute("listCTSP",listCTSP);

        model.addAttribute("listKH",listKH);

        //Lọc hóa đơn chi tiết theo id hóa đơn
        List<ChiTietHoaDon> gioHangTheoHoaDon = new ArrayList<>();
        for (ChiTietHoaDon ct :listHDCT){
            if (ct.getIdHoaDon().getId() == idHD){
                gioHangTheoHoaDon.add(ct);

                //Tính tổng tiền từng hóa đơn
                BigDecimal tongTien = BigDecimal.ZERO;
                for (ChiTietHoaDon hd:gioHangTheoHoaDon){
                    int sL = hd.getSoLuong();
                    BigDecimal donGia = BigDecimal.valueOf(hd.getDonGia());
                    BigDecimal thanhTien = donGia.multiply(BigDecimal.valueOf(sL));
                    tongTien = tongTien.add(thanhTien);
                }
                model.addAttribute("tongTien",tongTien);
                model.addAttribute("listHDCT",gioHangTheoHoaDon);
            }
        }


        return "/ban-hang/index";
    }

//    @PostMapping("add-san-pham/{idCTSP}")
//    public String addSanPhamVaoGioHang(@PathVariable String idCTSP,
//                                       @RequestParam String idHoaDon){
//        ChiTietHoaDon hdct = new ChiTietHoaDon();
//
//        //Tìm sản phẩm trong giỏ hàng
//        boolean spTonTaiTrongGioHang = false;
//        Integer slBanDau = 1;
//        for (ChiTietHoaDon sp:listHDCT){
//            if (sp.getIdCTSP().getId().equals(idCTSP) && sp.getIdHoaDon().getId().equals(idHoaDon)){
//                System.out.println("idCTSP"+sp.getIdCTSP().getId());
//                System.out.println("ID hóa đơn"+sp.getIdHoaDon().getId());
//
//                sp.setSoLuong(sp.getSoLuong()+1);
//                hoaDonChiTietRepository.save(sp);
//
////                slBanDau = sp.getSoLuongMua();
//                spTonTaiTrongGioHang=true;
//                System.out.println("-------Số lượng sản phẩm----------"+sp.getSoLuong());
//                System.out.println("-------Số lượng sản test----------"+slBanDau);
//                break;
//            }
//        }
//
//        Double donGia = null;
//        if (!spTonTaiTrongGioHang){
//            for (ChiTietSanPham sp:listCTSP){
//                if (sp.getId()==idCTSP){
//                    donGia = sp.getDonGia();
//                }
//            }
//
//            SPCT ctsp = new SPCT();
//            ctsp.setId(idCTSP);
//            hdct.setSpct(ctsp);
//
//            HoaDon hoaDon = new HoaDon();
//            hoaDon.setId(idHoaDon);
//            hdct.setHoaDon(hoaDon);
//            hdct.setSoLuong(1);
//            hdct.setDonGia(donGia);
//
//            hoaDonChiTietRepository.save(hdct);
//
//        }
//
//        return "redirect:/ban-hang/detail-hoa-don/" + idHoaDon;
//    }
//
//    @PostMapping("/delete-hdct/{idHDCT}")
//    public String deleteHDCT(@PathVariable Integer idHDCT,
//                             @RequestParam Integer idHoaDon){
//
//        for (HDCT hdct:listHDCT){
//            if (hdct.getId()==idHDCT){
//                hoaDonChiTietRepository.delete(hdct);
//            }
//        }
//
//        return "redirect:/ban-hang/detail-hoa-don/" + idHoaDon;
//    }
//
//    //Thanh toán
//    @PostMapping("/thanh-toan/{idHoaDon}")
//    public String thanhToanSanPham(@PathVariable Integer idHoaDon,
//                                   @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date ngayTao,
//                                   @RequestParam Integer idKhachHang){
//
//        for (int i=0;i<listHoaDon.size();i++){
//            if (listHoaDon.get(i).getId()==idHoaDon){
//                HoaDon hoaDon = new HoaDon();
//                hoaDon.setId(idHoaDon);
//                KhachHang khachHang = new KhachHang();
//                khachHang.setId(idKhachHang);
////                hoaDon.set(khachHang);
//                capMhatSoLuong();
//                hoaDon.setTrangThai(1);
//                hoaDon.setNgayTao( ngayTao);
//                hoaDonRepository.save(hoaDon);
//            }
//        }
//
//        return "redirect:/ban-hang";
//    }
//
//    //Cập nhật só lượng sau khi thanh toán
//    public void capMhatSoLuong(){
//        for (HDCT hdct:listHDCT){
//            int idSPCT = hdct.getSpct().getId();
//            int soLuong = hdct.getSoLuong();
//            for (SPCT sp:listCTSP){
//                if (sp.getId()==idSPCT){
//                    sp.setSoLuong(sp.getSoLuong()-soLuong);
//                    sanPhamChiTietRepository.save(sp);
//                }
//            }
//        }
//    }

}
