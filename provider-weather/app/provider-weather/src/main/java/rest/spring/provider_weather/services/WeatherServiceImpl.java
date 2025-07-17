package rest.spring.provider_weather.services;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import rest.spring.provider_weather.exceptions.ImpossibleToPredictWeatherException;
import rest.spring.provider_weather.exceptions.LocationNotFoundException;
import rest.spring.provider_weather.model.Weather;

@Service
public class WeatherServiceImpl implements WeatherService {
    
    private final Map<String, Map<Long, Weather>> weatherDatabase = new ConcurrentHashMap<>();
    
    @PostConstruct
    public void init() {
        addWeatherData("Sulmona", new Date(2023 - 1900, 4, 1), 
                       "Sunny", 22.5, 45.0, 10.0, true, "Perfect day for hiking");
        
        addWeatherData("Roccascalegna", new Date(2023 - 1900, 4, 1), 
                       "Cloudy", 18.0, 60.0, 15.0, true, "Good conditions for trails");
        
        addWeatherData("Scanno", new Date(2023 - 1900, 4, 1), 
                       "Rainy", 12.0, 85.0, 25.0, false, "Not recommended for outdoor activities");
    }
    
    private void addWeatherData(String location, Date date, String conditions, 
                               double temperature, double humidity, 
                               double windSpeed, boolean suitable, String recommendation) {
        Weather weather = new Weather(location, date, conditions, temperature, 
                                     humidity, windSpeed, suitable, recommendation);
        
        long dateKey = date.getTime();
        weatherDatabase.computeIfAbsent(location, k -> new ConcurrentHashMap<>())
                      .put(dateKey, weather);
    }

    @Override
    public Weather getWeatherForecast(String location, Date date) 
        throws LocationNotFoundException, ImpossibleToPredictWeatherException {
        
        if (!weatherDatabase.containsKey(location)) {
            throw new LocationNotFoundException("Location not found: " + location);
        }
        
        long dateKey = date.getTime();
        Weather forecast = weatherDatabase.get(location).get(dateKey);
        
        if (forecast == null) {
            throw new ImpossibleToPredictWeatherException(
                "No forecast available for " + location + " on " + date
            );
        }
        
        return forecast;
    }
}