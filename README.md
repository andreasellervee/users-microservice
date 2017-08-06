# Users microservice

Microservice for user management. 

Ability to create new users, modify users, delete users and query users by criteria.

Integrated with ActiveMQ to provide event-based messaging with other microservices.

## How to run?

* For message bus integration:
  * Download: http://activemq.apache.org/download.html
  * Run: `./activemq start`
    * Listens at: `tcp://localhost:61616`
    * Admin console at: `http://localhost:8161/admin/` with `admin/admin`
* Running the microservice:
  * Run: `./gradlew bootrun`
  * Endpoint documentation: `http://localhost:8080/swagger-ui.html#/`
* Testing:
  * To run unit-tests: `./gradlew test`
  * Postman API Collection: `https://www.getpostman.com/collections/33dc7416385c9d66b992`