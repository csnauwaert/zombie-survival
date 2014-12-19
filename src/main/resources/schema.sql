create table IF NOT EXISTS users (
  username varchar(256),
  password varchar(256),
  enabled boolean
);

create table IF NOT EXISTS authorities (
  username varchar(256),
  authority varchar(256)
);