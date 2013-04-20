# Users schema

# --- !Ups

CREATE TABLE Posts (
    id serial,
    title varchar(255) NOT NULL,
    content text NOT NULL,
    created_at timestamp NOT NULL,
    PRIMARY KEY (id)
);

insert into Posts values (1,'First post','First post content','2013-03-20 14:15:16');
insert into Posts values (2,'Second post','Second post content','2013-03-22 17:18:19');

# --- !Downs

DROP TABLE Posts;