package com.example.providerevents.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Event {
    @Id
    private String id;
    private String title;
    private String description;
    private String location;
    private LocalDate date;
    
    @Enumerated(EnumType.STRING)
    private EventType type;

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public EventType getType() { return type; }
    public void setType(EventType type) { this.type = type; }
}