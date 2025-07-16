package rest.spring.provider_trails.model;

public class Trail {

  private String name;
  private String location;
  WeatherAdaptability weatherAdaptability;

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

  public WeatherAdaptability getWeatherAdaptability() {
    return weatherAdaptability;
  }

  public void setWeatherAdaptability(WeatherAdaptability weatherAdaptability) {
    this.weatherAdaptability = weatherAdaptability;
  }

  @Override
  public String toString() {
    return "Trail [name=" + name + ", location=" + location + ", weatherAdaptability=" + weatherAdaptability
        + ", getName()=" + getName() + ", getLocation()=" + getLocation() + ", getWeatherAdaptability()="
        + getWeatherAdaptability() + "]";
  }

}
