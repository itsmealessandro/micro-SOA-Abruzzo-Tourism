package com.microsoa.tripPlanner.services;

import com.microsoa.tripPlanner.models.RestaurantWithMenu;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoa.tripPlanner.models.Event;
import com.microsoa.tripPlanner.models.LocationAvailabilityResponse;

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
  private final LocationAvailabilityService locationAvailabilityService;
  private final ObjectMapper objectMapper = new ObjectMapper();

  // Update constructor to inject EventService
  public TripPlannerService(FoodService foodService, EventService eventService,
      LocationAvailabilityService locationAvailabilityService) {
    this.locationAvailabilityService = locationAvailabilityService;
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

    CompletableFuture<String> weatherAndTrailsFuture = locationAvailabilityService.getOutdoorInfo(location, date);

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
          try {
            result.put("locationAvailabilityResponse", weatherAndTrailsFuture.get());
          } catch (Exception e) {
            logger.error("Failed to get outdoor info: {}", e.getMessage());
            result.put("outdoorInfo", "Unavailable");
          }
          logger.info("Combined food and event data for location: {} on date: {}", location, date);
          System.out.println("############################");
          System.out.println("TOTAL RESULT FROM ALL CALLS");
          String foodResult = result.get("food").toString();
          String eventsResult = result.get("events").toString();
          LocationAvailabilityResponse locAvailResp = null;
          try {
            String json = weatherAndTrailsFuture.get();
            locAvailResp = objectMapper.readValue(json,
                LocationAvailabilityResponse.class);
            result.put("locationAvailabilityResponse", locAvailResp);
          } catch (Exception e) {
            logger.error("Failed to get outdoor info: {}", e.getMessage());
            result.put("locationAvailabilityResponse", null);
          }
          System.out.println("FOOD:");
          System.out.println(foodResult);
          System.out.println("events:");
          System.out.println(eventsResult);
          System.out.println("locationAvailabilityResponse");
          System.out.println(locAvailResp);
          System.out.println("############################");
          return result;
        })
        .exceptionally(ex -> {
          logger.error("Error during trip planning for location: {} on date: {}: {}", location, date, ex.getMessage(),
              ex);
          Map<String, Object> errorResult = new HashMap<>();
          errorResult.put("error", "Failed to plan trip: " + ex.getMessage());
          errorResult.put("food", Collections.emptyList());
          errorResult.put("events", Collections.emptyList());
          errorResult.put("locationAvailabilityResponse", "Unavailable");
          return errorResult;
        });
  }
}
