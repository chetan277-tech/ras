<!DOCTYPE html>
<html>
<head>
    <title>Place Order</title>
    <script type="text/javascript">
        // Function to dynamically add input fields based on number of ingredients
        function generateFields() {
            var numIngredients = document.getElementById("num_ingredients").value;
            var container = document.getElementById("ingredientsContainer");
            container.innerHTML = ""; // Clear existing fields

            for (var i = 1; i <= numIngredients; i++) {
                container.innerHTML += "<label>Ingredient Name " + i + ":</label>" +
                    "<input type='text' name='ingredient_name_" + i + "' required><br>" +
                    "<label>Quantity " + i + ":</label>" +
                    "<input type='number' name='quantity_" + i + "' required><br><br>";
            }
        }
    </script>
</head>
<body>
    <h2>Inventory Order Form</h2>
    <form action="inventoryorder" method="post">
        <label>Sales Manager:</label>
        <input type="text" name="sales_manager" required><br><br>

        <label>Number of Ingredients:</label>
        <input type="number" id="num_ingredients" name="num_ingredients" min="1" required oninput="generateFields()"><br><br>

        <div id="ingredientsContainer">
            <!-- Dynamic fields will be added here -->
        </div>

        <input type="submit" value="Submit Order">
    </form>
</body>
</html>
