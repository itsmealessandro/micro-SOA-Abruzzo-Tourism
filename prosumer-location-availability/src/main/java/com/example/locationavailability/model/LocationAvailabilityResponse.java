package com.example.locationavailability.model;

import java.util.List;

public class LocationAvailabilityResponse {
  private String location;
  private String date;
  private List<TrailAvailability> trails;
  private Weather weather;
  private boolean overallAvailability;

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public List<TrailAvailability> getTrails() {
    return trails;
  }

  public void setTrails(List<TrailAvailability> trails) {
    this.trails = trails;
  }

  public Weather getWeather() {
    return weather;
  }

  public void setWeather(Weather weather) {
    this.weather = weather;
  }

  public boolean isOverallAvailability() {
    return overallAvailability;
  }

  public void setOverallAvailability(boolean overallAvailability) {
    this.overallAvailability = overallAvailability;
  }

  @Override
  public String toString() {
    return "LocationAvailabilityResponse [location=" + location + ", date=" + date + ", trails=" + trails
        + ", weather=" + weather + ", overallAvailability=" + overallAvailability + ", getLocation()=" + getLocation()
        + ", getDate()=" + getDate() + ", getTrails()=" + getTrails() + ", getWeather()=" + getWeather() + "]";
  }
}
