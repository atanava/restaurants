## curl samples (application deployed at application context `restaurants`).
> For windows use `Git Bash`
>
### AdminRestController
#### get All Users
`curl -s http://localhost:8080/restaurants/rest/admin/users --user admin@gmail.com:admin`

#### get All Users 403 – Forbidden 
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/users' 
--header 'Authorization: Basic dXNlcjFAeWFuZGV4LnJ1OnBhc3N3b3Jk'`

#### get all incorrect email 401 – Unauthorized
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/users' 
--header 'Authorization: Basic YWFhYWRtaW5AZ21haWwuY29tOmFkbWlu'` 

#### get all incorrect password 401 – Unauthorized
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/users' 
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOnBhc3N3b3Jk'`
 
#### get admin
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/users/100000' 
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
  
#### get user1
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/users/100001' 
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get User not found
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/users/100' 
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get User by email
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/users/by?email=user1@yandex.ru' 
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`

#### get User by email not found
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/users/by?email=notfound@gmail.com' 
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### create new User
`curl --location --request POST 'http://localhost:8080/restaurants/rest/admin/users'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
 --header 'Content-Type: application/json'
 --data-raw '{"name":"New User","email":"test@mail.ru","password":"password","roles":["USER"]}'`

#### create with duplicated email 409 - Conflict
`curl --location --request POST 'http://localhost:8080/restaurants/rest/admin/users'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
 --header 'Content-Type: application/json'
 --data-raw '{"name":"New User","email":"user1@yandex.ru","password":"password"}'`

#### create with incorrect email
`curl --location --request POST 'http://localhost:8080/restaurants/rest/admin/users'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
 --header 'Content-Type: application/json'
 --data-raw '{"name":"New User","email":"testmail.ru","password":"password","roles":["USER"]}'`
 
#### delete new User
`curl --location --request DELETE 'http://localhost:8080/restaurants/rest/admin/users/100026'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### delete not found
`curl --location --request DELETE 'http://localhost:8080/restaurants/rest/admin/users/100'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get user2
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/users/100002'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### update user2
`curl --location --request PUT 'http://localhost:8080/restaurants/rest/admin/users/100002'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
 --header 'Content-Type: application/json'
 --data-raw '{"id":"100002","name": "Vasja Pupkin","email": "updated2@hot.ee","password": "password","roles":["USER"]}'`
 
#### disable user2
`curl --location --request PATCH 'http://localhost:8080/restaurants/rest/admin/users/100002?enabled=false'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### enable user2
`curl --location --request PATCH 'http://localhost:8080/restaurants/rest/admin/users/100002?enabled=true'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
### ProfileRestController

#### create new User
`curl --location --request POST 'http://localhost:8080/restaurants/rest/profile/register'
 --header 'Content-Type: application/json'
 --data-raw '{"name":"New registered User","email":"test4@mail.ru","password":"password"}'`
 
#### get new registered User
`curl --location --request GET 'http://localhost:8080/restaurants/rest/profile'
 --header 'Authorization: Basic dGVzdDRAbWFpbC5ydTpwYXNzd29yZA=='`
 
#### update registered User
`curl --location --request PUT 'http://localhost:8080/restaurants/rest/profile'
 --header 'Authorization: Basic dGVzdDRAbWFpbC5ydTpwYXNzd29yZA=='
 --header 'Content-Type: application/json'
 --data-raw '{"name": "Pavel Chekhov","email": "test4@mail.ru","password": "password"}'`
 
#### update with incorrect id
`curl --location --request PUT 'http://localhost:8080/restaurants/rest/profile'
 --header 'Authorization: Basic dGVzdDRAbWFpbC5ydTpwYXNzd29yZA=='
 --header 'Content-Type: application/json'
 --data-raw '{"id": 100002,"name": "Pavel Chekhov","email": "test2@mail.ru","password": "password"}'`
 
#### delete unauthorized 
`curl --location --request DELETE 'http://localhost:8080/restaurants/rest/profile'
 --header 'Authorization: Basic bm90ZXhpc3RAbWFpbC5ydTpwYXNzd29yZA=='`
 
#### delete registered User
`curl --location --request DELETE 'http://localhost:8080/restaurants/rest/profile'
 --header 'Authorization: Basic dGVzdDRAbWFpbC5ydTpwYXNzd29yZA=='`


