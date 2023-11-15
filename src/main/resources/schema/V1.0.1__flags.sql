create table if not exists flag
(
  id int primary key,
  a_log boolean default false
);

insert into flag (id) values (1);
