<?php
// Replace these with your actual database connection details
$servername = "localhost";
$dbusername = "root";
$dbpassword = "";
$dbname = "electrical_sales_company";

// Function to handle the login process
function login($username, $password, $conn) {
    $query = "SELECT * FROM users WHERE username = ? AND password = ?";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("ss", $username, $password);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows === 1) {
        // Redirect to panel.html on successful login
        header("Location: panel.html");
        exit();
    } else {
        // Invalid credentials, display an error message or redirect back to the login page
        header("Location: login.html");
        exit();
    }
}

if ($_SERVER["REQUEST_METHOD"] === "POST") {
    // Check if the form is submitted
    if (isset($_POST["username"]) && isset($_POST["password"])) {
        // Get the submitted username and password
        $username = $_POST["username"];
        $password = $_POST["password"];

        // Create a connection to the database
        $conn = new mysqli($servername, $dbusername, $dbpassword, $dbname);

        // Check if the connection was successful
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        // Call the login function
        login($username, $password, $conn);

        // Close the database connection
        $conn->close();
    }
}
?>
