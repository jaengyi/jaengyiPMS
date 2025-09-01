# How to Run the Application

This document provides step-by-step instructions to build and run the entire Jaengyi PMS application stack, including all backend microservices and the frontend web application.

## Prerequisites

-   **Java 17:** Ensure you have Java 17 or a later version installed.
-   **Apache Maven:** The project uses Maven for building the backend services.
-   **Docker and Docker Compose:** Required to containerize and run the microservices and infrastructure (PostgreSQL, RabbitMQ).
-   **Node.js and npm:** Required for running the frontend React application.

## Step 1: Run Backend Services

All backend microservices and infrastructure are managed by Docker Compose.

1.  Open a terminal in the project's root directory (`C:\05.Project\jaengyiPMS`).
2.  Execute the following command to build the Docker images and start all containers. The `--build` flag ensures that any code changes are re-built into the images.

    ```shell
    docker-compose up --build
    ```

    This command will run all services in the foreground and display their logs. Keep this terminal window open.

## Step 2: Run the Frontend Application

With the backend running, you can now start the user interface.

1.  Open a **new terminal window**.
2.  Navigate to the `frontend` directory:
    ```shell
    cd frontend
    ```
3.  If you are running this for the first time, install the necessary Node.js dependencies:
    ```shell
    npm install
    ```
4.  Start the React development server:
    ```shell
    npm start
    ```

## Step 3: Access the Website

After `npm start` completes, it should automatically open a new tab in your default web browser.

If it doesn't, you can manually access the application by navigating to the following URL in your web browser:

**[http://localhost:3000](http://localhost:3000)**

You should see the application's login page.
