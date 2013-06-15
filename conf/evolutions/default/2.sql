# Users schema

# --- !Ups

CREATE TABLE Users (
    name varchar(255) NOT NULL,
    password_digest varchar(255) NOT NULL,
    PRIMARY KEY (name)
);


# --- !Downs

DROP TABLE Users;