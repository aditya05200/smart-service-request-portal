# 🚀 Smart Service Request Portal

![Java](https://img.shields.io/badge/Java-17-orange) 
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green) 
![MySQL](https://img.shields.io/badge/MySQL-Database-blue)

---

## 1️⃣ Introduction

The **Smart Service Request Portal** is a secure backend application built using **Spring Boot**.

It implements:

- JWT-based authentication  
- Role-based authorization  
- Service request lifecycle management  
- Comment system  
- Audit logging  

The system follows a clean layered architecture and demonstrates real-world backend development practices.

---

## 2️⃣ Tech Stack

- Java 17  
- Spring Boot 3.x  
- Spring Security  
- JWT (JSON Web Token)  
- Spring Data JPA (Hibernate)  
- MySQL  
- Maven  
- Postman (API Testing)

---

## 3️⃣ Roles in the System

### 👤 USER
- Register & login  
- Create service requests  
- View own requests  
- View comments  

### 🧑‍💼 AGENT
- Login using JWT  
- View assigned requests  
- Update request status  
- Add comments  

### 👑 ADMIN
- View all requests  
- Assign requests to agents  
- View audit logs  
- Add comments  

---

## 4️⃣ Service Request Lifecycle

Requests move through the following stages:

- OPEN  
- IN_PROGRESS  
- ON_HOLD  
- RESOLVED  
- CLOSED  

---

## 5️⃣ Project Architecture

The project follows **layered architecture**:
```text
src/main/java/com/example/ticketing
│
├── controller # REST controllers
├── service # Business logic
├── repository # JPA repositories
├── entity # JPA entities
├── dto # Data Transfer Objects
├── security # JWT & Security config
├── exception # Global exception handling
│
└── TicketManagementSystemApplication.java

```
---


### Flow

Client  
⬇  
Controller  
⬇  
Service  
⬇  
Repository  
⬇  
MySQL Database  

Security Layer:
- JWT Authentication Filter  
- Role-Based Access Control  
- Stateless Authentication  

---

## 📡 API Endpoints

### 🔑 Authentication
- `POST /auth/register` – Register new user  
- `POST /auth/login` – Login and receive JWT  

### 📌 Service Requests
- `POST /api/requests` – Create request (USER)  
- `PUT /api/requests/{id}/status` – Update status (AGENT)  
- `PUT /api/requests/{id}/assign/{agentId}` – Assign request (ADMIN)  

### 💬 Comments
- `POST /api/requests/{requestId}/comments` – Add comment (AGENT, ADMIN)  

### 🧾 Audit
- `GET /api/audit/requests/{requestId}` – View audit logs (ADMIN)  

---

## ⚙️ Database Configuration

Configure MySQL in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/portal_db
spring.datasource.username=root
spring.datasource.password=****
spring.jpa.hibernate.ddl-auto=update
spring. jpa.show-sql=true


## ▶️ How to Run the Project

- Clone or download the project

- Configure MySQL database

- Update application.properties

### Run:

```mvn spring-boot:run```


### Server starts at:

``` http://localhost:8080 ```

## 🧪 Testing

- APIs tested using Postman

- JWT token required in header:

- Authorization: Bearer <JWT_TOKEN>

## 👨‍💻 Author
**Aditya Mishra**

- [LinkedIn Profile](https://www.linkedin.com/in/aditya-mishra-x)
- [GitHub Profile](https://github.com/aditya05200)

