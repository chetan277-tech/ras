package com.item.model;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class Item
 */
public class Item extends HttpServlet {
	private int itemId;
	private String itemName;
	private int price;
	private String description;
       
  
    public Item(String itemName,int price,String description) {
        this.itemName=itemName;
        this.price=price;
        this.description=description;
    }
    public int getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
