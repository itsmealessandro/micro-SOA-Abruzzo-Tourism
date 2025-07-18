package com.example.locationavailability.service;

import com.example.locationavailability.model.LocationAvailabilityResponse;
import com.example.locationavailability.model.Trail;
import com.example.locationavailability.model.TrailAvailability;
import com.example.locationavailability.model.Weather;
import com.example.locationavailability.model.WeatherAdaptability;
import com.example.locationavailability.model.WeatherCondition;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class AvailabilityService {

  private final TrailServiceClient trailServiceClient;
  private final WeatherServiceClient weatherServiceClient;

  public AvailabilityService(
      TrailServiceClient trailServiceClient,
      WeatherServiceClient weatherServiceClient) {
    this.trailServiceClient = trailServiceClient;
    this.weatherServiceClient = weatherServiceClient;
  }

  // Versione sincrona per comunicazione tra prosumer
  public LocationAvailabilityResponse checkAvailabilitySync(String location, String date) {
    try {
      System.out.println("START ASYNCH CALLS");
      // Avvia chiamate asincrone
      CompletableFuture<List<Trail>> trailsFuture = CompletableFuture
          .supplyAsync(() -> trailServiceClient.getTrailsByLocation(location));

      CompletableFuture<Weather> weatherFuture = CompletableFuture
          .supplyAsync(() -> weatherServiceClient.getWeatherForecast(location, date));

      // Aspetta entrambe le risposte
      System.out.println("WAIT ALL CALLS");
      CompletableFuture.allOf(trailsFuture, weatherFuture).get();
      System.out.println("ALL CALLS ARRIVED");

      // Recupera risultati
      List<Trail> trails = trailsFuture.get();
      Weather weather = weatherFuture.get();

      System.out.println("building response...");
      return buildResponse(location, date, trails, weather);

    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException("Error processing availability", e);
    }

  }

  private LocationAvailabilityResponse buildResponse(
      String location,
      String date,
      List<Trail> trails,
      Weather weather) {

    List<TrailAvailability> trailAvailabilities = calculateTrailsAvailability(trails, weather);
    // Costruisci risposta
    LocationAvailabilityResponse response = new LocationAvailabilityResponse();
    response.setLocation(location);
    response.setDate(date);
    response.setTrails(trailAvailabilities);
    response.setWeather(weather);

    System.out.println("############################################");
    System.out.println("COMPLETED RESPONSE TO SEND");
    System.out.println(response);
    System.out.println("############################################");

    return response;

  }

  private List<TrailAvailability> calculateTrailsAvailability(List<Trail> trails, Weather weather) {
    List<TrailAvailability> result = new ArrayList<>();

    WeatherCondition condition = weather.getWeatherCondition();
    double temperature = weather.getTemperature();
    double wind = weather.getWindSpeed();

    for (Trail trail : trails) {
      boolean recommended = true;

      // Forte vento: nessun trail raccomandato
      if (wind > 30) {
        recommended = false;
      }

      // Temperature estreme
      if (temperature > 35 || temperature < 0) {
        recommended = trail.getWeatherAdaptability() == WeatherAdaptability.COPERTO;
      }

      // Condizioni meteo specifiche
      if (condition == WeatherCondition.NEVE || condition == WeatherCondition.PIOGGIA) {
        recommended = (trail.getWeatherAdaptability() == WeatherAdaptability.COPERTO ||
            trail.getWeatherAdaptability() == WeatherAdaptability.IMPERMEABILE);
      } else {
        // Condizioni buone ma trail inadatto
        if (trail.getWeatherAdaptability() == WeatherAdaptability.FANGOSO) {
          recommended = false;
        }
      }

      TrailAvailability ta = new TrailAvailability();
      ta.setTrail(trail);
      ta.setReccommanded(recommended);
      result.add(ta);
    }

    return result;
  }

}
