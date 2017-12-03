In this app I used Volley, Json, PHP, Apache server and MySQL to implement CRUD.
I developed it using Android Studio under Linux Mint 18.1, and therefore I used LAMP. But almost everything would be the same
if you develope it under Windows and have WAMP sever up and running.

You got to create a Database and Table to have the CRUDs interact with the table - “Companies”. My database is “MyCosts”. 
The table has three columns: 
Create table Companies
( company_code int NOT NULL auto_increment,
  company_name varchar(40),
  account_no varchar(20),
  primary key (company_code))


I have the PHP files copied under the PHP folder of this project, but you must move them to the “WWW” folder of your server. 
You need to set your username and password of MySQL in the PHP files, too. 

For each action of CRUD, you need to set your own IP address in the java files of the project. 


I found the youtube clips of the following user so useful and you might find them beneficial as well:
PRABEESH R K











 
