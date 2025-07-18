package rest.spring.provider_weather.controllers;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.xml.ws.Endpoint;
import rest.spring.provider_weather.services.WeatherService;

@Configuration
public class HomeController {
    
    @Autowired
    private Bus bus;
    
    @Autowired
    private WeatherService weatherService;
    
    @Bean
    public Endpoint weatherEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, weatherService);
        endpoint.publish("/weather");
        return endpoint;
    }

        
    @GetMapping("/test")
    public String testEndpoint() {
        return "Provider Weather Service is UP!";
    }
}