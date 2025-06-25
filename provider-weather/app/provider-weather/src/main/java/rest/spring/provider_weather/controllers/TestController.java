package rest.spring.provider_weather.controllers;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.UnprocessableEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import rest.spring.provider_weather.exceptions.ImpossibleToPredictWeatherException;
import rest.spring.provider_weather.exceptions.LocationNotFoundException;
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

  /*
   * The Request returns the weather of the location if location and date are
   * valid
   */
  @GetMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_VALUE)
  // INFO: This is only for the swagger documentation
  @Operation(summary = "get weather by location, return null if weather is impossible to predict", description = "Returns the location's weather", responses = {
      @ApiResponse(responseCode = "200", description = "Successful response with location's weather"),
      @ApiResponse(responseCode = "404", description = "No locations found with that name"),
      @ApiResponse(responseCode = "400", description = "bad Request for date parameter"),
      @ApiResponse(responseCode = "422", description = "Date is outside the allowed 7-day range")
  })
  public ResponseEntity<?> getWeatherByLocation(@RequestParam String location,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {

    try {
      Weather weather;
      weather = weatherService.getWeatherByLocationAndDate(location, localDate);

      return ResponseEntity.ok(weather);
    } catch (LocationNotFoundException e) {
      e.printStackTrace();
      return ResponseEntity.notFound().build();
    } catch (ImpossibleToPredictWeatherException e) {
      return ResponseEntity
          .status(HttpStatus.UNPROCESSABLE_ENTITY)
          .body("Date is outside the allowed 7-day prediction range");
    }

  }

}
