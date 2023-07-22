-- auto-generated definition
create table student
(
    id           BIGINT     auto_increment           not null,
    student_name CHARACTER VARYING(50) not null,
    age          INTEGER               not null,
    sex          CHARACTER             not null,
    email        CHARACTER VARYING(100),
    constraint STUDENT_PK
        primary key (id)
);