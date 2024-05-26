package com.example.java4.controller.controller_tai;


import com.example.java4.entities.ett_tai.KhuyenMai;
import com.example.java4.repositories.res_tai.KhuyenMaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/promotion")
public class KhuyenMaiController {

    @Autowired
    KhuyenMaiRepository khuyenMaiRepo;



    @GetMapping("/list")
    public String getViewListPromotion(Model model, @RequestParam("page")
            Optional<Integer> pageParam){

        int pageNumber = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(pageNumber,5);
        Page<KhuyenMai> pageData =  khuyenMaiRepo.findAll(pageable);
        model.addAttribute("pageKM", khuyenMaiRepo.findAll(pageable));
        return "/view/view_tai/promotion/view.jsp";
    }

    // Mapping để hiển thị form thêm khuyến mãi
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("khuyenmai", new KhuyenMai());
        return "/view/view_tai/promotion/add.jsp";
    }

    // Mapping để xử lý việc thêm khuyến mãi
    @PostMapping("/add")
    public String addPromotion(@ModelAttribute("khuyenmai") KhuyenMai khuyenmai) {
        khuyenmai.setId(UUID.randomUUID()); // Tạo ID mới cho khuyến mãi
        khuyenMaiRepo.save(khuyenmai); // Lưu khuyến mãi vào cơ sở dữ liệu
        return "redirect:/promotion/list"; // Chuyển hướng về trang danh sách khuyến mãi sau khi thêm thành công
    }

    // Mapping để hiển thị form sửa khuyến mãi
    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") UUID id, Model model) {
        KhuyenMai khuyenmai = khuyenMaiRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid promotion ID"));
        model.addAttribute("khuyenmai", khuyenmai);
        return "/view/view_tai/promotion/edit.jsp";
    }

    // Mapping để xử lý việc sửa khuyến mãi
    @PostMapping("/edit")
    public String editPromotion(@ModelAttribute("khuyenmai") KhuyenMai khuyenmai) {
        khuyenMaiRepo.save(khuyenmai); // Lưu thông tin khuyến mãi sau khi sửa vào cơ sở dữ liệu
        return "redirect:/promotion/list"; // Chuyển hướng về trang danh sách khuyến mãi sau khi sửa thành công
    }

    // Mapping để xóa khuyến mãi
    @GetMapping("/delete")
    public String deletePromotion(@RequestParam("id") UUID id) {
        khuyenMaiRepo.deleteById(id); // Xóa khuyến mãi từ cơ sở dữ liệu
        return "redirect:/promotion/list"; // Chuyển hướng về trang danh sách khuyến mãi sau khi xóa thành công
    }




}
