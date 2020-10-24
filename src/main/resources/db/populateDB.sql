DELETE FROM user_roles;
DELETE FROM votes;
DELETE FROM users;
DELETE FROM dishes;
DELETE FROM restaurants;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES  ('Admin', 'admin@gmail.com', 'admin'),
        ('User1', 'user1@yandex.ru', 'password'),
        ('User2', 'user2@hot.ee', 'password');

INSERT INTO user_roles (role, user_id)
VALUES  ('ADMIN', 100000),
        ('USER', 100001),
        ('USER', 100002);

INSERT INTO restaurants (name)
VALUES  ('Gloria'),
        ('Troika');

INSERT INTO dishes (restaurant_id, name, price)
VALUES  (100003, 'Salat', 400),
        (100003, 'Soup', 530),
        (100003, 'Meat', 750),
        (100003, 'Fish', 960),
        (100003, 'Juice', 200),
        (100004, 'Salat', 320),
        (100004, 'Soup', 400),
        (100004, 'Meat', 550),
        (100004, 'Fish', 650),
        (100004, 'Juice', 120);

INSERT INTO votes (restaurant_id, user_id)
VALUES (100003, 100001),
       (100004, 100002);