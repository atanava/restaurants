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

INSERT INTO dishes (restaurant_id, name, is_in_menu, price)
VALUES  (100003, 'Salad', true, 400),
        (100003, 'Soup', true, 530),
        (100003, 'Meat', true, 750),
        (100003, 'Fish', true, 960),
        (100003, 'Juice', true, 200),
        (100004, 'Salad', true, 320),
        (100004, 'Soup', true, 400),
        (100004, 'Meat', true, 550),
        (100004, 'Fish', true, 650),
        (100004, 'Juice', true, 120);

INSERT INTO menus (restaurant_id, dish_id)
VALUES  (100003, 100005),
        (100003, 100006),
        (100003, 100007),
        (100003, 100008),
        (100003, 100009),
        (100004, 100010),
        (100004, 100011),
        (100004, 100012),
        (100004, 100013),
        (100004, 100014);

INSERT INTO votes (restaurant_id, user_id)
VALUES (100003, 100001),
       (100004, 100002);