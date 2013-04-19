# Users schema

# --- !Ups

CREATE TABLE Projects (
    id serial,
    name varchar(255) NOT NULL,
    summary text NOT NULL,
    description text not null,
    history text not null,
    PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE Projects;