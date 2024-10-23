package com.inventory.model;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InventoryDAO extends HttpServlet {
	public boolean handleinventory() {
		String query="INSERT INTO Ingredients(ingredient_name,quantity) values (?,?)";
		
	}
}
