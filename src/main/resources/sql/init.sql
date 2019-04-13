DROP TABLE IF EXISTS learning_log;
DROP TABLE IF EXISTS command_tag;
DROP TABLE IF EXISTS tag;
DROP TABLE IF EXISTS command;
DROP TABLE IF EXISTS chat_user;
DROP TABLE IF EXISTS message;

create table message
(
	id bigserial not null
		constraint message_pk
			primary key,
	text text not null,
	timestamp bigint not null,
	read boolean not null
);


create table chat_user
(
	id bigserial not null
		constraint user_pk
			primary key,
	login varchar(40) not null,
	password varchar(40) not null
);

create unique index chat_user_login_uindex
	on chat_user (login);


create table command
(
	id bigserial not null
		constraint command_pk
			primary key,
	code varchar(24) not null,
	description varchar(100),
	user_command boolean not null,
	reply text not null
);

create unique index command_code_uindex
	on command (code);


create table tag
(
	id bigserial not null
		constraint tag_pk
			primary key,
	name varchar(100) not null,
	idf_value float not null
);

create unique index tag_name_uindex
	on tag (name);

create table command_tag
(
	id bigserial not null
		constraint command_tag_pk
			primary key,
	command_id bigint not null
		constraint command_tag_command_id_fk
			references command
			on update cascade on delete cascade,
	tag_id bigint not null
		constraint command_tag_tag_id_fk
			references tag
			on update cascade on delete cascade
);

create table learning_log
(
	id bigserial not null
		constraint learning_log_pk
			primary key,
	command_code varchar(100) not null,
	text text not null,
	date bigint not null
);


