package rest.spring.provider_weather.services;

import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

import rest.spring.provider_weather.exceptions.ImpossibleToPredictWeatherException;
import rest.spring.provider_weather.exceptions.LocationNotFoundException;
import rest.spring.provider_weather.model.Weather;

@Service
public class WeatherServiceImpl implements WeatherService {

  @Override
  public Weather getWeatherForecast(String location, Date date)
      throws LocationNotFoundException, ImpossibleToPredictWeatherException {
    System.out.println("creating weather for location:" + location + " and date:" + date);

    // Weather weather = weatherDatabase.get(location).get(dateKey);
    // Generatore di valori casuali
    Random random = new Random();

    String[] possibleConditions = {
        "Sunny", "Cloudy", "Rainy", "Stormy", "Foggy", "Snowy", "Windy"
    };
    String[] possibleRecommendations = {
        "Great day for a hike!",
        "Consider indoor activities.",
        "Bring an umbrella.",
        "Avoid outdoor activities.",
        "Perfect weather for trails.",
        "Too dangerous for hiking today."
    };

    String conditions = possibleConditions[random.nextInt(possibleConditions.length)];
    double temperature = 10 + (30 * random.nextDouble()); // 10–40 °C
    double humidity = 30 + (60 * random.nextDouble()); // 30–90%
    double windSpeed = 0 + (20 * random.nextDouble()); // 0–20 km/h
    boolean suitableForTrails = random.nextBoolean();
    String recommendation = possibleRecommendations[random.nextInt(possibleRecommendations.length)];

    Weather weather = new Weather(
        location,
        date,
        conditions,
        temperature,
        humidity,
        windSpeed,
        suitableForTrails,
        recommendation);

    return weather;
  }
}
