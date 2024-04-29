drop table if exists teacher_student;
drop table if exists teacher;
drop table if exists student;
drop table if exists school;

create table if not exists school (
    school_id int primary key AUTO_INCREMENT,
    school_name varchar(255) not null,
    city varchar(60) not null,
    principal_name varchar(60) not null
);

create table if not exists student (
    student_id int primary key AUTO_INCREMENT,
    school_id int not null,
    student_first_name varchar(60) not null,
    student_last_name varchar(60) not null,
    student_grade int,
    merits int,
    demerits int,
    foreign key (school_id) references school(school_id)
);

create table if not exists teacher (
    teacher_id int primary key AUTO_INCREMENT,
    school_id int not null,
    teacher_first_name varchar(40) not null,
    teacher_last_name varchar(40) not null,
    foreign key (school_id) references school(school_id)
);

create table if not exists teacher_student (
    teacher_id int,
    student_id int,
    primary key (teacher_id, student_id),
    foreign key (teacher_id) references teacher(teacher_id),
    foreign key (student_id) references student(student_id)
);