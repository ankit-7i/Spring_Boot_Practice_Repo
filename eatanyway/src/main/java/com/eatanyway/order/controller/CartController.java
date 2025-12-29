package com.eatanyway.order.controller;

import com.eatanyway.order.model.CartItem;
import com.eatanyway.order.service.CartService;
import com.eatanyway.order.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final OrderService orderService;

    public CartController(CartService cartService,
                          OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @PostMapping("/add")
    @ResponseBody
    public String addToCart(
            @RequestParam int itemId,
            @RequestParam String name,
            @RequestParam double price,
            HttpSession session) {

        CartItem item = new CartItem();
        item.setItemId(itemId);
        item.setName(name);
        item.setPrice(price);

        cartService.addToCart(session, item);
        return "Added to cart";
    }

    @PostMapping("/checkout")
    public String checkout(HttpSession session) {
        orderService.placeOrder(session);
        return "redirect:/customer/home";
    }
}

