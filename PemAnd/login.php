<?php

include_once 'connect.php';
$response = array();

$username = $_POST['username'];
$password = $_POST['password'];

$stmt = $con->prepare("SELECT ID,USERNAME FROM USERS WHERE USERNAME = '" . $username . "' AND PASSWORD = '" . $password . "'");
$stmt->execute();
$stmt->store_result();
if ($stmt->num_rows > 0) {
    $stmt->bind_result($id, $username);
    $stmt->fetch();
    $user = array(
        'id' => $id,
        'username' => $username
    );
    $response['error'] = false;
    $response['message'] = "Login Succesful";
    $response['user'] = $user;
} else {
    $response['error'] = true;
    $response['message'] = "Login Failed";
}

echo json_encode($response);
