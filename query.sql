create database data;


use data;


create table worker (
id_worker int AUTO_INCREMENT primary key,
surname varchar(20) not null,
name varchar(20) not null,

);

create table user (
login varchar(20) not null primary key,
password varchar(20) not null,
status varchar(20) not null,
id_worker int,
foreign key (id_worker) references worker (id_worker)
);

create table properties (
id_property int auto_increment not null primary key,
property varchar(30) not null,
base_value float not null,
com_index float
);

create table product (
name varchar(20) not null unique primary key,
id_property int not null,
this_value float,
price float,
quantity int,
rel_index float,
foreign key (id_property) references properties (id_property)
);


