create role db_owner INHERIT;
create role db_reader INHERIT;
create role db_writer INHERIT;

grant db_owner to @flyway.user@;

create database library owner db_owner;
