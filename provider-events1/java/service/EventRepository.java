package com.example.providerevents.service;

import com.example.providerevents.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
    List<Event> findByLocation(String location);
    List<Event> findByLocationAndDateBetween(String location, LocalDate startDate, LocalDate endDate);
    List<Event> findByTypeAndLocation(String type, String location);
}