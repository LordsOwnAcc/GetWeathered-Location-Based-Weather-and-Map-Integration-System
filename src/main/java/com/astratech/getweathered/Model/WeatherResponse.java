package com.astratech.getweathered.Model;

import lombok.Data;
import lombok.Data;

@Data
public class WeatherResponse {
    private double latitude;
    private double longitude;
    private double temperature;
    private int humidity;
}