package rest.spring.provider_events.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rest.spring.provider_events.model.Event;
import rest.spring.provider_events.service.EventService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/by-location-and-date")
    public List<Event> getEventsByLocationAndDate(
            @RequestParam String location,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) { // ISO.DATE for YYYY-MM-DD
        return eventService.getEventsByLocationAndDate(location, date);
    }

    @GetMapping("/by-location")
    public List<Event> getEventsByLocation(@RequestParam String location) {
        return eventService.getEventsByLocation(location);
    }
}