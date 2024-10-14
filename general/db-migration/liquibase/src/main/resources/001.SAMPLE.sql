--liquibase formatted sql

--changeset salerno:3

create table hr1.table_name2
(
	column_1 int
		constraint table_name2_pk
			primary key,
	column_2 varchar not null
);
--rollback drop table hr1.table_name2;

