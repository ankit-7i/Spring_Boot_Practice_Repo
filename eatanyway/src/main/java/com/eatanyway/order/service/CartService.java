package com.eatanyway.order.service;

import com.eatanyway.order.model.Cart;
import com.eatanyway.order.model.CartItem;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    public Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    public void addToCart(HttpSession session, CartItem item) {
        Cart cart = getCart(session);
        cart.addItem(item);
    }
}
