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
values (1, 'admin1', '123456');

insert into user (id, username, password)
values (2, 'admin2', '123456');

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

insert into employee (id, emp_id, name, surname, phone_number, address, title)
values (1, '2021001001', 'John Doe', 'Doe', '+1(613)463-7761', '169 Lees Ave', 'Junior Full Stack Developer');