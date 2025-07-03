package com.microsoa.tripPlanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.microsoa.tripPlanner.services.TripPlannerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.scheduling.annotation.Async;
import java.util.Map;
import java.util.HashMap;

@SpringBootApplication
@EnableAsync
public class TripPlannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripPlannerApplication.class, args);
    }

    @Bean
    public CommandLineRunner runTest(TripPlannerService tripPlannerService) {
        return args -> {
            CompletableFuture<Map<String, String>> result = tripPlannerService.planTrip("Majella", "2025-08-15");
            System.out.println("🧳 Risultato viaggio: " + result.get());
        };
    }
}
