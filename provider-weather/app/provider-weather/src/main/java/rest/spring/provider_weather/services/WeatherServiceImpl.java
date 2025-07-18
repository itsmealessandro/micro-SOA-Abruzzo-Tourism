package rest.spring.provider_weather.services;

import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

import rest.spring.provider_weather.exceptions.ImpossibleToPredictWeatherException;
import rest.spring.provider_weather.exceptions.LocationNotFoundException;
import rest.spring.provider_weather.model.Weather;
import rest.spring.provider_weather.model.WeatherCondition;

@Service
public class WeatherServiceImpl implements WeatherService {

  @Override
  public Weather getWeatherForecast(String location, Date date)
      throws LocationNotFoundException, ImpossibleToPredictWeatherException {
    System.out.println("creating weather for location:" + location + " and date:" + date);

    // Weather weather = weatherDatabase.get(location).get(dateKey);
    // Generatore di valori casuali
    Random random = new Random();

    WeatherCondition[] possibleConditions = WeatherCondition.values();

    WeatherCondition weatherCondition = possibleConditions[random.nextInt(possibleConditions.length)];
    double temperature = 10 + (30 * random.nextDouble()); // 10–40 °C
    double humidity = 30 + (60 * random.nextDouble()); // 30–90%
    double windSpeed = 0 + (20 * random.nextDouble()); // 0–20 km/h

    Weather weather = new Weather();
    weather.setWeatherCondition(weatherCondition);
    weather.setTemperature(temperature);
    weather.setLocation(location);
    weather.setHumidity(humidity);
    weather.setWindSpeed(windSpeed);
    weather.setDate(date);

    System.out.println("################################");
    System.out.println("Weather created");
    System.out.println(weather);
    System.out.println("################################");

    return weather;
  }
}
