package com.eatanyway.menu.controller;

import com.eatanyway.menu.model.MenuItem;
import com.eatanyway.menu.service.MenuService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/menu")
public class AdminMenuController {

    private final MenuService menuService;

    public AdminMenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public String menuPage(HttpSession session, Model model) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/login";
        }

        model.addAttribute("categories", menuService.getCategories());
        return "admin/menu";
    }

    @PostMapping("/category")
    public String addCategory(@RequestParam String name) {
        menuService.addCategory(name);
        return "redirect:/admin/menu";
    }

    @PostMapping("/item")
    public String addItem(MenuItem item) {
        menuService.addMenuItem(item);
        return "redirect:/admin/menu";
    }
}

