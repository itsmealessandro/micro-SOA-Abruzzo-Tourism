package com.example.locationavailability.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.locationavailability.model.LocationAvailabilityResponse;
import com.example.locationavailability.service.AvailabilityService;

@RestController
@RequestMapping("/availability")
public class AvailabilityController {
    
    private final AvailabilityService availabilityService;
    
    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }
    
    /**
     * Controlla la disponibilit  di una localit  per una certa data.
     * La richiesta deve contenere la localit  e la data in formato ISO-8601 (AAA-MM-GG).
     * La risposta contiene la disponibilit  complessiva per la localit  e la data richieste.
     * @param request La richiesta contenente la localit  e la data.
     * @return La risposta contenente la disponibilit  complessiva.
     */
    @PostMapping("/sync")
    public LocationAvailabilityResponse checkAvailabilitySync(
        @RequestBody AvailabilityRequest request
    ) {
        return availabilityService.checkAvailabilitySync(
            request.getLocation(), 
            request.getDate()
        );
    }
    @GetMapping("/test")
    public String testEndpoint() {
    return "Availability Service is UP!";
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