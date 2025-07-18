package com.example.locationavailability.model;

import java.util.Date;

public class Weather {
  private String location;
  private Date date; // MUST USE FOR XML CONVERSION
  private WeatherCondition weatherCondition;
  private double temperature;
  private double humidity;
  private double windSpeed;

  public Weather() {
  }

  public Weather(String location, Date date, WeatherCondition conditions,
      double temperature, double humidity,
      double windSpeed, boolean suitableForTrails,
      String recommendation) {
    this.location = location;
    this.date = date;
    this.weatherCondition = conditions;
    this.temperature = temperature;
    this.humidity = humidity;
    this.windSpeed = windSpeed;
  }

  // Getters and Setters
  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public double getTemperature() {
    return temperature;
  }

  public void setTemperature(double temperature) {
    this.temperature = temperature;
  }

  public double getHumidity() {
    return humidity;
  }

  public void setHumidity(double humidity) {
    this.humidity = humidity;
  }

  public double getWindSpeed() {
    return windSpeed;
  }

  public void setWindSpeed(double windSpeed) {
    this.windSpeed = windSpeed;
  }

  public WeatherCondition getWeatherCondition() {
    return weatherCondition;
  }

  public void setWeatherCondition(WeatherCondition weatherCondition) {
    this.weatherCondition = weatherCondition;
  }

  @Override
  public String toString() {
    return "Weather [location=" + location + ", date=" + date + ", weatherCondition=" + weatherCondition
        + ", temperature=" + temperature + ", humidity=" + humidity + ", windSpeed=" + windSpeed
        + ", getLocation()="
        + getLocation() + ", getDate()=" + getDate() + ", getTemperature()="
        + getTemperature() + ", getHumidity()=" + getHumidity() + ", getWindSpeed()=" + getWindSpeed() + "]";
  }

}
