# TinyURL

TinyURL is a Spring Boot application that provides a simple URL shortening service with statistics tracking.

## Features

- Shorten long URLs to short codes
- Redirect short codes to original URLs
- Track access statistics for each short URL
- MySQL database integration

## Project Structure

- `src/main/java/in/TinyURL/Controllers/UrlShortnerController.java` - REST API controller
- `src/main/java/in/TinyURL/Entities/UrlMapping.java` - Entity for URL mapping
- `src/main/java/in/TinyURL/Repositories/UrlMappingRepository.java` - JPA repository
- `src/main/java/in/TinyURL/Services/UrlShortnerService.java` - Service interface
- `src/main/java/in/TinyURL/Services/Impl/UrlShortnerServiceImpl.java` - Service implementation
- `src/main/java/in/TinyURL/DTOs/ShortUrlRequest.java` - DTO for shorten request
- `src/main/resources/application.properties` - Application configuration

## Getting Started

1. **Clone the repository**
2. **Configure MySQL database** in `src/main/resources/application.properties`
3. **Build and run the application**

```sh
./mvnw spring-boot:run
```

## API Endpoints

### 1. Get Short URL

- **URL:** `POST http://localhost:8080/api/shorten`
- **Body:**
  ```json
  {
    "longUrl": "https://example.com",
    "customAlias": "myalias"
  }
  ```
- **Response:**
  ```json
  {
    "shortUrl": "http://localhost:8083/{shortCode}"
  }
  ```

### 2. Redirect to Original URL

- **URL:** `GET http://localhost:8080/api/{shortCode}`
- **Response:**
  ```json
  {
    "redirectTo": "https://example.com"
  }
  ```

### 3. View Stats

- **URL:** `GET http://localhost:8080/api/stats/{shortCode}`
- **Response:**
  ```json
  {
    "id": 1,
    "longUrl": "https://example.com",
    "shortCode": "myalias",
    "createdAt": "...",
    "accessCount": 5,
    "expiryDate": null
  }
  ```
  ## How to Run the Project

1. **Clone the repository**
   ```sh
   git clone https://github.com/your-username/TinyURL.git
   cd TinyURL
   ```

2. **Configure the MySQL database**
   - Edit `src/main/resources/application.properties` and set your MySQL username, password, and database name.

3. **Install dependencies and build the project**
   ```sh
   mvn clean install
   ```

4. **Run the Spring Boot application**
   ```sh
   mvn spring-boot:run
   ```
   or
   ```sh
   java -jar target/TinyURL-0.0.1-SNAPSHOT.jar
   ```

5. **Access the API**
   - The server will start at `http://localhost:8080`
   - Use the API endpoints listed above with Postman or any HTTP client.


## License

Licensed under the Apache License, Version 2.0.
