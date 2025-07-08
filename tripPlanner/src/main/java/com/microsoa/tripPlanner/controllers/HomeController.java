package com.microsoa.tripPlanner.controllers;

import com.microsoa.tripPlanner.models.RestaurantWithMenu;
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
                                                 @RequestParam LocalDate date, // Spring automatically parses yyyy-MM-dd
                                                 Model model) {
        logger.info("Trip request received for location: {} on date: {}", location, date);

        return tripPlannerService.planTrip(location)
            .thenApply(tripData -> {
                // This block runs when tripData is available (asynchronously)
                List<RestaurantWithMenu> food = (List<RestaurantWithMenu>) tripData.get("food");
                model.addAttribute("food", food);
                model.addAttribute("message", "Ecco i ristoranti con i piatti locali!");
                logger.info("Trip data successfully processed for location: {}", location);
                return "homepage"; // Return the view name
            })
            .exceptionally(ex -> {
                // This block runs if an exception occurs in the CompletableFuture chain
                logger.error("Error during trip planning for location: {}", location, ex);
                model.addAttribute("message", "Errore durante la pianificazione del viaggio: " + ex.getMessage());
                // In case of error, you might want to clear previous food results
                model.addAttribute("food", null);
                return "homepage"; // Return the view even on error, displaying the error message
            });
    }
}