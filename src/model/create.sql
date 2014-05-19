create database feedback;
use feedback;

CREATE USER sqluser IDENTIFIED BY 'sqluserpw'; 

grant usage on *.* to sqluser@localhost identified by 'sqluserpw'; 
grant all privileges on feedback.* to sqluser@localhost;

CREATE TABLE Comments (
    id INT NOT NULL AUTO_INCREMENT, 
    myuser VARCHAR(30) NOT NULL,
    email VARCHAR(30), 
    webpage VARCHAR(100) NOT NULL, 
    datum DATE NOT NULL, 
    summary VARCHAR(40) NOT NULL,
    comments VARCHAR(400) NOT NULL,
    PRIMARY KEY (id));

INSERT INTO Comments values 
	(default, 'lars', 'myemail@gmail.com','http://www.vogella.com', '2009-09-14 10:33:11', 'Summary','My first comment'); 
