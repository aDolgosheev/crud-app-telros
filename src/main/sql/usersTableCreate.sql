create schema if not exists postgres;

create table if not exists postgres.users
(
    id            serial primary key,
    name          varchar(255) not null,
    surname       varchar(255) not null,
    patronymic    varchar(255),
    date_of_birth varchar(255),
    email        varchar(255) not null unique ,
    phone_number  varchar(20) not null unique
);

insert into postgres.users (name, surname, patronymic, date_of_birth, email, phone_number)
values ('Aleksandr', 'Dolgosheev', 'Olegovich', '30.11.1999', 'al.dolgosheev@gmail.com', '+79045565643'),
('Andrey', 'Kondratyev', 'Petrovich', '08.04.1989', 'andrey@gmail.com', '+79216549835'),
('Ivan', 'Petrov', 'Andreevich', '30.07.2005', 'ivan@amazon.com', '+79113569857'),
('Ekaterina', 'Dolgaya', 'Petrovna', '12.10.1985', 'katya@mail.com', '+79996458794'),
('Anastasiya', 'Yasnaya', 'Aleksandrovna', '15.12.1961', 'nastya@ya.ru', '+79326135973'),
('Elena', 'Letuchaya', 'Sergeevna', '03.07.1996', 'lenusik@yandex.ru', '+79526694312'),
('Petr', 'Osipov', 'Ivanovich', '19.09.1981', 'petr@gmail.com', '+79213679643'),
('Oleg', 'Arbuzov', 'Olegovich', '16.11.1949', 'oleg49@mail.ru', '+79114763591');