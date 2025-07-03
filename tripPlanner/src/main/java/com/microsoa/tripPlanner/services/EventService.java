
package com.microsoa.tripPlanner.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

import java.util.Map;
import java.util.HashMap;


@Service
public class EventService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String EVENTS_URL = "http://provider-events:8080/events?location={location}&date={date}";

    @Async
    public CompletableFuture<String> getEvents(String location, String date) {
        String response = restTemplate.getForObject(EVENTS_URL, String.class, location, date);
        return CompletableFuture.completedFuture(response);
    }
}
