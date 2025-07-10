package com.microsoa.tripPlanner.controllers;

import com.microsoa.tripPlanner.services.LocationAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private LocationAvailabilityService availabilityService;

    @PostMapping("/check-availability")
    public LocationAvailabilityService.AvailabilityResponse checkAvailability(
            @RequestBody LocationRequest request) {
        return availabilityService.checkAvailability(
            request.getLocation(),
            request.getDate()
        ).join();
    }

    static class LocationRequest {
        private String location;
        private LocalDate date;

        public String getLocation() { return location; }
        public void setLocation(String location) { this.location = location; }
        public LocalDate getDate() { return date; }
        public void setDate(LocalDate date) { this.date = date; }
    }
}
