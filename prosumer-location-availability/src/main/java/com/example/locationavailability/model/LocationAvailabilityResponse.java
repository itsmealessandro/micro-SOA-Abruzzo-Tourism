package com.example.locationavailability.model;

import lombok.Data;
import java.util.List;

@Data
public class LocationAvailabilityResponse {
    private String location;
    private String date;
    private List<TrailAvailability> trails;
    private WeatherInfo weather;
    private boolean overallAvailability;
}