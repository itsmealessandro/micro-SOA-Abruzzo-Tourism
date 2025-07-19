package com.microsoa.tripPlanner.services;

import com.microsoa.tripPlanner.models.RestaurantWithMenu;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FoodService {

    private static final Logger logger = LoggerFactory.getLogger(FoodService.class);

    private final RestTemplate restTemplate;

    // L'URL dovrebbe essere: Gateway_Base_URL + Predicate_Path_for_Food + Specific_Food_Endpoint
    // Basandoci sulla nostra configurazione del Gateway: /food-api/**
    private static final String FOOD_API_BASE_PATH = "/food-api"; // Il prefisso configurato nel Gateway
    private static final String FOOD_SERVICE_ENDPOINT = "/food/restaurants-with-menu"; // L'endpoint del provider-food
    private static final String GATEWAY_BASE_URL = "http://api-gateway-internal:8080"; 

    private static final String FOOD_URL = GATEWAY_BASE_URL + FOOD_API_BASE_PATH + FOOD_SERVICE_ENDPOINT + "?location={location}";

    public FoodService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
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
            return CompletableFuture.failedFuture(new RuntimeException("Failed to fetch food data: " + e.getMessage(), e));
        } catch (Exception e) {
            logger.error("An unexpected error occurred while fetching restaurants: {}", e.getMessage(), e);
            return CompletableFuture.failedFuture(new RuntimeException("An unexpected error occurred: " + e.getMessage(), e));
        }
    }
}