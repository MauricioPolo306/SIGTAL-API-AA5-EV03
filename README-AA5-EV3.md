# SIGTAL API - GA7-220501096-AA5-EV03

## Sistema Integral de Gestión para Taller Automotriz Londoño

API REST desarrollada para el proyecto formativo **SIGTAL (Sistema Integral de Gestión para Taller Automotriz Londoño)**, correspondiente a la evidencia de desempeño:

**GA7-220501096-AA5-EV03 - Diseño y desarrollo de servicios web - proyecto**

---

## 1. Información de la evidencia

**Programa de formación:** Análisis y Desarrollo de Software (ADSO)

**Evidencia:** GA7-220501096-AA5-EV03

**Título:** Diseño y desarrollo de servicios web - proyecto

**Proyecto:** SIGTAL - Sistema Integral de Gestión para Taller Automotriz Electricista Londoño

**Tipo de proyecto:** API REST

**Tecnología principal:** Java + Spring Boot

**Base de datos:** MySQL

**Herramienta de construcción:** Maven

**Control de versiones:** Git

**Repositorio:** GitHub

---

# 2. Descripción del proyecto

SIGTAL es un sistema integral de gestión desarrollado para apoyar los procesos administrativos y operativos del taller automotriz Londoño.

La API REST constituye la capa backend del sistema y permite gestionar información mediante servicios web HTTP.

En esta versión se implementan los servicios necesarios para los módulos que forman parte del alcance actual de la API:

- Autenticación.
- Clientes y Vehículo.
- Inventario.

Los servicios desarrollados permiten realizar operaciones de consulta, registro, actualización y eliminación de información, además de incorporar validaciones y manejo de excepciones.

---

# 5. Tecnologías utilizadas

## Java

Lenguaje utilizado para el desarrollo de la lógica de negocio y los servicios de la API.

**Versión utilizada**: Java 21

## Spring Boot
Framework utilizado para construir la aplicación backend y los servicios REST.

**Versión utilizada**: Spring Boot 3.5.6

## Spring Web
Utilizado para la creación de controladores REST y manejo de solicitudes HTTP.
Spring Data JPA

Utilizado para la comunicación entre la aplicación y la base de datos mediante entidades y repositorios.
Hibernate

Framework utilizado como implementación de JPA para la persistencia de información.

## MySQL

Sistema gestor de base de datos utilizado para almacenar la información del sistema SIGTAL.

## Maven

Herramienta utilizada para:
- Gestionar dependencias.
- Compilar el proyecto.
- Ejecutar pruebas.
- Generar el archivo JAR.
- Ejecutar la aplicación Spring Boot.

## Git

Sistema de control de versiones utilizado para realizar el seguimiento de los cambios realizados en el proyecto.

## GitHub

Plataforma utilizada para alojar el repositorio remoto del proyecto y facilitar el versionamiento del código fuente.

**6. Arquitectura del proyecto**

La API está organizada utilizando una estructura por capas.
Cliente / Frontend
        |
        v
   Controladores
        |
        v
     Servicios
        |
        v
    Repositorios
        |
        v
      MySQL

**7. Estructura principal del proyecto**

La estructura principal del proyecto es:
SIGTAL-API-AA5-EV03
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── sigtal
│   │   │           └── api
│   │   │               ├── controller
│   │   │               ├── service
│   │   │               ├── repository
│   │   │               ├── model
│   │   │               ├── dto
│   │   │               └── exception
│   │   │
│   │   └── resources
│   │       └── application.properties
│   │
│   └── test
│
├── pom.xml
├── README.md
└── .gitignore

**8. Módulos implementados**

**8.1 Autenticación**

El módulo de autenticación permite validar las credenciales de los usuarios registrados en el sistema.
El controlador correspondiente es: AuthController.java

La lógica de negocio se encuentra en: AuthService.java

La información de los usuarios se gestiona mediante:
Usuario.java
UsuarioRepository.java

Funcionalidad principal
- Inicio de sesión.
- Validación de usuario.
- Validación de contraseña.
- Manejo de usuarios inexistentes.
- Respuesta de autenticación.

**9. Clientes y Vehículo**

El módulo permite administrar la información relacionada con los clientes y los vehículos asociados al taller.
Controlador: ClienteVehiculoController.java

Servicio: ClienteVehiculoService.java

Funcionalidades
- Registrar clientes.
- Consultar clientes.
- Actualizar información.
- Eliminar registros.
- Gestionar vehículos asociados.
- Validar información duplicada.
- Validar documentos.
- Validar placas.
- Manejar recursos inexistentes.
Este módulo permite centralizar la información necesaria para relacionar a los clientes del taller con los vehículos que ingresan para recibir servicios.

**10. Inventario**

El módulo Inventario permite administrar los productos utilizados por el taller.
Controlador: InventarioController.java

Servicio: InventarioService.java

Repositorio: InventarioRepository.java

Entidad: Inventario.java

Funcionalidades
- Listar productos.
- Consultar producto por ID.
- Registrar productos.
- Actualizar productos.
- Eliminar productos.
- Validar códigos de productos duplicados.
- Manejar productos inexistentes.

La información administrada incluye datos como:
- Código.
- Nombre.
- Categoría.
- Marca.
- Descripción.
- Cantidad.
- Stock mínimo.
- Precio de compra.
- Precio de venta.
- Proveedor.
- Estado.
- Fecha de registro.

**11. Servicios REST**

Los servicios desarrollados utilizan métodos HTTP de acuerdo con la operación requerida.
Método HTTP	Operación
GET	Consultar información
POST	Registrar información
PUT	Actualizar información
DELETE	Eliminar información

Los endpoints implementados se encuentran organizados por módulo.

**12. Endpoint de autenticación**
Ruta base: /api/auth

El módulo permite realizar las operaciones relacionadas con el inicio de sesión de los usuarios.
Ejemplo de consumo:
POST http://localhost:8080/api/auth/login

La solicitud utiliza información de autenticación del usuario.

**13. Endpoint de Clientes y Vehículo**
Ruta base: /api/clientes-vehiculos

Este conjunto de servicios permite gestionar la información de clientes y vehículos utilizados por el sistema SIGTAL.
Las operaciones implementadas incluyen solicitudes GET, POST, PUT y DELETE de acuerdo con el servicio correspondiente.
14. Endpoint de Inventario
Ruta base:
/api/inventario

Listar productos
GET /api/inventario

Permite consultar los productos registrados en el inventario.
Consultar producto
GET /api/inventario/{id}

Permite consultar un producto específico mediante su identificador.
Registrar producto
POST /api/inventario

Permite registrar un nuevo producto.
Actualizar producto
PUT /api/inventario/{id}

Permite modificar la información de un producto existente.
Eliminar producto
DELETE /api/inventario/{id}

Permite eliminar un producto existente.

**15. Validaciones implementadas**

La API incorpora diferentes validaciones para mantener la integridad de la información.
Entre ellas:
- Validación de usuarios.
- Validación de credenciales.
- Validación de registros existentes.
- Validación de códigos de productos.
- Validación de documentos.
- Validación de placas.
- Validación de recursos inexistentes.
Estas validaciones permiten evitar información duplicada y controlar situaciones incorrectas durante el consumo de los servicios.

**16. Manejo de excepciones**

La aplicación cuenta con un mecanismo global para gestionar excepciones.
Clase principal:
GlobalExceptionHandler.java

También se utilizan excepciones personalizadas como:
RecursoNoEncontradoException.java
SolicitudInvalidaException.java

Cuando un recurso no existe, la API puede responder con un código HTTP:
404 Not Found

Por ejemplo:
{
    "mensaje": "Producto no encontrado con ID: 8",
    "error": "Recurso no encontrado"
}

Esto permite entregar respuestas más claras al consumidor de la API.

**17. Base de datos**

La API utiliza MySQL como sistema gestor de base de datos.
La conexión se configura mediante:
src/main/resources/application.properties

Spring Data JPA y Hibernate permiten realizar las operaciones de persistencia entre la aplicación y la base de datos.
La API fue comprobada mediante solicitudes HTTP y consultas realizadas durante las pruebas del proyecto.
18. Puerto de ejecución
La aplicación se ejecuta localmente utilizando el puerto:
8080

URL base:
http://localhost:8080

Ejemplo:
http://localhost:8080/api/inventario

**19. Requisitos para ejecutar el proyecto**

Para ejecutar el proyecto se requiere tener instalado:
- Java 21 o compatible.
- Maven.
- MySQL.
- Git.
- Un entorno de desarrollo como NetBeans o Visual Studio Code.

**20. Instalación y ejecución**

Clonar el repositorio:
git clone URL_DEL_REPOSITORIO

Ingresar al proyecto:
cd SIGTAL-API-AA5-EV03

Compilar el proyecto:
mvn clean package -DskipTests

Ejecutar la aplicación:
java -jar target/sigtal-api-aa5-ev01-1.0-SNAPSHOT.jar

También puede ejecutarse mediante Maven:
mvn spring-boot:run

Cuando la aplicación se encuentre funcionando, estará disponible en:
http://localhost:8080

**21. Pruebas de los servicios**

Los servicios REST fueron probados utilizando Postman.
Las pruebas realizadas en la evidencia anterior permitieron verificar el funcionamiento de:

**Autenticación**
- Inicio de sesión.
- Validación de credenciales.
- Manejo de usuario inexistente.
- Manejo de contraseña incorrecta.

**Clientes y Vehículo**
- Consulta.
- Registro.
- Actualización.
- Eliminación.
- Validación de documentos duplicados.
- Validación de placas duplicadas.
- Manejo de recursos inexistentes.

**Inventario**
- Consulta de productos.
- Consulta por ID.
- Registro.
- Actualización.
- Eliminación.
- Validación de códigos duplicados.
- Manejo de productos inexistentes.
Las pruebas permitieron comprobar que los endpoints responden de acuerdo con los escenarios definidos para el proyecto.

**22. Versionamiento**

El proyecto utiliza Git como sistema de control de versiones.
El versionamiento permite:

- Registrar cambios.
- Mantener un historial del proyecto.
- Recuperar versiones anteriores.
- Gestionar el código fuente.
- Sincronizar el proyecto con GitHub.

Comandos principales utilizados:

git init
git add .
git commit -m "Primera versión SIGTAL API"
git branch -M main
git remote add origin URL_DEL_REPOSITORIO
git push -u origin main

**23. Repositorio GitHub**

El proyecto será publicado en un repositorio independiente correspondiente a esta evidencia:
SIGTAL-API-AA5-EV03

Repositorio:
PENDIENTE DE COLOCAR URL

Una vez creado el repositorio, esta sección deberá actualizarse con el enlace definitivo.

**27. Autor**

**Aprendiz**: Mauricio José Polo Acosta
**Programa**: Análisis y Desarrollo de Software - ADSO
**Proyecto**: SIGTAL - Sistema Integral de Gestión para Taller Automotriz Electricista Londoño
**Evidencia**: GA7-220501096-AA5-EV03

**Estado del proyecto**

**API**: Implementada
**Autenticación**: Implementada
**Clientes y Vehículo**: Implementado
**Inventario**: Implementado
**Base de datos**: MySQL
**Versionamiento**: Git
**Repositorio**: GitHub
**Puerto**: 8080