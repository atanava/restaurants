## curl samples (application deployed at application context `restaurants`).
> For windows use `Git Bash`
>
### AdminRestController
#### get All Users
`curl -s http://localhost:8080/restaurants/rest/admin/users --user admin@gmail.com:admin`

#### get All Users 403 – Forbidden 
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/users' \
--header 'Authorization: Basic dXNlcjFAeWFuZGV4LnJ1OnBhc3N3b3Jk'`

#### get all incorrect email 401 – Unauthorized
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/users' \
 --header 'Authorization: Basic YWFhYWRtaW5AZ21haWwuY29tOmFkbWlu'` 

#### get all incorrect password 401 – Unauthorized
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/users' \
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOnBhc3N3b3Jk'`
 
#### get admin
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/users/100000' \
  --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
  
#### get user1
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/users/100001' \
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get user not found
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/users/100' \
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get user by email
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/users/by?email=user1@yandex.ru' \
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`

#### get user by email not found
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/users/by?email=notfound@gmail.com' \
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### create new user
`curl --location --request POST 'http://localhost:8080/restaurants/rest/admin/users' \
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
 --header 'Content-Type: application/json' \
 --data-raw '{"name":"New User","email":"test@mail.ru","password":"password","roles":["USER"]}'`

#### create with duplicated email 409 - Conflict
`curl --location --request POST 'http://localhost:8080/restaurants/rest/admin/users' \
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
 --header 'Content-Type: application/json' \
 --data-raw '{"name":"New User","email":"user1@yandex.ru","password":"password"}'`

#### create with incorrect email
`curl --location --request POST 'http://localhost:8080/restaurants/rest/admin/users' \
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
 --header 'Content-Type: application/json' \
 --data-raw '{"name":"New User","email":"testmail.ru","password":"password","roles":["USER"]}'`
 
#### delete new user
`curl --location --request DELETE 'http://localhost:8080/restaurants/rest/admin/users/100026' \
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### delete not found
`curl --location --request DELETE 'http://localhost:8080/restaurants/rest/admin/users/100' \
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get user2
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/users/100002' \
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### update user2
`curl --location --request PUT 'http://localhost:8080/restaurants/rest/admin/users/100002' \
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
 --header 'Content-Type: application/json' \
 --data-raw '{"id":"100002","name": "Vasja Pupkin","email": "updated2@hot.ee","password": "password","roles":["USER"]}'`
 
#### disable user2
`curl --location --request PATCH 'http://localhost:8080/restaurants/rest/admin/users/100002?enabled=false' \
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### enable user2
`curl --location --request PATCH 'http://localhost:8080/restaurants/rest/admin/users/100002?enabled=true' \
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
