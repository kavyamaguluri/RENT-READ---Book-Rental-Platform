Book Rental System
The Book Rental System is a Spring Boot-based application that allows users to rent books, manage book inventory, and handle user authentication and authorization. This system is designed for both users and administrators, with different functionalities for each role.

**Features**


User Authentication: Allows users to register, log in, and manage their sessions.

Book Management: Admins can add, update, delete, and list books in the inventory.

Rental Management: Users can rent books, and admins can manage rentals.

Role-based Access Control: Differentiates between user and admin functionalities.

Prerequisites
Before you can run the application, ensure you have the following tools installed:

Java 11 or newer: The application is built with Java.

 Gradle: The project uses Gradle as the build tool.

MySQL or any relational database: You need a running database to store user and book information.

IDE: You can use an IDE like IntelliJ IDEA or Eclipse to open and edit the project.

Installation

1. Clone the Repository
   Clone the repository to your local machine:
   git clone https://github.com/*****/book-rental-system.git
   cd book-rental-system
4. Configure Database
   Create a new MySQL database (or any other database of your choice) and configure the connection in the application.properties file located in src/main/resources/.

For example, if using MySQL, you can modify the application.properties file as follows:

properties
spring.datasource.url=jdbc:mysql://localhost:3306/book_rental_system
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
spring.jpa.show-sql=true


3. Build the Project
Depending on your preference, you can build the project using Gradle.

Using Gradle:

gradle build

Run the Application

4. Once the project is built, you can run the application:

Using Gradle:
gradle bootRun


The application exposes the following RESTful endpoints:

1. Authentication Endpoints
   POST /auth/register: Registers a new user (requires username, password, and role in the body).

POST /auth/login: Authenticates a user and returns a JWT token.

2. Book Management Endpoints (Admin Only)
   POST /books: Adds a new book (requires name, author, genre, availability in the body).

PUT /books/{id}: Updates an existing book by ID.

DELETE /books/{id}: Deletes a book by ID.

GET /books: Lists all books in the inventory.

3. Rental Management Endpoints
   POST /rentals: Creates a new rental (requires bookId, userId, rentalDate, dueDate).

GET /rentals/{id}: Retrieves a rental by ID.

PUT /rentals/{id}: Updates a rental's status (e.g., returned).

Running Tests

To run the unit tests for the application, use the following command:

Using Gradle:
gradle test

Ensure that your database is running and properly configured in the application.properties file.
