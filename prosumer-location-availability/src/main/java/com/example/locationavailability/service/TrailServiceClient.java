package com.example.locationavailability.service;

import com.example.locationavailability.model.TrailAvailability;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TrailServiceClient {

  private final RestTemplate restTemplate;

  @Value("${trail.service.url}")
  private String trailServiceUrl;

  public TrailServiceClient(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public List<TrailAvailability> getTrailsByLocation(String location) {
    TrailAvailability[] trails = restTemplate.getForObject(
        trailServiceUrl,
        TrailAvailability[].class,
        location);
    System.out.println("#########################################");
    System.out.println("TRAILS RESPONSE");
    System.out.println(trails);
    System.out.println("#########################################");
    return Arrays.asList(trails != null ? trails : new TrailAvailability[0]);
  }
}
