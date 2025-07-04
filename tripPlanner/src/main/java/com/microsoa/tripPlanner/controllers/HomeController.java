package com.microsoa.tripPlanner.controllers;

import com.microsoa.tripPlanner.services.TripPlannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        try {
            // Chiamata al servizio
            Map<String, String> tripData = tripPlannerService
                    .planTrip(location, date)
                    .get();

            // Converto le stringhe (separate da virgole) in liste
            List<String> events = List.of(tripData.getOrDefault("events", "").split("\\s*,\\s*"));
            List<String> food = List.of(tripData.getOrDefault("food", "").split("\\s*,\\s*"));
            List<String> outdoor = List.of(tripData.getOrDefault("outdoor", "").split("\\s*,\\s*"));

            // Aggiungo al model per la view
            model.addAttribute("events", events);
            model.addAttribute("food", food);
            model.addAttribute("outdoor", outdoor);
            model.addAttribute("message", "Ecco il tuo piano di viaggio!");

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Errore durante la pianificazione del viaggio.");
        }

        return "homepage";
    }
}
