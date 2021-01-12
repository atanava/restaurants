Voting for Restaurants
-----------------------
Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) **without frontend**.

The task is:

Build a voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it.

## Solution

### Technologies

- Java 14
- Spring Framework 5
- Spring Data JPA
- Spring Web MVC
- Spring Security
- Spring Test
- HSQLDB
- Hibernate ORM
- Hibernate Validator
- JSoup
- Apache Tomcat
- Ehcache
- Jackson JSON
- JUnit 5
- Hamcrest
- AssertJ
- JSONassert
- Logback
- SLF4J

### Solution Description

The decomposition of the tasks of the technical task was carried out, on the basis of which the following subtasks were performed:

1. The structure of the HSQLDB database has been developed (see files hsqldb.properties, initDB\_hsql.sql, populateDB.sql)
2. Created a hierarchical structure of entities on the model layer
3. Created interfaces and classes for Data JPA ORM on the repository layer
4. Added logging of the program
5. Created Data Transfer Objects for a more convenient and secure external presentation of data
6. Helper classes for transforming entities in Transfer Objects, as well as custom exceptions have been created (see the util package)
7. Created business logic classes on the service layer
8. Classes of REST controllers on the web layer have been created, as well as classes for transforming data from Java to JSON and vice versa
9. Added user authentication and authorization based on Spring Security
10. Added custom AOP-based ExceptionInfoHandler error handler
11. Tests created
12. Created collection curl.md and restaurants.postman\_collection.json for external API testing

### Application functionality

The program interacts with clients via REST API using Rest Controllers.

### Description of controllers

#### _AdminRestController_

The user must be authorized and have the ADMIN role

##### _Methods_

- _`getAll`_ returns a list of all users
- _`get`_ returns the user by his unique id
- _`getByMail`_ returns the user by his unique email
- _`createWithLocation`_ creates a new user based on a unique email. If a user with this email already exists in the database, then an exception is thrown
- _`delete`_ deletes a user by his unique id
- _`update`_ allows you to change user data by its unique id
- _`enable`_ allows you to enable or disable the user based on the received boolean request parameter

####_ProfileRestController_

#####_Methods_

- _`register`_ allows you to create an account for a new user based on a unique email. If a user with this email already exists in the database, then an exception is thrown
- _`get`_ allows an authorized user to view their account details
- _`update`_ allows an authorized user to change their account details
- _`delete`_ allows an authorized user to delete their account

####_ProfileRestaurantRestController_

The user must be authorized and have the USER or ADMIN role

##### _Methods_

- _`getAllTos`_ returns a list of all restaurants
- _`getTo`_ returns a restaurant with today&#39;s menu with a unique restaurant id

#### _AdminRestaurantRestController_

The user must be authorized and have the ADMIN role

##### _Methods_

- _`create`_ creates a new restaurant with a unique name. If a restaurant with the same name already exists in the database, an exception is thrown
- _`update`_ updates the restaurant data
- _`get`_ returns a restaurant by its unique id
- _`getWithVotes`_ returns a restaurant by its unique id with a list of votes belonging to it
- _`getWithMenus`_ returns a restaurant by its unique id with a list of menus belonging to it
- _`getWithVotesAndMenus`_ returns a restaurant by its unique id with lists of voices and menus belonging to it
- _`getAll`_ returns a list of all restaurants
- _`getAllWithVotes`_ returns a list of all restaurants with lists of their votes

#### _AdminDishRestController_

The user must be authorized and have the ADMIN role

#####_Methods_

- _`create`_ creates a new dish with a unique combination of restaurant id, dish name and price. If a dish with such a combination already exists in the database, an exception is thrown
- _`deactivate`_ deactivates the dish in the food catalog, while the dish remains in the menu. It uses unique restaurant id and dish id to identify the dish
- _`activate`_ activates the dish in the food catalog, while the dish remains in the menu. It uses unique restaurant id and dish id to identify the dish
- _`get`_ returns a dish by unique restaurant id and dish id
- _`getAll`_ returns a list of all restaurant dishes by unique restaurant id
- _`getAllActive`_ returns a list of all active restaurant dishes by unique restaurant id
- _`getAllInActive`_ returns a list of all inactive restaurant dishes by unique restaurant id

#### _AdminMenuRestController_

The user must be authorized and have the ADMIN role

##### _Methods_

- _`create`_ creates a menu with a unique combination of restaurant id and date. If a menu with this combination already exists in the database, an exception is thrown. The menu can contain only today&#39;s date or a date from the future. If the date is in the past, an exception is thrown. If the menu does not contain a date, then today&#39;s date is assigned to it. The menu should contain a list of dishes. If there is no list of dishes in the menu, or if it is empty, then an exception is thrown
- _`update`_ updates the existing menu based on the restaurant id and menu id. If there is no such menu in the database, an exception is thrown. The menu should contain a list of dishes. If there is no list of dishes in the menu, or if it is empty, then an exception is thrown
- _`delete`_ deletes an existing menu based on the restaurant id and menu id. If there is no such menu in the database, then an exception is thrown
- _`get`_ returns a menu based on restaurant id and menu id. If there is no such menu in the database, then an exception is thrown
- _`getByRestAndDate`_ returns a menu based on the restaurant id and date. If there is no such menu in the database, then nothing is returned.
- _`getAll`_ returns a list of all menus for all restaurants.
- _`getAllByRestaurant`_ returns a list of all restaurant menus based on its id
- _`getAllByDate`_ returns a list of all menus of all restaurants by a specific date

--------------------------------
#### Work in progress...