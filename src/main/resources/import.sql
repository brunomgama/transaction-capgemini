-- This file allow to write SQL commands that will be emitted in test and dev.

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

INSERT INTO account (id, customerId, balance) values (1, 4, 100);
INSERT INTO account (id, customerId, balance) values (2, 5, 50);
INSERT INTO account (id, customerId, balance) values (3, 6, 200);

INSERT INTO transaction (id, accountId, isDebit, amount) values (1, 3, true, 20.0);
INSERT INTO transaction (id, accountId, isDebit, amount) values (2, 3, true, 10.5);
INSERT INTO transaction (id, accountId, isDebit, amount) values (3, 3, true, 5.25);

ALTER SEQUENCE customer_seq RESTART WITH 21;
ALTER SEQUENCE account_seq RESTART WITH 4;
ALTER SEQUENCE transaction_seq RESTART WITH 4;