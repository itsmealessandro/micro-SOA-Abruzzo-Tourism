package rest.spring.provider_weather.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @GetMapping("/test")
    public String testEndpoint() {
        return "Provider Weather Service is UP!";
    }
}