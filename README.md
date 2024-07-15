Desarrollar una aplicación web simple para la gestión de tareas. La aplicación debe permitir a
los usuarios crear, leer, actualizar y eliminar tareas (CRUD). El backend debe ser desarrollado
en Java con Spring Boot.

Spring Boot JPA + H2

REQUISITOS:

● Banco de datos en memoria. H2.

● Proceso de build vía Gradle.

● Persistencia con JPA.

● Framework SpringBoot.

● Java 8+, en este proyecto se uso java 17.

● Patron de Diseño Utilizado: MVC.

Estructura del Proyecto:

![image](https://github.com/fhernandez204/task/blob/master/estructura%20del%20proyecto.png)


ENDPOINTS:

http://localhost:8080/api/tasks

CREAR TAREA
 http://localhost:8080/api/tasks

![image](https://github.com/fhernandez204/task/blob/master/create.jpg)

OBTENER TODAS LAS POR ID

  http://localhost:8080/api/tasks

 ![image](https://github.com/fhernandez204/task/blob/master/getAll.jpg)

OBTENER TAREA POR ID

  http://localhost:8080/api/tasks/{id}

 ![image](https://github.com/fhernandez204/task/blob/master/getById.jpg)


 MODIFICAR TAREA

 http://localhost:8080/api/task/{id}

 ![image](https://github.com/fhernandez204/task/blob/master/update.jpg)


 ELIMINAR TAREA

 http://localhost:8080/api/task/{id}

 ![image](https://github.com/fhernandez204/task/blob/master/delete1.jpg)

 ![image](https://github.com/fhernandez204/task/blob/master/delete2.jpg)


