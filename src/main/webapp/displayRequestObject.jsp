<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Display Request Object</title>
</head>
<body>
    <h2>Request Object Details</h2>
    
    <table border="1">
        <thead>
            <tr>
                <th>Parameter Name</th>
                <th>Parameter Value(s)</th>
            </tr>
        </thead>
        <tbody>
            <% 
                // Loop through all the request parameter names
                java.util.Enumeration<String> parameterNames = request.getParameterNames();
                
                while (parameterNames.hasMoreElements()) {
                    String paramName = parameterNames.nextElement();
                    String[] paramValues = request.getParameterValues(paramName);
            %>
            <tr>
                <td><%= paramName %></td>
                <td>
                    <% 
                        for (String value : paramValues) {
                            out.print(value + " ");
                        }
                    %>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>

</body>
</html>
