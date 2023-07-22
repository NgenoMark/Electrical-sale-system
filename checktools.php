<?php
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

// Query to select all data from the 'tools' table
$query = "SELECT * FROM tools";
$result = $conn->query($query);

// Check if any records were found
if ($result->num_rows > 0) {
    // Start creating the table to display the data
    echo '<table border="1">';
    echo '<tr><th>ID</th><th>Name of Tool</th><th>Power Value</th><th>Power Unit</th><th>Watts</th><th>Price</th></tr>';

    // Loop through each row and display the data in the table
    while ($row = $result->fetch_assoc()) {
        echo '<tr>';
        echo '<td>' . $row["id"] . '</td>';
        echo '<td>' . $row["name"] . '</td>';
        echo '<td>' . $row["power_value"] . '</td>';
        echo '<td>' . $row["power_unit"] . '</td>';
        echo '<td>' . $row["watts"] . '</td>';
        echo '<td>' . $row["price"] . '</td>';
        echo '</tr>';
    }

    // Close the table
    echo '</table>';
} else {
    // If no records found, display a message
    echo "No records found.";
}

// Close the database connection
$conn->close();
?>
