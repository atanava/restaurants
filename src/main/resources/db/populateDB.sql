DELETE FROM votes;
DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM menus;
DELETE FROM dishes;
DELETE FROM restaurants;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES  ('Vasja', 'admin@gmail.com', 'admin'),
        ('Fedja', 'user1@yandex.ru', 'password'),
        ('Vasja', 'user2@hot.ee', 'password');

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
        (100004, '2020-11-19');

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
        (100014, 100016);

INSERT INTO votes (restaurant_id, user_id)
VALUES (100003, 100001),
       (100004, 100002);