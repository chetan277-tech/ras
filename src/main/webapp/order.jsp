<!DOCTYPE html>
<html>
<head>
    <title>Place Order</title>
    <script type="text/javascript">
        function generateFields() {
            var numItems = document.getElementById("num_items").value;
            var container = document.getElementById("itemsContainer");
            container.innerHTML = ""; // Clear existing fields

            for (var i = 1; i <= numItems; i++) {
                container.innerHTML += "<label>Item Name " + i + ":</label>" +
                    "<input type='text' name='item_name_" + i + "' required><br>" +
                    "<label>Quantity " + i + ":</label>" +
                    "<input type='number' name='quantity_" + i + "' required><br><br>";
            }
        }
    </script>
</head>
<body>
    <h2>Place Order</h2>
    <form action="order" method="post">
        <input type="hidden" name="action" value="placeOrder">
        <label>Customer Name:</label>
        <input type="text" name="customer_name" required><br><br>

        <label>Customer Email:</label>
        <input type="email" name="customer_email" required><br><br>

        <label>Number of Items:</label>
        <input type="number" id="num_items" name="num_items" min="1" required oninput="generateFields()"><br><br>

        <div id="itemsContainer">
            <!-- Item fields will be added here -->
        </div>

        <input type="submit" value="Place Order">
      </form>
      <a href="order" value="viewHistory">asdfja</a>
      
    
</body>
</html>
