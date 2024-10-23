package com.item.model;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.item.model.Item;
import com.util.Util;

public class ItemDAO extends HttpServlet {
	public List<Item> getItems() throws SQLException{
		List<Item> items = new ArrayList<>();
        String itemQuery = "SELECT * FROM Items";
        
        try (Connection connection = Util.getConnection();
             PreparedStatement itemStmt = connection.prepareStatement(itemQuery)) {
            ResultSet itemRs = itemStmt.executeQuery();

            while (itemRs.next()) {
                int itemId = itemRs.getInt("item_id");
                String itemName = itemRs.getString("item_name");
                int price=itemRs.getInt("price");
                String description=itemRs.getString("description");
                

                items.add(new Item(itemName,price,description));
            }
        }
        return items;
	}
}
