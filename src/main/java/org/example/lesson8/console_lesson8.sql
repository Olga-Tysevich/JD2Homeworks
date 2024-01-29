CREATE DATABASE lesson8;

USE lesson8;

CREATE TABLE lesson8.houses
(
    id         int primary key auto_increment,
    size       DECIMAL,
    color      VARCHAR(50),
    room_count INT
);

CREATE TABLE lesson8.DOORS
(
    id   int primary key auto_increment,
    size DECIMAL(32, 2),
    type varchar(50)
);