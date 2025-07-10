package com.microsoa.tripPlanner.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.concurrent.CompletableFuture;
import java.time.LocalDate;
import com.microsoa.tripPlanner.exceptions.ServiceUnavailableException;

@Service
public class LocationAvailabilityService {

    private final RestTemplate restTemplate;
    private final String serviceUrl;

    public LocationAvailabilityService(
            RestTemplate restTemplate,
            @Value("${services.location-availability.url}") String serviceUrl) {
        this.restTemplate = restTemplate;
        this.serviceUrl = serviceUrl + "/api/v1/availability";
    }

    @Async
    public CompletableFuture<AvailabilityResponse> checkAvailability(String location, LocalDate date) {
        try {
            LocationRequest request = new LocationRequest(location, date);
            ResponseEntity<AvailabilityResponse> response = restTemplate.postForEntity(
                serviceUrl,
                request,
                AvailabilityResponse.class
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new ServiceUnavailableException("Location service returned: " + response.getStatusCode());
            }

            return CompletableFuture.completedFuture(response.getBody());
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    public static class LocationRequest {
        private String location;
        private LocalDate date;

        public LocationRequest(String location, LocalDate date) {
            this.location = location;
            this.date = date;
        }

        public String getLocation() { return location; }
        public void setLocation(String location) { this.location = location; }
        public LocalDate getDate() { return date; }
        public void setDate(LocalDate date) { this.date = date; }
    }

    public static class AvailabilityResponse {
        private String status;
        private String message;

        public AvailabilityResponse() {}

        public AvailabilityResponse(String status, String message) {
            this.status = status;
            this.message = message;
        }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
}
