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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public boolean isSuitableForTrails() {
        return suitableForTrails;
    }

    public void setSuitableForTrails(boolean suitableForTrails) {
        this.suitableForTrails = suitableForTrails;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}
