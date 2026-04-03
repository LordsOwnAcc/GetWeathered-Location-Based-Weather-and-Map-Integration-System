package com.astratech.getweathered.ApiCalling;


import com.astratech.getweathered.Model.WeatherApiResponse;
import com.astratech.getweathered.Model.WeatherResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    private final WebClient webClient;

    public WeatherService(WebClient webClient) {
        this.webClient = webClient;
    }

    public WeatherResponse getWeather(String city) {

        String geoUrl = "https://geocoding-api.open-meteo.com/v1/search?name=" + city;

        Map<String, Object> geo = webClient.get()
                .uri(geoUrl)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        List<Map<String, Object>> results = (List<Map<String, Object>>) geo.get("results");

        if (results == null || results.isEmpty()) {
            throw new RuntimeException("City not found");
        }

        double lat = (double) results.get(0).get("latitude");
        double lon = (double) results.get(0).get("longitude");

        // 🔹 STEP 2: Call Open-Meteo API
        WeatherApiResponse weather = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("api.open-meteo.com")
                        .path("/v1/forecast")
                        .queryParam("latitude", lat)
                        .queryParam("longitude", lon)
                        .queryParam("hourly",
                                "temperature_2m,relative_humidity_2m,dew_point_2m,apparent_temperature,rain,showers,precipitation_probability,precipitation")
                        .build())
                .retrieve()
                .bodyToMono(WeatherApiResponse.class)
                .block();

        // 🔹 STEP 3: Build Final Response
        WeatherResponse res = new WeatherResponse();
        res.setLatitude(lat);
        res.setLongitude(lon);
        res.setTemperature(weather.getHourly().getTemperature_2m().get(0));
        res.setHumidity(weather.getHourly().getRelative_humidity_2m().get(0));

        return res;
    }
}
