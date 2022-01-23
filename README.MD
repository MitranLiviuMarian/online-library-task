Online library
Application is managing customers and books that can be bought by a customer. Each customer has a list of purchased books.
The application is written with Java 11 and Spring Boot.
Customers and books are persisted in memory in static collections for simplicity. There is no real database or in memory database used.
Collections that were used to persist entities are not thread safe for simplicity.
The application does not contain any logging functionality for simplicity.
The application has no units tests.
Integration tests are testing Customer Controller and covers the controller/service/repository layers.
Validations were performed with Hibernate Validator.
The mapping between DTO's and entities is done via ModelMapper.class.
Custom exceptions are handled by using a ControllerAdvice.
The application does not have Swagger for API Documentation for simplicity.

RUNNING THE APPLICATION:
- USING IDE: Run the main class OnlineLibraryApplication
- USING MAVEN: Run from the root directory mvn spring-boot:run

Being a Spring boot application, when starting the Online Library, an embedded application server will start on port 8080 (Tomcat)
The application will be reachable at http://localhost:8080/


Endpoints:

Customer endpoints:

POST /customers creates a customer
PUT /customers/{id} updates an existing customer
DELETE /customers/{id} deletes an existing customer
GET /customers retrieves all customers
GET /customers/{id} retrieves the customer with the specified path id

Books endpoints:
IMPORTANT NOTE: Book endpoints can be used only if the requests contain a valid token
in the headers that is obtained by using the login API

POST /books creates a book
PUT /books/{id} updates an existing book
DELETE /books/{id} deletes an existing book
GET /books retrieves all books
GET /books/{id} retrieves the book with the specified path id
POST /books/{id}/buy?customerId={} accepts the customer and the book id that should be bought by the customer - customer id is passed as a query parameter

Login
POST /login accepts the email and password of a customer and generates a token that will be valid for 24 hours

After a token is generated, it is saved in memory. Each token tracks the generation date in order to perform the 24 hours validation.
Requests are intercepted by a custom interceptor which is expecting a valid token in the request headers.
