package com.microsoa.tripPlanner.services;

import com.microsoa.tripPlanner.models.RestaurantWithMenu;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TripPlannerService {

    private static final Logger logger = LoggerFactory.getLogger(TripPlannerService.class);

    private final FoodService foodService;

    public TripPlannerService(FoodService foodService) {
        this.foodService = foodService;
    }

    public CompletableFuture<Map<String, Object>> planTrip(String location) {
        logger.info("Planning trip for location: {}", location);

        // FoodService.getRestaurantsWithMenu is already @Async and returns CompletableFuture
        CompletableFuture<List<RestaurantWithMenu>> foodFuture = foodService.getRestaurantsWithMenu(location);

        return foodFuture.thenApply(foodList -> {
            logger.info("Received food data for location: {}", location);
            Map<String, Object> result = new HashMap<>();
            result.put("food", foodList);
            return result;
        }).exceptionally(ex -> {
            logger.error("Error during trip planning for location: {}", location, ex);
            // Propagate the exception so the controller can handle it
            throw new RuntimeException("Failed to plan trip due to food service error.", ex);
        });
    }
}