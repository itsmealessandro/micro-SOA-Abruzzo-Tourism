package com.microsoa.tripPlanner.services;

import com.microsoa.tripPlanner.models.Event;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    private final RestTemplate restTemplate;

    // L'URL dovrebbe essere: Gateway_Base_URL + Predicate_Path_for_Events + Specific_Events_Endpoint
    // Basandoci sulla tua configurazione del Gateway: /events-api/**
    private static final String EVENTS_API_BASE_PATH = "/events-api"; // Il prefisso configurato nel Gateway
    private static final String EVENTS_SERVICE_ENDPOINT = "/events/by-location-and-date"; // L'endpoint del provider-events
    private static final String GATEWAY_BASE_URL = "http://api-gateway-internal:8080"; 
    

    private static final String EVENTS_URL = GATEWAY_BASE_URL + EVENTS_API_BASE_PATH + EVENTS_SERVICE_ENDPOINT + "?location={location}&date={date}";


    public EventService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    public CompletableFuture<List<Event>> getEventsByLocationAndDate(String location, LocalDate date) {
        logger.info("Attempting to fetch events for location: {} on date: {} from {}", location, date, EVENTS_URL);
        try {
            ResponseEntity<List<Event>> response = restTemplate.exchange(
                EVENTS_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Event>>() {},
                location,
                date.toString() // Convert LocalDate to String for URL parameter
            );

            List<Event> events = response.getBody();
            if (events != null && !events.isEmpty()) {
                logger.info("Successfully fetched {} events for location: {} on date: {}", events.size(), location, date);
            } else {
                logger.info("No events found for location: {} on date: {}", location, date);
                events = Collections.emptyList(); // Ensure it's never null
            }
            return CompletableFuture.completedFuture(events);

        } catch (RestClientException e) {
            logger.error("Error fetching events from {}: {}", EVENTS_URL, e.getMessage(), e);
            return CompletableFuture.failedFuture(new RuntimeException("Failed to fetch event data: " + e.getMessage(), e));
        } catch (Exception e) {
            logger.error("An unexpected error occurred while fetching events: {}", e.getMessage(), e);
            return CompletableFuture.failedFuture(new RuntimeException("An unexpected error occurred: " + e.getMessage(), e));
        }
    }
}