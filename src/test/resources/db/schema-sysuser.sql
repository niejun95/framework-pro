-- auto-generated definition
create table sys_user
(
    id          bigint auto_increment
        primary key,
    username    varchar(20) not null,
    realname    varchar(20) not null,
    create_time datetime    not null,
    update_time datetime    null
);

