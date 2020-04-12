# Bolivia Solidaria - Backend
#### boliviasolidaria.com

Bolivia Solidaria es una plataforma de código abierto que permite a personas con necesidades registrar sus solicitudes y estas puedan ser visualizadas por personas que tienen interes de apoyar a las mismas.

Este modulo corresponde al codigo fuente del lado del servidor en el que se procesa la informacion de las solicitudes, puntos de acopio, receptores de donacion entre otros.


## Construido con

_Las siguientes herramientas se usaron para la implementación de este sistema_

* [Spring Boot](https://spring.io/projects/spring-boot) - Framework para las solicitudes HTTP, logica de negocio y acceso a datos.
* [Maven 3.3.9](https://maven.apache.org/) - Manejador de dependencias
* [Java 8](https://www.java.com) - Plataforma de ejecución

## Artefactos

_Este repositorio consta de los siguientes artefactos_

* **bs-api** Contiene todos los rest controllers para recibir las solicitudes HTTP, configuracion necesaria como aplicacion de Spring y Repositorio de datos.
* **bs-service** Contiene un conjunto de servicios y repositorios responsables de acceder a los datos de la base de datos y procesar la logica de negocio.
* **bs-domain** Contiene un conjunto de utilitarios, entidades de base de datos y DTO's.


### Instalación

El proyecto está implementando usando maven 3.x por lo mismo se debe generar los compilados usando comandos maven.

Construir

```
mvn clean package
```

Construir sin ejecutar los tests

```
mvn clean package -Dmaven.test.skip=true
```

Construir y desplegar en el Wildfly de esta máquina

```
mvn clean package wildfly:deploy
```
El aplicativo esta implementado para correr en Wildfly 13.

## Autores ✒️

_Desarrolladores de esta plataforma_

* **Milton BO** - *Fullstack Developer* - [LinkedIn](https://www.linkedin.com/in/j-milton-chambi-m-a2965482/)
