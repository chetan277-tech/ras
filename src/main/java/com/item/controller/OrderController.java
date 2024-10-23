package com.item.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.item.model.Order;
import com.item.model.OrderItem;
import com.item.model.OrderDAO;

@WebServlet("/order")
public class OrderController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleViewOrderHistory(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("placeOrder".equals(action)) {
            handlePlaceOrder(request, response);
        } else if ("viewHistory".equals(action)) {
            handleViewOrderHistory(request, response);
        }
    }

    private void handlePlaceOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerName = request.getParameter("customer_name");
        String customerEmail = request.getParameter("customer_email");
        int numItems = Integer.parseInt(request.getParameter("num_items"));

        List<OrderItem> orderItems = new ArrayList<>();
        for (int i = 1; i <= numItems; i++) {
            String itemName = request.getParameter("item_name_" + i);
            int quantity = Integer.parseInt(request.getParameter("quantity_" + i));
            orderItems.add(new OrderItem(itemName, quantity));
        }

        Order order = new Order(customerName, customerEmail, orderItems);
        OrderDAO orderDAO = new OrderDAO();

        try {
            boolean isSaved = orderDAO.saveOrder(order);
            if (isSaved) {
                request.getRequestDispatcher("orderSucess.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("orderFailure.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.getRequestDispatcher("orderFailure.jsp").forward(request, response);
        }
    }

    private void handleViewOrderHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerEmail = "chetanvarun121@gmail.com";

        OrderDAO orderDAO = new OrderDAO();
        try {
            List<Order> orders = orderDAO.getOrderHistory(customerEmail);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("orderhistory.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.getRequestDispatcher("orderFailure.jsp").forward(request, response);
        }
    }
}
