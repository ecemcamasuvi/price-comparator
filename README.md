# Price Comparator for MacBook Air M2

## Overview

This project is a comprehensive Spring Boot-based REST API application designed to aggregate and compare the prices of the MacBook Air M2 across multiple e-commerce websites. It utilizes a variety of modern technologies to ensure efficient data processing, messaging, and caching.

## Key Features

- **Price Aggregation**: Collects hourly price data for the MacBook Air M2 from various e-commerce sites in real-time.
- **Price Comparison**: Analyzes and compares prices to present users with the best available deal.
- **Data Storage**: Uses MongoDB for storing price data and Redis for caching to improve performance.

## Technology Stack

- **Backend**: **Spring Boot** - Provides the foundation for building the REST API.
- **Messaging**: **Kafka** - Handles asynchronous messaging for communication between different components.
- **Cache**: **Redis** - Caches frequently accessed data to speed up response times and reduce database load.
- **Database**: **MongoDB** - Stores price data and historical records for future reference.
- **Unit Testing**: **JUnit** and **Mockito** - Ensure robust testing of individual components and their interactions.
- **Containerization**: **Docker** - Simplifies the deployment and management of the application in different environments.

## Project Goals

The primary objective of this application is to:

1. **Collect Prices**: Fetch price data for the MacBook Air M2 from the following sites:
   - MediaMarkt
   - Teknosa
   - Pazarama

2. **Compare Prices**: Evaluate the collected data to find the lowest price available.

3. **Provide Best Price**: Present the user with the best deal based on the most recent data.

## Design Patterns

- **Factory Pattern**: Used to create different scraper instances for each e-commerce site, making the system extensible and maintainable.

## Getting Started

To get started with this project:

1. **Clone the Repository**:
    ```bash
    [git clone [https://github.com/your-username/price-comparison-service.git](https://github.com/ecemcamasuvi/price-comparator)](https://github.com/ecemcamasuvi/price-comparator.git)
    ```

2. **Navigate to the Project Directory**:
    ```bash
    cd price-comparator
    ```

3. **Build the Application**:
    ```bash
    mvn clean package -DskipTests
    ```

4. **Run the Application with Docker**:
    ```bash
    docker-compose up --build
    ```

5. **Access the API**:
   - Base URL: `http://localhost:8080`
   - Endpoints for fetching and comparing prices are available.
     
## Sample Requests

[<img src="https://run.pstmn.io/button.svg" alt="Run In Postman" style="width: 128px; height: 32px;">](https://god.gw.postman.com/run-collection/25664358-8bf964a6-4e63-4775-9180-2f0c01e70755?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D25664358-8bf964a6-4e63-4775-9180-2f0c01e70755%26entityType%3Dcollection%26workspaceId%3D4b09edc9-3ef3-44e2-9165-28997933f584)

### Success Response Format

```json
{
    "resultCode": "success",
    "errorMessage": null,
    "data": {
        "productUrl": "https://www.pazarama.com/apple-macbook-air-13-m2-cip-8-cekirdekli-cpu-8-cekirdekli-gpu-8-gb-bellek-256gb-ssd-gece-yarisi-mly33tu-a-p-194253083382",
        "price": 34271.04
    }
}

```

### Error Response Format

If an error occurs during the processing of a request, the API will return a structured response to help you understand what went wrong. Here is the format of the error response you might encounter:

```json
{
    "resultCode": "An error occurred while calculating the best price: ",
    "errorMessage": null,
    "data": null
}

```

## Configuration

The configuration for MongoDB, Redis, and Kafka can be adjusted in the `application.properties` file. Docker configuration can be found in the `Dockerfile` and `docker-compose.yml`.
