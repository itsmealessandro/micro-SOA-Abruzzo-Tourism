package rest.spring.provider_weather.services;

import java.util.Set;

import rest.spring.provider_weather.model.Weather;

public interface WeatherService {

  Set<String> getAllLocations();

  Weather getWeatherByLocation(String location);
}
