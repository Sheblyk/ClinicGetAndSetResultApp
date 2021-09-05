create table analysis (
    id_analysis bigint(19) auto_increment not null,
    description varchar(255) not null,
    name varchar(255) not null,
    price double precision not null,
    id_category bigint(19) not null,
    primary key (id_analysis))
    engine=InnoDB;

create table category (
    id_category bigint(19) auto_increment not null,
    category_name varchar(255) not null,
    primary key (id_category))
    engine=InnoDB;

create table order_ (
    id_order bigint(19) auto_increment not null,
    price_for_order double precision not null,
    time datetime(6) not null,
    id_user bigint(19) not null,
    primary key (id_order))
    engine=InnoDB;

create table order_has_analysis (
    fk_analysis bigint(19) auto_increment not null,
    fk_order bigint(19) not null,
    result double precision,
    primary key (fk_analysis, fk_order))
    engine=InnoDB;

create table role (
    id_role bigint(19) auto_increment not null,
    role_name varchar(255) not null,
    primary key (id_role))
    engine=InnoDB;

create table user (
    id_user bigint(19) auto_increment not null,
    age integer not null,
    name varchar(255) not null,
    surname varchar(255) not null,
    id_role bigint not null,
    primary key (id_user))
    engine=InnoDB;

alter table analysis add constraint fk_category
    foreign key (id_category) references category (id_category);

alter table order_ add constraint fk_user
    foreign key (id_user) references user (id_user);

alter table order_has_analysis add constraint FK4a4o61b74qsdhg9twqohqtq0h
    foreign key (fk_analysis) references analysis (id_analysis);

alter table order_has_analysis add constraint FK8s6emed1h2t69mxqb0f6ki2lb
    foreign key (fk_order) references order_ (id_order);

alter table user add constraint fk_role
    foreign key (id_role) references role (id_role);

insert into role(role_name) values ('ADMIN');
insert into role(role_name) values ('USER');

insert into user(age, name, surname, id_role)
values (12, 'cat', 'cat', 1);

insert into category(category_name) values ('blood');
insert into category(category_name) values ('health');

insert into analysis(description, name, price, id_category
) values ('check blood results', 'iMg', 12.2, 1);
insert into analysis(description, name, price, id_category
) values ('check blood results 2.0', 'idgg', 34.2, 1);

insert into order_(price_for_order, time, id_user)
values (40, CURRENT_TIMESTAMP, 1);

insert into order_has_analysis(fk_analysis, fk_order, result)
values (1, 1, 0.0);