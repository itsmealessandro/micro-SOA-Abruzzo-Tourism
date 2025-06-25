package rest.spring.provider_weather.services;

import java.time.LocalDate;
import java.util.Set;

import rest.spring.provider_weather.exceptions.ImpossibleToPredictWeatherException;
import rest.spring.provider_weather.exceptions.LocationNotFoundException;
import rest.spring.provider_weather.model.Weather;

public interface WeatherService {

  Set<String> getAllLocations();

  Weather getWeatherByLocationAndDate(String location, LocalDate localDate)
      throws ImpossibleToPredictWeatherException, LocationNotFoundException;
}
