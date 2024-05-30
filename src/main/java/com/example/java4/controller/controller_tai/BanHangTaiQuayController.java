package com.example.java4.controller.controller_tai;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BanHangTaiQuayController {


        @GetMapping("/ban-tai-quay")
        public String getBanTaiQuay(){
            return "/view/view_tai/sell/banHangTaiQuay.jsp";
        }
}
