<?php

    define('HOST','localhost');
    define('USER','Username'); //Username
    define('PASS','Password'); //Password
    define('DB','Database name'); //Database name

    $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect.');

    $name = $_POST['name'];
    $email = $_POST['email'];
    $message = $_POST['message'];

    $sql = "INSERT INTO TABLE_NAME (name, email, message) values ('$name','$email','$message')";

    if(mysqli_query($con,$sql)){
        echo 'success';
    }
    else{
        echo 'failure';
    }
    mysqli_close($con);

    ?>

