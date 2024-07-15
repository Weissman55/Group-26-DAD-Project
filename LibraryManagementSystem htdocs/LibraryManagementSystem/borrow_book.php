<?php
// Database connection parameters
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "library_db";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Get title parameter from request
$title = isset($_GET['title']) ? $_GET['title'] : '';

// Prepare response array
$response = array();

// Check if title is provided
if (!empty($title)) {
    // SQL to update book availability
    $sql = "UPDATE books SET available = 0 WHERE title = ?";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("s", $title);

    // Execute query
    if ($stmt->execute()) {
        $response["success"] = true;
        $response["message"] = "Book '$title' borrowed successfully.";
    } else {
        $response["success"] = false;
        $response["message"] = "Failed to borrow book.";
    }
} else {
    $response["success"] = false;
    $response["message"] = "Title parameter is required.";
}

// Close connection
$conn->close();

// Return JSON response
header('Content-Type: application/json');
echo json_encode($response);
?>
