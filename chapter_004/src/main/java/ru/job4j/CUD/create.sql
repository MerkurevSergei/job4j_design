-- ****************** CREATE DATABASE ****************** --
CREATE DATABASE ItemTracker
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

\c itemtracker

-- ****************** CREATE STRUCTURE ****************** --
-- DROP TABLE ETC_Comment;
-- DROP TABLE ETC_Attach;
-- DROP TABLE BIZ_Item;
-- DROP TABLE BIZ_Status;
-- DROP TABLE BIZ_Category;
-- DROP TABLE SCR_User;
-- DROP TABLE SCR_Permission;
-- DROP TABLE SCR_Rule;
-- DROP TABLE SCR_Role;

-- ROLES TABLE
CREATE TABLE SCR_Role (
	id SERIAL NOT NULL, 
	name varchar(200),
	CONSTRAINT role_pkey PRIMARY KEY (id)
);

-- RULES TABLE
CREATE TABLE SCR_Rule (
	id SERIAL NOT NULL, 
	name varchar(200),
	CONSTRAINT rule_pkey PRIMARY KEY (id)
);

-- ROLES RULES TABLE (many-to-many)
CREATE TABLE SCR_Permission (
	role_id integer REFERENCES SCR_Role (id), 
	rule_id integer REFERENCES SCR_Rule (id),
	CONSTRAINT role_rule_pkey PRIMARY KEY (role_id,rule_id)
);

-- USER TABLE
CREATE TABLE SCR_User (
	id SERIAL NOT NULL, 
	name varchar(200),
	role_id integer REFERENCES SCR_Role (id),
	CONSTRAINT user_pkey PRIMARY KEY (id)
);


-- CATEGORY TABLE
CREATE TABLE BIZ_Category (
	id SERIAL NOT NULL, 
	name varchar(200),
	CONSTRAINT category_pkey PRIMARY KEY (id)
);

-- STATES TABLE
CREATE TABLE BIZ_Status (
	id SERIAL NOT NULL, 
	name varchar(200),
	CONSTRAINT status_pkey PRIMARY KEY (id)
);

-- ITEM TABLE
CREATE TABLE BIZ_Item (
	id SERIAL NOT NULL, 
	description text,
	user_id integer REFERENCES SCR_User (id),
	status_id integer REFERENCES BIZ_Status (id),
	category_id integer REFERENCES BIZ_Category (id),
	CONSTRAINT item_pkey PRIMARY KEY (id)
);

-- ITEM ATTACH
CREATE TABLE ETC_Attach (
	id SERIAL NOT NULL, 
	name varchar(200),
	link varchar(250),
	item_id integer REFERENCES BIZ_Item (id),
	CONSTRAINT attach_pkey PRIMARY KEY (id)
);

-- ITEM COMMENT
CREATE TABLE ETC_Comment (
	id SERIAL NOT NULL, 
	content text,
	item_id integer REFERENCES BIZ_Item (id),
	CONSTRAINT comment_pkey PRIMARY KEY (id)
);




-- ****************** CREATE DATA ****************** --
DELETE FROM ETC_Comment;
DELETE FROM ETC_Attach;
DELETE FROM BIZ_Item; 
DELETE FROM BIZ_Status;
DELETE FROM BIZ_Category;
DELETE FROM SCR_User;
DELETE FROM SCR_Permission;
DELETE FROM SCR_Rule;
DELETE FROM SCR_Role;

INSERT INTO SCR_Role (id, name) VALUES
    (1, 'Administrator'),
	(2, 'Programmer'),
    (3, 'Manager'),
    (4, 'Client');
	
INSERT INTO SCR_Rule (id, name) VALUES
    (1, 'Full access'),
	(2, 'Create'),
    (3, 'Edit'),
    (4, 'Read only');

INSERT INTO SCR_Permission (role_id, rule_id) VALUES
    (1, 1),
	(2, 1),
    (3, 2),
	(3, 3),
    (4, 4);

INSERT INTO SCR_User (id, name, role_id) VALUES
    (1, 'Ivanov aka best admin', 1),
	(2, 'Petrov aka super programmer', 2),
    (3, 'Vasia aka horror', 3),
    (4, 'Petya aka help me', 4);
	
INSERT INTO BIZ_Category (id, name) VALUES
    (1, 'Bags'),
    (2, 'Feature'),
    (3, 'New function');
	
INSERT INTO BIZ_Status (id, name) VALUES
    (1, 'Open'),
    (2, 'In work'),
    (3, 'Close');
	
INSERT INTO BIZ_Item (id, description, user_id, status_id, category_id) VALUES
    (1, 'Do not open program', 1, 1, 1),
    (2, 'I need new feature', 4, 2, 3);

INSERT INTO ETC_Attach (id, name, link, item_id) VALUES
    (1, 'special file', 'https://www.pentagon.ru/allpass.txt', 2),
    (2, 'screen', 'C:\temp', 1);

INSERT INTO ETC_Comment (id, content, item_id) VALUES
    (1, 'I want to get all descripition password from file', 2),
    (2, 'This problem sometimes appear in user. I must to decide it', 1);