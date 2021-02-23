/* CREATING CLASS FOR PERSON(STUDENT, TEACHER) */
drop table if exists subject_group;
drop table if exists grade;
drop table if exists employment;
drop table if exists payment_history;
drop table if exists payment;
drop table if exists subject;
drop table if exists person;
drop table if exists student_groups;
drop table if exists specialization;
drop table if exists teaching_mode;



create table specialization (
                                id int primary key auto_increment,
                                name varchar(60) not null,
                                code varchar(4) not null
);

create table teaching_mode (
                               id int primary key auto_increment,
                               mode varchar(20) not null
);

create table student_groups (
                                id int primary key auto_increment,
                                code varchar(4) not null,
                                year int not null,
                                spec_id int,
                                mode_id int,
                                KEY `spec_id` (`spec_id`),
                                KEY `mode_id` (`mode_id`),
                                CONSTRAINT `group_spec` FOREIGN KEY (`spec_id`) REFERENCES `specialization` (`id`),
                                CONSTRAINT `group_mode` FOREIGN KEY (`mode_id`) REFERENCES `teaching_mode` (`id`)
);

create table person (
                        id int primary key auto_increment,
                        name varchar(60) not null,
                        surname varchar(60) not null,
                        email varchar(60),
                        telephone varchar(12),
                        salary double,
                        alias varchar(5),
                        group_id int,
                        KEY `group_id` (`group_id`),
                        CONSTRAINT `person_group` FOREIGN KEY (`group_id`) REFERENCES `student_groups` (`id`) ON DELETE SET NULL
);

create table employment (
                        id int primary key auto_increment,
                        date_From date,
                        date_To date,
                        teacher_id int not null,
                        KEY `teacher_id` (`teacher_id`),
                        CONSTRAINT `employment_teacher` FOREIGN KEY (`teacher_id`) REFERENCES `person` (`id`)
);

create table subject (
                        id int primary key auto_increment,
                        name varchar(60) not null,
                        alias varchar(60) not null,
                        semester int not null,
                        teacher_id int,
                        group_id int,
                        KEY `group_id` (`group_id`),
                        CONSTRAINT `subject_group` FOREIGN KEY (`group_id`) REFERENCES `student_groups` (`id`)
);

create table grade (
                        grade_id int primary key auto_increment,
                        grade int not null,
                        date date,
                        student_id int not null,
                        subject_id int not null,
                        KEY `student_id` (`student_id`),
                        KEY `subject_id` (`subject_id`),
                        CONSTRAINT `grade_student` FOREIGN KEY (`student_id`) REFERENCES `person` (`id`),
                        CONSTRAINT `grade_subject` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`)
);

create table payment (
                        id int primary key auto_increment,
                        current_Tuition double,
                        additional_Fees double,
                        deadline date,
                        active bit
);

create table payment_history (
                        id int primary key auto_increment,
                        date_Of_Payment date,
                        student_id int not null,
                        payment_id int not null,
                        KEY `student_id` (`student_id`),
                        KEY `payment_id` (`payment_id`),
                        CONSTRAINT `payment_student` FOREIGN KEY (`student_id`) REFERENCES `person` (`id`),
                        CONSTRAINT `payment_history` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`)
);

create table subject_group (
                               subject_id int,
                               group_id int,
                               KEY `subject_id` (`subject_id`),
                               KEY `group_id` (`group_id`),
                               CONSTRAINT `subject_groups` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`),
                               CONSTRAINT `group_subject` FOREIGN KEY (`group_id`) REFERENCES `student_groups` (`id`)
);




