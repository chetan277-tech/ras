<%@ page import ="com.item.model.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="com.item.model.OrderItem" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order History</title>
</head>
<body>
    <h2>Order History</h2>

    <%
        // Retrieve the "orders" attribute from the request scope
        List<Order> orders = (List<Order>) request.getAttribute("orders");

        // Check if the orders list is empty or null
        if (orders == null || orders.isEmpty()) {
    %>
        <p>No orders found.</p>
    <%
        } else {
            // Iterate over the orders list
            for (Order order : orders) {
    %>
        <h3>Order ID: <%= order.getOrderId() %></h3>
        <p>Customer Name: <%= order.getCustomerName() %></p>
        <p>Customer Email: <%= order.getCustomerEmail() %></p>
        <h4>Items:</h4>
        <ul>
            <%
                // Iterate over the items in each order
                for (OrderItem item : order.getOrderItems()) {
            %>
                <li><%= item.getItemName() %> - Quantity: <%= item.getQuantity() %></li>
            <%
                }
            %>
        </ul>
    <%
            }
        }
    %>
</body>
</html>
