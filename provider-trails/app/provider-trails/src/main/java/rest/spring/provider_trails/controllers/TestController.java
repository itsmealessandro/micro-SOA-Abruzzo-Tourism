package rest.spring.provider_trails.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import rest.spring.provider_trails.services.TrialService;

@RestController
public class TestController {
    
    private final TrialService trialService;
    
    @Autowired
    public TestController(TrialService trialService) {
        this.trialService = trialService;
    }
    
    @GetMapping("/test")
    public String testEndpoint() {
        return "Provider Trails Service is UP!";
    }
}