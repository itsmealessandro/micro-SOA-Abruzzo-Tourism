package com.example.locationavailability.service;

import com.example.locationavailability.model.LocationAvailabilityResponse;
import com.example.locationavailability.model.TrailAvailability;
import com.example.locationavailability.model.WeatherInfo;
import org.springframework.stereotype.Service;

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
      // Avvia chiamate asincrone
      CompletableFuture<List<TrailAvailability>> trailsFuture = CompletableFuture
          .supplyAsync(() -> trailServiceClient.getTrailsByLocation(location));

      CompletableFuture<WeatherInfo> weatherFuture = CompletableFuture
          .supplyAsync(() -> weatherServiceClient.getWeatherForecast(location, date));

      // Aspetta entrambe le risposte
      CompletableFuture.allOf(trailsFuture, weatherFuture).get();

      // Recupera risultati
      List<TrailAvailability> trails = trailsFuture.get();
      WeatherInfo weather = weatherFuture.get();

      return buildResponse(location, date, trails, weather);

    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException("Error processing availability", e);
    }
  }

  private LocationAvailabilityResponse buildResponse(
      String location,
      String date,
      List<TrailAvailability> trails,
      WeatherInfo weather) {

    // Costruisci risposta
    LocationAvailabilityResponse response = new LocationAvailabilityResponse();
    response.setLocation(location);
    response.setDate(date);
    response.setTrails(trails);
    response.setWeather(weather);

    System.out.println("############################################");
    System.out.println("COMPLETED RESPONSE");
    System.out.println(response);
    System.out.println("############################################");

    return response;
  }
}
