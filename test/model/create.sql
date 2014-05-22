create database testDB;
use testDB;

CREATE USER testUser IDENTIFIED BY 'password'; 

grant usage on *.* to testUser@localhost identified by 'password'; 
grant all privileges on testDB.* to testUser@localhost;

CREATE TABLE Article (
    id INT NOT NULL AUTO_INCREMENT, 
    user VARCHAR(30) NOT NULL,
    title VARCHAR(200) NOT NULL, 
    datum DATE NOT NULL, 
	text TEXT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Comment (
    id INT NOT NULL AUTO_INCREMENT, 
    user VARCHAR(30) NOT NULL,
    email VARCHAR(30), 
    webpage VARCHAR(100) NOT NULL, 
    datum DATE NOT NULL, 
    comments VARCHAR(400) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO Comment values 
	(default, 'lars',  'lars@gmail.com',  'www.lars.com',  '2009-09-14 08:44:00', 'Lars comment.'  ),
	(default, 'keanu', 'keanu@gmail.com', 'www.keanu.com', '2010-04-03 13:33:11', 'Keanus comment.'),
	(default, 'reavs', 'reavs@gmail.com', 'www.reavs.com', '2011-09-17 11:23:22', 'Reavs comment.' ),
	(default, 'crow',  'crow@gmail.com',  'www.crow.com',  '2012-05-23 10:56:01', 'Crows comment.' ); 
