create database techstock;

create table equipo_computo(
    numeroSerie varchar(50)
    idCentroComputo int,
    marca varchar(30),
    almacenamiento varchar(30),
    memoria varchar(30),
    procesador varchar(30),
    primary key(noSerie)
);

create table usuario(
    nombreUsuario varchar(50),
    contrasena varchar(64)
);


create table centro_computo(
    idCentroComputo int not null auto_increment,
    nombre varchar(20),
    primary key(idCentroComputo)
);

insert into centro_computo(nombre) values ('Centro de computo 1');
insert into centro_computo(nombre) values ('Centro de computo 2');
insert into centro_computo(nombre) values ('Centro de computo 3');
insert into centro_computo(nombre) values ('Centro de computo 4');


create table periferico(
    numeroSerie int not null auto_increment,
    idCentroComputo int,
    marca varchar(30),
    primary key(numeroSerie)
);

create table software(
    idSoftware int not null auto_increment,
    nombre varchar(30),
    version varchar(10),
    primary key(idSoftware)
);

create table software_equipo(
    idSoftware int,
    numeroSerie varchar(50)
);

CREATE USER 'techstock'@'%' IDENTIFIED BY 'Ikmujn19283';
GRANT ALL PRIVILEGES ON techstock.* TO 'techstock'@'%';