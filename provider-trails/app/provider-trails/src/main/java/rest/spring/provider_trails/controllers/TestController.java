package rest.spring.provider_trails.controllers;

import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import rest.spring.provider_trails.model.Trail;
import rest.spring.provider_trails.services.TrialService;

@RestController
@RequestMapping("/api")
public class TestController {
  final TrialService trialService;

  // NOTE: IOC paradigm
  public TestController(TrialService trialService) {
    this.trialService = trialService;

  }

  @GetMapping(value = "/trails", produces = MediaType.APPLICATION_JSON_VALUE)
  // INFO: This is only for the swagger documentation
  @Operation(summary = "Get all trails", description = "Returns a list of available trails in Abruzzo", responses = {
      @ApiResponse(responseCode = "200", description = "Successful response with list of trails"),
      @ApiResponse(responseCode = "404", description = "No trails found")
  })
  public ResponseEntity<Set<Trail>> getAllTrails() {
    Set<Trail> trails = null;
    trails = trialService.getAllTrails();

    if (trails == null || trails.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(trails);

  }

}
