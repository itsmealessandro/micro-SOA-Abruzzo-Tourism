package com.example.locationavailability.model;

import java.util.List;

public class LocationAvailabilityResponse {
    private String location;
    private String date;
    private List<TrailAvailability> trails;
    private WeatherInfo weather;
    private boolean overallAvailability;

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

    public List<TrailAvailability> getTrails() {
        return trails;
    }

    public void setTrails(List<TrailAvailability> trails) {
        this.trails = trails;
    }

    public WeatherInfo getWeather() {
        return weather;
    }

    public void setWeather(WeatherInfo weather) {
        this.weather = weather;
    }

    public boolean isOverallAvailability() {
        return overallAvailability;
    }

    public void setOverallAvailability(boolean overallAvailability) {
        this.overallAvailability = overallAvailability;
    }
}
