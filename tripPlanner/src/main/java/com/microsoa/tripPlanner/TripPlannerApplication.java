package com.microsoa.tripPlanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync; // IMPORTANT: Enable async processing

@SpringBootApplication
@EnableAsync // This annotation is crucial for @Async to work
public class TripPlannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripPlannerApplication.class, args);
    }
}