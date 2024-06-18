drop database if exists resume_builder_test;
create database resume_builder_test;
use resume_builder_test;

-- create tables and relationships
create table role(
    role_id int primary key auto_increment,
    `name` varchar(50) not null
);

create table user(
    user_id int primary key auto_increment,
    email varchar(320) unique not null,
    password_hash varchar(2048) not null,
    username varchar(50) not null,
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

delimiter //
create procedure set_known_good_state()
begin
    -- delete bridge tables
    delete from user_skill;
    delete from user_role;

    -- delete singular dependencies
    delete from user_info;
    delete from summary;
    delete from education;
    delete from experience;

    -- delete root tables
    delete from role;
    delete from skill;
    delete from user;

    -- reset auto increment primary key to 1
    -- bridge tables are excluded
    alter table user_info auto_increment = 1;
    alter table summary auto_increment = 1;
    alter table education auto_increment = 1;
    alter table experience auto_increment = 1;
    alter table role auto_increment = 1;
    alter table skill auto_increment = 1;
    alter table user auto_increment = 1;

    insert into role (`name`)
    values
        ('USER'),
        ('ADMIN');

    insert into user(email, password_hash, username)
    values
        ('BobMail@gmail.com', 'hashpassword', 'bobMail'),
        ('MaryMail@gmail.com', 'hashpassword', 'maryMail'),
        ('AdminMail@gmail.com', 'hashpassword', 'adminMail');

    insert into user_role(user_id, role_id)
    values
        (1, 1),
        (2, 1),
        (3, 1),
        (3, 2);

    insert into user_info(full_name, email, phone, website, `location`, user_id)
    values
        ("BobF BobL", "BobMail@gmail.com", "123 456 7890", "bobwebsite.com", "New York", 1),
        ("MaryF MaryL", "MaryMail@gmail.com", "123 456 7890", "marywebsite.com", "Mary", 2),
        ("AdminF AdminL", "AdminMail@gmail.com", "123 456 7890", "adminwebsite.com", "Admin", 3);

    insert into summary(`description`, display_name, user_id)
    values
        ("Hi, I'm Bob, a upcoming software developer. I recently got a Bachelors in Computer Science and I'm looking for opportunities for me to propel my career.", "Bob CS Summary", 1),
        ("Hi, I'm Bob, this is my general summary description.", "Bob General Summary", 1),
        ("Hi, I'm Mary. This is my general summary description.", "Mary General Summary", 2);

    insert into education(university_name, degree, major, gpa, `start_date`, `end_date`, `description`, user_id)
    values
        ("Hunter College", "Bachelor of Arts", "Computer Science", 3.90, '2020-08-01', null, null, 1),
        ("Harvard University", "Bachelor of Science", "Business Communications", 3.0, '2015-08-01', '2020-05-01', null, 2),
        ("Cornell University", "Bachelor of Science", "Economics", 4.0, '2022-08-01', null, null, 3);

    insert into experience(company_name, `role`, display_name, `start_date`, `end_date`, `description`, user_id)
    values
        ("Google", "Software Engineer Intern", "Google Intern", "2020-12-01", "2021-02-01", null, 1),
        ("Google", "Full Stack Engineer", "Google Full Stack", "2024-06-01", null, null, 1),
        ("Amazon", "Software Engineer", "Amazon Cloud Engineer", "2020-06-01", "2022-08-01", null, 2),
        ("Discord", "Project Manager", "Discord PM", "2023-02-01", null, null, 2);

    insert into skill(`name`)
    values
        ("Github"), ("React"), ("Angular"), ("Next"), ("VS Code"),
        ("Visual Studio"), ("Linux"), ("C++"), ("Python"), ("Java");

    insert into user_skill(user_id, skill_id)
    values
        (1, 1), (1, 2), (1, 4), (1, 5), (1, 6), (1, 10),
        (2, 1), (2, 3), (2, 5), (2, 6), (2, 7), (2, 8), (2, 9);

end //
delimiter ;