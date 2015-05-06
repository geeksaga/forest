create table users (
     id bigint generated by default as identity,
     name varchar(500),
     email varchar(200),
     userManagerId bigint,
     regDttm timestamp,
     PRIMARY KEY (id)
);

create table users_managers (
     id bigint generated by default as identity primary key,
     name varchar(500),
     regDttm timestamp
);

create table AuditableUser (
    id bigint generated by default as identity (start with 1),
    createdDate timestamp,
    lastModifiedDate timestamp,
    username varchar(255),
    createdBy_id bigint,
    lastModifiedBy_id bigint,
    primary key (id)
);

CREATE SCHEMA PUBLIC AUTHORIZATION DBA
--CREATE MEMORY TABLE SAMPLE_TABLE(ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,STR_COL VARCHAR(256),NUM_COL INTEGER)
--ALTER TABLE SAMPLE_TABLE ALTER COLUMN ID RESTART WITH 26
CREATE USER SA PASSWORD ""
GRANT DBA TO SA
SET WRITE_DELAY 20
SET SCHEMA PUBLIC
