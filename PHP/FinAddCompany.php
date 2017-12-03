<?php

/* get data from android application  */
/* $company_code= $_POST["company_code "]; */
$company_name= $_POST["companyName"];
$account_no= $_POST["accountNo"];

/* connection settings */
$user = "root";
$password = "your MySQL Password";
$host = "localhost";
$db_name = "MyCosts";   

$con = mysqli_connect($host, $user, $password, $db_name);

/* Sql command to insert data into the table  */
$sql = "INSERT INTO Companies (company_name,account_no)VALUES ('".$company_name."','".$account_no."')";


if(mysqli_query($con, $sql))
{
	echo " $company_name is sucsessfully added...";
}
else
{
	echo "Error while insertion...";
}

mysqli_close($con);



