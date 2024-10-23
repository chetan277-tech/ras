package com.inventory.model;

public class InventoryOrderItem {
    private String ingredientName;
    private int quantity;

    public InventoryOrderItem(String ingredientName, int quantity) {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public int getQuantity() {
        return quantity;
    }
}
