package com.soa.clientGui.model;

import java.util.List;

import com.soa.clientGui.model.food.Event;
import com.soa.clientGui.model.food.Restaurant;

public class TripPlannerResponseDTO {
  private String requestedLocation;
  private String requestedDate;
  private String message;
  private List<Restaurant> food;
  private List<Event> events;
  private TrailsAvailability trailsAvailability;

  // Getters and Setters
  public String getRequestedLocation() {
    return requestedLocation;
  }

  public void setRequestedLocation(String requestedLocation) {
    this.requestedLocation = requestedLocation;
  }

  public String getRequestedDate() {
    return requestedDate;
  }

  public void setRequestedDate(String requestedDate) {
    this.requestedDate = requestedDate;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<Restaurant> getFood() {
    return food;
  }

  public void setFood(List<Restaurant> food) {
    this.food = food;
  }

  public List<Event> getEvents() {
    return events;
  }

  public void setEvents(List<Event> events) {
    this.events = events;
  }

  public TrailsAvailability getTrailsAvailability() {
    return trailsAvailability;
  }

  public void setTrailsAvailability(TrailsAvailability trailsAvailability) {
    this.trailsAvailability = trailsAvailability;
  }

  @Override
  public String toString() {
    return "TripPlannerResponseDTO [requestedLocation=" + requestedLocation + ", requestedDate=" + requestedDate
        + ", message=" + message + ", food=" + food + ", events=" + events + ", trailsAvailability="
        + trailsAvailability + ", getRequestedLocation()=" + getRequestedLocation() + ", getRequestedDate()="
        + getRequestedDate() + ", getMessage()=" + getMessage() + ", getFood()=" + getFood() + ", getEvents()="
        + getEvents() + ", getTrailsAvailability()=" + getTrailsAvailability() + "]";
  }
}
