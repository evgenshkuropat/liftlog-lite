LiftLog Lite — Spring Boot REST API (Workout Tracker)

A clean and production-ready Spring Boot REST API for tracking strength workouts and exercises.
Designed with best practices: DTOs, validation, pagination, error handling, logging, and Docker support.

This project demonstrates my backend skills using Java + Spring Boot and can be easily extended into a full fitness application.

🚀 Key Features

✅ RESTful API architecture
✅ CRUD for workouts and exercises
✅ Pagination & sorting
✅ DTO-based responses
✅ Validation with clear error messages
✅ Global exception handling
✅ OpenAPI / Swagger documentation
✅ PostgreSQL + Spring Data JPA
✅ Docker & docker-compose support
✅ Request logging & timing
✅ Clean layered architecture

🧱 Tech Stack

Java 17

Spring Boot 3

Spring Web

Spring Data JPA (Hibernate)

PostgreSQL

Jakarta Validation

Springdoc OpenAPI (Swagger UI)

Maven

Docker / Docker Compose

📦 Project Structure
com.example.liftloglite
├── common        # global errors, exception handling
├── config        # OpenAPI, logging, filters
├── dto           # request / response DTOs
├── exercise      # exercise domain
├── workout       # workout domain
└── LiftlogLiteApplication

🔗 API Endpoints
Workouts
Method	Endpoint	Description
POST	/api/workouts	Create workout
GET	/api/workouts	List workouts (pagination)
POST	/api/workouts/{id}/sets	Add set to workout
POST	/api/workouts/{id}/finish	Finish workout
DELETE	/api/workouts/{id}	Delete workout
Exercises
Method	Endpoint	Description
POST	/api/exercises	Create exercise
GET	/api/exercises	List exercises
DELETE	/api/exercises/{id}	Delete exercise
📘 Swagger / OpenAPI

After starting the app:

👉 Swagger UI

http://localhost:8080/swagger-ui/index.html


Includes:

request/response examples

validation errors

response codes

tags & descriptions

📄 Example Requests
Create workout
POST /api/workouts
{
  "startedAt": "2025-01-01T10:00:00Z"
}

Add set to workout
POST /api/workouts/{id}/sets
{
  "exerciseId": "550e8400-e29b-41d4-a716-446655440000",
  "weightKg": 80,
  "reps": 8
}

⚠️ Error Response Format

All errors follow a consistent structure:

{
  "status": 400,
  "message": "Validation failed",
  "path": "/api/workouts",
  "timestamp": "2025-01-01T12:00:00Z",
  "errors": [
    "startedAt must not be null"
  ]
}

🐳 Run with Docker (recommended)
1️⃣ Copy environment variables
cp .env.example .env

2️⃣ Start application
docker compose up --build

3️⃣ Open Swagger
http://localhost:8080/swagger-ui/index.html

▶ Run locally without Docker

Requirements:

Java 17

PostgreSQL

Configure datasource in application.yml, then:

mvn spring-boot:run

🧠 Design Decisions

DTOs separate API layer from persistence

Mapping logic extracted to mapper classes

No entity exposure through controllers

Clear service boundaries

Centralized exception handling

Logging via OncePerRequestFilter

Ready for authentication extension (JWT)

👨‍💻 Author

Evžen Shkuropat
Java Backend Developer

GitHub: https://github.com/evgenshkuropat

🚀 Future Improvements

JWT authentication

User accounts

Workout templates

Statistics & progress tracking

Integration tests

CI pipeline (GitHub Actions)

Frontend (React)
