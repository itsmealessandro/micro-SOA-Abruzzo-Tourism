package rest.spring.provider_events.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule; // To handle LocalDate/LocalTime
import org.springframework.stereotype.Service;
import rest.spring.provider_events.model.Event;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private List<Event> events;

    public EventService() {
        loadEventsFromJson();
    }

    private void loadEventsFromJson() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // Register module for Java 8 Date/Time API
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("events.json")) {
            if (is != null) {
                events = mapper.readValue(is, new TypeReference<List<Event>>() {});
            } else {
                events = List.of(); // Empty list fallback
                System.err.println("events.json not found in classpath. Initializing with empty list.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            events = List.of();
            System.err.println("Error loading events.json: " + e.getMessage());
        }
    }

    public List<Event> getEventsByLocationAndDate(String location, LocalDate date) {
        return events.stream()
                .filter(event -> event.getLocation().equalsIgnoreCase(location) && event.getDate().isEqual(date))
                .collect(Collectors.toList());
    }

    public List<Event> getEventsByLocation(String location) {
        return events.stream()
                .filter(event -> event.getLocation().equalsIgnoreCase(location))
                .collect(Collectors.toList());
    }
}