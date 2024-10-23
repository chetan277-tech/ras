package com.item.model;

import java.util.List;

public class Order {
    private int orderId;
    private String customerName;
    private String customerEmail;
    private List<OrderItem> orderItems;

    public Order(String customerName, String customerEmail, List<OrderItem> orderItems) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.orderItems = orderItems;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}
