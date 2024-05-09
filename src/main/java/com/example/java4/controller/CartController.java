package com.example.java4.controller;

import com.example.java4.entities.SanPham;
import com.example.java4.repositories.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    SanPhamRepository sanPhamRepository;



    @GetMapping("/view")
    public String view(Model model){

        Pageable pageable = PageRequest.of(0,3);
        List<SanPham> listSP = sanPhamRepository.getDistinctTopBy3(pageable);
        model.addAttribute("listSP",listSP);
        return "cart";
    }


}
