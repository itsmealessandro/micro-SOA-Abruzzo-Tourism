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
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.soa.clientGui.model.TripPlannerResponseDTO;

@Controller
@RequestMapping("/")
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final RestTemplate restTemplate;
    // TRIP_PORT non è più usato direttamente per la chiamata a trip-planner,
    // ma l'API_GATEWAY_URL lo userà internamente.
    // Lo manteniamo solo per coerenza se dovesse servire altrove, altrimenti potresti rimuoverlo.
    private final String TRIP_PORT; // Mantenuto per compatibilità, ma non usato direttamente per l'URL
    private final String API_GATEWAY_URL; // Nuovo campo per l'URL base del Gateway
    private final ObjectMapper objectMapper;

    public HomeController(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        // Registra JavaTimeModule per la corretta serializzazione/deserializzazione di LocalDate/LocalDateTime
        this.objectMapper.registerModule(new JavaTimeModule());

        // Carica la porta del trip-planner (utile se lo chiamassi direttamente o per log)
        this.TRIP_PORT = System.getenv("TRIP_PLANNER_PORT") != null ? System.getenv("TRIP_PLANNER_PORT") : "8090";

        // Costruisci l'URL base per l'API Gateway Interno
        // Usa il nome del servizio Docker del Gateway (api-gateway-internal)
        // e la sua porta, letta da variabile d'ambiente o con un default
        String gatewayPort = System.getenv("API_GATEWAY_INTERNAL_PORT") != null ? System.getenv("API_GATEWAY_INTERNAL_PORT") : "8080";
        this.API_GATEWAY_URL = "http://api-gateway-internal:" + gatewayPort;
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

        // L'URL ora punta all'API Gateway, usando il prefisso definito nel Gateway
        // Ad esempio, "/trip-planner-api" è il prefisso che il Gateway userà per inoltrare
        // le richieste al servizio "trip-planner".
        String url = API_GATEWAY_URL + "/trip-planner-api/request";

        MultiValueMap<String, String> formParams = new LinkedMultiValueMap<>();
        formParams.add("location", location);
        formParams.add("date", date.toString());

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, formParams, String.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                TripPlannerResponseDTO responseDTO = objectMapper.readValue(response.getBody(), TripPlannerResponseDTO.class);

                model.addAttribute("messageSuccess", responseDTO.getMessage());
                model.addAttribute("food", responseDTO.getFood());
                model.addAttribute("events", responseDTO.getEvents());
                model.addAttribute("requestedLocation", responseDTO.getRequestedLocation());
                model.addAttribute("requestedDate", date);
                model.addAttribute("trailsAvailability", responseDTO.getTrailsAvailability());
            } else {
                logger.error("Trip planner responded with error: {} - {}", response.getStatusCode(), response.getBody());
                model.addAttribute("messageError", "Errore nella richiesta al trip planner: " + response.getStatusCode().value());
            }
        } catch (Exception e) {
            logger.error("Errore durante la comunicazione con il trip planner via Gateway: {}", e.getMessage(), e);
            model.addAttribute("messageError", "Errore nella comunicazione con il trip planner. Dettagli: " + e.getMessage());
        }

        return "homepage";
    }
}