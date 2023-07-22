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

// Query to fetch all the tool names from the database
$query = "SELECT name FROM tools";

// Execute the query
$result = $conn->query($query);

// Check if the query was successful
if ($result) {
    // Fetch the tool names and store them in an array
    $tools = array();
    while ($row = $result->fetch_assoc()) {
        $tools[] = $row['name'];
    }

    // Close the result set
    $result->close();

    // Close the database connection
    $conn->close();

    // Return the tools array as JSON
    echo json_encode($tools);
} else {
    // Error occurred while fetching tools, return an empty array
    $conn->close();
    echo json_encode(array());
}
?>
