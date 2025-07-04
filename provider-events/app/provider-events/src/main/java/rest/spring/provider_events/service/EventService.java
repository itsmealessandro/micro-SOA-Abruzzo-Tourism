package rest.spring.provider_events.service;

import rest.spring.provider_events.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findEvents(String location, LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return eventRepository.findByLocation(location);
        }
        return eventRepository.findByLocationAndDateBetween(location, startDate, endDate);
    }

    public List<Event> findByTypeAndLocation(String eventType, String location) {
        return eventRepository.findByTypeAndLocation(eventType, location);
    }
}