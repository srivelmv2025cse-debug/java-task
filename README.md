# Student Management REST API

Spring Boot REST API with model, service, and controller layers.

## Endpoints

- `GET /api/students` - get all students
- `GET /api/students/{id}` - get one student by id
- `POST /api/students` - create a student

Example request:

```json
{
  "name": "Ava Smith",
  "age": 20,
  "email": "ava.smith@example.com"
}
```

## Run

```bash
mvn spring-boot:run
```

The API runs on `http://localhost:8080`.
