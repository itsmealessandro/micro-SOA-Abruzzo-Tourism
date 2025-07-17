package com.example.locationavailability.model;

import lombok.Data;

@Data
public class WeatherInfo {
    private String location;
    private String date;
    private String conditions;
    private double temperature;
    private double humidity;
    private double windSpeed;
    private boolean suitableForTrails;
    private String recommendation;
}