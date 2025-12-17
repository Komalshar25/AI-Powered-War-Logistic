# War Logistics Optimizer

This repository contains a simple Spring Boot skeleton for a war logistics optimizer web application.

Structure created to match requested layout; implement services and controllers as needed.
# War Logistics Optimizer

A Spring Boot application for optimizing logistics in war scenarios, providing efficient routing, resource allocation, and safety considerations.

## Features

- User authentication and authorization with JWT
- Logistics management and optimization
- Web-based dashboard for visualization
- RESTful API for integration

## Technologies Used

- Java 17
- Spring Boot 3.3.5
- Spring Security
- Spring Data JPA
- H2 Database
- Thymeleaf
- Maven

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/war-logistics-optimizer.git
   cd war-logistics-optimizer
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Access the application at `http://localhost:8080`

## API Endpoints

- `POST /api/auth/login` - User login
- `POST /api/logistics/create` - Create logistics entry
- `GET /api/logistics/{id}` - Get logistics by ID
- `POST /api/optimization/optimize` - Optimize logistics route

## Configuration

Application properties can be found in `src/main/resources/application.properties`.

## Testing

Run tests with:
```bash
mvn test
```

## Contributing

Please read CONTRIBUTING.md for details on our code of conduct and the process for submitting pull requests.

## License

This project is licensed under the MIT License - see the LICENSE file for details.
