# Jaengyi PMS (SI Project Management & Collaboration Tool)

This is a comprehensive SI (System Integration) project management and collaboration tool built with a modern microservices architecture. This document outlines the project structure, technologies used, and instructions for running the application.

## 1. Architecture

The system is designed based on a **Microservice Architecture (MSA)** to ensure scalability, flexibility, and separation of concerns. Each core functionality is encapsulated within its own independent service.

-   **API Gateway:** A single entry point for all client requests, handling routing, authentication, and other cross-cutting concerns.
-   **Service Discovery:** (Assumed for `lb://` URI in gateway) Each microservice would register itself with a discovery server like Eureka or Consul.
-   **Backend Services:** Each service has its own dedicated responsibility and database schema.
-   **Frontend:** A single-page application (SPA) that communicates with the backend services via the API Gateway.

## 2. Technology Stack

| Category      | Technology                                               |
|---------------|----------------------------------------------------------|
| **Backend**   | Java 17, Spring Boot 3.x, Spring Cloud, Spring Security  |
| **Frontend**  | React.js, Axios                                          |
| **Database**  | PostgreSQL                                               |
| **ORM**       | JPA (Hibernate)                                          |
| **Build Tool**| Maven                                                    |
| **Infra/DevOps**| Docker, Docker Compose                                   |
| **Messaging** | RabbitMQ (Included in Docker Compose)                    |

## 3. Service Overview

### `api-gateway`
-   **Role:** Acts as the single entry point for all incoming requests. It routes requests to the appropriate microservice.
-   **Features:**
    -   Service routing for all backend services.
    -   Global filter for JWT-based authentication validation.

### `auth-service`
-   **Role:** Manages all aspects of user authentication and authorization.
-   **Features:**
    -   User registration (`/users`).
    -   User login and JWT token issuance (`/login`).
    -   User information retrieval.

### `project-service`
-   **Role:** Handles the core project management functionalities.
-   **Features:**
    -   Project creation and management.
    -   Task management within projects (WBS).
    -   Project member management.

### `document-service`
-   **Role:** Manages file uploads, downloads, and versioning for project-related documents.
-   **Features:**
    -   File upload associated with a project.
    -   File download.
    -   (Assumed) Integration with AWS S3 for object storage.

### `frontend`
-   **Role:** Provides the user interface for interacting with the system.
-   **Features:**
    -   Login page.
    -   Project dashboard to view projects.
    -   (Skeleton) Kanban board component for task visualization.

## 4. How to Run the Application

For detailed, step-by-step instructions on how to build and run the entire application stack, please see the execution guide:

**[>> How to Run the Application (EXECUTE.md)](EXECUTE.md)**


## 5. Project Structure

```
.
├── api-gateway/
├── auth-service/
├── document-service/
├── project-service/
├── frontend/
├── docker-compose.yml
├── pom.xml
└── README.md
```
