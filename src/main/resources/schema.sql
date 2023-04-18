create sequence hibernate_sequence;
create table drone (
    id bigint not null primary key,
    serial_number varchar(128),
    model varchar(20),
    weight double,
    capacity double,
    state varchar(20)
);

create table medication (
    id bigint not null primary key,
    name varchar(128),
    weight double,
    code varchar(128),
    image longblob,
    drone bigint references drone (id)
);