## Proyecto Integrador - Backend

Este repositorio contiene el código fuente del backend para un proyecto integrador. El proyecto está desarrollado en Java utilizando el framework Spring Boot.

### Características principales:

- Implementación de servicios RESTful para manejar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) relacionadas con entidades como Pacientes, Odontólogos y Turnos.
- Uso de JPA (Java Persistence API) para interactuar con la base de datos.
- Gestión de excepciones personalizadas para manejar casos como recursos no encontrados o errores en la validación de datos.
- Integración con una base de datos relacional (se requiere configuración de conexión a una base de datos MySQL u otro proveedor de bases de datos compatible).
- Implementación de controladores para manejar las solicitudes HTTP entrantes y enviar las respuestas correspondientes.
- Uso de anotaciones como `@RestController`, `@Service` y `@Repository` para definir componentes y estructurar la aplicación según el patrón de diseño MVC (Modelo-Vista-Controlador).

### Estructura del proyecto:

- `src/main/java`: Contiene el código fuente de la aplicación.
- `src/main/resources`: Contiene archivos de configuración y recursos estáticos.
- `src/test`: Contiene pruebas unitarias para probar la funcionalidad del código.

### Instalación y ejecución:

1. Clona este repositorio en tu máquina local.
2. Importa el proyecto en tu IDE de preferencia.
3. Configura la conexión a la base de datos en el archivo `application.properties`.
4. Ejecuta la aplicación desde tu IDE o utilizando Maven (`mvn spring-boot:run`).
5. La aplicación estará disponible en `http://localhost:8080`.