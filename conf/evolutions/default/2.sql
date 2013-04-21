# Users schema

# --- !Ups

CREATE TABLE Projects (
    id serial,
    name varchar(255) NOT NULL,
    title varchar(255) NOT NULL,
    summary text NOT NULL,
    description text not null,
    history text not null,
    PRIMARY KEY (id)
);

insert into Projects values (1,'rus-etrain','Rus-Etrain','The rus etrain project summary','The rus etrain project description, "Ris-Etrain":http://rus-etrain.ru/','The rus etrain project history');

# --- !Downs

DROP TABLE Projects;