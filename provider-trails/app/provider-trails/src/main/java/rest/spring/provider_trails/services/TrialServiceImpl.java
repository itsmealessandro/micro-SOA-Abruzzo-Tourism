package rest.spring.provider_trails.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import rest.spring.provider_trails.model.Trail;

@Service
public class TrialServiceImpl implements TrialService {

  @Override
  public Set<Trail> getAllTrails() {
    Set<Trail> trails = new HashSet<>();

    Trail trail1 = new Trail();
    trail1.setName("Gran Sasso Loop");
    trail1.setLocation("Gran Sasso");

    Trail trail2 = new Trail();
    trail2.setName("Campo Imperatore Ridge");
    trail2.setLocation("Campo Imperatore");

    Trail trail3 = new Trail();
    trail3.setName("Majella Trail");
    trail3.setLocation("Majella National Park");

    Trail trail4 = new Trail();
    trail4.setName("Sirente Ring");
    trail4.setLocation("Sirente-Velino");

    Trail trail5 = new Trail();
    trail5.setName("Lake Campotosto Path");
    trail5.setLocation("Campotosto");

    trails.add(trail1);
    trails.add(trail2);
    trails.add(trail3);
    trails.add(trail4);
    trails.add(trail5);

    return trails;
  }

}
