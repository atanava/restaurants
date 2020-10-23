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
VALUES  ('USER', 100000),
        ('ADMIN', 100000),
        ('USER', 100001),
        ('USER', 100002);

INSERT INTO restaurants (name)
VALUES  ('Gloria'),
        ('Troika');

INSERT INTO dishes (name, restaurant_id, price)
VALUES  ('Salat', 100003, 400),
        ('Soup', 100003, 530),
        ('Meat', 100003, 750),
        ('Fish', 100003, 960),
        ('Juice', 100003, 200),
        ('Salat', 100004, 320),
        ('Soup', 100004, 400),
        ('Meat', 100004, 550),
        ('Fish', 100004, 650),
        ('Juice', 100004, 120);

INSERT INTO votes (restaurant_id, user_id)
VALUES (100004, 100000),
       (100004, 100001);