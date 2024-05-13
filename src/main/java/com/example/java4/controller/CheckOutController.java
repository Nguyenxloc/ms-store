package com.example.java4.controller;

import com.example.java4.entities.HDCT;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/checkout")
public class CheckOutController {

    @Autowired
    HttpSession session;

    @GetMapping("/index")
    public String checkout(Model model,HttpSession session) {



        double subtotal = (double) session.getAttribute("total"); // Assuming total is calculated previously
        double shipping = 30000;

        List<HDCT> listHDCT = (List<HDCT>) session.getAttribute("listHDCT");

                // Add subtotal and shipping to model attributes
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("listHDCT", listHDCT);
        model.addAttribute("shipping", shipping); // Set shipping value as needed

        System.out.println(subtotal);
        System.out.println(shipping);
        return "checkout";
    }

    @GetMapping("/shop")
    public String shop() {
        return "shop";
    }




}
