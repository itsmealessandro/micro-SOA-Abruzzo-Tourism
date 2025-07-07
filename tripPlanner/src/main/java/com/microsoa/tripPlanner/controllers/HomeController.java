package com.microsoa.tripPlanner.controllers;

import com.microsoa.tripPlanner.services.TripPlannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.microsoa.tripPlanner.models.LocalDish; 


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController {

    private final TripPlannerService tripPlannerService;

    @Autowired
    public HomeController(TripPlannerService tripPlannerService) {
        this.tripPlannerService = tripPlannerService;
    }

    @GetMapping("/home")
    public String showHome() {
        return "homepage"; // Mostra il file homepage.html in resources/templates
    }

    @PostMapping("/request")
    public String tripRequest(@RequestParam String location,
                              @RequestParam LocalDate date,
                              Model model) {
        System.out.println(">>> LOCATION FORM INPUT: " + location);
        try {
            // Chiamata al servizio
            Map<String, Object> tripData = tripPlannerService
                    .planTrip(location)
                    .get();

            // Converto le stringhe (separate da virgole) in liste
            //List<String> events = List.of(tripData.getOrDefault("events", "").split("\\s*,\\s*"));
            List<LocalDish> food = (List<LocalDish>) tripData.get("food");
            //List<String> outdoor = List.of(tripData.getOrDefault("outdoor", "").split("\\s*,\\s*"));

            // Aggiungo al model per la view
            //model.addAttribute("events", events);
            model.addAttribute("food", food);
            //model.addAttribute("outdoor", outdoor);
            model.addAttribute("message", "Ecco i restoranti possibili!");

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Errore durante la pianificazione del viaggio.");
        }

        return "homepage";
    }
}
