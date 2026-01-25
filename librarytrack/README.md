# 📚 LibraryTrack – Backend API

LibraryTrack is a Spring Boot–based backend application designed to manage **Genres** (with parent–child hierarchy) and later **Books** for a library system. This project follows clean architecture principles using **DTOs, Mappers, Services, and Repositories**.

---

## 🛠 Tech Stack

* Java 17+
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Lombok
* Jakarta Validation
* Maven

---

## 📂 Project Structure

```
com.librarytrack
│
├── controller        # REST Controllers (API layer)
├── service           # Service interfaces
│   └── impl           # Service implementations
├── repository        # JPA Repositories
├── mapper            # Entity ↔ DTO mappers
├── payload
│   ├── dto            # Request/Response DTOs
│   └── response       # Common API responses
├── exception         # Custom exceptions
├── modal             # JPA Entities
└── LibraryTrackApplication.java
```

---

## 🧩 Core Domain – Genre

### Genre Features

* Create genre
* Update genre
* Soft delete (active = false)
* Hard delete (with FK constraints)
* Parent–child genre hierarchy
* Fetch top-level genres
* Fetch active genres with sub-genres
* Count active genres

---

## 🧾 Genre Fields Explained

| Field          | Description                             |
| -------------- | --------------------------------------- |
| `code`         | Unique genre identifier (e.g., FICTION) |
| `name`         | Display name of the genre               |
| `description`  | Details about the genre                 |
| `displayOrder` | Sorting order in UI                     |
| `active`       | Soft delete flag                        |
| `parentGenre`  | Reference to parent genre               |
| `subGenres`    | List of child genres                    |

---

## 🔁 Why DTOs?

We use **DTOs (Data Transfer Objects)** to:

* Avoid exposing entities directly
* Control API response shape
* Prevent lazy-loading issues
* Support future API versioning

---

## 🔄 Mapper Responsibility

**GenreMapper** handles:

* Entity → DTO
* DTO → Entity
* Updating existing entity from DTO

❌ No database calls inside mapper
✅ All DB logic stays in service layer

---

## 🧠 Service Layer Responsibility

Service layer:

* Contains business logic
* Handles parent–child relationships
* Calls repository
* Uses mapper for transformations

---

## 🌐 REST API Endpoints (Genre)

### Create Genre

```
POST /api/genre/create
```

Request body:

```json
{
  "code": "FICTION",
  "name": "Fiction",
  "description": "Fictional stories",
  "displayOrder": 1,
  "active": true,
  "parentGenreId": null
}
```

---

### Get All Genres

```
GET /api/genre
```

---

### Get Genre By ID

```
GET /api/genre/{genreId}
```

---

### Update Genre

```
PUT /api/genre/{genreId}
```

---

### Soft Delete Genre

```
DELETE /api/genre/{genreId}
```

---

### Hard Delete Genre

```
DELETE /api/genre/hardDelete/{genreId}
```

⚠️ Will fail if genre has sub-genres due to FK constraints.

---

### Get Top-Level Genres

```
GET /api/genre/top-level
```

---

### Count Active Genres

```
GET /api/genre/count
```

---

## ❗ Known Behavior (Important)

### Hard Delete Constraint Error

If a genre has sub-genres:

* MySQL prevents deleting parent row
* This is expected behavior

✅ Recommended: use **soft delete**

---

## 📌 Future Enhancements

* Book entity & mapping
* Pagination & search
* Global exception handling (`@ControllerAdvice`)
* Swagger / OpenAPI documentation
* Authentication & authorization
* Unit & integration tests

---

## 👨‍💻 Developer Notes

* Mapper ≠ Service
* DTO ≠ Entity
* Soft delete preferred in production
* Parent–child relationships handled in service

---

## 🚀 Status

✅ Genre module completed and stable

Next up: **Book module** 📖

---
