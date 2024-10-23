<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login Form</h2>
    <form action="user" method="post">
        <input type="hidden" name="action" value="login">
        <label>Email:</label>
        <input type="email" name="email" required><br>
        <label>Password:</label>
        <input type="password" name="password" required><br>
        <label for="role">Choose your role:</label>
		<select id="role" name="role">
		  <option value="customer">Customer</option>
		  <option value="salesmanager">Sales Manager</option>
		  <option value="manager">Manager</option>
		  <option value="itSupport">IT Support</option>
		  <option value="kitchenstaff">Kitchen Staff</option>
		</select> <br>
        <input type="submit" value="Login">
    </form>
    <p>don't have an account <a href="signup.jsp">signup</a></p>
</body>
</html>
