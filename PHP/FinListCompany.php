<?php
$user_name="root";
$password= "your MySQL Password";
$host="localhost";
$db_name="MyCosts";
$con= new mysqli($host, $user_name, $password,$db_name);
$sql= "select * from Companies;";
$result = mysqli_query($con, $sql);
$response= array();

while ($row = mysqli_fetch_array($result))
{
	array_push($response, array("companyId"=>$row["company_code"], "companyName"=>$row["company_name"], "accountNo"=>$row["account_no"]));
}
echo json_encode($response);
?>