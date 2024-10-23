package com.inventory.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.inventory.model.InventoryOrder;
import com.inventory.model.InventoryOrderItem;
import com.inventory.model.InventoryOrderDAO;

@WebServlet("/inventoryorder")
public class InventoryOrderController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String salesManager = request.getParameter("sales_manager");
        int numIngredients = Integer.parseInt(request.getParameter("num_ingredients"));

        // List to hold the order items
        List<InventoryOrderItem> orderItems = new ArrayList<>();

        // Loop through all the ingredients and quantities from the form
        for (int i = 1; i <= numIngredients; i++) {
            String ingredientName = request.getParameter("ingredient_name_" + i);
            int quantity = Integer.parseInt(request.getParameter("quantity_" + i));
            orderItems.add(new InventoryOrderItem(ingredientName, quantity));
        }

        // Create an Order object
        InventoryOrder order = new InventoryOrder(salesManager, orderItems);

        // Save the order to the database using OrderDAO
        InventoryOrderDAO orderDAO = new InventoryOrderDAO();
        try {
            boolean isSaved = orderDAO.saveOrder(order);
            if (isSaved) {
                response.sendRedirect("orderSucess.jsp");
            } else {
            	response.sendRedirect("orderFailure.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("orderFailure.jsp");
        }
    }
}
