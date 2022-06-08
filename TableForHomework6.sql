BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products
(
    id    bigserial PRIMARY KEY,
    title VARCHAR(255),
    cost  int
);
INSERT INTO products (title, cost)
VALUES ('apple', 100),
       ('orange', 200),
       ('banana', 50),
       ('papaya', 500),
       ('kiwi', 250);

DROP TABLE IF EXISTS buyers CASCADE;
CREATE TABLE buyers
(
    id   bigserial PRIMARY KEY,
    name VARCHAR(255)
);
INSERT INTO buyers (name)
VALUES ('Anna'),
       ('Georgy'),
       ('Alex'),
       ('Vitya'),
       ('Misha');


DROP TABLE IF EXISTS orders CASCADE;
CREATE TABLE orders
(
    id         bigserial PRIMARY KEY,
    buyer_id   bigint REFERENCES buyers (id),
    product_id bigint REFERENCES products (id),
    cost       int
);

INSERT INTO orders (buyer_id, product_id, cost)
VALUES (1, 1, 100),
       (1, 2, 200),
       (1, 3, 50),
       (1, 4, 500),
       (2, 1, 100),
       (3, 1, 100),
       (4, 5, 250),
       (5, 2, 200);

COMMIT;