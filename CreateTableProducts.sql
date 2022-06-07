BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products
(
    id    bigserial PRIMARY KEY,
    title VARCHAR(255),
    cost int
);
INSERT INTO products (title, cost)
VALUES ('apple', 100),
       ('orange', 200),
       ('banana', 50),
       ('papaya', 500),
       ('kiwi', 250);



COMMIT;