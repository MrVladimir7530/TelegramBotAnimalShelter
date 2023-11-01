--liquibase formatted sql
-- changeset IlyaAfanasev:1
create table Subscriber(
id serial primary key,
chat_id bigint,
name varchar(255),
user_name varchar(255)

);

-- changeset IlyaAfanasev:2
create table Animal(
id serial primary key,
name varchar(255),
shelter_id bigint,
user_id bigint

);
-- changeset IlyaAfanasev:3
create table Shelter(
id serial primary key,
name varchar(255),
generalInfo text,
info text,
securityContact text,
safetyPrecautions text

);

