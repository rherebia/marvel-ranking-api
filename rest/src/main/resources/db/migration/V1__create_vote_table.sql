CREATE SEQUENCE VOTES_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE votes
(
    id                bigint not null,
    character_id      bigint not null,
    vote_kind         varchar(255),
    primary key (id)
);