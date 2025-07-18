package com.microsoa.tripPlanner.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.time.LocalDate;

@Service
public class LocationAvailabilityService {

  private final RestTemplate restTemplate = new RestTemplate();
  private static final String LOCATION_AVAILABILITY_URL = "http://location-availability:8091/availability/trails-and-weather";

  @Async
  public CompletableFuture<String> getOutdoorInfo(String location, LocalDate date) {
    System.out.println("#####################################");
    System.out.println("Request data to locationAvailability");
    System.out.println("#####################################");
    var payload = new LocationRequest(location, date.toString());
    String response = restTemplate.postForObject(LOCATION_AVAILABILITY_URL, payload, String.class);
    System.out.println("#####################################");
    System.out.println("Recevied data from locationAvailability");
    System.out.println(response);
    System.out.println("#####################################");

    return CompletableFuture.completedFuture(response);
  }

  static class LocationRequest {
    public String location;
    public String date; // NECESSARY use string

    public LocationRequest(String location, String date) {
      this.location = location;
      this.date = date;
    }

    public LocationRequest() {
    }
  }
}
