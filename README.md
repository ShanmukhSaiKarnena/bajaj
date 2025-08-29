# Bajaj Finserv Health - Hiring Application

## Overview
This Spring Boot application implements the JAVA qualifier task for Bajaj Finserv Health hiring process. The application automatically:

1. Generates a webhook by sending a POST request on startup
2. Solves SQL problems based on registration number (odd/even logic)
3. Submits the solution using JWT authentication

## Features
- **Automatic Execution**: Runs the complete workflow on application startup
- **No REST Controllers**: Pure command-line runner implementation
- **JWT Authentication**: Uses access token for solution submission
- **Dual SQL Logic**: Handles both odd and even registration number scenarios
- **Comprehensive Logging**: Detailed logs for debugging and monitoring

## Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── com/example/hiring_app1/
│   │       ├── dto/
│   │       │   ├── WebhookRequest.java      # Request DTO for webhook generation
│   │       │   ├── WebhookResponse.java     # Response DTO from webhook API
│   │       │   └── SolutionRequest.java     # Request DTO for solution submission
│   │       ├── service/
│   │       │   ├── WebhookService.java      # Handles API communications
│   │       │   └── SqlSolutionService.java  # Solves SQL problems
│   │       ├── runner/
│   │       │   └── HiringAppRunner.java     # Main workflow executor
│   │       └── HiringApp1Application.java   # Spring Boot main class
│   └── resources/
│       └── application.properties           # App configuration
```

## Configuration
The application uses the following default user details (can be modified in `HiringAppRunner.java`):
- **Name**: John Doe
- **Registration Number**: REG12347
- **Email**: john@example.com

## API Endpoints Used
1. **Webhook Generation**: `POST https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA`
2. **Solution Submission**: `POST https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA`

## SQL Problem Logic
- **Odd Registration Numbers**: Executes Question 1 SQL solution
- **Even Registration Numbers**: Executes Question 2 SQL solution

The application determines the question type by extracting the last two digits from the registration number.

## Dependencies
- Spring Boot 3.5.5
- Spring WebFlux (for reactive HTTP client)
- Jackson (for JSON processing)
- SLF4J (for logging)

## Build and Run

### Prerequisites
- Java 17 or higher
- Maven 3.6+ (wrapper included)

### Building the Application
```bash
# Compile the project
./mvnw compile

# Create JAR file
./mvnw package

# Skip tests if needed
./mvnw package -DskipTests
```

### Running the Application
```bash
# Run directly with Maven
./mvnw spring-boot:run

# Or run the JAR file
java -jar target/hiring-app1-0.0.1-SNAPSHOT.jar
```

## Expected Flow
1. Application starts and logs initialization
2. Sends POST request to generate webhook with user details
3. Receives webhook URL and access token
4. Determines question type based on registration number
5. Generates appropriate SQL solution
6. Submits solution with JWT authentication
7. Logs completion status

## Logging
The application provides detailed logging at INFO level for:
- Workflow progress
- API request/response status
- SQL solution generation
- Error handling

Debug logging is enabled for HTTP client operations.

## Customization
To modify user details, edit the constants in `HiringAppRunner.java`:
```java
private static final String USER_NAME = "Your Name";
private static final String REG_NO = "Your Registration Number";
private static final String EMAIL = "your.email@example.com";
```

To update SQL solutions, modify the methods in `SqlSolutionService.java`:
- `solveQuestion1()` for odd registration numbers
- `solveQuestion2()` for even registration numbers

## Error Handling
The application includes comprehensive error handling for:
- Network connectivity issues
- API response parsing errors
- Authentication failures
- General runtime exceptions

All errors are logged with stack traces for debugging purposes.

## Notes
- The application automatically shuts down after completing the workflow
- No web server is started (web-application-type=none)
- Uses WebClient for reactive HTTP operations
- JWT token is passed in Authorization header as required

## Author
Built for Bajaj Finserv Health hiring assessment.
