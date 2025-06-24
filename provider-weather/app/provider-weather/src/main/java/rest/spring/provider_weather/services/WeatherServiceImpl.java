package rest.spring.provider_weather.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import rest.spring.provider_weather.model.Weather;

@Service
public class WeatherServiceImpl implements WeatherService {

  final Set<String> locations = new HashSet<>();

  public WeatherServiceImpl() {

    locations.add("Gran Sasso");
    locations.add("Campo Imperatore");
    locations.add("Majella");
    locations.add("Sirente");
    locations.add("Campotosto");
  }

  @Override
  public Set<String> getAllLocations() {

    return locations;
  }

  @Override
  public Weather getWeatherByLocation(String location) {
    if (locations.contains(location) == false) {
      return null;
    }
    Weather weather = new Weather();
    // TODO

    weather.setLocation(location);
    return weather;
  }

}
