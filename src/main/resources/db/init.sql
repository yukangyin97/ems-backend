drop database if exists ems;
create database ems;
drop user if exists 'emsadmin'@'localhost';
flush privileges;
create user 'emsadmin'@'localhost' identified by '123456';
grant all privileges on ems.* to 'emsadmin'@'localhost';
use ems;

create table user
(
    id       int primary key auto_increment,
    username varchar(255),
    password varchar(255)
);

insert into user (id, username, password)
values (1, 'admin1', 'e10adc3949ba59abbe56e057f20f883e');

insert into user (id, username, password)
values (2, 'admin2', 'e10adc3949ba59abbe56e057f20f883e');

create table employee
(
    id           int primary key auto_increment,
    emp_id       varchar(255),
    name         varchar(255),
    surname      varchar(255),
    phone_number varchar(255),
    address      varchar(255),
    title        varchar(255)
);

insert into ems.employee (id, emp_id, name, surname, phone_number, address, title)
values (1, '2021001001', 'John Doe', 'Doe', '+1(613)463-7761', '169 Lees Ave', 'Junior Full Stack Developer');
INSERT INTO ems.employee (id, emp_id, name, surname, phone_number, address, title)
VALUES (2, '2021002001', 'Jacquelyn George', 'George', '+1416-555-0722', '119 King George Hwy', 'Django Developer');
INSERT INTO ems.employee (id, emp_id, name, surname, phone_number, address, title)
VALUES (3, '2021002002', 'Aspen Bernard', 'Bernard', '+1416-555-0852', '8 Park Ct', 'JavaWeb Developer');
INSERT INTO ems.employee (id, emp_id, name, surname, phone_number, address, title)
VALUES (4, '2021002003', 'Nathaly Bishop', 'Bishop', '+1416-555-0302', '2599 rue Parc', 'Junior Full Stack Developer');
INSERT INTO ems.employee (id, emp_id, name, surname, phone_number, address, title)
VALUES (5, '2021002004', 'Rylee Vega', 'Vega', '+1416-555-0176', '103 Bridgeport Rd', 'Backend Engineer');
INSERT INTO ems.employee (id, emp_id, name, surname, phone_number, address, title)
VALUES (6, '2021002005', 'Kash Bentley', 'Bentley', '+1-613-555-0115', '3385 Reserve St', 'Software Engineer');
INSERT INTO ems.employee (id, emp_id, name, surname, phone_number, address, title)
VALUES (7, '2021002006', 'Krista Moss', 'Moss', '+1-613-555-0146', '3131 St Jean Baptiste St', 'PHP Tester');
INSERT INTO ems.employee (id, emp_id, name, surname, phone_number, address, title)
VALUES (8, '2021002007', 'Theodore Singleton', 'Singleton', '+1-613-555-0100', '4882 49th Avenue', 'Python Tester');
INSERT INTO ems.employee (id, emp_id, name, surname, phone_number, address, title)
VALUES (9, '2021002008', 'Cameron Hill', 'Hill', '+1-613-555-0175', '1867 Hastings Street', 'PHP Software Engineer');
INSERT INTO ems.employee (id, emp_id, name, surname, phone_number, address, title)
VALUES (10, '2021002009', 'Jimena Carroll', 'Carroll', '+1-613-555-0167', '2506 Albert Street', 'MERN Stack Developer');
INSERT INTO ems.employee (id, emp_id, name, surname, phone_number, address, title)
VALUES (11, '2021002010', 'Bradley Mayer', 'Mayer', '+1-613-555-0115', '1477 Blanshard', 'Java Tester');
INSERT INTO ems.employee (id, emp_id, name, surname, phone_number, address, title)
VALUES (12, '2021002011', 'Lia Combs', 'Combs', '+1-613-555-0113', '1033 Yonge Street', 'Python Engineer');
INSERT INTO ems.employee (id, emp_id, name, surname, phone_number, address, title)
VALUES (13, '2021002012', 'Kelsie Flowers', 'Flowers', '+1-613-555-0192', '511 Yonge Street', 'Network Technician');
INSERT INTO ems.employee (id, emp_id, name, surname, phone_number, address, title)
VALUES (14, '2021002013', 'Ainsley Clarke', 'Clarke', '+1-613-555-0141', '4644 184th Street', 'Full Stack Developer');
INSERT INTO ems.employee (id, emp_id, name, surname, phone_number, address, title)
VALUES (15, '2021002014', 'Eduardo Morgan', 'Morgan', '+1-613-555-0178', '2994 Brew Creek Rd', 'React Native Intern');
INSERT INTO ems.employee (id, emp_id, name, surname, phone_number, address, title)
VALUES (16, '2021002015', 'Presley Yang', 'Yang', '+1-613-555-0148', '2065 Tolmie St', 'Vue.js Engineer');
INSERT INTO ems.employee (id, emp_id, name, surname, phone_number, address, title)
VALUES (17, '2021002016', 'Yesenia Burns', 'Burns', '+1-613-555-0164', '1578 Wascana Parkway', 'SpringBoot Developer');
INSERT INTO ems.employee (id, emp_id, name, surname, phone_number, address, title)
VALUES (18, '2021002017', 'Waylon Nash', 'Nash', '+1-613-555-0132', '3752 Bellwood Acres Rd',
        'Senior MERN Stack Developer');
INSERT INTO ems.employee (id, emp_id, name, surname, phone_number, address, title)
VALUES (19, '2021002018', 'Kimberly Bradshaw', 'Bradshaw', '+1-613-555-0120', '3023 Lockhart Drive',
        'Web Developement Instructor');
INSERT INTO ems.employee (id, emp_id, name, surname, phone_number, address, title)
VALUES (20, '2021002019', 'Trace Floyd', 'Floyd', '+1-613-555-0164', '1029 Nelson Street', 'Web Developer');
INSERT INTO ems.employee (id, emp_id, name, surname, phone_number, address, title)
VALUES (21, '2021002020', 'Marissa Woods', 'Woods', '+1-613-555-0132', '4752 Garafraxa St',
        'Intermediate React.js Developer');
INSERT INTO ems.employee (id, emp_id, name, surname, phone_number, address, title)
VALUES (22, '2021002021', 'Eden Frederick', 'Frederick', '+1-613-555-0115', '697 Woolwick Drive',
        'System Architecture');
INSERT INTO ems.employee (id, emp_id, name, surname, phone_number, address, title)
VALUES (23, '2021002022', 'Mike Williamson', 'Williamson', '+1-613-555-0117', '1914 Tchesinkut Lake Rd',
        'Backend Engineer');
INSERT INTO ems.employee (id, emp_id, name, surname, phone_number, address, title)
VALUES (24, '2021002023', 'Damari Mccarthy
', 'Mccarthy', '+1-613-555-0158', '3282 99th St', 'Frontend Developer');
INSERT INTO ems.employee (id, emp_id, name, surname, phone_number, address, title)
VALUES (25, '2021002024', 'Maya Middleton', 'Middleton', '+1-613-555-0113', '3533 Barrydowne Road',
        'Frontend Engineer');