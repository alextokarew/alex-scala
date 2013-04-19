# Users schema

# --- !Ups

CREATE TABLE Posts (
    id serial,
    title varchar(255) NOT NULL,
    content text NOT NULL,
    created_at timestamp NOT NULL,
    PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE Posts;