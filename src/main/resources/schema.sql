DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS tweet;
DROP TABLE IF EXISTS followers;

CREATE TABLE person (id IDENTITY, name VARCHAR);
ALTER TABLE person ADD CONSTRAINT NAME_UNIQUE UNIQUE(name);

CREATE TABLE tweet (id IDENTITY, person_id NUMBER REFERENCES person(id), content VARCHAR);

CREATE TABLE followers (id IDENTITY, person_id NUMBER REFERENCES person (id), follower_person_id NUMBER REFERENCES person (id));
ALTER TABLE followers ADD CONSTRAINT FOLLOWER_FOLLOWEE_UNIQUE UNIQUE(person_id, follower_person_id);