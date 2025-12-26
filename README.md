# LiftLog Lite — Workout Tracking Backend

LiftLog Lite is a backend application for tracking workouts and exercises.
The project is designed as a learning backend system that demonstrates how to build a structured REST API using Spring Boot.

The main goal of the project is to practice backend development concepts such as entity modeling, RESTful architecture, and database interaction.

---

## 🚀 Tech Stack
- Java
- Spring Boot
- Spring Web (REST API)
- Spring Data JPA
- Hibernate
- SQL
- Maven
- JSON

---

## 📌 Features
- CRUD operations for workout-related entities
- REST API endpoints
- Layered architecture (controller / service / repository)
- Database persistence using JPA/Hibernate
- Clear domain model for fitness tracking
- JSON-based communication
- Extendable and clean backend structure

---

## 🧩 Project Structure
- **Controller layer** — handles HTTP requests and responses  
- **Service layer** — contains business logic  
- **Repository layer** — database access via JPA  
- **Entity layer** — domain models (Workout, Exercise, etc.)

---

## 📦 Example functionality
- Create and manage workouts
- Add exercises to workouts
- Retrieve workout data
- Update and delete records
- Work with relational data

---

## 🎯 Purpose
This project was created as a learning backend application to:
- practice Spring Boot development
- understand REST API design
- work with relational databases
- improve backend architecture skills
- prepare for real-world backend tasks

---

## ▶️ How to run
1. Clone the repository
2. Configure database connection in `application.properties`
3. Run the application:
   ```bash
   mvn spring-boot:run
