package com.microsoa.tripPlanner.controllers;

import com.microsoa.tripPlanner.models.RestaurantWithMenu;
import com.microsoa.tripPlanner.models.Event; // NEW IMPORT
import com.microsoa.tripPlanner.services.TripPlannerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/")
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final TripPlannerService tripPlannerService;

    public HomeController(TripPlannerService tripPlannerService) {
        this.tripPlannerService = tripPlannerService;
    }

    @GetMapping("/home")
    public String showHome() {
        return "homepage";
    }

    @PostMapping("/request")
    public CompletableFuture<String> tripRequest(@RequestParam String location,
                                                 @RequestParam LocalDate date, // Spring automatically parses YYYY-MM-DD
                                                 Model model) {
        logger.info("Trip request received for location: {} on date: {}", location, date);

        // Pass date to planTrip
        return tripPlannerService.planTrip(location, date)
            .thenApply(tripData -> {
                // This block runs when tripData (containing both food and events) is available
                List<RestaurantWithMenu> food = (List<RestaurantWithMenu>) tripData.get("food");
                List<Event> events = (List<Event>) tripData.get("events"); // NEW: Get events data

                model.addAttribute("food", food);
                model.addAttribute("events", events); // NEW: Add events to model
                model.addAttribute("message", "Ecco i risultati della tua pianificazione!");
                model.addAttribute("requestedLocation", location); // Add location to model for display
                model.addAttribute("requestedDate", date); // Add date to model for display
                logger.info("Trip data successfully processed for location: {} on date: {}", location, date);
                return "homepage"; // Return the view name
            })
            .exceptionally(ex -> {
                logger.error("Error during trip planning for location: {} on date: {}: {}", location, date, ex.getMessage(), ex);
                model.addAttribute("message", "Errore durante la pianificazione del viaggio: " + ex.getMessage());
                // In case of error, you might want to clear previous food/event results
                model.addAttribute("food", null);
                model.addAttribute("events", null);
                model.addAttribute("requestedLocation", location); // Still show what was requested
                model.addAttribute("requestedDate", date);
                return "homepage"; // Return the view even on error, displaying the error message
            });
    }
}