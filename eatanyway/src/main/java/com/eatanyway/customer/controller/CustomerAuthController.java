package com.eatanyway.customer.controller;

import com.eatanyway.customer.model.Customer;
import com.eatanyway.customer.service.CustomerAuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerAuthController {

    private final CustomerAuthService authService;

    public CustomerAuthController(CustomerAuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "customer/login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "customer/signup";
    }

    @PostMapping("/signup")
    public String signup(Customer customer, Model model) {

        String result = authService.signup(customer);

        if (!"SUCCESS".equals(result)) {
            model.addAttribute("error", result);
            return "customer/signup";
        }

        return "redirect:/customer/login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        Customer customer = authService.login(email, password);

        if (customer == null) {
            model.addAttribute("error", "Invalid email or password");
            return "customer/login";
        }

        session.setAttribute("customer", customer);
        return "redirect:/customer/home";
    }

    @GetMapping("/home")
    public String home(HttpSession session) {
        if (session.getAttribute("customer") == null) {
            return "redirect:/customer/login";
        }
        return "customer/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/customer/login";
    }
}

