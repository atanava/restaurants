### curl samples (application deployed at application context `restaurants`).
> For windows use `Git Bash`
>
#### get All Users
`curl -s http://localhost:8080/restaurants/rest/admin/users --user admin@gmail.com:admin`

#### get Users 100000
`curl -s http://localhost:8080/restaurants/rest/admin/users/100000 --user admin@gmail.com:admin`

#### register Users
`curl -s -i -X POST -d '{"name":"New User","email":"test@mail.ru","password":"password1"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurants/rest/profile/register`

#### validate with Error
`curl -s -X POST -d '{}' -H 'Content-Type: application/json' http://localhost:8080/restaurants/rest/admin/users --user admin@gmail.com:admin`
