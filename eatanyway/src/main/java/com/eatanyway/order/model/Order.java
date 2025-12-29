package com.eatanyway.order.model;

public class Order {
    private int orderId;
    private int customerId;
    private double total;

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
