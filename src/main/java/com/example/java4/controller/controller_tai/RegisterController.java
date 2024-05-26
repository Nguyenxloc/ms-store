package com.example.java4.controller.controller_tai;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dang-ky")
public class RegisterController {

    @GetMapping("/singup")
    public String singup(){
        return "/view/view_tai/login/register.jsp";
    }
}
