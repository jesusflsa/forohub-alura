# Forohub API

Forohub es una API desarrollada en Java Spring para gestionar foros de discusión. Utiliza diversas librerías y
herramientas de Spring para ofrecer una API segura y robusta.

## Tecnologías Utilizadas

- **Java 17**
- **Maven**
- **Spring Boot**

## Librerías

- **Lombok**
- **Spring Web**
- **Spring Boot DevTools**
- **Spring Data JPA**
- **Flyway Migration**
- **MySQL Driver**
- **Validation**
- **Spring Security**

## Endpoints

### Autenticación

- `POST /login`
    - **Descripción**: Autentica un usuario.
    - **Body**:
      ```json
      {
        "username": "string",
        "password": "string"
      }
      ```
    - **Respuesta**: Retorna un JWT.

### Tópicos

#### Requieren autenticación con JWT Bearer Token

- `GET /topicos`
    - **Descripción**: Retorna la lista de tópicos.
    - **Respuesta**:
      ```json
      [
        {
          "id": "Long",
          "id_usuario": "Long",
          "titulo": "String",
          "mensaje": "String",
          "curso": "String",
          "fecha_creacion": "Date"
        },
        ...
      ]
      ```

- `POST /topicos`
    - **Descripción**: Crea un nuevo tópico.
    - **Body**:
      ```json
      {
        "id_usuario": "Long",
        "titulo": "String",
        "mensaje": "String",
        "curso": "String"
      }
      ```
    - **Respuesta**:
      ```json
      {
        "id": "Long",
        "id_usuario": "Long",
        "titulo": "String",
        "mensaje": "String",
        "curso": "String",
        "fecha_creacion": "Date"
      }
      ```

- `GET /topicos/{id}`
    - **Descripción**: Retorna un tópico específico.
    - **Respuesta**:
      ```json
      {
        "id": "Long",
        "id_usuario": "Long",
        "titulo": "String",
        "mensaje": "String",
        "curso": "String",
        "fecha_creacion": "Date"
      }
      ```

- `PUT /topicos/{id}`
    - **Descripción**: Actualiza un tópico específico.
    - **Body**:
      ```json
      {
        "titulo": "String",
        "mensaje": "String",
        "curso": "String"
      }
      ```
    - **Respuesta**:
      ```json
      {
        "id": "Long",
        "id_usuario": "Long",
        "titulo": "String",
        "mensaje": "String",
        "curso": "String",
        "fecha_creacion": "Date"
      }
      ```

- `DELETE /topicos/{id}`
    - **Descripción**: Elimina un tópico específico.
    - **Respuesta**: No retorna nada.

## Configuración del Proyecto

Para construir y ejecutar este proyecto, asegúrate de tener instalado Java 17 y Maven. Sigue los pasos a continuación:

1. Clona el repositorio:
   ```bash
   git clone https://github.com/jesusflsa/forohub-alura.git
   cd forohub-alura
   ```

2. Configura la base de datos MySQL y actualiza las propiedades en `src/main/resources/application.properties`.
    ```properties
    spring.application.name=forohub
    spring.datasource.url = jdbc:mysql://localhost/forohub_api
    spring.datasource.username=root
    spring.datasource.password=root

    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true

    server.error.include-stacktrace=never

    api.security.secret=123456
   ```

3. Ejecuta las migraciones de Flyway:

    ```bash
    mvn flyway:migrate
    ```
4. Compila y ejecuta la aplicación:
    ```bash
    mvn spring-boot:run
   ```