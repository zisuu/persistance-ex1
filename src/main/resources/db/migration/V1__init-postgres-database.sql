
    drop table if exists employee cascade ;

    create table employee (
        id integer not null,
        salary bigint,
        name varchar(100),
        primary key (id)
    );
