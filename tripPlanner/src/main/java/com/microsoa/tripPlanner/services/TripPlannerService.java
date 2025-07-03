
package com.microsoa.tripPlanner.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import java.util.Map;
import java.util.HashMap;

@Service
public class TripPlannerService {

    private final EventService eventService;
    private final FoodService foodService;
    private final LocationAvailabilityService locationService;

    public TripPlannerService(EventService eventService,
                              FoodService foodService,
                              LocationAvailabilityService locationService) {
        this.eventService = eventService;
        this.foodService = foodService;
        this.locationService = locationService;
    }

    public CompletableFuture<Map<String, String>> planTrip(String location, String date) {
        CompletableFuture<String> eventsFuture = eventService.getEvents(location, date);
        CompletableFuture<String> foodFuture = foodService.getFood(location, date);
        CompletableFuture<String> outdoorFuture = locationService.getOutdoorInfo(location, date);

        return CompletableFuture.allOf(eventsFuture, foodFuture, outdoorFuture)
                .thenApply(voided -> {
                    Map<String, String> result = new HashMap<>();
                    result.put("events", eventsFuture.join());
                    result.put("food", foodFuture.join());
                    result.put("outdoor", outdoorFuture.join());
                    return result;
                });
    }
}
