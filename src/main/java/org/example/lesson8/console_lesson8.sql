CREATE DATABASE IF NOT EXISTS lesson8;


/* Alternative: */

drop database if exists lesson8;
create database lesson8;

/*recreating database every run*/


USE lesson8;



CREATE TABLE houses
(
    id         int primary key auto_increment,
    size       DECIMAL,
    color      VARCHAR(50),
    room_count INT
);

CREATE TABLE doors
(
    id   int primary key auto_increment,
    size DECIMAL(32, 2),
    type varchar(50)
);