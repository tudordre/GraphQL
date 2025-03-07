# README

This project showcases a basic Spring Boot application with GraphQL integration.

## Prerequisites

- Java 17 or higher
- Gradle 8.5 or higher

## Setup

1. Clone the repository:
    ```bash
   git clone <repository-url>
   cd <repository-directory>
    ```

2. Build the project:  
    ```bash
   ./gradlew build
    ```
3. Run the application:  
    ```bash
    ./gradlew bootRun
    ```
4. The application will be running at `http://localhost:8080`.

### API Endpoints
GET http://localhost:8080/client

#### Example Response

```json
    {
    [
      {
        "id": "book-1",
        "name": "Effective Java",
        "pageCount": 416,
        "authorId": null
      },
      {
        "id": "book-2",
        "name": "Hitchhiker's Guide to the Galaxy",
        "pageCount": 208,
        "authorId": null
      },
      {
        "id": "book-3",
        "name": "Down Under",
        "pageCount": 436,
        "authorId": null
      }
    ]
    }
```

GET http://localhost:8080/client/{id}
#### Example Response

```json
    {
      "id": "book-1",
      "name": "Effective Java",
      "pageCount": 416,
      "authorId": null
    }
```

## Dependencies
The project uses the following dependencies:  
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- H2 Database
- Httpclient5
- Jackson-Databind