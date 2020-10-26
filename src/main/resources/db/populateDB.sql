DELETE FROM votes;
DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM menus;
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

INSERT INTO dishes (name)
VALUES  ('Salat'),
        ('Soup'),
        ('Meat'),
        ('Fish'),
        ('Juice');

INSERT INTO menus (dish_id, restaurant_id, price)
VALUES  (100005, 100003, 400),
        (100006, 100003, 530),
        (100007, 100003, 750),
        (100008, 100003, 960),
        (100009, 100003, 200),
        (100005, 100004, 320),
        (100006, 100004, 400),
        (100007, 100004, 550),
        (100008, 100004, 650),
        (100009, 100004, 120);

INSERT INTO votes (restaurant_id, user_id)
VALUES (100003, 100001),
       (100004, 100002);