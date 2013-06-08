# Users schema

# --- !Ups

CREATE TABLE Users (
    name varchar(255) NOT NULL,
    password_digest varchar(255) NOT NULL,
    PRIMARY KEY (name)
);

insert into Users values ('sanchous-i','5d23a82bed6c309bece1a9ae9a063879');

# --- !Downs

DROP TABLE Users;