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
    $sql = "UPDATE books SET available = 1 WHERE title = ?";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("s", $title);

    // Execute query
    if ($stmt->execute()) {
        $response["success"] = true;
        $response["message"] = "Book '$title' returned successfully.";
    } else {
        $response["success"] = false;
        $response["message"] = "Failed to return book.";
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
