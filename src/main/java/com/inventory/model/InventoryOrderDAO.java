package com.inventory.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import com.util.Util;

public class InventoryOrderDAO {

    // Method to save an order and its items in the database
    public boolean saveOrder(InventoryOrder order) throws SQLException {
        String orderQuery = "INSERT INTO orders (sales_manager) VALUES (?)";
        String orderItemQuery = "INSERT INTO order_items (order_id, ingredient_name, quantity) VALUES (?, ?, ?)";
        
        try (Connection connection = Util.getConnection();
             PreparedStatement orderStmt = connection.prepareStatement(orderQuery, Statement.RETURN_GENERATED_KEYS)) {
            
            // Insert into the orders table
            orderStmt.setString(1, order.getSalesManager());
            int rowsInserted = orderStmt.executeUpdate();

            if (rowsInserted > 0) {
                // Get the generated order_id
                int orderId = 0;
                var generatedKeys = orderStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                }

                // Insert into the order_items table
                try (PreparedStatement orderItemStmt = connection.prepareStatement(orderItemQuery)) {
                    for (InventoryOrderItem item : order.getOrderItems()) {
                        orderItemStmt.setInt(1, orderId);
                        orderItemStmt.setString(2, item.getIngredientName());
                        orderItemStmt.setInt(3, item.getQuantity());
                        orderItemStmt.addBatch();
                    }
                    orderItemStmt.executeBatch();
                }

                return true;
            }
        }

        return false;
    }
}
