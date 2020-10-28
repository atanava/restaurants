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

INSERT INTO dishes (restaurant_id, name, is_in_menu)
VALUES  (100003, 'Salad', true),
        (100003, 'Soup', true),
        (100003, 'Meat', true),
        (100003, 'Fish', true),
        (100003, 'Juice', true),
        (100004, 'Salad', true),
        (100004, 'Soup', true),
        (100004, 'Meat', true),
        (100004, 'Fish', true),
        (100004, 'Juice', true);

INSERT INTO menus (dish_id, price)
VALUES  (100005, 400),
        (100006, 530),
        (100007, 750),
        (100008, 960),
        (100009, 200),
        (100010, 320),
        (100011, 400),
        (100012, 550),
        (100013, 650),
        (100014, 120);

INSERT INTO votes (restaurant_id, user_id)
VALUES (100003, 100001),
       (100004, 100002);