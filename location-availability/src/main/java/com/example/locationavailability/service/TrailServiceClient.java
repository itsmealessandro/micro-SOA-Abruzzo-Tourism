package com.example.locationavailability.service;

import com.example.locationavailability.model.Trail;
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

  public List<Trail> getTrailsByLocation(String location) {
    Trail[] trails = restTemplate.getForObject(
        trailServiceUrl,
        Trail[].class,
        location);
    System.out.println("#########################################");
    System.out.println("TRAILS RESPONSE");
    for (Trail trail : trails) {

      System.out.println(trail);

    }
    System.out.println("#########################################");
    return Arrays.asList(trails);
  }
}
