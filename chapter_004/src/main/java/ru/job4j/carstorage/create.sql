-- ****************** CREATE DATABASE ****************** --
CREATE DATABASE CarStorage
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

\c carstorage

-- ****************** CREATE STRUCTURE ****************** --
-- DROP TABLE Car;
-- DROP TABLE Transmission;
-- DROP TABLE Engine;
-- DROP TABLE CARCASS;

-- CARCASS TABLE
CREATE TABLE Carcass (
	id SERIAL NOT NULL,
	style varchar(200),
	CONSTRAINT carcass_pkey PRIMARY KEY (id)
);

-- CAR ENGINE TABLE
CREATE TABLE Engine (
	id SERIAL NOT NULL,
	type varchar(200),
	CONSTRAINT engine_pkey PRIMARY KEY (id)
);

-- CAR Transmission TABLE
CREATE TABLE Transmission (
	id SERIAL NOT NULL,
	type varchar(200),
	CONSTRAINT transmission_pkey PRIMARY KEY (id)
);


-- CAR TABLE
CREATE TABLE Car (
	id SERIAL NOT NULL,
	model varchar(200) NOT NULL,
	carcass_id integer REFERENCES Carcass (id) NOT NULL,
	engine_id integer REFERENCES Engine (id) NOT NULL,
	transmission_id integer REFERENCES Transmission (id) NOT NULL,
	CONSTRAINT car_pkey PRIMARY KEY (id)
);



-- ****************** CREATE DATA ****************** --
DELETE FROM Car;
DELETE FROM Transmission;
DELETE FROM Engine;
DELETE FROM Carcass;

INSERT INTO Carcass (id, style) VALUES
    (1, 'Minivan'),
	(2, 'Sedan'),
    (3, 'Coupe'),
    (4, 'Hardtop'),
	(5, '1'),
    (6, '11'),
    (7, '111'),
	(8, '1111'),
    (9, '2'),
    (10, '22'),
	(11, '222'),
    (12, '2222'),
    (13, '22222'),
	(14, '222222'),
    (15, '3'),
    (16, '33');
	
INSERT INTO Engine (id, type) VALUES
    (1, 'Two-Stroke'),
	(2, 'Four-Stroke'),
    (3, 'Six-Stroke');

INSERT INTO Transmission (id, type) VALUES
    (1, 'Manual'),
	(2, 'Automatic');

INSERT INTO Car (id, model, carcass_id, engine_id, transmission_id) VALUES
    (1, 'Honda Life', 1, 1, 1),
	(2, 'Toyota Aygo', 2, 2, 2),
    (3, 'Honda Civic', 3, 2, 1),
    (4, 'Honda Life', 1, 1, 1),
	(5, 'Toyota Aygo', 2, 2, 2),
    (6, 'Honda Civic', 3, 2, 1),
    (7, 'Honda Life', 1, 1, 1),
	(8, 'Toyota Aygo', 2, 2, 2),
    (9, 'Honda Civic', 3, 2, 1),
    (10, 'Honda Life', 1, 1, 1),
	(11, 'Toyota Aygo', 2, 2, 2),
    (12, 'Honda Civic', 3, 2, 1),
    (13, 'Honda Life', 1, 1, 1),
	(14, 'Toyota Aygo', 2, 2, 2),
    (15, 'Honda Civic', 3, 2, 1),
    (16, 'Honda Life', 1, 1, 1),
	(17, 'Toyota Aygo', 2, 2, 2),
    (18, 'Honda Civic', 3, 2, 1),
    (19, 'Honda Life', 1, 1, 1),
	(20, 'Toyota Aygo', 2, 2, 2),
    (21, 'Honda Civic', 3, 2, 1),
    (22, 'Honda Life', 1, 1, 1),
	(23, 'Toyota Aygo', 2, 2, 2),
    (24, 'Honda Civic', 3, 2, 1),
    (25, 'Honda Life', 1, 1, 1),
	(26, 'Toyota Aygo', 2, 2, 2),
    (27, 'Honda Civic', 3, 2, 1),
    (28, 'Honda Life', 1, 1, 1),
	(29, 'Toyota Aygo', 2, 2, 2),
    (30, 'Honda Civic', 3, 2, 1),
    (31, 'Honda Civic', 3, 2, 1),
    (32, 'Honda Life', 1, 1, 1),
	(33, 'Toyota Aygo', 2, 2, 2),
    (34, 'Honda Civic', 3, 2, 1),
    (35, 'Honda Life', 1, 1, 1),
	(36, 'Toyota Aygo', 2, 2, 2),
    (37, 'Honda Civic', 3, 2, 1),
    (38, 'Honda Life', 1, 1, 1),
	(39, 'Toyota Aygo', 2, 2, 2),
	(40, 'Toyota Aygo', 2, 2, 2),
    (41, 'Honda Civic', 3, 2, 1),
    (42, 'Honda Life', 1, 1, 1),
	(43, 'Toyota Aygo', 2, 2, 2),
    (44, 'Honda Civic', 3, 2, 1),
    (45, 'Honda Life', 1, 1, 1),
	(46, 'Toyota Aygo', 2, 2, 2),
    (47, 'Honda Civic', 3, 2, 1),
    (48, 'Honda Life', 1, 1, 1),
	(49, 'Toyota Aygo', 2, 2, 2),
    (50, 'Honda Civic', 3, 2, 1),
	(51, 'Toyota Aygo', 2, 2, 2);