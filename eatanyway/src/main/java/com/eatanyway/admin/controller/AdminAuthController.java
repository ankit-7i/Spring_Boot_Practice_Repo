package com.eatanyway.admin.controller;

import com.eatanyway.admin.model.AdminUser;
import com.eatanyway.admin.service.AdminAuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminAuthController {

    private final AdminAuthService authService;

    public AdminAuthController(AdminAuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "admin/login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "admin/signup";
    }

    @PostMapping("/signup")
    public String signup(AdminUser admin, Model model) {

        String result = authService.signup(admin);

        if (!"SUCCESS".equals(result)) {
            model.addAttribute("error", result);
            return "admin/signup";
        }

        return "redirect:/admin/login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        AdminUser admin = authService.login(email, password);

        if (admin == null) {
            model.addAttribute("error", "Invalid email or password");
            return "admin/login";
        }

        session.setAttribute("admin", admin);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/login";
        }

        return "admin/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
}
