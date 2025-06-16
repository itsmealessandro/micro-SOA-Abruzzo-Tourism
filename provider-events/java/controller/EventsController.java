package com.example.providerevents.controller;

import com.example.providerevents.model.Event;
import com.example.providerevents.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventsController {

    private final EventService eventService;

    @Autowired
    public EventsController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getEvents(
            @RequestParam String location,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        
        return eventService.findEvents(location, startDate, endDate);
    }

    @GetMapping("/type/{eventType}")
    public List<Event> getEventsByType(
            @PathVariable String eventType,
            @RequestParam String location) {
        
        return eventService.findByTypeAndLocation(eventType, location);
    }
}