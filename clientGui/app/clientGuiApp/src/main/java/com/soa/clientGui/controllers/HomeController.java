package com.soa.clientGui.controllers;

import java.net.http.HttpHeaders;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    ResponseEntity<String> response = restTemplate.postForEntity(url, formParams, String.class);

    if (response.getStatusCode().is2xxSuccessful()) {
      try {
        // ObjectMapper per convertire la stringa JSON in una mappa
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> responseData = objectMapper.readValue(response.getBody(), new TypeReference<>() {
        });

        model.addAttribute("messageSuccess", responseData.get("message"));
        model.addAttribute("food", responseData.get("food"));
        model.addAttribute("events", responseData.get("events"));
        model.addAttribute("requestedLocation", responseData.get("requestedLocation"));
        model.addAttribute("requestedDate", responseData.get("requestedDate"));
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
