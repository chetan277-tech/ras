package com.item.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.util.Util;

public class OrderDAO {

    // Method to save the order to the database
    public boolean saveOrder(Order order) throws SQLException {
        String orderQuery = "INSERT INTO CustOrders (customer_name, customer_email) VALUES (?, ?)";
        String orderItemQuery = "INSERT INTO cust_order_items (order_id, item_name, quantity) VALUES (?, ?, ?)";
        
        try (Connection connection = Util.getConnection();
             PreparedStatement orderStmt = connection.prepareStatement(orderQuery, Statement.RETURN_GENERATED_KEYS)) {
            
            // Insert into the orders table
            orderStmt.setString(1, order.getCustomerName());
            orderStmt.setString(2, order.getCustomerEmail());
            int rowsInserted = orderStmt.executeUpdate();

            if (rowsInserted > 0) {
                // Get the generated order_id
                int orderId = 0;
                ResultSet generatedKeys = orderStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                }

                // Insert each item into the order_items table
                try (PreparedStatement itemStmt = connection.prepareStatement(orderItemQuery)) {
                    for (OrderItem item : order.getOrderItems()) {
                        itemStmt.setInt(1, orderId);
                        itemStmt.setString(2, item.getItemName());
                        itemStmt.setInt(3, item.getQuantity());
                        itemStmt.addBatch();
                    }
                    itemStmt.executeBatch();
                }
                return true;
            }
        }
        return false;
    }

    // Method to retrieve order history for a customer
    public List<Order> getOrderHistory(String customerEmail) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String orderQuery = "SELECT * FROM CustOrders WHERE customer_email = ?";
        String orderItemsQuery = "SELECT * FROM cust_order_items WHERE order_id = ?";
        
        try (Connection connection = Util.getConnection();
             PreparedStatement orderStmt = connection.prepareStatement(orderQuery)) {
            
            orderStmt.setString(1, customerEmail);
            ResultSet orderRs = orderStmt.executeQuery();

            while (orderRs.next()) {
                int orderId = orderRs.getInt("order_id");
                String customerName = orderRs.getString("customer_name");

                // Fetch the items for this order
                List<OrderItem> orderItems = new ArrayList<>();
                try (PreparedStatement itemsStmt = connection.prepareStatement(orderItemsQuery)) {
                    itemsStmt.setInt(1, orderId);
                    ResultSet itemsRs = itemsStmt.executeQuery();
                    while (itemsRs.next()) {
                        String itemName = itemsRs.getString("item_name");
                        int quantity = itemsRs.getInt("quantity");
                        orderItems.add(new OrderItem(itemName, quantity));
                    }
                }

                orders.add(new Order(customerName, customerEmail, orderItems));
            }
        }
        return orders;
    }
}
