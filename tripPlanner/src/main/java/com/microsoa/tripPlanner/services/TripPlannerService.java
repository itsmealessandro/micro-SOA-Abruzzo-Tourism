package com.microsoa.tripPlanner.services;

import java.util.List;
import com.microsoa.tripPlanner.models.LocalDish; 

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class TripPlannerService {

    // private final EventService eventService;
    private final FoodService foodService;
    // private final LocationAvailabilityService locationService;

    public TripPlannerService(
        /* EventService eventService, */
        FoodService foodService
        /*, LocationAvailabilityService locationService */
    ) {
        // this.eventService = eventService;
        this.foodService = foodService;
        // this.locationService = locationService;
    }

    public CompletableFuture<Map<String, Object>> planTrip(String location /*, LocalDate date */) {
        // CompletableFuture<String> eventsFuture = eventService.getEvents(location, date);
        CompletableFuture<List<LocalDish>> foodFuture = foodService.getFood(location);
        // CompletableFuture<String> outdoorFuture = locationService.getOutdoorInfo(location, date);

        return foodFuture.thenApply(foodList -> {
            Map<String, Object> result = new HashMap<>();
            result.put("food", foodList); // oppure .toString() se vuoi un JSON testuale
            return result;
         });
    }
}
