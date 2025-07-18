package com.soa.clientGui.model;

import java.util.List;

public class LocationAvailabilityResponse {
  private String location;
  private String date;
  private List<TrailAvailability> trails;
  private Weather weather;

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

  @Override
  public String toString() {
    return "LocationAvailabilityResponse [location=" + location + ", date=" + date + ", trails=" + trails
        + ", weather=" + weather + ", getLocation()=" + getLocation()
        + ", getDate()=" + getDate() + ", getTrails()=" + getTrails() + ", getWeather()=" + getWeather() + "]";
  }
}
