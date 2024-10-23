package com.item.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.item.model.Item;
import com.item.model.ItemDAO;


public class ItemController extends HttpServlet {
	public void handleitems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ItemDAO itemDAO = new ItemDAO();
        try {
            List<Item> items = itemDAO.getItems();
            request.setAttribute("items", items);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.getRequestDispatcher("orderFailure.jsp").forward(request, response);
        }
    }
}
