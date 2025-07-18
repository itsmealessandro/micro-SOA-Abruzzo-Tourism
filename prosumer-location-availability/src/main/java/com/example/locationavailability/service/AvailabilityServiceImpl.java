package com.example.locationavailability.service;

import java.util.concurrent.CompletableFuture;

import javax.xml.datatype.DatatypeFactory;

import org.springframework.stereotype.Service;

import com.example.locationavailability.client.weather.*;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {
    
    private final WeatherService weatherService;
    
    public AvailabilityServiceImpl(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
    
    @Override
    public CompletableFuture<Weather> checkWeather(String location, String date) {
        try {
            GetWeatherForecast request = new GetWeatherForecast();
            request.setLocation(location);
            request.setDate(DatatypeFactory.newInstance()
                .newXMLGregorianCalendar(date + "T00:00:00Z"));
            
            GetWeatherForecastResponse response = weatherService.getWeatherForecast(request);
            return CompletableFuture.completedFuture(toWeatherModel(response.getReturn()));
        } catch (Exception e) {
            throw new RuntimeException("Weather service error", e);
        }
    }
    
    private Weather toWeatherModel(WeatherType soapWeather) {
        Weather weather = new Weather();
        weather.setLocation(soapWeather.getLocation());
        weather.setDate(soapWeather.getDate().toGregorianCalendar().toInstant());
        weather.setConditions(soapWeather.getConditions());
        weather.setTemperature(soapWeather.getTemperature());
        weather.setHumidity(soapWeather.getHumidity());
        weather.setWindSpeed(soapWeather.getWindSpeed());
        weather.setSuitableForTrails(soapWeather.isSuitableForTrails());
        weather.setRecommendation(soapWeather.getRecommendation());
        return weather;
    }
}