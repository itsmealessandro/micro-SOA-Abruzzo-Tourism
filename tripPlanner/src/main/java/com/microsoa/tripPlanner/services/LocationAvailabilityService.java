
package com.microsoa.tripPlanner.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

import java.util.Map;
import java.util.HashMap;

@Service
public class LocationAvailabilityService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String LOCATION_AVAILABILITY_URL = "http://locationAvailability:8080/availability";

    @Async
    public CompletableFuture<String> getOutdoorInfo(String location, String date) {
        var payload = new LocationRequest(location, date);
        String response = restTemplate.postForObject(LOCATION_AVAILABILITY_URL, payload, String.class);
        return CompletableFuture.completedFuture(response);
    }

    static class LocationRequest {
        public String location;
        public String date;

        public LocationRequest(String location, String date) {
            this.location = location;
            this.date = date;
        }

        public LocationRequest() {}
    }
}
