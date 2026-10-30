# Spring Boot CI/CD Lab

A project focused on building, testing, containerizing, and deploying a Spring Boot REST API through an automated CI/CD pipeline.

pre-requisites
------------------
- **experience with CI/CD workflows**, 
- **containerization**, 
- **Linux server administration**,
- **deployment automation**

## Tech Stack

- **Application:** Java 21, Spring Boot, Maven
- **Testing:** JUnit 5, Spring Boot Test, MockMvc
- **Containerization:** Docker, Docker Compose (planned)
- **CI/CD:** GitHub Actions (planned)
- **Infrastructure:** Ubuntu Linux, Ansible, Nginx (planned)

## Project Structure

```text
src/
├── main/
│   ├── java/com/example/cicdlab/
│   │   ├── controller/
│   │   ├── dto/
│   │   ├── model/
│   │   ├── service/
│   │   └── CicdLabApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/example/cicdlab/
        └── ApiIntegrationTest.java
```

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/health` | Check API availability |
| GET | `/api/tasks` | Retrieve all tasks |
| POST | `/api/tasks` | Create a new task |
| GET | `/actuator/health` | Check application health |

Tasks are currently stored in memory and are cleared when the application restarts.

## Getting Started

### Prerequisites

- Java JDK 21
- Git

Maven does not need to be installed globally because the project includes Maven Wrapper.

### Run the Application

Clone the repository and navigate to the project directory.

```powershell
.\mvnw.cmd spring-boot:run
```

The application will be available at:

`http://localhost:8080`

### Run Automated Tests

```powershell
.\mvnw.cmd clean test
```

The project includes automated tests for API health, task creation, input validation, and task retrieval.

### Build the Application

```powershell
.\mvnw.cmd clean package
```

The executable JAR will be generated inside the `target/` directory.

## API Example

Create a task using PowerShell:

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/tasks" -Method Post -ContentType "application/json" -Body '{"title":"Configure CI/CD pipeline"}'
```

Example response:

```json
{
  "id": 1,
  "title": "Configure CI/CD pipeline",
  "completed": false
}
```

## DevOps Implementation Roadmap

- [x] Develop a Spring Boot REST API
- [x] Implement automated integration tests
- [x] Build an executable application
- [X] Containerize the application using Docker
- [X] Configure Docker Compose
- [X] Automate builds and tests using GitHub Actions
- [ ] Publish versioned images to GitHub Container Registry
- [ ] Configure an Ubuntu deployment environment using Ansible
- [ ] Configure Nginx as a reverse proxy
- [ ] Implement automated deployment and rollback

## Learning Objectives

This project explores how application development integrates with DevOps practices, including:

- Reproducible application builds
- Automated testing and continuous integration
- Container image creation and management
- Linux infrastructure configuration
- Automated application deployment
- Deployment verification and troubleshooting

## Project Status

**Day 1:** Spring Boot REST API implementation and testing completed.

Docker containerization and CI/CD automation are the next development stages.


## Docker Deployment

The application uses a multi-stage Docker build to
separate application compilation from execution.

### Build the Docker Image

docker build -t cicd-lab:v1 .

### Run with Docker Compose

docker compose up -d --build

### Inspect the Running Application

docker compose ps
docker compose logs -f api
docker compose stats

### Stop the Application

docker compose down

## Container Recovery

The Docker Compose configuration uses the
`unless-stopped` restart policy.

Recovery was tested by terminating the Java process
inside the container and verifying that Docker
restarted the application automatically.

The application health endpoint was used to
confirm successful recovery.


## Continuous Integration

GitHub Actions automatically builds and tests the
Spring Boot application when changes are pushed to
main or submitted through a pull request.

### CI Workflow

1. Check out repository source code.
2. Configure Eclipse Temurin JDK 21.
3. Restore cached Maven dependencies.
4. Compile the application and run automated tests.
5. Package the executable JAR.
6. Upload the JAR as a workflow artifact.

### Failure Handling

A failed Maven build or automated test causes the
CI job to fail.

A separate branch was used to demonstrate failure
detection and recovery without intentionally
breaking the main branch.
