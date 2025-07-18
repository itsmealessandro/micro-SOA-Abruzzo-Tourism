package rest.spring.provider_trails.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rest.spring.provider_trails.model.Trail;
import rest.spring.provider_trails.services.TrialService;

@RestController
@RequestMapping("/trails")
public class HomeController {

  private final TrialService trialService;

  @Autowired
  public HomeController(TrialService trialService) {
    this.trialService = trialService;
  }

  @GetMapping
  public List<Trail> getTrailsByLocation(@RequestParam String location) {
    System.out.println("Received request for trails with location " + location);
    return trialService.getTrailsByLocation(location);
  }

  @GetMapping("/{id}")
  public Trail getTrailById(@PathVariable String id) {
    return trialService.getTrailById(id);
  }

  @PutMapping("/{id}/weather-adapt")
  public void updateWeatherAdaptability(
      @PathVariable String id,
      @RequestParam boolean suitable,
      @RequestParam String reason,
      @RequestParam String recommendation) {
  }
}
