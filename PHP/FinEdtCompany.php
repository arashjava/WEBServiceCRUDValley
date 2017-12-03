<?php

/* get data from android application  */
$company_name= $_POST["companyName"];
$account_no= $_POST["accountNo"];
$company_code= (int)$_POST["companyCode"];


/* connection settings */
$user = "root";
$password = "your MySQL Password";
$host = "localhost";
$db_name = "MyCosts";   

$con = mysqli_connect($host, $user, $password, $db_name);

/* Sql command to insert data into the table  */
$sql = "update Companies set company_name= '".$company_name."', account_no= '".$account_no."' where company_code= '".$company_code."'";



if(mysqli_query($con, $sql))
{
	echo " Company  $company_name is sucsessfully edited...";
}
else
{
	echo "Error while edition...";
}

mysqli_close($con);



