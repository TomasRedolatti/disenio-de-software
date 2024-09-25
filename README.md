Cursada Diseño de Software 2024 - Ingenieria de Sistemas - UNICEN
## Trabajos Grupo 4
### Integrantes:
- Franco Caraffo
- Gonzalo Librandi
- Luca Pianzola
- Tomás Redolatti

# Tp2 - Ej Integrador

## Base de Datos
Para correr la aplicación, dejamos un archivo 'docker-compose.yml para correr la base de datos MySQL en un contenedor de Docker.
Teniendo instalado docker-desktop, se puede levantar el contenedor  desde la consola con el comando:

**docker compose -f docker-file.yml up**

Para acceder a la base de datos desde otra aplicación como 'WorkBench MySQL', tener en cuenta que el contenedor corre a traves del puerto **3306** y los datos, tanto de usuario como contraseña son **grupo4**.


## Diseño de la resolución
### Diagrama DER:

![Diagrama de entidades y relaciones](https://github.com/user-attachments/assets/eb357e01-ad3f-4c47-9165-62903b3ca5de)

El diagrama de relaciones y entidades, muestra una relacion n a n, donde un Estudiante puede cursar muchas Carreras, y una Carrera puede ser cursada por muchos Estudiantes. En cada relación, tenemos atributos propios.

### Diagrama de Base de Datos:

![Diagrama de la Base de Datos](https://github.com/user-attachments/assets/100ba3dd-346e-4ac9-b8dc-c1639a1f44bc)

En la base de datos, tendremos tres tablas:
- Estudiante; posee toda la información personal de cada estudiante.
- Carrera; solo guarda el nombre y el ID único con el que se identifica cada una de las carreras.
- Inscripcion; esta tabla guarda las diversas instancias de las relaciones entre Estudiantes y Carreras. Esto lo hace guardando las FK de Estudiante y de Carrera. Además almacena los atributos específicos de la relación.

### Diagrama de Clases:

![Diagrama-de-Clases drawio](https://github.com/user-attachments/assets/55e91391-2fee-4207-9108-cb0ff5bd2240)

En el diagrama de clases, tenemos por un lado las clases "Entidades", donde guardamos los atributos para almacenar en la base de datos y para relacionar las distintas entidades. 
Por otro lado, tenemos las clases que forman parte del patron DAO utilizado para abtraer la interacción con la Base de Datos. En estas últimas, aplicamos los patrones Singleton y Factory.
