📘 Public API Gateway – Showcase Assignment
📌 Overview

This project implements a Public API Gateway App in Spring Boot.
It demonstrates:

JWT Authentication for secure login and user isolation.

Per-user API storage (each user can save & fetch only their own URLs).

Factory Pattern to parse external API responses dynamically.

OOP concepts (encapsulation, abstraction, polymorphism).

PostgreSQL persistence.

Deployable on free cloud platforms (Render, Railway, Fly.io).

🚀 Features

Register/Login users with JWT-based authentication.

Store public JSON API URLs per user.

Fetch & save API responses (raw + parsed).

View only the responses belonging to the logged-in user.

Factory Pattern ensures extensibility for multiple API types.

Clean DTO vs Entity separation.

Cloud-deployable with PostgreSQL support.

🏗️ Architecture

Entities:

AppUser → registered users.

ApiUrl → user’s saved API URLs.

ApiResponse → raw + parsed results from API calls.

Repositories: JPA repositories for persistence.

Service Layer: business logic for API fetching & parsing.

Factory Pattern:

ApiParser (interface).

JsonPlaceholderParser, OpenWeatherParser, etc. (implementations).

ApiParserFactory selects parser dynamically.

Security: Spring Security + JWT (JwtAuthenticationFilter, JwtUtil, AppUserDetailsService).

⚙️ Setup
Prerequisites

Java 17+

Maven 3+

PostgreSQL 14+

Git

Clone & Build
git clone <repo-url>
cd apigateway
mvn clean install

Configure Database

src/main/resources/application.yml:

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/apigatewaydb
    username: postgres
    password: postgres
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

security:
  jwt:
    secret: "change-this-secret-key-to-at-least-32-characters"
    expiration-ms: 86400000

Run
mvn spring-boot:run


App starts at: https://apigateway-xm21.onrender.com

🔑 Authentication Flow

Register

POST /auth/register

{ "username": "testuser", "password": "password123" }


Login

POST /auth/login


Response:

{ "token": "<jwt-token>" }


Use JWT

Authorization: Bearer <jwt-token>

📡 API Endpoints
Auth

POST /auth/register – Register new user.

POST /auth/login – Login & get JWT.

API URLs

POST /api/urls – Save API URL.

{ "url": "https://jsonplaceholder.typicode.com/todos/1",
  "description": "Sample Todo API" }


GET /api/urls – List saved URLs for user.

GET /api/urls/{id}/fetch – Fetch, parse & save response.

Dashboard

GET /dashboard/responses – List all stored responses for the user.

🛠️ Factory Pattern Explanation

Why Factory? Different APIs return different JSON structures.

Solution: Factory decides parser at runtime.

Example:

ApiParser parser = ApiParserFactory.getParser(apiUrl.getUrl());
Object parsed = parser.parse(rawResponse);


Parsers:

JsonPlaceholderParser – handles jsonplaceholder APIs.

OpenWeatherParser – handles weather APIs.

(Easily extendable with more parsers).

This shows abstraction (interface), inheritance (implementations), and polymorphism (runtime parser selection).

📊 Example Payloads
Save URLs
{ "url": "https://jsonplaceholder.typicode.com/comments/2", "description": "Sample Comment" }

{ "url": "https://api.coindesk.com/v1/bpi/currentprice.json", "description": "Bitcoin Price" }

{ "url": "https://api.open-meteo.com/v1/forecast?latitude=35&longitude=139&hourly=temperature_2m", "description": "Tokyo Weather" }

☁️ Deployment

Deployed to Render/Railway/Fly.io with PostgreSQL.


✅ Checklist

 JWT-secured authentication

 PostgreSQL persistence

 Save + Fetch + Parse APIs

 Factory Pattern usage

 Cloud deployment

 GitLab README with theory
