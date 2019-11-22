<?php

include_once 'connect.php';

$data = array();

$stmt = $con->prepare("SELECT * FROM DATA");
$stmt->execute();
$result = $stmt->get_result();
while ($row = $result->fetch_assoc()) {
    $data[] = $row;
}

echo json_encode($data);
