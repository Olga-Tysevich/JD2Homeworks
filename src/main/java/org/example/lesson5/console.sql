CREATE DATABASE PEOPLE;


USE PEOPLE;


CREATE TABLE PERSON(
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       age INT,
                       salary DECIMAL,
                       passport CHAR(10),
                       address VARCHAR(200),
                       dateOfBirthday DATE,
                       dateTimeCreate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
                       timeToLunch TIME,
                       letter TEXT
);


INSERT INTO PERSON(age, salary, passport, address, dateOfBirthday,  timeToLunch, letter)
VALUES (10, 0.0, 'MP12345678', 'г. Минск, ул. Кальварийская, д. 1, кв. 100', '2014-01-11', '11:00:00',
        'Не существует скорости, на которой вы ощутите невесомость.'),
       (22, 850.0, 'MP23145687', 'г. Минск, ул. Ольшевского, д. 13, кв. 85', '2001-12-15',  '13:30:00',
        'В общем, вы невесомы, если на вас не действует электромагнитная сила.'),
       (34, 890.0, 'MP87546395', 'г. Минск, ул. Васнецова, д. 13, кв. 63', '1989-04-07', '14:00:00',
        'В каком-то смысле это переворачивает понятие «ускорения», потому что можно утверждать, что человек «невозмутим».'),
       (18, 675.0, 'MP63546936', 'г. Минск, ул. Воронянского, д. 25, кв. 187', '2005-11-28', '13:45:00',
        'Если вы падаете свободно, вы чувствуете невесомость.'),
       (25, 1200.0, 'MP26983652', 'г. Минск, ул. Революционная, д. 9, кв. 1', '1999-01-05', '12:20:00',
        'Невесомость — это не то, что вы испытываете, когда падаете в одиночку.');

SELECT * FROM PERSON
WHERE age > 21
ORDER BY dateTimeCreate;