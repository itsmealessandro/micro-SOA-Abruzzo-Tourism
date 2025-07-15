package com.microsoa.tripPlanner.services;

import com.microsoa.tripPlanner.models.RestaurantWithMenu;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException; // New import for error handling

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FoodService {

    private static final Logger logger = LoggerFactory.getLogger(FoodService.class);

    // Inject RestTemplate instead of creating new instance
    private final RestTemplate restTemplate;
    // URL should ideally be in application.properties
    private static final String FOOD_URL = "http://provider-food:8083/food/restaurants-with-menu?location={location}"; // Using localhost for testing. Replace with "http://provider-food:8083" for Docker/K8s.

    public FoodService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async // Makes this method run in a separate thread, freeing the calling thread
    public CompletableFuture<List<RestaurantWithMenu>> getRestaurantsWithMenu(String location) {
        logger.info("Attempting to fetch restaurants for location: {} from {}", location, FOOD_URL);
        try {
            ResponseEntity<List<RestaurantWithMenu>> response = restTemplate.exchange(
                FOOD_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RestaurantWithMenu>>() {},
                location
            );

            List<RestaurantWithMenu> restaurants = response.getBody();
            if (restaurants != null) {
                logger.info("Successfully fetched {} restaurants for location: {}", restaurants.size(), location);
            } else {
                logger.warn("Received null body for restaurants for location: {}", location);
            }
            return CompletableFuture.completedFuture(restaurants);

        } catch (RestClientException e) {
            logger.error("Error fetching restaurants from {}: {}", FOOD_URL, e.getMessage(), e);
            // Return a CompletableFuture completed exceptionally
            return CompletableFuture.failedFuture(new RuntimeException("Failed to fetch food data: " + e.getMessage(), e));
        } catch (Exception e) {
            logger.error("An unexpected error occurred while fetching restaurants: {}", e.getMessage(), e);
            return CompletableFuture.failedFuture(new RuntimeException("An unexpected error occurred: " + e.getMessage(), e));
        }
    }
}