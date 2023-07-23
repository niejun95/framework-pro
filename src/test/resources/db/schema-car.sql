-- auto-generated definition
create table car
(
    id          bigint auto_increment comment '主键'
        primary key,
    car_name    varchar(20) null comment '汽车名',
    brand       varchar(20) null comment '汽车品牌名',
    guide_price decimal     null comment '指导价',
    car_type    varchar(3)  null comment '汽车类型'
);

