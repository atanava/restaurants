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

### AdminVoteRestController

#### get all Votes
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/votes'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get Vote 100024
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/votes/100024'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get Vote not found 
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/votes/100'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get all Votes by user1
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/votes/by-user?userId=100001'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get all Votes by Restaurant 100003
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/votes/by-restaurant?restaurantId=100003'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get all Votes by date
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/votes/by-date?date=2020-11-19'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get all Votes by Restaurant and date
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/votes/by-restaurant-and-date?restaurantId=100003&date=2020-11-19'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get all Votes by User and Restaurant
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/votes/by-user-and-restaurant?userId=100000&restaurantId=100003'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### delete Vote 100020
`curl --location --request DELETE 'http://localhost:8080/restaurants/rest/admin/votes/100020'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### delete not found
`curl --location --request DELETE 'http://localhost:8080/restaurants/rest/admin/votes/100'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
### ProfileVoteRestController

#### get all Votes from User admin
`curl --location --request GET 'http://localhost:8080/restaurants/rest/profile/votes'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get Vote 100017 from User admin
`curl --location --request GET 'http://localhost:8080/restaurants/rest/profile/votes/100017'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get Vote not found
`curl --location --request GET 'http://localhost:8080/restaurants/rest/profile/votes/100'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### vote for Restaurant 100003 as User admin
`curl --location --request POST 'http://localhost:8080/restaurants/rest/profile/votes?restaurantId=100003'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### re-vote for Restaurant 100004 as user1
`curl --location --request PUT 'http://localhost:8080/restaurants/rest/profile/votes?restaurantId=100004'
 --header 'Authorization: Basic dXNlcjFAeWFuZGV4LnJ1OnBhc3N3b3Jk'`
 
#### delete today Vote of user1
`curl --location --request DELETE 'http://localhost:8080/restaurants/rest/profile/votes'
 --header 'Authorization: Basic dXNlcjFAeWFuZGV4LnJ1OnBhc3N3b3Jk'`
 
### AdminDishRestController

#### get all Dishes from Restaurant 100003
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/dishes/all?restaurantId=100003'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get Juice from Restaurant 100003
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/dishes?restaurantId=100003&id=100009'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get Dish not found from Restaurant 100003
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/dishes?restaurantId=100003&id=100'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### deactivate Fish from Restaurant 100003
`curl --location --request PATCH 'http://localhost:8080/restaurants/rest/admin/dishes/activate?restaurantId=100003&id=100008&active=false'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### deactivate not found from Restaurant 100003
`curl --location --request PATCH 'http://localhost:8080/restaurants/rest/admin/dishes/activate?restaurantId=100003&id=100&active=false'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get all active Dishes from Restaurant 100003
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/dishes/all?restaurantId=100003&active=true'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get all inactive Dishes from Restaurant 100003
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/dishes/all?restaurantId=100003&active=false'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### activate Fish from Restaurant 100003
`curl --location --request PATCH 'http://localhost:8080/restaurants/rest/admin/dishes/activate?restaurantId=100003&id=100008&active=true'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### activate not found from Restaurant 100003
`curl --location --request PATCH 'http://localhost:8080/restaurants/rest/admin/dishes/activate?restaurantId=100003&id=100&active=true'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### create Coffee for Restaurant 100003
`curl --location --request POST 'http://localhost:8080/restaurants/rest/admin/dishes?restaurantId=100003'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
 --header 'Content-Type: application/json'
 --data-raw '{"name": "Coffee","restaurantId": 100003,"price": 300}'`
 
#### create duplicated Dish for Restaurant 100003
`curl --location --request POST 'http://localhost:8080/restaurants/rest/admin/dishes?restaurantId=100003'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
 --header 'Content-Type: application/json'
 --data-raw '{"name": "Meat","restaurantId": 100003,"price": 750}'`
 
#### get all Dishes from Restaurant 100004
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/dishes/all?restaurantId=100004'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
### AdminMenuRestController

#### get all Menus
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/menus/all'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get all Menus by Restaurant 100004
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/menus/all/by?restaurantId=100004'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get Menu 100023 from Restaurant 100003
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/menus?restaurantId=100003&menuId=100023'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get not found
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/menus?restaurantId=100003&menuId=100'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get by date
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/menus/all/by-date?date=2020-11-19'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get by Restaurant and date
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/menus/by-date?restaurantId=100004&date=2020-11-19'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### create Menu for Restaurant 100004
`curl --location --request POST 'http://localhost:8080/restaurants/rest/admin/menus?restaurantId=100004'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
 --header 'Content-Type: application/json'
 --data-raw '{"dishes": [
     {"id": 100013},
     {"id": 100014},
     {"id": 100012},
     {"id": 100010},
     {"id": 100011}
 ]}'`
 
#### create Menu with past date
`curl --location --request POST 'http://localhost:8080/restaurants/rest/admin/menus?restaurantId=100004'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
 --header 'Content-Type: application/json'
 --data-raw '{"dishes": [{"id": 100010}],"date": "2020-01-09"}'`
 
#### create Menu with duplicated date
`curl --location --request POST 'http://localhost:8080/restaurants/rest/admin/menus?restaurantId=100003'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
 --header 'Content-Type: application/json'
 --data-raw '{"dishes": [{"id": 100005}]}'`
 
#### update Menu 100023 for Restaurant 100003
`curl --location --request PUT 'http://localhost:8080/restaurants/rest/admin/menus?restaurantId=100003&menuId=100023'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
 --header 'Content-Type: application/json'
 --data-raw '{"dishes": [
     {"id": 100005},
     {"id": 100006},
     {"id": 100007}
 ]}'`
 
#### update not found
`curl --location --request PUT 'http://localhost:8080/restaurants/rest/admin/menus?restaurantId=100003&menuId=100'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
 --header 'Content-Type: application/json'
 --data-raw '{"dishes": [{"id": 100005}]}'`
  
#### delete Menu 100015 from Restaurant 100003
`curl --location --request DELETE 'http://localhost:8080/restaurants/rest/admin/menus?restaurantId=100003&menuId=100015'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### delete not found
`curl --location --request DELETE 'http://localhost:8080/restaurants/rest/admin/menus?restaurantId=100003&menuId=100'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
### ProfileRestaurantRestController

#### get all Restaurants
`curl --location --request GET 'http://localhost:8080/restaurants/rest/profile/restaurants'
 --header 'Authorization: Basic dXNlcjFAeWFuZGV4LnJ1OnBhc3N3b3Jk'`
 
#### get Restaurant 100003 with today Menu
`curl --location --request GET 'http://localhost:8080/restaurants/rest/profile/restaurants/100003'
 --header 'Authorization: Basic dXNlcjFAeWFuZGV4LnJ1OnBhc3N3b3Jk'`
 
#### get Restaurant 100004 with today Menu
`curl --location --request GET 'http://localhost:8080/restaurants/rest/profile/restaurants/100004'
 --header 'Authorization: Basic dXNlcjFAeWFuZGV4LnJ1OnBhc3N3b3Jk'`
 
#### get not found
`curl --location --request GET 'http://localhost:8080/restaurants/rest/profile/restaurants/100'
 --header 'Authorization: Basic dXNlcjFAeWFuZGV4LnJ1OnBhc3N3b3Jk'`
 
### AdminRestaurantRestController

#### get all Restaurants
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/restaurants'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get Restaurant 100003
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/restaurants/100003'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get Restaurant 100003 with Votes
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/restaurants/100003?votes=true'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get Restaurant 100003 with Menus
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/restaurants/100003?menus=true'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get Restaurant 100003 with Votes and Menus
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/restaurants/100003?votes=true&menus=true'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get all Restaurants with Votes
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/restaurants?votes=true'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### get not found
`curl --location --request GET 'http://localhost:8080/restaurants/rest/admin/restaurants/100'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
 
#### create new Restaurant
`curl --location --request POST 'http://localhost:8080/restaurants/rest/admin/restaurants'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
 --header 'Content-Type: application/json' --data-raw '{"name":"Beerhouse"}'`
 
#### create with duplicate name
`curl --location --request POST 'http://localhost:8080/restaurants/rest/admin/restaurants'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
 --header 'Content-Type: application/json'
 --data-raw '{"name":"Troika"}'`
 
#### update Restaurant 100004
`curl --location --request PUT 'http://localhost:8080/restaurants/rest/admin/restaurants/100004'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
 --header 'Content-Type: application/json'
 --data-raw '{"id":100004,"name":"Pushkin"}'`
 
#### update not found
`curl --location --request PUT 'http://localhost:8080/restaurants/rest/admin/restaurants/100'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
 --header 'Content-Type: application/json'
 --data-raw '{"id":100,"name":"Pushkin"}'`
 
#### delete Restaurant 100004
`curl --location --request DELETE 'http://localhost:8080/restaurants/rest/admin/restaurants/100004'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
 --data-raw ''`
 
#### delete not found
`curl --location --request DELETE 'http://localhost:8080/restaurants/rest/admin/restaurants/100'
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
