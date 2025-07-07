
package com.microsoa.tripPlanner.services;

import java.util.List;
import java.util.Arrays;

import com.microsoa.tripPlanner.models.LocalDish; 


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
    private static final String FOOD_URL = "http://provider-food:8083/food/dishes?region=Chieti";


    @Async
    public CompletableFuture<List<LocalDish>> getFood(String location) {
        LocalDish[] response = restTemplate.getForObject(FOOD_URL, LocalDish[].class, location);
        return CompletableFuture.completedFuture(Arrays.asList(response));
    }
}
