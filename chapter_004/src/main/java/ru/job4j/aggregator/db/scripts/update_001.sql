create table post (
	id serial not null,
	name varchar(200) not null,
	description text,
	link text unique not null,
	created timestamp not null,
	constraint id_pkey primary key (id)
);
