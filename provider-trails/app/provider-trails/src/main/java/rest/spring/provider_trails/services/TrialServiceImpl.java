package rest.spring.provider_trails.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import rest.spring.provider_trails.model.Trail;
import rest.spring.provider_trails.model.WeatherAdaptability;

@Service
public class TrialServiceImpl implements TrialService {

  @Override
  public Set<Trail> getAllTrails() {
    Set<Trail> trails = new HashSet<>();

    Trail trail1 = new Trail();
    trail1.setName("Gran Sasso Loop");
    trail1.setLocation("Gran Sasso");
    trail1.setWeatherAdaptability(WeatherAdaptability.COPERTO);

    Trail trail2 = new Trail();
    trail2.setName("Campo Imperatore Ridge");
    trail2.setLocation("Campo Imperatore");
    trail2.setWeatherAdaptability(WeatherAdaptability.FANGOSO);

    Trail trail3 = new Trail();
    trail3.setName("Majella Trail");
    trail3.setLocation("Majella National Park");
    trail3.setWeatherAdaptability(WeatherAdaptability.ESPOSTO);

    Trail trail4 = new Trail();
    trail4.setName("Sirente Ring");
    trail4.setLocation("Sirente-Velino");
    trail4.setWeatherAdaptability(WeatherAdaptability.INVERNALE);

    Trail trail5 = new Trail();
    trail5.setName("Lake Campotosto Path");
    trail5.setLocation("Campotosto");
    trail5.setWeatherAdaptability(WeatherAdaptability.COPERTO);

    trails.add(trail1);
    trails.add(trail2);
    trails.add(trail3);
    trails.add(trail4);
    trails.add(trail5);

    return trails;
  }

}
