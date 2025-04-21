# RENT-READ---Book-Rental-Platform

Problem Statement
Develop a RESTful API service using Spring Boot to manage an online book rental system while using MySQL to persist the data.


Key Features
Please note that this is a simplified version of a book rental system, and you should focus on implementing the specified features effectively within the given constraints

The service must implement authentication and authorization

The service uses Basic Auth

The service must have two roles: USER and ADMIN

The service must have two types of API endpoints:

Public endpoints - Anyone can access (Ex. Registration, Login)

Private endpoints - Only authenticated users can access (Ex. GET all books)

The private endpoints also require authorization i.e. only users with specific permissions can access the endpoint (Ex. Creating (POST) a book is only allowed for the admin)

Note: Some of the design choices are left to you. For example, the requirement may state that the users must be able to rent a book using the service. You can either let the users with the role "USER" rent a book or both the “USER” and the “ADMIN”. Technically, both approaches are correct but be prepared to defend your design choices. Designing the database schema is another critical decision you must make and defend.


Requirements
The API must have the following features:

User Registration and Login
Users must be able to register by providing their email address and password

The password must be hashed and stored using BCrypt

Fields: Email, Password, First Name, Last Name, Role

The Role must be defaulted to "User" if it is not specified

Registered users must log in using their email address and password

Book Management
Store and manage book details

Fields: Title, Author, Genre, Availability Status

Availability Status tells whether the book is available to rent or not

Any user can browse all the available books

Only the administrator is allowed to create, update, and delete books

Rental Management
Users must be able to rent books using the service

A user cannot have more than two active rentals i.e. the service should throw an error if a user requests to rent a book while already having two other book rentals

Users must be able to return books that they have rented


Validation and Error Handling
Handle common errors and return appropriate HTTP codes (Ex. 404, User not found)

Additional Requirement
Use logs to log information and errors

Handle common errors gracefully and return appropriate HTTP codes (Ex. 404, User not found)

Include basic unit tests while making use of MockMvc and Mockito (Minimum 3)

Write meaningful, incremental commit messages

Include a descriptive README.MD for your application codebase

Generate a JAR** **file for your application and provide instructions on how to run it

Create and add a public Postman Collection in the README.MD (Optional)


Publishing and Documentation
Write meaningful commit messages (optional)

Include a descriptive README.MD for your application codebase

Create and add a public Postman Collection in the README.MD


Additional Notes
Implement the solution using a** **layered approach - Ex. Entity, Controller, Service, Repository

User API Documentation
Base URL
http://localhost:8081/

Endpoints
1. Create Admin Account
Endpoint:

POST /auth/signup

Request Body:


{

   "email": "admin@rentread.com",

   "password": "admin123456",

   "firstName": "admin",

   "lastName": "test",

   "role": "ADMIN"

}

Response:


{

    "id": 6,

    "firstName": "admin",

    "lastName": "test",

    "email": "admin@rentread.com",

    "role": "ADMIN"

}

Response Code: 201

2. Create User Account
Endpoint:

POST /auth/signup

Request Body:


{

    "email": "user.test@example.com",

    "password": "user123456",

    "firstName": "RegularTest",

    "lastName": "UserTest"

}

Response:


{

    "id": 7,

    "firstName": "RegularTest",

    "lastName": "UserTest",

    "email": "user.test@example.com",

    "role": "USER"

}

Response Code: 201

3. Login User/Admin
Endpoint:

POST /auth/login

Request Body:


{

   "email": "admin@rentread.com",

   "password": "admin123456"

}

Response:


{

    "id": 6,

    "firstName": "admin",

    "lastName": "test",

    "email": "admin@rentread.com",

    "role": "ADMIN"

}

Response Code: 200

4. Setup/Create a Book(Admin Only)
Endpoint:

POST /books

Request Body:


{

     "title": "Test Book 1",

     "author": "Test Author 1",

     "genre": "FICTION",

     "availabilityStatus": "AVAILABLE"

}


Response:


{

    "id": 17,

    "title": "Test Book 1",

    "author": "Test Author 1",

    "genre": "FICTION",

    "availabilityStatus": "AVAILABLE"

}

Response Code: 201

5. Delete A Book(Admin Only)
Endpoint:

DELETE /books/{book_id}

Request Body:


{}

Response:


{}

Response Code: 204

6. Update A Book(Admin Only)
Endpoint:

PUT /books/{book_id}

Request Body:


{

    "id": 17,

    "title": "Test Book 1",

    "author": "Test Author 123",

    "genre": "FICTION",

    "availabilityStatus": "AVAILABLE"

}

Response:


{

    "id": 17,

    "title": "Test Book 1",

    "author": "Test Author 123",

    "genre": "FICTION",

    "availabilityStatus": "AVAILABLE"

}

Response Code: 200

7. User Can’t Create, Delete & Update Books(403 Forbidden)
Endpoint:

PUT /books/{book_id}

Request Body:


{

    "id": 17,

    "title": "Test Book 1",

    "author": "Test Author 123",

    "genre": "FICTION",

    "availabilityStatus": "AVAILABLE"

}

Response:


{

    "message": "Access Denied: You don't have permission to perform this action",

    "httpStatus": "FORBIDDEN",

    "localDateTime": "2025-03-10T20:51:27.8317066"

}

Response Code: 403

8. Rent A Book
Endpoint:

POST /rentals/users/{userId}/books/{bookId}

Request Body:


{}

Response:


{

    "id": 5,

    "book": {

        "id": 17,

        "title": "Test Book 1",

        "author": "Test Author 123",

        "genre": "FICTION",

        "availabilityStatus": "NOT_AVAILABLE"

    },

    "rentedAt": "2025-03-10",

    "returnDate": null

}

Response Code: 201

9. GET active rentals for a User
Endpoint:

GET /rentals/active-rentals/users/{userId}

Response:


[

    {

        "id": 5,

        "book": {

            "id": 17,

            "title": "Test Book 1",

            "author": "Test Author 123",

            "genre": "FICTION",

            "availabilityStatus": "NOT_AVAILABLE"

        },

        "rentedAt": "2025-03-10",

        "returnDate": null

    }

]

Response Code: 200

10. Return A Book
Endpoint:

PUT /rentals/{rental_id}

Request Body:


{}

Response:


{}

Response Code: 204

11. Enforce Rental Limit
If the User has already rented two books and is trying to rent another, rental limit should be reinforced.

Endpoint:

POST /rentals/users/{userId}/books/{bookId}

Request Body:


{}

Response:


{

    "message": "User has already reached maximum book rental limit!",

    "httpStatus": "BAD_REQUEST",

    "localDateTime": "2025-03-10T21:10:53.9228473"

}

Response Code: 400


