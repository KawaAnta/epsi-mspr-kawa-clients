# API CLients

## Description 

The Clients microservice plays a crucial role within the application's architecture for managing customer-related data and interactions. Its primary objective is to handle operations related to customer management, providing functionalities to create, retrieve, update, and delete client records.

In the context provided earlier in this conversation, the Clients microservice aligns with the broader goal of modernizing the PayeTonKawa company's IT infrastructure by migrating towards a microservices-based architecture. Specifically, it aims to cater to the needs of managing customer information for both B2C and B2B segments efficiently.

## Main Functionalities

### 1. Client Data Management : 

The microservice facilitates the storage and management of client information, including personal details, contact information, and purchase history.

### 2. CRUD Operations : 

It supports standard CRUD operations (Create, Read, Update, Delete) to enable seamless interaction with client data.

### 3. API Endpoints : 
The microservice exposes RESTful APIs to allow integration with other components of the system, such as the webshop and distributors, ensuring secure and controlled access to client data as per business requirements.

### 4. Integration with Authentication :

Integrating with authentication mechanisms ensures secure access to client-related functionalities, adhering to security standards and protecting sensitive customer information.

### 5. Scalability and Performance :

Designed as a standalone service, the Clients microservice is architected for scalability and performance, capable of handling a growing volume of client data and interactions efficiently.
Overall, the Clients microservice serves as a foundational component within the microservices architecture, addressing the specific needs of client management and contributing to the overall agility, scalability, and functionality of the PayeTonKawa application ecosystem.

## Installation

### Running with IDE : 

#### Prerequisites

* Java Development Kit (JDK) 17 or higher installed on your machine.
* Apache Maven installed to manage project dependencies and build the application.
* Git installed to clone the repository from GitHub.

#### 1. Clone the Repository : 
```
git clone https://github.com/your-username/clients-api.git
```
#### 2. Navigate to the Project Directory :
```
cd clients-api
```
#### 3. Build the Project :
```
mvn clean install
```
#### 4. Running Tests :
```
mvn test
```

### Running with Docker : 

Without PostgreSQL in local :

```sh
docker-compose up
```

URL API : https://localhost:8081
URL Data base: https://localhost:5433

### Running local application

Need a PostgreSQL running localy on 5432 

https://www.postgresql.org/download/

Install PgAdmin properly.

Once installed, open PgAdmin and follow these steps:

* Create a user named 'admin' (In login/grouprole)
* Use the password found in the configuration
* Give them privileges to connect and superadmin rights
* Create a database named 'client' and assign the 'admin' user to it
* Then, you can start the application:

```sh
mvn clean install
mvn spring-boot:run
```

### Contributions
#### Checkstyle : 

Please, before make a contribution and a pull-request ensure that checkstyle test are ok !
To do so, execute commande below : 

```sh
mvn checkstyle:check 
```

#### Branch Naming Strategy :
* fix/kawa-{ticket-number}-{brief-description-of-fix} (for bug fixes)
* feature/kawa-{ticket-number}-{brief-description-of-feature} (for user story developments)
* chore/kawa-{ticket-number}-{brief-description-of-refactoring} (for code cleanups / refactoring) (ticket number is optional if no user story)
* tests/kawa-{ticket-number}-{brief-description-of-test-dev} (for user story test developments)

#### Commit Naming Strategy :
* Fix[ticket-number]: Description of the fix
* Feat[ticket-number]: Description of the feature
* Chore[ticket-number]: Description of the refactoring
* Tests[ticket-number]: Description of the test
