package rest.spring.provider_trails.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import rest.spring.provider_trails.model.Trail;
import rest.spring.provider_trails.model.WeatherAdaptability;

@Service
public class TrialServiceImpl implements TrialService {

  private final Map<String, Trail> trailsDatabase = new ConcurrentHashMap<>();

  @PostConstruct
  public void init() {
    // Dati di esempio per i borghi abruzzesi
    List<String> features1 = Arrays.asList("panoramico", "storico", "familiare");
    Trail trail1 = new Trail("T1", "Sentiero del Lupo", "Roccascalegna", 8.5, 3, features1);
    trail1.setWeatherAdaptability(WeatherAdaptability.COPERTO);

    List<String> features2 = Arrays.asList("archeologico", "culturale", "difficile");
    Trail trail2 = new Trail("T2", "Via dei Templi", "Sulmona", 12.0, 4, features2);
    trail2.setWeatherAdaptability(WeatherAdaptability.IMPERMEABILE);

    List<String> features3 = Arrays.asList("lacustre", "naturale", "facile");
    Trail trail3 = new Trail("T3", "Lago di Scanno", "Scanno", 5.0, 2, features3);
    trail2.setWeatherAdaptability(WeatherAdaptability.SCOPERTO);

    trailsDatabase.put(trail1.getId(), trail1);
    trailsDatabase.put(trail2.getId(), trail2);
    trailsDatabase.put(trail3.getId(), trail3);
  }

  @Override
  public List<Trail> getTrailsByLocation(String location) {
    List<Trail> result = new ArrayList<>();
    for (Trail trail : trailsDatabase.values()) {
      if (trail.getLocation().equalsIgnoreCase(location)) {
        result.add(trail);
      }
    }
    return result;
  }

  @Override
  public Trail getTrailById(String id) {
    return trailsDatabase.get(id);
  }

  @Override
  public List<Trail> getAllTrails() {
    return new ArrayList<>(trailsDatabase.values());
  }
}
