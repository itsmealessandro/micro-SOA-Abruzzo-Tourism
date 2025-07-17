package com.soa.clientGui.controllers;

import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule; // New import for JavaTimeModule
import com.soa.clientGui.model.TripPlannerResponseDTO;

@Controller
@RequestMapping("/")
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final RestTemplate restTemplate;
    private final String TRIP_PORT;
    private final ObjectMapper objectMapper; // Add ObjectMapper instance

    public HomeController(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper; // Inject ObjectMapper
        // Register JavaTimeModule if not already globally configured in Spring Boot
        // This is crucial for LocalDate/LocalTime serialization/deserialization
        this.objectMapper.registerModule(new JavaTimeModule());
        this.TRIP_PORT = System.getenv("TRIP_PLANNER_PORT") != null ? System.getenv("TRIP_PLANNER_PORT") : "8080";
    }

    @GetMapping("/home")
    public String showHome() {
        return "homepage";
    }

    @PostMapping("/request")
    public String tripRequest(@RequestParam String location,
                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                              Model model) {
        logger.info("Trip request received for location: {} on date: {}", location, date);

        String url = "http://trip-planner:" + TRIP_PORT + "/request";

        MultiValueMap<String, String> formParams = new LinkedMultiValueMap<>();
        formParams.add("location", location);
        formParams.add("date", date.toString());

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, formParams, String.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                // ObjectMapper objectMapper = new ObjectMapper(); // Remove local instance, use injected one
                TripPlannerResponseDTO responseDTO = objectMapper.readValue(response.getBody(), TripPlannerResponseDTO.class);

                model.addAttribute("messageSuccess", responseDTO.getMessage());
                model.addAttribute("food", responseDTO.getFood());
                model.addAttribute("events", responseDTO.getEvents());
                model.addAttribute("requestedLocation", responseDTO.getRequestedLocation());
                model.addAttribute("requestedDate", date);
                model.addAttribute("trailsAvailability", responseDTO.getTrailsAvailability());
            } else {
                logger.error("Trip planner responded with error: {}", response.getStatusCode());
                model.addAttribute("messageError", "Errore nella richiesta al trip planner.");
            }
        } catch (Exception e) {
            logger.error("Errore durante il parsing della risposta JSON: {}", e.getMessage(), e);
            model.addAttribute("messageError", "Errore nella comunicazione con il trip planner.");
        }

        return "homepage";
    }
}