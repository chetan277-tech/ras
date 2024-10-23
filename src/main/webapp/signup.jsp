<!DOCTYPE html>
<html>
<head>
    <title>Signup</title>
</head>
<body>
    <h2>Signup Form</h2>
    <form action="user" method="post">
        <input type="hidden" name="action" value="signup">
        <label>User Name:</label>
        <input type="text" name="user_name" required><br>
        <label>Password:</label>
        <input type="password" name="password" required><br>
        <label>Email:</label>
        <input type="email" name="email" required><br>
        <label>Phone Number:</label>
        <input type="text" name="phone_number" required><br>
        <label>Address:</label>
        <textarea name="address" required></textarea><br>
        <input type="submit" value="Signup">
    </form>
</body>
</html>
