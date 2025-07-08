package com.microsoa.tripPlanner.services;

import com.microsoa.tripPlanner.models.RestaurantWithMenu;
import com.microsoa.tripPlanner.models.Event;
import org.springframework.stereotype.Service;
import java.time.LocalDate; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;

@Service
public class TripPlannerService {

    private static final Logger logger = LoggerFactory.getLogger(TripPlannerService.class);

    private final FoodService foodService;
    private final EventService eventService; // NEW FIELD

    // Update constructor to inject EventService
    public TripPlannerService(FoodService foodService, EventService eventService) {
        this.foodService = foodService;
        this.eventService = eventService;
    }

    // Update planTrip to take LocalDate
    public CompletableFuture<Map<String, Object>> planTrip(String location, LocalDate date) {
        logger.info("Planning trip for location: {} on date: {}", location, date);

        // Fetch food data asynchronously
        CompletableFuture<List<RestaurantWithMenu>> foodFuture = foodService.getRestaurantsWithMenu(location);

        // Fetch event data asynchronously
        CompletableFuture<List<Event>> eventsFuture = eventService.getEventsByLocationAndDate(location, date); // Pass date

        // Combine both futures
        return CompletableFuture.allOf(foodFuture, eventsFuture)
                .thenApply(ignoredVoid -> { // thenApply is called when all futures complete successfully
                    Map<String, Object> result = new HashMap<>();
                    try {
                        result.put("food", foodFuture.get()); // Get results from completed futures
                    } catch (Exception e) {
                        logger.error("Failed to get food data: {}", e.getMessage());
                        result.put("food", Collections.emptyList()); // Provide empty list on error
                    }
                    try {
                        result.put("events", eventsFuture.get()); // Get results from completed futures
                    } catch (Exception e) {
                        logger.error("Failed to get event data: {}", e.getMessage());
                        result.put("events", Collections.emptyList()); // Provide empty list on error
                    }
                    logger.info("Combined food and event data for location: {} on date: {}", location, date);
                    return result;
                })
                .exceptionally(ex -> {
                    logger.error("Error during trip planning for location: {} on date: {}: {}", location, date, ex.getMessage(), ex);
                    // Propagate the exception or return a partial result/error map
                    Map<String, Object> errorResult = new HashMap<>();
                    errorResult.put("error", "Failed to plan trip due to external service error: " + ex.getMessage());
                    errorResult.put("food", Collections.emptyList());
                    errorResult.put("events", Collections.emptyList());
                    return errorResult;
                });
    }
}