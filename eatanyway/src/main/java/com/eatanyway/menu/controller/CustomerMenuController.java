package com.eatanyway.menu.controller;

import com.eatanyway.menu.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/menu")
public class CustomerMenuController {

    private final MenuService menuService;

    public CustomerMenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public String showMenu(Model model) {
        model.addAttribute("categories", menuService.getCategories());
        return "customer/menu";
    }

    @GetMapping("/category/{id}")
    @ResponseBody
    public Object getMenuByCategory(@PathVariable int id) {
        return menuService.getMenuByCategory(id);
    }
}

