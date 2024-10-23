package com.auth.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import com.auth.model.User;
import com.item.model.Item;
import com.item.model.ItemDAO;

import java.util.List;

@WebServlet("/user")
public class UserController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String role = request.getParameter("role");
        if ("signup".equals(action)) {
            handleSignup(request, response);
        } else if ("login".equals(action)) {
        	if ("customer".equals(role)) {
        		handleLogin(request, response);
        	}
        }
    }

    private void handleSignup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("user_name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone_number");
        String address = request.getParameter("address");

        User user = new User(userName, password, email, phoneNumber, address);

        try {
            boolean isRegistered = user.registerUser();
            if (isRegistered) {
                request.getRequestDispatcher("orderSucess.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("orderFailure.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.getRequestDispatcher("orderFailure.jsp").forward(request, response);
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            boolean isValidLogin = User.validateLogin(email, password);
            if (isValidLogin) {
            	ItemDAO itemDAO = new ItemDAO();
                try {
                    List<Item> items = itemDAO.getItems();
                    request.setAttribute("items", items);
                    request.getRequestDispatcher("customer.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                    request.getRequestDispatcher("orderFailure.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("orderFailure.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.getRequestDispatcher("orderFailure.jsp").forward(request, response);
        }
    }
}
