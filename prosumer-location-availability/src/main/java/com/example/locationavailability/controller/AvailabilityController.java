package com.example.locationavailability.controller;

import com.example.locationavailability.model.LocationAvailabilityResponse;
import com.example.locationavailability.service.AvailabilityService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/availability")
public class AvailabilityController {
    
    private final AvailabilityService availabilityService;
    
    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }
    
    @PostMapping("/sync")
    public LocationAvailabilityResponse checkAvailabilitySync(
        @RequestBody AvailabilityRequest request
    ) {
        return availabilityService.checkAvailabilitySync(
            request.getLocation(), 
            request.getDate()
        );
    }
    
    // Classe interna per la richiesta
    public static class AvailabilityRequest {
        private String location;
        private String date;
        
        // Getter e Setter
        public String getLocation() { return location; }
        public void setLocation(String location) { this.location = location; }
        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }
    }
}