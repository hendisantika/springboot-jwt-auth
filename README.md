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

- Java 21 or higher
- Maven 3.6+
- MySQL 8.0+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

## Setup Instructions

### 1. Clone the Repository

```bash
git clone <repository-url>
cd springboot-jwt-auth
```

### 2. Database Configuration

Create a MySQL database and update the connection properties in `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/jwt_auth_db
spring.datasource.username=your_username
spring.datasource.password=your_password
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

### 3. Build and Run

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

- **Lombok not working**: Ensure annotation processing is enabled in your IDE
- **Database connection failed**: Check MySQL is running and credentials are correct
- **Token validation failed**: Verify secret key configuration matches

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