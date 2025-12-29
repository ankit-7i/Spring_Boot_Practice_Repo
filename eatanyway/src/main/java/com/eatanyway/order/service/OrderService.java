package com.eatanyway.order.service;

import com.eatanyway.order.dao.OrderDao;
import com.eatanyway.order.model.Cart;
import com.eatanyway.order.model.Order;
import com.eatanyway.customer.model.Customer;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Order placeOrder(HttpSession session) {

        Cart cart = (Cart) session.getAttribute("cart");
        Customer customer = (Customer) session.getAttribute("customer");

        if (cart == null || cart.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        double subTotal = cart.getSubTotal();

        // Charges
        double handlingCharge = 20;
        double surgeCharge = subTotal > 500 ? 30 : 0;

        // New Year Discount (10%)
        double discount = subTotal * 0.10;

        double finalAmount =
                subTotal + handlingCharge + surgeCharge - discount;

        Order order = new Order();
        order.setCustomerId(customer.getCustomerId());
        order.setTotal(finalAmount);

        int orderId = orderDao.saveOrder(order);
        order.setOrderId(orderId);

        session.removeAttribute("cart");

        return order;
    }
}
