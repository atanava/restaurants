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

INSERT INTO dishes (restaurant_id, name, price, active)
VALUES  (100003, 'Salad', 400, true),
        (100003, 'Soup', 530, true),
        (100003, 'Meat', 750, true),
        (100003, 'Fish', 960, true),
        (100003, 'Juice', 200, true),
        (100004, 'Salad', 320, true),
        (100004, 'Soup', 400, true),
        (100004, 'Meat', 550, true),
        (100004, 'Fish', 650, true),
        (100004, 'Juice', 120, true);

INSERT INTO menus (restaurant_id)
VALUES (100003),
        (100003),
        (100003),
        (100003),
        (100003),
        (100004),
        (100004),
        (100004),
        (100004),
        (100004);

INSERT INTO dishes_menus (dish_id, menu_id)
VALUES  (100005, 100015),
        (100006, 100016),
        (100007, 100017),
        (100008, 100018),
        (100009, 100019),
        (100010, 100020),
        (100011, 100021),
        (100012, 100022),
        (100013, 100023),
        (100014, 100024);

INSERT INTO votes (restaurant_id, user_id)
VALUES (100003, 100001),
       (100004, 100002);