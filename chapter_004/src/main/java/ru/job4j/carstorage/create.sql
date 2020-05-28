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
	model varchar(200),
	carcass_id integer REFERENCES Carcass (id),
	engine_id integer REFERENCES Engine (id),
	transmission_id integer REFERENCES Transmission (id),
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
    (4, 'Hardtop ');
	
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
    (3, 'Honda Civic', 3, 2, 1);