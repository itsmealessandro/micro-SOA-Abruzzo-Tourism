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
import com.soa.clientGui.model.TripPlannerResponseDTO;

@Controller
@RequestMapping("/")
public class HomeController {

  private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

  private final RestTemplate restTemplate;

  private final String TRIP_PORT;

  public HomeController(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
    this.TRIP_PORT = System.getenv("TRIP_PLANNER_PORT");
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

    System.out.println("###################################################################");
    System.out.println("Preparing POST to send...");
    System.out.println("###################################################################");

    ResponseEntity<String> response = restTemplate.postForEntity(url, formParams, String.class);

    System.out.println("###################################################################");
    System.out.println("Response Received:");
    System.out.println(response.toString());
    System.out.println("###################################################################");

    if (response.getStatusCode().is2xxSuccessful()) {
      try {

        ObjectMapper objectMapper = new ObjectMapper();
        TripPlannerResponseDTO responseDTO = objectMapper.readValue(response.getBody(), TripPlannerResponseDTO.class);

        System.out.println("########################################################################");

        System.out.println(responseDTO);
        System.out.println("########################################################################");

        model.addAttribute("messageSuccess", responseDTO.getMessage());
        model.addAttribute("food", responseDTO.getFood());
        model.addAttribute("events", responseDTO.getEvents());
        model.addAttribute("requestedLocation", responseDTO.getRequestedLocation());
        model.addAttribute("requestedDate", date);
        model.addAttribute("trailsAvailability", responseDTO.getTrailsAvailability()); // TODO: set data values on HTML
      } catch (Exception e) {
        logger.error("Errore durante il parsing della risposta JSON: {}", e.getMessage(), e);
        model.addAttribute("messageError", "Errore nella lettura della risposta dal trip-planner.");
      }

      return "homepage";
    } else {
      model.addAttribute("messageError", "Errore nella richiesta al trip-planner");
      return "homepage";
    }
  }
}
