create schema dolgosheev;

create table dolgosheev.users
(
    id            serial primary key,
    name          varchar(255),
    surname       varchar(255),
    patronymic    varchar(255),
    date_of_birth varchar(255),
    email         varchar(255),
    phone_number  varchar(20)
);