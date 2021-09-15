create table analysis
(
    id_analysis bigint(19) auto_increment not null,
    description varchar(500)              not null,
    name        varchar(255)              not null,
    price       double precision          not null,
    id_category bigint(19)                not null,
    primary key (id_analysis)
)
    engine = InnoDB;

create table category
(
    id_category   bigint(19) auto_increment not null,
    category_name varchar(255)              not null,
    primary key (id_category)
)
    engine = InnoDB;

create table order_
(
    id_order        bigint(19) auto_increment not null,
    price_for_order double precision          not null,
    time            datetime(6)               not null,
    id_user         bigint(19)                not null,
    finished        bit          default false            not null,
    id_clinic       bigint                    not null,
    primary key (id_order)
)
    engine = InnoDB;

create table order_has_analysis
(
    fk_analysis bigint(19) auto_increment not null,
    fk_order    bigint(19)                not null,
    result      double precision,
    primary key (fk_analysis, fk_order)
)
    engine = InnoDB;

create table role
(
    id_role   bigint(19) auto_increment not null,
    role_name varchar(255)              not null,
    primary key (id_role)
)
    engine = InnoDB;

create table user
(
    id_user   bigint(19) auto_increment not null,
    age       integer                   not null,
    name      varchar(255)              not null,
    surname   varchar(255)              not null,
    id_role   bigint                    not null,
    id_clinic bigint                    not null,
    primary key (id_user)
)
    engine = InnoDB;

create table clinic
(
    id_clinic bigint(19) auto_increment not null,
    address   varchar(255),
    primary key (id_clinic)
) engine = InnoDB;

alter table analysis
    add constraint fk_category
        foreign key (id_category) references category (id_category);

alter table order_
    add constraint fk_user
        foreign key (id_user) references user (id_user);

alter table order_has_analysis
    add constraint fk_order_analysis2
        foreign key (fk_analysis) references analysis (id_analysis);

alter table order_has_analysis
    add constraint fr_order_analysis
        foreign key (fk_order) references order_ (id_order);

alter table user
    add constraint fk_role
        foreign key (id_role) references role (id_role);

alter table order_
    add constraint fk_order_clinic
        foreign key (id_clinic) references clinic (id_clinic);

alter table user
    add constraint fk_user_clinic
    foreign key (id_clinic) references clinic (id_clinic);

insert into role(role_name)
values ('ADMIN');
insert into role(role_name)
values ('USER');

insert into clinic(id_clinic, address)
values (1, 'Pushkinskaya, 45');
insert into clinic(id_clinic, address)
values (2, 'Peremogy, 23A');

insert into user(age, name, surname, id_role, id_clinic)
values (29, 'Kate', 'Linova', 1, 1);
insert into user(age, name, surname, id_role, id_clinic)
values (20, 'Olga', 'Smirnova', 2, 1);

insert into category(category_name)
values ('allergic panel');
insert into category(category_name)
values ('hormonal panel');

insert into analysis(description, name, price, id_category)
values ('Allergen. Extract. Egg protein (f1), IgE antibodies;  Allergen.  Extract.  Dog hair (e5), IgE antibodies;  Allergen.  Extract.  Cow''s milk (f2), IgE antibodies;  Allergen.  Extract.  Cat epidermis (e1), IgE antibodies;  Allergen.  Extract.  Dermatophagoides pteronyssinus mite (d1), IgE antibodies;  Allergen.  Extract.  Wormwood (pollen, w6), IgE antibodies;  Allergen.  Molecule.  Birch (rBet v1), IgE antibodies;  Allergen.  Molecule.  Mixture.  Timothy (rPhl p1 & rPhl p5), IgE antibodies)', 'asthma', 12.2, 1);
insert into analysis(description, name, price, id_category)
values ('Allergen.  Extract.  Artemisia ragweed (w1), IgE antibodies', 'Ambrosia', 34.2, 1);
insert into analysis(description, name, price, id_category)
values ('Total thyroxine (total T4)', 'Thyroxin', 193.2, 2);
insert into analysis(description, name, price, id_category)
values ('Free thyroxine (free T4)', 'Thyroxin', 500.2, 2);

insert into order_(price_for_order, time, id_user, finished, id_clinic)
values (546.6, CURRENT_TIMESTAMP, 1, false, 1);

insert into order_has_analysis(fk_analysis, fk_order, result)
values (1, 1, 0.0);
insert into order_has_analysis(fk_analysis, fk_order, result)
values (2, 1, 0.0);
insert into order_has_analysis(fk_analysis, fk_order, result)
values (4, 1, 0.0);