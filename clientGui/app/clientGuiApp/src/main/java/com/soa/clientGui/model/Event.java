package com.soa.clientGui.model;

import java.time.LocalDate;
import java.time.LocalTime; // To store event time

public class Event {
  private String name;
  private String location;
  private String description;
  private LocalDate date;
  private LocalTime time;
  private String type;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public LocalTime getTime() {
    return time;
  }

  public void setTime(LocalTime time) {
    this.time = time;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Event [name=" + name + ", location=" + location + ", description=" + description + ", date=" + date
        + ", time=" + time + ", type=" + type + ", getName()=" + getName() + ", getLocation()=" + getLocation()
        + ", getDescription()=" + getDescription() + ", getDate()=" + getDate() + ", getTime()=" + getTime()
        + ", getType()=" + getType() + "]";
  }
}
