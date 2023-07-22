<?php
// Replace these with your actual database connection details
$servername = "localhost";
$dbusername = "root";
$dbpassword = "";
$dbname = "electrical_sales_company";

// Function to convert power units to watts
function convertToWatts($powerValue, $powerUnit) {
    switch ($powerUnit) {
        case "amps":
            // Conversion formula for Amps to Watts
            return $powerValue * 230; // Assuming 230 volts (you can change this value as needed)
        case "ohms":
            // Conversion formula for Ohms to Watts
            return pow($powerValue, 2) * 230; // Assuming 230 volts (you can change this value as needed)
        case "volts":
            // Volts is already in Watts
            return $powerValue * 2;
        case "watts":
            // Watts is the universal unit, so no conversion needed
            return $powerValue;
        // Add additional power unit conversions here if needed
        default:
            return 0;
    }
}

// Function to handle the form submission
function addTool($name, $powerValue, $powerUnit, $price, $conn) {
    // Convert power measurement to watts
    $watts = convertToWatts($powerValue, $powerUnit);

    // Prepare and execute the query to insert data into the database
    $query = "INSERT INTO tools (name, power_value, power_unit, watts, price) VALUES (?, ?, ?, ?, ?)";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("ssssd", $name, $powerValue, $powerUnit, $watts, $price);
    
    if ($stmt->execute()) {
        // Tool added successfully, redirect to a success page or perform any other desired action
        header("Location: add_tool_success.php");
        exit();
    } else {
        // Error occurred while adding tool, redirect back to the form or display an error message
        header("Location: add_tool_form.php");
        exit();
    }
}

if ($_SERVER["REQUEST_METHOD"] === "POST") {
    // Check if the form is submitted
    if (isset($_POST["tool-name"]) && isset($_POST["power-value"]) && isset($_POST["power-unit"]) && isset($_POST["tool-price"])) {
        // Get the submitted form data
        $name = $_POST["tool-name"];
        $powerValue = $_POST["power-value"];
        $powerUnit = $_POST["power-unit"];
        $price = $_POST["tool-price"];

        // Create a connection to the database
        $conn = new mysqli($servername, $dbusername, $dbpassword, $dbname);

        // Check if the connection was successful
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        // Call the addTool function to insert the data into the database
        addTool($name, $powerValue, $powerUnit, $price, $conn);

        // Close the database connection
        $conn->close();
    }
}
?>
