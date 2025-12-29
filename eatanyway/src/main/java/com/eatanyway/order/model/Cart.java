package com.eatanyway.order.model;

import java.util.*;

public class Cart {

    private final Map<Integer, CartItem> items = new HashMap<>();

    public void addItem(CartItem item) {
        if (items.containsKey(item.getItemId())) {
            CartItem existing = items.get(item.getItemId());
            existing.setQuantity(existing.getQuantity() + 1);
        } else {
            item.setQuantity(1);
            items.put(item.getItemId(), item);
        }
    }

    public Collection<CartItem> getItems() {
        return items.values();
    }

    public double getSubTotal() {
        return items.values()
                .stream()
                .mapToDouble(CartItem::getTotal)
                .sum();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
