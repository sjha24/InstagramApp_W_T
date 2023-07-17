# InstagramApp
[![Java](https://img.shields.io/badge/Java>=8.0-blue.svg)](https://docs.spring.io/spring-boot/docs/0.5.0.M6/api/org/springframework/boot/SpringApplication.html)
[![maven](https://img.shields.io/badge/maven->=3.0.5-green.svg)](https://www.npmjs.com/package/npm/v/5.5.0)
[![springBoot](https://img.shields.io/badge/SpringBoot->=3.0.6-blue.svg)](https://nodejs.org/en/blog/release/v9.3.0)
>This project is a basic web application that allows users to sign in, sign up, and manage their profile information. Additionally, users can create posts and view posts created by other users. The application uses authentication tokens to secure user data and ensure that only authenticated users can access certain features of the application.

[Homepage]();

## Framework used
 * Spring Boot
## Languaged Uesd
 * Java
## Data Model
```
* User Model
```
Id : integer
firstName : string
lastName : string
age : integer
email : string
password : string
phoneNumber : string
```
* Post Model

postId = Long
createdDate : Timestamp
updatedDate : Timestamp
postData : String
@ManyToOne
user : User

* Authentication Token

tokenId : Long
token : string
tokenCreationDate : LocalDate
@OneToOne 
user : User

```
## Data Flow
```
1. User send as a request to the application throgh the endpoints
2. the api recived request and sends it to the appropriate controll method
3. the controller method makes a call to the method in service class.
4. he controller method returns a response to the API
5. The API sends the response back to the user
```
## Function Used 
```
* User Controller

POST /user/signup: create a new user account
POST /user/signin: authenticate a user and receive an authentication token
PUT /user: update user details
DELETE /user/signout: authenticate a user and delete authentication token

* Post Controller

POST /post: create a new post
GET /post: get all posts
```
## Data Structure Used
* SQL Database
```
We have used Persistent database to implement CRUD Operations.
```

## Project Summary

The project is a basic web application built using Java and the Spring framework. It allows users to sign up, sign in, and manage their profile information. Users can also create and view posts. The application uses authentication tokens to secure user data and ensure that only authenticated users can access certain features. The API endpoints include user signup, signin, and update details, post creation and retrieval, and authentication token creation.

## Author

Saurav Kumar

* twiter : [@saurav](https://twitter.com/Sauravjha24)
* Github : [@sjha](https://github.com/sjha24)

## Contributing

Contributions , issues and features requestes are welcome !

Feel free to check issues page

## Show your support

Give a rating if this project help you

## License

Copyright : 2023 [Saurav Kumar]()
This project is [GeekSter](https://www.geekster.in/) license
