package com.example.locationavailability;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication(scanBasePackages = "com.example.locationavailability")
public class LocationAvailabilityApplication {
    public static void main(String[] args) {
        SpringApplication.run(LocationAvailabilityApplication.class, args);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RestController
public class TestController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}

}