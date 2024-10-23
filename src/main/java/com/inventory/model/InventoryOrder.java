package com.inventory.model;

import java.util.List;

public class InventoryOrder {
    private int orderId;
    private String salesManager;
    private List<InventoryOrderItem> orderItems;

    public InventoryOrder(String salesManager, List<InventoryOrderItem> orderItems) {
        this.salesManager = salesManager;
        this.orderItems = orderItems;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getSalesManager() {
        return salesManager;
    }

    public List<InventoryOrderItem> getOrderItems() {
        return orderItems;
    }
}
