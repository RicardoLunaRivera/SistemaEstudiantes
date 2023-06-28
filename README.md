# SISTEMA DE ESTUDIANTES

### Base de datos
>-- CRUD -> CREATE (INCERTAR), READ (LEER/CONSULTAR), UPDATE (ACTUALIZAR) y DELETE (ELIMINAR)

-- READ -> LEER - CONSULTAR

`SELECT * FROM db;`


-- CREATE -> INSERTAR

`INSERT INTO db (arguments) VALUES (values);
`

-- UPDATE -> ACTUALIZAR

`UPDATE db SET values WHERE id = #;
`

-- DELETE -> ELIMINAR

`DELETE FROM db WHERE id = #;`


### Estructura del proyecto
![DiagramaClases.png](src%2Fmain%2Fjava%2Forg%2Fexample%2Fimg%2FDiagramaClases.png)


### Patrón de diseño DAO
>El patrón DAO propone separar por completo la lógica de negocio de la lógica para acceder a los datos,
> de esta forma, el DAO proporcionará los métodos necesarios para insertar, actualizar, 
> borrar y consultar la información; por otra parte, la capa de negocio solo se preocupa por lógica 
> de negocio y utiliza el DAO para interactuar con la fuente de datos.


Los compones que conforman el patrón son:

- **BusinessObject:** representa un objeto con la lógica de negocio.
- **DataAccessObject:** representa una capa de acceso a datos que oculta la fuente y los detalles técnicos para recuperar los datos.
- **TransferObject:** este es un objeto plano que implementa el patrón Data Transfer Object (DTO), el cual sirve para transmitir la información entre el DAO y el Business Service.
- **DataSource:** representa de forma abstracta la fuente de datos, la cual puede ser una base de datos, Webservices, LDAP, archivos de texto, etc.


![sistemaEstudiantes.PNG](src%2Fmain%2Fjava%2Forg%2Fexample%2Fimg%2FsistemaEstudiantes.PNG)


>Esquema de la Base de Datos
> 
> [dbEstudiantes.sql](src%2Fmain%2Fjava%2Forg%2Fexample%2Fdb%2FdbEstudiantes.sql)