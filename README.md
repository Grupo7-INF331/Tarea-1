# Tarea 1

## Gestión de Entradas para Micro-Eventos

Proyecto desarrollado en **Java** con **MySQL**.

## Descripción

- Gestión de Eventos (CRUD): Crear, consultar, actualizar y eliminar eventos con nombre, descripción, fecha, categoría, precio y cupos disponibles.

- Filtrado y Búsqueda: Buscar y filtrar eventos por nombre, fecha o categoría.

- Reportes: Ver total de eventos, suma de cupos disponibles y eventos agotados.

- Venta y Devolución de Entradas: Registrar ventas y devoluciones, actualizando cupos automáticamente.

- Autenticación: Acceso protegido con usuario y contraseña; solo usuarios autenticados pueden modificar eventos o registrar ventas/devoluciones.


---

##  Instalación

### Opción 1 RECOMENDADAS: Usando Docker

1. Instalar Docker.  
2. Instalar Java.  

---


##  Ejecución

### Opción 1: Docker

1. Abrir terminal y dirigirse a la carpeta del proyecto:
    ```bash
    cd ../tarea-1/
    ```
2. Levantar los contenedores:
    ```
    docker-compose up -d
    ```
3. Compilar el proyecto Java:
    ```
    javac -cp ".;lib/*" -d out src/app/*.java src/controllers/*.java src/models/*.java src/views/*.java
    ```
4. Ejecutar la aplicación:
    ```
    java -cp ".;lib/*;out" app.App
    ```
5. Interactuar con terminal
  Contestar en la terminal segun lo que se vaya mostrando y eligiendo las opciones de lo que quiere hacer

## Consideraciones

- El contenedor donde contiene la BD se demora en cargar los primeros datos, asi que espere antes de usar los pasos 3 y 4 un momento para que de tiempo a que se ejecuten correctamente los contenedores.

---


## Contribuciones


1. **Fork** este repositorio.  
2. **Clona** tu fork en tu máquina local:  
   ```bash
   git clone https://github.com/Grupo7-INF331/Tarea-1.git
---

3. Crea una branch y subela al github
   
## Licencia


Este proyecto está bajo la licencia [MIT](LICENSE) (o la que corresponda).

## Comentario acerca de GreenTest

Existe un bug, cuando uno desea cambiar el nombre de una prueba. En particular, si quieres cambiar, por ejemplo, el principio del nombre del test, apenas uno escribe, el cursor se vuelve a colocar al final de la palabra.
