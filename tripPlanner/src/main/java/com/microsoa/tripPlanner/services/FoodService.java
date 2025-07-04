
package com.microsoa.tripPlanner.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.time.LocalDate;


import java.util.Map;
import java.util.HashMap;

@Service
public class FoodService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String FOOD_URL = "http://provider-food:8080/food?location={location}&date={date}";

    @Async
    public CompletableFuture<String> getFood(String location, LocalDate date) {
        String response = restTemplate.getForObject(FOOD_URL, String.class, location, date);
        return CompletableFuture.completedFuture(response);
    }
}
