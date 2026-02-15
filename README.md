# Medical Appointment System

## Thème du projet

**Conception et implémentation d’une architecture microservices sécurisée pour la gestion de rendez-vous médicaux, intégrant une authentification basée sur JWT et une API Gateway.**

Ce projet s’inscrit dans une démarche d’ingénierie logicielle moderne visant à :

- Mettre en œuvre une architecture basée sur les microservices
- Séparer clairement les responsabilités entre les services
- Garantir la sécurité des échanges via des tokens JWT
- Intégrer des pratiques DevSecOps (CI/CD, analyse de sécurité, conteneurisation)

---

## Description du projet

Le projet **Medical Appointment System** est une application backend développée en **Spring Boot 3.5.0**, reposant sur une architecture microservices.

Il permet la gestion simplifiée d’un système de prise de rendez-vous médical, incluant :

- La gestion des utilisateurs (patients, administrateurs)
- L’authentification et l’autorisation via JWT
- La gestion des rendez-vous
- Le contrôle d’accès basé sur les rôles

L’architecture est composée de plusieurs services indépendants :

### Services principaux

- **Authentication Service**  
  Gestion des comptes utilisateurs et génération des tokens JWT.

- **Appointment Service**  
  Gestion des rendez-vous et des disponibilités des médecins.

- **API Gateway**  
  Point d’entrée unique, validation des tokens et routage des requêtes vers les services internes ainsi 
  que la vérification de token.

---

## Objectifs techniques

Ce projet vise à démontrer :

1. La mise en place d’une architecture microservices modulaire
2. L’utilisation de **Spring Security avec authentification JWT**
3. L’intégration d’un **API Gateway** pour centraliser la sécurité
4. La séparation des responsabilités en utilisant une architecture classique MVC
5. L’intégration d’un pipeline CI sécurisé (GitHub Actions, Snyk, Gitleaks, OWASP ZAP, Checkov)
6. La conteneurisation des services via Docker

---

## Architecture technique

| Composant           | Technologie                 |
|---------------------|-----------------------------|
| Backend             | Spring Boot 3.5.0           |
| Sécurité            | Spring Security + JWT       |
| Gateway             | Spring Cloud Gateway        |
| Documentation API   | Springdoc OpenAPI (Swagger) |
| Build               | Maven multi-modules         |
| CI/CD               | GitHub Actions              |
| Analyse de sécurité | Snyk, Gitleaks              |
| Conteneurisation    | Docker / Docker Compose     |
| Frontend            | Angular/Tailwind/DaisyUI    |

---

## Prérequis

Avant de lancer le projet, les outils et environnements suivants doivent être installés et configurés :

### Environnement de développement

- **Java 17 ou supérieur**
- **Maven 3.9+**
- **Docker**
- **Docker Compose**
- **GitHub**

### Outils recommandés

- **IDE** : IntelliJ IDEA ou Visual Studio Code
- **Client API** : Postman ou accès via Swagger UI
- **Snyk Plugin** : Scanner les dépendances en temps réel

### Variables d’environnement (si nécessaire)

Selon la configuration choisie, les variables suivantes peuvent être requises :

- `JWT_SECRET` ou clé RSA
- Paramètres de connexion base de données
- Configuration Docker (fichier `.env`)

---

## How to Run

The project is structured as a multi-module Maven microservices architecture.  
Each service is packaged as a JAR file and deployed using Docker Compose.

### Step 1 — Build the project

From the root directory of the project:

```bash
mvn clean package -DskipTests
```

This command will:

- Compile all modules
- Generate JAR files for each microservice
- Place them inside the corresponding `target/` directories

Ensure that the JAR files are correctly copied or referenced inside the Docker configuration (if required).

---

### Step 2 — Start the microservices with Docker Compose

From the directory docker:

```bash
docker compose up --build
```

This command will:

- Build Docker images for each service
- Create the Docker network
- Start all microservices containers
- Expose the configured ports

---

### Step 3 — Access the services

Once all containers are running:

- API Gateway:  
  http://localhost:9000

- Authentication Service:  
  http://localhost:8081

- Appointment Service:  
  http://localhost:8082

- Swagger UI:  
  - http://localhost:8082/swagger-ui/index.html
  - http://localhost:8081/swagger-ui/index.html

---

### Optional — Stop the services

To stop all containers:

```bash
docker compose down
```

To remove volumes:

```bash
docker compose down -v
```

---

## Execution Flow Overview

1. Maven packages the services into executable JAR files
2. Docker builds images using those JAR files
3. Docker Compose orchestrates all containers
4. API Gateway routes external requests to internal services  

---

## API Documentation

The API documentation is available via Swagger UI.

Once the services are running, you can access the interactive API documentation at:

- Appointment Service:  
  http://localhost:8082/swagger-ui/index.html
- Authentification Service:  
    http://localhost:8081/swagger-ui/index.html

---

## Test Data

This project provides the necessary data to facilitate functional testing.

### Users

Users can be created directly from the front-end:

- Open the login page
- Use the registration feature to create a new account
- Log in with the newly created credentials

### Doctors

Doctor data is provided via a SQL initialization script.

A `data.sql` file is available inside the `data/` directory.  
Import this file into the database before starting the application to automatically generate the required doctor records.

### Appointments

Appointments are not pre-generated.

They can be created directly from the front-end:

- Log in as a registered user
- Browse available doctors
- Select an available time slot
- Confirm the booking
