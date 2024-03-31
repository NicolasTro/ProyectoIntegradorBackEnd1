## Proyecto Integrador - Backend

Este proyecto fue realizado como parte del curso Backend I de la carrera __Certified Tech Developer__ de __Digital House__.

El proyecto está desarrollado en Java utilizando el framework Spring Boot.

#### __Integrantes__
- Nicolas Troupkos
- Bautista Sanguinetti

## Tecnologías Utilizadas

- __Java__
- __Spring Boot__
- __H2 DataBase__
- __HTML5__
- __CSS__
- __Bootstrap__
- __JavaScript__
- __jQuery__
- __Postman__

### Instalación y ejecución:

1. Clona este repositorio en tu máquina local.
2. Importa el proyecto en tu IDE de preferencia.
3. Configura la conexión a la base de datos en el archivo `application.properties`.
4. Ejecuta la aplicación desde tu IDE o utilizando Maven (`mvn spring-boot:run`).
5. La aplicación estará disponible en `http://localhost:8080`.

## Tests

Puede ejecutar los test unitarios que se encuentran dentro de la carpeta `test`.

Para visualizar las pruebas realizadas desde Postman:

1. Abrir Postman.
2. Importar el archivo `CRUD .postman_collection.json`.
3. Ejecutar la colección.

### Características principales:

- Implementación de servicios __RESTful__ para manejar operaciones __CRUD__ (CREATE, READ, UPDATE, DELETE) relacionadas con entidades como Pacientes, Odontólogos y Turnos.
- Uso de __JPA__ (Java Persistence API) para interactuar con la base de datos.
- Gestión de excepciones personalizadas para manejar casos como recursos no encontrados o errores en la validación de datos.
- Integración con una base de datos relacional (se requiere configuración de conexión a una base de datos __MySQL__ u otro proveedor de bases de datos compatible).
- Implementación de controladores para manejar las solicitudes __HTTP__ entrantes y enviar las respuestas correspondientes.
- Uso de anotaciones como `@RestController`, `@Service` y `@Repository` para definir componentes y estructurar la aplicación según el patrón de diseño __MVC__ (Model-View-Controller).

## Características adicionales:

- Implementamos __DTO__(Data-Transfer-Object) para pasar informacion entre capas del modelo __MVC__ evitando que las entidades lleguen mas alla de la capa del Service.
- Tambien implementamos Polimorfismo para poder utilizar distintos __DTO__ segun la vista requerida.
- Para darle funcionalidades adicionales al proyecto, implementamos nuestras propias interfaces las cuales personalizamos segun la necesidad de cada servicio asignandole nuevos endpoints a cada una. Como puede ser el endpoint de `/buscar` o `/listarDTO`



### Estructura del proyecto:

- `src/main/java`: Contiene el código fuente de la aplicación.
- `src/main/resources`: Contiene archivos de configuración y recursos estáticos.
- `src/test`: Contiene pruebas unitarias para probar la funcionalidad del código.

### Requerimientos Técnicos

La aplicación debe ser desarrollada en capas:

- Capa de Entidades de Negocio: clases Java de nuestro negocio modelado a través del paradigma orientado a objetos.
- Capa de Acceso a Datos (Repository): clases encargadas de acceder a la base de datos.
- Capa de Datos (Base de Datos): la base de datos del sistema modelada a través de un modelo entidad-relación. Uso de la BD H2 por su practicidad.
- Capa de Negocio: clases service que se encargan de desacoplar el acceso a datos de la vista.
- Capa de Presentación: pantallas web desarrolladas utilizando el framework Spring Boot MVC con los controladores con HTML+JavaScript+Bootstrap.

Además, se requiere el manejo de excepciones logueando cualquier excepción que se pueda generar y la realización de test unitarios para garantizar la calidad de los desarrollos.