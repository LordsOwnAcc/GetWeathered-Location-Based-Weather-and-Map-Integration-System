# GetWeathered: Location-Based Weather System

GetWeathered is a Spring Boot application that provides current weather data for any specified city. It functions by first converting a city name into geographical coordinates and then using those coordinates to fetch weather information from the Open-Meteo API.

## How It Works

The service operates in a two-step process to deliver weather data:

1.  **Geocoding**: When a request with a city name is received, the application first calls the Open-Meteo Geocoding API (`https://geocoding-api.open-meteo.com/v1/search`) to retrieve the latitude and longitude for that city.

2.  **Weather Forecasting**: Using the obtained latitude and longitude, the application then makes a second call to the Open-Meteo Forecast API (`https://api.open-meteo.com/v1/forecast`). It requests hourly data for temperature and relative humidity.

3.  **Response**: The service processes the API response to extract the most recent temperature and humidity data and returns it in a simple JSON format, along with the city's coordinates.

## API Endpoint

The application exposes a single REST endpoint to retrieve weather data.

### Get Weather by City

-   **URL**: `/api/weather`
-   **Method**: `GET`
-   **Query Parameter**:
    -   `city` (string, required): The name of the city for which to fetch weather data.

#### Example Request

```sh
curl http://localhost:8080/api/weather?city=Tokyo
```

#### Example Response

```json
{
    "latitude": 35.6895,
    "longitude": 139.69171,
    "temperature": 15.3,
    "humidity": 78
}
```

## Technologies Used

-   **Java**: The core programming language.
-   **Spring Boot**: Framework for creating stand-alone, production-grade Spring-based Applications.
-   **Spring WebFlux**: Used for its reactive `WebClient` to make non-blocking API calls.
-   **Gradle**: The build automation tool used for the project.
-   **Lombok**: A Java library to reduce boilerplate code for model/data objects.

## Getting Started

Follow these instructions to get a local copy of the project up and running.

### Prerequisites

-   Java Development Kit (JDK) 17 or later.
-   An internet connection to fetch dependencies and connect to external APIs.

### Installation & Running

1.  **Clone the repository:**
    ```sh
    git clone https://github.com/LordsOwnAcc/GetWeathered-Location-Based-Weather-and-Map-Integration-System.git
    cd GetWeathered-Location-Based-Weather-and-Map-Integration-System
    ```

2.  **Run the application using the Gradle wrapper:**

    On macOS/Linux:
    ```sh
    ./gradlew bootRun
    ```

    On Windows:
    ```sh
    gradlew.bat bootRun
    ```

The application will start on `http://localhost:8080`. You can now make requests to the API endpoint as described above.
