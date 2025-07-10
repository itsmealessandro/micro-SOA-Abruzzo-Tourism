package com.microsoa.tripPlanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync; // IMPORTANT: Enable async processing
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@SpringBootApplication
@EnableAsync // This annotation is crucial for @Async to work
public class TripPlannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripPlannerApplication.class, args);
    }
      @GetMapping("/actuator/health")
    public String healthCheck() {
        return "{\"status\":\"UP\"}";
    }

    @PostMapping("/api/trips/check-availability")
    public String checkAvailability(@RequestBody String request) {
        return "{\"status\":\"UnderConstruction\",\"message\":\"Service being updated\"}";
    }
}