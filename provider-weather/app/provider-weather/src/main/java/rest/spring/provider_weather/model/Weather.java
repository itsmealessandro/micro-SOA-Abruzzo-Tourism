package rest.spring.provider_weather.model;

import java.util.Date;

public class Weather {
  private String location;
  private Date date; // MUST USE FOR XML CONVERSION
  private String conditions;
  private double temperature;
  private double humidity;
  private double windSpeed;
  private boolean suitableForTrails;
  private String recommendation;

  public Weather() {
  }

  public Weather(String location, Date date, String conditions,
      double temperature, double humidity,
      double windSpeed, boolean suitableForTrails,
      String recommendation) {
    this.location = location;
    this.date = date;
    this.conditions = conditions;
    this.temperature = temperature;
    this.humidity = humidity;
    this.windSpeed = windSpeed;
    this.suitableForTrails = suitableForTrails;
    this.recommendation = recommendation;
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

  public String getConditions() {
    return conditions;
  }

  public void setConditions(String conditions) {
    this.conditions = conditions;
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

  public boolean isSuitableForTrails() {
    return suitableForTrails;
  }

  public void setSuitableForTrails(boolean suitableForTrails) {
    this.suitableForTrails = suitableForTrails;
  }

  public String getRecommendation() {
    return recommendation;
  }

  public void setRecommendation(String recommendation) {
    this.recommendation = recommendation;
  }
}
