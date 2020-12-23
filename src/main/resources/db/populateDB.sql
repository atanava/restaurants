DELETE FROM votes;
DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM menus;
DELETE FROM dishes;
DELETE FROM restaurants;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES  ('Vasja', 'admin@gmail.com', '{noop}admin'),
        ('Fedja', 'user1@yandex.ru', '{noop}password'),
        ('Vasja', 'user2@hot.ee', '{noop}password');

INSERT INTO user_roles (role, user_id)
VALUES  ('ADMIN', 100000),
        ('USER', 100001),
        ('USER', 100002);

INSERT INTO restaurants (name)
VALUES  ('Troika'),
        ('Gloria');

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

INSERT INTO menus (restaurant_id, date)
VALUES (100003, '2020-11-19'),
        (100004, '2020-11-19'),
        (100003, '2020-11-20');

INSERT INTO dishes_menus (dish_id, menu_id)
VALUES  (100005, 100015),
        (100006, 100015),
        (100007, 100015),
        (100008, 100015),
        (100009, 100015),
        (100010, 100016),
        (100011, 100016),
        (100012, 100016),
        (100013, 100016),
        (100014, 100016),
        (100005, 100017),
        (100006, 100017),
        (100007, 100017),
        (100008, 100017),
        (100009, 100017)
        ;

INSERT INTO votes (restaurant_id, user_id, date)
VALUES (100003, 100000, '2020-11-19'),
        (100003, 100001, '2020-11-19'),
       (100004, 100002, '2020-11-19'),
       (100003, 100000, '2020-11-20'),
        (100004, 100001, '2020-11-20'),
       (100004, 100002, '2020-11-20');

INSERT INTO menus (restaurant_id)
VALUES (100003),
        (100004);

INSERT INTO dishes_menus (dish_id, menu_id)
VALUES  (100005, 100024),
        (100006, 100024),
        (100007, 100024),
        (100008, 100024),
        (100009, 100024),
        (100010, 100025),
        (100011, 100025),
        (100012, 100025),
        (100013, 100025),
        (100014, 100025);

