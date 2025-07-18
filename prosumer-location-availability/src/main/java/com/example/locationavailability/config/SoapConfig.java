package com.example.locationavailability.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapConfig {
    
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example.locationavailability.client.weather");
        return marshaller;
    }
    
    @Bean
    public WeatherService weatherService(Jaxb2Marshaller marshaller) {
        WeatherService_Service service = new WeatherService_Service();
        WeatherService port = service.getWeatherServicePort();
        ((BindingProvider)port).getRequestContext()
            .put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, 
                 "http://provider-weather:8084/soap-api/weather");
        return port;
    }
}