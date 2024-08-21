
# Test Java SmartJob

Aplicación que expone una API RESTful de creación de usuarios.
## Authors

- [@CristobalValenzuela](https://github.com/CristobalValenzuela)

## Run Locally

Clone the project

```bash
  git clone https://github.com/CristobalValenzuela/test-java-smartjob.git
```

Go to the project directory

```bash
  cd test-java-smartjob
```

Build project

```bash
  ./gradlew build
```

Start the server

```bash
  java -jar ./build/libs/api-users-1.0.0.jar
```

Open Swagger

[http://localhost:8080/users/swagger-ui/index.html](http://localhost:8080/users/swagger-ui/index.html)

## Environment Variables

Yo can modify in application.yml, the default value or set in a container over Kubernetes

| Name                | Description                           |
|:--------------------|:--------------------------------------|
| SERVICE_PORT        | Port for the application              |
| SECRET_KEY_JWT      | Secret for use JWT generation token   |
| EXPIRATION_TIME_JWT | Time of durarion generate token       |
| DB_URL              | Url for data base                     |
| DB_USER             | User for connect to the data base     |
| DB_PASSWORD         | Password for connect to the data base |
| EMAIL_REGEX         | Regexp for validation email           |
| PASSWORD_REGEX      | Regexp for validation password        |

## API Reference

| Method | URL                         | Parameter | Type   | Description               |
|:-------|:----------------------------|:---------:|:-------|:--------------------------|
| GET    | localhost:8080/users/       |           |        | Listar los usuarios       |
| GET    | localhost:8080/users/{uuid} |   uuid    | String | Obtener un usuario por ID |
| POST   | localhost:8080/users/       |           |        | Crear un usuario          |
| PUT    | localhost:8080/users/{uuid} |   uuid    | String | Crear un usuario          |
| DELETE | localhost:8080/users/{uuid} |   uuid    | String | Eliminar un usuario       |

## Postman Collection

[Api User.postman_collection.json](https://raw.githubusercontent.com/CristobalValenzuela/test-java-smartjob/master/Api%20User.postman_collection.json)