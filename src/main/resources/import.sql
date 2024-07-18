-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

INSERT INTO customer (id, name, surname) values (1, 'Bruno', 'Gama');
INSERT INTO customer (id, name, surname) values (2, 'Alice', 'Smith');
INSERT INTO customer (id, name, surname) values (3, 'John', 'Doe');
INSERT INTO customer (id, name, surname) values (4, 'Emma', 'Johnson');
INSERT INTO customer (id, name, surname) values (5, 'Liam', 'Williams');
INSERT INTO customer (id, name, surname) values (6, 'Olivia', 'Brown');
INSERT INTO customer (id, name, surname) values (7, 'Noah', 'Jones');
INSERT INTO customer (id, name, surname) values (8, 'Ava', 'Garcia');
INSERT INTO customer (id, name, surname) values (9, 'William', 'Martinez');
INSERT INTO customer (id, name, surname) values (10, 'Sophia', 'Rodriguez');
INSERT INTO customer (id, name, surname) values (11, 'James', 'Martinez');
INSERT INTO customer (id, name, surname) values (12, 'Isabella', 'Hernandez');
INSERT INTO customer (id, name, surname) values (13, 'Benjamin', 'Lopez');
INSERT INTO customer (id, name, surname) values (14, 'Mia', 'Gonzalez');
INSERT INTO customer (id, name, surname) values (15, 'Lucas', 'Wilson');
INSERT INTO customer (id, name, surname) values (16, 'Amelia', 'Anderson');
INSERT INTO customer (id, name, surname) values (17, 'Henry', 'Thomas');
INSERT INTO customer (id, name, surname) values (18, 'Evelyn', 'Taylor');
INSERT INTO customer (id, name, surname) values (19, 'Alexander', 'Moore');
INSERT INTO customer (id, name, surname) values (20, 'Harper', 'Jackson');
ALTER SEQUENCE customer_seq RESTART WITH 21;