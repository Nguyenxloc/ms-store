package com.example.java4.controller.controller_tai;


import com.example.java4.config.UserInfor;
import com.example.java4.entities.KhachHang;
import com.example.java4.repositories.KhachHangRepository;
import com.example.java4.request.req_tai.KhachHangDTO;
import com.example.java4.service.service_tai.StorageService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    KhachHangRepository khachHangRepository;



    // Đường dãn Upload file HinhAnh
    private static final String UPLOAD_DIR = "src/main/webapp/image/";

    @Autowired
    private StorageService storageService;

    @GetMapping("")
    public String home(Model model) {
        // Khởi tạo khachHangDTO mới nếu chưa tồn tại
        if (!model.containsAttribute("khachHangDTO")) {
            model.addAttribute("khachHangDTO", new KhachHangDTO());
        }
        return "/view/view_tai/home/trangChu.jsp";
    }


    // Hiển thị giao diện đăng nhâp của User
    @GetMapping("/login")
    public String view(Model model) {
        if (!model.containsAttribute("khachHangDTO")) {
            model.addAttribute("khachHangDTO", new KhachHangDTO());
        }
        return "/view/view_tai/home/trangChu.jsp";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> login( @ModelAttribute("khachHangDTO") KhachHangDTO khachHangDTO,
                                                     HttpSession session,
                                                     BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            response.put("success", false);
            response.put("errors", result.getAllErrors());
            return ResponseEntity.ok(response);
        }

        KhachHang khachHang = khachHangRepository.findByTaiKhoan(khachHangDTO.getTaiKhoan());
        if (khachHang != null) {
            if (khachHang.getMatKhau().equals(khachHangDTO.getMatKhau())) {
                session.setAttribute("user", khachHang);
                UserInfor.idKhachHang = khachHang.getId();
                System.out.println(UserInfor.idKhachHang);
                response.put("success", true);
                response.put("successMessage", "Đăng nhập thành công!");
                response.put("redirectUrl", "/home");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("errorPassword", "Sai mật khẩu");
                return ResponseEntity.ok(response);
            }
        } else {
            response.put("success", false);
            response.put("errorUsername", "Tài khoản không tồn tại");
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        // Xóa thông tin người dùng đã đăng nhập khỏi session
        session.removeAttribute("user");
        UserInfor.idKhachHang = null;
        redirectAttributes.addFlashAttribute("successMessage", "Đăng xuất thành công!");
        return "redirect:/home";
    }

    @PostMapping("/register")
    public String register( @ModelAttribute("khachHangDTO") KhachHangDTO khachHangDTO,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerErrors", result.getAllErrors());
            return "redirect:/home";
        }

        KhachHang existingUser = khachHangRepository.findByTaiKhoan(khachHangDTO.getTaiKhoan());
        if (existingUser != null) {
            redirectAttributes.addFlashAttribute("registerErrors", "Username already exists");
            return "redirect:/home";
        }

        // Save the new user
        KhachHang newUser = new KhachHang();
        newUser.setTaiKhoan(khachHangDTO.getTaiKhoan());
        newUser.setEmail(khachHangDTO.getEmail());
        newUser.setSdt(khachHangDTO.getSdt());
        newUser.setMatKhau(khachHangDTO.getMatKhau());
        newUser.setNgayTao( LocalDateTime.now());
        newUser.setTrangThai(khachHangRepository.ACTIVE);
        khachHangRepository.save(newUser);

        redirectAttributes.addFlashAttribute("successMessage", "Đăng ký thành công!");
        return "redirect:/home";
    }

    @GetMapping("/quan-ly-tai-khoan")
    public String viewAccount(Model model,HttpSession session) {
        KhachHang user = (KhachHang) session.getAttribute("user");
        if (user != null) {
            KhachHang updatedUser = khachHangRepository.findByIdKH(user.getId());
            if (updatedUser != null) {
                model.addAttribute("user", updatedUser);
            }
        }
        return "/view/view_tai/login/quan_ly_tai_khoan.jsp";
    }

    // Làm chức năng cập nhật thông tin cá nhân của User có upload file HinhAnh
    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute("user")  KhachHangDTO userDTO, BindingResult result,
                                @RequestParam("profileImage") MultipartFile file, RedirectAttributes redirectAttributes,
                                HttpSession session) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("updateErrors", result.getAllErrors());
            return "redirect:/home/quan-ly-tai-khoan";
        }

        KhachHang user = khachHangRepository.findByIdKH(UserInfor.idKhachHang);
        System.out.println(user.getTaiKhoan());
        if (user != null) {
            user.setHoTen(userDTO.getHoTen());
            user.setEmail(userDTO.getEmail());
            user.setSdt(userDTO.getSdt());
            user.setGioiTinh(userDTO.getGioiTinh());
            user.setNgaySinh(userDTO.getNgaySinh());
            user.setNgayTao(LocalDateTime.now());
            user.setNgaySua(LocalDateTime.now());
            user.setTrangThai(khachHangRepository.ACTIVE);

            // Lưu hình ảnh
            if (!file.isEmpty()) {
                try {
                    storageService.store(file);
                    user.setAnhDaiDien(file.getOriginalFilename());
                } catch (Exception e) {
                    e.printStackTrace();
                    // Xử lý lỗi khi lưu file
                    redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi lưu hình ảnh");
                    return "redirect:/home/quan-ly-tai-khoan";
                }
            }

            khachHangRepository.save(user);
            redirectAttributes.addFlashAttribute("successAccount", "Cập nhật thông tin thành công!");
            // Cập nhật lại thông tin mới của user
            redirectAttributes.addFlashAttribute("user", user);
            session.setAttribute("user", user);
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy người dùng!");
        }

        return "redirect:/home/quan-ly-tai-khoan";
    }
}