<?php

/* get data from android application  */
$company_code= (int)$_POST["company_code"];

/* connection settings */
$user = "root";
$password = "your MySQL Password";
$host = "localhost";
$db_name = "MyCosts";   

$con = mysqli_connect($host, $user, $password, $db_name);

/* Sql command to insert data into the table  */
$sql = "delete from Companies where company_code= '".$company_code."'";


if(mysqli_query($con, $sql))
{
	echo " $company_code is sucsessfully deleted...";
}
else
{
	echo "Error while insertion...";
}

mysqli_close($con);



