# Fibonacci API

## Descripción  
API REST en **Java 21 + Spring Boot 3** que calcula el n-ésimo número de Fibonacci, lo almacena en **MySQL** como caché y registra estadísticas de consultas para analizar la frecuencia de uso.  

**Documentación completa:** Disponible en la carpeta **Documentación**, ubicada en la raíz del proyecto.  

## Tecnologías Utilizadas  
- **Java 21**  
- **Spring Boot 3**  
- **MySQL**  
- **Spring Data JPA**  
- **Maven**  
- **JUnit 5 + Mockito**  
- **Docker + Docker Compose**  
- **AWS Elastic Beanstalk + RDS**  

## Arquitectura  
Esta API sigue una arquitectura monolítica en capas:  
1. **Controller**: Maneja las solicitudes HTTP.  
2. **Business Logic**: Contiene la lógica del cálculo.  
3. **Data Access**: Interactúa con MySQL.  

## Instalación y Uso  

### **Requisitos**  
- **Java 21**  
- **Maven**  
- **Docker** (Opcional)  

### **Ejecución Local**  
Para ejecutar la API de manera local, es necesario tener configurada la base de datos **MySQL** y completar los datos de conexión en el archivo **application.properties**, ubicado en:  
`\LaBancaPrueba\fibonacci-api\src\main\resources\`  

**Configuración en `application.properties`**  
```properties
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/{yourDatabaseName}?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username={yourMySQLUser}
spring.datasource.password={yourMySQLPassword}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```

**Reemplaza `{yourDatabaseName}`, `{yourMySQLUser}` y `{yourMySQLPassword}`** con los valores correspondientes a tu configuración local.  

Una vez configurado, ejecutar los siguientes comandos:  
```sh
mvn clean install
mvn spring-boot:run
```
Esto iniciará la API en `http://localhost:8080`.

---