create table medicos(
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(256) not null unique,
    dni varchar (10) not null unique,
    specialty varchar(100) not null,
    street varchar(100)not null,
    city varchar(100)not null,
    primary key(id)
);