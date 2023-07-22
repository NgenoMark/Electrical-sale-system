<?php
session_start();
// Replace these with your actual database connection details
$servername = "localhost";
$dbusername = "root";
$password = "";
$dbname = "electrical_sales_company";

// Create a connection to the database
$conn = new mysqli($servername, $dbusername, $password, $dbname);

// Check if the connection was successful
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Function to calculate the total price based on the number of tools and price per tool
function calculateTotalPrice($number_of_tools, $price_per_tool) {
    return $number_of_tools * $price_per_tool;
}

// Check if the form is submitted
if ($_SERVER["REQUEST_METHOD"] === "POST") {
    // Check if all form fields are present and not empty
    if (
        isset($_POST["buyer-name"]) && !empty($_POST["buyer-name"]) &&
        isset($_POST["phone-number"]) && !empty($_POST["phone-number"]) &&
        isset($_POST["id-number"]) && !empty($_POST["id-number"]) &&
        isset($_POST["tool-selection"]) && !empty($_POST["tool-selection"]) &&
        isset($_POST["number-of-tools"]) && !empty($_POST["number-of-tools"]) &&
        isset($_POST["product-price"]) && !empty($_POST["product-price"])
    ) {
        // Get the submitted form data
        $buyer_name = $_POST["buyer-name"];
        $phone_number = $_POST["phone-number"];
        $id_number = $_POST["id-number"];
        $tool_name = $_POST["tool-selection"];
        $number_of_tools = $_POST["number-of-tools"];
        $price_per_tool = $_POST["product-price"];

        // Calculate the total price
        $total_price = calculateTotalPrice($number_of_tools, $price_per_tool);

        // Prepare and execute the query to insert data into the database
        $query = "INSERT INTO sales (buyer_name, phone_number, id_number, tool_name, number_of_tools, price_per_tool, total_price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        $stmt = $conn->prepare($query);
        $stmt->bind_param("ssssidd", $buyer_name, $phone_number, $id_number, $tool_name, $number_of_tools, $price_per_tool, $total_price);
    
        if ($stmt->execute()) {
            // Sale recorded successfully, set a session variable to indicate success
            $_SESSION["success_message"] = "Successful insertion!";
            // Close the database connection
            $conn->close();
            // Redirect to the success page after 2 seconds
            header("Refresh: 2; URL=panel.html");
            exit();
        } else {
            // Error occurred while recording the sale, redirect back to the form or display an error message
            header("Location: selltools.html");
            exit();
        }
    } else {
        // Form data is incomplete or missing, redirect back to the form or display an error message
        header("Location: selltools.html");
        exit();
    }
}

// Close the database connection
$conn->close();
?>
