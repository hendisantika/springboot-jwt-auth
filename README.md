# Spring Boot JWT Authentication

A comprehensive Spring Boot application demonstrating JWT (JSON Web Token) based authentication and authorization.

## Features

- **JWT Authentication**: Secure token-based authentication system
- **User Registration**: New user signup with encrypted password storage
- **User Login**: Authenticate users and generate JWT tokens
- **Protected Endpoints**: Role-based access control for secured resources
- **Spring Security Integration**: Leverages Spring Security for robust authentication
- **MySQL Database**: Persistent storage for user data
- **Lombok Integration**: Reduces boilerplate code with annotations
- **RESTful API**: Clean REST endpoints for authentication operations

## Technology Stack

- **Java 21**: Latest LTS version of Java
- **Spring Boot 3.5.6**: Latest Spring Boot framework
- **Spring Security**: For authentication and authorization
- **Spring Data JPA**: For database operations
- **MySQL**: Relational database for data persistence
- **JWT (JSON Web Token)**: For stateless authentication
- **Lombok**: For reducing boilerplate code
- **Maven**: Build and dependency management
- **Hibernate**: ORM framework

## Project Structure

```
src/main/java/com/hendisantika/springbootjwtauth/
├── config/
│   ├── ApplicationConfiguration.java  # Application beans configuration
│   ├── JwtAuthenticationFilter.java   # JWT filter for request processing
│   └── SecurityConfiguration.java     # Security configuration
├── controller/
│   ├── AuthenticationController.java  # Authentication endpoints
│   └── UserController.java           # User management endpoints
├── dto/
│   ├── LoginUserDto.java             # Login request DTO
│   ├── LoginResponse.java            # Login response DTO
│   └── RegisterUserDto.java          # Registration request DTO
├── entity/
│   └── User.java                     # User entity
├── repository/
│   └── UserRepository.java          # User data access
├── service/
│   ├── AuthenticationService.java   # Authentication business logic
│   ├── JwtService.java              # JWT token operations
│   └── UserService.java             # User management service
└── SpringbootJwtAuthApplication.java # Main application class
```

## Prerequisites

### Local Development
- Java 21 or higher
- Maven 3.6+
- MySQL 8.0+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### Docker Development

- Docker 20.10+
- Docker Compose 2.0+

## Setup Instructions

### Option 1: Docker Setup (Recommended)

#### 1. Clone the Repository

```bash
git clone <repository-url>
cd springboot-jwt-auth
```

#### 2. Run with Docker Compose

```bash
# Start all services (MySQL + Spring Boot App + PhpMyAdmin)
docker-compose up -d

# Check logs
docker-compose logs -f app

# Stop all services
docker-compose down

# Remove volumes (to reset database)
docker-compose down -v
```

The application will be available at:

- **Spring Boot App**: `http://localhost:8081`
- **PhpMyAdmin**: `http://localhost:8080` (Database: `jwt_auth_db`, User: `yu71`, Password: `53cret`)
- **MySQL**: `localhost:3306` (User: `yu71`, Password: `53cret`)

#### 3. Docker Commands

```bash
# Build only the application image
docker build -t jwt-auth-app .

# Run only the application (assuming MySQL is running separately)
docker run -p 8081:8081 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/jwt_auth_db \
  -e SPRING_DATASOURCE_USERNAME=yu71 \
  -e SPRING_DATASOURCE_PASSWORD=53cret \
  jwt-auth-app

# View application logs
docker-compose logs -f app

# Access MySQL container
docker-compose exec mysql mysql -u yu71 -p53cret jwt_auth_db
```

### Option 2: Local Development Setup

#### 1. Clone the Repository

```bash
git clone <repository-url>
cd springboot-jwt-auth
```

#### 2. Database Configuration

Create a MySQL database and update the connection properties in `src/main/resources/application.properties`:

```properties
# Database Configuration for MySQL 9.4.0
spring.datasource.url=jdbc:mysql://localhost:3306/jwt_auth_db
spring.datasource.username=yu71
spring.datasource.password=53cret
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# JWT Configuration
security.jwt.secret-key=your-secret-key-here
security.jwt.expiration-time=86400000

# Server Configuration
server.port=8081
```

#### 3. Build and Run

```bash
# Clean and compile the project
mvn clean compile

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8081`

## API Endpoints

### Authentication Endpoints

#### Register New User

```http
POST /auth/signup
Content-Type: application/json

{
    "fullName": "John Doe",
    "email": "john.doe@example.com",
    "password": "securePassword123"
}
```

#### User Login

```http
POST /auth/login
Content-Type: application/json

{
    "email": "john.doe@example.com",
    "password": "securePassword123"
}
```

**Response:**

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "expiresIn": 86400000
}
```

### Protected Endpoints

#### Get User Profile

```http
GET /users/me
Authorization: Bearer <your-jwt-token>
```

#### Get All Users (Admin)

```http
GET /users
Authorization: Bearer <your-jwt-token>
```

## Security Configuration

- **Password Encryption**: Uses BCrypt for secure password hashing
- **JWT Tokens**: Stateless authentication with configurable expiration
- **CORS**: Cross-Origin Resource Sharing configuration
- **CSRF Protection**: Disabled for stateless REST API
- **Authentication Filter**: Custom JWT filter for token validation

## JWT Token Structure

The JWT token contains:

- **Header**: Algorithm and token type
- **Payload**: User information and claims
- **Signature**: Verification signature

Example payload:

```json
{
  "sub": "user@example.com",
  "iat": 1640995200,
  "exp": 1641081600
}
```

## Error Handling

The application includes comprehensive error handling for:

- Invalid credentials
- Expired tokens
- Malformed requests
- Database connection issues
- Validation errors

## Testing

### Docker Health Checks

```bash
# Check application health
curl http://localhost:8081/actuator/health

# Check database connectivity
docker-compose exec mysql mysqladmin ping -h localhost -u yu71 -p53cret

# View container status
docker-compose ps
```

### Manual Testing with curl

#### Register a new user:

```bash
curl -X POST http://localhost:8081/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Test User",
    "email": "test@example.com",
    "password": "password123"
  }'
```

#### Login:

```bash
curl -X POST http://localhost:8081/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@example.com",
    "password": "password123"
  }'
```

#### Access protected endpoint:

```bash
curl -X GET http://localhost:8081/users/me \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Docker Testing Commands

```bash
# Test with Docker services running
docker-compose up -d

# Wait for services to be healthy
docker-compose ps

# Run the curl commands above
# ...

# Check logs if needed
docker-compose logs app
docker-compose logs mysql

# Clean up
docker-compose down
```

## Configuration Options

### JWT Settings

- `security.jwt.secret-key`: Secret key for signing tokens
- `security.jwt.expiration-time`: Token expiration time in milliseconds

### Database Settings

- Configure connection URL, username, and password
- Adjust JPA/Hibernate properties as needed

### Server Settings

- `server.port`: Application port (default: 8081)

## Development

### Adding New Features

1. Create DTOs for request/response objects
2. Add controller endpoints
3. Implement service layer logic
4. Update security configuration if needed
5. Add appropriate validation

### Common Issues

#### Local Development
- **Lombok not working**: Ensure annotation processing is enabled in your IDE
- **Database connection failed**: Check MySQL is running and credentials are correct
- **Token validation failed**: Verify secret key configuration matches

#### Docker Issues

- **Container won't start**: Check logs with `docker-compose logs [service-name]`
- **Database connection timeout**: Wait for MySQL container to be fully ready (check health status)
- **Port already in use**: Stop existing services or change ports in docker-compose.yml
- **Out of disk space**: Clean up unused Docker resources with `docker system prune -a`
- **MySQL authentication issues**: Ensure MySQL user `yu71` has proper permissions

#### Docker Troubleshooting Commands

```bash
# Check container status
docker-compose ps

# View logs for specific service
docker-compose logs -f app
docker-compose logs -f mysql

# Restart specific service
docker-compose restart app

# Rebuild application image
docker-compose build app

# Reset everything (warning: deletes data)
docker-compose down -v
docker-compose up -d

# Access application container shell
docker-compose exec app sh

# Access MySQL container
docker-compose exec mysql mysql -u yu71 -p53cret jwt_auth_db
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is open source and available under the [MIT License](LICENSE).

## Author

**Hendi Santika**

- Email: hendisantika@gmail.com
- Telegram: @hendisantika34

---

For more information about Spring Boot and JWT authentication, visit:

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [JWT Introduction](https://jwt.io/introduction/)
- [Spring Security Reference](https://docs.spring.io/spring-security/reference/)