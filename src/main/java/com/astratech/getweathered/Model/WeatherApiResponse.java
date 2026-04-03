package com.astratech.getweathered.Model;

import lombok.Data;
import java.util.List;

@Data
public class WeatherApiResponse {
    private Hourly hourly;

    @Data
    public static class Hourly {
        private List<Double> temperature_2m;
        private List<Integer> relative_humidity_2m;
    }
}
