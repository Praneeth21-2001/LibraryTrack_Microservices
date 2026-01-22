# 📚 LibraryTrack Microservices – Genre Service

A production‑ready **Spring Boot microservice** responsible for managing **book genres** within the LibraryTrack ecosystem. This service exposes REST APIs for creating, retrieving, updating, and deleting genres, following clean architecture and best practices.

---

## 🚀 Features

* CRUD operations for **Genres**
* RESTful API design
* DTO–Entity mapping
* Global exception handling
* Clean layered architecture
* Maven-based build
* Ready for containerization & scaling

---

## 🏗️ Tech Stack

| Layer      | Technology                |
| ---------- | ------------------------- |
| Language   | Java 17                   |
| Framework  | Spring Boot               |
| Build Tool | Maven                     |
| Database   | MySQL / H2 (configurable) |
| ORM        | Spring Data JPA           |
| API Style  | REST                      |
| Testing    | JUnit, Spring Boot Test   |

---

## 📂 Project Structure

```
librarytrack/
├── src/main/java/com/librarytrack
│   ├── controller      # REST controllers
│   ├── service         # Business logic interfaces
│   ├── service/impl    # Service implementations
│   ├── repository      # JPA repositories
│   ├── mapper          # DTO ↔ Entity mappers
│   ├── payload         # DTOs & API responses
│   ├── exception       # Custom & global exceptions
│   └── modal           # JPA entities
│
├── src/main/resources
│   └── application.properties
│
├── src/test/java
│   └── LibrarytrackApplicationTests.java
│
├── pom.xml
└── README.md
```

---

## 🔗 API Endpoints

### ➕ Create Genre

```
POST /api/genres
```

**Request Body**

```json
{
  "name": "Science Fiction",
  "description": "Books based on futuristic concepts"
}
```

---

### 📄 Get All Genres

```
GET /api/genres
```

---

### 🔍 Get Genre by ID

```
GET /api/genres/{id}
```

---

### ✏️ Update Genre

```
PUT /api/genres/{id}
```

---

### ❌ Delete Genre

```
DELETE /api/genres/{id}
```

---

## ⚠️ Error Handling

All exceptions are handled globally using `@ControllerAdvice`.

**Sample Error Response:**

```json
{
  "timestamp": "2026-01-22T12:30:00",
  "message": "Genre not found",
  "status": 404
}
```

---

## ⚙️ Configuration

Update database details in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/librarytrack
spring.datasource.username=root
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## ▶️ Running the Application

### Using Maven Wrapper

```bash
./mvnw spring-boot:run
```

### Using Maven

```bash
mvn clean install
mvn spring-boot:run
```

Application will start at:

```
http://localhost:8080
```

---

## 🧪 Testing

Run tests using:

```bash
mvn test
```

---

## 🔐 Security (Planned)

* Spring Security
* JWT authentication
* Role-based access control

---

## 🧩 Future Enhancements

* Integration with **Book Service**
* API Gateway & Service Discovery
* Docker & Kubernetes support
* Centralized logging & monitoring
* CI/CD pipeline (GitHub Actions)

---

## 👨‍💻 Author

**Praneeth**
Software Engineer | Java | Spring Boot | Microservices

---

## 📄 License

This project is licensed under the **MIT License**.

---

⭐ If you like this project, give it a star — it helps a lot!
