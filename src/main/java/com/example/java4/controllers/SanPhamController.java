package com.example.java4.controllers;

import com.example.java4.dto.san_pham.StoreRequest;
import com.example.java4.entities.NhanVien;
import com.example.java4.entities.SanPham;
import com.example.java4.repositories.SanPhamRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/san-pham")
public class SanPhamController {
    //    @RequestMapping(name="login", method = RequestMethod.POST)
    StoreRequest rem;
    @Autowired
    SanPhamRepository spRepo;

    public SanPhamController() {
        rem = new StoreRequest();
    }

    //    Lấy tất cả các dữ liệu danh sách nhân viên
    @GetMapping("/get-all")
    public List<SanPham> index() {
        return spRepo.findAll();
    }

    // Lấy dữ liệu đối tượng Nhân Viên theo Id
    @GetMapping("/detail/{id}")
    public SanPham detail(@PathVariable("id") Integer id, Model model) {
        return spRepo.findById(id).orElse(null);
    }

    //  Lấy danh sách Nhân Viên có phân trang, 5 phần tử trên 1 trang
    @GetMapping("/phan-trang")
    public List<SanPham> page(@RequestParam(value = "page", defaultValue = "0") Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 12);
        return spRepo.findByTrangThai(1, pageable).getContent();
    }


    //    Chức năng thêm mới Nhân Viên
    @PostMapping("/create")
    public String create(
            @RequestBody @Valid StoreRequest sanPhamRequest,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            System.out.println(result.getFieldError().getDefaultMessage());
            return "Thêm mới sản phẩm thất bại";
        } else {
            SanPham sanPham = new SanPham();
            sanPham.setTen(sanPhamRequest.getTen());
            sanPham.setMa(sanPhamRequest.getMa());
            sanPham.setTrangThai(sanPhamRequest.getTrangThai());

            spRepo.save(sanPham);
            return "Thêm mới sản phẩm thành công";
        }
    }


    //    Chức năng cập nhật nhân viên
    @PutMapping("/update-product")
    public String doUpdate(
            @RequestBody @Valid StoreRequest sanPhamRequest,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            System.out.println(result.getFieldError().getDefaultMessage());
            return "Sửa sản phẩm thất bại";
        } else {

            SanPham sanPham = new SanPham();
            sanPham.setId(sanPhamRequest.getId());
            sanPham.setTen(sanPhamRequest.getTen());
            sanPham.setMa(sanPhamRequest.getMa());
            sanPham.setTrangThai(sanPhamRequest.getTrangThai());

            spRepo.save(sanPham);
            return "Sửa sản phẩm thành công";
        }
    }

    //    Chức năng xóa màu sắc
    @DeleteMapping("/delete-product/{id}")
    public String delete(@PathVariable(value = "id") SanPham sanPham) {

        if (sanPham == null) {
            return "Xóa sản phẩm thất bại";
        }

        spRepo.delete(sanPham);
        return "Xóa sản phẩm thành công ";
    }

}