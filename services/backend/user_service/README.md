# User service

User service implementation using Gradle as a project manager and Kotlin as programming language.

Please use this application with Docker as it offer 2 stages: compiling and deploying.

Refer to the Backend README for information about build and using Docker.

## Description

This service contains data about users and also implements a JWT Authentication and Authorization.

Each time a user tries to access the application, the orchestrator service executes a JWT validity check on this service.