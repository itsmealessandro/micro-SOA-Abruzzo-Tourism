package rest.spring.provider_weather.services;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import rest.spring.provider_weather.exceptions.ImpossibleToPredictWeatherException;
import rest.spring.provider_weather.exceptions.LocationNotFoundException;
import rest.spring.provider_weather.model.Weather;

@Service
public class WeatherServiceImpl implements WeatherService {

  final Set<String> locations = new HashSet<>();

  public WeatherServiceImpl() {

    locations.add("Gran Sasso");
    locations.add("Campo Imperatore");
    locations.add("Majella");
    locations.add("Sirente");
    locations.add("Campotosto");
  }

  @Override
  public Set<String> getAllLocations() {

    return locations;
  }

  @Override
  public Weather getWeatherByLocationAndDate(String location, LocalDate localDate)
      throws LocationNotFoundException, ImpossibleToPredictWeatherException {
    LocalDate today = LocalDate.now();
    LocalDate maxDate = today.plusDays(7);
    if (localDate.isAfter(maxDate)) {
      throw new ImpossibleToPredictWeatherException();
    }
    if (locations.contains(location) == false) {
      throw new LocationNotFoundException();
    }

    // NOTE:
    // This procedure will invent credible data based on where and when is the
    // request
    long daysFromToday = java.time.temporal.ChronoUnit.DAYS.between(today, localDate);

    Weather weather = new Weather();

    weather.setLocation(location);
    weather.setDate(localDate);

    int baseTemp;
    switch (location) {
      case "Gran Sasso":
        baseTemp = 10;
        break;
      case "Campo Imperatore":
        baseTemp = 12;
        break;
      case "Majella":
        baseTemp = 8;
        break;
      case "Sirente":
        baseTemp = 9;
        break;
      case "Campotosto":
        baseTemp = 7;
        break;
      default:
        baseTemp = 10;
    }

    // Simulo temperatura minima e massima con variazioni casuali e in base al
    // giorno
    int tempMin = baseTemp - 5 + (int) (Math.random() * 4) - (int) daysFromToday;
    int tempMax = baseTemp + 5 + (int) (Math.random() * 5) - (int) daysFromToday;

    Weather.Temperature temperature = new Weather.Temperature();
    temperature.setMin(tempMin);
    temperature.setMax(tempMax);
    weather.setTemperature(temperature);

    // Umidità random tra 50 e 90%
    int humidity = 50 + (int) (Math.random() * 40);
    weather.setHumidity(humidity);

    // Vento: velocità random da 0 a 30 km/h, direzione casuale
    String[] directions = { "N", "NE", "E", "SE", "S", "SW", "W", "NW" };
    int windSpeed = (int) (Math.random() * 31);
    String windDirection = directions[(int) (Math.random() * directions.length)];
    Weather.Wind wind = new Weather.Wind();
    wind.setSpeed(windSpeed);
    wind.setDirection(windDirection);
    weather.setWind(wind);

    // Condizioni meteo scelte randomicamente
    String[] conditions = { "Sunny", "Partly Cloudy", "Cloudy", "Rain", "Thunderstorm" };
    String condition = conditions[(int) (Math.random() * conditions.length)];
    weather.setCondition(condition);

    // Alert in base a condizioni e vento
    Weather.Alert alert;
    if ("Thunderstorm".equals(condition) || windSpeed > 25) {
      alert = new Weather.Alert();
      alert.setMessage("Severe weather warning: thunderstorm or strong wind");
      alert.setType("high");
    } else if ("Rain".equals(condition)) {
      alert = new Weather.Alert();
      alert.setMessage("Rain expected, drive carefully");
      alert.setType("moderate");
    } else {
      alert = new Weather.Alert();
      alert.setMessage("NO alerts");
      alert.setType("none");
    }
    weather.setAlert(alert);

    // Consiglio basato sulla condizione
    String advice;
    switch (condition) {
      case "Sunny":
        advice = "Wear sunglasses and sunscreen";
        break;
      case "Rain":
        advice = "Carry an umbrella";
        break;
      case "Thunderstorm":
        advice = "Stay indoors and avoid open areas";
        break;
      case "Cloudy":
        advice = "A pleasant day, light clothes recommended";
        break;
      default:
        advice = "Have a nice day";
        break;
    }
    weather.setAdvice(advice);
    weather.setLocation(location);
    return weather;
  }

}
