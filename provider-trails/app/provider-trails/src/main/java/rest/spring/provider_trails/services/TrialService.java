package rest.spring.provider_trails.services;

import java.util.List;

import rest.spring.provider_trails.model.Trail;

public interface TrialService {
  List<Trail> getTrailsByLocation(String location);

  Trail getTrailById(String id);

  List<Trail> getAllTrails(); // Aggiunto questo metodo
}
