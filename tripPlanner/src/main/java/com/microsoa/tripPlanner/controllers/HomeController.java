package com.microsoa.tripPlanner.controllers;

import com.microsoa.tripPlanner.models.RestaurantWithMenu;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoa.tripPlanner.models.Event; // NEW IMPORT
import com.microsoa.tripPlanner.models.LocationAvailabilityResponse;
import com.microsoa.tripPlanner.services.TripPlannerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* INFO:
 * This Controller is responsible for receiving a request from the client web application
 * and return to him the data in a JSON file
 * */
@Controller
@RequestMapping("/")
public class HomeController {

  private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
  @Autowired
  private ObjectMapper objectMapper;

  private final TripPlannerService tripPlannerService;

  public HomeController(TripPlannerService tripPlannerService) {
    this.tripPlannerService = tripPlannerService;
  }

  @PostMapping("/request")
  public CompletableFuture<ResponseEntity<Map<String, Object>>> tripRequest(@RequestParam String location,
      @RequestParam LocalDate date) {
    System.out.println("#######################");
    System.out.println("Request received from clientGUI");
    System.out.println("#######################");
    logger.info("Trip request received for location: {} on date: {}", location, date);

    return tripPlannerService.planTrip(location, date)
        .thenApply(tripData -> handleSuccess(tripData, location, date))
        .exceptionally(ex -> handleError(ex, location, date));
  }

  private ResponseEntity<Map<String, Object>> handleSuccess(Map<String, Object> tripData, String location,
      LocalDate date) {
    List<RestaurantWithMenu> food = objectMapper.convertValue(
        tripData.get("food"),
        new TypeReference<List<RestaurantWithMenu>>() {
        });

    List<Event> events = objectMapper.convertValue(
        tripData.get("events"),
        new TypeReference<List<Event>>() {
        });

    LocationAvailabilityResponse locationAvailability = objectMapper.convertValue(
        tripData.get("locationAvailabilityResponse"),
        LocationAvailabilityResponse.class);

    Map<String, Object> response = new HashMap<>();
    response.put("food", food);
    response.put("events", events);
    response.put("message", "Ecco i risultati della tua pianificazione!");
    response.put("requestedLocation", location);
    response.put("requestedDate", date);
    response.put("locationAvailabilityResponse", locationAvailability);

    logger.info("Trip data successfully processed for location: {} on date: {}", location, date);
    return ResponseEntity.ok(response);
  }

  private ResponseEntity<Map<String, Object>> handleError(Throwable ex, String location, LocalDate date) {
    logger.error("Error during trip planning for location: {} on date: {}: {}", location, date, ex.getMessage(), ex);

    Map<String, Object> errorResponse = new HashMap<>();
    errorResponse.put("message", "Errore durante la pianificazione del viaggio: " + ex.getMessage());
    errorResponse.put("requestedLocation", location);
    errorResponse.put("requestedDate", date);

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
  }
}
