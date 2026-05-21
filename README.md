# Hostel Complaint System (Spring Boot + H2 + Thymeleaf)

## Features
- Register (stores users in SQL database)
- Login (session-based using Spring Security)
- Add / Edit / Delete / List complaints (each complaint linked to the creator)
- Frontend: simple HTML/CSS/JS using Thymeleaf templates
- Uses H2 in-memory SQL database by default (easy to run). Instructions to switch to MySQL included below.

## Run (with Maven / IntelliJ)
1. Import the project in IntelliJ as a Maven project.
2. Run `com.example.hostel.HostelComplaintSystemApplication` or:
   ```bash
   mvn spring-boot:run
   ```
3. Open browser:
   - App: http://localhost:8080/register  (create user)
   - H2 console: http://localhost:8080/h2-console
     - JDBC URL: `jdbc:h2:mem:hosteldb`, user `sa`, no password

## Switch to MySQL
Edit `src/main/resources/application.properties`:
```
spring.datasource.url=jdbc:mysql://localhost:3306/hosteldb?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
```
Add MySQL dependency in `pom.xml`:
```xml
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <scope>runtime</scope>
</dependency>
```

## Notes
- Passwords are stored hashed with BCrypt.
- This is a starter project — you can extend validation, UI, and features (roles, admin panel, file uploads, etc).
