package rest.spring.provider_weather.controllers;

import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import rest.spring.provider_weather.model.Weather;
import rest.spring.provider_weather.services.WeatherService;

@RestController
@RequestMapping("/weather_api")
public class TestController {
  final WeatherService weatherService;

  // NOTE: IOC paradigm
  public TestController(WeatherService weatherService) {
    this.weatherService = weatherService;

  }

  @GetMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_VALUE)
  // INFO: This is only for the swagger documentation
  @Operation(summary = "get weather by location", description = "Returns the location's weather", responses = {
      @ApiResponse(responseCode = "200", description = "Successful response with location's weather"),
      @ApiResponse(responseCode = "404", description = "No locations found with that name")
  })
  public ResponseEntity<Weather> getWeatherByLocation(@RequestParam String location) {
    Set<String> locations = weatherService.getAllLocations();

    if (location == null) {
      return ResponseEntity.notFound().build();
    }
    if (locations.contains(location) == false) {
      return ResponseEntity.notFound().build();
    }

    Weather weather = weatherService.getWeatherByLocation(location);

    return ResponseEntity.ok(weather);

  }

}
