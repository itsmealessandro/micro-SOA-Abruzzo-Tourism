package com.soa.clientGui.model;

import java.util.List;

import com.soa.clientGui.model.food.Restaurant;

public class TripPlannerResponseDTO {
  private String requestedLocation;
  private String requestedDate;
  private String message;
  private List<Restaurant> food;
  private List<Event> events;
  private LocationAvailabilityResponse locationAvailabilityResponse;

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

  public LocationAvailabilityResponse getLocationAvailabilityResponse() {
    return locationAvailabilityResponse;
  }

  public void setLocationAvailabilityResponse(LocationAvailabilityResponse locationAvailabilityResponse) {
    this.locationAvailabilityResponse = locationAvailabilityResponse;
  }

  @Override
  public String toString() {
    return "TripPlannerResponseDTO [requestedLocation=" + requestedLocation + ", requestedDate=" + requestedDate
        + ", message=" + message + ", food=" + food + ", events=" + events + ", locationAvailabilityResponse="
        + locationAvailabilityResponse + ", getRequestedLocation()=" + getRequestedLocation() + ", getRequestedDate()="
        + getRequestedDate() + ", getMessage()=" + getMessage() + ", getFood()=" + getFood() + ", getEvents()="
        + getEvents() + ", getLocationAvailabilityResponse()=" + getLocationAvailabilityResponse() + "]";
  }

}
