drop database if exists resume_builder;
create database resume_builder;
use resume_builder;

-- create tables and relationships
create table role(
    role_id int primary key auto_increment,
    `name` varchar(50) not null
);

create table user(
    user_id int primary key auto_increment,
    email varchar(320) unique not null,
    password_hash varchar(2048) not null,
    username varchar(50) unique not null,
    disabled boolean not null default false
);

create table user_role(
    user_id int not null,
    role_id int not null,
    constraint pk_user_role
        primary key (user_id, role_id),
    constraint fk_user_user_id
        foreign key (user_id)
        references user (user_id),
    constraint fk_user_role_id
        foreign key (role_id)
        references role (role_id)
);

create table user_info(
    user_info_id int primary key auto_increment,
    full_name varchar(100) not null,
    email varchar(320) unique not null,
    phone varchar(50) null,
    website varchar(100) null,
    `location` varchar(50) null,
    user_id int not null,
    constraint user_info_user_id
        foreign key (user_id)
        references user (user_id)
);

create table summary(
    summary_id int primary key auto_increment,
    `description` varchar(250) not null,
    display_name varchar(50) not null,
    user_id int not null,
    constraint summary_user_id
        foreign key (user_id)
        references user (user_id)
);

create table education(
    education_id int primary key auto_increment,
    university_name varchar(50) not null,
    degree varchar(50) null,
    major varchar(50) null,
    gpa decimal(3,2) null,
    `start_date` date not null,
    `end_date` date null,
    `description` varchar(250) null,
    user_id int not null,
    constraint description_user_id
        foreign key (user_id)
        references user (user_id)
);

create table experience(
    experience_id int primary key auto_increment,
    company_name varchar(50) not null,
    `role` varchar(50) not null,
    display_name varchar(50) not null,
    `start_date` date not null,
    `end_date` date null,
    `description` varchar(250) null,
    user_id int not null,
    constraint experience_user_id
        foreign key (user_id)
        references user (user_id)
);

create table skill(
    skill_id int primary key auto_increment,
    `name` varchar(50) not null
);

create table user_skill (
    user_id int not null,
    skill_id int not null,
    constraint pk_user_skill
        primary key (user_id, skill_id),
    constraint fk_user_skill_user_id
        foreign key (user_id)
        references user (user_id),
    constraint fk_user_skill_skill_id
        foreign key (skill_id)
        references skill (skill_id)
);

insert into role (`name`)
    values
        ('USER'),
        ('ADMIN');
insert into user (email, password_hash, username) values
("test@gmail.com", "$2a$10$jEux/jBqfREzR9SDuWIKJuW8VgrL5Y2hJQe3fZ0f6zAVtoQJZdY7m", "test");
    
insert into user_role(user_id, role_id)
values
(1,1);