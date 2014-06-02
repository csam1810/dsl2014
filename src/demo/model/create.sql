create database demoDB;
use demoDB;

CREATE USER demoUser IDENTIFIED BY 'password'; 

grant usage on *.* to demoUser@localhost identified by 'password'; 
grant all privileges on demoDB.* to demoUser@localhost;

CREATE TABLE Person (
    id INT NOT NULL AUTO_INCREMENT, 
    lastName VARCHAR(100) NOT NULL,
    firstName VARCHAR(100) NOT NULL, 
    birthday DATE NOT NULL, 
    PRIMARY KEY (id)
);

INSERT INTO Person values 
	(0, 'von Trier', 'Lars',          '1956-04-30'),
	(1, 'Spielberg', 'Steven Allan',  '1946-12-18'),
	(2, 'Allen',     'Woody',         '1935-12-01'),
	(3, 'Abrahams',  'Jeffrey Jacob', '1966-06-27'),
	(4, 'Anderson',  'Wesley Wales',  '1969-05-01'); 
