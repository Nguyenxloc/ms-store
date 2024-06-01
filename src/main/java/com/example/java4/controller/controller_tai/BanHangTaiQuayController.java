package com.example.java4.controller.controller_tai;

import com.example.java4.entities.ChiTietHoaDon;
import com.example.java4.entities.ChiTietSanPham;
import com.example.java4.entities.HoaDon;
import com.example.java4.entities.KhachHang;
import com.example.java4.repositories.HDCTRepository;
import com.example.java4.repositories.HoaDonRepository;
import com.example.java4.repositories.KhachHangRepository;
import com.example.java4.repositories.SPCTRepository;
import com.example.java4.response.HoaDonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ban-hang/sell")
public class BanHangTaiQuayController {


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
        List<HoaDon> listHoaDon = hoaDonRepository.findByTrangThaiOrderByNgayTaoDesc(HoaDonRepository.INACTIVE);
        listCTSP = sanPhamChiTietRepository.findAll();
        listHDCT = hoaDonChiTietRepository.findAll();
        listKH = khachHangRepository.findAll();

        // Chuyển đổi LocalDateTime thành String
        List<HoaDonDTO> listHoaDonDTO = new ArrayList<HoaDonDTO>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        for (HoaDon hoaDon : listHoaDon) {
            HoaDonDTO dto = new HoaDonDTO();
            dto.setId(hoaDon.getId());
            dto.setMa(hoaDon.getMa());
            dto.setNgayTao(hoaDon.getNgayTao().format(formatter));
//            dto.setTenKhachHang(hoaDon.getIdKhachHang().getHoTen());
            dto.setTrangThai(hoaDon.getTrangThai());
            listHoaDonDTO.add(dto);
        }

        model.addAttribute("listHoaDonDTO", listHoaDonDTO);

        model.addAttribute("listHoaDon", listHoaDon);
        model.addAttribute("listCTSP", listCTSP);
        model.addAttribute("listHDCT", listHDCT);
        model.addAttribute("listKH", listKH);

        return "/view/view_tai/sell/banHangTaiQuay";
    }

    // Thêm mới hóa hóa đơn
    @PostMapping("/add-hoa-don")
    public String themHoaDon(RedirectAttributes redirectAttributes) {
        List<HoaDon> hoaDonList = this.hoaDonRepository.findByTrangThai(this.hoaDonRepository.INACTIVE);
        int currentHoaDonCount = hoaDonList.size();

        // Nếu số lượng hóa đơn lớn hơn 5, gửi dữ liệu Error từ Controller sang View(file.jsp)
        if (currentHoaDonCount >= 5) {
            redirectAttributes.addFlashAttribute("currentHoaDonCount", currentHoaDonCount);
            redirectAttributes.addFlashAttribute("errorBillMax", "Bạn chỉ có thể tạo tối đa 5 đơn hàng");
            return "redirect:/ban-hang/sell";
        }

        LocalDateTime now =LocalDateTime.now();
        HoaDon hoaDon = new HoaDon();
        //Tạo mã tự sinh
        hoaDon.generateMa();
        hoaDon.setNgayTao(now);
        hoaDon.setTrangThai(0);
        try {
            //gửi dữ liệu success từ Controller sang View(file.jsp)
            this.hoaDonRepository.save(hoaDon);
            redirectAttributes.addFlashAttribute("success", "Hóa đơn được tạo thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Đã xảy ra lỗi khi tạo hóa đơn.");
        }

        hoaDonRepository.save(hoaDon);

        return "redirect:/ban-hang/sell";
    }


    @GetMapping("/delete-hoa-don/{idHD}")
    public String xoaHoaDon(Model model, @PathVariable("idHD") String idHD,
                            RedirectAttributes redirectAttributes) {

        try {
            hoaDonRepository.deleteById(idHD);
            redirectAttributes.addFlashAttribute("success", "Xóa hóa đơn thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Đã xảy ra lỗi khi xóa hóa đơn.");
        }
        return "redirect:/ban-hang/sell";

    }


}

