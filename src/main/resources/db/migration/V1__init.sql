create table analysis (
    id_analysis binary(255) not null,
    description varchar(255) not null,
    name varchar(255) not null,
    price double precision not null,
    id_category binary(255) not null,
    primary key (id_analysis))
    engine=InnoDB;

create table category (
    id_category binary(255) not null,
    category_name varchar(255) not null,
    primary key (id_category))
    engine=InnoDB;

create table order_ (
    id_order binary(255) not null,
    price_for_order double precision not null,
    time datetime(6) not null,
    id_user binary(255) not null,
    primary key (id_order))
    engine=InnoDB;

create table order_has_analysis (
    fk_analysis binary(255) not null,
    fk_order binary(255) not null,
    primary key (fk_analysis, fk_order))
    engine=InnoDB;

create table role (
    id_role binary(255) not null,
    role_name varchar(255) not null,
    primary key (id_role))
    engine=InnoDB;

create table user (
    id_user binary(255) not null,
    age integer not null,
    name varchar(255) not null,
    surname varchar(255) not null,
    id_role binary(255) not null,
    primary key (id_user))
    engine=InnoDB;

alter table analysis add constraint fk_category
    foreign key (id_category) references category (id_category);

alter table order_ add constraint fk_user
    foreign key (id_user) references user (id_user);

alter table order_has_analysis add constraint fk_order_analysis
    foreign key (fk_order) references analysis (id_analysis);

alter table order_has_analysis add constraint fk_analysis_order
    foreign key (fk_analysis) references order_ (id_order);

alter table user add constraint fk_role
    foreign key (id_role) references role (id_role);