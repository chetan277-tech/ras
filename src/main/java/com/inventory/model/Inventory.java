package com.inventory.model;

public class Inventory{
	private int ingredient_id;
	private String ingredient_name;
	private int quantity;
	public Inventory(String ingredient_name,int quantity) {
		this.ingredient_name=ingredient_name;
		this.quantity=quantity;
	}
	
	public int getId() {
		return ingredient_id;
	}
	
	public String getName() {
		return ingredient_name;
	}
	
	public int getquantity() {
		return quantity;
	}
}
