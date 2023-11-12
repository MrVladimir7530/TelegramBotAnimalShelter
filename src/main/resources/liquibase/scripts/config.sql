--liquibase formatted sql

--changeset IlyaAfanasev:create_table_Subscriber
create table if not exists subscriber(
chat_id bigint primary key,
name varchar(255),
phone_number varchar(255),
user_name varchar(255)
);

--changeset IlyaAfanasev:create_table_Shelter
create table if not exists shelter(
id bigserial primary key,
name varchar(255),
general_info text,
info text,
security_contact text,
safety_precautions text
);

--changeset IlyaAfanasev:create_table_Animal
create table if not exists animal(
id bigserial primary key,
name varchar(255),
breed varchar(255),
shelter_id bigint,
shelter_id bigint not null,
foreign key (shelter_id) references shelter (id)
);

--changeset IlyaAfanasev:create_table_Adopter
create table if not exists adopter(
id bigserial primary key,
adoption_date date not null,
trial_period integer,
subscriber_id bigint not null,
animal_id bigint not null,
foreign key (subscriber_id) references subscriber (chat_id),
foreign key (animal_id) references animal (id)
);


--changeset IlyaAfanasev:create_table_Report
create table if not exists report(
id bigserial primary key,
photo_path text,
creation_date date not null ,
report text,
shelter_id bigint not null,
adopter_id bigint not null,
foreign key (shelter_id) references shelter (id),
foreign key (adopter_id) references adopter (id)
);

--changeset VolkovVladimir:create_table_dog_handler
create table if not exists dog_handler(
    id bigserial primary key,
    name varchar(255),
    phone_number varchar(255),
    info text
);




