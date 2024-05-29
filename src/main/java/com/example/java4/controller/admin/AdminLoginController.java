package com.example.java4.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {

    @GetMapping("/login")
    public String login(){
        return "/view_admin/account/login.jsp";
    }

    @GetMapping("/singup")
    public String singup(){
        return "/view_admin/account/register.jsp";
    }
}
