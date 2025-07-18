package rest.spring.provider_trails.model;

import java.util.List;

public class Trail {
  private String id;
  private String name;
  private String location;
  private double length; // in km
  private int difficulty; // 1-5
  private List<String> features;
  private WeatherAdaptability weatherAdaptability;

  // Costruttore
  public Trail(String id, String name, String location, double length,
      int difficulty, List<String> features) {
    this.id = id;
    this.name = name;
    this.location = location;
    this.length = length;
    this.difficulty = difficulty;
    this.features = features;
  }

  // Getters e Setters
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

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

  public double getLength() {
    return length;
  }

  public void setLength(double length) {
    this.length = length;
  }

  public int getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
  }

  public List<String> getFeatures() {
    return features;
  }

  public void setFeatures(List<String> features) {
    this.features = features;
  }

  public WeatherAdaptability getWeatherAdaptability() {
    return weatherAdaptability;
  }

  public void setWeatherAdaptability(WeatherAdaptability weatherAdaptability) {
    this.weatherAdaptability = weatherAdaptability;
  }

  @Override
  public String toString() {
    return "Trail [id=" + id + ", name=" + name + ", location=" + location + ", length=" + length + ", difficulty="
        + difficulty + ", features=" + features + ", weatherAdaptability=" + weatherAdaptability + ", getId()="
        + getId() + ", getName()=" + getName() + ", getLocation()=" + getLocation() + ", getLength()=" + getLength()
        + ", getDifficulty()=" + getDifficulty() + ", getFeatures()=" + getFeatures() + ", getWeatherAdaptability()="
        + getWeatherAdaptability() + "]";
  }
}
